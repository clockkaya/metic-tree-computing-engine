package com.sama.api.ledger.service;

import com.alibaba.fastjson2.JSONObject;
import com.sama.api.ledger.bean.dto.*;
import com.sama.api.ledger.bean.vo.*;

/**
 * 省侧
 * @author: huxh
 * @description:
 * @datetime: 2025/8/25 10:03
 */
public interface ProvinceVisualizationDubboService extends GroupVisualizationService {

    //==============================================================================
    // XX省安全能力成效评估
    //==============================================================================

    /**
     * 综合防护
     *
     * @param orgCode   待获取组织code
     * @return          VO
     */
    VisualizedProvinceBaseVO<StatisticComprehensiveProtectionProvinceDTO> displayComprehensiveProtection(String orgCode);

    /**
     * 效益评估总览
     *
     * @param orgCode   待获取组织code
     * @return          JSONObject
     */
    JSONObject displayBenefitOverview(String orgCode);

    /**
     * 对内建设
     *
     * @param orgCode   待获取组织code
     * @return          JSONObject
     */
    JSONObject displayBenefitInternalConstruction(String orgCode);

    /**
     * 对外赋能
     *
     * @param orgCode   待获取组织code
     * @return          VO
     */
    VisualizedProvinceBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> displayBenefitExternalEmpowerment(String orgCode);

    /**
     * 效率评估总览
     *
     * @param orgCode   待获取组织code
     * @return          VO
     */
    VisualizedProvinceBaseVO<StatisticEfficiencyOverviewProvinceDTO> displayEfficiencyOverview(String orgCode);

    /**
     * 工程进度
     *
     * @param orgCode   待获取组织code
     * @return          VO
     */
    VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> displayEfficiencyProgressSchedule(String orgCode);

    /**
     * 投资进度
     *
     * @param orgCode   待获取组织code
     * @return          VO
     */
    VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> displayEfficiencyInvestmentSchedule(String orgCode);

    /**
     * 特殊：效果评估总览——综合防护分数统计
     *
     * @param orgCode           待获取组织code
     * @return                  LineChartDTO
     */
    LineChartDTO offerVisualizedComprehensiveProtectionChart(String orgCode);

    //==============================================================================
    // 全网安全能力成效评估 GroupVisualizationDubboService
    //==============================================================================

}
