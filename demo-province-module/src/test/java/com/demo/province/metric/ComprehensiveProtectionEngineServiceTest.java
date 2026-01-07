package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSON;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.ledger.Excel.ExcelMergeReaderAdvanced;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.service.ComprehensiveProtectionService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sama.api.ledger.bean.enums.MetricTypeEnum.COMPREHENSIVE_PROTECTION;

/**
 * 3 综合防护引擎
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 14:07
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ComprehensiveProtectionEngineServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ComprehensiveProtectionEngineServiceTest.class);

    @Resource
    ComprehensiveProtectionService comprehensiveProtectionService;

    @Resource
    ComprehensiveProtectionEngineServiceImpl comprehensiveProtectionEngineService;

    /**
     * 1 数据库的增删改查（修改后必测！！！）
     */
    @Test
    public void rawDBTests(){
        // 增
        ComprehensiveProtectionExtendedDO init = new ComprehensiveProtectionExtendedDO();
        init.setComprehensiveScenarioType("APT攻击防护场景（有更新，见评估指标初稿及意见）");
        init.setEvaluationItem("攻击链检测率");
        init.setProcessingDataCn("攻击链路被检测的数量");
        init.setProcessingDataUnit("个");
        init.setProcessingData(50);
        // init.setCalculationMethod("");
        // init.setAssessedValue(new BigDecimal("0"));
        init.setOrgCode("mock");

        comprehensiveProtectionService.add(init);
        logger.info("【Test】 comprehensive_protection 表新增结果：{}", JSON.toJSONString(init));

        // 查
        init.setCreateTime(null);
        init.setUpdateTime(null);
        List<ComprehensiveProtectionExtendedDO> selectRes = comprehensiveProtectionService.queryList(init);
        logger.info("【Test】 comprehensive_protection 表查询结果：{}", JSON.toJSONString(selectRes));

        // 模糊查
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setEvaluationItem("率");
        List<ComprehensiveProtectionExtendedDO> queryRes = comprehensiveProtectionService.searchAndList(queryDO);
        logger.info("【Test】 comprehensive_protection 表模糊查询结果：{}", JSON.toJSONString(queryRes));
    }

    /**
     * 2 从本地导入、提交至数据库
     */
    @Test
    public void importAndSubmitTest(){
        List<ComprehensiveProtectionExtendedDO> excelList = new ArrayList<>();
        try{
            File file = new File("src/main/resources/综合防护 submit.xlsx");
            excelList = ExcelMergeReaderAdvanced.readExcelWithMergeHandling
                    (file, ComprehensiveProtectionExtendedDO.class, 0, Arrays.asList("processingData"));
            logger.info("【Test】 模拟 import 接口成功，共 {} 行！\n{}", excelList.size(), JSON.toJSONString(excelList));
        } catch (Exception e){
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
            BenefitEngineServiceTest.excelImportErrorLocation(e);
        }

        if (!excelList.isEmpty()) {
            comprehensiveProtectionService.submit("mock", excelList, 1L);
            logger.info("【Test】 模拟 submit 接口成功！");
        }
    }

    /**
     * 3 基于数据库数据的 etl
     */
    @Test
    public void etlIntoPreparedDataTest(){
        ComprehensiveProtectionPreparedDataBO initialData = new ComprehensiveProtectionPreparedDataBO();
        initialData.setOrgCode("mock");
        initialData.setMetricType(COMPREHENSIVE_PROTECTION.getType());
        comprehensiveProtectionEngineService.etlIntoPreparedData(initialData);
    }

    /**
     * 4 基于数据库数据的试算
     */
    @Test
    public void topProcessTest(){
        comprehensiveProtectionEngineService.topProcess(MetricConstants.UpdateMode.FORCE);
    }

    /**
     * 5 即时计分（保证线程安全）
     */
    @Test
    public void asyncInstantScoreTest() throws InterruptedException {
        String orgCode = "02250011";
        new Thread(() -> comprehensiveProtectionEngineService.asyncInstantScore(orgCode)).start();
        new Thread(() -> comprehensiveProtectionEngineService.asyncInstantScore(orgCode)).start();
        Thread.sleep(10_000);
    }

    /**
     * 基于 PreparedDataBO #customData() 的试算
     */
    @Test
    public void carryingDataTest(){
        ComprehensiveProtectionPreparedDataBO preparedData = ComprehensiveProtectionCalculatorTest.customData();
        preparedData.setMetricType(COMPREHENSIVE_PROTECTION.getType());
        comprehensiveProtectionEngineService.computeAndSaveResult(preparedData);
    }

}
