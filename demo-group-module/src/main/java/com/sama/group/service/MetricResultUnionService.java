package com.sama.analytic.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.MetricResultUnionDO;

import java.util.Date;
import java.util.List;

/**
 * 集团侧
 * @author: huxh
 * @description:
 * @datetime: 2025/8/14 10:53
 */
public interface MetricResultUnionService extends BaseService<MetricResultUnionDO> {

    /**
     * 不强求效率，普通性能的批量插入
     *
     * @param dataList  数据集
     */
    void insertBatchEraseId(List<MetricResultUnionDO> dataList);

    /**
     * 获取对齐（以天为单位）的数据
     *
     * @param metricType    指标类型
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内（所有组织的）最新结果
     */
    List<MetricResultUnionDO> getAlignedRecords(Integer metricType, Date alignDay);

    /**
     * 获取对齐（以天为单位）的数据，校验以确保非空
     *
     * @param metricType    指标类型
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内（所有组织的）最新结果
     */
    List<MetricResultUnionDO> getAlignedRecordsWithValidation(Integer metricType, Date alignDay);

}
