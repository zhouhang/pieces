package com.jointown.zy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.constant.WxConstant;

/**
 * 新手指引
 *
 * @author aizhengdong
 *
 * @data 2015年7月9日
 */
@Controller(value = "wxNewHandController")
public class WxNewHandController extends WxUserBaseController {

	/**
	 * 新手指引
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月9日
	 */
	@RequestMapping("newHand")
	public String newHand(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("hand_poster", WxConstant.ZYDS_URL);
		return "new-hand";
	}

	/**
	 * 了解珍药材
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月9日
	 */
	@RequestMapping("knowZyc")
	public String knowZyc(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "know-zyc";
	}

	/**
	 * 注册及认证
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月9日
	 */
	@RequestMapping("registerAuth")
	public String registerAuth(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "register-auth";
	}
	
	/**
	 * 采购商入门
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月9日
	 */
	@RequestMapping("buyerEntry")
	public String buyerEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "buyer-entry";
	}
	
	/**
	 * 供应商入门
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月9日
	 */
	@RequestMapping("supplierEntry")
	public String supplierEntry(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "supplier-entry";
	}
}
