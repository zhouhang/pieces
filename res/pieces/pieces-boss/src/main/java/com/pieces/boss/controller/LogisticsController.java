package com.pieces.boss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.dao.vo.LogisticalVo;
import com.pieces.service.LogisticalCommodityService;
import com.pieces.service.LogisticalService;
import com.pieces.service.ShippingAddressHistoryService;
import com.pieces.tools.utils.Reflection;

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


    /**
     * 我的物流页面
     * @return
     */
    @RequestMapping(value = "/logistics/index", method = RequestMethod.GET)
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
    @RequestMapping(value = "/logistics/{id}", method = RequestMethod.GET)
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



}
