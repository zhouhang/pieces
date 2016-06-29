package com.jointown.zy.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.ClassInfo;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.service.ClassInfoService;
import com.jointown.zy.common.vo.BossUserVo;
/**
 * 
*    
* 项目名称：jzt-boss   
* 类名称：CategoryController   
* 类描述： 类目相关Controller  
* 创建人：fanyuna   
* 创建时间：2014年12月25日 下午5:16:16   
* 修改人：fanyuna   
* 修改时间：2014年12月25日 下午5:16:16   
* 修改备注：   
* @version    
*
 */
@Controller
public class CategoryController {

	@Resource
	private CategorysService categoryService;
	@Resource
	private ClassInfoService classInfoService;
	@Resource
	private BossUserService bossUserService;
	/**
	 * 
	
	* @Title: getCategoryManage 
	
	* @Description: 第一次进类目管理 查询所有的有效类目分类
	
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	
	* @return String    跳转到类目管理页面
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage")
	public String getCategoryManage(HttpServletRequest request, HttpServletResponse response){
		List<ClassInfo> classInfoList = classInfoService.getEffectClass();
		
		//第一个分类
		if(classInfoList != null && classInfoList.size()>0){
			ClassInfo info = classInfoList.get(0);
			 request.setAttribute("firstClass", info);
		}
		request.setAttribute("classInfoList", classInfoList);
		return "public/categoryManage";
	}
	
	/**
	 * 批量修改排序规则
	 * 参数:10021:2,10022:3 ，逗号分割数据，冒号前面的是主键id,后面是自定义排序数字
	 * false表示失败，true表示成功
	 * add by Mr.song 2015.4.3
	 */
	@RequestMapping(value="/getCategoryManage/saveRules")
	@ResponseBody
	public boolean saveRules(HttpServletRequest request){
		String rules = request.getParameter("vmap");
		boolean flag =false;
		if(StringUtils.isBlank(rules)){
			return flag;
		}
		String[] array = rules.split(",");
		try{
			flag = categoryService.updateIndex(array);
		}catch(Exception e){flag =false;}
		return flag;
	}
	
