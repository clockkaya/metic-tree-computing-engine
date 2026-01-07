package com.sama.maint.controller;

import com.alibaba.fastjson2.JSONObject;
import com.core4ct.DTO.UserDTO;
import com.sama.api.ledger.bean.dto.*;
import com.sama.api.ledger.bean.vo.EffectOverviewVO;
import com.sama.api.ledger.bean.vo.EvaluationOverviewVO;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import com.sama.api.ledger.bean.vo.VisualizedProvinceBaseVO;
import com.sama.api.ledger.service.ProvinceVisualizationDubboService;
import com.sama.api.ledger.service.VisualOverviewDubboService;
import com.sama.maint.base.BaseMaintController;
import com.sama.maint.service.WebSocketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/3 10:23
 */
@Tag(name = "指标可视化相关接口")
@RestController
@RequestMapping("/metric")
public class MetricVisualizationController extends BaseMaintController {

    private static final Logger logger = LogManager.getLogger(MetricVisualizationController.class);

    @Resource
    WebSocketService webSocketService;

    @DubboReference
    VisualOverviewDubboService visualOverviewDubboService;

    @DubboReference
    ProvinceVisualizationDubboService provinceVisualizationDubboService;

    //==============================================================================
    // XX省安全能力成效评估 /display
    //==============================================================================

    @Operation(summary = "XX省综合防护", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/comprehensiveProtection")
    public Object displayComprehensiveProtection() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        VisualizedProvinceBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = new VisualizedProvinceBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.displayComprehensiveProtection(provinceCode);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "XX省效益评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/benefitOverview")
    public Object displayBenefitOverview() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        try {
            JSONObject targetVO = provinceVisualizationDubboService.displayBenefitOverview(provinceCode);
            return setSuccessModelMap(targetVO);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return setSuccessModelMap(new VisualizedProvinceBaseVO<>());
        }
    }

    @Operation(summary = "XX省对内建设", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/benefitInternalConstruction")
    public Object displayBenefitInternalConstruction() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        try {
            JSONObject targetVO = provinceVisualizationDubboService.displayBenefitInternalConstruction(provinceCode);
            return setSuccessModelMap(targetVO);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return setSuccessModelMap(new VisualizedProvinceBaseVO<>());
        }
    }

    @Operation(summary = "XX省对外赋能", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/benefitExternalEmpowerment")
    public Object displayBenefitExternalEmpowerment() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        VisualizedProvinceBaseVO<StatisticBenefitExternalEmpowermentProvinceDTO> targetVO = new VisualizedProvinceBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.displayBenefitExternalEmpowerment(provinceCode);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "XX省效率评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/efficiencyOverview")
    public Object displayEfficiencyOverview() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        VisualizedProvinceBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO = new VisualizedProvinceBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.displayEfficiencyOverview(provinceCode);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "XX省工程进度", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/efficiencyProgress")
    public Object displayEfficiencyProgress() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = new VisualizedProvinceBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.displayEfficiencyProgressSchedule(provinceCode);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "XX省投资进度", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedProvinceBaseVO.class))))
    @GetMapping("/display/efficiencyInvestment")
    public Object displayEfficiencyInvestment() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        VisualizedProvinceBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = new VisualizedProvinceBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.displayEfficiencyInvestmentSchedule(provinceCode);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "XX省效果评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EffectOverviewVO.class))))
    @GetMapping("/display/effect")
    public Object displayEffect() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        EffectOverviewVO effectOverviewVO = visualOverviewDubboService.getEffectOverview(provinceCode);
        if (effectOverviewVO == null) {
            return setSuccessModelMap(new EffectOverviewVO());
        }
        return setSuccessModelMap(effectOverviewVO);
    }

    @Operation(summary = "XX省可视化总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EvaluationOverviewVO.class))))
    @GetMapping("/display/overview")
    public Object displayOverview() throws Exception {
        UserDTO userDTO = getCurrUser();
        String provinceCode = webSocketService.getProvinceCodes(userDTO);
        EvaluationOverviewVO visualizeResult = visualOverviewDubboService.getAllVisualOverview(provinceCode);
        if (visualizeResult == null) {
            return setSuccessModelMap(new EvaluationOverviewVO());
        }
        return setSuccessModelMap(visualizeResult);
    }

    //==============================================================================
    // 全网安全能力成效评估 GroupVisualizationDubboService
    //==============================================================================

    @Operation(summary = "全网综合防护", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/comprehensiveProtection")
    public Object zoomComprehensiveProtection() {
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.zoomComprehensiveProtection();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "全网效益评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/benefitOverview")
    public Object zoomBenefitOverview() {
        VisualizedGroupBaseVO<StatisticBenefitOverviewProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.zoomBenefitOverview();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "全网对内建设", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/benefitInternalConstruction")
    public Object zoomBenefitInternalConstruction() {
        try {
            JSONObject targetVO = provinceVisualizationDubboService.zoomBenefitInternalConstruction();
            return setSuccessModelMap(targetVO);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return setSuccessModelMap(new VisualizedGroupBaseVO<>());
        }
    }

    @Operation(summary = "全网对外赋能", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/benefitExternalEmpowerment")
    public Object zoomBenefitExternalEmpowerment() {
        try {
            JSONObject targetVO = provinceVisualizationDubboService.zoomBenefitExternalEmpowerment();
            return setSuccessModelMap(targetVO);
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
            return setSuccessModelMap(new VisualizedGroupBaseVO<>());
        }
    }

    @Operation(summary = "全网效率评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/efficiencyOverview")
    public Object zoomEfficiencyOverview() {
        VisualizedGroupBaseVO<StatisticEfficiencyOverviewProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.zoomEfficiencyOverview();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "全网工程进度", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/efficiencyProgress")
    public Object zoomEfficiencyProgressSchedule() {
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.zoomEfficiencyProgressSchedule();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "全网投资进度", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/efficiencyInvestment")
    public Object zoomEfficiencyInvestmentSchedule() {
        VisualizedGroupBaseVO<StatisticEfficiencyScheduleProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = provinceVisualizationDubboService.zoomEfficiencyInvestmentSchedule();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }

    @Operation(summary = "全网效果评估总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EffectOverviewDTO.class))))
    @GetMapping("/display/globalEffect")
    public Object displayGlobalEffect() throws Exception {
        EffectOverviewDTO effectOverviewVO = visualOverviewDubboService.getGlobalEffectOverview();
        if (effectOverviewVO == null) {
            return setSuccessModelMap(new EffectOverviewDTO());
        }
        return setSuccessModelMap(effectOverviewVO);
    }

    @Operation(summary = "全网可视化总览", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EvaluationOverviewDTO.class))))
    @GetMapping("/display/globalOverview")
    public Object displayGlobalOverview() throws Exception {
        EvaluationOverviewDTO visualizeResult = visualOverviewDubboService.getEvaluationOverview();
        if (visualizeResult == null) {
            return setSuccessModelMap(new EvaluationOverviewDTO());
        }
        return setSuccessModelMap(visualizeResult);
    }
}