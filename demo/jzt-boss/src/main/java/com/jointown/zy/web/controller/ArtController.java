package com.jointown.zy.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jointown.zy.common.dto.CmsArticleDto;
import com.jointown.zy.common.service.CmsArticleService;
import com.jointown.zy.common.vo.MessageVo;
/**
 * 文章发布Controller
 * @author Mr.songwei
 * @date 2014年11月25日下午10:32:15
 */
@Controller(value = "ArtController")
public class ArtController {
	
	private static final Logger log = LoggerFactory.getLogger(ArtController.class);
	
	@Autowired
	private CmsArticleService cmsarticleservice;
	
	/**
	 * 跳转到文章发布界面
	 * @author Mr.songwei
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping(value="/cms/art",method=RequestMethod.GET)
	public String artview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/cms/articleForm";
	}
	
	/**
	 * 发布/修改文章
	 * @param article
	 * @param model
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/cms/art",method=RequestMethod.POST)
	public String artpost(HttpServletRequest request,@ModelAttribute CmsArticleDto article,Model model,@RequestParam("artfile") MultipartFile file) throws ServletException, IOException {
		
        System.out.println(file.getName());
        
		MessageVo mvo = new MessageVo();
		//页面数据验证逻辑
		if(article.validate()!=null){
			mvo.setOk(false);
			mvo.setErrorMessages(article.getErrors());
			model.addAttribute("mvo", mvo);
			return "/cms/articleForm";
		}else{
			try{
				//保存文章正文及标题内容
				cmsarticleservice.addArt(article);
	        	mvo.setOk(true);
	        }catch(Exception e){
	        	mvo.setOk(false);
	        	mvo.setErrorMessages(article.getErrors());
				model.addAttribute("mvo", mvo);
	        	log.error("article save faild~");
				return "/cms/articleForm";
	        }
		}
        return "redirect:/welcome";
	}
}
