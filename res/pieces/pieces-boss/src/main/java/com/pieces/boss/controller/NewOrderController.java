package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangbin on 2016/8/17.
 */
@Controller
@RequestMapping("order")
public class NewOrderController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderCommodityService orderCommodityService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private CommoditySearchService commoditySearchService;
    /**
     * 新建订单客户列表
     * @param pageNum
     * @param pageSize
     * @param model
     * @param userVo
     * @return
     */
    @RequestMapping(value = "customer")
    public String customerOrderIndex(Integer pageNum,
                                     Integer pageSize,
                                     ModelMap model,
                                     UserVo userVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<User> customerPage =  userService.findByCondition(userVo,pageNum,pageSize);
        model.put("customerParams", Reflection.serialize(userVo));
        model.put("customerPage",customerPage);
        return "order_customers";
    }

    /**
     * 创建订单页面
     * @param customerId
     * @return
     */
    @RequestMapping(value = "create/{customerId}")
    public String createOrder(@PathVariable("customerId") Integer customerId,
                              ModelMap model){
        User user = userService.findById(customerId);
        //查询用户最近5条询价记录
        EnquiryRecordVo  enquiryRecordVo = new EnquiryRecordVo();
        enquiryRecordVo.setUserId(user.getId());
        enquiryRecordVo.setStatus(1);
        PageInfo<EnquiryBills> billsPageInfo =  enquiryBillsService.findByPage(1,5,enquiryRecordVo);

        //订单form
        PageInfo<OrderFormVo>  orderFormVoPageInfo = orderFormService.findOrderByUserId(customerId,1,1);
        List<OrderFormVo> orderList = orderFormVoPageInfo.getList();
        if(!orderList.isEmpty()){
            OrderFormVo orderFormVo = orderList.get(0);
            List<OrderCommodityVo> orderCommodityList =  orderCommodityService.findByOrderId(orderFormVo.getId());
            orderFormVo.setCommodityVos(orderCommodityList);
            model.put("orderFormVo", orderFormVo);
        }
        //收货地址
        List<ShippingAddressVo> shippingAddressList =  shippingAddressService.findByUser(user.getId());
        if(!shippingAddressList.isEmpty()){
            model.put("shippingAddressList", shippingAddressList);
        }

        model.put("billsPage",billsPageInfo);
        model.put("user",user);
        return "order_create";
    }

    /**
     * 自动联想
     * @param request
     * @param response
     * @param commodityName
     */
    @RequestMapping(value = "auto")
    public void inputAuto(HttpServletRequest request,
                          HttpServletResponse response,
                          String commodityName){
        List<CommodityDoc> commodityDocList = commoditySearchService.findByCommodityName(commodityName);
        WebUtil.print(response,new Result(true).data(commodityDocList));
    }

    /**
     * 提交订单
     */
    @RequestMapping(value = "submit")
    @ResponseBody
    public Result save(@RequestBody OrderFormVo orderFormVo){
        List<OrderCommodity> commodities = orderFormVo.getCommodities();
        //计算商品金额
        BigDecimal sum = new BigDecimal(0);
        for(OrderCommodity commodity : commodities ){
            BigDecimal total= new BigDecimal(commodity.getAmount()).multiply(new BigDecimal(commodity.getPrice()));
            sum = sum.add(total);
        }
        orderFormVo.setSum(sum.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

        //计算应付总额
        BigDecimal payable = new BigDecimal(orderFormVo.getShippingCosts()).add(sum);
        orderFormVo.setAmountsPayable(payable.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());

        orderFormService.create(orderFormVo);

        return new Result(true).data(orderFormVo);
    }

}
