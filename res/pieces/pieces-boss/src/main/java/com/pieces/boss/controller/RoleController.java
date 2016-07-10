package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Role;
import com.pieces.dao.vo.RoleVo;
import com.pieces.service.RoleService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色控制
 * Created by burgl on 2016/7/9.
 */
@RequestMapping(value = "role")
@Controller
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

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
        model.put("roleVoPage",roleVoPage);
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
    @RequestMapping(value = "/info/{id}")
    public String info(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("id") Integer id,
                       ModelMap model){

        if(id!=null){
            Role role =  roleService.findById(id);
            model.put("role",role);
        }
        return "role_info";
    }

    /**
     * 新增角色
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,
                      HttpServletResponse response,
                      ModelMap model){


        return "role_info";
    }

    /**
     * 保存角色信息
     * @param request
     * @param response
     */
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


}
