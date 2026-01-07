package com.sama.api.ledger.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sama.api.ledger.bean.bo.PageBaseModel;

import java.io.Serial;

/**
 * 效益对内建设原表
 *
 * @author: huxh
 * @description:
 * @datetime: 2025/9/3 15:02
 */
@TableName("sama_ledger.benefit_internal_construction")
@ExcelIgnoreUnannotated
public class BenefitInternalConstructionDO extends PageBaseModel {

    @Serial
    private static final long serialVersionUID = 1430723002393490642L;

    @ExcelProperty(value = "序号", index = 0)
    private Long itemNo;

    @ExcelProperty(value = "省公司", index = 1)
    private String provincialCompany;

    @ExcelProperty(value = "项目名称", index = 2)
    private String projectName;

    @ExcelProperty(value = "项目编号", index = 3)
    private String projectCode;

    @ExcelProperty(value = "项目类型", index = 4)
    private String projectType;

    @ExcelProperty(value = "项目设计批复总投资（元）【自动生成不用填写】", index = 5)
    private Double autoProjectDesignReplyTotalInvestment;

    @ExcelProperty(value = "非安全类设备费（元）", index = 6)
    private Double nonSecurityDeviceFee;

    @ExcelProperty(value = "安全类设备费（元）", index = 7)
    private Double securityDeviceFee;

    @ExcelProperty(value = "总体其他费（元）", index = 8)
    private Double totalOtherFee;

    @ExcelProperty(value = "安全类设备其他费（元）【自动生成不用填写】", index = 9)
    private Double autoSecurityDeviceOtherFee;

    /**
     *  1	防火墙-硬件
     */
    @ExcelProperty(index = 10)
    private String abilityFirewallHardwareVendor;

    @ExcelProperty(index = 11)
    private Integer abilityFirewallHardwareIncNum;

    @ExcelProperty(index = 12)
    private Double abilityFirewallHardwareInc;

    @ExcelProperty(index = 13)
    private Double abilityFirewallHardwareInv;

    @ExcelProperty(index = 14)
    private String abilityFirewallHardwareRemark;

    /**
     *  2	防火墙-原子能力
     */
    @ExcelProperty(index = 15)
    private String abilityFirewallAtomicCapabilityVendor;

    @ExcelProperty(index = 16)
    private Integer abilityFirewallAtomicCapabilityIncNum;

    @ExcelProperty(index = 17)
    private Double abilityFirewallAtomicCapabilityInc;

    @ExcelProperty(index = 18)
    private Double abilityFirewallAtomicCapabilityInv;

    @ExcelProperty(index = 19)
    private String abilityFirewallAtomicCapabilityRemark;

    /**
     *  3	IPS-硬件
     */
    @ExcelProperty(index = 20)
    private String abilityIpsHardwareVendor;

    @ExcelProperty(index = 21)
    private Integer abilityIpsHardwareIncNum;

    @ExcelProperty(index = 22)
    private Double abilityIpsHardwareInc;

    @ExcelProperty(index = 23)
    private Double abilityIpsHardwareInv;

    @ExcelProperty(index = 24)
    private String abilityIpsHardwareRemark;

    /**
     *  4	IPS-原子能力
     */
    @ExcelProperty(index = 25)
    private String abilityIpsAtomicCapabilityVendor;

    @ExcelProperty(index = 26)
    private Integer abilityIpsAtomicCapabilityIncNum;

    @ExcelProperty(index = 27)
    private Double abilityIpsAtomicCapabilityInc;

    @ExcelProperty(index = 28)
    private Double abilityIpsAtomicCapabilityInv;

    @ExcelProperty(index = 29)
    private String abilityIpsAtomicCapabilityRemark;

    /**
     *  5	Web防御（WAF）-硬件
     */
    @ExcelProperty(index = 30)
    private String abilityWafHardwareVendor;

    @ExcelProperty(index = 31)
    private Integer abilityWafHardwareIncNum;

    @ExcelProperty(index = 32)
    private Double abilityWafHardwareInc;

    @ExcelProperty(index = 33)
    private Double abilityWafHardwareInv;

    @ExcelProperty(index = 34)
    private String abilityWafHardwareRemark;

    /**
     *  6	Web防御（WAF）-硬件-国产化设备
     */
    @ExcelProperty(index = 35)
    private String abilityWafDomesticHardwareVendor;

    @ExcelProperty(index = 36)
    private Integer abilityWafDomesticHardwareIncNum;

    @ExcelProperty(index = 37)
    private Double abilityWafDomesticHardwareInc;

    @ExcelProperty(index = 38)
    private Double abilityWafDomesticHardwareInv;

    @ExcelProperty(index = 39)
    private String abilityWafDomesticHardwareRemark;

    /**
     *  7	Web防御（WAF）-原子能力
     */
    @ExcelProperty(index = 40)
    private String abilityWafAtomicCapabilityVendor;

    @ExcelProperty(index = 41)
    private Integer abilityWafAtomicCapabilityIncNum;

    @ExcelProperty(index = 42)
    private Double abilityWafAtomicCapabilityInc;

    @ExcelProperty(index = 43)
    private Double abilityWafAtomicCapabilityInv;

    @ExcelProperty(index = 44)
    private String abilityWafAtomicCapabilityRemark;

    /**
     *  8	Web防御（动态防护）-硬件
     */
    @ExcelProperty(index = 45)
    private String abilityWebDynamicDefenseHardwareVendor;

    @ExcelProperty(index = 46)
    private Integer abilityWebDynamicDefenseHardwareIncNum;

    @ExcelProperty(index = 47)
    private Double abilityWebDynamicDefenseHardwareInc;

    @ExcelProperty(index = 48)
    private Double abilityWebDynamicDefenseHardwareInv;

    @ExcelProperty(index = 49)
    private String abilityWebDynamicDefenseHardwareRemark;

    /**
     *  9	Web防御（动态防护）-软件
     */
    @ExcelProperty(index = 50)
    private String abilityWebDynamicDefenseSoftwareVendor;

    @ExcelProperty(index = 51)
    private Integer abilityWebDynamicDefenseSoftwareIncNum;

    @ExcelProperty(index = 52)
    private Double abilityWebDynamicDefenseSoftwareInc;

    @ExcelProperty(index = 53)
    private Double abilityWebDynamicDefenseSoftwareInv;

    @ExcelProperty(index = 54)
    private String abilityWebDynamicDefenseSoftwareRemark;

    /**
     *  10	流量清洗（抗DDoS）
     */
    @ExcelProperty(index = 55)
    private String abilityTrafficScrubbingVendor;

    @ExcelProperty(index = 56)
    private Integer abilityTrafficScrubbingIncNum;

    @ExcelProperty(index = 57)
    private Double abilityTrafficScrubbingInc;

    @ExcelProperty(index = 58)
    private Double abilityTrafficScrubbingInv;

    @ExcelProperty(index = 59)
    private String abilityTrafficScrubbingRemark;

    /**
     *  11	流量分析（全流量分析）
     */
    @ExcelProperty(index = 60)
    private String abilityFullTrafficAnalysisVendor;

    @ExcelProperty(index = 61)
    private Integer abilityFullTrafficAnalysisIncNum;

    @ExcelProperty(index = 62)
    private Double abilityFullTrafficAnalysisInc;

    @ExcelProperty(index = 63)
    private Double abilityFullTrafficAnalysisInv;

    @ExcelProperty(index = 64)
    private String abilityFullTrafficAnalysisRemark;

    /**
     *  12	堡垒机
     */
    @ExcelProperty(index = 65)
    private String abilityBastionHostVendor;

    @ExcelProperty(index = 66)
    private Integer abilityBastionHostIncNum;

    @ExcelProperty(index = 67)
    private Double abilityBastionHostInc;

    @ExcelProperty(index = 68)
    private Double abilityBastionHostInv;

    @ExcelProperty(index = 69)
    private String abilityBastionHostRemark;

    /**
     *  13	日志审计
     */
    @ExcelProperty(index = 70)
    private String abilityLogAuditVendor;

    @ExcelProperty(index = 71)
    private Integer abilityLogAuditIncNum;

    @ExcelProperty(index = 72)
    private Double abilityLogAuditInc;

    @ExcelProperty(index = 73)
    private Double abilityLogAuditInv;

    @ExcelProperty(index = 74)
    private String abilityLogAuditRemark;

    /**
     *  14	EDR（含防病毒）
     */
    @ExcelProperty(index = 75)
    private String abilityEdrVendor;

    @ExcelProperty(index = 76)
    private Integer abilityEdrIncNum;

    @ExcelProperty(index = 77)
    private Double abilityEdrInc;

    @ExcelProperty(index = 78)
    private Double abilityEdrInv;

    @ExcelProperty(index = 79)
    private String abilityEdrRemark;

    /**
     *  15	漏洞扫描（主机）-硬件
     */
    @ExcelProperty(index = 80)
    private String abilityHostVulnerabilityScanHardwareVendor;

    @ExcelProperty(index = 81)
    private Integer abilityHostVulnerabilityScanHardwareIncNum;

    @ExcelProperty(index = 82)
    private Double abilityHostVulnerabilityScanHardwareInc;

    @ExcelProperty(index = 83)
    private Double abilityHostVulnerabilityScanHardwareInv;

    @ExcelProperty(index = 84)
    private String abilityHostVulnerabilityScanHardwareRemark;

    /**
     *  16	漏洞扫描（主机）-原子能力
     */
    @ExcelProperty(index = 85)
    private String abilityHostVulnerabilityScanAtomicCapabilityVendor;

    @ExcelProperty(index = 86)
    private Integer abilityHostVulnerabilityScanAtomicCapabilityIncNum;

    @ExcelProperty(index = 87)
    private Double abilityHostVulnerabilityScanAtomicCapabilityInc;

    @ExcelProperty(index = 88)
    private Double abilityHostVulnerabilityScanAtomicCapabilityInv;

    @ExcelProperty(index = 89)
    private String abilityHostVulnerabilityScanAtomicCapabilityRemark;

    /**
     *  17	漏洞扫描（Web）-硬件
     */
    @ExcelProperty(index = 90)
    private String abilityWebVulnerabilityScanHardwareVendor;

    @ExcelProperty(index = 91)
    private Integer abilityWebVulnerabilityScanHardwareIncNum;

    @ExcelProperty(index = 92)
    private Double abilityWebVulnerabilityScanHardwareInc;

    @ExcelProperty(index = 93)
    private Double abilityWebVulnerabilityScanHardwareInv;

    @ExcelProperty(index = 94)
    private String abilityWebVulnerabilityScanHardwareRemark;

    /**
     *  18	漏洞扫描（Web）-原子能力
     */
    @ExcelProperty(index = 95)
    private String abilityWebVulnerabilityScanAtomicCapabilityVendor;

    @ExcelProperty(index = 96)
    private Integer abilityWebVulnerabilityScanAtomicCapabilityIncNum;

    @ExcelProperty(index = 97)
    private Double abilityWebVulnerabilityScanAtomicCapabilityInc;

    @ExcelProperty(index = 98)
    private Double abilityWebVulnerabilityScanAtomicCapabilityInv;

    @ExcelProperty(index = 99)
    private String abilityWebVulnerabilityScanAtomicCapabilityRemark;

    /**
     *  19	容器安全
     */
    @ExcelProperty(index = 100)
    private String abilityContainerSecurityVendor;

    @ExcelProperty(index = 101)
    private Integer abilityContainerSecurityIncNum;

    @ExcelProperty(index = 102)
    private Double abilityContainerSecurityInc;

    @ExcelProperty(index = 103)
    private Double abilityContainerSecurityInv;

    @ExcelProperty(index = 104)
    private String abilityContainerSecurityRemark;

    /**
     *  20	容器安全-纯agent
     */
    @ExcelProperty(index = 105)
    private String abilityContainerSecurityAgentVendor;

    @ExcelProperty(index = 106)
    private Integer abilityContainerSecurityAgentIncNum;

    @ExcelProperty(index = 107)
    private Double abilityContainerSecurityAgentInc;

    @ExcelProperty(index = 108)
    private Double abilityContainerSecurityAgentInv;

    @ExcelProperty(index = 109)
    private String abilityContainerSecurityAgentRemark;

    /**
     *  21	网页防篡改-非池化
     */
    @ExcelProperty(index = 110)
    private String abilityWebTamperPreventionVendor;

    @ExcelProperty(index = 111)
    private Integer abilityWebTamperPreventionIncNum;

    @ExcelProperty(index = 112)
    private Double abilityWebTamperPreventionInc;

    @ExcelProperty(index = 113)
    private Double abilityWebTamperPreventionInv;

    @ExcelProperty(index = 114)
    private String abilityWebTamperPreventionRemark;

    /**
     *  22	网页防篡改-原子能力
     */
    @ExcelProperty(index = 115)
    private String abilityWebTamperPreventionAtomicCapabilityVendor;

    @ExcelProperty(index = 116)
    private Integer abilityWebTamperPreventionAtomicCapabilityIncNum;

    @ExcelProperty(index = 117)
    private Double abilityWebTamperPreventionAtomicCapabilityInc;

    @ExcelProperty(index = 118)
    private Double abilityWebTamperPreventionAtomicCapabilityInv;

    @ExcelProperty(index = 119)
    private String abilityWebTamperPreventionAtomicCapabilityRemark;

    /**
     *  23	接口安全管控（API网关）
     */
    @ExcelProperty(index = 120)
    private String abilityApiGatewaySecurityVendor;

    @ExcelProperty(index = 121)
    private Integer abilityApiGatewaySecurityIncNum;

    @ExcelProperty(index = 122)
    private Double abilityApiGatewaySecurityInc;

    @ExcelProperty(index = 123)
    private Double abilityApiGatewaySecurityInv;

    @ExcelProperty(index = 124)
    private String abilityApiGatewaySecurityRemark;

    /**
     *  24	数据防泄漏（网络侧）
     */
    @ExcelProperty(index = 125)
    private String abilityNetworkDlpVendor;

    @ExcelProperty(index = 126)
    private Integer abilityNetworkDlpIncNum;

    @ExcelProperty(index = 127)
    private Double abilityNetworkDlpInc;

    @ExcelProperty(index = 128)
    private Double abilityNetworkDlpInv;

    @ExcelProperty(index = 129)
    private String abilityNetworkDlpRemark;

    /**
     *  25	数据防泄漏（网络侧）-国产化设备
     */
    @ExcelProperty(index = 130)
    private String abilityNetworkDomesticDlpVendor;

    @ExcelProperty(index = 131)
    private Integer abilityNetworkDomesticDlpIncNum;

    @ExcelProperty(index = 132)
    private Double abilityNetworkDomesticDlpInc;

    @ExcelProperty(index = 133)
    private Double abilityNetworkDomesticDlpInv;

    @ExcelProperty(index = 134)
    private String abilityNetworkDomesticDlpRemark;

    /**
     *  26	数据脱敏-动态
     */
    @ExcelProperty(index = 135)
    private String abilityDynamicDataMaskingVendor;

    @ExcelProperty(index = 136)
    private Integer abilityDynamicDataMaskingIncNum;

    @ExcelProperty(index = 137)
    private Double abilityDynamicDataMaskingInc;

    @ExcelProperty(index = 138)
    private Double abilityDynamicDataMaskingInv;

    @ExcelProperty(index = 139)
    private String abilityDynamicDataMaskingRemark;

    /**
     *  27	数据脱敏-静态
     */
    @ExcelProperty(index = 140)
    private String abilityStaticDataMaskingVendor;

    @ExcelProperty(index = 141)
    private Integer abilityStaticDataMaskingIncNum;

    @ExcelProperty(index = 142)
    private Double abilityStaticDataMaskingInc;

    @ExcelProperty(index = 143)
    private Double abilityStaticDataMaskingInv;

    @ExcelProperty(index = 144)
    private String abilityStaticDataMaskingRemark;

    /**
     *  28	数据库审计
     */
    @ExcelProperty(index = 145)
    private String abilityDatabaseAuditVendor;

    @ExcelProperty(index = 146)
    private Integer abilityDatabaseAuditIncNum;

    @ExcelProperty(index = 147)
    private Double abilityDatabaseAuditInc;

    @ExcelProperty(index = 148)
    private Double abilityDatabaseAuditInv;

    @ExcelProperty(index = 149)
    private String abilityDatabaseAuditRemark;

    /**
     *  29	信令防火墙 C-IWF（5GC）
     */
    @ExcelProperty(index = 150)
    private String abilitySignalingFirewallVendor;

    @ExcelProperty(index = 151)
    private Integer abilitySignalingFirewallIncNum;

    @ExcelProperty(index = 152)
    private Double abilitySignalingFirewallInc;

    @ExcelProperty(index = 153)
    private Double abilitySignalingFirewallInv;

    @ExcelProperty(index = 154)
    private String abilitySignalingFirewallRemark;

    /**
     *  30	零信任（SDP）
     */
    @ExcelProperty(index = 155)
    private String abilityZeroTrustSdpVendor;

    @ExcelProperty(index = 156)
    private Integer abilityZeroTrustSdpIncNum;

    @ExcelProperty(index = 157)
    private Double abilityZeroTrustSdpInc;

    @ExcelProperty(index = 158)
    private Double abilityZeroTrustSdpInv;

    @ExcelProperty(index = 159)
    private String abilityZeroTrustSdpRemark;

    /**
     *  31	零信任（SDP）-国产化设备
     */
    @ExcelProperty(index = 160)
    private String abilityZeroTrustDomesticSdpVendor;

    @ExcelProperty(index = 161)
    private Integer abilityZeroTrustDomesticSdpIncNum;

    @ExcelProperty(index = 162)
    private Double abilityZeroTrustDomesticSdpInc;

    @ExcelProperty(index = 163)
    private Double abilityZeroTrustDomesticSdpInv;

    @ExcelProperty(index = 164)
    private String abilityZeroTrustDomesticSdpRemark;

    /**
     *  32	蜜罐
     */
    @ExcelProperty(index = 165)
    private String abilityHoneypotVendor;

    @ExcelProperty(index = 166)
    private Integer abilityHoneypotIncNum;

    @ExcelProperty(index = 167)
    private Double abilityHoneypotInc;

    @ExcelProperty(index = 168)
    private Double abilityHoneypotInv;

    @ExcelProperty(index = 169)
    private String abilityHoneypotRemark;

    /**
     *  33	蜜罐-国产化设备
     */
    @ExcelProperty(index = 170)
    private String abilityDomesticHoneypotVendor;

    @ExcelProperty(index = 171)
    private Integer abilityDomesticHoneypotIncNum;

    @ExcelProperty(index = 172)
    private Double abilityDomesticHoneypotInc;

    @ExcelProperty(index = 173)
    private Double abilityDomesticHoneypotInv;

    @ExcelProperty(index = 174)
    private String abilityDomesticHoneypotRemark;

    /**
     *  34	微隔离
     */
    @ExcelProperty(index = 175)
    private String abilityMicroSegmentationVendor;

    @ExcelProperty(index = 176)
    private Integer abilityMicroSegmentationIncNum;

    @ExcelProperty(index = 177)
    private Double abilityMicroSegmentationInc;

    @ExcelProperty(index = 178)
    private Double abilityMicroSegmentationInv;

    @ExcelProperty(index = 179)
    private String abilityMicroSegmentationRemark;

    /**
     *  35	异常流量检测-流量转发设备
     */
    @ExcelProperty(index = 180)
    private String abilityAbnormalTrafficDetectionForwardingDeviceVendor;

    @ExcelProperty(index = 181)
    private Integer abilityAbnormalTrafficDetectionForwardingDeviceIncNum;

    @ExcelProperty(index = 182)
    private Double abilityAbnormalTrafficDetectionForwardingDeviceInc;

    @ExcelProperty(index = 183)
    private Double abilityAbnormalTrafficDetectionForwardingDeviceInv;

    @ExcelProperty(index = 184)
    private String abilityAbnormalTrafficDetectionForwardingDeviceRemark;

    /**
     *  36	异常流量检测-流量采集设备
     */
    @ExcelProperty(index = 185)
    private String abilityAbnormalTrafficDetectionCollectionDeviceVendor;

    @ExcelProperty(index = 186)
    private Integer abilityAbnormalTrafficDetectionCollectionDeviceIncNum;

    @ExcelProperty(index = 187)
    private Double abilityAbnormalTrafficDetectionCollectionDeviceInc;

    @ExcelProperty(index = 188)
    private Double abilityAbnormalTrafficDetectionCollectionDeviceInv;

    @ExcelProperty(index = 189)
    private String abilityAbnormalTrafficDetectionCollectionDeviceRemark;

    /**
     *  37	异常流量检测-报表处理设备
     */
    @ExcelProperty(index = 190)
    private String abilityAbnormalTrafficDetectionReportDeviceVendor;

    @ExcelProperty(index = 191)
    private Integer abilityAbnormalTrafficDetectionReportDeviceIncNum;

    @ExcelProperty(index = 192)
    private Double abilityAbnormalTrafficDetectionReportDeviceInc;

    @ExcelProperty(index = 193)
    private Double abilityAbnormalTrafficDetectionReportDeviceInv;

    @ExcelProperty(index = 194)
    private String abilityAbnormalTrafficDetectionReportDeviceRemark;

    /**
     *  38	数据加解密（5GC）
     */
    @ExcelProperty(index = 195)
    private String abilityDataEncryptionDecryptionVendor;

    @ExcelProperty(index = 196)
    private Integer abilityDataEncryptionDecryptionIncNum;

    @ExcelProperty(index = 197)
    private Double abilityDataEncryptionDecryptionInc;

    @ExcelProperty(index = 198)
    private Double abilityDataEncryptionDecryptionInv;

    @ExcelProperty(index = 199)
    private String abilityDataEncryptionDecryptionRemark;

    /**
     *  39	僵木蠕监测-监测处置设备
     */
    @ExcelProperty(index = 200)
    private String abilityBotnetWormDetectionDisposalDeviceVendor;

    @ExcelProperty(index = 201)
    private Integer abilityBotnetWormDetectionDisposalDeviceIncNum;

    @ExcelProperty(index = 202)
    private Double abilityBotnetWormDetectionDisposalDeviceInc;

    @ExcelProperty(index = 203)
    private Double abilityBotnetWormDetectionDisposalDeviceInv;

    @ExcelProperty(index = 204)
    private String abilityBotnetWormDetectionDisposalDeviceRemark;

    /**
     *  40	僵木蠕监测-接口转发网关
     */
    @ExcelProperty(index = 205)
    private String abilityBotnetWormDetectionGatewayVendor;

    @ExcelProperty(index = 206)
    private Integer abilityBotnetWormDetectionGatewayIncNum;

    @ExcelProperty(index = 207)
    private Double abilityBotnetWormDetectionGatewayInc;

    @ExcelProperty(index = 208)
    private Double abilityBotnetWormDetectionGatewayInv;

    @ExcelProperty(index = 209)
    private String abilityBotnetWormDetectionGatewayRemark;

    /**
     *  41	僵木蠕监测-监测处置设备考核软件升级
     */
    @ExcelProperty(index = 210)
    private String abilityBotnetWormDetectionSoftwareUpgradeVendor;

