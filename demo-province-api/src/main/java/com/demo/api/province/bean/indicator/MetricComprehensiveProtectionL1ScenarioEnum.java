package com.sama.api.ledger.bean.indicator;

import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.*;

/**
 * a. ScenarioEnum
 */
public enum MetricComprehensiveProtectionL1ScenarioEnum {

    APT_ATTACK_PROTECTION(II_SCENARIO_APT_ATTACK_PROTECTION, "APT攻击防护场景", "攻击"),
    RANSOMWARE_PROTECTION(II_SCENARIO_RANSOMWARE_PROTECTION, "勒索病毒防护场景", "病毒"),
    HW_SPECIAL_PROTECTION(II_SCENARIO_HW_SPECIAL_PROTECTION, "HW/专项对抗防护场景", "专项"),
    EXPOSURE_PROTECTION(II_SCENARIO_EXPOSURE_PROTECTION, "全网暴露面安全防护场景", "暴露");

    private final String scenarioEn;
    private final String scenarioCn;
    private final String scenarioCnKey;

    MetricComprehensiveProtectionL1ScenarioEnum(String scenarioEn, String scenarioCn, String scenarioCnKey) {
        this.scenarioEn = scenarioEn;
        this.scenarioCn = scenarioCn;
        this.scenarioCnKey = scenarioCnKey;
    }

    public String getScenarioEn() {
        return scenarioEn;
    }

    public String getScenarioCn() {
        return scenarioCn;
    }

    public String getScenarioCnKey() {
        return scenarioCnKey;
    }

    @Override
    public String toString() {
        return "EffectComprehensiveProtectionScenarioEnum{" +
                "scenarioEn='" + scenarioEn + '\'' +
                ", scenarioCn='" + scenarioCn + '\'' +
                ", scenarioCnKey='" + scenarioCnKey + '\'' +
                "} " + super.toString();
    }

}
