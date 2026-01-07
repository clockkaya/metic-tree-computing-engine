package com.sama.ledger.dubboImpl;

import com.core4ct.base.impl.BaseDubboServiceImpl;
import com.sama.api.ledger.bean.MetricResultDO;
import com.sama.api.ledger.service.MetricResultDubboService;
import com.sama.ledger.service.MetricResultService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/3 10:31
 */
@DubboService
@RefreshScope
public class MetricResultDubboServiceImpl extends BaseDubboServiceImpl<MetricResultDO, MetricResultService> implements MetricResultDubboService {

}
