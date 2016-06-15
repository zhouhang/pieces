package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.google.gson.Gson;
import com.jointown.zy.common.dto.HomePageFeedBackDto;
import com.jointown.zy.common.service.FeedBackService;
import com.jointown.zy.common.vo.HomePageFeedBackVo;

/**
 * 
 *
 * @author ff
 *
 * @data 2015年11月03日
 */
@Controller
@RequestMapping(value = "feedBackManage")
public class FeedBackController extends UserBaseController{
	private final static Logger logger = LoggerFactory.getLogger(FeedBackController.class);
	
	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping(value = "/addFeedBack")
	@ResponseBody
	public String addFeedBack(
			@ModelAttribute HomePageFeedBackDto homePageFeedBackDto,HttpServletRequest request)
			throws Exception {
		// 查询结果
		Gson gson = new Gson();
		Map<String,Object> map  =  homePageFeedBackDto.validateFiled();
		if(map.size() != 0){
			map.put("ok", "error");
			return gson.toJson(map);
		}
		
		//珍药融资，半小时只能请求5次
		if(homePageFeedBackDto.getType().equals("4")){
			long firstTime = 0;
			int time = 0;
			HttpSession session = request.getSession();
			Object obj = session.getAttribute(session.getId());
			Map<String, Object> attr = null;
			if(obj!=null){
				attr = (Map<String, Object>)obj;
				firstTime = Long.parseLong(attr.get("firstTime").toString());
				time = Integer.parseInt(attr.get("time").toString());
			}else{
				attr = new HashMap<String, Object>();
				firstTime = new Date().getTime();
				time = 5;
			}
			
			long nowTime = new Date().getTime();
			
			if((nowTime - firstTime)<=30*60*1000&&time<=0){
				map.put("ok", "timeoff");
				return gson.toJson(map);
			}
			if((nowTime - firstTime)>30*60*1000){
				firstTime = nowTime;
				time = 5;
			}
			time--;
			attr.put("firstTime", firstTime+"");
			attr.put("time", time+"");
			session.setAttribute(session.getId(), attr);
		}
		
		HomePageFeedBackVo homePageFeedBackVo = new HomePageFeedBackVo();
		homePageFeedBackVo.setContent(homePageFeedBackDto.getContent());
		homePageFeedBackVo.setMobile(homePageFeedBackDto.getMobile());
		homePageFeedBackVo.setCreateTime(new Date());
		homePageFeedBackVo.setStatus("0");
		homePageFeedBackVo.setType(homePageFeedBackDto.getType());
		
		
		int ok = feedBackService.addFeedBack(homePageFeedBackVo);
		
		if (ok > 0) {
			map.put("msg", "处理成功！");
			map.put("ok", "true");
		} else {
			map.put("ok", "false");
			map.put("msg", "处理失败！");
		}
		return gson.toJson(map);
	}
	
	@RequestMapping(value = "/addIdeaResp")
	public String addIdeaResp(
			@ModelAttribute HomePageFeedBackDto homePageFeedBackDto)
			throws Exception {
		HomePageFeedBackVo homePageFeedBackVo = new HomePageFeedBackVo();

		String content = "名称："+homePageFeedBackDto.getUserName() +"  "
						+ "分类：" + homePageFeedBackDto.getIdeaType()+"  "
						+ "内容：" + homePageFeedBackDto.getContent();
		homePageFeedBackVo.setContent(content);
		homePageFeedBackVo.setMobile(homePageFeedBackDto.getMobile());
		homePageFeedBackVo.setCreateTime(new Date());
		homePageFeedBackVo.setStatus("0");
		homePageFeedBackVo.setType(homePageFeedBackDto.getType());
		
		
		int ok = feedBackService.addFeedBack(homePageFeedBackVo);
		
		return "/homepage2.0/response-success";
	}
	
	@RequestMapping(value = "/ideaResp")
	public String ideaResp(ModelMap model)
			throws Exception {
		return "/homepage2.0/idea-response";
	}
	
	@RequestMapping(value = "/validSidebarFormblur", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String validSidebarFormblur(
			@RequestParam("content") String content,@RequestParam("mobile") String mobile
			,@RequestParam("type") String type)
			throws Exception {
		Gson gson = new Gson();
		HomePageFeedBackDto homePageFeedBackDto = new HomePageFeedBackDto();
		homePageFeedBackDto.setContent(content);
		homePageFeedBackDto.setMobile(mobile);
		homePageFeedBackDto.setType(type);
		Map<String,Object> map  =  homePageFeedBackDto.validateFiled();
		if(map.size() != 0){
			map.put("ok", "error");
		}else{
			map.put("ok", "true");
		}
		return gson.toJson(map);
	}
}
