package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_LONG_TERM_DEBT;

/**
 * (1.)3.1.4 长期挂账项目数
 * @author: huxh
 * @description:
 * @datetime: 2025/7/1 10:32
 */
@Component
public class ProjectLongTermDebtCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectLongTermDebtCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "长期挂账项目数量";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_LONG_TERM_DEBT;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        Integer data1 = preparedData.getManual().getLongTermDebtProjectNum();

        // a.过程数据
        LinkedHashMap<String, Object> processingDataMap = new LinkedHashMap<>();
        processingDataMap.put(PROCESSING_DATA_CN_1, data1);
        resultNode.setProcessingDataMap(processingDataMap);

        // b.评估值
        Integer assessedValue = Optional.ofNullable(data1).orElse(0);
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    @Override
    public void validatePreparedDataBefore(EfficiencyPreparedDataBO preparedData) {
        // 暂无
    }
}
