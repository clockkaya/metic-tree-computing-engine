package com.sama.analytic.metric.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sama.analytic.service.MetricResultUnionService;
import com.sama.analytic.utils.GroceryUtils;
import com.sama.analytic.utils.MetricResultKit;
import com.sama.analytic.utils.NumberUtils;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.dto.*;
import com.sama.api.ledger.bean.indicator.MetricBenefitL1AssessmentEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitL2CalculatorEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.BasicChart;
import com.sama.api.ledger.bean.structure.BenefitInternalComplexChart;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.MetricVisualizedNode;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import com.sama.api.ledger.service.GroupVisualizationService;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedLinkedHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static com.sama.api.ledger.bean.enums.MetricTypeEnum.*;
import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;
import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.*;
import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.*;

/**
 * 集团侧可视化处理
 * @author: huxh
 * @description:
 * @datetime: 2025/8/25 14:36
 */
@Service
@RefreshScope
public class GroupVisualizationServiceImpl implements GroupVisualizationService {

    private static final Logger logger = LogManager.getLogger(GroupVisualizationServiceImpl.class);

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    @Resource
    MetricResultUnionService metricResultUnionService;

    /**
     * 通用数据处理
     *
     * @param metricType        指标类型
     * @param pageKeys          页面key名，第一个需为主key
     * @param detailFunction    #detail$Metric$ByProvince
     * @return                  VisualizedProvinceBaseVO
     * @param <T>               Statistic$Metric$ProvinceDTO
     */
    private <T> VisualizedGroupBaseVO<T> eltIntoVO(Integer metricType,
                                                   List<String> pageKeys,
                                                   Function<MetricResultUnionDO, List<T>> detailFunction) {
        // 1 extract
        List<MetricResultUnionDO> allProvinceResults = metricResultUnionService.getAlignedRecordsWithValidation(metricType, new Date());

        // 2 transform
        MultiValuedMap<String, BigDecimal> pageKeyAndScoreMultiValuedMap = new ArrayListValuedLinkedHashMap<>();
        List<String> orgCnList = new ArrayList<>();
        List<Object> totalScoreList = new ArrayList<>();
        Map<String, List<T>> provinceAndTableMap = new LinkedHashMap<>();

        allProvinceResults.forEach(provinceResult -> {
            String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
            MetricResultNode pageNode = MetricResultKit.findNode(provinceResult.getRootNode(), pageKeys.getFirst());
            // 2.1 取数
            pageKeys.forEach(pageKey -> {
                MetricResultNode subNode = MetricResultKit.findNode(pageNode, pageKey);
                pageKeyAndScoreMultiValuedMap.put(pageKey, subNode.getTotalScore());
            });
            // 2.2 取数
            orgCnList.add(orgCn);
            totalScoreList.add(NumberUtils.formatFlexibleConditions(pageNode.getTotalScore(), 2));
            // 2.3 省级表格（内遍历）
            provinceAndTableMap.put(orgCn, detailFunction.apply(provinceResult));
        });

        // 2.1 全网平均分
        LinkedHashMap<String, BigDecimal> pageKeyAndScoreMap = new LinkedHashMap<>();
        pageKeyAndScoreMultiValuedMap.asMap().forEach((subKey, scoreCollection) -> {
            // Collection 需要转换
            BigDecimal score = NumberUtils.calculateAverage(new ArrayList<>(scoreCollection));
            pageKeyAndScoreMap.put(subKey, score);
        });

        // 2.2 全网柱状图
        BasicChart scoreChart = new BasicChart();
        scoreChart.setxCategory(orgCnList);
        scoreChart.setyData(totalScoreList);

        // 3 load
        VisualizedGroupBaseVO<T> baseVO = new VisualizedGroupBaseVO<>();
        baseVO.setRefTime(new Date());
        baseVO.setPageKeyAndScoreMap(pageKeyAndScoreMap);
        baseVO.setScoreChart(scoreChart);
        baseVO.setProvinceAndTableMap(provinceAndTableMap);
        baseVO.setJoinResultUids(MetricResultKit.extractUids(allProvinceResults));
        baseVO.setJoinResults(allProvinceResults);

        return baseVO;
    }

    private <T> VisualizedGroupBaseVO<T> eltIntoVO(Integer metricType,
                                                   String pageKey,
                                                   Function<MetricResultUnionDO, List<T>> detailFunction) {
        return eltIntoVO(metricType, List.of(pageKey), detailFunction);
    }

