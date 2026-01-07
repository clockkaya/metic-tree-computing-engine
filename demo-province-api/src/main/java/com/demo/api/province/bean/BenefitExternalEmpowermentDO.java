package com.sama.api.ledger.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sama.api.ledger.bean.bo.PageBaseModel;

import java.io.Serial;

/**
 * 效益对外赋能原表
 * @author: huxh
 * @description:
 * @datetime: 2025/9/4 13:33
 */
@TableName("sama_ledger.benefit_external_empowerment")
@ExcelIgnoreUnannotated
public class BenefitExternalEmpowermentDO extends PageBaseModel {

    @Serial
    private static final long serialVersionUID = 7126766251443363874L;

    @ExcelProperty(value = "序号", index = 0)
    private Long itemNo;

    @ExcelProperty(value = "填表单位", index = 1)
    private String provincialCompany;

    @ExcelProperty(value = "本省当年网发安全类项目总投资（万元）【自动生成不用填写】", index = 2)
    private Double autoSafetyTotalInvestment;

    @ExcelProperty(value = "填报本省当年安全科目总收入（包含量子、ICT等）（万元）", index = 3)
    private Double safetyTotalIncome;

    /**
     * 组织code
     */
    private String orgCode;

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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "BenefitExternalEmpowermentDO{" +
            "itemNo=" + itemNo +
            ", provincialCompany='" + provincialCompany + '\'' +
            ", autoSafetyTotalInvestment=" + autoSafetyTotalInvestment +
            ", safetyTotalIncome=" + safetyTotalIncome +
            ", orgCode='" + orgCode + '\'' +
            "} " + super.toString();
    }
}
