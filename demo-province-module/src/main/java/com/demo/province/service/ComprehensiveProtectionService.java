package com.sama.ledger.service;

import com.core4ct.base.BaseService;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 9:43
 */
public interface ComprehensiveProtectionService extends BaseService<ComprehensiveProtectionExtendedDO> {

    List<String> selectAllOrgCodes();

    void submit(String provinceCode, List<ComprehensiveProtectionExtendedDO> entityList, Long userId);

    List<ComprehensiveProtectionExtendedDO> searchAndList(ComprehensiveProtectionExtendedDO queryDO);

}
