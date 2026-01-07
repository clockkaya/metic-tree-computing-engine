package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.III_PROJECT_THIS_YEAR_PROGRESS;

/**
 * 1.1.1 今年项目
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 11:11
 */
@Component
public class ProjectThisYearProgressHandler extends BaseHandler<EfficiencyPreparedDataBO>{

    private static final Logger logger = LogManager.getLogger(ProjectThisYearProgressHandler.class);

    @Override
    protected void assignHandlerKey() {
        handlerKey = III_PROJECT_THIS_YEAR_PROGRESS;
    }

    @Override
    public MetricResultNode recursiveHandle(EfficiencyPreparedDataBO preparedData) {
        MetricResultNode currentNode = super.recursiveHandle(preparedData);
            if (currentNode.getTotalScore().compareTo(BigDecimal.valueOf(100)) > 0) {
                logger.info("【{}】 原 e.累加总分（{}）超过100上限，人为调整为100！", handlerKey, currentNode.getTotalScore());
                currentNode.setTotalScore(BigDecimal.valueOf(100));
            }
            return currentNode;
    }

}
