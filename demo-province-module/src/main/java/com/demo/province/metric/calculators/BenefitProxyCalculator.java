package com.sama.ledger.metric.calculators;

import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitL2CalculatorEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitProvinceCategoryEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.BiConsumer;

import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.INVISIBLE_SOFTWARE_INVESTMENT;
import static com.sama.api.ledger.bean.indicator.MetricBenefitConstants.INVISIBLE_UNIT_COST;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/8 10:30
 */
public abstract class BenefitProxyCalculator<T extends PreparedDataModel> extends BaseCalculator<T>{

    private static final Logger logger = LogManager.getLogger(BenefitProxyCalculator.class);

    public static final String KEY_INNER_ROW = "innerRow";
    public static final String KEY_THRESHOLD = MetricConstants.DimensionKey.THRESHOLD;

    protected MetricBenefitL2CalculatorEnum calculatorEnum;

    //==============================================================================
    // proxy core frame
    //==============================================================================

    @Override
    @PostConstruct
    protected void init() {
        super.init();
        calculatorEnum = MetricBenefitL2CalculatorEnum.findByCalculatorEn(calculatorKey);
    }

    @Override
    protected boolean stepIntoAssessedScore(MetricResultNode resultNode, int metricType) {
        if (metricType == MetricTypeEnum.BENEFIT_DEV.getType()){
            logger.info("【{}】 {} 跳过计算 c.评估得分", calculatorKey, MetricTypeEnum.BENEFIT_DEV.getName());
            return false;
        }

        return super.stepIntoAssessedScore(resultNode, metricType);
    }

    //==============================================================================
    // utils
    //==============================================================================

    /**
     * 通用计分规则
     */
    protected void calculateAverageVar(BenefitPreparedDataBO preparedData, MetricResultNode resultNode){
        if (preparedData.getMetricType() == MetricTypeEnum.BENEFIT_DEV.getType()){
            calculateAverageVarDev(preparedData, resultNode);
        } else if (preparedData.getMetricType() == MetricTypeEnum.BENEFIT_RELEASE.getType()) {
            calculateAverageVarRelease(preparedData, resultNode);
        }
    }

