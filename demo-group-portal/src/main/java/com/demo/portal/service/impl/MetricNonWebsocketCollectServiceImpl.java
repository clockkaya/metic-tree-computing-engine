package com.sama.officer.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.api.analytic.service.DuplicateCollectDubboService;
import com.core4ct.constants.HttpCode;
import com.core4ct.exception.GenericException;
import com.core4ct.support.Pagination;
import com.core4ct.utils.DateUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.officer.object.vo.BenefitExternalEmpowermentProcessDataVO;
import com.sama.officer.object.vo.BenefitInternalConstructionProcessDataVO;
import com.sama.officer.object.vo.ComprehensiveProtectionProcessDataVO;
import com.sama.officer.service.MetricNonWebsocketCollectService;
import com.sama.officer.utils.BeanConvertBeanUtil;
import com.sama.officer.utils.ExcelPropertyUtil;
import com.sama.officer.utils.GenericMultiColumnMergeStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/27 15:18
 */
@Service
public class MetricNonWebsocketCollectServiceImpl implements MetricNonWebsocketCollectService {

    private static final Logger logger = LogManager.getLogger(MetricNonWebsocketCollectServiceImpl.class);

    public static final String COMPREHENSIVE_PROTECTION_CN = "综合防护";
    public static final String BENEFIT_INTERNAL_CONSTRUCTION_CN = "对内建设数据";
    public static final String BENEFIT_EXTERNAL_EMPOWERMENT_CN = "对外赋能数据";
    private static final Integer MAX_SIZE = 100_000;

    @Resource(name = "orgCodeAndNameCache")
    private LoadingCache<String, String> orgCodeAndNameCache;

    @DubboReference
    DuplicateCollectDubboService duplicateCollectDubboService;

    @Override
    public Pagination<ComprehensiveProtectionProcessDataVO> processDataPage(ComprehensiveProtectionExtendedDO queryDO) {
        try {
            logger.info("【{}】 条件查询分页 queryDO: {}", COMPREHENSIVE_PROTECTION_CN, JSON.toJSONString(queryDO));

            Pagination<ComprehensiveProtectionExtendedDO> rawPagination = duplicateCollectDubboService.processDataPage(queryDO);
            Pagination<ComprehensiveProtectionProcessDataVO> voPagination = new Pagination<>();
            BeanUtils.copyProperties(rawPagination, voPagination);

            List<ComprehensiveProtectionExtendedDO> rawList = rawPagination.getRecords();
            List<ComprehensiveProtectionProcessDataVO> voList = rawList.stream().map(rawDO -> {
                ComprehensiveProtectionProcessDataVO voItem = new ComprehensiveProtectionProcessDataVO();
                BeanConvertBeanUtil.copyProperties(rawDO, voItem);
                voItem.setOrgCn(orgCodeAndNameCache.get(rawDO.getOrgCode()));
                voItem.setProcessingDataDisplay(rawDO.getProcessingDataCn() + ": " +
                    (rawDO.getProcessingData() == null ? "-" : rawDO.getProcessingData()) +
                    rawDO.getProcessingDataUnit());
                return voItem;
            }).toList();
            voPagination.setRecords(voList);

            logger.info("【{}】 条件查询分页成功，返回共 {} 条", COMPREHENSIVE_PROTECTION_CN, voPagination.getTotal());
            return voPagination;
        } catch (Exception e){
            logger.error("隐藏报错信息如下: ", e);
            return new Pagination<>();
        }
    }

    @Override
    public Pagination<BenefitInternalConstructionProcessDataVO> processDataPage(BenefitInternalConstructionDO queryDO) {
        try {
            logger.info("【{}】 条件查询分页 queryDO: {}", BENEFIT_INTERNAL_CONSTRUCTION_CN, JSON.toJSONString(queryDO));

            Pagination<BenefitInternalConstructionDO> rawPagination = duplicateCollectDubboService.processDataPage(queryDO);
            Pagination<BenefitInternalConstructionProcessDataVO> voPagination = new Pagination<>();
            BeanUtils.copyProperties(rawPagination, voPagination);

            List<BenefitInternalConstructionDO> rawList = rawPagination.getRecords();
            List<BenefitInternalConstructionProcessDataVO> voList = rawList.stream().map(rawDO -> {
                BenefitInternalConstructionProcessDataVO voItem = new BenefitInternalConstructionProcessDataVO();
                BeanConvertBeanUtil.copyProperties(rawDO, voItem);
                voItem.setOrgCn(orgCodeAndNameCache.get(rawDO.getOrgCode()));
                return voItem;
            }).toList();
            voPagination.setRecords(voList);

            logger.info("【{}】 条件查询分页成功，返回共 {} 条", COMPREHENSIVE_PROTECTION_CN, voPagination.getTotal());
            return voPagination;
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return new Pagination<>();
        }
    }

