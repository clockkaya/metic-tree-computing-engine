package com.sama.api.ledger.bean.indicator;

import com.sama.api.ledger.bean.structure.ThresholdPair;
import com.sama.api.ledger.bean.structure.CategorizedThresholdPair;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author: huxh
 * @description: 我觉得这里是比较混乱的，全局一共出现了三处相似概念：表格"省公司"字段、（平台登录用户的）组织编码 orgCode/orgCn、项目部署对应 nacos配置中的 "init.env"
 * @datetime: 2025/10/22 14:50
 */
public enum MetricBenefitProvinceCategoryEnum {

    BEIJING("北京", MetricBenefitConstants.LARGE, "", CategorizedThresholdPair::getLarge),
    TIANJIN("天津", MetricBenefitConstants.SMALL, "tj", CategorizedThresholdPair::getSmall),
    HEBEI("河北", MetricBenefitConstants.MEDIUM, "heb", CategorizedThresholdPair::getMedium),
    SHANXI("山西", MetricBenefitConstants.SMALL, "sx", CategorizedThresholdPair::getSmall),
    NEIMENGGU("内蒙古", MetricBenefitConstants.SMALL, "", CategorizedThresholdPair::getSmall),
    LIAONING("辽宁", MetricBenefitConstants.SMALL, "ln", CategorizedThresholdPair::getSmall),
    JILIN("吉林", MetricBenefitConstants.SMALL, "", CategorizedThresholdPair::getSmall),
    HEILONGJIANG("黑龙江", MetricBenefitConstants.SMALL, "", CategorizedThresholdPair::getSmall),
    SHANGHAI("上海", MetricBenefitConstants.LARGE, "", CategorizedThresholdPair::getLarge),
    JIANGSU("江苏", MetricBenefitConstants.LARGE, "js", CategorizedThresholdPair::getLarge),
    ZHEJIANG("浙江", MetricBenefitConstants.LARGE, "zj", CategorizedThresholdPair::getLarge),
    ANHUI("安徽", MetricBenefitConstants.MEDIUM, "ah", CategorizedThresholdPair::getMedium),
    FUJIAN("福建", MetricBenefitConstants.MEDIUM, "fj", CategorizedThresholdPair::getMedium),
    JIANGXI("江西", MetricBenefitConstants.LARGE, "jx", CategorizedThresholdPair::getLarge),
    SHANDONG("山东", MetricBenefitConstants.SMALL, "sd", CategorizedThresholdPair::getSmall),
    HENAN("河南", MetricBenefitConstants.SMALL, "hen", CategorizedThresholdPair::getSmall),
    HUBEI("湖北", MetricBenefitConstants.MEDIUM, "hb", CategorizedThresholdPair::getMedium),
    HUNAN("湖南", MetricBenefitConstants.LARGE, "hn", CategorizedThresholdPair::getLarge),
    GUANGDONG("广东", MetricBenefitConstants.LARGE, "gd", CategorizedThresholdPair::getLarge),
    GUANGXI("广西", MetricBenefitConstants.MEDIUM, "", CategorizedThresholdPair::getMedium),
    HAINAN("海南", MetricBenefitConstants.SMALL, "hi", CategorizedThresholdPair::getSmall),
    CHONGQING("重庆", MetricBenefitConstants.SMALL, "cq", CategorizedThresholdPair::getSmall),
    SICHUAN("四川", MetricBenefitConstants.LARGE, "", CategorizedThresholdPair::getLarge),
    GUIZHOU("贵州", MetricBenefitConstants.SMALL, "gz", CategorizedThresholdPair::getSmall),
    YUNNAN("云南", MetricBenefitConstants.MEDIUM, "yn", CategorizedThresholdPair::getMedium),
    XIZANG("西藏", MetricBenefitConstants.SMALL, "xz", CategorizedThresholdPair::getSmall),
    SHANXI_SHAANXI("陕西", MetricBenefitConstants.MEDIUM, "sn", CategorizedThresholdPair::getMedium),
    GANSU("甘肃", MetricBenefitConstants.SMALL, "gs", CategorizedThresholdPair::getSmall),
    QINGHAI("青海", MetricBenefitConstants.SMALL, "", CategorizedThresholdPair::getSmall),
    NINGXIA("宁夏", MetricBenefitConstants.SMALL, "nx", CategorizedThresholdPair::getSmall),
    XINJIANG("新疆", MetricBenefitConstants.SMALL, "", CategorizedThresholdPair::getSmall),
    // 特殊项用于测试
    UNDEFINED("未定义", MetricBenefitConstants.UNKNOWN, "", CategorizedThresholdPair::getFull)
    ;

    /**
     * 省公司
     */
    private final String provincialCompany;

    /**
     * 类别
     */
    private final String category;

    /**
     * 项目部署
     */
    private final String deployment;

    /**
     * CategorizedThresholdPair -> 特定 ThresholdPair all getter
     */
    private final Function<CategorizedThresholdPair, ThresholdPair> categorizedExtractor;

    MetricBenefitProvinceCategoryEnum(String provincialCompany, String category, String deployment, Function<CategorizedThresholdPair, ThresholdPair> categorizedExtractor) {
        this.provincialCompany = provincialCompany;
        this.category = category;
        this.deployment = deployment;
        this.categorizedExtractor = categorizedExtractor;
    }

    public String getProvincialCompany() {
        return provincialCompany;
    }

    public String getCategory() {
        return category;
    }

    public String getDeployment() {
        return deployment;
    }

    public Function<CategorizedThresholdPair, ThresholdPair> getCategorizedExtractor() {
        return categorizedExtractor;
    }

    @Override
    public String toString() {
        return "MetricBenefitProvinceCategoryEnum{" +
            "provincialCompany='" + provincialCompany + '\'' +
            ", category='" + category + '\'' +
            ", deployment='" + deployment + '\'' +
            ", categorizedExtractor=" + categorizedExtractor +
            "} " + super.toString();
    }

    /**
     *  通过“fieldValue 是否包含 keyExtractor 常量”的逻辑，获取匹配的 Enum
     *
     * @param fieldExtractor    如 MetricBenefitProvinceCategoryEnum::getProvincialCompany
     * @param fieldValue        如 "北京市"，为 fieldExtractor 真实取值
     * @return                  Category
     */
    private static MetricBenefitProvinceCategoryEnum tellCategoryByAmbiguous(Function<MetricBenefitProvinceCategoryEnum, String> fieldExtractor, String fieldValue) {
        return Arrays.stream(values())
            .filter(value -> fieldValue.contains(fieldExtractor.apply(value)))
            .findFirst()
            .orElse(MetricBenefitProvinceCategoryEnum.UNDEFINED);
    }
    
    public static MetricBenefitProvinceCategoryEnum tellEnumByAmbiguousOrgCn(String orgCn) {
        return tellCategoryByAmbiguous(MetricBenefitProvinceCategoryEnum::getProvincialCompany, orgCn);
    }

    public static Function<CategorizedThresholdPair, ThresholdPair> tellExtractorByAmbiguousOrgCn(String orgCn) {
        return tellEnumByAmbiguousOrgCn(orgCn).getCategorizedExtractor();
    }
    
    public static String tellCategoryByAmbiguousOrgCn(String orgCn) {
        return tellEnumByAmbiguousOrgCn(orgCn).getCategory();
    }

}