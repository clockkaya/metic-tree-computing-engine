package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.IV_DEFENSE_IN_DEPTH_CAPABILITY;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 17:01
 */
@Component
public class DefenseInDepthCapabilityHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = IV_DEFENSE_IN_DEPTH_CAPABILITY;
    }

    @Override
    public MetricResultNode recursiveHandle(BenefitPreparedDataBO preparedData) {
        return averageInsteadOfCumulativeWeightScore(preparedData);
    }

}
