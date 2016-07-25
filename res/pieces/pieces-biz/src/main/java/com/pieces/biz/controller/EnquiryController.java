package com.pieces.biz.controller;

import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.tools.log.util.JSONUtils;
import com.pieces.tools.utils.CookieUtils;
import com.pieces.tools.utils.GsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangbin on 2016/7/22.
 */
@Controller
@RequestMapping(value = "/center/enquiry")
public class EnquiryController extends BaseController{

    @Autowired
    private CommodityService commodityService;


    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        ModelMap modelMap,
                        Integer commodityId){
        Set<Integer> cookieSet = null;
        String cookieValue = CookieUtils.getCookieValue(request, BasicConstants.ENQUIRY_COOKIES);
        if(StringUtils.isBlank(cookieValue)){
            cookieSet = new HashSet<>();
        }else{
            cookieSet = GsonUtil.jsonToEntity(cookieValue,new HashSet<Integer>().getClass());
        }
        if(commodityId!=null){
            cookieSet.add(commodityId);
        }


        //删除没用的ID
        Set<Integer> removesSet = new HashSet<>();

        //把询价的商品ID转换成集合对象
        List<CommodityVO> list = new ArrayList<>();
        if(!cookieSet.isEmpty()){
           Iterator<Integer> it = cookieSet.iterator();
           while (it.hasNext()){
               Integer id = it.next();
               CommodityVO commodityVO =  commodityService.findVoById(id);
               if(commodityVO!=null){
                   list.add(commodityVO);
               }else{
                   removesSet.add(id);
               }
           }
        }
        cookieSet.removeAll(removesSet);
        CookieUtils.setCookie(response, BasicConstants.ENQUIRY_COOKIES,GsonUtil.toJson(cookieSet),3600);


        modelMap.put("commodityList",list);

        return "user_enquiry";
    }




}
