package com.sama.maint.object.dto.ledger;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/24 15:30
 */
@Schema(description = "对内建设数据展示导出表")
public class BenefitInternalConstructionUnifiedDTO implements Serializable{

    @Serial
    private static final long serialVersionUID = 6406157780704493974L;

    @Schema(description = "序号")
    @ExcelProperty(value = "序号", index = 0)
    private Long itemNo;

    @Schema(description = "项目编号")
    @ExcelProperty(value = "项目编号", index = 1)
    private String projectCode;

    @Schema(description = "省公司")
    @ExcelProperty(value = "省公司", index = 2)
    private String provincialCompany;

    @Schema(description = "项目名称")
    @ExcelProperty(value = "项目名称", index = 3)
    private String projectName;

    @Schema(description = "项目类型")
    @ExcelProperty(value = "项目类型", index = 4)
    private String projectType;

    @Schema(description = "项目设计批复总投资（元）")
    @ExcelProperty(value = "项目设计批复总投资（元）", index = 5)
    private Double autoProjectDesignReplyTotalInvestment;

    @Schema(description = "非安全类设备费（元）")
    @ExcelProperty(value = "非安全类设备费（元）", index = 6)
    private Double nonSecurityDeviceFee;

    @Schema(description = "安全类设备费（元）")
    @ExcelProperty(value = "安全类设备费（元）", index = 7)
    private Double securityDeviceFee;

    @Schema(description = "总体其他费（元）")
    @ExcelProperty(value = "总体其他费（元）", index = 8)
    private Double totalOtherFee;

    @Schema(description = "安全类设备其他费（元）")
    @ExcelProperty(value = "安全类设备其他费（元）", index = 9)
    private Double autoSecurityDeviceOtherFee;

    @Schema(description = "防火墙-硬件单位造价（元）")
    @ExcelProperty(value = "防火墙-硬件单位造价（元）", index = 10)
    private Double abilityFirewallHardwareVar;

    @Schema(description = "防火墙-原子能力单位造价（元）")
    @ExcelProperty(value = "防火墙-原子能力单位造价（元）", index = 11)
    private Double abilityFirewallAtomicCapabilityVar;

    @Schema(description = "IPS-硬件单位造价（元）")
    @ExcelProperty(value = "IPS-硬件单位造价（元）", index = 12)
    private Double abilityIpsHardwareVar;

    @Schema(description = "IPS-原子能力单位造价（元）")
    @ExcelProperty(value = "IPS-原子能力单位造价（元）", index = 13)
    private Double abilityIpsAtomicCapabilityVar;

    @Schema(description = "Web防御（WAF）-硬件单位造价（元）")
    @ExcelProperty(value = "Web防御（WAF）-硬件单位造价（元）", index = 14)
    private Double abilityWafHardwareVar;

    @Schema(description = "Web防御（WAF）-硬件-国产化设备单位造价（元）")
    @ExcelProperty(value = "Web防御（WAF）-硬件-国产化设备单位造价（元）", index = 15)
    private Double abilityWafDomesticHardwareVar;

    @Schema(description = "Web防御（WAF）-原子能力单位造价（元）")
    @ExcelProperty(value = "Web防御（WAF）-原子能力单位造价（元）", index = 16)
    private Double abilityWafAtomicCapabilityVar;

    @Schema(description = "Web防御（动态防护）-硬件单位造价（元）")
    @ExcelProperty(value = "Web防御（动态防护）-硬件单位造价（元）", index = 17)
    private Double abilityWebDynamicDefenseHardwareVar;

    @Schema(description = "Web防御（动态防护）-软件单位造价（元）")
    @ExcelProperty(value = "Web防御（动态防护）-软件单位造价（元）", index = 18)
    private Double abilityWebDynamicDefenseSoftwareVar;

    @Schema(description = "流量清洗（抗DDoS）单位造价（元）")
    @ExcelProperty(value = "流量清洗（抗DDoS）单位造价（元）", index = 19)
    private Double abilityTrafficScrubbingVar;

    @Schema(description = "流量分析（全流量分析）单位造价（元）")
    @ExcelProperty(value = "流量分析（全流量分析）单位造价（元）", index = 20)
    private Double abilityFullTrafficAnalysisVar;

    @Schema(description = "堡垒机单位造价（元）")
    @ExcelProperty(value = "堡垒机单位造价（元）", index = 21)
    private Double abilityBastionHostVar;

