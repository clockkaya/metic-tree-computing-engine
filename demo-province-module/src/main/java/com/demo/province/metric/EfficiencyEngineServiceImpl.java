package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.core4ct.constants.Constants;
import com.core4ct.exception.BusinessException;
import com.core4ct.exception.ValidateException;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.ConstructionDO;
import com.sama.api.ledger.bean.ConstructionManualDO;
import com.sama.api.ledger.bean.EngineerProjectDO;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.bo.EfficiencyManualBO;
import com.sama.api.ledger.bean.bo.EfficiencyPreparedDataBO;
import com.sama.api.ledger.bean.bo.EfficiencyUnifiedBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.metric.handlers.EfficiencyHandler;
import com.sama.ledger.service.*;
import com.sama.ledger.utils.GroceryUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * 效率引擎
 * @author: huxh
 * @description:
 * @datetime: 2025/6/16 16:48
 */
@Service
public class EfficiencyEngineServiceImpl implements MetricEngineService<EfficiencyPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(EfficiencyEngineServiceImpl.class);

    private static final MetricTypeEnum METRIC = MetricTypeEnum.EFFICIENCY;

    private final ConcurrentHashMap<String, ReentrantLock> orgInstantLocks = new ConcurrentHashMap<>();

    @Resource(name = "efficiencyEngine")
    ThreadPoolTaskExecutor efficiencyEngine;

    @Resource
    LedgerEngineerProjectService engineerProjectService;

    @Resource
    LedgerConstructionManualService manualService;

    @Resource
    EfficiencyHandler efficiencyHandler;

    @Resource
    MetricConfigService metricConfigService;

    @Resource
    MetricResultService metricResultService;

    @Deprecated
    @Resource
    LedgerConstructionService constructionService;

    @Override
    public List<MetricResultDO> topProcess(Integer updateMode) {
        // 1 根据单表获取 allOrgCodes
        List<String> allOrgCodes = engineerProjectService.selectAllOrgCodes();
        if (DataUtils.isEmpty(allOrgCodes)){
            logger.warn("【{}】 无任一组织上传数据(表 engineering_project)，跳过此次处理！", METRIC.getName());
            return Collections.emptyList();
        }

        // 2 加载同一份配置树
        Date comingConfigRefTime = metricConfigService.reloadTreeFromDB(METRIC.getType());

        // 3 对 allOrgCodes 多线程执行
        List<CompletableFuture<MetricResultDO>> futures = allOrgCodes.stream()
            .map(orgCode -> CompletableFuture.supplyAsync(() -> {
                logger.info("【{}】 进入顶层处理 threadName: {}, orgCode: {}", METRIC.getName(), Thread.currentThread().getName(), orgCode);
                EfficiencyPreparedDataBO initialData = new EfficiencyPreparedDataBO();
                initialData.setMetricType(METRIC.getType());
                initialData.setConfigRefTime(comingConfigRefTime);
                initialData.setOrgCode(orgCode);
                initialData.setUpdateMode(updateMode);
                EfficiencyPreparedDataBO preparedData = etlIntoPreparedData(initialData);
                // 强校验，如入库前单独调用#validInitialdData 可省略此步
                validPreparedData(preparedData);
                return computeAndSaveResult(preparedData);
            }, efficiencyEngine))
            .toList();

        // 4 所有结果归集
        List<MetricResultDO> results = MetricEngineService.collectFutureResults(futures);
        logger.info("【{}】 完成顶层处理，返回结果共 {} 条", METRIC.getName(), results.size());

        return results;
    }

    @Override
    public void asyncInstantScore(String orgCode) {
        ReentrantLock lock = orgInstantLocks.computeIfAbsent(orgCode, k -> new ReentrantLock());
        efficiencyEngine.execute(() -> {
            if (lock.tryLock()) {
                logger.warn("【{}】 orgCode:{} 正在即时计分中，跳过此次处理！", METRIC.getName(), orgCode);
                return;
            }
            try {
                blockInstantScore(orgCode);
            } catch (Exception e) {
                // ignore
            } finally {
                lock.unlock();
                if (lock.getHoldCount() == 0) {
                    orgInstantLocks.remove(orgCode, lock);
                }
            }
        });
    }

    @Override
    public void blockInstantScore(String orgCode) {
        try {
            logger.info("【{}】 进入即时计分 threadName: {}, orgCode: {}", METRIC.getName(), Thread.currentThread().getName(), orgCode);
            Date comingConfigRefTime = metricConfigService.reloadTreeFromDB(METRIC.getType());
            EfficiencyPreparedDataBO initialData = new EfficiencyPreparedDataBO();
            initialData.setMetricType(METRIC.getType());
            initialData.setConfigRefTime(comingConfigRefTime);
            initialData.setOrgCode(orgCode);
            initialData.setUpdateMode(MetricConstants.UpdateMode.FORCE);
            EfficiencyPreparedDataBO preparedData = etlIntoPreparedData(initialData);
            validPreparedData(preparedData);
            MetricResultDO res = computeAndSaveResult(preparedData);
            logger.info("【{}】 完成即时计分，返回 metric_config.id:{}", METRIC.getName(), res.getId());
        } catch (Exception e) {
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
            throw new BusinessException("效率计分失败，请排查！" + e.getMessage());
        }
    }

    @Override
    public EfficiencyPreparedDataBO etlIntoPreparedData(EfficiencyPreparedDataBO initialData) {
        // 1 extract
        String orgCode = initialData.getOrgCode();
        // 1.1 EngineerProjectDO
        EngineerProjectDO engineerQuery = new EngineerProjectDO();
        engineerQuery.setTenantOrgCode(orgCode);
        engineerQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<EngineerProjectDO> fullSources = engineerProjectService.queryList(engineerQuery);
        if (DataUtils.isEmpty(fullSources)){
            throw new BusinessException(MessageFormat.format("EngineerProjectDO 对应表 (orgCode:{0}) 无数据，请上传！", orgCode));
        }
        // Attention！单行改非全量改
        Date engineerRefTime = fullSources.stream().map(EngineerProjectDO::getUpdateTime).max(Date::compareTo).orElse(null);

        // 1.2 ConstructionManualDO
        ConstructionManualDO manualQuery = new ConstructionManualDO();
        manualQuery.setTenantOrgCode(orgCode);
        manualQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ConstructionManualDO> manualSource = manualService.queryList(manualQuery);
        if (DataUtils.isEmpty(manualSource)){
            throw new BusinessException(MessageFormat.format("ConstructionManualDO 对应表 (orgCode:{0}) 无数据，请上传！", orgCode));
        }
        if (manualSource.size() > 1){
            throw new BusinessException(MessageFormat.format("ConstructionManualDO 对应表 (orgCode:{0}) 应为单条覆盖，请重新确认！", orgCode));
        }
        ConstructionManualDO validManualSource = manualSource.get(0);
        Date manualRefTime = validManualSource.getUpdateTime();

        // 2 transform
        // 2.1
        List<EfficiencyUnifiedBO> inProgress = new ArrayList<>();
        fullSources.forEach(fullSource -> {
            EfficiencyUnifiedBO inProgressTarget = unifiedTransform(fullSource);
            inProgress.add(inProgressTarget);
        });

        // 2.2
        EfficiencyManualBO manual = new EfficiencyManualBO();
        manual.setFixedItem(validManualSource.getFixedItem());
        manual.setThisYearOutPlanProjectNum(validManualSource.getThisYearOutPlanProjectNum());
        manual.setLateBookProjectNum(validManualSource.getLateBookingProjectNum());
        manual.setLatePreTransferProjectNum(validManualSource.getLatePreToFixedProjectNum());
        manual.setLateCloseProjectNum(validManualSource.getLateClosingProjectNum());
        manual.setLongTermDebtProjectNum(validManualSource.getLongTermPendingProjectNum());
        manual.setThisYearTotalInvestment(validManualSource.getThisYearTotalInvestment());

        // 3 load
        EfficiencyPreparedDataBO preparedData = new EfficiencyPreparedDataBO();
        BeanUtils.copyProperties(initialData, preparedData);
        preparedData.setDataRefTime(Stream.of(engineerRefTime, manualRefTime).filter(Objects::nonNull).max(Date::compareTo).orElse(null));
        preparedData.setInProgressData(inProgress);
        preparedData.setManual(manual);
        logger.info("【{}】 完成准备算前数据！", METRIC.getName());

        return preparedData;
    }

    @Override
    public void validInitialData(EfficiencyPreparedDataBO initialData) {
        EfficiencyPreparedDataBO preparedData = etlIntoPreparedData(initialData);
        validPreparedData(preparedData);
    }

    /**
     * 校验算前数据
     *
     * @param preparedData  算前数据
     */
    public void validPreparedData(EfficiencyPreparedDataBO preparedData){
        List<Exception> allExceptions = efficiencyHandler.recursiveValid(preparedData);
        if (!allExceptions.isEmpty()) {
            throw new ValidateException(MessageFormat.format("存在多个校验错误：\n{0}", allExceptions));
        }
    }

    @Override
    public MetricResultDO computeAndSaveResult(EfficiencyPreparedDataBO preparedData) {
        // 前置判断
        MetricResultDO latestResult = metricResultService.getLatestRecord(preparedData.getOrgCode(), METRIC.getType());
        if (!MetricEngineService.decideNeedUpdate(preparedData, latestResult)){
            return latestResult;
        }

        // 1 计算
        MetricResultNode rootNode = efficiencyHandler.recursiveHandle(preparedData);

        // 2 持久化
        MetricResultDO newResult = new MetricResultDO();
        newResult.setUid(GroceryUtils.generateUid());
        newResult.setOrgCode(preparedData.getOrgCode());
        newResult.setMetricType(METRIC.getType());
        newResult.setResultTree(JSON.toJSONString(rootNode, JSONWriter.Feature.PrettyFormat));
        newResult.setConfigRefTime(preparedData.getConfigRefTime());
        newResult.setDataRefTime(preparedData.getDataRefTime());
        MetricResultDO thisResult = metricResultService.add(newResult);
        logger.info("【{}】 计入数据库信息成功，并返回 MetricResultDO: {}", METRIC.getName(), JSON.toJSONString(thisResult));

        return thisResult;
    }

    /**
     * EngineerProjectDO -> EfficiencyUnifiedDO
     * 因为试算表经常混乱引用表（全量/在建表），所以统一处理不单字段区分
     */
    private EfficiencyUnifiedBO unifiedTransform(EngineerProjectDO engineerProjectDO){
        EfficiencyUnifiedBO unifiedDO = new EfficiencyUnifiedBO();
        unifiedDO.setProjectCode(engineerProjectDO.getProjectCode());
        unifiedDO.setProjectYear(engineerProjectDO.getApprovalYear());
        unifiedDO.setProjectType(engineerProjectDO.getProjectType());
        unifiedDO.setConstructionNature(engineerProjectDO.getConstructionNature());
        unifiedDO.setProjectApprovalDate(engineerProjectDO.getApprovalReplyDate());
        unifiedDO.setDesignType(engineerProjectDO.getDesignType());
        unifiedDO.setDesignApprovalDate(engineerProjectDO.getFirstDesignReplyDate());
        unifiedDO.setInspectionType(engineerProjectDO.getAcceptanceType());
        unifiedDO.setFirstInspectionApprovalDate(engineerProjectDO.getPreliminaryAcceptanceReplyDate());
        unifiedDO.setFinalInspectionApprovalDate(engineerProjectDO.getFinalAcceptanceReplyDate());
        unifiedDO.setProjectCloseDate(engineerProjectDO.getProjectCloseDate());
        unifiedDO.setInvestmentPeriod(engineerProjectDO.getInvestmentPeriod());
        unifiedDO.setProjectStatus(engineerProjectDO.getProjectStatus());
        unifiedDO.setYearlyCapitalExpenditure(engineerProjectDO.getYearlyCapitalExpenditure());
        unifiedDO.setSingleItemInvestment(engineerProjectDO.getSingleItemTotalInvestment());
        setStandardPeriods(unifiedDO);
        return unifiedDO;
    }

    /**
     * ConstructionDO -> EfficiencyUnifiedDO
     */
    @Deprecated
    private EfficiencyUnifiedBO unifiedTransform(ConstructionDO constructionDO){
        EfficiencyUnifiedBO unifiedDO = new EfficiencyUnifiedBO();
        unifiedDO.setProjectCode(constructionDO.getProjCode());
        unifiedDO.setProjectYear(constructionDO.getApproveYear());
        unifiedDO.setProjectType(constructionDO.getProjType());
        unifiedDO.setConstructionNature(constructionDO.getConstNature());
        unifiedDO.setProjectApprovalDate(constructionDO.getApproveDate());
        unifiedDO.setDesignType(constructionDO.getDesignType());
        unifiedDO.setDesignApprovalDate(constructionDO.getFirstDesignDate());
        unifiedDO.setInspectionType(constructionDO.getAcceptType());
        unifiedDO.setFirstInspectionApprovalDate(constructionDO.getFirstCheckDate());
        unifiedDO.setFinalInspectionApprovalDate(constructionDO.getFinalCheckDate());
        unifiedDO.setProjectCloseDate(constructionDO.getCloseDate());
        unifiedDO.setInvestmentPeriod(constructionDO.getInvestPeriod());
        unifiedDO.setProjectStatus(constructionDO.getProjStatus());
        unifiedDO.setYearlyCapitalExpenditure(constructionDO.getYearCapitalExpend());
        unifiedDO.setSingleItemInvestment(constructionDO.getSingleTotalInvest());
        setStandardPeriods(unifiedDO);
        return unifiedDO;
    }

    /**
     * 标准工期
     * 项目类型：常规项目，建设性质：新建，交付工期 = 380，关闭工期 = 630
     * 项目类型：常规项目，建设性质：扩容，交付工期 = 380，关闭工期 = 540
     * 项目类型：单纯性购置项目，建设性质：新建/扩容，交付工期 = 410，关闭工期 = 500
     */
    private void setStandardPeriods(EfficiencyUnifiedBO unifiedDO){
        // 占位防止 NPE
        unifiedDO.setStandardDeliveryPeriod(-1);
        unifiedDO.setStandardClosePeriod(-1);
        Optional.ofNullable(unifiedDO.getProjectType()).ifPresent(type -> {
            if ("常规项目".equals(type)) {
                Optional.ofNullable(unifiedDO.getConstructionNature()).ifPresent(nature -> {
                    if ("新建".equals(nature)) {
                        unifiedDO.setStandardDeliveryPeriod(380);
                        unifiedDO.setStandardClosePeriod(630);
                    } else if ("扩容".equals(nature)) {
                        unifiedDO.setStandardDeliveryPeriod(380);
                        unifiedDO.setStandardClosePeriod(540);
                    }
                });
            } else if ("单纯性购置项目".equals(type)) {
                Optional.ofNullable(unifiedDO.getConstructionNature()).ifPresent(nature -> {
                    if ("新建".equals(nature) || "扩容".equals(nature)) {
                        unifiedDO.setStandardDeliveryPeriod(410);
                        unifiedDO.setStandardClosePeriod(500);
                    }
                });
            }
        });
    }

    @Deprecated
    private void etlConstructionDO(String orgCode) {
        // 1.2 ConstructionDO
        ConstructionDO constructionQuery = new ConstructionDO();
        constructionQuery.setTenantOrgCode(orgCode);
        constructionQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ConstructionDO> inProgressSources = constructionService.queryList(constructionQuery);
        if (DataUtils.isEmpty(inProgressSources)){
            throw new BusinessException(MessageFormat.format("ConstructionDO 对应表 (orgCode:{0}) 无数据，请上传！", orgCode));
        }
        Date constructRefTime = inProgressSources.stream().map(ConstructionDO::getUpdateTime).max(Date::compareTo).orElse(null);

        // 2.2
        List<EfficiencyUnifiedBO> inProgress = new ArrayList<>();
        inProgressSources.forEach(inProgressSource -> {
            EfficiencyUnifiedBO inProgressTarget = unifiedTransform(inProgressSource);
            inProgress.add(inProgressTarget);
        });
    }

}
