package com.jointown.zy.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 该controller用来控制CmsCategory的增删改查
 * @author ChengChang
 * @version1.0
 * 2014/11/26
 */
@Controller(value="cmsCategoryController")
public class CmsCategoryController {
	private static final Logger LOG = LoggerFactory.getLogger(CmsCategoryController.class);
	
	@RequestMapping(value="/ftl/cmsCategory/queryCmsCategory" ,method=RequestMethod.GET)
	public String queryCmsCategory(){
		return "/ftl/cmsCategory/queryCmsCategory";
	}
	
}
