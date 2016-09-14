package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.AccountBillVo;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.AccountBillService;
import com.pieces.service.OrderCommodityService;
import com.pieces.service.PayAccountService;
import com.pieces.service.PayRecordService;
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
 * Created by wangbin on 2016/9/6.
 */
@Controller
@RequestMapping("/center/bill")
public class BillController  extends BaseController{

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AccountBillService accountBillService;
    @Autowired
    private PayAccountService payAccountService;
    @Autowired
    private OrderCommodityService orderCommodityService;
    @Autowired
    private PayRecordService payRecordService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap,
                        Integer pageSize,
                        Integer pageNum){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        pageNum=pageNum==null?Integer.valueOf(1):pageNum;
        pageSize=pageSize==null?Integer.valueOf(10):pageSize;
        PageInfo<AccountBillVo> billVoPageInfo = accountBillService.findVoAll(user.getId(),pageNum,pageSize);
        for(AccountBillVo accountBillVo : billVoPageInfo.getList()){
            accountBillVo.setCommodities(assignCommodity(accountBillVo.getOrderId()));
        }
        modelMap.put("billPage",billVoPageInfo);
        return "user_bill";
    }


    @RequestMapping("/pay/{billId}")
    public String billPay(ModelMap modelMap,
                          @PathVariable("billId")Integer billId){

        AccountBill accountBill = accountBillService.findById(billId);
        modelMap.put("accountBill",accountBill);
        String token = UUID.randomUUID().toString();
        httpSession.setAttribute(SessionEnum.PAY_BILL_TOKEN.getKey(),token);
        modelMap.put("token",token);
        List<PayAccount> payAccountList = payAccountService.findAll();
        modelMap.put("payAccountList",payAccountList);
        return "bill_payment";
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

        Object sessionToken =  httpSession.getAttribute(SessionEnum.PAY_BILL_TOKEN.getKey());

        if(StringUtils.isBlank(token)||sessionToken==null||!token.equals(sessionToken.toString())){
            return new Result(false).info("不能重复提交支付信息!");
        }

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        payRecordService.createForBill(payRecordVo,img,user.getId());
        //清空令牌
        httpSession.setAttribute(SessionEnum.PAY_TOKEN.getKey(),null);
        return new Result(true).info("支付信息提交成功!");
    }


    /**
     * 账单详情
     * @param modelMap
     * @param billId
     * @return
     */
    @RequestMapping("/detail/{billId}")
    public String billDetail(ModelMap modelMap,
                             @PathVariable("billId")Integer billId){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        AccountBillVo accountBillVo =  accountBillService.findVoById(billId);
        if(!accountBillVo.getUserId().equals(user.getId())){
            return "redirect:error/404";
        }

        modelMap.put("accountBillVo",accountBillVo);
        return "user_bill_detail";
    }

    private List<OrderCommodity> assignCommodity(Integer orderId){
        List<OrderCommodity>  commodityList = orderCommodityService.getCommodityByOrderId(orderId);
        return commodityList;
    }



}
