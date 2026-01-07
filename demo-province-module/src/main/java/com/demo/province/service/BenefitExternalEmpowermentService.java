package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:37
 */
public interface BenefitExternalEmpowermentService extends BaseService<BenefitExternalEmpowermentDO> {

    void submit(String provinceCode, List<BenefitExternalEmpowermentDO> entityList, Long userId);

}
