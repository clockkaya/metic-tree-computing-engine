package com.sama.ledger.metric;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.core4ct.constants.Constants;
import com.core4ct.exception.BusinessException;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.bo.ComprehensiveProtectionPreparedDataBO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionL2CalculatorEnum;
import com.sama.api.ledger.bean.indicator.MetricComprehensiveProtectionL3ProcessingDataEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.metric.handlers.ComprehensiveProtectionHandler;
import com.sama.ledger.service.ComprehensiveProtectionService;
import com.sama.ledger.service.MetricConfigService;
import com.sama.ledger.service.MetricResultService;
import com.sama.ledger.utils.GroceryUtils;
import com.sama.ledger.utils.MetricResultKit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;

/**
 * 综合防护引擎
 * @author: huxh
 * @description:
 * @datetime: 2025/7/25 14:03
 */
@Service
public class ComprehensiveProtectionEngineServiceImpl implements MetricEngineService<ComprehensiveProtectionPreparedDataBO> {

    private static final Logger logger = LogManager.getLogger(ComprehensiveProtectionEngineServiceImpl.class);

    private static final MetricTypeEnum METRIC = MetricTypeEnum.COMPREHENSIVE_PROTECTION;

    private final ConcurrentHashMap<String, ReentrantLock> orgInstantLocks = new ConcurrentHashMap<>();

    @Resource(name = "comprehensiveProtectionEngine")
    ThreadPoolTaskExecutor comprehensiveProtectionEngine;

    @Resource
    ComprehensiveProtectionService comprehensiveProtectionService;

    @Resource
    ComprehensiveProtectionHandler comprehensiveProtectionHandler;

    @Resource
    MetricConfigService metricConfigService;

    @Resource
    MetricResultService metricResultService;

    @Override
    public List<MetricResultDO> topProcess(Integer updateMode) {
        // 1 根据原表获取 allOrgCodes
        List<String> allOrgCodes = comprehensiveProtectionService.selectAllOrgCodes();
        if (DataUtils.isEmpty(allOrgCodes)){
            logger.warn("【{}】 无任一组织上传数据(表 comprehensive_protection)，跳过此次处理！", METRIC.getName());
            return Collections.emptyList();
        }

        // 2 加载同一份配置树
        Date comingConfigRefTime = metricConfigService.reloadTreeFromDB(METRIC.getType());

        // 3 对 allOrgCodes 多线程执行
        List<CompletableFuture<MetricResultDO>> futures = allOrgCodes.stream()
            .map(orgCode -> CompletableFuture.supplyAsync(() -> {
                logger.info("【{}】 进入顶层处理 threadName: {}, orgCode: {}", METRIC.getName(), Thread.currentThread().getName(), orgCode);
                // 独立的对象，避免多线程上的数据覆盖问题
                ComprehensiveProtectionPreparedDataBO initialData = new ComprehensiveProtectionPreparedDataBO();
                initialData.setMetricType(METRIC.getType());
                initialData.setConfigRefTime(comingConfigRefTime);
                initialData.setOrgCode(orgCode);
                initialData.setUpdateMode(updateMode);
                ComprehensiveProtectionPreparedDataBO preparedData = etlIntoPreparedData(initialData);
                return computeAndSaveResult(preparedData);
            }, comprehensiveProtectionEngine))
            .toList();

        // 4 所有结果归集
        List<MetricResultDO> results = MetricEngineService.collectFutureResults(futures);
        logger.info("【{}】 完成顶层处理，返回结果共 {} 条", METRIC.getName(), results.size());

        return results;
    }

