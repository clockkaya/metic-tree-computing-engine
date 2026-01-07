package com.sama.maint.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.core4ct.DTO.UserDTO;
import com.core4ct.api.gateway.UserRedisService;
import com.core4ct.api.system.object.OrgDO;
import com.core4ct.constants.Constants;
import com.core4ct.constants.GroupConstants;
import com.core4ct.exception.BusinessException;
import com.core4ct.support.Pagination;
import com.core4ct.utils.DataUtils;
import com.core4ct.utils.JwtUtils;
import com.core4ct.utils.OrgCodeUtils;
import com.core4ct.utils.redis.RedisUtils;
import com.sama.api.ledger.bean.*;
import com.sama.api.ledger.service.*;
import com.sama.maint.base.BaseMaintController;
import com.sama.maint.config.MaintConfig;
import com.sama.maint.controller.SpringContextHolder;
import com.sama.maint.object.dto.CellUpdateMessageDTO;
import com.sama.maint.object.dto.ledger.BenefitExternalEmpowermentOnlineBO;
import com.sama.maint.object.dto.ledger.BenefitInternalConstructionOnlineBO;
import com.sama.maint.object.dto.ledger.ComprehensiveProtectionOnlineBO;
import com.sama.maint.object.vo.*;
import com.sama.maint.service.AbilityImageManageService;
import com.sama.maint.service.WebSocketService;
import com.sama.maint.service.organize.TelecomOrgManageService;
import com.sama.maint.utils.BeanConvertBeanUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.core4ct.constants.OrgClassConstants.ORG;
import static com.sama.maint.constants.MetricTableKey.*;

@Service
@ServerEndpoint(value = "/webSocket/{token}", configurator = WebSocketServiceImpl.CustomSpringConfigurator.class)
public class WebSocketServiceImpl extends BaseMaintController implements WebSocketService , InitializingBean {


    // 存储所有在线会话
    private static final Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    // 登录省用户与会话ID的映射关系
    private static final Map<String, List<String>> orgSessionMap = new ConcurrentHashMap<>();

    // 存储文件ID与表格数据的映射关系  tableName -> 表格数据
    private static Map<String, List<Object>> tableDataMap = new ConcurrentHashMap<>();

    // 本地锁，用于同步对Redis的操作
    private static final Lock localLock = new ReentrantLock();

    // 锁前缀
    private static final String LOCK_PREFIX = "cell:lock:";
    // 用户信息前缀
    private static final String USER_PREFIX = "cell:user:";

    @DubboReference
    LedgerConstructionDubboService ledgerConstructionDubboService;

    @DubboReference
    SecurityOperationSystemDubboService securityOperationSystemDubboService;

    @DubboReference
    OperateEfficiencyDubboService operateEfficiencyDubboService;

    @DubboReference
    ComplianceConstructDubboService complianceConstructDubboService;

    @DubboReference
    LedgerEngineerProjectDubboService ledgerEngineerProjectDubboService;

    @DubboReference
    LedgerBasicNetworkDubboService ledgerBasicNetworkDubboService;

    @DubboReference
    LedgerCloudDubboService ledgerCloudDubboService;

    @DubboReference
    LedgerCoreNetworkDubboService ledgerCoreNetworkDubboService;

    @DubboReference
    LedgerCoreNetwork5GDubboService ledgerCoreNetwork5GDubboService;

    @DubboReference
    LedgerScaleDubboService ledgerScaleDubboService;

    @DubboReference
    LedgerConstructionManualDubboService ledgerConstructionManualDubboService;

    @DubboReference
    LedgerItBusinessDefenceDubboService ledgerItBusinessDefenceDubboService;

    @DubboReference
    UserRedisService userRedisService;

    @Resource
    AbilityImageManageService abilityImageManageService;

    @Resource
    TelecomOrgManageService telecomOrgManageService;

    @DubboReference
    BenefitInternalConstructionDubboService benefitInternalConstructionDubboService;

    @DubboReference
    BenefitExternalEmpowermentDubboService benefitExternalEmpowermentDubboService;

    @DubboReference
    ComprehensiveProtectionDubboService comprehensiveProtectionDubboService;

    @DubboReference
    PlannedProjectDubboService plannedProjectDubboService;

    @Resource
    RedisUtils redisUtils;

    public void setTableDataMap(Map<String, List<Object>> data){
        tableDataMap = data;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //loadData();
    }

    @Override
    public String getProvinceCodes(UserDTO userDTO) throws Exception{
        //todo hyg 获取电信组织code
//        List<TeleOrgManageVO> orgCodes = telecomOrgManageService.getTelecomListTree(userDTO, new QueryOrganizeVO(),new OrgDO(),getAllAreaCodesPrivate(GroupConstants.CHINA_TELECOM, userDTO));
//        List<String> orgs = orgCodes.stream().map(TeleOrgManageVO::getOrgCode).collect(Collectors.toList());
//        List<String> provinceCodes = new ArrayList<>();
//        for(String orgCode : orgs){
//            provinceCodes.add(OrgCodeUtils.getCode(orgCode,1));
//        }
//        return provinceCodes.get(0);
        List<String> allAreaCodeOfClass = getAllAreaCodeOfClass(GroupConstants.CHINA_TELECOM,Arrays.asList(ORG), userDTO);
        allAreaCodeOfClass = allAreaCodeOfClass.stream().map(orgCode -> OrgCodeUtils.getCode(orgCode,1)).collect(Collectors.toList());
        return allAreaCodeOfClass.get(0);
    }

    @Override
    public void submitConstructionManualProject(UserDTO userDTO) throws Exception {
        String provinceCode = getProvinceCodes(userDTO);
        String mapKey = "ConstructionManual" + " : " + provinceCode;
        Object fromTableDataMap = tableDataMap.get(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException("没有数据!");
        }
        List<CellUpdateMessageDTO> cellUpdateMessageDTOS = (List<CellUpdateMessageDTO>) fromTableDataMap;
        List<ConstructionManualProjectVO> systemVOS = new ArrayList<>();

        logger.info("------cellUpdateMessageDTO:{}", cellUpdateMessageDTOS.get(0));
        logger.info("-------cellUpdateMessageDTO.getRow():{}", cellUpdateMessageDTOS.get(0).getRow());

        for (CellUpdateMessageDTO cellUpdateMessageDTO : cellUpdateMessageDTOS) {
            ConstructionManualProjectVO systemVO = JSONObject.parseObject(
                    JSON.toJSONString(cellUpdateMessageDTO.getRow()), ConstructionManualProjectVO.class);
            systemVO.setTenantOrgCode(provinceCode);
            systemVOS.add(systemVO);
        }
        List<ConstructionManualDO> systemDOS = BeanConvertBeanUtil.copyListProperties(systemVOS, ConstructionManualDO::new);
        ledgerConstructionManualDubboService.submit(provinceCode,systemDOS,userDTO.getUserId());
    }

    @Override
    public ConstructionManualDO manualPage(UserDTO userDTO, ConstructionManualDO constructionManualDO) throws Exception {
        ConstructionManualDO query = new ConstructionManualDO();
        query.setTenantOrgCode(getProvinceCodes(userDTO));
        query.setDelFlag(Constants.DelFlag.AVAILABLE);

        List<ConstructionManualDO> constructionManualDOS = ledgerConstructionManualDubboService.list(query);
        if(DataUtils.isNotEmpty(constructionManualDOS)){
            return constructionManualDOS.get(0);
        }
        return new ConstructionManualDO();
    }

