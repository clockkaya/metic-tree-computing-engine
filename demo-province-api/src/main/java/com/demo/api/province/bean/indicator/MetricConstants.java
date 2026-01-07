package com.sama.api.ledger.bean.indicator;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;
import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.I_COMPREHENSIVE_PROTECTION;
import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.*;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/16 9:33
 */
public class MetricConstants {

    public final class UpdateMode {

        /**
         * 强制更新
         */
        public static final Integer FORCE = 0;

        /**
         * 条件更新
         */
        public static final Integer CONDITIONAL = 1;

        private UpdateMode() {
        }
    }

    public final class TurnoverMode {

        /**
         * 硬删除
         */
        public static final Integer HARD = 0;

        /**
         * 软删除
         */
        public static final Integer SOFT = 1;

        private TurnoverMode() {
        }
    }

    public final class KafkaTopic {

        /**
         * metric_result ——> metric_result_union
         */
        public static final String METRIC_RESULT = "message_result";

        /**
         * group_statistic ——> group_statistic_bak
         */
        public static final String GROUP_STATISTIC = "message_statistic";

        private KafkaTopic() {
        }
    }

    public final class DimensionKey {

        public static final String THRESHOLD = "threshold";

        public static final String COMPREHENSIVE_PROTECTION = I_COMPREHENSIVE_PROTECTION;

        public static final String BENEFIT = I_BENEFIT;

        public static final String BENEFIT_INTERNAL_CONSTRUCTION = II_INTERNAL_CONSTRUCTION;

        public static final String BENEFIT_EXTERNAL_EMPOWERMENT = II_EXTERNAL_EMPOWERMENT;

        public static final String EFFICIENCY = I_EFFICIENCY;

        public static final String EFFICIENCY_PROGRESS = II_SCHEDULE_PROGRESS;

        public static final String EFFICIENCY_INVESTMENT = II_SCHEDULE_INVESTMENT;

        private DimensionKey() {
        }
    }

}
