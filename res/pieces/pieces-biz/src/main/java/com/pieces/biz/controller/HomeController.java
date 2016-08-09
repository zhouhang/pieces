package com.pieces.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Ad;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.AdVo;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.*;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.enums.WeightEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.User;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 处理主页业务
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController extends BaseController{
	
	@Autowired
	private UserService userService;
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
		List<Category> categoryList = homeWeightService.getHomeCategorys(WeightEnum.CATEGORY.name());
		Map<Integer,List<Category>> categoryBreedList = homeWeightService.getHomeBreeds(WeightEnum.BREED.name());
		Map<Integer,List<CommodityVo>> cateCommodityList = homeWeightService.getHomeCategoryCommoditys(WeightEnum.CATE_COMMODITY.name());
		model.put("commodityList",commodityVos);
		model.put("categoryList",categoryList);
		model.put("categoryBreedList",categoryBreedList);
		model.put("cateCommodityList",cateCommodityList);
		//广告
		adModels(model);
        return "home";
    }

	/**
	查询所有分类和品种信息
	 */
	@RequestMapping(value = "/category")
	@ResponseBody
	public List category(Integer level,
						 String pinyin){
		List<CategoryVo> categoryList =  categoryService.findByLevelAndPinyin(level,pinyin);
		return categoryList;
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


	private void adModels(ModelMap model){
		List<AdVo> adBannerList =adService.findByType(CodeEnum.AD_BANNER.getId());
		List<AdVo> adSearchList =adService.findByType(CodeEnum.AD_SEARCH.getId());

		model.put(CodeEnum.AD_BANNER.name(),adBannerList);
		model.put(CodeEnum.AD_SEARCH.name(),adSearchList);


	}

}
