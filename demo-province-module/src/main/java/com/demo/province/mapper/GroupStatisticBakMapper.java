package com.sama.ledger.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.GroupStatisticBakDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 16:09
 */
public interface GroupStatisticBakMapper extends BaseMapper<GroupStatisticBakDO> {

    GroupStatisticBakDO selectAlignedRecord(@Param("cm")GroupStatisticBakDO queryDO);

}
