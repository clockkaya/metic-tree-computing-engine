package com.sama.api.ledger.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.base.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 综合防护省级表格
 * @author: huxh
 * @description:
 * @datetime: 2025/9/1 9:56
 */
@Schema(description = "综合防护省级表格")
public class StatisticComprehensiveProtectionProvinceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6444339058409706509L;

    //==============================================================================
    // prefix
    //==============================================================================

    @Schema(description = "组织 code")
    @JSONField(ordinal = 0)
    private String orgCode;

    @Schema(description = "省份")
    @JSONField(ordinal = 1)
    private String orgCn;

    //==============================================================================
    // node1
    //==============================================================================

    @Schema(description = "综合防护分数")
    @JSONField(ordinal = 2)
    private Object metricScore;

    //==============================================================================
    // node2
    //==============================================================================

    @Schema(description = "评估能力")
    @JSONField(ordinal = 3)
    private String scenarioCn;

    @Schema(description = "评估能力权重")
    @JSONField(ordinal = 4)
    private Object scenarioWeight;

    @Schema(description = "能力分数")
    @JSONField(ordinal = 5)
    private Object scenarioScore;

    //==============================================================================
    // node3
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

    @Schema(description = "评估项值")
    @JSONField(ordinal = 9)
    private Object assessmentValue;

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

    public String getScenarioCn() {
        return scenarioCn;
    }

    public void setScenarioCn(String scenarioCn) {
        this.scenarioCn = scenarioCn;
    }

    public Object getScenarioWeight() {
        return scenarioWeight;
    }

    public void setScenarioWeight(Object scenarioWeight) {
        this.scenarioWeight = scenarioWeight;
    }

    public Object getScenarioScore() {
        return scenarioScore;
    }

    public void setScenarioScore(Object scenarioScore) {
        this.scenarioScore = scenarioScore;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatisticComprehensiveProtectionProvinceDTO that = (StatisticComprehensiveProtectionProvinceDTO) o;
        return Objects.equal(orgCode, that.orgCode) && Objects.equal(orgCn, that.orgCn) && Objects.equal(metricScore, that.metricScore) && Objects.equal(scenarioCn, that.scenarioCn) && Objects.equal(scenarioWeight, that.scenarioWeight) && Objects.equal(scenarioScore, that.scenarioScore) && Objects.equal(assessmentCn, that.assessmentCn) && Objects.equal(assessmentWeight, that.assessmentWeight) && Objects.equal(assessmentScore, that.assessmentScore) && Objects.equal(assessmentValue, that.assessmentValue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgCode, orgCn, metricScore, scenarioCn, scenarioWeight, scenarioScore, assessmentCn, assessmentWeight, assessmentScore, assessmentValue);
    }

    @Override
    public String toString() {
        return "StatisticComprehensiveProtectionProvinceDTO{" +
            "orgCode='" + orgCode + '\'' +
            ", orgCn='" + orgCn + '\'' +
            ", metricScore=" + metricScore +
            ", scenarioCn='" + scenarioCn + '\'' +
            ", scenarioWeight=" + scenarioWeight +
            ", scenarioScore=" + scenarioScore +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", assessmentWeight=" + assessmentWeight +
            ", assessmentScore=" + assessmentScore +
            ", assessmentValue=" + assessmentValue +
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
    public static StatisticComprehensiveProtectionProvinceDTO instanceFormLinkDTO(String orgCode, String orgCn, PrettyLinkDTO linkDTO){
        StatisticComprehensiveProtectionProvinceDTO tableItem = instanceFormLinkDTO(orgCn, linkDTO);
        tableItem.setOrgCode(orgCode);
        return tableItem;
    }

    public static StatisticComprehensiveProtectionProvinceDTO instanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO){
        StatisticComprehensiveProtectionProvinceDTO tableItem = new StatisticComprehensiveProtectionProvinceDTO();
        tableItem.setOrgCn(orgCn);
        Optional.ofNullable(linkDTO.getNode1())
            .ifPresent(node -> tableItem.setMetricScore(node.getScore()));
        Optional.ofNullable(linkDTO.getNode2())
            .ifPresent(node -> {
                tableItem.setScenarioCn(node.getKeyCn());
                tableItem.setScenarioWeight(node.getWeight());
                tableItem.setScenarioScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode3())
            .ifPresent(node -> {
                tableItem.setAssessmentCn(node.getKeyCn());
                tableItem.setAssessmentWeight(node.getWeight());
                tableItem.setAssessmentScore(node.getScore());
                tableItem.setAssessmentValue(node.getValue());
            });
        return tableItem;
    }
}
