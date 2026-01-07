package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionL3ProcessingDataEnum;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 10:07
 */
public abstract class ComprehensiveProtectionProxyCalculator<T extends PreparedDataModel> extends BaseCalculator<T>{

    private static final Logger logger = LogManager.getLogger(ComprehensiveProtectionProxyCalculator.class);

    //==============================================================================
    // utils
    //==============================================================================

    /**
     * 计算上下除数
     */
    public void calculateRate(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        List<MetricComprehensiveProtectionL3ProcessingDataEnum> processingDataEnums = MetricComprehensiveProtectionL3ProcessingDataEnum.findByCalculatorEn(getCalculatorKey());
        if (processingDataEnums.size() < 2) {
            logger.error("【{}】 根据 calculatorKey 获取 ProcessingDataEnum 失败，请检查 EffectComprehensiveProtectionProcessingDataEnum 的对应关系！", getCalculatorKey());
            return;
        }
        MetricComprehensiveProtectionL3ProcessingDataEnum firstProcessingDataEnum = processingDataEnums.get(0);
        MetricComprehensiveProtectionL3ProcessingDataEnum secondProcessingDataEnum = processingDataEnums.get(1);
        // * 上
        Integer firstData = firstProcessingDataEnum.getDataExtractor().apply(preparedData);
        // * 下
        Integer secondData = secondProcessingDataEnum.getDataExtractor().apply(preparedData);

        // a.过程数据
        LinkedHashMap<String, Object> processingData = new LinkedHashMap<>();
        processingData.put(firstProcessingDataEnum.getProcessingDataCn(), firstData);
        processingData.put(secondProcessingDataEnum.getProcessingDataCn(), secondData);
        resultNode.setProcessingDataMap(processingData);

        // b.评估值
        BigDecimal assessedValue = NumberUtils.safeDivide(firstData, secondData);
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }
}
