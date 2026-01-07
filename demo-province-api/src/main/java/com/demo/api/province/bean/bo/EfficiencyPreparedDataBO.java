package com.sama.api.ledger.bean.bo;

import java.util.List;

/**
 * 效率BO
 * @author: huxh
 * @description: 因为是分开开发的，所以内部的DO和原表不一致
 * @datetime: 2025/6/16 16:16
 */
public class EfficiencyPreparedDataBO extends PreparedDataModel {

    /**
     * 在建工程明细表（网络和信息安全项目情况）
     */
    private List<EfficiencyUnifiedBO> inProgressData;

    /**
     * 人工数据
     */
    private EfficiencyManualBO manual;

    public List<EfficiencyUnifiedBO> getInProgressData() {
        return inProgressData;
    }

    public void setInProgressData(List<EfficiencyUnifiedBO> inProgressData) {
        this.inProgressData = inProgressData;
    }

    public EfficiencyManualBO getManual() {
        return manual;
    }

    public void setManual(EfficiencyManualBO manual) {
        this.manual = manual;
    }

    @Override
    public String toString() {
        return "EfficiencyPreparedDataBO{" +
            "inProgressData=" + inProgressData +
            ", manual=" + manual +
            "} " + super.toString();
    }
}
