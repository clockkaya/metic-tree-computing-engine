package com.sama.api.ledger.bean.structure;



import com.alibaba.fastjson2.annotation.JSONField;

import java.math.BigDecimal;

/**
 * 可视化节点/链表
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/8/8 13:28
 */
public class MetricVisualizedNode extends MetricUnifiedNode {

    private static final long serialVersionUID = -2749295722592853441L;

    /**
     * 权重
     */
    @JSONField(ordinal = 4)
    private BigDecimal weight;

    /**
     * 值
     */
    @JSONField(ordinal = 5)
    private Object value;

    /**
     * 分
     */
    @JSONField(ordinal = 6)
    private Object score;

    @JSONField(ordinal = 7)
    private MetricVisualizedNode nextNode;

    @Override
    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }

    public MetricVisualizedNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MetricVisualizedNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "MetricVisualizedNode{" +
                "weight=" + weight +
                ", value=" + value +
                ", score=" + score +
                ", nextNode=" + nextNode +
                "} " + super.toString();
    }

}
