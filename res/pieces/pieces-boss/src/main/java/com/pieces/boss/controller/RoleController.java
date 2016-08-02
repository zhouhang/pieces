package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.shiro.BossRealm;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.Resources;
import com.pieces.dao.model.Role;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.MemberVo;
import com.pieces.dao.vo.RoleVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;
import com.pieces.tools.utils.gson.GsonExclusion;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制
 * Created by burgl on 2016/7/9.
 */
@RequestMapping(value = "role")
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
    @RequiresPermissions(value = "role:view")
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
        model.put("roleParams",roleVo.toString());
        model.put("advices",advices);
        return "role";
    }

    /**
     * 角色编辑
     * @param request
     * @param response
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "/info/{id}")
    public String info(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("id") Integer id,
                       ModelMap model){

        if(id!=null){
            Role role =  roleService.findById(id);
            model.put("role",role);
        }
        return "role-info";
    }

    /**
     * 新增角色
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:add")
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,
                      HttpServletResponse response,
                      ModelMap model){
        return "role-info";
    }

    /**
     * 权限列表
     * @param request
     * @param response
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = "role:power")
    @RequestMapping(value = "/power/{id}")
    public String power(HttpServletRequest request,
                        HttpServletResponse response,
                        @PathVariable("id") Integer id,
                        ModelMap model){
        if(id!=null){
            Role role =  roleService.findById(id);
            model.put("role",role);
        }
        return "role-power";
    }


    /**
     * 所有权限资源列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/resources")
    public void resources(HttpServletRequest request,
                          HttpServletResponse response,
                          Integer roleId){
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

            resultList.add(map);
        }
        WebUtil.printJson(response,resultList);
    }


    /**
     * 保存资源
     * @param request
     * @param response
     * @param roleId
     * @param resourcesIds
     */
    @RequestMapping(value = "/resources/save")
    public void resourcesSave(HttpServletRequest request,
                              HttpServletResponse response,
                              Integer roleId,
                              @RequestParam(value="resourcesIds[]",required = false)Integer[] resourcesIds){
        roleResourcesService.updateRoleResources(roleId,resourcesIds);
        bossRealm.removeAuthorizationCacheInfo();
        WebUtil.print(response,new Result(true).info("权限保存成功!"));
    }

    /**
     * 保存角色信息
     * @param request
     * @param response
     */
    @RequiresPermissions(value = "role:edit")
    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request,
                     HttpServletResponse response,
                     Role role){
        if(role.getId()==null){
            roleService.add(role);
        }else{
            roleService.update(role);
        }
        WebUtil.print(response,new Result(true).data(role));
    }


    /**
     * 用户拥有那些角色
     * @param request
     * @param response
     */
    @RequestMapping(value = "/member")
    public void roleAll(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer memberId){

        List<RoleMember> roleMemberList= roleMemberService.findByMemberId(memberId);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(RoleMember roleMember : roleMemberList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",roleMember.getRoleId());
            mapList.add(map);
        }
        WebUtil.print(response,mapList);
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
        return "role-list";
    }



    /**
     *  删除角色
     * @param request
     * @param response
     * @param roleId
     * @return
     */
    @RequiresPermissions(value = "role:delete")
    @RequestMapping(value = "delete")
    public String delete(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap model,
                         Integer roleId){
        roleService.deleteRole(roleId);
        model.put("advices","删除角色成功");
        return "redirect:/role/index";
    }



}
