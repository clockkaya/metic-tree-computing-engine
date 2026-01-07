package com.sama.maint.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.core4ct.DTO.UserDTO;
import com.sama.api.ledger.bean.*;
import com.sama.api.ledger.bean.utils.MetricDoubleConverter;
import com.sama.maint.base.BaseMaintController;
import com.sama.maint.common.ExcelMergeReader;
import com.sama.maint.object.vo.ConstructionProjectVO;
import com.sama.maint.object.vo.EngineerProjectVO;
import com.sama.maint.service.WebSocketService;
import com.sama.maint.utils.CustomDateConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

import static com.sama.maint.constants.MetricTableKey.*;

@RestController
@Tag(name = "纵深防御基线")
@RequestMapping("/api/websocket")
public class WebSocketOnlineExcelController extends BaseMaintController {

    @Resource
    private WebSocketService webSocketService;

    @PostMapping(value = "/import-excel",produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object importExcel(@RequestPart("file") @Parameter(description = "上传的文件")  MultipartFile file,
                              @RequestParam("number") @Parameter(description = "文件别名") Integer number) throws Exception {

        UserDTO userDTO = getCurrUser();

        if (file.isEmpty()) {
            throw new RuntimeException("上传的文件不能为空！");
        }

        if (file.getInputStream().available() <= 0) {
            throw new IllegalArgumentException("文件流读取失败");
        }
        // 获取文件扩展名并确定Excel类型
        ExcelTypeEnum excelType = ExcelTypeEnum.XLSX; // 默认使用XLSX
        String filename = file.getOriginalFilename();
        if (filename != null && filename.endsWith(".xls")) {
            excelType = ExcelTypeEnum.XLS;
        }
        //用来传参
        Map<String,Object> sheetMap = new HashMap<>();
        List<EngineerProjectDO> sheetEngineerProject1231 = new ArrayList<>();
        List<EngineerProjectDO> sheetEngineerProject0930 = new ArrayList<>();
        List<EngineerProjectDO> sheetEngineerProject0630 = new ArrayList<>();
        List<ConstructionDO> sheetConstructionProject = new ArrayList<>();
        List<SecurityOperationSystemDO> sheetSecurityOperationSystems= new ArrayList<>();
        List<OperateEfficiencyDO> sheetOperateEfficiency = new ArrayList<>();
        List<ComplianceConstructDO> sheetComplianceConstruct = new ArrayList<>();
        List<BenefitInternalConstructionDO> sheetInternalConstruction = new ArrayList<>();
        List<BenefitExternalEmpowermentDO> sheetExternalEmpowerment = new ArrayList<>();
        List<ComprehensiveProtectionExtendedDO> sheetComprehensiveProtection = new ArrayList<>();

        List<PlannedProjectDO> sheetPlannedProject = new ArrayList<>();

        if(number == 0){
            //全量工程明细表2024/12/31
            sheetEngineerProject1231 = EasyExcel.read(file.getInputStream())
                    .registerConverter(new CustomDateConverter())
                    .excelType(ExcelTypeEnum.XLSX)
                    .head(EngineerProjectDO.class)
                    .doReadAllSync();

        }else if(number == 1){
            //全量工程明细表2024/09/30
            sheetEngineerProject0930 = EasyExcel.read(file.getInputStream())
                    .registerConverter(new CustomDateConverter())
                    .excelType(ExcelTypeEnum.XLSX)
                    .head(EngineerProjectDO.class)
                    .doReadAllSync();

        }else if(number == 2){
            //全量工程明细表2024/06/30
            sheetEngineerProject0630 = EasyExcel.read(file.getInputStream())
                    .registerConverter(new CustomDateConverter())
                    .excelType(ExcelTypeEnum.XLSX)
                    .head(EngineerProjectDO.class)
                    .doReadAllSync();

        }else if(number == 3 ){
            //在建工程明细表
            sheetConstructionProject = EasyExcel.read(file.getInputStream())
                    .registerConverter(new CustomDateConverter())
                    .excelType(ExcelTypeEnum.XLSX)
                    .head(ConstructionDO.class)
                    .doReadAllSync();
        }else if(number == 10 ){
            //安全运营系统，从10开始，隔离防冲突
            try {
                sheetSecurityOperationSystems = ExcelMergeReader.readSheet(file, 0, 3, 4, SecurityOperationSystemDO.class);
                sheetMap.put("SecurityOperationSystem", sheetSecurityOperationSystems);
            }catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == OPERATE_EFFICIENCY_ALIAS ){
            //运营效能-11
            try{
                sheetOperateEfficiency = ExcelMergeReader.readSheet(file, 0, 2, 3,OperateEfficiencyDO.class);
                sheetMap.put(OPERATE_EFFICIENCY,sheetOperateEfficiency);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == COMPLIANCE_CONSTRUCT_ALIAS ) {
            //合规建设-12
            try{
                sheetComplianceConstruct =  ExcelMergeReader.readExcelWithMergeHandling(file, ComplianceConstructDO.class, 0);
                sheetMap.put(COMPLIANCE_CONSTRUCT, sheetComplianceConstruct);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == BENEFIT_INTERNAL_CONSTRUCTION_ALIAS ){
            try{
                sheetInternalConstruction = EasyExcel.read(file.getInputStream())
                        .registerConverter(new MetricDoubleConverter())
                        .excelType(ExcelTypeEnum.XLSX)
                        .sheet()
                        .headRowNumber(8)
                        .head(BenefitInternalConstructionDO.class)
                        .doReadSync();
                sheetMap.put(BENEFIT_INTERNAL_CONSTRUCTION, sheetInternalConstruction);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == BENEFIT_EXTERNAL_EMPOWERMENT_ALIAS ){
            try{
                sheetExternalEmpowerment = EasyExcel.read(file.getInputStream())
                        .registerConverter(new MetricDoubleConverter())
                        .excelType(ExcelTypeEnum.XLSX)
                        .sheet()
                        .headRowNumber(3)
                        .head(BenefitExternalEmpowermentDO.class)
                        .doReadSync();
                sheetMap.put(BENEFIT_EXTERNAL_EMPOWERMENT, sheetExternalEmpowerment);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == COMPREHENSIVE_PROTECTION_ALIAS){
            try{
                sheetComprehensiveProtection = ExcelMergeReader.readExcelWithMergeHandling
                        (file, ComprehensiveProtectionExtendedDO.class, 0, Collections.singletonList("processingData"));
                sheetMap.put(COMPREHENSIVE_PROTECTION, sheetComprehensiveProtection);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }else if(number == 4){
            // 安全能力规模
            List<LedgerScaleDO> sheetScale = ExcelMergeReader.readSheet(file,0, 1,10, LedgerScaleDO.class);
            sheetMap.put("Scale",sheetScale);
            System.out.println("安全能力规模数据：" + sheetScale);

            // 云
            List<CloudDO> sheetClouds = ExcelMergeReader.readSheet(file,1, 4, 8, CloudDO.class);
            sheetMap.put("Cloud",sheetClouds);
            System.out.println("云数据：" + sheetClouds);

            // 核心网5GC
            List<CoreNetwork5GDO> sheetCore5G = ExcelMergeReader.readSheet(file,2, 4, 6, CoreNetwork5GDO.class);
            sheetMap.put("Core5G",sheetCore5G);
            System.out.println("核心网5G数据：" + sheetCore5G);

            // IT和业务平台
            List<ItBusinessPlatformDefenceDO> sheetItBusinessPlatform = ExcelMergeReader.readSheet(file,3, 4, 7, ItBusinessPlatformDefenceDO.class);
            sheetMap.put("IT",sheetItBusinessPlatform);
            System.out.println("IT和业务平台数据：" + sheetItBusinessPlatform);

            // 基础网
            List<BasicNetworkDO> sheetBasic = ExcelMergeReader.readSheet(file,4, 4, 7, BasicNetworkDO.class);
            sheetMap.put("Basic",sheetBasic);

            //核心网非5G
            List<CoreNetworkDO> sheetCore = ExcelMergeReader.readSheet(file,5, 4, 7, CoreNetworkDO.class);
            sheetMap.put("Core",sheetCore);
            System.out.println("核心网非5G数据：" + sheetCore);

        }else if(number == PLANNED_PROJECT_ALIAS){
            try{
                sheetPlannedProject = ExcelMergeReader.readSheet(file,0,2,3,PlannedProjectDO.class);
                sheetMap.put(PLANNED_PROJECT, sheetPlannedProject);
            } catch (Exception e){
                logger.error("捕获小异常一只，堆栈信息如下: ", e);
                excelImportErrorLocation(e);
            }
        }

        webSocketService.upload(userDTO,sheetEngineerProject1231,sheetEngineerProject0930,sheetEngineerProject0630
                , sheetConstructionProject,number,sheetMap);

        return setSuccessModelMapMsg("上传成功");
    }


    @Operation(summary = "模板文件下载")
    @PostMapping(value = "/downloadFile")
    public void downloadImageFile(HttpServletResponse response) throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.downloadFile(response);
    }


    @Operation(summary = "提交全量工程1231", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/submitEngineerProject1231")
    public Object submitEngineerProject1231() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitEngineerProject1231(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }


    @Operation(summary = "提交全量工程0930", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/submitEngineerProject0930")
    public Object submitEngineerProject0930() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitEngineerProject0930(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "提交全量工程0630", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/submitEngineerProject0630")
    public Object submitEngineerProject0630() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitEngineerProject0630(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "提交手动输入表格", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/submitManual")
    public Object submitManual() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitEngineerProject0630(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "提交在建工程", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/submitConstructionProject")
    public Object submitConstructionProject() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitConstructionProject(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "提交工程手动表格", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = ConstructionManualDO.class))))
    @PostMapping(value = "/submitConstructionManualProject")
    public Object submitConstructionManualProject() throws Exception {
        UserDTO userDTO = getCurrUser();
        webSocketService.submitConstructionManualProject(userDTO);

        return setSuccessModelMapMsg("提交成功");
    }

    @Operation(summary = "全量工程页面展示", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = EngineerProjectDO.class))))
    @PostMapping(value = "/engineerProjectPage")
    public Object engineerProjectPage(@RequestBody  EngineerProjectVO engineerProjectVO) throws Exception {
        UserDTO userDTO = getCurrUser();

        return setSuccessModelMap(webSocketService.engineerProjectPage(userDTO,engineerProjectVO));
    }

    @Operation(summary = "在建工程页面展示", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = ConstructionDO.class))))
    @PostMapping(value = "/constructionProjectPage")
    public Object constructionProjectPage(@RequestBody  ConstructionProjectVO constructionDO) throws Exception {
        UserDTO userDTO = getCurrUser();

        return setSuccessModelMap(webSocketService.constructionProjectPage(userDTO,constructionDO));
    }

    @Operation(summary = "在建工程页面展示", responses = @ApiResponse(
            content = @Content(schema = @Schema(implementation = ConstructionDO.class))))
    @PostMapping(value = "/manualPage")
    public Object manualPage(@RequestBody  ConstructionManualDO constructionManualDO) throws Exception {
        UserDTO userDTO = getCurrUser();

        return setSuccessModelMap(webSocketService.manualPage(userDTO,constructionManualDO));
    }

    private void excelImportErrorLocation(Exception e){
        if (e.getCause() instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) e.getCause();
            String cellMsg = "";
            CellData cellData = excelDataConvertException.getCellData();
            CellDataTypeEnum type = cellData.getType();
            if (type.equals(CellDataTypeEnum.NUMBER)) {
                cellMsg = cellData.getNumberValue().toString();
            } else if (type.equals(CellDataTypeEnum.STRING)) {
                cellMsg = cellData.getStringValue();
            } else if (type.equals(CellDataTypeEnum.BOOLEAN)) {
                cellMsg = cellData.getBooleanValue().toString();
            } else if (type.equals(CellDataTypeEnum.ERROR)){
                cellMsg = cellData.getStringValue();
            }
            String errorMsg = String.format("excel表格:第%s行,第%s列,数据值为:%s,该数据值不符合要求,请检验后重新导入!",
                    excelDataConvertException.getRowIndex() + 1, excelDataConvertException.getColumnIndex(), cellMsg);
            logger.error(errorMsg);
        }
    }
}