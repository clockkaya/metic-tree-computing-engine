package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.shaded.com.google.common.collect.ImmutableList;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.api.ledger.bean.structure.ThresholdPair;
import com.sama.ledger.SamaLedgerApplication;
import com.sama.ledger.metric.calculators.*;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.MetricMockDataUtils;
import com.sama.ledger.utils.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 1 效益算子
 * @author: huxh
 * @description:
 * @datetime: 2025/9/5 15:29
 */
@SpringBootTest(classes = SamaLedgerApplication.class)
public class BenefitProxyCalculatorTest {

    private static final Logger logger = LogManager.getLogger(BenefitProxyCalculatorTest.class);

    @Resource
    AbilityFirewallHardwareCalculator abilityFirewallHardwareCalculator;

    @Resource
    AbilityFirewallAtomicCapabilityCalculator abilityFirewallAtomicCapabilityCalculator;

    @Resource
    AbilityIpsHardwareCalculator abilityIpsHardwareCalculator;

    @Resource
    AbilityIpsAtomicCapabilityCalculator abilityIpsAtomicCapabilityCalculator;

    @Resource
    AbilityWafHardwareCalculator abilityWafHardwareCalculator;

    @Resource
    AbilityWafDomesticHardwareCalculator abilityWafDomesticHardwareCalculator;

    @Resource
    AbilityWafAtomicCapabilityCalculator abilityWafAtomicCapabilityCalculator;

    @Resource
    AbilityWebDynamicDefenseHardwareCalculator abilityWebDynamicDefenseHardwareCalculator;

    @Resource
    AbilityWebDynamicDefenseSoftwareCalculator abilityWebDynamicDefenseSoftwareCalculator;

    @Resource
    AbilityTrafficScrubbingCalculator abilityTrafficScrubbingCalculator;

    @Resource
    AbilityFullTrafficAnalysisCalculator abilityFullTrafficAnalysisCalculator;

    @Resource
    AbilityBastionHostCalculator abilityBastionHostCalculator;

    @Resource
    AbilityLogAuditCalculator abilityLogAuditCalculator;

    @Resource
    AbilityEdrCalculator abilityEdrCalculator;

    @Resource
    AbilityHostVulnerabilityScanHardwareCalculator abilityHostVulnerabilityScanHardwareCalculator;

    @Resource
    AbilityHostVulnerabilityScanAtomicCapabilityCalculator abilityHostVulnerabilityScanAtomicCapabilityCalculator;

    @Resource
    AbilityWebVulnerabilityScanHardwareCalculator abilityWebVulnerabilityScanHardwareCalculator;

    @Resource
    AbilityWebVulnerabilityScanAtomicCapabilityCalculator abilityWebVulnerabilityScanAtomicCapabilityCalculator;

    @Resource
    AbilityContainerSecurityCalculator abilityContainerSecurityCalculator;

    @Resource
    AbilityContainerSecurityAgentCalculator abilityContainerSecurityAgentCalculator;

    @Resource
    AbilityWebTamperPreventionCalculator abilityWebTamperPreventionCalculator;

    @Resource
    AbilityWebTamperPreventionAtomicCapabilityCalculator abilityWebTamperPreventionAtomicCapabilityCalculator;

    @Resource
    AbilityApiGatewaySecurityCalculator abilityApiGatewaySecurityCalculator;

    @Resource
    AbilityNetworkDlpCalculator abilityNetworkDlpCalculator;

    @Resource
    AbilityNetworkDomesticDlpCalculator abilityNetworkDomesticDlpCalculator;

    @Resource
    AbilityDynamicDataMaskingCalculator abilityDynamicDataMaskingCalculator;

    @Resource
    AbilityStaticDataMaskingCalculator abilityStaticDataMaskingCalculator;

    @Resource
    AbilityDatabaseAuditCalculator abilityDatabaseAuditCalculator;

    @Resource
    AbilitySignalingFirewallCalculator abilitySignalingFirewallCalculator;

    @Resource
    AbilityZeroTrustSdpCalculator abilityZeroTrustSdpCalculator;

    @Resource
    AbilityZeroTrustDomesticSdpCalculator abilityZeroTrustDomesticSdpCalculator;

    @Resource
    AbilityHoneypotCalculator abilityHoneypotCalculator;

    @Resource
    AbilityDomesticHoneypotCalculator abilityDomesticHoneypotCalculator;

    @Resource
    AbilityMicroSegmentationCalculator abilityMicroSegmentationCalculator;

    @Resource
    AbilityAbnormalTrafficDetectionForwardingDeviceCalculator abilityAbnormalTrafficDetectionForwardingDeviceCalculator;

    @Resource
    AbilityAbnormalTrafficDetectionCollectionDeviceCalculator abilityAbnormalTrafficDetectionCollectionDeviceCalculator;

    @Resource
    AbilityAbnormalTrafficDetectionReportDeviceCalculator abilityAbnormalTrafficDetectionReportDeviceCalculator;

    @Resource
    AbilityDataEncryptionDecryptionCalculator abilityDataEncryptionDecryptionCalculator;

    @Resource
    AbilityBotnetWormDetectionDisposalDeviceCalculator abilityBotnetWormDetectionDisposalDeviceCalculator;

    @Resource
    AbilityBotnetWormDetectionGatewayCalculator abilityBotnetWormDetectionGatewayCalculator;

    @Resource
    AbilityBotnetWormDetectionSoftwareUpgradeCalculator abilityBotnetWormDetectionSoftwareUpgradeCalculator;

    @Resource
    AbilityIdcispHardwareCalculator abilityIdcispHardwareCalculator;

    @Resource
    AbilityIdcispSoftwareCalculator abilityIdcispSoftwareCalculator;

    @Resource
    AbilityMobileDpiHardwareCalculator abilityMobileDpiHardwareCalculator;

    @Resource
    AbilityMobileDpiSoftwareCalculator abilityMobileDpiSoftwareCalculator;

    @Resource
    AbilityFixedNetworkDpiHardwareCalculator abilityFixedNetworkDpiHardwareCalculator;

    @Resource
    AbilityFixedNetworkDpiSoftwareCalculator abilityFixedNetworkDpiSoftwareCalculator;

    @Resource
    AbilityMobileMalwareDetectionHardwareCalculator abilityMobileMalwareDetectionHardwareCalculator;

    @Resource
    AbilityMobileMalwareDetectionSoftwareCalculator abilityMobileMalwareDetectionSoftwareCalculator;

