
package com.sama.analytic.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.support.Pagination;
import com.core4ct.utils.PageUtils;
import com.sama.analytic.mapper.BenefitExternalEmpowermentMapper;
import com.sama.analytic.service.BenefitExternalEmpowermentService;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 效益对外赋能
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:38
 */
@Service
public class BenefitExternalEmpowermentServiceImpl extends BaseServiceImpl<BenefitExternalEmpowermentDO, BenefitExternalEmpowermentMapper> implements BenefitExternalEmpowermentService {

    private final static String HEADER = "【对外赋能数据】 ";

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
    public Pagination<BenefitExternalEmpowermentDO> conditionalPageImplicitTurnover(BenefitExternalEmpowermentDO queryDO) {
        logger.info(HEADER + "条件查询分页 queryDO: {}", JSON.toJSONString(queryDO));
        if (queryDO.getCurrent() == 1){
            turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.HARD);
        }
        Pagination<BenefitExternalEmpowermentDO> targetPagination = new Pagination<>(queryDO.getCurrent(), queryDO.getSize());
        Page<BenefitExternalEmpowermentDO> page = PageUtils.getPage(targetPagination);
        List<BenefitExternalEmpowermentDO> res = this.mapper.selectConditionalPage(queryDO, page);
        targetPagination.setRecords(res);
        targetPagination.setTotal(page.getTotal());
        targetPagination.setOrderByField(queryDO.getOrderBy());
        logger.info(HEADER + "条件查询分页返回 Pagination: {}", JSON.toJSONString(targetPagination));
        return targetPagination;
    }

}
