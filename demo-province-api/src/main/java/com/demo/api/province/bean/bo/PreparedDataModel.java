package com.sama.api.ledger.bean.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/16 16:22
 */
public class PreparedDataModel implements Serializable {

    private static final long serialVersionUID = 2664762613028618964L;

    /**
     * 指标类型（自定义）
     * 具体见 MetricTypeEnum
     */
    private Integer metricType;

    /**
     * 对应的配置时间（hash值）
     */
    private Date configRefTime;

    /**
     * 对应的原始数据时间（hash值）
     */
    private Date dataRefTime;

    /**
     * 组织code
     */
    private String orgCode;

    /**
     * 更新模式：0-强制更新，1-条件更新
     */
    private Integer updateMode;

    public Integer getMetricType() {
        return metricType;
    }

    public void setMetricType(Integer metricType) {
        this.metricType = metricType;
    }

    public Date getConfigRefTime() {
        return configRefTime;
    }

    public void setConfigRefTime(Date configRefTime) {
        this.configRefTime = configRefTime;
    }

    public Date getDataRefTime() {
        return dataRefTime;
    }

    public void setDataRefTime(Date dataRefTime) {
        this.dataRefTime = dataRefTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(Integer updateMode) {
        this.updateMode = updateMode;
    }

    @Override
    public String toString() {
        return "PreparedDataModel{" +
            "metricType=" + metricType +
            ", configRefTime=" + configRefTime +
            ", dataRefTime=" + dataRefTime +
            ", orgCode='" + orgCode + '\'' +
            ", updateMode=" + updateMode +
            '}';
    }
}
