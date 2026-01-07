package com.sama.api.ledger.bean.bo;

import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;

import java.util.List;

/**
 * 综合防护BO
 * @author: huxh
 * @description: 行转列
 * @datetime: 2025/7/23 10:22
 */
public class ComprehensiveProtectionPreparedDataBO extends PreparedDataModel {

    private static final long serialVersionUID = 566886142908246237L;

    /**
     * 反向更新数据
     */
    List<ComprehensiveProtectionExtendedDO> horizontalData;

    /**
     * 攻击链路被检测的数量（APT攻击防护场景|攻击链检测率）
     */
    private Integer attackChainAptFirst;

    /**
     * 攻击链路总数量（APT攻击防护场景|攻击链检测率）
     */
    private Integer attackChainAptSecond;

    /**
     * 攻击节点检测率（APT攻击防护场景|攻击路径检测率）
     */
    private Integer attackPathAptFirst;

    /**
     * 攻击节点检测率（APT攻击防护场景|攻击路径检测率）
     */
    private Integer attackPathAptSecond;

    /**
     * 平均检测率（APT攻击防护场景|攻击节点平均检测率）
     */
    private Integer attackAverageDetectionAptFirst;

    /**
     * 平均检测率（APT攻击防护场景|攻击节点平均检测率）
     */
    private Integer attackAverageDetectionAptSecond;

    /**
     * 检出用例总数量（勒索病毒防护场景|平均检测率）
     */
    private Integer averageDetectionFirst;

    /**
     * 实施用例总数量（勒索病毒防护场景|平均检测率）
     */
    private Integer averageDetectionSecond;

    /**
     * 阻断用例总数量（勒索病毒防护场景|平均阻断率）
     */
    private Integer averageBlockFirst;

    /**
     * 实施用例总数量（勒索病毒防护场景|平均阻断率）
     */
    private Integer averageBlockSecond;

    /**
     * 攻击链路被检测的数量（HW/专项对抗防护场景|攻击链检测率）
     */
    private Integer attackChainHwFirst;

    /**
     * 攻击链路总数量（HW/专项对抗防护场景|攻击链检测率）
     */
    private Integer attackChainHwSecond;

    /**
     * 攻击节点检测率（HW/专项对抗防护场景|攻击路径检测率）
     */
    private Integer attackPathHwFirst;

    /**
     * 攻击节点检测率（HW/专项对抗防护场景|攻击路径检测率）
     */
    private Integer attackPathHwSecond;

    /**
     * 平均检测率（HW/专项对抗防护场景|攻击节点平均检测率）
     */
    private Integer attackAverageDetectionHwFirst;

    /**
     * 平均检测率（HW/专项对抗防护场景|攻击节点平均检测率）
     */
    private Integer attackAverageDetectionHwSecond;

    /**
     * 检出用例总数量（全网暴露面安全防护场景|防护策略有效率）
     */
    private Integer protectionStrategyEfficiencyFirst;

    /**
     * 阻断用例总数量（全网暴露面安全防护场景|防护策略有效率）
     */
    private Integer protectionStrategyEfficiencySecond;

    /**
     * 实施用例总数量（全网暴露面安全防护场景|防护策略有效率）
     */
    private Integer protectionStrategyEfficiencyThird;

    /**
     * 有效防护资产数量（全网暴露面安全防护场景|资产防护覆盖度）
     */
    private Integer assetProtectionCoverageFirst;

    /**
     * 测试资产总数量（全网暴露面安全防护场景|资产防护覆盖度）
     */
    private Integer assetProtectionCoverageSecond;

    public List<ComprehensiveProtectionExtendedDO> getHorizontalData() {
        return horizontalData;
    }

    public void setHorizontalData(List<ComprehensiveProtectionExtendedDO> horizontalData) {
        this.horizontalData = horizontalData;
    }

    public Integer getAttackChainAptFirst() {
        return attackChainAptFirst;
    }

    public void setAttackChainAptFirst(Integer attackChainAptFirst) {
        this.attackChainAptFirst = attackChainAptFirst;
    }

    public Integer getAttackChainAptSecond() {
        return attackChainAptSecond;
    }

    public void setAttackChainAptSecond(Integer attackChainAptSecond) {
        this.attackChainAptSecond = attackChainAptSecond;
    }

    public Integer getAttackPathAptFirst() {
        return attackPathAptFirst;
    }

    public void setAttackPathAptFirst(Integer attackPathAptFirst) {
        this.attackPathAptFirst = attackPathAptFirst;
    }

    public Integer getAttackPathAptSecond() {
        return attackPathAptSecond;
    }

    public void setAttackPathAptSecond(Integer attackPathAptSecond) {
        this.attackPathAptSecond = attackPathAptSecond;
    }

    public Integer getAttackAverageDetectionAptFirst() {
        return attackAverageDetectionAptFirst;
    }

    public void setAttackAverageDetectionAptFirst(Integer attackAverageDetectionAptFirst) {
        this.attackAverageDetectionAptFirst = attackAverageDetectionAptFirst;
    }

    public Integer getAttackAverageDetectionAptSecond() {
        return attackAverageDetectionAptSecond;
    }

    public void setAttackAverageDetectionAptSecond(Integer attackAverageDetectionAptSecond) {
        this.attackAverageDetectionAptSecond = attackAverageDetectionAptSecond;
    }

    public Integer getAverageDetectionFirst() {
        return averageDetectionFirst;
    }

    public void setAverageDetectionFirst(Integer averageDetectionFirst) {
        this.averageDetectionFirst = averageDetectionFirst;
    }

