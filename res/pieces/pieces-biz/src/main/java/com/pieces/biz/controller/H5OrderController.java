package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.bean.BASE64DecodedMultipartFile;
import com.pieces.tools.exception.NotFoundException;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.SpringUtil;
import com.pieces.tools.utils.WebUtil;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 3/14/17.
 * 微信端订单功能
 */
@Controller
@RequestMapping("/h5c/")
public class H5OrderController {

    Logger logger = LoggerFactory.getLogger(H5OrderController.class);
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderFormService orderFormService;

    private static final  Integer pageSize=5;

    @Autowired
    PayAccountService payAccountService;

    @Autowired
    PayRecordService payRecordService;


    @RequestMapping(value ="order/list",method = RequestMethod.GET)
    public String orderList(Integer status,ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(status==null){
            status=1;
        }
        modelMap.put("status",status);
        modelMap.put("userType",user.getType());
        return "wechat/order_list";
    }


    @RequestMapping(value ="order/list",method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderList(Integer pageNum,Integer status) {

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PageInfo<OrderFormVo> pageInfo;
        if(user.getType()==1){
           pageInfo = orderFormService.findOrderByUserId(user.getId(),status,pageNum, pageSize);
        }
        else{
            pageInfo = orderFormService.findOrderByAgentId(user.getId(),status,pageNum, pageSize);
        }

        return new Result(true).data(pageInfo);
    }

    /**
     * 用户订单详情
     * @return
     */
    @RequestMapping(value = "order/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/order_detail";
    }


    /**
     * 用户订单支付
     * @return
     */
    @RequestMapping(value = "order/pay/{id}", method = RequestMethod.GET)
    public String payOrder(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/pay";
    }

    @RequestMapping(value = "pay/bank/{id}", method = RequestMethod.GET)
    public String payBank(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);

        List<PayAccount> payAccountList = payAccountService.findAll();
        modelMap.put("payAccountList",payAccountList);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/pay_bank";
    }
    @RequestMapping(value = "pay/bill/{id}", method = RequestMethod.GET)
    public String payBill(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user.getType()==1){
            return "redirect:error/404";
        }
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("vo",vo);
        return "wechat/pay_bill";
    }

    @RequestMapping(value = "pay/success", method = RequestMethod.GET)
    public String paySuccess(ModelMap modelMap){
        return "wechat/pay_message";
    }


    /**
     * 创建支付记录
     * @param payRecordVo
     * @return
     */
    @RequestMapping(value = "/payRecord/create")
    @ResponseBody
    public Result create(PayRecordVo payRecordVo,
                         String[] img){

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        payRecordService.create(payRecordVo,img,user.getId());
        //支付成功后通知管理员审核
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.payment.getTitle(String.valueOf(payRecordVo.getId())),
                        NotifyTemplateEnum.payment.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.payment.getType(),payRecordVo.getId()));
        return new Result(true).info("支付信息提交成功!");
    }




}
