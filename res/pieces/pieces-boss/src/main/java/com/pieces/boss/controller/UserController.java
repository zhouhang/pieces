package com.pieces.boss.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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





}
