package com.sama.analytic.metric;

import com.alibaba.fastjson2.JSON;
import com.api.analytic.service.DuplicateCollectDubboService;
import com.sama.analytic.AnalyticApplication;
import com.sama.analytic.service.BenefitExternalEmpowermentService;
import com.sama.analytic.service.BenefitInternalConstructionService;
import com.sama.analytic.service.ComprehensiveProtectionService;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/23 16:01
 */
@SpringBootTest(classes = AnalyticApplication.class)
public class DuplicateCollectDubboServiceTest {

    private static final Logger logger = LogManager.getLogger(DuplicateCollectDubboServiceTest.class);

    @Resource
    ComprehensiveProtectionService comprehensiveProtectionService;

    @Resource
    BenefitInternalConstructionService benefitInternalConstructionService;

    @Resource
    BenefitExternalEmpowermentService benefitExternalEmpowermentService;

    @DubboReference
    DuplicateCollectDubboService duplicateCollectDubboService;

    private final String ORG_CODE = "02250011";

    /**
     * 1 数据库的增删改查（修改后必测！！！）
     */
    @Test
    public void rawDBTests() {
        comprehensiveProtectionDBTest();
        benefitInternalConstructionDBTest();
        benefitInternalConstructionExtraDBTest();
        benefitExternalEmpowermentDBTest();
    }

