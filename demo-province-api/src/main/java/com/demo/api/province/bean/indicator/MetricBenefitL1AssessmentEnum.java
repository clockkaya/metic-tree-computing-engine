package com.sama.api.ledger.bean.indicator;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;

public enum MetricBenefitL1AssessmentEnum {

    DEFENSE_IN_DEPTH_CAPABILITY(
        IV_DEFENSE_IN_DEPTH_CAPABILITY,
        "安全纵深能力-单位造价",
        INVISIBLE_UNIT_COST
    ),
    COMPLIANCE_CAPABILITY(
        IV_COMPLIANCE_CAPABILITY,
        "安全合规能力-单位造价",
        INVISIBLE_UNIT_COST
    ),
    COMPLIANCE_SOFTWARE(
        IV_COMPLIANCE_SOFTWARE,
        "安全合规软件-软件投资",
        INVISIBLE_SOFTWARE_INVESTMENT
    ),
    OPERATIONS_SYSTEM(
        IV_OPERATION_SYSTEM,
        "安全运营系统-软件投资",
        INVISIBLE_SOFTWARE_INVESTMENT
    );

    private final String assessmentEn;
    private final String assessmentCn;
    private final String invisibleParent;

    MetricBenefitL1AssessmentEnum(String assessmentEn, String assessmentCn, String invisibleParent) {
        this.assessmentEn = assessmentEn;
        this.assessmentCn = assessmentCn;
        this.invisibleParent = invisibleParent;
    }

    public String getAssessmentEn() {
        return assessmentEn;
    }

    public String getAssessmentCn() {
        return assessmentCn;
    }

    public String getInvisibleParent() {
        return invisibleParent;
    }

    @Override
    public String toString() {
        return "MetricBenefitL1AssessmentEnum{" +
            "assessmentEn='" + assessmentEn + '\'' +
            ", assessmentCn='" + assessmentCn + '\'' +
            ", invisibleParent='" + invisibleParent + '\'' +
            "} " + super.toString();
    }
}
