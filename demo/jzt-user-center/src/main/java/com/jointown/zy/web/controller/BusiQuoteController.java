package com.jointown.zy.web.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiQuoteDto;
import com.jointown.zy.common.enums.BusiQuoteStateEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiQuoteService;
import com.jointown.zy.common.util.BeanToMapUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.vo.BusiQuoteVo;

/**
* 项目名称：jzt-user-center  
* 类名称：BusiQuoteController  
* 类描述： 报价操作
* 创建人：shangcuijuan 
* 创建时间：2015-10-12 下午2:03:34    
* @version v1.0  
*
 */
@Controller	
@RequestMapping(value = "/Quote")
public class BusiQuoteController extends UserBaseController{
	private static final Logger log = LoggerFactory.getLogger(BusiQuoteController.class);

	@Autowired
	private BusiQuoteService busiQuoteService;
	//我的报价查询
		@RequestMapping(value="/QuoteList")
		public String myQuoteList(HttpServletRequest request,@ModelAttribute("QuoteForm") BusiQuoteDto busiQuoteDto, ModelMap model) throws Exception {
			log.info("BusiQuoteController.myQuoteList controller");
			
			if(busiQuoteDto==null)
			{
				busiQuoteDto=new BusiQuoteDto();
			}
			
			UcUserVo user = getUserInfo(request);
            busiQuoteDto.setQuoter(user.getUserName());
            
			Page<BusiQuoteVo> page = new Page<BusiQuoteVo>();
			page.setPageNo(busiQuoteDto.getPageNo());
			page.setPageSize(ConfigConstant.USER_CENTER_PAGE_SIZE);
			page.setParams(BeanToMapUtil.getOriginalParameters(busiQuoteDto));

			List<BusiQuoteVo> busiQuotes = busiQuoteService.selectQuoteListByCondition(page);
			page.setResults(busiQuotes);

			String salename=SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name");
			String salephone=SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone");
					//
			model.addAttribute("busiQuoteDto",busiQuoteDto);
			model.addAttribute("stateMap",BusiQuoteStateEnum.toMap());
			model.addAttribute("page",page);
			model.addAttribute("userinfo",user);
			model.addAttribute("salename",salename);
			model.addAttribute("salephone",salephone);
			return "business/busiQuoteList";
		}
}
