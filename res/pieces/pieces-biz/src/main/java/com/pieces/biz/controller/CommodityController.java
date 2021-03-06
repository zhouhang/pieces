package com.pieces.biz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.SeoTypeEnum;
import com.pieces.dao.model.*;
import com.pieces.service.*;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.exception.NotFoundException;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.WebUtil;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

	@Autowired
	private CommoditySearchService commoditySearchService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private HttpSession session;

	@Autowired
	private CommodityCollectService collectService;

	@Autowired
	private SeoSettingService seoSettingService;

	/**
	 * 获取商品列表分页
	 *
	 * @param pageSize
	 * @param pageNum
	 * @param commodityVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	@SecurityToken(generateToken = true)
	public String index(Integer pageSize, Integer pageNum, CommodityVo commodityVO, ModelMap model) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;

		PageInfo<CommodityVo> pageInfo = null;
		SeoSetting base=seoSettingService.findByType(SeoTypeEnum.BASE.getValue());
		String title="";
		String description="";
		String keyWords="";

		if(commodityVO.getBreedId() != null || commodityVO.getEqName() != null){
			pageInfo = indexBreed(pageSize, pageNum, commodityVO, model);

			//seo信息
			Category category=new Category();
			if(commodityVO.getBreedId() != null){
				category = categoryService.findById(commodityVO.getBreedId());
			}
			if(commodityVO.getEqName() != null){
				commodityVO.setEqName(commodityVO.getEqName());
				category = commodityService.findBreedByName(commodityVO);
			}


			SeoSetting commditySetting=seoSettingService.findByType(SeoTypeEnum.COMMODITY_LIST.getValue());
			title=commditySetting.getTitle();
			if(title!=null){
				title=title.replace("{品种名称}",category.getName());
				title=title.replace("{分类名称}",category.getName());
				title=title.replace("{通用标题}",base.getTitle()==null?"":base.getTitle());
			}
			description=commditySetting.getIntro();
			if(description!=null){
				description=description.replace("{品种名称}",category.getName());
				description=description.replace("{品种别名}",category.getAliases());
				description=description.replace("{品种描述}",category.getIntro()==null?"":category.getIntro());
				description=description.replace("{分类名称}",category.getName());
				description=description.replace("{通用描述}",base.getIntro()==null?"":base.getIntro());
			}



			keyWords=commditySetting.getKeyWord();
			if(keyWords!=null){
				keyWords=keyWords.replace("{品种名称}",category.getName());
				keyWords=keyWords.replace("{品种别名}",category.getAliases());
				keyWords=keyWords.replace("{分类名称}",category.getName());
				keyWords=keyWords.replace("{通用关键字}",base.getKeyWord()==null?"":base.getKeyWord());
			}






		}else{
			pageInfo = indexCategory(pageSize, pageNum, commodityVO, model);
			title="中药饮片商品展示 -"+base.getTitle();
			description=base.getIntro();
			keyWords=base.getKeyWord();
		}
		
		model.put("pageNum", pageNum);
		model.put("pageSize", pageSize);
		model.put("pageInfo", pageInfo);
		model.put("commodity", commodityVO);
		model.put("commodityParam", Reflection.serialize(commodityVO));
		model.put("title",title);
		model.put("description",description);
		model.put("keyWords",keyWords);


		//标志产品
		model.put("CURRENT_PAGE","commodity");

		return "product_list";
	}
	
	/**
	 * 查询品种列表，及需要参数
	 * @param pageSize
	 * @param pageNum
	 * @param commodityVO
	 * @param model
	 */
	private PageInfo<CommodityVo> indexBreed(Integer pageSize, Integer pageNum, CommodityVo commodityVO, ModelMap model) {
		Category category = null;
		List<CommodityVo> lxCommodity = null;
		// 根据品种ID 查询品种详情
		if(commodityVO.getBreedId() != null){
			category = categoryService.findById(commodityVO.getBreedId());
			// 查询品种下所有的商品名 只要名字相同都算一样的
			lxCommodity = commodityService.findDistinctName(commodityVO);
		}
		if(commodityVO.getEqName() != null){
			commodityVO.setEqName(commodityVO.getEqName());
			category = commodityService.findBreedByName(commodityVO);
			commodityVO.setBreedId(category.getId());
			lxCommodity = commodityService.findDistinctName(commodityVO);
			commodityVO.setBreedId(null);
		}
		
		if(category == null){
			return null;
		}

		
		Category parent = categoryService.findById(category.getParentId());
		Integer cid = commodityVO.getCategoryId();
		commodityVO.setCategoryId(category.getId());

		commodityVO.setSort(0);
		commodityVO.setStatus(1);
		PageInfo<CommodityVo> pageInfo = commodityService.query(commodityVO, pageNum, pageSize);
		commodityVO.setCategoryId(cid);
		                                                                                   
		model.put("lxCommodity", lxCommodity);
		model.put("category", category);
		model.put("parent", parent);
		return pageInfo;
	}
	
	/**
	 * 查询分类列表及参数
	 * @param pageSize
	 * @param pageNum
	 * @param commodityVO
	 * @param model
	 * @return
	 */
	private PageInfo<CommodityVo> indexCategory(Integer pageSize, Integer pageNum, CommodityVo commodityVO, ModelMap model) {
		List<CategoryVo> breedList = null;
		String breedIds = "";
		PageInfo<CommodityVo> pageInfo = null;
		if (commodityVO.getCategoryId() != null) {
			Category category = categoryService.findById(commodityVO.getCategoryId());
			breedList = categoryService.findBreedByParentId(commodityVO.getCategoryId());
			model.put("parent", category);
		} else {
			breedList = categoryService.findBreedNoPage(new CategoryVo());
		}
		if (!ValidUtils.listNotBlank(breedList)) {
			return null;
		}
		for (CategoryVo vo : breedList) {
			if (StringUtils.isNotBlank(vo.getId().toString())) {
				breedIds = breedIds + vo.getId() + ",";
			}
		}
		breedIds = breedIds.length() > 1 ? breedIds.substring(0, breedIds.length() - 1) : "";
		commodityVO.setCategoryIds(breedIds);// 查询分页数据
		Integer cid = commodityVO.getCategoryId();
		commodityVO.setCategoryId(null);
		commodityVO.setStatus(1);
		if(!breedIds.equals("")){
			pageInfo = new PageInfo<CommodityVo>(commodityService.query(commodityVO, pageNum, pageSize).getList());
			commodityVO.setCategoryId(cid);
			commodityVO.setCategoryIds(null);
		}
		return pageInfo;
	}

	/**
	 * 处理中文字符串，加单引号
	 *
	 * @param str
	 * @return
	 */
	private String formatString(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}

		if (str.indexOf(",") > 0) {
			String[] strs = str.split(",");
			String newStr = "";
			for (int i = 0; i < strs.length; i++) {
				newStr = newStr + "'" + strs[i] + "'" + ",";
			}
			newStr = newStr.substring(0, newStr.length() - 1);
			return newStr;
		} else {
			return "'" + str + "'";
		}

	}

	/**
	 * 设置code是否选中
	 *
	 * @param source
	 *            所有code
	 * @param target
	 *            已选code
	 * @param screens
	 *            页面已筛选
	 */
	private void setCodeCheck(List<Code> source, String target, List<String> screens) {
		if (!ValidUtils.listNotBlank(source) || !StringUtils.isNotBlank(target)) {
			return;
		}

		for (Code code : source) {
			if (target.contains(code.getId().toString())) {
				code.setChecked(true);
				screens.add(code.getName());
			} else {
				code.setChecked(false);
			}
		}
	}

	/**
	 * 搜索并跳转到搜索结果页面
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "search")
	@BizLog(type = LogConstant.commodity, desc = "商品搜索信息")
	public String proResult( Integer pageNum, Integer pageSize,
			ModelMap model, String keyword,HttpServletResponse response) {

		if(StringUtils.isBlank(keyword)){
//			return "redirect:/commodity/index";
		}

		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		Page<CommodityDoc> commodityDocPage = commoditySearchService.findByNameOrCategoryName(pageNum, pageSize, keyword);
		model.put("commodityDocPage", commodityDocPage);
		model.put("keyword", keyword);

		//标志产品
		model.put("CURRENT_PAGE","commodity");

		//seo信息
		SeoSetting base=seoSettingService.findByType(SeoTypeEnum.BASE.getValue());
		SeoSetting commditySetting=seoSettingService.findByType(SeoTypeEnum.SEARCH_RESULT.getValue());
		String title=commditySetting.getTitle();
		if(title!=null){
			title=title.replace("{搜索关键字}",keyword);
			title=title.replace("{通用标题}",base.getTitle()==null?"":base.getTitle());
		}
		String description=commditySetting.getIntro();
		if(description!=null){
			description=description.replace("{搜索关键字}",keyword);
			description=description.replace("{搜索结果数量}",Long.toString(commodityDocPage.getTotalElements()));
		}



		String keyWords=commditySetting.getKeyWord();
		if(keyWords!=null){
			keyWords=keyWords.replace("{搜索关键字}",keyword);
			keyWords=keyWords.replace("{通用关键字}",base.getKeyWord()==null?"":base.getKeyWord());
		}
		model.put("title",title);
		model.put("description",description);
		model.put("keyWords",keyWords);

		return "product_search_result";
	}

	/**
	 * 自动补全搜索关键字
	 *
	 * @param request
	 * @param response
	 * @param keyword
	 */
	@RequestMapping(value = "search/auto")
	public void autoComplete(HttpServletRequest request, HttpServletResponse response, String keyword) {
		if (StringUtils.isBlank(keyword)) {
			return;
		}
		List<Map<String, String>> result = commoditySearchService.findByName(keyword);
		WebUtil.print(response, result);
	}

	@RequestMapping(value = "/{id}")
	@BizLog(type = LogConstant.commodity, desc = "商品详情页面")
	@SecurityToken(generateToken = true)
	public String detail(@PathVariable("id") Integer id, ModelMap model) {
		CommodityVo commodity = commodityService.findVoById(id);
		if (commodity == null) {
			throw new NotFoundException();
		}

		List<Commodity> commodityList =	commodityService.findByName(commodity.getName());
		model.put("relations", commodityList);

		Category category = categoryService.findById(commodity.getCategoryId());
		Category category1 = categoryService.findById(category.getParentId());
		User user = (User) session.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		List<CommodityVo> featured = commodityService.featured(user, category.getId(), category1.getId());
		model.put("category", category1.getName());
		model.put("categoryId", category1.getId());
		model.put("commodity", commodity);
		model.put("featured", featured);
		if (user!= null) {
			model.put("collect", collectService.check(id, user.getId()));
		}
		//标志产品
		model.put("CURRENT_PAGE","commodity");
		//seo信息
		SeoSetting base=seoSettingService.findByType(SeoTypeEnum.BASE.getValue());
		SeoSetting commditySetting=seoSettingService.findByType(SeoTypeEnum.COMMODITY_DETAIL.getValue());

		String title=(commditySetting==null?null:commditySetting.getTitle());
		if(title!=null){
			title=title.replace("{商品标题}",commodity.getTitle());
			title=title.replace("{品种名称}",commodity.getName());
			title=title.replace("{分类名称}",category.getName());
			title=title.replace("{通用标题}",base.getTitle()==null?"":base.getTitle());
		}
		String description=commditySetting.getIntro();
		if(description!=null){
			description=description.replace("{商品描述}",commodity.getIntro()==null?"":commodity.getIntro());
			description=description.replace("{商品标题}",commodity.getTitle());
			description=description.replace("{品种名称}",commodity.getName());
			description=description.replace("{品种别名}",category.getAliases());
			description=description.replace("{分类名称}",category.getName());
			description=description.replace("{通用描述}",base.getIntro()==null?"":base.getIntro());
		}



		String keyWords=commditySetting.getKeyWord();
		if(keyWords!=null){
			keyWords=keyWords.replace("{商品关键字}",commodity.getKeyWord()==null?"":commodity.getKeyWord());
			keyWords=keyWords.replace("{品种名称}",commodity.getName());
			keyWords=keyWords.replace("{品种别名}",category.getAliases());
			keyWords=keyWords.replace("{通用关键字}",base.getKeyWord()==null?"":base.getKeyWord());
		}



		model.put("title",title);
		model.put("description",description);
		model.put("keyWords",keyWords);



		return "product";
	}



}
