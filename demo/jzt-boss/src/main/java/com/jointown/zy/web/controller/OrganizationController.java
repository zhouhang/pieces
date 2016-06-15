package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.BossPermissionService;
import com.jointown.zy.common.service.BossRoleService;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.service.OrganizationService;
import com.jointown.zy.common.vo.BossRoleVO;
/**
 * 组织管理Controller
 * @author zhouji
 * 2014年11月18日下午3:25:17
 */
@Controller(value = "organizationController")
@RequestMapping(value="/organization")
public class OrganizationController {

	private final static Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private BossPermissionService bpmService;
	@Autowired
	private BossUserService bossUserService;
	@Autowired
	private BossRoleService bossRoleService;
	/**
	 * 跳转到组织机构管理界面
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getOrganiztion(HttpServletRequest request, HttpServletResponse response,ModelMap model)
			throws Exception {
		model.put("status", null);
		return "/public/organization";
	}
	
	/**
	 * 获取组织机构树
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrganizationTree",method=RequestMethod.POST)
	@ResponseBody
	public String getOrganizationTree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Organization> orglist = orgService.getAllOrganization();
		JsonArray ja = new JsonArray();
		//拼出前台ztree对应的格式
		for (Organization org : orglist) {
			JsonObject j = new JsonObject();
			j.addProperty("id", org.getId());
			j.addProperty("pId", org.getParentId());
			j.addProperty("name", org.getOrgName());
			if(org.getParentId()==0){
				j.addProperty("open", true);
			}
			ja.add(j);
		}
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(ja);
		return json;
	}
	
	@RequestMapping(value="/getRoleTree",method=RequestMethod.POST)
	@ResponseBody
	public String getRoleTree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<BossRole> rolelist = bossRoleService.findBossRoles();
		JsonArray ja = new JsonArray();
		//拼出前台ztree对应的格式
		for (BossRole role : rolelist) {
			JsonObject j = new JsonObject();
			j.addProperty("id", role.getRoleId());
			j.addProperty("name", role.getRoleName());
			ja.add(j);
		}
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(ja);
		return json;
	}
	
	/**
	 * 通过id查询组织名称及父级名称
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrgNameById",method=RequestMethod.POST)
	@ResponseBody
	public List<String> getOrgNameById(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String id = request.getParameter("id");
		String porgName = orgService.getParentsOrgName(Integer.parseInt(id));
		String orgName = orgService.getOrganizationName(Integer.parseInt(id));
		//response.setCharacterEncoding("utf-8");
		List<String> orglist = new ArrayList<String>();
		orglist.add(porgName);
		orglist.add(orgName);
		return orglist;
	}
	
	/**
	 * 验证组织名称
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/validateOrgName",method=RequestMethod.POST)
	@ResponseBody
	public String validateOrgName(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String orgName = request.getParameter("param");
		String msg = orgService.validateOrgName(orgName);
		return msg;
	}
	
	/**
	 * 添加组织机构
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addOrganization",method=RequestMethod.POST)
	@ResponseBody
	public String addOrganization(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer pId = Integer.parseInt(request.getParameter("orgId"));
		String orgName = request.getParameter("orgName");
		Organization porg = orgService.getOrganizationById(pId);
		Integer orgLevel = porg.getOrgLevel();
		Organization org = new Organization();
		org.setParentId(pId);
		org.setOrgName(orgName);
		org.setOrgLevel(orgLevel+1);//层级默认父级+1
		orgService.addOrganization(org);
		JsonObject j = new JsonObject();
		j.addProperty("status","yes");
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(j);
		return json;
	}
	
	/**
	 * 修改组织机构
	 * @author zhouji
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOrganization",method=RequestMethod.POST)
	@ResponseBody
	public String updateOrganization(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = Integer.parseInt(request.getParameter("orgId"));
		String orgName = request.getParameter("orgName");
		Organization org = orgService.getOrganizationById(id);
		org.setOrgName(orgName);
		orgService.updateOrganization(org);
		JsonObject j = new JsonObject();
		j.addProperty("status","yes");
		Gson gson = new Gson();
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		String json = gson.toJson(j);
		return json;
	}
	
	/**
	 * 删除时判断是否有子集
	 * @author zhouji
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/validateOrgChildren",method=RequestMethod.POST)
	@ResponseBody
	public Boolean validateOrgChildren(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = Integer.parseInt(request.getParameter("orgId"));
		//根据id查询子集
		boolean b = orgService.validateOrgChildren(id);
		return b;
	}
	
	/**
	 * 删除组织(修改状态)
	 * @author zhouji
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteOrganization",method=RequestMethod.POST)
	@ResponseBody
	public String deleteOrganization(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = Integer.parseInt(request.getParameter("orgId"));
		Organization org = orgService.getOrganizationById(id);
		orgService.deleteOrganization(org);
		return "yes";
	}
	/**
	 * 根据组织Id查询该组织下的账号
	 * @author zhouji
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findBossUsersByOrgId")
	public String findBossUsersByOrgId(HttpServletRequest request, HttpServletResponse response,ModelMap model)
			throws Exception {
		String orgId = request.getParameter("orgId");
		String status = request.getParameter("status");
		String orgName = "";
		List<BossUser> buserlist = new ArrayList<BossUser>();
		if(!orgId.isEmpty()){
			orgName = orgService.getParentsOrgName(Integer.parseInt(orgId));
			buserlist = bossUserService.getBossUsersByOrgId(Integer.parseInt(orgId),status);
		}
		model.put("status", status);
		model.put("orgName", orgName);
		model.put("buserlist", buserlist);
		return "/public/organization";
	}
}
