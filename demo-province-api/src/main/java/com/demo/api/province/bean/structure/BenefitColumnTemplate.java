package com.sama.api.ledger.bean.structure;

/**
 * 效益列项模板
 * @author: huxh
 * @description:
 * @datetime: 2025/9/3 10:47
 */
public class BenefitColumnTemplate {

    /**
     * 能力投资
     */
    private Double investment;

    /**
     * 该能力新增指标总量
     */
    private Double increment;

    /**
     * 直接的指标结果
     */
    private Double directValue;

    public BenefitColumnTemplate(Double investment, Double increment) {
        this.investment = investment == null ? 0.0 : investment;
        this.increment = increment == null ? 0.0 : increment;
    }

    public BenefitColumnTemplate(Double directValue) {
        this.directValue = directValue == null ? 0.0 : directValue;
    }

    public BenefitColumnTemplate() {
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Double getIncrement() {
        return increment;
    }

    public void setIncrement(Double increment) {
        this.increment = increment;
    }

    public Double getDirectValue() {
        return directValue;
    }

    public void setDirectValue(Double directValue) {
        this.directValue = directValue;
    }

    @Override
    public String toString() {
        return "BenefitColumnTemplate{" +
            "investment=" + investment +
            ", increment=" + increment +
            ", directValue=" + directValue +
            '}';
    }
}
