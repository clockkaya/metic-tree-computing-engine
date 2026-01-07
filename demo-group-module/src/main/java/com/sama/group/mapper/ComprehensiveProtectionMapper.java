package com.sama.analytic.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 删除每个 org_code 组内 create_time 非最大的记录
     * TODO：后续可以用通用方法+Enum代替
     *
     * @return 影响的行数
     */
    int deleteNonLatestByOrgCode();

    /**
     * 逻辑删除每个 org_code 组内 create_time 非最大的记录
     *
     * @return 影响的行数
     */
    int updateNonLatestAsDeleted();

    /**
     * 条件查询分页
     *
     * @param queryDO
     * @param page
     * @return
     */
    List<ComprehensiveProtectionExtendedDO> selectConditionalPage(@Param("cm") ComprehensiveProtectionExtendedDO queryDO, Page<ComprehensiveProtectionExtendedDO> page);

}
