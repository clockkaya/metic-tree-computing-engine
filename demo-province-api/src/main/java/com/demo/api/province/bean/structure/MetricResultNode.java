package com.sama.api.ledger.bean.structure;

import com.alibaba.fastjson2.annotation.JSONField;
import com.core4ct.utils.DataUtils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果节点/树
 * @author: huxh
 * @description:
 * @datetime: 2025/6/24 10:47
 */
public class MetricResultNode extends MetricUnifiedNode {

    private static final long serialVersionUID = -165406965444312295L;

    /**
     * e.累加总分，只有非叶子节点才存在
     */
    @JSONField(ordinal = 4)
    private BigDecimal totalScore;

    /**
     * a.过程数据，强制 LinkedHashMap 保证顺序
     */
    @JSONField(ordinal = 5)
    private LinkedHashMap<String, Object> processingDataMap;

    /**
     * b.评估值，以 Map 形式计入，形如{"input": 90, "threshold": 100}
     */
    @JSONField(ordinal = 6)
    private Map<String, Object> assessedValueMap;

    /**
     * 计算规则的 Aviator 表达
     */
    @JSONField(ordinal = 7)
    private String aviatorRule;

    /**
     * c.评估得分
     */
    @JSONField(ordinal = 8)
    private BigDecimal assessedScore;

    @JSONField(ordinal = 9)
    private BigDecimal weight;

    /**
     * d.加权得分
     */
    @JSONField(ordinal = 10)
    private BigDecimal weightedScore;

    /**
     * 子节点
     */
    @JSONField(ordinal = 11)
    private List<MetricResultNode> subResultNodes;

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public LinkedHashMap<String, Object> getProcessingDataMap() {
        return processingDataMap;
    }

    public void setProcessingDataMap(LinkedHashMap<String, Object> processingDataMap) {
        this.processingDataMap = processingDataMap;
    }

    public Map<String, Object> getAssessedValueMap() {
        return assessedValueMap;
    }

    public void setAssessedValueMap(Map<String, Object> assessedValueMap) {
        this.assessedValueMap = assessedValueMap;
    }

    public String getAviatorRule() {
        return aviatorRule;
    }

    public void setAviatorRule(String aviatorRule) {
        this.aviatorRule = aviatorRule;
    }

    public BigDecimal getAssessedScore() {
        return assessedScore;
    }

    public void setAssessedScore(BigDecimal assessedScore) {
        this.assessedScore = assessedScore;
    }

    @Override
    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeightedScore() {
        return weightedScore;
    }

    public void setWeightedScore(BigDecimal weightedScore) {
        this.weightedScore = weightedScore;
    }

    public List<MetricResultNode> getSubResultNodes() {
        return subResultNodes;
    }

    public void setSubResultNodes(List<MetricResultNode> subResultNodes) {
        this.subResultNodes = subResultNodes;
    }

    @Override
    public String toString() {
        return "MetricResultNode{" +
                "totalScore=" + totalScore +
                ", processingDataMap=" + processingDataMap +
                ", assessedValueMap=" + assessedValueMap +
                ", aviatorRule='" + aviatorRule + '\'' +
                ", assessedScore=" + assessedScore +
                ", weight=" + weight +
                ", weightedScore=" + weightedScore +
                ", subResultNodes=" + subResultNodes +
                "} " + super.toString();
    }

    /**
     * 获取key为"input"的评估值
     * @return  b.评估值
     */
    public Object extractAssessedValue(){
        if (DataUtils.isNotEmpty(this.assessedValueMap)){
            return this.assessedValueMap.get("input");
        }
        return null;
    }
}
