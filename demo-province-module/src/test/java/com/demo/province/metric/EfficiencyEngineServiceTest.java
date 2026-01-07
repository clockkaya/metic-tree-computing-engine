package com.sama.ledger.metric;

import com.core4ct.utils.DataUtils;
import com.core4ct.utils.DateUtils;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 2 效率引擎
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 11:08
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class EfficiencyEngineServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EfficiencyEngineServiceTest.class);

    @Resource
    EfficiencyEngineServiceImpl efficiencyEngineService;

    /**
     * 3 基于数据库数据的 etl
     */
    @Test
    public void etlIntoPreparedDataTest(){
        EfficiencyPreparedDataBO initialData = new EfficiencyPreparedDataBO();
        initialData.setOrgCode("02260062");
        efficiencyEngineService.etlIntoPreparedData(initialData);
    }

    /**
     * 4 基于数据库数据的试算
     */
    @Test
    public void topProcessTest() throws InterruptedException {
        efficiencyEngineService.topProcess(MetricConstants.UpdateMode.FORCE);
        Thread.sleep(10_000);
    }

    /**
     * 5 即时计分（保证线程安全）
     */
    @Test
    public void asyncInstantScoreTest() throws InterruptedException {
        String orgCode = "02260062";
        new Thread(() -> efficiencyEngineService.asyncInstantScore(orgCode)).start();
        new Thread(() -> efficiencyEngineService.asyncInstantScore(orgCode)).start();
        Thread.sleep(10_000);
    }

    /**
     * 基于 PreparedDataBO #customData() 的试算
     */
    @Test
    public void carryingDataTest(){
        EfficiencyPreparedDataBO preparedData = EfficiencyBaseCalculatorTest.customData();
        preparedData.setMetricType(MetricTypeEnum.EFFICIENCY.getType());
        preparedData.getInProgressData().forEach(item -> {
            if (DataUtils.isEmpty(item.getProjectYear())){
                item.setProjectYear(DateUtils.stringToDate("2024-01-01"));
            }
        });
        efficiencyEngineService.validPreparedData(preparedData);
        // hash 相同
        // preparedData.setDataRefTime(DateUtils.stringToDate("2025-07-08 16:49:18"));
        efficiencyEngineService.computeAndSaveResult(preparedData);
    }

}
