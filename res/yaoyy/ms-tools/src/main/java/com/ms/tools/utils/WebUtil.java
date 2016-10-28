package com.ms.tools.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 发送response工具类
 * Created by wangbin on 2016/6/27.
 */
public class WebUtil {
    private WebUtil(){};

    private static final String ENCODING = "utf-8";

    public static void print(HttpServletResponse response, Object data){
        printJson(response, data);
    }
    public  static void printJson(HttpServletResponse response, Object data){
        try {
            // 设置响应头
            response.setContentType("application/json"); // 指定内容类型为 JSON 格式
            response.setCharacterEncoding(ENCODING); // 防止中文乱码
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(GsonUtil.toJson(data)); // 转为 JSON 字符串
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public  static void printJson(HttpServletResponse response, String data){
        try {
            // 设置响应头
            response.setContentType("application/json"); // 指定内容类型为 JSON 格式
            response.setCharacterEncoding(ENCODING); // 防止中文乱码
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(data); // 转为 JSON 字符串
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void print(HttpServletResponse response,String data){
        try {
            // 设置响应头
            response.setContentType("text/html"); // 指定内容类型为 JSON 格式
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding(ENCODING); // 防止中文乱码
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(data); // 转为 JSON 字符串
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
