package com.sama.ledger.metric.handlers;

import com.alibaba.fastjson2.JSON;
import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import com.sama.api.ledger.bean.structure.MetricConfigNode;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.metric.calculators.BaseCalculator;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/10 10:25
 */
public abstract class BenefitProxyHandler<T extends PreparedDataModel> extends BaseHandler<T>{

    private static final Logger logger = LogManager.getLogger(BenefitProxyHandler.class);

    //==============================================================================
    // utils
    //==============================================================================

    /**
     * 对 assessedScore 取均值（空值不计入），代替原 “提取、累加 weightedScore”处理
     *
     * @param preparedData  算前数据
     * @return              传递参数体（多层嵌套）
     */
    public MetricResultNode averageInsteadOfCumulativeWeightScore(BenefitPreparedDataBO preparedData){
        try{
            List<BaseCalculator<PreparedDataModel>> calculatorMembers = new ArrayList<>();
            MetricConfigNode standingNode = loadDifferentMembersByNodeDepth(preparedData.getMetricType(), calculatorMembers, null);
            MetricResultNode resultNode = new MetricResultNode();

            // 必在叶子节点
            List<MetricResultNode> subResultToBeAdd = new ArrayList<>();
            BigDecimal averageScore = BigDecimal.ZERO;
            if (!calculatorMembers.isEmpty()) {
                List<BigDecimal> assessedScores = calculatorMembers.stream()
                    .map(calculator -> calculator.processAccordingToProcedure(preparedData))
                    .peek(subResultToBeAdd::add)
                    .map(MetricResultNode::getAssessedScore)
                    .toList();
                averageScore = NumberUtils.calculatePositiveAverage(assessedScores);
            }

            BigDecimal weight = Optional.ofNullable(standingNode.getWeight()).orElse(BigDecimal.ONE);
            BigDecimal weightedScore = NumberUtils.safeMultiply(weight, averageScore);

            BeanUtils.copyProperties(standingNode, resultNode);
            // 均值
            resultNode.setTotalScore(averageScore);
            resultNode.setWeightedScore(weightedScore);
            resultNode.setSubResultNodes(subResultToBeAdd);
            logger.info("【{}】 {} 完成主流程处理，并返回 MetricResultNode: {}",
                    handlerKey,  getClass().getSimpleName(), JSON.toJSONString(resultNode));

            return resultNode;
        } catch (Exception e){
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
            MetricResultNode blankNode = new MetricResultNode();
            blankNode.setKeyEn(handlerKey);
            return blankNode;
        }
    }

}
