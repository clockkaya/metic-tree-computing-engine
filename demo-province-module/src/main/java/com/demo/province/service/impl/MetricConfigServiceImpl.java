package com.sama.ledger.service.impl;


import com.alibaba.fastjson2.JSON;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.exception.GenericException;
import com.sama.api.ledger.bean.MetricConfigDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.structure.MetricConfigNode;
import com.sama.ledger.mapper.MetricConfigMapper;
import com.sama.ledger.service.MetricConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 指标配置
 * @author: huxh
 * @description: 所有配置不区分 orgCode
 * @datetime: 2025/6/23 13:56
 */
@Service
public class MetricConfigServiceImpl extends BaseServiceImpl<MetricConfigDO, MetricConfigMapper> implements MetricConfigService {

    private static final Logger logger = LogManager.getLogger(MetricConfigServiceImpl.class);

    // 使用 ConcurrentHashMap 来保存不同 metricType 的配置树
    private final Map<Integer, AtomicReference<MetricConfigNode>> configTrees = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        List<Integer> allMetricTypes = Arrays.asList(
                MetricTypeEnum.COMPREHENSIVE_PROTECTION.getType(),
                MetricTypeEnum.BENEFIT_DEV.getType(),
                MetricTypeEnum.BENEFIT_RELEASE.getType(),
                MetricTypeEnum.EFFICIENCY.getType());
        // to extend
        allMetricTypes.forEach(this::reloadTreeFromDB);
    }

    @Override
    public Date reloadTreeFromDB(int metricType) {
        // 1 从数据库取最新（id最大）一条记录，并解析
        MetricConfigDO latestRecord = mapper.selectLatestRecordByMetricType(metricType);
        if (latestRecord == null) {
            throw new GenericException(MessageFormat.format("无法从数据库取到指标（{0}）的最新一条配置记录，请排查！",
                    MetricTypeEnum.getMetricNameByType(metricType)));
        }
        MetricConfigNode treeNode = latestRecord.getRootNode();
        if (treeNode == null) {
            throw new GenericException(MessageFormat.format("当前记录的配置树(id:{0})为空，请排查！", latestRecord.getId()));
        }

        // 2 更新
        configTrees.put(metricType, new AtomicReference<>(treeNode));
        logger.info("【{}】 已重载最新配置树(id:{}) configTree: {}",
                MetricTypeEnum.getMetricNameByType(metricType), latestRecord.getId(), JSON.toJSONString(treeNode));

        return latestRecord.getUpdateTime();
    }

    @Override
    public MetricConfigNode getCurrentTree(int metricType) {
        AtomicReference<MetricConfigNode> treeRef = configTrees.get(metricType);
        return treeRef != null ? treeRef.get() : null;
    }

    @Override
    public MetricConfigNode getStandingNode(int metricType, String keyEn) {
        MetricConfigNode treeNode = configTrees.get(metricType).get();
        return findNode(treeNode, keyEn);
    }

    /**
     * 递归计算树的最大深度
     */
    @Deprecated
    private int calculateMaxDepth(MetricConfigNode node) {
        // 如果当前节点为空或没有子节点，叶子节点的深度定义为1
        if (node == null || node.getSubConfigNodes() == null) {
            return 1;
        }

        int maxChildDepth = 0;
        for (MetricConfigNode child : node.getSubConfigNodes()) {
            int childDepth = calculateMaxDepth(child);
            if (childDepth > maxChildDepth) {
                maxChildDepth = childDepth;
            }
        }

        // 返回最大子节点深度+1
        return maxChildDepth + 1;
    }

    /**
     * 基于深度优先搜索（DFS），递归查找指定 keyEn 的节点
     */
    private MetricConfigNode findNode(MetricConfigNode node, String keyEn) {
        if (node == null) {
            return null;
        }

        // 首先检查当前节点是否匹配
        if (node.getKeyEn().equals(keyEn)) {
            return node;
        }

        // 如果当前节点有子节点，则按顺序进行深度优先搜索
        if (node.getSubConfigNodes() != null) {
            for (MetricConfigNode child : node.getSubConfigNodes()) {
                // 递归查找子节点
                MetricConfigNode recursiveChild = findNode(child, keyEn);
                // 如果找到匹配的子节点，立即返回结果
                if (recursiveChild != null) {
                    return recursiveChild;
                }
            }
        }

        // 所有路径都未找到匹配项
        return null;
    }
}
