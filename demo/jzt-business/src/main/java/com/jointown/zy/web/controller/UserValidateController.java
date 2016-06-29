package com.jointown.zy.web.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.s;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer.Req;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.shiro.ShiroUtils;
import com.jointown.zy.common.vo.UcUserVo;

@Controller
public class UserValidateController {

	@Autowired
	private UcUserService userService;

	@RequestMapping(value = "/validate")
	public String validate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String url = request.getParameter("service");
		return "redirect:"+url;
	}
	
	@RequestMapping(value = "/popValidate")
	public String popValidate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String url = request.getParameter("service");
		return "redirect:"+url;
	}
}
