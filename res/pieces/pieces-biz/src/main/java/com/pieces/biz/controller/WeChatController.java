package com.pieces.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: koabs
 * 3/14/17.
 * 微信端报价功能
 */
@Controller
@RequestMapping("/h5/")
public class WeChatController {

    // 用户进入任何一个页面时根据openID 判断用户在数据库中是否存在 然后自动登入


    /**
     * 用户询价
     * @return
     */
    public String enquiry() {
        return "";
    }

    // 询价单列表
    public String enquiryList() {
        return "" ;
    }

    // 询价单详情

    // 询价成功提示页

    // 商品详情

    //修改询价开票价

    // 分享商品时根据询价商品ID 来获取分享列表

    //
}
