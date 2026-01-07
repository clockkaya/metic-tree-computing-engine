package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.GroupStatisticBakDO;

import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 15:43
 */
public interface GroupStatisticBakService extends BaseService<GroupStatisticBakDO> {

    /**
     * 不强求效率，普通性能的批量插入
     *
     * @param dataList  数据集
     */
    void insertBatch(List<GroupStatisticBakDO> dataList);

    /**
     * 获取对齐（以天为单位）的数据
     *
     * @param dimensionKey  统计维度标识
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内最新结果
     */
    GroupStatisticBakDO getAlignedRecord(String dimensionKey, Date alignDay);

    /**
     * 获取对齐（以天为单位）的数据，校验以确保非空
     *
     * @param dimensionKey  统计维度标识
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内最新结果
     */
    GroupStatisticBakDO getAlignedRecordWithValidation(String dimensionKey, Date alignDay);

}
