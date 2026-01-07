package com.sama.api.ledger.bean;

import com.core4ct.base.BaseModel;

import java.io.Serial;

/**
 * 效益对内建设扩展表
 * @author: huxh
 * @description:
 * @datetime: 2025/9/11 13:59
 */
@Deprecated
public class BenefitInternalConstructionExtensionDO extends BaseModel {

    @Serial
    private static final long serialVersionUID = -7079060432475433972L;

    private Long joinId;

    /**
     *  1	防火墙-硬件
     */
    private Double abilityFirewallHardwareVar;

    /**
     *  2	防火墙-原子能力
     */
    private Double abilityFirewallAtomicCapabilityVar;

    /**
     *  3	IPS-硬件
     */
    private Double abilityIpsHardwareVar;

    /**
     *  4	IPS-原子能力
     */
    private Double abilityIpsAtomicCapabilityVar;

    /**
     *  5	Web防御（WAF）-硬件
     */
    private Double abilityWafHardwareVar;

    /**
     *  6	Web防御（WAF）-硬件-国产化设备
     */
    private Double abilityWafDomesticHardwareVar;

    /**
     *  7	Web防御（WAF）-原子能力
     */
    private Double abilityWafAtomicCapabilityVar;

    /**
     *  8	Web防御（动态防护）-硬件
     */
    private Double abilityWebDynamicDefenseHardwareVar;

    /**
     *  9	Web防御（动态防护）-软件
     */
    private Double abilityWebDynamicDefenseSoftwareVar;

    /**
     *  10	流量清洗（抗DDoS）
     */
    private Double abilityTrafficScrubbingVar;

    /**
     *  11	流量分析（全流量分析）
     */
    private Double abilityFullTrafficAnalysisVar;

    /**
     *  12	堡垒机
     */
    private Double abilityBastionHostVar;

    /**
     *  13	日志审计
     */
    private Double abilityLogAuditVar;

    /**
     *  14	EDR（含防病毒）
     */
    private Double abilityEdrVar;

    /**
     *  15	漏洞扫描（主机）-硬件
     */
    private Double abilityHostVulnerabilityScanHardwareVar;

    /**
     *  16	漏洞扫描（主机）-原子能力
     */
    private Double abilityHostVulnerabilityScanAtomicCapabilityVar;

    /**
     *  17	漏洞扫描（Web）-硬件
     */
    private Double abilityWebVulnerabilityScanHardwareVar;

    /**
     *  18	漏洞扫描（Web）-原子能力
     */
    private Double abilityWebVulnerabilityScanAtomicCapabilityVar;

    /**
     *  19	容器安全
     */
    private Double abilityContainerSecurityVar;

    /**
     *  20	容器安全-纯agent
     */
    private Double abilityContainerSecurityAgentVar;

    /**
     *  21	网页防篡改-非池化
     */
    private Double abilityWebTamperPreventionVar;

    /**
     *  22	网页防篡改-原子能力
     */
    private Double abilityWebTamperPreventionAtomicCapabilityVar;

    /**
     *  23	接口安全管控（API网关）
     */
    private Double abilityApiGatewaySecurityVar;

    /**
     *  24	数据防泄漏（网络侧）
     */
    private Double abilityNetworkDlpVar;

    /**
     *  25	数据防泄漏（网络侧）-国产化设备
     */
    private Double abilityNetworkDomesticDlpVar;

    /**
     *  26	数据脱敏-动态
     */
    private Double abilityDynamicDataMaskingVar;

    /**
     *  27	数据脱敏-静态
     */
    private Double abilityStaticDataMaskingVar;

    /**
     *  28	数据库审计
     */
    private Double abilityDatabaseAuditVar;

    /**
     *  29	信令防火墙 C-IWF（5GC）
     */
    private Double abilitySignalingFirewallVar;

    /**
     *  30	零信任（SDP）
     */
    private Double abilityZeroTrustSdpVar;

    /**
     *  31	零信任（SDP）-国产化设备
     */
    private Double abilityZeroTrustDomesticSdpVar;

    /**
     *  32	蜜罐
     */
    private Double abilityHoneypotVar;

    /**
     *  33	蜜罐-国产化设备
     */
    private Double abilityDomesticHoneypotVar;

    /**
     *  34	微隔离
     */
    private Double abilityMicroSegmentationVar;

    /**
     *  35	异常流量检测-流量转发设备
     */
    private Double abilityAbnormalTrafficDetectionForwardingDeviceVar;

    /**
     *  36	异常流量检测-流量采集设备
     */
    private Double abilityAbnormalTrafficDetectionCollectionDeviceVar;

    /**
     *  37	异常流量检测-报表处理设备
     */
    private Double abilityAbnormalTrafficDetectionReportDeviceVar;

    /**
     *  38	数据加解密（5GC）
     */
    private Double abilityDataEncryptionDecryptionVar;

    /**
     *  39	僵木蠕监测-监测处置设备
     */
    private Double abilityBotnetWormDetectionDisposalDeviceVar;

    /**
     *  40	僵木蠕监测-接口转发网关
     */
    private Double abilityBotnetWormDetectionGatewayVar;

    /**
     *  41	僵木蠕监测-监测处置设备考核软件升级
     */
    private Double abilityBotnetWormDetectionSoftwareUpgradeVar;

    /**
     *  42	IDCISP-硬件（分流器+全量服务器）
     */
    private Double abilityIdcispHardwareVar;

    /**
     *  43	IDCISP-软件
     *  Attention!
     */
    private Double abilityIdcispSoftwareVar;

    /**
     *  44	移动DPI-硬件
     */
    private Double abilityMobileDpiHardwareVar;

    /**
     *  45	移动DPI-软件
     */
    private Double abilityMobileDpiSoftwareVar;

    /**
     *  46	固网DPI-硬件（分流器+DPI服务器）
     */
    private Double abilityFixedNetworkDpiHardwareVar;

    /**
     *  47	固网DPI-软件
     *  Attention!
     */
    private Double abilityFixedNetworkDpiSoftwareVar;