    @Schema(description = "日志审计单位造价（元）")
    @ExcelProperty(value = "日志审计单位造价（元）", index = 22)
    private Double abilityLogAuditVar;

    @Schema(description = "EDR（含防病毒）单位造价（元）")
    @ExcelProperty(value = "EDR（含防病毒）单位造价（元）", index = 23)
    private Double abilityEdrVar;

    @Schema(description = "漏洞扫描（主机）-硬件单位造价（元）")
    @ExcelProperty(value = "漏洞扫描（主机）-硬件单位造价（元）", index = 24)
    private Double abilityHostVulnerabilityScanHardwareVar;

    @Schema(description = "漏洞扫描（主机）-原子能力单位造价（元）")
    @ExcelProperty(value = "漏洞扫描（主机）-原子能力单位造价（元）", index = 25)
    private Double abilityHostVulnerabilityScanAtomicCapabilityVar;

    @Schema(description = "漏洞扫描（Web）-硬件单位造价（元）")
    @ExcelProperty(value = "漏洞扫描（Web）-硬件单位造价（元）", index = 26)
    private Double abilityWebVulnerabilityScanHardwareVar;

    @Schema(description = "漏洞扫描（Web）-原子能力单位造价（元）")
    @ExcelProperty(value = "漏洞扫描（Web）-原子能力单位造价（元）", index = 27)
    private Double abilityWebVulnerabilityScanAtomicCapabilityVar;

    @Schema(description = "容器安全单位造价（元）")
    @ExcelProperty(value = "容器安全单位造价（元）", index = 28)
    private Double abilityContainerSecurityVar;

    @Schema(description = "容器安全-纯agent单位造价（元）")
    @ExcelProperty(value = "容器安全-纯agent单位造价（元）", index = 29)
    private Double abilityContainerSecurityAgentVar;

    @Schema(description = "网页防篡改-非池化单位造价（元）")
    @ExcelProperty(value = "网页防篡改-非池化单位造价（元）", index = 30)
    private Double abilityWebTamperPreventionVar;

    @Schema(description = "网页防篡改-原子能力单位造价（元）")
    @ExcelProperty(value = "网页防篡改-原子能力单位造价（元）", index = 31)
    private Double abilityWebTamperPreventionAtomicCapabilityVar;

    @Schema(description = "接口安全管控（API网关）单位造价（元）")
    @ExcelProperty(value = "接口安全管控（API网关）单位造价（元）", index = 32)
    private Double abilityApiGatewaySecurityVar;

    @Schema(description = "数据防泄漏（网络侧）单位造价（元）")
    @ExcelProperty(value = "数据防泄漏（网络侧）单位造价（元）", index = 33)
    private Double abilityNetworkDlpVar;

    @Schema(description = "数据防泄漏（网络侧）-国产化设备单位造价（元）")
    @ExcelProperty(value = "数据防泄漏（网络侧）-国产化设备单位造价（元）", index = 34)
    private Double abilityNetworkDomesticDlpVar;

    @Schema(description = "数据脱敏-动态单位造价（元）")
    @ExcelProperty(value = "数据脱敏-动态单位造价（元）", index = 35)
    private Double abilityDynamicDataMaskingVar;

    @Schema(description = "数据脱敏-静态单位造价（元）")
    @ExcelProperty(value = "数据脱敏-静态单位造价（元）", index = 36)
    private Double abilityStaticDataMaskingVar;

    @Schema(description = "数据库审计单位造价（元）")
    @ExcelProperty(value = "数据库审计单位造价（元）", index = 37)
    private Double abilityDatabaseAuditVar;

    @Schema(description = "信令防火墙 C-IWF（5GC）单位造价（元）")
    @ExcelProperty(value = "信令防火墙 C-IWF（5GC）单位造价（元）", index = 38)
    private Double abilitySignalingFirewallVar;

    @Schema(description = "零信任（SDP）单位造价（元）")
    @ExcelProperty(value = "零信任（SDP）单位造价（元）", index = 39)
    private Double abilityZeroTrustSdpVar;

    @Schema(description = "零信任（SDP）-国产化设备单位造价（元）")
    @ExcelProperty(value = "零信任（SDP）-国产化设备单位造价（元）", index = 40)
    private Double abilityZeroTrustDomesticSdpVar;

