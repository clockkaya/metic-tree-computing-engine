package com.sama.api.ledger.bean.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.sama.api.ledger.bean.MetricResultDO;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/8/28 9:08
 */
public class VisualizedProvinceBaseVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 6604875539965347570L;

    /**
     * 数据截止
     */
    @JSONField(ordinal = 1)
    private Date refTime;

    /**
     * 特定属性值
     */
    @JSONField(ordinal = 2)
    private LinkedHashMap<String, BigDecimal> pageKeyAndScoreMap;

    /**
     * 省级表格
     */
    @JSONField(ordinal = 3)
    private List<T> tableItems;

    /**
     * 关联的 metric_result 行项，不予显示
     */
    @JSONField(serialize = false)
    private transient MetricResultDO joinResult;

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

    public List<T> getTableItems() {
        return tableItems;
    }

    public void setTableItems(List<T> tableItems) {
        this.tableItems = tableItems;
    }

    public MetricResultDO getJoinResult() {
        return joinResult;
    }

    public void setJoinResult(MetricResultDO joinResult) {
        this.joinResult = joinResult;
    }

    @Override
    public String toString() {
        return "VisualizedProvinceBaseVO{" +
            "refTime=" + refTime +
            ", pageKeyAndScoreMap=" + pageKeyAndScoreMap +
            ", tableItems=" + tableItems +
            '}';
    }
}
