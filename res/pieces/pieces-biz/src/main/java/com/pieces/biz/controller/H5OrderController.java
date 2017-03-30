package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.pieces.biz.controller.commons.LogConstant;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.BASE64DecodedMultipartFile;
import com.pieces.tools.exception.NotFoundException;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.WebUtil;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
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
    @RequestMapping(value = "/order/detail/{id}", method = RequestMethod.GET)
    @BizLog(type = LogConstant.order, desc = "订单详情")
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        //该订单非用户自己订单
        if(!user.getId().equals(vo.getUserId())){
            return "redirect:error/404";
        }
        return "wechat/order_detail";
    }



}
