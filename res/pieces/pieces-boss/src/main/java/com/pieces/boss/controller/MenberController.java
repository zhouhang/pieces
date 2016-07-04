package com.pieces.boss.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value = "/menber")
public class MenberController {
	@Autowired
    private AreaService areaService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/get/userlist")
	public String getUserList(ModelMap model,User user,Integer pageNum,
            Integer pageSize,HttpServletRequest request) {
        if(pageNum==null){
            pageNum = 1;
        }
        if(pageSize==null){
            pageSize=10;
        }
		PageInfo<User> userPage = userService.findUserByVagueCondition(user,pageNum,pageSize);
		
		model.put("userPage", userPage);
		model.put("user", user);
		
		return "customers";
	}
	
	@RequestMapping(value = "/get/user")
	public String getUser(ModelMap model,User user,HttpServletRequest request) {
		List<User> users = userService.findUserByCondition(user);
		
		model.put("user", users.get(0));
		
		return "customers-info";
	}
	
	@RequestMapping(value = "/add/user")
	@ResponseBody
	public String addUser(ModelMap model,User user,HttpServletRequest request) {
		ValidFromVo mv = new ValidFromVo();
		if(user.getPassword()!=null&&!user.getPassword().equals("")){
			userService.addUser(user);
			mv.setStatus("y");
		}else{
			if(getMobileCode(user.getContactMobile(),request)){
				Map codeMap = (Map)request.getSession().getAttribute(user.getContactMobile());
				if(codeMap != null){
					String code = codeMap.get("code").toString();
					if(code!=null&&!code.equals("")){
						user.setPassword(code);
						userService.addUser(user);
						mv.setStatus("y");
					}else{
						mv.setStatus("n");
						mv.setInfo("验证码过期");
					}
				}else{
					mv.setStatus("n");
					mv.setInfo("短信发送失败");
				}
			}else{
				mv.setStatus("n");
				mv.setInfo("短信发送失败");
			}
		}
		Gson gson = new Gson();
		return gson.toJson(mv);
	}
	
	@RequestMapping(value = "/to/add/user")
	public String toAddUser(ModelMap model,HttpServletRequest request) {
		return "customers-add";
	}
	
	@RequestMapping(value = "/to/add/account")
	public String toAddAccount(ModelMap model,String id,HttpServletRequest request) {
		User user = new User();
		user.setId(Integer.parseInt(id));
		List<User> users = userService.findUserByCondition(user);
		Area province = areaService.findById(Integer.parseInt(users.get(0).getProvinceCode()));
		Area city = areaService.findById(Integer.parseInt(users.get(0).getCityCode()));
		Area area = areaService.findById(Integer.parseInt(users.get(0).getCountyCode()));
		model.put("user", users.get(0));
		model.put("province", province);
		model.put("city", city);
		model.put("area", area);
		return "customers-account";
	}
	
	@RequestMapping(value = "/update/account")
	@ResponseBody
	public String updateAccount(ModelMap model,User user,HttpServletRequest request) {
		ValidFromVo mv = new ValidFromVo();
		if(user.getPassword()!=null&&!user.getPassword().equals("")){
			user = userService.getPawAndSaltMd5(user);
			userService.updateUserByCondition(user);
			mv.setStatus("y");
		}else{
			if(getMobileCode(user.getContactMobile(),request)){
				Map codeMap = (Map)request.getSession().getAttribute(user.getContactMobile());
				if(codeMap != null){
					String code = codeMap.get("code").toString();
					if(code!=null&&!code.equals("")){
						user.setPassword(code);
						user = userService.getPawAndSaltMd5(user);
						userService.updateUserByCondition(user);
						mv.setStatus("y");
					}else{
						mv.setStatus("n");
						mv.setInfo("验证码过期");
					}
				}else{
					mv.setStatus("n");
					mv.setInfo("短信发送失败");
				}
			}else{
				mv.setStatus("n");
				mv.setInfo("短信发送失败");
			}
		}
		Gson gson = new Gson();
		return gson.toJson(mv);
	}
	
	@RequestMapping(value="/ifexist/username")
	@ResponseBody
	public String ifExistUserName(Model model,HttpServletRequest request){
		String userName = request.getParameter("param");
		ValidFromVo vo = new ValidFromVo();
		if(userService.ifExistUserName(userName)){
			vo.setStatus("n");
			vo.setInfo("用户名重复");
		}else{
			vo.setStatus("y");
		}
		Gson gson = new Gson();
		return gson.toJson(vo);
	}
	
	@RequestMapping(value="/ifexist/mobile")
	@ResponseBody
	public String ifExistMobile(Model model,HttpServletRequest request){
		String contactMobile = request.getParameter("param");
		ValidFromVo vo = new ValidFromVo();
		if(userService.ifExistMobile(contactMobile)){
			vo.setStatus("n");
			vo.setInfo("手机号重复");
		}else{
			vo.setStatus("y");
		}
		Gson gson = new Gson();
		return gson.toJson(vo);
	}
	
	public boolean getMobileCode(String contactMobile,HttpServletRequest request){
		SendMessage sm = new YPSendMessage();
		if(sm.sendMessage(request, contactMobile)){
			return true;
		}else{
			return false;
		}
	}
}
