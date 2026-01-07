package com.sama.ledger.dubboImpl;

import com.core4ct.base.impl.BaseDubboServiceImpl;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.service.BenefitInternalConstructionDubboService;
import com.sama.ledger.service.BenefitInternalConstructionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/21 13:59
 */
@DubboService
@RefreshScope
public class BenefitInternalConstructionDubboServiceImpl
        extends BaseDubboServiceImpl<BenefitInternalConstructionDO, BenefitInternalConstructionService> implements BenefitInternalConstructionDubboService {

    @Override
    public void submit(String provinceCode, List<BenefitInternalConstructionDO> entityList, Long userId) {
        this.service.submit(provinceCode, entityList, userId);
    }

    @Override
    public Pagination<BenefitInternalConstructionDO> searchAndPage(BenefitInternalConstructionDO queryDO, Pagination<BenefitInternalConstructionDO> rowBounds) {
        return this.service.searchAndPage(queryDO, rowBounds);
    }

}
