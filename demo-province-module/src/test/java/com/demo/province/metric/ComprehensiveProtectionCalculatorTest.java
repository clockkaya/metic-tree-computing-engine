package com.sama.ledger.metric;

import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.calculators.*;
import com.sama.ledger.utils.NumberUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.sama.api.ledger.bean.enums.MetricTypeEnum.COMPREHENSIVE_PROTECTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1 综合防护算子
 * @author: huxh
 * @description:
 * @datetime: 2025/7/23 17:42
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ComprehensiveProtectionCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(ComprehensiveProtectionCalculatorTest.class);

    @Resource
    RateAttackChainAptCalculator rateAttackChainAptCalculator;

    @Resource
    RateAttackPathAptCalculator rateAttackPathAptCalculator;

    @Resource
    RateAttackAverageDetectionAptCalculator rateAttackAverageDetectionAptCalculator;

    @Resource
    RateAverageDetectionCalculator rateAverageDetectionCalculator;

    @Resource
    RateAverageBlockCalculator rateAverageBlockCalculator;

    @Resource
    RateAttackChainHwCalculator rateAttackChainHwCalculator;

    @Resource
    RateAttackPathHwCalculator rateAttackPathHwCalculator;

    @Resource
    RateAttackAverageDetectionHwCalculator rateAttackAverageDetectionHwCalculator;

    @Resource
    RateProtectionStrategyEfficiencyCalculator rateProtectionStrategyEfficiencyCalculator;

    @Resource
    RateAssetProtectionCoverageCalculator rateAssetProtectionCoverageCalculator;

    //==============================================================================
    // 基于 PreparedDataModel 的主流程处理
    //==============================================================================

    @Test
    public void allCalculatorProcedureTests() {
        attackChainAptTest();
        attackPathAptTest();
        attackAverageDetectionAptTest();
        averageDetectionTest();
        averageBlockTest();
        attackChainHwTest();
        attackPathHwTest();
        attackAverageDetectionHwTest();
        protectionStrategyEfficiencyTest();
        assetProtectionCoverageTest();
    }

    private void unitAssertEqualsUsingPreparedData(BaseCalculator baseCalculator,
                                                   ComprehensiveProtectionPreparedDataBO customData,
                                                   String expected) {
        customData.setMetricType(COMPREHENSIVE_PROTECTION.getType());

        MetricResultNode resultNode = baseCalculator.processAccordingToProcedure(customData);
        if (resultNode.extractAssessedValue() == null) {
            logger.info("【Test】 null值直接返回！");
            return;
        }
        BigDecimal expectedB = new BigDecimal(expected);
        BigDecimal assessedScore = resultNode.getAssessedScore();
        assertEquals(NumberUtils.formatFlexibleConditions(expectedB, 2), NumberUtils.formatFlexibleConditions(assessedScore, 2));
    }

    public static void setAttackChainApt(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路被检测的数量
        initData.setAttackChainAptFirst(50);
        // * 攻击链路总数量
        initData.setAttackChainAptSecond(100);
    }

    @Test
    public void attackChainAptTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackChainApt(initData);
        unitAssertEqualsUsingPreparedData(rateAttackChainAptCalculator, initData, "50");

        initData.setAttackChainAptSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackChainAptCalculator, initData, "null");

        initData.setAttackChainAptFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackChainAptCalculator, initData, "null");
    }

    public static void setAttackPathApt(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路被检测的数量
        initData.setAttackPathAptFirst(18);
        // * 攻击链路总数量
        initData.setAttackPathAptSecond(39);
    }

    @Test
    public void attackPathAptTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackPathApt(initData);
        unitAssertEqualsUsingPreparedData(rateAttackPathAptCalculator, initData, "46.1538462");

        initData.setAttackPathAptSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackPathAptCalculator, initData, "null");

        initData.setAttackPathAptFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackPathAptCalculator, initData, "null");
    }

    public static void setAttackAverageDetectionApt(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路上被检测到的攻击用例数量
        initData.setAttackAverageDetectionAptFirst(7);
        // * 攻击链路包含的攻击用例总数
        initData.setAttackAverageDetectionAptSecond(80);
    }

    @Test
    public void attackAverageDetectionAptTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackAverageDetectionApt(initData);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionAptCalculator, initData, "8.75");

        initData.setAttackAverageDetectionAptSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionAptCalculator, initData, "null");

        initData.setAttackAverageDetectionAptFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionAptCalculator, initData, "null");
    }

    public static void setAverageDetection(ComprehensiveProtectionPreparedDataBO initData){
        // * 检出用例总数量
        initData.setAverageDetectionFirst(88);
        // * 实施用例总数量
        initData.setAverageDetectionSecond(55);
    }

    @Test
    public void averageDetectionTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAverageDetection(initData);
        unitAssertEqualsUsingPreparedData(rateAverageDetectionCalculator, initData, "160");

        initData.setAverageDetectionSecond(0);
        unitAssertEqualsUsingPreparedData(rateAverageDetectionCalculator, initData, "null");

        initData.setAverageDetectionFirst(null);
        unitAssertEqualsUsingPreparedData(rateAverageDetectionCalculator, initData, "null");
    }

    public static void setAverageBlock(ComprehensiveProtectionPreparedDataBO initData){
        // * 阻断用例总数量
        initData.setAverageBlockFirst(1);
        // * 实施用例总数量
        initData.setAverageBlockSecond(100);
    }

    @Test
    public void averageBlockTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAverageBlock(initData);
        unitAssertEqualsUsingPreparedData(rateAverageBlockCalculator, initData, "1");

        initData.setAverageBlockSecond(0);
        unitAssertEqualsUsingPreparedData(rateAverageBlockCalculator, initData, "null");

        initData.setAverageBlockFirst(null);
        unitAssertEqualsUsingPreparedData(rateAverageBlockCalculator, initData, "null");
    }

    public static void setAttackChainHw(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路被检测的数量
        initData.setAttackChainHwFirst(77);
        // * 攻击链路总数量
        initData.setAttackChainHwSecond(15);
    }

    @Test
    public void attackChainHwTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackChainHw(initData);
        unitAssertEqualsUsingPreparedData(rateAttackChainHwCalculator, initData, "513.33333333");

        initData.setAttackChainHwSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackChainHwCalculator, initData, "null");

        initData.setAttackChainHwFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackChainHwCalculator, initData, "null");
    }

    public static void setAttackPathHw(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路被检测的用例数量
        initData.setAttackPathHwFirst(9999);
        // * 攻击链路用例总数量
        initData.setAttackPathHwSecond(1500);
    }

    @Test
    public void attackPathHwTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackPathHw(initData);
        unitAssertEqualsUsingPreparedData(rateAttackPathHwCalculator, initData, "666.6");

        initData.setAttackPathHwSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackPathHwCalculator, initData, "null");

        initData.setAttackPathHwFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackPathHwCalculator, initData, "null");
    }

    public static void setAttackAverageDetectionHw(ComprehensiveProtectionPreparedDataBO initData){
        // * 攻击链路上被检测到的攻击用例数量
        initData.setAttackAverageDetectionHwFirst(57);
        // * 攻击链路包含的攻击用例总数
        initData.setAttackAverageDetectionHwSecond(222);
    }

    @Test
    public void attackAverageDetectionHwTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAttackAverageDetectionHw(initData);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionHwCalculator, initData, "25.6756757");

        initData.setAttackAverageDetectionHwSecond(0);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionHwCalculator, initData, "null");

        initData.setAttackAverageDetectionHwFirst(null);
        unitAssertEqualsUsingPreparedData(rateAttackAverageDetectionHwCalculator, initData, "null");
    }

    public static void setProtectionStrategyEfficiency(ComprehensiveProtectionPreparedDataBO initData){
        // * 检出用例总数量
        initData.setProtectionStrategyEfficiencyFirst(1000);
        // * 阻断用例总数量
        initData.setProtectionStrategyEfficiencySecond(500);
        // * 实施用例总数量
        initData.setProtectionStrategyEfficiencyThird(100);
    }

    @Test
    public void protectionStrategyEfficiencyTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setProtectionStrategyEfficiency(initData);
        unitAssertEqualsUsingPreparedData(rateProtectionStrategyEfficiencyCalculator, initData, "1500");

        initData.setProtectionStrategyEfficiencySecond(0);
        unitAssertEqualsUsingPreparedData(rateProtectionStrategyEfficiencyCalculator, initData, "1000");

        initData.setProtectionStrategyEfficiencyThird(null);
        unitAssertEqualsUsingPreparedData(rateProtectionStrategyEfficiencyCalculator, initData, "null");
    }

    public static void setAssetProtectionCoverage(ComprehensiveProtectionPreparedDataBO initData){
        // * 有效防护资产数量
        initData.setAssetProtectionCoverageFirst(378);
        // * 测试资产总数量
        initData.setAssetProtectionCoverageSecond(5);
    }

    @Test
    public void assetProtectionCoverageTest() {
        ComprehensiveProtectionPreparedDataBO initData = new ComprehensiveProtectionPreparedDataBO();
        setAssetProtectionCoverage(initData);
        unitAssertEqualsUsingPreparedData(rateAssetProtectionCoverageCalculator, initData, "7560");

        initData.setAssetProtectionCoverageSecond(0);
        unitAssertEqualsUsingPreparedData(rateAssetProtectionCoverageCalculator, initData, "null");

        initData.setAssetProtectionCoverageFirst(null);
        unitAssertEqualsUsingPreparedData(rateAssetProtectionCoverageCalculator, initData, "null");
    }

    public static ComprehensiveProtectionPreparedDataBO customData(){
        ComprehensiveProtectionPreparedDataBO customData = new ComprehensiveProtectionPreparedDataBO();
        customData.setOrgCode("mock");
        customData.setMetricType(COMPREHENSIVE_PROTECTION.getType());

        setAttackChainApt(customData);
        setAttackPathApt(customData);
        setAttackAverageDetectionApt(customData);
        setAverageDetection(customData);
        setAverageBlock(customData);
        setAttackChainHw(customData);
        setAttackPathHw(customData);
        setAttackAverageDetectionHw(customData);
        setProtectionStrategyEfficiency(customData);
        setAssetProtectionCoverage(customData);

        return customData;
    }
}
