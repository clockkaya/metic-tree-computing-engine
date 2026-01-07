package com.sama.ledger.utils;

import com.core4ct.exception.GenericException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 一式多份
 * @author: huxh
 * @description: copy and iterate
 * @datetime: 2024/3/20 11:02
 */
@RefreshScope
public class KafkaClientUtils {

    private static final Logger logger = LogManager.getLogger(KafkaClientUtils.class);

    private final static int DEFAULT_NUM_PARTITIONS = 6;
    private final static short DEFAULT_REPLICATION_FACTOR = 3;

    /**
     * 创建一个 KafkaAdminClient 实例
     *
     * @param servers       Kafka 集群的地址
     * @param extraProps    一个包含额外配置的 Map，这些配置将覆盖默认配置
     * @return  实例
     */
    public static AdminClient createAdminClient(String servers, Map<String, String> extraProps) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);

        if (extraProps != null) {
            props.putAll(extraProps);
        }

        return AdminClient.create(props);
    }

    public static boolean createTopicAndWait(AdminClient adminClient, String topic) {
        return createTopicAndWait(adminClient, topic, DEFAULT_NUM_PARTITIONS, DEFAULT_REPLICATION_FACTOR, 60);
    }

    /**
     * 使用 Kafka AdminClient 创建 topic 并等待完成
     *
     * @param adminClient       Kafka AdminClient 实例
     * @param topic             要创建的 topic 名称
     * @param numPartitions     分区数量
     * @param replicationFactor 副本因子
     * @param timeoutSeconds    超时时间（秒）
     * @return 创建是否成功
     */
    public static boolean createTopicAndWait(AdminClient adminClient, String topic, int numPartitions, short replicationFactor, long timeoutSeconds) {
        try {
            NewTopic newTopic = new NewTopic(topic, numPartitions, replicationFactor);
            CreateTopicsResult result = adminClient.createTopics(Collections.singletonList(newTopic));
            result.all().get(timeoutSeconds, TimeUnit.SECONDS);
            logger.info("创建 Topic({}) 成功！", topic);
            return true;
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("TopicExistsException")) {
                logger.info("当前 Topic({}) 已存在，无需重复创建！", topic);
                return true;
            }
            String errorInfo = String.format("创建 Topic(%s) 失败，具体原因如下：", topic);
            logger.error(errorInfo, e);
            return false;
        }
    }

    /**
     * 创建一个 KafkaProducer 实例
     *
     * @param servers       Kafka 集群的地址
     * @param extraProps    一个包含额外配置的 Map，这些配置将覆盖默认配置
     * @return  实例
     */
    public static KafkaProducer<String, String> createProducer(String servers, Map<String, String> extraProps) {
        Properties props = new Properties();
        // 设置 Kafka 集群的地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        /**
         * 设置消息确认级别
         * acks=0: 生产者不会等待任何确认，消息会被立即发送到服务器。
         * acks=1: 生产者会等待 leader 分区确认消息已写入日志。
         * acks=all 或 acks=-1: 生产者会等待所有同步副本确认消息已写入日志。
         */
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        // 设置重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        // 设置重试间隔时间（毫秒）
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "100");
        // 设置键、值的序列化器
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        /**
         * 情景假设：推送的实际数据量最多一次为1000条，每条1K，即每次推送的数据总量大约为1MB
         *
         * batch.size       每次推送的数据总量为1MB左右，将batch.size设置为1MB可以确保每个批次正好容纳一次推送的所有消息，提高批处理效率。
         * max.request.size max.request.size应略大于batch.size以确保即使有少量额外的开销（如消息头等），也能顺利发送。设置为5MB可以提供足够的缓冲空间。
         * buffer.memory    每次推送的数据量为1MB，假设在高并发情况下最多有20次推送同时进行，20MB的缓冲区足够使用，同时也避免了过大的内存占用导致的资源浪费和潜在的性能问题。
         * linger.ms        较短的linger.ms值可以在不影响批处理效果的前提下减少等待时间，提高消息发送的及时性。对于每次推送1000条消息的情况，100毫秒已经足够长以收集足够的消息进行批处理，而不会显著增加延迟。
         *
         * 注意：多线程调用异步 send 方法时，消息会被添加到生产者的内部队列中进行批处理。生产者会根据 batch.size 和 linger.ms 的设置来决定何时发送这些消息。
         * 不会严格等到 batch.size 才发送：当 linger.ms 到达时，即使没有达到 batch.size，生产者也会发送当前批次的消息。
         */
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "1048576");
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "5242880");
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "20971520");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "100");

        if (extraProps != null) {
            props.putAll(extraProps);
        }

        return new KafkaProducer<>(props);
    }

    /**
     * 异步发送消息
     *
     * @param producer  KafkaProducer 实例
     * @param topic     要发送的消息的 topic
     * @param key       消息的键
     * @param value     消息的值
     * @return  Future 对象，用于获取发送结果
     */
    public static Future<RecordMetadata> sendAsyncMessage(KafkaProducer<String, String> producer, String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        return producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                logger.error("捕获小异常一只，堆栈信息如下: ", exception);
                throw new GenericException("向 Kafka 发送消息失败！");
            }
        });
    }

    /**
     * 创建并返回一个 Kafka 消费者实例
     *
     * @param kafkaAddress  Kafka 集群的地址，例如 "localhost:9092"
     * @param groupId       消费者组的 ID，用于标识消费者组
     * @param configHashMap 自定义配置项的映射，可以覆盖默认配置
     * @return  实例
     */
    public static KafkaConsumer<String, String> createConsumer(String kafkaAddress, String groupId, Map<String, String> configHashMap) {
        Properties props = new Properties();
        // 设置 Kafka 集群的地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        // 设置消费者组的 ID
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 设置自动偏移量重置策略为 "latest"，即从最新的消息开始消费
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // 设置键、值的序列化器
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 启用自动提交偏移量
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 设置自动提交偏移量的时间间隔（毫秒）
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        /**
         * 1. 消费者调用 poll() 方法
         * @max.poll.records
         * @pollDurationSeconds 控制在客户端侧的最长等待时间
         *
         * 2. 内部触发 fetch 请求 （优先级高）
         * fetch.min.bytes 和 fetch.max.bytes 控制每次拉取的数据量，确保消费者不会因为数据不足而频繁拉取，也不会因为数据过多而占用过多内存。
         * fetch.min.bytes 和 fetch.max.wait.ms 结合使用，确保消费者在等待数据时不会无限期阻塞。
         *
         * 3. Kafka 集群返回数据
         * 4. poll() 方法返回数据给消费者
         * @max.poll.interval.ms 要大于 fetch.max.wait.ms 加上平均一次拉取处理的时间。
         *
         * 最佳实践：
         * poll(Duration.ofSeconds(10))
         * props.put("fetch.max.wait.ms", "10000")
         * invokeAll 超时20s
         * props.put("max.poll.interval.ms", "30000")
         */
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "102400"); // 1k/条 * 100
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "5120000"); // 5k/条 * 1000
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "10000"); // 10s

        props.putAll(configHashMap);
        return new KafkaConsumer<>(props);
    }

}
