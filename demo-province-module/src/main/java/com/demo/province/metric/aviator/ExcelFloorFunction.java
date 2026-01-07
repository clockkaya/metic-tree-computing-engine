package com.sama.ledger.metric.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorNumber;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * @author: huxh
 * @description: 等同于 excel 中 floor() 函数的作用：将参数 number 向下舍入（沿绝对值减小的方向）为最接近的 significance 的倍数。
 * @datetime: 2025/6/18 10:13
 */
public class ExcelFloorFunction extends AbstractFunction {

    private static final Logger logger = LogManager.getLogger(ExcelFloorFunction.class);

    @Override
    public String getName() {
        return "floor";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        // 获取 BigDecimal 类型的参数值
        BigDecimal number = toBigDecimal(FunctionUtils.getNumberValue(arg1, env));
        BigDecimal significance = toBigDecimal(FunctionUtils.getNumberValue(arg2, env));

        if (significance.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Significance cannot be zero.");
        }

        // 判断符号是否一致
        if (number.signum() != significance.signum()) {
            throw new IllegalArgumentException("Number and significance must have the same sign.");
        }

        // 实现 floor 函数
        int scale = significance.scale();
        BigDecimal quotient = number.divide(significance, scale, RoundingMode.DOWN);
        BigDecimal result = quotient.multiply(significance).setScale(scale, RoundingMode.DOWN);
        // logger.info("【floor】 小数位数:{}，quotient：{}，result：{}", scale, quotient, result);

        return AviatorNumber.valueOf(result);
    }

    /**
     * 将 Number 转换为 BigDecimal，确保精度安全
     */
    private BigDecimal toBigDecimal(Number number) {
        if (number == null) {
            return BigDecimal.ZERO;
        }
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        return new BigDecimal(number.toString());
    }
}