    /**
     *  48	移动恶意程序监测-硬件
     */
    private Double abilityMobileMalwareDetectionHardwareVar;

    /**
     *  49	移动恶意程序监测-软件
     */
    private Double abilityMobileMalwareDetectionSoftwareVar;

    /**
     *  50	移动恶意程序监测-软件升级
     */
    private Double abilityMobileMalwareDetectionSoftwareUpgradeVar;

    /**
     *  1	移动上网日志留存-集采
     */
    private Double softwareMobileInternetLogRetentionCentralizedVar;

    /**
     *  2	移动上网日志留存-个性化省采
     */
    private Double softwareMobileInternetLogRetentionCustomizedVar;

    /**
     *  3	僵木蠕监测-平台总价
     */
    private Double softwareBotnetWormDetectionPlatformTotalPriceVar;

    /**
     *  4	IDCISP-平台总价
     */
    private Double softwareIdcispPlatformTotalPriceVar;

    /**
     *  5	移动DPI-平台总价
     */
    private Double softwareMobileDpiPlatformTotalPriceVar;

    /**
     *  6	固网DPI-平台总价
     */
    private Double softwareFixedNetworkDpiPlatformTotalPriceVar;

    /**
     *  7	移动恶意程序监测-平台总价
     */
    private Double softwareMobileMalwareDetectionPlatformTotalPriceVar;

    /**
     *  8	资产管理
     */
    private Double softwareAssetManagementVar;

    /**
     *  9	基线管理
     */
    private Double softwareBaselineManagementVar;

    /**
     *  10	漏洞管理
     */
    private Double softwareVulnerabilityManagementVar;

    /**
     *  11	互联网暴露面管理
     */
    private Double softwareInternetExposureManagementVar;

    /**
     *  12	内网资产测绘
     */
    private Double softwareInternalNetworkAssetMappingVar;

    /**
     *  13	4A
     */
    private Double softwareAaaaVar;

    /**
     *  14	APP上线检测
     */
    private Double softwareAppReleaseDetectionVar;

    /**
     *  15	数据资产管理
     */
    private Double softwareDataAssetManagementVar;

    /**
     *  16	密码服务管理
     */
    private Double softwarePasswordServiceManagementVar;

    /**
     *  17	威胁情报
     */
    private Double softwareThreatIntelligenceVar;

    /**
     *  18	网络安全态势感知
     */
    private Double softwareNetworkSecuritySituationalAwarenessVar;

    /**
     *  19	数据安全态势感知
     */
    private Double softwareDataSecuritySituationalAwarenessVar;

    /**
     *  20	互联网网站备案监测
     */
    private Double softwareWebsiteFilingMonitoringVar;

    /**
     *  21	不良信息监测
     */
    private Double softwareHarmfulInformationMonitoringVar;

    /**
     *  22	反诈管理
     */
    private Double softwareAntiFraudManagementVar;

    /**
     *  23	内容安全“先审后发”管控
     */
    private Double softwareContentSecurityReviewPublishControlVar;

    /**
     *  24	一键处置（含一键派单、封堵、关停）
     */
    private Double softwareOneClickDisposalVar;

    /**
     *  25	SOAR
     */
    private Double softwareSoarVar;

    /**
     *  26	网络攻击溯源
     */
    private Double softwareNetworkAttackTracingVar;

    /**
     *  27	安全能力中心
     */
    private Double softwareSecurityCapabilityCenterVar;

    /**
     *  28	安全数据中心
     */
    private Double softwareSecurityDataCenterVar;

    /**
     *  29	攻防演练
     */
    private Double softwareAttackDefenseDrillVar;

    /**
     *  30	补丁管理（5GC内生）
     */
    private Double softwarePatchManagementCoreNativeVar;

    /**
     *  31	补丁管理（5GC外挂）
     */
    private Double softwarePatchManagementCoreExternalVar;

    /**
     *  32	漏洞管理（5GC内生）
     */
    private Double softwareVulnerabilityManagementCoreNativeVar;

    /**
     *  33	资产管理（5GC内生）
     */
    private Double softwareAssetManagementCoreNativeVar;

    /**
     *  34	态势感知（5GC内生）
     */
    private Double softwareSituationalAwarenessCoreNativeVar;

    /**
     *  35	UEBA（5GC用户行为分析）
     */
    private Double softwareUebaCoreVar;

    public Double getAbilityFirewallHardwareVar() {
        return abilityFirewallHardwareVar;
    }

    public void setAbilityFirewallHardwareVar(Double abilityFirewallHardwareVar) {
        this.abilityFirewallHardwareVar = abilityFirewallHardwareVar;
    }

    public Double getAbilityFirewallAtomicCapabilityVar() {
        return abilityFirewallAtomicCapabilityVar;
    }

    public void setAbilityFirewallAtomicCapabilityVar(Double abilityFirewallAtomicCapabilityVar) {
        this.abilityFirewallAtomicCapabilityVar = abilityFirewallAtomicCapabilityVar;
    }

    public Double getAbilityIpsHardwareVar() {
        return abilityIpsHardwareVar;
    }

    public void setAbilityIpsHardwareVar(Double abilityIpsHardwareVar) {
        this.abilityIpsHardwareVar = abilityIpsHardwareVar;
    }

    public Double getAbilityIpsAtomicCapabilityVar() {
        return abilityIpsAtomicCapabilityVar;
    }

    public void setAbilityIpsAtomicCapabilityVar(Double abilityIpsAtomicCapabilityVar) {
        this.abilityIpsAtomicCapabilityVar = abilityIpsAtomicCapabilityVar;
    }

    public Double getAbilityWafHardwareVar() {
        return abilityWafHardwareVar;
    }

    public void setAbilityWafHardwareVar(Double abilityWafHardwareVar) {
        this.abilityWafHardwareVar = abilityWafHardwareVar;
    }

    public Double getAbilityWafDomesticHardwareVar() {
        return abilityWafDomesticHardwareVar;
    }

