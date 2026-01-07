package com.sama.api.ledger.bean.dto;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.base.Objects;
import com.sama.api.ledger.bean.structure.ProcessingDataInnerRow;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 对内建设省级表格
 * @author: huxh
 * @description:
 * @datetime: 2025/9/1 10:17
 */
@Schema(description = "对内建设省级表格")
public class StatisticBenefitInternalConstructionProvinceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8523055845665989450L;

    //==============================================================================
    // prefix
    //==============================================================================

    @Schema(description = "省份/评估场景")
    @JSONField(ordinal = 1)
    private String orgCn;

    //==============================================================================
    // node2
    //==============================================================================

    @Schema(description = "对内建设分数")
    @JSONField(ordinal = 2)
    private Object metricScore;

    //==============================================================================
    // node3
    //================================================`==============================

    @Schema(description = "评估指标")
    @JSONField(ordinal = 3)
    private String indexCn;

    @Schema(description = "指标分数")
    @JSONField(ordinal = 4)
    private Object indexScore;

    //==============================================================================
    // node4
    //==============================================================================

    @Schema(description = "评估项")
    @JSONField(ordinal = 5)
    private String assessmentCn;

    @Schema(description = "评估项权重")
    @JSONField(ordinal = 6)
    private Object assessmentWeight;

    @Schema(description = "评估项分数")
    @JSONField(ordinal = 7)
    private Object assessmentScore;

    //==============================================================================
    // node5
    //==============================================================================

    @Schema(description = "评估能力/软件")
    @JSONField(ordinal = 8)
    private String calculatorCn;

    @Schema(description = "评估得分")
    @JSONField(ordinal = 12)
    private Object assessedScore;

    //==============================================================================
    // node6
    //==============================================================================

    @Schema(description = "厂家")
    @JSONField(ordinal = 9)
    private String vendors;

    @Schema(description = "单位造价")
    @JSONField(ordinal = 10)
    private Object var;

    @Schema(description = "标准化上限")
    @JSONField(ordinal = 11)
    private Object upperThreshold;

    public String getOrgCn() {
        return orgCn;
    }

    public void setOrgCn(String orgCn) {
        this.orgCn = orgCn;
    }

    public Object getMetricScore() {
        return metricScore;
    }

    public void setMetricScore(Object metricScore) {
        this.metricScore = metricScore;
    }

    public String getIndexCn() {
        return indexCn;
    }

    public void setIndexCn(String indexCn) {
        this.indexCn = indexCn;
    }

    public Object getIndexScore() {
        return indexScore;
    }

    public void setIndexScore(Object indexScore) {
        this.indexScore = indexScore;
    }

    public String getAssessmentCn() {
        return assessmentCn;
    }

    public void setAssessmentCn(String assessmentCn) {
        this.assessmentCn = assessmentCn;
    }

    public Object getAssessmentWeight() {
        return assessmentWeight;
    }

    public void setAssessmentWeight(Object assessmentWeight) {
        this.assessmentWeight = assessmentWeight;
    }

    public Object getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(Object assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    public String getCalculatorCn() {
        return calculatorCn;
    }

    public void setCalculatorCn(String calculatorCn) {
        this.calculatorCn = calculatorCn;
    }

    public Object getAssessedScore() {
        return assessedScore;
    }

    public void setAssessedScore(Object assessedScore) {
        this.assessedScore = assessedScore;
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

    public Object getUpperThreshold() {
        return upperThreshold;
    }

    public void setUpperThreshold(Object upperThreshold) {
        this.upperThreshold = upperThreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatisticBenefitInternalConstructionProvinceDTO that = (StatisticBenefitInternalConstructionProvinceDTO) o;
        return Objects.equal(orgCn, that.orgCn) && Objects.equal(metricScore, that.metricScore) && Objects.equal(indexCn, that.indexCn) && Objects.equal(indexScore, that.indexScore) && Objects.equal(assessmentCn, that.assessmentCn) && Objects.equal(assessmentWeight, that.assessmentWeight) && Objects.equal(assessmentScore, that.assessmentScore) && Objects.equal(calculatorCn, that.calculatorCn) && Objects.equal(assessedScore, that.assessedScore) && Objects.equal(vendors, that.vendors) && Objects.equal(var, that.var) && Objects.equal(upperThreshold, that.upperThreshold);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgCn, metricScore, indexCn, indexScore, assessmentCn, assessmentWeight, assessmentScore, calculatorCn, assessedScore, vendors, var, upperThreshold);
    }

    @Override
    public String toString() {
        return "StatisticBenefitInternalConstructionProvinceDTO{" +
            "orgCn='" + orgCn + '\'' +
            ", metricScore=" + metricScore +
            ", indexCn='" + indexCn + '\'' +
            ", indexScore=" + indexScore +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", assessmentWeight=" + assessmentWeight +
            ", assessmentScore=" + assessmentScore +
            ", calculatorCn='" + calculatorCn + '\'' +
            ", assessedScore=" + assessedScore +
            ", vendors='" + vendors + '\'' +
            ", var=" + var +
            ", upperThreshold=" + upperThreshold +
            '}';
    }

    public static StatisticBenefitInternalConstructionProvinceDTO frontInstanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO){
        StatisticBenefitInternalConstructionProvinceDTO tableItem = new StatisticBenefitInternalConstructionProvinceDTO();
        tableItem.setOrgCn(orgCn);
        Optional.ofNullable(linkDTO.getNode2())
            .ifPresent(node -> tableItem.setMetricScore(node.getScore()));
        Optional.ofNullable(linkDTO.getNode3())
            .ifPresent(node -> {
                tableItem.setIndexCn(node.getKeyCn());
                tableItem.setIndexScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode4())
            .ifPresent(node -> {
                tableItem.setAssessmentCn(node.getKeyCn());
                tableItem.setAssessmentWeight(node.getWeight());
                tableItem.setAssessmentScore(node.getScore());
            });
        return tableItem;
    }

    public static StatisticBenefitInternalConstructionProvinceDTO backInstanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO){
        StatisticBenefitInternalConstructionProvinceDTO tableItem = new StatisticBenefitInternalConstructionProvinceDTO();
        tableItem.setOrgCn(orgCn);
        Optional.ofNullable(linkDTO.getNode3())
            .ifPresent(node -> {
                tableItem.setIndexCn(node.getKeyCn());
                tableItem.setIndexScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode4())
            .ifPresent(node -> {
                tableItem.setAssessmentCn(node.getKeyCn());
                tableItem.setAssessmentWeight(node.getWeight());
                tableItem.setAssessmentScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode5())
            .ifPresent(node -> {
                tableItem.setCalculatorCn(node.getKeyCn());
                tableItem.setAssessedScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode6())
            .ifPresent(node -> {
                // 这些理应前端处理的结构现在都转到后端了
                ProcessingDataInnerRow processingDataInnerRow = JSON.parseObject(node.getValue().toString(), ProcessingDataInnerRow.class);
                tableItem.setVendors(processingDataInnerRow.getVendors());
                tableItem.setVar(processingDataInnerRow.getVar());
                tableItem.setUpperThreshold(processingDataInnerRow.getThreshold());
            });
        return tableItem;
    }
}
