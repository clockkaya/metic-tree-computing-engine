package com.sama.ledger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 一式多份
 * @author: huxh
 * @description:
 * @datetime: 2025/10/10 16:53
 */
@RefreshScope
@Component
public class NacosConfig {

    /**
     * 部署方，用以区分省侧、集团侧
     */
    @Value("${message.deployment:localhost}")
    private String deployment;

    @Value("${message.group-servers:kafka01:19091}")
    private String groupServers;

    @Value("${message.topic.globalMetricAvg:global_metric_avg}")
    private String topicMetricAvg;

    @Value("${message.topic.metricCopy:metric_result_copy}")
    private String topicMetricResultCopy;

    @Value("${metric.statisticYear:0}")
    private Integer statisticYear;

    //==============================================================================
    // 通用项（省侧）
    //==============================================================================

    @Value("${component.kafka.security-enabled:true}")
    private Boolean securityEnabled;

    @Value("${component.kafka.sasl-mechanism:PLAIN}")
    private String kafkaSaslMechanism;

    @Value("${component.kafka.security-protocol:SASL_PLAINTEXT}")
    private String kafkaSecurityProtocol;

    @Value("${component.kafka.sasl-jaas-config:org.apache.kafka.common.security.plain.PlainLoginModule;}")
    private String kafkaSaslJaasConfig;

    public String getDeployment() {
        return deployment;
    }

    public String getGroupServers() {
        return groupServers;
    }

    public String getTopicMetricAvg() {
        return topicMetricAvg;
    }

    public String getTopicMetricResultCopy() {
        return topicMetricResultCopy;
    }

    public Integer getStatisticYear() {
        return statisticYear;
    }

    public Boolean getSecurityEnabled() {
        return securityEnabled;
    }

    @Override
    public String toString() {
        return "NacosConfig{" +
            "deployment='" + deployment + '\'' +
            ", groupServers='" + groupServers + '\'' +
            ", topicMetricAvg='" + topicMetricAvg + '\'' +
            ", topicMetricResultCopy='" + topicMetricResultCopy + '\'' +
            ", efficiencyYear=" + statisticYear +
            ", securityEnabled=" + securityEnabled +
            '}';
    }

    public String extractConsumerId() {
        return "metric_" + deployment;
    }

    /**
     * 获取 Kafka 安全属性
     * @return  Map<String, String>
     */
    public Map<String, String> extractKafkaSecurityProperties() {
        Map<String, String> secProps = new HashMap<>();
        if (!securityEnabled) {
            return secProps;
        }
        // 注意是点号而不是连字符
        secProps.put("sasl.mechanism", kafkaSaslMechanism);
        secProps.put("security.protocol", kafkaSecurityProtocol);
        secProps.put("sasl.jaas.config", kafkaSaslJaasConfig);
        return secProps;
    }
}
