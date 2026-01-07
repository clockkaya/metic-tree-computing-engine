package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.III_PROJECT_THIS_YEAR_INVESTMENT;

/**
 * 1.2.1 今年项目
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 11:11
 */
@Component
public class ProjectThisYearInvestmentHandler extends BaseHandler<EfficiencyPreparedDataBO>{

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_PROJECT_THIS_YEAR_INVESTMENT;
    }
}
