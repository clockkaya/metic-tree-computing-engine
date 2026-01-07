package com.sama.ledger.metric;

import com.alibaba.nacos.shaded.com.google.common.collect.ImmutableList;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.calculators.BaseCalculator;
import com.sama.ledger.metric.handlers.BaseHandler;
import com.sama.ledger.metric.support.MetricUnitFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static com.sama.api.ledger.bean.indicator.MetricEfficiencyConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/25 15:48
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class MetricUnitFactoryTest {

    @Resource
    MetricUnitFactory metricUnitFactory;

    //==============================================================================
    // 工厂方法测试
    //==============================================================================

    @Test
    public void allFactoryTests(){
        factoryOnNonCalculatorTest();
        factoryOnCalculatorTest();
        factoryOnHandlerTest();
    }

    @Test
    public void factoryOnNonCalculatorTest(){
        try{
            BaseCalculator<EfficiencyPreparedDataBO> noneCalculator = metricUnitFactory.getCalculator(IV_PROJECT_NEW_BOOK_COMPLETION);
        } catch (Exception e){
            assertTrue(e.getMessage().contains("请排查！"));
        }
    }

    @Test
    public void factoryOnCalculatorTest(){
        List<String> calculatorKeys = ImmutableList.of(
                IV_PROJECT_APPROVAL_COMPLETION,
                IV_PROJECT_DESIGN_APPROVAL_THIS_YEAR,
                IV_PROJECT_DELIVERY_THIS_YEAR,
                IV_PROJECT_DELIVERY_LAST_YEAR,
                IV_PROJECT_CLOSE_LAST_YEAR,
                IV_PROJECT_LATE_BOOK,
                IV_PROJECT_LATE_PRE_TRANSFER,
                IV_PROJECT_LATE_CLOSE,
                IV_PROJECT_LONG_TERM_DEBT,
                IV_PROJECT_NEW_BOOK_COMPLETION,
                IV_PROJECT_WHOLE_BOOK_COMPLETION) ;
        calculatorKeys.forEach(calculatorKey -> {
            BaseCalculator<EfficiencyPreparedDataBO> specificCalculator = metricUnitFactory.getCalculator(calculatorKey);
            specificCalculator.processAccordingToProcedure(EfficiencyBaseCalculatorTest.customData());
        });
    }

    @Test
    public void factoryOnHandlerTest(){
        List<String> handlerKeys = ImmutableList.of(
                I_EFFICIENCY,
                II_SCHEDULE_PROGRESS,
                II_SCHEDULE_INVESTMENT) ;
        handlerKeys.forEach(handlerKey -> {
            BaseHandler<EfficiencyPreparedDataBO> specificHandler = metricUnitFactory.getHandler(handlerKey);
            specificHandler.recursiveHandle(EfficiencyBaseCalculatorTest.customData());
        });
    }

}
