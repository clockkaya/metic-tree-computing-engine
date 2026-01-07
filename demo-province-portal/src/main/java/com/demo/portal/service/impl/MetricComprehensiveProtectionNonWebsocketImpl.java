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
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.api.ledger.service.ComprehensiveProtectionDubboService;
import com.sama.maint.common.GenericMultiColumnMergeStrategy;
import com.sama.maint.object.dto.CellUpdateMessageDTO;
import com.sama.maint.object.dto.ledger.ComprehensiveProtectionOnlineBO;
import com.sama.maint.object.dto.ledger.ComprehensiveProtectionUnifiedDTO;
import com.sama.maint.service.MetricNonWebsocket;
import com.sama.maint.service.WebSocketService;
import com.sama.maint.utils.BeanConvertBeanUtil;
import com.sama.maint.utils.ExcelPropertyUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.*;

import static com.sama.maint.constants.MetricTableKey.COMPREHENSIVE_PROTECTION;
import static com.sama.maint.constants.MetricTableKey.COMPREHENSIVE_PROTECTION_CN;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 16:40
 */
@Service
public class MetricComprehensiveProtectionNonWebsocketImpl implements MetricNonWebsocket<ComprehensiveProtectionExtendedDO> {

    private static final Logger logger = LogManager.getLogger(MetricComprehensiveProtectionNonWebsocketImpl.class);

    @Resource
    WebSocketService webSocketService;

    @DubboReference
    ComprehensiveProtectionDubboService comprehensiveProtectionDubboService;

    @Override
    @Deprecated
    public Pagination<ComprehensiveProtectionExtendedDO> searchAndPage(Integer current, Integer size, ComprehensiveProtectionExtendedDO queryDO) {
        // 无需支持
        return null;
    }

    public List<ComprehensiveProtectionUnifiedDTO> list(ComprehensiveProtectionExtendedDO queryDO){
        return listAndUnify(queryDO);
    }

    @Override
    public void submit(String orgCode, Long userId) {
        // 1 从 WebSocketService 中获取数据，并强制转化
        String mapKey = COMPREHENSIVE_PROTECTION + " : " + orgCode;
        Object fromTableDataMap = webSocketService.getFromTableDataMap(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException(MessageFormat.format("未存有数据(mapKey:{0})，请排查！", mapKey));
        }
        List<CellUpdateMessageDTO> originalList = (List<CellUpdateMessageDTO>) fromTableDataMap;
        logger.info("【{}】 当此待提交数据共 {} 行，转换前第一行数据形如: {}",
                COMPREHENSIVE_PROTECTION_CN, originalList.size(), JSON.toJSONString(originalList.get(0).getRow()));

        // 2 再次取数（getRow）转换
        List<ComprehensiveProtectionOnlineBO> onlineBOList = new ArrayList<>();
        originalList.forEach(original -> {
            ComprehensiveProtectionOnlineBO onLineBO = JSONObject.parseObject(
                    JSON.toJSONString(original.getRow()), ComprehensiveProtectionOnlineBO.class);
            onLineBO.setOrgCode(OrgCodeUtils.getCode(orgCode, 1));
            onlineBOList.add(onLineBO);
        });
        List<ComprehensiveProtectionExtendedDO> dbDOList = BeanConvertBeanUtil.copyListProperties(onlineBOList, ComprehensiveProtectionExtendedDO::new);
        logger.info("【{}】 转换后第一行数据形如: {}",
                COMPREHENSIVE_PROTECTION_CN, JSON.toJSONString(dbDOList.get(0)));

        comprehensiveProtectionDubboService.submitAndInstantScore(orgCode, dbDOList, userId);
    }

    @Override
    public void export(ComprehensiveProtectionExtendedDO queryDO, HttpServletResponse response) {
        try {
            List<ComprehensiveProtectionUnifiedDTO> exportDTOList = listAndUnify(queryDO);
            logger.info("【breakpoint】 待导出数据共 {} 行，转换前第一行数据形如: {}", exportDTOList.size(), JSON.toJSONString(exportDTOList.get(0)));
            String fileName = URLEncoder.encode(COMPREHENSIVE_PROTECTION_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            exportExcel(fileName, exportDTOList, response);
            logger.info("【breakpoint】 完成 export");
        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下: ", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

    /**
     * 统一 VO 和 DTO 的列表返回
     */
    private List<ComprehensiveProtectionUnifiedDTO> listAndUnify(ComprehensiveProtectionExtendedDO queryDO){
        List<ComprehensiveProtectionExtendedDO> dbDOList = comprehensiveProtectionDubboService.searchAndList(queryDO);
        if (DataUtils.isEmpty(dbDOList)) {
            // 空直接返回
            logger.info("【{}】 数据库未查出有效数据 queryDO:{}", COMPREHENSIVE_PROTECTION_CN, JSON.toJSONString(queryDO));
            return new ArrayList<>();
        }
        List<ComprehensiveProtectionUnifiedDTO> unifiedDTOList = new ArrayList<>();
        dbDOList.forEach(rawDO->{
            ComprehensiveProtectionUnifiedDTO unifiedDTO = new ComprehensiveProtectionUnifiedDTO();
            BeanUtils.copyProperties(rawDO, unifiedDTO);
            // display 处理
            unifiedDTO.setProcessingDataDisplay(rawDO.getProcessingDataCn() + ": " +
                    (rawDO.getProcessingData() == null ? "-" : rawDO.getProcessingData()) +
                    rawDO.getProcessingDataUnit());
            unifiedDTOList.add(unifiedDTO);
        });
        return unifiedDTOList;
    }

    private void exportExcel(String fileName, List<ComprehensiveProtectionUnifiedDTO> records, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 定义合并规则
        // 综合场景类型 -> 评估项 -> 计算方法 依次合并
        List<Integer> levelMergeCols = Arrays.asList(0, 1, 4);
        // 评估值 跟随“评估项”
        Map<Integer, Integer> followMap = new HashMap<>();
        followMap.put(3, 1);

        Map<Integer, String> colFieldMap = ExcelPropertyUtil.getColIndexFieldMap(ComprehensiveProtectionUnifiedDTO.class);

        // 注册合并策略
        GenericMultiColumnMergeStrategy mergeStrategy = new GenericMultiColumnMergeStrategy(
                records, levelMergeCols, followMap, colFieldMap);

        // 写出 Excel
/*        EasyExcel.write(response.getOutputStream(), EffectComprehensiveProtectionUnifiedDTO.class)
                .registerWriteHandler(mergeStrategy)
                .registerWriteHandler(new CenterAllCellStyleStrategy())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet(EFFECT_COMPREHENSIVE_PROTECTION_CN)
                .doWrite(records);*/

        EasyExcel.write(response.getOutputStream(), ComprehensiveProtectionUnifiedDTO.class)
                .sheet(COMPREHENSIVE_PROTECTION_CN)
                .needHead(true)
                .doWrite(records);
    }
}
