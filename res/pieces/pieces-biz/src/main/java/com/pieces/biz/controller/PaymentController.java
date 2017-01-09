package com.pieces.biz.controller;

import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.dao.enums.PayEnum;
import com.pieces.dao.enums.PayTypeEnum;
import com.pieces.dao.model.Payment;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.PaymentVo;
import com.pieces.service.OrderFormService;
import com.pieces.service.PaymentService;
import com.pieces.service.UserService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.pay.alipay.AlipayNotify;
import com.pieces.service.pay.alipay.AlipaySubmit;
import com.pieces.service.pay.config.AlipayConfig;
import com.pieces.tools.log.annotation.BizLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wangbin on 2016/12/19.
 */
@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private PaymentService paymentService;




    @RequestMapping(value = "alipay" )
    @BizLog(type = LogConstant.pay, desc = "支付宝支付")
    public void alipay(Integer orderId,Integer accountBillId,Double money,HttpServletResponse response) throws IOException {

        OrderFormVo orderForm = orderFormService.findVoById(orderId);

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());

        if(money==null){
            //终端用户支付全款，代理商支付保证金
            if(user.getType()==1){
                money=orderForm.getAmountsPayable();
            }
            else{
                money=orderForm.getDeposit();
            }
        }
        //money=0.01;

        Payment payment=new Payment();
        payment.setStatus(PayEnum.UNPAID.getValue());
        payment.setUserId(user.getId());
        payment.setOrderId(orderForm.getId());
        payment.setAccountBillId(accountBillId);

        payment.setPayType(PayTypeEnum.ALIPAY.getValue());
        payment.setMoney(money);
        paymentService.save(payment);


        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", alipayConfig.getNotify_url());
        sParaTemp.put("return_url", alipayConfig.getReturn_url());
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("out_trade_no", payment.getOutTradeNo());
        sParaTemp.put("subject", "上工好药订单支付");

        sParaTemp.put("total_fee", money.toString());


        sParaTemp.put("body", "上工好药订单支付");



        payment.setOutParam(sParaTemp.toString());
        paymentService.update(payment);

        String htmlOut = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
        logger.info(htmlOut);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(htmlOut);
    }



    @RequestMapping(value = "alipay/result/page" )
    @BizLog(type = LogConstant.pay, desc = "支付宝页面回调")
    public String resutlPage( HttpServletResponse response,
                              HttpServletRequest request,
                              ModelMap modelMap)throws Exception{
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        logger.info("支付宝页面回调param="+params.toString());


        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        if(verify_result){//验证成功
            paymentService.handleResult(params);
            modelMap.put("result","验证成功");

        }else{
            modelMap.put("result","验证失败");
        }
        modelMap.put("payType","alipay");
        return "payment_result";
    }



    @RequestMapping(value = "alipay/result/notify" )
    @BizLog(type = LogConstant.pay, desc = "支付宝通知回调")
    public void resutlNotify(HttpServletResponse response,
                             HttpServletRequest request,
                             ModelMap modelMap)throws Exception{
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝通知param="+params.toString());
        PrintWriter out=response.getWriter();
        if(AlipayNotify.verify(params)){//验证成功
            paymentService.handleResult(params);
            out.print("success");

        }else{//验证失败
            out.print("fail");
        }


    }
}
