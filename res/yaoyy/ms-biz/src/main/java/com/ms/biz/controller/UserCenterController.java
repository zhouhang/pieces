package com.ms.biz.controller;

import com.ms.dao.model.User;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;
import com.ms.service.UserDetailService;
import com.ms.service.enums.RedisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by burgl on 2016/10/30.
 */
@Controller
@RequestMapping("center/")
public class UserCenterController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        //获取登陆用户userId
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        UserDetailVo detailVo = userDetailService.findByUserId(user.getId());
        if (detailVo != null){
            modelMap.put("nickname", detailVo.getNickname());
        }
        return "user_center";
    }

}
