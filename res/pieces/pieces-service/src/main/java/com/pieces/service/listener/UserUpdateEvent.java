package com.pieces.service.listener;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * Author: koabs
 * 12/13/16.
 */
public class UserUpdateEvent extends ApplicationEvent {

    private Integer userId;

    public UserUpdateEvent(Object source) {
        super(source);
    }

    public UserUpdateEvent(Integer userId) {
        super(userId);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
