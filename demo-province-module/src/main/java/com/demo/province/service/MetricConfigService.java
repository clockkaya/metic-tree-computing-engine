package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.MetricConfigDO;
import com.sama.api.ledger.bean.structure.MetricConfigNode;

import java.util.Date;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/23 10:56
 */
public interface MetricConfigService extends BaseService<MetricConfigDO> {

    /**
     * 重新从数据库加载最新配置树，并返回该条记录的 updateTime
     * Attention！只允许在顶层执行
     *
     * @param metricType    指标类型
     * @return              最新 updateTime
     */
    Date reloadTreeFromDB(int metricType);

    /**
     * 获取最新配置树（缓存非DB），即根节点
     *
     * @param metricType    指标类型
     * @return              根节点配置
     */
    MetricConfigNode getCurrentTree(int metricType);

    /**
     * 根据 Key 名，获取配置树（this.currentTree）向下的节点配置
     *
     * @param metricType    指标类型
     * @param keyEn         唯一标识
     * @return              当前节点配置
     */
    MetricConfigNode getStandingNode(int metricType, String keyEn);

}
