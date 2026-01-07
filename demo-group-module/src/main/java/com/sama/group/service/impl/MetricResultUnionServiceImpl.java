package com.sama.analytic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.analytic.mapper.MetricResultUnionMapper;
import com.sama.analytic.service.MetricResultUnionService;
import com.sama.analytic.utils.MetricResultKit;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: huxh
 * @description: 
 * @datetime: 2025/9/9 15:18
 */
@Service
public class MetricResultUnionServiceImpl extends BaseServiceImpl<MetricResultUnionDO, MetricResultUnionMapper> implements MetricResultUnionService {

    @Override
    public void insertBatchEraseId(List<MetricResultUnionDO> dataList) {
        dataList.forEach(metricResultUnionDO -> metricResultUnionDO.setId(null));
        this.mapper.insert(dataList);
    }

    @Override
    public List<MetricResultUnionDO> getAlignedRecords(Integer metricType, Date alignDay) {
        MetricResultUnionDO queryDO = new MetricResultUnionDO();
        queryDO.setMetricType(metricType);
        if (alignDay == null) {
            alignDay = new Date();
        }
        queryDO.setStartTime(DateUtil.beginOfDay(alignDay));
        queryDO.setEndTime(DateUtil.endOfDay(alignDay));
        return this.mapper.selectAlignedRecords(queryDO);
    }

    @Override
    public List<MetricResultUnionDO> getAlignedRecordsWithValidation(Integer metricType, Date alignDay) {
        List<MetricResultUnionDO> res = getAlignedRecords(metricType, alignDay);
        if (DataUtils.isEmpty(res)){
            throw new GenericException( MessageFormat.format("根据 metricType: {0}，无法从数据库取到任何组织最新结果记录，请排查！", metricType));
        }

        logger.info("【{}】 获取最新结果记录共 {} 条，uids = {}",
            MetricTypeEnum.getMetricNameByType(metricType), res.size(), MetricResultKit.extractUids(res));

        return res;
    }
}
