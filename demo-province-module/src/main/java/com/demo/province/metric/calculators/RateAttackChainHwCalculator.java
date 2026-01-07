package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_ATTACK_CHAIN_HW;

/**
 * @author: huxh
 * @description: HW/专项对抗防护场景|攻击链检测率
 * @datetime: 2025/7/23 14:07
 */
@Component
public class RateAttackChainHwCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(RateAttackChainHwCalculator.class);

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_ATTACK_CHAIN_HW;
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
