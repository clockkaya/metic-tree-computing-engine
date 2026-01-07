package com.sama.officer.controller;

import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.officer.base.BaseMaintController;
import com.sama.officer.service.MetricNonWebsocketCollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/27 14:02
 */
@Tag(name = "指标非台账相关接口")
@RestController
@RequestMapping("/nonwebsocket")
public class MetricNonWebsocketController extends BaseMaintController {

    @Resource
    MetricNonWebsocketCollectService metricNonWebsocketCollectService;

    @Operation(summary = "综合防护-过程数据", responses = @ApiResponse(
        content = @Content(schema = @Schema(implementation = ComprehensiveProtectionExtendedDO.class))))
    @PostMapping(value = "/comprehensiveProtection/processDataPage")
    public Object processDataPageComprehensiveProtection(@RequestBody ComprehensiveProtectionExtendedDO queryDO) {
        return setSuccessModelMap(metricNonWebsocketCollectService.processDataPage(queryDO));
    }

    @Operation(summary = "效益对内建设-过程数据", responses = @ApiResponse(
        content = @Content(schema = @Schema(implementation = BenefitInternalConstructionDO.class))))
    @PostMapping(value = "/benefitInternal/processDataPage")
    public Object processDataPageBenefitInternalConstruction(@RequestBody BenefitInternalConstructionDO queryDO) {
        return setSuccessModelMap(metricNonWebsocketCollectService.processDataPage(queryDO));
    }

    @Operation(summary = "效益对外赋值-过程数据", responses = @ApiResponse(
        content = @Content(schema = @Schema(implementation = BenefitExternalEmpowermentDO.class))))
    @PostMapping(value = "/benefitExternal/processDataPage")
    public Object processDataPageBenefitExternalEmpowerment(@RequestBody BenefitExternalEmpowermentDO queryDO) {
        return setSuccessModelMap(metricNonWebsocketCollectService.processDataPage(queryDO));
    }

    @Operation(summary = "综合防护-过程数据导出")
    @PostMapping(value = "/comprehensiveProtection/processDataExport")
    public void exportComprehensiveProtection(@RequestBody ComprehensiveProtectionExtendedDO queryDO, HttpServletResponse response) {
        metricNonWebsocketCollectService.processDataExport(queryDO, response);
    }

    @Operation(summary = "效益对内建设-过程数据导出")
    @PostMapping(value = "/benefitInternal/processDataExport")
    public void exportBenefitInternalConstruction(@RequestBody BenefitInternalConstructionDO queryDO, HttpServletResponse response) {
        metricNonWebsocketCollectService.processDataExport(queryDO, response);
    }

    @Operation(summary = "效益对外赋值-过程数据导出")
    @PostMapping(value = "/benefitExternal/processDataExport")
    public void exportBenefitExternalEmpowerment(BenefitExternalEmpowermentDO queryDO, HttpServletResponse response) {
        metricNonWebsocketCollectService.processDataExport(queryDO, response);
    }

}
