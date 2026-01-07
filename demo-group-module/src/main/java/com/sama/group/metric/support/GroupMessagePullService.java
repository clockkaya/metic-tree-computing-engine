package com.sama.analytic.metric.support;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.core4ct.utils.DataUtils;
import com.sama.analytic.config.NacosConfig;
import com.sama.analytic.service.MetricResultUnionService;
import com.sama.analytic.utils.KafkaClientUtils;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 集团侧消息拉取
 * @author: huxh
 * @description: 详细参见《交互流程》中 Province -> Group 时，集团侧作为拉取端的处理
 * @datetime: 2025/8/14 11:06
 */
@Service
@Order(-1)
public class GroupMessagePullService implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(GroupMessagePullService.class);

    private static final String LOG_HEADER = "【集团侧|拉取端】 ";
    private static final int POLL_DURATION_SECONDS = 30;
    private static final int INVOKE_ALL_TIMEOUT_SECONDS = 20;

    /**
     * 集团侧 Kafka
     */
    protected KafkaConsumer<String, String> groupConsumer;

    /**
     * 消费者条件语句
     */
    protected Map<String, Consumer<List<Object>>> loadConditionalStatements = new HashMap<>();

    @Resource(name = "bypass")
    ThreadPoolTaskExecutor bypass;

    @Resource
    NacosConfig nacosConfig;

    @Resource
    MetricResultUnionService metricResultUnionService;

    public KafkaConsumer<String, String> getGroupConsumer() {
        return groupConsumer;
    }

    //==============================================================================
    // copy and iterate from BaseDataProcessService
    // 异常处理原则：底层操作——在最接近具体操作的地方捕获异常并记录详细信息；上层调用——保持异常传递或进行汇总处理
    //==============================================================================

    @Override
    public void run(ApplicationArguments args) {
        bypass.execute(this::entrypoint);
    }

    /**
     * 主入口
     */
    public void entrypoint(){
        while (true){
            try {
                // step 1/3 抽取
                logger.info("【breakpoint】 开始 extract (1/3)");
                ConsumerRecords<String, String> records = groupConsumer.poll(Duration.ofSeconds(POLL_DURATION_SECONDS));
                if (records.count() == 0) {
                    continue;
                }
                ConcurrentHashMap<String, List<Object>> dataContainer = transform(records);
                load(dataContainer);
            } catch (Exception e){
                logger.error(LOG_HEADER + "当此处理失败，堆栈信息如下", e);
            }
        }
    }

    /**
     * step 2/3 转换
     *
     * @param records   原始消息
     * @return          数据容器
     */
    protected ConcurrentHashMap<String, List<Object>> transform(ConsumerRecords<String, String> records){
        logger.info("【breakpoint】 开始 transform (2/3)");
        ConcurrentHashMap<String, List<Object>> dataContainer = new ConcurrentHashMap<>();
        List<Callable<Integer>> tasks = new ArrayList<>();

        // 构建任务列表
        records.forEach(record -> {
            String topic = record.topic();
            String key = record.key();
            String value = record.value();
            if (DataUtils.isEmpty(value)) {
                return;
            }
            tasks.add(()-> {
                try {
                    routeTransform(topic, key, value, dataContainer);
                    return 1;
                } catch (Exception e){
                    String errorInfo = String.format("transform 处理失败，[Topic: %s][Value: %s]", topic, value);
                    logger.error(LOG_HEADER + errorInfo, e);
                    return 0;
                }
            });
        });

        // 批量执行限时任务
        if (!tasks.isEmpty()) {
            // 使用虚拟线程是最佳选择
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                List<Future<Integer>> futures = executor.invokeAll(tasks, INVOKE_ALL_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                // 统计结果
                long successCount = futures.stream()
                    .mapToLong(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            logger.error(LOG_HEADER + "捕获小异常一只，堆栈信息如下", e);
                            return 0;
                        }
                    })
                    .sum();
                logger.info(LOG_HEADER + "限时执行 transform 任务，成功 {}/{} 个", successCount, tasks.size());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error(LOG_HEADER + "任务执行被中断，堆栈信息如下", e);
            }
        }

        return dataContainer;
    }

    /**
     * step 3/3 持久化
     *
     * @param dataContainer 数据容器
     */
    protected void load(ConcurrentHashMap<String, List<Object>> dataContainer){
        if (dataContainer == null || dataContainer.size() == 0){
            return;
        }

        Map<String, Integer> dataStatistic = dataContainer.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().size()
            ));
        logger.info("【breakpoint】 开始 load (3/3)，当前 dataContainer：{}", dataStatistic);

        dataContainer.forEach((k, v) -> {
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                executor.execute(() -> {
                    try {
                        // 与 #routeByTopic 相对应的处理
                        Consumer<List<Object>> statement = loadConditionalStatements.get(k);
                        if (statement != null) {
                            statement.accept(v);
                        }
                    } catch (Exception e) {
                        logger.error(LOG_HEADER + "load 处理失败，堆栈信息如下", e);
                    }
                });
            }
        });
    }

    //==============================================================================
    // to extend
    //==============================================================================

    @PostConstruct
    protected void init() {
        // kafka 消费端订阅
        groupConsumer = KafkaClientUtils.createConsumer(nacosConfig.getGroupServers(), nacosConfig.extractConsumerId(), nacosConfig.extractKafkaSecurityProperties());
        String singleRegex = "^" + MetricConstants.KafkaTopic.METRIC_RESULT + ".*";
        Pattern singlePattern = Pattern.compile(singleRegex);
        groupConsumer.subscribe(singlePattern);

        // 定义静态的加载语句
        loadConditionalStatements.put(MetricConstants.KafkaTopic.METRIC_RESULT, this::loadMetricResultUnion);
    }

    private void routeTransform(String topic, String key, String value, ConcurrentHashMap<String, List<Object>> dataContainer) {
        if (topic.startsWith(MetricConstants.KafkaTopic.METRIC_RESULT)) {
            transformMetricResultUnion(dataContainer, value);
        }
    }

    private void transformMetricResultUnion(ConcurrentHashMap<String, List<Object>> dataContainer, String value){
        // 此处是 List
        List<MetricResultUnionDO> dataList = JSON.parseObject(value, new TypeReference<ArrayList<MetricResultUnionDO>>(){});
        dataContainer.computeIfAbsent(MetricConstants.KafkaTopic.METRIC_RESULT, k -> new CopyOnWriteArrayList<>()).addAll(dataList);
    }

    private void loadMetricResultUnion(List<?> dataList){
        metricResultUnionService.insertBatchEraseId((List<MetricResultUnionDO>) dataList);
        logger.info(LOG_HEADER + "计入表 sama_ledger.metric_result_union 成功，共计 {} 条", dataList.size());
    }

}
