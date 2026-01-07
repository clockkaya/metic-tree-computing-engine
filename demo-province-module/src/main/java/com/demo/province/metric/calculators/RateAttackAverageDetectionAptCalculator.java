package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_ATTACK_AVERAGE_DETECTION_APT;

/**
 * @author: huxh
 * @description: APT攻击防护场景|攻击节点平均检测率
 * @datetime: 2025/7/29 15:58
 */
@Component
public class RateAttackAverageDetectionAptCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_ATTACK_AVERAGE_DETECTION_APT;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 攻击链路上被检测到的攻击用例数量
        // * 攻击链路包含的攻击用例总数
        calculateRate(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }

}
