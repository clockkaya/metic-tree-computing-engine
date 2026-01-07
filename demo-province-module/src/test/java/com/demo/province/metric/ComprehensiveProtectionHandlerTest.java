package com.sama.ledger.metric;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.handlers.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 2 综合防护处理器
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 13:46
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ComprehensiveProtectionHandlerTest {

    private static final Logger logger = LoggerFactory.getLogger(ComprehensiveProtectionHandlerTest.class);

    @Resource
    ComprehensiveProtectionHandler comprehensiveProtectionHandler;

    @Resource
    ScenarioAptAttackProtectionHandler scenarioAptAttackProtectionHandler;

    @Resource
    ScenarioRansomwareProtectionHandler scenarioRansomwareProtectionHandler;

    @Resource
    ScenarioHwSpecialProtectionHandler scenarioHwSpecialProtectionHandler;

    @Resource
    ScenarioExposureProtectionHandler scenarioExposureProtectionHandler;

    @Test
    public void allBenefitHandlersTest(){
        ComprehensiveProtectionPreparedDataBO customData = ComprehensiveProtectionCalculatorTest.customData();
        customData.setMetricType(MetricTypeEnum.COMPREHENSIVE_PROTECTION.getType());
        comprehensiveProtectionHandler.recursiveHandle(customData);
        // scenarioAptAttackProtectionHandler.recursiveHandle(customData);
        // scenarioRansomwareProtectionHandler.recursiveHandle(customData);
        // scenarioHwSpecialProtectionHandler.recursiveHandle(customData);
        // scenarioExposureProtectionHandler.recursiveHandle(customData);
    }

}
