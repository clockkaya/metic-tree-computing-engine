package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_ASSET_PROTECTION_COVERAGE;

/**
 * @author: huxh
 * @description: 全网暴露面安全防护场景|资产防护覆盖度
 * @datetime: 2025/7/23 14:08
 */
@Component
public class RateAssetProtectionCoverageCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(RateAssetProtectionCoverageCalculator.class);

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_ASSET_PROTECTION_COVERAGE;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 有效防护资产数量
        // * 测试资产总数量
        calculateRate(preparedData, resultNode);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }
}
