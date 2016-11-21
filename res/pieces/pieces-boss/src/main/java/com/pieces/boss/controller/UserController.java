package com.pieces.boss.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.UserBind;
import com.pieces.dao.vo.UserBindVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.dto.Password;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.impl.SmsService;
import com.pieces.service.utils.EncryptUtil;
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

	@Autowired
	private UserBindService userBindService;


	@Autowired
	UserCertificationService userCertificationService;

	@Autowired
	UserQualificationService userQualificationService;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MemberService memberService;
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

	@RequestMapping(value = "/certify/{id}")
	@BizLog(type = LogConstant.user, desc = "会员详细信息页面")
	public String userCertify(@PathVariable("id") Integer id,
						   ModelMap model){
		User user = userService.findById(id);
		UserCertificationVo userCertification=new UserCertificationVo();
		userCertification.setUserId(id);
		UserQualificationVo userQualification=new UserQualificationVo();
		userQualification.setUserId(id);



		model.put("userCertification",userCertificationService.findAll(userCertification));
		model.put("userQualification",userQualificationService.findAll(userQualification));
		model.put("user",user);
		return "customers-certificate";
	}


	/**
	 * 添加会员页面
     * @return
     */
	@RequiresPermissions(value = "customer:add" )
	@RequestMapping(value = "/add" ,method= RequestMethod.GET)
	@BizLog(type = LogConstant.user, desc = "添加会员页面")
	public String userAdd(){

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
						   String random,
						   String memberPwd,
						   User user,Integer agentId)throws Exception{
		String advices = "新增用户信息成功!";
		String passWord =null;
		//是否发送随机密码
		if(random!=null&&random.equalsIgnoreCase("on")){
			passWord = SeqNoUtil.getXegerPwd();
			user.setPassword(passWord);
		}
		user.setSource(BasicConstants.USER_CREATECHANNEL_BOSS);

		/*
		//补全地址信息
		if(user.getAreaId()!=null){
			StringBuffer sb = new StringBuffer();
			Area area = areaService.findParentsById(user.getAreaId());
			sb.append(area.getProvince()).append(area.getCity()).append(area.getAreaname());
			user.setAreaFull(sb.toString());
		}*/
		//没有用户ID为新用户
		if(user.getId()==null){
			if(userService.ifExistMobile(user.getContactMobile())){
				advices="手机号存在";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}
			if(userService.checkUserName(user.getUserName())){
				advices="用户名存在";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}
			userService.addUser(user);
			if(user.getType()==1){
				UserBind userBind=new UserBind();
				userBind.setAgentId(agentId);
				userBind.setTerminalId(user.getId());
				userBind.setCreateTime(new Date());
				userBindService.saveBind(userBind);
			}
			//发送短信
			if(passWord!=null){
				smsService.sendAddUserAccount(passWord,user.getContactMobile(),user.getUserName());
			}
		}else{
			User oldUser=userService.findById(user.getId());
			if(!(oldUser.getContactMobile().equals(user.getContactMobile()))&&userService.ifExistMobile(user.getContactMobile())){
				advices="修改手机号存在";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}
			Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
			Member member=memberService.findById(mem.getId());
			Password pass = EncryptUtil.PiecesEncode(memberPwd, member.getSalt());
			if(memberPwd==null||!(pass.getPassword().equals(member.getPassword()))){
				advices="管理员密码错误";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}

			userService.updateUser(user);
			if(agentId!=null&&user.getType()==1){
				UserBind userBind=new UserBind();
				userBind.setAgentId(agentId);
				userBind.setTerminalId(user.getId());
				userBind.setCreateTime(new Date());
				userBindService.saveBind(userBind);
			}
			else{
				userBindService.deleteByTerminalId(user.getId());
			}

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
	 * @param id
	 * @param model
     * @return
     */
	@RequiresPermissions(value = "customer:edit" )
	@RequestMapping(value = "/edit/{id}" ,method= RequestMethod.GET)
	@BizLog(type = LogConstant.user, desc = "修改会员信息页面")
	public String edit(@PathVariable("id") Integer id,
					   ModelMap model){
		User user =	userService.findById(id);
		//Area area =  areaService.findParentsById(user.getAreaId());
		model.put("user",user);
		UserBindVo userBindVo=new UserBindVo();
		userBindVo.setTerminalId(id);
		UserBind userBind=userBindService.getByVo(userBindVo);
		//model.put("userArea",area);
		model.put("userBind",userBind);
		return "customers-account";
	}



}
