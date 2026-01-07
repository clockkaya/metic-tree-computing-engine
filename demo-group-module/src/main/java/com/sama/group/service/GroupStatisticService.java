package com.sama.analytic.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.GroupStatisticDO;

import java.util.Date;
import java.util.List;

/**
 * 集团侧
 * @author: huxh
 * @description:
 * @datetime: 2025/9/9 15:43
 */
public interface GroupStatisticService extends BaseService<GroupStatisticDO> {

    /**
     * 批量插入并返回数据库结果
     *
     * @param dataList  数据集
     */
    List<GroupStatisticDO> insertAndQueryBatch(List<GroupStatisticDO> dataList);

    /**
     * 获取对齐（以天为单位）的数据
     *
     * @param dimensionKey  统计维度标识
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内最新结果
     */
    GroupStatisticDO getAlignedRecord(String dimensionKey, Date alignDay);

    /**
     * 获取对齐（以天为单位）的数据，校验以确保非空
     *
     * @param dimensionKey  统计维度标识
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内最新结果
     */
    GroupStatisticDO getAlignedRecordWithValidation(String dimensionKey, Date alignDay);

}
