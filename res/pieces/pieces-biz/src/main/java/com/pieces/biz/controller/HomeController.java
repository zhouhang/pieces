package com.pieces.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Article;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.ArticleVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.service.ArticleService;
import com.pieces.service.CategoryService;
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
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
						ModelMap model){
		PageInfo<ArticleVo> page = articleService.findByModel(2,1,5);
		model.put("articles",page.getList());
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
	 * 
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
}
