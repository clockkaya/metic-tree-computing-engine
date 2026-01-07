package com.sama.ledger.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.MetricConfigDO;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/23 14:22
 */
public interface MetricConfigMapper extends BaseMapper<MetricConfigDO> {

    /**
     * Attention!包含了 BENEFIT_SNAPSHOT 作为 BENEFIT_RELEASE 的副本
     *
     * @param metricType
     * @return
     */
    MetricConfigDO selectLatestRecordByMetricType(int metricType);

}
