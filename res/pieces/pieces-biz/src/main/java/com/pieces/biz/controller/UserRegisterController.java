package com.pieces.biz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.utils.MobileCodeUtil;
import com.pieces.service.utils.ValidationCodeUtil;

@Controller(value = "userRegisterController")
@RequestMapping
public class UserRegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register")
	public String register(Model model,HttpServletRequest request) {
		//1.获取页面参数
		//2.校验页面参数，空否，取值规则，
		//3.校验图片验证码，手机验证码
		//4.用户名，手机号是否重复，
		//5.生成盐，加密密码
		//6.保存数据库
		User tu = new User();
		tu.setUserName("ycg");
		tu.setPassword("111111");
		tu.setCompanyFullName("药材购");
		tu.setContactName("周总");
		tu.setContactMobile("13577778888");
		tu.setStatus(0);
		tu.setProvinceCode("111");
		tu.setCityCode("23");
		tu.setCountyCode("33");
		tu.setCreateTime(new Date());
		try {
			if (!ValidationCodeUtil.isValidationCodeMatched("aaaa")) {
				System.out.println("验证码错误");
			}
			Map<String, Object> mobileCodeMap = (Map<String, Object>) request
					.getSession().getAttribute(MobileCodeUtil.MOBILE_CODE);
			String verityResult = MobileCodeUtil.verityMobileCode(mobileCodeMap,"567890",tu.getContactMobile());
			if (!"y".equals(verityResult)) {
				System.out.println("手机验证码错误");
			}
			User vUser = new User();
			vUser.setUserName(tu.getUserName());
			List<User> users = userService.findUserByCondition(vUser);
			if(users!=null||users.size()!=0){
				System.out.println("用户名重复");
			}
			vUser.setUserName(null);
			vUser.setContactMobile(tu.getContactMobile());
			users = userService.findUserByCondition(vUser);
			if(users!=null||users.size()!=0){
				System.out.println("手机号重复");
			}
			int result = userService.addUser(tu);
			if(result > 0){
				System.out.println("成功添加会员");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "public/login";
	}
}
