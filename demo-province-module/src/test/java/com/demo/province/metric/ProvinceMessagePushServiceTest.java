package com.sama.ledger.metric;

import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.config.NacosConfig;
import com.sama.ledger.metric.support.ProvinceMessagePushService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/11 9:58
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ProvinceMessagePushServiceTest {

    private static final Logger logger = LogManager.getLogger(ProvinceMessagePushServiceTest.class);

    @Resource
    NacosConfig nacosConfig;

    @Resource
    ProvinceMessagePushService provinceMessagePushService;

    /**
     * Nacos 加载测试
     */
    @Test
    public void loadNacosConfigTest(){
        logger.info("加载 Nacos 配置: {}", nacosConfig.toString());
    }

    /**
     * 集团侧 kafka 连接测试
     */
    @Test
    public void connectGroupKakfaTest() throws InterruptedException {
        provinceMessagePushService.getGroupProducer().flush();
        Thread.sleep(30_000);
    }

    /**
     * 1/4
     * @throws InterruptedException
     */
    @Test
    public void processAndSendBenefitDevTest() throws InterruptedException {
        provinceMessagePushService.processAndSendBenefitDev(MetricConstants.UpdateMode.CONDITIONAL);
        Thread.sleep(30_000);
    }

    /**
     * 3/4
     * @throws InterruptedException
     */
    @Test
    public void processAndSendMetricTest() throws InterruptedException {
        provinceMessagePushService.processAndSendMetric(MetricConstants.UpdateMode.CONDITIONAL);
        Thread.sleep(30_000);
    }

}
