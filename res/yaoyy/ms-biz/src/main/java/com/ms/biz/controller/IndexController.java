package com.ms.biz.controller;

import com.ms.dao.model.Commodity;
import com.ms.dao.model.User;
import com.ms.dao.vo.AdVo;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.*;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping()
public class IndexController {

    @Autowired
    private AdService adService;

    @Autowired
    CommodityService commodityService;


    @Autowired
    SendSampleService sendSampleService;

    @Autowired
    UserService userService;

    @Autowired
    private HttpSession httpSession;
    /**
     * 首页广告
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        // 首页banner 广告
        // 专场广告
        List<AdVo> banners = adService.findByType(1);
        List<AdVo> specials = adService.findByType(2);
        model.put("banners", banners);
        model.put("specials",specials);
        return "index";
    }

    @RequestMapping(value = "apply/sample", method = RequestMethod.GET)
    public String apply(Integer commdityId,ModelMap model) {

        Commodity commodity=commodityService.findById(commdityId);

        model.put("commodity", commodity);
        return "apply_sample";
    }


    @RequestMapping(value = "apply/sample", method = RequestMethod.POST)
    @ResponseBody
    public Result applySample(SendSampleVo sendSampleVo) {

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user!=null){
            sendSampleVo.setPhone(user.getPhone());
        }
        sendSampleService.save(sendSampleVo);
        UserVo userInfo=userService.findByPhone(sendSampleVo.getPhone());

        if(user==null){
            userInfo.setIslogin(false);
        }
        else{
            userInfo.setIslogin(true);
        }


        return Result.success().data(userInfo);
    }


}
