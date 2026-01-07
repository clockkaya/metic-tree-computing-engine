package com.sama.ledger.service.impl;

import cn.hutool.core.date.DateUtil;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.GroupStatisticBakDO;
import com.sama.ledger.mapper.GroupStatisticBakMapper;
import com.sama.ledger.service.GroupStatisticBakService;
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
public class GroupStatisticBakServiceImpl extends BaseServiceImpl<GroupStatisticBakDO, GroupStatisticBakMapper> implements GroupStatisticBakService {

    @Override
    public void insertBatch(List<GroupStatisticBakDO> dataList) {
        this.mapper.insert(dataList);
    }

    @Override
    public GroupStatisticBakDO getAlignedRecord(String dimensionKey, Date alignDay) {
        GroupStatisticBakDO queryDO = new GroupStatisticBakDO();
        queryDO.setDimensionKey(dimensionKey);
        if (alignDay == null) {
            alignDay = new Date();
        }
        queryDO.setStartTime(DateUtil.beginOfDay(alignDay));
        queryDO.setEndTime(DateUtil.endOfDay(alignDay));
        return this.mapper.selectAlignedRecord(queryDO);
    }

    @Override
    public GroupStatisticBakDO getAlignedRecordWithValidation(String dimensionKey, Date alignDay) {
        GroupStatisticBakDO res = getAlignedRecord(dimensionKey, alignDay);
        if (DataUtils.isEmpty(res)){
            throw new GenericException( MessageFormat.format("根据 dimensionKey: {0}，无法从数据库取到最新结果记录，请排查！", dimensionKey));
        }

        logger.info("【{}】 获取最新结果记录并解析成功（id = {}）", dimensionKey, res.getId());

        return res;
    }

}
