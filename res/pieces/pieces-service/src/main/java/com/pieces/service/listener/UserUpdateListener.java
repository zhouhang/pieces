package com.pieces.service.listener;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Author: koabs
 * 12/13/16.
 */
@Component
public class UserUpdateListener implements ApplicationListener<UserUpdateEvent> {
    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(UserUpdateEvent userUpdateEvent) {
        User user = new User();
        user.setId(userUpdateEvent.getUserId());
        userService.updateUser(user);
    }
}
