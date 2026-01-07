package com.sama.api.ledger.bean.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: huxh
 * @description: 全量表/在建表通用DO
 * @datetime: 2025/8/6 9:02
 */
public class EfficiencyUnifiedBO implements Serializable {

    private static final long serialVersionUID = 5165444235824628824L;

    /**
     * 工程编码
     * join on uid
     */
    private String projectCode;

    /**
     * 立项年份
     */
    private Date projectYear;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 建设性质
     */
    private String constructionNature;

    /**
     * （标准）交付工期
     */
    private Integer standardDeliveryPeriod;

    /**
     * （标准）关闭工期
     */
    private Integer standardClosePeriod;

    /**
     * 立项批复日期
     */
    private Date projectApprovalDate;

    /**
     * 设计类型
     */
    private String designType;

    /**
     * 设计批复日期
     */
    private Date designApprovalDate;

    /**
     *  验收类型
     */
    private String inspectionType;

    /**
     * 初验批复日期
     */
    private Date firstInspectionApprovalDate;

    /**
     * 终验批复日期
     */
    private Date finalInspectionApprovalDate;

    /**
     * 工程关闭日期
     */
    private Date projectCloseDate;

    /**
     * 投资时段
     */
    private String investmentPeriod;

    /**
     * 项目状态
     */
    private String projectStatus;

    /**
     * 本年累计资本性支出
     */
    private Double yearlyCapitalExpenditure;

    /**
     * 单项总投资
     */
    private Double singleItemInvestment;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Date getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Date projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getConstructionNature() {
        return constructionNature;
    }

    public void setConstructionNature(String constructionNature) {
        this.constructionNature = constructionNature;
    }

    public Integer getStandardDeliveryPeriod() {
        return standardDeliveryPeriod;
    }

    public void setStandardDeliveryPeriod(Integer standardDeliveryPeriod) {
        this.standardDeliveryPeriod = standardDeliveryPeriod;
    }

    public Integer getStandardClosePeriod() {
        return standardClosePeriod;
    }

    public void setStandardClosePeriod(Integer standardClosePeriod) {
        this.standardClosePeriod = standardClosePeriod;
    }

    public Date getProjectApprovalDate() {
        return projectApprovalDate;
    }

    public void setProjectApprovalDate(Date projectApprovalDate) {
        this.projectApprovalDate = projectApprovalDate;
    }

    public String getDesignType() {
        return designType;
    }

    public void setDesignType(String designType) {
        this.designType = designType;
    }

    public Date getDesignApprovalDate() {
        return designApprovalDate;
    }

    public void setDesignApprovalDate(Date designApprovalDate) {
        this.designApprovalDate = designApprovalDate;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public Date getFirstInspectionApprovalDate() {
        return firstInspectionApprovalDate;
    }

    public void setFirstInspectionApprovalDate(Date firstInspectionApprovalDate) {
        this.firstInspectionApprovalDate = firstInspectionApprovalDate;
    }

    public Date getFinalInspectionApprovalDate() {
        return finalInspectionApprovalDate;
    }

    public void setFinalInspectionApprovalDate(Date finalInspectionApprovalDate) {
        this.finalInspectionApprovalDate = finalInspectionApprovalDate;
    }

    public Date getProjectCloseDate() {
        return projectCloseDate;
    }

    public void setProjectCloseDate(Date projectCloseDate) {
        this.projectCloseDate = projectCloseDate;
    }

    public String getInvestmentPeriod() {
        return investmentPeriod;
    }

    public void setInvestmentPeriod(String investmentPeriod) {
        this.investmentPeriod = investmentPeriod;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Double getYearlyCapitalExpenditure() {
        return yearlyCapitalExpenditure;
    }

    public void setYearlyCapitalExpenditure(Double yearlyCapitalExpenditure) {
        this.yearlyCapitalExpenditure = yearlyCapitalExpenditure;
    }

    public Double getSingleItemInvestment() {
        return singleItemInvestment;
    }

    public void setSingleItemInvestment(Double singleItemInvestment) {
        this.singleItemInvestment = singleItemInvestment;
    }

    @Override
    public String toString() {
        return "EfficiencyUnifiedBO{" +
                "projectCode='" + projectCode + '\'' +
                ", projectYear=" + projectYear +
                ", projectType='" + projectType + '\'' +
                ", constructionNature='" + constructionNature + '\'' +
                ", standardDeliveryPeriod=" + standardDeliveryPeriod +
                ", standardClosePeriod=" + standardClosePeriod +
                ", projectApprovalDate=" + projectApprovalDate +
                ", designType='" + designType + '\'' +
                ", designApprovalDate=" + designApprovalDate +
                ", inspectionType='" + inspectionType + '\'' +
                ", firstInspectionApprovalDate=" + firstInspectionApprovalDate +
                ", finalInspectionApprovalDate=" + finalInspectionApprovalDate +
                ", projectCloseDate=" + projectCloseDate +
                ", investmentPeriod='" + investmentPeriod + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", yearlyCapitalExpenditure=" + yearlyCapitalExpenditure +
                ", singleItemInvestment=" + singleItemInvestment +
                '}';
    }
}
