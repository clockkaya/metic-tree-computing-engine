package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.I_BENEFIT;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/11 16:10
 */
@Component
public class BenefitHandler extends BenefitProxyHandler<BenefitPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(BenefitHandler.class);

    @Override
    protected void assignHandlerKey() {
        handlerKey = I_BENEFIT;
    }

    @Override
    public MetricResultNode recursiveHandle(BenefitPreparedDataBO preparedData) {
        MetricResultNode currentNode = super.recursiveHandle(preparedData);
        if (currentNode.getTotalScore().compareTo(BigDecimal.valueOf(100)) > 0) {
            logger.info("【{}】 原 e.累加总分（{}）超过100上限，人为调整为100！", handlerKey, currentNode.getTotalScore());
            currentNode.setTotalScore(BigDecimal.valueOf(100));
        }
        return currentNode;
    }
}