    @Override
    public Pagination<BenefitExternalEmpowermentProcessDataVO> processDataPage(BenefitExternalEmpowermentDO queryDO) {
        try {
            logger.info("【{}】 条件查询分页 queryDO: {}", BENEFIT_EXTERNAL_EMPOWERMENT_CN, JSON.toJSONString(queryDO));

            Pagination<BenefitExternalEmpowermentDO> rawPagination = duplicateCollectDubboService.processDataPage(queryDO);
            Pagination<BenefitExternalEmpowermentProcessDataVO> voPagination = new Pagination<>();
            BeanUtils.copyProperties(rawPagination, voPagination);

            List<BenefitExternalEmpowermentDO> rawList = rawPagination.getRecords();
            List<BenefitExternalEmpowermentProcessDataVO> voList = rawList.stream().map(rawDO -> {
                BenefitExternalEmpowermentProcessDataVO voItem = new BenefitExternalEmpowermentProcessDataVO();
                BeanConvertBeanUtil.copyProperties(rawDO, voItem);
                voItem.setOrgCn(orgCodeAndNameCache.get(rawDO.getOrgCode()));
                return voItem;
            }).toList();
            voPagination.setRecords(voList);

            logger.info("【{}】 条件查询分页成功，返回共 {} 条", BENEFIT_EXTERNAL_EMPOWERMENT_CN, voPagination.getTotal());
            return voPagination;
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return new Pagination<>();
        }
    }

    //==============================================================================
    // copy from maint
    //==============================================================================

    @Override
    public void processDataExport(ComprehensiveProtectionExtendedDO queryDO, HttpServletResponse response) {
        try {
            queryDO.setSize(MAX_SIZE);
            Pagination<ComprehensiveProtectionProcessDataVO> targetPagination = processDataPage(queryDO);
            List<ComprehensiveProtectionProcessDataVO> exportList = targetPagination.getRecords();

            String fileName = URLEncoder.encode(COMPREHENSIVE_PROTECTION_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 定义合并规则
            // 综合场景类型 -> 评估项 -> 计算方法 依次合并
            List<Integer> levelMergeCols = Arrays.asList(0, 1, 4);
            // 评估值 跟随“评估项”
            Map<Integer, Integer> followMap = new HashMap<>();
            followMap.put(3, 1);

            Map<Integer, String> colFieldMap = ExcelPropertyUtil.getColIndexFieldMap(ComprehensiveProtectionExtendedDO.class);

            // 注册合并策略
            GenericMultiColumnMergeStrategy mergeStrategy = new GenericMultiColumnMergeStrategy(
                exportList, levelMergeCols, followMap, colFieldMap);

            EasyExcel.write(response.getOutputStream(), ComprehensiveProtectionProcessDataVO.class)
                .sheet(COMPREHENSIVE_PROTECTION_CN)
                .needHead(true)
                .doWrite(exportList);

        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下:", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

    @Override
    public void processDataExport(BenefitInternalConstructionDO queryDO, HttpServletResponse response) {
        try {
            queryDO.setSize(MAX_SIZE);
            Pagination<BenefitInternalConstructionProcessDataVO> targetPagination = processDataPage(queryDO);
            List<BenefitInternalConstructionProcessDataVO> exportList = targetPagination.getRecords();

            String fileName = URLEncoder.encode(BENEFIT_INTERNAL_CONSTRUCTION_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), BenefitInternalConstructionProcessDataVO.class)
                .sheet(BENEFIT_INTERNAL_CONSTRUCTION_CN)
                .needHead(true)
                .doWrite(exportList);

        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下: ", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

    @Override
    public void processDataExport(BenefitExternalEmpowermentDO queryDO, HttpServletResponse response) {
        try {
            queryDO.setSize(MAX_SIZE);
            Pagination<BenefitExternalEmpowermentProcessDataVO> targetPagination = processDataPage(queryDO);
            List<BenefitExternalEmpowermentProcessDataVO> exportList = targetPagination.getRecords();

            String fileName = URLEncoder.encode(BENEFIT_EXTERNAL_EMPOWERMENT_CN + DateUtils.getDateTime(), "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), BenefitExternalEmpowermentProcessDataVO.class)
                .sheet(BENEFIT_EXTERNAL_EMPOWERMENT_CN)
                .needHead(true)
                .doWrite(exportList);

        } catch (Exception e) {
            logger.error("捕获异常一只，堆栈信息如下: ", e);
            throw new GenericException(HttpCode.INTERNAL_SERVER_ERROR, "导出失败");
        }
    }

}
