package com.pieces.boss.controller;

import com.pieces.service.constant.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 8/29/16.
 * 支付记录
 */
@Controller
@RequestMapping("payment/")
public class PaymentController {

    /**
     * 支付记录index
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {

        return "payment";
    }

    /**
     * 支付记录详情
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id) {
        return "payment_detail";
    }

    /**
     * 支付成功
     * @param payId
     * @return
     */
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    @ResponseBody
    public Result success(Integer payId) {
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
    public Result fail(Integer payId, String msg) {
        return new Result(true).info("支付状态修改成功");
    }

    // TODO: 收款账号管理
}
