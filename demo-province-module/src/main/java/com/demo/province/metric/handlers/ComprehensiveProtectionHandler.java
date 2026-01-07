package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.I_COMPREHENSIVE_PROTECTION;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 13:45
 */
@Component
public class ComprehensiveProtectionHandler extends BaseHandler<ComprehensiveProtectionPreparedDataBO>{
    @Override
    protected void assignHandlerKey() {
        handlerKey = I_COMPREHENSIVE_PROTECTION;
    }
}