    @Schema(description = "蜜罐单位造价（元）")
    @ExcelProperty(value = "蜜罐单位造价（元）", index = 41)
    private Double abilityHoneypotVar;

    @Schema(description = "蜜罐-国产化设备单位造价（元）")
    @ExcelProperty(value = "蜜罐-国产化设备单位造价（元）", index = 42)
    private Double abilityDomesticHoneypotVar;

    @Schema(description = "微隔离单位造价（元）")
    @ExcelProperty(value = "微隔离单位造价（元）", index = 43)
    private Double abilityMicroSegmentationVar;

    @Schema(description = "异常流量检测-流量转发设备单位造价（元）")
    @ExcelProperty(value = "异常流量检测-流量转发设备单位造价（元）", index = 44)
    private Double abilityAbnormalTrafficDetectionForwardingDeviceVar;

    @Schema(description = "异常流量检测-流量采集设备单位造价（元）")
    @ExcelProperty(value = "异常流量检测-流量采集设备单位造价（元）", index = 45)
    private Double abilityAbnormalTrafficDetectionCollectionDeviceVar;

    @Schema(description = "异常流量检测-报表处理设备单位造价（元）")
    @ExcelProperty(value = "异常流量检测-报表处理设备单位造价（元）", index = 46)
    private Double abilityAbnormalTrafficDetectionReportDeviceVar;

    @Schema(description = "数据加解密（5GC）单位造价（元）")
    @ExcelProperty(value = "数据加解密（5GC）单位造价（元）", index = 47)
    private Double abilityDataEncryptionDecryptionVar;

    @Schema(description = "僵木蠕监测-监测处置设备单位造价（元）")
    @ExcelProperty(value = "僵木蠕监测-监测处置设备单位造价（元）", index = 48)
    private Double abilityBotnetWormDetectionDisposalDeviceVar;

    @Schema(description = "僵木蠕监测-接口转发网关单位造价（元）")
    @ExcelProperty(value = "僵木蠕监测-接口转发网关单位造价（元）", index = 49)
    private Double abilityBotnetWormDetectionGatewayVar;

    @Schema(description = "僵木蠕监测-监测处置设备考核软件升级单位造价（元）")
    @ExcelProperty(value = "僵木蠕监测-监测处置设备考核软件升级单位造价（元）", index = 50)
    private Double abilityBotnetWormDetectionSoftwareUpgradeVar;

    @Schema(description = "IDCISP-硬件（分流器+全量服务器）单位造价（元）")
    @ExcelProperty(value = "IDCISP-硬件（分流器+全量服务器）单位造价（元）", index = 51)
    private Double abilityIdcispHardwareVar;

    @Schema(description = "IDCISP-软件单位造价（元）")
    @ExcelProperty(value = "IDCISP-软件单位造价（元）", index = 52)
    private Double abilityIdcispSoftwareVar;

    @Schema(description = "移动DPI-硬件单位造价（元）")
    @ExcelProperty(value = "移动DPI-硬件单位造价（元）", index = 53)
    private Double abilityMobileDpiHardwareVar;

    @Schema(description = "移动DPI-软件单位造价（元）")
    @ExcelProperty(value = "移动DPI-软件单位造价（元）", index = 54)
    private Double abilityMobileDpiSoftwareVar;

    @Schema(description = "固网DPI-硬件（分流器+DPI服务器）单位造价（元）")
    @ExcelProperty(value = "固网DPI-硬件（分流器+DPI服务器）单位造价（元）", index = 55)
    private Double abilityFixedNetworkDpiHardwareVar;

    @Schema(description = "固网DPI-软件单位造价（元）")
    @ExcelProperty(value = "固网DPI-软件单位造价（元）", index = 56)
    private Double abilityFixedNetworkDpiSoftwareVar;

    @Schema(description = "移动恶意程序监测-硬件单位造价（元）")
    @ExcelProperty(value = "移动恶意程序监测-硬件单位造价（元）", index = 57)
    private Double abilityMobileMalwareDetectionHardwareVar;

    @Schema(description = "移动恶意程序监测-软件单位造价（元）")
    @ExcelProperty(value = "移动恶意程序监测-软件单位造价（元）", index = 58)
    private Double abilityMobileMalwareDetectionSoftwareVar;

