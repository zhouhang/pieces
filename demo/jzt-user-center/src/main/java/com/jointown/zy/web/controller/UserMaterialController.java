package com.jointown.zy.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.dto.UcUserMaterialDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.UcUserSexEnum;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserScope;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.IMemberHomeService;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.service.UcUserDealInService;
import com.jointown.zy.common.service.UcUserScopeService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.util.StringTransferSymbolUtil;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.wms.WmsApiCommon;

/**
 * 
 * 描述： 前台-会员中心-我的资料
 * 
 * 日期： 2014年12月10日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
@Controller(value="userMaterialController")
@RequestMapping(value="/my_material")
public class UserMaterialController extends UserBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(UserMaterialController.class);
	
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	public IMemberHomeService memberHomeService;
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	@Autowired
	private UcUserContactService ucUserContactService;
	@Autowired
	private UcUserDealInService ucUserDealInService;
	@Autowired
	private UcUserScopeService ucUserScopeService;
	
	/**
	 * 显示我的资料
	 */
	@RequestMapping(value="/info")
	public String showMyMaterial(ModelMap model,HttpServletRequest request){
		try {
			//header登录名称使用
			UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			//left菜单选中使用
			String url=request.getRequestURI();
			userinfo.setUrl(url);
			model.addAttribute("userinfo", userinfo);
			//获取会员信息
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			UcUser user = ucUserService.findUcUserByUserName(userName);
			//对个人信息进行隐秘符号处理 by Calvin.Wangh date 2015-08-04
			String userMobile = user.getMobile();
			String userEmail = user.getEmail();
			if(StringUtils.isNotBlank(userMobile))
				user.setMobile(StringTransferSymbolUtil.hideString(user.getMobile(), StringTransferSymbolUtil.MOBILE));
			if(StringUtils.isNotBlank(userEmail))
				user.setEmail(StringTransferSymbolUtil.hideString(user.getEmail(), StringTransferSymbolUtil.EMAIL));
			
			//显示省份
			AreaVo record = new AreaVo();
			record.setType(String.valueOf(1));
			model.addAttribute("provinceCode", warehouseApplyService.findAreasByCondition(record));
			//性别
			model.addAttribute("sexMap", UcUserSexEnum.toMap());
			//经营信息,品种,城市列表(根据省份ID获取城市)
			UcUserScope ucUserScope = ucUserDealInService.getUcUserScope(userinfo.getUserId());
			model.addAttribute("dealInfo", ucUserScope);
			model.addAttribute("dealBreeds", ucUserScopeService.getBreedsById(userinfo.getUserId()));
			if (null != ucUserScope) {
				AreaVo cityVo = new AreaVo();
				cityVo.setFirstcode(ucUserScope.getProvinceCode());
				model.addAttribute("cityCodeList", warehouseApplyService.findAreasByCondition(cityVo));
			}
			model.addAttribute("ucContactLst", ucUserContactService.selectUcUserContactsByUserId(userinfo.getUserId()));
			//联系人信息
			model.addAttribute("user", user);
		} catch (Exception e) {
			log.error("UserMaterialController.showMyMaterial error:", e);
		}
		return "public/myMaterial"; 
	}
	
	/**
	 * 验证原密码的匹配性
	 */
	@RequestMapping(value="/match_password")
	public @ResponseBody String matchPassword(@RequestParam("param") String oldPassword){
		Subject subject = SecurityUtils.getSubject();
		String curUserName = (String) subject.getPrincipal();//当前登录用户名
		//判定该会员是否为同一个人
		if(pwdAndNameMatch(oldPassword, curUserName)){
			return "y";
		} else {
			return "原密码与登录用户不匹配！";
		}
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/reset_password")
	public @ResponseBody String resetPassword(@ModelAttribute("userMaterial") UcUserMaterialDto userMaterial){
		JsonObject json = new JsonObject();
		if(userMaterial == null){
			userMaterial = new UcUserMaterialDto();
		}
		
		//验证通过
		if (passwordValidate(userMaterial, json)){
			Subject subject = SecurityUtils.getSubject();
			String curUserName = (String) subject.getPrincipal();//当前登录用户名
			//判定该会员是否为同一个人
			if(pwdAndNameMatch(userMaterial.getOldPassword(), curUserName)){
				//修改密码
				UcUser ucuser = new UcUser();
				ucuser.setUserName(curUserName);
				Password password = EncryptUtil.JointownEncode(userMaterial.getNewPassword());
				ucuser.setPassword(password.getPassword());//加密后的新密码
				ucuser.setSalt(password.getSalt());//加密盐
				ucuser.setUpdateTime(new Date());//更新时间
				ucUserService.updateUcUserPassword(ucuser);
				json.addProperty("status", "y");
				json.addProperty("info", "登录密码修改成功！");
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "原密码与登录用户不匹配！");
			}
		}
		return json.toString();
	}
	
	/**
	 * 验证原手机号码的匹配性
	 */
	@RequestMapping(value="/match_mobile")
	public @ResponseBody String matchMobile(@RequestParam("param") String oldMobile){
		Subject subject = SecurityUtils.getSubject();
		String curUserName = (String) subject.getPrincipal();//当前登录用户名
		//判定该会员是否为同一个人
		if(mobileAndNameMatch(oldMobile, curUserName)){
			return "y";
		} else {
			return "原手机号码与登录用户不匹配！";
		}
	}
	
	/**
	 * 修改手机号码
	 */
	@RequestMapping(value="/reset_mobile")
	public @ResponseBody String resetMobile(@ModelAttribute("userMaterial") UcUserMaterialDto userMaterial,
			HttpServletRequest request){
		JsonObject json = new JsonObject();
		if(userMaterial == null){
			userMaterial = new UcUserMaterialDto();
		}
		
		//验证通过
		if (moblieValidate(userMaterial, json, request)){
			Subject subject = SecurityUtils.getSubject();
			String curUserName = (String) subject.getPrincipal();//当前登录用户名
			//判定原手机号码与当前用户是否匹配
			if(mobileAndNameMatch(userMaterial.getOldMobile(), curUserName)){
				//修改手机号码
				UcUser ucuser = new UcUser();
				ucuser.setUserName(curUserName);
				ucuser.setMobile(userMaterial.getNewMobile());
				ucuser.setUpdateTime(new Date());//更新时间
				ucUserService.updateUcUserMobile(ucuser);
				json.addProperty("status", "y");
				json.addProperty("info", "手机号码修改成功！");
				json.addProperty("objval", userMaterial.getNewMobile());
				
				//add by fanyuna start 2015.07.03 修改成功后put消息，调用WMS接口同步最新数据
				UcUser ucUser = ucUserService.findUcUserByUserName(curUserName);
				if(ucUser != null && ucUser.getCertifyStatus()!=null && ucUser.getCertifyStatus().intValue()!=0){
					//add by fanyuna 2015.08.10 增加认证名称
					String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser,name);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, ucUserJson);
				}
				//add by fanyuna end 
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "原手机号码与登录用户不匹配！");
			}
		}
		return json.toString();
	}
	
	/**
	 * 修改公司名称或真实姓名
	 * @param userMaterial
	 * @return
	 */
	@RequestMapping(value="/reset_company")
	public @ResponseBody String resetCompany(@ModelAttribute("userMaterial") UcUserMaterialDto userMaterial){
		JsonObject json = new JsonObject();
		if(userMaterial == null){
			userMaterial = new UcUserMaterialDto();
		}
		//验证通过
		if(companyValidate(userMaterial, json)){
			Subject subject = SecurityUtils.getSubject();
			String curUserName = (String) subject.getPrincipal();//当前登录用户名
			//修改公司名或姓名
			UcUser ucuser = new UcUser();
			ucuser.setUserName(curUserName);
			ucuser.setCompanyName(userMaterial.getNewCompanyName());
			ucuser.setUpdateTime(new Date());//更新时间
			ucUserService.updateUcUserCompany(ucuser);
			json.addProperty("status", "y");
			json.addProperty("info", "公司名或姓名修改成功！");
			json.addProperty("objval", userMaterial.getNewCompanyName());
			
			//add by fanyuna start 2015.07.03 修改成功后，如果用户做了认证put消息，调用WMS接口同步最新数据
			UcUser ucUser = ucUserService.findUcUserByUserName(curUserName);
		    if(ucUser != null && ucUser.getCertifyStatus()!=null && ucUser.getCertifyStatus().intValue()!=0){
		    	//add by fanyuna 2015.08.10 增加认证名称
		    	String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
			String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser,name);
//			 String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser);
			 RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, ucUserJson);
		    }
			//add by fanyuna end 
		}
		return json.toString();
	}
	
	/**
	 * 设置邮箱
	 */
	@RequestMapping(value="/set_email")
	public @ResponseBody String setEmail(@ModelAttribute("userMaterial") UcUserMaterialDto userMaterial){
		JsonObject json = new JsonObject();
		if(userMaterial == null){
			userMaterial = new UcUserMaterialDto();
		}
		
		//验证通过
		if(emailValidate(userMaterial, json, false)){
			Subject subject = SecurityUtils.getSubject();
			String curUserName = (String) subject.getPrincipal();//当前登录用户名
			//修改公司名或姓名
			UcUser ucuser = new UcUser();
			ucuser.setUserName(curUserName);
			ucuser.setEmail(userMaterial.getNewEmail());
			ucuser.setUpdateTime(new Date());//更新时间
			ucUserService.updateUcUserEmail(ucuser);
			json.addProperty("status", "y");
			json.addProperty("info", "邮箱设置成功！");
			json.addProperty("objval", userMaterial.getNewEmail());
			
			//add by fanyuna start 2015.07.03 修改成功后put消息，调用WMS接口同步最新数据
			UcUser ucUser = ucUserService.findUcUserByUserName(curUserName);
			if(ucUser != null && ucUser.getCertifyStatus()!=null && ucUser.getCertifyStatus().intValue()!=0){
				//add by fanyuna 2015.08.10 增加认证名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
			String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser,name);
//			String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser);
			RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, ucUserJson);
			}
			//add by fanyuna end 
		}
		
		return json.toString();
	}
	
	/**
	 * 修改邮箱
	 */
	@RequestMapping(value="/reset_email")
	public @ResponseBody String resetEmail(@ModelAttribute("userMaterial") UcUserMaterialDto userMaterial){
		JsonObject json = new JsonObject();
		if(userMaterial == null){
			userMaterial = new UcUserMaterialDto();
		}
		
		//验证通过
		if(emailValidate(userMaterial, json, true)){
			Subject subject = SecurityUtils.getSubject();
			String curUserName = (String) subject.getPrincipal();//当前登录用户名
			//判定登录密码和登录用户的一致性
			if(pwdAndNameMatch(userMaterial.getOldPassword(), curUserName)){
				UcUser ucuser = new UcUser();
				ucuser.setUserName(curUserName);
				ucuser.setEmail(userMaterial.getNewEmail());
				ucuser.setUpdateTime(new Date());//更新时间
				ucUserService.updateUcUserEmail(ucuser);
				json.addProperty("status", "y");
				json.addProperty("info", "邮箱修改成功！");
				json.addProperty("objval", userMaterial.getNewEmail());
				
				//add by fanyuna start 2015.07.03 修改成功后put消息，调用WMS接口同步最新数据
				UcUser ucUser = ucUserService.findUcUserByUserName(curUserName);
				if(ucUser != null && ucUser.getCertifyStatus()!=null && ucUser.getCertifyStatus().intValue()!=0){
					//add by fanyuna 2015.08.10 增加认证名称
					String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser,name);
//				String ucUserJson = WmsApiCommon.ucUserToJsonStr(ucUser);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, ucUserJson);
				}
				//add by fanyuna end 
			} else {
				json.addProperty("status", "n");
				json.addProperty("info", "登录密码与登录用户不匹配！");
			}
		}
		
		return json.toString();
	}
	
	
	/**
	 * 修改密码的验证
	 */
	private boolean passwordValidate(UcUserMaterialDto userMaterial, JsonObject json){
		//原密码验证
		if (StringUtils.isEmpty(userMaterial.getOldPassword())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入原密码！");
			return false;
		} else if(!userMaterial.getOldPassword().matches("^[\\w\\W]{6,16}$")) {
			json.addProperty("status", "n");
			json.addProperty("info", "密码范围在6~16位之间！");
			return false;
		}
		
		//新密码验证
		if (StringUtils.isEmpty(userMaterial.getNewPassword())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入新密码！");
			return false;
		} else if(!userMaterial.getNewPassword().matches("^[\\w\\W]{6,16}$")) {
			json.addProperty("status", "n");
			json.addProperty("info", "密码范围在6~16位之间！");
			return false;
		} else if (userMaterial.getNewPassword().equals(userMaterial.getOldPassword())){
			json.addProperty("status", "n");
			json.addProperty("info", "新密码与原密码不能一致！");
			return false;
		}
		
		//确认密码
		if (StringUtils.isEmpty(userMaterial.getSurePassword())){
			json.addProperty("status", "n");
			json.addProperty("info", "请再输入一次新密码！");
			return false;
		} else if(!userMaterial.getSurePassword().equals(userMaterial.getNewPassword())) {
			json.addProperty("status", "n");
			json.addProperty("info", "您两次输入的账号密码不一致！");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判定密码与登录用户的匹配性是否一致
	 */
	private boolean pwdAndNameMatch(String pwd, String name){
		UcUser user = ucUserService.findUcUserByUserName(name);
		Password password = EncryptUtil.JointownEncode(pwd, user.getSalt());
		//判定密码与用户的匹配性是否一致
		if(user.getPassword().equals(password.getPassword())){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 修改手机号码的验证
	 */
	@SuppressWarnings("unchecked")
	private boolean moblieValidate(UcUserMaterialDto userMaterial, JsonObject json, HttpServletRequest request){
		//原手机号码的验证
		if(StringUtils.isEmpty(userMaterial.getOldMobile())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入原手机号码！");
			return false;
		} else if(!userMaterial.getOldMobile().matches("^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$")){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入11位正确手机号码！");
			return false;
		}
		
		List<UcUser> list= ucUserService.findUcUserByMobile(userMaterial.getNewMobile());
		//新手机号码的验证
		if (StringUtils.isEmpty(userMaterial.getNewMobile())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入新手机号码！");
			return false;
		} else if(!userMaterial.getNewMobile().matches("^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$")) {
			json.addProperty("status", "n");
			json.addProperty("info", "请输入11位正确手机号码！");
			return false;
		} else if (userMaterial.getNewMobile().equals(userMaterial.getOldMobile())){
			json.addProperty("status", "n");
			json.addProperty("info", "新手机号码与原手机号码不能一致！");
			return false;
		}else if (null != list && list.size() != 0) {
			json.addProperty("status", "n");
			json.addProperty("info", "新手机号码已存在,请重新输入！");
			return false;
		}
		
		Map<String, Object> code = (Map<String, Object>) request.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
		//手机验证码的验证
		if(code == null){
			json.addProperty("status", "n");
			json.addProperty("info", "请获取手机验证码！");
			return false;
		} else if (StringUtils.isEmpty(userMaterial.getMobileVerificationCode())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入手机验证码！");
			return false;
		}
		
		String result = MobileCodeUtil.verityMobileCode(code,
				userMaterial.getMobileVerificationCode(), userMaterial.getNewMobile());
		if (!"y".equals(result)){
			json.addProperty("status", "n");
			json.addProperty("info", result);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判定手机号码与登录用户的匹配性是否一致
	 */
	private boolean mobileAndNameMatch(String mobile, String name){
		UcUser user = ucUserService.findUcUserByUserName(name);
		//判定密码与用户的匹配性是否一致
		if(user.getMobile().equals(mobile)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 修改公司名的验证
	 */
	private boolean companyValidate(UcUserMaterialDto userMaterial, JsonObject json){
		//新公司名验证
		if (StringUtils.isEmpty(userMaterial.getNewCompanyName())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入新公司名或姓名！");
			return false;
		} else if(!userMaterial.getNewCompanyName().matches("^[\\w\\W]{2,16}$")) {
			json.addProperty("status", "n");
			json.addProperty("info", "新公司名或姓名在2~16位之间！");
			return false;
		} else if(userMaterial.getNewCompanyName().equals(userMaterial.getOldCompanyName())){
			json.addProperty("status", "n");
			json.addProperty("info", "新公司名或姓名与原来的不能一致！");
			return false;
		}
		
		return true;
	}
	
	private boolean emailValidate(UcUserMaterialDto userMaterial, JsonObject json, boolean usePwd){
		if (usePwd){
			//登录密码验证
			if (StringUtils.isEmpty(userMaterial.getOldPassword())){
				json.addProperty("status", "n");
				json.addProperty("info", "请输入登录密码！");
				return false;
			} else if(!userMaterial.getOldPassword().matches("^[\\w\\W]{6,16}$")) {
				json.addProperty("status", "n");
				json.addProperty("info", "密码范围在6~16位之间！");
				return false;
			}
		}
		
		if (StringUtils.isEmpty(userMaterial.getNewEmail())){
			json.addProperty("status", "n");
			json.addProperty("info", "请输入邮箱！");
			return false;
		} else if(!userMaterial.getNewEmail().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
			json.addProperty("status", "n");
			json.addProperty("info", "输入的邮箱格式不正确！");
			return false;
		} else if(userMaterial.getNewEmail().equals(userMaterial.getOldEmail())){
			json.addProperty("status", "n");
			json.addProperty("info", "新邮箱与原来的不能一致！");
			return false;
		}
		
		return true;
	}
}
