package com.sama.analytic.metric;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.sama.analytic.AnalyticApplication;
import com.sama.analytic.metric.support.GroupVisualizationServiceImpl;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 集团侧可视化处理
 * @author: huxh
 * @description:
 * @datetime: 2025/10/20 16:45
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class GroupVisualizationServiceTest {

    private static final Logger logger = LogManager.getLogger(GroupVisualizationServiceTest.class);

    @Resource
    GroupVisualizationServiceImpl groupVisualizationService;

    /**
     * 全网安全能力成效评估，只有此处需要严格检验格式
     */
    @Test
    public void allZoomCalculationTest(){
        zoomComprehensiveProtectionTest();
        zoomBenefitOverviewTest();
        zoomBenefitInternalConstructionTest();
        zoomBenefitExternalEmpowermentTest();
        zoomEfficiencyOverviewTest();
        zoomEfficiencyProgressScheduleTest();
        zoomEfficiencyInvestmentScheduleTest();
    }

    /**
     * 综合防护 = 10 条
     */
    @Test
    public void zoomComprehensiveProtectionTest(){
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = groupVisualizationService.zoomComprehensiveProtection();
        int exampleTableSize = targetVO.getProvinceAndTableMap().values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(10, exampleTableSize);
    }

    /**
     * 效益评估总览 = 5 条
     */
    @Test
    public void zoomBenefitOverviewTest(){
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> targetVO = groupVisualizationService.zoomBenefitOverview();
        int exampleTableSize = targetVO.getProvinceAndTableMap().values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(5, exampleTableSize);
    }

    /**
     * 对内建设 = 4
     */
    @Test
    public void zoomBenefitInternalConstructionTest() {
        JSONObject targetVO = groupVisualizationService.zoomBenefitInternalConstruction();
        int exampleTableSize = targetVO.getObject("provinceAndTableMap", new TypeReference<Map<String, List>>(){}).values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(4, exampleTableSize);
    }

    /**
     * 对外赋能 = 2 条
     */
    @Test
    public void zoomBenefitExternalEmpowermentTest(){
        JSONObject targetVO = groupVisualizationService.zoomBenefitExternalEmpowerment();
        int exampleTableSize = targetVO.getObject("provinceAndTableMap", new TypeReference<Map<String, List>>(){}).values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(2, exampleTableSize);
    }

    /**
     * 效率评估总览 = 12 条
     */
    @Test
    public void zoomEfficiencyOverviewTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO = groupVisualizationService.zoomEfficiencyOverview();
        int exampleTableSize =targetVO.getProvinceAndTableMap().values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(12, exampleTableSize);
    }

    /**
     * 工程进度 = 11 条
     */
    @Test
    public void zoomEfficiencyProgressScheduleTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = groupVisualizationService.zoomEfficiencyProgressSchedule();
        int exampleTableSize =targetVO.getProvinceAndTableMap().values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(11, exampleTableSize);
    }

    /**
     * 投资进度 = 4 条
     */
    @Test
    public void zoomEfficiencyInvestmentScheduleTest(){
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = groupVisualizationService.zoomEfficiencyInvestmentSchedule();
        int exampleTableSize =targetVO.getProvinceAndTableMap().values().stream()
            .findFirst()
            .map(List::size)
            .orElse(0);
        assertEquals(4, exampleTableSize);
    }
}
