package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.II_SCENARIO_HW_SPECIAL_PROTECTION;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 13:41
 */
@Component
public class ScenarioHwSpecialProtectionHandler extends BaseHandler<ComprehensiveProtectionPreparedDataBO>{
    @Override
    protected void assignHandlerKey() {
        handlerKey = II_SCENARIO_HW_SPECIAL_PROTECTION;
    }
}
