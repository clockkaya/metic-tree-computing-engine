package com.sama.ledger.metric.calculators;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_DELIVERY_LAST_YEAR;

/**
 * (1.)1.2.1 去年项目交付率
 * @author: huxh
 * @description:
 * @datetime: 2025/6/18 10:36
 */
@Component
public class ProjectDeliveryLastYearCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectDeliveryLastYearCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "去年立项项目交付的项目数量";
    private static final String PROCESSING_DATA_CN_2 = "去年项目总数";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_DELIVERY_LAST_YEAR;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        int statisticLastYear = statisticYear - 1;
        Date cutoffDate1 = GroceryUtils.buildCutoffDate(statisticYear, 12, 31);
        long data1 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}-1
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticLastYear)
                // （初验时间优先，如果为空取终验时间）在${statisticYear}年12月31日之前（字段有值才参与统计）
                .filter(item -> {
                    Date inspectionApprovalDate = Optional.ofNullable(item.getFirstInspectionApprovalDate()).orElse(item.getFinalInspectionApprovalDate());
                    if (DataUtils.isEmpty(inspectionApprovalDate)){
                        return false;
                    }
                    // endDate - beginDate， 务必注意参数位置
                    return DateUtil.between(inspectionApprovalDate, cutoffDate1, DateUnit.DAY, false) >= 0;
                })
                .count();

        // * 2
        long data2 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}-1
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticLastYear)
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
