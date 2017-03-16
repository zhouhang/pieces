package com.pieces.biz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.*;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.MD5Util;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.SeqNoUtil;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("/center")
public class OrderController extends BaseController {
    
	@Autowired
    private HttpSession httpSession;
	
    @Autowired
	private OrderFormService orderFormService;
    
    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;

	@Autowired
	private EnquiryBillsService enquiryBillsService;
    
    @Autowired
    private ShippingAddressService shippingAddressService;
    
    @Autowired
    private AreaService areaService;

	@Autowired
	private PayAccountService payAccountService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountBillService accountBillService;

	@Autowired
	private PayRecordService payRecordService;

	@RequestMapping(value = "/order/md5")
	public String redictOrder(String commodityIds){
		String md5 = EncryptUtil.getSHA1(commodityIds,"UTF-8");
		httpSession.setAttribute(md5,commodityIds);
		return "redirect:/center/order/create?omd5=" + md5;
	}

	@RequestMapping(value = "/order/create")
	@BizLog(type = LogConstant.order, desc = "创建订单页面")
	@SecurityToken(generateToken = true)
	public String orderCreate(HttpServletRequest request,
            HttpServletResponse response,
            ModelMap modelMap,
            String commodityIds,
            Integer currentid, String omd5){
		if (omd5 != null) {
			commodityIds = (String) httpSession.getAttribute(omd5);
		}
		// 判断是否全选 获取询价单商品id 去除无效报价
		List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysService.findByIds(commodityIds);
		double totalPrice = 0.00;
		for(EnquiryCommoditys ec : enquiryCommoditysList){
			ec.setAmount(1);
			totalPrice = totalPrice + ec.getAmount() * ec.getMyPrice();
		}
		
		//获取收货地址
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<ShippingAddressVo>  shippingAddressList = shippingAddressService.findByUser(user.getId());
        ShippingAddressVo shippingAddress = null;
        if(currentid != null){
        	shippingAddress = getCurrentAdd(shippingAddressList,currentid);
        }else{
        	shippingAddress = ValidUtils.listNotBlank(shippingAddressList) ? shippingAddressList.get(0) : null;
        }
        
        //防止重复提交订单
        String token = SeqNoUtil.getRandomNum(5);
        request.getSession().setAttribute(SessionEnum.ORDER_TOKEN.getKey(), token);
        
        modelMap.put("enquiryCommoditysList", enquiryCommoditysList);
        modelMap.put("shippingAddressCurrent", shippingAddress);
        modelMap.put("shippingAddressList", shippingAddressList);
        modelMap.put("commodityIds", commodityIds);
        modelMap.put("totalPrice", totalPrice);
        modelMap.put("token", token);

		//根据用户类型来判断该跳转到那个下单页面并获取代理商代理的用户信息
		if (user.getType()==2) {
			List<UserVo> agentUser = userService.findUserByProxy(user.getId());
			modelMap.put("agentUser",agentUser);
			return "order_agent";
		}
		return "order";
	}
	
