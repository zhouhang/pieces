package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Controller
@RequestMapping("order/")
public class OrderController extends BaseController{


    @Autowired
    HttpSession httpSession;

    @Autowired
    OrderRemarkService orderRemarkService;
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
    private ShippingAddressHistoryService shippingAddressHistoryService;
    @Autowired
    private CommoditySearchService commoditySearchService;
    @Autowired
    private AreaService areaService;


    /**
     * 订单列表页面
     * @return
     */
    @RequestMapping("index")
    public String index(OrderFormVo vo, Integer pageSize, Integer pageNum, ModelMap modelMap){
        PageInfo<OrderFormVo> pageInfo = orderFormService.findByParams(vo,pageNum,pageSize);
        modelMap.put("pageInfo",pageInfo);
        modelMap.put("vo",vo);
        modelMap.put("param", vo.serialize());
        return  "order";
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        OrderFormVo vo = orderFormService.findVoById(id);
        List<OrderRemarkVo> remarks = orderRemarkService.findByOrderId(id);
        modelMap.put("vo", vo);
        modelMap.put("remarks", remarks);
        return  "order_detail";
    }

    /**
     * 给订单添加评论
     * @param remark
     * @return
     */
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(OrderRemark remark){
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        remark.setUserId(mem.getId());
        remark.setCreaterTime(new Date());
        orderRemarkService.create(remark);
        return new Result(true).info("添加成功!").data(remark);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public Result changeStatus(Integer orderId, Integer status) {
        return orderFormService.changeOrderStatus(orderId, status);
    }


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
     * 重新下单
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping(value = "anew/{orderId}")
    public String anewOrder(@PathVariable("orderId") Integer orderId,
                            ModelMap model){
        orderModel(null,orderId,model);
        model.put("order_type","重新下单");
        return "order_create";
    }

    /**
     * 修改订单
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{orderId}")
    public String updateOrder(@PathVariable("orderId") Integer orderId,
                              ModelMap model){
        orderModel(null,orderId,model);
        model.put("order_type","修改订单");
        return "order_create";
    }


    /**
     * 创建订单页面
     * @param customerId
     * @return
     */
    @RequestMapping(value = "create/{customerId}")
    public String createOrder(@PathVariable("customerId") Integer customerId,
                              ModelMap model){
        orderModel(customerId,null,model);
        model.put("order_type","创建新订单");
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


        Member member = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        orderFormVo.setCreateMember(member.getId());
        if(orderFormVo.getOrderId()==null){
            orderFormService.create(orderFormVo);
        }else{
            orderFormService.create(orderFormVo,orderFormVo.getOrderId());
        }
        return new Result(true).data(orderFormVo);
    }


    private void orderModel(Integer customerId,Integer orderId,ModelMap model){
        if(orderId!=null){
            //查询订单详情
            com.pieces.dao.model.OrderForm orderForm =  orderFormService.findById(orderId);

            customerId = orderForm.getUserId();

            model.put("origOrderForm",orderForm);

            //该订单填写的收货地址
            ShippingAddressHistory shippingAddressHistory = shippingAddressHistoryService.findById(orderForm.getAddrHistoryId());
            Area area = areaService.findParentsById(shippingAddressHistory.getAreaId());
            shippingAddressHistory.setAreaObj(area);
            model.put("shippingAddressHistory",shippingAddressHistory);

            //改订单商品列表
            List<OrderCommodityVo>  commodityVos= orderCommodityService.findByOrderId(orderId);
            model.put("commodityVos",commodityVos);
        }


        User user = userService.findById(customerId);
        //查询用户最近5条询价记录
        EnquiryRecordVo enquiryRecordVo = new EnquiryRecordVo();
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

    }


}
