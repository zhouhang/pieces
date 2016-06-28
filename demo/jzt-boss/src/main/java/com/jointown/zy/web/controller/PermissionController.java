package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.PermissionSearchDto;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossPermissionService;

/**
 * 权限管理Controller
 * 
 * @author biran 2014-12-03
 */
@Controller(value = "PermissionController")
@RequestMapping(value = "getPermission")
public class PermissionController extends UserBaseController {
	private static final Logger log = LoggerFactory
			.getLogger(PermissionController.class);
	@Autowired
	private BossPermissionService bossPermissionService;

	/**
	 * 根据条件查询权限
	 * 
	 * @author biran
	 * @alter lichenxiao
	 * @param request
	 * @param response
	 * @param permissionSearchDto
	 *            查询时所带条件的DTO
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String searchPermission(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute() PermissionSearchDto permissionSearchDto,
			ModelMap modelMap) {
		log.info(permissionSearchDto.toString());

		Page page = new Page();
		HashMap<String, Object> params = new HashMap<String, Object>();
		String permissionName = request.getParameter("permissionNamePara");// 接收到的权限名称
		String parentName = request.getParameter("parentNamePara");// 接收到的权限的父权限名称
		String operationResource = request
				.getParameter("operationResourcePara");// 接收到的权限路径
		params.put("permissionName", permissionName);
		params.put("operationResource", operationResource);
		int parentId = -1;
		// 如果不带任务条件（父权限名称）查询
		if (parentName != null && parentName != "") {
			List<BossPermission> bossPermissionList = bossPermissionService
					.findBossPermissionByParentName(parentName);// 得到跟据父权限名称查询到的父权限ID号

			for (BossPermission bossPermission : bossPermissionList) {

				parentId = bossPermission.getPermissionId();

			}

		}
		params.put("parentId", parentId);
		params.put("parentName", parentName);
		page.setParams(params);// 将查询的参数设置给page对象

		String pageNo = request.getParameter("pageNo");
		// 设置翻页数
		if (pageNo != null && !"".equals(pageNo)) {
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}

		page.setPageSize(10);// 设置一页显示条数

		List<BossPermission> bossPermissionList = bossPermissionService
				.findAllBossPermission(page);
		page.setResults(bossPermissionList);
		modelMap.put("page", page);
		return "/public/permission";

	}

	/**
	 * 添加权限时验证权限名是否存在
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bossPermissionIsHaved")
	@ResponseBody
	public String bossPermissionIsHaved(HttpServletRequest request,
			HttpServletResponse response) {
		// Validform ajaxurl方法带入两个参数:param,name param是input的value值
		// name是input的name值
		String permissionCode = request.getParameter("param");
		if (null != bossPermissionService
				.findBossPermissionByPermissionCode(permissionCode)) {
			return "该权限名已存在！";
		}
		;
		return "y";
	}

	/**
	 * 修改权限时验证权限名是否与其它已有权限名重名
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bossPermissionIsHavedForUpdate")
	@ResponseBody
	public String bossPermissionIsHavedForUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		// Validform ajaxurl方法带入两个参数:param,name param是input的value值
		// name是input的name值
		String permissionCode = request.getParameter("param");
		String permissionId = request.getParameter("permissionId");
		List<BossPermission> bossPermissions = bossPermissionService
				.findBossPermissionByPermissionCodeForUpdate(permissionCode,
						permissionId);
		if (bossPermissions != null) {
			if (bossPermissions.size() >= 1) {
				return "该权限已存在！";
			}
		}
		return "y";
	}

	/**
	 * 获取权限树
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPermissionTree", method = RequestMethod.POST)
	@ResponseBody
	public String getOrganizationTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BossPermission> permissionlist = new ArrayList<BossPermission>();
		permissionlist = bossPermissionService.findAllBossPermission();
		JsonArray ja = new JsonArray();
		// 拼出前台ztree对应的格式
		for (BossPermission permission : permissionlist) {
			JsonObject j = new JsonObject();
			j.addProperty("id", permission.getPermissionId());
			j.addProperty("pId", permission.getParentId());
			j.addProperty("name", permission.getPermissionName());
			j.addProperty("type", permission.getType());
			j.addProperty("sortno", permission.getSortNo());
			j.addProperty("path", permission.getPath());
			if (permission.getParentId() == 0) {
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

	/**
	 * 添加权限
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPermission")
	@ResponseBody
	public String addBossPermission(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		BossPermission bossPermission = getBossPermission(request, response);

		JsonObject j = new JsonObject();
		String json = null;
		String backValue = null;
		try {
			// 修改权限
			backValue = bossPermissionService.addBossPermission(bossPermission);
			if (backValue == "addSuccess") {
				j.addProperty("status", "yes");
			} else {
				j.addProperty("status", "no");
			}
		} catch (Exception e) {
			j.addProperty("status", "no");
			e.printStackTrace();
		} finally {
			Gson gson = new Gson();
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			json = gson.toJson(j);
		}

		return json;
	}

	/**
	 * 根据权限Id获得权限对象
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findBossPermissionByCondition", method = RequestMethod.POST)
	@ResponseBody
	public BossPermission findBossPermissionByCondition(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		String permissionId = request.getParameter("permissionId");
		BossPermission bossPermission = bossPermissionService
				.findBossPermissionById(Integer.parseInt(permissionId));
		return bossPermission;
	}

	/**
	 * 修改权限
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePermission")
	@ResponseBody
	public String updatePermission(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		BossPermission bossPermission = new BossPermission();
		bossPermission
				.setPermissionName(request.getParameter("permissionName"));
		bossPermission.setPermissionId(Integer.parseInt(request
				.getParameter("permissionId")));
		bossPermission.setOperationResource(request
				.getParameter("operationResource"));

		bossPermission.setParentId(Integer.parseInt(request
				.getParameter("parentId")));
		String path = request.getParameter("permissionPath");
		String pathOld = request.getParameter("permissionPathOld");

		if (path != null && !"".equals(path)) {
			bossPermission.setPath(path + "/");
		} else {
			bossPermission.setPath(pathOld);
		}

		bossPermission.setType(Integer.parseInt(request
				.getParameter("permissionType")));
		bossPermission.setSortNo(request.getParameter("permissionSortNo"));

		JsonObject j = new JsonObject();
		String json = null;
		String backValue = null;
		try {
			// 修改权限
			backValue = bossPermissionService.updateBossPermission(
					bossPermission, path);
			if (backValue == "updateSuccess") {
				j.addProperty("status", "yes");
			} else {
				j.addProperty("status", "no");
			}
		} catch (Exception e) {
			j.addProperty("status", "no");
			e.printStackTrace();
		} finally {
			Gson gson = new Gson();
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			json = gson.toJson(j);

		}

		return json;
	}

	/**
	 * 删除前验证是否有子结点
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/validatePermissionDeleForNote", method = RequestMethod.POST)
	@ResponseBody
	public int validatePermissionDeleForNote(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String permissionId = request.getParameter("permissionId");
		int bossPermissionNoteNumber = bossPermissionService
				.findBossPermissionByNoteNumber(Integer.parseInt(permissionId));
		return bossPermissionNoteNumber;
	}

	/**
	 * 删除前验证是否有角色使用该权限
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/validatePermissionDeleForRole", method = RequestMethod.POST)
	@ResponseBody
	public int validatePermissionDeleForRole(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String permissionId = request.getParameter("permissionId");
		int bossPermissionRoleNumber = bossPermissionService
				.findBossPermissionByRoleNumber(Integer.parseInt(permissionId));
		return bossPermissionRoleNumber;
	}

	/**
	 * 删除权限
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
	@ResponseBody
	public int deletePermission(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		String permissionId = request.getParameter("permissionId");

		int bossPermissionUpdateStatus = bossPermissionService
				.bossPermissionByUpdateStatus(Integer.parseInt(permissionId));
		return bossPermissionUpdateStatus;
	}

	/**
	 * 获取页面对象值
	 * 
	 * @author lichenxiao
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public BossPermission getBossPermission(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BossPermission bossPermission = new BossPermission();
		String permissionId = request.getParameter("permissionId");
		String permissionName = request.getParameter("permissionName");
		String parentId = request.getParameter("parentId");
		String type = request.getParameter("permissionType");
		String operationResource = request.getParameter("operationResource");
		String status = request.getParameter("permissionStatus");
		String sortNo = request.getParameter("permissionSortNo");
		String path = request.getParameter("permissionPath");

		String className = request.getParameter("permissionClassName");

		if (permissionId != null && permissionId.length() != 0) {
			bossPermission.setPermissionId(Integer.parseInt(permissionId));
		}
		bossPermission.setPermissionName(permissionName);
		bossPermission.setParentId(Integer.parseInt(parentId));
		bossPermission.setType(Integer.parseInt(type.substring(0, 1)) + 1);
		bossPermission.setOperationResource(operationResource);
		bossPermission.setSortNo(sortNo);
		bossPermission.setPath(path + "/");

		return bossPermission;
	}
	
//
//	/**
//	 * 进入角色权限页
//	 * 
//	 * @author biran
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/setPermission", method = RequestMethod.GET)
//	public ModelAndView setPermission(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String roleId = request.getParameter("roleId");
//		String level0Id = request.getParameter("level0Id");
//
//		ModelAndView mav = new ModelAndView();
//		/*---------先获取到所有菜单的信息------------------*/
//		// 取0级菜单，系统级菜单
//		List<BossPermission> Level0Permission = bossPermissionService
//				.findAllLevel0BossPermission();
//		if (Level0Permission != null) {
//			if (level0Id == null || level0Id.equals("")) {
//				level0Id = Level0Permission.get(0).getPermissionId().toString();
//			}
//			mav.addObject("Level0Permission", Level0Permission);
//		}
//		// 如果没有取到0级菜单，给默认值0
//		if (level0Id == null || level0Id.equals("")) {
//			level0Id = "0";
//		}
//		// 取1级目录，2级菜单，3级按钮
//		List<BossPermission> Level1Permission = bossPermissionService
//				.findBossPermissionByLevel0Id(Integer.valueOf(level0Id),
//						Integer.valueOf(roleId));
//		if (Level1Permission != null && !Level1Permission.isEmpty()) {
//			mav.addObject("Level1Permission", Level1Permission);
//		}
//
//		mav.addObject("roleId", roleId);
//		mav.addObject("level0Id", level0Id);
//		mav.setViewName("/public/setPermission");
//		return mav;
//	}
}