    @Schema(description = "移动恶意程序监测-软件升级单位造价（元）")
    @ExcelProperty(value = "移动恶意程序监测-软件升级单位造价（元）", index = 59)
    private Double abilityMobileMalwareDetectionSoftwareUpgradeVar;

    @Schema(description = "移动上网日志留存-集采单位造价（元）")
    @ExcelProperty(value = "移动上网日志留存-集采单位造价（元）", index = 60)
    private Double softwareMobileInternetLogRetentionCentralizedVar;

    @Schema(description = "移动上网日志留存-个性化省采单位造价（元）")
    @ExcelProperty(value = "移动上网日志留存-个性化省采单位造价（元）", index = 61)
    private Double softwareMobileInternetLogRetentionCustomizedVar;

    @Schema(description = "僵木蠕监测-平台总价单位造价（元）")
    @ExcelProperty(value = "僵木蠕监测-平台总价单位造价（元）", index = 62)
    private Double softwareBotnetWormDetectionPlatformTotalPriceVar;

    @Schema(description = "IDCISP-平台总价单位造价（元）")
    @ExcelProperty(value = "IDCISP-平台总价单位造价（元）", index = 63)
    private Double softwareIdcispPlatformTotalPriceVar;

    @Schema(description = "移动DPI-平台总价单位造价（元）")
    @ExcelProperty(value = "移动DPI-平台总价单位造价（元）", index = 64)
    private Double softwareMobileDpiPlatformTotalPriceVar;

    @Schema(description = "固网DPI-平台总价单位造价（元）")
    @ExcelProperty(value = "固网DPI-平台总价单位造价（元）", index = 65)
    private Double softwareFixedNetworkDpiPlatformTotalPriceVar;

    @Schema(description = "移动恶意程序监测-平台总价单位造价（元）")
    @ExcelProperty(value = "移动恶意程序监测-平台总价单位造价（元）", index = 66)
    private Double softwareMobileMalwareDetectionPlatformTotalPriceVar;

    @Schema(description = "资产管理单位造价（元）")
    @ExcelProperty(value = "资产管理单位造价（元）", index = 67)
    private Double softwareAssetManagementVar;

    @Schema(description = "基线管理单位造价（元）")
    @ExcelProperty(value = "基线管理单位造价（元）", index = 68)
    private Double softwareBaselineManagementVar;

    @Schema(description = "漏洞管理单位造价（元）")
    @ExcelProperty(value = "漏洞管理单位造价（元）", index = 69)
    private Double softwareVulnerabilityManagementVar;

    @Schema(description = "互联网暴露面管理单位造价（元）")
    @ExcelProperty(value = "互联网暴露面管理单位造价（元）", index = 70)
    private Double softwareInternetExposureManagementVar;

    @Schema(description = "内网资产测绘单位造价（元）")
    @ExcelProperty(value = "内网资产测绘单位造价（元）", index = 71)
    private Double softwareInternalNetworkAssetMappingVar;

    @Schema(description = "4A单位造价（元）")
    @ExcelProperty(value = "4A单位造价（元）", index = 72)
    private Double softwareAaaaVar;

    @Schema(description = "APP上线检测单位造价（元）")
    @ExcelProperty(value = "APP上线检测单位造价（元）", index = 73)
    private Double softwareAppReleaseDetectionVar;

    @Schema(description = "数据资产管理单位造价（元）")
    @ExcelProperty(value = "数据资产管理单位造价（元）", index = 74)
    private Double softwareDataAssetManagementVar;

    @Schema(description = "密码服务管理单位造价（元）")
    @ExcelProperty(value = "密码服务管理单位造价（元）", index = 75)
    private Double softwarePasswordServiceManagementVar;

    @Schema(description = "威胁情报单位造价（元）")
    @ExcelProperty(value = "威胁情报单位造价（元）", index = 76)
    private Double softwareThreatIntelligenceVar;

    @Schema(description = "网络安全态势感知单位造价（元）")
    @ExcelProperty(value = "网络安全态势感知单位造价（元）", index = 77)
    private Double softwareNetworkSecuritySituationalAwarenessVar;

    @Schema(description = "数据安全态势感知单位造价（元）")
    @ExcelProperty(value = "数据安全态势感知单位造价（元）", index = 78)
    private Double softwareDataSecuritySituationalAwarenessVar;

