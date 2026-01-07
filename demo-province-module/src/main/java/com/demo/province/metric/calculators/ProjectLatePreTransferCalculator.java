package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_LATE_PRE_TRANSFER;

/**
 * (1.)3.1.2 预转固不及时项目数
 * @author: huxh
 * @description:
 * @datetime: 2025/7/1 10:29
 */
@Component
public class ProjectLatePreTransferCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectLatePreTransferCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "预转固不及时的项目数量";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_LATE_PRE_TRANSFER;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        Integer data1 = preparedData.getManual().getLatePreTransferProjectNum();

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
