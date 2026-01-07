package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.V_FULL_TRAFFIC_ANALYSIS;

/**
 * 11 流量分析（全流量分析）
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 14:03
 */
@Component
public class AbilityFullTrafficAnalysisCalculator extends BenefitProxyCalculator<BenefitPreparedDataBO> {

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = V_FULL_TRAFFIC_ANALYSIS;
    }

    @Override
    protected void calculateAndSetAssessedValue(BenefitPreparedDataBO preparedData, MetricResultNode resultNode) {
        calculateAverageVar(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(BenefitPreparedDataBO preparedData) {
        // 暂无
    }
}