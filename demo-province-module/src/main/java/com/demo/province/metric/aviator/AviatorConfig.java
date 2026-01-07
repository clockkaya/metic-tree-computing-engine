package com.sama.ledger.metric.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author: huxh
 * @description: 注册自定义函数到 Aviator 环境中
 // * TODO 评估代替项 Apache Calcite
 * @datetime: 2025/6/18 10:19
 */
@Configuration
public class AviatorConfig {

    @PostConstruct
    public void registerFunctions() {
        AviatorEvaluator.addFunction(new ExcelFloorFunction());
        AviatorEvaluator.addFunction(new LinearInterpolationFunction());
        AviatorEvaluator.getInstance().setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        AviatorEvaluator.getInstance().setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, true);
    }
}