    private void loadData() throws Exception {
        EngineerProjectDO query1231 = new EngineerProjectDO();
        query1231.setDelFlag(Constants.DelFlag.AVAILABLE);
        query1231.setProjectFileDate("1231");

        List<EngineerProjectDO> sheetEngineerProject1231 = ledgerEngineerProjectDubboService.list(query1231);

        if(DataUtils.isNotEmpty(sheetEngineerProject1231)){

            //按省份分组
            Map<String, List<EngineerProjectDO>> orgCode2ListMap = sheetEngineerProject1231.stream()
                    .collect(Collectors.groupingBy(EngineerProjectDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<EngineerProjectDO> systemDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                systemDOS.sort(Comparator.comparing(EngineerProjectDO::getId));
                List<Object> objectList = convertEngineerProjects(systemDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("EngineerProject1231"+" : "+ orgCode, k -> new ArrayList<>());

                // 把当前 systemDO 添加到对应的 List
                tableDataMap.get("EngineerProject1231"+" : "+ orgCode).addAll(objectList);
            }
        }

//        /***************************************************/
//
//        EngineerProjectDO query0930 = new EngineerProjectDO();
//        query0930.setDelFlag(Constants.DelFlag.AVAILABLE);
//        query0930.setProjectFileDate("0930");
//
//        List<EngineerProjectDO> sheetEngineerProject0930 = ledgerEngineerProjectDubboService.list(query0930);
//
//        if(DataUtils.isNotEmpty(sheetEngineerProject0930)){
//            //按省份分组
//            Map<String, List<EngineerProjectDO>> orgCode2ListMap = sheetEngineerProject0930.stream()
//                    .collect(Collectors.groupingBy(EngineerProjectDO::getTenantOrgCode));
//            for (String orgCode : orgCode2ListMap.keySet()) {
//                List<EngineerProjectDO> systemDOS = orgCode2ListMap.get(orgCode);
//                //按照id排序
//                systemDOS.sort(Comparator.comparing(EngineerProjectDO::getId));
//                List<Object> objectList = convertEngineerProjects(systemDOS);
//                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
//                tableDataMap.computeIfAbsent("EngineerProject0930"+" : "+ orgCode, k -> new ArrayList<>());
//
//                // 把当前 systemDO 添加到对应的 List
//                tableDataMap.get("EngineerProject0930"+" : "+ orgCode).addAll(objectList);
//            }
//        }
//
//        /***************************************************/
//
//        EngineerProjectDO query0630 = new EngineerProjectDO();
//        query0630.setDelFlag(Constants.DelFlag.AVAILABLE);
//        query0630.setProjectFileDate("0630");
//
//        List<EngineerProjectDO> sheetEngineerProject0630 = ledgerEngineerProjectDubboService.list(query0630);
//
//        if(DataUtils.isNotEmpty(sheetEngineerProject0630)) {
//                //按省份分组
//                Map<String, List<EngineerProjectDO>> orgCode2ListMap = sheetEngineerProject0630.stream()
//                        .collect(Collectors.groupingBy(EngineerProjectDO::getTenantOrgCode));
//                for (String orgCode : orgCode2ListMap.keySet()) {
//                    List<EngineerProjectDO> systemDOS = orgCode2ListMap.get(orgCode);
//                    //按照id排序
//                    systemDOS.sort(Comparator.comparing(EngineerProjectDO::getId));
//                    List<Object> objectList = convertEngineerProjects(systemDOS);
//                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
//                    tableDataMap.computeIfAbsent("EngineerProject0630" + " : " + orgCode, k -> new ArrayList<>());
//
//                    // 把当前 systemDO 添加到对应的 List
//                    tableDataMap.get("EngineerProject0630" + " : " + orgCode).addAll(objectList);
//                }
//            }


//            /****************************************************/
//
//            ConstructionDO query = new ConstructionDO();
//            query.setDelFlag(Constants.DelFlag.AVAILABLE);
//
//
//            List<ConstructionDO> constructionDOS = ledgerConstructionDubboService.list(query);
//
//            if (DataUtils.isNotEmpty(constructionDOS)) {
//                //按省份分组
//                Map<String, List<ConstructionDO>> orgCode2ListMap = constructionDOS.stream()
//                        .collect(Collectors.groupingBy(ConstructionDO::getTenantOrgCode));
//                for (String orgCode : orgCode2ListMap.keySet()) {
//                    List<ConstructionDO> systemDOS = orgCode2ListMap.get(orgCode);
//                    //按照id排序
//                    systemDOS.sort(Comparator.comparing(ConstructionDO::getId));
//                    List<Object> objectList = convertConstructionProjects(systemDOS);
//                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
//                    tableDataMap.computeIfAbsent("ConstructionProject" + " : " + orgCode, k -> new ArrayList<>());
//
//                    // 把当前 systemDO 添加到对应的 List
//                    tableDataMap.get("ConstructionProject" + " : " + orgCode).addAll(objectList);
//                }
//            }

            /****************************************************/

        ConstructionManualDO queryManual = new ConstructionManualDO();
        queryManual.setDelFlag(Constants.DelFlag.AVAILABLE);

        List<ConstructionManualDO> constructionManualDOS = ledgerConstructionManualDubboService.list(queryManual);

        if (DataUtils.isNotEmpty(constructionManualDOS)) {
            // 按省份分组
            Map<String, List<ConstructionManualDO>> orgCode2ListMap = constructionManualDOS.stream()
                    .collect(Collectors.groupingBy(ConstructionManualDO::getTenantOrgCode));

            for (String orgCode : orgCode2ListMap.keySet()) {
                List<ConstructionManualDO> systemDOS = orgCode2ListMap.get(orgCode);
                // 按照 id 排序
                systemDOS.sort(Comparator.comparing(ConstructionManualDO::getId));

                List<Object> objectList = convertConstructionManualProjects(systemDOS);
                String mapKey = "ConstructionManual" + " : " + orgCode;

                // 如果 existingMap 没有这个 orgCode，先初始化一个 List，并放一个空 VO
                tableDataMap.computeIfAbsent(mapKey, k -> {
                    List<Object> tempList = new ArrayList<>();
                    tempList.add(createEmptyManualVO());
                    return tempList;
                });

                // 如果有真实数据，则替换掉空 VO
                if (!objectList.isEmpty()) {
                    tableDataMap.put(mapKey, new ArrayList<>(objectList));
                }
            }
        } else {
            String defaultKey = "ConstructionManual : DEFAULT";
            List<Object> emptyList = new ArrayList<>();
            emptyList.add(createEmptyManualVO());
            tableDataMap.put(defaultKey, emptyList);
        }




            /***************************************************/
            //todo hyg 安全运营系统
            SecurityOperationSystemDO securityOperationSystemDOQuery = new SecurityOperationSystemDO();
            securityOperationSystemDOQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<SecurityOperationSystemDO> securityOperationSystemDOS = securityOperationSystemDubboService.list(securityOperationSystemDOQuery);

            if (DataUtils.isNotEmpty(securityOperationSystemDOS)) {
                //按省份分组
                Map<String, List<SecurityOperationSystemDO>> orgCode2ListMap = securityOperationSystemDOS.stream()
                        .collect(Collectors.groupingBy(SecurityOperationSystemDO::getProvinceCode));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<SecurityOperationSystemDO> systemDOS = orgCode2ListMap.get(orgCode);
                    //按照id排序
                    systemDOS.sort(Comparator.comparing(SecurityOperationSystemDO::getId));
                    List<Object> objectList = convertSecurityOperationSystems(systemDOS);
                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                    tableDataMap.computeIfAbsent("SecurityOperationSystem" + " : " + orgCode, k -> new ArrayList<>());

                    // 把当前 systemDO 添加到对应的 List
                    tableDataMap.get("SecurityOperationSystem" + " : " + orgCode).addAll(objectList);
                }
            }


            /***************************************************/
            //wangsf-运营效能
            OperateEfficiencyDO operateEfficiencyQuery = new OperateEfficiencyDO();
            operateEfficiencyQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<OperateEfficiencyDO> operateEfficiencyDOS = operateEfficiencyDubboService.list(operateEfficiencyQuery);

            if (DataUtils.isNotEmpty(operateEfficiencyDOS)) {
                //按省份分组
                Map<String, List<OperateEfficiencyDO>> orgCode2ListMap = operateEfficiencyDOS.stream()
                        .collect(Collectors.groupingBy(OperateEfficiencyDO::getProvince));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<OperateEfficiencyDO> operateDOS = orgCode2ListMap.get(orgCode);
                    //按照id排序
                    operateDOS.sort(Comparator.comparing(OperateEfficiencyDO::getId));
                    List<Object> objectList = convertOperateEfficiency(operateDOS);
                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                    tableDataMap.computeIfAbsent(OPERATE_EFFICIENCY + " : " + orgCode, k -> new ArrayList<>());

                    // 把当前 operateDO 添加到对应的 List
                    tableDataMap.get(OPERATE_EFFICIENCY + " : " + orgCode).addAll(objectList);
                }
            }


            /***************************************************/
            //wangsf-合规建设
            ComplianceConstructDO complianceConstructQuery = new ComplianceConstructDO();
            complianceConstructQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<ComplianceConstructDO> complianceConstructDOS = complianceConstructDubboService.list(complianceConstructQuery);

            if (DataUtils.isNotEmpty(complianceConstructDOS)) {
                //按省份分组
                Map<String, List<ComplianceConstructDO>> orgCode2ListMap = complianceConstructDOS.stream()
                        .collect(Collectors.groupingBy(ComplianceConstructDO::getProvince));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<ComplianceConstructDO> operateDOS = orgCode2ListMap.get(orgCode);
                    //按照id排序
                    operateDOS.sort(Comparator.comparing(ComplianceConstructDO::getId));
                    List<Object> objectList = convertComplianceConstruct(operateDOS);
                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                    tableDataMap.computeIfAbsent(COMPLIANCE_CONSTRUCT + " : " + orgCode, k -> new ArrayList<>());

                    // 把当前 systemDO 添加到对应的 List
                    tableDataMap.get(COMPLIANCE_CONSTRUCT + " : " + orgCode).addAll(objectList);
                }
            }


            /***************************************************/

            BenefitInternalConstructionDO benefitInternalQuery = new BenefitInternalConstructionDO();
            benefitInternalQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<BenefitInternalConstructionDO> benefitInternalRes = benefitInternalConstructionDubboService.list(benefitInternalQuery);

