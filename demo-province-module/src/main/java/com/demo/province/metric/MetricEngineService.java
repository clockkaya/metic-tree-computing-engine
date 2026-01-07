package com.sama.ledger.metric;

import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.bo.PreparedDataModel;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author: huxh
 * @description: 标准化指标计算引擎
 * @datetime: 2025/6/30 8:54
 */
public interface MetricEngineService<T extends PreparedDataModel>{

    Logger logger = LogManager.getLogger(MetricEngineService.class);

    /**
     * 顶层处理
     *
     * @param updateMode    更新模式：0-强制更新，1-条件更新；建议低频0，高频1
     */
    List<MetricResultDO> topProcess(Integer updateMode);

    /**
     * （异步）即时计分
     *
     * @param orgCode   待获取组织code
     */
    void asyncInstantScore(String orgCode);

    /**
     * （阻塞）即时计分
     *
     * @param orgCode   待获取组织code
     */
    void blockInstantScore(String orgCode);

    /**
     * 准备算前数据
     *
     * @param initialData   仅对 orgCode 赋值的 new PreparedDataModel
     * @return              算前数据
     */
    T etlIntoPreparedData(T initialData);

    /**
     * 准备（包含内调用#etlIntoPreparedData）并校验算前数据
     *
     * @param initialData   仅对 orgCode 赋值的 new PreparedDataModel
     */
    void validInitialData(T initialData);

    /**
     * 计算并保存结果数据
     *
     * @param preparedData  算前数据
     */
    MetricResultDO computeAndSaveResult(T preparedData);

    /**
     * 收集异步任务的返回结果
     *
     * @param futures   包含异步任务结果的 CompletableFuture 列表
     * @return          收集到的所有任务结果列表，如果发生异常则返回空列表
     */
    static List<MetricResultDO> collectFutureResults(List<CompletableFuture<MetricResultDO>> futures){
        // CompletableFuture<Void> waitTask = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        // waitTask.join();
        return futures.stream()
            .map(future -> {
                try {
                    // 获取单个任务的结果
                    return future.join();
                } catch (Exception e) {
                    logger.error("单个任务执行失败: ", e);
                    // 失败的任务返回null
                    return null;
                }
            })
            // 过滤掉失败的任务
            .filter(Objects::nonNull)
            .toList();
    }

    /**
     * 根据 updateMode, configRefTime, dataRefTime 等多条件，判断当前指标是否需要重新计算
     * Attention!在 #submitAndInstantScore 下将失去意义
     *
     * @param preparedData  PreparedDataModel
     * @param latestResult  上次结果
     * @return              Boolean needUpdate
     */
    static Boolean decideNeedUpdate(PreparedDataModel preparedData, MetricResultDO latestResult) {
        String metricName = MetricTypeEnum.getMetricNameByType(preparedData.getMetricType());
        if (preparedData.getUpdateMode() == null || preparedData.getUpdateMode().equals(MetricConstants.UpdateMode.FORCE)) {
            logger.info("【{}】 强制更新模式，直接进入计算！", metricName);
            return Boolean.TRUE;
        }

        Date comingConfigRefTime = preparedData.getConfigRefTime();
        Date comingDataRefTime = preparedData.getDataRefTime();

        if (DataUtils.isEmpty(latestResult) || DataUtils.isEmpty(comingConfigRefTime) || DataUtils.isEmpty(comingDataRefTime)) {
            logger.info("【{}】 无法判断配置、原始数据是否更新，直接进入计算！", metricName);
            return Boolean.TRUE;
        }

        final boolean configUnchanged = latestResult.getConfigRefTime().getTime() == comingConfigRefTime.getTime();
        final boolean dataUnchanged = latestResult.getDataRefTime().getTime() == comingDataRefTime.getTime();

        if (configUnchanged && dataUnchanged) {
            logger.info("【{}】 配置、原始数据均无更新，无需重新计算！", metricName);
            return Boolean.FALSE;
        } else {
            logger.info("【{}】 配置、原始数据有更新，重新计算！", metricName);
            return Boolean.TRUE;
        }
    }

}
