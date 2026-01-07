package com.sama.api.ledger.bean.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.structure.BasicChart;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/17 16:55
 */
public class VisualizedGroupBaseVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -3504786659672909862L;

    /**
     * 数据截止，实为集团侧的计算时间
     */
    @JSONField(ordinal = 1)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date refTime;

    /**
     * 全网平均分
     */
    @JSONField(ordinal = 2)
    private LinkedHashMap<String, BigDecimal> pageKeyAndScoreMap;

    /**
     * 全网柱状图
     */
    @JSONField(ordinal = 3)
    private BasicChart scoreChart;

    /**
     * 省级表格Map
     */
    @JSONField(ordinal = 4)
    private Map<String, List<T>> provinceAndTableMap;

    /**
     * 关联的 metric_result_union.uid 项
     */
    @JSONField(ordinal = 5)
    private String joinResultUids;

    /**
     * 关联的 metric_result_union 行项，不予显示
     */
    @JSONField(serialize = false)
    private transient List<MetricResultUnionDO> joinResults;

    public Date getRefTime() {
        return refTime;
    }

    public void setRefTime(Date refTime) {
        this.refTime = refTime;
    }

    public LinkedHashMap<String, BigDecimal> getPageKeyAndScoreMap() {
        return pageKeyAndScoreMap;
    }

    public void setPageKeyAndScoreMap(LinkedHashMap<String, BigDecimal> pageKeyAndScoreMap) {
        this.pageKeyAndScoreMap = pageKeyAndScoreMap;
    }

    public BasicChart getScoreChart() {
        return scoreChart;
    }

    public void setScoreChart(BasicChart scoreChart) {
        this.scoreChart = scoreChart;
    }

    public Map<String, List<T>> getProvinceAndTableMap() {
        return provinceAndTableMap;
    }

    public void setProvinceAndTableMap(Map<String, List<T>> provinceAndTableMap) {
        this.provinceAndTableMap = provinceAndTableMap;
    }

    public String getJoinResultUids() {
        return joinResultUids;
    }

    public void setJoinResultUids(String joinResultUids) {
        this.joinResultUids = joinResultUids;
    }

    public List<MetricResultUnionDO> getJoinResults() {
        return joinResults;
    }

    public void setJoinResults(List<MetricResultUnionDO> joinResults) {
        this.joinResults = joinResults;
    }

    @Override
    public String toString() {
        return "VisualizedGroupBaseVO{" +
            "refTime=" + refTime +
            ", pageKeyAndScoreMap=" + pageKeyAndScoreMap +
            ", scoreChart=" + scoreChart +
            ", provinceAndTableMap=" + provinceAndTableMap +
            ", joinResultUids='" + joinResultUids + '\'' +
            '}';
    }
}
