package com.sama.api.ledger.bean.structure;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 统一指标节点
 * @author: huxh
 * @description: 包含配置/结果的关键通用属性
 * @datetime: 2025/8/8 9:13
 */
public class MetricUnifiedNode implements Serializable {

    private static final long serialVersionUID = 1301104537338713134L;

    /**
     * 英文Key，唯一主键
     */
    @JSONField(ordinal = 1)
    private String keyEn;

    /**
     * 中文Key
     */
    @JSONField(ordinal = 2)
    private String keyCn;

    /**
     * 透视层级，用 1/2.1/2.1.1 表示下钻
     */
    @JSONField(ordinal = 3)
    private String pivotId;

    /**
     * 权重，顶层节点为1
     */
    // @JSONField()
    private BigDecimal weight;

    public String getKeyEn() {
        return keyEn;
    }

    public void setKeyEn(String keyEn) {
        this.keyEn = keyEn;
    }

    public String getKeyCn() {
        return keyCn;
    }

    public void setKeyCn(String keyCn) {
        this.keyCn = keyCn;
    }

    public String getPivotId() {
        return pivotId;
    }

    public void setPivotId(String pivotId) {
        this.pivotId = pivotId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MetricUnifiedNode{" +
                "keyEn='" + keyEn + '\'' +
                ", keyCn='" + keyCn + '\'' +
                ", pivotId='" + pivotId + '\'' +
                ", weight=" + weight +
                '}';
    }
}
