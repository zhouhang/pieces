package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pieces.dao.model.TestUser;
import com.pieces.service.TestUserService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.vo.TestUserVo;

import redis.clients.jedis.ShardedJedis;

@Controller(value = "userLoginController")
@RequestMapping
public class UserLoginController{
	
	@Autowired
	private TestUserService testUserService;
	
    public TestUserVo getUserInfo(HttpServletRequest request){
    	
    	return null;
    }
    
	@RequestMapping(value = "/logout")
	public String logout(ModelMap model) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	@RequestMapping(value = "/login")
	public String welcome(Model model,HttpServletRequest re) {

		
		return "public/login";
	}
	
	@RequestMapping(value = "/testUser")
	public String testUser(Model model,HttpServletRequest re) {
		TestUser tu = new TestUser();
		// 页面数据验证逻辑
		try {
			// 登陆验证
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("ff","111");
			subject.login(token);
			tu.setUserName("ff");
			tu.setPassword("111");
			SecurityUtils.getSubject().getSession()
					.setAttribute(RedisEnum.USER_SESSION_BOSS.getValue(), tu);
			//查询菜单权限信息
			TestUser tu1 = (TestUser) SecurityUtils.getSubject().getSession()
			.getAttribute(RedisEnum.USER_SESSION_BOSS.getValue());
			System.out.println("-------------"+tu1.getPassword() + "---------------" + tu1.getPassword());
		} catch (Exception e) {
		}
		//TestUser tu = testUserService.getTestUserByUserName("ff");
		
		//System.out.println(tu);
		return "public/login";
	}
	
	@RequestMapping(value = "/testRedis")
	public String testRedis(Model model,HttpServletRequest re) {
		
		ApplicationContext ac =WebApplicationContextUtils.getRequiredWebApplicationContext(re.getServletContext()); 
		RedisManager rm =(RedisManager)ac.getBean(RedisManager.class); 
		
		
		ShardedJedis jedis = rm.getJedisPool().getResource();
		jedis.sadd("user","liuling");  
		jedis.sadd("user","xinxin");  
		jedis.sadd("user","ling");  
		jedis.sadd("user","zhangxinxin");
		jedis.sadd("user","who");  
		//移除noname  
		jedis.srem("user","who");  
		System.out.println(jedis.smembers("user"));//获取所有加入的value  
		System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
		System.out.println(jedis.srandmember("user"));  
		System.out.println(jedis.scard("user"));//返回集合的元素个数  
		return "public/login";
	}

}
