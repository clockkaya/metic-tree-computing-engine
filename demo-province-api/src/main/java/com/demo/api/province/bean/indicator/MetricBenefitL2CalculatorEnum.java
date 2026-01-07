package com.sama.api.ledger.bean.indicator;

import cn.hutool.core.util.StrUtil;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.structure.BenefitColumnTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;

/**
 * @author: huxh
 * @description:
 * 用 dataExtractor 代替原逻辑：
 * internalCol.setAbilityFirewall(new TwoCols(rawData.getAbilityFirewallMixedThroughput(), rawData.getAbilityFirewallInvestment()));
 * internalCol.setAbilityIps(new TwoCols(rawData.getAbilityIpsApplicationLayerDefenseCapacity(), rawData.getAbilityIpsInvestment()));
 * 等
 * @datetime: 2025/9/2 16:55
 */
public enum MetricBenefitL2CalculatorEnum {

    // Non-static method cannot be referenced from a static context
    // (BenefitInternalConstructionReferenceBO obj) -> obj.getAbilityFirewall(),
    // (BenefitInternalConstructionReferenceBO obj, BenefitInternalConstructionReferenceBO.TwoCols value) -> obj.setAbilityFirewall(value))

    ABILITY_FIREWALL_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        1,
        V_FIREWALL_HARDWARE,
        "防火墙-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityFirewallHardwareInv(), internalDO.getAbilityFirewallHardwareInc()),
        BenefitInternalConstructionDO::getAbilityFirewallHardwareVendor,
        BenefitInternalConstructionDO::setAbilityFirewallHardwareVar
    ),

    ABILITY_FIREWALL_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        2,
        V_FIREWALL_ATOMIC_CAPABILITY,
        "防火墙-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityFirewallAtomicCapabilityInv(), internalDO.getAbilityFirewallAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityFirewallAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityFirewallAtomicCapabilityVar
    ),

    ABILITY_IPS_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        3,
        V_IPS_HARDWARE,
        "IPS-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityIpsHardwareInv(), internalDO.getAbilityIpsHardwareInc()),
        BenefitInternalConstructionDO::getAbilityIpsHardwareVendor,
        BenefitInternalConstructionDO::setAbilityIpsHardwareVar
    ),

    ABILITY_IPS_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        4,
        V_IPS_ATOMIC_CAPABILITY,
        "IPS-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityIpsAtomicCapabilityInv(), internalDO.getAbilityIpsAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityIpsAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityIpsAtomicCapabilityVar
    ),

    ABILITY_WAF_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        5,
        V_WAF_HARDWARE,
        "Web防御（WAF）-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWafHardwareInv(), internalDO.getAbilityWafHardwareInc()),
        BenefitInternalConstructionDO::getAbilityWafHardwareVendor,
        BenefitInternalConstructionDO::setAbilityWafHardwareVar
    ),

    ABILITY_WAF_DOMESTIC_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        6,
        V_WAF_DOMESTIC_HARDWARE,
        "Web防御（WAF）-硬件-国产化设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWafDomesticHardwareInv(), internalDO.getAbilityWafDomesticHardwareInc()),
        BenefitInternalConstructionDO::getAbilityWafDomesticHardwareVendor,
        BenefitInternalConstructionDO::setAbilityWafDomesticHardwareVar
    ),

    ABILITY_WAF_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        7,
        V_WAF_ATOMIC_CAPABILITY,
        "Web防御（WAF）-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWafAtomicCapabilityInv(), internalDO.getAbilityWafAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityWafAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityWafAtomicCapabilityVar
    ),

    ABILITY_WEB_DYNAMIC_DEFENSE_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        8,
        V_WEB_DYNAMIC_DEFENSE_HARDWARE,
        "Web防御（动态防护）-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebDynamicDefenseHardwareInv(), internalDO.getAbilityWebDynamicDefenseHardwareInc()),
        BenefitInternalConstructionDO::getAbilityWebDynamicDefenseHardwareVendor,
        BenefitInternalConstructionDO::setAbilityWebDynamicDefenseHardwareVar
    ),

    ABILITY_WEB_DYNAMIC_DEFENSE_SOFTWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        9,
        V_WEB_DYNAMIC_DEFENSE_SOFTWARE,
        "Web防御（动态防护）-软件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebDynamicDefenseSoftwareInv(), internalDO.getAbilityWebDynamicDefenseSoftwareInc()),
        BenefitInternalConstructionDO::getAbilityWebDynamicDefenseSoftwareVendor,
        BenefitInternalConstructionDO::setAbilityWebDynamicDefenseSoftwareVar
    ),

    ABILITY_TRAFFIC_SCRUBBING(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        10,
        V_TRAFFIC_SCRUBBING,
        "流量清洗（抗DDoS）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityTrafficScrubbingInv(), internalDO.getAbilityTrafficScrubbingInc()),
        BenefitInternalConstructionDO::getAbilityTrafficScrubbingVendor,
        BenefitInternalConstructionDO::setAbilityTrafficScrubbingVar
    ),

    ABILITY_FULL_TRAFFIC_ANALYSIS(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        11,
        V_FULL_TRAFFIC_ANALYSIS,
        "流量分析（全流量分析）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityFullTrafficAnalysisInv(), internalDO.getAbilityFullTrafficAnalysisInc()),
        BenefitInternalConstructionDO::getAbilityFullTrafficAnalysisVendor,
        BenefitInternalConstructionDO::setAbilityFullTrafficAnalysisVar
    ),

    ABILITY_BASTION_HOST(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        12,
        V_BASTION_HOST,
        "堡垒机",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityBastionHostInv(), internalDO.getAbilityBastionHostInc()),
        BenefitInternalConstructionDO::getAbilityBastionHostVendor,
        BenefitInternalConstructionDO::setAbilityBastionHostVar
    ),

    ABILITY_LOG_AUDIT(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        13,
        V_LOG_AUDIT,
        "日志审计",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityLogAuditInv(), internalDO.getAbilityLogAuditInc()),
        BenefitInternalConstructionDO::getAbilityLogAuditVendor,
        BenefitInternalConstructionDO::setAbilityLogAuditVar
    ),

    ABILITY_EDR(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        14,
        V_EDR,
        "EDR（含防病毒）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityEdrInv(), internalDO.getAbilityEdrInc()),
        BenefitInternalConstructionDO::getAbilityEdrVendor,
        BenefitInternalConstructionDO::setAbilityEdrVar
    ),

    ABILITY_HOST_VULNERABILITY_SCAN_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        15,
        V_HOST_VULNERABILITY_SCAN_HARDWARE,
        "漏洞扫描（主机）-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityHostVulnerabilityScanHardwareInv(), internalDO.getAbilityHostVulnerabilityScanHardwareInc()),
        BenefitInternalConstructionDO::getAbilityHostVulnerabilityScanHardwareVendor,
        BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanHardwareVar
    ),

    ABILITY_HOST_VULNERABILITY_SCAN_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        16,
        V_HOST_VULNERABILITY_SCAN_ATOMIC_CAPABILITY,
        "漏洞扫描（主机）-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityHostVulnerabilityScanAtomicCapabilityInv(), internalDO.getAbilityHostVulnerabilityScanAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityHostVulnerabilityScanAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanAtomicCapabilityVar
    ),

    ABILITY_WEB_VULNERABILITY_SCAN_HARDWARE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        17,
        V_WEB_VULNERABILITY_SCAN_HARDWARE,
        "漏洞扫描（Web）-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebVulnerabilityScanHardwareInv(), internalDO.getAbilityWebVulnerabilityScanHardwareInc()),
        BenefitInternalConstructionDO::getAbilityWebVulnerabilityScanHardwareVendor,
        BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanHardwareVar
    ),

    ABILITY_WEB_VULNERABILITY_SCAN_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        18,
        V_WEB_VULNERABILITY_SCAN_ATOMIC_CAPABILITY,
        "漏洞扫描（Web）-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebVulnerabilityScanAtomicCapabilityInv(), internalDO.getAbilityWebVulnerabilityScanAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityWebVulnerabilityScanAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanAtomicCapabilityVar
    ),

    ABILITY_CONTAINER_SECURITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        19,
        V_CONTAINER_SECURITY,
        "容器安全",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityContainerSecurityInv(), internalDO.getAbilityContainerSecurityInc()),
        BenefitInternalConstructionDO::getAbilityContainerSecurityVendor,
        BenefitInternalConstructionDO::setAbilityContainerSecurityVar
    ),

    ABILITY_CONTAINER_SECURITY_AGENT(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        20,
        V_CONTAINER_SECURITY_AGENT,
        "容器安全-纯agent",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityContainerSecurityAgentInv(), internalDO.getAbilityContainerSecurityAgentInc()),
        BenefitInternalConstructionDO::getAbilityContainerSecurityAgentVendor,
        BenefitInternalConstructionDO::setAbilityContainerSecurityAgentVar
    ),

    ABILITY_WEB_TAMPER_PREVENTION(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        21,
        V_WEB_TAMPER_PREVENTION,
        "网页防篡改-非池化",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebTamperPreventionInv(), internalDO.getAbilityWebTamperPreventionInc()),
        BenefitInternalConstructionDO::getAbilityWebTamperPreventionVendor,
        BenefitInternalConstructionDO::setAbilityWebTamperPreventionVar
    ),

    ABILITY_WEB_TAMPER_PREVENTION_ATOMIC_CAPABILITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        22,
        V_WEB_TAMPER_PREVENTION_ATOMIC_CAPABILITY,
        "网页防篡改-原子能力",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityWebTamperPreventionAtomicCapabilityInv(), internalDO.getAbilityWebTamperPreventionAtomicCapabilityInc()),
        BenefitInternalConstructionDO::getAbilityWebTamperPreventionAtomicCapabilityVendor,
        BenefitInternalConstructionDO::setAbilityWebTamperPreventionAtomicCapabilityVar
    ),

    ABILITY_API_GATEWAY_SECURITY(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        23,
        V_API_GATEWAY_SECURITY,
        "接口安全管控（API网关）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityApiGatewaySecurityInv(), internalDO.getAbilityApiGatewaySecurityInc()),
        BenefitInternalConstructionDO::getAbilityApiGatewaySecurityVendor,
        BenefitInternalConstructionDO::setAbilityApiGatewaySecurityVar
    ),

    ABILITY_NETWORK_DLP(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        24,
        V_NETWORK_DLP,
        "数据防泄漏（网络侧）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityNetworkDlpInv(), internalDO.getAbilityNetworkDlpInc()),
        BenefitInternalConstructionDO::getAbilityNetworkDlpVendor,
        BenefitInternalConstructionDO::setAbilityNetworkDlpVar
    ),

    ABILITY_NETWORK_DOMESTIC_DLP(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        25,
        V_NETWORK_DOMESTIC_DLP,
        "数据防泄漏（网络侧）-国产化设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityNetworkDomesticDlpInv(), internalDO.getAbilityNetworkDomesticDlpInc()),
        BenefitInternalConstructionDO::getAbilityNetworkDomesticDlpVendor,
        BenefitInternalConstructionDO::setAbilityNetworkDomesticDlpVar
    ),

    ABILITY_DYNAMIC_DATA_MASKING(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        26,
        V_DYNAMIC_DATA_MASKING,
        "数据脱敏-动态",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityDynamicDataMaskingInv(), internalDO.getAbilityDynamicDataMaskingInc()),
        BenefitInternalConstructionDO::getAbilityDynamicDataMaskingVendor,
        BenefitInternalConstructionDO::setAbilityDynamicDataMaskingVar
    ),

    ABILITY_STATIC_DATA_MASKING(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        27,
        V_STATIC_DATA_MASKING,
        "数据脱敏-静态",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityStaticDataMaskingInv(), internalDO.getAbilityStaticDataMaskingInc()),
        BenefitInternalConstructionDO::getAbilityStaticDataMaskingVendor,
        BenefitInternalConstructionDO::setAbilityStaticDataMaskingVar
    ),

    ABILITY_DATABASE_AUDIT(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        28,
        V_DATABASE_AUDIT,
        "数据库审计",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityDatabaseAuditInv(), internalDO.getAbilityDatabaseAuditInc()),
        BenefitInternalConstructionDO::getAbilityDatabaseAuditVendor,
        BenefitInternalConstructionDO::setAbilityDatabaseAuditVar
    ),

    ABILITY_SIGNALING_FIREWALL(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        29,
        V_SIGNALING_FIREWALL,
        "信令防火墙 C-IWF（5GC）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilitySignalingFirewallInv(), internalDO.getAbilitySignalingFirewallInc()),
        BenefitInternalConstructionDO::getAbilitySignalingFirewallVendor,
        BenefitInternalConstructionDO::setAbilitySignalingFirewallVar
    ),

    ABILITY_ZERO_TRUST_SDP(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        30,
        V_ZERO_TRUST_SDP,
        "零信任（SDP）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityZeroTrustSdpInv(), internalDO.getAbilityZeroTrustSdpInc()),
        BenefitInternalConstructionDO::getAbilityZeroTrustSdpVendor,
        BenefitInternalConstructionDO::setAbilityZeroTrustSdpVar
    ),

    ABILITY_ZERO_TRUST_DOMESTIC_SDP(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        31,
        V_ZERO_TRUST_DOMESTIC_SDP,
        "零信任（SDP）-国产化设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityZeroTrustDomesticSdpInv(), internalDO.getAbilityZeroTrustDomesticSdpInc()),
        BenefitInternalConstructionDO::getAbilityZeroTrustDomesticSdpVendor,
        BenefitInternalConstructionDO::setAbilityZeroTrustDomesticSdpVar
    ),

    ABILITY_HONEYPOT(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        32,
        V_HONEYPOT,
        "蜜罐",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityHoneypotInv(), internalDO.getAbilityHoneypotInc()),
        BenefitInternalConstructionDO::getAbilityHoneypotVendor,
        BenefitInternalConstructionDO::setAbilityHoneypotVar
    ),

    ABILITY_DOMESTIC_HONEYPOT(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        33,
        V_DOMESTIC_HONEYPOT,
        "蜜罐-国产化设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityDomesticHoneypotInv(), internalDO.getAbilityDomesticHoneypotInc()),
        BenefitInternalConstructionDO::getAbilityDomesticHoneypotVendor,
        BenefitInternalConstructionDO::setAbilityDomesticHoneypotVar
    ),

    ABILITY_MICRO_SEGMENTATION(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        34,
        V_MICRO_SEGMENTATION,
        "微隔离",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMicroSegmentationInv(), internalDO.getAbilityMicroSegmentationInc()),
        BenefitInternalConstructionDO::getAbilityMicroSegmentationVendor,
        BenefitInternalConstructionDO::setAbilityMicroSegmentationVar
    ),

    ABILITY_ABNORMAL_TRAFFIC_DETECTION_FORWARDING_DEVICE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        35,
        V_ABNORMAL_TRAFFIC_DETECTION_FORWARDING_DEVICE,
        "异常流量检测-流量转发设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityAbnormalTrafficDetectionForwardingDeviceInv(), internalDO.getAbilityAbnormalTrafficDetectionForwardingDeviceInc()),
        BenefitInternalConstructionDO::getAbilityAbnormalTrafficDetectionForwardingDeviceVendor,
        BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionForwardingDeviceVar
    ),

    ABILITY_ABNORMAL_TRAFFIC_DETECTION_COLLECTION_DEVICE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        36,
        V_ABNORMAL_TRAFFIC_DETECTION_COLLECTION_DEVICE,
        "异常流量检测-流量采集设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityAbnormalTrafficDetectionCollectionDeviceInv(), internalDO.getAbilityAbnormalTrafficDetectionCollectionDeviceInc()),
        BenefitInternalConstructionDO::getAbilityAbnormalTrafficDetectionCollectionDeviceVendor,
        BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionCollectionDeviceVar
    ),

    ABILITY_ABNORMAL_TRAFFIC_DETECTION_REPORT_DEVICE(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        37,
        V_ABNORMAL_TRAFFIC_DETECTION_REPORT_DEVICE,
        "异常流量检测-报表处理设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityAbnormalTrafficDetectionReportDeviceInv(), internalDO.getAbilityAbnormalTrafficDetectionReportDeviceInc()),
        BenefitInternalConstructionDO::getAbilityAbnormalTrafficDetectionReportDeviceVendor,
        BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionReportDeviceVar
    ),

    ABILITY_DATA_ENCRYPTION_DECRYPTION(
        MetricBenefitL1AssessmentEnum.DEFENSE_IN_DEPTH_CAPABILITY,
        38,
        V_DATA_ENCRYPTION_DECRYPTION,
        "数据加解密（5GC）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityDataEncryptionDecryptionInv(), internalDO.getAbilityDataEncryptionDecryptionInc()),
        BenefitInternalConstructionDO::getAbilityDataEncryptionDecryptionVendor,
        BenefitInternalConstructionDO::setAbilityDataEncryptionDecryptionVar
    ),

    ABILITY_BOTNET_WORM_DETECTION_DISPOSAL_DEVICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        39,
        V_BOTNET_WORM_DETECTION_DISPOSAL_DEVICE,
        "僵木蠕监测-监测处置设备",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityBotnetWormDetectionDisposalDeviceInv(), internalDO.getAbilityBotnetWormDetectionDisposalDeviceInc()),
        BenefitInternalConstructionDO::getAbilityBotnetWormDetectionDisposalDeviceVendor,
        BenefitInternalConstructionDO::setAbilityBotnetWormDetectionDisposalDeviceVar
    ),

    ABILITY_BOTNET_WORM_DETECTION_GATEWAY(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        40,
        V_BOTNET_WORM_DETECTION_GATEWAY,
        "僵木蠕监测-接口转发网关",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityBotnetWormDetectionGatewayInv(), internalDO.getAbilityBotnetWormDetectionGatewayInc()),
        BenefitInternalConstructionDO::getAbilityBotnetWormDetectionGatewayVendor,
        BenefitInternalConstructionDO::setAbilityBotnetWormDetectionGatewayVar
    ),

    ABILITY_BOTNET_WORM_DETECTION_SOFTWARE_UPGRADE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        41,
        V_BOTNET_WORM_DETECTION_SOFTWARE_UPGRADE,
        "僵木蠕监测-监测处置设备考核软件升级",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityBotnetWormDetectionSoftwareUpgradeInv(), internalDO.getAbilityBotnetWormDetectionSoftwareUpgradeInc()),
        BenefitInternalConstructionDO::getAbilityBotnetWormDetectionSoftwareUpgradeVendor,
        BenefitInternalConstructionDO::setAbilityBotnetWormDetectionSoftwareUpgradeVar
    ),

    ABILITY_IDCISP_HARDWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        42,
        V_IDCISP_HARDWARE,
        "IDCISP-硬件（分流器+全量服务器）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityIdcispHardwareInv(), internalDO.getAbilityIdcispHardwareInc()),
        BenefitInternalConstructionDO::getAbilityIdcispHardwareVendor,
        BenefitInternalConstructionDO::setAbilityIdcispHardwareVar
    ),

    // 特殊
    ABILITY_IDCISP_SOFTWARE_SPECIAL(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        43,
        V_IDCISP_SOFTWARE,
        "IDCISP-软件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityIdcispSoftwareInv()),
        BenefitInternalConstructionDO::getAbilityIdcispSoftwareVendor,
        BenefitInternalConstructionDO::setAbilityIdcispSoftwareVar
    ),

    ABILITY_MOBILE_DPI_HARDWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        44,
        V_MOBILE_DPI_HARDWARE,
        "移动DPI-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMobileDpiHardwareInv(), internalDO.getAbilityMobileDpiHardwareInc()),
        BenefitInternalConstructionDO::getAbilityMobileDpiHardwareVendor,
        BenefitInternalConstructionDO::setAbilityMobileDpiHardwareVar
    ),

    ABILITY_MOBILE_DPI_SOFTWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        45,
        V_MOBILE_DPI_SOFTWARE,
        "移动DPI-软件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMobileDpiSoftwareInv(), internalDO.getAbilityMobileDpiSoftwareInc()),
        BenefitInternalConstructionDO::getAbilityMobileDpiSoftwareVendor,
        BenefitInternalConstructionDO::setAbilityMobileDpiSoftwareVar
    ),

    ABILITY_FIXED_NETWORK_DPI_HARDWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        46,
        V_FIXED_NETWORK_DPI_HARDWARE,
        "固网DPI-硬件（分流器+DPI服务器）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityFixedNetworkDpiHardwareInv(), internalDO.getAbilityFixedNetworkDpiHardwareInc()),
        BenefitInternalConstructionDO::getAbilityFixedNetworkDpiHardwareVendor,
        BenefitInternalConstructionDO::setAbilityFixedNetworkDpiHardwareVar
    ),

    // 特殊
    ABILITY_FIXED_NETWORK_DPI_SOFTWARE_SPECIAL(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        47,
        V_FIXED_NETWORK_DPI_SOFTWARE,
        "固网DPI-软件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityFixedNetworkDpiSoftwareInv()),
        BenefitInternalConstructionDO::getAbilityFixedNetworkDpiSoftwareVendor,
        BenefitInternalConstructionDO::setAbilityFixedNetworkDpiSoftwareVar
    ),

    ABILITY_MOBILE_MALWARE_DETECTION_HARDWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        48,
        V_MOBILE_MALWARE_DETECTION_HARDWARE,
        "移动恶意程序监测-硬件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMobileMalwareDetectionHardwareInv(), internalDO.getAbilityMobileMalwareDetectionHardwareInc()),
        BenefitInternalConstructionDO::getAbilityMobileMalwareDetectionHardwareVendor,
        BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionHardwareVar
    ),

    ABILITY_MOBILE_MALWARE_DETECTION_SOFTWARE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        49,
        V_MOBILE_MALWARE_DETECTION_SOFTWARE,
        "移动恶意程序监测-软件",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMobileMalwareDetectionSoftwareInv(), internalDO.getAbilityMobileMalwareDetectionSoftwareInc()),
        BenefitInternalConstructionDO::getAbilityMobileMalwareDetectionSoftwareVendor,
        BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareVar
    ),

    ABILITY_MOBILE_MALWARE_DETECTION_SOFTWARE_UPGRADE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_CAPABILITY,
        50,
        V_MOBILE_MALWARE_DETECTION_SOFTWARE_UPGRADE,
        "移动恶意程序监测-软件升级",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getAbilityMobileMalwareDetectionSoftwareUpgradeInv(), internalDO.getAbilityMobileMalwareDetectionSoftwareUpgradeInc()),
        BenefitInternalConstructionDO::getAbilityMobileMalwareDetectionSoftwareUpgradeVendor,
        BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareUpgradeVar
    ),

    SOFTWARE_MOBILE_INTERNET_LOG_RETENTION_CENTRALIZED(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        1,
        V_MOBILE_INTERNET_LOG_RETENTION_CENTRALIZED,
        "移动上网日志留存-集采",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareMobileInternetLogRetentionCentralizedInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareMobileInternetLogRetentionCentralizedVar
    ),

    SOFTWARE_MOBILE_INTERNET_LOG_RETENTION_CUSTOMIZED(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        2,
        V_MOBILE_INTERNET_LOG_RETENTION_CUSTOMIZED,
        "移动上网日志留存-个性化省采",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareMobileInternetLogRetentionCustomizedInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareMobileInternetLogRetentionCustomizedVar
    ),

    SOFTWARE_BOTNET_WORM_DETECTION_PLATFORM_TOTAL_PRICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        3,
        V_BOTNET_WORM_DETECTION_PLATFORM_TOTAL_PRICE,
        "僵木蠕监测-平台总价",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareBotnetWormDetectionPlatformTotalPriceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareBotnetWormDetectionPlatformTotalPriceVar
    ),

    SOFTWARE_IDCISP_PLATFORM_TOTAL_PRICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        4,
        V_IDCISP_PLATFORM_TOTAL_PRICE,
        "IDCISP-平台总价",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareIdcispPlatformTotalPriceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareIdcispPlatformTotalPriceVar
    ),

    SOFTWARE_MOBILE_DPI_PLATFORM_TOTAL_PRICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        5,
        V_MOBILE_DPI_PLATFORM_TOTAL_PRICE,
        "移动DPI-平台总价",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareMobileDpiPlatformTotalPriceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareMobileDpiPlatformTotalPriceVar
    ),

    SOFTWARE_FIXED_NETWORK_DPI_PLATFORM_TOTAL_PRICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        6,
        V_FIXED_NETWORK_DPI_PLATFORM_TOTAL_PRICE,
        "固网DPI-平台总价",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareFixedNetworkDpiPlatformTotalPriceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareFixedNetworkDpiPlatformTotalPriceVar
    ),

    SOFTWARE_MOBILE_MALWARE_DETECTION_PLATFORM_TOTAL_PRICE(
        MetricBenefitL1AssessmentEnum.COMPLIANCE_SOFTWARE,
        7,
        V_MOBILE_MALWARE_DETECTION_PLATFORM_TOTAL_PRICE,
        "移动恶意程序监测-平台总价",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareMobileMalwareDetectionPlatformTotalPriceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareMobileMalwareDetectionPlatformTotalPriceVar
    ),

    SOFTWARE_ASSET_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        8,
        V_ASSET_MANAGEMENT,
        "资产管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAssetManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAssetManagementVar
    ),

    SOFTWARE_BASELINE_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        9,
        V_BASELINE_MANAGEMENT,
        "基线管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareBaselineManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareBaselineManagementVar
    ),

    SOFTWARE_VULNERABILITY_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        10,
        V_VULNERABILITY_MANAGEMENT,
        "漏洞管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareVulnerabilityManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareVulnerabilityManagementVar
    ),

    SOFTWARE_INTERNET_EXPOSURE_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        11,
        V_INTERNET_EXPOSURE_MANAGEMENT,
        "互联网暴露面管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareInternetExposureManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareInternetExposureManagementVar
    ),

    SOFTWARE_INTERNAL_NETWORK_ASSET_MAPPING(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        12,
        V_INTERNAL_NETWORK_ASSET_MAPPING,
        "内网资产测绘",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareInternalNetworkAssetMappingInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareInternalNetworkAssetMappingVar
    ),

    SOFTWARE_AAAA(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        13,
        V_AAAA,
        "4A",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAaaaInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAaaaVar
    ),

    SOFTWARE_APP_RELEASE_DETECTION(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        14,
        V_APP_RELEASE_DETECTION,
        "APP上线检测",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAppReleaseDetectionInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAppReleaseDetectionVar
    ),

    SOFTWARE_DATA_ASSET_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        15,
        V_DATA_ASSET_MANAGEMENT,
        "数据资产管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareDataAssetManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareDataAssetManagementVar
    ),

    SOFTWARE_PASSWORD_SERVICE_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        16,
        V_PASSWORD_SERVICE_MANAGEMENT,
        "密码服务管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwarePasswordServiceManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwarePasswordServiceManagementVar
    ),

    SOFTWARE_THREAT_INTELLIGENCE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        17,
        V_THREAT_INTELLIGENCE,
        "威胁情报",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareThreatIntelligenceInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareThreatIntelligenceVar
    ),

    SOFTWARE_NETWORK_SECURITY_SITUATIONAL_AWARENESS(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        18,
        V_NETWORK_SECURITY_SITUATIONAL_AWARENESS,
        "网络安全态势感知",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareNetworkSecuritySituationalAwarenessInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareNetworkSecuritySituationalAwarenessVar
    ),

    SOFTWARE_DATA_SECURITY_SITUATIONAL_AWARENESS(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        19,
        V_DATA_SECURITY_SITUATIONAL_AWARENESS,
        "数据安全态势感知",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareDataSecuritySituationalAwarenessInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareDataSecuritySituationalAwarenessVar
    ),

    SOFTWARE_WEBSITE_FILING_MONITORING(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        20,
        V_WEBSITE_FILING_MONITORING,
        "互联网网站备案监测",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareWebsiteFilingMonitoringInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareWebsiteFilingMonitoringVar
    ),

    SOFTWARE_HARMFUL_INFORMATION_MONITORING(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        21,
        V_HARMFUL_INFORMATION_MONITORING,
        "不良信息监测",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareHarmfulInformationMonitoringInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareHarmfulInformationMonitoringVar
    ),

    SOFTWARE_ANTI_FRAUD_MANAGEMENT(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        22,
        V_ANTI_FRAUD_MANAGEMENT,
        "反诈管理",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAntiFraudManagementInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAntiFraudManagementVar
    ),

    SOFTWARE_CONTENT_SECURITY_REVIEW_PUBLISH_CONTROL(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        23,
        V_CONTENT_SECURITY_REVIEW_PUBLISH_CONTROL,
        "内容安全“先审后发”管控",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareContentSecurityReviewPublishControlInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareContentSecurityReviewPublishControlVar
    ),

    SOFTWARE_ONE_CLICK_DISPOSAL(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        24,
        V_ONE_CLICK_DISPOSAL,
        "一键处置（含一键派单、封堵、关停）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareOneClickDisposalInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareOneClickDisposalVar
    ),

    SOFTWARE_SOAR(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        25,
        V_SOAR,
        "SOAR",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareSoarInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareSoarVar
    ),

    SOFTWARE_NETWORK_ATTACK_TRACING(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        26,
        V_NETWORK_ATTACK_TRACING,
        "网络攻击溯源",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareNetworkAttackTracingInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareNetworkAttackTracingVar
    ),

    SOFTWARE_SECURITY_CAPABILITY_CENTER(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        27,
        V_SECURITY_CAPABILITY_CENTER,
        "安全能力中心",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareSecurityCapabilityCenterInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareSecurityCapabilityCenterVar
    ),

    SOFTWARE_SECURITY_DATA_CENTER(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        28,
        V_SECURITY_DATA_CENTER,
        "安全数据中心",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareSecurityDataCenterInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareSecurityDataCenterVar
    ),

    SOFTWARE_ATTACK_DEFENSE_DRILL(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        29,
        V_ATTACK_DEFENSE_DRILL,
        "攻防演练",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAttackDefenseDrillInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAttackDefenseDrillVar
    ),

    SOFTWARE_PATCH_MANAGEMENT_CORE_NATIVE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        30,
        V_PATCH_MANAGEMENT_CORE_NATIVE,
        "补丁管理（5GC内生）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwarePatchManagementCoreNativeInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwarePatchManagementCoreNativeVar
    ),

    SOFTWARE_PATCH_MANAGEMENT_CORE_EXTERNAL(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        31,
        V_PATCH_MANAGEMENT_CORE_EXTERNAL,
        "补丁管理（5GC外挂）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwarePatchManagementCoreExternalInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwarePatchManagementCoreExternalVar
    ),

    SOFTWARE_VULNERABILITY_MANAGEMENT_CORE_NATIVE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        32,
        V_VULNERABILITY_MANAGEMENT_CORE_NATIVE,
        "漏洞管理（5GC内生）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareVulnerabilityManagementCoreNativeInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareVulnerabilityManagementCoreNativeVar
    ),

    SOFTWARE_ASSET_MANAGEMENT_CORE_NATIVE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        33,
        V_ASSET_MANAGEMENT_CORE_NATIVE,
        "资产管理（5GC内生）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareAssetManagementCoreNativeInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareAssetManagementCoreNativeVar
    ),

    SOFTWARE_SITUATIONAL_AWARENESS_CORE_NATIVE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        34,
        V_SITUATIONAL_AWARENESS_CORE_NATIVE,
        "态势感知（5GC内生）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareSituationalAwarenessCoreNativeInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareSituationalAwarenessCoreNativeVar
    ),

    SOFTWARE_UEBA_CORE(
        MetricBenefitL1AssessmentEnum.OPERATIONS_SYSTEM,
        35,
        V_UEBA_CORE,
        "UEBA（5GC用户行为分析）",
        (BenefitInternalConstructionDO internalDO) ->
            new BenefitColumnTemplate(internalDO.getSoftwareUebaCoreInv(), null),
        null,
        BenefitInternalConstructionDO::setSoftwareUebaCoreVar
    );

    private final MetricBenefitL1AssessmentEnum assessment;
    private final Integer index;
    private final String calculatorEn;
    private final String calculatorCn;
    // BenefitInternalConstructionDO 特定列 -> BenefitColumnTemplate -> all getter
    private final Function<BenefitInternalConstructionDO, BenefitColumnTemplate> columnTemplateExtractor;
    private final Function<BenefitInternalConstructionDO, String> vendorExtractor;
    // BenefitInternalConstructionDO 特定列 -> all setter
    private final BiConsumer<BenefitInternalConstructionDO, Double> internalVarSetter;

    MetricBenefitL2CalculatorEnum(MetricBenefitL1AssessmentEnum assessment, Integer index, String calculatorEn, String calculatorCn, Function<BenefitInternalConstructionDO, BenefitColumnTemplate> columnTemplateExtractor, Function<BenefitInternalConstructionDO, String> vendorExtractor, BiConsumer<BenefitInternalConstructionDO, Double> internalVarSetter) {
        this.assessment = assessment;
        this.index = index;
        this.calculatorEn = calculatorEn;
        this.calculatorCn = calculatorCn;
        this.columnTemplateExtractor = columnTemplateExtractor;
        this.vendorExtractor = vendorExtractor;
        this.internalVarSetter = internalVarSetter;
    }

    public MetricBenefitL1AssessmentEnum getAssessment() {
        return assessment;
    }

    public Integer getIndex() {
        return index;
    }

    public String getCalculatorEn() {
        return calculatorEn;
    }

    public String getCalculatorCn() {
        return calculatorCn;
    }

    public Function<BenefitInternalConstructionDO, BenefitColumnTemplate> getColumnTemplateExtractor() {
        return columnTemplateExtractor;
    }

    public Function<BenefitInternalConstructionDO, String> getVendorExtractor() {
        return vendorExtractor;
    }

    public BiConsumer<BenefitInternalConstructionDO, Double> getInternalVarSetter() {
        return internalVarSetter;
    }

    @Override
    public String toString() {
        return "MetricBenefitL2CalculatorEnum{" +
            "assessment=" + assessment +
            ", index=" + index +
            ", calculatorEn='" + calculatorEn + '\'' +
            ", calculatorCn='" + calculatorCn + '\'' +
            ", columnTemplateExtractor=" + columnTemplateExtractor +
            ", vendorExtractor=" + vendorExtractor +
            ", internalVarSetter=" + internalVarSetter +
            "} " + super.toString();
    }

    /**
     * 根据 calculatorEn 标识查询 MetricBenefitL2CalculatorEnum
     *
     * @param calculatorEn  算子 En
     * @return              MetricBenefitL2CalculatorEnum
     */
    public static MetricBenefitL2CalculatorEnum findByCalculatorEn(String calculatorEn) {
        for (MetricBenefitL2CalculatorEnum calculator : values()) {
            if (calculator.getCalculatorEn().equals(calculatorEn)) {
                return calculator;
            }
        }
        return null;
    }

    /**
     * 根据 assessmentEn 标识查询所有匹配的 MetricBenefitL2CalculatorEnum
     *
     * @param assessmentEn  评估项 En
     * @return              List<MetricBenefitL2CalculatorEnum>
     */
    public static List<MetricBenefitL2CalculatorEnum> findByAssessmentEn(String assessmentEn) {
        List<MetricBenefitL2CalculatorEnum> result = new ArrayList<>();
        for (MetricBenefitL2CalculatorEnum calculator : values()) {
            if (calculator.getAssessment().getAssessmentEn().equals(assessmentEn)) {
                result.add(calculator);
            }
        }
        return result;
    }

    /**
     * 打印所有枚举项的表格形式信息，使行数据和表头对齐
     */
    public static void printAlignedTable() {
        // 定义表头
        String header1 = "评估项";
        String header2 = "序列";
        String header3 = "算子名";
        String header4 = "算子中文名";
        String header5 = "别名";

        // 计算各列的最大宽度
        int maxCol1Width = header1.length();
        int maxCol2Width = header2.length();
        int maxCol3Width = header3.length();
        int maxCol4Width = header4.length();
        int maxCol5Width = 30;

        // 遍历所有枚举值，找出每列的最大宽度
        for (MetricBenefitL2CalculatorEnum calculator : values()) {
            String col1 = calculator.getAssessment().getAssessmentCn();
            String col2 = String.valueOf(calculator.getIndex());
            String col3 = calculator.getCalculatorEn();
            String col4 = calculator.getCalculatorCn();

            maxCol1Width = Math.max(maxCol1Width, col1 != null ? col1.length() : 0);
            maxCol2Width = Math.max(maxCol2Width, col2 != null ? col2.length() : 0);
            maxCol3Width = Math.max(maxCol3Width, col3 != null ? col3.length() : 0);
            maxCol4Width = Math.max(maxCol4Width, col4 != null ? col4.length() : 0);
        }
        System.out.println(maxCol4Width);

        // 打印表头，使用格式化字符串确保对齐
        String headerFormat = "%-" + maxCol1Width + "s\t%-" + maxCol2Width + "s\t%-" + maxCol3Width + "s\t%-" + maxCol4Width + "s\t%-" + maxCol5Width + "s%n";
        System.out.printf(headerFormat, header1, header2, header3, header4, header5);

        // 打印分隔线
        for (int i = 0; i < maxCol1Width; i++) System.out.print("-");
        System.out.print("\t");
        for (int i = 0; i < maxCol2Width; i++) System.out.print("-");
        System.out.print("\t");
        for (int i = 0; i < maxCol3Width; i++) System.out.print("-");
        System.out.print("\t");
        for (int i = 0; i < maxCol4Width; i++) System.out.print("-");
        System.out.print("\t");
        for (int i = 0; i < maxCol5Width; i++) System.out.print("-");
        System.out.println();

        // 打印每个枚举项的数据
        String rowFormat = "%-" + maxCol1Width + "s\t%-" + maxCol2Width + "s\t%-" + maxCol3Width + "s\t%-" + maxCol4Width + "s\t%-" + maxCol5Width + "s%n";
        for (MetricBenefitL2CalculatorEnum calculator : values()) {
            System.out.printf(rowFormat,
                calculator.getAssessment().getAssessmentCn(),
                calculator.getIndex(),
                calculator.getCalculatorEn(),
                calculator.getCalculatorCn(),
                customAliasB(calculator)
            );
        }
    }

    private static String customAliasA(MetricBenefitL2CalculatorEnum calculatorEnum){
        return "1.1.1.1." + calculatorEnum.getIndex();
    }

    private static String customAliasB(MetricBenefitL2CalculatorEnum calculatorEnum){
        String prefix = "private Double ";

        // StrUtil.upperFirst()
        String common =  StrUtil.toCamelCase(calculatorEnum.getCalculatorEn().replaceFirst("v", calculatorEnum.getAssessment().getInvisibleParent()));

        String suffix = "Var;";

        return prefix + common + suffix ;
    }

    public static void main(String[] args) {
        printAlignedTable();
    }

}