    @Schema(description = "互联网网站备案监测单位造价（元）")
    @ExcelProperty(value = "互联网网站备案监测单位造价（元）", index = 79)
    private Double softwareWebsiteFilingMonitoringVar;

    @Schema(description = "不良信息监测单位造价（元）")
    @ExcelProperty(value = "不良信息监测单位造价（元）", index = 80)
    private Double softwareHarmfulInformationMonitoringVar;

    @Schema(description = "反诈管理单位造价（元）")
    @ExcelProperty(value = "反诈管理单位造价（元）", index = 81)
    private Double softwareAntiFraudManagementVar;

    @Schema(description = "内容安全“先审后发”管控单位造价（元）")
    @ExcelProperty(value = "内容安全“先审后发”管控单位造价（元）", index = 82)
    private Double softwareContentSecurityReviewPublishControlVar;

    @Schema(description = "一键处置（含一键派单、封堵、关停）单位造价（元）")
    @ExcelProperty(value = "一键处置（含一键派单、封堵、关停）单位造价（元）", index = 83)
    private Double softwareOneClickDisposalVar;

    @Schema(description = "SOAR单位造价（元）")
    @ExcelProperty(value = "SOAR单位造价（元）", index = 84)
    private Double softwareSoarVar;

    @Schema(description = "网络攻击溯源单位造价（元）")
    @ExcelProperty(value = "网络攻击溯源单位造价（元）", index = 85)
    private Double softwareNetworkAttackTracingVar;

    @Schema(description = "安全能力中心单位造价（元）")
    @ExcelProperty(value = "安全能力中心单位造价（元）", index = 86)
    private Double softwareSecurityCapabilityCenterVar;

    @Schema(description = "安全数据中心单位造价（元）")
    @ExcelProperty(value = "安全数据中心单位造价（元）", index = 87)
    private Double softwareSecurityDataCenterVar;

    @Schema(description = "攻防演练单位造价（元）")
    @ExcelProperty(value = "攻防演练单位造价（元）", index = 88)
    private Double softwareAttackDefenseDrillVar;

    @Schema(description = "补丁管理（5GC内生）单位造价（元）")
    @ExcelProperty(value = "补丁管理（5GC内生）单位造价（元）", index = 89)
    private Double softwarePatchManagementCoreNativeVar;

    @Schema(description = "补丁管理（5GC外挂）单位造价（元）")
    @ExcelProperty(value = "补丁管理（5GC外挂）单位造价（元）", index = 90)
    private Double softwarePatchManagementCoreExternalVar;

    @Schema(description = "漏洞管理（5GC内生）单位造价（元）")
    @ExcelProperty(value = "漏洞管理（5GC内生）单位造价（元）", index = 91)
    private Double softwareVulnerabilityManagementCoreNativeVar;

    @Schema(description = "资产管理（5GC内生）单位造价（元）")
    @ExcelProperty(value = "资产管理（5GC内生）单位造价（元）", index = 92)
    private Double softwareAssetManagementCoreNativeVar;

    @Schema(description = "态势感知（5GC内生）单位造价（元）")
    @ExcelProperty(value = "态势感知（5GC内生）单位造价（元）", index = 93)
    private Double softwareSituationalAwarenessCoreNativeVar;

    @Schema(description = "UEBA（5GC用户行为分析）单位造价（元）")
    @ExcelProperty(value = "UEBA（5GC用户行为分析）单位造价（元）", index = 94)
    private Double softwareUebaCoreVar;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Schema(description = "更新时间")
    @ExcelProperty(value = "创建时间", index = 95)
    private Date updateTime;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @Schema(description = "创建时间")
    @ExcelProperty(value = "更新时间", index = 96)
    private Date createTime;

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BenefitInternalConstructionUnifiedDTO{" +
            "itemNo=" + itemNo +
            ", projectCode='" + projectCode + '\'' +
            ", provincialCompany='" + provincialCompany + '\'' +
            ", projectName='" + projectName + '\'' +
            ", projectType='" + projectType + '\'' +
            ", autoProjectDesignReplyTotalInvestment=" + autoProjectDesignReplyTotalInvestment +
            ", nonSecurityDeviceFee=" + nonSecurityDeviceFee +
            ", securityDeviceFee=" + securityDeviceFee +
            ", totalOtherFee=" + totalOtherFee +
            ", autoSecurityDeviceOtherFee=" + autoSecurityDeviceOtherFee +
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
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            '}';
    }
}
