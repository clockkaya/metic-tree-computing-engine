package com.sama.analytic.metric;

import com.api.analytic.service.GroupVisualizationDubboService;
import com.sama.analytic.AnalyticApplication;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 集团侧可视化展示
 * @author: huxh
 * @description:
 * @datetime: 2025/10/21 15:57
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class GroupVisualizationDubboServiceTest {

    private static final Logger logger = LogManager.getLogger(GroupVisualizationDubboServiceTest.class);

    @DubboReference
    GroupVisualizationDubboService groupVisualizationDubboService;

    /**
     * 全网安全能力成效评估，从表 group_statistic 直接解析
     */
    @Test
    public void allZoomDirectTest(){
        groupVisualizationDubboService.zoomComprehensiveProtection();
        groupVisualizationDubboService.zoomBenefitOverview();
        groupVisualizationDubboService.zoomBenefitInternalConstruction();
        groupVisualizationDubboService.zoomBenefitExternalEmpowerment();
        groupVisualizationDubboService.zoomEfficiencyOverview();
        groupVisualizationDubboService.zoomEfficiencyProgressSchedule();
        groupVisualizationDubboService.zoomEfficiencyInvestmentSchedule();
    }
}
