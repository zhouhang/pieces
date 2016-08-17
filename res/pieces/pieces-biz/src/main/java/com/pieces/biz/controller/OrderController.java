package com.pieces.biz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pieces.dao.model.User;
import com.pieces.service.enums.RedisEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.OrderCommodityService;
import com.pieces.service.OrderFormService;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.WebUtil;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("/center/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderCommodityService orderCommodityService;

	@Autowired
	private OrderFormService orderFormService;
	
    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;

	@RequestMapping(value = "/create")
	public void orderSave(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            String commodity){
		if(commodity.contains(",")){
			
		}else{
			EnquiryCommoditys enquiryCommoditys = enquiryCommoditysService.findById(Integer.parseInt(commodity));
			//enquiryCommoditys.getUserId()
		}
	}

}
