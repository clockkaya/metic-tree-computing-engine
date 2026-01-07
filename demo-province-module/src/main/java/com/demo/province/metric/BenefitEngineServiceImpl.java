package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.core4ct.constants.Constants;
import com.core4ct.exception.BadRequestException;
import com.core4ct.exception.BusinessException;
import com.core4ct.utils.DataUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.GroupStatisticBakDO;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.bo.BenefitPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricBenefitProvinceCategoryEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.BenefitThresholdMap;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.metric.handlers.BenefitHandler;
import com.sama.ledger.service.*;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.MetricMockDataUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * 效益引擎
 * @author: huxh
 * @description:
 * 1. 效益原表太大太复杂，不用 PreparedDataBO 包装而直接用原表；
 * 2. 涉及同步至集团侧再计算，需做两步定时处理(DEV & RELEASE)，并相应修改原hash比较逻辑；
 * @datetime: 2025/7/3 13:31
 */
@Service
public class BenefitEngineServiceImpl implements MetricEngineService<BenefitPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(BenefitEngineServiceImpl.class);

    private static final MetricTypeEnum METRIC_DEV = MetricTypeEnum.BENEFIT_DEV;
    private static final MetricTypeEnum METRIC_RELEASE = MetricTypeEnum.BENEFIT_RELEASE;

    @Resource(name = "benefitEngine")
    ThreadPoolTaskExecutor benefitEngine;

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    @Resource
    BenefitInternalConstructionService benefitInternalConstructionService;

    @Resource
    BenefitExternalEmpowermentService benefitExternalEmpowermentService;

    @Resource
    BenefitHandler benefitHandler;

    @Resource
    MetricConfigService metricConfigService;

    @Resource
    MetricResultService metricResultService;

    @Resource
    GroupStatisticBakService groupStatisticBakService;

    @Override
    public List<MetricResultDO> topProcess(Integer updateMode) {
        throw new BadRequestException("请使用正确的接口 #topProcessDev/#topProcessRelease ！");
    }

    @Override
    public void asyncInstantScore(String orgCode) {
        blockInstantScore(orgCode);
    }

    @Override
    public void blockInstantScore(String orgCode) {
        throw new BadRequestException("效益不支持即时计分，请等待！");
    }

    public List<MetricResultDO> topProcessDev(Integer updateMode){
        return universalProcess(METRIC_DEV, updateMode);
    }

    public List<MetricResultDO> topProcessRelease(Integer updateMode){
        return universalProcess(METRIC_RELEASE, updateMode);
    }

    private List<MetricResultDO> universalProcess(MetricTypeEnum metric, Integer updateMode){
        // 1 根据单表获取 allOrgCodes
        List<String> allOrgCodes = benefitInternalConstructionService.selectAllOrgCodes();
        if (DataUtils.isEmpty(allOrgCodes)){
            logger.warn("【{}】 无任一组织上传数据(表 benefit_internal_construction)，跳过此次处理！", metric.getName());
            return Collections.emptyList();
        }

        // 2 加载同一份配置树
        Date comingConfigRefTime = metricConfigService.reloadTreeFromDB(metric.getType());

        // 3 对 allOrgCodes 多线程执行
        List<CompletableFuture<MetricResultDO>> futures = allOrgCodes.stream()
            .map(orgCode -> CompletableFuture.supplyAsync(() -> {
                logger.info("【{}】 进入顶层处理 threadName: {}, orgCode: {}", metric.getName(), Thread.currentThread().getName(), orgCode);
                BenefitPreparedDataBO initialData = new BenefitPreparedDataBO();
                initialData.setMetricType(metric.getType());
                initialData.setConfigRefTime(comingConfigRefTime);
                initialData.setOrgCode(orgCode);
                initialData.setUpdateMode(updateMode);
                BenefitPreparedDataBO preparedData = etlIntoPreparedData(initialData);
                return computeAndSaveResult(preparedData);
            }, benefitEngine))
            .toList();

        // 4 所有结果归集
        List<MetricResultDO> results = MetricEngineService.collectFutureResults(futures);
        logger.info("【{}】 完成顶层处理，返回结果共 {} 条", metric.getName(), results.size());

        return results;
    }

    @Override
    public BenefitPreparedDataBO etlIntoPreparedData(BenefitPreparedDataBO initialData) {
        // 1 extract
        String orgCode = initialData.getOrgCode();
        // 1.1 效益对内建设原表
        BenefitInternalConstructionDO internalQuery = new BenefitInternalConstructionDO();
        internalQuery.setOrgCode(orgCode);
        internalQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BenefitInternalConstructionDO> internalSource = benefitInternalConstructionService.queryList(internalQuery);
        if (DataUtils.isEmpty(internalSource)){
            throw new BusinessException(MessageFormat.format("效益对内建设原表(orgCode:{0})无数据，请上传！", orgCode));
        }
        Date internalRefTime = internalSource.stream().map(BenefitInternalConstructionDO::getUpdateTime).max(Date::compareTo).orElse(null);

        // 1.2 效益对外赋能原表
        BenefitExternalEmpowermentDO externalQuery = new BenefitExternalEmpowermentDO();
        externalQuery.setOrgCode(orgCode);
        externalQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BenefitExternalEmpowermentDO> externalSource = benefitExternalEmpowermentService.queryList(externalQuery);
        if (DataUtils.isEmpty(externalSource)){
            throw new BusinessException(MessageFormat.format("效益对外赋能原表(orgCode:{0})无数据，请上传！", orgCode));
        }
        Date externalRefTime = externalSource.stream().map(BenefitExternalEmpowermentDO::getUpdateTime).max(Date::compareTo).orElse(null);

        // 1.3 效益类间阈值 Map（集团侧）
        BenefitThresholdMap benefitThresholdMap = new BenefitThresholdMap();
        if (initialData.getMetricType() == METRIC_RELEASE.getType()) {
            GroupStatisticBakDO groupStatisticBakDO = groupStatisticBakService.getAlignedRecord(MetricConstants.DimensionKey.THRESHOLD, null);
            if (groupStatisticBakDO == null || groupStatisticBakDO.getRecord() == null) {
                benefitThresholdMap = MetricMockDataUtils.mockBenefitThresholdMap();
                logger.info("【{}】 BenefitThresholdMap 为空，使用模拟数据：{}", METRIC_RELEASE.getName(), JSON.toJSONString(benefitThresholdMap));
            } else {
                benefitThresholdMap = JSON.parseObject(groupStatisticBakDO.getRecord(), BenefitThresholdMap.class);
                logger.info("【{}】 BenefitThresholdMap 可得，使用真实数据：{}", METRIC_RELEASE.getName(), JSON.toJSONString(benefitThresholdMap));
            }
            String orgCn = orgCodeAndNameCache.get(orgCode);
            String category = MetricBenefitProvinceCategoryEnum.tellCategoryByAmbiguousOrgCn(orgCodeAndNameCache.get(orgCode));
            logger.info("【{}】 根据 orgCn({}) 定位 MetricBenefitProvinceCategoryEnum.category: {}", METRIC_RELEASE.getName(), orgCn, category);
        }

        // 2 load
        BenefitPreparedDataBO preparedData = new BenefitPreparedDataBO();
        BeanUtils.copyProperties(initialData, preparedData);
        preparedData.setInternalData(internalSource);
        preparedData.setExternalData(externalSource.getFirst());
        preparedData.setThresholdMap(benefitThresholdMap);
        preparedData.setDataRefTime(Stream.of(internalRefTime, externalRefTime).max(Date::compareTo).orElse(null));
        logger.info("【{}】 完成准备算前数据！", MetricTypeEnum.getMetricNameByType(preparedData.getMetricType()));

        return preparedData;
    }

    @Override
    public void validInitialData(BenefitPreparedDataBO initialData) {
        // 暂无
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MetricResultDO computeAndSaveResult(BenefitPreparedDataBO preparedData) {
        // 前置判断
        Integer metricType = preparedData.getMetricType();
        MetricResultDO latestResult = metricResultService.getLatestRecord(preparedData.getOrgCode(), metricType);
        if (!MetricEngineService.decideNeedUpdate(preparedData, latestResult)){
            return latestResult;
        }

        // 1 计算
        MetricResultNode rootNode =  benefitHandler.recursiveHandle(preparedData);

        // 2 持久化
        MetricResultDO newResult = new MetricResultDO();
        newResult.setUid(GroceryUtils.generateUid());
        newResult.setOrgCode(preparedData.getOrgCode());
        newResult.setMetricType(preparedData.getMetricType());
        newResult.setResultTree(JSON.toJSONString(rootNode, JSONWriter.Feature.PrettyFormat));
        newResult.setConfigRefTime(preparedData.getConfigRefTime());
        newResult.setDataRefTime(preparedData.getDataRefTime());
        MetricResultDO thisResult = metricResultService.add(newResult);
        logger.info("【{}】 计入数据库信息成功，并返回 MetricResultDO: {}",
            MetricTypeEnum.getMetricNameByType(metricType), JSON.toJSONString(thisResult));

        // 3 算后更新对内建设所有Var值
        List<BenefitInternalConstructionDO> internalData = preparedData.getInternalData();
        if (DataUtils.isEmpty(internalData) || metricType != METRIC_DEV.getType()){
            // 避免错误、重复更改
            return thisResult;
        }
        benefitInternalConstructionService.updateBatch(internalData);
        logger.info("【{}】 算后更新对内建设所有Var值成功！", MetricTypeEnum.getMetricNameByType(metricType));

        return thisResult;
    }

}