    /**
     * copy from ComprehensiveProtectionEngineServiceTest
     */
    @Test
    public void comprehensiveProtectionDBTest() {
        // 增
        ComprehensiveProtectionExtendedDO init = new ComprehensiveProtectionExtendedDO();
        init.setComprehensiveScenarioType("APT攻击防护场景（有更新，见评估指标初稿及意见）");
        init.setEvaluationItem("攻击链检测率");
        init.setProcessingDataCn("攻击链路被检测的数量");
        init.setProcessingDataUnit("个");
        init.setProcessingData(50);
        // init.setCalculationMethod("");
        // init.setAssessedValue(new BigDecimal("0"));
        init.setOrgCode(ORG_CODE);

        comprehensiveProtectionService.add(init);
        logger.info("【Test】 comprehensive_protection 表新增结果：{}", JSON.toJSONString(init));

        // 查
        init.setCreateTime(null);
        init.setUpdateTime(null);
        List<ComprehensiveProtectionExtendedDO> selectRes = comprehensiveProtectionService.queryList(init);
        logger.info("【Test】 comprehensive_protection 表查询结果：{}", JSON.toJSONString(selectRes));

        // turnover
        comprehensiveProtectionService.turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.SOFT);
    }

    /**
     * copy from BenefitEngineServiceTest
     */
    @Test
    public void benefitInternalConstructionDBTest() {
        // 增
        BenefitInternalConstructionDO init = new BenefitInternalConstructionDO();
        init.setItemNo(1L);
        init.setProvincialCompany("模拟");
        init.setProjectName("名称");
        init.setProjectCode("编号");
        init.setProjectType("类型");
        init.setAutoProjectDesignReplyTotalInvestment(100.0D);
        init.setNonSecurityDeviceFee(200.0D);
        init.setSecurityDeviceFee(300.0D);
        init.setTotalOtherFee(500.0D);
        init.setAutoSecurityDeviceOtherFee(600.0D);
        init.setAbilityFirewallHardwareVendor("1");
        init.setAbilityFirewallHardwareIncNum(1);
        init.setAbilityFirewallHardwareInc(1.0D);
        init.setAbilityFirewallHardwareInv(1.D);
        init.setAbilityFirewallHardwareRemark("1");
        init.setAbilityFirewallAtomicCapabilityVendor("2");
        init.setAbilityFirewallAtomicCapabilityIncNum(2);
        init.setAbilityFirewallAtomicCapabilityInc(2.0D);
        init.setAbilityFirewallAtomicCapabilityInv(2.0D);
        init.setAbilityFirewallAtomicCapabilityRemark("2");
        init.setAbilityIpsHardwareVendor("3");
        init.setAbilityIpsHardwareIncNum(3);
        init.setAbilityIpsHardwareInc(3.0D);
        init.setAbilityIpsHardwareInv(3.0D);
        init.setAbilityIpsHardwareRemark("3");
        init.setAbilityIpsAtomicCapabilityVendor("4");
        init.setAbilityIpsAtomicCapabilityIncNum(4);
        init.setAbilityIpsAtomicCapabilityInc(4.0D);
        init.setAbilityIpsAtomicCapabilityInv(4.0D);
        init.setAbilityIpsAtomicCapabilityRemark("4");
        init.setAbilityWafHardwareVendor("5");
        init.setAbilityWafHardwareIncNum(5);
        init.setAbilityWafHardwareInc(5.0D);
        init.setAbilityWafHardwareInv(5.0D);
        init.setAbilityWafHardwareRemark("5");
        init.setAbilityWafDomesticHardwareVendor("6");
        init.setAbilityWafDomesticHardwareIncNum(6);
        init.setAbilityWafDomesticHardwareInc(6.0D);
        init.setAbilityWafDomesticHardwareInv(6.0D);
        init.setAbilityWafDomesticHardwareRemark("6");
        init.setAbilityWafAtomicCapabilityVendor("7");
        init.setAbilityWafAtomicCapabilityIncNum(7);
        init.setAbilityWafAtomicCapabilityInc(7.0D);
        init.setAbilityWafAtomicCapabilityInv(7.0D);
        init.setAbilityWafAtomicCapabilityRemark("7");
        init.setAbilityWebDynamicDefenseHardwareVendor("8");
        init.setAbilityWebDynamicDefenseHardwareIncNum(8);
        init.setAbilityWebDynamicDefenseHardwareInc(8.0D);
        init.setAbilityWebDynamicDefenseHardwareInv(8.0D);
        init.setAbilityWebDynamicDefenseHardwareRemark("8");
        init.setAbilityWebDynamicDefenseSoftwareVendor("9");
        init.setAbilityWebDynamicDefenseSoftwareIncNum(9);
        init.setAbilityWebDynamicDefenseSoftwareInc(9.0D);
        init.setAbilityWebDynamicDefenseSoftwareInv(9.0D);
        init.setAbilityWebDynamicDefenseSoftwareRemark("9");
        init.setAbilityTrafficScrubbingVendor("10");
        init.setAbilityTrafficScrubbingIncNum(10);
        init.setAbilityTrafficScrubbingInc(10.0D);
        init.setAbilityTrafficScrubbingInv(10.0D);
        init.setAbilityTrafficScrubbingRemark("10");
        init.setAbilityFullTrafficAnalysisVendor("11");
        init.setAbilityFullTrafficAnalysisIncNum(11);
        init.setAbilityFullTrafficAnalysisInc(11.0D);
        init.setAbilityFullTrafficAnalysisInv(11.0D);
        init.setAbilityFullTrafficAnalysisRemark("11");
        init.setAbilityBastionHostVendor("12");
        init.setAbilityBastionHostIncNum(12);
        init.setAbilityBastionHostInc(12.0D);
        init.setAbilityBastionHostInv(12.0D);
        init.setAbilityBastionHostRemark("12");
        init.setAbilityLogAuditVendor("13");
        init.setAbilityLogAuditIncNum(13);
        init.setAbilityLogAuditInc(13.0D);
        init.setAbilityLogAuditInv(13.0D);
        init.setAbilityLogAuditRemark("13");
        init.setAbilityEdrVendor("14");
        init.setAbilityEdrIncNum(14);
        init.setAbilityEdrInc(14.0D);
        init.setAbilityEdrInv(14.0D);
        init.setAbilityEdrRemark("14");
        init.setAbilityHostVulnerabilityScanHardwareVendor("15");
        init.setAbilityHostVulnerabilityScanHardwareIncNum(15);
        init.setAbilityHostVulnerabilityScanHardwareInc(15.0D);
        init.setAbilityHostVulnerabilityScanHardwareInv(15.0D);
        init.setAbilityHostVulnerabilityScanHardwareRemark("15");
        init.setAbilityHostVulnerabilityScanAtomicCapabilityVendor("16");
        init.setAbilityHostVulnerabilityScanAtomicCapabilityIncNum(16);
        init.setAbilityHostVulnerabilityScanAtomicCapabilityInc(16.0D);
        init.setAbilityHostVulnerabilityScanAtomicCapabilityInv(16.0D);
        init.setAbilityHostVulnerabilityScanAtomicCapabilityRemark("16");
        init.setAbilityWebVulnerabilityScanHardwareVendor("17");
        init.setAbilityWebVulnerabilityScanHardwareIncNum(17);
        init.setAbilityWebVulnerabilityScanHardwareInc(17.0D);
        init.setAbilityWebVulnerabilityScanHardwareInv(17.0D);
        init.setAbilityWebVulnerabilityScanHardwareRemark("17");
        init.setAbilityWebVulnerabilityScanAtomicCapabilityVendor("18");
        init.setAbilityWebVulnerabilityScanAtomicCapabilityIncNum(18);
        init.setAbilityWebVulnerabilityScanAtomicCapabilityInc(18.0D);
        init.setAbilityWebVulnerabilityScanAtomicCapabilityInv(18.0D);
        init.setAbilityWebVulnerabilityScanAtomicCapabilityRemark("18");
        init.setAbilityContainerSecurityVendor("19");
        init.setAbilityContainerSecurityIncNum(19);
        init.setAbilityContainerSecurityInc(19.0D);
        init.setAbilityContainerSecurityInv(19.0D);
        init.setAbilityContainerSecurityRemark("19");
        init.setAbilityContainerSecurityAgentVendor("20");
        init.setAbilityContainerSecurityAgentIncNum(20);
        init.setAbilityContainerSecurityAgentInc(20.0D);
        init.setAbilityContainerSecurityAgentInv(20.0D);
        init.setAbilityContainerSecurityAgentRemark("20");
        init.setAbilityWebTamperPreventionVendor("21");
        init.setAbilityWebTamperPreventionIncNum(21);
        init.setAbilityWebTamperPreventionInc(21.0D);
        init.setAbilityWebTamperPreventionInv(21.0D);
        init.setAbilityWebTamperPreventionRemark("21");
        init.setAbilityWebTamperPreventionAtomicCapabilityVendor("22");
        init.setAbilityWebTamperPreventionAtomicCapabilityIncNum(22);
        init.setAbilityWebTamperPreventionAtomicCapabilityInc(22.0D);
        init.setAbilityWebTamperPreventionAtomicCapabilityInv(22.0D);
        init.setAbilityWebTamperPreventionAtomicCapabilityRemark("22");
        init.setAbilityApiGatewaySecurityVendor("23");
        init.setAbilityApiGatewaySecurityIncNum(23);
        init.setAbilityApiGatewaySecurityInc(23.0D);
        init.setAbilityApiGatewaySecurityInv(23.0D);
        init.setAbilityApiGatewaySecurityRemark("23");
        init.setAbilityNetworkDlpVendor("24");
        init.setAbilityNetworkDlpIncNum(24);
        init.setAbilityNetworkDlpInc(24.0D);
        init.setAbilityNetworkDlpInv(24.0D);
        init.setAbilityNetworkDlpRemark("24");
        init.setAbilityNetworkDomesticDlpVendor("25");
        init.setAbilityNetworkDomesticDlpIncNum(25);
        init.setAbilityNetworkDomesticDlpInc(25.0D);
        init.setAbilityNetworkDomesticDlpInv(25.0D);
        init.setAbilityNetworkDomesticDlpRemark("25");
        init.setAbilityDynamicDataMaskingVendor("26");
        init.setAbilityDynamicDataMaskingIncNum(26);
        init.setAbilityDynamicDataMaskingInc(26.0D);
        init.setAbilityDynamicDataMaskingInv(26.0D);
        init.setAbilityDynamicDataMaskingRemark("26");
        init.setAbilityStaticDataMaskingVendor("27");
        init.setAbilityStaticDataMaskingIncNum(27);
        init.setAbilityStaticDataMaskingInc(27.0D);
        init.setAbilityStaticDataMaskingInv(27.0D);
        init.setAbilityStaticDataMaskingRemark("27");
        init.setAbilityDatabaseAuditVendor("28");
        init.setAbilityDatabaseAuditIncNum(28);
        init.setAbilityDatabaseAuditInc(28.0D);
        init.setAbilityDatabaseAuditInv(28.0D);
        init.setAbilityDatabaseAuditRemark("28");
        init.setAbilitySignalingFirewallVendor("29");
        init.setAbilitySignalingFirewallIncNum(29);
        init.setAbilitySignalingFirewallInc(29.0D);
        init.setAbilitySignalingFirewallInv(29.0D);
        init.setAbilitySignalingFirewallRemark("29");
        init.setAbilityZeroTrustSdpVendor("30");
        init.setAbilityZeroTrustSdpIncNum(30);
        init.setAbilityZeroTrustSdpInc(30.0D);
        init.setAbilityZeroTrustSdpInv(30.0D);
        init.setAbilityZeroTrustSdpRemark("30");
        init.setAbilityZeroTrustDomesticSdpVendor("31");
        init.setAbilityZeroTrustDomesticSdpIncNum(31);
        init.setAbilityZeroTrustDomesticSdpInc(31.0D);
        init.setAbilityZeroTrustDomesticSdpInv(31.0D);
        init.setAbilityZeroTrustDomesticSdpRemark("31");
        init.setAbilityHoneypotVendor("32");
        init.setAbilityHoneypotIncNum(32);
        init.setAbilityHoneypotInc(32.0D);
        init.setAbilityHoneypotInv(32.0D);
        init.setAbilityHoneypotRemark("32");
        init.setAbilityDomesticHoneypotVendor("33");
        init.setAbilityDomesticHoneypotIncNum(33);
        init.setAbilityDomesticHoneypotInc(33.0D);
        init.setAbilityDomesticHoneypotInv(33.0D);
        init.setAbilityDomesticHoneypotRemark("33");
        init.setAbilityMicroSegmentationVendor("34");
        init.setAbilityMicroSegmentationIncNum(34);
        init.setAbilityMicroSegmentationInc(34.0D);
        init.setAbilityMicroSegmentationInv(34.0D);
        init.setAbilityMicroSegmentationRemark("34");
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceVendor("35");
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceIncNum(35);
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceInc(35.0D);
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceInv(35.0D);
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceRemark("35");
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceVendor("36");
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceIncNum(36);
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceInc(36.0D);
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceInv(36.0D);
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceRemark("36");
        init.setAbilityAbnormalTrafficDetectionReportDeviceVendor("37");
        init.setAbilityAbnormalTrafficDetectionReportDeviceIncNum(37);
        init.setAbilityAbnormalTrafficDetectionReportDeviceInc(37.0D);
        init.setAbilityAbnormalTrafficDetectionReportDeviceInv(37.0D);
        init.setAbilityAbnormalTrafficDetectionReportDeviceRemark("37");
        init.setAbilityDataEncryptionDecryptionVendor("38");
        init.setAbilityDataEncryptionDecryptionIncNum(38);
        init.setAbilityDataEncryptionDecryptionInc(38.0D);
        init.setAbilityDataEncryptionDecryptionInv(38.0D);
        init.setAbilityDataEncryptionDecryptionRemark("38");
        init.setAbilityBotnetWormDetectionDisposalDeviceVendor("39");
        init.setAbilityBotnetWormDetectionDisposalDeviceIncNum(39);
        init.setAbilityBotnetWormDetectionDisposalDeviceInc(39.0D);
        init.setAbilityBotnetWormDetectionDisposalDeviceInv(39.0D);
        init.setAbilityBotnetWormDetectionDisposalDeviceRemark("39");
        init.setAbilityBotnetWormDetectionGatewayVendor("40");
        init.setAbilityBotnetWormDetectionGatewayIncNum(40);
        init.setAbilityBotnetWormDetectionGatewayInc(40.0D);
        init.setAbilityBotnetWormDetectionGatewayInv(40.0D);
        init.setAbilityBotnetWormDetectionGatewayRemark("40");
        init.setAbilityBotnetWormDetectionSoftwareUpgradeVendor("41");
        init.setAbilityBotnetWormDetectionSoftwareUpgradeIncNum(41);
        init.setAbilityBotnetWormDetectionSoftwareUpgradeInc(41.0D);
        init.setAbilityBotnetWormDetectionSoftwareUpgradeInv(41.0D);
        init.setAbilityBotnetWormDetectionSoftwareUpgradeRemark("41");
        init.setAbilityIdcispHardwareVendor("42");
        init.setAbilityIdcispHardwareIncNum(42);
        init.setAbilityIdcispHardwareInc(42.0D);
        init.setAbilityIdcispHardwareInv(42.0D);
        init.setAbilityIdcispHardwareRemark("42");
        init.setAbilityIdcispSoftwareVendor("43");
        init.setAbilityIdcispSoftwareInv(43.0D);
        init.setAbilityIdcispSoftwareRemark("43");
        init.setAbilityMobileDpiHardwareVendor("44");
        init.setAbilityMobileDpiHardwareIncNum(44);
        init.setAbilityMobileDpiHardwareInc(44.0D);
        init.setAbilityMobileDpiHardwareInv(44.0D);
        init.setAbilityMobileDpiHardwareRemark("44");
        init.setAbilityMobileDpiSoftwareVendor("45");
        init.setAbilityMobileDpiSoftwareIncNum(45);
        init.setAbilityMobileDpiSoftwareInc(45.0D);
        init.setAbilityMobileDpiSoftwareInv(45.0D);
        init.setAbilityMobileDpiSoftwareRemark("45");
        init.setAbilityFixedNetworkDpiHardwareVendor("46");
        init.setAbilityFixedNetworkDpiHardwareIncNum(46);
        init.setAbilityFixedNetworkDpiHardwareInc(46.0D);
        init.setAbilityFixedNetworkDpiHardwareInv(46.0D);
        init.setAbilityFixedNetworkDpiHardwareRemark("46");
        init.setAbilityFixedNetworkDpiSoftwareVendor("47");
        init.setAbilityFixedNetworkDpiSoftwareInv(47.0D);
        init.setAbilityFixedNetworkDpiSoftwareRemark("47");
        init.setAbilityMobileMalwareDetectionHardwareVendor("48");
        init.setAbilityMobileMalwareDetectionHardwareIncNum(48);
        init.setAbilityMobileMalwareDetectionHardwareInc(48.0D);
        init.setAbilityMobileMalwareDetectionHardwareInv(48.0D);
        init.setAbilityMobileMalwareDetectionHardwareRemark("48");
        init.setAbilityMobileMalwareDetectionSoftwareVendor("49");
        init.setAbilityMobileMalwareDetectionSoftwareIncNum(49);
        init.setAbilityMobileMalwareDetectionSoftwareInc(49.0D);
        init.setAbilityMobileMalwareDetectionSoftwareInv(49.0D);
        init.setAbilityMobileMalwareDetectionSoftwareRemark("49");
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeVendor("50");
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeIncNum(50);
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeInc(50.0D);
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeInv(50.0D);
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeRemark("50");
        init.setSoftwareMobileInternetLogRetentionCentralizedInv(1.0D);
        init.setSoftwareMobileInternetLogRetentionCentralizedRemark("1");
        init.setSoftwareMobileInternetLogRetentionCustomizedInv(2.0D);
        init.setSoftwareMobileInternetLogRetentionCustomizedRemark("2");
        init.setSoftwareBotnetWormDetectionPlatformTotalPriceInv(3.0D);
        init.setSoftwareBotnetWormDetectionPlatformTotalPriceRemark("3");
        init.setSoftwareIdcispPlatformTotalPriceInv(4.0D);
        init.setSoftwareIdcispPlatformTotalPriceRemark("4");
        init.setSoftwareMobileDpiPlatformTotalPriceInv(5.0D);
        init.setSoftwareMobileDpiPlatformTotalPriceRemark("5");
        init.setSoftwareFixedNetworkDpiPlatformTotalPriceInv(6.0D);
        init.setSoftwareFixedNetworkDpiPlatformTotalPriceRemark("6");
        init.setSoftwareMobileMalwareDetectionPlatformTotalPriceInv(7.0D);
        init.setSoftwareMobileMalwareDetectionPlatformTotalPriceRemark("7");
        init.setSoftwareAssetManagementInv(8.0D);
        init.setSoftwareAssetManagementRemark("8");
        init.setSoftwareBaselineManagementInv(9.0D);
        init.setSoftwareBaselineManagementRemark("9");
        init.setSoftwareVulnerabilityManagementInv(10.0D);
        init.setSoftwareVulnerabilityManagementRemark("10");
        init.setSoftwareInternetExposureManagementInv(11.0D);
        init.setSoftwareInternetExposureManagementRemark("11");
        init.setSoftwareInternalNetworkAssetMappingInv(12.0D);
        init.setSoftwareInternalNetworkAssetMappingRemark("12");
        init.setSoftwareAaaaInv(13.0D);
        init.setSoftwareAaaaRemark("13");
        init.setSoftwareAppReleaseDetectionInv(15.0D);
        init.setSoftwareAppReleaseDetectionRemark("15");
        init.setSoftwareDataAssetManagementInv(16.0D);
        init.setSoftwareDataAssetManagementRemark("16");
        init.setSoftwarePasswordServiceManagementInv(17.0D);
        init.setSoftwarePasswordServiceManagementRemark("17");
        init.setSoftwareThreatIntelligenceInv(18.0D);
        init.setSoftwareThreatIntelligenceRemark("18");
        init.setSoftwareNetworkSecuritySituationalAwarenessInv(19.0D);
        init.setSoftwareNetworkSecuritySituationalAwarenessRemark("19");
        init.setSoftwareDataSecuritySituationalAwarenessInv(20.0D);
        init.setSoftwareDataSecuritySituationalAwarenessRemark("20");
        init.setSoftwareWebsiteFilingMonitoringInv(21.0D);
        init.setSoftwareWebsiteFilingMonitoringRemark("21");
        init.setSoftwareHarmfulInformationMonitoringInv(22.0D);
        init.setSoftwareHarmfulInformationMonitoringRemark("22");
        init.setSoftwareAntiFraudManagementInv(23.0D);
        init.setSoftwareAntiFraudManagementRemark("23");
        init.setSoftwareContentSecurityReviewPublishControlInv(24.0D);
        init.setSoftwareContentSecurityReviewPublishControlRemark("24");
        init.setSoftwareOneClickDisposalInv(25.0D);
        init.setSoftwareOneClickDisposalRemark("25");
        init.setSoftwareSoarInv(26.0D);
        init.setSoftwareSoarRemark("26");
        init.setSoftwareNetworkAttackTracingInv(27.0D);
        init.setSoftwareNetworkAttackTracingRemark("27");
        init.setSoftwareSecurityCapabilityCenterInv(28.0D);
        init.setSoftwareSecurityCapabilityCenterRemark("28");
        init.setSoftwareSecurityDataCenterInv(29.0D);
        init.setSoftwareSecurityDataCenterRemark("29");
        init.setSoftwareAttackDefenseDrillInv(30.0D);
        init.setSoftwareAttackDefenseDrillRemark("30");
        init.setSoftwarePatchManagementCoreNativeInv(31.0D);
        init.setSoftwarePatchManagementCoreNativeRemark("31");
        init.setSoftwarePatchManagementCoreExternalInv(32.0D);
        init.setSoftwarePatchManagementCoreExternalRemark("32");
        init.setSoftwareVulnerabilityManagementCoreNativeInv(33.0D);
        init.setSoftwareVulnerabilityManagementCoreNativeRemark("33");
        init.setSoftwareAssetManagementCoreNativeInv(34.0D);
        init.setSoftwareAssetManagementCoreNativeRemark("34");
        init.setSoftwareSituationalAwarenessCoreNativeInv(35.0D);
        init.setSoftwareSituationalAwarenessCoreNativeRemark("35");
        init.setSoftwareUebaCoreInv(36.0D);
        init.setSoftwareUebaCoreRemark("36");

        init.setAbilityFirewallHardwareVar(1.0D);
        init.setAbilityFirewallAtomicCapabilityVar(2.0D);
        init.setAbilityIpsHardwareVar(3.0D);
        init.setAbilityIpsAtomicCapabilityVar(4.0D);
        init.setAbilityWafHardwareVar(5.0D);
        init.setAbilityWafDomesticHardwareVar(6.0D);
        init.setAbilityWafAtomicCapabilityVar(7.0D);
        init.setAbilityWebDynamicDefenseHardwareVar(8.0D);
        init.setAbilityWebDynamicDefenseSoftwareVar(9.0D);
        init.setAbilityTrafficScrubbingVar(10.0D);
        init.setAbilityFullTrafficAnalysisVar(11.0D);
        init.setAbilityBastionHostVar(12.0D);
        init.setAbilityLogAuditVar(13.0D);
        init.setAbilityEdrVar(14.0D);
        init.setAbilityHostVulnerabilityScanHardwareVar(15.0D);
        init.setAbilityHostVulnerabilityScanAtomicCapabilityVar(16.0D);
        init.setAbilityWebVulnerabilityScanHardwareVar(17.0D);
        init.setAbilityWebVulnerabilityScanAtomicCapabilityVar(18.0D);
        init.setAbilityContainerSecurityVar(19.0D);
        init.setAbilityContainerSecurityAgentVar(20.0D);
        init.setAbilityWebTamperPreventionVar(21.0D);
        init.setAbilityWebTamperPreventionAtomicCapabilityVar(22.0D);
        init.setAbilityApiGatewaySecurityVar(23.0D);
        init.setAbilityNetworkDlpVar(24.0D);
        init.setAbilityNetworkDomesticDlpVar(25.0D);
        init.setAbilityDynamicDataMaskingVar(26.0D);
        init.setAbilityStaticDataMaskingVar(27.0D);
        init.setAbilityDatabaseAuditVar(28.0D);
        init.setAbilitySignalingFirewallVar(29.0D);
        init.setAbilityZeroTrustSdpVar(30.0D);
        init.setAbilityZeroTrustDomesticSdpVar(31.0D);
        init.setAbilityHoneypotVar(32.0D);
        init.setAbilityDomesticHoneypotVar(33.0D);
        init.setAbilityMicroSegmentationVar(34.0D);
        init.setAbilityAbnormalTrafficDetectionForwardingDeviceVar(35.0D);
        init.setAbilityAbnormalTrafficDetectionCollectionDeviceVar(36.0D);
        init.setAbilityAbnormalTrafficDetectionReportDeviceVar(37.0D);
        init.setAbilityDataEncryptionDecryptionVar(38.0D);
        init.setAbilityBotnetWormDetectionDisposalDeviceVar(39.0D);
        init.setAbilityBotnetWormDetectionGatewayVar(40.0D);
        init.setAbilityBotnetWormDetectionSoftwareUpgradeVar(41.0D);
        init.setAbilityIdcispHardwareVar(42.0D);
        init.setAbilityIdcispSoftwareVar(43.0D);
        init.setAbilityMobileDpiHardwareVar(44.0D);
        init.setAbilityMobileDpiSoftwareVar(45.0D);
        init.setAbilityFixedNetworkDpiHardwareVar(46.0D);
        init.setAbilityFixedNetworkDpiSoftwareVar(47.0D);
        init.setAbilityMobileMalwareDetectionHardwareVar(48.0D);
        init.setAbilityMobileMalwareDetectionSoftwareVar(49.0D);
        init.setAbilityMobileMalwareDetectionSoftwareUpgradeVar(50.0D);
        init.setSoftwareMobileInternetLogRetentionCentralizedVar(1.0D);
        init.setSoftwareMobileInternetLogRetentionCustomizedVar(2.0D);
        init.setSoftwareBotnetWormDetectionPlatformTotalPriceVar(3.0D);
        init.setSoftwareIdcispPlatformTotalPriceVar(4.0D);
        init.setSoftwareMobileDpiPlatformTotalPriceVar(5.0D);
        init.setSoftwareFixedNetworkDpiPlatformTotalPriceVar(6.0D);
        init.setSoftwareMobileMalwareDetectionPlatformTotalPriceVar(7.0D);
        init.setSoftwareAssetManagementVar(8.0D);
        init.setSoftwareBaselineManagementVar(9.0D);
        init.setSoftwareVulnerabilityManagementVar(10.0D);
        init.setSoftwareInternetExposureManagementVar(11.0D);
        init.setSoftwareInternalNetworkAssetMappingVar(12.0D);
        init.setSoftwareAaaaVar(13.0D);
        init.setSoftwareAppReleaseDetectionVar(14.0D);
        init.setSoftwareDataAssetManagementVar(15.0D);
        init.setSoftwarePasswordServiceManagementVar(16.0D);
        init.setSoftwareThreatIntelligenceVar(17.0D);
        init.setSoftwareNetworkSecuritySituationalAwarenessVar(18.0D);
        init.setSoftwareDataSecuritySituationalAwarenessVar(19.0D);
        init.setSoftwareWebsiteFilingMonitoringVar(20.0D);
        init.setSoftwareHarmfulInformationMonitoringVar(21.0D);
        init.setSoftwareAntiFraudManagementVar(22.0D);
        init.setSoftwareContentSecurityReviewPublishControlVar(23.0D);
        init.setSoftwareOneClickDisposalVar(24.0D);
        init.setSoftwareSoarVar(25.0D);
        init.setSoftwareNetworkAttackTracingVar(26.0D);
        init.setSoftwareSecurityCapabilityCenterVar(27.0D);
        init.setSoftwareSecurityDataCenterVar(28.0D);
        init.setSoftwareAttackDefenseDrillVar(29.0D);
        init.setSoftwarePatchManagementCoreNativeVar(30.0D);
        init.setSoftwarePatchManagementCoreExternalVar(31.0D);
        init.setSoftwareVulnerabilityManagementCoreNativeVar(32.0D);
        init.setSoftwareAssetManagementCoreNativeVar(33.0D);
        init.setSoftwareSituationalAwarenessCoreNativeVar(34.0D);
        init.setSoftwareUebaCoreVar(35.0D);
        init.setOrgCode(ORG_CODE);

        benefitInternalConstructionService.add(init);
        logger.info("【Test】 benefit_internal_construction 表新增结果：{}", JSON.toJSONString(init));

        // 查
        init.setCreateTime(null);
        init.setUpdateTime(null);
        List<BenefitInternalConstructionDO> selectRes = benefitInternalConstructionService.queryList(init);
        logger.info("【Test】 benefit_internal_construction 表查询结果：{}", JSON.toJSONString(selectRes));
    }

    @Test
    public void benefitInternalConstructionExtraDBTest(){
        // turnover
        benefitInternalConstructionService.turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.HARD);
    }

    /**
     * copy from BenefitEngineServiceTest
     */
    @Test
    public void benefitExternalEmpowermentDBTest() {
        // 增
        BenefitExternalEmpowermentDO init = new BenefitExternalEmpowermentDO();
        init.setItemNo(1L);
        init.setProvincialCompany("填表单位");
        init.setAutoSafetyTotalInvestment(1.0D);
        init.setSafetyTotalIncome(2.0D);
        init.setOrgCode(ORG_CODE);

        benefitExternalEmpowermentService.add(init);
        logger.info("【Test】 benefit_external_empowerment 表新增结果：{}", JSON.toJSONString(init));

        // 查
        // init.setId(null);
        init.setCreateTime(null);
        init.setUpdateTime(null);
        List<BenefitExternalEmpowermentDO> selectRes = benefitExternalEmpowermentService.queryList(init);
        logger.info("【Test】 benefit_external_empowerment 表查询结果：{}", JSON.toJSONString(selectRes));

        // turnover
        benefitExternalEmpowermentService.turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.HARD);
    }

    @Test
    public void allProcessDataPageTests(){
        processDataPageComprehensiveProtectionTest();
        processDataPageBenefitInternalConstructionTest();
        processDataPageBenefitExternalEmpowermentTest();
    }

    @Test
    public void processDataPageComprehensiveProtectionTest(){
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setOrgCode("02250011");
        queryDO.setComprehensiveScenarioType("APT");
        queryDO.setEvaluationItem("攻击");
        duplicateCollectDubboService.processDataPage(queryDO);
    }

    @Test
    public void processDataPageBenefitInternalConstructionTest(){
        BenefitInternalConstructionDO queryDO = new BenefitInternalConstructionDO();
        queryDO.setOrgCode("02260062");
        queryDO.setProjectCode("24");
        queryDO.setProjectName("中国");
        queryDO.setProjectType("合规考核");
        queryDO.setSize(5);
        duplicateCollectDubboService.processDataPage(queryDO);
    }

    @Test
    public void processDataPageBenefitExternalEmpowermentTest(){
        BenefitExternalEmpowermentDO queryDO = new BenefitExternalEmpowermentDO();
        queryDO.setOrgCode("02250011");
        queryDO.setSize(1);
        duplicateCollectDubboService.processDataPage(queryDO);
    }
}
