package com.sama.api.ledger.bean.structure;

import java.io.Serial;
import java.io.Serializable;

/**
 * 类间阈值对
 * @author: huxh
 * @description:
 * @datetime: 2025/10/22 14:41
 */
public class CategorizedThresholdPair implements Serializable {

    @Serial
    private static final long serialVersionUID = 8795242286026525277L;

    private ThresholdPair full;

    private ThresholdPair large;

    private ThresholdPair medium;

    private ThresholdPair small;

    public CategorizedThresholdPair(ThresholdPair full, ThresholdPair large, ThresholdPair medium, ThresholdPair small) {
        this.full = full;
        this.large = large;
        this.medium = medium;
        this.small = small;
    }

    public CategorizedThresholdPair() {
        this.full = new ThresholdPair(null, null);
        this.large = new ThresholdPair(null, null);
        this.medium = new ThresholdPair(null, null);
        this.small = new ThresholdPair(null, null);
    }

    public ThresholdPair getFull() {
        return full;
    }

    public void setFull(ThresholdPair full) {
        this.full = full;
    }

    public ThresholdPair getLarge() {
        return large;
    }

    public void setLarge(ThresholdPair large) {
        this.large = large;
    }

    public ThresholdPair getMedium() {
        return medium;
    }

    public void setMedium(ThresholdPair medium) {
        this.medium = medium;
    }

    public ThresholdPair getSmall() {
        return small;
    }

    public void setSmall(ThresholdPair small) {
        this.small = small;
    }

    @Override
    public String toString() {
        return "CategorizedThresholdPair{" +
            "full=" + full +
            ", large=" + large +
            ", medium=" + medium +
            ", small=" + small +
            '}';
    }
}
