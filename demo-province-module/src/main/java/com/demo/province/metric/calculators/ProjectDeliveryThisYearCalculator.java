package com.sama.ledger.metric.calculators;

import cn.hutool.core.date.DateUtil;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_DELIVERY_THIS_YEAR;

/**
 * (1.)1.1.3 今年项目交付率
 * @author: huxh
 * @description:
 * @datetime: 2025/6/18 9:59
 */
@Component
public class ProjectDeliveryThisYearCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectDeliveryThisYearCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "今年立项且初验或终验完成的项目数量";
    private static final String PROCESSING_DATA_CN_2 = "今年安全项目总数";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_DELIVERY_THIS_YEAR;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        long data1 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 验收类型：一次验收 终验批复日期有值
                // 验收类型：两次验收 初验批复日期有值
                .filter(item -> {
                    String inspectionType = item.getInspectionType();
                    if (DataUtils.isNotEmpty(inspectionType)) {
                        if ("一次验收".equals(inspectionType)){
                            return DataUtils.isNotEmpty(item.getFinalInspectionApprovalDate());
                        } else if ("两次验收".equals(inspectionType)){
                            return DataUtils.isNotEmpty(item.getFirstInspectionApprovalDate());
                        }
                    }
                    return false;
                })
                .count();

        // * 2
        long data2 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                .count();

        // a.过程数据
        LinkedHashMap<String, Object> processingDataMap = new LinkedHashMap<>();
        processingDataMap.put(PROCESSING_DATA_CN_1, data1);
        processingDataMap.put(PROCESSING_DATA_CN_2, data2);
        resultNode.setProcessingDataMap(processingDataMap);

        // b.评估值
        BigDecimal assessedValue = NumberUtils.safeDivide(data1, data2);
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    @Override
    public void validatePreparedDataBefore(EfficiencyPreparedDataBO preparedData) {
        // 暂无
    }

}
