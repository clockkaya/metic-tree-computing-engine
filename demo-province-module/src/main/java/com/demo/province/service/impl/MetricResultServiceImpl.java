package com.sama.ledger.service.impl;

import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.exception.GenericException;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.bean.enums.MetricTypeEnum;
import com.sama.api.ledger.bean.structure.MetricResultNode;
import com.sama.ledger.mapper.MetricResultMapper;
import com.sama.ledger.service.MetricResultService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * 指标结果
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:42
 */
@Service
public class MetricResultServiceImpl extends BaseServiceImpl<MetricResultDO, MetricResultMapper> implements MetricResultService {

    private static final Logger logger = LogManager.getLogger(MetricResultServiceImpl.class);

    @Override
    public MetricResultDO getLatestRecord(String orgCode, int metricType) {
        return mapper.selectLatestRecordByOrgCodeAndMetricType(orgCode, metricType);
    }

    @Override
    public MetricResultDO getLatestRecordWithValidation(String orgCode, int metricType) {
        // 近似 #reloadTreeFromDB
        MetricResultDO resultDO = mapper.selectLatestRecordByOrgCodeAndMetricType(orgCode, metricType);
        if (DataUtils.isEmpty(resultDO)){
            throw new GenericException( MessageFormat.format("根据 orgCode:{0}, metricType:{1}，无法从数据库取到最新一条结果记录，请排查！",
                    orgCode, metricType
            ));
        }
        MetricResultNode treeNode = resultDO.getRootNode();
        if (DataUtils.isEmpty(treeNode)){
            throw new GenericException( MessageFormat.format("根据 orgCode:{0}, metricType:{1}，无法解析出有效的结果根节点，请排查！",
                    orgCode, metricType
            ));
        }

        logger.info("【{}】 获取最新结果记录并解析成功（id = {}）", MetricTypeEnum.getMetricNameByType(resultDO.getMetricType()), resultDO.getId());

        return resultDO;
    }

    @Override
    public List<MetricResultDO> getAlignedRecords(Integer metricType, Date alignDay) {
        // TODO
        return List.of();
    }

    @Override
    public List<String> selectAllProvinceCodes(List<Integer> metricTypeList) {
        return mapper.selectAllProvinceCodes(metricTypeList);
    }

    @Override
    public Date getMaxDataRefTime(String orgCode, List<Integer> metricTypeList) {
        return mapper.getMaxDataRefTime(orgCode, metricTypeList);
    }

    @Override
    public Date getMaxCreateTime(String orgCode, List<Integer> metricTypeList) {
        return mapper.getMaxCreateTime(orgCode, metricTypeList);
    }

}
