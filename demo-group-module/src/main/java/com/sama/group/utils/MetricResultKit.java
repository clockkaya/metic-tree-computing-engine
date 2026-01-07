package com.sama.analytic.utils;

import com.alibaba.fastjson2.JSON;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.dto.PrettyLinkDTO;
import com.sama.api.ledger.bean.indicator.MetricBenefitL2CalculatorEnum;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.MetricVisualizedNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一式多份
 * @author: huxh
 * @description: 围绕 MetricResultDO/MetricResultUnionDO 的工具类
 * @datetime: 2025/9/18 9:08
 */
public class MetricResultKit {

    private static final Logger logger = LogManager.getLogger(MetricResultKit.class);

    /**
     * 根据传入结果树，查找指定key的节点
     *
     * @param node  结果树
     * @param keyEn 节点名
     * @return      MetricResultNode
     */
    public static MetricResultNode findNode(MetricResultNode node, String keyEn) {
        // TODO：是否直接抛出 null
        if (node == null) {
            return null;
        }

        if (node.getKeyEn().equals(keyEn)) {
            return node;
        }

        if (node.getSubResultNodes() != null) {
            for (MetricResultNode subResult : node.getSubResultNodes()) {
                MetricResultNode recursiveSubResult = findNode(subResult, keyEn);
                if (recursiveSubResult != null) {
                    return recursiveSubResult;
                }
            }
        }

        return null;
    }

    /**
     * 将 MetricResultNode 树结构转换为 多个 MetricResultNode 单节点结构
     *
     * @param resultNode    指标结果节点
     * @param flatList      用于存储生成的单节点集合
     */
    public static void generateFlatSingleRecursively(MetricResultNode resultNode, List<MetricResultNode> flatList) {
        if (resultNode == null) {
            return;
        }

        // 联动 offerVisualizedResult#1.3 处做扩展处理
        if (MetricBenefitL2CalculatorEnum.findByCalculatorEn(resultNode.getKeyEn()) != null){
            return;
        }

        // 创建新节点并复制基本字段
        MetricResultNode newNode = new MetricResultNode();
        newNode.setKeyEn(resultNode.getKeyEn());
        newNode.setKeyCn(resultNode.getKeyCn());
        newNode.setTotalScore(resultNode.getTotalScore());
        newNode.setProcessingDataMap(resultNode.getProcessingDataMap());
        newNode.setAssessedValueMap(resultNode.getAssessedValueMap());
        newNode.setAssessedScore(NumberUtils.formatFlexibleConditions(resultNode.getAssessedScore(), 2));
        newNode.setWeight(resultNode.getWeight());
        newNode.setWeightedScore(NumberUtils.formatFlexibleConditions(resultNode.getWeightedScore(), 2));

        // 擦除子节点及其他无效展示引用
        newNode.setSubResultNodes(null);
        newNode.setAviatorRule(null);

        // 添加到展平列表
        flatList.add(newNode);

        // 递归处理子节点
        if (resultNode.getSubResultNodes() != null && !resultNode.getSubResultNodes().isEmpty()) {
            for (MetricResultNode childNode : resultNode.getSubResultNodes()) {
                generateFlatSingleRecursively(childNode, flatList);
            }
        }
    }

    /**
     * 将 MetricResultNode 树结构转换为 多条 MetricVisualizedNode 链表结构
     *
     * @param resultNode    指标结果节点
     * @param linkList      用于存储生成的链表集合
     */
    public static void generateLinkRecursively(MetricResultNode resultNode, List<MetricVisualizedNode> linkList) {
        // 1 如果节点为空，直接返回
        if (resultNode == null) {
            return;
        }

        // 2.1 处理叶子节点
        if (DataUtils.isEmpty(resultNode.getSubResultNodes())) {
            Map<String, Object> processingDataMap = resultNode.getProcessingDataMap();
            if (processingDataMap != null && !processingDataMap.isEmpty()){
                // 为每个键值对创建一个完整的链
                processingDataMap.forEach((key, value) -> {
                    // 创建链表的当前节点
                    MetricVisualizedNode standingNode = copyPasteClean(resultNode);
                    // 创建尾节点并赋值
                    MetricVisualizedNode tailNode = new MetricVisualizedNode();
                    tailNode.setKeyCn(key);
                    tailNode.setValue(value);
                    // 连接尾节点到链表（当前节点）
                    standingNode.setNextNode(tailNode);
                    // 添加完整链表到结果列表
                    linkList.add(standingNode);
                });
            } else {
                // 即使没有处理数据，也要确保链的完整性
                linkList.add(copyPasteClean(resultNode));
            }
        } else {
            // 2.2 处理内部节点
            for (MetricResultNode subResultNode : resultNode.getSubResultNodes()) {
                // 递归创建子链
                List<MetricVisualizedNode> subLinks = new ArrayList<>();
                generateLinkRecursively(subResultNode, subLinks);

                // 从后向前处理
                subLinks.forEach(subLink -> {
                    MetricVisualizedNode standingNode = copyPasteClean(resultNode);
                    standingNode.setNextNode(subLink);
                    linkList.add(standingNode);
                });
            }
        }
    }

