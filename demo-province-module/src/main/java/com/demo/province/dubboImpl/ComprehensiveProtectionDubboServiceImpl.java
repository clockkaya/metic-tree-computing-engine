package com.sama.ledger.dubboImpl;

import com.core4ct.base.impl.BaseDubboServiceImpl;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.service.ComprehensiveProtectionDubboService;
import com.sama.ledger.metric.ComprehensiveProtectionEngineServiceImpl;
import com.sama.ledger.service.ComprehensiveProtectionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:48
 */
@DubboService
@RefreshScope
public class ComprehensiveProtectionDubboServiceImpl
        extends BaseDubboServiceImpl<ComprehensiveProtectionExtendedDO, ComprehensiveProtectionService> implements ComprehensiveProtectionDubboService {

    @Resource
    ComprehensiveProtectionEngineServiceImpl comprehensiveProtectionEngineService;

    @Override
    public void submit(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId) {
        this.service.submit(provinceCode, entityList, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitAndInstantScore(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId) {
        this.service.submit(provinceCode, entityList, userId);
        comprehensiveProtectionEngineService.blockInstantScore(provinceCode);
    }

    @Override
    public List<ComprehensiveProtectionExtendedDO> searchAndList(ComprehensiveProtectionExtendedDO queryDO) {
        return this.service.searchAndList(queryDO);
    }
}
