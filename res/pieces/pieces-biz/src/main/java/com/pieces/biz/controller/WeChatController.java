package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.dto.UserValidate;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.BASE64DecodedMultipartFile;
import com.pieces.tools.exception.NotFoundException;
import com.pieces.tools.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;

/**
 * Author: koabs
 * 3/14/17.
 * 微信端报价功能
 */
@Controller
@RequestMapping("/h5/")
public class WeChatController {

    Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    CommodityService commodityService;

    @Autowired
    AnonEnquiryService anonEnquiryService;

    @Autowired
    UserService userService;

    @Autowired
    RedisManager redisManager;

    @Autowired
    HttpSession httpSession;

    @Autowired
    WxMpService wxService;

    @Autowired
    EnquiryBillsService enquiryBillsService;

    @Autowired
    EnquiryCommoditysService enquiryCommoditysService;


    /**
     * 用户询价
     * @return
     */
    @RequestMapping(value = "enquiry", method = RequestMethod.GET)
    public String enquiry(ModelMap model) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        model.put("user", user);
        return "wechat/enquiry";
    }

    /**
     * 保存询价信息
     * @return
     */
    @RequestMapping(value = "enquiry", method = RequestMethod.POST)
    @ResponseBody
    public Result enquirySave(@RequestBody AnonEnquiryVo anonEnquiryVo, String code) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        // 把询价信息保存到新客询价里面
        // 用户填写的姓名和手机号 要保存到自动生成的账号里面.
        if (anonEnquiryVo.getContacts()!= null && anonEnquiryVo.getPhone() != null && code != null) {
            // code 和Redis 存的code做对比
            //帮用户注册生成询价单
            String sessionCode  = redisManager.get(RedisEnum.KEY_MOBILE_EQUIRY_CAPTCHA.getValue()+anonEnquiryVo.getPhone());
            if (Strings.isNullOrEmpty(sessionCode) || !code.equals(sessionCode)) {
                // 验证码错误.
                return new Result(false).info("验证码错误");
            } else {
                WxMpUser wxUser = (WxMpUser)httpSession.getAttribute("wxMpUser");
                user = userService.createWxUser(wxUser,anonEnquiryVo.getContacts(),anonEnquiryVo.getPhone());

                Subject subject = SecurityUtils.getSubject();
                BizToken token = new BizToken(user.getUserName(), user.getPassword(), false,null, "");
                token.setWechat(true);
                userService.login(subject, token);
            }

        }
        //用户已经登入
        if (user!= null) {
            anonEnquiryVo.setContacts(user.getContactName());
            anonEnquiryVo.setPhone(user.getContactMobile());
        }
        anonEnquiryService.save(anonEnquiryVo);

        return new Result(true).info("询价成功");
    }

    /**
     * 询价成功页面
     * @return
     */
    @RequestMapping("enquiry/success")
    public String enquirySuccess() {
        return "wechat/enquiry_message";
    }

    // 询价单列表
    @RequestMapping("enquiry/list")
    public String enquiryList(ModelMap model, Integer status) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        status = status== null?1:status;
        model.put("status",status);
        if (user != null) {
            EnquiryRecordVo vo = new EnquiryRecordVo();
            vo.setUserId(user.getId());
            vo.setStatus(status);
            //查询用户的询价单
            PageInfo<EnquiryBillsVo> pageInfo =  enquiryBillsService.findByPage(1,100,vo);
            model.put("pageInfo",pageInfo);
        }
        return "/wechat/enquiry_list" ;
    }

    // 询价单详情
    @RequestMapping("enquiry/detail")
    public String enquiryDetail(Integer billId, ModelMap model, HttpServletRequest request) {
        // 报价时销售价等于开票价
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if (user!= null) {
            enquiryBillsService.read(billId);
            EnquiryBillsVo vo = enquiryBillsService.findVOById(billId);
            model.put("bill", vo);
        }

        model.put("user", user);

        try {
            WxJsapiSignature signature = wxService.createJsapiSignature(WebUtil.getFullUrl(request));
            model.put("signature",signature);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return "wechat/enquiry_detail" ;
    }

    //修改询价开票价
    @RequestMapping(value = "enquiry/updatePrice", method = RequestMethod.GET)
    public String enquiryUpdatePrice(String ids, Integer billId, ModelMap model) {
        // 安全性检测用户只能访问自己的询价商品
        List<EnquiryCommoditysVo> list = enquiryCommoditysService.findVoByIds(ids);
        model.put("list",list);
        model.put("ids",ids);
        model.put("billId",billId);
        return "wechat/enquiry_price_update" ;
    }

    /**
     * 保存商品价格修改后信息
     * @param list
     * @return
     */
    @RequestMapping(value = "enquiry/updatePrice", method = RequestMethod.POST)
    @ResponseBody
    public Result enquiryUpdatePriceSave(@RequestBody List<EnquiryCommoditys> list) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if (user!= null) {
            enquiryCommoditysService.priceUpdate(list,user.getId());
        }
        return new Result(true);
    }

    /**
     * 询价开票价修改成功
     * 分享商品时根据询价商品ID 来获取分享列表
     * @param ids
     * @return
     */
    @RequestMapping(value = "enquiry/updatePriceSuccess", method = RequestMethod.GET)
    public String enquiryUpdatePriceSuccess(String ids,Integer billId, ModelMap model,HttpServletRequest request) {
        model.put("ids",ids);
        model.put("billId",billId);
        try {
            WxJsapiSignature signature = wxService.createJsapiSignature(WebUtil.getFullUrl(request));
            model.put("signature",signature);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "wechat/enquiry_price_update_message" ;
    }

    /**
     * 商品详情页
     * @param id
     * @return
     */
    @RequestMapping(value = "commodity/{id}", method = RequestMethod.GET)
    public String enquiryUpdatePriceSuccess(@PathVariable("id") Integer id, ModelMap model) {

        CommodityVo commodity = commodityService.findVoById(id);
        if (commodity == null) {
            throw new NotFoundException();
        }
        model.put("commodity", commodity);
        return "wechat/commodity_detail" ;
    }

    /**
     * 上传转账图片
     * @param img
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CropResult updateFile(String img, String fileName) throws Exception{
        img=img.substring(img.indexOf(",")+1);//需要去掉头部信息，这很重要
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] result = base64Decoder.decodeBuffer(img);//解码
        BASE64DecodedMultipartFile multipartFile = new BASE64DecodedMultipartFile(result, fileName);
        return commodityService.uploadImage(multipartFile);
    }


    // 自动登入
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(String call, String code) {
        if (!Strings.isNullOrEmpty(code)) {
            try {
                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
                WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
                User user = userService.findByOpenId(wxMpUser.getOpenId());
                if (user != null) {
                    // 登入
                    Subject subject = SecurityUtils.getSubject();
                    BizToken token = new BizToken(user.getUserName(), user.getPassword(), false,null, "");
                    token.setWechat(true);
                    userService.login(subject, token);

                } else {
                    //保存微信信息到Session 里面
                    httpSession.setAttribute("wxMpUser",wxMpUser);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        if (!Strings.isNullOrEmpty(call)) {
            return "redirect:" + call;
        } else {
            return "redirect:/h5/enquiry/list";
        }
    }
}
