package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.V_CONTENT_SECURITY_REVIEW_PUBLISH_CONTROL;

/**
 * 23 内容安全“先审后发”管控
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 14:03
 */
@Component
public class SoftwareContentSecurityReviewPublishControlCalculator extends BenefitProxyCalculator<BenefitPreparedDataBO> {

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = V_CONTENT_SECURITY_REVIEW_PUBLISH_CONTROL;
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