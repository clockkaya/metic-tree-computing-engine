package com.sama.ledger.metric.handlers;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.II_SCHEDULE_PROGRESS;

/**
 * @author: huxh
 * @description: 工程进度
 * @datetime: 2025/6/23 9:56
 */
@Component
public class ScheduleProgressHandler extends BaseHandler<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ScheduleProgressHandler.class);

    @Override
    protected void assignHandlerKey() {
        handlerKey = II_SCHEDULE_PROGRESS;
    }

/*    @Override
    public MetricResultNode recursiveHandle(EfficiencyPreparedDataBO preparedData) {
        MetricResultNode resultNode = super.recursiveHandle(preparedData);

        // 非负
        BigDecimal totalScore = resultNode.getTotalScore();
        if (totalScore != null && totalScore.compareTo(BigDecimal.ZERO) < 0) {
            logger.info("【{}】 由 今年项目、去年项目、去年以前项目、总体项目 加权累加得到的 totalScore({}) ，小于0则用0代替！",
                    handlerKey, totalScore);
            resultNode.setTotalScore(BigDecimal.ZERO);
            resultNode.setWeightedScore(BigDecimal.ZERO);
        }
        return resultNode;
    }*/
}