    public void setAbilityWafDomesticHardwareVar(Double abilityWafDomesticHardwareVar) {
        this.abilityWafDomesticHardwareVar = abilityWafDomesticHardwareVar;
    }

    public Double getAbilityWafAtomicCapabilityVar() {
        return abilityWafAtomicCapabilityVar;
    }

    public void setAbilityWafAtomicCapabilityVar(Double abilityWafAtomicCapabilityVar) {
        this.abilityWafAtomicCapabilityVar = abilityWafAtomicCapabilityVar;
    }

    public Double getAbilityWebDynamicDefenseHardwareVar() {
        return abilityWebDynamicDefenseHardwareVar;
    }

    public void setAbilityWebDynamicDefenseHardwareVar(Double abilityWebDynamicDefenseHardwareVar) {
        this.abilityWebDynamicDefenseHardwareVar = abilityWebDynamicDefenseHardwareVar;
    }

    public Double getAbilityWebDynamicDefenseSoftwareVar() {
        return abilityWebDynamicDefenseSoftwareVar;
    }

    public void setAbilityWebDynamicDefenseSoftwareVar(Double abilityWebDynamicDefenseSoftwareVar) {
        this.abilityWebDynamicDefenseSoftwareVar = abilityWebDynamicDefenseSoftwareVar;
    }

    public Double getAbilityTrafficScrubbingVar() {
        return abilityTrafficScrubbingVar;
    }

    public void setAbilityTrafficScrubbingVar(Double abilityTrafficScrubbingVar) {
        this.abilityTrafficScrubbingVar = abilityTrafficScrubbingVar;
    }

    public Double getAbilityFullTrafficAnalysisVar() {
        return abilityFullTrafficAnalysisVar;
    }

    public void setAbilityFullTrafficAnalysisVar(Double abilityFullTrafficAnalysisVar) {
        this.abilityFullTrafficAnalysisVar = abilityFullTrafficAnalysisVar;
    }

    public Double getAbilityBastionHostVar() {
        return abilityBastionHostVar;
    }

    public void setAbilityBastionHostVar(Double abilityBastionHostVar) {
        this.abilityBastionHostVar = abilityBastionHostVar;
    }

    public Double getAbilityLogAuditVar() {
        return abilityLogAuditVar;
    }

    public void setAbilityLogAuditVar(Double abilityLogAuditVar) {
        this.abilityLogAuditVar = abilityLogAuditVar;
    }

    public Double getAbilityEdrVar() {
        return abilityEdrVar;
    }

    public void setAbilityEdrVar(Double abilityEdrVar) {
        this.abilityEdrVar = abilityEdrVar;
    }

    public Double getAbilityHostVulnerabilityScanHardwareVar() {
        return abilityHostVulnerabilityScanHardwareVar;
    }

    public void setAbilityHostVulnerabilityScanHardwareVar(Double abilityHostVulnerabilityScanHardwareVar) {
        this.abilityHostVulnerabilityScanHardwareVar = abilityHostVulnerabilityScanHardwareVar;
    }

