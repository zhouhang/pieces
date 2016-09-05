package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


/**
 * Created by wangbin on 2016/8/29.
 */
@Controller
@RequestMapping("/center/pay")
public class PayController extends BaseController{

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private PayAccountService payAccountService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private PayRecordService payRecordService;
    @Autowired
    private OrderCommodityService orderCommodityService;

    @RequestMapping(value = "/go/{orderId}")
    public String go(ModelMap modelMap,
                     @PathVariable("orderId")Integer orderId){

        OrderForm orderForm = orderFormService.findById(orderId);
        modelMap.put("orderForm",orderForm);
        String token = UUID.randomUUID().toString();
        httpSession.setAttribute(SessionEnum.PAY_TOKEN.getKey(),token);
        modelMap.put("token",token);
        List<PayAccount> payAccountList = payAccountService.findAll();
        modelMap.put("payAccountList",payAccountList);
        return "payment";
    }

    @RequestMapping(value = "/success")
    public String success(){
        return "payment_result";
    }


    /**
     * 创建支付记录
     * @param payRecordVo
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Result create(PayRecordVo payRecordVo,
                         String token,
                         String[] img){

        Object sessionToken =  httpSession.getAttribute(SessionEnum.PAY_TOKEN.getKey());

        if(StringUtils.isBlank(token)||sessionToken==null||!token.equals(sessionToken.toString())){
            return new Result(false).info("不能重复提交支付信息!");
        }

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        payRecordService.create(payRecordVo,img,user.getId());
        //清空令牌
        httpSession.setAttribute(SessionEnum.PAY_TOKEN.getKey(),null);
        return new Result(true).info("支付信息提交成功!");
    }

    /**
     * 支付记录
     * @param modelMap
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/record")
    public String record(ModelMap modelMap,
                         Integer pageSize,
                         Integer pageNum){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<PayRecordVo> recordPage = payRecordService.findByNormalRecord(pageNum,pageSize);
        for(PayRecordVo payRecordVo : recordPage.getList()){
            assignCommodity(payRecordVo);
        }
        modelMap.put("recordPage",recordPage);
        return "pay_record";
    }


    @RequestMapping(value = "/details/{id}")
    public String details(ModelMap modelMap,
                          @PathVariable("id")Integer id){
        PayRecordVo payRecordVo =  payRecordService.findVoById(id);
        modelMap.put("payRecordVo",payRecordVo);
        return "pay_detail";
    }


    private void assignCommodity(PayRecordVo payRecordVo){
        String orderCode =  payRecordVo.getOrderCode();
        OrderForm orderForm = orderFormService.findByOrderCode(orderCode);
        Integer orderId = orderForm.getId();
        List<OrderCommodity>  commodityList = orderCommodityService.getCommodityByOrderId(orderId);
        payRecordVo.setCommodities(commodityList);
    }



}
