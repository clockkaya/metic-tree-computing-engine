package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.III_PROJECT_OVERALL_MANAGEMENT;

/**
 * 1.3.1 总体项目（扣分项）
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 11:15
 */
@Component
public class ProjectOverallManagementHandler extends BaseHandler<EfficiencyPreparedDataBO>{

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_PROJECT_OVERALL_MANAGEMENT;
    }
}