    private void calculateAverageVarDev(BenefitPreparedDataBO preparedData, MetricResultNode resultNode){
        String invisibleParent = calculatorEnum.getAssessment().getInvisibleParent();

        // 造价 var
        OptionalDouble averageVar = preparedData.getInternalData().stream()
            .filter(this::validBefore)
            // 行找
            .mapToDouble(internalDO -> {
                // 列找
                BenefitColumnTemplate columnTemplate = calculatorEnum.getColumnTemplateExtractor().apply(internalDO);
                Double var = null;

                // 计算
                if (calculatorEnum.name().contains("SPECIAL")){
                    var = columnTemplate.getDirectValue();
                } else {
                    double autoSecurityDeviceOtherFee = Optional.ofNullable(internalDO.getAutoSecurityDeviceOtherFee()).orElse(0.0);
                    // (1) 该指标分摊费用 = (能力投资 / 安全类设备费（元）) * 安全类设备其他费（元）【自动生成不用填写】
                    double shareCost = (columnTemplate.getInvestment() / internalDO.getSecurityDeviceFee()) * autoSecurityDeviceOtherFee;
                    // (2) 该指标综合总投资 = 能力投资 + 该指标分摊费用
                    double comprehensiveInvestment = columnTemplate.getInvestment() + shareCost;

                    if (INVISIBLE_UNIT_COST.equals(invisibleParent)){
                        // (3) 单位造价 = 该指标综合总投资 / 该能力新增指标总量
                        double unitCost = comprehensiveInvestment / columnTemplate.getIncrement();
                        var =  unitCost;
                    } else if(INVISIBLE_SOFTWARE_INVESTMENT.equals(invisibleParent)) {
                        var =  comprehensiveInvestment;
                    }
                }

                // 定位
                BiConsumer<BenefitInternalConstructionDO, Double> internalVarSetter = calculatorEnum.getInternalVarSetter();
                // 赋值待更新
                internalVarSetter.accept(internalDO, var);

                return var;
            })
            .filter(var -> var > 0)
            .average();

        // 有效造价
        Double validAverageVar = null;
        if (averageVar.isPresent() && averageVar.getAsDouble() > 0) {
            validAverageVar = averageVar.getAsDouble();
        }

        // b.评估值
        Map<String, Object> assessedValueMap = getDefaultAssessedValueMap(validAverageVar);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    private void calculateAverageVarRelease(BenefitPreparedDataBO preparedData, MetricResultNode resultNode){
        String invisibleParent = calculatorEnum.getAssessment().getInvisibleParent();
        // repeat Dev
        calculateAverageVarDev(preparedData, resultNode);
        Map<String, Object> assessedValueMap = resultNode.getAssessedValueMap();
        Double validAverageVar = (Double)assessedValueMap.get(KEY_INPUT);

        // 厂家
        String vendors = "--";
        if (INVISIBLE_UNIT_COST.equals(invisibleParent)){
            List<String> vendorList = preparedData.getInternalData().stream()
                .map(internalDO -> calculatorEnum.getVendorExtractor().apply(internalDO))
                .filter(DataUtils::isNotEmpty)
                .flatMap(vendorStr -> Arrays.stream(vendorStr.split("\\s*[,，.。;；|/、]\\s*")))
                .map(String::trim)
                .filter(DataUtils::isNotEmpty)
                .distinct()
                .toList();
            vendors = String.join(", ", vendorList);
        }

        // 阈值上限
        Double upperThreshold = null;
        CategorizedThresholdPair categorizedThresholdPair = preparedData.getThresholdMap().get(calculatorKey);
        if (INVISIBLE_UNIT_COST.equals(invisibleParent)){
            upperThreshold = Optional.ofNullable(categorizedThresholdPair)
                .map(CategorizedThresholdPair::getFull)
                .map(ThresholdPair::getUpper)
                .orElse(null);
        } else if(INVISIBLE_SOFTWARE_INVESTMENT.equals(invisibleParent)) {
            // orgCode -> orgCn -> MetricBenefitProvinceCategoryEnum.betweenCategoryExtractor -> preparedData.getThresholds() -> getUpper
            String orgCn = this.getOrgCodeAndNameCache().get(preparedData.getOrgCode());
            upperThreshold = Optional.ofNullable(MetricBenefitProvinceCategoryEnum.tellExtractorByAmbiguousOrgCn(orgCn))
                .map(categorizedExtractor -> categorizedExtractor.apply(categorizedThresholdPair))
                .map(ThresholdPair::getUpper)
                .orElse(null);
        }

        // a.过程数据
        LinkedHashMap<String, Object> processingDataMap = new LinkedHashMap<>();
        // 因为横向展示，此处用 ProcessingDataInnerRow 深嵌套保证一行
        processingDataMap.put(KEY_INNER_ROW, new ProcessingDataInnerRow(vendors, validAverageVar, upperThreshold));
        resultNode.setProcessingDataMap(processingDataMap);

        // b.评估值
        // 阈值为空的情况由 #stepIntoAssessedScore 拦截
        assessedValueMap.put(KEY_THRESHOLD, upperThreshold);
        resultNode.setAssessedValueMap(assessedValueMap);
    }

    private Boolean validBefore(BenefitInternalConstructionDO internalDO){
        if (calculatorEnum.name().contains("SPECIAL")){
            return true;
        }

        // 公式(1)：安全类设备费（元）作为分母，不可为0/空值
        Double securityDeviceFee = Optional.ofNullable(internalDO.getSecurityDeviceFee()).orElse(0.0);
        if (securityDeviceFee <= 0) {
            return false;
        }

        // 公式(3)：该能力新增指标总量作为分母，不可为0/空值
        if (INVISIBLE_UNIT_COST.equals(calculatorEnum.getAssessment().getInvisibleParent())){
            BenefitColumnTemplate columnTemplate = calculatorEnum.getColumnTemplateExtractor().apply(internalDO);
            Double increment = Optional.ofNullable(columnTemplate).map(BenefitColumnTemplate::getIncrement).orElse(0.0);
            return increment > 0;
        }

        return true;
    }
    
}