    @ExcelProperty(index = 211)
    private Integer abilityBotnetWormDetectionSoftwareUpgradeIncNum;

    @ExcelProperty(index = 212)
    private Double abilityBotnetWormDetectionSoftwareUpgradeInc;

    @ExcelProperty(index = 213)
    private Double abilityBotnetWormDetectionSoftwareUpgradeInv;

    @ExcelProperty(index = 214)
    private String abilityBotnetWormDetectionSoftwareUpgradeRemark;

    /**
     *  42	IDCISP-硬件（分流器+全量服务器）
     */
    @ExcelProperty(index = 215)
    private String abilityIdcispHardwareVendor;

    @ExcelProperty(index = 216)
    private Integer abilityIdcispHardwareIncNum;

    @ExcelProperty(index = 217)
    private Double abilityIdcispHardwareInc;

    @ExcelProperty(index = 218)
    private Double abilityIdcispHardwareInv;

    @ExcelProperty(index = 219)
    private String abilityIdcispHardwareRemark;

    /**
     *  43	IDCISP-软件
     *  Attention!
     */
    @ExcelProperty(index = 220)
    private String abilityIdcispSoftwareVendor;

    @ExcelProperty(index = 221)
    private Double abilityIdcispSoftwareInv;

    @ExcelProperty(index = 222)
    private String abilityIdcispSoftwareRemark;

    /**
     *  44	移动DPI-硬件
     */
    @ExcelProperty(index = 223)
    private String abilityMobileDpiHardwareVendor;

    @ExcelProperty(index = 224)
    private Integer abilityMobileDpiHardwareIncNum;

    @ExcelProperty(index = 225)
    private Double abilityMobileDpiHardwareInc;

    @ExcelProperty(index = 226)
    private Double abilityMobileDpiHardwareInv;

    @ExcelProperty(index = 227)
    private String abilityMobileDpiHardwareRemark;

    /**
     *  45	移动DPI-软件
     */
    @ExcelProperty(index = 228)
    private String abilityMobileDpiSoftwareVendor;

    @ExcelProperty(index = 229)
    private Integer abilityMobileDpiSoftwareIncNum;

    @ExcelProperty(index = 230)
    private Double abilityMobileDpiSoftwareInc;

    @ExcelProperty(index = 231)
    private Double abilityMobileDpiSoftwareInv;

    @ExcelProperty(index = 232)
    private String abilityMobileDpiSoftwareRemark;

    /**
     *  46	固网DPI-硬件（分流器+DPI服务器）
     */
    @ExcelProperty(index = 233)
    private String abilityFixedNetworkDpiHardwareVendor;

    @ExcelProperty(index = 234)
    private Integer abilityFixedNetworkDpiHardwareIncNum;

    @ExcelProperty(index = 235)
    private Double abilityFixedNetworkDpiHardwareInc;

    @ExcelProperty(index = 236)
    private Double abilityFixedNetworkDpiHardwareInv;

    @ExcelProperty(index = 237)
    private String abilityFixedNetworkDpiHardwareRemark;

    /**
     *  47	固网DPI-软件
     *  Attention!
     */
    @ExcelProperty(index = 238)
    private String abilityFixedNetworkDpiSoftwareVendor;

    @ExcelProperty(index = 239)
    private Double abilityFixedNetworkDpiSoftwareInv;

    @ExcelProperty(index = 240)
    private String abilityFixedNetworkDpiSoftwareRemark;

    /**
     *  48	移动恶意程序监测-硬件
     */
    @ExcelProperty(index = 241)
    private String abilityMobileMalwareDetectionHardwareVendor;

    @ExcelProperty(index = 242)
    private Integer abilityMobileMalwareDetectionHardwareIncNum;

    @ExcelProperty(index = 243)
    private Double abilityMobileMalwareDetectionHardwareInc;

    @ExcelProperty(index = 244)
    private Double abilityMobileMalwareDetectionHardwareInv;

    @ExcelProperty(index = 245)
    private String abilityMobileMalwareDetectionHardwareRemark;

    /**
     *  49	移动恶意程序监测-软件
     */
    @ExcelProperty(index = 246)
    private String abilityMobileMalwareDetectionSoftwareVendor;

    @ExcelProperty(index = 247)
    private Integer abilityMobileMalwareDetectionSoftwareIncNum;

    @ExcelProperty(index = 248)
    private Double abilityMobileMalwareDetectionSoftwareInc;

    @ExcelProperty(index = 249)
    private Double abilityMobileMalwareDetectionSoftwareInv;

    @ExcelProperty(index = 250)
    private String abilityMobileMalwareDetectionSoftwareRemark;

    /**
     *  50	移动恶意程序监测-软件升级
     */
    @ExcelProperty(index = 251)
    private String abilityMobileMalwareDetectionSoftwareUpgradeVendor;

    @ExcelProperty(index = 252)
    private Integer abilityMobileMalwareDetectionSoftwareUpgradeIncNum;

    @ExcelProperty(index = 253)
    private Double abilityMobileMalwareDetectionSoftwareUpgradeInc;

    @ExcelProperty(index = 254)
    private Double abilityMobileMalwareDetectionSoftwareUpgradeInv;

    @ExcelProperty(index = 255)
    private String abilityMobileMalwareDetectionSoftwareUpgradeRemark;

    /**
     *  1	移动上网日志留存-集采
     */
    @ExcelProperty(index = 256)
    private Double softwareMobileInternetLogRetentionCentralizedInv;

    @ExcelProperty(index = 257)
    private String softwareMobileInternetLogRetentionCentralizedRemark;

    /**
     *  2	移动上网日志留存-个性化省采
     */
    @ExcelProperty(index = 258)
    private Double softwareMobileInternetLogRetentionCustomizedInv;

    @ExcelProperty(index = 259)
    private String softwareMobileInternetLogRetentionCustomizedRemark;

    /**
     *  3	僵木蠕监测-平台总价
     */
    @ExcelProperty(index = 260)
    private Double softwareBotnetWormDetectionPlatformTotalPriceInv;

    @ExcelProperty(index = 261)
    private String softwareBotnetWormDetectionPlatformTotalPriceRemark;

    /**
     *  4	IDCISP-平台总价
     */
    @ExcelProperty(index = 262)
    private Double softwareIdcispPlatformTotalPriceInv;

    @ExcelProperty(index = 263)
    private String softwareIdcispPlatformTotalPriceRemark;

    /**
     *  5	移动DPI-平台总价
     */
    @ExcelProperty(index = 264)
    private Double softwareMobileDpiPlatformTotalPriceInv;

    @ExcelProperty(index = 265)
    private String softwareMobileDpiPlatformTotalPriceRemark;

    /**
     *  6	固网DPI-平台总价
     */
    @ExcelProperty(index = 266)
    private Double softwareFixedNetworkDpiPlatformTotalPriceInv;

    @ExcelProperty(index = 267)
    private String softwareFixedNetworkDpiPlatformTotalPriceRemark;

    /**
     *  7	移动恶意程序监测-平台总价
     */
    @ExcelProperty(index = 268)
    private Double softwareMobileMalwareDetectionPlatformTotalPriceInv;

    @ExcelProperty(index = 269)
    private String softwareMobileMalwareDetectionPlatformTotalPriceRemark;

    /**
     *  8	资产管理
     */
    @ExcelProperty(index = 270)
    private Double softwareAssetManagementInv;

    @ExcelProperty(index = 271)
    private String softwareAssetManagementRemark;

    /**
     *  9	基线管理
     */
    @ExcelProperty(index = 272)
    private Double softwareBaselineManagementInv;

    @ExcelProperty(index = 273)
    private String softwareBaselineManagementRemark;

    /**
     *  10	漏洞管理
     */
    @ExcelProperty(index = 274)
    private Double softwareVulnerabilityManagementInv;

    @ExcelProperty(index = 275)
    private String softwareVulnerabilityManagementRemark;

    /**
     *  11	互联网暴露面管理
     */
    @ExcelProperty(index = 276)
    private Double softwareInternetExposureManagementInv;

    @ExcelProperty(index = 277)
    private String softwareInternetExposureManagementRemark;

    /**
     *  12	内网资产测绘
     */
    @ExcelProperty(index = 278)
    private Double softwareInternalNetworkAssetMappingInv;

    @ExcelProperty(index = 279)
    private String softwareInternalNetworkAssetMappingRemark;

    /**
     *  13	4A
     */
    @ExcelProperty(index = 280)
    private Double softwareAaaaInv;

    @ExcelProperty(index = 281)
    private String softwareAaaaRemark;

    /**
     *  14	APP上线检测
     */
    @ExcelProperty(index = 282)
    private Double softwareAppReleaseDetectionInv;

    @ExcelProperty(index = 283)
    private String softwareAppReleaseDetectionRemark;

    /**
     *  15	数据资产管理
     */
    @ExcelProperty(index = 284)
    private Double softwareDataAssetManagementInv;

    @ExcelProperty(index = 285)
    private String softwareDataAssetManagementRemark;

    /**
     *  16	密码服务管理
     */
    @ExcelProperty(index = 286)
    private Double softwarePasswordServiceManagementInv;

    @ExcelProperty(index = 287)
    private String softwarePasswordServiceManagementRemark;

    /**
     *  17	威胁情报
     */
    @ExcelProperty(index = 288)
    private Double softwareThreatIntelligenceInv;

    @ExcelProperty(index = 289)
    private String softwareThreatIntelligenceRemark;

    /**
     *  18	网络安全态势感知
     */
    @ExcelProperty(index = 290)
    private Double softwareNetworkSecuritySituationalAwarenessInv;

    @ExcelProperty(index = 291)
    private String softwareNetworkSecuritySituationalAwarenessRemark;

    /**
     *  19	数据安全态势感知
     */
    @ExcelProperty(index = 292)
    private Double softwareDataSecuritySituationalAwarenessInv;

    @ExcelProperty(index = 293)
    private String softwareDataSecuritySituationalAwarenessRemark;

    /**
     *  20	互联网网站备案监测
     */
    @ExcelProperty(index = 294)
    private Double softwareWebsiteFilingMonitoringInv;

    @ExcelProperty(index = 295)
    private String softwareWebsiteFilingMonitoringRemark;

    /**
     *  21	不良信息监测
     */
    @ExcelProperty(index = 296)
    private Double softwareHarmfulInformationMonitoringInv;

    @ExcelProperty(index = 297)
    private String softwareHarmfulInformationMonitoringRemark;

    /**
     *  22	反诈管理
     */
    @ExcelProperty(index = 298)
    private Double softwareAntiFraudManagementInv;

    @ExcelProperty(index = 299)
    private String softwareAntiFraudManagementRemark;

    /**
     *  23	内容安全“先审后发”管控
     */
    @ExcelProperty(index = 300)
    private Double softwareContentSecurityReviewPublishControlInv;

    @ExcelProperty(index = 301)
    private String softwareContentSecurityReviewPublishControlRemark;

    /**
     *  24	一键处置（含一键派单、封堵、关停）
     */
    @ExcelProperty(index = 302)
    private Double softwareOneClickDisposalInv;

    @ExcelProperty(index = 303)
    private String softwareOneClickDisposalRemark;

    /**
     *  25	SOAR
     */
    @ExcelProperty(index = 304)
    private Double softwareSoarInv;

    @ExcelProperty(index = 305)
    private String softwareSoarRemark;

    /**
     *  26	网络攻击溯源
     */
    @ExcelProperty(index = 306)
    private Double softwareNetworkAttackTracingInv;

    @ExcelProperty(index = 307)
    private String softwareNetworkAttackTracingRemark;

    /**
     *  27	安全能力中心
     */
    @ExcelProperty(index = 308)
    private Double softwareSecurityCapabilityCenterInv;

    @ExcelProperty(index = 309)
    private String softwareSecurityCapabilityCenterRemark;

    /**
     *  28	安全数据中心
     */
    @ExcelProperty(index = 310)
    private Double softwareSecurityDataCenterInv;

    @ExcelProperty(index = 311)
    private String softwareSecurityDataCenterRemark;

    /**
     *  29	攻防演练
     */
    @ExcelProperty(index = 312)
    private Double softwareAttackDefenseDrillInv;

    @ExcelProperty(index = 313)
    private String softwareAttackDefenseDrillRemark;

    /**
     *  30	补丁管理（5GC内生）
     */
    @ExcelProperty(index = 314)
    private Double softwarePatchManagementCoreNativeInv;

    @ExcelProperty(index = 315)
    private String softwarePatchManagementCoreNativeRemark;

    /**
     *  31	补丁管理（5GC外挂）
     */
    @ExcelProperty(index = 316)
    private Double softwarePatchManagementCoreExternalInv;

    @ExcelProperty(index = 317)
    private String softwarePatchManagementCoreExternalRemark;

    /**
     *  32	漏洞管理（5GC内生）
     */
    @ExcelProperty(index = 318)
    private Double softwareVulnerabilityManagementCoreNativeInv;

    @ExcelProperty(index = 319)
    private String softwareVulnerabilityManagementCoreNativeRemark;

    /**
     *  33	资产管理（5GC内生）
     */
    @ExcelProperty(index = 320)
    private Double softwareAssetManagementCoreNativeInv;

    @ExcelProperty(index = 321)
    private String softwareAssetManagementCoreNativeRemark;

    /**
     *  34	态势感知（5GC内生）
     */
    @ExcelProperty(index = 322)
    private Double softwareSituationalAwarenessCoreNativeInv;

    @ExcelProperty(index = 323)
    private String softwareSituationalAwarenessCoreNativeRemark;

    /**
     *  35	UEBA（5GC用户行为分析）
     */
    @ExcelProperty(index = 324)
    private Double softwareUebaCoreInv;

    @ExcelProperty(index = 325)
    private String softwareUebaCoreRemark;

    //==============================================================================
    // extension
    //==============================================================================
    private Double abilityFirewallHardwareVar;

    private Double abilityFirewallAtomicCapabilityVar;

    private Double abilityIpsHardwareVar;

    private Double abilityIpsAtomicCapabilityVar;

    private Double abilityWafHardwareVar;

    private Double abilityWafDomesticHardwareVar;

    private Double abilityWafAtomicCapabilityVar;

    private Double abilityWebDynamicDefenseHardwareVar;

    private Double abilityWebDynamicDefenseSoftwareVar;

    private Double abilityTrafficScrubbingVar;

    private Double abilityFullTrafficAnalysisVar;

    private Double abilityBastionHostVar;

    private Double abilityLogAuditVar;

    private Double abilityEdrVar;

    private Double abilityHostVulnerabilityScanHardwareVar;

    private Double abilityHostVulnerabilityScanAtomicCapabilityVar;

    private Double abilityWebVulnerabilityScanHardwareVar;

    private Double abilityWebVulnerabilityScanAtomicCapabilityVar;

    private Double abilityContainerSecurityVar;

    private Double abilityContainerSecurityAgentVar;

    private Double abilityWebTamperPreventionVar;

    private Double abilityWebTamperPreventionAtomicCapabilityVar;

    private Double abilityApiGatewaySecurityVar;

    private Double abilityNetworkDlpVar;

    private Double abilityNetworkDomesticDlpVar;

    private Double abilityDynamicDataMaskingVar;

    private Double abilityStaticDataMaskingVar;

    private Double abilityDatabaseAuditVar;

    private Double abilitySignalingFirewallVar;

    private Double abilityZeroTrustSdpVar;

    private Double abilityZeroTrustDomesticSdpVar;

    private Double abilityHoneypotVar;

    private Double abilityDomesticHoneypotVar;

    private Double abilityMicroSegmentationVar;

    private Double abilityAbnormalTrafficDetectionForwardingDeviceVar;

    private Double abilityAbnormalTrafficDetectionCollectionDeviceVar;

    private Double abilityAbnormalTrafficDetectionReportDeviceVar;

    private Double abilityDataEncryptionDecryptionVar;

    private Double abilityBotnetWormDetectionDisposalDeviceVar;

    private Double abilityBotnetWormDetectionGatewayVar;

    private Double abilityBotnetWormDetectionSoftwareUpgradeVar;

    private Double abilityIdcispHardwareVar;

    private Double abilityIdcispSoftwareVar;

    private Double abilityMobileDpiHardwareVar;

    private Double abilityMobileDpiSoftwareVar;

    private Double abilityFixedNetworkDpiHardwareVar;

    private Double abilityFixedNetworkDpiSoftwareVar;

    private Double abilityMobileMalwareDetectionHardwareVar;

    private Double abilityMobileMalwareDetectionSoftwareVar;

    private Double abilityMobileMalwareDetectionSoftwareUpgradeVar;

    private Double softwareMobileInternetLogRetentionCentralizedVar;

    private Double softwareMobileInternetLogRetentionCustomizedVar;

    private Double softwareBotnetWormDetectionPlatformTotalPriceVar;

    private Double softwareIdcispPlatformTotalPriceVar;

    private Double softwareMobileDpiPlatformTotalPriceVar;

    private Double softwareFixedNetworkDpiPlatformTotalPriceVar;

    private Double softwareMobileMalwareDetectionPlatformTotalPriceVar;

    private Double softwareAssetManagementVar;

    private Double softwareBaselineManagementVar;

    private Double softwareVulnerabilityManagementVar;

    private Double softwareInternetExposureManagementVar;

    private Double softwareInternalNetworkAssetMappingVar;

    private Double softwareAaaaVar;

    private Double softwareAppReleaseDetectionVar;

    private Double softwareDataAssetManagementVar;

    private Double softwarePasswordServiceManagementVar;

    private Double softwareThreatIntelligenceVar;

    private Double softwareNetworkSecuritySituationalAwarenessVar;

    private Double softwareDataSecuritySituationalAwarenessVar;

    private Double softwareWebsiteFilingMonitoringVar;

    private Double softwareHarmfulInformationMonitoringVar;

    private Double softwareAntiFraudManagementVar;

    private Double softwareContentSecurityReviewPublishControlVar;

    private Double softwareOneClickDisposalVar;

    private Double softwareSoarVar;

    private Double softwareNetworkAttackTracingVar;

    private Double softwareSecurityCapabilityCenterVar;

    private Double softwareSecurityDataCenterVar;

    private Double softwareAttackDefenseDrillVar;

    private Double softwarePatchManagementCoreNativeVar;

    private Double softwarePatchManagementCoreExternalVar;

    private Double softwareVulnerabilityManagementCoreNativeVar;

    private Double softwareAssetManagementCoreNativeVar;

    private Double softwareSituationalAwarenessCoreNativeVar;

    private Double softwareUebaCoreVar;

    /**
     * 组织code
     */
    private String orgCode;

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getProvincialCompany() {
        return provincialCompany;
    }

