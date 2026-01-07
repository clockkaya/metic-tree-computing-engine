package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.MetricResultDO;

import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:39
 */
public interface MetricResultService extends BaseService<MetricResultDO> {

    /**
     * 获取数据库最新的一条记录
     *
     * @param orgCode       组织code
     * @param metricType    指标类型
     * @return              MetricResultDO
     */
    MetricResultDO getLatestRecord(String orgCode, int metricType);

    /**
     * 获取数据库最新的一条记录，校验以确保非空
     */
    MetricResultDO getLatestRecordWithValidation(String orgCode, int metricType);

    /**
     * 获取对齐（以天为单位）的数据
     *
     * @param metricType    指标类型
     * @param alignDay      校准日期，如空则为当天
     * @return              校准日内（所有组织的）最新结果
     */
    List<MetricResultDO> getAlignedRecords(Integer metricType, Date alignDay);

    /**
     * 查询不同层级计算结果的所有省份code信息
     * @return
     */
    List<String> selectAllProvinceCodes(List<Integer> metricTypeList);


    /**
     * 获取指定省份下的某些维度数据下的最大数据截止时间
     * @param orgCode
     * @param metricTypeList
     * @return
     */
    Date getMaxDataRefTime(String orgCode, List<Integer> metricTypeList);

    /**
     * 获取指定省份下的某些维度数据下的最大数据创建时间
     * @param orgCode
     * @param metricTypeList
     * @return
     */
    Date getMaxCreateTime(String orgCode, List<Integer> metricTypeList);
}
