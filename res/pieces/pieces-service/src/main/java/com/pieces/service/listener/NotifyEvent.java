package com.pieces.service.listener;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.ApplicationEvent;

/**
 * Author: koabs
 * 12/20/16.
 * 消息通知时间
 */
public class NotifyEvent extends ApplicationEvent {

    private String title;

    private String content;

    private Integer type;

    private Integer eventId;

    public NotifyEvent(String title, String content) {
        super(title);
        this.title = title;
        this.content = content;
    }


    public NotifyEvent(String title, String content, Integer type, Integer eventId) {
        super(title);
        this.title = title;
        this.content = content;

        this.type=type;
        this.eventId=eventId;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getType() {
        return type;
    }

    public Integer getEventId() {
        return eventId;
    }

}
