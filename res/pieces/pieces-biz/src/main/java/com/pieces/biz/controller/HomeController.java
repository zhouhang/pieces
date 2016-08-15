package com.pieces.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.enums.WeightEnum;
import com.pieces.service.utils.predicate.RegularPredicate;
import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 处理主页业务
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private HomeWeightService homeWeightService;
	@Autowired
	private AdService adService;


	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
						ModelMap model){
		//新闻
		PageInfo<ArticleVo> page = articleService.findByModel(2,1,5);
		model.put("articles",page.getList());
		//商品和分类
		List<CommodityVo> commodityVos = homeWeightService.getHomeCommoditys(WeightEnum.COMMODITY.name());
		List<HomeCategoryVo> categoryList = homeWeightService.getHomeCategorys(WeightEnum.CATEGORY.name());

		model.put("commodityList",commodityVos);
		model.put("categoryList",categoryList);
		//广告
		List<AdVo> adBannerList =adService.findByType(CodeEnum.AD_BANNER.getId());
		List<AdVo> adBarList =adService.findByType(CodeEnum.AD_SHOWCASE_BAR.getId());
		model.put(CodeEnum.AD_BANNER.name(),adBannerList);
		model.put(CodeEnum.AD_SHOWCASE_BAR.name(),adBarList);

		//标志首页
		model.put("CURRENT_PAGE","home");

        return "home";
    }

    
	/**
	 * 是否弹框登录
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/pop", method = RequestMethod.POST)
	public void login(ModelMap model,String url, HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated() && !subject.isRemembered()){
			Result result = new Result(true).info(url);
			WebUtil.print(response, result);
			return;
		}
		Result result = new Result(false);
		WebUtil.print(response, result);
	}


    /**
	 * 进入弹框登录页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/popLogin", method = RequestMethod.GET)
	public String toLogin(ModelMap model,String url, HttpServletRequest request) {
		model.put("url", url);
		return "login_mini";
	}


	/**
	 * 全部分类
	 * @return
     */
	@RequestMapping(value = "/category/all", method = RequestMethod.GET)
	public String allCategory(ModelMap model){
		List<CategoryVo> categoryVoList = categoryService.findByLevelAndPinyin(1,null,null);
		categoryPinyin(categoryVoList);
		model.put("categorys",categoryVoList);
		return "allsort";
	}


	private void categoryPinyin(List<CategoryVo> categoryVoList){
		String[] letters =  new String[]{"A","B","C","D","E","F","G","H","I","J",
				"K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(CategoryVo categoryVo : categoryVoList){
			List<CategoryVo> childCategorys = categoryService.findByLevelAndPinyin(2,categoryVo.getId(),null);
			Map<String,List<CategoryVo>> map = new HashMap<>();
			for(String letter: letters){
				RegularPredicate eqlPredicate = new RegularPredicate(Pattern.compile(letter.toLowerCase()+"[a-z]*"));
				Collection<CategoryVo> collection = CollectionUtils.select(childCategorys,new BeanPredicate("pinyin",eqlPredicate));
				if(!collection.isEmpty()){
					map.put(letter,new ArrayList<CategoryVo>(collection));
				}

			}
			categoryVo.setCategoryPinyin(map);
		}

	}



}