    @Resource
    AbilityMobileMalwareDetectionSoftwareUpgradeCalculator abilityMobileMalwareDetectionSoftwareUpgradeCalculator;

    @Resource
    SoftwareMobileInternetLogRetentionCentralizedCalculator softwareMobileInternetLogRetentionCentralizedCalculator;

    @Resource
    SoftwareMobileInternetLogRetentionCustomized softwareMobileInternetLogRetentionCustomized;

    @Resource
    SoftwareBotnetWormDetectionPlatformTotalPriceCalculator softwareBotnetWormDetectionPlatformTotalPriceCalculator;

    @Resource
    SoftwareIdcispPlatformTotalPriceCalculator softwareIdcispPlatformTotalPriceCalculator;

    @Resource
    SoftwareMobileDpiPlatformTotalPriceCalculator softwareMobileDpiPlatformTotalPriceCalculator;

    @Resource
    SoftwareFixedNetworkDpiPlatformTotalPriceCalculator softwareFixedNetworkDpiPlatformTotalPriceCalculator;

    @Resource
    SoftwareMobileMalwareDetectionPlatformTotalPriceCalculator softwareMobileMalwareDetectionPlatformTotalPriceCalculator;

    @Resource
    SoftwareAssetManagementCalculator softwareAssetManagementCalculator;

    @Resource
    SoftwareBaselineManagementCalculator softwareBaselineManagementCalculator;

    @Resource
    SoftwareVulnerabilityManagementCalculator softwareVulnerabilityManagementCalculator;

    @Resource
    SoftwareInternetExposureManagementCalculator softwareInternetExposureManagementCalculator;

    @Resource
    SoftwareInternalNetworkAssetMappingCalculator softwareInternalNetworkAssetMappingCalculator;

    @Resource
    SoftwareAaaaCalculator softwareAaaaCalculator;

    @Resource
    SoftwareAppReleaseDetectionCalculator softwareAppReleaseDetectionCalculator;

    @Resource
    SoftwareDataAssetManagementCalculator softwareDataAssetManagementCalculator;

    @Resource
    SoftwarePasswordServiceManagementCalculator softwarePasswordServiceManagementCalculator;

    @Resource
    SoftwareThreatIntelligenceCalculator softwareThreatIntelligenceCalculator;

    @Resource
    SoftwareNetworkSecuritySituationalAwarenessCalculator softwareNetworkSecuritySituationalAwarenessCalculator;

    @Resource
    SoftwareDataSecuritySituationalAwarenessCalculator softwareDataSecuritySituationalAwarenessCalculator;

    @Resource
    SoftwareWebsiteFilingMonitoringCalculator softwareWebsiteFilingMonitoringCalculator;

    @Resource
    SoftwareHarmfulInformationMonitoringCalculator softwareHarmfulInformationMonitoringCalculator;

    @Resource
    SoftwareAntiFraudManagementCalculator softwareAntiFraudManagementCalculator;

    @Resource
    SoftwareContentSecurityReviewPublishControlCalculator softwareContentSecurityReviewPublishControlCalculator;

    @Resource
    SoftwareOneClickDisposalCalculator softwareOneClickDisposalCalculator;

    @Resource
    SoftwareSoarCalculator softwareSoarCalculator;

    @Resource
    SoftwareNetworkAttackTracingCalculator softwareNetworkAttackTracingCalculator;

    @Resource
    SoftwareSecurityCapabilityCenterCalculator softwareSecurityCapabilityCenterCalculator;

    @Resource
    SoftwareSecurityDataCenterCalculator softwareSecurityDataCenterCalculator;

    @Resource
    SoftwareAttackDefenseDrillCalculator softwareAttackDefenseDrillCalculator;

    @Resource
    SoftwarePatchManagementCoreNativeCalculator softwarePatchManagementCoreNativeCalculator;

    @Resource
    SoftwarePatchManagementCoreExternalCalculator softwarePatchManagementCoreExternalCalculator;

    @Resource
    SoftwareVulnerabilityManagementCoreNativeCalculator softwareVulnerabilityManagementCoreNativeCalculator;

    @Resource
    SoftwareAssetManagementCoreNativeCalculator softwareAssetManagementCoreNativeCalculator;

    @Resource
    SoftwareSituationalAwarenessCoreNativeCalculator softwareSituationalAwarenessCoreNativeCalculator;

    @Resource
    SoftwareUebaCoreCalculator softwareUebaCoreCalculator;

    @Resource
    ProvinceCompanyIncomeToInvestmentRatioCalculator provinceCompanyIncomeToInvestmentRatioCalculator;

