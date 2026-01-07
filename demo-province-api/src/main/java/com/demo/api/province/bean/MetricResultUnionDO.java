package com.sama.api.ledger.bean;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.core4ct.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sama.api.ledger.bean.structure.MetricResultNode;

import java.util.Date;
import java.util.List;

/**
 * 指标结果的集团侧汇总
 * @author: huxh
 * @description: 存储从各省上报的原始数据（或关键部分），用于计算和追溯。
 * @datetime: 2025/9/9 11:20
 */
@TableName("sama_ledger.metric_result_union")
public class MetricResultUnionDO extends BaseModel {

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

    /**
     * 组织 code 列表
     */
    @TableField(exist = false)
    private transient List<String> orgCodes;

    /**
     * 指标类型列表
     */
    @TableField(exist = false)
    private transient List<Integer> metricTypes;

    /**
     * 查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(exist = false)
    private transient Date startTime;

    /**
     * 查询结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(exist = false)
    private transient Date endTime;

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

    public int getMetricType() {
        return metricType;
    }

    public void setMetricType(int metricType) {
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

    public void setMetricType(Integer metricType) {
        this.metricType = metricType;
    }

    public List<String> getOrgCodes() {
        return orgCodes;
    }

    public void setOrgCodes(List<String> orgCodes) {
        this.orgCodes = orgCodes;
    }

    public List<Integer> getMetricTypes() {
        return metricTypes;
    }

    public void setMetricTypes(List<Integer> metricTypes) {
        this.metricTypes = metricTypes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setRootNode(MetricResultNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        return "MetricResultUnionDO{" +
            "uid='" + uid + '\'' +
            ", orgCode='" + orgCode + '\'' +
            ", metricType=" + metricType +
            ", resultTree='" + resultTree + '\'' +
            ", configRefTime=" + configRefTime +
            ", dataRefTime=" + dataRefTime +
            ", rootNode=" + rootNode +
            ", orgCodes=" + orgCodes +
            ", metricTypes=" + metricTypes +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            "} " + super.toString();
    }
}