    @Override
    public void asyncInstantScore(String orgCode) {
        ReentrantLock lock = orgInstantLocks.computeIfAbsent(orgCode, k -> new ReentrantLock());
        comprehensiveProtectionEngine.execute(() -> {
            if (!lock.tryLock()) {
                logger.warn("【{}】 orgCode:{} 正在即时计分中，跳过此次处理！", METRIC.getName(), orgCode);
                return;
            }
            try {
                blockInstantScore(orgCode);
            } catch (Exception e) {
                // 不要反复打印
            } finally {
                // 确保在同一个子线程释放锁
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
            ComprehensiveProtectionPreparedDataBO initialData = new ComprehensiveProtectionPreparedDataBO();
            initialData.setMetricType(METRIC.getType());
            initialData.setConfigRefTime(comingConfigRefTime);
            initialData.setOrgCode(orgCode);
            initialData.setUpdateMode(MetricConstants.UpdateMode.FORCE);
            ComprehensiveProtectionPreparedDataBO preparedData = etlIntoPreparedData(initialData);
            MetricResultDO res = computeAndSaveResult(preparedData);
            logger.info("【{}】 完成即时计分，返回 metric_config.id:{}", METRIC.getName(), res.getId());
        } catch (Exception e) {
            logger.error("捕获小异常一只，堆栈信息如下: ", e);
            throw new BusinessException("综合防护计分失败，请排查！" + e.getMessage());
        }
    }

    @Override
    public ComprehensiveProtectionPreparedDataBO etlIntoPreparedData(ComprehensiveProtectionPreparedDataBO initialData) {
        // 1 extract
        String orgCode = initialData.getOrgCode();
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setOrgCode(orgCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ComprehensiveProtectionExtendedDO> horizontalData = comprehensiveProtectionService.queryList(queryDO);
        if (DataUtils.isEmpty(horizontalData)){
            throw new BusinessException(MessageFormat.format("综合防护(orgCode:{0})无数据，请上传！", orgCode));
        }
        Date dataRefTime = horizontalData.stream().map(ComprehensiveProtectionExtendedDO::getUpdateTime).max(Date::compareTo).orElse(null);

        // 2 transform
        ComprehensiveProtectionPreparedDataBO preparedData = new ComprehensiveProtectionPreparedDataBO();
        BeanUtils.copyProperties(initialData, preparedData);
        horizontalData.forEach(horizon -> {
            // 定位 L3ProcessingDataEnum
            String comprehensiveScenarioType = horizon.getComprehensiveScenarioType();
            String evaluationItem = horizon.getEvaluationItem();
            String processingDataCn = horizon.getProcessingDataCn();
            MetricComprehensiveProtectionL3ProcessingDataEnum processingDataEnum =
                    MetricComprehensiveProtectionL3ProcessingDataEnum.findByScenarioCalculatorAndProcessingDataCn(comprehensiveScenarioType, evaluationItem, processingDataCn);
            if (DataUtils.isEmpty(processingDataEnum)){
                logger.error("【{}】 根据 综合场景类型({}) + 评估项({}) + 过程数据({}) 定位 L3ProcessingDataEnum 失败，请检查对应关系！",
                    METRIC.getName(), comprehensiveScenarioType, evaluationItem, processingDataCn);
                return;
            }

            // 映射 PreparedDataBO 特定列
            BiConsumer<ComprehensiveProtectionPreparedDataBO, Integer> dataSetter = processingDataEnum.getDataSetter();
            // 赋值
            dataSetter.accept(preparedData, horizon.getProcessingData());

            // 算前更新 * 计算方法
            // horizon.setCalculationMethod(processingDataEnum.getCalculator().getCalculatorComputingMethod());
        });
        // comprehensiveProtectionService.updateBatch(horizontalData);

        // 3 load
        preparedData.setHorizontalData(horizontalData);
        preparedData.setDataRefTime(dataRefTime);
        logger.info("【{}】 完成准备算前数据！", METRIC.getName());

        return preparedData;
    }

    @Override
    public void validInitialData(ComprehensiveProtectionPreparedDataBO initialData) {
        // 暂无
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MetricResultDO computeAndSaveResult(ComprehensiveProtectionPreparedDataBO preparedData) {
        // 前置判断
        MetricResultDO latestResult = metricResultService.getLatestRecord(preparedData.getOrgCode(), METRIC.getType());
        if (!MetricEngineService.decideNeedUpdate(preparedData, latestResult)){
            return latestResult;
        }

        // 2 计算
        MetricResultNode rootNode = comprehensiveProtectionHandler.recursiveHandle(preparedData);

        // 3 持久化
        MetricResultDO newResult = new MetricResultDO();
        newResult.setUid(GroceryUtils.generateUid());
        newResult.setOrgCode(preparedData.getOrgCode());
        newResult.setMetricType(METRIC.getType());
        newResult.setResultTree(JSON.toJSONString(rootNode, JSONWriter.Feature.PrettyFormat));
        newResult.setConfigRefTime(preparedData.getConfigRefTime());
        newResult.setDataRefTime(preparedData.getDataRefTime());
        MetricResultDO thisResult = metricResultService.add(newResult);
        logger.info("【{}】 计入数据库信息成功，并返回 MetricResultDO: {}", METRIC.getName(), JSON.toJSONString(thisResult));

        // 4 算后更新 * 评估值
        List<ComprehensiveProtectionExtendedDO> horizontalData = preparedData.getHorizontalData();
        if (DataUtils.isEmpty(horizontalData)){
            // 防止单测或脏数据覆盖
            return thisResult;
        }

        horizontalData.forEach(horizon -> {
            // 定位 L2CalculatorEnum
            String comprehensiveScenarioType = horizon.getComprehensiveScenarioType();
            String evaluationItem = horizon.getEvaluationItem();
            MetricComprehensiveProtectionL2CalculatorEnum calculatorEnum =
                    MetricComprehensiveProtectionL2CalculatorEnum.findByScenarioCnLikeAndCalculatorCn(comprehensiveScenarioType, evaluationItem);
            if (DataUtils.isEmpty(calculatorEnum)){
                logger.error("【{}】 根据 综合场景类型({}) + 评估项({}) 定位 L2CalculatorEnum 失败，请检查对应关系！",
                    METRIC.getName(), comprehensiveScenarioType, evaluationItem);
                return;
            }

            // 定位 MetricResultNode
            MetricResultNode calculatorNode = MetricResultKit.findNode(rootNode, calculatorEnum.getCalculatorEn());
            // 赋值
            horizon.setAssessedValue((BigDecimal) calculatorNode.extractAssessedValue());
        });
        // 保持 createTime, updateTime 不变
        comprehensiveProtectionService.updateBatch(horizontalData);
        logger.info("【{}】 算后更新 * 评估值成功！", METRIC.getName());

        return thisResult;
    }

}
