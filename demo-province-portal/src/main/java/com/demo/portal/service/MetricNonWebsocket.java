package com.sama.maint.service;

import com.core4ct.base.BaseModel;
import com.core4ct.support.Pagination;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author: huxh
 * @description: 实体类说明：1. OnlineBO extend dbDO，用于在线编辑；2. UnifiedDTO 则较为独立，为页面展示VO和导出DTO的统一。
 * @datetime: 2025/7/22 15:31
 */
public interface MetricNonWebsocket<T extends BaseModel>{

    /**
     * XX省安全能力成效评估——评估过程数据
     */
    Pagination<T> searchAndPage(Integer current, Integer size, T queryDO);

    /**
     * XX省数据录入——导入
     */
    void submit(String orgCode, Long userId);

    /**
     * XX省数据录入——导出
     */
    void export(T queryDO, HttpServletResponse response);

}
