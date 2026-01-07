package com.sama.analytic.service;

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
     * 前序 build-in function 持久化，用以获取最新数据集
     *
     * @param orgCodes      组织code 列表（可以为空，即全国）
     * @param metricTypes   指标类型 列表（可以为空，即全维度）
     * @return              MetricResultDO 列表
     */
    List<MetricResultDO> getLatestRecords(List<String> orgCodes, List<Integer> metricTypes);

    /**
     * 基于当前时间、组织、指标类型：1. 逻辑删除历史所有数据；2. 插入新数据
     *
     * @param comingData    待更新数据
     */
    void turnoverUntilNow(List<MetricResultDO> comingData);

    /**
     * 前序 #turnoverUntilNow 持久化，用以获取最新数据集
     *
     * @param orgCodes      组织code 列表（可以为空，即全国）
     * @param metricTypes   指标类型 列表（可以为空，即全维度）
     * @return              MetricResultDO 列表
     */
    List<MetricResultDO> getValidRecordsAfterTurnover(List<String> orgCodes, List<Integer> metricTypes);

    MetricResultDO getOneRecordByUid(String uid);

}
