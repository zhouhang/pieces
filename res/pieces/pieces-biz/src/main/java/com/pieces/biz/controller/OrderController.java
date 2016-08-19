package com.pieces.biz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.AreaService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.OrderCommodityService;
import com.pieces.service.OrderFormService;
import com.pieces.service.OrderInvoiceService;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.ValidUtils;

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
    @Autowired
    private AreaService areaService;
    @Autowired
    private OrderInvoiceService orderInvoiceService;

	@RequestMapping(value = "/create")
	public String orderCreate(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            String commodityIds,
            Integer currentid){
		List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysService.findByIds(commodityIds);
		double totalPrice = 0.00;
		for(EnquiryCommoditys ec : enquiryCommoditysList){
			totalPrice = totalPrice + ec.getAmount() * ec.getMyPrice();
		}
		
		//获取收货地址
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<ShippingAddressVo>  shippingAddressList = shippingAddressService.findByUser(user.getId());
        //获取详细地址
        shippingAddressList = setFullAdd(shippingAddressList);
        ShippingAddressVo shippingAddress = null;
        if(currentid != null){
        	shippingAddress = getCurrentAdd(shippingAddressList,currentid);
        }else{
        	shippingAddress = ValidUtils.listNotBlank(shippingAddressList) ? shippingAddressList.get(0) : null;
        }
        
        modelMap.put("enquiryCommoditysList", enquiryCommoditysList);
        modelMap.put("shippingAddressCurrent", shippingAddress);
        modelMap.put("shippingAddressList", shippingAddressList);
        modelMap.put("commodityIds", commodityIds);
        modelMap.put("totalPrice", totalPrice);
        return "order";
	}
	
	@RequestMapping(value = "/addAdd")
	public String addAdd(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            ShippingAddress shippingAddress,
            String commodityIds){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		shippingAddress.setUserId(user.getId());
		shippingAddress.setCreateTime(new Date());
		shippingAddressService.create(shippingAddress);
        return "redirect:/center/order/create?commodityIds="+commodityIds;
	}
	
	
	@RequestMapping(value = "/save")
	public String orderSave(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            OrderInvoice orderInvoice,
            OrderFormVo orderFormVo){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		orderFormVo.setInvoice(orderInvoice);
		ShippingAddress sa = shippingAddressService.findById(orderFormVo.getAddrHistoryId());
		ShippingAddressHistory sah = new ShippingAddressHistory();
		BeanUtils.copyProperties(sa, sah);
		sah.setId(null);
		sah.setArea(getFullAdd(sa.getAreaId()));
		orderFormVo.setAddress(sah);
		List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysService.findByIds(orderFormVo.getCommodityIds());
		List<OrderCommodity> orderCommoditysList = new ArrayList<OrderCommodity>();
		Double total = 0.0d;
		for(EnquiryCommoditys ec : enquiryCommoditysList){
			OrderCommodity oc = new OrderCommodity();
			oc.setName(ec.getCommodityName());
			oc.setSpec(ec.getSpecs());
			oc.setLevel(ec.getLevel());
			oc.setOriginOf(ec.getOrigin());
			oc.setExpectDate(ec.getExpectDate());
			oc.setAmount(ec.getAmount());
			oc.setPrice(ec.getMyPrice().floatValue());
			oc.setSubtotal(oc.getAmount()*oc.getPrice());
			oc.setEnquiryCommodityId(ec.getId());
			oc.setOrderId(null);
			orderCommoditysList.add(oc);
			total = total + oc.getSubtotal();
		}
		orderFormVo.setSum(total);
		total = total + orderFormVo.getShippingCosts();
		orderFormVo.setAmountsPayable(total);
		orderFormVo.setCommodities(orderCommoditysList);
		orderFormService.save(orderFormVo, user);
		modelMap.put("total", total);
		modelMap.put("orderId", orderFormVo.getCode());
        return "order_success";
	}
	
	/**
	 * 获取当前地址
	 */
	private ShippingAddressVo getCurrentAdd(List<ShippingAddressVo>  shippingAddressList,Integer id){
		for(ShippingAddressVo svo : shippingAddressList){
			if(svo.getId() == id){
				return svo;
			}
        }
		return null;
	}
	
	/**
	 * 设置地址全称
	 */
	private List<ShippingAddressVo> setFullAdd(List<ShippingAddressVo>  shippingAddressList){
		for(ShippingAddressVo svo : shippingAddressList){
			svo.setFullAdd(getFullAdd(svo.getAreaId()) + svo.getDetail());
        }
		return shippingAddressList;
	}
	
	/**
	 * 获取地址全称
	 */
	private String getFullAdd(Integer areaId){
		Area area = areaService.findParentsById(areaId);
		return area.getProvince() + area.getCity() + area.getAreaname();
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
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("orderForm", vo);
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
