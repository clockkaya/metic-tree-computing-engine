package com.sama.api.ledger.bean.structure;

import com.alibaba.fastjson2.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;

/**
 * 配置节点/树
 * @author: huxh
 * @description:
 * @datetime: 2025/6/23 10:42
 */
public class MetricConfigNode extends MetricUnifiedNode {

    private static final long serialVersionUID = 7887649501295595225L;

    /**
     * 计算规则的 Aviator 表达
     */
    @JSONField(ordinal = 4)
    private String aviatorRule;

    @JSONField(ordinal = 5)
    private BigDecimal weight;

    /**
     * 子节点
     */
    @JSONField(ordinal = 6)
    private List<MetricConfigNode> subConfigNodes;

    public String getAviatorRule() {
        return aviatorRule;
    }

    public void setAviatorRule(String aviatorRule) {
        this.aviatorRule = aviatorRule;
    }

    @Override
    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public List<MetricConfigNode> getSubConfigNodes() {
        return subConfigNodes;
    }

    public void setSubConfigNodes(List<MetricConfigNode> subConfigNodes) {
        this.subConfigNodes = subConfigNodes;
    }

    @Override
    public String toString() {
        return "MetricConfigNode{" +
                "aviatorRule='" + aviatorRule + '\'' +
                ", weight=" + weight +
                ", subConfigNodes=" + subConfigNodes +
                "} " + super.toString();
    }
}
