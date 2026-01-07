package com.sama.api.ledger.bean;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.core4ct.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 指标结果汇总的集团侧统计
 * @author: huxh
 * @description: 存储最终的统计结果。
 * @datetime: 2025/9/9 13:27
 */
@TableName("sama_ledger.group_statistic")
public class GroupStatisticDO extends BaseModel {

    /**
     * 统计维度标识
     */
    private String dimensionKey;

    /**
     * 统计结果记录
     */
    private String record;

    /**
     * 关联 uid
     */
    private String resultUids;

    @TableField(exist = false)
    private transient List<String> resultUidList;

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

    public String getDimensionKey() {
        return dimensionKey;
    }

    public void setDimensionKey(String dimensionKey) {
        this.dimensionKey = dimensionKey;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getResultUids() {
        return resultUids;
    }

    public void setResultUids(String resultUids) {
        this.resultUids = resultUids;
    }

    public List<String> getResultUidList() {
        if (resultUidList == null && resultUids != null) {
            resultUidList = JSON.parseObject(resultUids, new TypeReference<ArrayList<String>>(){});
        }
        return resultUidList;
    }

    public void setResultUidList(List<String> resultUidList) {
        this.resultUidList = resultUidList;
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

    @Override
    public String toString() {
        return "GroupStatisticDO{" +
            "dimensionKey='" + dimensionKey + '\'' +
            ", record='" + record + '\'' +
            ", resultUids='" + resultUids + '\'' +
            ", resultUidList=" + resultUidList +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            "} " + super.toString();
    }
}
