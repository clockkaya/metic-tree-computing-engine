package com.sama.ledger.utils;

import com.alibaba.fastjson2.JSON;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.sama.api.ledger.bean.structure.*;
import com.sama.ledger.config.NacosConfig;
import com.sama.ledger.metric.calculators.BaseCalculator;
import com.sama.ledger.service.MetricConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.*;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/18 13:28
 */
@Component
public class MetricMockDataUtils {

    private static final Logger logger = LogManager.getLogger(MetricMockDataUtils.class);

    @Resource
    MetricConfigService metricConfigService;

    @Resource
    NacosConfig nacosConfig;

    /**
     * 旨在模拟通过指定的 aviatorRule 和传入的 b.评估值，是否可以运算得到正确 c.评估得分
     *
     * @param baseCalculator            具体的 BaseCalculator 实现
     * @param mockedAssessedValueMap    b.评估值
     * @return  MetricResultNode
     */
    public MetricResultNode simulateSimpleAviatorCalculate(BaseCalculator<?> baseCalculator, int metricType, Map<String, Object> mockedAssessedValueMap){
        // 1 准备传递参数体
        MetricResultNode resultNode = new MetricResultNode();
        MetricConfigNode metricConfigNode = metricConfigService.getStandingNode(metricType, baseCalculator.getCalculatorKey());
        String aviatorRule = metricConfigNode.getAviatorRule();
        // BigDecimal weight = metricConfigNode.getWeight();

        // 2
        resultNode.setAssessedValueMap(mockedAssessedValueMap);

        // 4 ——> c.评估得分
        Expression compiledExpr = AviatorEvaluator.compile(aviatorRule, true);
        Object assessedScore = compiledExpr.execute(mockedAssessedValueMap);
        BigDecimal formattedAssessedScore = NumberUtils.safeConvertToBigDecimal(assessedScore);

        // 6 赋值返回
        resultNode.setKeyEn(baseCalculator.getCalculatorKey());
        resultNode.setAviatorRule(aviatorRule);
        resultNode.setAssessedScore(formattedAssessedScore);

        logger.info("【{}|Mock】 完成 Aviator 计算:{} ", baseCalculator.getCalculatorKey(), JSON.toJSONString(resultNode));
        return resultNode;
    }

