package com.sama.ledger.metric.handlers;

import com.alibaba.fastjson2.JSON;
import com.core4ct.exception.BusinessException;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.ledger.metric.support.MetricUnitFactory;
import com.sama.ledger.metric.calculators.BaseCalculator;
import com.sama.ledger.service.MetricConfigService;
import com.sama.ledger.utils.NumberUtils;
import com.sama.api.ledger.bean.structure.MetricConfigNode;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: huxh
 * @description: BaseHandler 校验、计算框架
 * @datetime: 2025/6/24 10:14
 */
public abstract class BaseHandler<T extends PreparedDataModel> {

    private static final Logger logger = LogManager.getLogger(BaseHandler.class);

    /**
     * 处理器Key
     */
    protected String handlerKey;

    @Resource
    protected MetricConfigService metricConfigService;

    @Resource
    protected MetricUnitFactory metricUnitFactory;

    public String getHandlerKey() {
        return handlerKey;
    }

    //==============================================================================
    // callback functions
    //==============================================================================

    /**
     * 初始化指定具体 handlerKey
     */
    abstract protected void assignHandlerKey();

    //==============================================================================
    // core frame
    //==============================================================================

    @PostConstruct
    private void init() {
        assignHandlerKey();
        if (DataUtils.isEmpty(handlerKey)){
            throw new BusinessException(MessageFormat.format("该服务({0})未能满足初始化条件，请排查！", getClass().getSimpleName()));
        }
    }

    /**
     * 框架主流程：
     * 加载配置、new MetricResultNode ——> e.累加总分（递归） ——> d.加权得分
     *
     * @param preparedData  算前数据
     * @return              传递参数体（多层嵌套）
     */
    public MetricResultNode recursiveHandle(T preparedData){
        try{
            // 1 准备传递参数体（非类成员变量支持热部署）
            List<BaseCalculator<PreparedDataModel>> calculatorMembers = new ArrayList<>();
            List<BaseHandler<PreparedDataModel>> handlerMembers = new ArrayList<>();
            MetricConfigNode standingNode = loadDifferentMembersByNodeDepth(preparedData.getMetricType(), calculatorMembers, handlerMembers);
            MetricResultNode resultNode = new MetricResultNode();

            // 2 ——> 按成员类型计算 e.累加总分
            List<MetricResultNode> subResultToBeAdd = new ArrayList<>();
            BigDecimal totalScore = BigDecimal.ZERO;
            if (!calculatorMembers.isEmpty()) {
                totalScore = calculatorMembers.stream()
                        // 主流程计算
                        .map(calculator -> calculator.processAccordingToProcedure(preparedData))
                        // 收集子列表
                        .peek(subResultToBeAdd::add)
                        // 提取、累加
                        .map(dto -> NumberUtils.formatFlexibleConditions(dto.getWeightedScore()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            // Attention！与 #loadDifferentMembersByNodeDepth 不同，非排他
            if (!handlerMembers.isEmpty()){
                totalScore = handlerMembers.stream()
                        // 递归计算
                        .map(handler -> handler.recursiveHandle(preparedData))
                        .peek(subResultToBeAdd::add)
                        .map(dto -> NumberUtils.formatFlexibleConditions(dto.getWeightedScore()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            }

            // 3 ——> d.加权得分
            BigDecimal weight = Optional.ofNullable(standingNode.getWeight()).orElse(BigDecimal.ONE);
            BigDecimal weightedScore = NumberUtils.safeMultiply(weight, totalScore);

            // 4 赋值返回
            BeanUtils.copyProperties(standingNode, resultNode);
            resultNode.setTotalScore(totalScore);
            resultNode.setWeightedScore(weightedScore);
            resultNode.setSubResultNodes(subResultToBeAdd);
            logger.info("【{}】 {} 完成主流程处理，并返回 MetricResultNode: {}",
                    handlerKey,  getClass().getSimpleName(), JSON.toJSONString(resultNode));

            return resultNode;
        } catch (Exception e){
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
            // 错误也返回
            MetricResultNode blankNode = new MetricResultNode();
            blankNode.setKeyEn(handlerKey);
            return blankNode;
        }
    }

    /**
     * 统一进行校验，并返回所有错误信息
     *
     * @param preparedData  算前数据
     * @return              错误信息列表
     */
    public List<Exception> recursiveValid(T preparedData){
        // 1 准备传递参数体
        List<Exception> exceptions = new ArrayList<>();
        List<BaseCalculator<PreparedDataModel>> calculatorMembers = new ArrayList<>();
        List<BaseHandler<PreparedDataModel>> handlerMembers = new ArrayList<>();
        loadDifferentMembersByNodeDepth(preparedData.getMetricType(), calculatorMembers, handlerMembers);

        if (!calculatorMembers.isEmpty()) {
            calculatorMembers.forEach(calculator -> {
                try {
                    calculator.validatePreparedDataBefore(preparedData);
                } catch (Exception e) {
                    exceptions.add(e);
                }
            });
        }
        if (!handlerMembers.isEmpty()){
            handlerMembers.forEach(handler -> {
                try {
                    // 递归调用自身进行校验
                    List<Exception> subExceptions = handler.recursiveValid(preparedData);
                    exceptions.addAll(subExceptions);
                } catch (Exception e) {
                    exceptions.add(e);
                }
            });
        }

        return exceptions;
    }

    /**
     * 根据节点深度，加载不同类型的成员，并返回当前节点的配置信息
     *
     * @param metricType        指标类型
     * @param calculatorMembers BaseCalculator 类型的子级成员
     * @param handlerMembers    BaseHandler 类型的子级成员
     * @return                  当前节点的配置信息
     */
    protected MetricConfigNode loadDifferentMembersByNodeDepth(int metricType,
                                                             List<BaseCalculator<PreparedDataModel>> calculatorMembers,
                                                             List<BaseHandler<PreparedDataModel>> handlerMembers) {
        MetricConfigNode standingNode = metricConfigService.getStandingNode(metricType, handlerKey);

        // 当前已无子级时，即已在叶子节点，应注册为 Calculator
        if (standingNode.getSubConfigNodes() == null || standingNode.getSubConfigNodes().isEmpty()){
            throw new GenericException(MessageFormat.format("该服务({0})注册类型错误，请排查！", handlerKey));
        }

        // 按照当前的子级是否可继续下钻，加载不同类型的子级成员
        standingNode.getSubConfigNodes().forEach(childNode -> {
            String childKey = childNode.getKeyEn();
            // 不可下钻，则注册为 Calculator
            if (childNode.getSubConfigNodes() == null || childNode.getSubConfigNodes().isEmpty()){
                BaseCalculator<PreparedDataModel> calculator = metricUnitFactory.getCalculator(childKey);
                calculatorMembers.add(calculator);
            } else { // 可继续下钻，则注册为 Handler
                BaseHandler<PreparedDataModel> handler = metricUnitFactory.getHandler(childKey);
                handlerMembers.add(handler);
            }
        });

        return standingNode;
    }

}
