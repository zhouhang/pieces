package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.HomePageFeedBackDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.FeedBackService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.HomePageFeedBackVo;
import com.jointown.zy.common.vo.WxDemandVo;

/**
 * 
 *
 * @author ff
 *
 * @data 2015年11月03日
 */
@Controller
@RequestMapping(value = "feedBackManage")
public class FeedBackController {
	
	private final static Logger logger = LoggerFactory.getLogger(FeedBackController.class);
	
	@Autowired
	private FeedBackService feedBackService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getFeedBackManager(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@ModelAttribute HomePageFeedBackDto homePageFeedBackDto, ModelMap model)
			throws Exception {
		Page<HomePageFeedBackVo> page = new Page<HomePageFeedBackVo>();
		if (!pageNo.isEmpty()) {
			page.setPageNo(Integer.parseInt(pageNo));
		}

		// 查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("homePageFeedBackDto", homePageFeedBackDto);
		page.setParams(params);

		// 查询结果
		List<HomePageFeedBackVo> homePageFeedBacks = feedBackService.findFeedBacksByCondition(page);
		for(HomePageFeedBackVo o : homePageFeedBacks){
			if(o.getType().equals("1")){
				o.setContent("采购需求："+o.getContent());
			}
			if(o.getType().equals("2")){
				o.setContent("卖货需求："+o.getContent());
			}
			if(o.getType().equals("3") || o.getType().equals("4")){
				o.setContent("融资需求："+o.getContent());
			}
		}
		page.setResults(homePageFeedBacks);
		model.put("page", page);

		return "/public/feedBackManage";
	}
	
	@RequestMapping(value = "/operFeedBack")
	@ResponseBody
	public String operFeedBack(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute HomePageFeedBackDto homePageFeedBackDto)
			throws Exception {
		// 查询结果
		JsonObject json = new JsonObject();
		
		HomePageFeedBackVo homePageFeedBackVo = new HomePageFeedBackVo();
		
		BossUserVo userinfo = this.getUserInfo(request);
		if(userinfo!=null){
			homePageFeedBackVo.setOperator(userinfo.getId().toString());
		}
		homePageFeedBackVo.setHomePageId(homePageFeedBackDto.getHomePageId());
		homePageFeedBackVo.setOprateTime(new Date());
		homePageFeedBackVo.setStatus(homePageFeedBackDto.getStatus());
		homePageFeedBackVo.setRemark(homePageFeedBackDto.getRemark());
		int ok = feedBackService.operFeedBack(homePageFeedBackVo);
		
		if (ok > 0) {
			json.addProperty("msg", "处理成功！");
			json.addProperty("ok", true);
		} else {
			json.addProperty("ok", false);
			json.addProperty("msg", "处理失败！");
		}
		return json.toString();
	}
	
	/**
     * 获取登录用户信息
     * @param requests
     */
    public BossUserVo getUserInfo(HttpServletRequest request){
    	
    	BossUserVo userinfo = (BossUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
    	return userinfo;
    }
}
