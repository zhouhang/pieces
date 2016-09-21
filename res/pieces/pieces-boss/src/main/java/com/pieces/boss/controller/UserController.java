package com.pieces.boss.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.impl.SmsService;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.User;
import com.pieces.service.AreaService;
import com.pieces.service.UserService;
import com.pieces.service.constant.BasicConstants;

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
	@RequiresPermissions(value = "customer:view")
	@RequestMapping(value = "/index")
	@BizLog(type = LogConstant.user, desc = "会员查询页面")
	public String userIndex(HttpServletRequest request,
							HttpServletResponse response,
							String  advices,
							Integer pageNum,
							Integer pageSize,
							UserVo userVo,
							ModelMap model) {
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;
		PageInfo<User> userPage = userService.findByCondition(userVo,pageNum,pageSize);
		model.put("userPage",userPage);
		model.put("userParams", Reflection.serialize(userVo));
		model.put("advices",advices);
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
	@RequiresPermissions(value = "customer:edit" )
	@RequestMapping(value = "/info/{id}")
	@BizLog(type = LogConstant.user, desc = "会员详细信息页面")
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
	@RequiresPermissions(value = "customer:add" )
	@RequestMapping(value = "/add" ,method= RequestMethod.GET)
	@BizLog(type = LogConstant.user, desc = "添加会员页面")
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
	@RequiresPermissions(value = {"customer:add","customer:edit"} ,logical = Logical.OR)
	@RequestMapping(value = "/save" ,method= RequestMethod.POST)
	@BizLog(type = LogConstant.user, desc = "保存会员信息")
	public void userSubmit(HttpServletRequest request,
						   HttpServletResponse response,
						   Boolean random,
						   User user)throws Exception{
		String advices = "新增用户信息成功!";
		String passWord =null;
		//是否发送随机密码
		if(random!=null&&random){
			passWord = SeqNoUtil.getXegerPwd();
			user.setPassword(passWord);
		}
		user.setSource(BasicConstants.USER_CREATECHANNEL_BOSS);
		//补全地址信息
		if(user.getAreaId()!=null){
			StringBuffer sb = new StringBuffer();
			Area area = areaService.findParentsById(user.getAreaId());
			sb.append(area.getProvince()).append(area.getCity()).append(area.getAreaname());
			user.setAreaFull(sb.toString());
		}
		//没有用户ID为新用户
		if(user.getId()==null){
			userService.addUser(user);
			//发送短信
			if(passWord!=null){
				smsService.sendAddUserAccount(passWord,user.getContactMobile(),user.getUserName());
			}
		}else{
			userService.updateUser(user);
			advices = "修改用户信息成功!";
			if(passWord!=null){
				smsService.sendUpdateUserAccount(passWord,user.getContactMobile(),user.getUserName());
			}
		}
		
		WebUtil.print(response,new Result(true).info(advices));
	}

	/**
	 * 验证用户名和手机号
	 * @param request
	 * @param response
     */
	@RequestMapping(value = "/username/check" ,method= RequestMethod.POST)
	public void checkUserName(HttpServletRequest request,
							  HttpServletResponse response,
							  @RequestParam(required = true) String userName){
		Map<String, String> result = new HashMap<String, String>();
		if(StringUtils.isNotBlank(userName)){
			User user =	userService.findByUserName(userName);
			if(user!=null){
				
				result.put("error", "名字已被占用!");
				WebUtil.print(response,result);
			}else{
				WebUtil.print(response,new Result(true).info("可以注册用户名!"));
			}
			return;
		}
		result.put("error", "用户名不能为空!");
		WebUtil.print(response,result);
	}


	/**
	 * 修改账户信息
	 * @param request
	 * @param response
	 * @param id
	 * @param model
     * @return
     */
	@RequiresPermissions(value = "customer:edit" )
	@RequestMapping(value = "/edit/{id}" ,method= RequestMethod.GET)
	@BizLog(type = LogConstant.user, desc = "修改会员信息页面")
	public String edit(HttpServletRequest request,
					   HttpServletResponse response,
					   @PathVariable("id") Integer id,
					   ModelMap model){
		User user =	userService.findById(id);
		Area area =  areaService.findParentsById(user.getAreaId());
		model.put("user",user);
		model.put("userArea",area);
		return "customers-account";
	}






}
