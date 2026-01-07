package com.sama.api.ledger.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.base.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 对外赋能省级表格
 * @author: huxh
 * @description:
 * @datetime: 2025/9/1 11:15
 */
@Schema(description = "对外赋能省级表格")
public class StatisticBenefitExternalEmpowermentProvinceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3689796737151442951L;

    //==============================================================================
    // prefix
    //==============================================================================

    @Schema(description = "省份/评估场景")
    @JSONField(ordinal = 1)
    private String orgCn;

    //==============================================================================
    // node2
    //==============================================================================

    @Schema(description = "对外赋能分数")
    @JSONField(ordinal = 2)
    private Object metricScore;

    //==============================================================================
    // node3
    //==============================================================================

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

    @Schema(description = "评估得分")
    @JSONField(ordinal = 6)
    private Object assessmentScore;

    @Schema(description = "评估值")
    @JSONField(ordinal = 7)
    private Object assessmentValue;

    //==============================================================================
    // node5
    //==============================================================================

    @Schema(description = "过程数据")
    @JSONField(ordinal = 8)
    private Object processingDataCn;

    @Schema(description = "过程数据值")
    @JSONField(ordinal = 9)
    private Object processingDataValue;

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

    public Object getProcessingDataCn() {
        return processingDataCn;
    }

    public void setProcessingDataCn(Object processingDataCn) {
        this.processingDataCn = processingDataCn;
    }

    public Object getProcessingDataValue() {
        return processingDataValue;
    }

    public void setProcessingDataValue(Object processingDataValue) {
        this.processingDataValue = processingDataValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatisticBenefitExternalEmpowermentProvinceDTO that = (StatisticBenefitExternalEmpowermentProvinceDTO) o;
        return Objects.equal(orgCn, that.orgCn) && Objects.equal(metricScore, that.metricScore) && Objects.equal(indexCn, that.indexCn) && Objects.equal(indexScore, that.indexScore) && Objects.equal(assessmentCn, that.assessmentCn) && Objects.equal(assessmentScore, that.assessmentScore) && Objects.equal(assessmentValue, that.assessmentValue) && Objects.equal(processingDataCn, that.processingDataCn) && Objects.equal(processingDataValue, that.processingDataValue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgCn, metricScore, indexCn, indexScore, assessmentCn, assessmentScore, assessmentValue, processingDataCn, processingDataValue);
    }

    @Override
    public String toString() {
        return "StatisticBenefitExternalEmpowermentProvinceDTO{" +
            "orgCn='" + orgCn + '\'' +
            ", metricScore=" + metricScore +
            ", indexCn='" + indexCn + '\'' +
            ", indexScore=" + indexScore +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", assessmentScore=" + assessmentScore +
            ", assessmentValue=" + assessmentValue +
            ", processingDataCn=" + processingDataCn +
            ", processingDataValue=" + processingDataValue +
            '}';
    }

    public static StatisticBenefitExternalEmpowermentProvinceDTO instanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO, Object lowerThreshold){
        StatisticBenefitExternalEmpowermentProvinceDTO tableItem = new StatisticBenefitExternalEmpowermentProvinceDTO();
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
                tableItem.setAssessmentScore(node.getScore());
                tableItem.setAssessmentValue(node.getValue());
            });
        Optional.ofNullable(linkDTO.getNode5())
            .ifPresent(node -> {
                tableItem.setProcessingDataCn(node.getKeyCn());
                tableItem.setProcessingDataValue(node.getValue());
            });
        return tableItem;
    }
}
