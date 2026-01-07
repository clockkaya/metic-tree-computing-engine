package com.sama.maint.base;

import com.alibaba.fastjson2.JSON;
import com.core4ct.DTO.OrgDTO;
import com.core4ct.DTO.UserDTO;
import com.core4ct.api.system.OrgDubboService;
import com.core4ct.api.system.UserDubboService;
import com.core4ct.api.system.object.OrgDO;
import com.core4ct.base.AbstractController;
import com.core4ct.constants.Constants;
import com.core4ct.constants.GroupConstants;
import com.core4ct.constants.OrgClassConstants;
import com.core4ct.exception.BusinessException;
import com.core4ct.exception.ForbiddenException;
import com.core4ct.utils.DataUtils;
import com.core4ct.utils.JwtUtils;
import com.core4ct.utils.OrgCodeUtils;
import com.core4ct.utils.redis.RedisUtils;
import com.sama.maint.config.MaintConfig;
import com.sama.maint.object.vo.RoleUserVO;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Base maint controller.
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

    @Resource
    protected RedisUtils redisUtils;

    @DubboReference
    protected UserDubboService userDubboService;

    @DubboReference
    protected OrgDubboService orgDubboService;

    //   TODO @Value()
    protected Integer cacheTimeout = 86400;


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
        if (!userDTO.getAdminCase() && DataUtils.isEmpty(userDTO.getRoleIds(MaintConfig.GroupId))) {
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
        if (!userDTO.getAdminCase() && DataUtils.isEmpty(userDTO.getRoleIds(MaintConfig.GroupId))) {
            throw new ForbiddenException("无权访问");
        }
        return jwt;
    }

    /**
     * 只取数据库中存的值，不包含任何上下级，为用户数据域最简且最精准描述方式
     * AREA_CODE  配的是什么返回是什么  如只配置了一级 则 只返回一级的组织
     * @param groupId the group id
     * @return area codes
     * @throws Exception the exception
     */
    protected List<String> getConfigAreaCodes(String groupId) throws Exception {
        List<String> areaTree = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(areaTree)) {
            areaTree = userDubboService.getAreaCodes(groupId, getCurrUserId(), getCurrUser().getAdminCase());
            redisUtils.hset(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, areaTree, cacheTimeout);
        }
        return areaTree;
    }

    protected List<String> getConfigAreaCodes(String groupId, UserDTO userDTO) throws Exception {
        List<String> areaTree = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(areaTree)) {
            areaTree = userDubboService.getAreaCodes(groupId, userDTO.getUserId(), userDTO.getAdminCase());
            redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, areaTree, cacheTimeout);
        }
        return areaTree;
    }

    /**
     * 只取数据库中存的值，不包含任何上下级，为用户数据域最简且最精准描述方式
     *
     * @param groupId the group id
     * @return area codes
     * @throws Exception the exception
     */
    protected List<OrgDTO> getConfigArea(String groupId) throws Exception {
        List<OrgDTO> area = (List<OrgDTO>) redisUtils.hget(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(area)) {
            area = new ArrayList<>();
            List<String> areaCodes = getConfigAreaCodes(groupId);
            if (DataUtils.isNotEmpty(areaCodes)) {
                final List<OrgDO> orgDOS = orgDubboService.listByCodes(areaCodes);
                for (OrgDO orgDO : orgDOS) {
                    if (0 == orgDO.getStatus() && orgDO.getDelFlag().equals(Constants.DelFlag.AVAILABLE)) {
                        if (GroupConstants.MAINTENANCE_PORTAL.equals(MaintConfig.GroupId) && GroupConstants.POOL.equals(orgDO.getGroupId()) && OrgClassConstants.VIRTUAL_POOL == orgDO.getOrgClass()) {
                        } else {
                            area.add(covertOrgDTO(orgDO));
                        }
                    }
                    }
                }
            redisUtils.hset(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_" + MaintConfig.GroupId + "_" + groupId, area, cacheTimeout);
        }
        return area;
    }

    /**
     * 取用户所有有权限的orgCode,包括数据库中存的值和所有下级，为用户数据域最全面描述方式
     *  ALL_AREA_CODE     实际配置了一级组织  ，查询的时候会补上有权限的下级
     * @param groupId the group id
     * @return area codes
     * @throws Exception the exception
     */
    protected List<String> getAllAreaCodes(String groupId) throws Exception {
        List<String> allAreaCodes = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + getCurrUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(allAreaCodes)) {
            OrgDO orgDO = new OrgDO();
            List<String> configAreaCodes = getConfigAreaCodes(groupId);
            if (DataUtils.isEmpty(configAreaCodes)) {
                allAreaCodes = new ArrayList<>();
                redisUtils.hset(Constants.REDIS_USER_CACHE + getCurrUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
                return allAreaCodes;
            }
            orgDO.setOrgPrefixes(getConfigAreaCodes(groupId));
            orgDO.setStatus(0);
            orgDO.setDelFlag(Constants.DelFlag.AVAILABLE);
            orgDO.setOrderBy("org_level,org_sort");
            allAreaCodes = orgDubboService.listCode(orgDO);
            redisUtils.hset(Constants.REDIS_USER_CACHE + getCurrUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
        }
        return allAreaCodes;
    }

    protected List<String> getAllAreaCodes(String groupId, UserDTO userDTO) throws Exception {
        List<String> allAreaCodes = (List<String>) redisUtils.hget(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId);
        if (DataUtils.isEmpty(allAreaCodes)) {
            OrgDO orgDO = new OrgDO();
            List<String> configAreaCodes = getConfigAreaCodes(groupId, userDTO);
            if (DataUtils.isEmpty(configAreaCodes)) {
                allAreaCodes = new ArrayList<>();
                redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
                return allAreaCodes;
            }
            orgDO.setOrgPrefixes(getConfigAreaCodes(groupId, userDTO));
            orgDO.setStatus(0);
            orgDO.setDelFlag(Constants.DelFlag.AVAILABLE);
            orgDO.setOrderBy("org_level,org_sort");
            allAreaCodes = orgDubboService.listCode(orgDO);
            redisUtils.hset(Constants.REDIS_USER_CACHE + userDTO.getUserId(), "ALL_AREA_CODE_" + MaintConfig.GroupId + "_" + groupId, allAreaCodes, cacheTimeout);
        }
        return allAreaCodes;
    }

    /**
     * 获取权限树，返回的树为裁剪之后的树，即只往下补充节点
     *
     * @param groupId the group id
     * @return the areas tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreasTree(String groupId, List<Character> orgClasses) throws Exception {
        String orgClassStr = DataUtils.isNotEmpty(orgClasses) ? "_" + orgClasses.stream().map(Object::toString).collect(Collectors.joining("_")) : "";
        List<OrgDTO> areaTree = (List<OrgDTO>) redisUtils.hget(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_TREE_" + MaintConfig.GroupId + "_" + groupId + orgClassStr);
        if (DataUtils.isEmpty(areaTree)) {
            final List<String> areaCodes = getConfigAreaCodes(groupId);
            if (DataUtils.isEmpty(areaCodes)) {
                areaTree = new ArrayList<>();
            } else {
                areaTree = orgDubboService.getTreesByCodeList(areaCodes, orgClasses);
            }
            redisUtils.hset(Constants.REDIS_USER_CACHE + getCurrUserId(), "AREA_TREE_" + MaintConfig.GroupId + "_" + groupId + orgClassStr, areaTree, cacheTimeout);
        }
        return areaTree;
    }

    protected List<OrgDTO> getAreasTree(String groupId, List<Character> orgClasses, Long userId) throws Exception {
        String orgClassStr = DataUtils.isNotEmpty(orgClasses) ? "_" + orgClasses.stream().map(Object::toString).collect(Collectors.joining("_")) : "";
        List<OrgDTO> areaTree = (List<OrgDTO>) redisUtils.hget(Constants.REDIS_USER_CACHE + userId, "AREA_TREE_" + MaintConfig.GroupId + "_" + groupId + orgClassStr);
        if (DataUtils.isEmpty(areaTree)) {
            final List<String> areaCodes = getConfigAreaCodes(groupId);
            if (DataUtils.isEmpty(areaCodes)) {
                areaTree = new ArrayList<>();
            } else {
                areaTree = orgDubboService.getTreesByCodeList(areaCodes, orgClasses);
            }
            redisUtils.hset(Constants.REDIS_USER_CACHE + userId, "AREA_TREE_" + MaintConfig.GroupId + "_" + groupId + orgClassStr, areaTree, cacheTimeout);
        }
        return areaTree;
    }


    protected List<String> getTenantPrefix() throws Exception {
        return getConfigAreaCodes(GroupConstants.TENANT);
    }

    protected List<String> getPoolPrefix() throws Exception {
        return getConfigAreaCodes(GroupConstants.POOL);
    }

    /**
     * 获取取用户有权限访问的资源池数据域树，返回结果为裁剪后结果
     *
     * @return area pool tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaPoolTree(List<Character> orgClasses) throws Exception {
        return getAreasTree(GroupConstants.POOL, orgClasses);
    }

    /**
     * 获取取用户有权限访问集团数据域树，返回结果为裁剪后结果
     *
     * @return area telecom tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaTelecomTree(List<Character> orgClasses) throws Exception {
        return getAreasTree(GroupConstants.CHINA_TELECOM, orgClasses);
    }

    /**
     * 获取取用户有权限访问的某类型节点
     *
     * @param groupId  the group id
     * @param orgClasses the orgClass
     * @return area pool tree
     * @throws Exception the exception
     */
    protected List<String> getAllAreaCodeOfClass(String groupId, List<Character> orgClasses) throws Exception {
        final List<String> allAreaCodes = getAllAreaCodes(groupId);
        return allAreaCodes.stream().filter(c -> orgClasses.contains(OrgCodeUtils.getClass(c))).collect(Collectors.toList());

    }

    protected List<String> getAllAreaCodeOfClass(String groupId, List<Character> orgClasses, UserDTO userDTO) throws Exception {
        final List<String> allAreaCodes = getAllAreaCodes(groupId, userDTO);
        return allAreaCodes.stream().filter(c -> orgClasses.contains(OrgCodeUtils.getClass(c))).collect(Collectors.toList());

    }


    /**
     * 获取取用户有权限访问的某类型节点
     *
     * @param groupId  the group id
     * @param orgClasses the orgClass
     * @return the all areas
     */
    protected List<OrgDTO> getAllAreaOfClass(String groupId, List<Character> orgClasses) throws Exception {
        final List<OrgDTO> areasTree = getAreasTree(groupId, orgClasses);
        List<OrgDTO> result = new ArrayList<>();
        areasTree.forEach(item -> {
            result.addAll(getClassOrgFromTree(item, orgClasses));
        });
        return result;
    }

    protected List<OrgDTO> getAllAreaOfClass(String groupId, List<Character> orgClasses, Long userId) throws Exception {
        final List<OrgDTO> areasTree = getAreasTree(groupId, orgClasses, userId);
        List<OrgDTO> result = new ArrayList<>();
        areasTree.forEach(item -> {
            result.addAll(getClassOrgFromTree(item, orgClasses));
        });
        return result;
    }

    private List<OrgDTO> getClassOrgFromTree(OrgDTO orgDTO, List<Character> orgClasses) {
        List<OrgDTO> result = new ArrayList<>();
        List<OrgDTO> children = orgDTO.getChildren();

        if (orgClasses.contains(OrgCodeUtils.getClass(orgDTO.getOrgCode()))) {
            result.add(orgDTO);
            orgDTO.setChildren(new ArrayList<>());
        }
        if (DataUtils.isNotEmpty(children)) {
            for (OrgDTO child : children) {
                result.addAll(getClassOrgFromTree(child, orgClasses));
            }
        }
        return result;
    }




    /**
     * 取用户有权限访问的资源池OrgDTO
     *
     * @return area pools
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaPools() throws Exception {
        return getAllAreaOfClass(GroupConstants.POOL, OrgClassConstants.ALL_POOL);
    }


    /**
     * 取用户有权限访问的资源池OrgCode
     *
     * @return area pool codes
     * @throws Exception the exception
     */
    protected List<String> getAreaPoolCodes() throws Exception {
        return getAllAreaCodeOfClass(GroupConstants.POOL, OrgClassConstants.ALL_POOL);
    }

    /**
     * 取用户有权限访问的资源池OrgCode树
     *
     * @return the area tenant tree
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaTenantTree(List<Character> orgClasses) throws Exception {
        List<OrgDTO> areasTree = getAreasTree(GroupConstants.TENANT, orgClasses);
        if (DataUtils.isEmpty(orgClasses)) {
            return areasTree;
        }
        return getAreasTree(GroupConstants.TENANT, orgClasses);
    }

    /**
     * 取用户有权限访问的租户OrgDTO
     *
     * @return the area tenants
     * @throws Exception the exception
     */
    protected List<OrgDTO> getAreaTenants() throws Exception {
        return getAllAreaOfClass(GroupConstants.TENANT, Collections.singletonList(OrgClassConstants.ORG));
    }

    /**
     * 取用户有权限访问的租户OrgCode
     *
     * @return the area tenant codes
     * @throws Exception the exception
     */
    protected List<String> getAreaTenantCodes() throws Exception {
        return getAllAreaCodeOfClass(GroupConstants.TENANT, Collections.singletonList(OrgClassConstants.ORG));
    }

    /**
     * Check pool permission.
     *
     * @param poolOrgCodes the pool org codes
     * @throws Exception the exception
     */
    protected void checkPoolPermission(Collection<String> poolOrgCodes) throws Exception {
        checkPoolPermission(poolOrgCodes, "无权查看资源池");
    }

    /**
     * Check pool permission.
     *
     * @param poolOrgCode the pool org code
     * @throws Exception the exception
     */
    protected void checkPoolPermission(String poolOrgCode) throws Exception {
        checkPoolPermission(poolOrgCode, "无权查看资源池");
    }

    protected void checkPoolPermission(Collection<String> poolOrgCodes, String msg) throws Exception {
        checkPermission(poolOrgCodes, Collections.singletonList(GroupConstants.POOL), msg);
    }

    /**
     * Check pool permission.
     *
     * @param poolOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkPoolPermission(String poolOrgCode, String msg) throws Exception {
        checkPermission(poolOrgCode, Collections.singletonList(GroupConstants.POOL), msg);
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCodes the tenant org codes
     * @throws Exception the exception
     */
    protected void checkTenantPermission(Collection<String> tenantOrgCodes) throws Exception {
        checkTenantPermission(tenantOrgCodes, "无权查看租户");
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkTenantPermission(String tenantOrgCode) throws Exception {
        checkTenantPermission(tenantOrgCode, "无权查看租户");
    }

    protected void checkTenantPermission(Collection<String> tenantOrgCodes, String msg) throws Exception {
        checkPermission(tenantOrgCodes, Collections.singletonList(GroupConstants.TENANT), msg);
    }

    /**
     * Check tenant permission.
     *
     * @param tenantOrgCode the tenant org code
     * @throws Exception the exception
     */
    protected void checkTenantPermission(String tenantOrgCode, String msg) throws Exception {
        checkPermission(tenantOrgCode, Collections.singletonList(GroupConstants.TENANT), msg);
    }


    protected void checkPermission(Collection<String> orgCodes, List<String> groupIds, String msg) throws Exception {
        if (DataUtils.isEmpty(groupIds) || DataUtils.isEmpty(orgCodes)) {
            throw new BusinessException(msg);
        }

        List<String> allAreaCodes = new ArrayList<>();
        for (String groupId : groupIds) {
            allAreaCodes.addAll(getAllAreaCodes(groupId));
        }

        for (String orgCode : orgCodes) {
            if (!OrgCodeUtils.checkPermission(orgCode, allAreaCodes)) {
                throw new ForbiddenException(msg);
            }
        }
    }

    protected void checkPermission(String orgCode, List<String> groupIds, String msg) throws Exception {
        if (DataUtils.isEmpty(groupIds) || DataUtils.isEmpty(orgCode)) {
            throw new BusinessException(msg);
        }

        List<String> allAreaCodes = new ArrayList<>();
        for (String groupId : groupIds) {
            allAreaCodes.addAll(getAllAreaCodes(groupId));
        }

        if (!OrgCodeUtils.checkPermission(orgCode, allAreaCodes)) {
            throw new ForbiddenException(msg);
        }
    }

    // TODO 这个函数问题有点多，改完这波来调整
    protected void setOrgCodeAndOrgName(List<OrgDTO> orgDTOS, RoleUserVO roleUserVO) {
        if (DataUtils.isNotEmpty(orgDTOS)) {
            OrgDTO child = orgDTOS.get(0);
            // 记录最底层组织
            while (DataUtils.isNotEmpty(child.getChildren())) {
                child = child.getChildren().get(0);
            }
            roleUserVO.setOrgCode(child.getOrgCode());
        } else {
            throw new BusinessException("未查询到所属组织信息！");
        }
    }

    // TODO 这个函数问题有点多，改完这波来调整
    protected String setOrgCodeAndOrgName(List<OrgDTO> orgDTOS) {
        String orgCode = "";
        if (DataUtils.isNotEmpty(orgDTOS)) {
            OrgDTO child = orgDTOS.get(0);
            // 记录最底层组织
            while (DataUtils.isNotEmpty(child.getChildren())) {
                child = child.getChildren().get(0);
            }
            orgCode = child.getOrgCode();
        } else {
            throw new BusinessException("未查询到所属组织信息！");
        }
        return orgCode;
    }

    protected String getTeleComOrgCode() {
        List<OrgDTO> orgDTOS = userDTO.getOrgs(GroupConstants.CHINA_TELECOM);
        String orgCode = "";
        if (!userDTO.getAdminCase()) {
            if (DataUtils.isNotEmpty(orgDTOS)) {
                OrgDTO child = orgDTOS.get(0);
                // 记录最底层组织
                while (DataUtils.isNotEmpty(child.getChildren())) {
                    child = child.getChildren().get(0);
                }
                orgCode = child.getOrgCode();
            } else {
                throw new BusinessException("未查询到所属组织信息！");
            }
        }
        return orgCode;
    }

    protected List<String> getRealOrgByGroupFromDB(String group, OrgDO orgDO) {
        List<String> orgList = new ArrayList<>();
        if (DataUtils.isNotEmpty(orgDO) && DataUtils.isNotEmpty(orgDO.getDataList())) {
            List<String> dataList = JSON.parseArray(orgDO.getDataList(), String.class);
            orgList = dataList.stream().filter(item -> group.equals(OrgCodeUtils.getGroup(item))).collect(Collectors.toList());
        }
        return orgList;
    }

    /**
     * TODO 这个应该也要调整，有空再来
     * 根据用户权限下的约束，得到该组织下最大的子组织,第一个参数是维度，第二个参数是组织
     */
    protected List<String> getSubOrgCodeUnderAuthority(String groupName, String orgCode) throws Exception {
        final Set<String> areaCodes = new HashSet<>(getAllAreaCodes(groupName));
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

    protected void cleanUserCache() throws Exception {
        cleanUserCache(getCurrUserId());
    }

    protected void cleanUserCache(Long userId) {
        redisUtils.del(Constants.REDIS_USER_CACHE + userId);
    }

    protected void cleanUserCache(List<Long> userIds) {
        List<String> keys = new ArrayList<>();
        userIds.forEach( u-> keys.add(Constants.REDIS_USER_CACHE + u));
        redisUtils.del(keys);
    }

    private OrgDTO covertOrgDTO(OrgDO orgDO) {
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgId(orgDO.getId());
        orgDTO.setOrgCode(orgDO.getOrgCode());
        orgDTO.setParentCode(orgDO.getParentCode());
        orgDTO.setOrgType(orgDO.getOrgType());
        orgDTO.setOrgName(orgDO.getOrgName());
        orgDTO.setGroupId(orgDO.getGroupId());
        orgDTO.setIntBusiness(orgDO.getIntBusiness());
        orgDTO.setVarcharBusiness(orgDO.getVarcharBusiness());
        orgDTO.setChildren(new ArrayList<>());
        return orgDTO;
    }
}