	/**
	 * 验证规则是否存在
	 * true表示存在,false表示不存在，可以继续下一步
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCategoryManage/validRules")
	@ResponseBody
	public int validRules(HttpServletRequest request){
		String cindex = request.getParameter("cindex");
		String categorysid = request.getParameter("categorysid");
		int rules = 0;
		rules = categoryService.getExistRules(cindex,categorysid);
		return rules;
	}
	
	/**
	 * 
	
	* @Title: getCategoryManage 
	
	* @Description: 根据分类ID及其第一个类目ID查询此类目及其子类目关联的品种
	
	* @param @param request
	* @param @param response
	* @param @param classId
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/getCategoryBy")
	@ResponseBody
	public String getCategoryManage(HttpServletRequest request, HttpServletResponse response,
			Long classId,Long categoryId,String pageNo,String name,String code,String place){
		if(classId == null){
			List<ClassInfo> classInfoList = classInfoService.getEffectClass();
			if(classInfoList != null && classInfoList.size()>0){
				 classId = classInfoList.get(0).getClassId();
				}
		}
		 if(classId!=null){
			request.setAttribute("classId", classId);
			if(categoryId==null){
				   List<Categorys> catlist = categoryService.selectCategoryBy(classId);
				   if(catlist != null && catlist.size()>0){
					   categoryId = catlist.get(0).getId();
				   }
			   }
				
				//分类的第一个类目
				if(categoryId!=null){
					Page<Map<String,Object>> page = new Page<Map<String,Object>>();
					page.setPageNo(StringUtils.isNotBlank(pageNo)?Integer.valueOf(pageNo):1);
						
					Map<String,Object> map = new HashMap<String,Object>();
						map.put("classId", classId);//不用于查询只用于将些值带到页面使用
						map.put("categoryId", categoryId);
						map.put("name", name);
						map.put("code", code);
						map.put("place", place);
						page.setParams(map);
						//第一个分类 第一个类目及其子类目关联的品种
						List<Map<String,Object>> breedMapList = categoryService.getBreedByCategory(page);
						page.setResults(breedMapList);
						//request.setAttribute("page", page);
						Gson gson = new Gson();
						// 加以下语句：页面中文没有乱码
						response.setCharacterEncoding("utf-8");
						return gson.toJson(page);
						
				}
		 }
		return null;
	}
	/**
	 * 条件查询此类目及其子类目关联的品种(默认查询第一页数据)
	 * @param request
	 * @param response
	 * @param classId
	 * @param categoryId
	 * @param pageNo
	 * @param name
	 * @param code
	 * @param place
	 * @author zhouji
	 * @return
	 */
	@RequestMapping(value="/getCategoryManage/getCategoryByCondition")
	@ResponseBody
	public String getCategoryByCondition(HttpServletRequest request, HttpServletResponse response,
			Long classId,Long categoryId,String pageNo,String name,String code,String place){
		if(classId == null){
			List<ClassInfo> classInfoList = classInfoService.getEffectClass();
			if(classInfoList != null && classInfoList.size()>0){
				classId = classInfoList.get(0).getClassId();
			}
		}
		if(classId!=null){
			request.setAttribute("classId", classId);
			if(categoryId==null){
				List<Categorys> catlist = categoryService.selectCategoryBy(classId);
				if(catlist != null && catlist.size()>0){
					categoryId = catlist.get(0).getId();
				}
			}
			
			//分类的第一个类目
			if(categoryId!=null){
				Page<Map<String,Object>> page = new Page<Map<String,Object>>();
				page.setPageNo(1);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("classId", classId);//不用于查询只用于将些值带到页面使用
				map.put("categoryId", categoryId);
				map.put("name", name);
				map.put("code", code);
				map.put("place", place);
				page.setParams(map);
				//第一个分类 第一个类目及其子类目关联的品种
				List<Map<String,Object>> breedMapList = categoryService.getBreedByCategory(page);
				page.setResults(breedMapList);
				//request.setAttribute("page", page);
				Gson gson = new Gson();
				// 加以下语句：页面中文没有乱码
				response.setCharacterEncoding("utf-8");
				return gson.toJson(page);
				
			}
		}
		return null;
	}
	/**
	 * 
	
	* @Title: getFirstCategoryByClass 
	
	* @Description: 获取指定类目下的第一个类目
	
	* @param @param request
	* @param @param response
	* @param @param classId
	* @param @return    设定文件 
	
	* @return Categorys    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/getFirstCategoryByClass")
	@ResponseBody
	public Categorys getFirstCategoryByClass(HttpServletRequest request, HttpServletResponse response,
			Long classId){
		List<Categorys> catlist = categoryService.selectCategoryBy(classId);
		   if(catlist != null && catlist.size()>0){
			   return catlist.get(0);
		   }
		   return null;
	}
	
	
	/**
	 * 
	
	* @Title: getCategoryTreeByClass 
	
	* @Description: ajax 根据分类ID获取类目树的json串，如果ClassId为空，则返回空字符串 
	
	* @param @param request
	* @param @param response
	* @param @param classId
	* @param @return
	* @param @throws Exception    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/getCategoryTreeByClass",method=RequestMethod.POST)
	@ResponseBody
	public String getCategoryTreeByClass(HttpServletRequest request, HttpServletResponse response,Long classId)
			throws Exception {
	  if(classId == null)
		  return "";
		List<Categorys> catlist = categoryService.selectCategoryBy(classId);
		JsonArray ja = new JsonArray();
		//拼出前台ztree对应的格式
		for (Categorys cat : catlist) {
			JsonObject j = new JsonObject();
			j.addProperty("id", cat.getId());
			j.addProperty("pId", cat.getParentId());
			j.addProperty("name", cat.getCategorysName());
			if(cat.getParentId()==0){
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
	
	@RequestMapping(value="/getCategoryManage/addClassInfo")
	public void addClassInfo(HttpServletRequest request, HttpServletResponse response,String className,String operateStrage) throws IOException{
		Long userId = 0l,id=0l;
		if(StringUtils.isNotBlank(className)){
			Subject subject = SecurityUtils.getSubject();
			String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
			if(!"".equals(userCode)){
				BossUser user = bossUserService.findBossUserByUserCode(userCode);
				userId = user.getUserId().longValue();
			}
			 id = classInfoService.addClassInfo(className,operateStrage,userId);
		}
		response.setContentType("text/html");
		response.getWriter().print(id);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 验证类目名称
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getCategoryManage/validateCategoryName",method=RequestMethod.POST)
	@ResponseBody
	public Boolean validateCategoryName(HttpServletRequest request, HttpServletResponse response,Long classId,Long categoryId)
			throws Exception {
		String catName = request.getParameter("catName");
		boolean boo = false;
		List<Categorys> categoryList = categoryService.getCategoryByClassAndName(classId, catName,categoryId);
		if(categoryList != null && categoryList.size()>0){
			boo = true;
		}
		return boo;
	}
	
	/**
	 * 
	
	* @Title: addCategory 
	
	* @Description: 新增类目
	
	* @param @param request
	* @param @param response
	* @param @param classId 分类ID
	* @param @param parentCatId 父级类目ID
	* @param @param catName 类目名称 
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/addCategory")
	public String addCategory(HttpServletRequest request, HttpServletResponse response,Long classId,Long parentCatId,String catName){
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
		if(!"".equals(userCode)){
			BossUser user = bossUserService.findBossUserByUserCode(userCode);
			userId = user.getUserId().longValue();
		}
		Categorys category = new Categorys();
			category.setCategorysName(catName);
			category.setClassId(classId);
			category.setParentId(parentCatId);
			category.setCreater(userId);
			int num = categoryService.addCategory(category);
			if(num>0)
			{
				return "redirect:getCategoryBy?classId="+classId+"&categoryId="+parentCatId;
			}
		return null;
	}
	
	@RequestMapping(value="/getCategoryManage/addTopCategory")
	@ResponseBody
	public boolean addTopCategory(HttpServletRequest request, HttpServletResponse response,Long classId,Long parentCatId,String catName){
		Subject subject = SecurityUtils.getSubject();
		BossUserVo user = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Categorys category = new Categorys();
			category.setCategorysName(catName);
			category.setClassId(classId);
			category.setParentId(parentCatId);
			category.setCreater(user.getId().longValue());
			int num = categoryService.addCategory(category);
			if(num>0)
			{
				return true;
			}
		return false;
	}
	
	/**
	 * 
	
	* @Title: updateCategory 
	
	* @Description: 修改类目名称 
	
	* @param @param request
	* @param @param response
	* @param @param classId 分类ID
	* @param @param catId 类目ID
	* @param @param catName 修改后的类目名称
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/updateCategory")
	@ResponseBody
	public Boolean updateCategory(HttpServletRequest request, HttpServletResponse response,Long classId,Long catId,String catName){
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
		if(!"".equals(userCode)){
			BossUser user = bossUserService.findBossUserByUserCode(userCode);
			userId = user.getUserId().longValue();
		}
		
			int num = categoryService.updateCategoryName(catId, catName, userId);
			if(num>0)
			{
				return true;
			}
		return false;
	}
	
	/**
	 * 
	
	* @Title: validateCatChildrenOrhasBreed 
	
	* @Description: 验证类目是否有子类目或关联品种
	
	* @param @param request
	* @param @param response
	* @param @param catId
	* @param @return
	* @param @throws Exception    设定文件 
	
	* @return Boolean    如果存在，则返回false；如果都无，则返回true 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/validateCatChildrenOrhasBreed",method=RequestMethod.POST)
	@ResponseBody
	public Boolean validateCatChildrenOrhasBreed(HttpServletRequest request, HttpServletResponse response,Long catId,Long classId)
			throws Exception {
		boolean b = true;
		//是否有子节点
		List<Categorys> childCats = categoryService.getChildNode(catId,classId);
		if(childCats != null && childCats.size()>0)
			b = false;
		if(b){
			//如果无子节点，是否有关联品种
			List<Breed> breedList = categoryService.selectBreedByCat(catId);
			if(breedList != null && breedList.size()>0)
				b=false;
		}
		return b;
	}
	
	/**
	 * 
	
	* @Title: deleteCategory 
	
	* @Description: 删除类目
	
	* @param @param request
	* @param @param response
	* @param @param classId
	* @param @param catId
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/deleteCategory")
	@ResponseBody
	public Boolean deleteCategory(HttpServletRequest request, HttpServletResponse response,Long classId,Long catId){
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
		if(!"".equals(userCode)){
			BossUser user = bossUserService.findBossUserByUserCode(userCode);
			userId = user.getUserId().longValue();
		}
		
			int num = categoryService.deleteCategory(catId, userId,classId);
			if(num>0)
			{
				return true;
			}
		return false;
	}
	
	/**
	 * 
	
	* @Title: deleteCategory 
	
	* @Description: 取消品种与类目的关联
	
	* @param @param request
	* @param @param response
	* @param @param classId
	* @param @param catId
	* @param @param breedCategoryId
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	
	@RequestMapping(value="/getCategoryManage/deleteBreedByCategory")
	@ResponseBody
	public Boolean deleteCategory(HttpServletRequest request, HttpServletResponse response,Long breedCategoryId){
		boolean boo = false;
			int num = categoryService.deleteCategoryAndBreedRela(breedCategoryId);
			if(num>0)
			{
				boo = true;
			}
		return boo;
	}
	
	@RequestMapping(value="/getCategoryManage/getNotAssociateBreedBy")
	@ResponseBody
	public String getNotAssociateBreedBy(HttpServletRequest request, HttpServletResponse response,Long classId,Long catId,String associatePageNo,String name){
			Page page = new Page();
				page.setPageNo(StringUtils.isNotBlank(associatePageNo)?Integer.valueOf(associatePageNo):1);
			Map<String,Object> map = new HashMap<String,Object>();
				map.put("categoryId", catId);
				map.put("name", name);
				page.setParams(map);
			List<Breed> notAssociateBreedList = categoryService.getNotAssociateBreedBy(page);
				page.setResults(notAssociateBreedList);
			//request.setAttribute("page", page);
			//request.setAttribute("classId", classId);
			Gson gson = new Gson();
			// 加以下语句：页面中文没有乱码
			response.setCharacterEncoding("utf-8");
			return gson.toJson(page);
	}
	
	@RequestMapping(value="/getCategoryManage/associateCategoryAndBreed")
	@ResponseBody
	public Boolean associateCategoryAndBreed(HttpServletRequest request, HttpServletResponse response,Long classId,Long catId){
		String[] breedIds = request.getParameterValues("breedId");
		 if(breedIds!=null && breedIds.length>0){
				 categoryService.associateCategoryAndBreed(catId, breedIds);
				  return true;
		 }
		return false;
	}
	
	@RequestMapping(value="/getCategoryManage/updateCategoryOrder")
	@ResponseBody
	public String updateCategoryOrder(HttpServletRequest request, HttpServletResponse response,Long id,Long targetId){
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
		if(!"".equals(userCode)){
			BossUser user = bossUserService.findBossUserByUserCode(userCode);
			userId = user.getUserId().longValue();
		}
		 int num = categoryService.updateCategoryOrder(id, userId, targetId);
		 if(num>0)
			 return "success";
		 return "fail";
	}
	
	/**
	 * 
	
	* @Title: validateCatChildrenOrhasBreed 
	
	* @Description: 验证分类下是否有类目
	
	* @param @param request
	* @param @param response
	* @param @param catId
	* @param @return
	* @param @throws Exception    设定文件 
	
	* @return Boolean    如果存在，则返回false；如果都无，则返回true 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/validateClasshasCat",method=RequestMethod.POST)
	@ResponseBody
	public Boolean validateClasshasCat(HttpServletRequest request, HttpServletResponse response,Long classId)
			throws Exception {
		boolean b = true;
		//是否有节点
		List<Categorys> cats = classInfoService.getCategoryByClass(classId);
		if(cats != null && cats.size()>0)
			b = false;
		
		return b;
	}
	
	
	@RequestMapping(value="/getCategoryManage/deleteClass")
	@ResponseBody
	public Boolean deleteClass(HttpServletRequest request, HttpServletResponse response,Long classId){
		Subject subject = SecurityUtils.getSubject();
		BossUserVo user = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int num = classInfoService.deleteClassInfo(classId, user.getId().longValue());
		if(num>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	
	* @Title: validateCatChildrenOrhasBreed 
	
	* @Description: 验证分类名是否存在 
	
	* @param @param request
	* @param @param response
	* @param @param catId
	* @param @return
	* @param @throws Exception    设定文件 
	
	* @return Boolean    如果存在，则返回false；如果都无，则返回true 
	
	* @throws
	 */
	@RequestMapping(value="/getCategoryManage/validateClassbyName",method=RequestMethod.POST)
	@ResponseBody
	public Boolean validateClasshasCat(HttpServletRequest request, HttpServletResponse response,Long classId,String className)
			throws Exception {
		boolean b = true;
		//是否有节点
		List<ClassInfo> classList = classInfoService.getClassByName(className, classId);
		if(classList != null && classList.size()>0)
			b = false;
		
		return b;
	}
	
	
	@RequestMapping(value="/getCategoryManage/updateClassName",method=RequestMethod.POST)
	@ResponseBody
	public Boolean updateClassName(HttpServletRequest request, HttpServletResponse response,Long classId,String className,String classStrage)
			throws Exception {
		
		boolean b = true;
		Subject subject = SecurityUtils.getSubject();
		BossUserVo user = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		//是否有节点
		int num = classInfoService.updateClassInfo(className, classId, user.getId().longValue(),classStrage);
		if(num<=0)
			b = false;
		
		return b;
	}
	
	@RequestMapping(value="/getCategoryManage/getClassStrageBy",method=RequestMethod.POST)
	@ResponseBody
	public ClassInfo getClassStrageBy(HttpServletRequest request, HttpServletResponse response,Long classId)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		
		ClassInfo classInfo = classInfoService.selectByPrimaryKey(classId);
		return classInfo;
		
	}
	
}
