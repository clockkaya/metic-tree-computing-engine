package com.sama.ledger.metric.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.GroupStatisticBakDO;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.dto.*;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.BasicChart;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.MetricVisualizedNode;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import com.sama.api.ledger.bean.vo.VisualizedProvinceBaseVO;
import com.sama.api.ledger.service.ProvinceVisualizationDubboService;
import com.sama.api.ledger.bean.indicator.MetricBenefitL1AssessmentEnum;
import com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionL1ScenarioEnum;
import com.sama.ledger.service.GroupStatisticBakService;
import com.sama.ledger.service.MetricResultService;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.MetricResultKit;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;
import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.I_COMPREHENSIVE_PROTECTION;
import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.*;

/**
 * 省侧可视化展示
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 17:15
 */
@DubboService
@RefreshScope
public class ProvinceVisualizationDubboServiceImpl implements ProvinceVisualizationDubboService {

    private static final Logger logger = LogManager.getLogger(ProvinceVisualizationDubboServiceImpl.class);

    @Resource
    MetricResultService metricResultService;

    @Resource
    GroupStatisticBakService groupStatisticBakService;

    /**
     * 通用数据处理
     *
     * @param orgCode           待获取组织code
     * @param metricType        指标类型
     * @param pageKeys          页面key名
     * @param detailFunction    #detail$Metric$ByProvince
     * @return                  VisualizedProvinceBaseVO
     * @param <T>               Statistic$Metric$ProvinceDTO
     */
    private <T> VisualizedProvinceBaseVO<T> eltIntoVO(String orgCode,
                                                      Integer metricType,
                                                      List<String> pageKeys,
                                                      Function<MetricResultDO, List<T>> detailFunction) {
        // 1 extract
        MetricResultDO result = metricResultService.getLatestRecordWithValidation(orgCode, metricType);

        // 2 transform
        // 2.1 取数
        LinkedHashMap<String, BigDecimal> pageKeyAndScoreMap = pageKeys.stream()
            .collect(Collectors.toMap(
                pageKey -> pageKey,
                pageKey -> {
                    MetricResultNode node = MetricResultKit.findNode(result.getRootNode(), pageKey);
                    return (node != null) ? node.getTotalScore() : null;
                },
                (existing, replacement) -> replacement,
                LinkedHashMap::new
            ));
        // 2.2 省级表格
        List<T> tableItems =  Collections.emptyList();
        if (detailFunction != null) {
            tableItems = detailFunction.apply(result);
        }

        // 3 load
        VisualizedProvinceBaseVO<T> baseVO = new VisualizedProvinceBaseVO<>();
        baseVO.setRefTime(result.getDataRefTime());
        baseVO.setPageKeyAndScoreMap(pageKeyAndScoreMap);
        baseVO.setTableItems(tableItems);
        baseVO.setJoinResult(result);

        return baseVO;
    }

    private <T> VisualizedProvinceBaseVO<T> eltIntoVO(String orgCode,
                                                      Integer metricType,
                                                      String pageKey,
                                                      Function<MetricResultDO, List<T>> detailFunction) {
        return eltIntoVO(orgCode, metricType, List.of(pageKey), detailFunction);
    }

