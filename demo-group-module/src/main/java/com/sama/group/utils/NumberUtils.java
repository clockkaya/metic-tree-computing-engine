package com.sama.analytic.utils;

import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

/**
 * 一式多份
 * @author: huxh
 * @description:
 * @datetime: 2025/6/27 15:26
 */
public class NumberUtils {

    private static final Logger logger = LogManager.getLogger(NumberUtils.class);

    // 异常提示信息常量
    private static final String NULL_OPERAND_WARN = "操作数为 null，直接返回 null 值";
    private static final String ZERO_DIVISOR_WARN = "除数为 0，直接返回 null 值";

    // formatAllConditions 方法参数常量
    private static final BigDecimal DEFAULT_NULL_ALTERNATIVE = BigDecimal.ZERO;
    private static final int DEFAULT_SCALE = 4;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final Boolean DEFAULT_STRIP_TRAILING_ZEROS = Boolean.TRUE;

    //==============================================================================
    // 加减法（Add/Subtract）
    //==============================================================================

    /**
     * 安全地执行两个 BigDecimal 值的加法运算。
     * 如果任一参数为 null，则以返回 null 值退出运算。
     *
     * @param first     第一个加数
     * @param augend    第二个加数
     * @return          加法运算后的结果
     */
    public static BigDecimal safeAdd(BigDecimal first, BigDecimal augend) {
        if (first == null || augend == null) {
            logger.warn(NULL_OPERAND_WARN);
            return null;
        }
        return first.add(augend);
    }

    /**
     * 安全地执行两个 BigDecimal 值的减法运算。
     * 如果任一参数为 null，则以返回 null 值退出运算。
     *
     * @param first         被减数
     * @param subtrahend    减数
     * @return              减法运算后的结果
     */
    public static BigDecimal safeSubtract(BigDecimal first, BigDecimal subtrahend) {
        if (first == null || subtrahend == null) {
            logger.warn(NULL_OPERAND_WARN);
            return null;
        }
        return first.subtract(subtrahend);
    }

    /**
     * 安全地执行两个 OptionalDouble 值的减法运算。
     * 如果任一参数为 null，则将其替换为 0.0。
     *
     * @param first         被减数（可为空）
     * @param subtrahend    减数（可为空）
     * @return              减法运算后的结果，使用实际数值参与运算
     */
    public static OptionalDouble optionalSubtract(OptionalDouble first, OptionalDouble subtrahend) {
        if (!(first.isPresent() && subtrahend.isPresent())) {
            logger.warn(NULL_OPERAND_WARN);
            return OptionalDouble.empty();
        }
        double firstValue = first.orElse(0.0);
        double subtrahendValue = subtrahend.orElse(0.0);
        return OptionalDouble.of(firstValue - subtrahendValue);
    }

    //==============================================================================
    // 乘法运算（Multiply）
    //==============================================================================

    /**
     * 安全地执行两个 BigDecimal 值的乘法运算。
     * 如果任一参数为 null，则以返回 null 值退出运算。
     *
     * @param multiplier    乘数
     * @param multiplicand  被乘数
     * @return              乘法运算后的结果
     */
    public static BigDecimal safeMultiply(BigDecimal multiplier, BigDecimal multiplicand) {
        if (multiplier == null || multiplicand == null) {
            logger.warn(NULL_OPERAND_WARN);
            return null;
        }
        return multiplier.multiply(multiplicand);
    }

    //==============================================================================
    // 除法运算（Divide）
    //==============================================================================