	@RequestMapping(value = "/address/add")
	@ResponseBody
	@BizLog(type = LogConstant.order, desc = "添加订单地址")
	public Result addressAdd(@Valid ShippingAddress shippingAddress){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		shippingAddress.setUserId(user.getId());
		shippingAddress.setCreateTime(new Date());

		List<ShippingAddressVo>  shippingAddressList = shippingAddressService.findByUser(user.getId());
		if(shippingAddressList.size()>=10){
			return new Result(false).info("收货地址不能超过10条");
		}

		//修改默认地址
		if(shippingAddress.getIsDefault()!= null && shippingAddress.getIsDefault()){
			for(ShippingAddressVo sav : shippingAddressList){
				if(sav.getIsDefault()!=null && sav.getIsDefault()){
					ShippingAddress sa = new ShippingAddress();
					sa.setId(sav.getId());
					sa.setIsDefault(false);
					shippingAddressService.update(sa);
				}
			}
		}
		//创建新地址
		shippingAddressService.create(shippingAddress);
        return new Result(true).info(shippingAddress.getId()+"");
	}
	
	
	@RequestMapping(value = "/order/save",consumes = MediaType.APPLICATION_JSON_VALUE)
	@BizLog(type = LogConstant.order, desc = "保存订单")
	@ResponseBody
	@SecurityToken(generateToken = true,validateToken=true)
	public Result orderSave(HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody  OrderFormVo orderFormVo){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		if (orderFormVo.getAddrHistoryId() == null) {
			throw new RuntimeException("收货地址不能为空");
		}
		String token=(String)request.getSession().getAttribute(SessionEnum.ORDER_TOKEN.getKey());
		if(token==null||!(token.equals(orderFormVo.getToken()))){
			return new Result(false).data("该订单已经提交！不允许重复提交");
		}
		ShippingAddress sa = shippingAddressService.findById(orderFormVo.getAddrHistoryId());
		ShippingAddressHistory sah = new ShippingAddressHistory();
		BeanUtils.copyProperties(sa, sah);
		sah.setId(null);
		sah.setArea(getFullAdd(sa.getAreaId()));
		sah.setAreaId(sa.getAreaId());
		orderFormVo.setAddress(sah);

		List<OrderCommodity> orderCommoditysList = new ArrayList<OrderCommodity>();
		Double total = 0.0d;
		Double deposit = 0.0D;
		Integer billsId = null; // 询价单id
		for(EnquiryCommoditys enquiryCommoditys:orderFormVo.getCommodityses()){
			EnquiryCommoditys ec=enquiryCommoditysService.findById(enquiryCommoditys.getId());
			OrderCommodity oc = new OrderCommodity();
			oc.setName(ec.getCommodityName());
			oc.setSpec(ec.getSpecs());
			oc.setLevel(ec.getLevel());
			oc.setOriginOf(ec.getOrigin());
			oc.setAmount(enquiryCommoditys.getAmount());
			if (user.getType()==1) {
				oc.setGuidePrice(ec.getMyPrice());
				oc.setPrice(ec.getMyPrice());
			} else {
				oc.setGuidePrice(ec.getMyPrice());
				oc.setPrice(enquiryCommoditys.getMyPrice());
			}
			oc.setSubtotal(oc.getAmount()*oc.getPrice());
			oc.setEnquiryCommodityId(ec.getId());
			oc.setOrderId(null);
			oc.setGuidePrice(ec.getMyPrice());
			orderCommoditysList.add(oc);
			total = total + oc.getSubtotal();
			deposit += oc.getGuidePrice()*oc.getAmount();
			// 询价单id
			billsId = ec.getBillsId();
		}
		// 设置订单过期时间
		orderFormVo.setExpireDate(enquiryBillsService.findById(billsId).getExpireDate());

		orderFormVo.setSum(total);
		orderFormVo.setDeposit(deposit);
		//total = total + orderFormVo.getShippingCosts();
		orderFormVo.setAmountsPayable(total);
		orderFormVo.setCommodities(orderCommoditysList);
		orderFormService.save(orderFormVo, user);
		request.getSession().removeAttribute(SessionEnum.ORDER_TOKEN.getKey());


        return new Result(true).data(orderFormVo.getId());
	}
	
