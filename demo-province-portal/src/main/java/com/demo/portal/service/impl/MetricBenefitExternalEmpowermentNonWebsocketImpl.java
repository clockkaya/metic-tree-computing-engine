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
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.service.BenefitExternalEmpowermentDubboService;
import com.sama.maint.object.dto.CellUpdateMessageDTO;
import com.sama.maint.object.dto.ledger.BenefitExternalEmpowermentOnlineBO;
import com.sama.maint.object.dto.ledger.BenefitExternalEmpowermentUnifiedDTO;
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

import static com.sama.maint.constants.MetricTableKey.BENEFIT_EXTERNAL_EMPOWERMENT;
import static com.sama.maint.constants.MetricTableKey.BENEFIT_EXTERNAL_EMPOWERMENT_CN;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/22 15:54
 */
@Service
public class MetricBenefitExternalEmpowermentNonWebsocketImpl implements MetricNonWebsocket<BenefitExternalEmpowermentDO> {

    private static final Logger logger = LogManager.getLogger(MetricBenefitExternalEmpowermentNonWebsocketImpl.class);

    @Resource
    WebSocketService webSocketService;

    @DubboReference
    BenefitExternalEmpowermentDubboService benefitExternalEmpowermentDubboService;

    @Override
    @Deprecated
    public Pagination<BenefitExternalEmpowermentDO> searchAndPage(Integer current, Integer size, BenefitExternalEmpowermentDO queryDO) {
        // 无需支持
        return null;
    }

    public List<BenefitExternalEmpowermentDO> list(BenefitExternalEmpowermentDO queryDO){
        return benefitExternalEmpowermentDubboService.list(queryDO);
    }

    @Override
    public void submit(String orgCode, Long userId) {
        // 1
        String mapKey = BENEFIT_EXTERNAL_EMPOWERMENT + " : " + orgCode;
        Object fromTableDataMap = webSocketService.getFromTableDataMap(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException(MessageFormat.format("未存有数据(mapKey:{0})，请排查！", mapKey));
        }
        List<CellUpdateMessageDTO> originalList = (List<CellUpdateMessageDTO>) fromTableDataMap;
        logger.info("【{}】 当此待提交数据共 {} 行，转换前第一行数据形如: {}",
            BENEFIT_EXTERNAL_EMPOWERMENT_CN, originalList.size(), JSON.toJSONString(originalList.get(0).getRow()));

        // 2
        List<BenefitExternalEmpowermentOnlineBO> onlineBOList = new ArrayList<>();
        originalList.forEach(original -> {
            BenefitExternalEmpowermentOnlineBO onLineBO = JSONObject.parseObject(
                JSON.toJSONString(original.getRow()), BenefitExternalEmpowermentOnlineBO.class);
            onLineBO.setOrgCode(OrgCodeUtils.getCode(orgCode, 1));
            onlineBOList.add(onLineBO);
        });
        List<BenefitExternalEmpowermentDO> dbDOList = BeanConvertBeanUtil.copyListProperties(onlineBOList, BenefitExternalEmpowermentDO::new);
        logger.info("【{}】 转换后第一行数据形如: {}",
            BENEFIT_EXTERNAL_EMPOWERMENT_CN, JSON.toJSONString(dbDOList.get(0)));

        benefitExternalEmpowermentDubboService.submit(orgCode, dbDOList, userId);
    }

    @Override
    public void export(BenefitExternalEmpowermentDO queryDO, HttpServletResponse response) {
        try {
            List<BenefitExternalEmpowermentUnifiedDTO> exportDTOList = listAndUnify(queryDO);
            String fileName = URLEncoder.encode(BENEFIT_EXTERNAL_EMPOWERMENT_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            exportExcel(fileName, exportDTOList, response);
        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下: ", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

    /**
     * 统一 VO 和 DTO 的列表返回
     */
    private List<BenefitExternalEmpowermentUnifiedDTO> listAndUnify(BenefitExternalEmpowermentDO queryDO){
        List<BenefitExternalEmpowermentDO> dbDOList = benefitExternalEmpowermentDubboService.list(queryDO);
        if (DataUtils.isEmpty(dbDOList)) {
            // 空直接返回
            logger.info("【{}】 数据库未查出有效数据 queryDO:{}", BENEFIT_EXTERNAL_EMPOWERMENT_CN, JSON.toJSONString(queryDO));
            return new ArrayList<>();
        }
        List<BenefitExternalEmpowermentUnifiedDTO> unifiedDTOList = BeanConvertBeanUtil.copyListProperties(dbDOList, BenefitExternalEmpowermentUnifiedDTO::new);
        return unifiedDTOList;
    }

    private void exportExcel(String fileName, List<BenefitExternalEmpowermentUnifiedDTO> records, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), BenefitExternalEmpowermentUnifiedDTO.class)
            .sheet(BENEFIT_EXTERNAL_EMPOWERMENT_CN)
            .needHead(true)
            .doWrite(records);
    }
}