    public Double getAbilityHostVulnerabilityScanAtomicCapabilityVar() {
        return abilityHostVulnerabilityScanAtomicCapabilityVar;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityVar(Double abilityHostVulnerabilityScanAtomicCapabilityVar) {
        this.abilityHostVulnerabilityScanAtomicCapabilityVar = abilityHostVulnerabilityScanAtomicCapabilityVar;
    }

    public Double getAbilityWebVulnerabilityScanHardwareVar() {
        return abilityWebVulnerabilityScanHardwareVar;
    }

    public void setAbilityWebVulnerabilityScanHardwareVar(Double abilityWebVulnerabilityScanHardwareVar) {
        this.abilityWebVulnerabilityScanHardwareVar = abilityWebVulnerabilityScanHardwareVar;
    }

    public Double getAbilityWebVulnerabilityScanAtomicCapabilityVar() {
        return abilityWebVulnerabilityScanAtomicCapabilityVar;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityVar(Double abilityWebVulnerabilityScanAtomicCapabilityVar) {
        this.abilityWebVulnerabilityScanAtomicCapabilityVar = abilityWebVulnerabilityScanAtomicCapabilityVar;
    }

    public Double getAbilityContainerSecurityVar() {
        return abilityContainerSecurityVar;
    }

    public void setAbilityContainerSecurityVar(Double abilityContainerSecurityVar) {
        this.abilityContainerSecurityVar = abilityContainerSecurityVar;
    }

    public Double getAbilityContainerSecurityAgentVar() {
        return abilityContainerSecurityAgentVar;
    }

    public void setAbilityContainerSecurityAgentVar(Double abilityContainerSecurityAgentVar) {
        this.abilityContainerSecurityAgentVar = abilityContainerSecurityAgentVar;
    }

    public Double getAbilityWebTamperPreventionVar() {
        return abilityWebTamperPreventionVar;
    }

    public void setAbilityWebTamperPreventionVar(Double abilityWebTamperPreventionVar) {
        this.abilityWebTamperPreventionVar = abilityWebTamperPreventionVar;
    }

    public Double getAbilityWebTamperPreventionAtomicCapabilityVar() {
        return abilityWebTamperPreventionAtomicCapabilityVar;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityVar(Double abilityWebTamperPreventionAtomicCapabilityVar) {
        this.abilityWebTamperPreventionAtomicCapabilityVar = abilityWebTamperPreventionAtomicCapabilityVar;
    }

    public Double getAbilityApiGatewaySecurityVar() {
        return abilityApiGatewaySecurityVar;
    }

    public void setAbilityApiGatewaySecurityVar(Double abilityApiGatewaySecurityVar) {
        this.abilityApiGatewaySecurityVar = abilityApiGatewaySecurityVar;
    }

    public Double getAbilityNetworkDlpVar() {
        return abilityNetworkDlpVar;
    }

    public void setAbilityNetworkDlpVar(Double abilityNetworkDlpVar) {
        this.abilityNetworkDlpVar = abilityNetworkDlpVar;
    }

    public Double getAbilityNetworkDomesticDlpVar() {
        return abilityNetworkDomesticDlpVar;
    }

    public void setAbilityNetworkDomesticDlpVar(Double abilityNetworkDomesticDlpVar) {
        this.abilityNetworkDomesticDlpVar = abilityNetworkDomesticDlpVar;
    }

    public Double getAbilityDynamicDataMaskingVar() {
        return abilityDynamicDataMaskingVar;
    }

    public void setAbilityDynamicDataMaskingVar(Double abilityDynamicDataMaskingVar) {
        this.abilityDynamicDataMaskingVar = abilityDynamicDataMaskingVar;
    }

    public Double getAbilityStaticDataMaskingVar() {
        return abilityStaticDataMaskingVar;
    }

    public void setAbilityStaticDataMaskingVar(Double abilityStaticDataMaskingVar) {
        this.abilityStaticDataMaskingVar = abilityStaticDataMaskingVar;
    }

    public Double getAbilityDatabaseAuditVar() {
        return abilityDatabaseAuditVar;
    }

    public void setAbilityDatabaseAuditVar(Double abilityDatabaseAuditVar) {
        this.abilityDatabaseAuditVar = abilityDatabaseAuditVar;
    }

    public Double getAbilitySignalingFirewallVar() {
        return abilitySignalingFirewallVar;
    }

    public void setAbilitySignalingFirewallVar(Double abilitySignalingFirewallVar) {
        this.abilitySignalingFirewallVar = abilitySignalingFirewallVar;
    }

    public Double getAbilityZeroTrustSdpVar() {
        return abilityZeroTrustSdpVar;
    }

    public void setAbilityZeroTrustSdpVar(Double abilityZeroTrustSdpVar) {
        this.abilityZeroTrustSdpVar = abilityZeroTrustSdpVar;
    }

    public Double getAbilityZeroTrustDomesticSdpVar() {
        return abilityZeroTrustDomesticSdpVar;
    }

    public void setAbilityZeroTrustDomesticSdpVar(Double abilityZeroTrustDomesticSdpVar) {
        this.abilityZeroTrustDomesticSdpVar = abilityZeroTrustDomesticSdpVar;
    }

    public Double getAbilityHoneypotVar() {
        return abilityHoneypotVar;
    }

    public void setAbilityHoneypotVar(Double abilityHoneypotVar) {
        this.abilityHoneypotVar = abilityHoneypotVar;
    }

    public Double getAbilityDomesticHoneypotVar() {
        return abilityDomesticHoneypotVar;
    }

    public void setAbilityDomesticHoneypotVar(Double abilityDomesticHoneypotVar) {
        this.abilityDomesticHoneypotVar = abilityDomesticHoneypotVar;
    }

    public Double getAbilityMicroSegmentationVar() {
        return abilityMicroSegmentationVar;
    }

    public void setAbilityMicroSegmentationVar(Double abilityMicroSegmentationVar) {
        this.abilityMicroSegmentationVar = abilityMicroSegmentationVar;
    }

    public Double getAbilityAbnormalTrafficDetectionForwardingDeviceVar() {
        return abilityAbnormalTrafficDetectionForwardingDeviceVar;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceVar(Double abilityAbnormalTrafficDetectionForwardingDeviceVar) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceVar = abilityAbnormalTrafficDetectionForwardingDeviceVar;
    }

    public Double getAbilityAbnormalTrafficDetectionCollectionDeviceVar() {
        return abilityAbnormalTrafficDetectionCollectionDeviceVar;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceVar(Double abilityAbnormalTrafficDetectionCollectionDeviceVar) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceVar = abilityAbnormalTrafficDetectionCollectionDeviceVar;
    }

    public Double getAbilityAbnormalTrafficDetectionReportDeviceVar() {
        return abilityAbnormalTrafficDetectionReportDeviceVar;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceVar(Double abilityAbnormalTrafficDetectionReportDeviceVar) {
        this.abilityAbnormalTrafficDetectionReportDeviceVar = abilityAbnormalTrafficDetectionReportDeviceVar;
    }

    public Double getAbilityDataEncryptionDecryptionVar() {
        return abilityDataEncryptionDecryptionVar;
    }

    public void setAbilityDataEncryptionDecryptionVar(Double abilityDataEncryptionDecryptionVar) {
        this.abilityDataEncryptionDecryptionVar = abilityDataEncryptionDecryptionVar;
    }

    public Double getAbilityBotnetWormDetectionDisposalDeviceVar() {
        return abilityBotnetWormDetectionDisposalDeviceVar;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceVar(Double abilityBotnetWormDetectionDisposalDeviceVar) {
        this.abilityBotnetWormDetectionDisposalDeviceVar = abilityBotnetWormDetectionDisposalDeviceVar;
    }

    public Double getAbilityBotnetWormDetectionGatewayVar() {
        return abilityBotnetWormDetectionGatewayVar;
    }

    public void setAbilityBotnetWormDetectionGatewayVar(Double abilityBotnetWormDetectionGatewayVar) {
        this.abilityBotnetWormDetectionGatewayVar = abilityBotnetWormDetectionGatewayVar;
    }

    public Double getAbilityBotnetWormDetectionSoftwareUpgradeVar() {
        return abilityBotnetWormDetectionSoftwareUpgradeVar;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeVar(Double abilityBotnetWormDetectionSoftwareUpgradeVar) {
        this.abilityBotnetWormDetectionSoftwareUpgradeVar = abilityBotnetWormDetectionSoftwareUpgradeVar;
    }

    public Double getAbilityIdcispHardwareVar() {
        return abilityIdcispHardwareVar;
    }

    public void setAbilityIdcispHardwareVar(Double abilityIdcispHardwareVar) {
        this.abilityIdcispHardwareVar = abilityIdcispHardwareVar;
    }

    public Double getAbilityIdcispSoftwareVar() {
        return abilityIdcispSoftwareVar;
    }

    public void setAbilityIdcispSoftwareVar(Double abilityIdcispSoftwareVar) {
        this.abilityIdcispSoftwareVar = abilityIdcispSoftwareVar;
    }

    public Double getAbilityMobileDpiHardwareVar() {
        return abilityMobileDpiHardwareVar;
    }

    public void setAbilityMobileDpiHardwareVar(Double abilityMobileDpiHardwareVar) {
        this.abilityMobileDpiHardwareVar = abilityMobileDpiHardwareVar;
    }

    public Double getAbilityMobileDpiSoftwareVar() {
        return abilityMobileDpiSoftwareVar;
    }

    public void setAbilityMobileDpiSoftwareVar(Double abilityMobileDpiSoftwareVar) {
        this.abilityMobileDpiSoftwareVar = abilityMobileDpiSoftwareVar;
    }

    public Double getAbilityFixedNetworkDpiHardwareVar() {
        return abilityFixedNetworkDpiHardwareVar;
    }

    public void setAbilityFixedNetworkDpiHardwareVar(Double abilityFixedNetworkDpiHardwareVar) {
        this.abilityFixedNetworkDpiHardwareVar = abilityFixedNetworkDpiHardwareVar;
    }

    public Double getAbilityFixedNetworkDpiSoftwareVar() {
        return abilityFixedNetworkDpiSoftwareVar;
    }

    public void setAbilityFixedNetworkDpiSoftwareVar(Double abilityFixedNetworkDpiSoftwareVar) {
        this.abilityFixedNetworkDpiSoftwareVar = abilityFixedNetworkDpiSoftwareVar;
    }

    public Double getAbilityMobileMalwareDetectionHardwareVar() {
        return abilityMobileMalwareDetectionHardwareVar;
    }

    public void setAbilityMobileMalwareDetectionHardwareVar(Double abilityMobileMalwareDetectionHardwareVar) {
        this.abilityMobileMalwareDetectionHardwareVar = abilityMobileMalwareDetectionHardwareVar;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareVar() {
        return abilityMobileMalwareDetectionSoftwareVar;
    }

    public void setAbilityMobileMalwareDetectionSoftwareVar(Double abilityMobileMalwareDetectionSoftwareVar) {
        this.abilityMobileMalwareDetectionSoftwareVar = abilityMobileMalwareDetectionSoftwareVar;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareUpgradeVar() {
        return abilityMobileMalwareDetectionSoftwareUpgradeVar;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeVar(Double abilityMobileMalwareDetectionSoftwareUpgradeVar) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeVar = abilityMobileMalwareDetectionSoftwareUpgradeVar;
    }

    public Double getSoftwareMobileInternetLogRetentionCentralizedVar() {
        return softwareMobileInternetLogRetentionCentralizedVar;
    }

    public void setSoftwareMobileInternetLogRetentionCentralizedVar(Double softwareMobileInternetLogRetentionCentralizedVar) {
        this.softwareMobileInternetLogRetentionCentralizedVar = softwareMobileInternetLogRetentionCentralizedVar;
    }

    public Double getSoftwareMobileInternetLogRetentionCustomizedVar() {
        return softwareMobileInternetLogRetentionCustomizedVar;
    }

    public void setSoftwareMobileInternetLogRetentionCustomizedVar(Double softwareMobileInternetLogRetentionCustomizedVar) {
        this.softwareMobileInternetLogRetentionCustomizedVar = softwareMobileInternetLogRetentionCustomizedVar;
    }

    public Double getSoftwareBotnetWormDetectionPlatformTotalPriceVar() {
        return softwareBotnetWormDetectionPlatformTotalPriceVar;
    }

    public void setSoftwareBotnetWormDetectionPlatformTotalPriceVar(Double softwareBotnetWormDetectionPlatformTotalPriceVar) {
        this.softwareBotnetWormDetectionPlatformTotalPriceVar = softwareBotnetWormDetectionPlatformTotalPriceVar;
    }

    public Double getSoftwareIdcispPlatformTotalPriceVar() {
        return softwareIdcispPlatformTotalPriceVar;
    }

    public void setSoftwareIdcispPlatformTotalPriceVar(Double softwareIdcispPlatformTotalPriceVar) {
        this.softwareIdcispPlatformTotalPriceVar = softwareIdcispPlatformTotalPriceVar;
    }

    public Double getSoftwareMobileDpiPlatformTotalPriceVar() {
        return softwareMobileDpiPlatformTotalPriceVar;
    }

    public void setSoftwareMobileDpiPlatformTotalPriceVar(Double softwareMobileDpiPlatformTotalPriceVar) {
        this.softwareMobileDpiPlatformTotalPriceVar = softwareMobileDpiPlatformTotalPriceVar;
    }

    public Double getSoftwareFixedNetworkDpiPlatformTotalPriceVar() {
        return softwareFixedNetworkDpiPlatformTotalPriceVar;
    }

    public void setSoftwareFixedNetworkDpiPlatformTotalPriceVar(Double softwareFixedNetworkDpiPlatformTotalPriceVar) {
        this.softwareFixedNetworkDpiPlatformTotalPriceVar = softwareFixedNetworkDpiPlatformTotalPriceVar;
    }

    public Double getSoftwareMobileMalwareDetectionPlatformTotalPriceVar() {
        return softwareMobileMalwareDetectionPlatformTotalPriceVar;
    }

    public void setSoftwareMobileMalwareDetectionPlatformTotalPriceVar(Double softwareMobileMalwareDetectionPlatformTotalPriceVar) {
        this.softwareMobileMalwareDetectionPlatformTotalPriceVar = softwareMobileMalwareDetectionPlatformTotalPriceVar;
    }

    public Double getSoftwareAssetManagementVar() {
        return softwareAssetManagementVar;
    }

    public void setSoftwareAssetManagementVar(Double softwareAssetManagementVar) {
        this.softwareAssetManagementVar = softwareAssetManagementVar;
    }

    public Double getSoftwareBaselineManagementVar() {
        return softwareBaselineManagementVar;
    }

    public void setSoftwareBaselineManagementVar(Double softwareBaselineManagementVar) {
        this.softwareBaselineManagementVar = softwareBaselineManagementVar;
    }

    public Double getSoftwareVulnerabilityManagementVar() {
        return softwareVulnerabilityManagementVar;
    }

    public void setSoftwareVulnerabilityManagementVar(Double softwareVulnerabilityManagementVar) {
        this.softwareVulnerabilityManagementVar = softwareVulnerabilityManagementVar;
    }

    public Double getSoftwareInternetExposureManagementVar() {
        return softwareInternetExposureManagementVar;
    }

    public void setSoftwareInternetExposureManagementVar(Double softwareInternetExposureManagementVar) {
        this.softwareInternetExposureManagementVar = softwareInternetExposureManagementVar;
    }

    public Double getSoftwareInternalNetworkAssetMappingVar() {
        return softwareInternalNetworkAssetMappingVar;
    }

    public void setSoftwareInternalNetworkAssetMappingVar(Double softwareInternalNetworkAssetMappingVar) {
        this.softwareInternalNetworkAssetMappingVar = softwareInternalNetworkAssetMappingVar;
    }

    public Double getSoftwareAaaaVar() {
        return softwareAaaaVar;
    }

    public void setSoftwareAaaaVar(Double softwareAaaaVar) {
        this.softwareAaaaVar = softwareAaaaVar;
    }

    public Double getSoftwareAppReleaseDetectionVar() {
        return softwareAppReleaseDetectionVar;
    }

    public void setSoftwareAppReleaseDetectionVar(Double softwareAppReleaseDetectionVar) {
        this.softwareAppReleaseDetectionVar = softwareAppReleaseDetectionVar;
    }

    public Double getSoftwareDataAssetManagementVar() {
        return softwareDataAssetManagementVar;
    }

    public void setSoftwareDataAssetManagementVar(Double softwareDataAssetManagementVar) {
        this.softwareDataAssetManagementVar = softwareDataAssetManagementVar;
    }

    public Double getSoftwarePasswordServiceManagementVar() {
        return softwarePasswordServiceManagementVar;
    }

    public void setSoftwarePasswordServiceManagementVar(Double softwarePasswordServiceManagementVar) {
        this.softwarePasswordServiceManagementVar = softwarePasswordServiceManagementVar;
    }

    public Double getSoftwareThreatIntelligenceVar() {
        return softwareThreatIntelligenceVar;
    }

    public void setSoftwareThreatIntelligenceVar(Double softwareThreatIntelligenceVar) {
        this.softwareThreatIntelligenceVar = softwareThreatIntelligenceVar;
    }

    public Double getSoftwareNetworkSecuritySituationalAwarenessVar() {
        return softwareNetworkSecuritySituationalAwarenessVar;
    }

    public void setSoftwareNetworkSecuritySituationalAwarenessVar(Double softwareNetworkSecuritySituationalAwarenessVar) {
        this.softwareNetworkSecuritySituationalAwarenessVar = softwareNetworkSecuritySituationalAwarenessVar;
    }

    public Double getSoftwareDataSecuritySituationalAwarenessVar() {
        return softwareDataSecuritySituationalAwarenessVar;
    }

    public void setSoftwareDataSecuritySituationalAwarenessVar(Double softwareDataSecuritySituationalAwarenessVar) {
        this.softwareDataSecuritySituationalAwarenessVar = softwareDataSecuritySituationalAwarenessVar;
    }

    public Double getSoftwareWebsiteFilingMonitoringVar() {
        return softwareWebsiteFilingMonitoringVar;
    }

    public void setSoftwareWebsiteFilingMonitoringVar(Double softwareWebsiteFilingMonitoringVar) {
        this.softwareWebsiteFilingMonitoringVar = softwareWebsiteFilingMonitoringVar;
    }

    public Double getSoftwareHarmfulInformationMonitoringVar() {
        return softwareHarmfulInformationMonitoringVar;
    }

    public void setSoftwareHarmfulInformationMonitoringVar(Double softwareHarmfulInformationMonitoringVar) {
        this.softwareHarmfulInformationMonitoringVar = softwareHarmfulInformationMonitoringVar;
    }

    public Double getSoftwareAntiFraudManagementVar() {
        return softwareAntiFraudManagementVar;
    }

    public void setSoftwareAntiFraudManagementVar(Double softwareAntiFraudManagementVar) {
        this.softwareAntiFraudManagementVar = softwareAntiFraudManagementVar;
    }

    public Double getSoftwareContentSecurityReviewPublishControlVar() {
        return softwareContentSecurityReviewPublishControlVar;
    }

    public void setSoftwareContentSecurityReviewPublishControlVar(Double softwareContentSecurityReviewPublishControlVar) {
        this.softwareContentSecurityReviewPublishControlVar = softwareContentSecurityReviewPublishControlVar;
    }

    public Double getSoftwareOneClickDisposalVar() {
        return softwareOneClickDisposalVar;
    }

    public void setSoftwareOneClickDisposalVar(Double softwareOneClickDisposalVar) {
        this.softwareOneClickDisposalVar = softwareOneClickDisposalVar;
    }

    public Double getSoftwareSoarVar() {
        return softwareSoarVar;
    }

    public void setSoftwareSoarVar(Double softwareSoarVar) {
        this.softwareSoarVar = softwareSoarVar;
    }

    public Double getSoftwareNetworkAttackTracingVar() {
        return softwareNetworkAttackTracingVar;
    }

    public void setSoftwareNetworkAttackTracingVar(Double softwareNetworkAttackTracingVar) {
        this.softwareNetworkAttackTracingVar = softwareNetworkAttackTracingVar;
    }

    public Double getSoftwareSecurityCapabilityCenterVar() {
        return softwareSecurityCapabilityCenterVar;
    }

    public void setSoftwareSecurityCapabilityCenterVar(Double softwareSecurityCapabilityCenterVar) {
        this.softwareSecurityCapabilityCenterVar = softwareSecurityCapabilityCenterVar;
    }

    public Double getSoftwareSecurityDataCenterVar() {
        return softwareSecurityDataCenterVar;
    }

    public void setSoftwareSecurityDataCenterVar(Double softwareSecurityDataCenterVar) {
        this.softwareSecurityDataCenterVar = softwareSecurityDataCenterVar;
    }

    public Double getSoftwareAttackDefenseDrillVar() {
        return softwareAttackDefenseDrillVar;
    }

    public void setSoftwareAttackDefenseDrillVar(Double softwareAttackDefenseDrillVar) {
        this.softwareAttackDefenseDrillVar = softwareAttackDefenseDrillVar;
    }

    public Double getSoftwarePatchManagementCoreNativeVar() {
        return softwarePatchManagementCoreNativeVar;
    }

    public void setSoftwarePatchManagementCoreNativeVar(Double softwarePatchManagementCoreNativeVar) {
        this.softwarePatchManagementCoreNativeVar = softwarePatchManagementCoreNativeVar;
    }

    public Double getSoftwarePatchManagementCoreExternalVar() {
        return softwarePatchManagementCoreExternalVar;
    }

    public void setSoftwarePatchManagementCoreExternalVar(Double softwarePatchManagementCoreExternalVar) {
        this.softwarePatchManagementCoreExternalVar = softwarePatchManagementCoreExternalVar;
    }

    public Double getSoftwareVulnerabilityManagementCoreNativeVar() {
        return softwareVulnerabilityManagementCoreNativeVar;
    }

    public void setSoftwareVulnerabilityManagementCoreNativeVar(Double softwareVulnerabilityManagementCoreNativeVar) {
        this.softwareVulnerabilityManagementCoreNativeVar = softwareVulnerabilityManagementCoreNativeVar;
    }

    public Double getSoftwareAssetManagementCoreNativeVar() {
        return softwareAssetManagementCoreNativeVar;
    }

    public void setSoftwareAssetManagementCoreNativeVar(Double softwareAssetManagementCoreNativeVar) {
        this.softwareAssetManagementCoreNativeVar = softwareAssetManagementCoreNativeVar;
    }

    public Double getSoftwareSituationalAwarenessCoreNativeVar() {
        return softwareSituationalAwarenessCoreNativeVar;
    }

    public void setSoftwareSituationalAwarenessCoreNativeVar(Double softwareSituationalAwarenessCoreNativeVar) {
        this.softwareSituationalAwarenessCoreNativeVar = softwareSituationalAwarenessCoreNativeVar;
    }

    public Double getSoftwareUebaCoreVar() {
        return softwareUebaCoreVar;
    }

    public void setSoftwareUebaCoreVar(Double softwareUebaCoreVar) {
        this.softwareUebaCoreVar = softwareUebaCoreVar;
    }

    @Override
    public String toString() {
        return "BenefitInternalConstructionExtensionDO{" +
            "abilityFirewallHardwareVar=" + abilityFirewallHardwareVar +
            ", abilityFirewallAtomicCapabilityVar=" + abilityFirewallAtomicCapabilityVar +
            ", abilityIpsHardwareVar=" + abilityIpsHardwareVar +
            ", abilityIpsAtomicCapabilityVar=" + abilityIpsAtomicCapabilityVar +
            ", abilityWafHardwareVar=" + abilityWafHardwareVar +
            ", abilityWafDomesticHardwareVar=" + abilityWafDomesticHardwareVar +
            ", abilityWafAtomicCapabilityVar=" + abilityWafAtomicCapabilityVar +
            ", abilityWebDynamicDefenseHardwareVar=" + abilityWebDynamicDefenseHardwareVar +
            ", abilityWebDynamicDefenseSoftwareVar=" + abilityWebDynamicDefenseSoftwareVar +
            ", abilityTrafficScrubbingVar=" + abilityTrafficScrubbingVar +
            ", abilityFullTrafficAnalysisVar=" + abilityFullTrafficAnalysisVar +
            ", abilityBastionHostVar=" + abilityBastionHostVar +
            ", abilityLogAuditVar=" + abilityLogAuditVar +
            ", abilityEdrVar=" + abilityEdrVar +
            ", abilityHostVulnerabilityScanHardwareVar=" + abilityHostVulnerabilityScanHardwareVar +
            ", abilityHostVulnerabilityScanAtomicCapabilityVar=" + abilityHostVulnerabilityScanAtomicCapabilityVar +
            ", abilityWebVulnerabilityScanHardwareVar=" + abilityWebVulnerabilityScanHardwareVar +
            ", abilityWebVulnerabilityScanAtomicCapabilityVar=" + abilityWebVulnerabilityScanAtomicCapabilityVar +
            ", abilityContainerSecurityVar=" + abilityContainerSecurityVar +
            ", abilityContainerSecurityAgentVar=" + abilityContainerSecurityAgentVar +
            ", abilityWebTamperPreventionVar=" + abilityWebTamperPreventionVar +
            ", abilityWebTamperPreventionAtomicCapabilityVar=" + abilityWebTamperPreventionAtomicCapabilityVar +
            ", abilityApiGatewaySecurityVar=" + abilityApiGatewaySecurityVar +
            ", abilityNetworkDlpVar=" + abilityNetworkDlpVar +
            ", abilityNetworkDomesticDlpVar=" + abilityNetworkDomesticDlpVar +
            ", abilityDynamicDataMaskingVar=" + abilityDynamicDataMaskingVar +
            ", abilityStaticDataMaskingVar=" + abilityStaticDataMaskingVar +
            ", abilityDatabaseAuditVar=" + abilityDatabaseAuditVar +
            ", abilitySignalingFirewallVar=" + abilitySignalingFirewallVar +
            ", abilityZeroTrustSdpVar=" + abilityZeroTrustSdpVar +
            ", abilityZeroTrustDomesticSdpVar=" + abilityZeroTrustDomesticSdpVar +
            ", abilityHoneypotVar=" + abilityHoneypotVar +
            ", abilityDomesticHoneypotVar=" + abilityDomesticHoneypotVar +
            ", abilityMicroSegmentationVar=" + abilityMicroSegmentationVar +
            ", abilityAbnormalTrafficDetectionForwardingDeviceVar=" + abilityAbnormalTrafficDetectionForwardingDeviceVar +
            ", abilityAbnormalTrafficDetectionCollectionDeviceVar=" + abilityAbnormalTrafficDetectionCollectionDeviceVar +
            ", abilityAbnormalTrafficDetectionReportDeviceVar=" + abilityAbnormalTrafficDetectionReportDeviceVar +
            ", abilityDataEncryptionDecryptionVar=" + abilityDataEncryptionDecryptionVar +
            ", abilityBotnetWormDetectionDisposalDeviceVar=" + abilityBotnetWormDetectionDisposalDeviceVar +
            ", abilityBotnetWormDetectionGatewayVar=" + abilityBotnetWormDetectionGatewayVar +
            ", abilityBotnetWormDetectionSoftwareUpgradeVar=" + abilityBotnetWormDetectionSoftwareUpgradeVar +
            ", abilityIdcispHardwareVar=" + abilityIdcispHardwareVar +
            ", abilityIdcispSoftwareVar=" + abilityIdcispSoftwareVar +
            ", abilityMobileDpiHardwareVar=" + abilityMobileDpiHardwareVar +
            ", abilityMobileDpiSoftwareVar=" + abilityMobileDpiSoftwareVar +
            ", abilityFixedNetworkDpiHardwareVar=" + abilityFixedNetworkDpiHardwareVar +
            ", abilityFixedNetworkDpiSoftwareVar=" + abilityFixedNetworkDpiSoftwareVar +
            ", abilityMobileMalwareDetectionHardwareVar=" + abilityMobileMalwareDetectionHardwareVar +
            ", abilityMobileMalwareDetectionSoftwareVar=" + abilityMobileMalwareDetectionSoftwareVar +
            ", abilityMobileMalwareDetectionSoftwareUpgradeVar=" + abilityMobileMalwareDetectionSoftwareUpgradeVar +
            ", softwareMobileInternetLogRetentionCentralizedVar=" + softwareMobileInternetLogRetentionCentralizedVar +
            ", softwareMobileInternetLogRetentionCustomizedVar=" + softwareMobileInternetLogRetentionCustomizedVar +
            ", softwareBotnetWormDetectionPlatformTotalPriceVar=" + softwareBotnetWormDetectionPlatformTotalPriceVar +
            ", softwareIdcispPlatformTotalPriceVar=" + softwareIdcispPlatformTotalPriceVar +
            ", softwareMobileDpiPlatformTotalPriceVar=" + softwareMobileDpiPlatformTotalPriceVar +
            ", softwareFixedNetworkDpiPlatformTotalPriceVar=" + softwareFixedNetworkDpiPlatformTotalPriceVar +
            ", softwareMobileMalwareDetectionPlatformTotalPriceVar=" + softwareMobileMalwareDetectionPlatformTotalPriceVar +
            ", softwareAssetManagementVar=" + softwareAssetManagementVar +
            ", softwareBaselineManagementVar=" + softwareBaselineManagementVar +
            ", softwareVulnerabilityManagementVar=" + softwareVulnerabilityManagementVar +
            ", softwareInternetExposureManagementVar=" + softwareInternetExposureManagementVar +
            ", softwareInternalNetworkAssetMappingVar=" + softwareInternalNetworkAssetMappingVar +
            ", softwareAaaaVar=" + softwareAaaaVar +
            ", softwareAppReleaseDetectionVar=" + softwareAppReleaseDetectionVar +
            ", softwareDataAssetManagementVar=" + softwareDataAssetManagementVar +
            ", softwarePasswordServiceManagementVar=" + softwarePasswordServiceManagementVar +
            ", softwareThreatIntelligenceVar=" + softwareThreatIntelligenceVar +
            ", softwareNetworkSecuritySituationalAwarenessVar=" + softwareNetworkSecuritySituationalAwarenessVar +
            ", softwareDataSecuritySituationalAwarenessVar=" + softwareDataSecuritySituationalAwarenessVar +
            ", softwareWebsiteFilingMonitoringVar=" + softwareWebsiteFilingMonitoringVar +
            ", softwareHarmfulInformationMonitoringVar=" + softwareHarmfulInformationMonitoringVar +
            ", softwareAntiFraudManagementVar=" + softwareAntiFraudManagementVar +
            ", softwareContentSecurityReviewPublishControlVar=" + softwareContentSecurityReviewPublishControlVar +
            ", softwareOneClickDisposalVar=" + softwareOneClickDisposalVar +
            ", softwareSoarVar=" + softwareSoarVar +
            ", softwareNetworkAttackTracingVar=" + softwareNetworkAttackTracingVar +
            ", softwareSecurityCapabilityCenterVar=" + softwareSecurityCapabilityCenterVar +
            ", softwareSecurityDataCenterVar=" + softwareSecurityDataCenterVar +
            ", softwareAttackDefenseDrillVar=" + softwareAttackDefenseDrillVar +
            ", softwarePatchManagementCoreNativeVar=" + softwarePatchManagementCoreNativeVar +
            ", softwarePatchManagementCoreExternalVar=" + softwarePatchManagementCoreExternalVar +
            ", softwareVulnerabilityManagementCoreNativeVar=" + softwareVulnerabilityManagementCoreNativeVar +
            ", softwareAssetManagementCoreNativeVar=" + softwareAssetManagementCoreNativeVar +
            ", softwareSituationalAwarenessCoreNativeVar=" + softwareSituationalAwarenessCoreNativeVar +
            ", softwareUebaCoreVar=" + softwareUebaCoreVar +
            "} " + super.toString();
    }
}
