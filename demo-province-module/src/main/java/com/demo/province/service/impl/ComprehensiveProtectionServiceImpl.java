package com.sama.ledger.service.impl;

import com.core4ct.base.impl.BaseServiceImpl;
import com.core4ct.constants.Constants;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.ledger.mapper.ComprehensiveProtectionMapper;
import com.sama.ledger.service.ComprehensiveProtectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 综合防护
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:42
 */
@Service
public class ComprehensiveProtectionServiceImpl extends BaseServiceImpl<ComprehensiveProtectionExtendedDO, ComprehensiveProtectionMapper> implements ComprehensiveProtectionService {

    @Override
    public List<String> selectAllOrgCodes() {
        return mapper.selectAllOrgCodes();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submit(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId) {
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ComprehensiveProtectionExtendedDO> exists = queryList(queryDO);
        if (DataUtils.isNotEmpty(exists)) {
            List<Long> ids = exists.stream().map(ComprehensiveProtectionExtendedDO::getId).collect(Collectors.toList());
            this.mapper.hardBatchDelete(ids);
            logger.info("【综合防护】 orgCode:{} 下历史数据硬删除共 {} 条，", provinceCode, ids.size());
        }

        if (DataUtils.isNotEmpty(entityList)) {
            Date submitTime = new Date();
            List<ComprehensiveProtectionExtendedDO> filteredList = entityList.stream()
                    .filter(i -> DataUtils.isNotEmpty(i.getProcessingDataCn()))
                    .collect(Collectors.toList());
            filteredList.forEach(i -> {
                // i.setCreateTime(submitTime);
                i.setUpdateTime(submitTime);
                i.setOrgCode(provinceCode);
                i.setId(null);
            });
            updateBatch(userId ,filteredList,30);
            logger.info("【综合防护】 orgCode:{} 下插入新数据共 {} 条，", provinceCode, filteredList.size());
        }
    }

    @Override
    public List<ComprehensiveProtectionExtendedDO> searchAndList(ComprehensiveProtectionExtendedDO queryDO) {
        return this.mapper.selectListByCondAndOrd(queryDO);
    }
}
