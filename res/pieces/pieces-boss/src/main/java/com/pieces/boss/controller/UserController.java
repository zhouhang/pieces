package com.pieces.boss.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.common.base.Strings;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.dto.Password;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.impl.SmsService;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.service.utils.SerializeUtils;
import com.pieces.tools.annotation.SecurityToken;
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
import com.pieces.service.constant.BasicConstants;

@Controller
@RequestMapping(value = "/user")
public class UserController extends  BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;
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

	@Autowired
	private CertifyRecordService certifyRecordService;


	@Autowired
	private RedisManager redisManager;

	@Autowired
    private UserFollowRecordService userFollowRecordService;

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
		PageInfo<UserVo> userPage = userService.findVoByCondition(userVo,pageNum,pageSize);
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
		UserVo user = userService.findVoById(id);
		model.put("user",user);
		return "customers-info";
	}
	@RequiresPermissions(value = {"customer:add","customer:edit"} ,logical = Logical.OR)
	@RequestMapping(value = "/certify/{id}")
	@BizLog(type = LogConstant.user, desc = "会员详细信息页面")
	@SecurityToken(generateToken = true)
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
	@SecurityToken(generateToken = true)
	public String userAdd(Integer userType,ModelMap model){
		model.put("userType",userType==null?1:userType);
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
	@SecurityToken(generateToken = true,validateToken=true)
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
			// 用户类型为代理商时手机号为必填
			if (Strings.isNullOrEmpty(user.getContactMobile()) && user.getType()==2) {
				advices="用户类型为代理商时手机号必填";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}

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
			if(user.getType()==1&&agentId!=null){
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
			// 用户类型为代理商时手机号为必填
			if (Strings.isNullOrEmpty(user.getContactMobile()) && user.getType()==2) {
				advices="用户类型为代理商时手机号必填";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}
			if (oldUser.getContactMobile() == null) {
				oldUser.setContactMobile("");
			}
			if(!Strings.isNullOrEmpty(user.getContactMobile()) &&!(oldUser.getContactMobile().equals(user.getContactMobile()))&&userService.ifExistMobile(user.getContactMobile())){
				advices="修改手机号存在";
				WebUtil.print(response,new Result(false).info(advices));
				return;
			}
			Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
			Member member=memberService.findById(mem.getId());
			if(user.getPassword()!=null){
				Password pass = EncryptUtil.PiecesEncode(memberPwd, member.getSalt());
				if(memberPwd==null||!(pass.getPassword().equals(member.getPassword()))){
					advices="管理员密码错误";
					WebUtil.print(response,new Result(false).info(advices));
					return;
				}
			}


			userService.updateUser(user);
			if(agentId!=null&&oldUser.getType()==1){
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
			if(user.getPassword()!=null){//清除认证缓存
				byte[] byteKey = SerializeUtils.serialize(user.getUserName());
				redisManager.removeInHash("biz:shiro_cache:authenticationCache".getBytes(), byteKey);
			}
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
	@SecurityToken(generateToken = true)
	public String edit(@PathVariable("id") Integer id,
					   ModelMap model){
		UserVo user =	userService.findVoById(id);
		//Area area =  areaService.findParentsById(user.getAreaId());
		model.put("user",user);
		/*
		if(user.getType()==1){
			UserBindVo userBindVo=new UserBindVo();
			userBindVo.setTerminalId(id);
			UserBindVo userBind=userBindService.getByVo(userBindVo);
			if(userBind!=null){
				User bindUser=userService.findById(userBind.getAgentId());
				if(bindUser!=null) {
					userBind.setAgentName(bindUser.getContactName());
				}
			}


			model.put("userBind",userBind);
		}*/


		return "customers-account";
	}
	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/search" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "代理商搜索")
	public Result searchUser(String name){

        UserVo userVo=new UserVo();
		userVo.setContactName(name);
		userVo.setType(2);
		userVo.setIsDel(false); // 搜索未禁用的代理商
		PageInfo<User> userPage = userService.findByCondition(userVo,1,10);
		return new Result(true).data(userPage);
	}
	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/searchMember" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "后台客服人员搜索")
	public Result searchMember(String name){

		MemberVo memberVo=new MemberVo();
		memberVo.setName(name);
		PageInfo<Member>  memberVoPageInfo=memberService.findByCondition(memberVo,1,10);
		return new Result(true).data(memberVoPageInfo);
	}


	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/certify/save" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "修改认证信息")
	@SecurityToken(generateToken = true,validateToken=true)
	public Result saveCertify(@RequestBody CertifyParamVo certifyParamVo,
							  ModelMap model){

        certifyRecordService.saveCertify(certifyParamVo.getUserCertificationVo(),certifyParamVo.getUserQualificationVos());
		return new Result(true).data("保存成功");
	}

	/**
	 * 禁用用户
	 * @param id
	 * @return
     */
	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/disable" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "禁用用户")
	public Result disable(Integer id) {
		userService.disable(id);
		return new Result(true);
	}


	/**
	 * 启用用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/enable" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "启用用户")
	public Result enable(Integer id) {
		userService.enable(id);
		return new Result(true);
	}



	@RequiresPermissions(value = "customer:edit" )
	@RequestMapping(value = "/trail/{id}")
	@BizLog(type = LogConstant.user, desc = "会员跟进记录页面")
	public String trailUser(HttpServletRequest request,
						   HttpServletResponse response,
						   @PathVariable("id") Integer id,
							Integer pageNum,
							Integer pageSize,
						   ModelMap model){
        UserVo userVo=userService.findVoById(id);
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;

		UserFollowRecordVo userFollowRecordVo=new UserFollowRecordVo();
		userFollowRecordVo.setUserId(id);

		PageInfo<UserFollowRecordVo> records=userFollowRecordService.findByParams(userFollowRecordVo,pageNum,pageSize);
        model.put("user",userVo);
        model.put("records",records);

		return "user_trail";

	}


	@RequiresPermissions(value = "customer:edit")
	@RequestMapping(value = "/trail" ,method= RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.user, desc = "会员跟进记录保存")
	public Result saveTrail(UserFollowRecordVo userFollowRecordVo) {
		Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
		userFollowRecordVo.setFollowId(mem.getId());
		userFollowRecordVo.setCreateTime(new Date());
		userFollowRecordService.create(userFollowRecordVo);
		return new Result(true).info("创建成功");
	}



}