    public void setProvincialCompany(String provincialCompany) {
        this.provincialCompany = provincialCompany;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Double getAutoProjectDesignReplyTotalInvestment() {
        return autoProjectDesignReplyTotalInvestment;
    }

    public void setAutoProjectDesignReplyTotalInvestment(Double autoProjectDesignReplyTotalInvestment) {
        this.autoProjectDesignReplyTotalInvestment = autoProjectDesignReplyTotalInvestment;
    }

    public Double getNonSecurityDeviceFee() {
        return nonSecurityDeviceFee;
    }

    public void setNonSecurityDeviceFee(Double nonSecurityDeviceFee) {
        this.nonSecurityDeviceFee = nonSecurityDeviceFee;
    }

    public Double getSecurityDeviceFee() {
        return securityDeviceFee;
    }

    public void setSecurityDeviceFee(Double securityDeviceFee) {
        this.securityDeviceFee = securityDeviceFee;
    }

    public Double getTotalOtherFee() {
        return totalOtherFee;
    }

    public void setTotalOtherFee(Double totalOtherFee) {
        this.totalOtherFee = totalOtherFee;
    }

    public Double getAutoSecurityDeviceOtherFee() {
        return autoSecurityDeviceOtherFee;
    }

    public void setAutoSecurityDeviceOtherFee(Double autoSecurityDeviceOtherFee) {
        this.autoSecurityDeviceOtherFee = autoSecurityDeviceOtherFee;
    }

    public String getAbilityFirewallHardwareVendor() {
        return abilityFirewallHardwareVendor;
    }

    public void setAbilityFirewallHardwareVendor(String abilityFirewallHardwareVendor) {
        this.abilityFirewallHardwareVendor = abilityFirewallHardwareVendor;
    }

    public Integer getAbilityFirewallHardwareIncNum() {
        return abilityFirewallHardwareIncNum;
    }

    public void setAbilityFirewallHardwareIncNum(Integer abilityFirewallHardwareIncNum) {
        this.abilityFirewallHardwareIncNum = abilityFirewallHardwareIncNum;
    }

    public Double getAbilityFirewallHardwareInc() {
        return abilityFirewallHardwareInc;
    }

    public void setAbilityFirewallHardwareInc(Double abilityFirewallHardwareInc) {
        this.abilityFirewallHardwareInc = abilityFirewallHardwareInc;
    }

    public Double getAbilityFirewallHardwareInv() {
        return abilityFirewallHardwareInv;
    }

    public void setAbilityFirewallHardwareInv(Double abilityFirewallHardwareInv) {
        this.abilityFirewallHardwareInv = abilityFirewallHardwareInv;
    }

    public String getAbilityFirewallHardwareRemark() {
        return abilityFirewallHardwareRemark;
    }

    public void setAbilityFirewallHardwareRemark(String abilityFirewallHardwareRemark) {
        this.abilityFirewallHardwareRemark = abilityFirewallHardwareRemark;
    }

    public String getAbilityFirewallAtomicCapabilityVendor() {
        return abilityFirewallAtomicCapabilityVendor;
    }

    public void setAbilityFirewallAtomicCapabilityVendor(String abilityFirewallAtomicCapabilityVendor) {
        this.abilityFirewallAtomicCapabilityVendor = abilityFirewallAtomicCapabilityVendor;
    }

    public Integer getAbilityFirewallAtomicCapabilityIncNum() {
        return abilityFirewallAtomicCapabilityIncNum;
    }

    public void setAbilityFirewallAtomicCapabilityIncNum(Integer abilityFirewallAtomicCapabilityIncNum) {
        this.abilityFirewallAtomicCapabilityIncNum = abilityFirewallAtomicCapabilityIncNum;
    }

    public Double getAbilityFirewallAtomicCapabilityInc() {
        return abilityFirewallAtomicCapabilityInc;
    }

    public void setAbilityFirewallAtomicCapabilityInc(Double abilityFirewallAtomicCapabilityInc) {
        this.abilityFirewallAtomicCapabilityInc = abilityFirewallAtomicCapabilityInc;
    }

    public Double getAbilityFirewallAtomicCapabilityInv() {
        return abilityFirewallAtomicCapabilityInv;
    }

    public void setAbilityFirewallAtomicCapabilityInv(Double abilityFirewallAtomicCapabilityInv) {
        this.abilityFirewallAtomicCapabilityInv = abilityFirewallAtomicCapabilityInv;
    }

    public String getAbilityFirewallAtomicCapabilityRemark() {
        return abilityFirewallAtomicCapabilityRemark;
    }

    public void setAbilityFirewallAtomicCapabilityRemark(String abilityFirewallAtomicCapabilityRemark) {
        this.abilityFirewallAtomicCapabilityRemark = abilityFirewallAtomicCapabilityRemark;
    }

    public String getAbilityIpsHardwareVendor() {
        return abilityIpsHardwareVendor;
    }

    public void setAbilityIpsHardwareVendor(String abilityIpsHardwareVendor) {
        this.abilityIpsHardwareVendor = abilityIpsHardwareVendor;
    }

    public Integer getAbilityIpsHardwareIncNum() {
        return abilityIpsHardwareIncNum;
    }

    public void setAbilityIpsHardwareIncNum(Integer abilityIpsHardwareIncNum) {
        this.abilityIpsHardwareIncNum = abilityIpsHardwareIncNum;
    }

    public Double getAbilityIpsHardwareInc() {
        return abilityIpsHardwareInc;
    }

    public void setAbilityIpsHardwareInc(Double abilityIpsHardwareInc) {
        this.abilityIpsHardwareInc = abilityIpsHardwareInc;
    }

    public Double getAbilityIpsHardwareInv() {
        return abilityIpsHardwareInv;
    }

    public void setAbilityIpsHardwareInv(Double abilityIpsHardwareInv) {
        this.abilityIpsHardwareInv = abilityIpsHardwareInv;
    }

    public String getAbilityIpsHardwareRemark() {
        return abilityIpsHardwareRemark;
    }

    public void setAbilityIpsHardwareRemark(String abilityIpsHardwareRemark) {
        this.abilityIpsHardwareRemark = abilityIpsHardwareRemark;
    }

    public String getAbilityIpsAtomicCapabilityVendor() {
        return abilityIpsAtomicCapabilityVendor;
    }

    public void setAbilityIpsAtomicCapabilityVendor(String abilityIpsAtomicCapabilityVendor) {
        this.abilityIpsAtomicCapabilityVendor = abilityIpsAtomicCapabilityVendor;
    }

    public Integer getAbilityIpsAtomicCapabilityIncNum() {
        return abilityIpsAtomicCapabilityIncNum;
    }

    public void setAbilityIpsAtomicCapabilityIncNum(Integer abilityIpsAtomicCapabilityIncNum) {
        this.abilityIpsAtomicCapabilityIncNum = abilityIpsAtomicCapabilityIncNum;
    }

    public Double getAbilityIpsAtomicCapabilityInc() {
        return abilityIpsAtomicCapabilityInc;
    }

    public void setAbilityIpsAtomicCapabilityInc(Double abilityIpsAtomicCapabilityInc) {
        this.abilityIpsAtomicCapabilityInc = abilityIpsAtomicCapabilityInc;
    }

    public Double getAbilityIpsAtomicCapabilityInv() {
        return abilityIpsAtomicCapabilityInv;
    }

    public void setAbilityIpsAtomicCapabilityInv(Double abilityIpsAtomicCapabilityInv) {
        this.abilityIpsAtomicCapabilityInv = abilityIpsAtomicCapabilityInv;
    }

    public String getAbilityIpsAtomicCapabilityRemark() {
        return abilityIpsAtomicCapabilityRemark;
    }

    public void setAbilityIpsAtomicCapabilityRemark(String abilityIpsAtomicCapabilityRemark) {
        this.abilityIpsAtomicCapabilityRemark = abilityIpsAtomicCapabilityRemark;
    }

    public String getAbilityWafHardwareVendor() {
        return abilityWafHardwareVendor;
    }

    public void setAbilityWafHardwareVendor(String abilityWafHardwareVendor) {
        this.abilityWafHardwareVendor = abilityWafHardwareVendor;
    }

    public Integer getAbilityWafHardwareIncNum() {
        return abilityWafHardwareIncNum;
    }

    public void setAbilityWafHardwareIncNum(Integer abilityWafHardwareIncNum) {
        this.abilityWafHardwareIncNum = abilityWafHardwareIncNum;
    }

    public Double getAbilityWafHardwareInc() {
        return abilityWafHardwareInc;
    }

    public void setAbilityWafHardwareInc(Double abilityWafHardwareInc) {
        this.abilityWafHardwareInc = abilityWafHardwareInc;
    }

    public Double getAbilityWafHardwareInv() {
        return abilityWafHardwareInv;
    }

    public void setAbilityWafHardwareInv(Double abilityWafHardwareInv) {
        this.abilityWafHardwareInv = abilityWafHardwareInv;
    }

    public String getAbilityWafHardwareRemark() {
        return abilityWafHardwareRemark;
    }

    public void setAbilityWafHardwareRemark(String abilityWafHardwareRemark) {
        this.abilityWafHardwareRemark = abilityWafHardwareRemark;
    }

    public String getAbilityWafDomesticHardwareVendor() {
        return abilityWafDomesticHardwareVendor;
    }

    public void setAbilityWafDomesticHardwareVendor(String abilityWafDomesticHardwareVendor) {
        this.abilityWafDomesticHardwareVendor = abilityWafDomesticHardwareVendor;
    }

    public Integer getAbilityWafDomesticHardwareIncNum() {
        return abilityWafDomesticHardwareIncNum;
    }

    public void setAbilityWafDomesticHardwareIncNum(Integer abilityWafDomesticHardwareIncNum) {
        this.abilityWafDomesticHardwareIncNum = abilityWafDomesticHardwareIncNum;
    }

    public Double getAbilityWafDomesticHardwareInc() {
        return abilityWafDomesticHardwareInc;
    }

    public void setAbilityWafDomesticHardwareInc(Double abilityWafDomesticHardwareInc) {
        this.abilityWafDomesticHardwareInc = abilityWafDomesticHardwareInc;
    }

    public Double getAbilityWafDomesticHardwareInv() {
        return abilityWafDomesticHardwareInv;
    }

    public void setAbilityWafDomesticHardwareInv(Double abilityWafDomesticHardwareInv) {
        this.abilityWafDomesticHardwareInv = abilityWafDomesticHardwareInv;
    }

    public String getAbilityWafDomesticHardwareRemark() {
        return abilityWafDomesticHardwareRemark;
    }

    public void setAbilityWafDomesticHardwareRemark(String abilityWafDomesticHardwareRemark) {
        this.abilityWafDomesticHardwareRemark = abilityWafDomesticHardwareRemark;
    }

    public String getAbilityWafAtomicCapabilityVendor() {
        return abilityWafAtomicCapabilityVendor;
    }

    public void setAbilityWafAtomicCapabilityVendor(String abilityWafAtomicCapabilityVendor) {
        this.abilityWafAtomicCapabilityVendor = abilityWafAtomicCapabilityVendor;
    }

    public Integer getAbilityWafAtomicCapabilityIncNum() {
        return abilityWafAtomicCapabilityIncNum;
    }

    public void setAbilityWafAtomicCapabilityIncNum(Integer abilityWafAtomicCapabilityIncNum) {
        this.abilityWafAtomicCapabilityIncNum = abilityWafAtomicCapabilityIncNum;
    }

    public Double getAbilityWafAtomicCapabilityInc() {
        return abilityWafAtomicCapabilityInc;
    }

    public void setAbilityWafAtomicCapabilityInc(Double abilityWafAtomicCapabilityInc) {
        this.abilityWafAtomicCapabilityInc = abilityWafAtomicCapabilityInc;
    }

    public Double getAbilityWafAtomicCapabilityInv() {
        return abilityWafAtomicCapabilityInv;
    }

    public void setAbilityWafAtomicCapabilityInv(Double abilityWafAtomicCapabilityInv) {
        this.abilityWafAtomicCapabilityInv = abilityWafAtomicCapabilityInv;
    }

    public String getAbilityWafAtomicCapabilityRemark() {
        return abilityWafAtomicCapabilityRemark;
    }

    public void setAbilityWafAtomicCapabilityRemark(String abilityWafAtomicCapabilityRemark) {
        this.abilityWafAtomicCapabilityRemark = abilityWafAtomicCapabilityRemark;
    }

    public String getAbilityWebDynamicDefenseHardwareVendor() {
        return abilityWebDynamicDefenseHardwareVendor;
    }

    public void setAbilityWebDynamicDefenseHardwareVendor(String abilityWebDynamicDefenseHardwareVendor) {
        this.abilityWebDynamicDefenseHardwareVendor = abilityWebDynamicDefenseHardwareVendor;
    }

    public Integer getAbilityWebDynamicDefenseHardwareIncNum() {
        return abilityWebDynamicDefenseHardwareIncNum;
    }

    public void setAbilityWebDynamicDefenseHardwareIncNum(Integer abilityWebDynamicDefenseHardwareIncNum) {
        this.abilityWebDynamicDefenseHardwareIncNum = abilityWebDynamicDefenseHardwareIncNum;
    }

    public Double getAbilityWebDynamicDefenseHardwareInc() {
        return abilityWebDynamicDefenseHardwareInc;
    }

    public void setAbilityWebDynamicDefenseHardwareInc(Double abilityWebDynamicDefenseHardwareInc) {
        this.abilityWebDynamicDefenseHardwareInc = abilityWebDynamicDefenseHardwareInc;
    }

    public Double getAbilityWebDynamicDefenseHardwareInv() {
        return abilityWebDynamicDefenseHardwareInv;
    }

    public void setAbilityWebDynamicDefenseHardwareInv(Double abilityWebDynamicDefenseHardwareInv) {
        this.abilityWebDynamicDefenseHardwareInv = abilityWebDynamicDefenseHardwareInv;
    }

    public String getAbilityWebDynamicDefenseHardwareRemark() {
        return abilityWebDynamicDefenseHardwareRemark;
    }

    public void setAbilityWebDynamicDefenseHardwareRemark(String abilityWebDynamicDefenseHardwareRemark) {
        this.abilityWebDynamicDefenseHardwareRemark = abilityWebDynamicDefenseHardwareRemark;
    }

    public String getAbilityWebDynamicDefenseSoftwareVendor() {
        return abilityWebDynamicDefenseSoftwareVendor;
    }

    public void setAbilityWebDynamicDefenseSoftwareVendor(String abilityWebDynamicDefenseSoftwareVendor) {
        this.abilityWebDynamicDefenseSoftwareVendor = abilityWebDynamicDefenseSoftwareVendor;
    }

    public Integer getAbilityWebDynamicDefenseSoftwareIncNum() {
        return abilityWebDynamicDefenseSoftwareIncNum;
    }

    public void setAbilityWebDynamicDefenseSoftwareIncNum(Integer abilityWebDynamicDefenseSoftwareIncNum) {
        this.abilityWebDynamicDefenseSoftwareIncNum = abilityWebDynamicDefenseSoftwareIncNum;
    }

    public Double getAbilityWebDynamicDefenseSoftwareInc() {
        return abilityWebDynamicDefenseSoftwareInc;
    }

    public void setAbilityWebDynamicDefenseSoftwareInc(Double abilityWebDynamicDefenseSoftwareInc) {
        this.abilityWebDynamicDefenseSoftwareInc = abilityWebDynamicDefenseSoftwareInc;
    }

    public Double getAbilityWebDynamicDefenseSoftwareInv() {
        return abilityWebDynamicDefenseSoftwareInv;
    }

    public void setAbilityWebDynamicDefenseSoftwareInv(Double abilityWebDynamicDefenseSoftwareInv) {
        this.abilityWebDynamicDefenseSoftwareInv = abilityWebDynamicDefenseSoftwareInv;
    }

    public String getAbilityWebDynamicDefenseSoftwareRemark() {
        return abilityWebDynamicDefenseSoftwareRemark;
    }

    public void setAbilityWebDynamicDefenseSoftwareRemark(String abilityWebDynamicDefenseSoftwareRemark) {
        this.abilityWebDynamicDefenseSoftwareRemark = abilityWebDynamicDefenseSoftwareRemark;
    }

    public String getAbilityTrafficScrubbingVendor() {
        return abilityTrafficScrubbingVendor;
    }

    public void setAbilityTrafficScrubbingVendor(String abilityTrafficScrubbingVendor) {
        this.abilityTrafficScrubbingVendor = abilityTrafficScrubbingVendor;
    }

    public Integer getAbilityTrafficScrubbingIncNum() {
        return abilityTrafficScrubbingIncNum;
    }

    public void setAbilityTrafficScrubbingIncNum(Integer abilityTrafficScrubbingIncNum) {
        this.abilityTrafficScrubbingIncNum = abilityTrafficScrubbingIncNum;
    }

    public Double getAbilityTrafficScrubbingInc() {
        return abilityTrafficScrubbingInc;
    }

    public void setAbilityTrafficScrubbingInc(Double abilityTrafficScrubbingInc) {
        this.abilityTrafficScrubbingInc = abilityTrafficScrubbingInc;
    }

    public Double getAbilityTrafficScrubbingInv() {
        return abilityTrafficScrubbingInv;
    }

    public void setAbilityTrafficScrubbingInv(Double abilityTrafficScrubbingInv) {
        this.abilityTrafficScrubbingInv = abilityTrafficScrubbingInv;
    }

    public String getAbilityTrafficScrubbingRemark() {
        return abilityTrafficScrubbingRemark;
    }

    public void setAbilityTrafficScrubbingRemark(String abilityTrafficScrubbingRemark) {
        this.abilityTrafficScrubbingRemark = abilityTrafficScrubbingRemark;
    }

    public String getAbilityFullTrafficAnalysisVendor() {
        return abilityFullTrafficAnalysisVendor;
    }

    public void setAbilityFullTrafficAnalysisVendor(String abilityFullTrafficAnalysisVendor) {
        this.abilityFullTrafficAnalysisVendor = abilityFullTrafficAnalysisVendor;
    }

    public Integer getAbilityFullTrafficAnalysisIncNum() {
        return abilityFullTrafficAnalysisIncNum;
    }

    public void setAbilityFullTrafficAnalysisIncNum(Integer abilityFullTrafficAnalysisIncNum) {
        this.abilityFullTrafficAnalysisIncNum = abilityFullTrafficAnalysisIncNum;
    }

    public Double getAbilityFullTrafficAnalysisInc() {
        return abilityFullTrafficAnalysisInc;
    }

    public void setAbilityFullTrafficAnalysisInc(Double abilityFullTrafficAnalysisInc) {
        this.abilityFullTrafficAnalysisInc = abilityFullTrafficAnalysisInc;
    }

    public Double getAbilityFullTrafficAnalysisInv() {
        return abilityFullTrafficAnalysisInv;
    }

    public void setAbilityFullTrafficAnalysisInv(Double abilityFullTrafficAnalysisInv) {
        this.abilityFullTrafficAnalysisInv = abilityFullTrafficAnalysisInv;
    }

    public String getAbilityFullTrafficAnalysisRemark() {
        return abilityFullTrafficAnalysisRemark;
    }

    public void setAbilityFullTrafficAnalysisRemark(String abilityFullTrafficAnalysisRemark) {
        this.abilityFullTrafficAnalysisRemark = abilityFullTrafficAnalysisRemark;
    }

    public String getAbilityBastionHostVendor() {
        return abilityBastionHostVendor;
    }

    public void setAbilityBastionHostVendor(String abilityBastionHostVendor) {
        this.abilityBastionHostVendor = abilityBastionHostVendor;
    }

    public Integer getAbilityBastionHostIncNum() {
        return abilityBastionHostIncNum;
    }

    public void setAbilityBastionHostIncNum(Integer abilityBastionHostIncNum) {
        this.abilityBastionHostIncNum = abilityBastionHostIncNum;
    }

    public Double getAbilityBastionHostInc() {
        return abilityBastionHostInc;
    }

    public void setAbilityBastionHostInc(Double abilityBastionHostInc) {
        this.abilityBastionHostInc = abilityBastionHostInc;
    }

    public Double getAbilityBastionHostInv() {
        return abilityBastionHostInv;
    }

    public void setAbilityBastionHostInv(Double abilityBastionHostInv) {
        this.abilityBastionHostInv = abilityBastionHostInv;
    }

    public String getAbilityBastionHostRemark() {
        return abilityBastionHostRemark;
    }

    public void setAbilityBastionHostRemark(String abilityBastionHostRemark) {
        this.abilityBastionHostRemark = abilityBastionHostRemark;
    }

    public String getAbilityLogAuditVendor() {
        return abilityLogAuditVendor;
    }

    public void setAbilityLogAuditVendor(String abilityLogAuditVendor) {
        this.abilityLogAuditVendor = abilityLogAuditVendor;
    }

    public Integer getAbilityLogAuditIncNum() {
        return abilityLogAuditIncNum;
    }

    public void setAbilityLogAuditIncNum(Integer abilityLogAuditIncNum) {
        this.abilityLogAuditIncNum = abilityLogAuditIncNum;
    }

    public Double getAbilityLogAuditInc() {
        return abilityLogAuditInc;
    }

    public void setAbilityLogAuditInc(Double abilityLogAuditInc) {
        this.abilityLogAuditInc = abilityLogAuditInc;
    }

    public Double getAbilityLogAuditInv() {
        return abilityLogAuditInv;
    }

    public void setAbilityLogAuditInv(Double abilityLogAuditInv) {
        this.abilityLogAuditInv = abilityLogAuditInv;
    }

    public String getAbilityLogAuditRemark() {
        return abilityLogAuditRemark;
    }

    public void setAbilityLogAuditRemark(String abilityLogAuditRemark) {
        this.abilityLogAuditRemark = abilityLogAuditRemark;
    }

    public String getAbilityEdrVendor() {
        return abilityEdrVendor;
    }

    public void setAbilityEdrVendor(String abilityEdrVendor) {
        this.abilityEdrVendor = abilityEdrVendor;
    }

    public Integer getAbilityEdrIncNum() {
        return abilityEdrIncNum;
    }

    public void setAbilityEdrIncNum(Integer abilityEdrIncNum) {
        this.abilityEdrIncNum = abilityEdrIncNum;
    }

    public Double getAbilityEdrInc() {
        return abilityEdrInc;
    }

    public void setAbilityEdrInc(Double abilityEdrInc) {
        this.abilityEdrInc = abilityEdrInc;
    }

    public Double getAbilityEdrInv() {
        return abilityEdrInv;
    }

    public void setAbilityEdrInv(Double abilityEdrInv) {
        this.abilityEdrInv = abilityEdrInv;
    }

    public String getAbilityEdrRemark() {
        return abilityEdrRemark;
    }

    public void setAbilityEdrRemark(String abilityEdrRemark) {
        this.abilityEdrRemark = abilityEdrRemark;
    }

    public String getAbilityHostVulnerabilityScanHardwareVendor() {
        return abilityHostVulnerabilityScanHardwareVendor;
    }

    public void setAbilityHostVulnerabilityScanHardwareVendor(String abilityHostVulnerabilityScanHardwareVendor) {
        this.abilityHostVulnerabilityScanHardwareVendor = abilityHostVulnerabilityScanHardwareVendor;
    }

    public Integer getAbilityHostVulnerabilityScanHardwareIncNum() {
        return abilityHostVulnerabilityScanHardwareIncNum;
    }

    public void setAbilityHostVulnerabilityScanHardwareIncNum(Integer abilityHostVulnerabilityScanHardwareIncNum) {
        this.abilityHostVulnerabilityScanHardwareIncNum = abilityHostVulnerabilityScanHardwareIncNum;
    }

    public Double getAbilityHostVulnerabilityScanHardwareInc() {
        return abilityHostVulnerabilityScanHardwareInc;
    }

    public void setAbilityHostVulnerabilityScanHardwareInc(Double abilityHostVulnerabilityScanHardwareInc) {
        this.abilityHostVulnerabilityScanHardwareInc = abilityHostVulnerabilityScanHardwareInc;
    }

    public Double getAbilityHostVulnerabilityScanHardwareInv() {
        return abilityHostVulnerabilityScanHardwareInv;
    }

    public void setAbilityHostVulnerabilityScanHardwareInv(Double abilityHostVulnerabilityScanHardwareInv) {
        this.abilityHostVulnerabilityScanHardwareInv = abilityHostVulnerabilityScanHardwareInv;
    }

    public String getAbilityHostVulnerabilityScanHardwareRemark() {
        return abilityHostVulnerabilityScanHardwareRemark;
    }

    public void setAbilityHostVulnerabilityScanHardwareRemark(String abilityHostVulnerabilityScanHardwareRemark) {
        this.abilityHostVulnerabilityScanHardwareRemark = abilityHostVulnerabilityScanHardwareRemark;
    }

    public String getAbilityHostVulnerabilityScanAtomicCapabilityVendor() {
        return abilityHostVulnerabilityScanAtomicCapabilityVendor;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityVendor(String abilityHostVulnerabilityScanAtomicCapabilityVendor) {
        this.abilityHostVulnerabilityScanAtomicCapabilityVendor = abilityHostVulnerabilityScanAtomicCapabilityVendor;
    }

    public Integer getAbilityHostVulnerabilityScanAtomicCapabilityIncNum() {
        return abilityHostVulnerabilityScanAtomicCapabilityIncNum;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityIncNum(Integer abilityHostVulnerabilityScanAtomicCapabilityIncNum) {
        this.abilityHostVulnerabilityScanAtomicCapabilityIncNum = abilityHostVulnerabilityScanAtomicCapabilityIncNum;
    }

    public Double getAbilityHostVulnerabilityScanAtomicCapabilityInc() {
        return abilityHostVulnerabilityScanAtomicCapabilityInc;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityInc(Double abilityHostVulnerabilityScanAtomicCapabilityInc) {
        this.abilityHostVulnerabilityScanAtomicCapabilityInc = abilityHostVulnerabilityScanAtomicCapabilityInc;
    }

    public Double getAbilityHostVulnerabilityScanAtomicCapabilityInv() {
        return abilityHostVulnerabilityScanAtomicCapabilityInv;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityInv(Double abilityHostVulnerabilityScanAtomicCapabilityInv) {
        this.abilityHostVulnerabilityScanAtomicCapabilityInv = abilityHostVulnerabilityScanAtomicCapabilityInv;
    }

    public String getAbilityHostVulnerabilityScanAtomicCapabilityRemark() {
        return abilityHostVulnerabilityScanAtomicCapabilityRemark;
    }

    public void setAbilityHostVulnerabilityScanAtomicCapabilityRemark(String abilityHostVulnerabilityScanAtomicCapabilityRemark) {
        this.abilityHostVulnerabilityScanAtomicCapabilityRemark = abilityHostVulnerabilityScanAtomicCapabilityRemark;
    }

    public String getAbilityWebVulnerabilityScanHardwareVendor() {
        return abilityWebVulnerabilityScanHardwareVendor;
    }

    public void setAbilityWebVulnerabilityScanHardwareVendor(String abilityWebVulnerabilityScanHardwareVendor) {
        this.abilityWebVulnerabilityScanHardwareVendor = abilityWebVulnerabilityScanHardwareVendor;
    }

    public Integer getAbilityWebVulnerabilityScanHardwareIncNum() {
        return abilityWebVulnerabilityScanHardwareIncNum;
    }

    public void setAbilityWebVulnerabilityScanHardwareIncNum(Integer abilityWebVulnerabilityScanHardwareIncNum) {
        this.abilityWebVulnerabilityScanHardwareIncNum = abilityWebVulnerabilityScanHardwareIncNum;
    }

    public Double getAbilityWebVulnerabilityScanHardwareInc() {
        return abilityWebVulnerabilityScanHardwareInc;
    }

    public void setAbilityWebVulnerabilityScanHardwareInc(Double abilityWebVulnerabilityScanHardwareInc) {
        this.abilityWebVulnerabilityScanHardwareInc = abilityWebVulnerabilityScanHardwareInc;
    }

    public Double getAbilityWebVulnerabilityScanHardwareInv() {
        return abilityWebVulnerabilityScanHardwareInv;
    }

    public void setAbilityWebVulnerabilityScanHardwareInv(Double abilityWebVulnerabilityScanHardwareInv) {
        this.abilityWebVulnerabilityScanHardwareInv = abilityWebVulnerabilityScanHardwareInv;
    }

    public String getAbilityWebVulnerabilityScanHardwareRemark() {
        return abilityWebVulnerabilityScanHardwareRemark;
    }

    public void setAbilityWebVulnerabilityScanHardwareRemark(String abilityWebVulnerabilityScanHardwareRemark) {
        this.abilityWebVulnerabilityScanHardwareRemark = abilityWebVulnerabilityScanHardwareRemark;
    }

    public String getAbilityWebVulnerabilityScanAtomicCapabilityVendor() {
        return abilityWebVulnerabilityScanAtomicCapabilityVendor;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityVendor(String abilityWebVulnerabilityScanAtomicCapabilityVendor) {
        this.abilityWebVulnerabilityScanAtomicCapabilityVendor = abilityWebVulnerabilityScanAtomicCapabilityVendor;
    }

    public Integer getAbilityWebVulnerabilityScanAtomicCapabilityIncNum() {
        return abilityWebVulnerabilityScanAtomicCapabilityIncNum;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityIncNum(Integer abilityWebVulnerabilityScanAtomicCapabilityIncNum) {
        this.abilityWebVulnerabilityScanAtomicCapabilityIncNum = abilityWebVulnerabilityScanAtomicCapabilityIncNum;
    }

    public Double getAbilityWebVulnerabilityScanAtomicCapabilityInc() {
        return abilityWebVulnerabilityScanAtomicCapabilityInc;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityInc(Double abilityWebVulnerabilityScanAtomicCapabilityInc) {
        this.abilityWebVulnerabilityScanAtomicCapabilityInc = abilityWebVulnerabilityScanAtomicCapabilityInc;
    }

    public Double getAbilityWebVulnerabilityScanAtomicCapabilityInv() {
        return abilityWebVulnerabilityScanAtomicCapabilityInv;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityInv(Double abilityWebVulnerabilityScanAtomicCapabilityInv) {
        this.abilityWebVulnerabilityScanAtomicCapabilityInv = abilityWebVulnerabilityScanAtomicCapabilityInv;
    }

    public String getAbilityWebVulnerabilityScanAtomicCapabilityRemark() {
        return abilityWebVulnerabilityScanAtomicCapabilityRemark;
    }

    public void setAbilityWebVulnerabilityScanAtomicCapabilityRemark(String abilityWebVulnerabilityScanAtomicCapabilityRemark) {
        this.abilityWebVulnerabilityScanAtomicCapabilityRemark = abilityWebVulnerabilityScanAtomicCapabilityRemark;
    }

    public String getAbilityContainerSecurityVendor() {
        return abilityContainerSecurityVendor;
    }

    public void setAbilityContainerSecurityVendor(String abilityContainerSecurityVendor) {
        this.abilityContainerSecurityVendor = abilityContainerSecurityVendor;
    }

    public Integer getAbilityContainerSecurityIncNum() {
        return abilityContainerSecurityIncNum;
    }

    public void setAbilityContainerSecurityIncNum(Integer abilityContainerSecurityIncNum) {
        this.abilityContainerSecurityIncNum = abilityContainerSecurityIncNum;
    }

    public Double getAbilityContainerSecurityInc() {
        return abilityContainerSecurityInc;
    }

    public void setAbilityContainerSecurityInc(Double abilityContainerSecurityInc) {
        this.abilityContainerSecurityInc = abilityContainerSecurityInc;
    }

    public Double getAbilityContainerSecurityInv() {
        return abilityContainerSecurityInv;
    }

    public void setAbilityContainerSecurityInv(Double abilityContainerSecurityInv) {
        this.abilityContainerSecurityInv = abilityContainerSecurityInv;
    }

    public String getAbilityContainerSecurityRemark() {
        return abilityContainerSecurityRemark;
    }

    public void setAbilityContainerSecurityRemark(String abilityContainerSecurityRemark) {
        this.abilityContainerSecurityRemark = abilityContainerSecurityRemark;
    }

    public String getAbilityContainerSecurityAgentVendor() {
        return abilityContainerSecurityAgentVendor;
    }

    public void setAbilityContainerSecurityAgentVendor(String abilityContainerSecurityAgentVendor) {
        this.abilityContainerSecurityAgentVendor = abilityContainerSecurityAgentVendor;
    }

    public Integer getAbilityContainerSecurityAgentIncNum() {
        return abilityContainerSecurityAgentIncNum;
    }

    public void setAbilityContainerSecurityAgentIncNum(Integer abilityContainerSecurityAgentIncNum) {
        this.abilityContainerSecurityAgentIncNum = abilityContainerSecurityAgentIncNum;
    }

    public Double getAbilityContainerSecurityAgentInc() {
        return abilityContainerSecurityAgentInc;
    }

    public void setAbilityContainerSecurityAgentInc(Double abilityContainerSecurityAgentInc) {
        this.abilityContainerSecurityAgentInc = abilityContainerSecurityAgentInc;
    }

    public Double getAbilityContainerSecurityAgentInv() {
        return abilityContainerSecurityAgentInv;
    }

    public void setAbilityContainerSecurityAgentInv(Double abilityContainerSecurityAgentInv) {
        this.abilityContainerSecurityAgentInv = abilityContainerSecurityAgentInv;
    }

    public String getAbilityContainerSecurityAgentRemark() {
        return abilityContainerSecurityAgentRemark;
    }

    public void setAbilityContainerSecurityAgentRemark(String abilityContainerSecurityAgentRemark) {
        this.abilityContainerSecurityAgentRemark = abilityContainerSecurityAgentRemark;
    }

    public String getAbilityWebTamperPreventionVendor() {
        return abilityWebTamperPreventionVendor;
    }

    public void setAbilityWebTamperPreventionVendor(String abilityWebTamperPreventionVendor) {
        this.abilityWebTamperPreventionVendor = abilityWebTamperPreventionVendor;
    }

    public Integer getAbilityWebTamperPreventionIncNum() {
        return abilityWebTamperPreventionIncNum;
    }

    public void setAbilityWebTamperPreventionIncNum(Integer abilityWebTamperPreventionIncNum) {
        this.abilityWebTamperPreventionIncNum = abilityWebTamperPreventionIncNum;
    }

    public Double getAbilityWebTamperPreventionInc() {
        return abilityWebTamperPreventionInc;
    }

    public void setAbilityWebTamperPreventionInc(Double abilityWebTamperPreventionInc) {
        this.abilityWebTamperPreventionInc = abilityWebTamperPreventionInc;
    }

    public Double getAbilityWebTamperPreventionInv() {
        return abilityWebTamperPreventionInv;
    }

    public void setAbilityWebTamperPreventionInv(Double abilityWebTamperPreventionInv) {
        this.abilityWebTamperPreventionInv = abilityWebTamperPreventionInv;
    }

    public String getAbilityWebTamperPreventionRemark() {
        return abilityWebTamperPreventionRemark;
    }

    public void setAbilityWebTamperPreventionRemark(String abilityWebTamperPreventionRemark) {
        this.abilityWebTamperPreventionRemark = abilityWebTamperPreventionRemark;
    }

    public String getAbilityWebTamperPreventionAtomicCapabilityVendor() {
        return abilityWebTamperPreventionAtomicCapabilityVendor;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityVendor(String abilityWebTamperPreventionAtomicCapabilityVendor) {
        this.abilityWebTamperPreventionAtomicCapabilityVendor = abilityWebTamperPreventionAtomicCapabilityVendor;
    }

    public Integer getAbilityWebTamperPreventionAtomicCapabilityIncNum() {
        return abilityWebTamperPreventionAtomicCapabilityIncNum;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityIncNum(Integer abilityWebTamperPreventionAtomicCapabilityIncNum) {
        this.abilityWebTamperPreventionAtomicCapabilityIncNum = abilityWebTamperPreventionAtomicCapabilityIncNum;
    }

    public Double getAbilityWebTamperPreventionAtomicCapabilityInc() {
        return abilityWebTamperPreventionAtomicCapabilityInc;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityInc(Double abilityWebTamperPreventionAtomicCapabilityInc) {
        this.abilityWebTamperPreventionAtomicCapabilityInc = abilityWebTamperPreventionAtomicCapabilityInc;
    }

    public Double getAbilityWebTamperPreventionAtomicCapabilityInv() {
        return abilityWebTamperPreventionAtomicCapabilityInv;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityInv(Double abilityWebTamperPreventionAtomicCapabilityInv) {
        this.abilityWebTamperPreventionAtomicCapabilityInv = abilityWebTamperPreventionAtomicCapabilityInv;
    }

    public String getAbilityWebTamperPreventionAtomicCapabilityRemark() {
        return abilityWebTamperPreventionAtomicCapabilityRemark;
    }

    public void setAbilityWebTamperPreventionAtomicCapabilityRemark(String abilityWebTamperPreventionAtomicCapabilityRemark) {
        this.abilityWebTamperPreventionAtomicCapabilityRemark = abilityWebTamperPreventionAtomicCapabilityRemark;
    }

    public String getAbilityApiGatewaySecurityVendor() {
        return abilityApiGatewaySecurityVendor;
    }

    public void setAbilityApiGatewaySecurityVendor(String abilityApiGatewaySecurityVendor) {
        this.abilityApiGatewaySecurityVendor = abilityApiGatewaySecurityVendor;
    }

    public Integer getAbilityApiGatewaySecurityIncNum() {
        return abilityApiGatewaySecurityIncNum;
    }

    public void setAbilityApiGatewaySecurityIncNum(Integer abilityApiGatewaySecurityIncNum) {
        this.abilityApiGatewaySecurityIncNum = abilityApiGatewaySecurityIncNum;
    }

    public Double getAbilityApiGatewaySecurityInc() {
        return abilityApiGatewaySecurityInc;
    }

    public void setAbilityApiGatewaySecurityInc(Double abilityApiGatewaySecurityInc) {
        this.abilityApiGatewaySecurityInc = abilityApiGatewaySecurityInc;
    }

    public Double getAbilityApiGatewaySecurityInv() {
        return abilityApiGatewaySecurityInv;
    }

    public void setAbilityApiGatewaySecurityInv(Double abilityApiGatewaySecurityInv) {
        this.abilityApiGatewaySecurityInv = abilityApiGatewaySecurityInv;
    }

    public String getAbilityApiGatewaySecurityRemark() {
        return abilityApiGatewaySecurityRemark;
    }

    public void setAbilityApiGatewaySecurityRemark(String abilityApiGatewaySecurityRemark) {
        this.abilityApiGatewaySecurityRemark = abilityApiGatewaySecurityRemark;
    }

    public String getAbilityNetworkDlpVendor() {
        return abilityNetworkDlpVendor;
    }

    public void setAbilityNetworkDlpVendor(String abilityNetworkDlpVendor) {
        this.abilityNetworkDlpVendor = abilityNetworkDlpVendor;
    }

    public Integer getAbilityNetworkDlpIncNum() {
        return abilityNetworkDlpIncNum;
    }

    public void setAbilityNetworkDlpIncNum(Integer abilityNetworkDlpIncNum) {
        this.abilityNetworkDlpIncNum = abilityNetworkDlpIncNum;
    }

    public Double getAbilityNetworkDlpInc() {
        return abilityNetworkDlpInc;
    }

    public void setAbilityNetworkDlpInc(Double abilityNetworkDlpInc) {
        this.abilityNetworkDlpInc = abilityNetworkDlpInc;
    }

    public Double getAbilityNetworkDlpInv() {
        return abilityNetworkDlpInv;
    }

    public void setAbilityNetworkDlpInv(Double abilityNetworkDlpInv) {
        this.abilityNetworkDlpInv = abilityNetworkDlpInv;
    }

    public String getAbilityNetworkDlpRemark() {
        return abilityNetworkDlpRemark;
    }

    public void setAbilityNetworkDlpRemark(String abilityNetworkDlpRemark) {
        this.abilityNetworkDlpRemark = abilityNetworkDlpRemark;
    }

    public String getAbilityNetworkDomesticDlpVendor() {
        return abilityNetworkDomesticDlpVendor;
    }

    public void setAbilityNetworkDomesticDlpVendor(String abilityNetworkDomesticDlpVendor) {
        this.abilityNetworkDomesticDlpVendor = abilityNetworkDomesticDlpVendor;
    }

    public Integer getAbilityNetworkDomesticDlpIncNum() {
        return abilityNetworkDomesticDlpIncNum;
    }

    public void setAbilityNetworkDomesticDlpIncNum(Integer abilityNetworkDomesticDlpIncNum) {
        this.abilityNetworkDomesticDlpIncNum = abilityNetworkDomesticDlpIncNum;
    }

    public Double getAbilityNetworkDomesticDlpInc() {
        return abilityNetworkDomesticDlpInc;
    }

    public void setAbilityNetworkDomesticDlpInc(Double abilityNetworkDomesticDlpInc) {
        this.abilityNetworkDomesticDlpInc = abilityNetworkDomesticDlpInc;
    }

    public Double getAbilityNetworkDomesticDlpInv() {
        return abilityNetworkDomesticDlpInv;
    }

    public void setAbilityNetworkDomesticDlpInv(Double abilityNetworkDomesticDlpInv) {
        this.abilityNetworkDomesticDlpInv = abilityNetworkDomesticDlpInv;
    }

    public String getAbilityNetworkDomesticDlpRemark() {
        return abilityNetworkDomesticDlpRemark;
    }

    public void setAbilityNetworkDomesticDlpRemark(String abilityNetworkDomesticDlpRemark) {
        this.abilityNetworkDomesticDlpRemark = abilityNetworkDomesticDlpRemark;
    }

    public String getAbilityDynamicDataMaskingVendor() {
        return abilityDynamicDataMaskingVendor;
    }

    public void setAbilityDynamicDataMaskingVendor(String abilityDynamicDataMaskingVendor) {
        this.abilityDynamicDataMaskingVendor = abilityDynamicDataMaskingVendor;
    }

    public Integer getAbilityDynamicDataMaskingIncNum() {
        return abilityDynamicDataMaskingIncNum;
    }

    public void setAbilityDynamicDataMaskingIncNum(Integer abilityDynamicDataMaskingIncNum) {
        this.abilityDynamicDataMaskingIncNum = abilityDynamicDataMaskingIncNum;
    }

    public Double getAbilityDynamicDataMaskingInc() {
        return abilityDynamicDataMaskingInc;
    }

    public void setAbilityDynamicDataMaskingInc(Double abilityDynamicDataMaskingInc) {
        this.abilityDynamicDataMaskingInc = abilityDynamicDataMaskingInc;
    }

    public Double getAbilityDynamicDataMaskingInv() {
        return abilityDynamicDataMaskingInv;
    }

    public void setAbilityDynamicDataMaskingInv(Double abilityDynamicDataMaskingInv) {
        this.abilityDynamicDataMaskingInv = abilityDynamicDataMaskingInv;
    }

    public String getAbilityDynamicDataMaskingRemark() {
        return abilityDynamicDataMaskingRemark;
    }

    public void setAbilityDynamicDataMaskingRemark(String abilityDynamicDataMaskingRemark) {
        this.abilityDynamicDataMaskingRemark = abilityDynamicDataMaskingRemark;
    }

    public String getAbilityStaticDataMaskingVendor() {
        return abilityStaticDataMaskingVendor;
    }

    public void setAbilityStaticDataMaskingVendor(String abilityStaticDataMaskingVendor) {
        this.abilityStaticDataMaskingVendor = abilityStaticDataMaskingVendor;
    }

    public Integer getAbilityStaticDataMaskingIncNum() {
        return abilityStaticDataMaskingIncNum;
    }

    public void setAbilityStaticDataMaskingIncNum(Integer abilityStaticDataMaskingIncNum) {
        this.abilityStaticDataMaskingIncNum = abilityStaticDataMaskingIncNum;
    }

    public Double getAbilityStaticDataMaskingInc() {
        return abilityStaticDataMaskingInc;
    }

    public void setAbilityStaticDataMaskingInc(Double abilityStaticDataMaskingInc) {
        this.abilityStaticDataMaskingInc = abilityStaticDataMaskingInc;
    }

    public Double getAbilityStaticDataMaskingInv() {
        return abilityStaticDataMaskingInv;
    }

    public void setAbilityStaticDataMaskingInv(Double abilityStaticDataMaskingInv) {
        this.abilityStaticDataMaskingInv = abilityStaticDataMaskingInv;
    }

    public String getAbilityStaticDataMaskingRemark() {
        return abilityStaticDataMaskingRemark;
    }

    public void setAbilityStaticDataMaskingRemark(String abilityStaticDataMaskingRemark) {
        this.abilityStaticDataMaskingRemark = abilityStaticDataMaskingRemark;
    }

    public String getAbilityDatabaseAuditVendor() {
        return abilityDatabaseAuditVendor;
    }

    public void setAbilityDatabaseAuditVendor(String abilityDatabaseAuditVendor) {
        this.abilityDatabaseAuditVendor = abilityDatabaseAuditVendor;
    }

    public Integer getAbilityDatabaseAuditIncNum() {
        return abilityDatabaseAuditIncNum;
    }

    public void setAbilityDatabaseAuditIncNum(Integer abilityDatabaseAuditIncNum) {
        this.abilityDatabaseAuditIncNum = abilityDatabaseAuditIncNum;
    }

    public Double getAbilityDatabaseAuditInc() {
        return abilityDatabaseAuditInc;
    }

    public void setAbilityDatabaseAuditInc(Double abilityDatabaseAuditInc) {
        this.abilityDatabaseAuditInc = abilityDatabaseAuditInc;
    }

    public Double getAbilityDatabaseAuditInv() {
        return abilityDatabaseAuditInv;
    }

    public void setAbilityDatabaseAuditInv(Double abilityDatabaseAuditInv) {
        this.abilityDatabaseAuditInv = abilityDatabaseAuditInv;
    }

    public String getAbilityDatabaseAuditRemark() {
        return abilityDatabaseAuditRemark;
    }

    public void setAbilityDatabaseAuditRemark(String abilityDatabaseAuditRemark) {
        this.abilityDatabaseAuditRemark = abilityDatabaseAuditRemark;
    }

    public String getAbilitySignalingFirewallVendor() {
        return abilitySignalingFirewallVendor;
    }

    public void setAbilitySignalingFirewallVendor(String abilitySignalingFirewallVendor) {
        this.abilitySignalingFirewallVendor = abilitySignalingFirewallVendor;
    }

    public Integer getAbilitySignalingFirewallIncNum() {
        return abilitySignalingFirewallIncNum;
    }

    public void setAbilitySignalingFirewallIncNum(Integer abilitySignalingFirewallIncNum) {
        this.abilitySignalingFirewallIncNum = abilitySignalingFirewallIncNum;
    }

    public Double getAbilitySignalingFirewallInc() {
        return abilitySignalingFirewallInc;
    }

    public void setAbilitySignalingFirewallInc(Double abilitySignalingFirewallInc) {
        this.abilitySignalingFirewallInc = abilitySignalingFirewallInc;
    }

    public Double getAbilitySignalingFirewallInv() {
        return abilitySignalingFirewallInv;
    }

    public void setAbilitySignalingFirewallInv(Double abilitySignalingFirewallInv) {
        this.abilitySignalingFirewallInv = abilitySignalingFirewallInv;
    }

    public String getAbilitySignalingFirewallRemark() {
        return abilitySignalingFirewallRemark;
    }

    public void setAbilitySignalingFirewallRemark(String abilitySignalingFirewallRemark) {
        this.abilitySignalingFirewallRemark = abilitySignalingFirewallRemark;
    }

    public String getAbilityZeroTrustSdpVendor() {
        return abilityZeroTrustSdpVendor;
    }

    public void setAbilityZeroTrustSdpVendor(String abilityZeroTrustSdpVendor) {
        this.abilityZeroTrustSdpVendor = abilityZeroTrustSdpVendor;
    }

    public Integer getAbilityZeroTrustSdpIncNum() {
        return abilityZeroTrustSdpIncNum;
    }

    public void setAbilityZeroTrustSdpIncNum(Integer abilityZeroTrustSdpIncNum) {
        this.abilityZeroTrustSdpIncNum = abilityZeroTrustSdpIncNum;
    }

    public Double getAbilityZeroTrustSdpInc() {
        return abilityZeroTrustSdpInc;
    }

    public void setAbilityZeroTrustSdpInc(Double abilityZeroTrustSdpInc) {
        this.abilityZeroTrustSdpInc = abilityZeroTrustSdpInc;
    }

    public Double getAbilityZeroTrustSdpInv() {
        return abilityZeroTrustSdpInv;
    }

    public void setAbilityZeroTrustSdpInv(Double abilityZeroTrustSdpInv) {
        this.abilityZeroTrustSdpInv = abilityZeroTrustSdpInv;
    }

    public String getAbilityZeroTrustSdpRemark() {
        return abilityZeroTrustSdpRemark;
    }

    public void setAbilityZeroTrustSdpRemark(String abilityZeroTrustSdpRemark) {
        this.abilityZeroTrustSdpRemark = abilityZeroTrustSdpRemark;
    }

    public String getAbilityZeroTrustDomesticSdpVendor() {
        return abilityZeroTrustDomesticSdpVendor;
    }

    public void setAbilityZeroTrustDomesticSdpVendor(String abilityZeroTrustDomesticSdpVendor) {
        this.abilityZeroTrustDomesticSdpVendor = abilityZeroTrustDomesticSdpVendor;
    }

    public Integer getAbilityZeroTrustDomesticSdpIncNum() {
        return abilityZeroTrustDomesticSdpIncNum;
    }

    public void setAbilityZeroTrustDomesticSdpIncNum(Integer abilityZeroTrustDomesticSdpIncNum) {
        this.abilityZeroTrustDomesticSdpIncNum = abilityZeroTrustDomesticSdpIncNum;
    }

    public Double getAbilityZeroTrustDomesticSdpInc() {
        return abilityZeroTrustDomesticSdpInc;
    }

    public void setAbilityZeroTrustDomesticSdpInc(Double abilityZeroTrustDomesticSdpInc) {
        this.abilityZeroTrustDomesticSdpInc = abilityZeroTrustDomesticSdpInc;
    }

    public Double getAbilityZeroTrustDomesticSdpInv() {
        return abilityZeroTrustDomesticSdpInv;
    }

    public void setAbilityZeroTrustDomesticSdpInv(Double abilityZeroTrustDomesticSdpInv) {
        this.abilityZeroTrustDomesticSdpInv = abilityZeroTrustDomesticSdpInv;
    }

    public String getAbilityZeroTrustDomesticSdpRemark() {
        return abilityZeroTrustDomesticSdpRemark;
    }

    public void setAbilityZeroTrustDomesticSdpRemark(String abilityZeroTrustDomesticSdpRemark) {
        this.abilityZeroTrustDomesticSdpRemark = abilityZeroTrustDomesticSdpRemark;
    }

    public String getAbilityHoneypotVendor() {
        return abilityHoneypotVendor;
    }

    public void setAbilityHoneypotVendor(String abilityHoneypotVendor) {
        this.abilityHoneypotVendor = abilityHoneypotVendor;
    }

    public Integer getAbilityHoneypotIncNum() {
        return abilityHoneypotIncNum;
    }

    public void setAbilityHoneypotIncNum(Integer abilityHoneypotIncNum) {
        this.abilityHoneypotIncNum = abilityHoneypotIncNum;
    }

    public Double getAbilityHoneypotInc() {
        return abilityHoneypotInc;
    }

    public void setAbilityHoneypotInc(Double abilityHoneypotInc) {
        this.abilityHoneypotInc = abilityHoneypotInc;
    }

    public Double getAbilityHoneypotInv() {
        return abilityHoneypotInv;
    }

    public void setAbilityHoneypotInv(Double abilityHoneypotInv) {
        this.abilityHoneypotInv = abilityHoneypotInv;
    }

    public String getAbilityHoneypotRemark() {
        return abilityHoneypotRemark;
    }

    public void setAbilityHoneypotRemark(String abilityHoneypotRemark) {
        this.abilityHoneypotRemark = abilityHoneypotRemark;
    }

    public String getAbilityDomesticHoneypotVendor() {
        return abilityDomesticHoneypotVendor;
    }

    public void setAbilityDomesticHoneypotVendor(String abilityDomesticHoneypotVendor) {
        this.abilityDomesticHoneypotVendor = abilityDomesticHoneypotVendor;
    }

    public Integer getAbilityDomesticHoneypotIncNum() {
        return abilityDomesticHoneypotIncNum;
    }

    public void setAbilityDomesticHoneypotIncNum(Integer abilityDomesticHoneypotIncNum) {
        this.abilityDomesticHoneypotIncNum = abilityDomesticHoneypotIncNum;
    }

    public Double getAbilityDomesticHoneypotInc() {
        return abilityDomesticHoneypotInc;
    }

    public void setAbilityDomesticHoneypotInc(Double abilityDomesticHoneypotInc) {
        this.abilityDomesticHoneypotInc = abilityDomesticHoneypotInc;
    }

    public Double getAbilityDomesticHoneypotInv() {
        return abilityDomesticHoneypotInv;
    }

    public void setAbilityDomesticHoneypotInv(Double abilityDomesticHoneypotInv) {
        this.abilityDomesticHoneypotInv = abilityDomesticHoneypotInv;
    }

    public String getAbilityDomesticHoneypotRemark() {
        return abilityDomesticHoneypotRemark;
    }

    public void setAbilityDomesticHoneypotRemark(String abilityDomesticHoneypotRemark) {
        this.abilityDomesticHoneypotRemark = abilityDomesticHoneypotRemark;
    }

    public String getAbilityMicroSegmentationVendor() {
        return abilityMicroSegmentationVendor;
    }

    public void setAbilityMicroSegmentationVendor(String abilityMicroSegmentationVendor) {
        this.abilityMicroSegmentationVendor = abilityMicroSegmentationVendor;
    }

    public Integer getAbilityMicroSegmentationIncNum() {
        return abilityMicroSegmentationIncNum;
    }

    public void setAbilityMicroSegmentationIncNum(Integer abilityMicroSegmentationIncNum) {
        this.abilityMicroSegmentationIncNum = abilityMicroSegmentationIncNum;
    }

    public Double getAbilityMicroSegmentationInc() {
        return abilityMicroSegmentationInc;
    }

    public void setAbilityMicroSegmentationInc(Double abilityMicroSegmentationInc) {
        this.abilityMicroSegmentationInc = abilityMicroSegmentationInc;
    }

    public Double getAbilityMicroSegmentationInv() {
        return abilityMicroSegmentationInv;
    }

    public void setAbilityMicroSegmentationInv(Double abilityMicroSegmentationInv) {
        this.abilityMicroSegmentationInv = abilityMicroSegmentationInv;
    }

    public String getAbilityMicroSegmentationRemark() {
        return abilityMicroSegmentationRemark;
    }

    public void setAbilityMicroSegmentationRemark(String abilityMicroSegmentationRemark) {
        this.abilityMicroSegmentationRemark = abilityMicroSegmentationRemark;
    }

    public String getAbilityAbnormalTrafficDetectionForwardingDeviceVendor() {
        return abilityAbnormalTrafficDetectionForwardingDeviceVendor;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceVendor(String abilityAbnormalTrafficDetectionForwardingDeviceVendor) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceVendor = abilityAbnormalTrafficDetectionForwardingDeviceVendor;
    }

    public Integer getAbilityAbnormalTrafficDetectionForwardingDeviceIncNum() {
        return abilityAbnormalTrafficDetectionForwardingDeviceIncNum;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceIncNum(Integer abilityAbnormalTrafficDetectionForwardingDeviceIncNum) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceIncNum = abilityAbnormalTrafficDetectionForwardingDeviceIncNum;
    }

    public Double getAbilityAbnormalTrafficDetectionForwardingDeviceInc() {
        return abilityAbnormalTrafficDetectionForwardingDeviceInc;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceInc(Double abilityAbnormalTrafficDetectionForwardingDeviceInc) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceInc = abilityAbnormalTrafficDetectionForwardingDeviceInc;
    }

    public Double getAbilityAbnormalTrafficDetectionForwardingDeviceInv() {
        return abilityAbnormalTrafficDetectionForwardingDeviceInv;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceInv(Double abilityAbnormalTrafficDetectionForwardingDeviceInv) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceInv = abilityAbnormalTrafficDetectionForwardingDeviceInv;
    }

    public String getAbilityAbnormalTrafficDetectionForwardingDeviceRemark() {
        return abilityAbnormalTrafficDetectionForwardingDeviceRemark;
    }

    public void setAbilityAbnormalTrafficDetectionForwardingDeviceRemark(String abilityAbnormalTrafficDetectionForwardingDeviceRemark) {
        this.abilityAbnormalTrafficDetectionForwardingDeviceRemark = abilityAbnormalTrafficDetectionForwardingDeviceRemark;
    }

    public String getAbilityAbnormalTrafficDetectionCollectionDeviceVendor() {
        return abilityAbnormalTrafficDetectionCollectionDeviceVendor;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceVendor(String abilityAbnormalTrafficDetectionCollectionDeviceVendor) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceVendor = abilityAbnormalTrafficDetectionCollectionDeviceVendor;
    }

    public Integer getAbilityAbnormalTrafficDetectionCollectionDeviceIncNum() {
        return abilityAbnormalTrafficDetectionCollectionDeviceIncNum;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceIncNum(Integer abilityAbnormalTrafficDetectionCollectionDeviceIncNum) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceIncNum = abilityAbnormalTrafficDetectionCollectionDeviceIncNum;
    }

    public Double getAbilityAbnormalTrafficDetectionCollectionDeviceInc() {
        return abilityAbnormalTrafficDetectionCollectionDeviceInc;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceInc(Double abilityAbnormalTrafficDetectionCollectionDeviceInc) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceInc = abilityAbnormalTrafficDetectionCollectionDeviceInc;
    }

    public Double getAbilityAbnormalTrafficDetectionCollectionDeviceInv() {
        return abilityAbnormalTrafficDetectionCollectionDeviceInv;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceInv(Double abilityAbnormalTrafficDetectionCollectionDeviceInv) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceInv = abilityAbnormalTrafficDetectionCollectionDeviceInv;
    }

    public String getAbilityAbnormalTrafficDetectionCollectionDeviceRemark() {
        return abilityAbnormalTrafficDetectionCollectionDeviceRemark;
    }

    public void setAbilityAbnormalTrafficDetectionCollectionDeviceRemark(String abilityAbnormalTrafficDetectionCollectionDeviceRemark) {
        this.abilityAbnormalTrafficDetectionCollectionDeviceRemark = abilityAbnormalTrafficDetectionCollectionDeviceRemark;
    }

    public String getAbilityAbnormalTrafficDetectionReportDeviceVendor() {
        return abilityAbnormalTrafficDetectionReportDeviceVendor;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceVendor(String abilityAbnormalTrafficDetectionReportDeviceVendor) {
        this.abilityAbnormalTrafficDetectionReportDeviceVendor = abilityAbnormalTrafficDetectionReportDeviceVendor;
    }

    public Integer getAbilityAbnormalTrafficDetectionReportDeviceIncNum() {
        return abilityAbnormalTrafficDetectionReportDeviceIncNum;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceIncNum(Integer abilityAbnormalTrafficDetectionReportDeviceIncNum) {
        this.abilityAbnormalTrafficDetectionReportDeviceIncNum = abilityAbnormalTrafficDetectionReportDeviceIncNum;
    }

    public Double getAbilityAbnormalTrafficDetectionReportDeviceInc() {
        return abilityAbnormalTrafficDetectionReportDeviceInc;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceInc(Double abilityAbnormalTrafficDetectionReportDeviceInc) {
        this.abilityAbnormalTrafficDetectionReportDeviceInc = abilityAbnormalTrafficDetectionReportDeviceInc;
    }

    public Double getAbilityAbnormalTrafficDetectionReportDeviceInv() {
        return abilityAbnormalTrafficDetectionReportDeviceInv;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceInv(Double abilityAbnormalTrafficDetectionReportDeviceInv) {
        this.abilityAbnormalTrafficDetectionReportDeviceInv = abilityAbnormalTrafficDetectionReportDeviceInv;
    }

    public String getAbilityAbnormalTrafficDetectionReportDeviceRemark() {
        return abilityAbnormalTrafficDetectionReportDeviceRemark;
    }

    public void setAbilityAbnormalTrafficDetectionReportDeviceRemark(String abilityAbnormalTrafficDetectionReportDeviceRemark) {
        this.abilityAbnormalTrafficDetectionReportDeviceRemark = abilityAbnormalTrafficDetectionReportDeviceRemark;
    }

    public String getAbilityDataEncryptionDecryptionVendor() {
        return abilityDataEncryptionDecryptionVendor;
    }

    public void setAbilityDataEncryptionDecryptionVendor(String abilityDataEncryptionDecryptionVendor) {
        this.abilityDataEncryptionDecryptionVendor = abilityDataEncryptionDecryptionVendor;
    }

    public Integer getAbilityDataEncryptionDecryptionIncNum() {
        return abilityDataEncryptionDecryptionIncNum;
    }

    public void setAbilityDataEncryptionDecryptionIncNum(Integer abilityDataEncryptionDecryptionIncNum) {
        this.abilityDataEncryptionDecryptionIncNum = abilityDataEncryptionDecryptionIncNum;
    }

    public Double getAbilityDataEncryptionDecryptionInc() {
        return abilityDataEncryptionDecryptionInc;
    }

    public void setAbilityDataEncryptionDecryptionInc(Double abilityDataEncryptionDecryptionInc) {
        this.abilityDataEncryptionDecryptionInc = abilityDataEncryptionDecryptionInc;
    }

    public Double getAbilityDataEncryptionDecryptionInv() {
        return abilityDataEncryptionDecryptionInv;
    }

    public void setAbilityDataEncryptionDecryptionInv(Double abilityDataEncryptionDecryptionInv) {
        this.abilityDataEncryptionDecryptionInv = abilityDataEncryptionDecryptionInv;
    }

    public String getAbilityDataEncryptionDecryptionRemark() {
        return abilityDataEncryptionDecryptionRemark;
    }

    public void setAbilityDataEncryptionDecryptionRemark(String abilityDataEncryptionDecryptionRemark) {
        this.abilityDataEncryptionDecryptionRemark = abilityDataEncryptionDecryptionRemark;
    }

    public String getAbilityBotnetWormDetectionDisposalDeviceVendor() {
        return abilityBotnetWormDetectionDisposalDeviceVendor;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceVendor(String abilityBotnetWormDetectionDisposalDeviceVendor) {
        this.abilityBotnetWormDetectionDisposalDeviceVendor = abilityBotnetWormDetectionDisposalDeviceVendor;
    }

    public Integer getAbilityBotnetWormDetectionDisposalDeviceIncNum() {
        return abilityBotnetWormDetectionDisposalDeviceIncNum;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceIncNum(Integer abilityBotnetWormDetectionDisposalDeviceIncNum) {
        this.abilityBotnetWormDetectionDisposalDeviceIncNum = abilityBotnetWormDetectionDisposalDeviceIncNum;
    }

    public Double getAbilityBotnetWormDetectionDisposalDeviceInc() {
        return abilityBotnetWormDetectionDisposalDeviceInc;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceInc(Double abilityBotnetWormDetectionDisposalDeviceInc) {
        this.abilityBotnetWormDetectionDisposalDeviceInc = abilityBotnetWormDetectionDisposalDeviceInc;
    }

    public Double getAbilityBotnetWormDetectionDisposalDeviceInv() {
        return abilityBotnetWormDetectionDisposalDeviceInv;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceInv(Double abilityBotnetWormDetectionDisposalDeviceInv) {
        this.abilityBotnetWormDetectionDisposalDeviceInv = abilityBotnetWormDetectionDisposalDeviceInv;
    }

    public String getAbilityBotnetWormDetectionDisposalDeviceRemark() {
        return abilityBotnetWormDetectionDisposalDeviceRemark;
    }

    public void setAbilityBotnetWormDetectionDisposalDeviceRemark(String abilityBotnetWormDetectionDisposalDeviceRemark) {
        this.abilityBotnetWormDetectionDisposalDeviceRemark = abilityBotnetWormDetectionDisposalDeviceRemark;
    }

    public String getAbilityBotnetWormDetectionGatewayVendor() {
        return abilityBotnetWormDetectionGatewayVendor;
    }

    public void setAbilityBotnetWormDetectionGatewayVendor(String abilityBotnetWormDetectionGatewayVendor) {
        this.abilityBotnetWormDetectionGatewayVendor = abilityBotnetWormDetectionGatewayVendor;
    }

    public Integer getAbilityBotnetWormDetectionGatewayIncNum() {
        return abilityBotnetWormDetectionGatewayIncNum;
    }

    public void setAbilityBotnetWormDetectionGatewayIncNum(Integer abilityBotnetWormDetectionGatewayIncNum) {
        this.abilityBotnetWormDetectionGatewayIncNum = abilityBotnetWormDetectionGatewayIncNum;
    }

    public Double getAbilityBotnetWormDetectionGatewayInc() {
        return abilityBotnetWormDetectionGatewayInc;
    }

    public void setAbilityBotnetWormDetectionGatewayInc(Double abilityBotnetWormDetectionGatewayInc) {
        this.abilityBotnetWormDetectionGatewayInc = abilityBotnetWormDetectionGatewayInc;
    }

    public Double getAbilityBotnetWormDetectionGatewayInv() {
        return abilityBotnetWormDetectionGatewayInv;
    }

    public void setAbilityBotnetWormDetectionGatewayInv(Double abilityBotnetWormDetectionGatewayInv) {
        this.abilityBotnetWormDetectionGatewayInv = abilityBotnetWormDetectionGatewayInv;
    }

    public String getAbilityBotnetWormDetectionGatewayRemark() {
        return abilityBotnetWormDetectionGatewayRemark;
    }

    public void setAbilityBotnetWormDetectionGatewayRemark(String abilityBotnetWormDetectionGatewayRemark) {
        this.abilityBotnetWormDetectionGatewayRemark = abilityBotnetWormDetectionGatewayRemark;
    }

    public String getAbilityBotnetWormDetectionSoftwareUpgradeVendor() {
        return abilityBotnetWormDetectionSoftwareUpgradeVendor;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeVendor(String abilityBotnetWormDetectionSoftwareUpgradeVendor) {
        this.abilityBotnetWormDetectionSoftwareUpgradeVendor = abilityBotnetWormDetectionSoftwareUpgradeVendor;
    }

    public Integer getAbilityBotnetWormDetectionSoftwareUpgradeIncNum() {
        return abilityBotnetWormDetectionSoftwareUpgradeIncNum;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeIncNum(Integer abilityBotnetWormDetectionSoftwareUpgradeIncNum) {
        this.abilityBotnetWormDetectionSoftwareUpgradeIncNum = abilityBotnetWormDetectionSoftwareUpgradeIncNum;
    }

    public Double getAbilityBotnetWormDetectionSoftwareUpgradeInc() {
        return abilityBotnetWormDetectionSoftwareUpgradeInc;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeInc(Double abilityBotnetWormDetectionSoftwareUpgradeInc) {
        this.abilityBotnetWormDetectionSoftwareUpgradeInc = abilityBotnetWormDetectionSoftwareUpgradeInc;
    }

    public Double getAbilityBotnetWormDetectionSoftwareUpgradeInv() {
        return abilityBotnetWormDetectionSoftwareUpgradeInv;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeInv(Double abilityBotnetWormDetectionSoftwareUpgradeInv) {
        this.abilityBotnetWormDetectionSoftwareUpgradeInv = abilityBotnetWormDetectionSoftwareUpgradeInv;
    }

    public String getAbilityBotnetWormDetectionSoftwareUpgradeRemark() {
        return abilityBotnetWormDetectionSoftwareUpgradeRemark;
    }

    public void setAbilityBotnetWormDetectionSoftwareUpgradeRemark(String abilityBotnetWormDetectionSoftwareUpgradeRemark) {
        this.abilityBotnetWormDetectionSoftwareUpgradeRemark = abilityBotnetWormDetectionSoftwareUpgradeRemark;
    }

    public String getAbilityIdcispHardwareVendor() {
        return abilityIdcispHardwareVendor;
    }

    public void setAbilityIdcispHardwareVendor(String abilityIdcispHardwareVendor) {
        this.abilityIdcispHardwareVendor = abilityIdcispHardwareVendor;
    }

    public Integer getAbilityIdcispHardwareIncNum() {
        return abilityIdcispHardwareIncNum;
    }

    public void setAbilityIdcispHardwareIncNum(Integer abilityIdcispHardwareIncNum) {
        this.abilityIdcispHardwareIncNum = abilityIdcispHardwareIncNum;
    }

    public Double getAbilityIdcispHardwareInc() {
        return abilityIdcispHardwareInc;
    }

    public void setAbilityIdcispHardwareInc(Double abilityIdcispHardwareInc) {
        this.abilityIdcispHardwareInc = abilityIdcispHardwareInc;
    }

    public Double getAbilityIdcispHardwareInv() {
        return abilityIdcispHardwareInv;
    }

    public void setAbilityIdcispHardwareInv(Double abilityIdcispHardwareInv) {
        this.abilityIdcispHardwareInv = abilityIdcispHardwareInv;
    }

    public String getAbilityIdcispHardwareRemark() {
        return abilityIdcispHardwareRemark;
    }

    public void setAbilityIdcispHardwareRemark(String abilityIdcispHardwareRemark) {
        this.abilityIdcispHardwareRemark = abilityIdcispHardwareRemark;
    }

    public String getAbilityIdcispSoftwareVendor() {
        return abilityIdcispSoftwareVendor;
    }

    public void setAbilityIdcispSoftwareVendor(String abilityIdcispSoftwareVendor) {
        this.abilityIdcispSoftwareVendor = abilityIdcispSoftwareVendor;
    }

    public Double getAbilityIdcispSoftwareInv() {
        return abilityIdcispSoftwareInv;
    }

    public void setAbilityIdcispSoftwareInv(Double abilityIdcispSoftwareInv) {
        this.abilityIdcispSoftwareInv = abilityIdcispSoftwareInv;
    }

    public String getAbilityIdcispSoftwareRemark() {
        return abilityIdcispSoftwareRemark;
    }

    public void setAbilityIdcispSoftwareRemark(String abilityIdcispSoftwareRemark) {
        this.abilityIdcispSoftwareRemark = abilityIdcispSoftwareRemark;
    }

    public String getAbilityMobileDpiHardwareVendor() {
        return abilityMobileDpiHardwareVendor;
    }

    public void setAbilityMobileDpiHardwareVendor(String abilityMobileDpiHardwareVendor) {
        this.abilityMobileDpiHardwareVendor = abilityMobileDpiHardwareVendor;
    }

    public Integer getAbilityMobileDpiHardwareIncNum() {
        return abilityMobileDpiHardwareIncNum;
    }

    public void setAbilityMobileDpiHardwareIncNum(Integer abilityMobileDpiHardwareIncNum) {
        this.abilityMobileDpiHardwareIncNum = abilityMobileDpiHardwareIncNum;
    }

    public Double getAbilityMobileDpiHardwareInc() {
        return abilityMobileDpiHardwareInc;
    }

    public void setAbilityMobileDpiHardwareInc(Double abilityMobileDpiHardwareInc) {
        this.abilityMobileDpiHardwareInc = abilityMobileDpiHardwareInc;
    }

    public Double getAbilityMobileDpiHardwareInv() {
        return abilityMobileDpiHardwareInv;
    }

    public void setAbilityMobileDpiHardwareInv(Double abilityMobileDpiHardwareInv) {
        this.abilityMobileDpiHardwareInv = abilityMobileDpiHardwareInv;
    }

    public String getAbilityMobileDpiHardwareRemark() {
        return abilityMobileDpiHardwareRemark;
    }

    public void setAbilityMobileDpiHardwareRemark(String abilityMobileDpiHardwareRemark) {
        this.abilityMobileDpiHardwareRemark = abilityMobileDpiHardwareRemark;
    }

    public String getAbilityMobileDpiSoftwareVendor() {
        return abilityMobileDpiSoftwareVendor;
    }

    public void setAbilityMobileDpiSoftwareVendor(String abilityMobileDpiSoftwareVendor) {
        this.abilityMobileDpiSoftwareVendor = abilityMobileDpiSoftwareVendor;
    }

    public Integer getAbilityMobileDpiSoftwareIncNum() {
        return abilityMobileDpiSoftwareIncNum;
    }

    public void setAbilityMobileDpiSoftwareIncNum(Integer abilityMobileDpiSoftwareIncNum) {
        this.abilityMobileDpiSoftwareIncNum = abilityMobileDpiSoftwareIncNum;
    }

    public Double getAbilityMobileDpiSoftwareInc() {
        return abilityMobileDpiSoftwareInc;
    }

    public void setAbilityMobileDpiSoftwareInc(Double abilityMobileDpiSoftwareInc) {
        this.abilityMobileDpiSoftwareInc = abilityMobileDpiSoftwareInc;
    }

    public Double getAbilityMobileDpiSoftwareInv() {
        return abilityMobileDpiSoftwareInv;
    }

    public void setAbilityMobileDpiSoftwareInv(Double abilityMobileDpiSoftwareInv) {
        this.abilityMobileDpiSoftwareInv = abilityMobileDpiSoftwareInv;
    }

    public String getAbilityMobileDpiSoftwareRemark() {
        return abilityMobileDpiSoftwareRemark;
    }

    public void setAbilityMobileDpiSoftwareRemark(String abilityMobileDpiSoftwareRemark) {
        this.abilityMobileDpiSoftwareRemark = abilityMobileDpiSoftwareRemark;
    }

    public String getAbilityFixedNetworkDpiHardwareVendor() {
        return abilityFixedNetworkDpiHardwareVendor;
    }

    public void setAbilityFixedNetworkDpiHardwareVendor(String abilityFixedNetworkDpiHardwareVendor) {
        this.abilityFixedNetworkDpiHardwareVendor = abilityFixedNetworkDpiHardwareVendor;
    }

    public Integer getAbilityFixedNetworkDpiHardwareIncNum() {
        return abilityFixedNetworkDpiHardwareIncNum;
    }

    public void setAbilityFixedNetworkDpiHardwareIncNum(Integer abilityFixedNetworkDpiHardwareIncNum) {
        this.abilityFixedNetworkDpiHardwareIncNum = abilityFixedNetworkDpiHardwareIncNum;
    }

    public Double getAbilityFixedNetworkDpiHardwareInc() {
        return abilityFixedNetworkDpiHardwareInc;
    }

    public void setAbilityFixedNetworkDpiHardwareInc(Double abilityFixedNetworkDpiHardwareInc) {
        this.abilityFixedNetworkDpiHardwareInc = abilityFixedNetworkDpiHardwareInc;
    }

    public Double getAbilityFixedNetworkDpiHardwareInv() {
        return abilityFixedNetworkDpiHardwareInv;
    }

    public void setAbilityFixedNetworkDpiHardwareInv(Double abilityFixedNetworkDpiHardwareInv) {
        this.abilityFixedNetworkDpiHardwareInv = abilityFixedNetworkDpiHardwareInv;
    }

    public String getAbilityFixedNetworkDpiHardwareRemark() {
        return abilityFixedNetworkDpiHardwareRemark;
    }

    public void setAbilityFixedNetworkDpiHardwareRemark(String abilityFixedNetworkDpiHardwareRemark) {
        this.abilityFixedNetworkDpiHardwareRemark = abilityFixedNetworkDpiHardwareRemark;
    }

    public String getAbilityFixedNetworkDpiSoftwareVendor() {
        return abilityFixedNetworkDpiSoftwareVendor;
    }

    public void setAbilityFixedNetworkDpiSoftwareVendor(String abilityFixedNetworkDpiSoftwareVendor) {
        this.abilityFixedNetworkDpiSoftwareVendor = abilityFixedNetworkDpiSoftwareVendor;
    }

    public Double getAbilityFixedNetworkDpiSoftwareInv() {
        return abilityFixedNetworkDpiSoftwareInv;
    }

    public void setAbilityFixedNetworkDpiSoftwareInv(Double abilityFixedNetworkDpiSoftwareInv) {
        this.abilityFixedNetworkDpiSoftwareInv = abilityFixedNetworkDpiSoftwareInv;
    }

    public String getAbilityFixedNetworkDpiSoftwareRemark() {
        return abilityFixedNetworkDpiSoftwareRemark;
    }

    public void setAbilityFixedNetworkDpiSoftwareRemark(String abilityFixedNetworkDpiSoftwareRemark) {
        this.abilityFixedNetworkDpiSoftwareRemark = abilityFixedNetworkDpiSoftwareRemark;
    }

    public String getAbilityMobileMalwareDetectionHardwareVendor() {
        return abilityMobileMalwareDetectionHardwareVendor;
    }

    public void setAbilityMobileMalwareDetectionHardwareVendor(String abilityMobileMalwareDetectionHardwareVendor) {
        this.abilityMobileMalwareDetectionHardwareVendor = abilityMobileMalwareDetectionHardwareVendor;
    }

    public Integer getAbilityMobileMalwareDetectionHardwareIncNum() {
        return abilityMobileMalwareDetectionHardwareIncNum;
    }

    public void setAbilityMobileMalwareDetectionHardwareIncNum(Integer abilityMobileMalwareDetectionHardwareIncNum) {
        this.abilityMobileMalwareDetectionHardwareIncNum = abilityMobileMalwareDetectionHardwareIncNum;
    }

    public Double getAbilityMobileMalwareDetectionHardwareInc() {
        return abilityMobileMalwareDetectionHardwareInc;
    }

    public void setAbilityMobileMalwareDetectionHardwareInc(Double abilityMobileMalwareDetectionHardwareInc) {
        this.abilityMobileMalwareDetectionHardwareInc = abilityMobileMalwareDetectionHardwareInc;
    }

    public Double getAbilityMobileMalwareDetectionHardwareInv() {
        return abilityMobileMalwareDetectionHardwareInv;
    }

    public void setAbilityMobileMalwareDetectionHardwareInv(Double abilityMobileMalwareDetectionHardwareInv) {
        this.abilityMobileMalwareDetectionHardwareInv = abilityMobileMalwareDetectionHardwareInv;
    }

    public String getAbilityMobileMalwareDetectionHardwareRemark() {
        return abilityMobileMalwareDetectionHardwareRemark;
    }

    public void setAbilityMobileMalwareDetectionHardwareRemark(String abilityMobileMalwareDetectionHardwareRemark) {
        this.abilityMobileMalwareDetectionHardwareRemark = abilityMobileMalwareDetectionHardwareRemark;
    }

    public String getAbilityMobileMalwareDetectionSoftwareVendor() {
        return abilityMobileMalwareDetectionSoftwareVendor;
    }

    public void setAbilityMobileMalwareDetectionSoftwareVendor(String abilityMobileMalwareDetectionSoftwareVendor) {
        this.abilityMobileMalwareDetectionSoftwareVendor = abilityMobileMalwareDetectionSoftwareVendor;
    }

    public Integer getAbilityMobileMalwareDetectionSoftwareIncNum() {
        return abilityMobileMalwareDetectionSoftwareIncNum;
    }

    public void setAbilityMobileMalwareDetectionSoftwareIncNum(Integer abilityMobileMalwareDetectionSoftwareIncNum) {
        this.abilityMobileMalwareDetectionSoftwareIncNum = abilityMobileMalwareDetectionSoftwareIncNum;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareInc() {
        return abilityMobileMalwareDetectionSoftwareInc;
    }

    public void setAbilityMobileMalwareDetectionSoftwareInc(Double abilityMobileMalwareDetectionSoftwareInc) {
        this.abilityMobileMalwareDetectionSoftwareInc = abilityMobileMalwareDetectionSoftwareInc;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareInv() {
        return abilityMobileMalwareDetectionSoftwareInv;
    }

    public void setAbilityMobileMalwareDetectionSoftwareInv(Double abilityMobileMalwareDetectionSoftwareInv) {
        this.abilityMobileMalwareDetectionSoftwareInv = abilityMobileMalwareDetectionSoftwareInv;
    }

    public String getAbilityMobileMalwareDetectionSoftwareRemark() {
        return abilityMobileMalwareDetectionSoftwareRemark;
    }

    public void setAbilityMobileMalwareDetectionSoftwareRemark(String abilityMobileMalwareDetectionSoftwareRemark) {
        this.abilityMobileMalwareDetectionSoftwareRemark = abilityMobileMalwareDetectionSoftwareRemark;
    }

    public String getAbilityMobileMalwareDetectionSoftwareUpgradeVendor() {
        return abilityMobileMalwareDetectionSoftwareUpgradeVendor;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeVendor(String abilityMobileMalwareDetectionSoftwareUpgradeVendor) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeVendor = abilityMobileMalwareDetectionSoftwareUpgradeVendor;
    }

    public Integer getAbilityMobileMalwareDetectionSoftwareUpgradeIncNum() {
        return abilityMobileMalwareDetectionSoftwareUpgradeIncNum;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeIncNum(Integer abilityMobileMalwareDetectionSoftwareUpgradeIncNum) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeIncNum = abilityMobileMalwareDetectionSoftwareUpgradeIncNum;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareUpgradeInc() {
        return abilityMobileMalwareDetectionSoftwareUpgradeInc;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeInc(Double abilityMobileMalwareDetectionSoftwareUpgradeInc) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeInc = abilityMobileMalwareDetectionSoftwareUpgradeInc;
    }

    public Double getAbilityMobileMalwareDetectionSoftwareUpgradeInv() {
        return abilityMobileMalwareDetectionSoftwareUpgradeInv;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeInv(Double abilityMobileMalwareDetectionSoftwareUpgradeInv) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeInv = abilityMobileMalwareDetectionSoftwareUpgradeInv;
    }

    public String getAbilityMobileMalwareDetectionSoftwareUpgradeRemark() {
        return abilityMobileMalwareDetectionSoftwareUpgradeRemark;
    }

    public void setAbilityMobileMalwareDetectionSoftwareUpgradeRemark(String abilityMobileMalwareDetectionSoftwareUpgradeRemark) {
        this.abilityMobileMalwareDetectionSoftwareUpgradeRemark = abilityMobileMalwareDetectionSoftwareUpgradeRemark;
    }

    public Double getSoftwareMobileInternetLogRetentionCentralizedInv() {
        return softwareMobileInternetLogRetentionCentralizedInv;
    }

    public void setSoftwareMobileInternetLogRetentionCentralizedInv(Double softwareMobileInternetLogRetentionCentralizedInv) {
        this.softwareMobileInternetLogRetentionCentralizedInv = softwareMobileInternetLogRetentionCentralizedInv;
    }

    public String getSoftwareMobileInternetLogRetentionCentralizedRemark() {
        return softwareMobileInternetLogRetentionCentralizedRemark;
    }

    public void setSoftwareMobileInternetLogRetentionCentralizedRemark(String softwareMobileInternetLogRetentionCentralizedRemark) {
        this.softwareMobileInternetLogRetentionCentralizedRemark = softwareMobileInternetLogRetentionCentralizedRemark;
    }

    public Double getSoftwareMobileInternetLogRetentionCustomizedInv() {
        return softwareMobileInternetLogRetentionCustomizedInv;
    }

    public void setSoftwareMobileInternetLogRetentionCustomizedInv(Double softwareMobileInternetLogRetentionCustomizedInv) {
        this.softwareMobileInternetLogRetentionCustomizedInv = softwareMobileInternetLogRetentionCustomizedInv;
    }

    public String getSoftwareMobileInternetLogRetentionCustomizedRemark() {
        return softwareMobileInternetLogRetentionCustomizedRemark;
    }

    public void setSoftwareMobileInternetLogRetentionCustomizedRemark(String softwareMobileInternetLogRetentionCustomizedRemark) {
        this.softwareMobileInternetLogRetentionCustomizedRemark = softwareMobileInternetLogRetentionCustomizedRemark;
    }

    public Double getSoftwareBotnetWormDetectionPlatformTotalPriceInv() {
        return softwareBotnetWormDetectionPlatformTotalPriceInv;
    }

    public void setSoftwareBotnetWormDetectionPlatformTotalPriceInv(Double softwareBotnetWormDetectionPlatformTotalPriceInv) {
        this.softwareBotnetWormDetectionPlatformTotalPriceInv = softwareBotnetWormDetectionPlatformTotalPriceInv;
    }

    public String getSoftwareBotnetWormDetectionPlatformTotalPriceRemark() {
        return softwareBotnetWormDetectionPlatformTotalPriceRemark;
    }

    public void setSoftwareBotnetWormDetectionPlatformTotalPriceRemark(String softwareBotnetWormDetectionPlatformTotalPriceRemark) {
        this.softwareBotnetWormDetectionPlatformTotalPriceRemark = softwareBotnetWormDetectionPlatformTotalPriceRemark;
    }

    public Double getSoftwareIdcispPlatformTotalPriceInv() {
        return softwareIdcispPlatformTotalPriceInv;
    }

    public void setSoftwareIdcispPlatformTotalPriceInv(Double softwareIdcispPlatformTotalPriceInv) {
        this.softwareIdcispPlatformTotalPriceInv = softwareIdcispPlatformTotalPriceInv;
    }

    public String getSoftwareIdcispPlatformTotalPriceRemark() {
        return softwareIdcispPlatformTotalPriceRemark;
    }

    public void setSoftwareIdcispPlatformTotalPriceRemark(String softwareIdcispPlatformTotalPriceRemark) {
        this.softwareIdcispPlatformTotalPriceRemark = softwareIdcispPlatformTotalPriceRemark;
    }

    public Double getSoftwareMobileDpiPlatformTotalPriceInv() {
        return softwareMobileDpiPlatformTotalPriceInv;
    }

    public void setSoftwareMobileDpiPlatformTotalPriceInv(Double softwareMobileDpiPlatformTotalPriceInv) {
        this.softwareMobileDpiPlatformTotalPriceInv = softwareMobileDpiPlatformTotalPriceInv;
    }

    public String getSoftwareMobileDpiPlatformTotalPriceRemark() {
        return softwareMobileDpiPlatformTotalPriceRemark;
    }

    public void setSoftwareMobileDpiPlatformTotalPriceRemark(String softwareMobileDpiPlatformTotalPriceRemark) {
        this.softwareMobileDpiPlatformTotalPriceRemark = softwareMobileDpiPlatformTotalPriceRemark;
    }

    public Double getSoftwareFixedNetworkDpiPlatformTotalPriceInv() {
        return softwareFixedNetworkDpiPlatformTotalPriceInv;
    }

    public void setSoftwareFixedNetworkDpiPlatformTotalPriceInv(Double softwareFixedNetworkDpiPlatformTotalPriceInv) {
        this.softwareFixedNetworkDpiPlatformTotalPriceInv = softwareFixedNetworkDpiPlatformTotalPriceInv;
    }

    public String getSoftwareFixedNetworkDpiPlatformTotalPriceRemark() {
        return softwareFixedNetworkDpiPlatformTotalPriceRemark;
    }

    public void setSoftwareFixedNetworkDpiPlatformTotalPriceRemark(String softwareFixedNetworkDpiPlatformTotalPriceRemark) {
        this.softwareFixedNetworkDpiPlatformTotalPriceRemark = softwareFixedNetworkDpiPlatformTotalPriceRemark;
    }

    public Double getSoftwareMobileMalwareDetectionPlatformTotalPriceInv() {
        return softwareMobileMalwareDetectionPlatformTotalPriceInv;
    }

    public void setSoftwareMobileMalwareDetectionPlatformTotalPriceInv(Double softwareMobileMalwareDetectionPlatformTotalPriceInv) {
        this.softwareMobileMalwareDetectionPlatformTotalPriceInv = softwareMobileMalwareDetectionPlatformTotalPriceInv;
    }

    public String getSoftwareMobileMalwareDetectionPlatformTotalPriceRemark() {
        return softwareMobileMalwareDetectionPlatformTotalPriceRemark;
    }

    public void setSoftwareMobileMalwareDetectionPlatformTotalPriceRemark(String softwareMobileMalwareDetectionPlatformTotalPriceRemark) {
        this.softwareMobileMalwareDetectionPlatformTotalPriceRemark = softwareMobileMalwareDetectionPlatformTotalPriceRemark;
    }

    public Double getSoftwareAssetManagementInv() {
        return softwareAssetManagementInv;
    }

    public void setSoftwareAssetManagementInv(Double softwareAssetManagementInv) {
        this.softwareAssetManagementInv = softwareAssetManagementInv;
    }

    public String getSoftwareAssetManagementRemark() {
        return softwareAssetManagementRemark;
    }

    public void setSoftwareAssetManagementRemark(String softwareAssetManagementRemark) {
        this.softwareAssetManagementRemark = softwareAssetManagementRemark;
    }

    public Double getSoftwareBaselineManagementInv() {
        return softwareBaselineManagementInv;
    }

    public void setSoftwareBaselineManagementInv(Double softwareBaselineManagementInv) {
        this.softwareBaselineManagementInv = softwareBaselineManagementInv;
    }

    public String getSoftwareBaselineManagementRemark() {
        return softwareBaselineManagementRemark;
    }

    public void setSoftwareBaselineManagementRemark(String softwareBaselineManagementRemark) {
        this.softwareBaselineManagementRemark = softwareBaselineManagementRemark;
    }

    public Double getSoftwareVulnerabilityManagementInv() {
        return softwareVulnerabilityManagementInv;
    }

    public void setSoftwareVulnerabilityManagementInv(Double softwareVulnerabilityManagementInv) {
        this.softwareVulnerabilityManagementInv = softwareVulnerabilityManagementInv;
    }

    public String getSoftwareVulnerabilityManagementRemark() {
        return softwareVulnerabilityManagementRemark;
    }

    public void setSoftwareVulnerabilityManagementRemark(String softwareVulnerabilityManagementRemark) {
        this.softwareVulnerabilityManagementRemark = softwareVulnerabilityManagementRemark;
    }

    public Double getSoftwareInternetExposureManagementInv() {
        return softwareInternetExposureManagementInv;
    }

    public void setSoftwareInternetExposureManagementInv(Double softwareInternetExposureManagementInv) {
        this.softwareInternetExposureManagementInv = softwareInternetExposureManagementInv;
    }

    public String getSoftwareInternetExposureManagementRemark() {
        return softwareInternetExposureManagementRemark;
    }

    public void setSoftwareInternetExposureManagementRemark(String softwareInternetExposureManagementRemark) {
        this.softwareInternetExposureManagementRemark = softwareInternetExposureManagementRemark;
    }

    public Double getSoftwareInternalNetworkAssetMappingInv() {
        return softwareInternalNetworkAssetMappingInv;
    }

    public void setSoftwareInternalNetworkAssetMappingInv(Double softwareInternalNetworkAssetMappingInv) {
        this.softwareInternalNetworkAssetMappingInv = softwareInternalNetworkAssetMappingInv;
    }

    public String getSoftwareInternalNetworkAssetMappingRemark() {
        return softwareInternalNetworkAssetMappingRemark;
    }

    public void setSoftwareInternalNetworkAssetMappingRemark(String softwareInternalNetworkAssetMappingRemark) {
        this.softwareInternalNetworkAssetMappingRemark = softwareInternalNetworkAssetMappingRemark;
    }

    public Double getSoftwareAaaaInv() {
        return softwareAaaaInv;
    }

    public void setSoftwareAaaaInv(Double softwareAaaaInv) {
        this.softwareAaaaInv = softwareAaaaInv;
    }

    public String getSoftwareAaaaRemark() {
        return softwareAaaaRemark;
    }

    public void setSoftwareAaaaRemark(String softwareAaaaRemark) {
        this.softwareAaaaRemark = softwareAaaaRemark;
    }

    public Double getSoftwareAppReleaseDetectionInv() {
        return softwareAppReleaseDetectionInv;
    }

    public void setSoftwareAppReleaseDetectionInv(Double softwareAppReleaseDetectionInv) {
        this.softwareAppReleaseDetectionInv = softwareAppReleaseDetectionInv;
    }

    public String getSoftwareAppReleaseDetectionRemark() {
        return softwareAppReleaseDetectionRemark;
    }

    public void setSoftwareAppReleaseDetectionRemark(String softwareAppReleaseDetectionRemark) {
        this.softwareAppReleaseDetectionRemark = softwareAppReleaseDetectionRemark;
    }

    public Double getSoftwareDataAssetManagementInv() {
        return softwareDataAssetManagementInv;
    }

    public void setSoftwareDataAssetManagementInv(Double softwareDataAssetManagementInv) {
        this.softwareDataAssetManagementInv = softwareDataAssetManagementInv;
    }

    public String getSoftwareDataAssetManagementRemark() {
        return softwareDataAssetManagementRemark;
    }

    public void setSoftwareDataAssetManagementRemark(String softwareDataAssetManagementRemark) {
        this.softwareDataAssetManagementRemark = softwareDataAssetManagementRemark;
    }

    public Double getSoftwarePasswordServiceManagementInv() {
        return softwarePasswordServiceManagementInv;
    }

    public void setSoftwarePasswordServiceManagementInv(Double softwarePasswordServiceManagementInv) {
        this.softwarePasswordServiceManagementInv = softwarePasswordServiceManagementInv;
    }

    public String getSoftwarePasswordServiceManagementRemark() {
        return softwarePasswordServiceManagementRemark;
    }

    public void setSoftwarePasswordServiceManagementRemark(String softwarePasswordServiceManagementRemark) {
        this.softwarePasswordServiceManagementRemark = softwarePasswordServiceManagementRemark;
    }

    public Double getSoftwareThreatIntelligenceInv() {
        return softwareThreatIntelligenceInv;
    }

    public void setSoftwareThreatIntelligenceInv(Double softwareThreatIntelligenceInv) {
        this.softwareThreatIntelligenceInv = softwareThreatIntelligenceInv;
    }

    public String getSoftwareThreatIntelligenceRemark() {
        return softwareThreatIntelligenceRemark;
    }

    public void setSoftwareThreatIntelligenceRemark(String softwareThreatIntelligenceRemark) {
        this.softwareThreatIntelligenceRemark = softwareThreatIntelligenceRemark;
    }

    public Double getSoftwareNetworkSecuritySituationalAwarenessInv() {
        return softwareNetworkSecuritySituationalAwarenessInv;
    }

    public void setSoftwareNetworkSecuritySituationalAwarenessInv(Double softwareNetworkSecuritySituationalAwarenessInv) {
        this.softwareNetworkSecuritySituationalAwarenessInv = softwareNetworkSecuritySituationalAwarenessInv;
    }

    public String getSoftwareNetworkSecuritySituationalAwarenessRemark() {
        return softwareNetworkSecuritySituationalAwarenessRemark;
    }

    public void setSoftwareNetworkSecuritySituationalAwarenessRemark(String softwareNetworkSecuritySituationalAwarenessRemark) {
        this.softwareNetworkSecuritySituationalAwarenessRemark = softwareNetworkSecuritySituationalAwarenessRemark;
    }

    public Double getSoftwareDataSecuritySituationalAwarenessInv() {
        return softwareDataSecuritySituationalAwarenessInv;
    }

    public void setSoftwareDataSecuritySituationalAwarenessInv(Double softwareDataSecuritySituationalAwarenessInv) {
        this.softwareDataSecuritySituationalAwarenessInv = softwareDataSecuritySituationalAwarenessInv;
    }

    public String getSoftwareDataSecuritySituationalAwarenessRemark() {
        return softwareDataSecuritySituationalAwarenessRemark;
    }

    public void setSoftwareDataSecuritySituationalAwarenessRemark(String softwareDataSecuritySituationalAwarenessRemark) {
        this.softwareDataSecuritySituationalAwarenessRemark = softwareDataSecuritySituationalAwarenessRemark;
    }

    public Double getSoftwareWebsiteFilingMonitoringInv() {
        return softwareWebsiteFilingMonitoringInv;
    }

    public void setSoftwareWebsiteFilingMonitoringInv(Double softwareWebsiteFilingMonitoringInv) {
        this.softwareWebsiteFilingMonitoringInv = softwareWebsiteFilingMonitoringInv;
    }

    public String getSoftwareWebsiteFilingMonitoringRemark() {
        return softwareWebsiteFilingMonitoringRemark;
    }

    public void setSoftwareWebsiteFilingMonitoringRemark(String softwareWebsiteFilingMonitoringRemark) {
        this.softwareWebsiteFilingMonitoringRemark = softwareWebsiteFilingMonitoringRemark;
    }

    public Double getSoftwareHarmfulInformationMonitoringInv() {
        return softwareHarmfulInformationMonitoringInv;
    }

    public void setSoftwareHarmfulInformationMonitoringInv(Double softwareHarmfulInformationMonitoringInv) {
        this.softwareHarmfulInformationMonitoringInv = softwareHarmfulInformationMonitoringInv;
    }

    public String getSoftwareHarmfulInformationMonitoringRemark() {
        return softwareHarmfulInformationMonitoringRemark;
    }

    public void setSoftwareHarmfulInformationMonitoringRemark(String softwareHarmfulInformationMonitoringRemark) {
        this.softwareHarmfulInformationMonitoringRemark = softwareHarmfulInformationMonitoringRemark;
    }

    public Double getSoftwareAntiFraudManagementInv() {
        return softwareAntiFraudManagementInv;
    }

    public void setSoftwareAntiFraudManagementInv(Double softwareAntiFraudManagementInv) {
        this.softwareAntiFraudManagementInv = softwareAntiFraudManagementInv;
    }

    public String getSoftwareAntiFraudManagementRemark() {
        return softwareAntiFraudManagementRemark;
    }

    public void setSoftwareAntiFraudManagementRemark(String softwareAntiFraudManagementRemark) {
        this.softwareAntiFraudManagementRemark = softwareAntiFraudManagementRemark;
    }

    public Double getSoftwareContentSecurityReviewPublishControlInv() {
        return softwareContentSecurityReviewPublishControlInv;
    }

    public void setSoftwareContentSecurityReviewPublishControlInv(Double softwareContentSecurityReviewPublishControlInv) {
        this.softwareContentSecurityReviewPublishControlInv = softwareContentSecurityReviewPublishControlInv;
    }

    public String getSoftwareContentSecurityReviewPublishControlRemark() {
        return softwareContentSecurityReviewPublishControlRemark;
    }

    public void setSoftwareContentSecurityReviewPublishControlRemark(String softwareContentSecurityReviewPublishControlRemark) {
        this.softwareContentSecurityReviewPublishControlRemark = softwareContentSecurityReviewPublishControlRemark;
    }

    public Double getSoftwareOneClickDisposalInv() {
        return softwareOneClickDisposalInv;
    }

    public void setSoftwareOneClickDisposalInv(Double softwareOneClickDisposalInv) {
        this.softwareOneClickDisposalInv = softwareOneClickDisposalInv;
    }

    public String getSoftwareOneClickDisposalRemark() {
        return softwareOneClickDisposalRemark;
    }

    public void setSoftwareOneClickDisposalRemark(String softwareOneClickDisposalRemark) {
        this.softwareOneClickDisposalRemark = softwareOneClickDisposalRemark;
    }

    public Double getSoftwareSoarInv() {
        return softwareSoarInv;
    }

    public void setSoftwareSoarInv(Double softwareSoarInv) {
        this.softwareSoarInv = softwareSoarInv;
    }

    public String getSoftwareSoarRemark() {
        return softwareSoarRemark;
    }

    public void setSoftwareSoarRemark(String softwareSoarRemark) {
        this.softwareSoarRemark = softwareSoarRemark;
    }

    public Double getSoftwareNetworkAttackTracingInv() {
        return softwareNetworkAttackTracingInv;
    }

    public void setSoftwareNetworkAttackTracingInv(Double softwareNetworkAttackTracingInv) {
        this.softwareNetworkAttackTracingInv = softwareNetworkAttackTracingInv;
    }

    public String getSoftwareNetworkAttackTracingRemark() {
        return softwareNetworkAttackTracingRemark;
    }

    public void setSoftwareNetworkAttackTracingRemark(String softwareNetworkAttackTracingRemark) {
        this.softwareNetworkAttackTracingRemark = softwareNetworkAttackTracingRemark;
    }

    public Double getSoftwareSecurityCapabilityCenterInv() {
        return softwareSecurityCapabilityCenterInv;
    }

    public void setSoftwareSecurityCapabilityCenterInv(Double softwareSecurityCapabilityCenterInv) {
        this.softwareSecurityCapabilityCenterInv = softwareSecurityCapabilityCenterInv;
    }

    public String getSoftwareSecurityCapabilityCenterRemark() {
        return softwareSecurityCapabilityCenterRemark;
    }

    public void setSoftwareSecurityCapabilityCenterRemark(String softwareSecurityCapabilityCenterRemark) {
        this.softwareSecurityCapabilityCenterRemark = softwareSecurityCapabilityCenterRemark;
    }

    public Double getSoftwareSecurityDataCenterInv() {
        return softwareSecurityDataCenterInv;
    }

    public void setSoftwareSecurityDataCenterInv(Double softwareSecurityDataCenterInv) {
        this.softwareSecurityDataCenterInv = softwareSecurityDataCenterInv;
    }

    public String getSoftwareSecurityDataCenterRemark() {
        return softwareSecurityDataCenterRemark;
    }

    public void setSoftwareSecurityDataCenterRemark(String softwareSecurityDataCenterRemark) {
        this.softwareSecurityDataCenterRemark = softwareSecurityDataCenterRemark;
    }

    public Double getSoftwareAttackDefenseDrillInv() {
        return softwareAttackDefenseDrillInv;
    }

    public void setSoftwareAttackDefenseDrillInv(Double softwareAttackDefenseDrillInv) {
        this.softwareAttackDefenseDrillInv = softwareAttackDefenseDrillInv;
    }

    public String getSoftwareAttackDefenseDrillRemark() {
        return softwareAttackDefenseDrillRemark;
    }

    public void setSoftwareAttackDefenseDrillRemark(String softwareAttackDefenseDrillRemark) {
        this.softwareAttackDefenseDrillRemark = softwareAttackDefenseDrillRemark;
    }

    public Double getSoftwarePatchManagementCoreNativeInv() {
        return softwarePatchManagementCoreNativeInv;
    }

    public void setSoftwarePatchManagementCoreNativeInv(Double softwarePatchManagementCoreNativeInv) {
        this.softwarePatchManagementCoreNativeInv = softwarePatchManagementCoreNativeInv;
    }

    public String getSoftwarePatchManagementCoreNativeRemark() {
        return softwarePatchManagementCoreNativeRemark;
    }

    public void setSoftwarePatchManagementCoreNativeRemark(String softwarePatchManagementCoreNativeRemark) {
        this.softwarePatchManagementCoreNativeRemark = softwarePatchManagementCoreNativeRemark;
    }

    public Double getSoftwarePatchManagementCoreExternalInv() {
        return softwarePatchManagementCoreExternalInv;
    }

    public void setSoftwarePatchManagementCoreExternalInv(Double softwarePatchManagementCoreExternalInv) {
        this.softwarePatchManagementCoreExternalInv = softwarePatchManagementCoreExternalInv;
    }

    public String getSoftwarePatchManagementCoreExternalRemark() {
        return softwarePatchManagementCoreExternalRemark;
    }

    public void setSoftwarePatchManagementCoreExternalRemark(String softwarePatchManagementCoreExternalRemark) {
        this.softwarePatchManagementCoreExternalRemark = softwarePatchManagementCoreExternalRemark;
    }

    public Double getSoftwareVulnerabilityManagementCoreNativeInv() {
        return softwareVulnerabilityManagementCoreNativeInv;
    }

    public void setSoftwareVulnerabilityManagementCoreNativeInv(Double softwareVulnerabilityManagementCoreNativeInv) {
        this.softwareVulnerabilityManagementCoreNativeInv = softwareVulnerabilityManagementCoreNativeInv;
    }

    public String getSoftwareVulnerabilityManagementCoreNativeRemark() {
        return softwareVulnerabilityManagementCoreNativeRemark;
    }

    public void setSoftwareVulnerabilityManagementCoreNativeRemark(String softwareVulnerabilityManagementCoreNativeRemark) {
        this.softwareVulnerabilityManagementCoreNativeRemark = softwareVulnerabilityManagementCoreNativeRemark;
    }

    public Double getSoftwareAssetManagementCoreNativeInv() {
        return softwareAssetManagementCoreNativeInv;
    }

    public void setSoftwareAssetManagementCoreNativeInv(Double softwareAssetManagementCoreNativeInv) {
        this.softwareAssetManagementCoreNativeInv = softwareAssetManagementCoreNativeInv;
    }

    public String getSoftwareAssetManagementCoreNativeRemark() {
        return softwareAssetManagementCoreNativeRemark;
    }

    public void setSoftwareAssetManagementCoreNativeRemark(String softwareAssetManagementCoreNativeRemark) {
        this.softwareAssetManagementCoreNativeRemark = softwareAssetManagementCoreNativeRemark;
    }

    public Double getSoftwareSituationalAwarenessCoreNativeInv() {
        return softwareSituationalAwarenessCoreNativeInv;
    }

    public void setSoftwareSituationalAwarenessCoreNativeInv(Double softwareSituationalAwarenessCoreNativeInv) {
        this.softwareSituationalAwarenessCoreNativeInv = softwareSituationalAwarenessCoreNativeInv;
    }

    public String getSoftwareSituationalAwarenessCoreNativeRemark() {
        return softwareSituationalAwarenessCoreNativeRemark;
    }

    public void setSoftwareSituationalAwarenessCoreNativeRemark(String softwareSituationalAwarenessCoreNativeRemark) {
        this.softwareSituationalAwarenessCoreNativeRemark = softwareSituationalAwarenessCoreNativeRemark;
    }

    public Double getSoftwareUebaCoreInv() {
        return softwareUebaCoreInv;
    }

    public void setSoftwareUebaCoreInv(Double softwareUebaCoreInv) {
        this.softwareUebaCoreInv = softwareUebaCoreInv;
    }

    public String getSoftwareUebaCoreRemark() {
        return softwareUebaCoreRemark;
    }

    public void setSoftwareUebaCoreRemark(String softwareUebaCoreRemark) {
        this.softwareUebaCoreRemark = softwareUebaCoreRemark;
    }

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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "BenefitInternalConstructionDO{" +
            "itemNo=" + itemNo +
            ", provincialCompany='" + provincialCompany + '\'' +
            ", projectName='" + projectName + '\'' +
            ", projectCode='" + projectCode + '\'' +
            ", projectType='" + projectType + '\'' +
            ", autoProjectDesignReplyTotalInvestment=" + autoProjectDesignReplyTotalInvestment +
            ", nonSecurityDeviceFee=" + nonSecurityDeviceFee +
            ", securityDeviceFee=" + securityDeviceFee +
            ", totalOtherFee=" + totalOtherFee +
            ", autoSecurityDeviceOtherFee=" + autoSecurityDeviceOtherFee +
            ", abilityFirewallHardwareVendor='" + abilityFirewallHardwareVendor + '\'' +
            ", abilityFirewallHardwareIncNum=" + abilityFirewallHardwareIncNum +
            ", abilityFirewallHardwareInc=" + abilityFirewallHardwareInc +
            ", abilityFirewallHardwareInv=" + abilityFirewallHardwareInv +
            ", abilityFirewallHardwareRemark='" + abilityFirewallHardwareRemark + '\'' +
            ", abilityFirewallAtomicCapabilityVendor='" + abilityFirewallAtomicCapabilityVendor + '\'' +
            ", abilityFirewallAtomicCapabilityIncNum=" + abilityFirewallAtomicCapabilityIncNum +
            ", abilityFirewallAtomicCapabilityInc=" + abilityFirewallAtomicCapabilityInc +
            ", abilityFirewallAtomicCapabilityInv=" + abilityFirewallAtomicCapabilityInv +
            ", abilityFirewallAtomicCapabilityRemark='" + abilityFirewallAtomicCapabilityRemark + '\'' +
            ", abilityIpsHardwareVendor='" + abilityIpsHardwareVendor + '\'' +
            ", abilityIpsHardwareIncNum=" + abilityIpsHardwareIncNum +
            ", abilityIpsHardwareInc=" + abilityIpsHardwareInc +
            ", abilityIpsHardwareInv=" + abilityIpsHardwareInv +
            ", abilityIpsHardwareRemark='" + abilityIpsHardwareRemark + '\'' +
            ", abilityIpsAtomicCapabilityVendor='" + abilityIpsAtomicCapabilityVendor + '\'' +
            ", abilityIpsAtomicCapabilityIncNum=" + abilityIpsAtomicCapabilityIncNum +
            ", abilityIpsAtomicCapabilityInc=" + abilityIpsAtomicCapabilityInc +
            ", abilityIpsAtomicCapabilityInv=" + abilityIpsAtomicCapabilityInv +
            ", abilityIpsAtomicCapabilityRemark='" + abilityIpsAtomicCapabilityRemark + '\'' +
            ", abilityWafHardwareVendor='" + abilityWafHardwareVendor + '\'' +
            ", abilityWafHardwareIncNum=" + abilityWafHardwareIncNum +
            ", abilityWafHardwareInc=" + abilityWafHardwareInc +
            ", abilityWafHardwareInv=" + abilityWafHardwareInv +
            ", abilityWafHardwareRemark='" + abilityWafHardwareRemark + '\'' +
            ", abilityWafDomesticHardwareVendor='" + abilityWafDomesticHardwareVendor + '\'' +
            ", abilityWafDomesticHardwareIncNum=" + abilityWafDomesticHardwareIncNum +
            ", abilityWafDomesticHardwareInc=" + abilityWafDomesticHardwareInc +
            ", abilityWafDomesticHardwareInv=" + abilityWafDomesticHardwareInv +
            ", abilityWafDomesticHardwareRemark='" + abilityWafDomesticHardwareRemark + '\'' +
            ", abilityWafAtomicCapabilityVendor='" + abilityWafAtomicCapabilityVendor + '\'' +
            ", abilityWafAtomicCapabilityIncNum=" + abilityWafAtomicCapabilityIncNum +
            ", abilityWafAtomicCapabilityInc=" + abilityWafAtomicCapabilityInc +
            ", abilityWafAtomicCapabilityInv=" + abilityWafAtomicCapabilityInv +
            ", abilityWafAtomicCapabilityRemark='" + abilityWafAtomicCapabilityRemark + '\'' +
            ", abilityWebDynamicDefenseHardwareVendor='" + abilityWebDynamicDefenseHardwareVendor + '\'' +
            ", abilityWebDynamicDefenseHardwareIncNum=" + abilityWebDynamicDefenseHardwareIncNum +
            ", abilityWebDynamicDefenseHardwareInc=" + abilityWebDynamicDefenseHardwareInc +
            ", abilityWebDynamicDefenseHardwareInv=" + abilityWebDynamicDefenseHardwareInv +
            ", abilityWebDynamicDefenseHardwareRemark='" + abilityWebDynamicDefenseHardwareRemark + '\'' +
            ", abilityWebDynamicDefenseSoftwareVendor='" + abilityWebDynamicDefenseSoftwareVendor + '\'' +
            ", abilityWebDynamicDefenseSoftwareIncNum=" + abilityWebDynamicDefenseSoftwareIncNum +
            ", abilityWebDynamicDefenseSoftwareInc=" + abilityWebDynamicDefenseSoftwareInc +
            ", abilityWebDynamicDefenseSoftwareInv=" + abilityWebDynamicDefenseSoftwareInv +
            ", abilityWebDynamicDefenseSoftwareRemark='" + abilityWebDynamicDefenseSoftwareRemark + '\'' +
            ", abilityTrafficScrubbingVendor='" + abilityTrafficScrubbingVendor + '\'' +
            ", abilityTrafficScrubbingIncNum=" + abilityTrafficScrubbingIncNum +
            ", abilityTrafficScrubbingInc=" + abilityTrafficScrubbingInc +
            ", abilityTrafficScrubbingInv=" + abilityTrafficScrubbingInv +
            ", abilityTrafficScrubbingRemark='" + abilityTrafficScrubbingRemark + '\'' +
            ", abilityFullTrafficAnalysisVendor='" + abilityFullTrafficAnalysisVendor + '\'' +
            ", abilityFullTrafficAnalysisIncNum=" + abilityFullTrafficAnalysisIncNum +
            ", abilityFullTrafficAnalysisInc=" + abilityFullTrafficAnalysisInc +
            ", abilityFullTrafficAnalysisInv=" + abilityFullTrafficAnalysisInv +
            ", abilityFullTrafficAnalysisRemark='" + abilityFullTrafficAnalysisRemark + '\'' +
            ", abilityBastionHostVendor='" + abilityBastionHostVendor + '\'' +
            ", abilityBastionHostIncNum=" + abilityBastionHostIncNum +
            ", abilityBastionHostInc=" + abilityBastionHostInc +
            ", abilityBastionHostInv=" + abilityBastionHostInv +
            ", abilityBastionHostRemark='" + abilityBastionHostRemark + '\'' +
            ", abilityLogAuditVendor='" + abilityLogAuditVendor + '\'' +
            ", abilityLogAuditIncNum=" + abilityLogAuditIncNum +
            ", abilityLogAuditInc=" + abilityLogAuditInc +
            ", abilityLogAuditInv=" + abilityLogAuditInv +
            ", abilityLogAuditRemark='" + abilityLogAuditRemark + '\'' +
            ", abilityEdrVendor='" + abilityEdrVendor + '\'' +
            ", abilityEdrIncNum=" + abilityEdrIncNum +
            ", abilityEdrInc=" + abilityEdrInc +
            ", abilityEdrInv=" + abilityEdrInv +
            ", abilityEdrRemark='" + abilityEdrRemark + '\'' +
            ", abilityHostVulnerabilityScanHardwareVendor='" + abilityHostVulnerabilityScanHardwareVendor + '\'' +
            ", abilityHostVulnerabilityScanHardwareIncNum=" + abilityHostVulnerabilityScanHardwareIncNum +
            ", abilityHostVulnerabilityScanHardwareInc=" + abilityHostVulnerabilityScanHardwareInc +
            ", abilityHostVulnerabilityScanHardwareInv=" + abilityHostVulnerabilityScanHardwareInv +
            ", abilityHostVulnerabilityScanHardwareRemark='" + abilityHostVulnerabilityScanHardwareRemark + '\'' +
            ", abilityHostVulnerabilityScanAtomicCapabilityVendor='" + abilityHostVulnerabilityScanAtomicCapabilityVendor + '\'' +
            ", abilityHostVulnerabilityScanAtomicCapabilityIncNum=" + abilityHostVulnerabilityScanAtomicCapabilityIncNum +
            ", abilityHostVulnerabilityScanAtomicCapabilityInc=" + abilityHostVulnerabilityScanAtomicCapabilityInc +
            ", abilityHostVulnerabilityScanAtomicCapabilityInv=" + abilityHostVulnerabilityScanAtomicCapabilityInv +
            ", abilityHostVulnerabilityScanAtomicCapabilityRemark='" + abilityHostVulnerabilityScanAtomicCapabilityRemark + '\'' +
            ", abilityWebVulnerabilityScanHardwareVendor='" + abilityWebVulnerabilityScanHardwareVendor + '\'' +
            ", abilityWebVulnerabilityScanHardwareIncNum=" + abilityWebVulnerabilityScanHardwareIncNum +
            ", abilityWebVulnerabilityScanHardwareInc=" + abilityWebVulnerabilityScanHardwareInc +
            ", abilityWebVulnerabilityScanHardwareInv=" + abilityWebVulnerabilityScanHardwareInv +
            ", abilityWebVulnerabilityScanHardwareRemark='" + abilityWebVulnerabilityScanHardwareRemark + '\'' +
            ", abilityWebVulnerabilityScanAtomicCapabilityVendor='" + abilityWebVulnerabilityScanAtomicCapabilityVendor + '\'' +
            ", abilityWebVulnerabilityScanAtomicCapabilityIncNum=" + abilityWebVulnerabilityScanAtomicCapabilityIncNum +
            ", abilityWebVulnerabilityScanAtomicCapabilityInc=" + abilityWebVulnerabilityScanAtomicCapabilityInc +
            ", abilityWebVulnerabilityScanAtomicCapabilityInv=" + abilityWebVulnerabilityScanAtomicCapabilityInv +
            ", abilityWebVulnerabilityScanAtomicCapabilityRemark='" + abilityWebVulnerabilityScanAtomicCapabilityRemark + '\'' +
            ", abilityContainerSecurityVendor='" + abilityContainerSecurityVendor + '\'' +
            ", abilityContainerSecurityIncNum=" + abilityContainerSecurityIncNum +
            ", abilityContainerSecurityInc=" + abilityContainerSecurityInc +
            ", abilityContainerSecurityInv=" + abilityContainerSecurityInv +
            ", abilityContainerSecurityRemark='" + abilityContainerSecurityRemark + '\'' +
            ", abilityContainerSecurityAgentVendor='" + abilityContainerSecurityAgentVendor + '\'' +
            ", abilityContainerSecurityAgentIncNum=" + abilityContainerSecurityAgentIncNum +
            ", abilityContainerSecurityAgentInc=" + abilityContainerSecurityAgentInc +
            ", abilityContainerSecurityAgentInv=" + abilityContainerSecurityAgentInv +
            ", abilityContainerSecurityAgentRemark='" + abilityContainerSecurityAgentRemark + '\'' +
            ", abilityWebTamperPreventionVendor='" + abilityWebTamperPreventionVendor + '\'' +
            ", abilityWebTamperPreventionIncNum=" + abilityWebTamperPreventionIncNum +
            ", abilityWebTamperPreventionInc=" + abilityWebTamperPreventionInc +
            ", abilityWebTamperPreventionInv=" + abilityWebTamperPreventionInv +
            ", abilityWebTamperPreventionRemark='" + abilityWebTamperPreventionRemark + '\'' +
            ", abilityWebTamperPreventionAtomicCapabilityVendor='" + abilityWebTamperPreventionAtomicCapabilityVendor + '\'' +
            ", abilityWebTamperPreventionAtomicCapabilityIncNum=" + abilityWebTamperPreventionAtomicCapabilityIncNum +
            ", abilityWebTamperPreventionAtomicCapabilityInc=" + abilityWebTamperPreventionAtomicCapabilityInc +
            ", abilityWebTamperPreventionAtomicCapabilityInv=" + abilityWebTamperPreventionAtomicCapabilityInv +
            ", abilityWebTamperPreventionAtomicCapabilityRemark='" + abilityWebTamperPreventionAtomicCapabilityRemark + '\'' +
            ", abilityApiGatewaySecurityVendor='" + abilityApiGatewaySecurityVendor + '\'' +
            ", abilityApiGatewaySecurityIncNum=" + abilityApiGatewaySecurityIncNum +
            ", abilityApiGatewaySecurityInc=" + abilityApiGatewaySecurityInc +
            ", abilityApiGatewaySecurityInv=" + abilityApiGatewaySecurityInv +
            ", abilityApiGatewaySecurityRemark='" + abilityApiGatewaySecurityRemark + '\'' +
            ", abilityNetworkDlpVendor='" + abilityNetworkDlpVendor + '\'' +
            ", abilityNetworkDlpIncNum=" + abilityNetworkDlpIncNum +
            ", abilityNetworkDlpInc=" + abilityNetworkDlpInc +
            ", abilityNetworkDlpInv=" + abilityNetworkDlpInv +
            ", abilityNetworkDlpRemark='" + abilityNetworkDlpRemark + '\'' +
            ", abilityNetworkDomesticDlpVendor='" + abilityNetworkDomesticDlpVendor + '\'' +
            ", abilityNetworkDomesticDlpIncNum=" + abilityNetworkDomesticDlpIncNum +
            ", abilityNetworkDomesticDlpInc=" + abilityNetworkDomesticDlpInc +
            ", abilityNetworkDomesticDlpInv=" + abilityNetworkDomesticDlpInv +
            ", abilityNetworkDomesticDlpRemark='" + abilityNetworkDomesticDlpRemark + '\'' +
            ", abilityDynamicDataMaskingVendor='" + abilityDynamicDataMaskingVendor + '\'' +
            ", abilityDynamicDataMaskingIncNum=" + abilityDynamicDataMaskingIncNum +
            ", abilityDynamicDataMaskingInc=" + abilityDynamicDataMaskingInc +
            ", abilityDynamicDataMaskingInv=" + abilityDynamicDataMaskingInv +
            ", abilityDynamicDataMaskingRemark='" + abilityDynamicDataMaskingRemark + '\'' +
            ", abilityStaticDataMaskingVendor='" + abilityStaticDataMaskingVendor + '\'' +
            ", abilityStaticDataMaskingIncNum=" + abilityStaticDataMaskingIncNum +
            ", abilityStaticDataMaskingInc=" + abilityStaticDataMaskingInc +
            ", abilityStaticDataMaskingInv=" + abilityStaticDataMaskingInv +
            ", abilityStaticDataMaskingRemark='" + abilityStaticDataMaskingRemark + '\'' +
            ", abilityDatabaseAuditVendor='" + abilityDatabaseAuditVendor + '\'' +
            ", abilityDatabaseAuditIncNum=" + abilityDatabaseAuditIncNum +
            ", abilityDatabaseAuditInc=" + abilityDatabaseAuditInc +
            ", abilityDatabaseAuditInv=" + abilityDatabaseAuditInv +
            ", abilityDatabaseAuditRemark='" + abilityDatabaseAuditRemark + '\'' +
            ", abilitySignalingFirewallVendor='" + abilitySignalingFirewallVendor + '\'' +
            ", abilitySignalingFirewallIncNum=" + abilitySignalingFirewallIncNum +
            ", abilitySignalingFirewallInc=" + abilitySignalingFirewallInc +
            ", abilitySignalingFirewallInv=" + abilitySignalingFirewallInv +
            ", abilitySignalingFirewallRemark='" + abilitySignalingFirewallRemark + '\'' +
            ", abilityZeroTrustSdpVendor='" + abilityZeroTrustSdpVendor + '\'' +
            ", abilityZeroTrustSdpIncNum=" + abilityZeroTrustSdpIncNum +
            ", abilityZeroTrustSdpInc=" + abilityZeroTrustSdpInc +
            ", abilityZeroTrustSdpInv=" + abilityZeroTrustSdpInv +
            ", abilityZeroTrustSdpRemark='" + abilityZeroTrustSdpRemark + '\'' +
            ", abilityZeroTrustDomesticSdpVendor='" + abilityZeroTrustDomesticSdpVendor + '\'' +
            ", abilityZeroTrustDomesticSdpIncNum=" + abilityZeroTrustDomesticSdpIncNum +
            ", abilityZeroTrustDomesticSdpInc=" + abilityZeroTrustDomesticSdpInc +
            ", abilityZeroTrustDomesticSdpInv=" + abilityZeroTrustDomesticSdpInv +
            ", abilityZeroTrustDomesticSdpRemark='" + abilityZeroTrustDomesticSdpRemark + '\'' +
            ", abilityHoneypotVendor='" + abilityHoneypotVendor + '\'' +
            ", abilityHoneypotIncNum=" + abilityHoneypotIncNum +
            ", abilityHoneypotInc=" + abilityHoneypotInc +
            ", abilityHoneypotInv=" + abilityHoneypotInv +
            ", abilityHoneypotRemark='" + abilityHoneypotRemark + '\'' +
            ", abilityDomesticHoneypotVendor='" + abilityDomesticHoneypotVendor + '\'' +
            ", abilityDomesticHoneypotIncNum=" + abilityDomesticHoneypotIncNum +
            ", abilityDomesticHoneypotInc=" + abilityDomesticHoneypotInc +
            ", abilityDomesticHoneypotInv=" + abilityDomesticHoneypotInv +
            ", abilityDomesticHoneypotRemark='" + abilityDomesticHoneypotRemark + '\'' +
            ", abilityMicroSegmentationVendor='" + abilityMicroSegmentationVendor + '\'' +
            ", abilityMicroSegmentationIncNum=" + abilityMicroSegmentationIncNum +
            ", abilityMicroSegmentationInc=" + abilityMicroSegmentationInc +
            ", abilityMicroSegmentationInv=" + abilityMicroSegmentationInv +
            ", abilityMicroSegmentationRemark='" + abilityMicroSegmentationRemark + '\'' +
            ", abilityAbnormalTrafficDetectionForwardingDeviceVendor='" + abilityAbnormalTrafficDetectionForwardingDeviceVendor + '\'' +
            ", abilityAbnormalTrafficDetectionForwardingDeviceIncNum=" + abilityAbnormalTrafficDetectionForwardingDeviceIncNum +
            ", abilityAbnormalTrafficDetectionForwardingDeviceInc=" + abilityAbnormalTrafficDetectionForwardingDeviceInc +
            ", abilityAbnormalTrafficDetectionForwardingDeviceInv=" + abilityAbnormalTrafficDetectionForwardingDeviceInv +
            ", abilityAbnormalTrafficDetectionForwardingDeviceRemark='" + abilityAbnormalTrafficDetectionForwardingDeviceRemark + '\'' +
            ", abilityAbnormalTrafficDetectionCollectionDeviceVendor='" + abilityAbnormalTrafficDetectionCollectionDeviceVendor + '\'' +
            ", abilityAbnormalTrafficDetectionCollectionDeviceIncNum=" + abilityAbnormalTrafficDetectionCollectionDeviceIncNum +
            ", abilityAbnormalTrafficDetectionCollectionDeviceInc=" + abilityAbnormalTrafficDetectionCollectionDeviceInc +
            ", abilityAbnormalTrafficDetectionCollectionDeviceInv=" + abilityAbnormalTrafficDetectionCollectionDeviceInv +
            ", abilityAbnormalTrafficDetectionCollectionDeviceRemark='" + abilityAbnormalTrafficDetectionCollectionDeviceRemark + '\'' +
            ", abilityAbnormalTrafficDetectionReportDeviceVendor='" + abilityAbnormalTrafficDetectionReportDeviceVendor + '\'' +
            ", abilityAbnormalTrafficDetectionReportDeviceIncNum=" + abilityAbnormalTrafficDetectionReportDeviceIncNum +
            ", abilityAbnormalTrafficDetectionReportDeviceInc=" + abilityAbnormalTrafficDetectionReportDeviceInc +
            ", abilityAbnormalTrafficDetectionReportDeviceInv=" + abilityAbnormalTrafficDetectionReportDeviceInv +
            ", abilityAbnormalTrafficDetectionReportDeviceRemark='" + abilityAbnormalTrafficDetectionReportDeviceRemark + '\'' +
            ", abilityDataEncryptionDecryptionVendor='" + abilityDataEncryptionDecryptionVendor + '\'' +
            ", abilityDataEncryptionDecryptionIncNum=" + abilityDataEncryptionDecryptionIncNum +
            ", abilityDataEncryptionDecryptionInc=" + abilityDataEncryptionDecryptionInc +
            ", abilityDataEncryptionDecryptionInv=" + abilityDataEncryptionDecryptionInv +
            ", abilityDataEncryptionDecryptionRemark='" + abilityDataEncryptionDecryptionRemark + '\'' +
            ", abilityBotnetWormDetectionDisposalDeviceVendor='" + abilityBotnetWormDetectionDisposalDeviceVendor + '\'' +
            ", abilityBotnetWormDetectionDisposalDeviceIncNum=" + abilityBotnetWormDetectionDisposalDeviceIncNum +
            ", abilityBotnetWormDetectionDisposalDeviceInc=" + abilityBotnetWormDetectionDisposalDeviceInc +
            ", abilityBotnetWormDetectionDisposalDeviceInv=" + abilityBotnetWormDetectionDisposalDeviceInv +
            ", abilityBotnetWormDetectionDisposalDeviceRemark='" + abilityBotnetWormDetectionDisposalDeviceRemark + '\'' +
            ", abilityBotnetWormDetectionGatewayVendor='" + abilityBotnetWormDetectionGatewayVendor + '\'' +
            ", abilityBotnetWormDetectionGatewayIncNum=" + abilityBotnetWormDetectionGatewayIncNum +
            ", abilityBotnetWormDetectionGatewayInc=" + abilityBotnetWormDetectionGatewayInc +
            ", abilityBotnetWormDetectionGatewayInv=" + abilityBotnetWormDetectionGatewayInv +
            ", abilityBotnetWormDetectionGatewayRemark='" + abilityBotnetWormDetectionGatewayRemark + '\'' +
            ", abilityBotnetWormDetectionSoftwareUpgradeVendor='" + abilityBotnetWormDetectionSoftwareUpgradeVendor + '\'' +
            ", abilityBotnetWormDetectionSoftwareUpgradeIncNum=" + abilityBotnetWormDetectionSoftwareUpgradeIncNum +
            ", abilityBotnetWormDetectionSoftwareUpgradeInc=" + abilityBotnetWormDetectionSoftwareUpgradeInc +
            ", abilityBotnetWormDetectionSoftwareUpgradeInv=" + abilityBotnetWormDetectionSoftwareUpgradeInv +
            ", abilityBotnetWormDetectionSoftwareUpgradeRemark='" + abilityBotnetWormDetectionSoftwareUpgradeRemark + '\'' +
            ", abilityIdcispHardwareVendor='" + abilityIdcispHardwareVendor + '\'' +
            ", abilityIdcispHardwareIncNum=" + abilityIdcispHardwareIncNum +
            ", abilityIdcispHardwareInc=" + abilityIdcispHardwareInc +
            ", abilityIdcispHardwareInv=" + abilityIdcispHardwareInv +
            ", abilityIdcispHardwareRemark='" + abilityIdcispHardwareRemark + '\'' +
            ", abilityIdcispSoftwareVendor='" + abilityIdcispSoftwareVendor + '\'' +
            ", abilityIdcispSoftwareInv=" + abilityIdcispSoftwareInv +
            ", abilityIdcispSoftwareRemark='" + abilityIdcispSoftwareRemark + '\'' +
            ", abilityMobileDpiHardwareVendor='" + abilityMobileDpiHardwareVendor + '\'' +
            ", abilityMobileDpiHardwareIncNum=" + abilityMobileDpiHardwareIncNum +
            ", abilityMobileDpiHardwareInc=" + abilityMobileDpiHardwareInc +
            ", abilityMobileDpiHardwareInv=" + abilityMobileDpiHardwareInv +
            ", abilityMobileDpiHardwareRemark='" + abilityMobileDpiHardwareRemark + '\'' +
            ", abilityMobileDpiSoftwareVendor='" + abilityMobileDpiSoftwareVendor + '\'' +
            ", abilityMobileDpiSoftwareIncNum=" + abilityMobileDpiSoftwareIncNum +
            ", abilityMobileDpiSoftwareInc=" + abilityMobileDpiSoftwareInc +
            ", abilityMobileDpiSoftwareInv=" + abilityMobileDpiSoftwareInv +
            ", abilityMobileDpiSoftwareRemark='" + abilityMobileDpiSoftwareRemark + '\'' +
            ", abilityFixedNetworkDpiHardwareVendor='" + abilityFixedNetworkDpiHardwareVendor + '\'' +
            ", abilityFixedNetworkDpiHardwareIncNum=" + abilityFixedNetworkDpiHardwareIncNum +
            ", abilityFixedNetworkDpiHardwareInc=" + abilityFixedNetworkDpiHardwareInc +
            ", abilityFixedNetworkDpiHardwareInv=" + abilityFixedNetworkDpiHardwareInv +
            ", abilityFixedNetworkDpiHardwareRemark='" + abilityFixedNetworkDpiHardwareRemark + '\'' +
            ", abilityFixedNetworkDpiSoftwareVendor='" + abilityFixedNetworkDpiSoftwareVendor + '\'' +
            ", abilityFixedNetworkDpiSoftwareInv=" + abilityFixedNetworkDpiSoftwareInv +
            ", abilityFixedNetworkDpiSoftwareRemark='" + abilityFixedNetworkDpiSoftwareRemark + '\'' +
            ", abilityMobileMalwareDetectionHardwareVendor='" + abilityMobileMalwareDetectionHardwareVendor + '\'' +
            ", abilityMobileMalwareDetectionHardwareIncNum=" + abilityMobileMalwareDetectionHardwareIncNum +
            ", abilityMobileMalwareDetectionHardwareInc=" + abilityMobileMalwareDetectionHardwareInc +
            ", abilityMobileMalwareDetectionHardwareInv=" + abilityMobileMalwareDetectionHardwareInv +
            ", abilityMobileMalwareDetectionHardwareRemark='" + abilityMobileMalwareDetectionHardwareRemark + '\'' +
            ", abilityMobileMalwareDetectionSoftwareVendor='" + abilityMobileMalwareDetectionSoftwareVendor + '\'' +
            ", abilityMobileMalwareDetectionSoftwareIncNum=" + abilityMobileMalwareDetectionSoftwareIncNum +
            ", abilityMobileMalwareDetectionSoftwareInc=" + abilityMobileMalwareDetectionSoftwareInc +
            ", abilityMobileMalwareDetectionSoftwareInv=" + abilityMobileMalwareDetectionSoftwareInv +
            ", abilityMobileMalwareDetectionSoftwareRemark='" + abilityMobileMalwareDetectionSoftwareRemark + '\'' +
            ", abilityMobileMalwareDetectionSoftwareUpgradeVendor='" + abilityMobileMalwareDetectionSoftwareUpgradeVendor + '\'' +
            ", abilityMobileMalwareDetectionSoftwareUpgradeIncNum=" + abilityMobileMalwareDetectionSoftwareUpgradeIncNum +
            ", abilityMobileMalwareDetectionSoftwareUpgradeInc=" + abilityMobileMalwareDetectionSoftwareUpgradeInc +
            ", abilityMobileMalwareDetectionSoftwareUpgradeInv=" + abilityMobileMalwareDetectionSoftwareUpgradeInv +
            ", abilityMobileMalwareDetectionSoftwareUpgradeRemark='" + abilityMobileMalwareDetectionSoftwareUpgradeRemark + '\'' +
            ", softwareMobileInternetLogRetentionCentralizedInv=" + softwareMobileInternetLogRetentionCentralizedInv +
            ", softwareMobileInternetLogRetentionCentralizedRemark='" + softwareMobileInternetLogRetentionCentralizedRemark + '\'' +
            ", softwareMobileInternetLogRetentionCustomizedInv=" + softwareMobileInternetLogRetentionCustomizedInv +
            ", softwareMobileInternetLogRetentionCustomizedRemark='" + softwareMobileInternetLogRetentionCustomizedRemark + '\'' +
            ", softwareBotnetWormDetectionPlatformTotalPriceInv=" + softwareBotnetWormDetectionPlatformTotalPriceInv +
            ", softwareBotnetWormDetectionPlatformTotalPriceRemark='" + softwareBotnetWormDetectionPlatformTotalPriceRemark + '\'' +
            ", softwareIdcispPlatformTotalPriceInv=" + softwareIdcispPlatformTotalPriceInv +
            ", softwareIdcispPlatformTotalPriceRemark='" + softwareIdcispPlatformTotalPriceRemark + '\'' +
            ", softwareMobileDpiPlatformTotalPriceInv=" + softwareMobileDpiPlatformTotalPriceInv +
            ", softwareMobileDpiPlatformTotalPriceRemark='" + softwareMobileDpiPlatformTotalPriceRemark + '\'' +
            ", softwareFixedNetworkDpiPlatformTotalPriceInv=" + softwareFixedNetworkDpiPlatformTotalPriceInv +
            ", softwareFixedNetworkDpiPlatformTotalPriceRemark='" + softwareFixedNetworkDpiPlatformTotalPriceRemark + '\'' +
            ", softwareMobileMalwareDetectionPlatformTotalPriceInv=" + softwareMobileMalwareDetectionPlatformTotalPriceInv +
            ", softwareMobileMalwareDetectionPlatformTotalPriceRemark='" + softwareMobileMalwareDetectionPlatformTotalPriceRemark + '\'' +
            ", softwareAssetManagementInv=" + softwareAssetManagementInv +
            ", softwareAssetManagementRemark='" + softwareAssetManagementRemark + '\'' +
            ", softwareBaselineManagementInv=" + softwareBaselineManagementInv +
            ", softwareBaselineManagementRemark='" + softwareBaselineManagementRemark + '\'' +
            ", softwareVulnerabilityManagementInv=" + softwareVulnerabilityManagementInv +
            ", softwareVulnerabilityManagementRemark='" + softwareVulnerabilityManagementRemark + '\'' +
            ", softwareInternetExposureManagementInv=" + softwareInternetExposureManagementInv +
            ", softwareInternetExposureManagementRemark='" + softwareInternetExposureManagementRemark + '\'' +
            ", softwareInternalNetworkAssetMappingInv=" + softwareInternalNetworkAssetMappingInv +
            ", softwareInternalNetworkAssetMappingRemark='" + softwareInternalNetworkAssetMappingRemark + '\'' +
            ", softwareAaaaInv=" + softwareAaaaInv +
            ", softwareAaaaRemark='" + softwareAaaaRemark + '\'' +
            ", softwareAppReleaseDetectionInv=" + softwareAppReleaseDetectionInv +
            ", softwareAppReleaseDetectionRemark='" + softwareAppReleaseDetectionRemark + '\'' +
            ", softwareDataAssetManagementInv=" + softwareDataAssetManagementInv +
            ", softwareDataAssetManagementRemark='" + softwareDataAssetManagementRemark + '\'' +
            ", softwarePasswordServiceManagementInv=" + softwarePasswordServiceManagementInv +
            ", softwarePasswordServiceManagementRemark='" + softwarePasswordServiceManagementRemark + '\'' +
            ", softwareThreatIntelligenceInv=" + softwareThreatIntelligenceInv +
            ", softwareThreatIntelligenceRemark='" + softwareThreatIntelligenceRemark + '\'' +
            ", softwareNetworkSecuritySituationalAwarenessInv=" + softwareNetworkSecuritySituationalAwarenessInv +
            ", softwareNetworkSecuritySituationalAwarenessRemark='" + softwareNetworkSecuritySituationalAwarenessRemark + '\'' +
            ", softwareDataSecuritySituationalAwarenessInv=" + softwareDataSecuritySituationalAwarenessInv +
            ", softwareDataSecuritySituationalAwarenessRemark='" + softwareDataSecuritySituationalAwarenessRemark + '\'' +
            ", softwareWebsiteFilingMonitoringInv=" + softwareWebsiteFilingMonitoringInv +
            ", softwareWebsiteFilingMonitoringRemark='" + softwareWebsiteFilingMonitoringRemark + '\'' +
            ", softwareHarmfulInformationMonitoringInv=" + softwareHarmfulInformationMonitoringInv +
            ", softwareHarmfulInformationMonitoringRemark='" + softwareHarmfulInformationMonitoringRemark + '\'' +
            ", softwareAntiFraudManagementInv=" + softwareAntiFraudManagementInv +
            ", softwareAntiFraudManagementRemark='" + softwareAntiFraudManagementRemark + '\'' +
            ", softwareContentSecurityReviewPublishControlInv=" + softwareContentSecurityReviewPublishControlInv +
            ", softwareContentSecurityReviewPublishControlRemark='" + softwareContentSecurityReviewPublishControlRemark + '\'' +
            ", softwareOneClickDisposalInv=" + softwareOneClickDisposalInv +
            ", softwareOneClickDisposalRemark='" + softwareOneClickDisposalRemark + '\'' +
            ", softwareSoarInv=" + softwareSoarInv +
            ", softwareSoarRemark='" + softwareSoarRemark + '\'' +
            ", softwareNetworkAttackTracingInv=" + softwareNetworkAttackTracingInv +
            ", softwareNetworkAttackTracingRemark='" + softwareNetworkAttackTracingRemark + '\'' +
            ", softwareSecurityCapabilityCenterInv=" + softwareSecurityCapabilityCenterInv +
            ", softwareSecurityCapabilityCenterRemark='" + softwareSecurityCapabilityCenterRemark + '\'' +
            ", softwareSecurityDataCenterInv=" + softwareSecurityDataCenterInv +
            ", softwareSecurityDataCenterRemark='" + softwareSecurityDataCenterRemark + '\'' +
            ", softwareAttackDefenseDrillInv=" + softwareAttackDefenseDrillInv +
            ", softwareAttackDefenseDrillRemark='" + softwareAttackDefenseDrillRemark + '\'' +
            ", softwarePatchManagementCoreNativeInv=" + softwarePatchManagementCoreNativeInv +
            ", softwarePatchManagementCoreNativeRemark='" + softwarePatchManagementCoreNativeRemark + '\'' +
            ", softwarePatchManagementCoreExternalInv=" + softwarePatchManagementCoreExternalInv +
            ", softwarePatchManagementCoreExternalRemark='" + softwarePatchManagementCoreExternalRemark + '\'' +
            ", softwareVulnerabilityManagementCoreNativeInv=" + softwareVulnerabilityManagementCoreNativeInv +
            ", softwareVulnerabilityManagementCoreNativeRemark='" + softwareVulnerabilityManagementCoreNativeRemark + '\'' +
            ", softwareAssetManagementCoreNativeInv=" + softwareAssetManagementCoreNativeInv +
            ", softwareAssetManagementCoreNativeRemark='" + softwareAssetManagementCoreNativeRemark + '\'' +
            ", softwareSituationalAwarenessCoreNativeInv=" + softwareSituationalAwarenessCoreNativeInv +
            ", softwareSituationalAwarenessCoreNativeRemark='" + softwareSituationalAwarenessCoreNativeRemark + '\'' +
            ", softwareUebaCoreInv=" + softwareUebaCoreInv +
            ", softwareUebaCoreRemark='" + softwareUebaCoreRemark + '\'' +
            ", abilityFirewallHardwareVar=" + abilityFirewallHardwareVar +
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
            ", orgCode='" + orgCode + '\'' +
            "} " + super.toString();
    }
}
