package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossPermissionService;
import com.jointown.zy.common.service.BossRoleService;
import com.jointown.zy.common.vo.BossRoleVO;

/**
 * 角色管理Controller
 * @author biran
 * 2014-11-24 
 */
@Controller(value = "roleController")
@RequestMapping(value="/bossRole")
public class RoleController {
	@Autowired
	private BossRoleService bossRoleService;
	@Autowired
	private BossPermissionService bossPermissionService;
	/**
	 * 角色管理页面
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/getRole",method=RequestMethod.GET)
//	public String getOrganiztion(ModelMap model)
//			throws Exception {
//		//方式1：直接list放入到页面中
//		List<BossRole> rolelist = bossRoleService.findBossRoles();
//		model.put("rolelist", rolelist);
//		return "/public/role";
//	}
	@RequestMapping(value="")
	public String getRole(HttpServletRequest request, HttpServletResponse response,ModelMap model)
			throws Exception {
		String roleName = request.getParameter("roleNamePara");
		//封装为page
		String pageNo = request.getParameter("pageNo");
		Page page = new Page();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		page.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName",roleName);
		page.setParams(params);
		List<BossRole> rolelist= bossRoleService.findBossRoles(page);
 		page.setResults(rolelist);
 		ModelAndView mav=new ModelAndView();
        model.put("page", page);
        return "/public/role";
	}
	
	/**
	 * 新增角色
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addRole")
	@ResponseBody
	public String addRole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String newRoleName = request.getParameter("newRoleName");
		
		JsonObject j = new JsonObject();
		String json =null;
		try{
			//新增角色
			bossRoleService.addBossRole(newRoleName);
			j.addProperty("status", "yes");
		} catch (Exception e) {
			j.addProperty("status", "no");
			e.printStackTrace();
		} finally{
			Gson gson = new Gson();
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			json = gson.toJson(j);
			
		}
		return json;
		//return "redirect:../bossRole";
	}
	
	/**
	 * 更新角色
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public String updateRole(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String updateRoleName = request.getParameter("updateRoleName");
		String updateRoleId = request.getParameter("updateRoleId");
		Integer roleId = Integer.valueOf(updateRoleId);

		JsonObject j = new JsonObject();
		String json =null;
		try{
			//修改角色
			bossRoleService.updateBossRole(roleId,updateRoleName);
			j.addProperty("status", "yes");
		} catch (Exception e) {
			j.addProperty("status", "no");
			e.printStackTrace();
		} finally{
			Gson gson = new Gson();
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			json = gson.toJson(j);
			
		}
		return json;
		
		//return "redirect:../bossRole";
	}
	
	
	/**
	 * 删除角色
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteRoleById",method=RequestMethod.POST)
	@ResponseBody
	public void deleteRoleById(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer roleId = Integer.valueOf(request.getParameter("roleId"));
		//新增角色
		bossRoleService.deleteBossRoleById(roleId);
	}
	
	/**
	 * 新增时，验证角色名称
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/validateNewRoleName")
	@ResponseBody
	public String validateRoleName(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//新名称
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		String newRoleName = request.getParameter("param");
		if(newRoleName.length()>20){
			return "角色名称不能大于20个字";
		}
		String msg = bossRoleService.validateRoleName(newRoleName);
		if(msg!=null&&!"".equals(msg)){
			return msg;
		}
		return "y";
	}
	
	
	
	/**
	 * 修改时，验证角色名称
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/validateUpdateRoleName")
	@ResponseBody
	public String validateUpdateRoleName(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//新名称
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		String updateRoleName = request.getParameter("param");
		if(updateRoleName.length()>20){
			return "角色名称不能大于20个字";
		}
		String OriRoleName = request.getParameter("OriRoleName");
		String roleId = request.getParameter("roleId");
		if(OriRoleName==null||OriRoleName.equals("")){
			return "系统错误，未取到原角色名称";
		}
		if(updateRoleName==null||updateRoleName.equals("")){
			return "请输入角色";
		}
		if(OriRoleName.equals(updateRoleName)){
			return "y";
		}
		//校验名称
		String msg = bossRoleService.validateRoleName(updateRoleName);
		if(msg!=null&&!"".equals(msg)){
			return msg;
		}
		//校验状态
		
		 msg = bossRoleService.validateRoleStatus(roleId);
		 if(msg.equals("01")){
			 return "角色已删除";
		 }
		return "y";
	}
	
	/**
	 * 修改，删除时，验证角色状态
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/validateRoleDele",method=RequestMethod.POST)
	@ResponseBody
	public String validateRoleDele(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//新名称
		String roleId = request.getParameter("roleId");
		String msg = bossRoleService.validateRoleDele(roleId);
		if(msg==null||msg.equals("")){
			msg="00";
		}
		return msg;
	}
	
	
	/**
	 * 进入角色权限页
	 * 
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setPermission", method = RequestMethod.GET)
	public ModelAndView setPermission(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		String level0Id = request.getParameter("level0Id");
		//列表页面的参数
		String roleNamePara=request.getParameter("roleNamePara");
		ModelAndView mav = new ModelAndView();
		/*---------先获取到所有菜单的信息------------------*/
		// 取0级菜单，系统级菜单
		List<BossPermission> Level0Permission = bossPermissionService
				.findAllLevel0BossPermission();
		if (Level0Permission != null) {
			if (level0Id == null || level0Id.equals("")) {
				level0Id = Level0Permission.get(0).getPermissionId().toString();
			}
			mav.addObject("Level0Permission", Level0Permission);
		}
		// 如果没有取到0级菜单，给默认值0
		if (level0Id == null || level0Id.equals("")) {
			level0Id = "0";
		}
		// 取1级目录，2级菜单，3级按钮
		List<BossPermission> Level1Permission = bossPermissionService
				.findBossPermissionByLevel0Id(Integer.valueOf(level0Id),
						Integer.valueOf(roleId));
		if (Level1Permission != null && !Level1Permission.isEmpty()) {
			mav.addObject("Level1Permission", Level1Permission);
		}

		mav.addObject("roleId", roleId);
		mav.addObject("level0Id", level0Id);
		mav.addObject("roleNamePara", roleNamePara);
		mav.setViewName("/public/setPermission");
		return mav;
	}
	

}
