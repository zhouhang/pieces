package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jointown.zy.common.service.BossRolePermissionService;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.web.shiro.BossRealm2;


/**
 * 角色权限管理Controller
 * @author biran
 * 2014-12-05
 */
@Controller(value = "RolePermissionController")
public class RolePermissionController {
	@Autowired
	private BossRolePermissionService bossRolePermissionService;
	@Autowired
	private BossRealm2 bossRealm;
	private static final Logger log = LoggerFactory.getLogger(RolePermissionController.class);

	/**
	 * 保存角色权限
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bossRole/saveRolePermission",method=RequestMethod.POST)
	public String saveRolePermission(HttpServletRequest request, HttpServletResponse response,ModelMap model)
			throws Exception {
		String roleId = request.getParameter("roleId");
		String parentId=request.getParameter("level0Id");//0级系统ID
		String roleNamePara=request.getParameter("roleNamePara");//列表页参数
		ModelAndView mav = new ModelAndView();
		mav.addObject("roleNamePara", roleNamePara);
		String[] permissionIds =request.getParameterValues("permissionIds[]");
		//开始更新角色权限
		bossRolePermissionService.saveRolePermission(Integer.valueOf(parentId),Integer.valueOf(roleId),permissionIds);
		//更新完之后，清除权限缓存，不需重新登录即可使用权限。
		bossRealm.removeAuthorizationCacheInfo();
		
		return "redirect:../bossRole";
		//return "/public/role";
	}
	
}