    /**
     * 打分规则
     */
    @Test
    public void allCalculatorTest() {
        abilityFirewallCalculatorTest();
        abilityFirewallAtomicCapabilityCalculatorTest();
        abilityIpsHardwareCalculatorTest();
        abilityIpsAtomicCapabilityCalculatorTest();
        abilityWafHardwareCalculatorTest();
        abilityWafDomesticHardwareCalculatorTest();
        abilityWafAtomicCapabilityCalculatorTest();
        abilityWebDynamicDefenseHardwareCalculatorTest();
        abilityWebDynamicDefenseSoftwareCalculatorTest();
        abilityTrafficScrubbingCalculatorTest();
        abilityFullTrafficAnalysisCalculatorTest();
        abilityBastionHostCalculatorTest();
        abilityLogAuditCalculatorTest();
        abilityEdrCalculatorTest();
        abilityHostVulnerabilityScanHardwareCalculatorTest();
        abilityHostVulnerabilityScanAtomicCapabilityCalculatorTest();
        abilityWebVulnerabilityScanHardwareCalculatorTest();
        abilityWebVulnerabilityScanAtomicCapabilityCalculatorTest();
        abilityContainerSecurityCalculatorTest();
        abilityContainerSecurityAgentCalculatorTest();
        abilityWebTamperPreventionCalculatorTest();
        abilityWebTamperPreventionAtomicCapabilityCalculatorTest();
        abilityApiGatewaySecurityCalculatorTest();
        abilityNetworkDlpCalculatorTest();
        abilityNetworkDomesticDlpCalculatorTest();
        abilityDynamicDataMaskingCalculatorTest();
        abilityStaticDataMaskingCalculatorTest();
        abilityDatabaseAuditCalculatorTest();
        abilitySignalingFirewallCalculatorTest();
        abilityZeroTrustSdpCalculatorTest();
        abilityZeroTrustDomesticSdpCalculatorTest();
        abilityHoneypotCalculatorTest();
        abilityDomesticHoneypotCalculatorTest();
        abilityMicroSegmentationCalculatorTest();
        abilityAbnormalTrafficDetectionForwardingDeviceCalculatorTest();
        abilityAbnormalTrafficDetectionCollectionDeviceCalculatorTest();
        abilityAbnormalTrafficDetectionReportDeviceCalculatorTest();
        abilityDataEncryptionDecryptionCalculatorTest();
        abilityBotnetWormDetectionDisposalDeviceCalculatorTest();
        abilityBotnetWormDetectionGatewayCalculatorTest();
        abilityBotnetWormDetectionSoftwareUpgradeCalculatorTest();
        abilityIdcispHardwareCalculatorTest();
        abilityIdcispSoftwareCalculatorTest();
        abilityMobileDpiHardwareCalculatorTest();
        abilityMobileDpiSoftwareCalculatorTest();
        abilityFixedNetworkDpiHardwareCalculatorTest();
        abilityFixedNetworkDpiSoftwareCalculatorTest();
        abilityMobileMalwareDetectionHardwareCalculatorTest();
        abilityMobileMalwareDetectionSoftwareCalculatorTest();
        abilityMobileMalwareDetectionSoftwareUpgradeCalculatorTest();
        softwareMobileInternetLogRetentionCentralizedCalculatorTest();
        softwareMobileInternetLogRetentionCustomizedTest();
        softwareBotnetWormDetectionPlatformTotalPriceCalculatorTest();
        softwareIdcispPlatformTotalPriceCalculatorTest();
        softwareMobileDpiPlatformTotalPriceCalculatorTest();
        softwareFixedNetworkDpiPlatformTotalPriceCalculatorTest();
        softwareMobileMalwareDetectionPlatformTotalPriceCalculatorTest();
        softwareAssetManagementCalculatorTest();
        softwareBaselineManagementCalculatorTest();
        softwareVulnerabilityManagementCalculatorTest();
        softwareInternetExposureManagementCalculatorTest();
        softwareInternalNetworkAssetMappingCalculatorTest();
        softwareAaaaCalculatorTest();
        softwareAppReleaseDetectionCalculatorTest();
        softwareDataAssetManagementCalculatorTest();
        softwarePasswordServiceManagementCalculatorTest();
        softwareThreatIntelligenceCalculatorTest();
        softwareNetworkSecuritySituationalAwarenessCalculatorTest();
        softwareDataSecuritySituationalAwarenessCalculatorTest();
        softwareWebsiteFilingMonitoringCalculatorTest();
        softwareHarmfulInformationMonitoringCalculatorTest();
        softwareAntiFraudManagementCalculatorTest();
        softwareContentSecurityReviewPublishControlCalculatorTest();
        softwareOneClickDisposalCalculatorTest();
        softwareSoarCalculatorTest();
        softwareNetworkAttackTracingCalculatorTest();
        softwareSecurityCapabilityCenterCalculatorTest();
        softwareSecurityDataCenterCalculatorTest();
        softwareAttackDefenseDrillCalculatorTest();
        softwarePatchManagementCoreNativeCalculatorTest();
        softwarePatchManagementCoreExternalCalculatorTest();
        softwareVulnerabilityManagementCoreNativeCalculatorTest();
        softwareAssetManagementCoreNativeCalculatorTest();
        softwareSituationalAwarenessCoreNativeCalculatorTest();
        softwareUebaCoreCalculatorTest();
        provinceCompanyIncomeToInvestmentRatioCalculatorTest();
    }

    private void unitAssertEqualsUsingPreparedData(BaseCalculator<BenefitPreparedDataBO> baseCalculator,
                                                   List<BenefitInternalConstructionDO> internalTable,
                                                   BenefitExternalEmpowermentDO externalTable,
                                                   String expectedAverageVar,
                                                   String expectedScore){
        BenefitPreparedDataBO customData = new BenefitPreparedDataBO();
        customData.setExternalData(externalTable);
        customData.setInternalData(internalTable);
        customData.setThresholdMap(MetricMockDataUtils.mockBenefitThresholdMap());
        customData.setOrgCode("02260062");
        customData.setMetricType(MetricTypeEnum.BENEFIT_RELEASE.getType());

        // 计算
        MetricResultNode resultNode = baseCalculator.processAccordingToProcedure(customData);

        // 判断
        if (expectedAverageVar == null || expectedScore == null) {
            assertNull(resultNode.getAssessedScore());
            return;
        }

        // b.评估值
        BigDecimal expectedAssessedValue = new BigDecimal(expectedAverageVar);
        BigDecimal actualAssessedValue = NumberUtils.safeConvertToBigDecimal(resultNode.extractAssessedValue());
        assertEquals(NumberUtils.formatFlexibleConditions(expectedAssessedValue, 2), NumberUtils.formatFlexibleConditions(actualAssessedValue, 2));

        // c.评估得分
        BigDecimal expectedAssessedScore = new BigDecimal(expectedScore);
        BigDecimal actualAssessedScore = NumberUtils.safeConvertToBigDecimal(resultNode.getAssessedScore());
        assertEquals(NumberUtils.formatFlexibleConditions(expectedAssessedScore, 2), NumberUtils.formatFlexibleConditions(actualAssessedScore, 2));
    }

    /**
     * 生成统一的测试数据集
     *
     * @param incSetter BenefitInternalConstructionDO::setInc，原 first
     * @param invSetter BenefitInternalConstructionDO::setInv，原 second
     * @return  测试数据集
     */
    private static List<BenefitInternalConstructionDO> generateAbilityCalculatorData(
        BiConsumer<BenefitInternalConstructionDO, Double> incSetter, BiConsumer<BenefitInternalConstructionDO, Double> invSetter){
        BenefitInternalConstructionDO rowA = new BenefitInternalConstructionDO();
        rowA.setNonSecurityDeviceFee(0d);
        rowA.setSecurityDeviceFee(981132.08);
        rowA.setTotalOtherFee(63678.6907924528);
        mockUpdateAutoSecurityDeviceOtherFee(rowA);
        incSetter.accept(rowA, 5695.8788);
        invSetter.accept(rowA, 7846465d);

        BenefitInternalConstructionDO rowB = new BenefitInternalConstructionDO();
        rowB.setNonSecurityDeviceFee(4564.88);
        rowB.setSecurityDeviceFee(9984.11);
        rowB.setTotalOtherFee(55.55555);
        mockUpdateAutoSecurityDeviceOtherFee(rowB);
        incSetter.accept(rowB, 333.12);
        invSetter.accept(rowB, 5555.19);

        BenefitInternalConstructionDO rowC = new BenefitInternalConstructionDO();

        return ImmutableList.of(rowA, rowB, rowC);
    }

