package com.ms.biz.controller;

import com.ms.biz.shiro.BizToken;
import com.ms.dao.model.User;
import com.ms.service.UserService;
import com.ms.service.enums.RedisEnum;
import com.ms.service.redis.RedisManager;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.WebUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangbin on 2016/10/28.
 */
@Controller
@RequestMapping("wechat")
public class WechatController {

    private static final Logger logger = Logger.getLogger(WechatController.class);

    @Autowired
    private RedisManager redisManager;
    @Autowired
    private WxMpService wxService;
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 接入微信公众号
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(path ="init" ,method = RequestMethod.GET )
    @ResponseBody
    public void authGet(HttpServletResponse response,
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {
        logger.info("接收微信signature:"+signature+",timestamp:"+timestamp+",nonce:"+nonce+"echostr:"+echostr);
        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实~");
        }
        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            WebUtil.print(response,echostr);
        }
        WebUtil.print(response,"非法请求");

    }



    /**
     * 跳转到微信登陆页面（绑定微信和手机号）
     * @param request
     * @param response
     * @param call
     * @param code
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String call,
                        String code,
                        ModelMap model){
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            User user = userService.findByOpenId(wxMpUser.getOpenId());
            if(user!=null){
                autoLogin(user);
                if(StringUtils.isNotBlank(call)){
                    return "redirect:"+call;
                }else{
                    return "redirect:/center/index";
                }
            }
            model.put("headImgUrl",wxMpUser.getHeadImgUrl());
            model.put("nickname",wxMpUser.getNickname());
            model.put("openId",wxMpUser.getOpenId());
            model.put("call",call);
        }catch (Exception e){
            logger.error(e);
        }
        return "wechat_login";
    }


    /**
     * 通过页面表单信息注册用户并登陆
     * @param response
     * @param request
     * @param callUrl
     * @param phone
     * @param code
     * @param openId
     * @param nickname
     * @param headImgUrl
     * @return
     * @throws Exception
     */
    @RequestMapping("bind")
    @ResponseBody
    public Result bindPhone(HttpServletResponse response,
                            HttpServletRequest request,
                            String callUrl,
                            String phone,
                            String code,
                            String openId,
                            String nickname,
                            String headImgUrl)throws Exception{
        String rcode = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_REGISTER.getValue()+phone);
        if (!code.equalsIgnoreCase(rcode)) {
            return Result.error().msg("验证码错误!");
        }
        User user =userService.registerWechat(phone, openId, nickname, headImgUrl);
        autoLogin(user);

        return Result.success("绑定成功").data(callUrl);
    }


    /**
     * 实现shiro自动登录(并未绑定到redis)
     * @param user
     */
    public void autoLogin(User user){
        Subject subject = SecurityUtils.getSubject();
        BizToken token = new BizToken(user.getPhone(), user.getPassword(), false, null, "");
        token.setOpenId(user.getOpenid());
        userService.login(subject, token);
    }






}
