package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.III_PROJECT_LAST_YEAR_PROGRESS;

/**
 * 1.1.2 去年项目
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 11:13
 */
@Component
public class ProjectLastYearProgressHandler extends BaseHandler<EfficiencyPreparedDataBO>{

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_PROJECT_LAST_YEAR_PROGRESS;
    }
}
