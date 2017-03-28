package com.pieces.boss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.Member;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.log.annotation.BizLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Logistical;
import com.pieces.dao.model.LogisticalCommodity;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.dao.vo.LogisticalVo;
import com.pieces.dao.vo.OrderCommodityVo;
import com.pieces.tools.utils.Reflection;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: ff 7/19/16. 商品信息
 */
@Controller
@RequestMapping("")
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
    private OrderFormService orderFormService;
    /**
     * 我的物流页面
     * @return
     */
    @RequiresPermissions(value = "logistical:index")
    @RequestMapping(value = "/logistics/index", method = RequestMethod.GET)
    @BizLog(type = LogConstant.logistics, desc = "物流列表页面")
    public String list(LogisticalVo logisticalVo,Integer pageNum, Integer pageSize, ModelMap modelMap) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
        PageInfo<LogisticalVo> pageInfo = logisticalService.findByUser(logisticalVo, pageNum, pageSize);
        modelMap.put("logisticalVo", logisticalVo);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("params", Reflection.serialize(logisticalVo));
        return "logistics";
    }
    
    /**
     * 物流详情页面
     * @return
     */
    @RequiresPermissions(value = "logistical:info")
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
        return "logistics_detail";
    }


    /**
     * 物流保存
     * @return
     */
    @RequestMapping(value = "/logistics/create", method = RequestMethod.POST)
    @BizLog(type = LogConstant.logistics, desc = "保存物流")
    @ResponseBody
    public Result create(Logistical logistic) {
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        logistic.setMemId(mem.getId());
        logisticalService.save(logistic);
        // 保存物流信息同时改变订单状态为已发货
        orderFormService.changeOrderStatus(logistic.getOrderId(), OrderEnum.SHIPPED.getValue());
        return  new Result(true);
    }
}
