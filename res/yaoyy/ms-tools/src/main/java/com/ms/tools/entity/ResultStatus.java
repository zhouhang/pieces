package com.ms.tools.entity;

/**
 * Author: koabs
 * 8/22/16.
 * 返回结果状态
 */
public class ResultStatus {
    //用户未登入
    public static Integer Not_Login = -1;

    //系统内部错误,需要前端捕获的
    public static Integer Error = 500;

    public static Integer Success = 200;

    // 页面未找到
    public static Integer S404 = 404;

}
