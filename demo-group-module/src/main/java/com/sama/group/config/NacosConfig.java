package com.sama.analytic.config;

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

    @Value("${message.group-servers:kafka03:19093}")
    private String groupServers;

    //==============================================================================
    // 通用项（集团侧）
    //==============================================================================

    @Value("${spring.kafka.producer.security-enabled:true}")
    private Boolean securityEnabled;

    @Value("${spring.kafka.producer.sasl-mechanism:PLAIN}")
    private String kafkaSaslMechanism;

    @Value("${spring.kafka.producer.security-protocol:SASL_PLAINTEXT}")
    private String kafkaSecurityProtocol;

    @Value("${spring.kafka.producer.sasl-jaas-config:org.apache.kafka.common.security.plain.PlainLoginModule;}")
    private String kafkaSaslJaasConfig;

    public String getDeployment() {
        return deployment;
    }

    public String getGroupServers() {
        return groupServers;
    }

    public Boolean getSecurityEnabled() {
        return securityEnabled;
    }

    public String getKafkaSaslMechanism() {
        return kafkaSaslMechanism;
    }

    public String getKafkaSecurityProtocol() {
        return kafkaSecurityProtocol;
    }

    public String getKafkaSaslJaasConfig() {
        return kafkaSaslJaasConfig;
    }

    @Override
    public String toString() {
        return "NacosConfig{" +
            "deployment='" + deployment + '\'' +
            ", groupServers='" + groupServers + '\'' +
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
