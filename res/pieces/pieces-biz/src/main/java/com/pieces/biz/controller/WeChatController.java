package com.pieces.biz.controller;

import com.pieces.dao.model.Commodity;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

/**
 * Author: koabs
 * 3/14/17.
 * 微信端报价功能
 */
@Controller
@RequestMapping("/h5/")
public class WeChatController {

    @Autowired
    CommodityService commodityService;

    // 用户进入任何一个页面时根据openID 判断用户在数据库中是否存在 然后自动登入
    /**
     * 用户询价
     * @return
     */
    @RequestMapping(value = "enquiry", method = RequestMethod.GET)
    public String enquiry() {
        return "enquiry";
    }

    @RequestMapping(value = "enquiry", method = RequestMethod.POST)
    @ResponseBody
    public Result enquirySave() {

        return null;
    }

    /**
     * 询价成功页面
     * @return
     */
    @RequestMapping("enquiry/success")
    public String enquirySuccess() {
        return "enquiry_message";
    }

    // 询价单列表
    @RequestMapping("enquiry/list")
    public String enquiryList() {
        return "enquiry_list" ;
    }

    // 询价单详情
    @RequestMapping("enquiry/detail")
    public String enquiryDetail(Integer id) {
        return "enquiry_detail" ;
    }
    // 商品详情

    //修改询价开票价
    @RequestMapping(value = "enquiry/updatePrice", method = RequestMethod.GET)
    public String enquiryUpdatePrice(String ids) {
        return "enquiry_detail" ;
    }

    /**
     * 保存商品价格修改后信息
     * @param commoditys
     * @param enquiryId
     * @return
     */
    @RequestMapping(value = "enquiry/updatePrice", method = RequestMethod.POST)
    public String enquiryUpdatePriceSave(@RequestBody EnquiryCommoditys commoditys, Integer enquiryId) {
        return "enquiry_price_update" ;
    }

    /**
     * 询价开票价修改成功
     * 分享商品时根据询价商品ID 来获取分享列表
     * @param ids
     * @return
     */
    @RequestMapping(value = "enquiry/updatePriceSuccess", method = RequestMethod.GET)
    public String enquiryUpdatePriceSuccess(String ids) {

        return "enquiry_price_update_message" ;
    }

    /**
     * 商品详情页
     * @param id
     * @return
     */
    @RequestMapping(value = "commodity/{id}", method = RequestMethod.GET)
    public String enquiryUpdatePriceSuccess(@PathVariable("id") Integer id) {

        return "commodity_detail" ;
    }

    /**
     * 上传转账图片
     * @param img
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CropResult updateFile(String img, String fileName) throws Exception{
        img=img.substring(img.indexOf(",")+1);//需要去掉头部信息，这很重要
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] result = base64Decoder.decodeBuffer(img);//解码
        BASE64DecodedMultipartFile multipartFile = new BASE64DecodedMultipartFile(result, fileName);
        return commodityService.uploadImage(multipartFile);
    }
}
