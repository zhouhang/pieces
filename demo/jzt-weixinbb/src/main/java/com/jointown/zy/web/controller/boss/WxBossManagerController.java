package com.jointown.zy.web.controller.boss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理菜单
 *
 * @author aizhengdong
 *
 * @date 2015年7月25日
 */
@Controller
@RequestMapping(value = "/WxBoss/manager")
public class WxBossManagerController extends WxUserBaseController {
	
	@RequestMapping(value = "")
	public String wxBossManagerAuthentication(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//验证登录
		BossUserVo bossUser = getWxBossUserInfo();
		if(bossUser == null){
			return "redirect:/WxBoss/wxSystem";
		}
		
		return "/boss/admin-list";
	}

}
