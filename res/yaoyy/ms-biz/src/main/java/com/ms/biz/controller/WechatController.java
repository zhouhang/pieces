package com.ms.biz.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangbin on 2016/10/28.
 */
@Controller
@RequestMapping("wechat")
public class WechatController {

    private static final Logger logger = Logger.getLogger(WechatController.class);

    @Autowired
    private WxMpService wxService;

    @RequestMapping(path ="init" ,method = RequestMethod.GET ,produces = "text/html;charset=utf-8")
    @ResponseBody
    public String authGet(HttpServletResponse response,
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {
        logger.info("接收微信signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+"echostr:"+echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实~");
        }
        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }


    @RequestMapping(path ="info" ,method = RequestMethod.GET ,produces = "text/html;charset=utf-8")
    @ResponseBody
    public String authGet(HttpServletResponse response) {


        return "hao123";
    }

}
