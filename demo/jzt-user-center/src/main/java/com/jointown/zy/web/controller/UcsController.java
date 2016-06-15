package com.jointown.zy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ucs.creditpay.entities.requests.Request1000;

import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 珍药宝操作
 * @author zhouji
 * @date 2015年5月8日 上午11:30:50
 */
@Controller(value="ucsController")
@RequestMapping(value="/ucs")
public class UcsController extends UserBaseController  {
	@Autowired
	public UcsService ucsService;
	/**
	 * 跳转到我的珍药宝页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/gotoMyPage")
	public String gotoMyPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		UcUserVo userinfo = getUserInfo(request);
		model.put("type", type);
		model.put("userinfo", userinfo);
		return "public/myPage";
	}
	/**
	 * 访问优迈页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/payLogin")
	public String payLogin(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		try {
			//生成单点登录提交表单
			String form = ucsService.payLogin(type);
			model.put("form", form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "public/GoToUcsmyPage";
	}
}
