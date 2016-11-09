package com.ms.biz.controller;

import com.ms.dao.model.User;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;
import com.ms.service.UserDetailService;
import com.ms.service.UserService;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        String nickname = null;
        //获取登陆用户userId
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if (user != null) {
            UserDetailVo detailVo = userDetailService.findByUserId(user.getId());
            if (detailVo != null && detailVo.getNickname() != null){
                nickname = detailVo.getNickname();
            } else {
                nickname = "";
            }
        }
        modelMap.put("nickname", nickname);
        return "user_center";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.GET)
    public String findPassword(ModelMap modelMap){
        //获取登陆用户userId
        // 用户未登入请求用户登入
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        modelMap.put("phone", user.getPhone());
        return "update_password";
    }

    @RequestMapping(value = "sendResetPasswordSms", method = RequestMethod.POST)
    @ResponseBody
    public Result sendResetPasswordSms() {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        userService.sendResetPasswordSms(user.getPhone());
        return Result.success("验证码发送成功");
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(String code, String password){
        //获取登陆用户userId
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        userService.resetPassword(user.getPhone(), code, password);
        return Result.success("密码重置成功");
    }
}