    public static BenefitThresholdMap mockBenefitThresholdMap(){
        BenefitThresholdMap benefitThresholdMap = new BenefitThresholdMap();
        benefitThresholdMap.put(V_FIREWALL_HARDWARE, simpleSet(252.7686d, 682.8468, 591.7616, 444.1981));
        benefitThresholdMap.put(V_FIREWALL_ATOMIC_CAPABILITY, simpleSet(264.0000));
        benefitThresholdMap.put(V_IPS_HARDWARE, simpleSet(1057.8049, 888.3962));
        benefitThresholdMap.put(V_IPS_ATOMIC_CAPABILITY, simpleSet(528.0000));
        benefitThresholdMap.put(V_WAF_HARDWARE, simpleSet(7314.2244, 21816.2737, 4649.2615));
        benefitThresholdMap.put(V_WAF_DOMESTIC_HARDWARE, simpleSet(41797d));
        benefitThresholdMap.put(V_WAF_ATOMIC_CAPABILITY, simpleSet(7186.0000, 17790.1307));
        benefitThresholdMap.put(V_WEB_DYNAMIC_DEFENSE_HARDWARE, simpleSet(59960.0000, 189145.8320, 416411.0704));
        benefitThresholdMap.put(V_WEB_DYNAMIC_DEFENSE_SOFTWARE, simpleSet(10000.0000, 9688.7479, 8908.0000));
        // mock
        benefitThresholdMap.put(V_TRAFFIC_SCRUBBING, simpleSet(0, 666.6667));
        benefitThresholdMap.put(V_FULL_TRAFFIC_ANALYSIS, simpleSet(14897.3833, 13454.0000, 21986.2621, 1028.0000, 29183.3792, 17900.0000, 13500.0000, 19287.7948, 56092.0000, 15152.0000, 23420.0000, 13528.0000));
        benefitThresholdMap.put(V_BASTION_HOST, simpleSet(500.0000, 405.4341));
        benefitThresholdMap.put(V_LOG_AUDIT, simpleSet(15.2233, 46.0095,12.8429, 5.2247));
        benefitThresholdMap.put(V_EDR, simpleSet(776.5577, 215.7747, 427.0000, 293.8000, 307.0000, 242.0000));
        benefitThresholdMap.put(V_HOST_VULNERABILITY_SCAN_HARDWARE, simpleSet(2317.3909, 4488.6906));
        benefitThresholdMap.put(V_HOST_VULNERABILITY_SCAN_ATOMIC_CAPABILITY, simpleSet(391.7535));
        benefitThresholdMap.put(V_WEB_VULNERABILITY_SCAN_HARDWARE, simpleSet(42081.4748));
        benefitThresholdMap.put(V_WEB_VULNERABILITY_SCAN_ATOMIC_CAPABILITY, simpleSet(50.1444));
        benefitThresholdMap.put(V_CONTAINER_SECURITY, simpleSet(2589.2964, 1609.9793, 2535.3257, 1000.0000, 4077.0000, 3906.0000));
        benefitThresholdMap.put(V_CONTAINER_SECURITY_AGENT, simpleSet(486d));
        benefitThresholdMap.put(V_WEB_TAMPER_PREVENTION, simpleSet(0d, 333.3333));
        benefitThresholdMap.put(V_WEB_TAMPER_PREVENTION_ATOMIC_CAPABILITY, simpleSet(1300.0000));
        benefitThresholdMap.put(V_API_GATEWAY_SECURITY, simpleSet(10079.9410, 36363.0718, 24149.6897, 14680.0000, 15600.0000));
        benefitThresholdMap.put(V_NETWORK_DLP, simpleSet(58751.3012, 12351.0017, 46967.8673, 55120.8353, 45000.0000, 20012.1000));
        benefitThresholdMap.put(V_NETWORK_DOMESTIC_DLP, simpleSet(152233d, 38800d));
        benefitThresholdMap.put(V_DYNAMIC_DATA_MASKING, simpleSet(8000.0000));
        benefitThresholdMap.put(V_STATIC_DATA_MASKING, simpleSet(6400.0000, 5070.6400));
        benefitThresholdMap.put(V_DATABASE_AUDIT, simpleSet(16.1700));
        // mock
        benefitThresholdMap.put(V_SIGNALING_FIREWALL, simpleSet(0d, 0.00001));
        benefitThresholdMap.put(V_ZERO_TRUST_SDP, simpleSet(450.0000, 343.0000, 1303.5323));
        benefitThresholdMap.put(V_ZERO_TRUST_DOMESTIC_SDP, simpleSet(5559d));
        benefitThresholdMap.put(V_HONEYPOT, simpleSet(637.0000, 636.0000, 4310.0000, 4046.1259, 4905.6604));
        benefitThresholdMap.put(V_DOMESTIC_HONEYPOT, simpleSet(10666d));
        benefitThresholdMap.put(V_MICRO_SEGMENTATION, simpleSet(160.1150, 392.5500));
        benefitThresholdMap.put(V_ABNORMAL_TRAFFIC_DETECTION_FORWARDING_DEVICE, simpleSet(15586.0000, 88623.0000));
        benefitThresholdMap.put(V_ABNORMAL_TRAFFIC_DETECTION_COLLECTION_DEVICE, simpleSet(7225.0000, 11500.0000));
        benefitThresholdMap.put(V_ABNORMAL_TRAFFIC_DETECTION_REPORT_DEVICE, simpleSet(57472.0000, 70000.0000));
        // mock
        benefitThresholdMap.put(V_DATA_ENCRYPTION_DECRYPTION, simpleSet(0d, 888.8888));
        benefitThresholdMap.put(V_BOTNET_WORM_DETECTION_DISPOSAL_DEVICE, simpleSet(5303.8957, 3675.0000, 2443.7753, 5429.4592));
        benefitThresholdMap.put(V_BOTNET_WORM_DETECTION_GATEWAY, simpleSet(94000.0000, 348800.0000, 125000.0000, 325767.5505, 350000.0000, 48508.9100));
        benefitThresholdMap.put(V_BOTNET_WORM_DETECTION_SOFTWARE_UPGRADE, simpleSet(770440.0000, 669965.7774, 261600.0000, 450000.0000, 1078000.0000, 200000.0000, 720000.0000, 570000.0000));
        benefitThresholdMap.put(V_IDCISP_HARDWARE, simpleSet(1850.3033, 430.0000, 1475.3138, 1034.0000, 1991.0000, 1262.0000, 1643.8267, 539.2209, 1720.0000, 2229.0000, 602.0000));
        benefitThresholdMap.put(V_IDCISP_SOFTWARE, simpleSet(1385500.0000, 2145000.0000, 1912796.0000, 2327220.0000, 1500019.0000, 1325800.0000, 2666840.0000, 2307600.0000, 1772507.0000));
        benefitThresholdMap.put(V_MOBILE_DPI_HARDWARE, simpleSet(3910.3367, 4279.6919, 3229.0000, 2252.0000, 2776.0000, 3520.0000, 5912.0000, 2919.3177, 2123.0000, 2919.0000, 2215.0000));
        benefitThresholdMap.put(V_MOBILE_DPI_SOFTWARE, simpleSet(2668.0000, 1890.0000, 2089.1255, 1188.0000, 861.0000));
        benefitThresholdMap.put(V_FIXED_NETWORK_DPI_HARDWARE, simpleSet(278.0909, 330.0000, 1057.0000));
        // mock
        benefitThresholdMap.put(V_FIXED_NETWORK_DPI_SOFTWARE,  simpleSet(0d, 3589.55));
        benefitThresholdMap.put(V_MOBILE_MALWARE_DETECTION_HARDWARE, simpleSet(4997.2633, 1449.0000, 1273.0000, 5018.0000, 3629.4841, 605.2569, 1260.0000, 2896.5105, 945.0000, 2591.0000));
        benefitThresholdMap.put(V_MOBILE_MALWARE_DETECTION_SOFTWARE, simpleSet(613.0000, 265.0000, 649.0000, 1987.2998, 858.0000, 270.0000, 324.0000, 225.0000, 661.0000));
        benefitThresholdMap.put(V_MOBILE_MALWARE_DETECTION_SOFTWARE_UPGRADE, simpleSet(580.0000, 321.0000, 862.0000, 591.0000, 407.0000, 579.0000, 541.0000, 226.0000, 381.0000));
        // mock
        benefitThresholdMap.put(V_MOBILE_INTERNET_LOG_RETENTION_CENTRALIZED, simpleSet(0d, 1000d));
        // mock
        benefitThresholdMap.put(V_MOBILE_INTERNET_LOG_RETENTION_CUSTOMIZED, simpleSet(0d, 3180651.3615d));
        // mock
        benefitThresholdMap.put(V_BOTNET_WORM_DETECTION_PLATFORM_TOTAL_PRICE, simpleSet(0d, 2180651.3615));
        // mock
        benefitThresholdMap.put(V_IDCISP_PLATFORM_TOTAL_PRICE, simpleSet(0d, 1180651.3615));
        // mock
        benefitThresholdMap.put(V_MOBILE_DPI_PLATFORM_TOTAL_PRICE, simpleSet(0d, 3888888.888));
        // mock
        benefitThresholdMap.put(V_FIXED_NETWORK_DPI_PLATFORM_TOTAL_PRICE, simpleSet(0d, 2888888.888));
        // mock
        benefitThresholdMap.put(V_MOBILE_MALWARE_DETECTION_PLATFORM_TOTAL_PRICE, simpleSet(0d, 1888888.888));
        benefitThresholdMap.put(V_ASSET_MANAGEMENT, simpleSet(975250d, 2208730d, 2644489d, 822806d, 666450d, 698998d, 2744000d, 71700d, 1539950d, 2091729d, 589000d));
        benefitThresholdMap.put(V_BASELINE_MANAGEMENT, simpleSet(5416725d, 129465d, 810000d));
        benefitThresholdMap.put(V_VULNERABILITY_MANAGEMENT, simpleSet(830189d, 1839900d, 1691774d, 1584516d, 1172600d, 1326572d, 1398600d, 2660000d, 1681754d));
        benefitThresholdMap.put(V_INTERNET_EXPOSURE_MANAGEMENT, simpleSet(784798d));
        benefitThresholdMap.put(V_INTERNAL_NETWORK_ASSET_MAPPING, simpleSet(1825297d, 538602d, 746226d));
        benefitThresholdMap.put(V_AAAA, simpleSet(1617925d, 3215677d, 2050281d, 1356600d, 5273953d, 2600000d, 1542842d, 3452065d, 2009659d));
        benefitThresholdMap.put(V_APP_RELEASE_DETECTION, simpleSet(1117430d, 575369d));
        benefitThresholdMap.put(V_DATA_ASSET_MANAGEMENT, simpleSet(1802071d, 177148d, 891077d, 1025000d, 2654075d, 1493328d));
        benefitThresholdMap.put(V_PASSWORD_SERVICE_MANAGEMENT, simpleSet(3735399d, 196598d, 2477638d, 1331052d, 2937434d,  1185899d));
        benefitThresholdMap.put(V_THREAT_INTELLIGENCE, simpleSet(785000d, 300000d, 703243d));
        benefitThresholdMap.put(V_NETWORK_SECURITY_SITUATIONAL_AWARENESS, simpleSet(920000d, 951415d, 813053d, 1649346d, 1956450d, 5054170d, 350000d, 470000d, 474719d, 524116d, 295846d));
        benefitThresholdMap.put(V_DATA_SECURITY_SITUATIONAL_AWARENESS, simpleSet(1379083d, 1548485d, 1239463d, 2315000d));
        benefitThresholdMap.put(V_WEBSITE_FILING_MONITORING, simpleSet(457547d, 710000d, 150000d,  858074d));
        benefitThresholdMap.put(V_HARMFUL_INFORMATION_MONITORING, simpleSet(328302d, 3325245d, 310000d, 150000d, 409930d, 1212105d));
        benefitThresholdMap.put(V_ANTI_FRAUD_MANAGEMENT, simpleSet(3609946d, 6345743d, 955000d, 827100d, 6057166d, 1475000d, 1653285d));
        benefitThresholdMap.put(V_CONTENT_SECURITY_REVIEW_PUBLISH_CONTROL, simpleSet(1031726d, 858462d, 1483000d, 181132d, 215000d));
        benefitThresholdMap.put(V_ONE_CLICK_DISPOSAL, simpleSet(1701690d));
        benefitThresholdMap.put(V_SOAR, simpleSet(2981132d));
        benefitThresholdMap.put(V_NETWORK_ATTACK_TRACING, simpleSet(1139200d));
        benefitThresholdMap.put(V_SECURITY_CAPABILITY_CENTER, simpleSet(3972685d, 798343d, 804926d, 4083011d, 661000d));
        benefitThresholdMap.put(V_SECURITY_DATA_CENTER, simpleSet(380188.6800, 617607.9744, 854606.0349, 1000000.0000, 370000.0000, 480000.0000, 634951.1981, 749025.4700, 2462728.0000));
        benefitThresholdMap.put(V_ATTACK_DEFENSE_DRILL, simpleSet(594450.0000, 852529.1900, 563207.5500));
        // mock
        benefitThresholdMap.put(V_PATCH_MANAGEMENT_CORE_NATIVE, simpleSet(0d, 3000000d));
        // mock
        benefitThresholdMap.put(V_PATCH_MANAGEMENT_CORE_EXTERNAL, simpleSet(0d, 3000000d));
        // mock
        benefitThresholdMap.put(V_VULNERABILITY_MANAGEMENT_CORE_NATIVE, simpleSet(0d, 3000000d));
        // mock
        benefitThresholdMap.put(V_ASSET_MANAGEMENT_CORE_NATIVE, simpleSet(0d, 3000000d));
        // mock
        benefitThresholdMap.put(V_SITUATIONAL_AWARENESS_CORE_NATIVE, simpleSet(0d, 3000000d));
        benefitThresholdMap.put(V_UEBA_CORE, simpleSet(300000.0000, 966401.6432, 441681.5104, 151515.3307));
        // mock
        benefitThresholdMap.put(IV_PROVINCE_COMPANY_INCOME_TO_INVESTMENT_RATIO, simpleSet(0.4339, 0.2544, 0.5585, 0.4770, 0.4925, 0.2307, 0.1685, 1.0007, 1.1681, 0.3452, 0.5007, 0.2409, 0.5711));
        return benefitThresholdMap;
    }

    private static CategorizedThresholdPair simpleSet(Number... data){
        CategorizedThresholdPair categorizedThresholdPair = new CategorizedThresholdPair();
        ThresholdPair thresholdPair = GroceryUtils.calculateThresholdPair(List.of(data));
        categorizedThresholdPair.setFull(thresholdPair);
        // categorizedThresholdPair.setLarge(thresholdPair);
        // categorizedThresholdPair.setMedium(thresholdPair);
        // categorizedThresholdPair.setSmall(thresholdPair);
        return categorizedThresholdPair;
    }

}
