package com.sama.api.ledger.bean;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.core4ct.base.BaseModel;
import com.sama.api.ledger.bean.structure.MetricResultNode;

import java.util.Date;

/**
 * 指标结果（的省侧原始数据）
 * @author: huxh
 * @description: 存储省侧自己产生的原始业务数据。这是数据的唯一事实来源 (Source of Truth)。
 * @datetime: 2025/6/30 9:16
 */
@TableName("sama_ledger.metric_result")
public class MetricResultDO extends BaseModel {

    /**
     * uid
     */
    private String uid;

    /**
     * 组织 code
     */
    private String orgCode;

    /**
     * 指标类型（自定义）
     * 具体见 MetricTypeEnum
     */
    private Integer metricType;

    /**
     * 结果树
     */
    private String resultTree;

    /**
     * 对应的配置时间（hash值）
     */
    private Date configRefTime;

    /**
     * 对应的原始数据时间（hash值）
     */
    private Date dataRefTime;

    /**
     * 结果树解析
     */
    @TableField(exist = false)
    private transient MetricResultNode rootNode;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getMetricType() {
        return metricType;
    }

    public void setMetricType(Integer metricType) {
        this.metricType = metricType;
    }

    public String getResultTree() {
        return resultTree;
    }

    public void setResultTree(String resultTree) {
        this.resultTree = resultTree;
    }

    public Date getConfigRefTime() {
        return configRefTime;
    }

    public void setConfigRefTime(Date configRefTime) {
        this.configRefTime = configRefTime;
    }

    public Date getDataRefTime() {
        return dataRefTime;
    }

    public void setDataRefTime(Date dataRefTime) {
        this.dataRefTime = dataRefTime;
    }

    public MetricResultNode getRootNode() {
        if (rootNode == null && resultTree != null) {
            rootNode = JSON.parseObject(resultTree, MetricResultNode.class);
        }
        return rootNode;
    }

    public void setRootNode(MetricResultNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "MetricResultDO{" +
            "uid='" + uid + '\'' +
            ", orgCode='" + orgCode + '\'' +
            ", metricType=" + metricType +
            ", resultTree='" + resultTree + '\'' +
            ", configRefTime=" + configRefTime +
            ", dataRefTime=" + dataRefTime +
            ", rootNode=" + rootNode +
            "} " + super.toString();
    }
}