    private static MetricVisualizedNode copyPasteClean(MetricResultNode source) {
        MetricVisualizedNode target = new MetricVisualizedNode();
        BeanUtils.copyProperties(source, target);
        target.setValue(source.extractAssessedValue());
        target.setScore(GroceryUtils.firstNonNull(source.getTotalScore(), source.getAssessedScore()));
        target.setNextNode(null);
        return target;
    }

    /**
     * 指标结果（的省侧原始数据） ——> 可视化链表DTO；一对多
     *
     * @param resultDO  MetricResultDO
     * @return          List<StatisticGeneralLinkDTO>
     */
    public static List<PrettyLinkDTO> composePrettyLinkList(MetricResultDO resultDO) {
        List<MetricVisualizedNode> linkList = new ArrayList<>();
        generateLinkRecursively(resultDO.getRootNode(), linkList);
        List<PrettyLinkDTO> res = linkList.stream().map(MetricResultKit::singlePrettyLink).toList();
        // logger.info();

        return res;
    }

    /**
     * 指标结果的集团侧汇总 ——> 可视化链表DTO；一对多
     *
     * @param unionDO   MetricResultUnionDO
     * @return          List<StatisticGeneralLinkDTO>
     */
    public static List<PrettyLinkDTO> composePrettyLinkList(MetricResultUnionDO unionDO) {
        List<MetricVisualizedNode> linkList = new ArrayList<>();
        generateLinkRecursively(unionDO.getRootNode(), linkList);
        List<PrettyLinkDTO> res = linkList.stream().map(MetricResultKit::singlePrettyLink).toList();
        // logger.info();

        return res;
    }

    private static PrettyLinkDTO singlePrettyLink(MetricVisualizedNode headNode) {
        PrettyLinkDTO prettyLinkDTO = new PrettyLinkDTO();

        if (DataUtils.isEmpty(headNode.getKeyEn())) {
            return prettyLinkDTO;
        }

        prettyLinkDTO.setNode1(copyPasteCleanForPretty(headNode));

        // 通过循环遍历链表，避免多层嵌套
        MetricVisualizedNode currentNode = headNode.getNextNode();
        int nodeLevel = 2;

        while (currentNode != null && nodeLevel <= 6) {
            switch (nodeLevel) {
                case 2:
                    prettyLinkDTO.setNode2(copyPasteCleanForPretty(currentNode));
                    break;
                case 3:
                    prettyLinkDTO.setNode3(copyPasteCleanForPretty(currentNode));
                    break;
                case 4:
                    prettyLinkDTO.setNode4(copyPasteCleanForPretty(currentNode));
                    break;
                case 5:
                    prettyLinkDTO.setNode5(copyPasteCleanForPretty(currentNode));
                    break;
                case 6:
                    prettyLinkDTO.setNode6(copyPasteCleanForPretty(currentNode));
                    break;
            }
            currentNode = currentNode.getNextNode();
            nodeLevel++;
        }

        return prettyLinkDTO;
    }

    private static MetricVisualizedNode copyPasteCleanForPretty(MetricVisualizedNode source) {
        MetricVisualizedNode target = new MetricVisualizedNode();
        BeanUtils.copyProperties(source, target);
        target.setNextNode(null);
        return target;
    }

    /**
     * 批量提取 id
     *
     * @param unionDOList   目标集合
     * @return              String，形如 [1,2]
     */
    public static String extractIds(List<MetricResultUnionDO> unionDOList){
        return JSON.toJSONString(unionDOList.stream().map(MetricResultUnionDO::getId).toList());
    }

    /**
     * 批量提取 uid
     *
     * @param unionDOList   目标集合
     * @return              String，形如 ["a","b"]
     */
    public static String extractUids(List<MetricResultUnionDO> unionDOList){
        return JSON.toJSONString(unionDOList.stream().map(MetricResultUnionDO::getUid).toList());
    }

}
