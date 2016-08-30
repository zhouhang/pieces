package com.pieces.biz.controller;

import com.pieces.dao.enums.SessionEnum;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.OrderFormService;
import com.pieces.service.PayAccountService;
import com.pieces.service.PayRecordService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private HttpSession httpSession;


    @Autowired
    private PayAccountService payAccountService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private PayRecordService payRecordService;



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

        return new Result(true).info("支付信息提交成功!");
    }


}
