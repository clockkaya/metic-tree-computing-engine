package com.sama.api.ledger.bean.structure;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 基础图表数据结构
 * @author: huxh
 * @description:
 * @datetime: 2025/7/22 9:42
 */
public class BasicChart implements Serializable {

    @Serial
    private static final long serialVersionUID = -7143336211169405028L;

    /**
     * x轴类别
     */
    @JSONField(ordinal = 1)
    private List<String> xCategory;

    /**
     * y轴数据
     */
    @JSONField(ordinal = 2)
    private List<Object> yData;

    public List<String> getxCategory() {
        return xCategory;
    }

    public void setxCategory(List<String> xCategory) {
        this.xCategory = xCategory;
    }

    public List<Object> getyData() {
        return yData;
    }

    public void setyData(List<Object> yData) {
        this.yData = yData;
    }

    @Override
    public String toString() {
        return "BasicChart{" +
            "xCategory=" + xCategory +
            ", yData=" + yData +
            '}';
    }
}
