package com.sama.api.ledger.bean.indicator;

import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * a. ScenarioEnum -> b. CalculatorEnum -> c. ProcessingDataEnum
 */
public enum MetricComprehensiveProtectionL3ProcessingDataEnum {

    ATTACK_CHAIN_APT_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_CHAIN_APT,
            "攻击链路被检测的数量",
            ComprehensiveProtectionPreparedDataBO::getAttackChainAptFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackChainAptFirst
    ),
    ATTACK_CHAIN_APT_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_CHAIN_APT,
            "攻击链路总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackChainAptSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackChainAptSecond
    ),
    ATTACK_PATH_APT_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_PATH_APT,
            "攻击链路被检测的攻击节点数量",
            ComprehensiveProtectionPreparedDataBO::getAttackPathAptFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackPathAptFirst
    ),
    ATTACK_PATH_APT_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_PATH_APT,
            "攻击链路攻击节点总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackPathAptSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackPathAptSecond
    ),
    ATTACK_AVERAGE_DETECTION_APT_FIRST(
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_AVERAGE_DETECTION_APT,
            "攻击链路被检测的用例数量",
            ComprehensiveProtectionPreparedDataBO::getAttackAverageDetectionAptFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackAverageDetectionAptFirst
    ),
    ATTACK_AVERAGE_DETECTION_APT_SECOND(
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_AVERAGE_DETECTION_APT,
            "攻击链路用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackAverageDetectionAptSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackAverageDetectionAptSecond
    ),
    AVERAGE_DETECTION_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.AVERAGE_DETECTION,
            "检出用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAverageDetectionFirst,
            ComprehensiveProtectionPreparedDataBO::setAverageDetectionFirst
    ),
    AVERAGE_DETECTION_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.AVERAGE_DETECTION,
            "实施用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAverageDetectionSecond,
            ComprehensiveProtectionPreparedDataBO::setAverageDetectionSecond
    ),
    AVERAGE_BLOCK_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.AVERAGE_BLOCK,
            "阻断用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAverageBlockFirst,
            ComprehensiveProtectionPreparedDataBO::setAverageBlockFirst
    ),
    AVERAGE_BLOCK_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.AVERAGE_BLOCK,
            "实施用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAverageBlockSecond,
            ComprehensiveProtectionPreparedDataBO::setAverageBlockSecond
    ),
    ATTACK_CHAIN_HW_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_CHAIN_HW,
            "攻击链路被检测的数量",
            ComprehensiveProtectionPreparedDataBO::getAttackChainHwFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackChainHwFirst
    ),
    ATTACK_CHAIN_HW_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_CHAIN_HW,
            "攻击链路总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackChainHwSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackChainHwSecond
    ),
    ATTACK_PATH_HW_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_PATH_HW,
            "攻击链路被检测的攻击节点数量",
            ComprehensiveProtectionPreparedDataBO::getAttackPathHwFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackPathHwFirst
    ),
    ATTACK_PATH_HW_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_PATH_HW,
            "攻击链路攻击节点总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackPathHwSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackPathHwSecond
    ),
    ATTACK_AVERAGE_DETECTION_HW_FIRST(
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_AVERAGE_DETECTION_HW,
            "攻击链路被检测的用例数量",
            ComprehensiveProtectionPreparedDataBO::getAttackAverageDetectionHwFirst,
            ComprehensiveProtectionPreparedDataBO::setAttackAverageDetectionHwFirst
    ),
    ATTACK_AVERAGE_DETECTION_HW_SECOND(
            MetricComprehensiveProtectionL2CalculatorEnum.ATTACK_AVERAGE_DETECTION_HW,
            "攻击链路用例总数量",
            ComprehensiveProtectionPreparedDataBO::getAttackAverageDetectionHwSecond,
            ComprehensiveProtectionPreparedDataBO::setAttackAverageDetectionHwSecond
    ),
    PROTECTION_STRATEGY_EFFICIENCY_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.PROTECTION_STRATEGY_EFFICIENCY,
            "检出用例总数量",
            ComprehensiveProtectionPreparedDataBO::getProtectionStrategyEfficiencyFirst,
            ComprehensiveProtectionPreparedDataBO::setProtectionStrategyEfficiencyFirst
    ),
    PROTECTION_STRATEGY_EFFICIENCY_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.PROTECTION_STRATEGY_EFFICIENCY,
            "阻断用例总数量",
            ComprehensiveProtectionPreparedDataBO::getProtectionStrategyEfficiencySecond,
            ComprehensiveProtectionPreparedDataBO::setProtectionStrategyEfficiencySecond
    ),
    PROTECTION_STRATEGY_EFFICIENCY_THIRD (
            MetricComprehensiveProtectionL2CalculatorEnum.PROTECTION_STRATEGY_EFFICIENCY,
            "实施用例总数量",
            ComprehensiveProtectionPreparedDataBO::getProtectionStrategyEfficiencyThird,
            ComprehensiveProtectionPreparedDataBO::setProtectionStrategyEfficiencyThird
    ),
    ASSET_PROTECTION_COVERAGE_FIRST (
            MetricComprehensiveProtectionL2CalculatorEnum.ASSET_PROTECTION_COVERAGE,
            "有效防护资产数量",
            ComprehensiveProtectionPreparedDataBO::getAssetProtectionCoverageFirst,
            ComprehensiveProtectionPreparedDataBO::setAssetProtectionCoverageFirst
    ),
    ASSET_PROTECTION_COVERAGE_SECOND (
            MetricComprehensiveProtectionL2CalculatorEnum.ASSET_PROTECTION_COVERAGE,
            "测试资产总数量",
            ComprehensiveProtectionPreparedDataBO::getAssetProtectionCoverageSecond,
            ComprehensiveProtectionPreparedDataBO::setAssetProtectionCoverageSecond
    );

    private final MetricComprehensiveProtectionL2CalculatorEnum calculator;
    private final String processingDataCn;
    // ComprehensiveProtectionPreparedDataBO 特定列 -> all getter
    private final Function<ComprehensiveProtectionPreparedDataBO, Integer> dataExtractor;
    // ComprehensiveProtectionPreparedDataBO 特定列 -> all setter
    private final BiConsumer<ComprehensiveProtectionPreparedDataBO, Integer> dataSetter;

    MetricComprehensiveProtectionL3ProcessingDataEnum(MetricComprehensiveProtectionL2CalculatorEnum calculator, String processingDataCn, Function<ComprehensiveProtectionPreparedDataBO, Integer> dataExtractor, BiConsumer<ComprehensiveProtectionPreparedDataBO, Integer> dataSetter) {
        this.calculator = calculator;
        this.processingDataCn = processingDataCn;
        this.dataExtractor = dataExtractor;
        this.dataSetter = dataSetter;
    }

    public MetricComprehensiveProtectionL2CalculatorEnum getCalculator() {
        return calculator;
    }

    public String getProcessingDataCn() {
        return processingDataCn;
    }

    public Function<ComprehensiveProtectionPreparedDataBO, Integer> getDataExtractor() {
        return dataExtractor;
    }

    public BiConsumer<ComprehensiveProtectionPreparedDataBO, Integer> getDataSetter() {
        return dataSetter;
    }

    @Override
    public String toString() {
        return "EffectComprehensiveProtectionProcessingDataEnum{" +
                "calculator=" + calculator +
                ", processingDataCn='" + processingDataCn + '\'' +
                ", dataExtractor=" + dataExtractor +
                ", dataSetter=" + dataSetter +
                "} " + super.toString();
    }

    /**
     * 根据 scenarioCn 模糊匹配、calculatorCn 精准匹配、processingDataCn 精准匹配查找对应的枚举
     *
     * @param scenarioCn        * 综合场景类型
     * @param calculatorCn      * 评估项
     * @param processingDataCn  * 过程数据
     * @return                  c. ProcessingDataEnum
     */
    public static MetricComprehensiveProtectionL3ProcessingDataEnum findByScenarioCalculatorAndProcessingDataCn(
            String scenarioCn, String calculatorCn, String processingDataCn) {

        MetricComprehensiveProtectionL2CalculatorEnum calculatorEnum =
                MetricComprehensiveProtectionL2CalculatorEnum.findByScenarioCnLikeAndCalculatorCn(scenarioCn, calculatorCn);
        if (calculatorEnum == null || DataUtils.isEmpty(processingDataCn)) {
            return null;
        }

        for (MetricComprehensiveProtectionL3ProcessingDataEnum processingDataEnum : values()) {
            if (processingDataEnum.getCalculator().equals(calculatorEnum)
                    && processingDataEnum.getProcessingDataCn().equals(processingDataCn)) {
                return processingDataEnum;
            }
        }
        return null;
    }

    /**
     * 根据 calculatorEn 精准匹配查找对应的枚举列表（First必在前）
     *
     * @param calculatorEn  calculator Key
     * @return              List<EffectComprehensiveProtectionProcessingDataEnum>
     */
    public static List<MetricComprehensiveProtectionL3ProcessingDataEnum> findByCalculatorEn(String calculatorEn) {
        List<MetricComprehensiveProtectionL3ProcessingDataEnum> target = new ArrayList<>();
        for (MetricComprehensiveProtectionL3ProcessingDataEnum processingDataEnum : values()) {
            if (processingDataEnum.getCalculator().getCalculatorEn().equals(calculatorEn)) {
                target.add(processingDataEnum);
            }
        }
        return target;
    }

}
