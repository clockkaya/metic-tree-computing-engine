package com.sama.analytic.metric.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.api.analytic.service.GroupVisualizationDubboService;
import com.sama.analytic.service.GroupStatisticService;
import com.sama.api.ledger.bean.GroupStatisticDO;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 集团侧可视化展示
 * @author: huxh
 * @description:
 * @datetime: 2025/10/15 9:00
 */
@DubboService
@RefreshScope
public class GroupVisualizationDubboServiceImpl implements GroupVisualizationDubboService {

    private static final Logger logger = LogManager.getLogger(GroupVisualizationDubboServiceImpl.class);

    @Resource
    GroupStatisticService groupStatisticService;

    @Override
    public VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> zoomComprehensiveProtection() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.COMPREHENSIVE_PROTECTION, null);
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|综合防护】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> zoomBenefitOverview() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT, null);
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|效益评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public JSONObject zoomBenefitInternalConstruction() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT_INTERNAL_CONSTRUCTION, null);
        JSONObject groupVO = JSON.parseObject(groupStatistic.getRecord(), JSONObject.class);
        logger.info("【全网展示|对内建设】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public JSONObject zoomBenefitExternalEmpowerment() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.BENEFIT_EXTERNAL_EMPOWERMENT, null);
        JSONObject groupVO = JSON.parseObject(groupStatistic.getRecord(), JSONObject.class);
        logger.info("【全网展示|对外赋能】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> zoomEfficiencyOverview() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY, null);
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|效率评估总览】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyProgressSchedule() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY_PROGRESS, null);
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|工程进度】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }

    @Override
    public VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> zoomEfficiencyInvestmentSchedule() {
        GroupStatisticDO groupStatistic = groupStatisticService.getAlignedRecordWithValidation(MetricConstants.DimensionKey.EFFICIENCY_INVESTMENT, null);
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> groupVO = JSON.parseObject(groupStatistic.getRecord(), VisualizedGroupBaseVO.class);
        logger.info("【全网展示|投资进度】 处理成功，返回 VO: {}", JSON.toJSONString(groupVO));

        return groupVO;
    }
}
