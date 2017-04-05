package com.pieces.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.model.*;
import com.pieces.service.impl.KdApiService;
import com.pieces.tools.log.annotation.BizLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.dao.vo.LogisticalVo;
import com.pieces.dao.vo.OrderCommodityVo;
import com.pieces.service.LogisticalCommodityService;
import com.pieces.service.LogisticalService;
import com.pieces.service.OrderCommodityService;
import com.pieces.service.ShippingAddressHistoryService;
import com.pieces.service.enums.RedisEnum;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("/center")
public class LogisticsController extends BaseController {
    
	@Autowired
    private HttpSession httpSession;
	
    @Autowired
	private LogisticalService logisticalService;
    
    @Autowired
    private LogisticalCommodityService logisticalCommodityService;
    
    @Autowired
    private ShippingAddressHistoryService shippingAddressHistoryService;
    
    @Autowired
    private OrderCommodityService orderCommodityService;

    @Autowired
    private KdApiService kdApiService;


    /**
     * 我的物流页面
     * @return
     */
    @RequestMapping(value = "/logistics/list", method = RequestMethod.GET)
    @BizLog(type = LogConstant.logistics, desc = "物流列表")
    public String list(Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        LogisticalVo logisticalVo = new LogisticalVo();
        logisticalVo.setUserId(user.getId());
        PageInfo<LogisticalVo> pageInfo = logisticalService.findByUser(logisticalVo, pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        return "user_logistics";
    }
    
    /**
     * 物流详情页面
     * @return
     */
    @RequestMapping(value = "/logistics/{id}", method = RequestMethod.GET)
    @BizLog(type = LogConstant.logistics, desc = "物流详情页面")
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        LogisticalVo logisticalVo = new LogisticalVo();
        logisticalVo.setId(id);
        LogisticalVo logistic = logisticalService.findByUser(logisticalVo, 0, 10).getList().get(0);
        LogisticalCommodityVo logisticalCommodityVo = new LogisticalCommodityVo();
        List<LogisticalCommodityVo> list = logisticalCommodityService.findCommoditys(logisticalCommodityVo);
        ShippingAddressHistory addr = shippingAddressHistoryService.findById(logistic.getAddr());
        modelMap.put("logistic", logistic);
        modelMap.put("logisticCommoditys", list);
        modelMap.put("addr", addr);
        return "user_logistics_detail";
    }

    /**
     * 物流保存
     * @return
     */
    @RequestMapping(value = "/logistics/create", method = RequestMethod.GET)
    @BizLog(type = LogConstant.logistics, desc = "物流保存")
    public void create(Logistical logistic,String logisticalCommodityIds,ModelMap modelMap) {
    	List<OrderCommodityVo> orderCommodityVo = orderCommodityService.findByOrderId(logistic.getOrderId());
    	String[] logisticalCommodityId = logisticalCommodityIds.split(",");
    	logistic.setTotal(orderCommodityVo.size());
    	logistic.setShipNumber(logisticalCommodityId.length);
    	logisticalService.create(logistic);
    	for(OrderCommodityVo ocv : orderCommodityVo){
    		if(logisticalCommodityIds.contains(ocv.getId().toString())){
    			LogisticalCommodity logisticalCommodity = new LogisticalCommodity();
        		logisticalCommodity.setLogisticalId(logistic.getId());
        		logisticalCommodity.setOrderCommodityId(ocv.getId());
        		logisticalCommodity.setAmount(ocv.getAmount());
        		logisticalCommodityService.create(logisticalCommodity);
    		}
    	}
    }


    /***-代理商物流-***/

    /**
     * 代理商物流页面
     * @return
     */
    @RequestMapping(value = "/logistics/agent/list", method = RequestMethod.GET)
    @BizLog(type = LogConstant.logistics, desc = "代理商物流列表")
    public String agentList(Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        LogisticalVo logisticalVo = new LogisticalVo();
        logisticalVo.setAgentId(user.getId());
        PageInfo<LogisticalVo> pageInfo = logisticalService.findByUser(logisticalVo, pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        return "user_logistics";
    }






}
