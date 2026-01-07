package com.sama.ledger.metric.support;

import com.core4ct.exception.BusinessException;
import com.sama.ledger.metric.calculators.BaseCalculator;
import com.sama.ledger.metric.handlers.BaseHandler;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * @author: huxh
 * @description: Calculator、Handler 工厂类
 * ObjectProvider + stream() 比 ApplicationContext + CacheMap(ConcurrentHashMap) 更简洁、高效
 * @datetime: 2025/6/26 9:35
 */
@Component
public class MetricUnitFactory<T extends PreparedDataModel> {

    private static final Logger logger = LogManager.getLogger(MetricUnitFactory.class);

    /**
     * Attention！严格区分类型
     * ObjectProvider<BaseCalculator<PreparedDataModel>> 和 BaseCalculator<ConstructionInProcessPreparedDataBO> 是不同类型
     */
    @Resource
    private ObjectProvider<BaseCalculator<T>> efficiencyCalculatorProvider;

    @Resource
    private ObjectProvider<BaseHandler<T>> efficiencyHandlerProvider;

    public BaseCalculator<T> getCalculator(String calculatorKey) {
        return efficiencyCalculatorProvider.stream()
                .filter(calculator -> calculator.getCalculatorKey().equals(calculatorKey))
                .findFirst()
                .orElseThrow(() -> new BusinessException(MessageFormat.format("无法找到 calculatorKey({0})对应的 @Component，请排查！", calculatorKey)));
    }

    public BaseHandler<T> getHandler(String handlerKey) {
        return efficiencyHandlerProvider.stream()
                .filter(handler -> handler.getHandlerKey().equals(handlerKey))
                .findFirst()
                .orElseThrow(() -> new BusinessException(MessageFormat.format("无法找到 handlerKey({0})对应的 @Component，请排查！", handlerKey)));
    }

}
