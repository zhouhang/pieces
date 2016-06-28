package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jointown.zy.common.dto.WmsBreedDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedAttr;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.service.BreedAttrService;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.service.DictInfoService;
/**
 * 
 * @author chengchang
 * @description 用来管理类目
 *@time 2014 12-23
 */
@Controller(value="breedController")
public class BreedController {
	@Autowired BreedService breedService;
	@Autowired CategorysService categorysService;
	@Autowired DictInfoService dictInfoService;
	@Autowired BossUserService bossUserService;
	@Autowired BreedAttrService breedAttrService;
	Logger logger = LoggerFactory.getLogger(BreedController.class);
	@RequestMapping(value="/getBreed" ,method=RequestMethod.GET)
	public ModelAndView getBreedInfo(){
		Page page = new Page();
		page.setPageSize(10);
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.setViewName("/public/breed");
		return mav;
	}
	/**
	 * 模糊查询品种
	 * @return
	 */
	@RequestMapping(value="/getBreed/queryBreed")
	public String  queryBreed(HttpServletRequest request,HttpServletResponse response ,ModelMap modelMap){
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String place = request.getParameter("place");
		String pageNo = request.getParameter("pageNo");
		
		Map <String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("code", code);
		params.put("place", place);
		Page <Map<String,Object>>page = new Page <Map<String,Object>>();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		page.setPageSize(10);
		page.setParams(params);
		List  breeds= breedService.selectBreedBy(page);
		if (breeds!=null&&breeds.size()>0) {
			page.setResults(breeds);
		}
		modelMap.put("page", page);
		return "/public/breed";
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @description 通过前台的ajax获取该breed的ID,然后删除breed中的关系和品种目录关系表中的关系
	 * @return
	 */
	@RequestMapping(value="/getBreed/deleteBreedById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> deleteBreedById(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		int result=0;
		if(id!=null && !"".equals(id)){
			Breed breed = new Breed();
			breed.setBreedId(Long.parseLong(id));
			result=breedService.deleteBreed(breed);
		}
		Map <String, Boolean> resultMap  = new HashMap<String, Boolean>();
		//表示删除breed成功了
		if(result>0){
			resultMap.put("flag", true);
			//add by fanyuna删除品种为逻辑删除，因此需要调用修改品种同步接口，删除品种成功后往rabbitmq生产者put消息，以便调用WMS修改品种同步接口
			try{
				Breed breed = breedService.selectBreedById(Long.valueOf(id));
				breedService.WmsBreedputMsg(breed, null, ApiFlagEnums.BREED_UPDATE);
				}catch(Exception e){
					logger.error("删除品种成功后往rabbitmq生产者put消息时异常："+e.getMessage(),e);
				}
		}else{
			resultMap.put("flag", false);
		}
		return resultMap;
	}
	/**
	 * @description 
	 * 1.查询出字典表里面的数据，通过异步显示在前台页面
	 * 2.从数据库中查询出树的结构显示在前台页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getBreed/getDictInfoAndTreeInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map getDictInfoAndTreeInfo(HttpServletRequest request,HttpServletResponse response){
		String dictType=request.getParameter("DICT_TYPE");
		List listLimtDict=dictInfoService.getLimitDictList(dictType);
		List <Object> listTree=categorysService.getAllTree();
		Map <String, List> reusltMap = new HashMap<String, List>();
		reusltMap.put("listLimtDict",listLimtDict );
		reusltMap.put("listTree",listTree );
		return reusltMap;
	}
	
	
	@RequestMapping(value="/getBreed/addBreed",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertBreed(HttpServletRequest request,HttpServletResponse response){
		String errorMessage="";
		//1从前台获取数据
		//alter by biran 20150702 为编码和名称加trim
		String breedName= request.getParameter("breedName").trim();
		String breedCode= request.getParameter("breedCode").trim();
		String selectDict= request.getParameter("selectDict");
		String BREED_CNAME= request.getParameter("BREED_CNAME");
		String BREED_STANDARD_LEVEL= request.getParameter("BREED_STANDARD_LEVEL");
		String BREED_PLACE= request.getParameter("BREED_PLACE");
		String noteIds= request.getParameter("noteIds");
		if(
				StringUtils.isEmpty(breedName)||
				StringUtils.isEmpty(breedCode)||
				StringUtils.isEmpty(selectDict)||
				StringUtils.isEmpty(BREED_CNAME)||
				StringUtils.isEmpty(BREED_STANDARD_LEVEL)||
				StringUtils.isEmpty(BREED_PLACE)
				){
			errorMessage="部分数据不为空，请检查数据";
		}
		
		//封装数据
		List<BreedAttr> breedAttrs = new ArrayList<BreedAttr>();
		List<Categorys> listCategories = new ArrayList<Categorys>();
		String [] BREED_CNAMEs=BREED_CNAME.split(",");
		String [] BREED_STANDARD_LEVELs=BREED_STANDARD_LEVEL.split(",");
		String [] BREED_PLACEs=BREED_PLACE.split(",");
		//String [] noteId=noteIds.split(",");
		Breed breed = new Breed();
		breed.setBreedName(breedName);
		breed.setBreedCode(breedCode);
		breed.setBreedCountUnit(selectDict);
		breed.setBreedCname(BREED_CNAME);
		breed.setBreedPlace(BREED_PLACE);
		breed.setBreedStandardLevel(BREED_STANDARD_LEVEL);
		breed.setCreateTime(new Date());
		breed.setStatus(Short.parseShort("1"));
		//获取用户的userID
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
		if(!"".equals(userCode)){
			BossUser user = bossUserService.findBossUserByUserCode(userCode);
			userId = user.getUserId().longValue();
		}
		breed.setCreater(userId);
		/*********************封装breedAttrs（开始）*******************************/
		//将BREED_CNAMEs拆分装入到breedAttrs
		for(int i=0;i<BREED_CNAMEs.length;i++){
			BreedAttr breedAttr = new BreedAttr();
			breedAttr.setAttrName("BREED_CNAME");
			breedAttr.setAttrValue(BREED_CNAMEs[i]);
			breedAttrs.add(breedAttr);
		}
		//将BREED_CNAMEs拆分装入到BREED_STANDARD_LEVELs
		for(int i=0;i<BREED_STANDARD_LEVELs.length;i++){
			BreedAttr breedAttr = new BreedAttr();
			breedAttr.setAttrName("BREED_STANDARD_LEVEL");
			breedAttr.setAttrValue(BREED_STANDARD_LEVELs[i]);
			breedAttrs.add(breedAttr);
		}
		//将BREED_CNAMEs拆分装入到BREED_PLACEs
		for(int i=0;i<BREED_PLACEs.length;i++){
			BreedAttr breedAttr = new BreedAttr();
			breedAttr.setAttrName("BREED_PLACE");
			breedAttr.setAttrValue(BREED_PLACEs[i]);
			breedAttrs.add(breedAttr);
		}
		/*********************封装breedAttrs（结束）*******************************/
		/*********************封装categorys（开始）********************************/
		if(!StringUtils.isEmpty(noteIds)){
			String [] noteId=noteIds.split(",");
			for(int i=0;i<noteId.length;i++){
				Categorys categorys = new Categorys();
				categorys.setId(Long.parseLong(noteId[i]));
				listCategories.add(categorys);
			}
		}
		/*for(int i=0;i<noteId.length;i++){
			Categorys categorys = new Categorys();
			categorys.setId(Long.parseLong(noteId[i]));
			listCategories.add(categorys);
		}*/
		/*********************封装categorys（结束）********************************/
		int result =breedService.addBreeds(breed, listCategories, breedAttrs);
		//result>0表示添加成功了
		Map <String, Object> resultMap  = new HashMap<String, Object>();
		if(result>0){
			resultMap.put("flag", true);
			//add by fanyuna添加品种成功后往rabbitmq生产者put消息，以便调用WMS添加品种同步接口
			try{
			breedService.WmsBreedputMsg(breed, noteIds, ApiFlagEnums.BREED_ADD);
			}catch(Exception e){
				logger.error("添加品种成功后往rabbitmq生产者put消息时异常："+e.getMessage(),e);
			}
			
		}else{
			resultMap.put("flag", false);
		}
		resultMap.put("errorMessage", errorMessage);
		return resultMap;
	}
	@RequestMapping(value="/getBreed/modifyBreed",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBreed(HttpServletRequest request,HttpServletResponse response){
			//1从前台获取数据
			String breedId= request.getParameter("breedId");
			//alter by biran 20150702 为编码和名称加trim
			String breedName= request.getParameter("breedName").trim();
			String breedCode= request.getParameter("breedCode").trim();
			String selectDict= request.getParameter("selectDict");
			String BREED_CNAME= request.getParameter("BREED_CNAME");
			String BREED_STANDARD_LEVEL= request.getParameter("BREED_STANDARD_LEVEL");
			String BREED_PLACE= request.getParameter("BREED_PLACE");
			String noteIds= request.getParameter("noteIds");
			String errorMessage="";
			if(
					StringUtils.isEmpty(breedName)||
					StringUtils.isEmpty(breedCode)||
					StringUtils.isEmpty(selectDict)||
					StringUtils.isEmpty(BREED_CNAME)||
					StringUtils.isEmpty(BREED_STANDARD_LEVEL)||
					StringUtils.isEmpty(BREED_PLACE)
					){
				errorMessage="操作失败，数据部完整";
			}
			//封装数据
			List<BreedAttr> breedAttrs = new ArrayList<BreedAttr>();
			List<Categorys> listCategories = new ArrayList<Categorys>();
			String [] BREED_CNAMEs=BREED_CNAME.split(",");
			String [] BREED_STANDARD_LEVELs=BREED_STANDARD_LEVEL.split(",");
			String [] BREED_PLACEs=BREED_PLACE.split(",");
			
			Breed breed = new Breed();
			breed.setBreedId(Long.parseLong(breedId));
			breed.setBreedName(breedName);
			breed.setBreedCode(breedCode);
			breed.setBreedCountUnit(selectDict);
			breed.setBreedCname(BREED_CNAME);
			breed.setBreedPlace(BREED_PLACE);
			breed.setBreedStandardLevel(BREED_STANDARD_LEVEL);
			breed.setUpdateTime(new Date());
			breed.setStatus(Short.parseShort("1"));
			//获取用户的userID
			Long userId=0l;
			Subject subject = SecurityUtils.getSubject();
			String userCode = subject.getPrincipal()!=null?subject.getPrincipal().toString():"";//用户名 user_code
			if(!"".equals(userCode)){
				BossUser user = bossUserService.findBossUserByUserCode(userCode);
				userId = user.getUserId().longValue();
			}
			breed.setUpdater(userId);
			/*********************封装breedAttrs（开始）*******************************/
			//将BREED_CNAMEs拆分装入到breedAttrs
			for(int i=0;i<BREED_CNAMEs.length;i++){
				BreedAttr breedAttr = new BreedAttr();
				breedAttr.setAttrName("BREED_CNAME");
				breedAttr.setAttrValue(BREED_CNAMEs[i]);
				breedAttrs.add(breedAttr);
			}
			//将BREED_CNAMEs拆分装入到BREED_STANDARD_LEVELs
			for(int i=0;i<BREED_STANDARD_LEVELs.length;i++){
				BreedAttr breedAttr = new BreedAttr();
				breedAttr.setAttrName("BREED_STANDARD_LEVEL");
				breedAttr.setAttrValue(BREED_STANDARD_LEVELs[i]);
				breedAttrs.add(breedAttr);
			}
			//将BREED_CNAMEs拆分装入到BREED_PLACEs
			for(int i=0;i<BREED_PLACEs.length;i++){
				BreedAttr breedAttr = new BreedAttr();
				breedAttr.setAttrName("BREED_PLACE");
				breedAttr.setAttrValue(BREED_PLACEs[i]);
				breedAttrs.add(breedAttr);
			}
			/*********************封装breedAttrs（结束）*******************************/
			/*********************封装categorys（开始）********************************/
			if(!StringUtils.isEmpty(noteIds)){
				String [] noteId=noteIds.split(",");
				for(int i=0;i<noteId.length;i++){
					Categorys categorys = new Categorys();
					categorys.setId(Long.parseLong(noteId[i]));
					listCategories.add(categorys);
				}
			}
			
			/*********************封装categorys（结束）********************************/
			//result>0表示添加成功了
			Map <String, Object> resultMap  = new HashMap<String, Object>();
			int result =breedService.updateBreeds(breed, listCategories, breedAttrs);
			if(result>0){
				resultMap.put("flag", true);
				//add by fanyuna修改品种成功后往rabbitmq生产者put消息，以便调用WMS修改品种同步接口
				try{
					breedService.WmsBreedputMsg(breed, noteIds, ApiFlagEnums.BREED_UPDATE);
					}catch(Exception e){
						logger.error("修改品种成功后往rabbitmq生产者put消息时异常："+e.getMessage(),e);
					}
			}else{
				resultMap.put("flag", false);
			}
			resultMap.put("errorMessage", errorMessage);
			return resultMap;
	}
	/**
	 * 点击breed的详情，弹出详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getBreed/queryBreedInfoById",method=RequestMethod.POST)
	@ResponseBody
	public Map  <Object ,Object> queryBreedInfoById(HttpServletRequest request ,HttpServletResponse response){
		String breedId = request.getParameter("breedId");
		//1查询出breed的数据
		Breed breed =breedService.selectBreedById(Long.parseLong(breedId));
		//1.1品种名称
		String breedName=breed.getBreedName();
		//1.2品种编码
		String breedCode=breed.getBreedCode();
		//1.3常见计量单位
		String breedCountUnit=breed.getBreedCountUnit();
		//1.4常见别名
		BreedAttr breedAttr = new BreedAttr();
		breedAttr.setBreedId(Long.parseLong(breedId));
		breedAttr.setAttrName("BREED_CNAME");
		List <BreedAttr> listBreedCname= breedAttrService.queryBreedAttr(breedAttr);
		//1.5常见等级规格
		breedAttr.setAttrName("BREED_STANDARD_LEVEL");
		List <BreedAttr> listBreedLevel= breedAttrService.queryBreedAttr(breedAttr);
		//1.6常见产地
		breedAttr.setAttrName("BREED_PLACE");
		List <BreedAttr> listBreedPlace= breedAttrService.queryBreedAttr(breedAttr);
		Map <Object ,Object> resultMap = new HashMap <Object ,Object>();
		resultMap.put("breedName", breedName);
		resultMap.put("breedId", breedId);
		resultMap.put("breedCode", breedCode);
		resultMap.put("breedCountUnit", breedCountUnit);
		resultMap.put("listBreedCname", listBreedCname);
		resultMap.put("listBreedLevel", listBreedLevel);
		resultMap.put("listBreedPlace", listBreedPlace);
		/*List <Object> listTree=categorysService.getAllTree();
		//1.7查询出所有的categorys
		resultMap.put("listTree", listTree);*/
		//1.8查询出breed和categorys的关系
		/*List <BreedCategory>listBreedCategorys= breedService.queryBreedCategoryByBreedId(Long.parseLong(breedId));
		StringBuffer listBreedBuffer = new StringBuffer();
		if(listBreedCategorys!=null&&listBreedCategorys.size()>0){
			for(BreedCategory breedCategory :listBreedCategorys){
				listBreedBuffer.append(breedCategory.getBreedId().toString()).append(",");
			}
		}
		//将listBreedBuffer转化为String
		String categoryIds = listBreedBuffer.toString();
		//去掉最后的一个","
		if(categoryIds.endsWith(",")&&categoryIds.length()>1){
			//表示最后是以","结尾，并且长度大于1
			categoryIds= categoryIds.substring(0, categoryIds.length()-1);
		}
		resultMap.put("categoryIds", categoryIds);*/
		//1.7查询出所有的树，并且勾选出要求的，这棵树的复选框不能再进行操作
		List <Object> listTree =categorysService.getAllTreeAndChecked(breed, true);
		resultMap.put("listTree", listTree);
		return resultMap;
	}
	
