package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.enums.ListingSearchEngineEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.WxSearchService;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.service.WxUserService;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;

/**
* 项目名称：jzt-weixinbb  
* 类名称：WxSearchController  
* 类描述：  小珍现货,无登录状态下的挂牌搜索、排序，查看货主信息等。
* 创建人：  宋威
* 创建时间：2015-07-15  
* 修改人：  
* 修改时间：  
* 修改备注：  
* @version v1.0  
 */
@Controller
@RequestMapping("/search")
public class WxSearchController extends WxUserBaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(WxSearchController.class);
	
	@Autowired
	private WxSearchService wxSearchService;
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private WxSupplyService wxSupplyService;
	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	
	@RequestMapping(value="/getWarehouseName",method=RequestMethod.GET)
	@ResponseBody
	public List<BusiWareHouseVo> getWarehouseName(){
		//仓库列表
		List<BusiWareHouseVo> wxWarehouses = busiWareHouseService.findBusiWareHousesByTree();
		return	wxWarehouses;	
	}
	
	/**
	 * 品种名称联想供应信息
	 * @param breedName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWxSupplyByBreedName",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxSupplyByBreedName(@RequestParam("breedName") String breedName) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			WxSupplyBreedVo wxSupply = wxSupplyService.findWxSupplyByBreedName(breedName);
			if(wxSupply==null){
				map.put("ok", false);
			}else{
				map.put("ok", true);
				map.put("obj", wxSupply);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			//if(e instanceof WxErrorException){
			//	msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", "网络异常，请重试");
			//}else{
				
			//}
			logger.error("WxSearchController.getWxSupplyByBreedName："+msg);
		}
		return map;
	}
	
	/**
	 * @Description: 根据搜索关键字去solr服务器里分页查询搜索结果
	 * @Author: 宋威
	 * @Date: 2015-07-15
	 * @param modelMap
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@RequestMapping(value={""},method={RequestMethod.POST, RequestMethod.GET})
	public String searchByKeyWords(ModelMap modelMap,HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		String pageNo = request.getParameter("pageNo");
		setSearchParams(dto, request);
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		List<BusiListingSearchVo> list = wxSearchService.searchByKeyWords(dto,page);
		page.setResults(list);
		modelMap.put("page", page);
		//将keywords放在页面上
		setFormData(dto, modelMap,request);
		String tunnage = wxSearchService.getWarrantsTunnage();
		modelMap.put("tunnage", tunnage);
		List<Map<String, String>> categorys = wxSearchService.getCateGorys();
		modelMap.put("categorys", categorys);
		modelMap.put("type", "all");
		modelMap.put("cur_url", "/search?type=all");
		modelMap.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "sells";
	}
	
	/**
	 * @Description: 高级搜索
	 * @Author: 宋威
	 * @Date: 2015-07-15
	 * @param modelMap
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@RequestMapping(value={"/senior"},method={RequestMethod.POST, RequestMethod.GET})
	public String seniorSearch(ModelMap modelMap,HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		String pageNo = request.getParameter("pageNo");
		setSearchParams(dto, request);
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		List<BusiListingSearchVo> list = wxSearchService.searchByKeyWords(dto,page);
		page.setResults(list);
		modelMap.put("page", page);
		//将keywords放在页面上
		setFormData(dto, modelMap,request);
		String tunnage = wxSearchService.getWarrantsTunnage();
		modelMap.put("tunnage", tunnage);
		List<Map<String, String>> categorys = wxSearchService.getCateGorys();
		modelMap.put("categorys", categorys);
		modelMap.put("flag", true);  //判断是否出现排序筛选条件的开关
		modelMap.put("type", "senior");
		modelMap.put("cur_url", "/search/senior?type=senior");
		return "sells";
	}
	
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
		setSearchParams(dto, request);
		dto.setCategoryName(StringUtils.isNotEmpty(value)?value:"");
		dto.setSearchId(StringUtils.isNotEmpty(id)?id:"");
		//设置有效的可搜索的品种id集合
		String breedIds = wxSearchService.getSearchBreedId(id);
		dto.setBreedId(breedIds);
		//搜索结果
		String pageNo = request.getParameter("pageNo");
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		dto.setMode(ListingSearchEngineEnum.SEARCH_MODE_LINK.getValue());
		
		List<BusiListingSearchVo> list= wxSearchService.searchBreedsBySolr(dto,page);
		page.setResults(list);
		//将值放在页面上
		setFormData(dto, modelMap,request);
		modelMap.put("page", page);
		//设置顶部搜索条件
		List<Map<String, String>> categorys = wxSearchService.getCateGorys();
		String total = wxSearchService.getWlTotalByCateGoryId(id);
		modelMap.put("categorys", categorys);
		modelMap.put("cid", id);
		modelMap.put("total", total);
		modelMap.put("categoryname", value);
		modelMap.put("flag", true);  //判断是否出现排序筛选条件的开关
		modelMap.put("type", "one");
		modelMap.put("cur_url", "/search/category/"+id+"?type=one");
		return "sells";
	}
	
	/**
	 * @Description: 根据挂牌id 查询用户姓名，注册日期，挂牌量，成交量等
	 * @Author: 宋威
	 * @Date: 2015-07-16
	 * @param listingId 挂牌Id
	 * @return BusiGoodsSellerVo 卖家信息json串
	 */
	@ResponseBody
	@RequestMapping(value={"/getSeller"},method={RequestMethod.POST, RequestMethod.GET})
	public BusiGoodsSellerVo getSeller(@RequestParam(value="listingId") String listingId){
		// 卖家信息
		BusiGoodsSellerVo sellerInfo = wxSearchService.selectGoodsSellerInfo(listingId);
		return sellerInfo;
	}
	
	/**
	 * @Description: 根据挂牌id 查询用户姓名，注册日期，挂牌量，成交量等
	 * @Author: 宋威
	 * @Date: 2015-07-16
	 * @param listingId 挂牌Id
	 * @return BusiGoodsSellerVo 卖家信息json串
	 */
	@ResponseBody
	@RequestMapping(value={"/getMobile"},method={RequestMethod.POST, RequestMethod.GET})
	public UcUser getMobile(@RequestParam(value="listingId") String listingId){
		//这里主要是根据挂牌id搜索到用户的手机号码。
		UcUser ucuser = wxUserService.getUcUserByListingId(listingId);
		return ucuser;
	}
	
	/**
	 * @Description: 根据搜索关键字去solr服务器里分页查询搜索结果
	 * @Author: 宋威
	 * @Date: 2015-07-14
	 * @param modelMap
	 * @param keyWords 搜索关键字
	 * @param request
	 * @return String : 前台freemarker文件名称
	 */
	@ResponseBody
	@RequestMapping(value={"/searchByFenye"},method={RequestMethod.POST, RequestMethod.GET})
	public Page <BusiListingSearchVo> searchByFenye(ModelMap modelMap,HttpServletRequest request){
		BusiListingSearchEngineDto dto = new BusiListingSearchEngineDto();
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String id = request.getParameter("id");
		Page <BusiListingSearchVo> page = new Page <BusiListingSearchVo>();
		//搜索结果
		String pageNo = request.getParameter("pageNo");
		setSearchParams(dto, request);
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);  //设置一页显示5条记录
		if(pageNo!=null&&!"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}else{
			page.setPageNo(1);
		}
		if("all".equals(type)){
			List<BusiListingSearchVo> list = wxSearchService.searchByKeyWords(dto,page);
			page.setResults(list);
		}else if("one".equals(type)){ 
			dto.setCategoryName(StringUtils.isNotEmpty(value)?value:"");
			dto.setSearchId(StringUtils.isNotEmpty(id)?id:"");
			//设置有效的可搜索的品种id集合
			String breedIds = wxSearchService.getSearchBreedId(id);
			dto.setBreedId(breedIds);
			dto.setMode(ListingSearchEngineEnum.SEARCH_MODE_LINK.getValue());
			List<BusiListingSearchVo> list= wxSearchService.searchBreedsBySolr(dto,page);
			page.setResults(list);
		}else if("senior".equals(type)){ 
			List<BusiListingSearchVo> list = wxSearchService.searchByKeyWords(dto,page);
			page.setResults(list);
		}
		return page;
	}
	
	private void setSearchParams(BusiListingSearchEngineDto dto,HttpServletRequest request){
		//关键字(搜索为关键字的时候)
		dto.setKeyWords(request.getParameter("keyWords"));
		//链接
		String searchType=request.getParameter("searchType");
		if(StringUtils.isNotEmpty(searchType)){
			if(searchType.equals(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_CATEGORY.toString())){
				dto.setCategoryName(request.getParameter("categoryName"));
			}else if(searchType.equals(ListingSearchEngineEnum.SEARCH_RESULT_TYPE_BREED.toString())){
				dto.setBreedName(request.getParameter("breedName"));
			}
		} else
			searchType = dto.getSearchType();
		dto.setWarehouseName(request.getParameter("warehouseName"));
		dto.setGrade(request.getParameter("grade"));
		dto.setBreedCname(request.getParameter("breedCname"));
		//过滤
		String priceB= request.getParameter("priceB");
		String priceE= request.getParameter("priceE");
		List <String>priceList=new ArrayList<String>();
		//String[] priceRange= new String[2];
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
		//
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
		//String[] examineTimeRange = request.getParameterValues("examineTimeRange[]");
		if(!ArrayUtils.isEmpty(examineTimeRange)){
			dto.setExamineTimeRange(examineTimeRange);
		}
		//
		dto.setOrigin(request.getParameter("origin"));
		//排序
		dto.setSortPrice(request.getParameter("sortPrice"));
		dto.setSortListingSurplus(request.getParameter("sortListingSurplus"));
		dto.setSortExamineTime(request.getParameter("sortExamineTime"));
		//搜索的类型和返回的类型
		dto.setSearchType(searchType);
		dto.setResultType(request.getParameter("resultType"));
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
		modelMap.put("resultType", dto.getResultType());
		//添加的2个参数
		//(1)用来存放breedId或者是categoryId
		modelMap.put("searchId", dto.getSearchId());
		//(2)用来存放breed上级类目的名称
		modelMap.put("categorys_name", request.getParameter("categorys_name"));
		//将价格和时间添加到modelMap中
		modelMap.put("priceB", request.getParameter("priceB"));
		modelMap.put("priceE", request.getParameter("priceE"));
		modelMap.put("dateB", request.getParameter("dateB"));
		modelMap.put("dateE", request.getParameter("dateE"));
		//如果上次是排序，保存上次按照排序的结构
		modelMap.put("lastSort", request.getParameter("lastSort"));
		//上一次的值
		modelMap.put("value", request.getParameter("value"));
	}
}