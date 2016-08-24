package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.OrderFormDao;
import com.pieces.dao.OrderRemarkDao;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.OrderRemarkVo;
import com.pieces.service.OrderFormService;
import com.pieces.service.OrderRemarkService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import org.apache.commons.io.ByteOrderMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Controller
@RequestMapping("order/")
public class OrderController extends BaseController{

    @Autowired
    OrderRemarkService orderRemarkService;

    @Autowired
    OrderFormService orderFormService;

    @Autowired
    HttpSession httpSession;

    /**
     * 订单列表页面
     * @return
     */
    @RequestMapping("index")
    public String index(OrderFormVo vo, Integer pageSize, Integer pageNum, ModelMap modelMap){
        PageInfo<OrderFormVo> pageInfo = orderFormService.findByParams(vo,pageNum,pageSize);
        modelMap.put("pageInfo",pageInfo);
        modelMap.put("vo",vo);
        vo.setUser(null);
        modelMap.put("param", Reflection.serialize(vo));

        return  "order";
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        OrderFormVo vo = orderFormService.findVoById(id);
        List<OrderRemarkVo> remarks = orderRemarkService.findByOrderId(id);
        modelMap.put("vo", vo);
        modelMap.put("remarks", remarks);
        return  "order_detail";
    }

    /**
     * 给订单添加评论
     * @param remark
     * @return
     */
    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(OrderRemark remark){
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        remark.setUserId(mem.getId());
        remark.setCreaterTime(new Date());
        orderRemarkService.create(remark);
        return new Result(true).info("添加成功!").data(remark);
    }

}
