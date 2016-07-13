package com.pieces.boss.commons;

import com.pieces.tools.log.api.GetLogUser;
import com.pieces.tools.log.api.LogUser;
import org.springframework.stereotype.Component;

/**
 * Created by kevin1 on 7/12/16.
 */
@Component("logGetUser")
public class GetUser implements GetLogUser {
    @Override
    public LogUser getLogUser() {
        LogUser user = new LogUser();
        user.setUserName("张珊");
        user.setUserId(12L);
        return user;
    }
}
