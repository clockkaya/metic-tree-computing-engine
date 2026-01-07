package com.sama.api.ledger.bean.structure;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 效益对内建设图表数据结构
 * @author: huxh
 * @description: 非常复杂
 * @datetime: 2025/9/22 15:24
 */
public class BenefitInternalComplexChart implements Serializable {

    @Serial
    private static final long serialVersionUID = -8780764596203764055L;

    /**
     * 外层 chart
     */
    @JSONField(ordinal = 1)
    private OuterChart outerChart;

    /**
     * 内层 chart
     */
    @JSONField(ordinal = 2)
    private InnerChart innerChart;

    public OuterChart getOuterChart() {
        return outerChart;
    }

    public void setOuterChart(OuterChart outerChart) {
        this.outerChart = outerChart;
    }

    public InnerChart getInnerChart() {
        return innerChart;
    }

    public void setInnerChart(InnerChart innerChart) {
        this.innerChart = innerChart;
    }

    @Override
    public String toString() {
        return "BenefitInternalComplexChart{" +
            "outerChart=" + outerChart +
            ", innerChart=" + innerChart +
            '}';
    }

    public static class OuterChart implements Serializable {

        @Serial
        private static final long serialVersionUID = -6103006305261772439L;

        /**
         * x轴类别
         */
        private List<String> xCategory;

        /**
         * y轴数据
         */
        private Map<String, List<Object>> yData;

        public List<String> getxCategory() {
            return xCategory;
        }

        public void setxCategory(List<String> xCategory) {
            this.xCategory = xCategory;
        }

        public Map<String, List<Object>> getyData() {
            return yData;
        }

        public void setyData(Map<String, List<Object>> yData) {
            this.yData = yData;
        }

        @Override
        public String toString() {
            return "OuterChart{" +
                "xCategory=" + xCategory +
                ", yData=" + yData +
                '}';
        }
    }

    public static class InnerChart extends LinkedHashMap<String, Map<String, Object>> implements Serializable {

        /**
         * [
         *     {"北京": {
         *         "流量分析（全流量分析）": {
         *             "var": 100,
         *             "score": 13454
         *         },
         *         "EDR（含防病毒）": {
         *             "value": 55.87,
         *             "score": 2784.74
         *         }
         *     }},
         *     {"安徽": {
         *         "Web防御（动态防护）-软件": {
         *             "var": 99.99,
         *             "score": 10000
         *         },
         *         "流量分析（全流量分析）": {
         *             "value": 100,
         *             "score": 14235.81
         *         }
         *     }}
         * ]
         */

        @Serial
        private static final long serialVersionUID = 5771688693820377884L;

        /**
         * 按照规定格式添加数据
         *
         * @param orgCn         省份名称
         * @param calculatorEn  算子名称
         * @param value         造价
         * @param assessedScore 评估得分
         */
        public void addData(String orgCn, String calculatorEn, Object value, Object assessedScore) {
            Map<String, Object> calculatorData = new LinkedHashMap<>();
            calculatorData.put("var", value);
            calculatorData.put("score", assessedScore);

            this.computeIfAbsent(orgCn, k -> new LinkedHashMap<>()).put(calculatorEn, calculatorData);
        }

        /**
         * 获取指定省份和产品的具体值
         *
         * @param orgCn         省份名称
         * @param calculatorEn  算子名称
         * @return              Map<String, Object>
         */
        public Object getData(String orgCn, String calculatorEn) {
            Map<String, Object> calculatorData = this.get(orgCn);
            if (calculatorData != null) {
                return calculatorData.get(calculatorEn);
            }
            return null;
        }

    }
}
