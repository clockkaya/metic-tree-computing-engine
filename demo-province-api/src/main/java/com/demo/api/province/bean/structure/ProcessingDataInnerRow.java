package com.sama.api.ledger.bean.structure;

import java.io.Serial;
import java.io.Serializable;

/**
 * 效益对内过程数据内嵌行项
 * @author: huxh
 * @description:
 * @datetime: 2025/9/12 13:59
 */
public class ProcessingDataInnerRow implements Serializable {

    @Serial
    private static final long serialVersionUID = -2446327337304217038L;

    /**
     * 厂家
     */
    private String vendors;

    /**
     * 单位造价
     */
    private Object var;

    /**
     * 阈值
     */
    private Object threshold;

    public ProcessingDataInnerRow(String vendors, Object var, Object threshold) {
        this.vendors = vendors;
        this.var = var;
        this.threshold = threshold;
    }

    public String getVendors() {
        return vendors;
    }

    public void setVendors(String vendors) {
        this.vendors = vendors;
    }

    public Object getVar() {
        return var;
    }

    public void setVar(Object var) {
        this.var = var;
    }

    public Object getThreshold() {
        return threshold;
    }

    public void setThreshold(Object threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "ProcessingDataInnerRow{" +
            "vendors='" + vendors + '\'' +
            ", var=" + var +
            ", threshold=" + threshold +
            '}';
    }
}
