package com.pieces.tools.config;

/**
 * Author: koabs
 * 10/8/16.
 * 验证的正则表达式
 */
public interface ValidPattern {

    // 银行卡号
    public static String bank = "/^\\d{16}|\\d{19}$/";

    // 手机号
    public static String mobile = "^1\\d{10}$";

}
