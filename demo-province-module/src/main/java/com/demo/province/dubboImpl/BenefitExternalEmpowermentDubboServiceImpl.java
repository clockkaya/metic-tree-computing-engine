package com.sama.ledger.dubboImpl;

import com.core4ct.base.impl.BaseDubboServiceImpl;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.service.BenefitExternalEmpowermentDubboService;
import com.sama.ledger.service.BenefitExternalEmpowermentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 13:56
 */
@DubboService
@RefreshScope
public class BenefitExternalEmpowermentDubboServiceImpl
        extends BaseDubboServiceImpl<BenefitExternalEmpowermentDO, BenefitExternalEmpowermentService> implements BenefitExternalEmpowermentDubboService {

    @Override
    public void submit(String provinceCode, List<BenefitExternalEmpowermentDO> entityList, Long userId) {
        this.service.submit(provinceCode, entityList, userId);
    }

    @Override
    public Pagination<BenefitExternalEmpowermentDO> page(BenefitExternalEmpowermentDO queryDO, Pagination<BenefitExternalEmpowermentDO> rowBounds) {
        return this.service.page(queryDO, rowBounds);
    }
}
