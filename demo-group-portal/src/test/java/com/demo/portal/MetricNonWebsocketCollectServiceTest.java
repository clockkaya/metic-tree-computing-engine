package com.sama.officer;

import com.alibaba.fastjson2.JSON;
import com.api.analytic.service.DuplicateCollectDubboService;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.officer.object.vo.BenefitExternalEmpowermentProcessDataVO;
import com.sama.officer.object.vo.BenefitInternalConstructionProcessDataVO;
import com.sama.officer.object.vo.ComprehensiveProtectionProcessDataVO;
import com.sama.officer.service.MetricNonWebsocketCollectService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/27 14:13
 */
@SpringBootTest(classes = SamaOfficerApplication.class)
public class MetricNonWebsocketCollectServiceTest {

    private static final Logger logger = LogManager.getLogger(MetricNonWebsocketCollectServiceTest.class);

    @DubboReference
    DuplicateCollectDubboService duplicateCollectDubboService;

    @Resource
    MetricNonWebsocketCollectService metricNonWebsocketCollectService;

    @Test
    public void dubboServiceTest(){
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        Pagination<ComprehensiveProtectionExtendedDO> test = duplicateCollectDubboService.processDataPage(queryDO);
        logger.info("【breakpoint】 可以调用 analytic Dubbo service, test: {}", JSON.toJSONString(test));
    }

    /**
     * 需要先 build analytic-api，再 maven syn
     * copy from analytic
     */
    @Test
    public void allProcessDataPageTests(){
        processDataPageComprehensiveProtectionTest();
        processDataPageBenefitInternalConstructionTest();
        processDataPageBenefitExternalEmpowermentTest();
    }

    @Test
    public void processDataPageComprehensiveProtectionTest(){
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setOrgCode("02250011");
        queryDO.setComprehensiveScenarioType("APT");
        queryDO.setEvaluationItem("攻击");
        Pagination<ComprehensiveProtectionProcessDataVO> voPagination = metricNonWebsocketCollectService.processDataPage(queryDO);
        logger.info("【breakpoint】 /comprehensiveProtection/processDataPage: {}", JSON.toJSONString(voPagination));
    }

    @Test
    public void processDataPageBenefitInternalConstructionTest(){
        BenefitInternalConstructionDO queryDO = new BenefitInternalConstructionDO();
        queryDO.setOrgCode("02260062");
        queryDO.setProjectCode("24");
        queryDO.setProjectName("中国");
        queryDO.setProjectType("合规考核");
        queryDO.setSize(5);
        Pagination<BenefitInternalConstructionProcessDataVO> voPagination = metricNonWebsocketCollectService.processDataPage(queryDO);
        logger.info("【breakpoint】 /benefitInternal/processDataPage: {}", JSON.toJSONString(voPagination));
    }

    @Test
    public void processDataPageBenefitExternalEmpowermentTest(){
        BenefitExternalEmpowermentDO queryDO = new BenefitExternalEmpowermentDO();
        queryDO.setOrgCode("02250011");
        queryDO.setSize(1);
        Pagination<BenefitExternalEmpowermentProcessDataVO> voPagination = metricNonWebsocketCollectService.processDataPage(queryDO);
        logger.info("【breakpoint】 /benefitExternal/processDataPage: {}", JSON.toJSONString(voPagination));
    }

}
