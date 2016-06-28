package com.jointown.zy.web.controller.boss;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.enums.WxActivityTypeEnum;
import com.jointown.zy.common.enums.WxAdTypeEnum;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理：微信系统
 *
 * @author aizhengdong
 * @date 2015年8月17日
 */
@Controller
@RequestMapping(value = "/Boss/wxBossWeiXinSys")
public class WxBossWeiXinSysController extends WxUserBaseController {
	
	@RequestMapping(value = "")
	public String weiXinSysManager(HttpServletRequest request, ModelMap model){
		// 验证登录
		BossUserVo bossUser = getWxBossUserInfo();
		if (bossUser == null) {
			return "redirect:/WxBoss/wxSystem";
		}
		
		//活动类型
		model.put("wxActivityTypeMap", WxActivityTypeEnum.toMap());
		
		// 广告类型
		model.put("wxAdTypeMap", WxAdTypeEnum.toMap());
		
		return "/boss/admin-system";
	}
	
	
	/**
	 * 查询活动和广告类型
	 *
	 * @author aizhengdong
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/getType", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getType(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wxActivityTypeMap", WxActivityTypeEnum.toMap());
		map.put("wxAdTypeMap", WxAdTypeEnum.toMap());
		
		return map;
	}

}
