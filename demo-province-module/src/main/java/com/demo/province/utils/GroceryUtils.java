package com.sama.ledger.utils;

import com.alibaba.fastjson2.JSONObject;
import com.core4ct.utils.DateUtils;
import com.google.common.math.Stats;
import com.sama.api.ledger.bean.structure.ThresholdPair;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 一式多份
 * @author: huxh
 * @description:
 * @datetime: 2025/8/12 9:25
 */
public class GroceryUtils {

    /**
     * 获取第一个非空的元素
     *
     * @param items
     * @return
     * @param <T>
     */
    public static <T> T firstNonNull(T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    /**
     * 生成 UID
     *
     * @return
     */
    public static String generateUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 计算阈值对（存在精度问题）
     *
     * @param dataList  数据集
     * @return          ThresholdPair(Double lower, Double upper)
     */
    public static ThresholdPair calculateThresholdPair(List<Number> dataList) {
        // 筛选出正数
        double[] positiveData = dataList.stream().filter(Objects::nonNull).mapToDouble(Number::doubleValue).filter(d -> d > 0).toArray();

        if (positiveData.length == 0) {
            return new ThresholdPair(null, null);
        }

        // 特殊情况：只有一个数据点时，直接返回该点作为上下限
        if (positiveData.length == 1) {
            double value = positiveData[0];
            return new ThresholdPair(value, value);
        }

        // log, mean, std
        double[] logData = new double[positiveData.length];
        for (int i = 0; i < positiveData.length; i++) {
            logData[i] = Math.log10(positiveData[i]);
        }
        Stats stats = Stats.of(logData);
        double meanLog = stats.mean();
        double stdLog = stats.populationStandardDeviation();

        // 计算合理区间的下限和上限，并反转换回原始尺度
        double lower = Math.pow(10, meanLog - 0.5 * stdLog);
        double upper = Math.pow(10, meanLog + 0.5 * stdLog);

        // 返回包含下限和上限的数组
        return new ThresholdPair(lower, upper);
    }

    /**
     * javaBean 转 JSONObject
     *
     * @param obj   原 javaBean
     * @return      JSONObject
     */
    public static JSONObject javaBean2Json(Object obj) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj));
    }

    /**
     * 根据年、月、日构建截止日期
     *
     * @param year  年份
     * @param month 月份(1-12)
     * @param day   日期
     * @return      对应的Date对象
     */
    public static Date buildCutoffDate(Integer year, Integer month, Integer day) {
        if (year == null || month == null || day == null) {
            throw new IllegalArgumentException("Year, month and day cannot be null");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }

        String dateStr = String.format("%d%02d%02d", year, month, day);
        return DateUtils.stringToDate(dateStr);
    }

}
