package com.sama.analytic.metric.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sama.analytic.config.NacosConfig;
import com.sama.analytic.service.GroupStatisticService;
import com.sama.analytic.service.MetricResultUnionService;
import com.sama.analytic.utils.GroceryUtils;
import com.sama.analytic.utils.KafkaClientUtils;
import com.sama.analytic.utils.MetricResultKit;
import com.sama.api.ledger.bean.GroupStatisticDO;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitConstants;
import com.sama.api.ledger.bean.indicator.MetricBenefitL2CalculatorEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitProvinceCategoryEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.BenefitThresholdMap;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.ThresholdPair;
import com.sama.api.ledger.bean.structure.CategorizedThresholdPair;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedLinkedHashMap;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 集团侧消息推送
 * @author: huxh
 * @description: 详细参见《交互流程》中 Group -> Province 时，集团侧作为推送端的处理（此处实际发向集团侧 Kafka）
 * @datetime: 2025/8/15 9:24
 */
@Service
public class GroupMessagePushService {

    private static final Logger logger = LogManager.getLogger(GroupMessagePushService.class);

    private static final String LOG_HEADER = "【集团侧|推送端】 ";

    /**
     * 集团侧 Kafka
     */
    private KafkaProducer<String, String> groupProducer;

    @Resource(name = "bypass")
    ThreadPoolTaskExecutor bypass;

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    @Resource
    NacosConfig nacosConfig;

    @Resource
    MetricResultUnionService metricResultUnionService;

    @Resource
    GroupVisualizationServiceImpl groupVisualizationService;

    @Resource
    GroupStatisticService groupStatisticService;

    public KafkaProducer<String, String> getGroupProducer() {
        return groupProducer;
    }

    @PostConstruct
    private void init(){
        // 定义 groupProducer
        String groupServers = nacosConfig.getGroupServers();
        Map<String, String> extraProps = nacosConfig.extractKafkaSecurityProperties();
        groupProducer = KafkaClientUtils.createProducer(groupServers, extraProps);

        // 预创建 topic（无需后缀）
        bypass.execute(() -> {
            AdminClient groupAdmin = KafkaClientUtils.createAdminClient(groupServers, extraProps);
            KafkaClientUtils.createTopicAndWait(groupAdmin, MetricConstants.KafkaTopic.GROUP_STATISTIC);
        });
    }

    /**
     * 编制并推送效益类间阈值组
     */
    public void composeAndSendBenefitThresholdMap(){
        // 1 编制
        List<MetricResultUnionDO> allProvinceResults = metricResultUnionService.getAlignedRecordsWithValidation(MetricTypeEnum.BENEFIT_DEV.getType(), null);
        // segment, loop for 86 (MetricBenefitL2CalculatorEnum.size) times
        BenefitThresholdMap benefitThresholdMap = Arrays.stream(MetricBenefitL2CalculatorEnum.values())
            .collect(BenefitThresholdMap::new,
                (map, calculatorEnum) -> map.put(calculatorEnum.getCalculatorEn(), categorizeAndCalculate(allProvinceResults, calculatorEnum)),
                BenefitThresholdMap::putAll);
        logger.info(LOG_HEADER + "BenefitThresholdMap 编制成功：{}", JSON.toJSONString(benefitThresholdMap));

        // 2 本地持久化
        GroupStatisticDO targetDO = new GroupStatisticDO();
        targetDO.setDimensionKey(MetricConstants.DimensionKey.THRESHOLD);
        targetDO.setRecord(JSON.toJSONString(benefitThresholdMap, JSONWriter.Feature.PrettyFormat));
        targetDO.setResultUids(MetricResultKit.extractUids(allProvinceResults));
        GroupStatisticDO addedDO = groupStatisticService.add(targetDO);
        logger.info(LOG_HEADER + "效益类间阈值组计入表 group_statistic 成功！");

        // 3 推送
        wrapperSend(MetricTypeEnum.BENEFIT_DEV, Collections.singletonList(addedDO));
    }

    private CategorizedThresholdPair categorizeAndCalculate(List<MetricResultUnionDO> allProvinceResults, MetricBenefitL2CalculatorEnum calculatorEnum) {
        MultiValuedMap<String, Number> categoryAndData = new ArrayListValuedLinkedHashMap<>();
        // categorize, loop for 31 (allProvinceResults.size) times
        allProvinceResults.forEach(provinceResult -> {
            String orgCn = orgCodeAndNameCache.get(provinceResult.getOrgCode());
            MetricResultNode calculatorNode = MetricResultKit.findNode(provinceResult.getRootNode(), calculatorEnum.getCalculatorEn());
            categoryAndData.put(MetricBenefitProvinceCategoryEnum.tellCategoryByAmbiguousOrgCn(orgCn), (Number) calculatorNode.extractAssessedValue());
        });

        // calculate, repeat for 4 (category's size) times
        ThresholdPair full = GroceryUtils.calculateThresholdPair(new ArrayList<>(categoryAndData.values()));
        ThresholdPair large = GroceryUtils.calculateThresholdPair(new ArrayList<>(categoryAndData.get(MetricBenefitConstants.LARGE)));
        ThresholdPair medium = GroceryUtils.calculateThresholdPair(new ArrayList<>(categoryAndData.get(MetricBenefitConstants.MEDIUM)));
        ThresholdPair small = GroceryUtils.calculateThresholdPair(new ArrayList<>(categoryAndData.get(MetricBenefitConstants.SMALL)));
        return new CategorizedThresholdPair(full, large, medium, small);
    }

