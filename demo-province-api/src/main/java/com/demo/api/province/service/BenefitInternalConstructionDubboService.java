package com.sama.api.ledger.service;

import com.core4ct.base.BaseDubboService;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 13:58
 */
public interface BenefitInternalConstructionDubboService extends BaseDubboService<BenefitInternalConstructionDO> {

    void submit(String provinceCode, List<BenefitInternalConstructionDO> entityList, Long userId);

    Pagination<BenefitInternalConstructionDO> searchAndPage(BenefitInternalConstructionDO queryDO, Pagination<BenefitInternalConstructionDO> rowBounds);

}
