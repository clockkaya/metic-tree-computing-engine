package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_ATTACK_PATH_APT;

/**
 * @author: huxh
 * @description: APT攻击防护场景|攻击路径检测率
 * @datetime: 2025/7/23 14:07
 */
@Component
public class RateAttackPathAptCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_ATTACK_PATH_APT;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 攻击链路被检测的用例数量
        // * 攻击链路用例总数量
        calculateRate(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }
}
