package com.sama.api.ledger.service;

import com.core4ct.base.BaseDubboService;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 13:55
 */
public interface BenefitExternalEmpowermentDubboService extends BaseDubboService<BenefitExternalEmpowermentDO> {

    void submit(String provinceCode, List<BenefitExternalEmpowermentDO> entityList, Long userId);

    Pagination<BenefitExternalEmpowermentDO> page(BenefitExternalEmpowermentDO queryDO, Pagination<BenefitExternalEmpowermentDO> rowBounds);

}
