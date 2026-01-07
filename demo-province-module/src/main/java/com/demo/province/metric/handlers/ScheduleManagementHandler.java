package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.II_SCHEDULE_MANAGEMENT;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/8 15:07
 */
@Component
public class ScheduleManagementHandler extends BaseHandler<EfficiencyPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey =  II_SCHEDULE_MANAGEMENT;
    }
}
