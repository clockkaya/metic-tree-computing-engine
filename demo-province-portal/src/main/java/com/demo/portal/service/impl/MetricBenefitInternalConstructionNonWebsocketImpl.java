package com.sama.maint.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.core4ct.constants.HttpCode;
import com.core4ct.exception.BusinessException;
import com.core4ct.exception.GenericException;
import com.core4ct.support.Pagination;
import com.core4ct.utils.DataUtils;
import com.core4ct.utils.DateUtils;
import com.core4ct.utils.OrgCodeUtils;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.service.BenefitInternalConstructionDubboService;
import com.sama.maint.object.dto.CellUpdateMessageDTO;
import com.sama.maint.object.dto.ledger.BenefitInternalConstructionOnlineBO;
import com.sama.maint.object.dto.ledger.BenefitInternalConstructionUnifiedDTO;
import com.sama.maint.service.MetricNonWebsocket;
import com.sama.maint.service.WebSocketService;
import com.sama.maint.utils.BeanConvertBeanUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.sama.maint.constants.MetricTableKey.BENEFIT_INTERNAL_CONSTRUCTION;
import static com.sama.maint.constants.MetricTableKey.BENEFIT_INTERNAL_CONSTRUCTION_CN;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/22 15:28
 */
@Service
public class MetricBenefitInternalConstructionNonWebsocketImpl implements MetricNonWebsocket<BenefitInternalConstructionDO> {

    private static final Logger logger = LogManager.getLogger(MetricBenefitInternalConstructionNonWebsocketImpl.class);

    private static final Integer MAX_SIZE = 100_000;

    @Resource
    WebSocketService webSocketService;

    @DubboReference
    BenefitInternalConstructionDubboService benefitInternalConstructionDubboService;

    @Override
    public Pagination<BenefitInternalConstructionDO> searchAndPage(Integer current, Integer size, BenefitInternalConstructionDO queryDO){
        return benefitInternalConstructionDubboService.searchAndPage(queryDO, new Pagination<>(current, size));
    }

    @Override
    public void submit(String orgCode, Long userId) {
        // 1
        String mapKey = BENEFIT_INTERNAL_CONSTRUCTION + " : " + orgCode;
        Object fromTableDataMap = webSocketService.getFromTableDataMap(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException(MessageFormat.format("未存有数据(mapKey:{0})，请排查！", mapKey));
        }
        List<CellUpdateMessageDTO> originalList = (List<CellUpdateMessageDTO>) fromTableDataMap;
        logger.info("【{}】 当此待提交数据共 {} 行，转换前第一行数据形如: {}",
            BENEFIT_INTERNAL_CONSTRUCTION_CN, originalList.size(), JSON.toJSONString(originalList.get(0).getRow()));

        // 2
        List<BenefitInternalConstructionOnlineBO> onlineBOList = new ArrayList<>();
        originalList.forEach(original -> {
            BenefitInternalConstructionOnlineBO onLineBO = JSONObject.parseObject(
                JSON.toJSONString(original.getRow()), BenefitInternalConstructionOnlineBO.class);
            onLineBO.setOrgCode(OrgCodeUtils.getCode(orgCode, 1));
            onlineBOList.add(onLineBO);
        });
        List<BenefitInternalConstructionDO> dbDOList = BeanConvertBeanUtil.copyListProperties(onlineBOList, BenefitInternalConstructionDO::new);
        logger.info("【{}】 转换后第一行数据形如: {}",
            BENEFIT_INTERNAL_CONSTRUCTION_CN, JSON.toJSONString(dbDOList.get(0)));

        benefitInternalConstructionDubboService.submit(orgCode, dbDOList, userId);
    }

    @Override
    public void export(BenefitInternalConstructionDO queryDO, HttpServletResponse response) {
        Pagination<BenefitInternalConstructionDO> page = searchAndPage(1, MAX_SIZE, queryDO);
        List<BenefitInternalConstructionDO> list = page.getRecords();
        try {
            List<BenefitInternalConstructionUnifiedDTO> exportDTOList = BeanConvertBeanUtil.copyListProperties(list, BenefitInternalConstructionUnifiedDTO::new);
            String fileName = URLEncoder.encode(BENEFIT_INTERNAL_CONSTRUCTION_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            exportExcel(fileName, exportDTOList, response);
        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下: ", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

    private void exportExcel(String fileName, List<BenefitInternalConstructionUnifiedDTO> records, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), BenefitInternalConstructionUnifiedDTO.class)
            .sheet(BENEFIT_INTERNAL_CONSTRUCTION_CN)
            .needHead(true)
            .doWrite(records);
    }

}
