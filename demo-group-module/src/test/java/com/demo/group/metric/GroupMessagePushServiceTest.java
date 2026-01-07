package com.sama.analytic.metric;

import com.alibaba.fastjson2.JSON;
import com.sama.analytic.AnalyticApplication;
import com.sama.analytic.metric.support.GroupMessagePushService;
import com.sama.analytic.service.GroupStatisticService;
import com.sama.analytic.utils.GroceryUtils;
import com.sama.api.ledger.bean.GroupStatisticDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.I_BENEFIT;
import static com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionConstants.I_COMPREHENSIVE_PROTECTION;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/15 9:01
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class GroupMessagePushServiceTest {

    private static final Logger logger = LogManager.getLogger(GroupMessagePushServiceTest.class);

    @Resource
    GroupStatisticService groupStatisticService;

    @Resource
    GroupMessagePushService groupMessagePushService;

    /**
     * 1 数据库的增删改查（修改后必测！！！）
     */
    @Test
    public void groupStatisticDBTest() {
        GroupStatisticDO init = new GroupStatisticDO();
        init.setDimensionKey(I_BENEFIT);
        init.setRecord("{}");
        init.setResultUids(Arrays.asList(GroceryUtils.generateUid(), GroceryUtils.generateUid()).toString());
        groupStatisticService.add(init);
        logger.info("【Test】 group_statistic 表新增结果：{}", JSON.toJSONString(init));

        // 查
        GroupStatisticDO query = new GroupStatisticDO();
        query.setDimensionKey(init.getDimensionKey());
        query.setRecord(init.getRecord());
        query.setResultUids(init.getResultUids());
        List<GroupStatisticDO> selectRes = groupStatisticService.queryList(query);
        logger.info("【Test】 group_statistic 表查询结果：{}", JSON.toJSONString(selectRes));

        // 批量增
        GroupStatisticDO statisticA = new GroupStatisticDO();
        statisticA.setDimensionKey(I_COMPREHENSIVE_PROTECTION);
        statisticA.setRecord("{}");
        GroupStatisticDO statisticB = new GroupStatisticDO();
        statisticB.setDimensionKey(I_BENEFIT);
        statisticB.setRecord("{}");
        List<GroupStatisticDO> addedList = groupStatisticService.insertAndQueryBatch(Arrays.asList(statisticA, statisticB));
        logger.info("【test】 group_statistic 表批量插入返回：{}", JSON.toJSONString(addedList));
    }

    /**
     * 2/4
     * @throws InterruptedException
     */
    @Test
    public void composeAndSendBenefitThresholdMapTest() throws InterruptedException {
        groupMessagePushService.composeAndSendBenefitThresholdMap();
        Thread.sleep(30_000);
    }

    /**
     * 4/4
     * @throws InterruptedException
     */
    @Test
    public void visualizeAndSendMetricTest() throws InterruptedException {
        groupMessagePushService.visualizeAndSendMetric();
        Thread.sleep(30_000);
    }

}
