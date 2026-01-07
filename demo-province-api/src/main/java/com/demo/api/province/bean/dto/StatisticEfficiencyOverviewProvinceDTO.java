package com.sama.api.ledger.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.google.common.base.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 效率评估总览省级表格
 * @author: huxh
 * @description:
 * @datetime: 2025/9/1 13:40
 */
@Schema(description = "效率评估总览省级表格")
public class StatisticEfficiencyOverviewProvinceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2350815419045597060L;

    //==============================================================================
    // prefix
    //==============================================================================

    @Schema(description = "省份/维度")
    @JSONField(ordinal = 1)
    private String orgCn;

    //==============================================================================
    // node1
    //==============================================================================

    @Schema(description = "效率分数")
    @JSONField(ordinal = 2)
    private Object metricScore;

    //==============================================================================
    // node2
    //==============================================================================

    @Schema(description = "评估场景")
    @JSONField(ordinal = 3)
    private String scenarioCn;

    @Schema(description = "评估场景权重")
    @JSONField(ordinal = 4)
    private Object scenarioWeight;

    @Schema(description = "场景分数")
    @JSONField(ordinal = 5)
    private Object scenarioScore;

    //==============================================================================
    // node3
    //==============================================================================

    @Schema(description = "项目类别")
    @JSONField(ordinal = 6)
    private String categoryCn;

    @Schema(description = "项目类别权重")
    @JSONField(ordinal = 7)
    private Object categoryWeight;

    @Schema(description = "项目分数")
    @JSONField(ordinal = 8)
    private Object categoryScore;

    //==============================================================================
    // node4
    //==============================================================================

    @Schema(description = "评估项")
    @JSONField(ordinal = 9)
    private String assessmentCn;

    @Schema(description = "评估项权重")
    @JSONField(ordinal = 10)
    private Object assessmentWeight;

    @Schema(description = "评估项得分")
    @JSONField(ordinal = 11)
    private Object assessmentScore;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEfficiencyOverviewProvinceDTO that = (StatisticEfficiencyOverviewProvinceDTO) o;
        return Objects.equal(orgCn, that.orgCn) && Objects.equal(metricScore, that.metricScore) && Objects.equal(scenarioCn, that.scenarioCn) && Objects.equal(scenarioWeight, that.scenarioWeight) && Objects.equal(scenarioScore, that.scenarioScore) && Objects.equal(categoryCn, that.categoryCn) && Objects.equal(categoryWeight, that.categoryWeight) && Objects.equal(categoryScore, that.categoryScore) && Objects.equal(assessmentCn, that.assessmentCn) && Objects.equal(assessmentWeight, that.assessmentWeight) && Objects.equal(assessmentScore, that.assessmentScore);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orgCn, metricScore, scenarioCn, scenarioWeight, scenarioScore, categoryCn, categoryWeight, categoryScore, assessmentCn, assessmentWeight, assessmentScore);
    }

    @Override
    public String toString() {
        return "StatisticEfficiencyOverviewProvinceDTO{" +
            "orgCn='" + orgCn + '\'' +
            ", metricScore=" + metricScore +
            ", scenarioCn='" + scenarioCn + '\'' +
            ", scenarioWeight=" + scenarioWeight +
            ", scenarioScore=" + scenarioScore +
            ", categoryCn='" + categoryCn + '\'' +
            ", categoryWeight=" + categoryWeight +
            ", categoryScore=" + categoryScore +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", assessmentWeight=" + assessmentWeight +
            ", assessmentScore=" + assessmentScore +
            '}';
    }

    public static StatisticEfficiencyOverviewProvinceDTO instanceFormLinkDTO(String orgCn, PrettyLinkDTO linkDTO){
        StatisticEfficiencyOverviewProvinceDTO tableItem = new StatisticEfficiencyOverviewProvinceDTO();
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
                tableItem.setCategoryCn(node.getKeyCn());
                tableItem.setCategoryWeight(node.getWeight());
                tableItem.setCategoryScore(node.getScore());
            });
        Optional.ofNullable(linkDTO.getNode4())
            .ifPresent(node -> {
                tableItem.setAssessmentCn(node.getKeyCn());
                tableItem.setAssessmentWeight(node.getWeight());
                tableItem.setAssessmentScore(node.getScore());
            });
        return tableItem;
    }
}
