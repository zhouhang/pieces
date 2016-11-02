package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.boss.shiro.BossRealm;
import com.ms.dao.model.Resources;
import com.ms.dao.model.Role;
import com.ms.dao.model.RoleMember;
import com.ms.dao.vo.MemberVo;
import com.ms.dao.vo.RoleVo;
import com.ms.service.*;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import com.ms.tools.utils.WebUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制
 * Created by fengf on 2016/7/9.
 */
@RequestMapping(value = "/role")
@Controller
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private RoleResourcesService roleResourcesService;
    @Autowired
    private RoleMemberService roleMemberService;
    @Autowired
    private BossRealm bossRealm;

    /**
     * 角色列表页
     * @param request
     * @param response
     * @param advices
     * @param pageNum
     * @param pageSize
     * @param roleVo
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:list")
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        String  advices,
                        Integer pageNum,
                        Integer pageSize,
                        RoleVo roleVo,
                        ModelMap model){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<Role> roleVoPage =   roleService.findByCondition(roleVo,pageNum,pageSize);
        model.put("rolePage",roleVoPage);
        model.put("roleParams", Reflection.serialize(roleVo));
        model.put("advices",advices);
        return "role";
    }



    /**
     * 新增角色
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,
                      HttpServletResponse response,
                      ModelMap model){
        return "role_power";
    }

    /**
     * 权限列表
     * @param request
     * @param response
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "/power/{id}")
    public String power(HttpServletRequest request,
                        HttpServletResponse response,
                        @PathVariable("id") Integer id,
                        ModelMap model){
        if(id!=null){
            Role role =  roleService.findById(id);
            model.put("role",role);
        }
        return "role_power";
    }


    /**
     * 所有权限资源列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/resources")
    @ResponseBody
    public List resources(HttpServletRequest request,
                          HttpServletResponse response,
                          Integer roleId,
                          String rolename){
        List<Resources> resourcesList = resourcesService.findAll();
        List<Map<String,Object>> resultList = new ArrayList<>();
        List<Integer> resourcesIds = roleResourcesService.findResourcesByRole(roleId);
        for(Resources resources : resourcesList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",resources.getId());
            map.put("pId",resources.getPid());
            map.put("name",resources.getName());
            map.put("open",true);
            if(resourcesIds.contains(resources.getId())){
                map.put("checked",true);
            }
            if(rolename == null || (resources.getName().contains(rolename))){
                resultList.add(map);
            }
        }

        return resultList;
    }


    /**
     * 保存资源
     * @param request
     * @param response
     * @param roleId
     * @param resourcesIds
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "/resources/save")
    @ResponseBody
    public Result resourcesSave(HttpServletRequest request,
                                HttpServletResponse response,
                                Integer roleId,
                                String roleName,
                                @RequestParam(value="resourcesIds[]",required = false)Integer[] resourcesIds){
        roleResourcesService.updateRoleResources(roleId,resourcesIds,roleName);
        bossRealm.removeAuthorizationCacheInfo();
        return Result.success("权限保存成功!").data(roleId);
    }





    /**
     * 用户角色列表
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{roleId}")
    public String memberList(HttpServletRequest request,
                             HttpServletResponse response,
                             @PathVariable("roleId") Integer roleId,
                             Integer pageNum,
                             Integer pageSize,
                             MemberVo memberVo,
                             ModelMap model){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        if(roleId!=null){
            Role role =  roleService.findById(roleId);
            model.put("role",role);
        }
        PageInfo<RoleMember> roleMemberPage =roleMemberService.findByConditionAndRole(memberVo, pageNum, pageSize);
        model.put("roleMemberPage",roleMemberPage);
        model.put("memberParams",memberVo.toString());
        return "role_power";
    }



    /**
     *  删除角色
     * @param request
     * @param response
     * @param roleId
     * @return
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "delete/{roleId}")
    @ResponseBody
    public Result delete(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model,
                         @PathVariable("roleId") Integer roleId){
        roleService.deleteRole(roleId);
        return Result.success();
    }



}
