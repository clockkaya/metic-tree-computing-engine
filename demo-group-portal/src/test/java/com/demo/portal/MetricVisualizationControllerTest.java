package com.sama.officer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.api.analytic.bean.DTO.TimeCountDTO;
import com.api.analytic.service.AbilityEventDubboService;
import com.api.analytic.service.GroupVisualizationDubboService;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/4 14:09
 */
@SpringBootTest(classes = SamaOfficerApplication.class)
public class MetricVisualizationControllerTest {

    private static final Logger logger = LogManager.getLogger(MetricVisualizationControllerTest.class);

    @DubboReference
    AbilityEventDubboService abilityEventDubboService;

    @DubboReference
    GroupVisualizationDubboService groupVisualizationDubboService;

    /**
     * 需要先 build analytic-api，再 maven syn
     */
    @Test
    public void dubboServiceTest(){
        Map<String, List<TimeCountDTO>> test = abilityEventDubboService.getAbilityTrend(new Date());
        logger.info("【breakpoint】 可以调用 analytic Dubbo service, test: {}", JSON.toJSONString(test));
    }

    /**
     * 解决 /metric/zoom/comprehensiveProtection 405
     */
    @Test
    public void fixError(){
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = groupVisualizationDubboService.zoomComprehensiveProtection();
        VisualizedGroupBaseVO controllerVO = Objects.requireNonNullElseGet(targetVO, VisualizedGroupBaseVO::new);
        logger.info("controllerVO: {}", JSON.toJSONString(controllerVO));
    }

    /**
     * 全网安全能力成效评估，仅校验序列化问题
     */
    @Test
    public void allZoomSerializationTest(){
        zoomComprehensiveProtectionTest();
        zoomBenefitOverviewTest();
        zoomBenefitInternalConstructionTest();
        zoomBenefitExternalEmpowermentTest();
        zoomEfficiencyOverviewTest();
        zoomEfficiencyProgressScheduleTest();
        zoomEfficiencyInvestmentScheduleTest();
    }

    @Test
    public void zoomComprehensiveProtectionTest(){
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = groupVisualizationDubboService.zoomComprehensiveProtection();
        logger.info("综合防护: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomBenefitOverviewTest(){
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> targetVO = groupVisualizationDubboService.zoomBenefitOverview();
        logger.info("效益评估总览: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomBenefitInternalConstructionTest() {
        JSONObject targetVO = groupVisualizationDubboService.zoomBenefitInternalConstruction();
        logger.info("对内建设: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomBenefitExternalEmpowermentTest(){
        JSONObject targetVO = groupVisualizationDubboService.zoomBenefitExternalEmpowerment();
        logger.info("对外赋能: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomEfficiencyOverviewTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO = groupVisualizationDubboService.zoomEfficiencyOverview();
        logger.info("效率评估总览: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomEfficiencyProgressScheduleTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = groupVisualizationDubboService.zoomEfficiencyProgressSchedule();
        logger.info("工程进度: {}", JSON.toJSONString(targetVO));
    }

    @Test
    public void zoomEfficiencyInvestmentScheduleTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = groupVisualizationDubboService.zoomEfficiencyInvestmentSchedule();
        logger.info("投资进度: {}", JSON.toJSONString(targetVO));
    }
}
