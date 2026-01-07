package com.sama.analytic.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.support.Pagination;
import com.core4ct.utils.PageUtils;
import com.sama.analytic.mapper.BenefitInternalConstructionMapper;
import com.sama.analytic.service.BenefitInternalConstructionService;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 效益对内建设
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:38
 */
@Service
public class BenefitInternalConstructionServiceImpl extends BaseServiceImpl<BenefitInternalConstructionDO, BenefitInternalConstructionMapper> implements BenefitInternalConstructionService {

    private final static String HEADER = "【对内建设数据】 ";

    @Override
    public void turnoverUntilLatestByOrgCode(Integer turnoverMode) {
        if (MetricConstants.TurnoverMode.HARD.equals(turnoverMode)) {
            int count = this.mapper.deleteNonLatestByOrgCode();
            logger.info("硬删除表共 {} 条", count);
        } else if (MetricConstants.TurnoverMode.SOFT.equals(turnoverMode)) {
            int count = this.mapper.updateNonLatestAsDeleted();
            logger.info("软删除表共 {} 条", count);
        }
    }

    @Override
    public Pagination<BenefitInternalConstructionDO> conditionalPageImplicitTurnover(BenefitInternalConstructionDO queryDO) {
        logger.info(HEADER + "条件查询分页 queryDO: {}", JSON.toJSONString(queryDO));
        if (queryDO.getCurrent() == 1){
            turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.HARD);
        }
        Pagination<BenefitInternalConstructionDO> targetPagination = new Pagination<>(queryDO.getCurrent(), queryDO.getSize());
        Page<BenefitInternalConstructionDO> page = PageUtils.getPage(targetPagination);
        List<BenefitInternalConstructionDO> res = this.mapper.selectConditionalPage(queryDO, page);
        targetPagination.setRecords(res);
        targetPagination.setTotal(page.getTotal());
        targetPagination.setOrderByField(queryDO.getOrderBy());
        logger.info(HEADER + "条件查询分页返回 Pagination: {}", JSON.toJSONString(targetPagination));
        return targetPagination;
    }

}
