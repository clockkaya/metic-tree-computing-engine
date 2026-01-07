package com.sama.api.ledger.bean.bo;

import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.structure.BenefitThresholdMap;

import java.util.List;

/**
 * 效益BO
 * @author: huxh
 * @description:
 * @datetime: 2025/7/7 14:24
 */
public class BenefitPreparedDataBO extends PreparedDataModel {

    /**
     * 对内建设数据（包含反向更新）
     */
    private List<BenefitInternalConstructionDO> internalData;

    /**
     * 对外赋能数据（单行）
     */
    private BenefitExternalEmpowermentDO externalData;

    /**
     * 效益类间阈值 Map（集团侧）
     */
    private BenefitThresholdMap thresholdMap;

    public List<BenefitInternalConstructionDO> getInternalData() {
        return internalData;
    }

    public void setInternalData(List<BenefitInternalConstructionDO> internalData) {
        this.internalData = internalData;
    }

    public BenefitExternalEmpowermentDO getExternalData() {
        return externalData;
    }

    public void setExternalData(BenefitExternalEmpowermentDO externalData) {
        this.externalData = externalData;
    }

    public BenefitThresholdMap getThresholdMap() {
        return thresholdMap;
    }

    public void setThresholdMap(BenefitThresholdMap thresholdMap) {
        this.thresholdMap = thresholdMap;
    }

    @Override
    public String toString() {
        return "BenefitPreparedDataBO{" +
            "internalData=" + internalData +
            ", externalData=" + externalData +
            ", thresholdMap=" + thresholdMap +
            "} " + super.toString();
    }
}
