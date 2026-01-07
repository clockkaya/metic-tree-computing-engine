package com.sama.api.ledger.service;

import com.alibaba.fastjson2.JSONObject;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;

/**
 * 集团侧（非Dubbo）
 * @author: huxh
 * @description: 多处继承、实现
 * @datetime: 2025/8/25 15:33
 */
public interface GroupVisualizationService {

    //==============================================================================
    // 全网安全能力成效评估
    //==============================================================================

    /**
     * 综合防护
     *
     * @return  VO
     */
    VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> zoomComprehensiveProtection();

    /**
     * 效益评估总览
     *
     * @return  VO
     */
    VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> zoomBenefitOverview();

    /**
     * 对内建设
     *
     * @return  JSONObject
     */
    JSONObject zoomBenefitInternalConstruction();

    /**
     * 对外赋能
     *
     * @return JSONObject
     */
    JSONObject zoomBenefitExternalEmpowerment();

    /**
     * 效率评估总览
     *
     * @return  VO
     */
    VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> zoomEfficiencyOverview();

    /**
     * 工程进度
     *
     * @return  VO
     */
    VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyProgressSchedule();

    /**
     * 投资进度
     *
     * @return  VO
     */
    VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyInvestmentSchedule();

}
