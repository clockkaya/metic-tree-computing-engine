package com.sama.ledger.metric.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorNumber;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: huxh
 * @description: 线性插值评分函数
 * @datetime: 2025/8/11 11:12
 */
public class LinearInterpolationFunction extends AbstractFunction {

    private static final Logger logger = LogManager.getLogger(LinearInterpolationFunction.class);

    @Override
    public String getName() {
        return "linear_interpolation";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3, AviatorObject arg4, AviatorObject arg5) {
        // 获取参数值
        // arg1: 评估值 (value)
        // arg2: 最低值 (minValue)
        // arg3: 最高值 (maxValue)
        // arg4: 最低分 (minScore)
        // arg5: 最高分 (maxScore)
        BigDecimal value = NumberUtils.safeConvertToBigDecimal(FunctionUtils.getNumberValue(arg1, env));
        BigDecimal minValue = NumberUtils.safeConvertToBigDecimal(FunctionUtils.getNumberValue(arg2, env));
        BigDecimal maxValue = NumberUtils.safeConvertToBigDecimal(FunctionUtils.getNumberValue(arg3, env));
        BigDecimal minScore = NumberUtils.safeConvertToBigDecimal(FunctionUtils.getNumberValue(arg4, env));
        BigDecimal maxScore = NumberUtils.safeConvertToBigDecimal(FunctionUtils.getNumberValue(arg5, env));

        // 参数校验
        if (minValue.compareTo(maxValue) >= 0) {
            throw new IllegalArgumentException("最低值必须小于最高值");
        }

        BigDecimal result;
        // 当评估值 >= 最高值时：得分为最高分
        if (value.compareTo(maxValue) >= 0) {
            result = maxScore;
        }
        // 当评估值 <= 最低值时：得分为最低分
        else if (value.compareTo(minValue) <= 0) {
            result = minScore;
        }
        // 当评估值在最低值和最高值之间时：按线性插值计算得分
        else {
            // 计算公式: minScore + ((maxScore - minScore) * (value - minValue) / (maxValue - minValue))
            BigDecimal scoreRange = maxScore.subtract(minScore);
            BigDecimal valueOffset = value.subtract(minValue);
            BigDecimal valueRange = maxValue.subtract(minValue);

            // 为避免精度问题，使用适当的精度进行计算
            BigDecimal interpolation = NumberUtils.safeDivide(NumberUtils.safeMultiply(scoreRange, valueOffset), valueRange);
            result = NumberUtils.safeAdd(minScore, interpolation);
        }

        logger.debug("线性插值计算: 值={}, 最低值={}, 最高值={}, 最低分={}, 最高分={}, 得分={}",
                value, minValue, maxValue, minScore, maxScore, result);

        return AviatorNumber.valueOf(result);
    }

}
