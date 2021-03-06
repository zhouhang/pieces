package com.pieces.boss.commons;

import com.pieces.dao.model.Member;
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
        Member member = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        LogUser user = new LogUser();
        if (member != null) {
            user.setUserName(member.getUsername());
            user.setUserId(Long.valueOf(member.getId()));
        }

        return user;
    }
}