            if (DataUtils.isNotEmpty(benefitInternalRes)) {
                Map<String, List<BenefitInternalConstructionDO>> orgCode2ListMap = benefitInternalRes.stream()
                        .collect(Collectors.groupingBy(BenefitInternalConstructionDO::getOrgCode));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<BenefitInternalConstructionDO> rawList = orgCode2ListMap.get(orgCode);
                    rawList.sort(Comparator.comparing(BenefitInternalConstructionDO::getId));
                    List<Object> objectList = convertBenefitInternalConstruction(rawList);
                    tableDataMap.computeIfAbsent(BENEFIT_INTERNAL_CONSTRUCTION + " : " + orgCode, k -> new ArrayList<>());
                    tableDataMap.get(BENEFIT_INTERNAL_CONSTRUCTION + " : " + orgCode).addAll(objectList);
                }
            }

            BenefitExternalEmpowermentDO benefitExternalQuery = new BenefitExternalEmpowermentDO();
            benefitExternalQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<BenefitExternalEmpowermentDO> benefitExternalRes = benefitExternalEmpowermentDubboService.list(benefitExternalQuery);

            if (DataUtils.isNotEmpty(benefitExternalRes)) {
                Map<String, List<BenefitExternalEmpowermentDO>> orgCode2ListMap = benefitExternalRes.stream()
                        .collect(Collectors.groupingBy(BenefitExternalEmpowermentDO::getOrgCode));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<BenefitExternalEmpowermentDO> rawList = orgCode2ListMap.get(orgCode);
                    rawList.sort(Comparator.comparing(BenefitExternalEmpowermentDO::getId));
                    List<Object> objectList = convertBenefitExternalEmpowerment(rawList);
                    tableDataMap.computeIfAbsent(BENEFIT_EXTERNAL_EMPOWERMENT + " : " + orgCode, k -> new ArrayList<>());
                    tableDataMap.get(BENEFIT_EXTERNAL_EMPOWERMENT + " : " + orgCode).addAll(objectList);
                }
            }


            /***************************安全规模***********************/
            LedgerScaleDO ledgerScaleDO = new LedgerScaleDO();
            ledgerScaleDO.setDelFlag(Constants.DelFlag.AVAILABLE);
            List<LedgerScaleDO> ledgerScaleDOS = ledgerScaleDubboService.list(ledgerScaleDO);

            if (DataUtils.isNotEmpty(ledgerScaleDOS)) {
                //按省份分组
                Map<String, List<LedgerScaleDO>> orgCode2ListMap = ledgerScaleDOS.stream()
                        .collect(Collectors.groupingBy(LedgerScaleDO::getTenantOrgCode));
                for (String orgCode : orgCode2ListMap.keySet()) {
                    List<LedgerScaleDO> operateDOS = orgCode2ListMap.get(orgCode);
                    //按照id排序
                    operateDOS.sort(Comparator.comparing(LedgerScaleDO::getId));
                    List<Object> objectList = convertScale(operateDOS);
                    // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                    tableDataMap.computeIfAbsent("Scale" + " : " + orgCode, k -> new ArrayList<>());

                    // 把当前 operateDO 添加到对应的 List
                    tableDataMap.get("Scale" + " : " + orgCode).addAll(objectList);
                }
            }

        /***************************云***********************/
        CloudDO cloudDO = new CloudDO();
        cloudDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<CloudDO> cloudDOS = ledgerCloudDubboService.list(cloudDO);

        if (DataUtils.isNotEmpty(cloudDOS)) {
            //按省份分组
            Map<String, List<CloudDO>> orgCode2ListMap = cloudDOS.stream()
                    .collect(Collectors.groupingBy(CloudDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<CloudDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(CloudDO::getId));
                List<Object> objectList = convertCloud(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("Cloud" + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 operateDO 添加到对应的 List
                tableDataMap.get("Cloud" + " : " + orgCode).addAll(objectList);
            }
        }

        /***************************基础网***********************/
        BasicNetworkDO basicNetworkDO = new BasicNetworkDO();
        basicNetworkDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<BasicNetworkDO> basicNetworkDOS = ledgerBasicNetworkDubboService.list(basicNetworkDO);

        if (DataUtils.isNotEmpty(basicNetworkDOS)) {
            //按省份分组
            Map<String, List<BasicNetworkDO>> orgCode2ListMap = basicNetworkDOS.stream()
                    .collect(Collectors.groupingBy(BasicNetworkDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<BasicNetworkDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(BasicNetworkDO::getId));
                List<Object> objectList = convertBasic(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("Basic" + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 operateDO 添加到对应的 List
                tableDataMap.get("Basic" + " : " + orgCode).addAll(objectList);
            }
        }

        /***************************核心网***********************/
        CoreNetworkDO coreNetworkDO = new CoreNetworkDO();
        coreNetworkDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<CoreNetworkDO> coreNetworkDOS = ledgerCoreNetworkDubboService.list(coreNetworkDO);

        if (DataUtils.isNotEmpty(coreNetworkDOS)) {
            //按省份分组
            Map<String, List<CoreNetworkDO>> orgCode2ListMap = coreNetworkDOS.stream()
                    .collect(Collectors.groupingBy(CoreNetworkDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<CoreNetworkDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(CoreNetworkDO::getId));
                List<Object> objectList = convertCore(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("Core" + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 operateDO 添加到对应的 List
                tableDataMap.get("Core" + " : " + orgCode).addAll(objectList);
            }
        }

        /***************************核心网5G***********************/
        CoreNetwork5GDO coreNetwork5GDO = new CoreNetwork5GDO();
        coreNetwork5GDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<CoreNetwork5GDO> coreNetwork5GDOS = ledgerCoreNetwork5GDubboService.list(coreNetwork5GDO);

        if (DataUtils.isNotEmpty(coreNetworkDOS)) {
            //按省份分组
            Map<String, List<CoreNetwork5GDO>> orgCode2ListMap = coreNetwork5GDOS.stream()
                    .collect(Collectors.groupingBy(CoreNetwork5GDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<CoreNetwork5GDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(CoreNetwork5GDO::getId));
                List<Object> objectList = convertCore5G(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("Core5G" + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 operateDO 添加到对应的 List
                tableDataMap.get("Core5G" + " : " + orgCode).addAll(objectList);
            }
        }

        /***************************核心网***********************/
        ItBusinessPlatformDefenceDO itBusinessPlatformDefenceDO = new ItBusinessPlatformDefenceDO();
        itBusinessPlatformDefenceDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ItBusinessPlatformDefenceDO> itBusinessPlatformDefenceDOS = ledgerItBusinessDefenceDubboService.list(itBusinessPlatformDefenceDO);

        if (DataUtils.isNotEmpty(itBusinessPlatformDefenceDOS)) {
            //按省份分组
            Map<String, List<ItBusinessPlatformDefenceDO>> orgCode2ListMap = itBusinessPlatformDefenceDOS.stream()
                    .collect(Collectors.groupingBy(ItBusinessPlatformDefenceDO::getTenantOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<ItBusinessPlatformDefenceDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(ItBusinessPlatformDefenceDO::getId));
                List<Object> objectList = convertItBusiness(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent("IT" + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 operateDO 添加到对应的 List
                tableDataMap.get("IT" + " : " + orgCode).addAll(objectList);
            }
        }


        ComprehensiveProtectionExtendedDO comprehensiveProtectionQuery = new ComprehensiveProtectionExtendedDO();
        comprehensiveProtectionQuery.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<ComprehensiveProtectionExtendedDO> comprehensiveRes = comprehensiveProtectionDubboService.list(comprehensiveProtectionQuery);

        if(DataUtils.isNotEmpty(comprehensiveRes)){
            Map<String, List<ComprehensiveProtectionExtendedDO>> orgCode2ListMap = comprehensiveRes.stream()
                    .collect(Collectors.groupingBy(ComprehensiveProtectionExtendedDO::getOrgCode));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<ComprehensiveProtectionExtendedDO> rawList = orgCode2ListMap.get(orgCode);
                rawList.sort(Comparator.comparing(ComprehensiveProtectionExtendedDO::getId));
                List<Object> objectList = convertComprehensiveProtection(rawList);
                tableDataMap.computeIfAbsent(COMPREHENSIVE_PROTECTION + " : " + orgCode, k -> new ArrayList<>());
                tableDataMap.get(COMPREHENSIVE_PROTECTION + " : " + orgCode).addAll(objectList);
            }
        }

        /***************************************************/
        //规划项目清单
        PlannedProjectDO plannedProjectDO = new PlannedProjectDO();
        plannedProjectDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<PlannedProjectDO> plannedProjectDOS = plannedProjectDubboService.list(plannedProjectDO);

        if (DataUtils.isNotEmpty(plannedProjectDOS)) {
            //按省份分组
            Map<String, List<PlannedProjectDO>> orgCode2ListMap = plannedProjectDOS.stream()
                    .collect(Collectors.groupingBy(PlannedProjectDO::getProvince));
            for (String orgCode : orgCode2ListMap.keySet()) {
                List<PlannedProjectDO> operateDOS = orgCode2ListMap.get(orgCode);
                //按照id排序
                operateDOS.sort(Comparator.comparing(PlannedProjectDO::getId));
                List<Object> objectList = convertPlannedProject(operateDOS);
                // 如果 existingMap 里没有这个 orgCode，就初始化一个空 List
                tableDataMap.computeIfAbsent(PLANNED_PROJECT + " : " + orgCode, k -> new ArrayList<>());

                // 把当前 systemDO 添加到对应的 List
                tableDataMap.get(PLANNED_PROJECT + " : " + orgCode).addAll(objectList);
            }
        }

    }

    private Object createEmptyManualVO() {
        ConstructionManualProjectVO vo = new ConstructionManualProjectVO();
        vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
        vo.setLocks(new HashMap<>());

        CellUpdateMessageDTO message = new CellUpdateMessageDTO();
        message.setRow(vo);
        return message;
    }

    @Override
    public WebSocketResponseVO<?> initConnection(UserDTO userDTO) {
        try {
            String provinceCode = getProvinceCodes(userDTO);
            // 初始化文件相关数据结构
            //TODO这个初始化需要把所有的table都初始化
            tableDataMap.computeIfAbsent("EngineerProject1231"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
//            tableDataMap.computeIfAbsent("EngineerProject0930"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
//            tableDataMap.computeIfAbsent("EngineerProject0630"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
//            tableDataMap.computeIfAbsent("ConstructionProject"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("ConstructionManual"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            //todo hyg 安全运营系统
            tableDataMap.computeIfAbsent("SecurityOperationSystem"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            //纵深防御
            tableDataMap.computeIfAbsent("Scale"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("Cloud"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("Basic"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("IT"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("Core"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent("Core5G"+" : "+ provinceCode, k -> new CopyOnWriteArrayList<>());

            //运营效能和合规建设
            tableDataMap.computeIfAbsent(OPERATE_EFFICIENCY + " : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent(COMPLIANCE_CONSTRUCT + " : "+ provinceCode, k -> new CopyOnWriteArrayList<>());

            tableDataMap.computeIfAbsent(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode, k -> new CopyOnWriteArrayList<>());
            tableDataMap.computeIfAbsent(COMPREHENSIVE_PROTECTION + " : "+ provinceCode, k -> new CopyOnWriteArrayList<>());


//            // 初始化表格数据（如果是首次访问该文件）
//            if (tableDataMap.get("EngineerProject1231" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject1231"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("EngineerProject0930" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject0930"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("EngineerProject0630" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject0630"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("ConstructionProject" +" : "+ provinceCode).isEmpty()) {
//                initTableData("ConstructionProject"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("ConstructionManual" +" : "+ provinceCode).isEmpty()) {
//                initTableData("ConstructionManual"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("SecurityOperationSystem" +" : "+ provinceCode).isEmpty()) {
//                initTableData("SecurityOperationSystem"+" : "+ provinceCode);
//            }
//            //纵深防御
//            if (tableDataMap.get("Scale" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Scale"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Cloud" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Cloud"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Basic" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Basic"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("IT" +" : "+ provinceCode).isEmpty()) {
//                initTableData("IT"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Core" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Core"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Core5G" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Core5G"+" : "+ provinceCode);
//            }
//
//            //todo hyg 安全运营系统
//
//            //wangsf-运营效能和合规建设
//            if (tableDataMap.get(OPERATE_EFFICIENCY + " : "+ provinceCode).isEmpty()) {
//                initTableData(OPERATE_EFFICIENCY + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(COMPLIANCE_CONSTRUCT + " : "+ provinceCode).isEmpty()) {
//                initTableData(COMPLIANCE_CONSTRUCT + " : "+ provinceCode);
//            }
//
//            if (tableDataMap.get(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode).isEmpty()) {
//                initTableData(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode).isEmpty()) {
//                initTableData(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(COMPREHENSIVE_PROTECTION + " : "+ provinceCode).isEmpty()) {
//                initTableData(COMPREHENSIVE_PROTECTION + " : "+ provinceCode);
//            }

//            Map<String, Object> result = new HashMap<>();
//            //todo hyg 存入tableDataMap
//            result.put("EngineerProject1231", tableDataMap.get("EngineerProject1231"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));


//            // 初始化表格数据（如果是首次访问该文件）
//            if (tableDataMap.get("EngineerProject1231" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject1231"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("EngineerProject0930" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject0930"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("EngineerProject0630" +" : "+ provinceCode).isEmpty()) {
//                initTableData("EngineerProject0630"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("ConstructionProject" +" : "+ provinceCode).isEmpty()) {
//                initTableData("ConstructionProject"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("ConstructionManual" +" : "+ provinceCode).isEmpty()) {
//                initTableData("ConstructionManual"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("SecurityOperationSystem" +" : "+ provinceCode).isEmpty()) {
//                initTableData("SecurityOperationSystem"+" : "+ provinceCode);
//            }
//            //纵深防御
//            if (tableDataMap.get("Scale" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Scale"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Cloud" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Cloud"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Basic" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Basic"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("IT" +" : "+ provinceCode).isEmpty()) {
//                initTableData("IT"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Core" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Core"+" : "+ provinceCode);
//            }
//            if (tableDataMap.get("Core5G" +" : "+ provinceCode).isEmpty()) {
//                initTableData("Core5G"+" : "+ provinceCode);
//            }

//            //todo hyg 安全运营系统
//
//            //wangsf-运营效能和合规建设
//            if (tableDataMap.get(OPERATE_EFFICIENCY + " : "+ provinceCode).isEmpty()) {
//                initTableData(OPERATE_EFFICIENCY + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(COMPLIANCE_CONSTRUCT + " : "+ provinceCode).isEmpty()) {
//                initTableData(COMPLIANCE_CONSTRUCT + " : "+ provinceCode);
//            }
//
//            if (tableDataMap.get(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode).isEmpty()) {
//                initTableData(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode).isEmpty()) {
//                initTableData(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode);
//            }
//            if (tableDataMap.get(EFFECT_COMPREHENSIVE_PROTECTION + " : "+ provinceCode).isEmpty()) {
//                initTableData(EFFECT_COMPREHENSIVE_PROTECTION + " : "+ provinceCode);
//            }

//            Map<String, Object> result = new HashMap<>();
//            //todo hyg 存入tableDataMap
//            result.put("EngineerProject1231", tableDataMap.get("EngineerProject1231"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("EngineerProject0930", tableDataMap.get("EngineerProject0930"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("EngineerProject0630", tableDataMap.get("EngineerProject0630"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("ConstructionProject", tableDataMap.get("ConstructionProject"+" : "+ getProvinceCodes(userDTO)).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("ConstructionManual", tableDataMap.get("ConstructionManual"+" : "+ getProvinceCodes(userDTO)).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("SecurityOperationSystem", tableDataMap.get("SecurityOperationSystem"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put("Scale", tableDataMap.get("Scale"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            result.put("Cloud", tableDataMap.get("Cloud"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            result.put("Basic", tableDataMap.get("Basic"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            result.put("IT", tableDataMap.get("IT"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            result.put("Core", tableDataMap.get("Core"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            result.put("Core5G", tableDataMap.get("Core5G"+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//            //todo hyg 安全运营系统
//            //wangsf-运营效能和合规建设
//            result.put(OPERATE_EFFICIENCY, tableDataMap.get(OPERATE_EFFICIENCY+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put(COMPLIANCE_CONSTRUCT, tableDataMap.get(COMPLIANCE_CONSTRUCT+" : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put(EFFECT_COMPREHENSIVE_PROTECTION, tableDataMap.get(EFFECT_COMPREHENSIVE_PROTECTION + " : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put(BENEFIT_INTERNAL_CONSTRUCTION, tableDataMap.get(BENEFIT_INTERNAL_CONSTRUCTION + " : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));
//
//            result.put(BENEFIT_EXTERNAL_EMPOWERMENT, tableDataMap.get(BENEFIT_EXTERNAL_EMPOWERMENT + " : "+ provinceCode).stream()
//                    .map(obj -> {
//                        if (obj instanceof CellUpdateMessageDTO) {
//                            return ((CellUpdateMessageDTO) obj).getRow();
//                        }
//                        throw new IllegalArgumentException("不支持的输入类型");
//                    })
//                    .collect(Collectors.toList()));

            return new WebSocketResponseVO<>(200, "初始化成功","init",new Object());
        } catch (Exception e) {
            e.printStackTrace();
            return new WebSocketResponseVO<>(500, "初始化失败");
        }
    }



    @Override
    public WebSocketResponseVO<?> addRow(String sheetName, Object row, UserDTO userDTO) throws Exception {
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);

        try {
            localLock.lock(); // 加锁保证线程安全

            // 1. 获取或初始化表格数据
            List<Object> tableData = tableDataMap.computeIfAbsent(mapKey, k -> new CopyOnWriteArrayList<>());

            // 2. 创建CellUpdateMessageDTO包装器
            CellUpdateMessageDTO newRow = new CellUpdateMessageDTO();
            newRow.setSheetName(sheetName);

            // 3. 根据sheetName处理不同类型的row
            Object rowData;
            if (sheetName.startsWith("EngineerProject")) {
                EngineerProjectVO vo = new EngineerProjectVO();
                safeCopyProperties(vo, row); // 复制属性
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>()); // 初始化锁
                rowData = vo;
            }
            else if (sheetName.equals("ConstructionProject")) {
                ConstructionProjectVO vo = new ConstructionProjectVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("Manual")) {
                EngineerManualProjectVO vo = new EngineerManualProjectVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            //todo hyg 安全运营系统
            else if (sheetName.equals("SecurityOperationSystem")) {
                SecurityOperationSystemVO vo = new SecurityOperationSystemVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            //wangsf-运营效能和合规建设
            else if (sheetName.equals(OPERATE_EFFICIENCY)) {
                OperateEfficiencyVO vo = new OperateEfficiencyVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals(COMPLIANCE_CONSTRUCT)) {
                ComplianceConstructVO vo = new ComplianceConstructVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals(BENEFIT_INTERNAL_CONSTRUCTION)) {
                BenefitInternalConstructionOnlineBO onlineBO = new BenefitInternalConstructionOnlineBO();
                safeCopyProperties(onlineBO, row);
                onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                onlineBO.setLocks(new HashMap<>());
                rowData = onlineBO;
            }
            else if (sheetName.equals(BENEFIT_EXTERNAL_EMPOWERMENT)) {
                BenefitExternalEmpowermentOnlineBO onlineBO = new BenefitExternalEmpowermentOnlineBO();
                safeCopyProperties(onlineBO, row);
                onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                onlineBO.setLocks(new HashMap<>());
                rowData = onlineBO;
            }
            else if (sheetName.equals(COMPREHENSIVE_PROTECTION)) {
                ComprehensiveProtectionOnlineBO onlineBO = new ComprehensiveProtectionOnlineBO();
                safeCopyProperties(onlineBO, row);
                onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                onlineBO.setLocks(new HashMap<>());
                rowData = onlineBO;
            }
            else if (sheetName.equals("Scale")) {
                ScaleVO vo = new ScaleVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("Cloud")) {
                CloudVO vo = new CloudVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("Core5G")) {
                LedgerCoreNetwork5GDefenceVO vo = new LedgerCoreNetwork5GDefenceVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("Core")) {
                LedgerCoreNetworkDefenceVO vo = new LedgerCoreNetworkDefenceVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("IT")) {
                LedgerItBusinessDefenceVO vo = new LedgerItBusinessDefenceVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else if (sheetName.equals("Basic")) {
                LedgerBasicNetworkDefenceVO vo = new LedgerBasicNetworkDefenceVO();
                safeCopyProperties(vo, row);
                vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
                vo.setLocks(new HashMap<>());
                rowData = vo;
            }
            else {
                throw new IllegalArgumentException("不支持的sheet类型: " + sheetName);
            }
            //todo hyg 安全运营系统

            newRow.setRow(rowData);

            // 4. 添加新行
            tableData.add(newRow);

            List<Object> result = tableData.stream()
                    .map(obj -> {
                        if (obj instanceof CellUpdateMessageDTO) {
                            return ((CellUpdateMessageDTO) obj).getRow();
                        }
                        throw new IllegalArgumentException("不支持的输入类型");
                    })
                    .collect(Collectors.toList());

            // 5. 通知所有客户端
            sendToAllInRoom( userDTO,
                    new WebSocketResponseVO<>(200, "行添加成功", "rowAdd", result, sheetName)
            );

            return new WebSocketResponseVO<>(200, "行添加成功");

        } catch (Exception e) {
            logger.error("添加行失败 - sheet: {}, user: {}", sheetName, userDTO.getUserId(), e);
            return new WebSocketResponseVO<>(500, "添加失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }


    @Override
    public WebSocketResponseVO<?> deleteRow(String sheetName, Object newData, UserDTO userDTO) throws Exception {
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);

        // 1. 从newData中提取infoId并设为final
        String infoId = (String)JSONObject.from(newData).get("infoId");


        try {
            localLock.lock();

            // 2. 获取当前表数据
            List<Object> currentTableData = tableDataMap.get(mapKey);
            if (currentTableData == null || currentTableData.isEmpty()) {
                return new WebSocketResponseVO<>(400, "未找到对应的表格数据");
            }

            // 3. 查找并删除匹配行
            boolean removed = currentTableData.removeIf(item -> {
                if (!(item instanceof CellUpdateMessageDTO)) {
                    return false;
                }

                CellUpdateMessageDTO cellMessage = (CellUpdateMessageDTO) item;
                Object rowData = cellMessage.getRow();

                // 使用final的infoId变量
                return infoId.equals((String)JSONObject.from(rowData).get("infoId"));
            });

            if (!removed) {
                return new WebSocketResponseVO<>(400, "未找到匹配的infoId记录");
            }

            // 4. 更新数据
            tableDataMap.put(mapKey, currentTableData);

            List<Object> result = currentTableData.stream()
                    .map(obj -> {
                        if (obj instanceof CellUpdateMessageDTO) {
                            return ((CellUpdateMessageDTO) obj).getRow();
                        }
                        throw new IllegalArgumentException("不支持的输入类型");
                    })
                    .collect(Collectors.toList());

            if(DataUtils.isEmpty(result)){
                result = new ArrayList<>();
            }

            // 5. 通知所有客户端
            sendToAllInRoom(userDTO,
                    new WebSocketResponseVO<>(200, "删除成功", "rowDelete", result, sheetName)
            );

            return new WebSocketResponseVO<>(200, "删除成功");

        } catch (Exception e) {
            logger.error("删除行失败 - sheet: {}, userId: {}", sheetName, userDTO.getUserId(), e);
            return new WebSocketResponseVO<>(500, "删除失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }

    @Override
    public WebSocketResponseVO<?> lockCell(CellUpdateMessageDTO message, UserDTO userDTO) throws Exception {
        String sheetName = message.getSheetName();
        String userKey = buildUserKey(sheetName, message.getRowIndex(), message.getColProp());
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);

        try {
            localLock.lock();

            List<Object> rawTableData = tableDataMap.get(mapKey);
            if (rawTableData == null || rawTableData.isEmpty()) {
                return new WebSocketResponseVO<>(400, "未找到表格数据");
            }

            // 克隆消息用于广播
            CellUpdateMessageDTO broadcastMsg = cloneMessage(message);
            sendToAllInRoom( userDTO,
                    new WebSocketResponseVO<>(200, "锁定成功", "cellLock", broadcastMsg,sheetName)
            );

            return new WebSocketResponseVO<>(200, "锁定成功");

        } catch (Exception e) {
            logger.error("锁定失败", e);
            return new WebSocketResponseVO<>(500, "锁定失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }

    // 通用的项目锁处理方法
    private boolean handleProjectLock(Object project, String colProp, Long userId) {
        try {
            if (project instanceof EngineerProjectVO) {
                EngineerProjectVO engineerProject = (EngineerProjectVO) project;
                if (engineerProject.getLocks() == null) {
                    engineerProject.setLocks(new HashMap<>());
                }
                engineerProject.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof ConstructionProjectVO) {
                ConstructionProjectVO constructionProject = (ConstructionProjectVO) project;
                if (constructionProject.getLocks() == null) {
                    constructionProject.setLocks(new HashMap<>());
                }
                constructionProject.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof SecurityOperationSystemVO) {
                SecurityOperationSystemVO securityOperationSystemVO = (SecurityOperationSystemVO) project;
                if (securityOperationSystemVO.getLocks() == null) {
                    securityOperationSystemVO.setLocks(new HashMap<>());
                }
                securityOperationSystemVO.getLocks().put(colProp, userId);
                return true;
            }
            //wangsf-运营效能和合规建设
            else if (project instanceof OperateEfficiencyVO) {
                OperateEfficiencyVO operateEfficiencyVO = (OperateEfficiencyVO) project;
                if (operateEfficiencyVO.getLocks() == null) {
                    operateEfficiencyVO.setLocks(new HashMap<>());
                }
                operateEfficiencyVO.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof ComplianceConstructVO) {
                ComplianceConstructVO complianceConstructVO = (ComplianceConstructVO) project;
                if (complianceConstructVO.getLocks() == null) {
                    complianceConstructVO.setLocks(new HashMap<>());
                }
                complianceConstructVO.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof BenefitInternalConstructionOnlineBO) {
                BenefitInternalConstructionOnlineBO onlineBO = (BenefitInternalConstructionOnlineBO) project;
                if (onlineBO.getLocks() == null) {
                    onlineBO.setLocks(new HashMap<>());
                }
                onlineBO.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof BenefitExternalEmpowermentOnlineBO) {
                BenefitExternalEmpowermentOnlineBO onlineBO = (BenefitExternalEmpowermentOnlineBO) project;
                if (onlineBO.getLocks() == null) {
                    onlineBO.setLocks(new HashMap<>());
                }
                onlineBO.getLocks().put(colProp, userId);
                return true;
            }
            else if (project instanceof ComprehensiveProtectionOnlineBO) {
                ComprehensiveProtectionOnlineBO onlineBO = (ComprehensiveProtectionOnlineBO) project;
                if (onlineBO.getLocks() == null) {
                    onlineBO.setLocks(new HashMap<>());
                }
                onlineBO.getLocks().put(colProp, userId);
                return true;
            }

            //todo hyg 安全运营系统

            return false;
        } catch (Exception e) {
            logger.error("处理项目锁失败", e);
            return false;
        }
    }

    private CellUpdateMessageDTO cloneMessage(CellUpdateMessageDTO original) {
        CellUpdateMessageDTO clone = new CellUpdateMessageDTO();
        // 复制所有简单字段
        clone.setSheetName(original.getSheetName());
        clone.setRowIndex(original.getRowIndex());
        clone.setColProp(original.getColProp());
        clone.setValue(original.getValue());
        clone.setUserId(original.getUserId());

        // 深拷贝row对象
        if (original.getRow() instanceof Map) {
            Map<String, Object> originalRow = (Map<String, Object>) original.getRow();
            Map<String, Object> newRow = new HashMap<>(originalRow);
            clone.setRow(newRow);
        } else {
            clone.setRow(original.getRow());
        }

        return clone;
    }

    private static <T> List<T> convertList(List<?> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(source -> {
                    try {
                        T target = targetClass.getDeclaredConstructor().newInstance();
                        BeanUtils.copyProperties(source, target);  // Spring BeanUtils
                        return target;
                    } catch (Exception e) {
                        throw new RuntimeException("转换失败", e);
                    }
                })
                .collect(Collectors.toList());
    }

    private String buildUserKey(String sheetName, int row, String colProp) {
        return USER_PREFIX +  ":" + sheetName + ":" + row + ":" + colProp;
    }

    @Override
    public WebSocketResponseVO<?> unlockCell(CellUpdateMessageDTO message, UserDTO userDTO) throws Exception {
        String sheetName = message.getSheetName();
        String userKey = buildUserKey(sheetName, message.getRowIndex(), message.getColProp());
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);

        try {
            localLock.lock();

            List<Object> rawTableData = tableDataMap.get(mapKey);
            if (rawTableData == null || rawTableData.isEmpty()) {
                return new WebSocketResponseVO<>(400, "未找到表格数据");
            }

            // 克隆消息用于广播
            CellUpdateMessageDTO broadcastMsg = cloneMessage(message);
            sendToAllInRoom( userDTO,
                    new WebSocketResponseVO<>(200, "解锁成功", "cellUnlock", broadcastMsg,sheetName)
            );

            return new WebSocketResponseVO<>(200, "解锁成功");

        } catch (Exception e) {
            logger.error("锁定失败", e);
            return new WebSocketResponseVO<>(500, "解锁失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }

    // 通用的项目解锁处理方法
    private boolean handleProjectUnlock(Object project, String colProp) {
        try {
            if (project instanceof EngineerProjectVO) {
                EngineerProjectVO engineerProject = (EngineerProjectVO) project;
                if (engineerProject.getLocks() != null) {
                    engineerProject.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof ConstructionProjectVO) {
                ConstructionProjectVO constructionProject = (ConstructionProjectVO) project;
                if (constructionProject.getLocks() != null) {
                    constructionProject.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof SecurityOperationSystemVO) {
                SecurityOperationSystemVO vo = (SecurityOperationSystemVO) project;
                if (vo.getLocks() != null) {
                    vo.getLocks().remove(colProp);
                    return true;
                }
            }
            //wangsf-运营效能和合规建设
            else if (project instanceof OperateEfficiencyVO) {
                OperateEfficiencyVO vo = (OperateEfficiencyVO) project;
                if (vo.getLocks() != null) {
                    vo.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof ComplianceConstructVO) {
                ComplianceConstructVO vo = (ComplianceConstructVO) project;
                if (vo.getLocks() != null) {
                    vo.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof BenefitInternalConstructionOnlineBO) {
                BenefitInternalConstructionOnlineBO onlineBO = (BenefitInternalConstructionOnlineBO) project;
                if (onlineBO.getLocks() != null) {
                    onlineBO.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof BenefitExternalEmpowermentOnlineBO) {
                BenefitExternalEmpowermentOnlineBO onlineBO = (BenefitExternalEmpowermentOnlineBO) project;
                if (onlineBO.getLocks() != null) {
                    onlineBO.getLocks().remove(colProp);
                    return true;
                }
            }
            else if (project instanceof ComprehensiveProtectionOnlineBO) {
                ComprehensiveProtectionOnlineBO onlineBO = (ComprehensiveProtectionOnlineBO) project;
                if (onlineBO.getLocks() != null) {
                    onlineBO.getLocks().remove(colProp);
                    return true;
                }
            }

            //todo hyg 安全运营系统
            return false;
        } catch (Exception e) {
            logger.error("处理项目解锁失败", e);
            return false;
        }
    }

    @Override
    public WebSocketResponseVO<?> updateCell(String sheetName, Object newData,UserDTO userDTO) throws Exception {
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);

        String infoId = (String)JSONObject.from(newData).get("infoId");

        try {
            localLock.lock(); // 保证线程安全

            // 1. 获取表格数据
            List<Object> rawTableData = tableDataMap.get(mapKey);
            if (rawTableData == null || rawTableData.isEmpty()) {
                return new WebSocketResponseVO<>(400, "未找到对应的表格数据");
            }

            // 2. 查找目标行（根据sheetName匹配类型，并检查row中的infoId）
            boolean updated = false;
            CellUpdateMessageDTO changedRow = null;

            for (Object item : rawTableData) {
                if (item instanceof CellUpdateMessageDTO) {
                    CellUpdateMessageDTO cellMessage = (CellUpdateMessageDTO) item;
                    Object rowData = cellMessage.getRow();

                    // 检查infoId是否匹配
                    boolean isMatch = ((String)JSONObject.from(rowData).get("infoId")).equals(infoId);


                    // 匹配成功则更新数据
                    if (isMatch) {
                        // 验证新数据类型是否匹配

                            cellMessage.setRow(newData);
                            updated = true;

                            // 准备广播消息
                            changedRow = new CellUpdateMessageDTO();
                            changedRow.setSheetName(sheetName);
                            changedRow.setRowIndex(cellMessage.getRowIndex());
                            changedRow.setColProp(cellMessage.getColProp());
                            changedRow.setRow(newData);
                            break;
                        }
                    }
            }

            if (!updated) {
                return new WebSocketResponseVO<>(400, "未找到匹配的infoId记录");
            }

            // 3. 更新存储（直接修改原列表，无需重新put）
            // tableDataMap.put(mapKey, rawTableData); // 注释：列表内容已修改，引用未变

            List<Object> result = tableDataMap.get(mapKey).stream()
                    .map(obj -> {
                        if (obj instanceof CellUpdateMessageDTO) {
                            return ((CellUpdateMessageDTO) obj).getRow();
                        }
                        throw new IllegalArgumentException("不支持的输入类型");
                    })
                    .collect(Collectors.toList());

            // 4. 广播变更
            sendToAllInRoom(userDTO,
                    new WebSocketResponseVO<>(200, "更新成功", "cellUpdate", result, sheetName)
            );

            return new WebSocketResponseVO<>(200, "更新成功");

        } catch (Exception e) {
            logger.error("更新单元格失败", e);
            return new WebSocketResponseVO<>(500, "更新失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }

    @Override
    public  WebSocketResponseVO<?> getTableData(String sheetName,UserDTO userDTO) throws  Exception {
        String mapKey = sheetName + " : " + getProvinceCodes(userDTO);
        try {
            localLock.lock(); // 保证线程安全

            // 1. 获取表格数据
            List<Object> currentTableData = tableDataMap.get(mapKey);
            if (currentTableData == null || currentTableData.isEmpty()) {
                return new WebSocketResponseVO<>(400, "未找到对应的表格数据");
            }

            List<Object> result = currentTableData.stream()
                    .map(obj -> {
                        if (obj instanceof CellUpdateMessageDTO) {
                            return ((CellUpdateMessageDTO) obj).getRow();
                        }
                        throw new IllegalArgumentException("不支持的输入类型");
                    })
                    .collect(Collectors.toList());

            if(DataUtils.isEmpty(result)){
                result = new ArrayList<>();
            }
            // 2. 广播变更
            sendToAllInRoom(userDTO,
                    new WebSocketResponseVO<>(200, "查询成功", "getTableData", result, sheetName)
            );

            return new WebSocketResponseVO<>(200, "查询成功");
        }  catch (Exception e) {
            logger.error("查询失败", e);
            return new WebSocketResponseVO<>(500, "查询失败: " + e.getMessage());
        } finally {
            localLock.unlock();
        }
    }

    @Override
    public WebSocketResponseVO<?> userLeave(UserDTO userDTO) throws Exception {
        try {
            localLock.lock(); // 加锁保证线程安全

            // 1. 清理所有Redis锁定记录（匹配用户所有sheet的锁）
            String globalKeyPattern = "ledger:*:*:" + userDTO.getUserId();
            Collection<Object> allLockKeys = redisUtils.keys(globalKeyPattern);
            if (allLockKeys != null && !allLockKeys.isEmpty()) {
                redisUtils.del(allLockKeys.toArray(new String[0]));
            }

            // 2. 清理所有sheet的内存锁定记录
            tableDataMap.forEach((mapKey, tableData) -> {
                // 检查是否是该用户省份的数据
                try {
                    if (mapKey.endsWith(" : " + getProvinceCodes(userDTO))) {
                        tableData.stream()
                                .filter(CellUpdateMessageDTO.class::isInstance)
                                .map(CellUpdateMessageDTO.class::cast)
                                .forEach(cellMessage -> {
                                    // 通用锁清理方法
                                    clearLocksInRow(cellMessage.getRow(), userDTO.getUserId());
                                });
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            // 3. 通知所有相关房间（需要提取用户所在的所有sheetName）
            sendToAllInRoom(userDTO,
                        new WebSocketResponseVO<>(200, "用户离开成功", "cleanLocks", userDTO.getUserId()));


            return new WebSocketResponseVO<>(200, "用户离开成功");

        } catch (Exception e) {
            logger.error("用户离开处理失败 - userId: {}", userDTO.getUserId(), e);
            return new WebSocketResponseVO<>(500, "用户离开失败: " + e.getMessage());
        } finally {
            localLock.unlock(); // 确保锁释放
        }
    }

    // 辅助方法：清理row对象中的用户锁
    private void clearLocksInRow(Object row, Long userId) {
        try {
            if (row instanceof EngineerProjectVO) {
                Map<String, Long> locks = ((EngineerProjectVO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof ConstructionProjectVO) {
                Map<String, Long> locks = ((ConstructionProjectVO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof SecurityOperationSystemVO) {
                Map<String, Long> locks = ((SecurityOperationSystemVO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof OperateEfficiencyVO) {
                Map<String, Long> locks = ((OperateEfficiencyVO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof ComplianceConstructVO) {
                Map<String, Long> locks = ((ComplianceConstructVO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof BenefitInternalConstructionOnlineBO) {
                Map<String, Long> locks = ((BenefitInternalConstructionOnlineBO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof BenefitExternalEmpowermentOnlineBO) {
                Map<String, Long> locks = ((BenefitExternalEmpowermentOnlineBO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
            else if (row instanceof ComprehensiveProtectionOnlineBO) {
                Map<String, Long> locks = ((ComprehensiveProtectionOnlineBO) row).getLocks();
                if (locks != null) locks.values().removeIf(userId::equals);
            }
        } catch (Exception e) {
            logger.error("清理行锁失败", e);
        }
    }

    //todo hyg 安全运营系统

    // 辅助方法：获取用户活跃的sheet列表（需要根据业务实现）

    @Override
    public void upload(UserDTO userDTO,
                                     List<EngineerProjectDO> sheetEngineerProject1231,
                                     List<EngineerProjectDO> sheetEngineerProject0930,
                                     List<EngineerProjectDO> sheetEngineerProject0630,
                                     List<ConstructionDO> sheetConstructionProject,
                                     Integer number, Map<String, Object> sheetMap) throws Exception {

        // 1. 初始化返回对象
        LedgerEfficiencyVO ledgerEfficiencyVO = new LedgerEfficiencyVO();
        List<Object> resultList = new ArrayList<>();
        String sheetName = "";

        try {
            // 2. 根据number选择处理的数据集
            switch(number) {
                case 0:
                    sheetName = "EngineerProject1231";
                    resultList = convertEngineerProjects(sheetEngineerProject1231);
                    break;
                case 1:
                    sheetName = "EngineerProject0930";
                    resultList = convertEngineerProjects(sheetEngineerProject0930);
                    break;
                case 2:
                    sheetName = "EngineerProject0630";
                    resultList = convertEngineerProjects(sheetEngineerProject0630);
                    break;
                case 3:
                    sheetName = "ConstructionProject";
                    resultList = convertConstructionProjects(sheetConstructionProject);
                    break;
                case 4:
                    for(String name: sheetMap.keySet()){
                        String mapKey = name + " : " + getProvinceCodes(userDTO);
                        if(name.equals("Scale")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertScale((List<LedgerScaleDO>) sheetMap.get("Scale"));
                        }else if(name.equals("Cloud")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertCloud((List<CloudDO>) sheetMap.get("Cloud"));
                        }else if(name.equals("IT")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertItBusiness((List<ItBusinessPlatformDefenceDO>) sheetMap.get("IT"));
                        }else if(name.equals("Basic")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertBasic((List<BasicNetworkDO>) sheetMap.get("Basic"));
                        }else if(name.equals("Core")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertCore((List<CoreNetworkDO>) sheetMap.get("Core"));
                        }else if(name.equals("Core5G")){
                            sheetMap.keySet().stream().findFirst().get();
                            resultList = convertCore5G((List<CoreNetwork5GDO>) sheetMap.get("Core5G"));
                        }
                        tableDataMap.remove(mapKey);
                        tableDataMap.put(mapKey, resultList);
                    }
                    break;
                case 10:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertSecurityOperationSystems((List<SecurityOperationSystemDO>) sheetMap.get("SecurityOperationSystem"));
                    break;
                case OPERATE_EFFICIENCY_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertOperateEfficiency((List<OperateEfficiencyDO>) sheetMap.get(OPERATE_EFFICIENCY));
                    break;
                case COMPLIANCE_CONSTRUCT_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertComplianceConstruct((List<ComplianceConstructDO>) sheetMap.get(COMPLIANCE_CONSTRUCT));
                    break;
                case BENEFIT_INTERNAL_CONSTRUCTION_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertBenefitInternalConstruction((List<BenefitInternalConstructionDO>) sheetMap.get(BENEFIT_INTERNAL_CONSTRUCTION));
                    break;
                case BENEFIT_EXTERNAL_EMPOWERMENT_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertBenefitExternalEmpowerment((List<BenefitExternalEmpowermentDO>) sheetMap.get(BENEFIT_EXTERNAL_EMPOWERMENT));
                    break;
                case COMPREHENSIVE_PROTECTION_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertComprehensiveProtection((List<ComprehensiveProtectionExtendedDO>) sheetMap.get(COMPREHENSIVE_PROTECTION));
                    break;
                case PLANNED_PROJECT_ALIAS:
                    sheetName = sheetMap.keySet().stream().findFirst().get();
                    resultList = convertPlannedProject((List<PlannedProjectDO>) sheetMap.get(PLANNED_PROJECT));
                    break;
                default:
                    throw new IllegalArgumentException("无效的表格编号");
            }

            // 3. 更新数据到tableDataMap
            if(number != 4){
                String mapKey = sheetName + " : " + getProvinceCodes(userDTO);
                tableDataMap.remove(mapKey);
                tableDataMap.put(mapKey, resultList);
            }

        } catch (Exception e) {
            logger.error("上传表格数据失败 - 用户: {}, 表格类型: {}", userDTO.getUserId(), number, e);
            throw e;
        }

    }

    private List<Object> convertCore5G(List<CoreNetwork5GDO> projectDOs) {
        return projectDOs.stream().map(doItem -> {
            LedgerCoreNetwork5GDefenceVO vo = new LedgerCoreNetwork5GDefenceVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertCore(List<CoreNetworkDO> projectDOs) {

        return projectDOs.stream().map(doItem -> {
            LedgerCoreNetworkDefenceVO vo = new LedgerCoreNetworkDefenceVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());

    }

    private List<Object> convertBasic(List<BasicNetworkDO> projectDOs) {

        return projectDOs.stream().map(doItem -> {
            LedgerBasicNetworkDefenceVO vo = new LedgerBasicNetworkDefenceVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertItBusiness(List<ItBusinessPlatformDefenceDO> projectDOs) {
        return projectDOs.stream().map(doItem -> {
            LedgerItBusinessDefenceVO vo = new LedgerItBusinessDefenceVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertCloud(List<CloudDO> projectDOs) {
        return projectDOs.stream().map(doItem -> {
            CloudVO vo = new CloudVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertScale(List<LedgerScaleDO> projectDOs) {
        return projectDOs.stream().map(doItem -> {
            ScaleVO vo = new ScaleVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }


    // 转换EngineerProjectDO列表为CellUpdateMessageDTO列表
    private List<Object> convertEngineerProjects(List<EngineerProjectDO> projectDOs) {
        return projectDOs.stream().map(doItem -> {
            EngineerProjectVO vo = new EngineerProjectVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>());// 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    // 转换ConstructionDO列表为CellUpdateMessageDTO列表
    private List<Object> convertConstructionProjects(List<ConstructionDO> constructionDOs) {
        return constructionDOs.stream().map(doItem -> {
            ConstructionProjectVO vo = new ConstructionProjectVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    // 转换ConstructionManualDO列表为CellUpdateMessageDTO列表
    private List<Object> convertConstructionManualProjects(List<ConstructionManualDO> constructionManualDOS) {
        return constructionManualDOS.stream().map(doItem -> {
            ConstructionManualProjectVO vo = new ConstructionManualProjectVO();
            try {

                BeanUtils.copyProperties(vo, doItem);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }

    // 转换SecurityOperationSystemDO列表为CellUpdateMessageDTO列表
    private List<Object> convertSecurityOperationSystems(List<SecurityOperationSystemDO> systemDOS) {
        return systemDOS.stream().map(doItem -> {
            SecurityOperationSystemVO vo = new SecurityOperationSystemVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            // 设置其他必要字段...
            return message;
        }).collect(Collectors.toList());
    }


    private List<Object> convertOperateEfficiency(List<OperateEfficiencyDO> operateEfficiencyDOS) {
        return operateEfficiencyDOS.stream().map(doItem -> {
            OperateEfficiencyVO vo = new OperateEfficiencyVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertComplianceConstruct(List<ComplianceConstructDO> complianceConstructDOS) {
        return complianceConstructDOS.stream().map(doItem -> {
            ComplianceConstructVO vo = new ComplianceConstructVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertBenefitInternalConstruction(List<BenefitInternalConstructionDO> dbList) {
        return dbList.stream().map(doItem -> {
            BenefitInternalConstructionOnlineBO onlineBO = new BenefitInternalConstructionOnlineBO();
            try {
                BeanUtils.copyProperties(onlineBO, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            onlineBO.setLocks(new HashMap<>());

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(onlineBO);
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertBenefitExternalEmpowerment(List<BenefitExternalEmpowermentDO> dbList) {
        return dbList.stream().map(doItem -> {
            BenefitExternalEmpowermentOnlineBO onlineBO = new BenefitExternalEmpowermentOnlineBO();
            try {
                BeanUtils.copyProperties(onlineBO, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            onlineBO.setLocks(new HashMap<>());

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(onlineBO);
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertComprehensiveProtection(List<ComprehensiveProtectionExtendedDO> dbList) {
        return dbList.stream().map(doItem -> {
            ComprehensiveProtectionOnlineBO onlineBO = new ComprehensiveProtectionOnlineBO();
            try {
                BeanUtils.copyProperties(onlineBO, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            onlineBO.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            onlineBO.setLocks(new HashMap<>());

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(onlineBO);
            return message;
        }).collect(Collectors.toList());
    }

    private List<Object> convertPlannedProject(List<PlannedProjectDO> plannedProjectDOS) {
        return plannedProjectDOS.stream().map(doItem -> {
            PlannedProjectVO vo = new PlannedProjectVO();
            try {
                BeanUtils.copyProperties(vo, doItem);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            vo.setInfoId(UUID.randomUUID().toString().replace("-", ""));
            vo.setLocks(new HashMap<>()); // 初始化锁

            CellUpdateMessageDTO message = new CellUpdateMessageDTO();
            message.setRow(vo);
            return message;
        }).collect(Collectors.toList());
    }

    @Override
    public void downloadFile(HttpServletResponse response) throws Exception {
        abilityImageManageService.downLoadFileByPath(response,"static/三效导入模板.zip");
    }

    @Override
    public void submitEngineerProject1231(UserDTO userDTO) throws Exception {

        String provinceCode = getProvinceCodes(userDTO);
        String mapKey = "EngineerProject1231" + " : " + provinceCode;
        Object fromTableDataMap = tableDataMap.get(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException("没有数据!");
        }
        List<CellUpdateMessageDTO> cellUpdateMessageDTOS = (List<CellUpdateMessageDTO>) fromTableDataMap;
        List<EngineerProjectVO> systemVOS = new ArrayList<>();

        logger.info("------cellUpdateMessageDTO:{}", cellUpdateMessageDTOS.get(0));
        logger.info("-------cellUpdateMessageDTO.getRow():{}", cellUpdateMessageDTOS.get(0).getRow());

        for (CellUpdateMessageDTO cellUpdateMessageDTO : cellUpdateMessageDTOS) {
            EngineerProjectVO systemVO = JSONObject.parseObject(
                    JSON.toJSONString(cellUpdateMessageDTO.getRow()), EngineerProjectVO.class);
            systemVO.setTenantOrgCode(provinceCode);
            systemVO.setProjectFileDate("1231");
            systemVOS.add(systemVO);
        }
        List<EngineerProjectDO> systemDOS = BeanConvertBeanUtil.copyListProperties(systemVOS, EngineerProjectDO::new);
        ledgerEngineerProjectDubboService.submit("1231",provinceCode,systemDOS,userDTO.getUserId());

    }

    @Override
    public void submitEngineerProject0930(UserDTO userDTO) throws Exception {

        String provinceCode = getProvinceCodes(userDTO);
        String mapKey = "EngineerProject0930" + " : " + provinceCode;
        Object fromTableDataMap = tableDataMap.get(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException("没有数据!");
        }
        List<CellUpdateMessageDTO> cellUpdateMessageDTOS = (List<CellUpdateMessageDTO>) fromTableDataMap;
        List<EngineerProjectVO> systemVOS = new ArrayList<>();

        logger.info("------cellUpdateMessageDTO:{}", cellUpdateMessageDTOS.get(0));
        logger.info("-------cellUpdateMessageDTO.getRow():{}", cellUpdateMessageDTOS.get(0).getRow());

        for (CellUpdateMessageDTO cellUpdateMessageDTO : cellUpdateMessageDTOS) {
            EngineerProjectVO systemVO = JSONObject.parseObject(
                    JSON.toJSONString(cellUpdateMessageDTO.getRow()), EngineerProjectVO.class);
            systemVO.setTenantOrgCode(provinceCode);
            systemVO.setProjectFileDate("0930");
            systemVOS.add(systemVO);
        }
        List<EngineerProjectDO> systemDOS = BeanConvertBeanUtil.copyListProperties(systemVOS, EngineerProjectDO::new);
        ledgerEngineerProjectDubboService.submit("0930",provinceCode,systemDOS,userDTO.getUserId());

    }

    @Override
    public void submitEngineerProject0630(UserDTO userDTO) throws Exception {

        String provinceCode = getProvinceCodes(userDTO);
        String mapKey = "EngineerProject0630" + " : " + provinceCode;
        Object fromTableDataMap = tableDataMap.get(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException("没有数据!");
        }
        List<CellUpdateMessageDTO> cellUpdateMessageDTOS = (List<CellUpdateMessageDTO>) fromTableDataMap;
        List<EngineerProjectVO> systemVOS = new ArrayList<>();

        logger.info("------cellUpdateMessageDTO:{}", cellUpdateMessageDTOS.get(0));
        logger.info("-------cellUpdateMessageDTO.getRow():{}", cellUpdateMessageDTOS.get(0).getRow());

        for (CellUpdateMessageDTO cellUpdateMessageDTO : cellUpdateMessageDTOS) {
            EngineerProjectVO systemVO = JSONObject.parseObject(
                    JSON.toJSONString(cellUpdateMessageDTO.getRow()), EngineerProjectVO.class);
            systemVO.setTenantOrgCode(provinceCode);
            systemVO.setProjectFileDate("0630");
            systemVOS.add(systemVO);
        }
        List<EngineerProjectDO> systemDOS = BeanConvertBeanUtil.copyListProperties(systemVOS, EngineerProjectDO::new);
        ledgerEngineerProjectDubboService.submit("0630",provinceCode,systemDOS, userDTO.getUserId());

    }

    @Override
    public void submitConstructionProject(UserDTO userDTO) throws Exception {

        String provinceCode = getProvinceCodes(userDTO);
        String mapKey = "ConstructionProject" + " : " + provinceCode;
        Object fromTableDataMap = tableDataMap.get(mapKey);
        if (DataUtils.isEmpty(fromTableDataMap)) {
            throw new BusinessException("没有数据!");
        }
        List<CellUpdateMessageDTO> cellUpdateMessageDTOS = (List<CellUpdateMessageDTO>) fromTableDataMap;
        List<ConstructionProjectVO> systemVOS = new ArrayList<>();

        logger.info("------cellUpdateMessageDTO:{}", cellUpdateMessageDTOS.get(0));
        logger.info("-------cellUpdateMessageDTO.getRow():{}", cellUpdateMessageDTOS.get(0).getRow());

        for (CellUpdateMessageDTO cellUpdateMessageDTO : cellUpdateMessageDTOS) {
            ConstructionProjectVO systemVO = JSONObject.parseObject(
                    JSON.toJSONString(cellUpdateMessageDTO.getRow()), ConstructionProjectVO.class);
            systemVO.setTenantOrgCode(provinceCode);
            systemVOS.add(systemVO);
        }
        List<ConstructionDO> systemDOS = BeanConvertBeanUtil.copyListProperties(systemVOS, ConstructionDO::new);
        ledgerConstructionDubboService.submit(provinceCode,systemDOS,userDTO.getUserId());

    }


    @Override
    public Pagination<EngineerProjectDO> engineerProjectPage(UserDTO userDTO, EngineerProjectVO engineerProjectVO) throws Exception {

        EngineerProjectDO query = new EngineerProjectDO();
        BeanUtils.copyProperties(query,engineerProjectVO);
        query.setTenantOrgCode(OrgCodeUtils.getCode(getProvinceCodes(userDTO), 1));

        Integer number = engineerProjectVO.getNumber();
        Integer current = engineerProjectVO.getCurrent();
        Integer size = engineerProjectVO.getSize();

        switch (number) {
            case 0:
                query.setProjectFileDate("1231");
                return ledgerEngineerProjectDubboService.page(query,current,size);
            case 1:
                query.setProjectFileDate("0930");
                return ledgerEngineerProjectDubboService.page(query,current,size);
            case 2:
                query.setProjectFileDate("0630");
                return ledgerEngineerProjectDubboService.page(query,current,size);
            default:
                return null;
        }
    }

    @Override
    public Pagination<ConstructionDO> constructionProjectPage(UserDTO userDTO,ConstructionProjectVO constructionDO) throws Exception {

        ConstructionDO query = new ConstructionDO();
        query.setTenantOrgCode(OrgCodeUtils.getCode(getProvinceCodes(userDTO),1));
        query.setDelFlag(Constants.DelFlag.AVAILABLE);

        return ledgerConstructionDubboService.page(constructionDO,constructionDO.getCurrent(),constructionDO.getSize());
    }

    @Override
    public Object getFromTableDataMap(String key) {
        return tableDataMap.get(key);
    }


    private <S, T> Pagination<T> buildPagination(Pagination<S> oldPagination, Supplier<T> supplier) {
        Pagination<T> newPagination = new Pagination<>();
        if (DataUtils.isEmpty(oldPagination.getRecords())) {
            newPagination.setTotal(0);
            return newPagination;
        }
        List<T> copyList = BeanConvertBeanUtil.copyListProperties(oldPagination.getRecords(), supplier);

        newPagination.setRecords(copyList);
        newPagination.setTotal(oldPagination.getTotal());
        newPagination.setCurrent(oldPagination.getCurrent());
        newPagination.setSize(oldPagination.getSize());
        return newPagination;
    }


    // WebSocket事件处理方法
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        try {

            //根据token获取到登陆用户信息
            String JwtString = userRedisService.getJwt(token);
            UserDTO userDTO = JwtUtils.getPayloads(JwtString);

            //处理用户信息
            onlineSessions.put(session.getId(), session);
            List<String> sessionIds = orgSessionMap.computeIfAbsent(
                    getProvinceCodes(userDTO),
                    k -> new CopyOnWriteArrayList<>()
            );

            // 添加新会话ID
            sessionIds.add(session.getId());

            logger.info("用户 {} 连接到工作表", userDTO.getUserName());

            // 发送初始化数据
            WebSocketResponseVO<?> response = initConnection(userDTO);
            logger.info("用户 {} 初始化数据 {}", userDTO.getUserName(), JSON.toJSONString(response));
            sendToSession(session, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("token") String token) {
        try {
            // 1. 获取用户信息
            String jwtString = userRedisService.getJwt(token);
            UserDTO userDTO = JwtUtils.getPayloads(jwtString);
            String sessionId = session.getId();

            // 2. 清理session映射关系
            synchronized (orgSessionMap) { // 保证线程安全
                if (orgSessionMap.containsKey(getProvinceCodes(userDTO))) {
                    List<String> sessionIds = orgSessionMap.get(getProvinceCodes(userDTO));

                    // 移除当前session
                    sessionIds.remove(sessionId);

                    // 如果这是最后一个session，则清理所有锁
                    if (sessionIds.isEmpty()) {
                        orgSessionMap.remove(getProvinceCodes(userDTO));
                        userLeave(userDTO); // 释放所有sheet的锁
                    } else {
                        // 只更新session列表
                        orgSessionMap.put(getProvinceCodes(userDTO), sessionIds);
                    }
                }
            }

            // 3. 清理在线会话记录
            onlineSessions.remove(sessionId);

        } catch (Exception e) {
            logger.error("会话关闭处理失败 - sessionId: {}, token: {}", session.getId(), token, e);
        }
    }

    @OnMessage
    public void onMessage(String message, @PathParam("token") String token) {
        Session session = null;
        try {

            //根据token获取到登陆用户信息
            String JwtString = userRedisService.getJwt(token);
            UserDTO userDTO = JwtUtils.getPayloads(JwtString);

            List<String> sessionId = orgSessionMap.get(getProvinceCodes(userDTO));
            session = onlineSessions.get(sessionId);

            // 解析消息类型和内容
            WebSocketMessage wsMessage = parseMessage(message);

            switch (wsMessage.getType()) {
                case "cellUpdate":
                    updateCell(wsMessage.getData().getSheetName(),wsMessage.getData().getRow(), userDTO);
                    break;
                case "rowAdd":
                    addRow(wsMessage.getData().getSheetName(), wsMessage.getData().getRow(), userDTO);
                    break;
                case "rowDelete":
                    deleteRow(wsMessage.getData().getSheetName(), wsMessage.getData().getRow(), userDTO);
                    break;
                case "cellLock":
                    lockCell(wsMessage.getData(), userDTO);
                    break;
                case "cellUnlock":
                    unlockCell(wsMessage.getData(), userDTO);
                    break;
                case "userLeave":
                    userLeave(userDTO);

                    break;
                case "getTableData":
                    getTableData(wsMessage.getData().getSheetName(),userDTO);
                    break;
                default:
                    sendToSession(session, new WebSocketResponseVO<>(400, "未知消息类型"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendToSession(session, new WebSocketResponseVO<>(500, "处理消息时发生错误"));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    // 初始化表格数据
//    private void initTableData(String key) {
//        List<Object> tableData = tableDataMap.get(key);
//    }


    // 发送消息到指定会话
    private void sendToSession(Session session, Object message) {
        try {
            session.getBasicRemote().sendText(convertToJson(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 发送消息到房间内所有用户
    private void sendToAllInRoom(UserDTO userDTO, Object message) throws Exception {
        String jsonMessage = convertToJson(message);
        List<String> sessionIds = orgSessionMap.get(getProvinceCodes(userDTO));
        for(String sessionId : sessionIds){
            Session session = onlineSessions.get(sessionId);
            if(session != null && session.isOpen()){
                sendToSession(session, jsonMessage);
            }
        }

    }

    // 消息解析方法（需要根据实际情况实现）
    private WebSocketMessage parseMessage(String message) {
        logger.info("解析消息：{}", message);
        // 实际实现中需要使用JSON解析库将消息转换为对象
        // 这里简化处理，假设消息已经被正确解析
        WebSocketMessage wsMessage = JSON.parseObject(message, WebSocketMessage.class);
        // 解析逻辑...
        return wsMessage;
    }

    // 对象转JSON方法（需要根据实际情况实现）
    private String convertToJson(Object object) {
        return JSON.toJSONString(
                object,
                JSONWriter.Feature.WriteNulls // 关键：保留 null 字段
        );
    }

    // 内部类：表示WebSocket消息结构
    private static class WebSocketMessage {
        private String type;
        private CellUpdateMessageDTO data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public CellUpdateMessageDTO getData() {
            return data;
        }

        public void setData(CellUpdateMessageDTO data) {
            this.data = data;
        }
    }


    // 自定义Spring配置器，用于在WebSocket端点中注入Spring Bean
    public static class CustomSpringConfigurator extends ServerEndpointConfig.Configurator {
        @Override
        public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
            return SpringContextHolder.getBean(endpointClass);
        }
    }

    private List<String> getAllAreaCodesPrivate(String groupId, UserDTO userDTO) throws Exception {
        List<String> allAreaCodes = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(allAreaCodes)) {
            OrgDO orgDO = new OrgDO();
            List<String> configAreaCodes = getConfigAreaCodesPrivate(groupId, userDTO);
            if (DataUtils.isEmpty(configAreaCodes)) {
                allAreaCodes = new ArrayList<>();
                redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
                return allAreaCodes;
            }
            orgDO.setOrgPrefixes(getConfigAreaCodesPrivate(groupId, userDTO));
            orgDO.setStatus(0);
            orgDO.setDelFlag(Constants.DelFlag.AVAILABLE);
            orgDO.setOrderBy("org_level,org_sort");
            allAreaCodes = orgDubboService.listCode(orgDO);
            redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
        }
        return allAreaCodes;
    }

    private List<String> getConfigAreaCodesPrivate(String groupId, UserDTO userDTO) throws Exception {
        List<String> areaTree = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(areaTree)) {
            areaTree = userDubboService.getAreaCodes(groupId, userDTO.getUserId(), userDTO.getAdminCase());
            redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, areaTree, cacheTimeout);
        }
        return areaTree;
    }

    // 安全属性复制方法
    private void safeCopyProperties(Object dest, Object orig) throws Exception {
        // 注册日期转换器
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                try {
                    if (value instanceof String) {
                        return new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
                    }
                } catch (ParseException e) {
                    return null;
                }
                return value;
            }
        }, Date.class);

        // 复制非空属性
        BeanUtils.copyProperties(dest, orig);

        // 重置转换器
        ConvertUtils.deregister(Date.class);
    }
}