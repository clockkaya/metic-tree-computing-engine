package com.sama.ledger.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.MetricResultDO;

import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */
public interface MetricResultMapper extends BaseMapper<MetricResultDO> {

    MetricResultDO selectLatestRecordByOrgCodeAndMetricType(String orgCode, int metricType);

    List<String> selectAllProvinceCodes(List<Integer> metricTypeList);

    Date getMaxDataRefTime(String orgCode, List<Integer> metricTypeList);

    Date getMaxCreateTime(String orgCode, List<Integer> metricTypeList);
}
