package com.sama.api.ledger.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sama.api.ledger.bean.bo.PageBaseModel;
import com.sama.api.ledger.bean.utils.MetricIntegerConverter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * 综合防护原表+反向更新字段
 * @author: huxh
 * @description:
 * @datetime: 2025/7/23 10:42
 */
@Schema(description = "综合防护原表")
@TableName("sama_ledger.comprehensive_protection")
@ExcelIgnoreUnannotated
public class ComprehensiveProtectionExtendedDO extends PageBaseModel {

    private static final long serialVersionUID = -147253548800799623L;

    @Schema(description = "综合场景类型")
    @ExcelProperty(value = "综合场景类型", index = 0)
    private String comprehensiveScenarioType;

    @Schema(description = "评估项")
    @ExcelProperty(value = "评估项", index = 2)
    private String evaluationItem;

    @Schema(description = "计算方法")
    @ExcelProperty(value = "评估项", index = 4)
    private String calculationMethod;

    @Schema(description = "过程数据中文key")
    @ExcelProperty(value = "过程数据", index = 5)
    private String processingDataCn;

    @Schema(description = "过程数据单位")
    @ExcelProperty(value = "单位", index = 6)
    private String processingDataUnit;

    @Schema(description = "过程数据值")
    @ExcelProperty(value = "过程数据值", index = 7, converter = MetricIntegerConverter.class)
    private Integer processingData;

    @Schema(description = "评估值")
    private BigDecimal assessedValue;

    @Schema(description = "组织code")
    private String orgCode;

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

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getProcessingDataCn() {
        return processingDataCn;
    }

    public void setProcessingDataCn(String processingDataCn) {
        this.processingDataCn = processingDataCn;
    }

    public String getProcessingDataUnit() {
        return processingDataUnit;
    }

    public void setProcessingDataUnit(String processingDataUnit) {
        this.processingDataUnit = processingDataUnit;
    }

    public Integer getProcessingData() {
        return processingData;
    }

    public void setProcessingData(Integer processingData) {
        this.processingData = processingData;
    }

    public BigDecimal getAssessedValue() {
        return assessedValue;
    }

    public void setAssessedValue(BigDecimal assessedValue) {
        this.assessedValue = assessedValue;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "ComprehensiveProtectionExtendedDO{" +
                "comprehensiveScenarioType='" + comprehensiveScenarioType + '\'' +
                ", evaluationItem='" + evaluationItem + '\'' +
                ", calculationMethod='" + calculationMethod + '\'' +
                ", processingDataCn='" + processingDataCn + '\'' +
                ", processingDataUnit='" + processingDataUnit + '\'' +
                ", processingData=" + processingData +
                ", assessedValue=" + assessedValue +
                ", orgCode='" + orgCode + '\'' +
                "} " + super.toString();
    }
}
