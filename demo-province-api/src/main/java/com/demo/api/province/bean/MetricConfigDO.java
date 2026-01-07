package com.sama.api.ledger.bean;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.core4ct.base.BaseModel;
import com.sama.api.ledger.bean.structure.MetricConfigNode;

/**
 * 指标配置
 * @author: huxh
 * @description:
 * @datetime: 2025/6/23 10:51
 */
@TableName("metric_config")
public class MetricConfigDO extends BaseModel {

    private static final long serialVersionUID = 719783398006218247L;

    /**
     * 指标类型（自定义）
     * 具体见 MetricTypeEnum
     */
    private int metricType;

    /**
     * 配置树
     */
    private String configTree;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 配置树解析
     */
    @TableField(exist = false)
    private MetricConfigNode rootNode;

    public int getMetricType() {
        return metricType;
    }

    public void setMetricType(int metricType) {
        this.metricType = metricType;
    }

    public String getConfigTree() {
        return configTree;
    }

    public void setConfigTree(String configTree) {
        this.configTree = configTree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public MetricConfigNode getRootNode() {
        if (rootNode == null && configTree != null) {
            rootNode = JSON.parseObject(configTree, MetricConfigNode.class);
        }
        return rootNode;
    }

    public void setRootNode(MetricConfigNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "MetricConfigDO{" +
                "metricType=" + metricType +
                ", configTree='" + configTree + '\'' +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