    @Override
    public VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> zoomComprehensiveProtection() {
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO =
            eltIntoVO(
                COMPREHENSIVE_PROTECTION.getType(),
                // 用以省侧 #offerVisualizedComprehensiveProtectionChart
                List.of(I_COMPREHENSIVE_PROTECTION,
                    II_SCENARIO_APT_ATTACK_PROTECTION,
                    II_SCENARIO_RANSOMWARE_PROTECTION,
                    II_SCENARIO_HW_SPECIAL_PROTECTION,
                    II_SCENARIO_EXPOSURE_PROTECTION),
                this::detailComprehensiveProtectionByProvince
            );
        logger.info("【全网展示|综合防护】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticComprehensiveProtectionProvinceDTO> detailComprehensiveProtectionByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticComprehensiveProtectionProvinceDTO> tableItems = linkList.stream()
            .map(prettyLink -> StatisticComprehensiveProtectionProvinceDTO.instanceFormLinkDTO(orgCn, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> zoomBenefitOverview() {
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> targetVO =
            eltIntoVO(
                BENEFIT_RELEASE.getType(),
                List.of(I_BENEFIT, II_INTERNAL_CONSTRUCTION, II_EXTERNAL_EMPOWERMENT),
                this::detailBenefitOverviewByProvince
            );
        logger.info("【全网展示|效益评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticBenefitOverviewProvinceDTO> detailBenefitOverviewByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticBenefitOverviewProvinceDTO> tableItems = linkList.stream()
            .map(prettyLink -> StatisticBenefitOverviewProvinceDTO.instanceFormLinkDTO(orgCn, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public JSONObject zoomBenefitInternalConstruction() {
        // 1 通用处理
        VisualizedGroupBaseVO<StatisticBenefitInternalConstructionProvinceDTO> originalVO =
            eltIntoVO(
                BENEFIT_RELEASE.getType(),
                II_INTERNAL_CONSTRUCTION,
                this::detailBenefitInternalConstructionByProvince
            );

        // 2 复杂图处理
        List<MetricResultUnionDO> allProvinceResults = originalVO.getJoinResults();
        BenefitInternalComplexChart defenseInDepthCapabilityChart = restructureBenefitInternalConstructionChart(allProvinceResults, MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY);
        BenefitInternalComplexChart complianceCapabilityChart = restructureBenefitInternalConstructionChart(allProvinceResults, MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY);
        BenefitInternalComplexChart complianceSoftwareChart = restructureBenefitInternalConstructionChart(allProvinceResults, MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE);
        BenefitInternalComplexChart operationSystemChart = restructureBenefitInternalConstructionChart(allProvinceResults, MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM);

        // 3 直接序列化
        JSONObject targetVO = GroceryUtils.javaBean2Json(originalVO);
        targetVO.put("defenseInDepthCapabilityChart", defenseInDepthCapabilityChart);
        targetVO.put("complianceCapabilityChart", complianceCapabilityChart);
        targetVO.put("complianceSoftwareChart", complianceSoftwareChart);
        targetVO.put("operationSystemChart", operationSystemChart);
        logger.info("【全网展示|对内建设】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticBenefitInternalConstructionProvinceDTO> detailBenefitInternalConstructionByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticBenefitInternalConstructionProvinceDTO> frontTable = linkList.stream()
            // 确保过滤对应的 pageKey
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_INTERNAL_CONSTRUCTION::equals)
                .isPresent())
            .map(prettyLink -> StatisticBenefitInternalConstructionProvinceDTO.frontInstanceFormLinkDTO(orgCn, prettyLink))
            .distinct()
            .toList();

        return frontTable;
    }

    private BenefitInternalComplexChart restructureBenefitInternalConstructionChart(List<MetricResultUnionDO> allProvinceResults, MetricBenefitL1AssessmentEnum assessmentEnum){
        BenefitInternalComplexChart targetChart = new BenefitInternalComplexChart();
        try {
            List<String> orgCnList = new ArrayList<>();
            List<Object> scoreList = new ArrayList<>();
            BenefitInternalComplexChart.InnerChart innerChart = new BenefitInternalComplexChart.InnerChart();

            allProvinceResults.forEach(provinceResult -> {
                // 外层 chart 数据由 L1Assessment 对应的节点提取
                String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
                orgCnList.add(orgCn);
                MetricResultNode assessmentNode = MetricResultKit.findNode(provinceResult.getRootNode(), assessmentEnum.getAssessmentEn());
                // Attention！理解这里的处理逻辑是：单个 L2Calculator 的单位造价得单个评估分数，再向上汇总平均，而不是多个 L2Calculator 向上汇总平均再得汇总的评估分数
                // 事实上，无论哪种处理方式，此处都不可得两组数列
                scoreList.add(assessmentNode.getTotalScore());

                // 内层 chart 数据由 L2Calculator 对应的节点提取
                MetricBenefitL2CalculatorEnum.findByAssessmentEn(assessmentEnum.getAssessmentEn()).forEach(calculatorEnum -> {
                    MetricResultNode calculatorNode = MetricResultKit.findNode(assessmentNode, calculatorEnum.getCalculatorEn());
                    BigDecimal scalingValue = NumberUtils.formatFlexibleConditions(NumberUtils.safeConvertToBigDecimal(calculatorNode.extractAssessedValue()), (BigDecimal) null);
                    BigDecimal scalingScore = NumberUtils.formatFlexibleConditions(calculatorNode.getAssessedScore(), (BigDecimal) null);
                    innerChart.addData(orgCn, calculatorEnum.getCalculatorCn(), scalingValue, scalingScore);
                });
            });

            BenefitInternalComplexChart.OuterChart outerChart = new BenefitInternalComplexChart.OuterChart();
            outerChart.setxCategory(orgCnList);
            outerChart.setyData(Map.of("score", scoreList));

            targetChart.setOuterChart(outerChart);
            targetChart.setInnerChart(innerChart);
        } catch (Exception e) {
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
        }
        return targetChart;
    }

    @Override
    public JSONObject zoomBenefitExternalEmpowerment() {
        // 1 通用处理
        VisualizedGroupBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> originalVO =
            eltIntoVO(
                BENEFIT_RELEASE.getType(),
                II_EXTERNAL_EMPOWERMENT,
                this::detailBenefitExternalEmpowermentByProvince
            );

        // 2 图处理
        List<MetricResultUnionDO> allProvinceResults = originalVO.getJoinResults();
        BasicChart incomeChart = restructureBenefitExternalEmpowermentBasicChart(allProvinceResults);

        JSONObject targetVO = GroceryUtils.javaBean2Json(originalVO);
        targetVO.put("incomeChart", incomeChart);
        logger.info("【全网展示|对外赋能】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticBenefitExternalEmpowermentProvinceDTO> detailBenefitExternalEmpowermentByProvince(MetricResultUnionDO provinceResult) {
        // 先取出 assessedValueMap: { "threshold": }
        MetricResultNode leafNode = MetricResultKit.findNode(provinceResult.getRootNode(), IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO);
        Object threshold = Optional.ofNullable(leafNode.getAssessedValueMap())
            .flatMap(map -> Optional.ofNullable(map.get(MetricConstants.DimensionKey.THRESHOLD)))
            .orElse(null);

        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticBenefitExternalEmpowermentProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_EXTERNAL_EMPOWERMENT::equals)
                .isPresent())
            .map(prettyLink -> StatisticBenefitExternalEmpowermentProvinceDTO.instanceFormLinkDTO(orgCn, prettyLink, threshold))
            .distinct()
            .toList();

        return tableItems;
    }

    /**
     * 图：x轴组织名/y轴总收入
     *
     * @param allProvinceResults    所有组织结果
     * @return                      BasicChart
     */
    private BasicChart restructureBenefitExternalEmpowermentBasicChart(List<MetricResultUnionDO> allProvinceResults){
        List<String> orgCnList = new ArrayList<>();
        List<Object> valueList = new ArrayList<>();

        allProvinceResults.forEach(provinceResult -> {
            String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
            orgCnList.add(orgCn);
            MetricResultNode assessmentNode = MetricResultKit.findNode(provinceResult.getRootNode(), IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO);
            Object safetyTotalIncome =  Optional.ofNullable(assessmentNode)
                .map(MetricResultNode::getProcessingDataMap)
                .map(map -> map.get("当年安全科目总收入"))
                .orElse(null);
            valueList.add(safetyTotalIncome);
        });

        BasicChart basicChart = new BasicChart();
        basicChart.setxCategory(orgCnList);
        basicChart.setyData(valueList);
        return basicChart;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> zoomEfficiencyOverview() {
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO =
            eltIntoVO(
                EFFICIENCY.getType(),
                List.of(I_EFFICIENCY, II_SCHEDULE_PROGRESS, II_SCHEDULE_INVESTMENT, II_SCHEDULE_MANAGEMENT),
                this::detailEfficiencyOverviewByProvince
            );
        logger.info("【全网展示|效率评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyOverviewProvinceDTO> detailEfficiencyOverviewByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticEfficiencyOverviewProvinceDTO> tableItems = linkList.stream()
            .map(prettyLink -> StatisticEfficiencyOverviewProvinceDTO.instanceFormLinkDTO(orgCn, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyProgressSchedule() {
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO =
            eltIntoVO(
                EFFICIENCY.getType(),
                II_SCHEDULE_PROGRESS,
                this::detailEfficiencyProgressScheduleByProvince
            );
        logger.info("【全网展示|工程进度】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyScheduleProvinceDTO> detailEfficiencyProgressScheduleByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticEfficiencyScheduleProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_SCHEDULE_PROGRESS::equals)
                .isPresent())
            .map(prettyLink -> StatisticEfficiencyScheduleProvinceDTO.instanceFormLinkDTO(provinceResult.getOrgCode(), orgCn, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyInvestmentSchedule() {
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO =
            eltIntoVO(
                EFFICIENCY.getType(),
                II_SCHEDULE_INVESTMENT,
                this::detailEfficiencyInvestmentScheduleByProvince
            );
        logger.info("【全网展示|投资进度】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyScheduleProvinceDTO> detailEfficiencyInvestmentScheduleByProvince(MetricResultUnionDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
        List<StatisticEfficiencyScheduleProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_SCHEDULE_INVESTMENT::equals)
                .isPresent())
            .map(prettyLink -> StatisticEfficiencyScheduleProvinceDTO.instanceFormLinkDTO(provinceResult.getOrgCode(), orgCn, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

}
