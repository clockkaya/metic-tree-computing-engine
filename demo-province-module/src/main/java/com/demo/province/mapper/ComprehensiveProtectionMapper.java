package com.sama.ledger.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:42
 */
public interface ComprehensiveProtectionMapper extends BaseMapper<ComprehensiveProtectionExtendedDO> {

    List<String> selectAllOrgCodes();

    void hardBatchDelete(List<Long> ids);

    List<ComprehensiveProtectionExtendedDO> selectListByCondAndOrd(@Param("cm") ComprehensiveProtectionExtendedDO queryDO);

}
