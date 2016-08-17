package com.pieces.biz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.OrderFormVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.OrderCommodityService;
import com.pieces.service.OrderFormService;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.enums.RedisEnum;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("/center/order")
public class OrderController extends BaseController {

    @Autowired
    private HttpSession httpSession;

	@Autowired
	private OrderCommodityService orderCommodityService;

	@Autowired
	private OrderFormService orderFormService;
	
    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;
    @Autowired
    private ShippingAddressService shippingAddressService;

	@RequestMapping(value = "/create")
	public String orderSave(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            String commodity){
		List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysService.findByIds(commodity);
		
		//获取收货地址
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<ShippingAddressVo>  shippingAddressList = shippingAddressService.findByUser(user.getId());
        
        modelMap.put("enquiryCommoditysList", enquiryCommoditysList);
        modelMap.put("shippingAddressList", shippingAddressList);
        return "order";
	}
	
    /**
     * 用户订单页面
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PageInfo<OrderFormVo> pageInfo = orderFormService.findOrderByUserId(user.getId(),pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        return "order_list";
    }


    /**
     * 用户订单详情
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id) {

        return "order_detail";
    }

    /**
     * 订单提交成功
     * @return
     */
    @RequestMapping(value = "success/{id}", method = RequestMethod.GET)
    public String success(@PathVariable("id")Integer id) {
        return "order_success";
    }

}
