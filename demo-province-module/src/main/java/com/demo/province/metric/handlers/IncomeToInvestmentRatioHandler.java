package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.III_INCOME_TO_INVESTMENT_RATIO;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 17:01
 */
@Component
public class IncomeToInvestmentRatioHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_INCOME_TO_INVESTMENT_RATIO;
    }

}
