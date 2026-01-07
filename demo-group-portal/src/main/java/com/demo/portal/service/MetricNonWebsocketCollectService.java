package com.sama.officer.service;

import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.officer.object.vo.BenefitExternalEmpowermentProcessDataVO;
import com.sama.officer.object.vo.BenefitInternalConstructionProcessDataVO;
import com.sama.officer.object.vo.ComprehensiveProtectionProcessDataVO;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author: huxh
 * @description: reshape form DuplicateCollectDubboService
 * maint                                        ledger                                  office
 * ComprehensiveProtectionUnifiedDTO        <-  ComprehensiveProtectionExtendedDO   ->  ComprehensiveProtectionProcessDataVO
 * BenefitInternalConstructionUnifiedDTO    <-  BenefitInternalConstructionDO       ->  BenefitInternalConstructionProcessDataVO
 * BenefitExternalEmpowermentUnifiedDTO     <-  BenefitExternalEmpowermentDO        ->  BenefitExternalEmpowermentProcessDataVO
 * maint 和 office 无法用同一个DTO/VO，因为当@ExcelProperty注解中指定的index出现不连续时，在Excel中会显示为空列
 * 未使用 BeanConvertBeanUtil.copyListProperties(rawList, VO::new);
 *
 * @datetime: 2025/10/27 15:16
 */
public interface MetricNonWebsocketCollectService {

    Pagination<ComprehensiveProtectionProcessDataVO> processDataPage(ComprehensiveProtectionExtendedDO queryDO);

    Pagination<BenefitInternalConstructionProcessDataVO> processDataPage(BenefitInternalConstructionDO queryDO);

    Pagination<BenefitExternalEmpowermentProcessDataVO> processDataPage(BenefitExternalEmpowermentDO queryDO);

    void processDataExport(ComprehensiveProtectionExtendedDO queryDO, HttpServletResponse response);

    void processDataExport(BenefitInternalConstructionDO queryDO, HttpServletResponse response);

    void processDataExport(BenefitExternalEmpowermentDO queryDO, HttpServletResponse response);
}
