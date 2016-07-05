package com.pieces.boss.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.User;
import com.pieces.service.AreaService;
import com.pieces.service.UserService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.utils.SendMessage;
import com.pieces.service.utils.YPSendMessage;
import com.pieces.service.vo.ValidFromVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 会员查询页面
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param model
     * @return
     */
	@RequestMapping(value = "/index")
	public String userIndex(HttpServletRequest request,
							HttpServletResponse response,
							Integer pageNum,
							Integer pageSize,
							ModelMap model) {
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;
		PageInfo<User> userPage =	userService.find(pageNum,pageSize);
		model.put("userPage",userPage);
		return "customers";
	}

	/**
	 * 添加会员页面
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value = "/add" ,method= RequestMethod.GET)
	public String userAdd(HttpServletRequest request,
						  HttpServletResponse response){
		return "customers-add";
	}

	/**
	 * 提交用户信息
	 * @param request
	 * @param response
	 * @param user
     */
	@RequestMapping(value = "/add" ,method= RequestMethod.POST)
	public void userSubmit(HttpServletRequest request,
						   HttpServletResponse response,
						   User user){
		user.setSource(BasicConstants.USER_CREATECHANNEL_BOSS);
		int userId = userService.addUser(user);
		System.out.println(user.getId());
	}

	/**
	 * 验证用户名和手机号
	 * @param request
	 * @param response

     */
	@RequestMapping(value = "/username/check" ,method= RequestMethod.POST)
	public void checkUserName(HttpServletRequest request,
							  HttpServletResponse response,
							  @RequestParam(required = true) String name,
							  @RequestParam(required = true) String param){

		if("userName".equals(name)&&StringUtils.isNotBlank(param)){
			User user =	userService.findByUserName(param);
			if(user!=null){
				WebUtil.print(response,new Result(false).info("该用户名已存在!"));
			}else{
				WebUtil.print(response,new Result(true).info("可以注册用户名!"));
			}
			return;
		}
		WebUtil.print(response,new Result(false).info("用户名不能为空!"));
	}


}
