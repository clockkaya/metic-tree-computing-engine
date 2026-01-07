package com.sama.ledger.metric.calculators;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.core4ct.exception.IllegalParameterException;
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

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.IV_PROJECT_APPROVAL_COMPLETION;

/**
 * (1.)1.1.1 项目立项完成率
 * @author: huxh
 * @description:
 * @datetime: 2025/6/16 16:28
 */
@Component
public class ProjectApprovalCompletionCalculator extends BaseCalculator<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ProjectApprovalCompletionCalculator.class);

    private static final String PROCESSING_DATA_CN_1 = "截止三季度已立项数量";
    private static final String PROCESSING_DATA_CN_2 = "今年安全项目总数";
    private static final String PROCESSING_DATA_CN_3 = "修正项（四季度立项属于特殊情况）";

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = IV_PROJECT_APPROVAL_COMPLETION;
    }

    @Override
    protected void calculateAndSetAssessedValue(EfficiencyPreparedDataBO preparedData, MetricResultNode resultNode) {
        // * 1
        Date cutoffDate1 = GroceryUtils.buildCutoffDate(statisticYear, 9, 30);
        long data1 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 立项批复日期 ${statisticYear}年9月30日之前
                .filter(item -> {
                    if (DataUtils.isEmpty(item.getProjectApprovalDate())){
                        return false;
                    }
                    return DateUtil.between(item.getProjectApprovalDate(), cutoffDate1, DateUnit.DAY, false) >= 0;
                })
                .count();

        // * 2
        Date cutoffDate2 = GroceryUtils.buildCutoffDate(statisticYear, 12, 31);
        long data2 = preparedData.getInProgressData().stream()
                // 立项年份 ${statisticYear}
                .filter(item -> DateUtil.year(item.getProjectYear()) == statisticYear)
                // 立项批复日期 ${statisticYear}年12月31日之前
                .filter(item -> {
                    if (DataUtils.isEmpty(item.getProjectApprovalDate())){
                        return false;
                    }
                    return DateUtil.between(item.getProjectApprovalDate(), cutoffDate2, DateUnit.DAY, false) >= 0;
                })
                .count();

        // * 3
        long data3 = Optional.ofNullable(preparedData.getManual().getFixedItem()).orElse(0);

        // a.过程数据
        LinkedHashMap<String, Object> processingDataMap = new LinkedHashMap<>();
        processingDataMap.put(PROCESSING_DATA_CN_1, data1);
        processingDataMap.put(PROCESSING_DATA_CN_2, data2);
        processingDataMap.put(PROCESSING_DATA_CN_3, data3);
        resultNode.setProcessingDataMap(processingDataMap);

        // b.评估值
        BigDecimal assessedValue = NumberUtils.safeDivide(data1, (data2 - data3));
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    @Override
    public void validatePreparedDataBefore(EfficiencyPreparedDataBO preparedData) {
        // 立项年份，必填项
        preparedData.getInProgressData().forEach(item -> {
            if (DataUtils.isEmpty(item.getProjectYear())){
                throw new IllegalParameterException("#立项年份 不可为空！");
            }
        });
    }

}
