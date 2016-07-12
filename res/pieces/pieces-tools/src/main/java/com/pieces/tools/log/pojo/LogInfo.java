package com.pieces.tools.log.pojo;

import com.pieces.tools.log.annotation.BizLog;

import java.util.Date;

/**
 * Created by kevin1 on 7/11/16.
 * 日志信息实体类
 */
public class LogInfo {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String userName;
    /**
     * 应用名
     */
    private String appCode;
    /**
     * log记录类型
     */
    private String logType = "KLOG";

    /**
     * app地址
     */
    private String appHost;
    /**
     * 请求者IP
     */
    private String requestIp;

    /**
     * 请求的url
     */
    private String requestUrl;

    /**
     * 异常类名
     */
    private String exceptionClassname;
    /**
     * 异常描述
     */
    private String exceptionDesc;
    /**
     * 日志记录时间
     */
    private Date logTime;

    /**
     * 执行方法类名
     */
    private String actionClassname;

    /**
     * 执行方法名
     */
    private String actionMethod;

    /**
     * 花费时间
     */
    private Integer costTime = Integer.valueOf(0);

    /**
     * 执行是否成功
     */
    private Integer successed;

    private String requestInfo;
    /**
     * 输入参数
     */
    private String inParam;
    /**
     * 输出参数
     */
    private String outParam;

    /**
     * 额外附加信息
     */
    private String extInfo;
    /**
     * 业务描述
     */
    private String bizDesc;
    /**
     * 业务类型
     */
    private String bizType;

    static final String decollator = "_";

    private transient BizLog biz;

    public static String getDecollator() {
        return decollator;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getAppHost() {
        return appHost;
    }

    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getExceptionClassname() {
        return exceptionClassname;
    }

    public void setExceptionClassname(String exceptionClassname) {
        this.exceptionClassname = exceptionClassname;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getActionClassname() {
        return actionClassname;
    }

    public void setActionClassname(String actionClassname) {
        this.actionClassname = actionClassname;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    public Integer getSuccessed() {
        return successed;
    }

    public void setSuccessed(Integer successed) {
        this.successed = successed;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getInParam() {
        return inParam;
    }

    public void setInParam(String inParam) {
        this.inParam = inParam;
    }

    public String getOutParam() {
        return outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public BizLog getBiz() {
        return biz;
    }

    public void setBiz(BizLog biz) {
        this.biz = biz;
    }
}
