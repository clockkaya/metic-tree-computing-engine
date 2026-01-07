package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.IV_COMPLIANCE_SOFTWARE;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 17:02
 */
@Component
public class ComplianceSoftwareHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = IV_COMPLIANCE_SOFTWARE;
    }

    @Override
    public MetricResultNode recursiveHandle(BenefitPreparedDataBO preparedData) {
        return averageInsteadOfCumulativeWeightScore(preparedData);
    }

}
