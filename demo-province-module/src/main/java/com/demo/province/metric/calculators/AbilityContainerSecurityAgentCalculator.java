package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.V_CONTAINER_SECURITY_AGENT;

/**
 * 20 容器安全-纯agent
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 14:03
 */
@Component
public class AbilityContainerSecurityAgentCalculator extends BenefitProxyCalculator<BenefitPreparedDataBO> {

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = V_CONTAINER_SECURITY_AGENT;
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