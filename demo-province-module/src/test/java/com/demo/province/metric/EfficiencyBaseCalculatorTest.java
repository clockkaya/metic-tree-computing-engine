package com.sama.ledger.metric;

import com.alibaba.nacos.shaded.com.google.common.collect.ImmutableList;
import com.core4ct.utils.DateUtils;
import com.google.common.collect.ImmutableMap;
import com.sama.api.ledger.bean.bo.EfficiencyManualBO;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.bo.EfficiencyUnifiedBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.calculators.*;
import com.sama.ledger.utils.MetricMockDataUtils;
import com.sama.ledger.utils.NumberUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * 1 效率算子
 * @author: huxh
 * @description:
 * @datetime: 2025/6/17 11:07
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class EfficiencyBaseCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(EfficiencyBaseCalculatorTest.class);

    @Resource
    MetricMockDataUtils metricMockDataUtils;

    @Resource
    ProjectApprovalCompletionCalculator projectApprovalCompletionCalculator;

    @Resource
    ProjectDesignApprovalThisYearCalculator projectDesignApprovalThisYearCalculator;

    @Resource
    ProjectDeliveryThisYearCalculator projectDeliveryThisYearCalculator;

    @Resource
    ProjectDeliveryLastYearCalculator projectDeliveryLastYearCalculator;

    @Resource
    ProjectCloseLastYearCalculator projectCloseLastYearCalculator;

    @Resource
    ProjectNewBookCompletionCalculator projectNewBookCompletionCalculator;

    @Resource
    ProjectWholeBookCompletionCalculator projectWholeBookCompletionCalculator;

    @Resource
    ProjectLateBookCalculator projectLateBookCalculator;

    @Resource
    ProjectLatePreTransferCalculator projectLatePreTransferCalculator;

    @Resource
    ProjectLateCloseCalculator projectLateCloseCalculator;

    @Resource
    ProjectLongTermDebtCalculator projectLongTermDebtCalculator;

    /**
     * 仅基于 Aviator 的计算测试
     */
    @Test
    public void allAviatorTests() {
        projectApprovalCompletionAviatorTest();
        projectDesignApprovalAviatorTest();
        projectDeliveryThisYearAviatorTest();
        projectDeliveryLastYearAviatorTest();
        projectCloseLastYearAviatorTest();
        projectNewBookCompletionAviatorTest();
        projectWholeBookCompletionAviatorTest();
        projectDeductionCalculatorAviatorTest();
    }

    private void unitAssertEqualsWithSingle(BaseCalculator<?> baseCalculator, String assessedValue, String expected){
        BigDecimal expectedB = new BigDecimal(expected);
        BigDecimal varB = new BigDecimal(assessedValue);
        Map<String, Object> mockAssessedValueMap = ImmutableMap.of("input", varB);
        MetricResultNode actualB = metricMockDataUtils.simulateSimpleAviatorCalculate(baseCalculator, MetricTypeEnum.EFFICIENCY.getType(), mockAssessedValueMap);
        assertEquals(NumberUtils.formatFlexibleConditions(expectedB, 2), NumberUtils.formatFlexibleConditions(actualB.getAssessedScore(), 2));
    }

    private void unitAssertEqualsWithMap(BaseCalculator<?> baseCalculator, Map<String, Object> mockAssessedValueMap, String expected){
        BigDecimal expectedB = new BigDecimal(expected);
        MetricResultNode actualB = metricMockDataUtils.simulateSimpleAviatorCalculate(baseCalculator, MetricTypeEnum.EFFICIENCY.getType(), mockAssessedValueMap);
        assertEquals(NumberUtils.formatFlexibleConditions(expectedB, 2), NumberUtils.formatFlexibleConditions(actualB.getAssessedScore(), 2));
    }

    @Test
    public void projectApprovalCompletionAviatorTest() {
        unitAssertEqualsWithSingle(projectApprovalCompletionCalculator, "0.7742", "77.42");
        unitAssertEqualsWithSingle(projectApprovalCompletionCalculator, "0.0001", "0.01");
        unitAssertEqualsWithSingle(projectApprovalCompletionCalculator, "1.5", "100");
    }

    @Test
    public void projectDesignApprovalAviatorTest() {
        unitAssertEqualsWithSingle(projectDesignApprovalThisYearCalculator, "1.12", "100");
        unitAssertEqualsWithSingle(projectDesignApprovalThisYearCalculator, "0.5", "50");
        unitAssertEqualsWithSingle(projectDesignApprovalThisYearCalculator, "0.00001", "0.0010");
    }

    @Test
    public void projectDeliveryThisYearAviatorTest() {
        unitAssertEqualsWithSingle(projectDeliveryThisYearCalculator, "0.4", "6");
    }

    @Test
    public void projectDeliveryLastYearAviatorTest() {
        unitAssertEqualsWithSingle(projectDeliveryLastYearCalculator, "0.931034482758621", "93.1034");
        unitAssertEqualsWithSingle(projectDeliveryLastYearCalculator, "0.001", "0.10000");
        unitAssertEqualsWithSingle(projectDeliveryLastYearCalculator, "-5", "0");
    }

    @Test
    public void projectCloseLastYearAviatorTest() {
        unitAssertEqualsWithSingle(projectCloseLastYearCalculator, "0.931034482758621", "93.1034");
        unitAssertEqualsWithSingle(projectCloseLastYearCalculator, "0.001", "0.10000");
        unitAssertEqualsWithSingle(projectCloseLastYearCalculator, "-5", "0");
    }

    @Test
    public void projectNewBookCompletionAviatorTest(){
        unitAssertEqualsWithSingle(projectNewBookCompletionCalculator, "0.790207772120504", "100");
        unitAssertEqualsWithSingle(projectNewBookCompletionCalculator, "0.055", "7.8571");
        unitAssertEqualsWithSingle(projectNewBookCompletionCalculator, "-2", "0");
    }

    @Test
    public void projectWholeBookCompletionAviatorTest(){
        unitAssertEqualsWithSingle(projectWholeBookCompletionCalculator, "0.743806425323381", "100");
        unitAssertEqualsWithSingle(projectWholeBookCompletionCalculator, "0.02", "2.8571");
        unitAssertEqualsWithSingle(projectWholeBookCompletionCalculator, "-2", "0");
    }

    @Test
    public void projectDeductionCalculatorAviatorTest() {
        unitAssertEqualsWithSingle(projectLateBookCalculator, "100", "-50");
        unitAssertEqualsWithSingle(projectLateBookCalculator, "0", "0");
    }

    /**
     * 基于 PreparedDataModel 的主流程处理
     */
    @Test
    public void allCalculatorTests() {
        projectApprovalCompletionCalculatorTest();
        projectDesignApprovalThisYearCalculatorTest();
        projectDeliveryThisYearCalculatorTest();
        projectDeliveryLastYearCalculatorTest();
        projectCloseLastYearCalculatorTest();
        projectNewBookCompletionCalculatorTest();
        projectWholeBookCompletionCalculatorTest();
        projectDeductionCalculatorTest();
    }

    private void unitAssertEqualsUsingPreparedData(BaseCalculator<EfficiencyPreparedDataBO> baseCalculator,
                                                   List<EfficiencyUnifiedBO> inProgressData,
                                                   EfficiencyManualBO manual,
                                                   String expectedValue,
                                                   String expectedScore){
        EfficiencyPreparedDataBO customData = new EfficiencyPreparedDataBO();
        customData.setMetricType(MetricTypeEnum.EFFICIENCY.getType());
        customData.setInProgressData(inProgressData);
        customData.setManual(manual);

        // 计算
        MetricResultNode resultNode = baseCalculator.processAccordingToProcedure(customData);

        // 判断
        if (expectedValue == null || expectedScore == null) {
            assertNull(resultNode.getAssessedScore());
            return;
        }

        // b.评估值
        BigDecimal expectedAssessedValue = new BigDecimal(expectedValue);
        BigDecimal actualAssessedValue = NumberUtils.safeConvertToBigDecimal(resultNode.extractAssessedValue());
        assertEquals(NumberUtils.formatFlexibleConditions(expectedAssessedValue, 4), NumberUtils.formatFlexibleConditions(actualAssessedValue, 4));


        // c.评估得分
        BigDecimal expectedAssessedScore = new BigDecimal(expectedScore);
        BigDecimal actualAssessedScore = resultNode.getAssessedScore();
        assertEquals(NumberUtils.formatFlexibleConditions(expectedAssessedScore, 2), NumberUtils.formatFlexibleConditions(actualAssessedScore, 2));
    }

    public static List<EfficiencyUnifiedBO> generate1111(){
        // * 截止三季度已立项数量 = 2
        // * 今年安全项目总数 = 3
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowA.setProjectApprovalDate(DateUtils.stringToDate("2025-02-01"));
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowB.setProjectApprovalDate(DateUtils.stringToDate("2025-08-01"));
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowC.setProjectApprovalDate(DateUtils.stringToDate("2025-10-01"));

        return ImmutableList.of(rowA, rowB, rowC);
    }

    @Test
    public void projectApprovalCompletionCalculatorTest() {
        List<EfficiencyUnifiedBO> inProgressData = generate1111();

        // * 修正项（四季度立项属于特殊情况）= 0
        EfficiencyManualBO manual = new EfficiencyManualBO();
        manual.setFixedItem(0);

        unitAssertEqualsUsingPreparedData(projectApprovalCompletionCalculator, inProgressData, manual, "0.6667", "66.6667");

        // * 今年安全项目总数 = 2
        unitAssertEqualsUsingPreparedData(projectApprovalCompletionCalculator, inProgressData.subList(0,2), manual, "1.0000", "100");

        // * 修正项（四季度立项属于特殊情况）= 1
        manual.setFixedItem(1);
        unitAssertEqualsUsingPreparedData(projectApprovalCompletionCalculator, inProgressData, manual, "1.0000", "100");
    }

    public static List<EfficiencyUnifiedBO> generate1112(){
        // * 设计批复完成的项目数量 = 2
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowA.setDesignType("一次设计");
        rowA.setDesignApprovalDate(null);
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowB.setDesignType("两次设计");
        rowB.setDesignApprovalDate(null);
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowC.setDesignType("一次设计");
        rowC.setDesignApprovalDate(new Date());
        EfficiencyUnifiedBO rowD = new EfficiencyUnifiedBO();
        rowD.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowD.setDesignType("无设计");
        rowD.setProjectApprovalDate(null);
        EfficiencyUnifiedBO rowE = new EfficiencyUnifiedBO();
        rowE.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowE.setDesignType("无设计");
        rowE.setProjectApprovalDate(new Date());
        EfficiencyUnifiedBO rowF = new EfficiencyUnifiedBO();
        rowF.setProjectYear(DateUtils.stringToDate("2025-07-01"));
        rowF.setDesignType("else");
        rowF.setDesignApprovalDate(new Date());
        rowF.setProjectApprovalDate(new Date());

        // * 今年安全项目总数 = 6

        return ImmutableList.of(rowA, rowB, rowC, rowD, rowE, rowF);
    }

    @Test
    public void projectDesignApprovalThisYearCalculatorTest() {
        List<EfficiencyUnifiedBO> inProgressData = generate1112();

        unitAssertEqualsUsingPreparedData(projectDesignApprovalThisYearCalculator, inProgressData, null, "0.3333", "33.3333");

        // 0/0
        unitAssertEqualsUsingPreparedData(projectDesignApprovalThisYearCalculator, inProgressData.subList(0,0), null, null, null);
    }

    public static List<EfficiencyUnifiedBO> generate1113(){
        // * 今年立项且初验或终验完成的项目数量 = 2
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowA.setInspectionType("一次验收");
        rowA.setFinalInspectionApprovalDate(null);
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowB.setInspectionType("一次验收");
        rowB.setFinalInspectionApprovalDate(new Date());
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowC.setInspectionType("两次验收");
        rowC.setFirstInspectionApprovalDate(null);
        EfficiencyUnifiedBO rowD = new EfficiencyUnifiedBO();
        rowD.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowD.setInspectionType("两次验收");
        rowD.setFirstInspectionApprovalDate(new Date());
        EfficiencyUnifiedBO rowE = new EfficiencyUnifiedBO();
        rowE.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowE.setInspectionType("else");
        rowE.setFinalInspectionApprovalDate(null);
        rowE.setFirstInspectionApprovalDate(null);

        // * 今年安全项目总数 = 5

        return ImmutableList.of(rowA, rowB, rowC, rowD, rowE);
    }

    @Test
    public void projectDeliveryThisYearCalculatorTest() {
        List<EfficiencyUnifiedBO> inProgressData = generate1113();

        unitAssertEqualsUsingPreparedData(projectDeliveryThisYearCalculator, inProgressData, null, "0.4000",  "6");

        // 0/0
        unitAssertEqualsUsingPreparedData(projectDeliveryThisYearCalculator, inProgressData.subList(0,0), null, null,  null);

        // 0/1
        unitAssertEqualsUsingPreparedData(projectDeliveryThisYearCalculator, inProgressData.subList(4,5), null, "0",  "0");
    }

    public static List<EfficiencyUnifiedBO> generate1121(){
        // * 去年立项项目交付的项目数量 = 2
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowA.setFirstInspectionApprovalDate(DateUtils.stringToDate("2024-06-01"));
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowB.setFinalInspectionApprovalDate(DateUtils.stringToDate("2024-06-01"));
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowC.setFirstInspectionApprovalDate(null);
        rowC.setFinalInspectionApprovalDate(null);
        EfficiencyUnifiedBO rowD = new EfficiencyUnifiedBO();
        rowD.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowD.setFirstInspectionApprovalDate(DateUtils.stringToDate("2026-06-01"));
        EfficiencyUnifiedBO rowE = new EfficiencyUnifiedBO();
        rowE.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowE.setFinalInspectionApprovalDate(DateUtils.stringToDate("2026-06-01"));

        // * 去年项目总数 = 5

        return ImmutableList.of(rowA, rowB, rowC, rowD, rowE);
    }

    @Test
    public void projectDeliveryLastYearCalculatorTest() {
        List<EfficiencyUnifiedBO> inProgressData = generate1121();

        unitAssertEqualsUsingPreparedData(projectDeliveryLastYearCalculator, inProgressData, null, "0.4000", "40");

        // 2/2
        unitAssertEqualsUsingPreparedData(projectDeliveryLastYearCalculator, inProgressData.subList(0, 2), null, "1.0000", "100.0000");

        // 0/0
        unitAssertEqualsUsingPreparedData(projectDeliveryLastYearCalculator, inProgressData.subList(0, 0), null, null, null);
    }

    public static List<EfficiencyUnifiedBO> generate1122(){
        // * 去年项目在标准工期内关闭的项目数量 = 2
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowA.setProjectCloseDate(DateUtils.stringToDate("2024-06-01"));
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowB.setProjectCloseDate(DateUtils.stringToDate("2024-06-01"));
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowC.setProjectCloseDate(null);
        EfficiencyUnifiedBO rowD = new EfficiencyUnifiedBO();
        rowD.setProjectYear(DateUtils.stringToDate("2024-01-01"));
        rowD.setProjectCloseDate(DateUtils.stringToDate("2026-06-01"));

        // * 去年项目总数 = 4

        return ImmutableList.of(rowA, rowB, rowC, rowD);
    }

    @Test
    public void projectCloseLastYearCalculatorTest() {
        List<EfficiencyUnifiedBO> inProgressData = generate1122();

        unitAssertEqualsUsingPreparedData(projectCloseLastYearCalculator, inProgressData, null, "0.5000", "50.0000");

        // 2/2
        unitAssertEqualsUsingPreparedData(projectCloseLastYearCalculator,inProgressData.subList(0, 2), null, "1.0000", "100.0000");
    }

    public static EfficiencyPreparedDataBO generate1211(){
        // * 截至四季度新立项项目支出金额（元）= 1186392.9328773
        // * 二季度新立项项目支出金额（元）= 454804.9989
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowA.setYearlyCapitalExpenditure(445648.5489);
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowB.setYearlyCapitalExpenditure(9156.45);

        // * 三季度新立项项目支出金额（元）= 721500.5881003
        EfficiencyUnifiedBO rowC = new EfficiencyUnifiedBO();
        rowC.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowC.setYearlyCapitalExpenditure(721500.5881003);

        // * 四季度新立项项目支出金额（元）= 10087.345877
        EfficiencyUnifiedBO rowD = new EfficiencyUnifiedBO();
        rowD.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowD.setYearlyCapitalExpenditure(10087.345877);

        // * 今年立项总投资（元） = 2083754.0656
        EfficiencyUnifiedBO rowE = new EfficiencyUnifiedBO();
        rowE.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowE.setSingleItemInvestment(58898.0156);
        EfficiencyUnifiedBO rowF = new EfficiencyUnifiedBO();
        rowF.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowF.setSingleItemInvestment(2399.5);
        EfficiencyUnifiedBO rowG = new EfficiencyUnifiedBO();
        rowG.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowG.setSingleItemInvestment(22456.55);
        EfficiencyUnifiedBO rowH = new EfficiencyUnifiedBO();
        rowH.setProjectYear(DateUtils.stringToDate("2025-01-01"));
        rowH.setSingleItemInvestment(2000000.0);

        EfficiencyPreparedDataBO preparedDataBO = new EfficiencyPreparedDataBO();
        preparedDataBO.setInProgressData(ImmutableList.of(rowA, rowB, rowC, rowD, rowE, rowF, rowG, rowH));

        return preparedDataBO;
    }

    @Test
    public void projectNewBookCompletionCalculatorTest(){
        EfficiencyPreparedDataBO preparedDataBO = generate1211();

        unitAssertEqualsUsingPreparedData(projectNewBookCompletionCalculator, preparedDataBO.getInProgressData(), null, "0.5694", "81.3362");

        // 0/0
        unitAssertEqualsUsingPreparedData(projectNewBookCompletionCalculator, preparedDataBO.getInProgressData().subList(0,0), null, null, null);
    }

    public static EfficiencyPreparedDataBO generate1221(){
        // * （四季度结转+新建）支出金额（元） = 1598288.9999
        EfficiencyUnifiedBO rowA = new EfficiencyUnifiedBO();
        rowA.setYearlyCapitalExpenditure(1598189.0);
        EfficiencyUnifiedBO rowB = new EfficiencyUnifiedBO();
        rowB.setYearlyCapitalExpenditure(99.9999);

        EfficiencyPreparedDataBO preparedDataBO = new EfficiencyPreparedDataBO();
        preparedDataBO.setInProgressData(ImmutableList.of(rowA, rowB));

        return preparedDataBO;
    }

    @Test
    public void projectWholeBookCompletionCalculatorTest() {
        EfficiencyPreparedDataBO preparedDataBO = generate1221();

        // * 今年投资总额（元） = 54568
        EfficiencyManualBO manual = new EfficiencyManualBO();
        manual.setThisYearTotalInvestment(54568.0);

        // 1598288.9999/54568 = 29.2898585233
        unitAssertEqualsUsingPreparedData(projectWholeBookCompletionCalculator, preparedDataBO.getInProgressData(), manual,"29.2899", "100");

        // 1598288.9999/10000000 = 0.1598289000
        manual.setThisYearTotalInvestment(10000000.0);
        unitAssertEqualsUsingPreparedData(projectWholeBookCompletionCalculator, preparedDataBO.getInProgressData(), manual,"0.1598", "22.8327");
    }

    @Test
    public void projectDeductionCalculatorTest() {
        EfficiencyManualBO manual = new EfficiencyManualBO();

        // * 列账不及时的项目数量 = 10
        manual.setLateBookProjectNum(10);
        unitAssertEqualsUsingPreparedData(projectLateBookCalculator, null, manual, "10", "-5");

        // * 关闭不及时的项目数量 = 20
        manual.setLateCloseProjectNum(20);
        unitAssertEqualsUsingPreparedData(projectLateCloseCalculator, null, manual, "20", "-10");

        // * 预转固不及时的项目数量 = 30
        manual.setLatePreTransferProjectNum(30);
        unitAssertEqualsUsingPreparedData(projectLatePreTransferCalculator, null, manual, "30", "-15");

        // * 长期挂账项目数量 = null
        manual.setLongTermDebtProjectNum(null);
        unitAssertEqualsUsingPreparedData(projectLongTermDebtCalculator, null, manual, "0", "0");
    }

    public static EfficiencyPreparedDataBO customData(){
        List<EfficiencyUnifiedBO> inProgress = new ArrayList<>();
        inProgress.addAll(generate1111());
        inProgress.addAll(generate1112());
        inProgress.addAll(generate1113());
        inProgress.addAll(generate1121());
        inProgress.addAll(generate1122());
        EfficiencyPreparedDataBO newProjectBookCompletionData = generate1211();
        inProgress.addAll(newProjectBookCompletionData.getInProgressData());
        EfficiencyPreparedDataBO projectBookCompletionData = generate1221();
        inProgress.addAll(projectBookCompletionData.getInProgressData());

        EfficiencyManualBO manual = new EfficiencyManualBO();
        manual.setThisYearOutPlanProjectNum(5);
        manual.setLateBookProjectNum(10);
        // 工程进度为负数
        // manual.setLatePreTransferProjectNum(20);
        manual.setThisYearTotalInvestment(54568.0);

        EfficiencyPreparedDataBO customData = new EfficiencyPreparedDataBO();
        customData.setOrgCode("mock");
        customData.setInProgressData(inProgress);
        customData.setManual(manual);

        return customData;
    }
}
