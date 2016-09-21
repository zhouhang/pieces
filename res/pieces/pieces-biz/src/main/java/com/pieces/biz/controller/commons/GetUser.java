package com.pieces.biz.controller.commons;

import com.pieces.dao.model.Member;
import com.pieces.dao.model.User;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.log.api.GetLogUser;
import com.pieces.tools.log.api.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by kevin1 on 7/12/16.
 */
@Component("logGetUser")
public class GetUser implements GetLogUser {
    @Autowired
    HttpSession httpSession;

    @Override
    public LogUser getLogUser() {
        User userB = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        LogUser user = new LogUser();
        if (userB != null) {
            user.setUserName(userB.getUserName());
            user.setUserId(Long.valueOf(userB.getId()));
        }

        return user;
    }
}
