package com.sama.officer.object.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 过程数据
 * @author: huxh
 * @description:
 * @datetime: 2025/10/27 16:00
 */
@Schema(description = "对外赋值数据展示导出表")
@ExcelIgnoreUnannotated
public class BenefitExternalEmpowermentProcessDataVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9168922622569420978L;

    @Schema(description =  "省份")
    @ExcelProperty(value = "省份", index = 0)
    private String orgCn;

    @Schema(description = "序号")
    @ExcelProperty(value = "序号", index = 1)
    private Long itemNo;

    @Schema(description = "填表单位")
    @ExcelProperty(value = "填表单位", index = 2)
    private String provincialCompany;

    @Schema(description = "本省当年网发安全类项目总投资（万元）")
    @ExcelProperty(value = "本省当年网发安全类项目总投资（万元）", index = 3)
    private Double autoSafetyTotalInvestment;

    @Schema(description = "本省当年安全科目总收入（包含量子、ICT等）（万元）")
    @ExcelProperty(value = "本省当年安全科目总收入（包含量子、ICT等）（万元）", index = 4)
    private Double safetyTotalIncome;

    @JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "GMT+8"
    )
    @Schema(description = "更新时间")
    @ExcelProperty(value = "创建时间", index = 5)
    private Date updateTime;

    @JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss",
        timezone = "GMT+8"
    )
    @Schema(description = "创建时间")
    @ExcelProperty(value = "更新时间", index = 6)
    private Date createTime;

    public String getOrgCn() {
        return orgCn;
    }

    public void setOrgCn(String orgCn) {
        this.orgCn = orgCn;
    }

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getProvincialCompany() {
        return provincialCompany;
    }

    public void setProvincialCompany(String provincialCompany) {
        this.provincialCompany = provincialCompany;
    }

    public Double getAutoSafetyTotalInvestment() {
        return autoSafetyTotalInvestment;
    }

    public void setAutoSafetyTotalInvestment(Double autoSafetyTotalInvestment) {
        this.autoSafetyTotalInvestment = autoSafetyTotalInvestment;
    }

    public Double getSafetyTotalIncome() {
        return safetyTotalIncome;
    }

    public void setSafetyTotalIncome(Double safetyTotalIncome) {
        this.safetyTotalIncome = safetyTotalIncome;
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
        return "BenefitExternalEmpowermentProcessDataVO{" +
            "orgCn='" + orgCn + '\'' +
            ", itemNo=" + itemNo +
            ", provincialCompany='" + provincialCompany + '\'' +
            ", autoSafetyTotalInvestment=" + autoSafetyTotalInvestment +
            ", safetyTotalIncome=" + safetyTotalIncome +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            '}';
    }
}
