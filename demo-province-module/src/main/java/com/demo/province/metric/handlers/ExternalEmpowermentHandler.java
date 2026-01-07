package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.II_EXTERNAL_EMPOWERMENT;


/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/11 16:08
 */
@Component
public class ExternalEmpowermentHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = II_EXTERNAL_EMPOWERMENT;
    }
}
