package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.II_SCHEDULE_INVESTMENT;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/25 16:15
 */
@Component
public class ScheduleInvestmentHandler extends BaseHandler<EfficiencyPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = II_SCHEDULE_INVESTMENT;
    }
}
