package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_ATTACK_CHAIN_APT;

/**
 * @author: huxh
 * @description: APT攻击防护场景|攻击链检测率
 * @datetime: 2025/7/23 14:06
 */
@Component
public class RateAttackChainAptCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(RateAttackChainAptCalculator.class);

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_ATTACK_CHAIN_APT;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 攻击链路被检测的数量
        // * 攻击链路总数量
        calculateRate(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }
}
