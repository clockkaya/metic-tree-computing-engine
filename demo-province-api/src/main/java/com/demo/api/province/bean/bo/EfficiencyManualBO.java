package com.sama.api.ledger.bean.bo;

import java.io.Serializable;

/**
 * @author: huxh
 * @description: 人工输入项
 * @datetime: 2025/6/19 9:13
 */
public class EfficiencyManualBO implements Serializable {

    private static final long serialVersionUID = -6824913133157112594L;

    /**
     * 修正项（四季度立项属于特殊情况）
     */
    private Integer fixedItem;

    /**
     * 今年计划外项目数量
     */
    private Integer thisYearOutPlanProjectNum;

    /**
     * 列账不及时的项目数量
     */
    private Integer lateBookProjectNum;

    /**
     * 预转固不及时项目数
     */
    private Integer latePreTransferProjectNum;

    /**
     * 关闭不及时项目数
     */
    private Integer lateCloseProjectNum;

    /**
     * 长期挂账项目数
     */
    private Integer longTermDebtProjectNum;

    /**
     * 今年投资总额（元）
     */
    private Double thisYearTotalInvestment;

    public Integer getThisYearOutPlanProjectNum() {
        return thisYearOutPlanProjectNum;
    }

    public Integer getFixedItem() {
        return fixedItem;
    }

    public void setFixedItem(Integer fixedItem) {
        this.fixedItem = fixedItem;
    }

    public void setThisYearOutPlanProjectNum(Integer thisYearOutPlanProjectNum) {
        this.thisYearOutPlanProjectNum = thisYearOutPlanProjectNum;
    }

    public Integer getLateBookProjectNum() {
        return lateBookProjectNum;
    }

    public void setLateBookProjectNum(Integer lateBookProjectNum) {
        this.lateBookProjectNum = lateBookProjectNum;
    }

    public Integer getLatePreTransferProjectNum() {
        return latePreTransferProjectNum;
    }

    public void setLatePreTransferProjectNum(Integer latePreTransferProjectNum) {
        this.latePreTransferProjectNum = latePreTransferProjectNum;
    }

    public Integer getLateCloseProjectNum() {
        return lateCloseProjectNum;
    }

    public void setLateCloseProjectNum(Integer lateCloseProjectNum) {
        this.lateCloseProjectNum = lateCloseProjectNum;
    }

    public Integer getLongTermDebtProjectNum() {
        return longTermDebtProjectNum;
    }

    public void setLongTermDebtProjectNum(Integer longTermDebtProjectNum) {
        this.longTermDebtProjectNum = longTermDebtProjectNum;
    }

    public Double getThisYearTotalInvestment() {
        return thisYearTotalInvestment;
    }

    public void setThisYearTotalInvestment(Double thisYearTotalInvestment) {
        this.thisYearTotalInvestment = thisYearTotalInvestment;
    }

    @Override
    public String toString() {
        return "EfficiencyManualBO{" +
            "fixedItem=" + fixedItem +
            ", thisYearOutPlanProjectNum=" + thisYearOutPlanProjectNum +
            ", lateBookProjectNum=" + lateBookProjectNum +
            ", latePreTransferProjectNum=" + latePreTransferProjectNum +
            ", lateCloseProjectNum=" + lateCloseProjectNum +
            ", longTermDebtProjectNum=" + longTermDebtProjectNum +
            ", thisYearTotalInvestment=" + thisYearTotalInvestment +
            '}';
    }
}