    /**
     * 安全地执行两个 BigDecimal 值的除法运算。
     * 如果除数为零，则记录警告并以返回 null 值退出运算。
     * 正常运算时，结果保留默认精度 DEFAULT_SCALE，并使用默认舍入模式 DEFAULT_ROUNDING_MODE。
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @return          除法运算后的结果
     */
    public static BigDecimal safeDivide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || divisor == null) {
            logger.warn(NULL_OPERAND_WARN);
            return null;
        }
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            logger.warn(ZERO_DIVISOR_WARN);
            return null;
        }
        // BigDecimal.divide() 必须指定 scale 和 roundingMode 参数
        return dividend.divide(divisor, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 安全地执行两个 long 值的除法运算。
     * 使用 BigDecimal 进行高精度计算。
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @return          除法运算后的结果
     */
    public static BigDecimal safeDivide(long dividend, long divisor) {
        BigDecimal a = BigDecimal.valueOf(dividend);
        BigDecimal b = BigDecimal.valueOf(divisor);
        return safeDivide(a, b);
    }

    public static BigDecimal safeDivide(Long dividend, Long divisor) {
        if (dividend == null || divisor == null) {
            return null;
        }
        return safeDivide((long) dividend, (long) divisor);
    }

    public static BigDecimal safeDivide(Integer dividend, Integer divisor) {
        if (dividend == null || divisor == null) {
            return null;
        }
        return safeDivide((long) dividend, (long) divisor);
    }

    /**
     * 安全地执行两个 double 值的除法运算。
     * 使用 BigDecimal 进行高精度计算。
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @return          除法运算后的结果
     */
    public static BigDecimal safeDivide(double dividend, double divisor) {
        BigDecimal a = BigDecimal.valueOf(dividend);
        BigDecimal b = BigDecimal.valueOf(divisor);
        return safeDivide(a, b);
    }

    public static BigDecimal safeDivide(Double dividend, Double divisor) {
        if (dividend == null || divisor == null) {
            return null;
        }
        return safeDivide((double) dividend, (double) divisor);
    }

    /**
     * 安全地执行两个 OptionalDouble 值的除法运算。
     * 如果任意值为空，则记录警告并返回 BigDecimal.ZERO。
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @return          除法运算后的结果或空值
     */
    public static BigDecimal safeDivide(OptionalDouble dividend, OptionalDouble divisor) {
        if (!(dividend.isPresent() && divisor.isPresent())) {
            logger.warn(NULL_OPERAND_WARN);
            return null;
        }
        BigDecimal a = BigDecimal.valueOf(dividend.getAsDouble());
        BigDecimal b = BigDecimal.valueOf(divisor.getAsDouble());
        return safeDivide(a, b);
    }

    /**
     * 非空值均值计算
     *
     * @param values    原始值
     * @return          平均值
     */
    public static BigDecimal calculateAverage(List<BigDecimal> values) {
        return calculateAverageInternal(values, false);
    }

    /**
     * 正数均值计算（排除空值和非正数）
     *
     * @param values    原始值
     * @return          平均值
     */
    public static BigDecimal calculatePositiveAverage(List<BigDecimal> values) {
        return calculateAverageInternal(values, true);
    }

    /**
     * 均值计算内部实现
     *
     * @param values    原始值
     * @param positiveOnly 是否只计算正数
     * @return          平均值
     */
    private static BigDecimal calculateAverageInternal(List<BigDecimal> values, boolean positiveOnly) {
        List<BigDecimal> validValues;
        if (positiveOnly) {
            // 空值和非正数不参与均值计算
            validValues = values.stream()
                .filter(Objects::nonNull)
                .filter(value -> value.compareTo(BigDecimal.ZERO) > 0)
                .toList();
        } else {
            // 空值不参与均值计算
            validValues = values.stream()
                .filter(Objects::nonNull)
                .toList();
        }

        // 累加
        BigDecimal sum = validValues.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        // 平均
        BigDecimal size = BigDecimal.valueOf(validValues.size());
        return NumberUtils.safeDivide(sum, size);
    }

    //==============================================================================
    // 辅助方法（Utility）
    //==============================================================================

    /**
     * 安全地将给定对象转换为 BigDecimal 值。
     * 如果输入为空或转换失败，则返回默认值。
     *
     * @param value         需要转换的对象
     * @return              转换后的 BigDecimal 值或默认值
     */
    public static BigDecimal safeConvertToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        BigDecimal converted = null;
        try {
            if (value instanceof BigDecimal) {
                converted = (BigDecimal) value;
            } else if (value instanceof Number) {
                converted = new BigDecimal(value.toString());
            } else if (value instanceof String) {
                String str = ((String) value).trim();
                if (!str.isEmpty()) {
                    converted = new BigDecimal(str);
                }
            // }
            // else if (value instanceof OptionalDouble) {
            //     OptionalDouble optionalDouble = (OptionalDouble) value;
            //     if (optionalDouble.isPresent()) {
            //         converted = new BigDecimal(Double.toString(optionalDouble.getAsDouble()));
            //     }
            } else {
                logger.warn("不支持的类型: {}", value.getClass().getName());
            }
        } catch (NumberFormatException e) {
            logger.warn("数值格式错误，无法转换为 BigDecimal", e);
        }

        return converted;
    }

    public static Double safeConvertToDouble(Object value) {
        if (value == null) {
            return null;
        }

        Double converted = null;
        try {
            if (value instanceof Double) {
                converted = (Double) value;
            } else if (value instanceof Number) {
                converted = ((Number) value).doubleValue();
            } else if (value instanceof String) {
                String str = ((String) value).trim();
                if (!str.isEmpty()) {
                    converted = Double.valueOf(str);
                }
            } else if (value instanceof BigDecimal) {
                converted = ((BigDecimal) value).doubleValue();
            } else {
                logger.warn("不支持的类型: {}", value.getClass().getName());
            }
        } catch (NumberFormatException e) {
            logger.warn("数值格式错误，无法转换为 Double", e);
        }

        return converted;
    }

    /**
     * 默认替代值 = BigDecimal.ZERO;
     * 默认精度 = 4;
     * 默认舍入模式 = RoundingMode.HALF_UP;
     * 默认去除尾部零 = Boolean.TRUE;
     */
    public static BigDecimal formatFlexibleConditions(BigDecimal value) {
        return formatAllConditions(value, DEFAULT_NULL_ALTERNATIVE, DEFAULT_SCALE, DEFAULT_STRIP_TRAILING_ZEROS);
    }

    public static BigDecimal formatFlexibleConditions(BigDecimal value, @NotNull Integer scale) {
        return formatAllConditions(value, DEFAULT_NULL_ALTERNATIVE, scale, DEFAULT_STRIP_TRAILING_ZEROS);
    }

    public static BigDecimal formatFlexibleConditions(BigDecimal value, BigDecimal alternativeValue) {
        return formatAllConditions(value, alternativeValue, DEFAULT_SCALE, DEFAULT_STRIP_TRAILING_ZEROS);
    }

    /**
     * 格式化 BigDecimal 值
     *
     * @param value                 原值，可能为 null
     * @param alternativeValue      替代值，可为 null
     * @param scale                 精度（包含舍入模式）
     * @param isStripTrailingZeros  去除末尾多余的零
     * @return                      格式化后的非空 BigDecimal 结果
     */
    private static BigDecimal formatAllConditions(BigDecimal value,
                                                  BigDecimal alternativeValue,
                                                  @NotNull Integer scale,
                                                  @NotNull Boolean isStripTrailingZeros) {

        if (value == null) {
            value = alternativeValue;
        }

        if (value == null) {
            return value;
        }

        BigDecimal reshapedValue = value.setScale(scale, DEFAULT_ROUNDING_MODE);
        if (Boolean.TRUE.equals(isStripTrailingZeros)) {
            reshapedValue = reshapedValue.stripTrailingZeros();
        }

        return reshapedValue;
    }

}
