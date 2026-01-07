package com.sama.api.ledger.bean.structure;

import java.io.Serial;
import java.io.Serializable;

/**
 * 阈值对
 * @author: huxh
 * @description:
 * @datetime: 2025/9/19 8:56
 */
public class ThresholdPair implements Serializable {

    @Serial
    private static final long serialVersionUID = -8051877275771777353L;

    /**
     * 标准下限
     */
    private Double lower;

    /**
     * 标准上限
     */
    private Double upper;

    public ThresholdPair(Double lower, Double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    public Double getUpper() {
        return upper;
    }

    public void setUpper(Double upper) {
        this.upper = upper;
    }

    @Override
    public String toString() {
        return "ThresholdPair{" +
            "lower=" + lower +
            ", upper=" + upper +
            '}';
    }
}
