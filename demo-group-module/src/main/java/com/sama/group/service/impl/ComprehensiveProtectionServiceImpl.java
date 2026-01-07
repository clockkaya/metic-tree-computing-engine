package com.sama.analytic.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.support.Pagination;
import com.core4ct.utils.PageUtils;
import com.sama.analytic.mapper.ComprehensiveProtectionMapper;
import com.sama.analytic.service.ComprehensiveProtectionService;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.bean.indicator.MetricConstants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 综合防护
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:42
 */
@Service
public class ComprehensiveProtectionServiceImpl extends BaseServiceImpl<ComprehensiveProtectionExtendedDO, ComprehensiveProtectionMapper> implements ComprehensiveProtectionService {

    private final static String HEADER = "【综合防护】 ";

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
    public Pagination<ComprehensiveProtectionExtendedDO> conditionalPageImplicitTurnover(ComprehensiveProtectionExtendedDO queryDO) {
        logger.info(HEADER + "条件查询分页 queryDO: {}", JSON.toJSONString(queryDO));
        if (queryDO.getCurrent() == 1){
            // 默认首页时才翻新，既防止数据不一致也减少冗余操作（每次查询翻新已属下策）
            turnoverUntilLatestByOrgCode(MetricConstants.TurnoverMode.HARD);
        }
        Pagination<ComprehensiveProtectionExtendedDO> targetPagination = new Pagination<>(queryDO.getCurrent(), queryDO.getSize());
        Page<ComprehensiveProtectionExtendedDO> page = PageUtils.getPage(targetPagination);
        List<ComprehensiveProtectionExtendedDO> res = this.mapper.selectConditionalPage(queryDO, page);
        targetPagination.setRecords(res);
        targetPagination.setTotal(page.getTotal());
        targetPagination.setOrderByField(queryDO.getOrderBy());
        logger.info(HEADER + "条件查询分页返回 Pagination: {}", JSON.toJSONString(targetPagination));
        return targetPagination;
    }

}
