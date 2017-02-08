package com.pieces.tools.log.api;

import com.pieces.tools.log.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Author: koabs
 * 2/7/17.
 */
public class LogAuditing {

    private static Logger logger = LoggerFactory.getLogger(LogAuditing.class);

    private Object preData; // 用户修改前数据

    private Object afterData; // 用户修改后的数据

    private String userName; // 操作用户名

    private String type; // 业务类型

    private String description; // 业务描述

    private Date data; // 业务发生时间

    public LogAuditing(Object preData, Object afterData, String userName, String type, String description, Date data) {
        this.preData = preData;
        this.afterData = afterData;
        this.userName = userName;
        this.type = type;
        this.description = description;
        this.data = data;
    }

    public static void audit(Object preData, Object afterData, String type, String description) {
        LogAuditing auditing = new LogAuditing(preData,afterData,null,type,description,new Date());
        LogUser user = LogConfig.user.getLogUser();
        // 设置登入用户信息
        if (user != null) {
            auditing.setUserName(user.getUserName());
        }
        logger.info("审计日志:"+ JSONUtils.toJson(auditing));
    }

    public Object getPreData() {
        return preData;
    }

    public void setPreData(Object preData) {
        this.preData = preData;
    }

    public Object getAfterData() {
        return afterData;
    }

    public void setAfterData(Object afterData) {
        this.afterData = afterData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}