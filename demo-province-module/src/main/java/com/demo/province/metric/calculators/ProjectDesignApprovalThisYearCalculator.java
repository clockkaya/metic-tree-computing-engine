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

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_DESIGN_APPROVAL_THIS_YEAR;

/**
 * (1.)1.1.2 今年项目设计批复率
 * @author: huxh
 * @description:
 * @datetime: 2025/6/17 16:29
 */
@Component
public class ProjectDesignApprovalThisYearCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectDesignApprovalThisYearCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "设计批复完成的项目数量";
    private static final String PROCESSING_DATA_CN_2 = "今年安全项目总数";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_DESIGN_APPROVAL_THIS_YEAR;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        long data1 = preparedData.getInProgressData().stream()
                // 立项年份：${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 设计类型：一次设计或两次设计 设计批复日期 有值
                // 设计类型：无设计 立项批复日期 有值
                .filter(item -> {
                    String designType = item.getDesignType();
                    if (DataUtils.isNotEmpty(designType)) {
                        if ("一次设计".equals(designType) || "两次设计".equals(designType)){
                            return DataUtils.isNotEmpty(item.getDesignApprovalDate());
                        } else if ("无设计".equals(designType)){
                            return DataUtils.isNotEmpty(item.getProjectApprovalDate());
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
