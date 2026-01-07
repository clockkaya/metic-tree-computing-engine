package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.utils.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO;

/**
 * 效益/对外赋能/评估省公司收投比
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 14:03
 */
@Component
public class ProvinceCompanyIncomeToInvestmentRatioCalculator extends BenefitProxyCalculator<BenefitPreparedDataBO> {

    private static final String PROCESSING_DATA_CN_1 = "当年安全科目总收入";
    private static final String PROCESSING_DATA_CN_2 = "当年网发安全类项目总投资";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO;
    }

    @Override
    protected void calculateAndSetAssessedValue(BenefitPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 填报本省当年安全科目总收入（包含量子、ICT等）（万元）
        double safetyTotalIncome = Optional.ofNullable(preparedData.getExternalData().getSafetyTotalIncome()).orElse(0.0d);

        // * 本省当年网发安全类项目总投资（万元）【自动生成不用填写】
        double safetyTotalInvestment =  Optional.ofNullable(preparedData.getExternalData().getAutoSafetyTotalInvestment()).orElse(0.0d);

        // a.过程数据
        LinkedHashMap<String, Object> processingDataMap = new LinkedHashMap<>();
        processingDataMap.put(PROCESSING_DATA_CN_1, safetyTotalIncome);
        processingDataMap.put(PROCESSING_DATA_CN_2, safetyTotalInvestment);
        resultNode.setProcessingDataMap(processingDataMap);

        // b.评估值
        BigDecimal assessedValue = NumberUtils.safeDivide(safetyTotalIncome, safetyTotalInvestment);
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    @Override
    public void validatePreparedDataBefore(BenefitPreparedDataBO preparedData) {
        // 暂无
    }
}