    /**
     * 生成统一的测试数据集
     *
     * @param invSetter BenefitInternalConstructionDO::setInv，原 second
     * @return  测试数据集
     */
    private static List<BenefitInternalConstructionDO> generateSoftwareCalculatorData(BiConsumer<BenefitInternalConstructionDO, Double> invSetter){
        BenefitInternalConstructionDO rowA = new BenefitInternalConstructionDO();
        rowA.setNonSecurityDeviceFee(0d);
        rowA.setSecurityDeviceFee(981132.08);
        rowA.setTotalOtherFee(63678.6907924528);
        mockUpdateAutoSecurityDeviceOtherFee(rowA);
        invSetter.accept(rowA, 7846465d);

        BenefitInternalConstructionDO rowB = new BenefitInternalConstructionDO();
        rowB.setNonSecurityDeviceFee(4564.88);
        rowB.setSecurityDeviceFee(9984.11);
        rowB.setTotalOtherFee(55.55555);
        mockUpdateAutoSecurityDeviceOtherFee(rowB);
        invSetter.accept(rowB, 5555.19);

        BenefitInternalConstructionDO rowC = new BenefitInternalConstructionDO();

        return ImmutableList.of(rowA, rowB, rowC);
    }

    /**
     * 1 防火墙-硬件
     */
    @Test
    public void abilityFirewallCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityFirewallHardwareInc,
            BenefitInternalConstructionDO::setAbilityFirewallHardwareInv
        );

        unitAssertEqualsUsingPreparedData(abilityFirewallHardwareCalculator, internalTable, null, "741.8587", "83.5539");
    }

    /**
     * 2 防火墙-原子能力
     */
    @Test
    public void abilityFirewallAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityFirewallAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityFirewallAtomicCapabilityInv
        );

        unitAssertEqualsUsingPreparedData(abilityFirewallAtomicCapabilityCalculator, internalTable, null, "741.8587", "9.4965");
    }

    /**
     * 3 IPS-硬件
     */
    @Test
    public void abilityIpsHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityIpsHardwareInc,
            BenefitInternalConstructionDO::setAbilityIpsHardwareInv
        );

        unitAssertEqualsUsingPreparedData(abilityIpsHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 4 IPS-原子能力
     */
    @Test
    public void abilityIpsAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityIpsAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityIpsAtomicCapabilityInv
        );

        unitAssertEqualsUsingPreparedData(abilityIpsAtomicCapabilityCalculator, internalTable, null, "741.8587", "79.748");
    }

    /**
     * 5 Web防御（WAF）-硬件
     */
    @Test
    public void abilityWafHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWafHardwareInc,
            BenefitInternalConstructionDO::setAbilityWafHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityWafHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 6 Web防御（WAF）-硬件-国产化设备
     */
    @Test
    public void abilityWafDomesticHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWafDomesticHardwareInc,
            BenefitInternalConstructionDO::setAbilityWafDomesticHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityWafDomesticHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 7 Web防御（WAF）-原子能力
     */
    @Test
    public void abilityWafAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWafAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityWafAtomicCapabilityInv
        );
        unitAssertEqualsUsingPreparedData(abilityWafAtomicCapabilityCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 8 Web防御（动态防护）-硬件
     */
    @Test
    public void abilityWebDynamicDefenseHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebDynamicDefenseHardwareInc,
            BenefitInternalConstructionDO::setAbilityWebDynamicDefenseHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebDynamicDefenseHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 9 Web防御（动态防护）-软件
     */
    @Test
    public void abilityWebDynamicDefenseSoftwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebDynamicDefenseSoftwareInc,
            BenefitInternalConstructionDO::setAbilityWebDynamicDefenseSoftwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebDynamicDefenseSoftwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 10 流量清洗（抗DDoS）
     */
    @Test
    public void abilityTrafficScrubbingCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityTrafficScrubbingInc,
            BenefitInternalConstructionDO::setAbilityTrafficScrubbingInv
        );
        unitAssertEqualsUsingPreparedData(abilityTrafficScrubbingCalculator, internalTable, null, "741.8587", "94.3606");
    }

    /**
     * 11 流量分析（全流量分析）
     */
    @Test
    public void abilityFullTrafficAnalysisCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityFullTrafficAnalysisInc,
            BenefitInternalConstructionDO::setAbilityFullTrafficAnalysisInv
        );
        unitAssertEqualsUsingPreparedData(abilityFullTrafficAnalysisCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 12 堡垒机
     */
    @Test
    public void abilityBastionHostCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityBastionHostInc,
            BenefitInternalConstructionDO::setAbilityBastionHostInv
        );
        unitAssertEqualsUsingPreparedData(abilityBastionHostCalculator, internalTable, null, "741.8587", "71.8222");
    }

    /**
     * 13 日志审计
     */
    @Test
    public void abilityLogAuditCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityLogAuditInc,
            BenefitInternalConstructionDO::setAbilityLogAuditInv
        );
        unitAssertEqualsUsingPreparedData(abilityLogAuditCalculator, internalTable, null, "741.8587", "0");
    }

    /**
     * 14 EDR（含防病毒）
     */
    @Test
    public void abilityEdrCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityEdrInc,
            BenefitInternalConstructionDO::setAbilityEdrInv
        );
        unitAssertEqualsUsingPreparedData(abilityEdrCalculator, internalTable, null, "741.8587", "61.9868");
    }

    /**
     * 15 漏洞扫描（主机）-硬件
     */
    @Test
    public void abilityHostVulnerabilityScanHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanHardwareInc,
            BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityHostVulnerabilityScanHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 16 漏洞扫描（主机）-原子能力
     */
    @Test
    public void abilityHostVulnerabilityScanAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityHostVulnerabilityScanAtomicCapabilityInv
        );
        unitAssertEqualsUsingPreparedData(abilityHostVulnerabilityScanAtomicCapabilityCalculator, internalTable, null, "741.8587", "55.3156");
    }

    /**
     * 17 漏洞扫描（Web）-硬件
     */
    @Test
    public void abilityWebVulnerabilityScanHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanHardwareInc,
            BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebVulnerabilityScanHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 18 漏洞扫描（Web）-原子能力
     */
    @Test
    public void abilityWebVulnerabilityScanAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityWebVulnerabilityScanAtomicCapabilityInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebVulnerabilityScanAtomicCapabilityCalculator, internalTable, null, "741.8587", "0");
    }

    /**
     * 19 容器安全
     */
    @Test
    public void abilityContainerSecurityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityContainerSecurityInc,
            BenefitInternalConstructionDO::setAbilityContainerSecurityInv
        );
        unitAssertEqualsUsingPreparedData(abilityContainerSecurityCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 20 容器安全-纯agent
     */
    @Test
    public void abilityContainerSecurityAgentCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityContainerSecurityAgentInc,
            BenefitInternalConstructionDO::setAbilityContainerSecurityAgentInv
        );
        unitAssertEqualsUsingPreparedData(abilityContainerSecurityAgentCalculator, internalTable, null, "741.8587", "73.6771");
    }

    /**
     * 21 网页防篡改-非池化
     */
    @Test
    public void abilityWebTamperPreventionCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebTamperPreventionInc,
            BenefitInternalConstructionDO::setAbilityWebTamperPreventionInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebTamperPreventionCalculator, internalTable, null, "741.8587", "38.7212");
    }

    /**
     * 22 网页防篡改-原子能力
     */
    @Test
    public void abilityWebTamperPreventionAtomicCapabilityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityWebTamperPreventionAtomicCapabilityInc,
            BenefitInternalConstructionDO::setAbilityWebTamperPreventionAtomicCapabilityInv
        );
        unitAssertEqualsUsingPreparedData(abilityWebTamperPreventionAtomicCapabilityCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 23 接口安全管控（API网关）
     */
    @Test
    public void abilityApiGatewaySecurityCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityApiGatewaySecurityInc,
            BenefitInternalConstructionDO::setAbilityApiGatewaySecurityInv
        );
        unitAssertEqualsUsingPreparedData(abilityApiGatewaySecurityCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 24 数据防泄漏（网络侧）
     */
    @Test
    public void abilityNetworkDlpCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityNetworkDlpInc,
            BenefitInternalConstructionDO::setAbilityNetworkDlpInv
        );
        unitAssertEqualsUsingPreparedData(abilityNetworkDlpCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 25 数据防泄漏（网络侧）-国产化设备
     */
    @Test
    public void abilityNetworkDomesticDlpCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityNetworkDomesticDlpInc,
            BenefitInternalConstructionDO::setAbilityNetworkDomesticDlpInv
        );
        unitAssertEqualsUsingPreparedData(abilityNetworkDomesticDlpCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 26 数据脱敏-动态
     */
    @Test
    public void abilityDynamicDataMaskingCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityDynamicDataMaskingInc,
            BenefitInternalConstructionDO::setAbilityDynamicDataMaskingInv
        );
        unitAssertEqualsUsingPreparedData(abilityDynamicDataMaskingCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 27 数据脱敏-静态
     */
    @Test
    public void abilityStaticDataMaskingCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityStaticDataMaskingInc,
            BenefitInternalConstructionDO::setAbilityStaticDataMaskingInv
        );
        unitAssertEqualsUsingPreparedData(abilityStaticDataMaskingCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 28 数据库审计
     */
    @Test
    public void abilityDatabaseAuditCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityDatabaseAuditInc,
            BenefitInternalConstructionDO::setAbilityDatabaseAuditInv
        );
        unitAssertEqualsUsingPreparedData(abilityDatabaseAuditCalculator, internalTable, null, "741.8587", "0");
    }

    /**
     * 29 信令防火墙 C-IWF（5GC）
     */
    @Test
    public void abilitySignalingFirewallCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilitySignalingFirewallInc,
            BenefitInternalConstructionDO::setAbilitySignalingFirewallInv
        );
        unitAssertEqualsUsingPreparedData(abilitySignalingFirewallCalculator, internalTable, null, "741.8587", "0.0000");
    }

    /**
     * 30 零信任（SDP）
     */
    @Test
    public void abilityZeroTrustSdpCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityZeroTrustSdpInc,
            BenefitInternalConstructionDO::setAbilityZeroTrustSdpInv
        );
        unitAssertEqualsUsingPreparedData(abilityZeroTrustSdpCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 31 零信任（SDP）-国产化设备
     */
    @Test
    public void abilityZeroTrustDomesticSdpCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityZeroTrustDomesticSdpInc,
            BenefitInternalConstructionDO::setAbilityZeroTrustDomesticSdpInv
        );
        unitAssertEqualsUsingPreparedData(abilityZeroTrustDomesticSdpCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 32 蜜罐
     */
    @Test
    public void abilityHoneypotCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityHoneypotInc,
            BenefitInternalConstructionDO::setAbilityHoneypotInv
        );
        unitAssertEqualsUsingPreparedData(abilityHoneypotCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 33 蜜罐-国产化设备
     */
    @Test
    public void abilityDomesticHoneypotCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityDomesticHoneypotInc,
            BenefitInternalConstructionDO::setAbilityDomesticHoneypotInv
        );
        unitAssertEqualsUsingPreparedData(abilityDomesticHoneypotCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 34 微隔离
     */
    @Test
    public void abilityMicroSegmentationCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMicroSegmentationInc,
            BenefitInternalConstructionDO::setAbilityMicroSegmentationInv
        );
        unitAssertEqualsUsingPreparedData(abilityMicroSegmentationCalculator, internalTable, null, "741.8587", "31.7607");
    }

    /**
     * 35 异常流量检测-流量转发设备
     */
    @Test
    public void abilityAbnormalTrafficDetectionForwardingDeviceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionForwardingDeviceInc,
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionForwardingDeviceInv
        );
        unitAssertEqualsUsingPreparedData(abilityAbnormalTrafficDetectionForwardingDeviceCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 36 异常流量检测-流量采集设备
     */
    @Test
    public void abilityAbnormalTrafficDetectionCollectionDeviceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionCollectionDeviceInc,
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionCollectionDeviceInv
        );
        unitAssertEqualsUsingPreparedData(abilityAbnormalTrafficDetectionCollectionDeviceCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 37 异常流量检测-报表处理设备
     */
    @Test
    public void abilityAbnormalTrafficDetectionReportDeviceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionReportDeviceInc,
            BenefitInternalConstructionDO::setAbilityAbnormalTrafficDetectionReportDeviceInv
        );
        unitAssertEqualsUsingPreparedData(abilityAbnormalTrafficDetectionReportDeviceCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 38 数据加解密（5GC）
     */
    @Test
    public void abilityDataEncryptionDecryptionCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityDataEncryptionDecryptionInc,
            BenefitInternalConstructionDO::setAbilityDataEncryptionDecryptionInv
        );
        unitAssertEqualsUsingPreparedData(abilityDataEncryptionDecryptionCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 39 僵木蠕监测-监测处置设备
     */
    @Test
    public void abilityBotnetWormDetectionDisposalDeviceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionDisposalDeviceInc,
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionDisposalDeviceInv
        );
        unitAssertEqualsUsingPreparedData(abilityBotnetWormDetectionDisposalDeviceCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 40 僵木蠕监测-接口转发网关
     */
    @Test
    public void abilityBotnetWormDetectionGatewayCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionGatewayInc,
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionGatewayInv
        );
        unitAssertEqualsUsingPreparedData(abilityBotnetWormDetectionGatewayCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 41 僵木蠕监测-监测处置设备考核软件升级
     */
    @Test
    public void abilityBotnetWormDetectionSoftwareUpgradeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionSoftwareUpgradeInc,
            BenefitInternalConstructionDO::setAbilityBotnetWormDetectionSoftwareUpgradeInv
        );
        unitAssertEqualsUsingPreparedData(abilityBotnetWormDetectionSoftwareUpgradeCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 42 IDCISP-硬件（分流器+全量服务器）
     */
    @Test
    public void abilityIdcispHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityIdcispHardwareInc,
            BenefitInternalConstructionDO::setAbilityIdcispHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityIdcispHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 43 IDCISP-软件
     */
    @Test
    public void abilityIdcispSoftwareCalculatorTest() {
        BenefitInternalConstructionDO internalDO = new BenefitInternalConstructionDO();
        internalDO.setAbilityIdcispSoftwareInv(3000000.1111);
        unitAssertEqualsUsingPreparedData(abilityIdcispSoftwareCalculator, List.of(internalDO), null, "3000000.1111", "78.8839");
    }

    /**
     * 44 移动DPI-硬件
     */
    @Test
    public void abilityMobileDpiHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMobileDpiHardwareInc,
            BenefitInternalConstructionDO::setAbilityMobileDpiHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityMobileDpiHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 45 移动DPI-软件
     */
    @Test
    public void abilityMobileDpiSoftwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMobileDpiSoftwareInc,
            BenefitInternalConstructionDO::setAbilityMobileDpiSoftwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityMobileDpiSoftwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 46 固网DPI-硬件（分流器+DPI服务器）
     */
    @Test
    public void abilityFixedNetworkDpiHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityFixedNetworkDpiHardwareInc,
            BenefitInternalConstructionDO::setAbilityFixedNetworkDpiHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityFixedNetworkDpiHardwareCalculator, internalTable, null, "741.8587", "89.9912");
    }

    /**
     * 47 固网DPI-软件
     */
    @Test
    public void abilityFixedNetworkDpiSoftwareCalculatorTest() {
        BenefitInternalConstructionDO internalDO = new BenefitInternalConstructionDO();
        internalDO.setAbilityFixedNetworkDpiSoftwareInv(9353.6489);
        unitAssertEqualsUsingPreparedData(abilityFixedNetworkDpiSoftwareCalculator, List.of(internalDO), null, "9353.6489", "19.7100");
    }

    /**
     * 48 移动恶意软件检测-硬件
     */
    @Test
    public void abilityMobileMalwareDetectionHardwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionHardwareInc,
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionHardwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityMobileMalwareDetectionHardwareCalculator, internalTable, null, "741.8587", "100");
    }

    /**
     * 49 移动恶意程序监测-软件
     */
    @Test
    public void abilityMobileMalwareDetectionSoftwareCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareInc,
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareInv
        );
        unitAssertEqualsUsingPreparedData(abilityMobileMalwareDetectionSoftwareCalculator, internalTable, null, "741.8587", "97.8718");
    }

    /**
     * 50 移动恶意程序监测-软件升级
     */
    @Test
    public void abilityMobileMalwareDetectionSoftwareUpgradeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateAbilityCalculatorData(
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareUpgradeInc,
            BenefitInternalConstructionDO::setAbilityMobileMalwareDetectionSoftwareUpgradeInv
        );
        unitAssertEqualsUsingPreparedData(abilityMobileMalwareDetectionSoftwareUpgradeCalculator, internalTable, null, "741.8587", "84.0839");
    }

    /**
     * 1 移动上网日志留存-集采
     */
    @Test
    public void softwareMobileInternetLogRetentionCentralizedCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareMobileInternetLogRetentionCentralizedInv
        );

        unitAssertEqualsUsingPreparedData(softwareMobileInternetLogRetentionCentralizedCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 2 移动上网日志留存-个性化省采
     */
    @Test
    public void softwareMobileInternetLogRetentionCustomizedTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareMobileInternetLogRetentionCustomizedInv
        );
        unitAssertEqualsUsingPreparedData(softwareMobileInternetLogRetentionCustomized, internalTable, null, "4180651.3615", "84.2799");
    }

    /**
     * 3 僵木蠕监测-平台总价
     */
    @Test
    public void softwareBotnetWormDetectionPlatformTotalPriceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareBotnetWormDetectionPlatformTotalPriceInv
        );
        unitAssertEqualsUsingPreparedData(softwareBotnetWormDetectionPlatformTotalPriceCalculator, internalTable, null, "4180651.3615", "54.1421");
    }

    /**
     * 4 IDCISP-平台总价
     */
    @Test
    public void softwareIdcispPlatformTotalPriceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareIdcispPlatformTotalPriceInv
        );
        unitAssertEqualsUsingPreparedData(softwareIdcispPlatformTotalPriceCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 5 移动DPI-平台总价
     */
    @Test
    public void softwareMobileDpiPlatformTotalPriceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareMobileDpiPlatformTotalPriceInv
        );
        unitAssertEqualsUsingPreparedData(softwareMobileDpiPlatformTotalPriceCalculator, internalTable, null, "4180651.3615", "96.2488");
    }

    /**
     * 6 固网DPI-平台总价
     */
    @Test
    public void softwareFixedNetworkDpiPlatformTotalPriceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareFixedNetworkDpiPlatformTotalPriceInv
        );
        unitAssertEqualsUsingPreparedData(softwareFixedNetworkDpiPlatformTotalPriceCalculator, internalTable, null, "4180651.3615", "77.6426");
    }

    /**
     * 7 移动恶意程序监测-平台总价
     */
    @Test
    public void softwareMobileMalwareDetectionPlatformTotalPriceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareMobileMalwareDetectionPlatformTotalPriceInv
        );
        unitAssertEqualsUsingPreparedData(softwareMobileMalwareDetectionPlatformTotalPriceCalculator, internalTable, null, "4180651.3615", "39.3357");
    }

    /**
     * 8 资产管理
     */
    @Test
    public void softwareAssetManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAssetManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareAssetManagementCalculator, internalTable, null, "4180651.3615", "20.4708");
    }

    /**
     * 9 基线管理
     */
    @Test
    public void softwareBaselineManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareBaselineManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareBaselineManagementCalculator, internalTable, null, "4180651.3615", "32.2212");
    }

    /**
     * 10 漏洞管理
     */
    @Test
    public void softwareVulnerabilityManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareVulnerabilityManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareVulnerabilityManagementCalculator, internalTable, null, "4180651.3615", "30.7633");
    }

    /**
     * 11 互联网暴露面管理
     */
    @Test
    public void softwareInternetExposureManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareInternetExposureManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareInternetExposureManagementCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 12 内网资产测绘
     */
    @Test
    public void softwareInternalNetworkAssetMappingCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareInternalNetworkAssetMappingInv
        );
        unitAssertEqualsUsingPreparedData(softwareInternalNetworkAssetMappingCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 13 4A
     */
    @Test
    public void softwareAaaaCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAaaaInv
        );
        unitAssertEqualsUsingPreparedData(softwareAaaaCalculator, internalTable, null, "4180651.3615", "77.5891");
    }

    /**
     * 14 APP上线检测
     */
    @Test
    public void softwareAppReleaseDetectionCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAppReleaseDetectionInv
        );
        unitAssertEqualsUsingPreparedData(softwareAppReleaseDetectionCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 15 数据资产管理
     */
    @Test
    public void softwareDataAssetManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareDataAssetManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareDataAssetManagementCalculator, internalTable, null, "4180651.3615", "17.4460");
    }

    /**
     * 16 密码服务管理
     */
    @Test
    public void softwarePasswordServiceManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwarePasswordServiceManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwarePasswordServiceManagementCalculator, internalTable, null, "4180651.3615", "60.0837");
    }

    /**
     * 17 威胁情报
     */
    @Test
    public void softwareThreatIntelligenceCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareThreatIntelligenceInv
        );
        unitAssertEqualsUsingPreparedData(softwareThreatIntelligenceCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 18 网络安全态势感知
     */
    @Test
    public void softwareNetworkSecuritySituationalAwarenessCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareNetworkSecuritySituationalAwarenessInv
        );
        unitAssertEqualsUsingPreparedData(softwareNetworkSecuritySituationalAwarenessCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 19 数据安全态势感知
     */
    @Test
    public void softwareDataSecuritySituationalAwarenessCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareDataSecuritySituationalAwarenessInv
        );
        unitAssertEqualsUsingPreparedData(softwareDataSecuritySituationalAwarenessCalculator, internalTable, null, "4180651.3615", "31.9557");
    }

    /**
     * 20 互联网网站备案监测
     */
    @Test
    public void softwareWebsiteFilingMonitoringCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareWebsiteFilingMonitoringInv
        );
        unitAssertEqualsUsingPreparedData(softwareWebsiteFilingMonitoringCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 21 不良信息监测
     */
    @Test
    public void softwareHarmfulInformationMonitoringCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareHarmfulInformationMonitoringInv
        );
        unitAssertEqualsUsingPreparedData(softwareHarmfulInformationMonitoringCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 22 反诈管理
     */
    @Test
    public void softwareAntiFraudManagementCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAntiFraudManagementInv
        );
        unitAssertEqualsUsingPreparedData(softwareAntiFraudManagementCalculator, internalTable, null, "4180651.3615", "86.3843");
    }

    /**
     * 23 内容安全“先审后发”管控
     */
    @Test
    public void softwareContentSecurityReviewPublishControlCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareContentSecurityReviewPublishControlInv
        );
        unitAssertEqualsUsingPreparedData(softwareContentSecurityReviewPublishControlCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 24 一键处置（含一键派单、封堵、关停）
     */
    @Test
    public void softwareOneClickDisposalCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareOneClickDisposalInv
        );
        unitAssertEqualsUsingPreparedData(softwareOneClickDisposalCalculator, internalTable, null, "4180651.3615", "27.1618");
    }

    /**
     * 25 SOAR
     */
    @Test
    public void softwareSoarCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareSoarInv
        );
        unitAssertEqualsUsingPreparedData(softwareSoarCalculator, internalTable, null, "4180651.3615", "79.8815");
    }

    /**
     * 26 网络攻击溯源
     */
    @Test
    public void softwareNetworkAttackTracingCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareNetworkAttackTracingInv
        );
        unitAssertEqualsUsingPreparedData(softwareNetworkAttackTracingCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 27 安全能力中心
     */
    @Test
    public void softwareSecurityCapabilityCenterCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareSecurityCapabilityCenterInv
        );
        unitAssertEqualsUsingPreparedData(softwareSecurityCapabilityCenterCalculator, internalTable, null, "4180651.3615", "55.9533");
    }

    /**
     * 28 安全数据中心
     */
    @Test
    public void softwareSecurityDataCenterCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareSecurityDataCenterInv
        );
        unitAssertEqualsUsingPreparedData(softwareSecurityDataCenterCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 29 攻防演练
     */
    @Test
    public void softwareAttackDefenseDrillCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAttackDefenseDrillInv
        );
        unitAssertEqualsUsingPreparedData(softwareAttackDefenseDrillCalculator, internalTable, null, "4180651.3615", "0");
    }

    /**
     * 30 补丁管理（5GC内生）
     */
    @Test
    public void softwarePatchManagementCoreNativeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwarePatchManagementCoreNativeInv
        );
        unitAssertEqualsUsingPreparedData(softwarePatchManagementCoreNativeCalculator, internalTable, null, "4180651.3615", "80.3225");
    }

    /**
     * 31 补丁管理（5GC外挂）
     */
    @Test
    public void softwarePatchManagementCoreExternalCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwarePatchManagementCoreExternalInv
        );
        unitAssertEqualsUsingPreparedData(softwarePatchManagementCoreExternalCalculator, internalTable, null, "4180651.3615", "80.3225");
    }

    /**
     * 32 漏洞管理（5GC内生）
     */
    @Test
    public void softwareVulnerabilityManagementCoreNativeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareVulnerabilityManagementCoreNativeInv
        );
        unitAssertEqualsUsingPreparedData(softwareVulnerabilityManagementCoreNativeCalculator, internalTable, null, "4180651.3615", "80.3225");
    }

    /**
     * 33 资产管理（5GC内生）
     */
    @Test
    public void softwareAssetManagementCoreNativeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareAssetManagementCoreNativeInv
        );
        unitAssertEqualsUsingPreparedData(softwareAssetManagementCoreNativeCalculator, internalTable, null, "4180651.3615", "80.3225");
    }

    /**
     * 34 态势感知（5GC内生）
     */
    @Test
    public void softwareSituationalAwarenessCoreNativeCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareSituationalAwarenessCoreNativeInv
        );
        unitAssertEqualsUsingPreparedData(softwareSituationalAwarenessCoreNativeCalculator, internalTable, null, "4180651.3615", "80.3225");
    }

    /**
     * 35 UEBA（5GC用户行为分析）
     */
    @Test
    public void softwareUebaCoreCalculatorTest() {
        List<BenefitInternalConstructionDO> internalTable = generateSoftwareCalculatorData(
            BenefitInternalConstructionDO::setSoftwareUebaCoreInv
        );
        unitAssertEqualsUsingPreparedData(softwareUebaCoreCalculator, internalTable, null, "4180651.3615", "0");
    }

    private static BenefitExternalEmpowermentDO generateProvinceCompanyIncomeToInvestmentRatioData(double safetyTotalIncome){
        BenefitInternalConstructionDO rowA = new BenefitInternalConstructionDO();
        rowA.setNonSecurityDeviceFee(0d);
        rowA.setSecurityDeviceFee(981132.08);
        rowA.setTotalOtherFee(63678.6907924528);

        BenefitInternalConstructionDO rowB = new BenefitInternalConstructionDO();
        rowB.setNonSecurityDeviceFee(333.33);

        // * 本省当年网发安全类项目总投资（万元）【自动生成不用填写】 = 104.5144100792
        double autoProjectDesignReplyTotalInvestment = mockUpdateAutoSafetyTotalInvestment(ImmutableList.of(rowA, rowB));

        BenefitExternalEmpowermentDO externalTable = new BenefitExternalEmpowermentDO();
        externalTable.setAutoSafetyTotalInvestment(autoProjectDesignReplyTotalInvestment);
        externalTable.setSafetyTotalIncome(safetyTotalIncome);

        return externalTable;
    }

    /**
     * 效益/对外赋能/评估省公司收投比
     */
    @Test
    public void provinceCompanyIncomeToInvestmentRatioCalculatorTest() {
        // * 填报本省当年安全科目总收入（包含量子、ICT等）（万元） = 500
        BenefitExternalEmpowermentDO externalTable = generateProvinceCompanyIncomeToInvestmentRatioData(500d);

        unitAssertEqualsUsingPreparedData(provinceCompanyIncomeToInvestmentRatioCalculator, null, externalTable, "4.7840292991", "1");
    }

    /**
     * 测试 python 脚本计算
     */
    @Test
    public void calculateThresholdTest(){
        // psvm
        List<Number> asPython = List.of(8.04, 11.04, 2.04, 0.71);
        ThresholdPair thresholdPair =  GroceryUtils.calculateThresholdPair(asPython);
        System.out.printf("Output: %f %f\n", thresholdPair.getLower(), thresholdPair.getUpper());
        assertEquals(new BigDecimal(thresholdPair.getLower()).setScale(4, RoundingMode.HALF_UP), new BigDecimal("1.9425739583676782").setScale(4, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal(thresholdPair.getUpper()).setScale(4, RoundingMode.HALF_UP), new BigDecimal("5.836859732226507").setScale(4, RoundingMode.HALF_UP));
    }

    /**
     * 模拟 #updateAutoSecurityDeviceOtherFee
     * = 总体其他费（元） * (安全类设备费（元） / (非安全类设备费（元）+ 安全类设备费（元）))
     */
    private static void mockUpdateAutoSecurityDeviceOtherFee(BenefitInternalConstructionDO internalDO){
        double totalOtherFee = internalDO.getTotalOtherFee() == null ? 0d : internalDO.getTotalOtherFee();
        double securityDeviceFee = internalDO.getSecurityDeviceFee() == null ? 0d : internalDO.getSecurityDeviceFee();
        double nonSecurityDeviceFee = internalDO.getNonSecurityDeviceFee() == null ? 0d : internalDO.getNonSecurityDeviceFee();
        Double calculateRes;
        if (securityDeviceFee + nonSecurityDeviceFee == 0d){
            calculateRes = null;
        } else {
            calculateRes = totalOtherFee * (securityDeviceFee / (nonSecurityDeviceFee + securityDeviceFee));
        }
        internalDO.setAutoSecurityDeviceOtherFee(calculateRes);
    }

    /**
     * 模拟 #updateAutoSafetyTotalInvestment
     * = 非安全类设备费（元） + 安全类设备费（元） + 总体其他费
     */
    private static double mockUpdateAutoSafetyTotalInvestment(List<BenefitInternalConstructionDO> internalTable){
        double sum = internalTable.stream().mapToDouble(internalDO -> {
            double totalOtherFee = internalDO.getTotalOtherFee() == null ? 0d : internalDO.getTotalOtherFee();
            double securityDeviceFee = internalDO.getSecurityDeviceFee() == null ? 0d : internalDO.getSecurityDeviceFee();
            double nonSecurityDeviceFee = internalDO.getNonSecurityDeviceFee() == null ? 0d : internalDO.getNonSecurityDeviceFee();
            return nonSecurityDeviceFee + securityDeviceFee + totalOtherFee;
        }).sum();
        return sum/10000;
    }

}
