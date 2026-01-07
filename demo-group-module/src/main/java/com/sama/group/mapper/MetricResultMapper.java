package com.sama.analytic.mapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.MetricResultDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */

@DS("ledger")
public interface MetricResultMapper extends BaseMapper<MetricResultDO> {

    List<MetricResultDO> selectMetricListByMetricType(Integer metricType);

    MetricResultDO selectLatestRecordByOrgCodeAndMetricType(String orgCode, Integer metricType);

    List<String> selectAllProvinceCodes(List<Integer> metricTypeList);

    Date getMaxDataRefTime(String orgCode, List<Integer> metricTypeList);

    // 窗口函数
    List<MetricResultDO> selectLatestRecords(@Param("cm") MetricResultDO queryDO);
}
