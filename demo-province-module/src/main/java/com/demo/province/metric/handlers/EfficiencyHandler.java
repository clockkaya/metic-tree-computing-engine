package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.I_EFFICIENCY;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/27 13:39
 */
@Component
public class EfficiencyHandler extends BaseHandler<EfficiencyPreparedDataBO>{

    @Override
    protected void assignHandlerKey() {
        handlerKey = I_EFFICIENCY;
    }
}
