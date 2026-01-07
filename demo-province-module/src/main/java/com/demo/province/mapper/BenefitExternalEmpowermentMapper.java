package com.sama.ledger.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */
public interface BenefitExternalEmpowermentMapper extends BaseMapper<BenefitExternalEmpowermentDO> {

    void hardBatchDelete(List<Long> ids);

    /**
     * 更新 * 本省当年网发安全类项目总投资（万元）【自动生成不用填写】
     * = sum (对内建设数据.项目设计批复总投资（元）【自动生成不用填写】) / 10000
     */
    void updateAutoSafetyTotalInvestment(String orgCode);

}
