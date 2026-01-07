
package com.sama.ledger.service.impl;

import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.constants.Constants;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.ledger.mapper.BenefitExternalEmpowermentMapper;
import com.sama.ledger.service.BenefitExternalEmpowermentService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 效益对外赋能
 * @author: huxh
 * @description:
 * @datetime: 2025/7/16 10:38
 */
@Service
public class BenefitExternalEmpowermentServiceImpl extends BaseServiceImpl<BenefitExternalEmpowermentDO, BenefitExternalEmpowermentMapper> implements BenefitExternalEmpowermentService {

    @Resource(name = "metricSecondaryProcess")
    ThreadPoolTaskExecutor metricSecondaryProcess;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(String provinceCode, List<BenefitExternalEmpowermentDO> entityList, Long userId) {
        BenefitExternalEmpowermentDO queryDO = new BenefitExternalEmpowermentDO();
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BenefitExternalEmpowermentDO> exists = queryList(queryDO);
        if (DataUtils.isNotEmpty(exists)) {
            List<Long> ids = exists.stream().map(BenefitExternalEmpowermentDO::getId).collect(Collectors.toList());
            this.mapper.hardBatchDelete(ids);
            logger.info("【对外赋能数据】 orgCode:{} 下历史数据硬删除共 {} 条，", provinceCode, ids.size());
        }

        if (DataUtils.isNotEmpty(entityList)) {
            Date submitTime = new Date();
            List<BenefitExternalEmpowermentDO> filteredList = entityList.stream()
                    .filter(i -> DataUtils.isNotEmpty(i.getItemNo()))
                    .collect(Collectors.toList());
            filteredList.forEach(i -> {
                // i.setCreateTime(submitTime);
                i.setUpdateTime(submitTime);
                i.setOrgCode(provinceCode);
                i.setId(null);
            });
            // 实际只有 1 条
            updateBatch(userId ,filteredList,30);
            logger.info("【对外赋能数据】 orgCode:{} 下插入新数据共 {} 条，", provinceCode, filteredList.size());
        }

        // 3 更新 auto 项
        metricSecondaryProcess.execute(() -> {
            try {
                long startTime = System.currentTimeMillis();
                this.mapper.updateAutoSafetyTotalInvestment(provinceCode);
                logger.info("【对外赋能数据】 orgCode:{} 下自动更新，耗时 {} 秒", provinceCode, (System.currentTimeMillis() - startTime) / 1_000);
            } catch (Exception e){
                logger.error("【对外赋能数据】 orgCode:{} 下自动更新失败：{}", provinceCode, e.getMessage());
                // 不回退
            }
        });
    }

}
