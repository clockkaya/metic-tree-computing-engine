package com.sama.ledger.metric.calculators;

import com.alibaba.fastjson2.JSON;
import com.core4ct.exception.BusinessException;
import com.core4ct.utils.DataUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import com.sama.api.ledger.bean.structure.MetricConfigNode;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.config.NacosConfig;
import com.sama.ledger.service.MetricConfigService;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: huxh
 * @description: Calculator 校验、计算框架
 * @datetime: 2025/6/16 14:30
 */
public abstract class BaseCalculator <T extends PreparedDataModel>{

    private static final Logger logger = LogManager.getLogger(BaseCalculator.class);

    protected static final String KEY_INPUT = "input";

    /**
     * 算子Key
     */
    protected String calculatorKey;

    /**
     * 统计年（如无指定，则默认日历去年）
     */
    protected Integer statisticYear;

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    @Resource
    NacosConfig nacosConfig;

    @Resource
    protected MetricConfigService metricConfigService;

    public String getCalculatorKey() {
        return calculatorKey;
    }

    public LoadingCache<String, String> getOrgCodeAndNameCache() {
        return orgCodeAndNameCache;
    }

    //==============================================================================
    // callback functions （每个算子的具体实现都不同）
    //==============================================================================

    /**
     * 初始化指定具体 calculatorKey
     */
    abstract protected void assignCalculatorKey();

    /**
     * 由 a.过程数据 计算 b.评估值，并赋值到传递参数体中
     * 因可能传入 ，不在主框架中统一赋值处理
     *
     * @param preparedData  算前数据，包含 a.过程数据
     * @param resultNode    传递参数体，需赋值
     */
    abstract protected void calculateAndSetAssessedValue(T preparedData, MetricResultNode resultNode);

    //==============================================================================
    // core frame
    //==============================================================================

    @PostConstruct
    protected void init() {
        assignCalculatorKey();
        if (DataUtils.isEmpty(calculatorKey)){
            throw new BusinessException(MessageFormat.format("该服务({0})未能满足初始化条件，请排查！", getClass().getSimpleName()));
        }

        statisticYear = Optional.of(nacosConfig.getStatisticYear())
            .filter(assigned -> assigned != 0)
            .orElseGet(() -> LocalDate.now().minusYears(1).getYear());
    }

    /**
     * 对 b.评估值 的结果单独校验非空，条件可重写以扩展灵活性
     *
     * @param resultNode    前序处理后的 MetricResultNode
     * @param metricType    指标类型
     * @return              是否进入计算 c.评估得分
     */
    protected boolean stepIntoAssessedScore(MetricResultNode resultNode, int metricType){
        Map<String, Object> assessedValueMapToValid = resultNode.getAssessedValueMap();
        if (DataUtils.isEmpty(assessedValueMapToValid) || assessedValueMapToValid.containsValue(null)){
            logger.info("【{}】 前序处理未能得到有效的 b.评估值 ", calculatorKey);
            // 擦除
            resultNode.setAssessedValueMap(null);
            return false;
        }
        return true;
    }

    /**
     * 框架主流程：
     * 加载 MetricConfigNode、MetricResultNode ——> a.过程数据 ——> b.评估值 ——> （校验后）c.评估得分 ——> d.加权得分
     * Attention！需联动修改 MockDataUtils#simulateSimpleAviatorCalculate
     *
     * @param preparedData  算前数据
     * @return              传递参数体
     */
    public MetricResultNode processAccordingToProcedure(T preparedData){
        // 1 准备传递参数体
        int metricType = preparedData.getMetricType();
        MetricConfigNode metricConfigNode = metricConfigService.getStandingNode(metricType, calculatorKey);
        MetricResultNode resultNode = new MetricResultNode();
        BeanUtils.copyProperties(metricConfigNode, resultNode);

        try{
            // 2 算前数据 ——> a.过程数据 ——> b.评估值
            calculateAndSetAssessedValue(preparedData, resultNode);

            // 3.1 校验
            BigDecimal assessedScore = null;
            if (stepIntoAssessedScore(resultNode, metricType)){
                // 3.2 b.评估值 ——> c.评估得分
                String aviatorRule = metricConfigNode.getAviatorRule();
                Expression compiledExpr = AviatorEvaluator.compile(aviatorRule, true);
                Object assessedScoreObj = compiledExpr.execute(resultNode.getAssessedValueMap());
                assessedScore = NumberUtils.safeConvertToBigDecimal(assessedScoreObj);
            }
            resultNode.setAssessedScore(assessedScore);

            // 4 c.评估得分 ——> d.加权得分
            BigDecimal weight = metricConfigNode.getWeight();
            BigDecimal weightedScore = NumberUtils.safeMultiply(weight, assessedScore);
            resultNode.setWeightedScore(weightedScore);

            logger.info("【{}】 {} 完成主流程处理，并返回 MetricResultNode: {} ",
                    calculatorKey, getClass().getSimpleName(), JSON.toJSONString(resultNode));

        } catch (Exception e){
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
        }

        // 5 返回节点结果
        return resultNode;
    }

    //==============================================================================
    // penetrable functions
    //==============================================================================

    /**
     * 校验算前数据
     *
     * @param preparedData  算前数据
     */
    abstract public void validatePreparedDataBefore(T preparedData);

    //==============================================================================
    // utils
    //==============================================================================

    protected Map<String, Object> getDefaultAssessedValueMap(Object input){
        Map<String, Object> defaultMap = new LinkedHashMap<>();
        defaultMap.put(KEY_INPUT, input);
        return defaultMap;
    }

}
