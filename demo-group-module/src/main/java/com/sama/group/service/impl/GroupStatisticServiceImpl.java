package com.sama.analytic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.analytic.mapper.GroupStatisticMapper;
import com.sama.analytic.service.GroupStatisticService;
import com.sama.api.ledger.bean.GroupStatisticDO;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 15:43
 */
@Service
public class GroupStatisticServiceImpl extends BaseServiceImpl<GroupStatisticDO, GroupStatisticMapper> implements GroupStatisticService {

    @Override
    public List<GroupStatisticDO> insertAndQueryBatch(List<GroupStatisticDO> dataList) {
        this.mapper.customInsertBatch(dataList);
        List<Long> ids = dataList.stream().map(GroupStatisticDO::getId).toList();
        return this.mapper.selectByIds(ids);
    }

    @Override
    public GroupStatisticDO getAlignedRecord(String dimensionKey, Date alignDay) {
        GroupStatisticDO queryDO = new GroupStatisticDO();
        queryDO.setDimensionKey(dimensionKey);
        if (alignDay == null) {
            alignDay = new Date();
        }
        queryDO.setStartTime(DateUtil.beginOfDay(alignDay));
        queryDO.setEndTime(DateUtil.endOfDay(alignDay));
        return this.mapper.selectAlignedRecord(queryDO);
    }

    @Override
    public GroupStatisticDO getAlignedRecordWithValidation(String dimensionKey, Date alignDay) {
        GroupStatisticDO res = getAlignedRecord(dimensionKey, alignDay);
        if (DataUtils.isEmpty(res)){
            throw new GenericException( MessageFormat.format("根据 dimensionKey: {0}，无法从数据库取到最新结果记录，请排查！", dimensionKey));
        }

        logger.info("【{}】 获取最新结果记录并解析成功（id = {}）", dimensionKey, res.getId());

        return res;
    }

}
