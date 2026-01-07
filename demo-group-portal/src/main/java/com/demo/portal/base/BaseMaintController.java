package com.sama.officer.base;

import com.alibaba.fastjson2.JSON;
import com.core4ct.DTO.OrgDTO;
import com.core4ct.DTO.UserDTO;
import com.core4ct.base.AbstractController;
import com.core4ct.constants.Constants;
import com.core4ct.constants.GroupConstants;
import com.core4ct.exception.BusinessException;
import com.core4ct.exception.ForbiddenException;
import com.core4ct.utils.DataUtils;
import com.core4ct.utils.JwtUtils;
import com.core4ct.utils.OrgCodeUtils;
import com.sama.api.pool.object.DO.PoolDO;
import com.sama.api.pool.service.PoolDubboService;
import com.sama.officer.object.vo.RoleUserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * The type Base officer controller.
 *
 * @author wangyanzheng
 * @date 2021年7月1日11 :01:52
 * @description 运营门户controller基类
 */
public class BaseMaintController extends AbstractController {

    /**
     * The Disallowed fields.
     */
    final String[] DISALLOWED_FIELDS = new String[]{};

    /**
     * The User dto.
     */
    @Resource
    protected UserDTO userDTO;
    @DubboReference
    PoolDubboService poolDubboService;

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(DISALLOWED_FIELDS);
    }

    /**
     * 获取当前用户
     */
    @Override
    protected UserDTO getCurrUser() throws Exception {
//        userDTO = super.getCurrUser();
        System.out.println(userDTO.getVarcharBusiness());
        if (!userDTO.getAdminCase() && DataUtils.isEmpty(userDTO.getRoleIds(GroupConstants.OFFICER_PORTAL))) {
            throw new ForbiddenException("无权访问");
        }
        return userDTO;
    }

    /**
     * 获取当前用户Id
     */
    @Override
    protected Long getCurrUserId() throws Exception {
        return getCurrUser().getUserId();
    }

    /**
     * 获取当前Jwt
     *
     * @return the curr jwt
     * @throws Exception the exception
     */
    protected String getCurrJwt() throws Exception {
        String jwt = getRequest().getHeader("jwt");
        UserDTO userDTO = JwtUtils.getPayloads(jwt);
        if (!userDTO.getAdminCase() && DataUtils.isEmpty(userDTO.getRoleIds(GroupConstants.OFFICER_PORTAL))) {
            throw new ForbiddenException("无权访问");
        }
        return jwt;
    }

    /**
     * 只取数据库中存的值，不包含任何上下级，为用户数据域最简且最精准描述方式
     *
     * @param groupId the group id
     * @return area codes
     * @throws Exception the exception
     */
    protected List<String> getAreaCodes(String groupId) throws Exception {
        final Set<String> areaCodes = getCurrUser().getAreaCodes(groupId);
        return new ArrayList<>(areaCodes);
    }

    protected List<String> getTenantPrefix() throws Exception {
        final Set<String> areaCodes = getCurrUser().getAreaCodes(GroupConstants.TENANT);
        Set<String> preFix = new HashSet<>();
        areaCodes.forEach(item -> preFix.add(OrgCodeUtils.getPrefix(item)));
        return new ArrayList<>(preFix);
    }

    protected List<String> getPoolPrefix() throws Exception {
        final Set<String> areaCodes = getCurrUser().getAreaCodes(GroupConstants.POOL);
        Set<String> preFix = new HashSet<>();
        areaCodes.forEach(item -> preFix.add(OrgCodeUtils.getPrefix(item)));
        return new ArrayList<>(preFix);
    }

    /**
     * 取用户有权限访问的资源池数据域树，仅保证到叶子节点为真正有全部权限的
     *
     * @return area pool tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaPoolTree() throws Exception {
        return getAreasTree(GroupConstants.POOL);
    }

    /**
     * 取用户有权限访问的资源池OrgDTO
     *
     * @return area pools
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaPools() throws Exception {
        PoolDO poolDO = new PoolDO();
        poolDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<PoolDO> list = poolDubboService.list(poolDO);
        if (DataUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        List<OrgDTO> orgDTOS = new ArrayList<>();
        for (PoolDO pool : list) {
            OrgDTO orgDTO = new OrgDTO();
            orgDTO.setOrgName(pool.getPoolName());
            orgDTO.setOrgCode(pool.getOrgCode());
            orgDTOS.add(orgDTO);
        }
        return orgDTOS;
    }

    /**
     * 获取所有的资源池
     *
     * @return area pools
     * @throws Exception the exception
     */
    protected List<PoolDO> getPools(List<String> orgPrefixes) {
        PoolDO poolDO = new PoolDO();
        poolDO.setOrgPrefixes(orgPrefixes);
        poolDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<PoolDO> list = poolDubboService.list(poolDO);
        if (DataUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        list.sort(new Comparator<PoolDO>() {
            @Override
            public int compare(PoolDO o1, PoolDO o2) {
                return o1.getOrgCode().compareTo(o2.getOrgCode());
            }
        });
        return list;
    }

    /**
     * 获取池内资源池
     *
     * @return area pools
     * @throws Exception the exception
     */
    protected List<PoolDO> getInnerPools(List<String> orgPrefixes) {
        return getPools(orgPrefixes).stream()
                .filter(p -> p.getCloudType() != null && p.getCloudType() != 4)
                .collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的资源池OrgCode
     *
     * @return area pool codes
     * @throws Exception the exception
     */
    protected List<String> getAreaPoolCodes() throws Exception {
        PoolDO poolDO = new PoolDO();
        poolDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        List<PoolDO> list = poolDubboService.list(poolDO);
        if (DataUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list.stream().map(PoolDO::getOrgCode).collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的池内资源池OrgCode
     *
     * @return area pool codes
     * @throws Exception the exception
     */
    protected List<String> getInnerAreaPoolCodes(List<String> orgPrefixes){
        return getPools(orgPrefixes).stream()
                .filter(p -> p.getCloudType() != null && p.getCloudType() != 4)
                .map(PoolDO::getOrgCode)
                .collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的池内资源池OrgCode
     *
     * @return area pool codes
     * @throws Exception the exception
     */
    protected List<String> getInnerAreaPoolCodes(){
        return getPools(null).stream()
                .filter(p -> p.getCloudType() != null && p.getCloudType() != 4)
                .map(PoolDO::getOrgCode)
                .collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的资源池OrgCode
     *
     * @return area pool codes
     * @throws Exception the exception
     */
    protected List<String> getAreaPoolCodes(List<String> orgPrefixes) throws Exception {
        PoolDO poolDO = new PoolDO();
        poolDO.setDelFlag(Constants.DelFlag.AVAILABLE);
        poolDO.setOrgPrefixes(orgPrefixes);
        List<PoolDO> list = poolDubboService.list(poolDO);
        if (DataUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        return list.stream().map(PoolDO::getOrgCode).collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的资源池OrgCode包含有权限的地市和省份code
     *
     * @return the area pool codes with parent code
     * @throws Exception the exception
     */
    protected Set<String> getAreaPoolCodesWithParent() throws Exception {
        return getCurrUser().getAreaLeafCodeWithParent(GroupConstants.POOL);
    }

    /**
     * 取用户有权限访问的资源池OrgCode树
     *
     * @return the area tenant tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaTenantTree() throws Exception {
        return getAreasTree(GroupConstants.TENANT);
    }

    /**
     * 取用户有权限访问的租户OrgDTO
     *
     * @return the area tenants
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaTenants() throws Exception {
        return getAreaLeafOrg(GroupConstants.TENANT);
    }

    /**
     * 取用户有权限访问的租户OrgCode
     *
     * @return the area tenant codes
     * @throws Exception the exception
     */
    protected List<String> getAreaTenantCodes() throws Exception {
        return getAreaLeafOrg(GroupConstants.TENANT).stream().map(OrgDTO::getOrgCode).collect(Collectors.toList());
    }

    /**
     * 取用户有权限访问的租户OrgCode包含有权限的地市和省份code
     *
     * @return the area tenant codes
     * @throws Exception the exception
     */
    protected Set<String> getAreaTenantCodesWithParent() throws Exception {
        return getCurrUser().getAreaLeafCodeWithParent(GroupConstants.TENANT);
    }

    /**
     * 只取数据库中存的值，不包含任何上下级，为用户数据域最简且最精准描述方式
     *
     * @param groupId the group id
     * @return the areas
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreas(String groupId) throws Exception {
        return getCurrUser().getAreas(groupId);
    }

    /**
     * 获取权限树
     *
     * @param groupId the group id
     * @return the areas tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreasTree(String groupId) throws Exception {
        return getCurrUser().getAreaTree(groupId);
    }

    /**
     * 获取叶子节点
     *
     * @param groupId the group id
     * @return the area leaf org
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaLeafOrg(String groupId) throws Exception {
        return getCurrUser().getAreaLeafOrg(groupId);
    }

    /**
     * 获取有权限的某个省份下的叶子节点
     *
     * @param provinceCode the province code
     * @return the area province leaf
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaProvinceLeaf(String provinceCode) throws Exception {
        String groupId = OrgCodeUtils.getGroup(provinceCode);
        List<OrgDTO> groupAreaTree = getAreasTree(groupId);
        List<OrgDTO> leaves = new ArrayList<>();
        if (DataUtils.isNotEmpty(groupAreaTree)) {
            groupAreaTree.stream().anyMatch(item -> {
                if (provinceCode.equals(item.getOrgCode())) {
                    if (DataUtils.isNotEmpty(item.getChildren())) {
                        List<OrgDTO> children = item.getChildren();
                        children.forEach(child -> {
                            if (DataUtils.isNotEmpty(child) && DataUtils.isNotEmpty(child.getChildren())) {
                                leaves.addAll(child.getChildren());
                            }
                        });
                    }
                    return true;
                }
                return false;
            });
        }

        return leaves;
    }

    /**
     * 获取有权限的某个省份下的叶子节点Codes
     *
     * @param provinceCode the province code
     * @return the area province leaf codes
     * @throws Exception the exception
     */
    protected List<String> getAreaProvinceLeafCodes(String provinceCode) throws Exception {
        String groupId = OrgCodeUtils.getGroup(provinceCode);
        List<OrgDTO> groupAreaTree = getAreas(OrgCodeUtils.getGroup(provinceCode));
        List<String> leafCodes = new ArrayList<>();
        if (DataUtils.isNotEmpty(groupAreaTree)) {
            groupAreaTree.stream().anyMatch(item -> {
                if (provinceCode.equals(item.getOrgCode())) {
                    if (DataUtils.isNotEmpty(item.getChildren())) {
                        List<OrgDTO> children = item.getChildren();
                        children.forEach(child -> {
                            if (DataUtils.isNotEmpty(child) && DataUtils.isNotEmpty(child.getChildren())) {
                                List<OrgDTO> grandChildren = child.getChildren();
                                grandChildren.forEach(grandChild -> leafCodes.add(grandChild.getOrgCode()));
                            }
                        });
                    }
                    return true;
                }
                return false;
            });
        }
        return leafCodes;
    }

    /**
     * Check pool permission.
     *
     * @param poolOrgCodes the pool org codes
     * @throws Exception the exception
     */
    protected void checkPoolPermission(Collection<String> poolOrgCodes) throws Exception {
        getCurrUser();
    }

    /**
     * Check pool permission.
     *
     * @param poolOrgCode the pool org code
     * @throws Exception the exception
     */
    protected void checkPoolPermission(String poolOrgCode) throws Exception {
        getCurrUser();
    }


    protected void checkPoolPermission(Collection<String> poolOrgCodes, String msg) throws Exception {
        List<String> areaPoolCodes = getAreaPoolCodes();
        if (DataUtils.isNotEmpty(areaPoolCodes)) {
            Set<String> areaPoolCodeSet = new HashSet<>(areaPoolCodes);
            poolOrgCodes.forEach(poolOrgCode -> {
                if (!areaPoolCodeSet.contains(poolOrgCode)) {
                    throw new ForbiddenException(msg);
                }
            });
        } else {
            throw new ForbiddenException(msg);
        }
    }


    /**
     * Check tenant permission.
     *
     * @param poolOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkPoolPermission(String poolOrgCode, String msg) throws Exception {
        Set<String> areaPoolCodesWithParent = getAreaPoolCodesWithParent();
        if (DataUtils.isNotEmpty(areaPoolCodesWithParent)) {
            boolean flag = checkAuthorityPermission(poolOrgCode, areaPoolCodesWithParent);
            if (!flag) {
                throw new ForbiddenException(msg);
            }
        } else {
            throw new ForbiddenException(msg);
        }
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCodes the tenant org codes
     * @throws Exception the exception
     */
    protected void checkTenantPermission(Collection<String> tenantOrgCodes) throws Exception {
        checkTenantPermission(tenantOrgCodes, "无权查看该租户");
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCodes the tenant org codes
     * @throws Exception the exception
     */
    protected void checkTenanProvincePermission(Collection<String> tenantOrgCodes) throws Exception {
        checkTenantProvincePermission(tenantOrgCodes, "无权查看该租户");
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkTenantPermission(String tenantOrgCode) throws Exception {
        checkTenantPermission(tenantOrgCode, "无权查看该租户");
    }

    protected void checkTenantPermission(Collection<String> tenantOrgCodes, String msg) throws Exception {
        Set<String> areaTenantCodesWithParent = getAreaTenantCodesWithParent();
        if (DataUtils.isNotEmpty(areaTenantCodesWithParent)) {
            tenantOrgCodes.forEach(tenantOrgCode -> {
                boolean flag = checkAuthorityPermission(tenantOrgCode, areaTenantCodesWithParent);
                if (!flag) {
                    throw new ForbiddenException(msg);
                }
            });
        } else {
            throw new ForbiddenException(msg);
        }
    }

    protected void checkTenantProvincePermission(Collection<String> tenantOrgCodes, String msg) throws Exception {
        Set<String> areaTenantCodesWithParent = getAreaTenantCodesWithParent().stream().map(a -> OrgCodeUtils.getCode(a, 1)).collect(Collectors.toSet());
        if (DataUtils.isNotEmpty(areaTenantCodesWithParent)) {
            tenantOrgCodes.forEach(tenantOrgCode -> {
                boolean flag = checkAuthorityPermission(tenantOrgCode, areaTenantCodesWithParent);
                if (!flag) {
                    throw new ForbiddenException(msg);
                }
            });
        } else {
            throw new ForbiddenException(msg);
        }
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkTenantPermission(String tenantOrgCode, String msg) throws Exception {
        Set<String> areaTenantCodesWithParent = getAreaTenantCodesWithParent();
        if (DataUtils.isNotEmpty(areaTenantCodesWithParent)) {
            boolean flag = checkAuthorityPermission(tenantOrgCode, areaTenantCodesWithParent);
            if (!flag) {
                throw new ForbiddenException(msg);
            }
        } else {
            throw new ForbiddenException(msg);
        }
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkTenantProvincePermission(String tenantOrgCode, String msg) throws Exception {
        Set<String> areaTenantCodesWithParent = getAreaTenantCodesWithParent().stream().map(a -> OrgCodeUtils.getCode(a, 1)).collect(Collectors.toSet());
        if (DataUtils.isNotEmpty(areaTenantCodesWithParent)) {
            boolean flag = checkAuthorityPermission(tenantOrgCode, areaTenantCodesWithParent);
            if (!flag) {
                throw new ForbiddenException(msg);
            }
        } else {
            throw new ForbiddenException(msg);
        }
    }

    /**
     * 校验权限
     *
     * @param orgCode
     * @param areaCodesWithParent
     * @return
     */
    protected boolean checkAuthorityPermission(String orgCode, Set<String> areaCodesWithParent) {
        boolean flag = false;
        try {
            if (areaCodesWithParent.contains(orgCode) || areaCodesWithParent.contains(OrgCodeUtils.getCode(orgCode, 2))
                    || areaCodesWithParent.contains(OrgCodeUtils.getCode(orgCode, 1))) {
                flag = true;
            }
        } catch (Exception e) {
            logger.info("无组织权限范围内的操作权限!");
            return flag;
        }
        return flag;
    }

    /**
     * 处理资源池搜索条件
     *
     * @param poolOrgCodes     选中的资源此code
     * @param platformCodes    选中的平台
     * @param provinceOrgCodes 选中的省份
     * @return
     * @throws Exception
     */
    protected List<String> hanlderPoolOrgCode(List<String> poolOrgCodes,
                                              List<String> platformCodes,
                                              List<String> provinceOrgCodes) throws Exception {

        logger.info("开始处理资源池code-------选中的资源此code：{}，选中的平台code:{},选中的省份code：{}"
                , JSON.toJSONString(poolOrgCodes), JSON.toJSONString(platformCodes), JSON.toJSONString(provinceOrgCodes));
        getCurrUser();
        //选了资源池以资源池为准
        if (DataUtils.isNotEmpty(poolOrgCodes)) {
            return getInnerAreaPoolCodes(poolOrgCodes);
        }

        //没选资源池，以省份为准,都要在权限范围内
        if (DataUtils.isNotEmpty(provinceOrgCodes)) {
            return getInnerAreaPoolCodes(provinceOrgCodes);
        }

        //没选省份以平台为准,都要在权限范围内
        if (DataUtils.isNotEmpty(platformCodes)) {
            return getInnerAreaPoolCodes(platformCodes);
        }
        //资源池 省份 平台都没选，以权限范围内为准
        return getInnerAreaPoolCodes();
    }

    /**
     * 处理资源池搜索条件
     *
     * @param poolOrgCodes     选中的资源此code
     * @param platformCodes    选中的平台
     * @param provinceOrgCodes 选中的省份
     * @return
     * @throws Exception
     */
    protected Map<String,List<String>> hanlderAllPoolOrgCode(List<String> poolOrgCodes,
                                              List<String> platformCodes,
                                              List<String> provinceOrgCodes) throws Exception {

        logger.info("开始处理资源池code-------选中的资源此code：{}，选中的平台code:{},选中的省份code：{}"
                , JSON.toJSONString(poolOrgCodes), JSON.toJSONString(platformCodes), JSON.toJSONString(provinceOrgCodes));
        getCurrUser();
        //选了资源池以资源池为准
        if (DataUtils.isNotEmpty(poolOrgCodes)) {
            return getMap(poolOrgCodes);
        }

        //没选资源池，以省份为准,都要在权限范围内
        if (DataUtils.isNotEmpty(provinceOrgCodes)) {
            return getMap(provinceOrgCodes);
        }

        //没选省份以平台为准,都要在权限范围内
        if (DataUtils.isNotEmpty(platformCodes)) {
            return getMap(platformCodes);
        }
        //资源池 省份 平台都没选，以权限范围内为准
        return getMap(poolOrgCodes);
    }

    private Map<String,List<String>> getMap(List<String> orgPrefixes){
        Map<String,List<String>> map = new HashMap<>();
        List<PoolDO> pools = getPools(orgPrefixes);
        if (DataUtils.isNotEmpty(pools)){
            //分成池内池外
            map.put("inner",pools.stream().filter(p -> p.getCloudType() != 4).map(PoolDO::getOrgCode).collect(Collectors.toList()));
            map.put("outer",pools.stream().filter(p -> p.getCloudType() == 4).map(PoolDO::getOrgCode).collect(Collectors.toList()));
        }
        return map;
    }

    protected String setOrgCodeAndOrgName(List<OrgDTO> orgDTOS) {
        String orgCode = "";
        if (DataUtils.isNotEmpty(orgDTOS)) {
            OrgDTO grandFather = orgDTOS.get(0);
            if (DataUtils.isEmpty(grandFather.getChildren())) {
                orgCode = grandFather.getOrgCode();
            } else {
                OrgDTO son = grandFather.getChildren().get(0);
                if (DataUtils.isEmpty(son.getChildren())) {
                    orgCode = son.getOrgCode();
                } else {
                    OrgDTO grandSon = son.getChildren().get(0);
                    orgCode = grandSon.getOrgCode();
                }
            }
        } else {
            throw new BusinessException("未查询到所属组织信息！");
        }
        return orgCode;
    }


    protected void setOrgCodeAndOrgName(List<OrgDTO> orgDTOS, RoleUserVO roleUserVO) {
        if (DataUtils.isNotEmpty(orgDTOS)) {
            OrgDTO grandFather = orgDTOS.get(0);
            if (DataUtils.isEmpty(grandFather.getChildren())) {
                roleUserVO.setOrgCode(grandFather.getOrgCode());
            } else {
                OrgDTO son = grandFather.getChildren().get(0);
                if (DataUtils.isEmpty(son.getChildren())) {
                    roleUserVO.setOrgCode(son.getOrgCode());
                } else {
                    OrgDTO grandSon = son.getChildren().get(0);
                    roleUserVO.setOrgCode(grandSon.getOrgCode());
                }
            }
        } else {
            throw new BusinessException("未查询到所属组织信息！");
        }
    }

    /**
     * 根据用户权限下的约束，得到该组织下最大的子组织,第一个参数是维度，第二个参数是组织
     */
    protected List<String> getSubOrgCodeUnderAuthority(String groupName, String orgCode) throws Exception {
        final Set<String> areaCodes = getCurrUser().getAreaCodes(groupName);
        List<String> subOrgCodeList = new ArrayList<>();
        for (String areaCode : areaCodes) {
            if (Objects.equals(OrgCodeUtils.compare(areaCode, orgCode), OrgCodeUtils.CompareResult.EQU) || Objects.equals(OrgCodeUtils.compare(areaCode, orgCode), OrgCodeUtils.CompareResult.GTR)) {
                subOrgCodeList.clear();
                subOrgCodeList.add(orgCode);
                return subOrgCodeList;
            }
            if (Objects.equals(OrgCodeUtils.compare(areaCode, orgCode), OrgCodeUtils.CompareResult.LSS)) {
                subOrgCodeList.add(areaCode);
            }
        }
        return subOrgCodeList;
    }
}
