package com.sama.ledger.metric.calculators;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionL3ProcessingDataEnum;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.III_RATE_PROTECTION_STRATEGY_EFFICIENCY;

/**
 * @author: huxh
 * @description: 全网暴露面安全防护场景|防护策略有效率
 * @datetime: 2025/7/23 14:08
 */
@Component
public class RateProtectionStrategyEfficiencyCalculator extends ComprehensiveProtectionProxyCalculator<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(RateProtectionStrategyEfficiencyCalculator.class);

    @Override
    protected void assignCalculatorKey() {
        calculatorKey = III_RATE_PROTECTION_STRATEGY_EFFICIENCY;
    }

    @Override
    protected void calculateAndSetAssessedValue(ComprehensiveProtectionPreparedDataBO preparedData, MetricResultNode resultNode) {
        List<MetricComprehensiveProtectionL3ProcessingDataEnum> processingDataEnums = MetricComprehensiveProtectionL3ProcessingDataEnum.findByCalculatorEn(getCalculatorKey());
        if (processingDataEnums.size() != 3) {
            logger.error("【{}】 根据 calculatorKey 获取 ProcessingDataEnum 失败，请检查 EffectComprehensiveProtectionProcessingDataEnum 的对应关系！", getCalculatorKey());
            return;
        }
        MetricComprehensiveProtectionL3ProcessingDataEnum firstProcessingDataEnum = processingDataEnums.get(0);
        MetricComprehensiveProtectionL3ProcessingDataEnum secondProcessingDataEnum = processingDataEnums.get(1);
        MetricComprehensiveProtectionL3ProcessingDataEnum thirdProcessingDataEnum = processingDataEnums.get(2);

        // * 检出用例总数量
        Integer firstData = firstProcessingDataEnum.getDataExtractor().apply(preparedData);
        // * 阻断用例总数量
        Integer secondData = secondProcessingDataEnum.getDataExtractor().apply(preparedData);
        // * 实施用例总数量
        Integer thirdData = thirdProcessingDataEnum.getDataExtractor().apply(preparedData);

        // a.过程数据
        LinkedHashMap<String, Object> processingData = new LinkedHashMap<>();
        processingData.put(firstProcessingDataEnum.getProcessingDataCn(), firstData);
        processingData.put(secondProcessingDataEnum.getProcessingDataCn(), secondData);
        processingData.put(thirdProcessingDataEnum.getProcessingDataCn(), thirdData);
        resultNode.setProcessingDataMap(processingData);

        // b.评估值
        Integer firstAddSecond = firstData + secondData;
        BigDecimal assessedValue = NumberUtils.safeDivide(firstAddSecond, thirdData);
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(assessedValue);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    @Override
    public void validatePreparedDataBefore(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 暂无
    }
}