    public Integer getAverageDetectionSecond() {
        return averageDetectionSecond;
    }

    public void setAverageDetectionSecond(Integer averageDetectionSecond) {
        this.averageDetectionSecond = averageDetectionSecond;
    }

    public Integer getAverageBlockFirst() {
        return averageBlockFirst;
    }

    public void setAverageBlockFirst(Integer averageBlockFirst) {
        this.averageBlockFirst = averageBlockFirst;
    }

    public Integer getAverageBlockSecond() {
        return averageBlockSecond;
    }

    public void setAverageBlockSecond(Integer averageBlockSecond) {
        this.averageBlockSecond = averageBlockSecond;
    }

    public Integer getAttackChainHwFirst() {
        return attackChainHwFirst;
    }

    public void setAttackChainHwFirst(Integer attackChainHwFirst) {
        this.attackChainHwFirst = attackChainHwFirst;
    }

    public Integer getAttackChainHwSecond() {
        return attackChainHwSecond;
    }

    public void setAttackChainHwSecond(Integer attackChainHwSecond) {
        this.attackChainHwSecond = attackChainHwSecond;
    }

    public Integer getAttackPathHwFirst() {
        return attackPathHwFirst;
    }

    public void setAttackPathHwFirst(Integer attackPathHwFirst) {
        this.attackPathHwFirst = attackPathHwFirst;
    }

    public Integer getAttackPathHwSecond() {
        return attackPathHwSecond;
    }

    public void setAttackPathHwSecond(Integer attackPathHwSecond) {
        this.attackPathHwSecond = attackPathHwSecond;
    }

    public Integer getAttackAverageDetectionHwFirst() {
        return attackAverageDetectionHwFirst;
    }

    public void setAttackAverageDetectionHwFirst(Integer attackAverageDetectionHwFirst) {
        this.attackAverageDetectionHwFirst = attackAverageDetectionHwFirst;
    }

    public Integer getAttackAverageDetectionHwSecond() {
        return attackAverageDetectionHwSecond;
    }

    public void setAttackAverageDetectionHwSecond(Integer attackAverageDetectionHwSecond) {
        this.attackAverageDetectionHwSecond = attackAverageDetectionHwSecond;
    }

    public Integer getProtectionStrategyEfficiencyFirst() {
        return protectionStrategyEfficiencyFirst;
    }

    public void setProtectionStrategyEfficiencyFirst(Integer protectionStrategyEfficiencyFirst) {
        this.protectionStrategyEfficiencyFirst = protectionStrategyEfficiencyFirst;
    }

    public Integer getProtectionStrategyEfficiencySecond() {
        return protectionStrategyEfficiencySecond;
    }

    public void setProtectionStrategyEfficiencySecond(Integer protectionStrategyEfficiencySecond) {
        this.protectionStrategyEfficiencySecond = protectionStrategyEfficiencySecond;
    }

    public Integer getProtectionStrategyEfficiencyThird() {
        return protectionStrategyEfficiencyThird;
    }

    public void setProtectionStrategyEfficiencyThird(Integer protectionStrategyEfficiencyThird) {
        this.protectionStrategyEfficiencyThird = protectionStrategyEfficiencyThird;
    }

    public Integer getAssetProtectionCoverageFirst() {
        return assetProtectionCoverageFirst;
    }

    public void setAssetProtectionCoverageFirst(Integer assetProtectionCoverageFirst) {
        this.assetProtectionCoverageFirst = assetProtectionCoverageFirst;
    }

    public Integer getAssetProtectionCoverageSecond() {
        return assetProtectionCoverageSecond;
    }

    public void setAssetProtectionCoverageSecond(Integer assetProtectionCoverageSecond) {
        this.assetProtectionCoverageSecond = assetProtectionCoverageSecond;
    }

    @Override
    public String toString() {
        return "ComprehensiveProtectionPreparedDataBO{" +
                "horizontalData=" + horizontalData +
                ", attackChainAptFirst=" + attackChainAptFirst +
                ", attackChainAptSecond=" + attackChainAptSecond +
                ", attackPathAptFirst=" + attackPathAptFirst +
                ", attackPathAptSecond=" + attackPathAptSecond +
                ", attackAverageDetectionAptFirst=" + attackAverageDetectionAptFirst +
                ", attackAverageDetectionAptSecond=" + attackAverageDetectionAptSecond +
                ", averageDetectionFirst=" + averageDetectionFirst +
                ", averageDetectionSecond=" + averageDetectionSecond +
                ", averageBlockFirst=" + averageBlockFirst +
                ", averageBlockSecond=" + averageBlockSecond +
                ", attackChainHwFirst=" + attackChainHwFirst +
                ", attackChainHwSecond=" + attackChainHwSecond +
                ", attackPathHwFirst=" + attackPathHwFirst +
                ", attackPathHwSecond=" + attackPathHwSecond +
                ", attackAverageDetectionHwFirst=" + attackAverageDetectionHwFirst +
                ", attackAverageDetectionHwSecond=" + attackAverageDetectionHwSecond +
                ", protectionStrategyEfficiencyFirst=" + protectionStrategyEfficiencyFirst +
                ", protectionStrategyEfficiencySecond=" + protectionStrategyEfficiencySecond +
                ", protectionStrategyEfficiencyThird=" + protectionStrategyEfficiencyThird +
                ", assetProtectionCoverageFirst=" + assetProtectionCoverageFirst +
                ", assetProtectionCoverageSecond=" + assetProtectionCoverageSecond +
                "} " + super.toString();
    }
}
