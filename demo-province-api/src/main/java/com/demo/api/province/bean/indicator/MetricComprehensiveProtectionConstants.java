package com.sama.api.ledger.bean.indicator;

/**
 * @author: huxh
 * @description: 所有中文都存于Enum类中
 * @datetime: 2025/7/23 10:52
 */
public class MetricComprehensiveProtectionConstants {

    //==============================================================================
    // I. 指标
    //==============================================================================
    public static final String I_COMPREHENSIVE_PROTECTION = "i_comprehensive_protection";

    //==============================================================================
    // II. 综合场景类型
    //==============================================================================
    public static final String II_SCENARIO_APT_ATTACK_PROTECTION = "ii_scenario_apt_attack_protection";
    public static final String II_SCENARIO_RANSOMWARE_PROTECTION = "ii_scenario_ransomware_protection";
    public static final String II_SCENARIO_HW_SPECIAL_PROTECTION = "ii_scenario_hw_special_protection";
    public static final String II_SCENARIO_EXPOSURE_PROTECTION = "ii_scenario_exposure_protection";

    //==============================================================================
    // III. 评估项（算子）
    //==============================================================================
    public static final String III_RATE_ATTACK_CHAIN_APT = "iii_rate_attack_chain_apt";
    public static final String III_RATE_ATTACK_PATH_APT = "iii_rate_attack_path_apt";
    public static final String III_RATE_ATTACK_AVERAGE_DETECTION_APT = "iii_rate_attack_average_detection_apt";
    public static final String III_RATE_AVERAGE_DETECTION = "iii_rate_average_detection";
    public static final String III_RATE_AVERAGE_BLOCK = "iii_rate_average_block";
    public static final String III_RATE_ATTACK_CHAIN_HW = "iii_rate_attack_chain_hw";
    public static final String III_RATE_ATTACK_PATH_HW = "iii_rate_attack_path_hw";
    public static final String III_RATE_ATTACK_AVERAGE_DETECTION_HW = "iii_rate_attack_average_detection_hw";
    public static final String III_RATE_PROTECTION_STRATEGY_EFFICIENCY = "iii_rate_protection_strategy_efficiency";
    public static final String III_RATE_ASSET_PROTECTION_COVERAGE = "iii_rate_asset_protection_coverage";

}
