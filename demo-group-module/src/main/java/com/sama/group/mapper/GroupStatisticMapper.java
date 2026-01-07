package com.sama.analytic.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.GroupStatisticDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 15:42
 */
public interface GroupStatisticMapper extends BaseMapper<GroupStatisticDO> {

    int customInsertBatch(List<GroupStatisticDO> dataList);

    GroupStatisticDO selectAlignedRecord(@Param("cm")GroupStatisticDO queryDO);

}
