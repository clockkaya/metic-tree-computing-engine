package com.sama.api.ledger.bean.dto;

import com.sama.api.ledger.bean.structure.MetricVisualizedNode;

import java.io.Serializable;

/**
 * 可视化链表DTO
 * @author: huxh
 * @description: 仅为展示用的“虚拟”链（从左至右），MetricVisualizedNode 已是链表结构
 * @datetime: 2025/8/11 16:33
 */
public class PrettyLinkDTO implements Serializable {

    private static final long serialVersionUID = 5569618999057113240L;

    /**
     * 1
     */
    MetricVisualizedNode node1;

    /**
     * 1.1
     */
    MetricVisualizedNode node2;

    /**
     * 1.1.1
     */
    MetricVisualizedNode node3;

    /**
     * 1.1.1.1
     */
    MetricVisualizedNode node4;

    /**
     * 1.1.1.1.1
     */
    MetricVisualizedNode node5;

    /**
     * 1.1.1.1.1.1
     */
    MetricVisualizedNode node6;

    public MetricVisualizedNode getNode1() {
        return node1;
    }

    public void setNode1(MetricVisualizedNode node1) {
        this.node1 = node1;
    }

    public MetricVisualizedNode getNode2() {
        return node2;
    }

    public void setNode2(MetricVisualizedNode node2) {
        this.node2 = node2;
    }

    public MetricVisualizedNode getNode3() {
        return node3;
    }

    public void setNode3(MetricVisualizedNode node3) {
        this.node3 = node3;
    }

    public MetricVisualizedNode getNode4() {
        return node4;
    }

    public void setNode4(MetricVisualizedNode node4) {
        this.node4 = node4;
    }

    public MetricVisualizedNode getNode5() {
        return node5;
    }

    public void setNode5(MetricVisualizedNode node5) {
        this.node5 = node5;
    }

    public MetricVisualizedNode getNode6() {
        return node6;
    }

    public void setNode6(MetricVisualizedNode node6) {
        this.node6 = node6;
    }

    @Override
    public String toString() {
        return "StatisticGeneralLinkDTO{" +
            "node1=" + node1 +
            ", node2=" + node2 +
            ", node3=" + node3 +
            ", node4=" + node4 +
            ", node5=" + node5 +
            ", node6=" + node6 +
            '}';
    }
}
