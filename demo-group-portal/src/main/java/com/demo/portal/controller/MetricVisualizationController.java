package com.sama.officer.controller;

import com.alibaba.fastjson2.JSONObject;
import com.api.analytic.service.GroupVisualizationDubboService;
import com.sama.api.ledger.bean.dto.StatisticBenefitOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticComprehensiveProtectionProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyOverviewProvinceDTO;
import com.sama.api.ledger.bean.dto.StatisticEfficiencyScheduleProvinceDTO;
import com.sama.api.ledger.bean.vo.VisualizedGroupBaseVO;
import com.sama.officer.base.BaseMaintController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/10/23 10:26
 */
@Tag(name = "指标可视化相关接口")
@RestController
@RequestMapping("/metric")
public class MetricVisualizationController extends BaseMaintController {

    @DubboReference
    GroupVisualizationDubboService groupVisualizationDubboService;

    //==============================================================================
    // 全网安全能力成效评估 GroupVisualizationDubboService
    //==============================================================================

    @Operation(summary = "全网综合防护", responses = @ApiResponse(
        content = @Content(schema = @Schema(implementation = VisualizedGroupBaseVO.class))))
    @GetMapping("/zoom/comprehensiveProtection")
    public Object zoomComprehensiveProtection() {
        VisualizedGroupBaseVO<StatisticComprehensiveProtectionProvinceDTO> targetVO = new VisualizedGroupBaseVO<>();
        try {
            targetVO = groupVisualizationDubboService.zoomComprehensiveProtection();
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
            targetVO = groupVisualizationDubboService.zoomBenefitOverview();
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
            JSONObject targetVO = groupVisualizationDubboService.zoomBenefitInternalConstruction();
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
            JSONObject targetVO = groupVisualizationDubboService.zoomBenefitExternalEmpowerment();
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
            targetVO = groupVisualizationDubboService.zoomEfficiencyOverview();
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
            targetVO = groupVisualizationDubboService.zoomEfficiencyProgressSchedule();
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
            targetVO = groupVisualizationDubboService.zoomEfficiencyInvestmentSchedule();
        } catch (Exception e) {
            logger.error("隐藏报错信息如下: ", e);
        }
        return setSuccessModelMap(targetVO);
    }
}
