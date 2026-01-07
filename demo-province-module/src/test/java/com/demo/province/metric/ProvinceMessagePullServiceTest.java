package com.sama.ledger.metric;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.sama.api.ledger.bean.GroupStatisticBakDO;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.config.NacosConfig;
import com.sama.ledger.mapper.GroupStatisticBakMapper;
import com.sama.ledger.metric.support.ProvinceMessagePullService;
import com.sama.ledger.service.GroupStatisticBakService;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.KafkaClientUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.I_EFFICIENCY;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/13 15:05
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class ProvinceMessagePullServiceTest {

    private static final Logger logger = LogManager.getLogger(ProvinceMessagePullServiceTest.class);

    @Resource
    GroupStatisticBakMapper groupStatisticBakMapper;

    @Resource
    GroupStatisticBakService groupStatisticBakService;

    @Resource
    NacosConfig nacosConfig;

    @Resource
    ProvinceMessagePullService provinceMessagePullService;

    /**
     * 1 数据库的增删改查（修改后必测！！！）
     */
    @Test
    public void groupStatisticBakDBTest() {
        // 特殊：id 非自增
        // 增
        GroupStatisticBakDO init = new GroupStatisticBakDO();
        init.setId(RandomUtil.randomLong(1000, 2000));
        init.setDimensionKey(I_EFFICIENCY);
        init.setRecord("{}");
        init.setResultUids(Arrays.asList(GroceryUtils.generateUid(), GroceryUtils.generateUid()).toString());
        groupStatisticBakMapper.insert(init);
        logger.info("【Test】 group_statistic_bak 表新增结果：{}", JSON.toJSONString(init));

        // 查
        GroupStatisticBakDO query = new GroupStatisticBakDO();
        query.setId(init.getId());
        query.setDimensionKey(init.getDimensionKey());
        query.setRecord(init.getRecord());
        query.setResultUids(init.getResultUids());
        List<GroupStatisticBakDO> selectRes = groupStatisticBakService.queryList(query);
        logger.info("【Test】 group_statistic_bak 表查询结果：{}", JSON.toJSONString(selectRes));

        // 批量增
        GroupStatisticBakDO bakA = new GroupStatisticBakDO();
        bakA.setId(RandomUtil.randomLong(1000, 2000));
        bakA.setDimensionKey("1");
        bakA.setRecord("{}");
        GroupStatisticBakDO bakB = new GroupStatisticBakDO();
        bakB.setId(RandomUtil.randomLong(1000, 2000));
        bakB.setDimensionKey("2");
        bakB.setRecord("{}");
        groupStatisticBakService.insertBatch(Arrays.asList(bakA, bakB));
    }

    /**
     * 2 省侧 kafka 连接测试
     */
    @Test
    public void connectProvinceKakfaTest() {
        String groupServers = nacosConfig.getGroupServers();
        String consumerId = nacosConfig.extractConsumerId();
        Map<String, String> secProps = nacosConfig.extractKafkaSecurityProperties();
        // KafkaConsumer<String, String> provinceConsumer = KafkaClientUtils.createConsumer(provinceServers, consumerId,  secProps);
        // provinceConsumer.listTopics().forEach((k, v) -> {
        //     if (k.startsWith("edr")) {
        //         logger.info("topic: {}", k);
        //     }
        // });
        AdminClient groupAdmin = KafkaClientUtils.createAdminClient(groupServers, secProps);
        KafkaClientUtils.createTopicAndWait(groupAdmin, "group_statistic");
    }

    @Test
    public void provinceMessagePullServiceTest() {
        provinceMessagePullService.entrypoint();
    }

}
