package com.sama.ledger.metric.calculators;

import cn.hutool.core.date.DateUtil;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_NEW_BOOK_COMPLETION;

/**
 * (1.)2.1.1 新立项项目列账完成率
 * @author: huxh
 * @description:
 * @datetime: 2025/7/1 11:20
 */
@Component
public class ProjectNewBookCompletionCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectNewBookCompletionCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "截至四季度新立项项目支出金额（元）";
    private static final String PROCESSING_DATA_CN_2 = "今年立项总投资（元）";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_NEW_BOOK_COMPLETION;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        double data1 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 导出时间截止日期${statisticYear}1231，资本性支出总和
                .mapToDouble(item -> item.getYearlyCapitalExpenditure() == null ? 0.0 : item.getYearlyCapitalExpenditure())
                .sum();

        // * 2
        double data2 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 导出时间截止日期${statisticYear}1231，单项总投资和
                .mapToDouble(item -> item.getSingleItemInvestment() == null ? 0.0 : item.getSingleItemInvestment())
                .sum();

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
