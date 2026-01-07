package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:37
 */
public interface BenefitInternalConstructionService extends BaseService<BenefitInternalConstructionDO> {

    /**
     * 返回所有 orgCodes
     * @return  List<String>
     */
    List<String> selectAllOrgCodes();

    void submit(String provinceCode, List<BenefitInternalConstructionDO> entityList, Long userId);

    Pagination<BenefitInternalConstructionDO> searchAndPage(BenefitInternalConstructionDO queryDO, Pagination<BenefitInternalConstructionDO> rowBounds);

}