    @Override
    public VisualizedProvinceBaseVO<StatisticComprehensiveProtectionProvinceDTO> displayComprehensiveProtection(String orgCode) {
        VisualizedProvinceBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.COMPREHENSIVE_PROTECTION.getType(),
                I_COMPREHENSIVE_PROTECTION,
                this::detailComprehensiveProtectionByProvince
            );
        logger.info("【省侧展示|综合防护】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticComprehensiveProtectionProvinceDTO> detailComprehensiveProtectionByProvince(MetricResultDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticComprehensiveProtectionProvinceDTO> tableItems = linkList.stream()
            // 特殊携参
            .map(prettyLink -> StatisticComprehensiveProtectionProvinceDTO.instanceFormLinkDTO(provinceResult.getOrgCode(), null, prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public JSONObject displayBenefitOverview(String orgCode) {
        // 1 通用处理
        VisualizedProvinceBaseVO<StatisticBenefitOverviewProvinceDTO> originalVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.BENEFIT_RELEASE.getType(),
                List.of(I_BENEFIT, II_INTERNAL_CONSTRUCTION, II_EXTERNAL_EMPOWERMENT),
                this::detailBenefitOverviewByProvince
            );

        // 2 简单图处理
        MetricResultDO result = originalVO.getJoinResult();
        BasicChart defenseInDepthCapabilityChart = restructureBenefitOverviewBasicChart(result.getRootNode(), MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY);
        BasicChart complianceCapabilityChart = restructureBenefitOverviewBasicChart(result.getRootNode(), MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY);
        BasicChart complianceSoftwareChart = restructureBenefitOverviewBasicChart(result.getRootNode(), MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE);
        BasicChart operationSystemChart = restructureBenefitOverviewBasicChart(result.getRootNode(), MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM);

        // 3 直接序列化
        JSONObject targetVO = GroceryUtils.javaBean2Json(originalVO);
        targetVO.put("defenseInDepthCapabilityChart", defenseInDepthCapabilityChart);
        targetVO.put("complianceCapabilityChart", complianceCapabilityChart);
        targetVO.put("complianceSoftwareChart", complianceSoftwareChart);
        targetVO.put("operationSystemChart", operationSystemChart);
        logger.info("【省侧展示|效益评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticBenefitOverviewProvinceDTO> detailBenefitOverviewByProvince(MetricResultDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticBenefitOverviewProvinceDTO> tableItems = linkList.stream()
            .map(prettyLink -> StatisticBenefitOverviewProvinceDTO.instanceFormLinkDTO("效益", prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    /**
     * 图：x轴算子中文名/y轴评估分数
     *
     * @param treeNode          结果树
     * @param assessmentEnum    MetricBenefitL1AssessmentEnum
     * @return                  BasicChart
     */
    private BasicChart restructureBenefitOverviewBasicChart(MetricResultNode treeNode, MetricBenefitL1AssessmentEnum assessmentEnum){
        // 1 取 L1AssessmentEnum 下对应 List<MetricResultNode>
        List<MetricResultNode> calculatorNodes = MetricResultKit.findNode(treeNode, assessmentEnum.getAssessmentEn()).getSubResultNodes();
        if (DataUtils.isEmpty(calculatorNodes)){
            return new BasicChart();
        }

        // 2 x/y轴
        List<String> keyCnList = new ArrayList<>();
        List<Object> assessedScoreList = new ArrayList<>();
        calculatorNodes.forEach(node -> {
            // 所有图处理统一逻辑：空值不过滤
            keyCnList.add(node.getKeyCn());
            assessedScoreList.add(node.getAssessedScore());
        });

        // 3 load
        BasicChart basicChart = new BasicChart();
        basicChart.setxCategory(keyCnList);
        basicChart.setyData(assessedScoreList);

        return basicChart;
    }

    @Override
    public JSONObject displayBenefitInternalConstruction(String orgCode) {
        // 1 通用处理
        VisualizedProvinceBaseVO<StatisticBenefitInternalConstructionProvinceDTO> originalVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.BENEFIT_RELEASE.getType(),
                II_INTERNAL_CONSTRUCTION,
                null
            );

        // 2 两表处理
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(originalVO.getJoinResult());
        List<StatisticBenefitInternalConstructionProvinceDTO> frontTable = linkList.stream()
            // 确保过滤对应的 pageKey
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_INTERNAL_CONSTRUCTION::equals)
                .isPresent())
            .map(prettyLink -> StatisticBenefitInternalConstructionProvinceDTO.frontInstanceFormLinkDTO("对内建设", prettyLink))
            .distinct()
            .toList();

        List<StatisticBenefitInternalConstructionProvinceDTO> backTable = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_INTERNAL_CONSTRUCTION::equals)
                .isPresent())
            .map(prettyLink -> StatisticBenefitInternalConstructionProvinceDTO.backInstanceFormLinkDTO(null, prettyLink))
            .distinct()
            .toList();

        // 3 直接序列化
        JSONObject targetVO = GroceryUtils.javaBean2Json(originalVO);
        targetVO.remove("tableItems");
        targetVO.put("frontTable", frontTable);
        targetVO.put("backTable", backTable);
        logger.info("【省侧展示|对内建设】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    @Override
    public VisualizedProvinceBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> displayBenefitExternalEmpowerment(String orgCode) {
        VisualizedProvinceBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> targetVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.BENEFIT_RELEASE.getType(),
                List.of(II_EXTERNAL_EMPOWERMENT),
                this::detailBenefitExternalEmpowermentByProvince
            );
        logger.info("【省侧展示|对外赋能】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticBenefitExternalEmpowermentProvinceDTO> detailBenefitExternalEmpowermentByProvince(MetricResultDO provinceResult) {
        // 先取出 assessedValueMap: { "threshold": }
        MetricResultNode leafNode = MetricResultKit.findNode(provinceResult.getRootNode(), IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO);
        Object threshold = Optional.ofNullable(leafNode.getAssessedValueMap())
            .flatMap(map -> Optional.ofNullable(map.get(MetricConstants.DimensionKey.THRESHOLD)))
            .orElse(null);

        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticBenefitExternalEmpowermentProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_EXTERNAL_EMPOWERMENT::equals)
                .isPresent())
            // 特殊
            .map(prettyLink -> StatisticBenefitExternalEmpowermentProvinceDTO.instanceFormLinkDTO("对外赋能", prettyLink, threshold))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedProvinceBaseVO<StatisticEfficiencyOverviewProvinceDTO> displayEfficiencyOverview(String orgCode) {
        VisualizedProvinceBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.EFFICIENCY.getType(),
                List.of(I_EFFICIENCY, II_SCHEDULE_PROGRESS, II_SCHEDULE_INVESTMENT, II_SCHEDULE_MANAGEMENT),
                this::detailEfficiencyOverviewByProvince
            );
        logger.info("【省侧展示|效率评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyOverviewProvinceDTO> detailEfficiencyOverviewByProvince(MetricResultDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticEfficiencyOverviewProvinceDTO> tableItems = linkList.stream()
            .map(prettyLink -> StatisticEfficiencyOverviewProvinceDTO.instanceFormLinkDTO("效率", prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> displayEfficiencyProgressSchedule(String orgCode) {
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.EFFICIENCY.getType(),
                II_SCHEDULE_PROGRESS,
                this::detailEfficiencyProgressScheduleByProvince
            );
        logger.info("【省侧展示|工程进度】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyScheduleProvinceDTO> detailEfficiencyProgressScheduleByProvince(MetricResultDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticEfficiencyScheduleProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_SCHEDULE_PROGRESS::equals)
                .isPresent())
            .map(prettyLink -> StatisticEfficiencyScheduleProvinceDTO.instanceFormLinkDTO("工程进度", prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> displayEfficiencyInvestmentSchedule(String orgCode) {
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO =
            eltIntoVO(
                orgCode,
                MetricTypeEnum.EFFICIENCY.getType(),
                II_SCHEDULE_INVESTMENT,
                this::detailEfficiencyInvestmentScheduleByProvince
            );
        logger.info("【省侧展示|投资进度】 处理成功，返回 VO: {}", JSON.toJSONString(targetVO));

        return targetVO;
    }

    private List<StatisticEfficiencyScheduleProvinceDTO> detailEfficiencyInvestmentScheduleByProvince(MetricResultDO provinceResult) {
        List<PrettyLinkDTO> linkList = MetricResultKit.composePrettyLinkList(provinceResult);
        List<StatisticEfficiencyScheduleProvinceDTO> tableItems = linkList.stream()
            .filter(prettyLink -> Optional.ofNullable(prettyLink.getNode2())
                .map(MetricVisualizedNode::getKeyEn)
                .filter(II_SCHEDULE_INVESTMENT::equals)
                .isPresent())
            .map(prettyLink -> StatisticEfficiencyScheduleProvinceDTO.instanceFormLinkDTO("投资进度", prettyLink))
            .distinct()
            .toList();

        return tableItems;
    }

    @Override
    public LineChartDTO offerVisualizedComprehensiveProtectionChart(String orgCode){
        List<String> scenarioCnList = new ArrayList<>();
        List<BigDecimal> provinceScoreList = new ArrayList<>();
        List<BigDecimal> groupScoreList = new ArrayList<>();

        try {
            // 1 extract
            // 省侧数据（最新）
            MetricResultDO provinceResult = metricResultService.getLatestRecordWithValidation(orgCode, MetricTypeEnum.COMPREHENSIVE_PROTECTION.getType());
            // 集团侧数据（当日）
            GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.COMPREHENSIVE_PROTECTION, null);
            VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
            Map<String, BigDecimal> groupAverageScoreMap = Optional.ofNullable(groupVO.getPageKeyAndScoreMap()).orElse(new LinkedHashMap<>());

            // 2 transform
            Arrays.stream(MetricComprehensiveProtectionL1ScenarioEnum.values())
                .forEach(scenarioEnum -> {
                    // x轴：综合场景类型
                    scenarioCnList.add(scenarioEnum.getScenarioCn());
                    // y轴：省侧分
                    MetricResultNode provinceNode = MetricResultKit.findNode(provinceResult.getRootNode(), scenarioEnum.getScenarioEn());
                    provinceScoreList.add(provinceNode.getTotalScore());
                    // y轴：集团侧平均分
                    groupScoreList.add(groupAverageScoreMap.get(scenarioEnum.getScenarioEn()));
                });
        } catch (Exception e) {
            logger.error("【省侧展示|特殊】 处理失败！", e);
        }

        // 3 load
        LineChartDTO.SeriesDTO selfSeries = new LineChartDTO.SeriesDTO("本省分数", provinceScoreList);
        LineChartDTO.SeriesDTO groupSeries = new LineChartDTO.SeriesDTO("全网平均分", groupScoreList);
        LineChartDTO lineChartDTO = new LineChartDTO("综合防护分数统计", scenarioCnList, Arrays.asList(selfSeries, groupSeries));
        logger.info("【省侧展示|特殊】 返回 VO: {}", JSON.toJSONString(lineChartDTO));

        return lineChartDTO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> zoomComprehensiveProtection() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.COMPREHENSIVE_PROTECTION, null);
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|综合防护】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> zoomBenefitOverview() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT, null);
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|效益评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public JSONObject zoomBenefitInternalConstruction() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT_INTERNAL_CONSTRUCTION, null);
        JSONObject groupVO = JSON.parseObject(groupStatistic.getRecord(), JSONObject.class);
        logger.info("【全网展示|对内建设】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public JSONObject zoomBenefitExternalEmpowerment() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT_EXTERNAL_EMPOWERMENT, null);
        JSONObject groupVO = JSON.parseObject(groupStatistic.getRecord(), JSONObject.class);
        logger.info("【全网展示|对外赋能】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> zoomEfficiencyOverview() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY, null);
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|效率评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyProgressSchedule() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY_PROGRESS, null);
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|工程进度】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyInvestmentSchedule() {
        GroupStatisticBakDO groupStatistic = groupStatisticBakService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY_INVESTMENT, null);
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|投资进度】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }
}
