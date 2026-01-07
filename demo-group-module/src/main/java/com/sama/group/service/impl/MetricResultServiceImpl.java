package com.sama.analytic.service.impl;

import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.constants.Constants;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.analytic.mapper.MetricResultMapper;
import com.sama.analytic.service.MetricResultService;
import com.sama.analytic.utils.GroceryUtils;
import com.sama.analytic.utils.NumberUtils;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.MetricVisualizedNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:42
 */
@Service
public class MetricResultServiceImpl extends BaseServiceImpl<MetricResultDO, MetricResultMapper> implements MetricResultService {

    private static final Logger logger = LogManager.getLogger(MetricResultServiceImpl.class);

    // TODO
    private static final Map<String, String> COLS_REFLECTION_MAP = new LinkedHashMap<>();

    @Override
    public MetricResultDO getLatestRecord(String orgCode, int metricType) {
        return mapper.selectLatestRecordByOrgCodeAndMetricType(orgCode, metricType);
    }

    @Override
    public MetricResultDO getLatestRecordWithValidation(String orgCode, int metricType) {
        // 近似 #reloadTreeFromDB
        MetricResultDO resultDO = mapper.selectLatestRecordByOrgCodeAndMetricType(orgCode, metricType);
        if (DataUtils.isEmpty(resultDO)){
            throw new GenericException( MessageFormat.format("根据 orgCode:{0}, metricType:{1}，无法从数据库取到最新一条结果记录，请排查！",
                    orgCode, metricType
            ));
        }
        MetricResultNode treeNode = resultDO.getRootNode();
        if (DataUtils.isEmpty(treeNode)){
            throw new GenericException( MessageFormat.format("根据 orgCode:{0}, metricType:{1}，无法解析出有效的结果根节点，请排查！",
                    orgCode, metricType
            ));
        }

        logger.info("【{}】 获取最新结果记录并解析成功（id = {}）", MetricTypeEnum.getMetricNameByType(resultDO.getMetricType()), resultDO.getId());

        return resultDO;
    }

    @Override
    public List<String> selectAllProvinceCodes(List<Integer> metricTypeList) {
        return mapper.selectAllProvinceCodes(metricTypeList);
    }

    @Override
    public Date getMaxDataRefTime(String orgCode, List<Integer> metricTypeList) {
        return mapper.getMaxDataRefTime(orgCode, metricTypeList);
    }

    @Override
    public List<MetricResultDO> getLatestRecords(List<String> orgCodes, List<Integer> metricTypes) {
        // MyBatis在处理JDK 21中的不可变集合时会出现兼容性问题
        MetricResultDO queryDO = new MetricResultDO();
        // queryDO.setOrgCodes(orgCodes);
        // queryDO.setMetricTypes(metricTypes);
        return this.mapper.selectLatestRecords(queryDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void turnoverUntilNow(List<MetricResultDO> comingData) {
        // 基于当前时间
        Date now = new Date();
        // 基于当前所有组织
        List<String> allOrgCodes = comingData.stream().map(MetricResultDO::getOrgCode).distinct().toList();
        // 基于当前指标类型
        List<Integer> allMetricTypes = comingData.stream().map(MetricResultDO::getMetricType).distinct().toList();

        // 1 逻辑删除历史数据
        MetricResultDO queryDO = new MetricResultDO();
        // queryDO.setEndTime(now);
        // queryDO.setOrgCodes(allOrgCodes);
        // queryDO.setMetricTypes(allMetricTypes);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<Long> historyData = this.listId(queryDO);
        this.del(historyData, 0L);

        // 2 批量更新当前数据
        this.updateBatch(comingData);
    }

    @Override
    public List<MetricResultDO> getValidRecordsAfterTurnover(List<String> orgCodes, List<Integer> metricTypes) {
        MetricResultDO queryDO = new MetricResultDO();
        // 不强制 startTime 为当天
        // queryDO.setOrgCodes(orgCodes);
        // queryDO.setMetricTypes(metricTypes);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<MetricResultDO> res = queryList(queryDO);

        List<Long> ids = res.stream().map(MetricResultDO::getId).toList();
        // logger.info("【{}】 获取最新结果记录并解析成功（ids = {}）", MetricTypeEnum.getMetricNameByTypes(metricTypes), ids);
        return res;
    }

    @Override
    public MetricResultDO getOneRecordByUid(String uid) {
        MetricResultDO resultDO = new MetricResultDO();
        resultDO.setUid(uid);
        resultDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        MetricResultDO res = this.selectOne(resultDO);
        if (DataUtils.isEmpty(res)){
            throw new GenericException( MessageFormat.format("根据 uid:{0}, 无法从数据库取到最新一条结果记录，请排查！", uid));
        }
        return res;
    }

    /**
     * copy from MetricConfigServiceImpl#findNode
     */
    public static MetricResultNode findNode(MetricResultNode node, String keyEn) {
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
        if (COLS_REFLECTION_MAP.containsKey(resultNode.getKeyEn())){
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
        if (DataUtils.isNotEmpty(source.getAssessedValueMap())) {
            target.setValue(source.getAssessedValueMap().get("input"));
        }
        target.setScore(GroceryUtils.firstNonNull(source.getTotalScore(), source.getAssessedScore()));
        target.setNextNode(null);
        return target;
    }

}
