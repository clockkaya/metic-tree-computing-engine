package com.sama.api.ledger.bean.indicator;

import com.core4ct.utils.DataUtils;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.*;

/**
 * a. ScenarioEnum -> b. CalculatorEnum
 */
public enum MetricComprehensiveProtectionL2CalculatorEnum {
    ATTACK_CHAIN_APT(
            MetricComprehensiveProtectionL1ScenarioEnum.APT_ATTACK_PROTECTION,
            III_RATE_ATTACK_CHAIN_APT,
            "攻击链检测率",
            "攻击链检测率 = 被成功检测的攻击链路数 / 攻击链路总数 * 100%"
    ),
    ATTACK_PATH_APT(
            MetricComprehensiveProtectionL1ScenarioEnum.APT_ATTACK_PROTECTION,
            III_RATE_ATTACK_PATH_APT,
            "攻击节点检测率",
            "攻击节点检测率 = 一条攻击链路内被检测的节点数量 / 该攻击链路包含的节点总数 * 100% * 100%"
    ),
    ATTACK_AVERAGE_DETECTION_APT(
            MetricComprehensiveProtectionL1ScenarioEnum.APT_ATTACK_PROTECTION,
            III_RATE_ATTACK_AVERAGE_DETECTION_APT,
            "平均检测率",
            "平均检测率=攻击链路上被检测到的攻击用例数量/攻击链路包含的攻击用例总数*100%"
    ),
    AVERAGE_DETECTION(
            MetricComprehensiveProtectionL1ScenarioEnum.RANSOMWARE_PROTECTION,
            III_RATE_AVERAGE_DETECTION,
            "平均检测率",
            "平均检测率 = 检出用例总数 / 实施用例总数 * 100%"
    ),
    AVERAGE_BLOCK(
            MetricComprehensiveProtectionL1ScenarioEnum.RANSOMWARE_PROTECTION,
            III_RATE_AVERAGE_BLOCK,
            "平均阻断率",
            "平均阻断率 = 阻断用例总数 / 实施用例总数 * 100%"
    ),
    ATTACK_CHAIN_HW(
            MetricComprehensiveProtectionL1ScenarioEnum.HW_SPECIAL_PROTECTION,
            III_RATE_ATTACK_CHAIN_HW,
            "攻击链检测率",
            "攻击链检测率 = 被成功检测的攻击链路数 / 攻击链路总数 * 100%"
    ),
    ATTACK_PATH_HW(
            MetricComprehensiveProtectionL1ScenarioEnum.HW_SPECIAL_PROTECTION,
            III_RATE_ATTACK_PATH_HW,
            "攻击节点检测率",
            "攻击节点检测率 = 一条攻击链路内被检测的节点数量 / 该攻击链路包含的节点总数 * 100%"
    ),
    ATTACK_AVERAGE_DETECTION_HW(
            MetricComprehensiveProtectionL1ScenarioEnum.HW_SPECIAL_PROTECTION,
            III_RATE_ATTACK_AVERAGE_DETECTION_HW,
            "平均检测率",
            "平均检测率=攻击链路上被检测到的攻击用例数量/攻击链路包含的攻击用例总数*100%"
    ),
    PROTECTION_STRATEGY_EFFICIENCY(
            MetricComprehensiveProtectionL1ScenarioEnum.EXPOSURE_PROTECTION,
            III_RATE_PROTECTION_STRATEGY_EFFICIENCY,
            "防护策略有效率",
            "防护策略有效率 = （检出用例数 + 阻断用例数） / 实施用例数 * 100%"
    ),
    ASSET_PROTECTION_COVERAGE(
            MetricComprehensiveProtectionL1ScenarioEnum.EXPOSURE_PROTECTION,
            III_RATE_ASSET_PROTECTION_COVERAGE,
            "资产防护覆盖度",
            "资产防护覆盖度 = 有效防护资产数 / 测试资产总数 * 100%"
    );

    private final MetricComprehensiveProtectionL1ScenarioEnum scenario;
    // uid
    private final String calculatorEn;
    private final String calculatorCn;
    private final String calculatorComputingMethod;

    MetricComprehensiveProtectionL2CalculatorEnum(MetricComprehensiveProtectionL1ScenarioEnum scenario, String calculatorEn, String calculatorCn, String calculatorComputingMethod) {
        this.scenario = scenario;
        this.calculatorEn = calculatorEn;
        this.calculatorCn = calculatorCn;
        this.calculatorComputingMethod = calculatorComputingMethod;
    }

    public MetricComprehensiveProtectionL1ScenarioEnum getScenario() {
        return scenario;
    }

    public String getCalculatorEn() {
        return calculatorEn;
    }

    public String getCalculatorCn() {
        return calculatorCn;
    }

    public String getCalculatorComputingMethod() {
        return calculatorComputingMethod;
    }

    @Override
    public String toString() {
        return "EffectComprehensiveProtectionCalculatorEnum{" +
                "scenario=" + scenario +
                ", calculatorEn='" + calculatorEn + '\'' +
                ", calculatorCn='" + calculatorCn + '\'' +
                ", calculatorComputingMethod='" + calculatorComputingMethod + '\'' +
                "} " + super.toString();
    }

    /**
     * 根据 scenarioCn 模糊匹配和 calculatorCn 精准匹配查找对应的枚举
     *
     * @param scenarioCn    * 综合场景类型
     * @param calculatorCn  * 评估项
     * @return              b. CalculatorEnum
     */
    public static MetricComprehensiveProtectionL2CalculatorEnum findByScenarioCnLikeAndCalculatorCn(String scenarioCn, String calculatorCn) {
        if (DataUtils.isEmpty(scenarioCn) || DataUtils.isEmpty(calculatorCn)) {
            return null;
        }
        for (MetricComprehensiveProtectionL2CalculatorEnum calculator : MetricComprehensiveProtectionL2CalculatorEnum.values()) {
            if (scenarioCn.contains(calculator.getScenario().getScenarioCnKey()) &&
                    calculator.getCalculatorCn().equals(calculatorCn)) {
                return calculator;
            }
        }
        return null;
    }

}
