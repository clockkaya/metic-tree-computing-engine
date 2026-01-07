package com.sama.ledger.metric.support;


import com.alibaba.fastjson2.JSON;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.ledger.config.NacosConfig;
import com.sama.ledger.metric.BenefitEngineServiceImpl;
import com.sama.ledger.metric.ComprehensiveProtectionEngineServiceImpl;
import com.sama.ledger.metric.EfficiencyEngineServiceImpl;
import com.sama.ledger.utils.KafkaClientUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 省侧消息推送
 * @author: huxh
 * @description: 详细参见《交互流程》中 Province -> Group 时，省侧作为推送端的处理
 * @datetime: 2025/8/15 9:24
 */
@Service
public class ProvinceMessagePushService {

    private static final Logger logger = LogManager.getLogger(ProvinceMessagePushService.class);

    private static final String LOG_HEADER = "【省侧|推送端】 ";

    /**
     * 集团侧 Kafka
     */
    private KafkaProducer<String, String> groupProducer;

    private String topicMetricResultProvince;

    @Resource(name = "bypass")
    ThreadPoolTaskExecutor bypass;

    @Resource
    NacosConfig nacosConfig;

    @Resource
    ComprehensiveProtectionEngineServiceImpl metricComprehensiveProtectionService;

    @Resource
    BenefitEngineServiceImpl metricBenefitProcessService;

    @Resource
    EfficiencyEngineServiceImpl metricEfficiencyProcessService;

    public KafkaProducer<String, String> getGroupProducer() {
        return groupProducer;
    }

    public String getTopicMetricResultProvince() {
        return topicMetricResultProvince;
    }

    @PostConstruct
    private void init(){
        // 定义 groupProducer
        String groupServers = nacosConfig.getGroupServers();
        Map<String, String> extraProps = nacosConfig.extractKafkaSecurityProperties();
        // extraProps.put(ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, "resolve_canonical_bootstrap_servers_only");
        groupProducer = KafkaClientUtils.createProducer(groupServers, extraProps);

        // topic 需加后缀区分
        topicMetricResultProvince = String.join("_", MetricConstants.KafkaTopic.METRIC_RESULT, nacosConfig.getDeployment());
        // 并预创建该 topic
        bypass.execute(() -> {
            AdminClient groupAdmin = KafkaClientUtils.createAdminClient(groupServers, extraProps);
            KafkaClientUtils.createTopicAndWait(groupAdmin, topicMetricResultProvince);
        });
    }

    /**
     * 处理并推送效益(DEV)
     *
     * @param updateMode    更新模式：0-强制更新，1-条件更新
     */
    public void processAndSendBenefitDev(Integer updateMode){
        List<MetricResultDO> results = metricBenefitProcessService.topProcessDev(updateMode);
        wrapperSend(MetricTypeEnum.BENEFIT_DEV, results);
    }

    /**
     * 处理并推送所有指标
     *
     * @param updateMode    更新模式：0-强制更新，1-条件更新
     */
    public void processAndSendMetric(Integer updateMode){
        // 虚拟线程
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                List<MetricResultDO> results = metricComprehensiveProtectionService.topProcess(updateMode);
                wrapperSend(MetricTypeEnum.COMPREHENSIVE_PROTECTION, results);
            });

            executor.submit(() -> {
                List<MetricResultDO> results = metricBenefitProcessService.topProcessRelease(updateMode);
                wrapperSend(MetricTypeEnum.BENEFIT_RELEASE, results);
            });

            executor.submit(() -> {
                List<MetricResultDO> results = metricEfficiencyProcessService.topProcess(updateMode);
                wrapperSend(MetricTypeEnum.EFFICIENCY, results);
            });
        }
    }

    private void wrapperSend(MetricTypeEnum metricTypeEnum, List<MetricResultDO> results) {
        Future<RecordMetadata> future = KafkaClientUtils.sendAsyncMessage(
            groupProducer,
            topicMetricResultProvince,
            metricTypeEnum.name(),
            JSON.toJSONString(results)
        );
        // future
    }

}
