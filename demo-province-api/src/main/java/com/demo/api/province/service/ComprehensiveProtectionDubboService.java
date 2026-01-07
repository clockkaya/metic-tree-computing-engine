package com.sama.api.ledger.service;

import com.core4ct.base.BaseDubboService;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:47
 */
public interface ComprehensiveProtectionDubboService extends BaseDubboService<ComprehensiveProtectionExtendedDO> {

    @Deprecated
    void submit(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId);

    void submitAndInstantScore(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId);

    List<ComprehensiveProtectionExtendedDO> searchAndList(ComprehensiveProtectionExtendedDO queryDO);

}
