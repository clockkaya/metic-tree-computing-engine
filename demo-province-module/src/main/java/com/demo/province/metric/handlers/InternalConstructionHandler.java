package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.II_INTERNAL_CONSTRUCTION;


/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/11 15:24
 */
@Component
public class InternalConstructionHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = II_INTERNAL_CONSTRUCTION;
    }

}
