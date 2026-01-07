package com.sama.api.ledger.bean.indicator;

/**
 * @author: huxh
 * @description: 原则上，关于层级结构的划分应出自一处，不应再使用冗余 Enum
 * @datetime: 2025/6/17 9:56
 */
public class MetricEfficiencyConstants {

    //==============================================================================
    // I. 指标
    //==============================================================================
    public static final String I_EFFICIENCY = "i_efficiency";

    //==============================================================================
    // II. 评估场景
    //==============================================================================
    public static final String II_SCHEDULE_PROGRESS = "ii_schedule_progress";
    public static final String II_SCHEDULE_INVESTMENT = "ii_schedule_investment";
    public static final String II_SCHEDULE_MANAGEMENT = "ii_schedule_management";

    //==============================================================================
    // III. 项目类别
    //==============================================================================
    public static final String III_PROJECT_THIS_YEAR_PROGRESS = "iii_project_this_year_progress";
    public static final String III_PROJECT_LAST_YEAR_PROGRESS = "iii_project_last_year_progress";
    public static final String III_PROJECT_THIS_YEAR_INVESTMENT = "iii_project_this_year_investment";
    public static final String III_PROJECT_OVERALL_INVESTMENT = "iii_project_overall_investment";
    public static final String III_PROJECT_OVERALL_MANAGEMENT = "iii_project_overall_management";

    //==============================================================================
    // IV. 评估项（算子名称）
    //==============================================================================
    public static final String IV_PROJECT_APPROVAL_COMPLETION = "iv_project_approval_completion";
    public static final String IV_PROJECT_DESIGN_APPROVAL_THIS_YEAR = "iv_project_design_approval_this_year";
    public static final String IV_PROJECT_DELIVERY_THIS_YEAR = "iv_project_delivery_this_year";
    public static final String IV_PROJECT_DELIVERY_LAST_YEAR = "iv_project_delivery_last_year";
    public static final String IV_PROJECT_CLOSE_LAST_YEAR = "iv_project_close_last_year";
    public static final String IV_PROJECT_NEW_BOOK_COMPLETION = "iv_project_new_book_completion";
    public static final String IV_PROJECT_WHOLE_BOOK_COMPLETION = "iv_project_whole_book_completion";
    public static final String IV_PROJECT_LATE_BOOK = "iv_project_late_book";
    public static final String IV_PROJECT_LATE_PRE_TRANSFER = "iv_project_late_pre_transfer";
    public static final String IV_PROJECT_LATE_CLOSE = "iv_project_late_close";
    public static final String IV_PROJECT_LONG_TERM_DEBT = "iv_project_long_term_debt";

    //==============================================================================
    // V. 过程数据
    //==============================================================================
}