	/**
	 * 获取当前地址
	 */
	private ShippingAddressVo getCurrentAdd(List<ShippingAddressVo>  shippingAddressList,Integer id){
		for(ShippingAddressVo svo : shippingAddressList){
			if(svo.getId().equals(id)){
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
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public String list(Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PageInfo<OrderFormVo> pageInfo = orderFormService.findOrderByUserId(user.getId(),pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
		if (user.getType()==2){
			return "redirect:/center/order/agent";
		}
		else{
			return "order_list";
		}


    }

    /**
     * 用户订单详情
     * @return
     */
    @RequestMapping(value = "/order/detail/{id}", method = RequestMethod.GET)
	@BizLog(type = LogConstant.order, desc = "订单详情")
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		OrderFormVo vo =  orderFormService.findVoById(id);
		//该订单非用户自己订单
		if(!user.getId().equals(vo.getUserId())){
			return "redirect:error/404";
		}

		// 查询用户该订单的付款记录
		// 先查账单 再查付款记录确定用户支付类型
		// 1. 判定用户类型
		// 只查询有效的付款信息
		AccountBill accountBill = accountBillService.findValidBillByOrderID(id,user.getId());
		if (accountBill!=null) {
			modelMap.put("accountBill",accountBill);
		} else {
			// 获取非账单支付的支付信息
			//1 代理商 agentId orderId accountBillId=null
			//2. 普通用户 orderId userId = userId 和agentId == null
			PayRecordVo payRecord = null;
			if (user.getType() == 2) {
				// 代理商
				payRecord = payRecordService.findByOrderForAgent(user.getId(),id);
			} else if (user.getType() ==1) {
				// 终端
				payRecord = payRecordService.findByOrderForUser(user.getId(),id);
			}
			modelMap.put("payRecord",payRecord);
		}
        modelMap.put("orderForm", vo);
        return "order_detail";
    }

    /**
     * 订单提交成功
     * @return
     */
    @RequestMapping(value = "/order/success/{id}", method = RequestMethod.GET)
    public String success(@PathVariable("id")Integer id,ModelMap modelMap) {
		OrderForm order=orderFormService.findVoById(id);
		List<PayAccount> payAccountList = payAccountService.findAll();

		String token = UUID.randomUUID().toString();
		httpSession.setAttribute(SessionEnum.PAY_TOKEN.getKey(),token);
		modelMap.put("token",token);
		modelMap.put("payAccountList",payAccountList);
		modelMap.put("order", order);
		return "order_success";
    }


	/**
	 * 修改订单状态
	 * TODO: 待完善修改状态前需要判断订单当前状态
	 * @param orderId
	 * @param status
     * @return
     */
	@RequestMapping(value = "/order/status", method = RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.order, desc = "修改订单状态")
	public Result changeStatus(Integer orderId, Integer status) {
		return orderFormService.changeOrderStatus(orderId, status);
	}


	/**
	 * 保存订单发票信息(补开发票)
	 * @param orderId
	 * @param invoice
	 * @return
	 */
	@RequestMapping(value = "/order/invoice", method = RequestMethod.POST)
	@ResponseBody
	@BizLog(type = LogConstant.order, desc = "补开订单发票")
	public Result invoice(Integer orderId, OrderInvoice invoice) {
		orderFormService.saveInvoice(orderId, invoice);
		return new Result(true).info("发票信息保存成功");
	}

	/**--代理商订单信息--**/
	/**
	 *代理商订单列表
     * @return
     */
	@RequestMapping(value = "/order/agent", method = RequestMethod.GET)
	@BizLog(type = LogConstant.order, desc = "代理商订单列表")
	public String agent(Integer pageNum, Integer pageSize, ModelMap modelMap) {
		User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		PageInfo<OrderFormVo> pageInfo = orderFormService.findOrderByAgentId(user.getId(),pageNum, pageSize);
		modelMap.put("pageInfo", pageInfo);
		return "order_list";
	}

	/**
	 *
	 * @param id
	 * @param modelMap
     * @return
     */
	@RequestMapping(value = "/order/agent/detail/{id}", method = RequestMethod.GET)
	@BizLog(type = LogConstant.order, desc = "代理商订单详情")
	public String agentDetail(@PathVariable("id")Integer id, ModelMap modelMap) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		OrderFormVo vo =  orderFormService.findVoById(id);
		modelMap.put("orderForm", vo);
		//该订单非代理商代理的订单
		if(!user.getId().equals(vo.getAgentId())){
			return "redirect:error/404";
		}
		return "order_detail";
	}

}
