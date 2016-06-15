package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.enums.ListingSearchEngineEnum;
import com.jointown.zy.common.enums.SeoHeaderEnum;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.BusiListingSearchService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.trace.Trace;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
* 项目名称：jzt-business  
* 类名称：SortListController  
* 类描述：  对应于sortList.ftl页面进行操作
* 创建人：  chengchang
* 创建时间：2015-01-28  
* 修改人：  宋威
* 修改时间：  2015-04-29 09:18
* 修改备注：  主要添加代码注释，以及修改品种搜索导航逻辑
* @version v1.0  
 */
@Controller
@RequestMapping("/search")
public class SearchController extends UserBaseController{
	@Autowired
	private SortListService sortListService;
	
	@Autowired 
	private BusiListingSearchService solrService;
	@Autowired 
	private BusiGoodsDetailsService busiGoodsDetailsService;
	@Autowired 
	private CategorysService categorysService;
	@Autowired 
	private IndexService indexService;
	
	
	/**
	 * @Description: 根据类目大类导航进入搜索二级界面，这里主要是根据类目查询类目下的品种
	 * @Author: 宋威
	 * @Date: 2015年4月29日
	 * @param modelMap
	 * @param response
	 * @param id ：类目ID
	 * @param value ：前台展示的类目中文名称
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@RequestMapping(value="/category/{id}" ,method={RequestMethod.POST, RequestMethod.GET})
	public String searchByCategory(ModelMap modelMap,@PathVariable(value="id") String id,@RequestParam(value="value") String value,HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		dto.setSearchType(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_CATEGORY.getValue());
		dto.setCategoryName(StringUtils.isNotEmpty(value)?value:"");
		dto.setSearchId(StringUtils.isNotEmpty(id)?id:"");
		dto.setMode(ListingSearchEngineEnum.SEARCH_MODE_LINK.getValue());
		searchByLink(request, dto, modelMap);
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
			modelMap.put("hearder_type", SeoHeaderEnum.PRODUCT_TYPE_FIRST_LEVEL.getValue());
			modelMap.put("search_keywords", value);
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
		return "searchedListing";
	}
	
	/**
	 * @Description: 根据品种ID进入品种三级特性搜索界面，这里主要是根据品种ID查询该品种的特性，及所在仓库等
	 * @Author: 宋威
	 * @Date: 2015年4月29日
	 * @param modelMap
	 * @param cid  品种所属大类
	 * @param id ：品种ID
	 * @param value ：前台展示的品种中文名称
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@RequestMapping(value="/category/{cid}/breed/{id}" ,method={RequestMethod.POST, RequestMethod.GET})
	public String searchByBreed(ModelMap modelMap,@PathVariable(value="cid") Long cid,@PathVariable(value="id") String id,@RequestParam(value="value") String value,HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		dto.setSearchType(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_BREED.getValue());
		Categorys category = categorysService.getCategorysById(cid);
		dto.setCategoryName((category!=null)?category.getCategorysName():"");
		dto.setBreedName(StringUtils.isNotEmpty(value)?value:"");
		dto.setBreedId(StringUtils.isNotEmpty(id)?id:"");
		dto.setSearchId(StringUtils.isNotEmpty(id)?id:"");
		dto.setMode(ListingSearchEngineEnum.SEARCH_MODE_LINK.getValue());
		modelMap.addAttribute("cid", cid);
		searchByLink(request, dto, modelMap);
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
			modelMap.put("hearder_type", SeoHeaderEnum.PRODUCT_TYPE_SECOND_LEVEL.getValue());
			modelMap.put("search_keywords", value);
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
		return "searchedListing";
	}
	
	/**
	 * @Description: 根据搜索关键字去solr服务器里分页查询搜索结果
	 * @Author: 宋威
	 * @Date: 2015年4月29日
	 * @param modelMap
	 * @param keyWords 搜索关键字
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@RequestMapping(value={"","/keyWords"},method={RequestMethod.POST, RequestMethod.GET})
	public String searchByKeyWords(ModelMap modelMap,@RequestParam(required=false,value="keyWords") String keyWords,HttpServletRequest request){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		modelMap.put("sortList", sortList);
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		String pageNo = request.getParameter("pageNo");
		setSearchParams(dto, request);
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		if(pageNo!=null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		//记录印记
		if(isUserLogin()){
			new Trace(getUserInfo().getUserId().toString())
			.setForSearchKeyword(keyWords).syslogForBusiness();
		}
		List<BusiListingSearchVo> list = solrService.searchByKeyWords(dto,page);
		page.setResults(list);
		modelMap.put("page", page);
		//将keywords放在页面上
		setFormData(dto, modelMap,request);
		String tunnage = indexService.getWarrantsTunnage();
		modelMap.put("tunnage", tunnage);
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("categorylist", categorylist);
		
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
			modelMap.put("hearder_type", SeoHeaderEnum.SEARCH_LIST_HEADER.getValue());
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
			
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);	
		return "searchedListing";
	}
	
	/**
	 * @Description: 根据用户输入的字符串post联想相关关键字列表
	 * @Author: 宋威
	 * @Date: 2015年4月29日
	 * @param request
	 * @return json
	 */
	@RequestMapping(value="/suggest/keyWords",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> suggestByKeyWords(HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		setSearchParams(dto, request);
		Map<String,Object> map = new HashMap<String, Object>();
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		List<BusiListingSearchVo> list = solrService.suggestByKeyWords(dto,page);
		page.setResults(list);
		map.put("page", page);
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		map.put("categorylist", categorylist);
		return map;
	}
	
	
	/**
	 * @Description: 搜索界面右侧的热门药材推荐 查询热门药材 5条记录
	 * 查交易数量最多的前五个，如果商品没有一个交易，默认按挂牌时间倒序；
	 * 如果交易的商品不够五个，先查出所有的交易商品，剩余个数由挂牌时间倒序的商品的靠前商品填充
	 * @Author: 宋威
	 * @Date: 2015年4月29日
	 * @return json
	 */
	@RequestMapping(value="/selectHotBusiListing")
	public @ResponseBody List<HashMap<String,Object>> selectHotBusiListing(){
		return busiGoodsDetailsService.selectHotBusiListing();
	}
	
	
	private void setSearchParams(BusiListingSearchEngineDto dto,HttpServletRequest request){
		//关键字(搜索为关键字的时候)
		dto.setKeyWords(request.getParameter("keyWords"));
		//关键字查询模式
		dto.setMode(StringUtils.isNotEmpty(request.getParameter("mode"))?request.getParameter("mode"):dto.getMode());
		//链接
		String searchType=request.getParameter("searchType");
		if(StringUtils.isNotEmpty(searchType)){
			//单独点导航中的品种进行搜索
			if(searchType.equals(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_CATEGORY.toString())){
				dto.setCategoryName(request.getParameter("categoryName"));
			}
		} else{
			searchType = dto.getSearchType();
		}
		dto.setWarehouseName(request.getParameter("warehouseName"));
		dto.setGrade(request.getParameter("grade"));
		dto.setOrigin(request.getParameter("origin"));
		dto.setBreedCname(request.getParameter("breedCname"));
		//过滤
		String priceB= request.getParameter("priceB");
		String priceE= request.getParameter("priceE");
		List <String>priceList=new ArrayList<String>();
		if(StringUtils.isNotEmpty(priceB)){
			priceList.add(priceB);
		}
		
		if(StringUtils.isNotEmpty(priceE)){
			priceList.add(priceE);
		}
        String[] priceRange = (String[])priceList.toArray(new String[priceList.size()]);
        
		if(!ArrayUtils.isEmpty(priceRange)){
			dto.setPriceRange(priceRange);
		}
		String dateB= request.getParameter("dateB");
		String dateE= request.getParameter("dateE");
		List <String>dateList=new ArrayList<String>();
		if(StringUtils.isNotEmpty(dateB)){
			dateList.add(dateB);
		}
		if(StringUtils.isNotEmpty(dateE)){
			dateList.add(dateE);
		}
		String[] examineTimeRange = (String[])dateList.toArray(new String[dateList.size()]);
		if(!ArrayUtils.isEmpty(examineTimeRange)){
			dto.setExamineTimeRange(examineTimeRange);
		}
		//产地
		dto.setOriginText(request.getParameter("originText"));
		//排序
		dto.setSortPrice(request.getParameter("sortPrice"));
		dto.setSortListingSurplus(request.getParameter("sortListingSurplus"));
		dto.setSortExamineTime(request.getParameter("sortExamineTime"));
		//搜索的类型和返回的类型
		dto.setSearchType(searchType);
	}

	//将需要的数据传递到隐藏form表单，为了分页进行操作
	private void setFormData(BusiListingSearchEngineDto dto ,ModelMap modelMap,HttpServletRequest request){
		modelMap.put("categoryName", dto.getCategoryName());
		modelMap.put("breedName", dto.getBreedName());
		modelMap.put("grade", dto.getGrade());
		modelMap.put("warehouseName", dto.getWarehouseName());
		modelMap.put("priceRange", dto.getPriceRange());
		modelMap.put("examineTimeRange", dto.getExamineTimeRange());
		modelMap.put("origin", dto.getOrigin());
		modelMap.put("sortListingSurplus", dto.getSortListingSurplus());
		modelMap.put("sortPrice", dto.getSortPrice());
		modelMap.put("sortExamineTime", dto.getSortExamineTime());
		modelMap.put("keyWords", "*".equals(dto.getKeyWords())?"":dto.getKeyWords());
		modelMap.put("searchType", dto.getSearchType());
		//添加的2个参数
		//(1)用来存放breedId或者是categoryId
		modelMap.put("searchId", dto.getSearchId());
		//(2)用来存放breed上级类目的名称
		modelMap.put("categorys_name", request.getParameter("categorys_name"));
		//将价格和时间和产地字符串添加到modelMap中
		modelMap.put("priceB", request.getParameter("priceB"));
		modelMap.put("priceE", request.getParameter("priceE"));
		modelMap.put("dateB", request.getParameter("dateB"));
		modelMap.put("dateE", request.getParameter("dateE"));
		modelMap.put("originText", dto.getOriginText());
		//如果上次是排序，保存上次按照排序的结构
		modelMap.put("lastSort", request.getParameter("lastSort"));
		//上一次的值
		modelMap.put("value", request.getParameter("value"));
	}
	
	//根据搜索的类型searchType，是breed搜索还是category搜索，返回不同的搜索条件。比如品种、所在仓库、等级规格
	private Map<String,Object> getSearchData(String searchType,String Id,List <Map<Object,Object>> list,List <String> breedIds){
		//搜索的类型是breed
		Map <String ,Object> resultMap = new HashMap<String, Object>();
		if(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_BREED.getValue().equals(searchType)){
			if(!StringUtils.isEmpty(Id)){
				resultMap=solrService.getBreedSearchQuery(Id);
			}
		}else if(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_CATEGORY.getValue().equals(searchType)){
			//搜索的类型是category
			if(StringUtils.isNotEmpty(Id)){
				//按A-F...封装类目下的品种，并将符合条件的品种ID放到集合breedIds。
				resultMap= solrService.getBreedsByCategoryId(list,Id,breedIds);
				//List <Map<Object,Object>> listBreed= sortListService.selectBreedByCategorys(Id);
				//2.查询出category的name
				//Categorys categorys=categorysService.selectByPrimaryKey(Long.parseLong(Id));
				//resultMap.put("categorys", categorys);
			}
		}
		return resultMap;
	}
	
	/**
	 * @Description: 根据品种、类目搜索条件查询结果
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param request
	 * @param dto
	 * @param modelMap
	 * @return
	 */
	private String searchByLink(HttpServletRequest request,BusiListingSearchEngineDto dto,ModelMap modelMap){
		//首页导航内容封装。
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		modelMap.put("sortList", sortList);
		
		String pageNo = request.getParameter("pageNo");
		//搜索条件拼接
		setSearchParams(dto, request);
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		//判断是点击了品种还是类目,点击不同的，然后显示不同的页面
		String searchType=dto.getSearchType();
		String searchId = dto.getSearchId();
		List<String> breedIds = new ArrayList<String>(); //用来装该目录下所有符合条件的品种id
		//界面搜索条件区域内容填充 如品种、所在仓库、等级规格
		if(StringUtils.isNotEmpty(searchType) && StringUtils.isNotEmpty(searchId)){
			Map <String,Object> resultMap=getSearchData(searchType,searchId,sortList,breedIds);
			//当获得的resultMap不为空，将两个map进行合并
			if(MapUtils.isNotEmpty(resultMap)){
				modelMap.putAll(resultMap);
			}
		}
		//获取搜索后的结果集
		List<BusiListingSearchVo> list =new ArrayList<BusiListingSearchVo>();
		if(dto !=null && StringUtils.isNotEmpty(dto.getSearchType())){
			if(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_CATEGORY.getValue().equals(searchType)){
				//dto.setBreedId(breedId);
				StringBuffer buffer = new StringBuffer("^");
				int i=0;
				for(String breedId:breedIds){
					buffer.append(breedId);
					if(i<breedIds.size()-1)
						buffer.append("^");
					i++;
				}
				dto.setBreedId(buffer.toString());
			}
			list = solrService.searchBreedsBySolr(dto,page);
		}
		page.setResults(list);
		modelMap.put("page", page);
		//页面隐藏form绑值
		setFormData(dto, modelMap,request);
		
		//仓库总重量统计
		String tunnage = indexService.getWarrantsTunnage();
		modelMap.put("tunnage", tunnage);
		//添加顶部的热门搜索关键字
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("categorylist", categorylist);
		return "searchedListing";
	}
}