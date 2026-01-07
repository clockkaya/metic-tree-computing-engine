package com.sama.analytic.metric.support;

import com.api.analytic.service.DuplicateCollectDubboService;
import com.core4ct.support.Pagination;
import com.sama.analytic.service.BenefitExternalEmpowermentService;
import com.sama.analytic.service.BenefitInternalConstructionService;
import com.sama.analytic.service.ComprehensiveProtectionService;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 前置 NonWebsocket
 * @author: huxh
 * @description:
 * @datetime: 2025/10/23 15:44
 */
@DubboService
@RefreshScope
public class DuplicateCollectDubboServiceImpl implements DuplicateCollectDubboService {

    private static final Logger logger = LogManager.getLogger(DuplicateCollectDubboServiceImpl.class);

    @Resource
    ComprehensiveProtectionService comprehensiveProtectionService;

    @Resource
    BenefitInternalConstructionService benefitInternalConstructionService;

    @Resource
    BenefitExternalEmpowermentService benefitExternalEmpowermentService;

    @Override
    public Pagination<ComprehensiveProtectionExtendedDO> processDataPage(ComprehensiveProtectionExtendedDO queryDO) {
        return comprehensiveProtectionService.conditionalPageImplicitTurnover(queryDO);
    }

    @Override
    public Pagination<BenefitInternalConstructionDO> processDataPage(BenefitInternalConstructionDO queryDO) {
        return benefitInternalConstructionService.conditionalPageImplicitTurnover(queryDO);
    }

    @Override
    public Pagination<BenefitExternalEmpowermentDO> processDataPage(BenefitExternalEmpowermentDO queryDO) {
        return benefitExternalEmpowermentService.conditionalPageImplicitTurnover(queryDO);
    }

}
