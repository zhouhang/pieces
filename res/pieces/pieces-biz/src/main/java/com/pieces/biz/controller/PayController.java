package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.PayTypeEnum;
import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.service.impl.SmsService;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.SpringUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


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
    @Autowired
    private AccountBillService accountBillService;
    @Autowired
    private SmsService smsService;

    @Autowired
    private PaymentService paymentService;

    /**
     * 订单支付
     * @param modelMap
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/go/{orderId}")
    @BizLog(type = LogConstant.pay, desc = "订单支付界面")
    @SecurityToken(generateToken = true)
    public String go(ModelMap modelMap,
                     @PathVariable("orderId")Integer orderId){

        OrderFormVo orderForm = orderFormService.findVoById(orderId);
        modelMap.put("orderForm",orderForm);
        String token = UUID.randomUUID().toString();
        httpSession.setAttribute(SessionEnum.PAY_TOKEN.getKey(),token);
        modelMap.put("token",token);
        List<PayAccount> payAccountList = payAccountService.findAll();
        modelMap.put("payAccountList",payAccountList);
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user.getType()==1){
            return "payment";
        }else{
            return "payment_agent";
        }

    }

    /**
     * 支付成功
     * @param modelMap
     * @param state
     * @return
     */
    @RequestMapping(value = "/success")
    public String success(ModelMap modelMap,
                          String way,
                          String state){
        modelMap.put("state",state);
        modelMap.put("way",way);
        //线下支付
        modelMap.put("payType","outPay");
        return "payment_result";
    }


    /**
     * 创建支付记录
     * @param payRecordVo
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @BizLog(type = LogConstant.pay, desc = "创建支付记录")
    @SecurityToken(validateToken=true)
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
        //支付成功后通知管理员审核
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.payment.getTitle(String.valueOf(payRecordVo.getId())),
                        NotifyTemplateEnum.payment.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.payment.getType(),payRecordVo.getId()));
        return new Result(true).info("支付信息提交成功!");
    }


    /**
     * 提交帐单
     * @param billtime
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/bill")
    @ResponseBody
    @BizLog(type = LogConstant.pay, desc = "提交账单")
    @SecurityToken(validateToken=true)
    public Result bill(Integer billtime,
                       Integer orderId){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        AccountBill accountBill = new AccountBill();
        accountBill.setUserId(user.getId());

        accountBill.setOrderId(orderId);
        accountBill.setBillTime(billtime);
        accountBillService.createBill(accountBill);
        // 账单提交成功后通知管理员审核
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.account_bill.getTitle(String.valueOf(accountBill.getId())),
                        NotifyTemplateEnum.account_bill.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.account_bill.getType(),accountBill.getId()));

        return new Result(true).info("账单提交成功!");
    }


    /**
     * 支付记录
     * @param modelMap
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/record")
    @BizLog(type = LogConstant.pay, desc = "支付记录页面")
    public String record(ModelMap modelMap,
                         Integer pageSize,
                         Integer pageNum){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PayRecordVo payRecord=new PayRecordVo();
        PageInfo<PayRecordVo> recordPage;
        if(user.getType()==1){
            payRecord.setUserId(user.getId());
            recordPage = payRecordService.findByUserId(payRecord,pageNum,pageSize);
        }
        else{
            payRecord.setAgentId(user.getId());
            recordPage = payRecordService.findByNormalRecord(payRecord,pageNum,pageSize);
        }

        for(PayRecordVo payRecordVo : recordPage.getList()){
            String orderCode =  payRecordVo.getOrderCode();
            OrderForm orderForm = orderFormService.findByOrderCode(orderCode);
            Integer orderId = orderForm.getId();
            payRecordVo.setCommodities(assignCommodity(orderId));
            if(payRecordVo.getDeposit()==null){
                payRecordVo.setDeposit(orderForm.getDeposit());
            }
        }
        modelMap.put("recordPage",recordPage);
        if(user.getType()==1) {
            return "pay_record";
        }
        else{
            return "pay_record_agent";
        }
    }


    /**
     * 支付详情
     * @param modelMap
     * @param id
     * @return
     */
    @RequestMapping(value = "/details/{id}")
    public String details(ModelMap modelMap,
                          @PathVariable("id")Integer id){
        PayRecordVo payRecordVo =  payRecordService.findVoById(id);
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user.getType()==1){
            if(!payRecordVo.getUserId().equals(user.getId())){
                return "redirect:error/404";
            }
        }
        else{
            if(!payRecordVo.getAgentId().equals(user.getId())){
                return "redirect:error/404";
            }
        }
        OrderForm orderForm = orderFormService.findByOrderCode(payRecordVo.getOrderCode());
        if(payRecordVo.getDeposit()==null){
            payRecordVo.setDeposit(orderForm.getDeposit());
        }
        if(payRecordVo.getPaymentId()!=null){
            Payment payment=paymentService.findById(payRecordVo.getPaymentId());
            payRecordVo.setPayTypeName(PayTypeEnum.findByValue(payment.getPayType()));
        }

        modelMap.put("payRecordVo",payRecordVo);
        if(user.getType()==1){
            return "pay_detail";
        }
        else{
            return "pay_detail_agent";
        }
    }
    @RequestMapping("/sendAccount")
    @ResponseBody
    @BizLog(type = LogConstant.pay, desc = "发送账号到手机")
    public Result sendAccount(Integer payAccountId,Integer orderId){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PayAccount payAccount=payAccountService.findById(payAccountId);
        OrderFormVo orderForm = orderFormService.findVoById(orderId);

        try {
            smsService.sendAccount(user.getContactMobile(),payAccount.getReceiveAccount(),payAccount.getReceiveBank(),payAccount.getReceiveBankCard(),orderForm.getAmountsPayable(),orderForm.getCode());
        } catch (Exception e) {
            return new Result(false).info("发送账号失败!");
        }

        return new Result(true).info("发送账号到手机成功!");
    }





    private List<OrderCommodity> assignCommodity(Integer orderId){
        List<OrderCommodity>  commodityList = orderCommodityService.getCommodityByOrderId(orderId);
        return commodityList;
    }





}
