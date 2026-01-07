package com.api.analytic.service;

import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;

/**
 * 通用接口汇总
 * @author: huxh
 * @description:
 * @datetime: 2025/10/23 15:41
 */
public interface DuplicateCollectDubboService {

    /**
     * 评估过程数据|综合防护|分页
     *
     * @param queryDO   ComprehensiveProtectionExtendedDO
     * @return          Pagination
     */
    Pagination<ComprehensiveProtectionExtendedDO> processDataPage(ComprehensiveProtectionExtendedDO queryDO);

    /**
     * 评估过程数据|效益数据|对内建设|分页
     *
     * @param queryDO   BenefitInternalConstructionDO
     * @return          Pagination
     */
    Pagination<BenefitInternalConstructionDO> processDataPage(BenefitInternalConstructionDO queryDO);

    /**
     * 评估过程数据|效益数据|对外赋能|分页
     *
     * @param queryDO   BenefitExternalEmpowermentDO
     * @return          Pagination
     */
    Pagination<BenefitExternalEmpowermentDO> processDataPage(BenefitExternalEmpowermentDO queryDO);

}
