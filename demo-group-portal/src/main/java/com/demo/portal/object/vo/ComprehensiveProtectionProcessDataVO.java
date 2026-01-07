package com.sama.officer.object.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 过程数据
 * @author: huxh
 * @description:
 * @datetime: 2025/10/27 15:58
 */
@Schema(description = "综合防护展示导出表")
@ExcelIgnoreUnannotated
public class ComprehensiveProtectionProcessDataVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7198586728927409002L;

    @Schema(description =  "省份")
    @ExcelProperty(value = "省份", index = 0)
    private String orgCn;

    @Schema(description =  "综合场景类型")
    @ExcelProperty(value = "综合场景类型", index = 1)
    private String comprehensiveScenarioType;

    @Schema(description =  "评估项")
    @ExcelProperty(value = "评估项", index = 2)
    private String evaluationItem;

    @Schema(description =  "过程数据")
    @ExcelProperty(value = "过程数据", index = 3)
    private String processingDataDisplay;

    @Schema(description =  "评估值")
    @ExcelProperty(value = "评估值", index = 4)
    private BigDecimal assessedValue;

    @Schema(description =  "计算方法")
    @ExcelProperty(value = "计算方法", index = 5)
    private String calculationMethod;

    @JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "GMT+8"
    )
    @Schema(description = "更新时间")
    @ExcelProperty(value = "创建时间", index = 6)
    private Date updateTime;

    @JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "GMT+8"
    )
    @Schema(description = "创建时间")
    @ExcelProperty(value = "更新时间", index = 7)
    private Date createTime;

    public String getOrgCn() {
        return orgCn;
    }

    public void setOrgCn(String orgCn) {
        this.orgCn = orgCn;
    }

    public String getComprehensiveScenarioType() {
        return comprehensiveScenarioType;
    }

    public void setComprehensiveScenarioType(String comprehensiveScenarioType) {
        this.comprehensiveScenarioType = comprehensiveScenarioType;
    }

    public String getEvaluationItem() {
        return evaluationItem;
    }

    public void setEvaluationItem(String evaluationItem) {
        this.evaluationItem = evaluationItem;
    }

    public String getProcessingDataDisplay() {
        return processingDataDisplay;
    }

    public void setProcessingDataDisplay(String processingDataDisplay) {
        this.processingDataDisplay = processingDataDisplay;
    }

    public BigDecimal getAssessedValue() {
        return assessedValue;
    }

    public void setAssessedValue(BigDecimal assessedValue) {
        this.assessedValue = assessedValue;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ComprehensiveProtectionProcessDataVO{" +
            "orgCn='" + orgCn + '\'' +
            ", comprehensiveScenarioType='" + comprehensiveScenarioType + '\'' +
            ", evaluationItem='" + evaluationItem + '\'' +
            ", processingDataDisplay='" + processingDataDisplay + '\'' +
            ", assessedValue=" + assessedValue +
            ", calculationMethod='" + calculationMethod + '\'' +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            '}';
    }
}
