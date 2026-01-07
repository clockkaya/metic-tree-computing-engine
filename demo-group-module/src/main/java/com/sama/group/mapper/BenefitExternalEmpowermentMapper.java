package com.sama.analytic.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */
public interface BenefitExternalEmpowermentMapper extends BaseMapper<BenefitExternalEmpowermentDO> {

    int deleteNonLatestByOrgCode();

    int updateNonLatestAsDeleted();

    List<BenefitExternalEmpowermentDO> selectConditionalPage(@Param("cm") BenefitExternalEmpowermentDO queryDO, Page<BenefitExternalEmpowermentDO> page);

}
