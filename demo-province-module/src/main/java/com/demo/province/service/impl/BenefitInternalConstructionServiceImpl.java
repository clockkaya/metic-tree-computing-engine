package com.sama.ledger.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.constants.Constants;
import com.core4ct.support.Pagination;
import com.core4ct.utils.DataUtils;
import com.core4ct.utils.PageUtils;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.ledger.mapper.BenefitExternalEmpowermentMapper;
import com.sama.ledger.mapper.BenefitInternalConstructionMapper;
import com.sama.ledger.service.BenefitInternalConstructionService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 效益对内建设
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:38
 */
@Service
public class BenefitInternalConstructionServiceImpl extends BaseServiceImpl<BenefitInternalConstructionDO, BenefitInternalConstructionMapper> implements BenefitInternalConstructionService {

    @Resource(name = "metricSecondaryProcess")
    ThreadPoolTaskExecutor metricSecondaryProcess;

    @Resource
    BenefitExternalEmpowermentMapper benefitExternalEmpowermentMapper;

    @Override
    public List<String> selectAllOrgCodes() {
        return mapper.selectAllOrgCodes();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(String provinceCode, List<BenefitInternalConstructionDO> entityList, Long userId) {
        // 1 历史数据硬删除（这样做的原因是考虑删行、修改无法定位的问题）
        BenefitInternalConstructionDO queryDO = new BenefitInternalConstructionDO();
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BenefitInternalConstructionDO> exists = queryList(queryDO);
        if (DataUtils.isNotEmpty(exists)) {
            List<Long> ids = exists.stream().map(BenefitInternalConstructionDO::getId).collect(Collectors.toList());
            this.mapper.hardBatchDelete(ids);
            logger.info("【对内建设数据】 orgCode:{} 下历史数据硬删除共 {} 条，", provinceCode, ids.size());
        }

        // 2 插入新数据
        if (DataUtils.isNotEmpty(entityList)) {
            Date submitTime = new Date();
            List<BenefitInternalConstructionDO> filteredList = entityList.stream()
                    // 去掉空数据
                    .filter(i -> DataUtils.isNotEmpty(i.getItemNo()))
                    .collect(Collectors.toList());
            filteredList.forEach(i -> {
                // 防止旧数据留存与时间抖动
                // i.setCreateTime(submitTime);
                i.setUpdateTime(submitTime);
                i.setOrgCode(provinceCode);
                i.setId(null);
            });
            updateBatch(userId ,filteredList,30);
            logger.info("【对内建设数据】 orgCode:{} 下插入新数据共 {} 条，", provinceCode, filteredList.size());
        }

        // 3 更新 auto 项
        metricSecondaryProcess.execute(() -> {
            try {
                long startTime = System.currentTimeMillis();
                this.mapper.updateAutoProjectDesignReplyTotalInvestment(provinceCode);
                this.mapper.updateAutoSecurityDeviceOtherFee(provinceCode);
                // 联动更新
                benefitExternalEmpowermentMapper.updateAutoSafetyTotalInvestment(provinceCode);
                // this.mapper.updateAllUnitCosts(provinceCode);
                logger.info("【对内建设数据】 orgCode:{} 下自动更新，耗时 {} 秒", provinceCode, (System.currentTimeMillis() - startTime) / 1_000);
            } catch (Exception e){
                logger.error("【对内建设数据】 orgCode:{} 下自动更新失败：{}", provinceCode, e.getMessage());
                // 不回退
            }
        });
    }

    @Override
    public Pagination<BenefitInternalConstructionDO> searchAndPage(BenefitInternalConstructionDO queryDO, Pagination<BenefitInternalConstructionDO> rowBounds) {
        Page<BenefitInternalConstructionDO> page = PageUtils.getPage(rowBounds);
        List<BenefitInternalConstructionDO> res = this.mapper.selectListByCondAndOrd(queryDO, page);
        rowBounds.setTotal(page.getTotal());
        rowBounds.setRecords(res);
        rowBounds.setOrderByField(queryDO.getOrderBy());
        return rowBounds;
    }
}
