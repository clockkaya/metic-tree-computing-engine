package com.sama.ledger.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */
public interface BenefitInternalConstructionMapper extends BaseMapper<BenefitInternalConstructionDO> {

    List<String> selectAllOrgCodes();

    void hardBatchDelete(List<Long> ids);

    List<BenefitInternalConstructionDO> selectListByCondAndOrd(@Param("cm") BenefitInternalConstructionDO queryDO, Page<BenefitInternalConstructionDO> page);

    /**
     * 更新 * 项目设计批复总投资（元）【自动生成不用填写】
     * = 非安全类设备费（元） + 安全类设备费（元） + 总体其他费
     */
    void updateAutoProjectDesignReplyTotalInvestment(String orgCode);

    /**
     * 更新 * 安全类设备其他费（元）【自动生成不用填写】
     * = 总体其他费（元） * (安全类设备费（元） / (非安全类设备费（元）+ 安全类设备费（元）))
     */
    void updateAutoSecurityDeviceOtherFee(String orgCode);

    /**
     * 更新 * 单位造价
     */
    void updateAllUnitCosts(String orgCode);

}