	@RequestMapping(value="/getBreed/modifyBreedQueryById",method=RequestMethod.POST)
	@ResponseBody
	public Map  <Object ,Object> modifyBreedQueryById(HttpServletRequest request ,HttpServletResponse response){
		String breedId = request.getParameter("breedId");
		//1查询出breed的数据
		Breed breed =breedService.selectBreedById(Long.parseLong(breedId));
		//2从queryBreedInfoById中获取数据
		Map <Object ,Object> resultMap=this.queryBreedInfoById(request,response);
		//3移除不可以操作的树
		resultMap.remove("listTree");
		//4添加可以操作的树
		List <Object> listTree =categorysService.getAllTreeAndChecked(breed, false);
		resultMap.put("listTree", listTree);
		//5添加查询字典表数据
		String dictType="weight_unit";
		List listLimtDict=dictInfoService.getLimitDictList(dictType);
		resultMap.put("listLimtDict", listLimtDict);
		return resultMap;
	}
	
	/**
	 * 验证品种编码是否存在
	 * @author fanyuna
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getBreed/breedCodeIsHaved")
	@ResponseBody
	public String breedCodeIsHaved(HttpServletRequest request,HttpServletResponse response){
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		response.setCharacterEncoding("UTF-8");
		//alter by biran 20150702 为编码和名称加trim
		String breedCode = request.getParameter("param").trim();
		if (null != breedService.selectBreedByCode(breedCode)) {
			return "n";
		};
		return "y";
	}
	
	/**
	 * 验证品种名称是否存在
	 * @author fanyuna
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getBreed/breedNameIsHaved")
	@ResponseBody
	public String breedNameIsHaved(HttpServletRequest request,HttpServletResponse response){
		//Validform ajaxurl方法带入两个参数:param,name param是input的value值 name是input的name值
		response.setCharacterEncoding("UTF-8");
		String breedName = request.getParameter("param").trim();
		return breedService.selectBreedByNameIgnoreStatus(breedName)!=null?"n":"y";
	}
	
}
