package com.jointown.zy.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.MemberAddDto;
import com.jointown.zy.common.dto.MemberMotifyDto;
import com.jointown.zy.common.dto.MemberSearchDto;
import com.jointown.zy.common.dto.UcUserContactDto;
import com.jointown.zy.common.dto.UcUserScopeDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.UcUserSexEnum;
import com.jointown.zy.common.exception.ErrorMsg;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserContact;
import com.jointown.zy.common.model.UcUserScope;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.CompanyCertifyService;
import com.jointown.zy.common.service.IMemberCertifyService;
import com.jointown.zy.common.service.IMemberManageService;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.service.UcUserDealInService;
import com.jointown.zy.common.service.UcUserScopeService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.GetInitPassword;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.wms.WmsApiCommon;

/**
 * 会员管理
 * @author ldp
 * 时间 2014-11-20
 */

@Controller(value="memberManagerController")
@RequestMapping(value="/getMemberManage")
public class MemberManagerController extends UserBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberManagerController.class);
	
	@Autowired
	public IMemberManageService memberManageService;
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	private BossUserService bossUserService;
	@Autowired
	private UcUserScopeService ucUserScopeService;
	@Autowired
	private UcUserContactService ucUserContactService;
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	@Autowired
	private UcUserDealInService ucUserDealInService;
	/**
	 * 会员添加
	 * @param memberAddDto
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value="/addMember",method=RequestMethod.POST)
	@ResponseBody
	public ErrorMsg addMember(@ModelAttribute MemberAddDto memberAddDto,HttpServletRequest request){
		
		ErrorMsg errorMsg = new ErrorMsg();
		log.info(memberAddDto.toString());
		String msg = memberAddDto.validate();
		if (!"success".equals(msg)) {
			errorMsg.setMsg(msg);
			return errorMsg;
		}
		
		if (null != memberManageService.memberNameIsHaved(memberAddDto.getUserName())) {
			errorMsg.setMsg("该会员名已存在,请重新输入！");
			return errorMsg;
		}
		
		
		List<UcUser> list= ucUserService.findUcUserByMobile(memberAddDto.getMobile());
		if (null != list&&0!=list.size()) {
			errorMsg.setMsg("该手机号已存在,请重新输入！");
			return errorMsg;
		}
		
		
		UcUser ucUser = new UcUser();
		ucUser.setUserName(memberAddDto.getUserName());
		ucUser.setPassword(memberAddDto.getPassWord());
		ucUser.setMobile(memberAddDto.getMobile());
		ucUser.setCompanyName(memberAddDto.getRealName());
		ucUser.setRemark(memberAddDto.getRemark());
		errorMsg.setMsg(String.valueOf(memberManageService.addMember(ucUser)));
		return errorMsg;
		//redirect:/searchMemberCondition
	}*/
	

	/**
	 * 跳转会员添加
	 * @param 
	 * @param request
	 * @author ldp update优化会员添加   重写修改方法2015-06-26
	 * @return
	 */
	@RequestMapping(value="/toAddMember")
	public String addMember(HttpServletRequest request,ModelMap model){
		AreaVo record = new AreaVo();
		record.setType(String.valueOf(1));
		model.addAttribute("sexMap", UcUserSexEnum.toMap());
		model.addAttribute("provinceCode", warehouseApplyService.findAreasByCondition(record));
		return "public/memberAdd";
	}
	
	/**
	 * 会员添加
	 * @param memberAddDto
	 * @param request
	 * @author ldp update优化会员添加   重写修改方法2015-06-26
	 * @return
	 */
	@RequestMapping(value="/addMember",method=RequestMethod.POST)
	@ResponseBody
	public String addMember(@ModelAttribute MemberAddDto memberAddDto,HttpServletRequest request){
		JsonObject j = new JsonObject();
		Gson gson = new Gson();
		log.info(memberAddDto.toString());
		String msg = memberAddDto.validate();
		if (!"success".equals(msg)) {
			j.addProperty("status", msg);;
			return gson.toJson(j);
		}
		
		if (null != memberManageService.memberNameIsHaved(memberAddDto.getUserName())) {
			j.addProperty("status", "该会员名已存在,请重新输入！");//该会员名已存在,请重新输入！
			return gson.toJson(j);
		}
		
		List<UcUser> list= ucUserService.findUcUserByMobile(memberAddDto.getMobile());
		if (null != list&&0!=list.size()) {
			j.addProperty("status", "该手机号已存在,请重新输入！");
			return gson.toJson(j);
		}
		
		UcUser ucUser = new UcUser();
		ucUser.setUserName(memberAddDto.getUserName());
		ucUser.setPassword(memberAddDto.getPassWord());
		ucUser.setMobile(memberAddDto.getMobile());
		ucUser.setCompanyName(memberAddDto.getRealName());
		ucUser.setRemark(memberAddDto.getRemark());
		//added by biran 20150901 后台添加来源
		ucUser.setSource(2);
		//创建人，后台登录用户
		BossUserVo userinfo = this.getUserInfo(request);
		if(userinfo!=null){
			ucUser.setCreaterId(userinfo.getId().intValue());
			ucUser.setUpdaterId(userinfo.getId().intValue());
		}
		int flag = memberManageService.addMember(ucUser);
		//获取ID信息
		UcUser NewUcUser=memberManageService.findMemberByUserName(memberAddDto.getUserName());
		if(flag > 0){
			j.addProperty("status", "y");
			j.addProperty("userId", NewUcUser.getUserId().toString());
		}else{
			j.addProperty("status", "n");
		}
		return gson.toJson(j);
		//redirect:/searchMemberCondition
	}
	
	/**
	 * 根据条件查询会员
	 * @param memberSearchDto
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/searchMemberCondition")
	public String searchMemberCondition(@ModelAttribute() MemberSearchDto memberSearchDto,ModelMap modelMap){
		log.info(memberSearchDto.toString());
		
		List<UcUser> ucUserList = memberManageService.conditonSearchMember(memberSearchDto);
		modelMap.addAttribute("ucUserList", ucUserList);
		modelMap.addAttribute("memberSearchParams", memberSearchDto);
		
		return "public/memberManage";
	}
	
	/**
	 * 根据会员id获取会员信息
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getMember")
	@ResponseBody
	public UcUser getMemberByUserId(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId").trim();
		UcUser ucUser = memberManageService.findMemberByUserID(userId);
		/*StringBuilder memberData = new StringBuilder();
		memberData.append("userId:").append(ucUser.getUserId());
		memberData.append(",userName:").append(ucUser.getUserName());
		memberData.append(",companyName:").append(ucUser.getCompanyName());
		memberData.append(",mobile:").append(ucUser.getMobile());
		memberData.append(",email:").append(ucUser.getEmail());
		memberData.append(",remark:").append(ucUser.getRemark());*/
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userId", ucUser.getUserId());
		jsonObject.addProperty("userName", ucUser.getUserName());
		jsonObject.addProperty("companyName", String.valueOf(ucUser.getCompanyName()));
		jsonObject.addProperty("mobile", ucUser.getMobile());
		jsonObject.addProperty("email", ucUser.getEmail());
		jsonObject.addProperty("remark", ucUser.getRemark());
		return ucUser;
		//responseHmtl(response, jsonObject.toString());
		//return null;
	}
	
	/**
	 * 根据会员id获取会员所有信息
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	@RequestMapping(value="/getAllByUserId")
	public String getAllByUserId(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String userId = request.getParameter("userId").trim();
		UcUser ucUser = memberManageService.findMemberByUserID(userId);
		UcUserScope ucUserScope = null;
		List<UcUserBreed> breedList = null;
		List<UcUserContact> ucUserContact = null;
		if(ucUser!=null){
			ucUserScope = ucUserScopeService.selectUcUserScopeById(Long.valueOf(userId));
			
			if(ucUserScope!=null){
				AreaVo record = new AreaVo();
				record.setCode(ucUserScope.getProvinceCode());
				record.setType(String.valueOf(1));
				List<AreaVo> provinceList = warehouseApplyService.findAreasByCondition(record);
				
				record = new AreaVo();
				record.setCode(ucUserScope.getCityCode());
				record.setType(String.valueOf(2));
				List<AreaVo> areaList = warehouseApplyService.findAreasByCondition(record);
				
				ucUserScope.setProvinceCode(provinceList.get(0).getName());
				ucUserScope.setCityCode(areaList.get(0).getName());
				
				breedList = ucUserScopeService.getBreedsById(Long.valueOf(userId));
			}
			
			ucUserContact = ucUserContactService.selectUcUserContactsByUserId(Long.valueOf(userId));
		}
		modelMap.addAttribute("ucUser", ucUser);
		modelMap.addAttribute("ucUserScope", ucUserScope);
		modelMap.addAttribute("breedList", breedList);
		modelMap.addAttribute("ucUserContact", ucUserContact);
		return "public/memberDetails";
	}
	
	/**
	 * 根据会员id修改会员
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	@RequestMapping(value="/toUpdateMember")
	public String toUpdateMember(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String userId = request.getParameter("userId").trim();
		UcUser ucUser = memberManageService.findMemberByUserID(userId);
		UcUserScope ucUserScope = null;
		List<UcUserBreed> breedList = null;
		List<UcUserContact> ucUserContact = null;
		ucUserScope = ucUserScopeService.selectUcUserScopeById(Long.valueOf(userId));
		AreaVo record = null;
		if(ucUserScope!=null){
			record = new AreaVo();
			record.setCode(ucUserScope.getProvinceCode());
			record.setType(String.valueOf(1));
			List<AreaVo> provinceList = warehouseApplyService.findAreasByCondition(record);
			
			record = new AreaVo();
			record.setCode(ucUserScope.getCityCode());
			record.setType(String.valueOf(2));
			List<AreaVo> areaList = warehouseApplyService.findAreasByCondition(record);
			
			breedList = ucUserScopeService.getBreedsById(Long.valueOf(userId));
			
			modelMap.addAttribute("province", provinceList.get(0));
			modelMap.addAttribute("city", areaList.get(0));
		}
		
		ucUserContact = ucUserContactService.selectUcUserContactsByUserId(Long.valueOf(userId));
		
		record = new AreaVo();
		record.setType(String.valueOf(1));
		modelMap.addAttribute("provinceCode", warehouseApplyService.findAreasByCondition(record));
		modelMap.addAttribute("ucUser", ucUser);
		modelMap.addAttribute("ucUserScope", ucUserScope);
		modelMap.addAttribute("breedList", breedList);
		modelMap.addAttribute("ucUserContact", ucUserContact);
		
		return "public/memberAmend";
	}
	
	/**
	 * 根据会员名称查询会员信息
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getMemberByUserName")
	public String getMemberByUserName(HttpServletRequest request,ModelMap modelMap){
		String userName = request.getParameter("memberName").trim();
		UcUser ucUser = memberManageService.findMemberByUserName(userName);
		modelMap.addAttribute("ucUser", ucUser);
		return "public/memberDetail";
	}
	
	/**
	 * 根据公司名称查询会员信息
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getMemberByCompanyName")
	public String getMemberByCompanyName(HttpServletRequest request,ModelMap modelMap){
		String companyName = request.getParameter("companyName").trim();
		UcUser ucUser = memberManageService.findMemberByCompanyName(companyName);
		modelMap.addAttribute("ucUser", ucUser);
		return "public/memberDetail";
	}
	
	/**
	 * 根据user_id查询用户信息
	 * @author Calvin.Wangh
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getByUserId")
	public String getByUserId(HttpServletRequest request,ModelMap modelMap){
		String userId = request.getParameter("userId").trim();
		UcUser ucUser = memberManageService.findMemberByUserID(userId);
		modelMap.addAttribute("ucUser", ucUser);
		return "public/memberDetail";
	}
	
	/**
	 * 会员修改
	 * @param memMotifyDto
	 * @return
	 */
	@RequestMapping(value="/modifyMember",method=RequestMethod.POST)
	@ResponseBody
	public ErrorMsg modifyMember(@ModelAttribute MemberMotifyDto memMotifyDto,HttpServletRequest request){
		ErrorMsg errorMsg = new ErrorMsg();
		String msg = memMotifyDto.validate();
		if (!"success".equals(msg)) {
			errorMsg.setMsg(msg);
			return errorMsg;
		}
		//更新人，后台登录用户 added by biran 20150901
		BossUserVo userinfo = this.getUserInfo(request);
		List<UcUser> list = memberManageService.reMemberMobIsExist(
				memMotifyDto.getMemberId(), memMotifyDto.getMobileNo());
		if (null != list&&0!=list.size()) {
			errorMsg.setMsg("该手机号已存在,请重新输入！");
			return errorMsg;
		};
		int modFlag = memberManageService.modifyMember(memMotifyDto,userinfo);
		errorMsg.setMsg(String.valueOf(modFlag));
		/********wms api 修改用户嵌入开始   ldp************/
		if (modFlag == 1) {
			UcUser ucUser = memberManageService.findMemberByUserID(memMotifyDto.getMemberId());
			if (ucUser.getCertifyStatus() == 1 || ucUser.getCertifyStatus() == 2) {
				//add by fanyuna 2015.08.10 增加认证名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String json = WmsApiCommon.ucUserToJsonStr(ucUser,name);
				log.info("wms api update user ---put rabbitmq:" + json);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
			}
		}
		/********wms api 修改用户嵌入结束 ************/
		return errorMsg;
		//"redirect:/searchMemberCondition";
	}
	
	/**
	 * 会员锁定与解锁
	 * @return
	 */
	@RequestMapping(value="/lock")
	@ResponseBody
	public String isLock(HttpServletRequest request){
		String userId = request.getParameter("userId").trim();
		String status = request.getParameter("status").trim();
		//更新人，后台登录用户 added by biran 20150901
		BossUserVo userinfo = this.getUserInfo(request);
		UcUser LockcUser=new UcUser();
		LockcUser.setUserId(Long.valueOf(userId));
		LockcUser.setStatus(Integer.valueOf(status));
		if(userinfo!=null){
			LockcUser.setUpdaterId(userinfo.getId().intValue());
		}
		LockcUser.setUpdateTime(new Date());
		int modFlag = memberManageService.isLock(LockcUser);
		/********wms api 修改用户状态嵌入开始   ldp************/
		if (modFlag == 1) {
			UcUser ucUser = memberManageService.findMemberByUserID(userId);
			if (ucUser.getCertifyStatus() == 1 || ucUser.getCertifyStatus() == 2) {
				//add by fanyuna 2015.08.10 增加认证名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String json = WmsApiCommon.ucUserToJsonStr(ucUser,name);
				log.info("wms api update user status ---put rabbitmq:" + json);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
			}
		}
		/********wms api 修改用户状态嵌入结束 ************/
		return String.valueOf(modFlag);
	}
	
	/**
	 * 密码重置
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/secretReset",method=RequestMethod.POST)
	@ResponseBody
	public String secretReset(HttpServletRequest request){
		String userId = request.getParameter("userId").trim();
		
		//更新人，后台登录用户 added by biran 20150901
		BossUserVo userinfo = this.getUserInfo(request);
		UcUser ucUser=new UcUser();
		ucUser.setUserId(Long.valueOf(userId));
		if(userinfo!=null){
			ucUser.setUpdaterId(userinfo.getId().intValue());
		}
		ucUser.setUpdateTime(new Date());
				
		return String.valueOf(memberManageService.secretReset(ucUser));
	}
	
	/**
	 * 会员删除（逻辑上永久删除）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delMember")
	@ResponseBody
	public String delMember(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId").trim();
		/**
		 * add by by Mr.song 2015.5.12 10:53 非空判断
		 */
		if(StringUtils.isEmpty(userId)){
			return "0";
		}
		
		//更新人，后台登录用户 added by biran 20150901
		BossUserVo userinfo = this.getUserInfo(request);
		UcUser delUser=new UcUser();
		delUser.setUserId(Long.valueOf(userId));
		if(userinfo!=null){
			delUser.setUpdaterId(userinfo.getId().intValue());
		}
		delUser.setUpdateTime(new Date());
				
		int delFlag = memberManageService.deleteMember(delUser);
		if (delFlag > 0) {
			log.info("del member success");;//删除成功
			/********wms api 删除用户嵌入开始   ldp************/
			UcUser ucUser = memberManageService.getUcUserById(Integer.parseInt(userId)); //update by by Mr.song 2015.5.12 10:53 修改以前排除删除状态的逻辑
			if(ucUser==null){
				throw new NullPointerException("empty");  //抛出异常，回滚事务
			}
			if (ucUser.getCertifyStatus() == 1 || ucUser.getCertifyStatus() == 2) {
				//add by fanyuna 2015.08.10 增加认证名称
				String name = ucUserService.getCertifyNameByUserId(ucUser.getUserId());
				String json = WmsApiCommon.ucUserToJsonStr(ucUser,name);
				log.info("wms api del user ---put rabbitmq:" + json);
				RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_UPDATE, json);
			}
			/********wms api 删除用户嵌入结束 ************/
		}else {
			log.info("del member fail ");//删除失败
		}
		return String.valueOf(delFlag);
	}
	
	/**
	 * 查看备注
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/lookRemark")
	@ResponseBody
	public UcUser lookRemark(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId").trim();
		//responseHmtl(response, memberManageService.lookRemark(userId));
		UcUser ucUser = new UcUser();
		ucUser.setRemark(memberManageService.lookRemark(userId));
		return ucUser;
	}
	
	/**
	 * 判断会员名是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/memberNameIsHaved")
	@ResponseBody
	public String memberNameIsHaved(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("param").trim();
		if (null != memberManageService.memberNameIsHaved(userName)) {
			//responseHmtl(response, "该会员名已存在,请重新输入！");//用户已存在
			return "该会员名已存在,请重新输入！";
		};
		//responseHmtl(response, "y");//可以使用！
		return "y";
	}
	
	/**
	 * 判断手机号是否已注册
	 * @return
	 */
	@RequestMapping("/memberMobIsExist")
	@ResponseBody
	public String memberMobIsExist(HttpServletRequest request){
		String mobile = request.getParameter("param").trim();
		List<UcUser> list= ucUserService.findUcUserByMobile(mobile);
		if (null != list&&0!=list.size()) {
			return "该手机号已存在,请重新输入！";
		}
		return "y";
	}
	
	/**
	 * 会员管理
	 * @return
	 */
	@RequestMapping(value="")
	public String memberManage(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		return "public/memberManage";
	}
	
	/**
	 * 获取6位随机密码
	 * @return
	 */
	@RequestMapping(value="/romSecret")
	@ResponseBody
	public String romSecret(){
		return GetInitPassword.get6BitPwd();
	}
	
	@RequestMapping(value="/member_manage")
	public String member(){
		return "public/memberManage";
	}
	
	
	@RequestMapping(value="/renameAccount")
	public String renameAccount(){
		return "public/renameAccount";
	}
	
	
	/**
	 * 响应数据
	 * @param response
	 * @param responseText
	 */
	public void responseHmtl(HttpServletResponse response,String responseText){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(new String(responseText.getBytes("UTF-8"),"ISO-8859-1"));
			pw.close();
		} catch (IOException e) {
			log.error("error is:", e);
		}
		
	}
	
	
	/**
	 * 编码格式转换
	 * @param text
	 * @return
	 */
	public String charsetTransfer(String text){
		String temp = null;
		try {
			temp = new String(text.getBytes("UTF-8"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("transferCharset error is :",e);
		}
		return temp;
	}
	
	
	

	/**
	 * 跳转到选择业务员
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectSalesMan",method=RequestMethod.GET)
	public String getBossUserManager(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		BossUser bossuser = new BossUser();
		String userName = request.getParameter("userName");
		bossuser.setUserName(userName);
		String pageNo = request.getParameter("pageNo");
		Page page = new Page();
		if(pageNo==null||pageNo.equals("")){
			page.setPageNo(1);
		}else{
			page.setPageNo(Integer.parseInt(pageNo));
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", bossuser.getUserName());
		page.setParams(params);
 		List<BossUser> buserlist = bossUserService.getBossUsersByCondition(page);
 		page.setResults(buserlist);
		model.put("page", page);
		
		//链接中参数
		String memberId = request.getParameter("memberId");
		model.put("memberId", memberId);
		String regStartDate = request.getParameter("regStartDate");
		model.put("regStartDate", regStartDate);
		String regEndDate = request.getParameter("regEndDate");
		model.put("regEndDate", regEndDate);
		String ucuserName = request.getParameter("ucuserName");
		model.put("ucuserName", ucuserName);
		String mobileNo = request.getParameter("mobileNo");
		model.put("mobileNo", mobileNo);
		String realName = request.getParameter("realName");
		model.put("realName", realName);
		String salesMan = request.getParameter("salesMan");
		model.put("salesMan", salesMan);
		return "/public/selectSalesMan";
	}

	/**
	 * 给会员绑定到选择业务员
	 * @author biran
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bindSalesMan")
	@ResponseBody
	public String bindSalesMan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String salesManId = request.getParameter("salesManId");//后台业务员ID
		String memberId = request.getParameter("memberId");//会员ID
		UcUser user=new UcUser();
		if(salesManId!=null&&!salesManId.equals("")){
			user.setSalesmanId(Long.valueOf(salesManId));
		}
		if(memberId!=null&&!memberId.equals("")){
			user.setUserId(Long.valueOf(memberId));
		}
		user.setUpdateTime(new Date());
		//更新人，后台登录用户 added by biran 20150901
		BossUserVo userinfo = this.getUserInfo(request);
		if(userinfo!=null){
			user.setUpdaterId(userinfo.getId().intValue());
		}
		String jsonStr = null;
		JsonObject json = new JsonObject();
		Gson gson = new Gson();
		//开始更新
		try {
			ucUserService.updateMemberSalesMan(user);
			json.addProperty("status", "yes");
		} catch (Exception e) {
			log.error("MemberManagerController.bindSalesMan, error is " + e.getMessage());
			json.addProperty("status", "error");
		}
		jsonStr = gson.toJson(json);
		return jsonStr;

	}
	
	/**
	 * @Description: 添加经营信息
	 * @Author: ff
	 * @Date: 2015年10月21日
	 * @return
	 */
	@RequestMapping(value="/addAnyUserDealInfo",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAnyUserDealInfo(@ModelAttribute UcUserScopeDto ucUserScopeDto){
		log.info("UcUserDealInController．addUserDealInfo:" + ucUserScopeDto);
		String result = null;
		String userId = ucUserScopeDto.getUserId();
		if(userId==null||userId.equals("")){
			JsonObject json = new JsonObject();
			json.addProperty("status", "nouserId");
			return json.toString();
		}
		try {
			result = ucUserDealInService.addAnyUserDealInfo(ucUserScopeDto);
		} catch (Exception e) {
			log.error("UcUserDealInController．addUserDealInfo error",e);
		}
		return result;
	}
	
	
	/**
	 * @Description: 根据省份Code获取下面的城市
	 * @Author: ff
	 * @Date: 2015年10月20日
	 * @param provinceCode
	 * @return
	 */
	@RequestMapping(value="/getCity")
	@ResponseBody
	public MessageVo getCity(@RequestParam("provinceCode") String provinceCode){
		MessageVo mvo = new MessageVo();
		if(provinceCode==null&&provinceCode.equals("")){
			mvo.setOk(false);
			mvo.addError("error01", "请选择省份!");
			return mvo;
		}
		List<AreaVo> cityList = new ArrayList<AreaVo>();
		AreaVo record = new AreaVo();
		record.setFirstcode(provinceCode);
		record.setType(String.valueOf(2));
		cityList = warehouseApplyService.findAreasByCondition(record);
		if(cityList.size() <= 0){
			mvo.setOk(false);
			return mvo;
		}
		mvo.setObj(cityList);
		mvo.setOk(true);
		return mvo;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBreeds",method={RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> getBreeds(@RequestParam(value="param",defaultValue="")String param,Model model,HttpServletRequest request){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String topTag = "<span class='red'>";
		String footerTag = "</span>";
		try {
			if(!param.equals("")){
				List<Breed> breeds = ucUserDealInService.getBreeds(param);
				if(null!=breeds && breeds.size()>0){
					for(Breed breed : breeds){
						//获取品种名称 组装html代码 关键字标红
						breed.setBreedCname(breed.getBreedName());
						breed.setBreedName(breed.getBreedName().replace(param, topTag+param+footerTag));
					}
					map.put("ok", true);
					map.put("obj", breeds);
				}else{
					map.put("ok", false);
				}
			}
		} catch (Exception e) {
			map.put("ok", false);
		}
		return map;
	}
	
	@RequestMapping(value="/saveContacterInfo",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveContacterInfo(UcUserContactDto dto){
		String result = null;
		Long userId = dto.getUcUserContact().get(0).getUserId();
		if(userId==null){
			JsonObject json = new JsonObject();
			json.addProperty("status", "nouserId");
			return json.toString();
		}
		try {
			result = ucUserContactService.addContacterByUserId(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
