package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.constant.WebConstatVar;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.HomePageListingService;
import com.jointown.zy.common.service.IMemberHomeService;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.vo.MemberHomeVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 会员首页controller
 * author ldp
 * 2015年1月4日
 */
@Controller()
@RequestMapping(value = "/memberHome")
public class MemberHomeController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(MemberHomeController.class);
	
	@Autowired
	public IMemberHomeService memberHomeService;
	
	@Autowired
	public HomePageListingService homePageListingService;
	
	
	/**
	 * @Description: 调整到会员中心首页
	 * @Author: 宋威
	 * @Date: 2015年5月22日
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String getMemberHome(ModelMap modelMap,HttpServletRequest request){
			String result = "";
			Subject subject = SecurityUtils.getSubject();
			//header登录名称使用
			UcUserVo userinfo = (UcUserVo)subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			//left菜单选中使用
			String url=request.getRequestURI();
		try {
			Map<String,Object>  map = completeInfoAuth(url, userinfo, subject);
			if(!(boolean) map.get("isComplete")){
				result = "redirect:http://uc.54315.com"+(String) map.get("redirectUrl");
			}else{
				userinfo.setUrl(url);
				modelMap.addAttribute("userinfo", userinfo);
				//查询会员信息
				String userName = (String) subject.getPrincipal();
				MemberHomeVo memberInfo = memberHomeService.getMemberHomeInfo(userName);
				modelMap.addAttribute("memberInfo", memberInfo);
				List<HomePageListingDto> list = homePageListingService.getRecommendList(getUserInfo().getUserId());
				modelMap.addAttribute("hotlist", list);
				List<Integer> nums = memberHomeService.getNums((userinfo!=null)?userinfo.getUserId():0L);
				modelMap.addAttribute("nums", nums);
				modelMap.addAttribute("COMPLETED_ORDER", BusiOrderStateEnum.COMPLETED_ORDER.getCode());
				modelMap.addAttribute("PlACED_ORDER", BusiOrderStateEnum.PlACED_ORDER.getCode());
				modelMap.addAttribute("READY_WARE", BusiOrderStateEnum.READY_WARE.getCode());
				result = "public/memberCenter";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description: 推动用户去完善信息{经营信息,联系人信息}
	 * @Author: Calvin.wh
	 * @Date: 2015-10-22
	 * @param flag
	 * @param url
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> completeInfoAuth(String url, UcUserVo user , Subject subject) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 验证当前用户是否完善经营信息
		if (!user.isHasScope()) {
			resultMap.put("redirectUrl", WebConstatVar.COMPLETE_BUSI_INFO_URL);
			resultMap.put("isComplete", Boolean.FALSE);
			return resultMap;
		} else {
			// 验证当前用户是否完善联系人信息
			if (!user.isHasContacter()) {
				resultMap.put("redirectUrl", WebConstatVar.COMPLETE_CONTACTER_INFO_URL);
				resultMap.put("isComplete",  Boolean.FALSE);
				return resultMap;
			}
		}
		resultMap.put("isComplete",  Boolean.TRUE);
		return resultMap;
	}
}
