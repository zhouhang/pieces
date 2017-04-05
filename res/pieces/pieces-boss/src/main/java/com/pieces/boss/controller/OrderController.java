package com.pieces.boss.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.WebUtil;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private LogisticalService logisticalService;


    /**
     * 订单列表页面
     * @return
     */
    @RequiresPermissions(value = "order:index")
    @RequestMapping("index")
    @BizLog(type = LogConstant.order, desc = "订单列表页面")
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
    @RequiresPermissions(value = "order:info")
    @RequestMapping("detail/{id}")
    @BizLog(type = LogConstant.order, desc = "订单详情")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        OrderFormVo vo = orderFormService.findVoById(id);
        List<OrderRemarkVo> remarks = orderRemarkService.findByOrderId(id);
        modelMap.put("vo", vo);
        modelMap.put("remarks", remarks);
        modelMap.put("logistical",logisticalService.findByOrderId(id));

        return  "order_detail";
    }

    /**
     * 给订单添加评论
     * @param remark
     * @return
     */
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.order, desc = "订单添加评论")
    public Result addComment(@Valid OrderRemark remark){
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
    @BizLog(type = LogConstant.order, desc = "修改订单状态")
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
    @RequiresPermissions(value = "order:add")
    @RequestMapping(value = "customer")
    @BizLog(type = LogConstant.order, desc = "新建订单客户选择页面")
    public String customerOrderIndex(Integer pageNum,
                                     Integer pageSize,
                                     ModelMap model,
                                     UserVo userVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<UserVo> customerPage =  userService.findProxyUser(userVo,pageNum,pageSize);
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
    @RequiresPermissions(value = "order:edit")
    @RequestMapping(value = "anew/{orderId}")
    @BizLog(type = LogConstant.order, desc = "重新下单页面")
    @SecurityToken(generateToken = true)
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
    @RequiresPermissions(value = "order:edit")
    @RequestMapping(value = "edit/{orderId}")
    @BizLog(type = LogConstant.order, desc = "修改订单页面")
    @SecurityToken(generateToken = true)
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
    @RequiresPermissions(value = "order:add")
    @RequestMapping(value = "create/{customerId}")
    @BizLog(type = LogConstant.order, desc = "创建订单页面")
    @SecurityToken(generateToken = true)
    public String createOrder(@PathVariable("customerId") Integer customerId, Integer agentId,
                              ModelMap model){
        orderModel(customerId,null,model);
        model.put("agentId", agentId);
        // 修改和重新下单时 要带上代理商ID
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
        //附加当前品种的最近成交价格
        //1. 查询当前用户所有成交品种的最近成交价格Map
        //2. 循环给查询结果赋值

        WebUtil.print(response,new Result(true).data(commodityDocList));
    }

    /**
     * 自动联想 会带出商品的最近成交价格（先用es查询出品种，然后查询当前用户这些商品最近成交价格）
     * @param commodityName
     */
    @RequestMapping(value = "/commodity/auto")
    @ResponseBody
    public Result inputCommodityAuto(String commodityName,Integer userId){
        List<CommodityDoc> commodityDocList = commoditySearchService.findByCommodityName(commodityName);
        List<CommodityVo> commodityVos=new ArrayList<CommodityVo>();
        List<Integer> idList=new ArrayList<Integer>();
        String ids;
        if(commodityDocList.size()!=0){
            for (CommodityDoc commodityDoc:commodityDocList){
                idList.add(commodityDoc.getId());
                commodityVos.add(commoditySearchService.doc2Vo(commodityDoc));
            }
        }
        if(idList.size()!=0){
            ids=StringUtils.join(idList,",");
        }else{
            ids=null;
        }
        List<CommodityVo> pInfo=commodityService.searchOrderByIds(userId, ids);
        if(pInfo.size()!=0){
            Map<Integer,String> idPrice=new HashMap<Integer,String>();
            for (CommodityVo commodityVo:pInfo){
                idPrice.put(commodityVo.getId(),commodityVo.getOrderPrice());
            }
            for (CommodityVo commodityVo:commodityVos){
                String price=idPrice.get(commodityVo.getId());
                if(price!=null)
                    commodityVo.setOrderPrice(price);
                }
        }



        PageHelper.startPage(1, 20);
        PageInfo pageInfo = new PageInfo(commodityVos);


        //PageInfo<CommodityVo> pageInfo = commodityService.searchForOrder(userId,commodityName,1,20);
        return new Result(true).data(pageInfo);
    }

    /**
     * 提交订单
     */
    @RequiresPermissions(value = {"order:add","order:edit"},logical = Logical.OR)
    @RequestMapping(value = "submit")
    @ResponseBody
    @BizLog(type = LogConstant.order, desc = "提交订单")
    @SecurityToken(validateToken=true)
    public Result save(@Valid @RequestBody OrderFormVo orderFormVo){
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

        if (orderFormVo.getAgentId() != null) {
            //计算保证金 销售价X 数量加运费
            BigDecimal sumD = new BigDecimal(0);
            for(OrderCommodity commodity : commodities ){
                BigDecimal total= new BigDecimal(commodity.getAmount()).multiply(new BigDecimal(commodity.getGuidePrice()));
                sumD = sumD.add(total);
            }
            //保证金总额
            BigDecimal deposit = new BigDecimal(orderFormVo.getShippingCosts()).add(sumD);
            orderFormVo.setDeposit(deposit.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        Member member = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        orderFormVo.setCreateMember(member.getId());
        if(orderFormVo.getOrderId()==null){
            orderFormService.create(orderFormVo);
        }else{
            orderFormService.create(orderFormVo,orderFormVo.getOrderId());
        }
        return new Result(true).data(orderFormVo).info("订单提交成功!");
    }


    private void orderModel(Integer customerId,Integer orderId,ModelMap model){
        if(orderId!=null){
            //查询订单详情
            OrderForm orderForm =  orderFormService.findById(orderId);

            customerId = orderForm.getUserId();

            model.put("origOrderForm",orderForm);
            //如果有代理商放入代理商id
            model.put("agentId", orderForm.getAgentId());

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
        PageInfo<EnquiryBillsVo> billsPageInfo =  enquiryBillsService.findByPage(1,5,enquiryRecordVo);

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

    //解析上传的报价excel
    @RequestMapping(value = "/importExcel")
    @ResponseBody
    private Result importExcel(@RequestParam("file") MultipartFile file){
        List<EnquiryCommoditys> enquiryCommodityses= null;
        try {
            enquiryCommodityses = ExcelParse.importQuoteInfo(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return new Result(true).data(enquiryCommodityses);
    }

    /**
     *
     */
    @RequestMapping(value = "/download/{id}")
    public void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") Integer id){
        orderFormService.exportOrderExcel(response, request, id);
    }

}
