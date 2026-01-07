package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.III_CONSTRUCTION_COST;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/24 11:00
 */
@Component
public class ConstructionCostHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_CONSTRUCTION_COST;
    }

}