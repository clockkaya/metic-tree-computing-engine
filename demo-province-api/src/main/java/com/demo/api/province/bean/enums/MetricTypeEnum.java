package com.sama.api.ledger.bean.enums;

public enum MetricTypeEnum {

    COMPREHENSIVE_PROTECTION(8, "综合防护"),

    BENEFIT_DEV(3, "效益(DEV)"),

    BENEFIT_RELEASE(4, "效益(RELEASE)"),

    EFFICIENCY(2, "效率"),

    DEPTH_IN_DEFENSE(5, "效果-纵深防御"),

    OPERATE_EFFICIENCY(6, "效果-运营效能"),

    COMPLIANCE_CONSTRUCT(7, "效果-合规建设"),

    EFFECT_CATEGORY(9, "效果"),

    VISUAL_OVERVIEW(10, "可视化总览"),

    OPERATE_CAPABILITY_AVG(11, "效果-运营效能全网平均"),

    COMPLIANCE_CAPABILITY_AVG(12, "效果-合规建设全网平均");

    // to extend

    /**
     * 即 MetricConfigDO.metricType
     */
    private int type;

    private String name;

    MetricTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getMetricNameByType(int type) {
        MetricTypeEnum[] values = values();

        for(MetricTypeEnum metricType : values) {
            if (metricType.getType() == type) {
                return metricType.getName();
            }
        }

        return "未知";
    }
}
