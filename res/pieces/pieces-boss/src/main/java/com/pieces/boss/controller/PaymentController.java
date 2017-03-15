package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.enums.PayTypeEnum;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.Payment;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.PayRecordService;
import com.pieces.service.PaymentService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Author: koabs
 * 8/29/16.
 * 支付记录
 */
@Controller
@RequestMapping("payment/")
public class PaymentController {

    @Autowired
    PayRecordService payRecordService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    private PaymentService paymentService;

    /**
     * 支付记录index
     * @return
     */
    @RequiresPermissions(value = "pay:index")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @BizLog(type = LogConstant.pay, desc = "支付记录页面")
    public String index(PayRecordVo vo, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        PageInfo<PayRecordVo> pageInfo = payRecordService.findByParams(vo, pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("param", vo);
        modelMap.put("paramGet", Reflection.serialize(vo));
        return "payment";
    }


    /**
     * 支付记录详情
     * @param id
     * @return
     */
    @RequiresPermissions(value = "pay:info")
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @BizLog(type = LogConstant.pay, desc = "支付记录详情")
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        PayRecordVo vo = payRecordService.findVoById(id);
        if(vo.getPaymentId()!=null){
            Payment payment=paymentService.findById(vo.getPaymentId());
            vo.setPayTypeName(PayTypeEnum.findByValue(payment.getPayType()));
        }
        modelMap.put("pay",vo);
        return "payment_detail";
    }

    /**
     * 支付成功
     * @param payId
     * @return
     */
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.pay, desc = "支付成功")
    public Result success(Integer payId) {
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        payRecordService.success(payId,mem);
        return new Result(true).info("支付状态修改成功.");
    }

    /**
     * 支付失败
     * @param payId
     * @param msg
     * @return
     */
    @RequestMapping(value = "/fail", method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.pay, desc = "支付失败")
    public Result fail(Integer payId, String msg) {
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        payRecordService.fail(payId,msg,mem);
        return new Result(true).info("支付状态修改成功");
    }
}
