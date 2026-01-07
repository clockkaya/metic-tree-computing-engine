package com.sama.analytic.mapper;

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

    int deleteNonLatestByOrgCode();

    int updateNonLatestAsDeleted();

    List<BenefitInternalConstructionDO> selectConditionalPage(@Param("cm") BenefitInternalConstructionDO queryDO, Page<BenefitInternalConstructionDO> page);

}
