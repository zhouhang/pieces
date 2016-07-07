package com.pieces.boss.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.dao.vo.UserVo;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.impl.SmsService;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
public class UserController extends  BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private AreaService areaService;
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
							UserVo userVo,
							ModelMap model) {
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;
		PageInfo<User> userPage = userService.findByCondition(userVo,pageNum,pageSize);
		model.put("userPage",userPage);
		model.put("userParams",userVo.toString());
		return "customers";
	}

	/**
	 * 会员信息页面
	 * @param request
	 * @param response
	 * @param id
	 * @param model
     * @return
     */
	@RequestMapping(value = "/info/{id}")
	public String userInfo(HttpServletRequest request,
						   HttpServletResponse response,
						   @PathVariable("id") Integer id,
						   ModelMap model){
		User user = userService.findById(id);
		model.put("user",user);
		return "customers-info";
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
	 * @param random
     */
	@RequestMapping(value = "/save" ,method= RequestMethod.POST)
	public void userSubmit(HttpServletRequest request,
						   HttpServletResponse response,
						   Boolean random,
						   User user)throws Exception{
		String passWord =null;
		//是否发送随机密码
		if(random!=null&&random){
			passWord = SeqNoUtil.getRandomNum(6);
			user.setPassword(passWord);
		}
		user.setSource(BasicConstants.USER_CREATECHANNEL_BOSS);

		Area area =  areaService.findParentsById(user.getAreaId());

		//没有用户ID为新用户
		if(user.getId()==null){
			userService.addUser(user);
		}else{
			userService.updateUser(user);
		}
		//发送短信
		if(passWord!=null){
			smsService.sendUserAccount(passWord,user.getContactMobile(),user.getUserName());
		}
		WebUtil.print(response,new Result(true));
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


	/**
	 * 修改账户信息
	 * @param request
	 * @param response
	 * @param id
	 * @param model
     * @return
     */
	@RequestMapping(value = "/edit/{id}" ,method= RequestMethod.GET)
	public String edit(HttpServletRequest request,
					   HttpServletResponse response,
					   @PathVariable("id") Integer id,
					   ModelMap model){
		User user =	userService.findById(id);
		model.put("user",user);
		return "customers-account";
	}



}