    /**
     * 可视化并推送所有指标
     */
    public void visualizeAndSendMetric() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                try {
                    List<GroupStatisticDO> dataList = new ArrayList<>();
                    VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> comprehensiveProtectionVO = groupVisualizationService.zoomComprehensiveProtection();
                    transformAndLoad(MetricConstants.DimensionKey.COMPREHENSIVE_PROTECTION, comprehensiveProtectionVO, dataList);
                    List<GroupStatisticDO> addedList = groupStatisticService.insertAndQueryBatch(dataList);
                    logger.info(LOG_HEADER + "{}可视化计入表 group_statistic 成功！", MetricTypeEnum.COMPREHENSIVE_PROTECTION.getName());
                    wrapperSend(MetricTypeEnum.COMPREHENSIVE_PROTECTION, addedList);
                } catch (Exception e) {
                    logger.error(e);
                }
            });

            executor.submit(() -> {
                try {
                    List<GroupStatisticDO> dataList = new ArrayList<>();
                    VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> benefitVO = groupVisualizationService.zoomBenefitOverview();
                    transformAndLoad(MetricConstants.DimensionKey.BENEFIT, benefitVO, dataList);
                    JSONObject internalVO = groupVisualizationService.zoomBenefitInternalConstruction();
                    transformAndLoad(MetricConstants.DimensionKey.BENEFIT_INTERNAL_CONSTRUCTION, internalVO, dataList);
                    JSONObject externalVO = groupVisualizationService.zoomBenefitExternalEmpowerment();
                    transformAndLoad(MetricConstants.DimensionKey.BENEFIT_EXTERNAL_EMPOWERMENT, externalVO, dataList);
                    List<GroupStatisticDO> addedList = groupStatisticService.insertAndQueryBatch(dataList);
                    logger.info(LOG_HEADER + "{}可视化计入表 group_statistic 成功！", MetricTypeEnum.BENEFIT_RELEASE.getName());
                    wrapperSend(MetricTypeEnum.BENEFIT_RELEASE, addedList);
                } catch (Exception e) {
                    logger.error(e);
                }
            });

            executor.submit(() -> {
                try {
                    List<GroupStatisticDO> dataList = new ArrayList<>();
                    VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> efficiencyVO = groupVisualizationService.zoomEfficiencyOverview();
                    transformAndLoad(MetricConstants.DimensionKey.EFFICIENCY, efficiencyVO, dataList);
                    VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> processVO = groupVisualizationService.zoomEfficiencyProgressSchedule();
                    transformAndLoad(MetricConstants.DimensionKey.EFFICIENCY_PROGRESS, processVO, dataList);
                    VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> investmentVO = groupVisualizationService.zoomEfficiencyInvestmentSchedule();
                    transformAndLoad(MetricConstants.DimensionKey.EFFICIENCY_INVESTMENT, investmentVO, dataList);
                    List<GroupStatisticDO> addedList = groupStatisticService.insertAndQueryBatch(dataList);
                    logger.info(LOG_HEADER + "{}可视化计入表 group_statistic 成功！", MetricTypeEnum.EFFICIENCY.getName());
                    wrapperSend(MetricTypeEnum.EFFICIENCY, addedList);
                } catch (Exception e) {
                    logger.error(e);
                }
            });
        }
    }

    private void transformAndLoad(String dimensionKey, Object targetVO, List<GroupStatisticDO> dataList){
        GroupStatisticDO targetDO = new GroupStatisticDO();
        targetDO.setDimensionKey(dimensionKey);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("joinResultUids");
        targetDO.setRecord(JSON.toJSONString(targetVO, filter, JSONWriter.Feature.PrettyFormat));
        String resultUids = null;
        if (targetVO instanceof VisualizedGroupBaseVO){
            resultUids = ((VisualizedGroupBaseVO<?>) targetVO).getJoinResultUids();
        } else if (targetVO instanceof JSONObject) {
            resultUids = ((JSONObject) targetVO).getString("joinResultUids");
        }
        targetDO.setResultUids(resultUids);
        dataList.add(targetDO);
    }

    private void wrapperSend(MetricTypeEnum metricTypeEnum, List<GroupStatisticDO> groupStatisticList) {
        Future<RecordMetadata> future = KafkaClientUtils.sendAsyncMessage(
            groupProducer,
            MetricConstants.KafkaTopic.GROUP_STATISTIC,
            metricTypeEnum.name(),
            JSON.toJSONString(groupStatisticList)
        );
        // future
    }

}
