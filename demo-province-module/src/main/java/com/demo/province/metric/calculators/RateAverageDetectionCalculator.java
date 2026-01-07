package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_AVERAGE_DETECTION;

/**
 * @author: huxh
 * @description: 勒索病毒防护场景|平均检测率
 * @datetime: 2025/7/23 14:07
 */
@Component
public class RateAverageDetectionCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(RateAverageDetectionCalculator.class);

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_AVERAGE_DETECTION;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 检出用例总数量
        // * 实施用例总数量
        calculateRate(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }
}
