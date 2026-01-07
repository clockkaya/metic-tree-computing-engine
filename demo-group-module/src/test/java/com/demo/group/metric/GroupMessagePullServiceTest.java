package com.sama.analytic.metric;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.sama.analytic.AnalyticApplication;
import com.sama.analytic.metric.support.GroupMessagePullService;
import com.sama.analytic.service.MetricResultUnionService;
import com.sama.analytic.utils.GroceryUtils;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/20 11:03
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class GroupMessagePullServiceTest {

    private static final Logger logger = LogManager.getLogger(GroupMessagePullServiceTest.class);

    @Resource
    MetricResultUnionService metricResultUnionService;

    @Resource
    GroupMessagePullService groupMessagePullService;

    /**
     * 1 数据库的增删改查（修改后必测！！！）
     */
    @Test
    public void metricResultUnionDBTest() {
        // 增
        MetricResultUnionDO init = new MetricResultUnionDO();
        init.setUid(GroceryUtils.generateUid());
        init.setOrgCode("mock");
        init.setMetricType(MetricTypeEnum.BENEFIT_DEV.getType());
        init.setResultTree("{}");
        init.setConfigRefTime(new Date());
        init.setDataRefTime(new Date());
        metricResultUnionService.add(init);
        logger.info("【Test】 metric_result_union 表新增结果：{}", JSON.toJSONString(init));

        // 查
        MetricResultUnionDO query = new MetricResultUnionDO();
        query.setUid(init.getUid());
        query.setOrgCode(init.getOrgCode());
        query.setMetricType(init.getMetricType());
        List<MetricResultUnionDO> selectRes = metricResultUnionService.queryList(query);
        logger.info("【Test】 metric_result_union 表查询结果：{}", JSON.toJSONString(selectRes));
    }

    /**
     * entrypoint
     */
    @Test
    public void groupMessagePullServiceTest(){
        groupMessagePullService.entrypoint();
    }

    @Test
    public void groupMessagePullBootstrap(){
        List<MetricResultUnionDO> dataList = JSON.parseObject(MESSAGE, new TypeReference<ArrayList<MetricResultUnionDO>>(){});
        metricResultUnionService.insertBatchEraseId(dataList);
    }

    private final static String MESSAGE = "";

}
