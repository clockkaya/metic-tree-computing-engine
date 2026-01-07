package com.sama.ledger.metric;

import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.handlers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 2 效益处理器
 * @author: huxh
 * @description: 
 * @datetime: 2025/9/5 17:10
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class BenefitProxyHandlerTest {

    private static final Logger logger = LogManager.getLogger(BenefitProxyHandlerTest.class);

    @Resource
    BenefitHandler benefitHandler;

    @Resource
    InternalConstructionHandler internalConstructionHandler;

    @Resource
    ExternalEmpowermentHandler externalEmpowermentHandler;

    @Resource
    ConstructionCostHandler constructionCostHandler;

    @Resource
    IncomeToInvestmentRatioHandler incomeToInvestmentRatioHandler;

    @Resource
    DefenseInDepthCapabilityHandler defenseInDepthCapabilityHandler;

    @Resource
    ComplianceCapabilityHandler complianceCapabilityHandler;

    @Resource
    ComplianceSoftwareHandler complianceSoftwareHandler;

    @Resource
    OperationSystemHandler operationSystemHandler;

    @Test
    public void allBenefitHandlersTest(){
        BenefitExternalEmpowermentDO externalData = new BenefitExternalEmpowermentDO();
        externalData.setSafetyTotalIncome(678d);
        externalData.setAutoSafetyTotalInvestment(718.91);
        BenefitPreparedDataBO customData = new BenefitPreparedDataBO();
        customData.setExternalData(externalData);
        customData.setMetricType(MetricTypeEnum.BENEFIT_RELEASE.getType());
        incomeToInvestmentRatioHandler.recursiveHandle(customData);
    }
}
