package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BossPermissionService;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.vo.BossUserOrgRoleVO;
import com.jointown.zy.common.wms.WmsApiCommon;
import com.jointown.zy.web.shiro.BossRealm2;
/**
 * 账号管理Controller
 * @author zhouji
 * 2014年11月26日下午3:42:08
 */
@Controller(value = "bossUserController")
@RequestMapping(value="/bossUser")
public class BossUserController {
	
	private final static Logger logger = LoggerFactory.getLogger(BossUserController.class);
	
	@Autowired
	private BossUserService bossUserService;
	@Autowired
	private BossRealm2 bossRealm;
	@Autowired
	private BossPermissionService permissionService;
	
	/**
	 * 跳转到账号管理页面
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getBossUserManager(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		BossUser bossuser = this.getBossUser(request, response);
		String pageNo = request.getParameter("pageNo");
		Page page = new Page();
		if(StringUtils.isNotEmpty(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", bossuser.getUserCode());
		params.put("userName", bossuser.getUserName());
		params.put("orgName", bossuser.getOrgName());
		params.put("orgId", bossuser.getOrgId());
		params.put("roleName", bossuser.getRoleName());
		params.put("roleId", bossuser.getRoleId());
		page.setParams(params);
 		List<BossUser> buserlist = bossUserService.getBossUsersByCondition(page);
 		page.setResults(buserlist);
		model.put("page", page);
		
		return "/public/bossUserManager";
	}
	/**
	 * 添加账号
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addBossUserManager")
	@ResponseBody
	public String addBossUserManager(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		Gson gson = new Gson();
		JsonObject j = new JsonObject();
		BossUser bossuser = getBossUser(request, response);
		//此处查询是否有用户名存在，不考虑状态
		BossUser existedUser = bossUserService.findBossUserByUserCode(bossuser.getUserCode(),true);
		if(existedUser!=null){
			j.addProperty("status","no");
			j.addProperty("msg","该用户名已存在,请重新输入！");
		}else{
			bossUserService.addBossUser(bossuser);
			j.addProperty("status","yes");
			/***************wms api 账号添加  接口嵌入开始  ldp******************/
			BossUser bossUser2 = bossUserService.findBossUserByUserCode(bossuser.getUserCode());
			String bossJson = WmsApiCommon.bossUserToJsonStr(bossUser2);
			RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.BOSS_USER_ADD, bossJson);
			/***************wms api 账号添加  接口嵌入结束******************/
		}
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(j);
		return json;
	}
	/**
	 * 修改账号信息
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/alterBossUserManager")
	@ResponseBody
	public String alterBossUserManager(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		BossUser bossuser = getBossUser(request, response);
		try{
			bossUserService.updateBossUser(bossuser);
			//add byMr.song 2015.5.12 14:23 修改角色后清空上次登录后的角色信息
			bossRealm.removeAuthorizationCacheInfo();  
		}catch(NullPointerException e){
			logger.warn("BossUserController.alterBossUserManager==>NullPointerException");
		}
		JsonObject j = new JsonObject();
		j.addProperty("status","yes");
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		/***************wms api 账号修改  接口嵌入开始  ldp******************/
		BossUser bossUser2 = bossUserService.findBossUserByUserCode(bossuser.getUserCode());
		String bossJson = WmsApiCommon.bossUserToJsonStr(bossUser2);
		RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.BOSS_USER_UPDATE, bossJson);
		/***************wms api 账号修改  接口嵌入结束******************/
		String json = gson.toJson(j);
		return json;
	}
	
	/**
	 * 根据登录名获得对象
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findBossUserByCondition")
	@ResponseBody
	public BossUser findBossUserByCondition(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String userCode = request.getParameter("userCode");
		BossUser bossuser = bossUserService.findBossUserByUserCode(userCode);
		return bossuser;
	}
	
	/**
	 * 验证用户名是否存在
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/bossUserIsHaved")
	@ResponseBody
	public String bossUserIsHaved(HttpServletRequest request,HttpServletResponse response){
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		String userCode = request.getParameter("param");
		//此处查询忽略用户状态
		if (null != bossUserService.findBossUserByUserCode(userCode,true)) {
			return "该会员名已存在,请重新输入！";
		};
		return "y";
	}
	
	/**
	 * 更改状态(锁定,解锁,删除共用)
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public String changeStatus(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		BossUser bossuser = getBossUser(request, response);
		bossUserService.updateBossUserByLock(bossuser);
		/***************wms api 更改状态  接口嵌入开始  ldp******************/
		BossUser bossUser2 = bossUserService.findBossUserByUserCode(bossuser.getUserCode());
		String bossJson = WmsApiCommon.bossUserToJsonStr(bossUser2);
		RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.BOSS_USER_UPDATE, bossJson);
		/***************wms api 更改状态  接口嵌入结束******************/
		return "yes";
	}
	
	/**
	 * 重置密码
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resetBossUserPassWord")
	@ResponseBody
	public String resetBossUserPassWord(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String userId = request.getParameter("userId");
		bossUserService.secretReset(userId);
		return "yes";
	}
	
	/**  
	 * 取到前台数据封装成对象
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public BossUser getBossUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId = request.getParameter("userId");
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String mobile = request.getParameter("mobile");
		String status = request.getParameter("status");
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String roleName = request.getParameter("roleName");
		String roleId = request.getParameter("roleId");
		
		BossUser bossuser = new BossUser();
		
		if(userId != null && userId.length() != 0){
			bossuser.setUserId(Integer.parseInt(userId));
		}
		bossuser.setUserCode(userCode==null?null:userCode.trim());
		bossuser.setPassword(password);
		bossuser.setUserName(userName);
		bossuser.setMobile(mobile);
		if(status != null && status.length() != 0){
			bossuser.setStatus(Integer.parseInt(status));
		}
		if(orgId != null && orgId.length() != 0){
			bossuser.setOrgId(Integer.parseInt(orgId));
		}
		bossuser.setOrgName(orgName);
		bossuser.setRoleId(roleId);
		bossuser.setRoleName(roleName);
		return bossuser;
	}
	/**
	 * 验证前台数据有效性
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String validateInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String mobile = request.getParameter("mobile");
		
		//验证数据有效性正确跳转到对应方法,错误返回错误信息
		return "";
	}
	
	
	
	/**
	 * 获取管理员用户名、组织角色信息
	 * @author ldp
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getBossUserOrgAndRoleInfo")
	public String getBossUserOrgAndRoleInfo(ModelMap modelMap) {
		Subject subject = SecurityUtils.getSubject();
		BossUserOrgRoleVO bossUserOrgRoleVO = bossUserService
				.getBossUserOrgRole(String.valueOf(subject.getPrincipal()));
		modelMap.addAttribute("bossUserOrgRole", bossUserOrgRoleVO);
		return "public/renameAccount";
	}
	
	/**
	 * 管理员修改自己账号密码
	 * @author ldp
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getBossUserOrgAndRoleInfo/modifyBossUserPassword")
	public String modifyBossUserPassword(HttpServletRequest request,ModelMap modelMap){
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String verityPassword = request.getParameter("configPassword");
		
		Subject subject = SecurityUtils.getSubject();
		modelMap.addAttribute("bossUserOrgRole", bossUserService
				.getBossUserOrgRole(String.valueOf(subject.getPrincipal())));
		
		if ((oldPassword == null || oldPassword.equals(""))
				|| (newPassword == null || newPassword.equals(""))
				|| (verityPassword == null || verityPassword.equals(""))) {
			modelMap.addAttribute("errorMsg", "密码信息不能为空！");
			return "public/renameAccount";
		}
		
		if (!newPassword.equals(verityPassword)) {
			modelMap.addAttribute("errorMsg", "两次输入的密码不一致！");
			return "public/renameAccount";
		}
		
		String modifyFlag = bossUserService.modifyBossUserSecret(
				String.valueOf(subject.getPrincipal()),oldPassword, newPassword);
		
		if ("1".equals(modifyFlag)) {//修改成功
			logger.info("clear cache!");
			bossRealm.removeAuthenticationCacheInfo();//清除缓存
			logger.info("本工作账号修改密码成功！");
			modifyFlag = "修改成功";
		}
		modelMap.addAttribute("modifyMsg",modifyFlag);
		
		return "public/renameAccount";
		
	}
	
}
