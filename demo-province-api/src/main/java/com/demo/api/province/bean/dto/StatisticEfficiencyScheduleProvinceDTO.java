package com.sama.api.ledger.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.base.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 工程进度/投资进度省级表格
 * @author: huxh
 * @description:
 * @datetime: 2025/9/1 13:51
 */
@Schema(description = "工程进度/投资进度省级表格")
public class StatisticEfficiencyScheduleProvinceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6967469686711721771L;

    //==============================================================================
    // prefix
    //==============================================================================

    @Schema(description = "组织 code")
    @JSONField(ordinal = 0)
    private String orgCode;

    @Schema(description = "省份/评估场景")
    @JSONField(ordinal = 1)
    private String orgCn;

    //==============================================================================
    // node2
    //==============================================================================

    @Schema(description = "进度分数")
    @JSONField(ordinal = 2)
    private Object metricScore;

    //==============================================================================
    // node3
    //==============================================================================

    @Schema(description = "项目类别")
    @JSONField(ordinal = 3)
    private String categoryCn;

    @Schema(description = "项目类别权重")
    @JSONField(ordinal = 4)
    private Object categoryWeight;

    @Schema(description = "项目分数")
    @JSONField(ordinal = 5)
    private Object categoryScore;

    //==============================================================================
    // node4
    //==============================================================================

    @Schema(description = "评估项")
    @JSONField(ordinal = 6)
    private String assessmentCn;

    @Schema(description = "评估项权重")
    @JSONField(ordinal = 7)
    private Object assessmentWeight;

    @Schema(description = "评估项得分")
    @JSONField(ordinal = 8)
    private Object assessmentScore;

    @Schema(description = "评估值")
    @JSONField(ordinal = 9)
    private Object assessmentValue;

    //==============================================================================
    // node5
    //==============================================================================

    @Schema(description = "过程数据")
    @JSONField(ordinal = 10)
    private String processingDataCn;

    @Schema(description = "过程数据值")
    @JSONField(ordinal = 11)
    private Object processingData;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

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

    public String getCategoryCn() {
        return categoryCn;
    }

    public void setCategoryCn(String categoryCn) {
        this.categoryCn = categoryCn;
    }

    public Object getCategoryWeight() {
        return categoryWeight;
    }

    public void setCategoryWeight(Object categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    public Object getCategoryScore() {
        return categoryScore;
    }

    public void setCategoryScore(Object categoryScore) {
        this.categoryScore = categoryScore;
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

    public Object getAssessmentValue() {
        return assessmentValue;
    }

    public void setAssessmentValue(Object assessmentValue) {
        this.assessmentValue = assessmentValue;
    }

    public String getProcessingDataCn() {
        return processingDataCn;
    }

    public void setProcessingDataCn(String processingDataCn) {
        this.processingDataCn = processingDataCn;
    }

    public Object getProcessingData() {
        return processingData;
    }

    public void setProcessingData(Object processingData) {
        this.processingData = processingData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEfficiencyScheduleProvinceDTO that = (StatisticEfficiencyScheduleProvinceDTO) o;
        return Objects.equal(orgCode, that.orgCode) && Objects.equal(orgCn, that.orgCn) && Objects.equal(metricScore, that.metricScore) && Objects.equal(categoryCn, that.categoryCn) && Objects.equal(categoryWeight, that.categoryWeight) && Objects.equal(categoryScore, that.categoryScore) && Objects.equal(assessmentCn, that.assessmentCn) && Objects.equal(assessmentWeight, that.assessmentWeight) && Objects.equal(assessmentScore, that.assessmentScore) && Objects.equal(assessmentValue, that.assessmentValue) && Objects.equal(processingDataCn, that.processingDataCn) && Objects.equal(processingData, that.processingData);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgCode, orgCn, metricScore, categoryCn, categoryWeight, categoryScore, assessmentCn, assessmentWeight, assessmentScore, assessmentValue, processingDataCn, processingData);
    }

    @Override
    public String toString() {
        return "StatisticEfficiencyScheduleProvinceDTO{" +
            "orgCode='" + orgCode + '\'' +
            ", orgCn='" + orgCn + '\'' +
            ", metricScore=" + metricScore +
            ", categoryCn='" + categoryCn + '\'' +
            ", categoryWeight=" + categoryWeight +
            ", categoryScore=" + categoryScore +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", assessmentWeight=" + assessmentWeight +
            ", assessmentScore=" + assessmentScore +
            ", assessmentValue=" + assessmentValue +
            ", processingDataCn='" + processingDataCn + '\'' +
            ", processingData=" + processingData +
            '}';
    }

    /**
     * 特殊表格行化
     *
     * @param orgCode   特殊携参
     * @param orgCn
     * @param linkDTO
     * @return
     */
    public static StatisticEfficiencyScheduleProvinceDTO instanceFormLinkDTO(String orgCode, String orgCn, PrettyLinkDTO linkDTO){
        StatisticEfficiencyScheduleProvinceDTO tableItem = instanceFormLinkDTO(orgCn, linkDTO);
        tableItem.setOrgCode(orgCode);
        return tableItem;
    }

    public static StatisticEfficiencyScheduleProvinceDTO instanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO){
        StatisticEfficiencyScheduleProvinceDTO tableItem = new StatisticEfficiencyScheduleProvinceDTO();
        tableItem.setOrgCn(orgCn);
        Optional.ofNullable(linkDTO.getNode2())
            .ifPresent(node -> tableItem.setMetricScore(node.getScore()));
        Optional.ofNullable(linkDTO.getNode3())
            .ifPresent(node -> {
                tableItem.setCategoryCn(node.getKeyCn());
                tableItem.setCategoryWeight(node.getWeight());
                tableItem.setCategoryScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode4())
            .ifPresent(node -> {
                tableItem.setAssessmentCn(node.getKeyCn());
                tableItem.setAssessmentWeight(node.getWeight());
                tableItem.setAssessmentScore(node.getScore());
                tableItem.setAssessmentValue(node.getValue());
            });
        Optional.ofNullable(linkDTO.getNode5())
            .ifPresent(node -> {
                tableItem.setProcessingDataCn(node.getKeyCn());
                tableItem.setProcessingData(node.getValue());
            });
        return tableItem;
    }
}
