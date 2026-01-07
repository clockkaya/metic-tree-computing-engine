package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSONObject;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sama.api.ledger.bean.dto.*;
import com.sama.api.ledger.bean.indicator.MetricBenefitProvinceCategoryEnum;
import com.sama.api.ledger.bean.vo.VisualizedProvinceBaseVO;
import com.sama.api.ledger.service.ProvinceVisualizationDubboService;
import com.sama.ledger.SamaLedgerApplication;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 省侧展示
 * @author: huxh
 * @description:
 * @datetime: 2025/8/18 11:04
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ProvinceVisualizationDubboServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceVisualizationDubboServiceTest.class);

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    /**
     * 需本地先 build module 才可调用
     */
    @DubboReference
    ProvinceVisualizationDubboService provinceVisualizationDubboService;

    private String ORG_CODE = "02260062";

    @Test
    public void caffeineAndEnumTest() {
        String orgName = orgCodeAndNameCache.get("02260011");
        MetricBenefitProvinceCategoryEnum provinceCategoryEnum = MetricBenefitProvinceCategoryEnum.tellEnumByAmbiguousOrgCn(orgName);
        logger.info("根据 orgName({}) 找得 provinceCategoryEnum: {}", orgName, provinceCategoryEnum);
        assertEquals(provinceCategoryEnum, MetricBenefitProvinceCategoryEnum.BEIJING);
    }

    /**
     * XX省安全能力成效评估
     */
    @Test
    public void allDisplayTest(){
        displayComprehensiveProtectionTest();
        displayBenefitOverviewTest();
        displayVisualizedBenefitInternalConstructionTest();
        displayBenefitExternalEmpowermentTest();
        displayEfficiencyOverviewTest();
        displayEfficiencyProgressSchedule();
        displayEfficiencyInvestmentSchedule();
    }

    /**
     * 综合防护 = 10 条
     */
    @Test
    public void displayComprehensiveProtectionTest() {
        VisualizedProvinceBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = provinceVisualizationDubboService.displayComprehensiveProtection(ORG_CODE);
        assertEquals(10, targetVO.getTableItems().size());
    }

    /**
     * 效益评估总览 = 5 条
     */
    @Test
    public void displayBenefitOverviewTest(){
        JSONObject targetVO = provinceVisualizationDubboService.displayBenefitOverview(ORG_CODE);
        assertEquals(5, targetVO.getJSONArray("tableItems").size());
    }

    /**
     * 对内建设 = 4、85 条
     */
    @Test
    public void displayVisualizedBenefitInternalConstructionTest(){
        JSONObject targetVO = provinceVisualizationDubboService.displayBenefitInternalConstruction(ORG_CODE);
        assertEquals(4, targetVO.getJSONArray("frontTable").size());
        assertEquals(85,  targetVO.getJSONArray("backTable").size());
    }

    /**
     * 对外赋能 = 2 条
     */
    @Test
    public void displayBenefitExternalEmpowermentTest(){
        VisualizedProvinceBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> targetVO = provinceVisualizationDubboService.displayBenefitExternalEmpowerment(ORG_CODE);
        assertEquals(2, targetVO.getTableItems().size());
    }

    /**
     * 效率评估总览 = 12 条
     */
    @Test
    public void displayEfficiencyOverviewTest(){
        VisualizedProvinceBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO = provinceVisualizationDubboService.displayEfficiencyOverview(ORG_CODE);
        assertEquals(12, targetVO.getTableItems().size());
    }

    /**
     * 工程进度 = 11 条
     */
    @Test
    public void displayEfficiencyProgressSchedule(){
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = provinceVisualizationDubboService.displayEfficiencyProgressSchedule(ORG_CODE);
        assertEquals(11, targetVO.getTableItems().size());
    }

    /**
     * 投资进度 = 4 条
     */
    @Test
    public void displayEfficiencyInvestmentSchedule(){
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = provinceVisualizationDubboService.displayEfficiencyInvestmentSchedule(ORG_CODE);
        assertEquals(4, targetVO.getTableItems().size());
    }

    /**
     * 特殊：效果评估总览——综合防护分数统计
     */
    @Test
    public void offerVisualizedComprehensiveProtectionChartTest(){
        LineChartDTO targetVO = provinceVisualizationDubboService.offerVisualizedComprehensiveProtectionChart(ORG_CODE);
    }

    /**
     * 全网安全能力成效评估，从表 group_statistic_bak 间接解析
     */
    @Test
    public void allZoomIndirectTest(){
        provinceVisualizationDubboService.zoomComprehensiveProtection();
        provinceVisualizationDubboService.zoomBenefitOverview();
        provinceVisualizationDubboService.zoomBenefitInternalConstruction();
        provinceVisualizationDubboService.zoomBenefitExternalEmpowerment();
        provinceVisualizationDubboService.zoomEfficiencyOverview();
        provinceVisualizationDubboService.zoomEfficiencyProgressSchedule();
        provinceVisualizationDubboService.zoomEfficiencyInvestmentSchedule();
    }

}
