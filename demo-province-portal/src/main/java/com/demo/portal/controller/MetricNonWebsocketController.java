package com.sama.maint.controller;

import com.core4ct.DTO.UserDTO;
import com.core4ct.constants.Constants;
import com.core4ct.support.Pagination;
import com.core4ct.utils.DataUtils;
import com.sama.api.ledger.bean.BenefitExternalEmpowermentDO;
import com.sama.api.ledger.bean.BenefitInternalConstructionDO;
import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import com.sama.maint.base.BaseMaintController;
import com.sama.maint.object.dto.ledger.ComprehensiveProtectionUnifiedDTO;
import com.sama.maint.service.WebSocketService;
import com.sama.maint.service.impl.MetricBenefitExternalEmpowermentNonWebsocketImpl;
import com.sama.maint.service.impl.MetricBenefitInternalConstructionNonWebsocketImpl;
import com.sama.maint.service.impl.MetricComprehensiveProtectionNonWebsocketImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huxh
 * @description: copy from SecurityOperationSystemController
 * @datetime: 2025/7/22 15:14
 */
@Tag(name = "指标非台账相关接口")
@RestController
@RequestMapping("/nonwebsocket")
public class MetricNonWebsocketController extends BaseMaintController {

    @Resource
    WebSocketService webSocketService;

    @Resource
    MetricComprehensiveProtectionNonWebsocketImpl comprehensiveProtectionNonWebsocket;

    @Resource
    MetricBenefitInternalConstructionNonWebsocketImpl benefitInternalConstructionNonWebsocket;

    @Resource
    MetricBenefitExternalEmpowermentNonWebsocketImpl benefitExternalEmpowermentNonWebsocket;

    @Operation(summary = "综合防护-过程数据", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = ComprehensiveProtectionUnifiedDTO.class))))
    @PostMapping(value = "/comprehensiveProtection/list")
    public Object listComprehensiveProtection(@RequestBody ComprehensiveProtectionUnifiedDTO params) throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setComprehensiveScenarioType(params.getComprehensiveScenarioType());
        queryDO.setEvaluationItem(params.getEvaluationItem());
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ComprehensiveProtectionUnifiedDTO> res = comprehensiveProtectionNonWebsocket.list(queryDO);

        if (DataUtils.isNotEmpty(res)) {
            return setSuccessModelMap(res);
        } else {
            return setSuccessModelMap(new ArrayList<>());
        }
    }

    @Operation(summary = "效益对内建设-过程数据", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = BenefitInternalConstructionDO.class))))
    @GetMapping(value = "/benefitInternal/page")
    public Object pageBenefitInternalConstruction(
        @Parameter(description = "项目编号") @RequestParam(required = false) String projectCode,
        @Parameter(description = "项目名称") @RequestParam(required = false) String projectName,
        @Parameter(description = "项目类型") @RequestParam(required = false) String projectType,
        @RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size
    ) throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        BenefitInternalConstructionDO queryDO = new BenefitInternalConstructionDO();
        queryDO.setProjectCode(projectCode);
        queryDO.setProjectName(projectName);
        queryDO.setProjectType(projectType);
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        queryDO.setOrderBy("id asc");
        Pagination<BenefitInternalConstructionDO> res = benefitInternalConstructionNonWebsocket.searchAndPage(current, size, queryDO);

        if (DataUtils.isNotEmpty(res)) {
            return setSuccessModelMap(res);
        } else {
            return setSuccessModelMap(new Pagination<>());
        }
    }

    @Operation(summary = "效益对外赋值-过程数据", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = BenefitExternalEmpowermentDO.class))))
    @PostMapping(value = "/benefitExternal/list")
    public Object listBenefitExternalEmpowerment() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        BenefitExternalEmpowermentDO queryDO = new BenefitExternalEmpowermentDO();
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BenefitExternalEmpowermentDO> res = benefitExternalEmpowermentNonWebsocket.list(queryDO);

        if (DataUtils.isNotEmpty(res)) {
            return setSuccessModelMap(res);
        } else {
            return setSuccessModelMap(new ArrayList<>());
        }
    }

    @Operation(summary = "综合防护-提交")
    @PostMapping(value = "/comprehensiveProtection/submit")
    public Object submitComprehensiveProtection() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        comprehensiveProtectionNonWebsocket.submit(provinceCode, userDTO.getUserId());
        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "效益对内建设-提交")
    @PostMapping(value = "/benefitInternal/submit")
    public Object submitBenefitInternalConstruction() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        benefitInternalConstructionNonWebsocket.submit(provinceCode, userDTO.getUserId());
        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "效益对外赋值-提交")
    @PostMapping(value = "/benefitExternal/submit")
    public Object submitBenefitExternalEmpowerment() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        benefitExternalEmpowermentNonWebsocket.submit(provinceCode, userDTO.getUserId());
        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "综合防护-导出")
    @PostMapping(value = "/comprehensiveProtection/export")
    public void exportComprehensiveProtection(@RequestBody ComprehensiveProtectionUnifiedDTO params, HttpServletResponse response) throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        ComprehensiveProtectionExtendedDO queryDO = new ComprehensiveProtectionExtendedDO();
        queryDO.setComprehensiveScenarioType(params.getComprehensiveScenarioType());
        queryDO.setEvaluationItem(params.getEvaluationItem());
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);

        comprehensiveProtectionNonWebsocket.export(queryDO, response);
    }

    @Operation(summary = "效益对内建设-导出")
    @PostMapping(value = "/benefitInternal/export")
    public void exportBenefitInternalConstruction(@RequestBody BenefitInternalConstructionDO params, HttpServletResponse response) throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        BenefitInternalConstructionDO queryDO = new BenefitInternalConstructionDO();
        queryDO.setProjectCode(params.getProjectCode());
        queryDO.setProjectName(params.getProjectName());
        queryDO.setProjectType(params.getProjectType());
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        queryDO.setOrderBy("id asc");

        benefitInternalConstructionNonWebsocket.export(queryDO, response);
    }

    @Operation(summary = "效益对外赋值-导出")
    @PostMapping(value = "/benefitExternal/export")
    public void exportBenefitExternalEmpowerment(HttpServletResponse response) throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        BenefitExternalEmpowermentDO queryDO = new BenefitExternalEmpowermentDO();
        queryDO.setOrgCode(provinceCode);
        queryDO.setDelFlag(Constants.DelFlag.AVAILABLE);

        benefitExternalEmpowermentNonWebsocket.export(queryDO, response);
    }

}
