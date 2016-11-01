package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.boss.shiro.BossRealm;
import com.ms.dao.model.Member;
import com.ms.dao.model.Role;
import com.ms.dao.vo.MemberVo;
import com.ms.service.MemberService;
import com.ms.service.RoleMemberService;
import com.ms.service.RoleService;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import com.ms.tools.utils.WebUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * BOSS用户管理
 * Created by fengf on 2016/7/8.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController{

    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMemberService roleMemberService;
    @Autowired
    private BossRealm bossRealm;
    /**
     * BOSS用户列表页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer pageNum,
                        Integer pageSize,
                        String  advices,
                        MemberVo memberVo,
                        ModelMap model){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<Member> memberPage =memberService.findByCondition(memberVo, pageNum, pageSize);
        List<Role> roleList = roleService.findAll();
        model.put("roleList",roleList);
        model.put("memberPage",memberPage);
        model.put("memberParams", Reflection.serialize(memberVo));
        model.put("advices",advices);
        return "member";
    }

    /**
     * BOSS用户编辑页
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions(value = "member:add")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model){

        return "member-info";
    }


    /**
     * BOSS用户编辑页
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions(value = "member:edit")
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @PathVariable("id") Integer id){
        if(id!=null){
            MemberVo memberVo = new MemberVo();
            memberVo.setId(id);
            PageInfo<Member> memberPage = memberService.findByCondition(memberVo, 0, 10);
            model.put("member",memberPage.getList().get(0));
        }
        return "member-info";
    }


    /**
     * BOSS用户存储页面
     * @param request
     * @param response
     * @param member
     */
    @RequiresPermissions(value = {"member:add","member:edit"},logical = Logical.OR)
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(HttpServletRequest request,
                     HttpServletResponse response,
                     Member member,
                     Integer roleId){
        String advices = "新增用户信息成功!";
        if(member.getId()==null){
            member.setIsDel(false);
            memberService.addMember(member,roleId);
        }else{
            memberService.updateMember(member,roleId);
            advices = "修改用户信息成功!";
        }
        bossRealm.removeAuthenticationCacheInfo();
        return Result.success().msg(advices);
    }

    /**
     * BOSS用户删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @PathVariable("id") Integer id,
                                             ModelMap model){
        if(id!=null){
            roleMemberService.deleteByMember(id);
            memberService.deleteById(id);
        }
        return Result.success();
    }


    /**
     * BOSS角色编辑
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions(value = "member:role")
    @RequestMapping(value = "/role/{id}")
    public String role(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("id") Integer id,
                       ModelMap model){
        if(id!=null){
            Member member = memberService.findById(id);
            model.put("member",member);
        }
        List<Role> roleList =  roleService.findAll();
        model.put("roleList",roleList);
        return "member-role";
    }


    /**
     * 保存角色
     * @param request
     * @param response
     */
    @RequiresPermissions(value = "member:role")
    @RequestMapping(value = "/role/save")
    @ResponseBody
    public Result roleSave(HttpServletRequest request,
                         HttpServletResponse response,
                         Integer memberId,
                         Integer[] roleIds){
        roleMemberService.createRoleMember(roleIds,memberId);
        return Result.success().msg("修改角色成功!");
    }


}
