package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
@Controller
@RequestMapping("/bank")
public class PayAccountController extends BaseController{
    @Autowired
    private PayAccountService payAccountService;


    /**
     * 银行账号页面
     * @return
     */
    @RequestMapping("/index")
    public String index(PayAccountVo vo, Integer pageSize, Integer pageNum, ModelMap modelMap){
        pageNum = pageNum==null?1:pageNum;
        pageSize = pageSize==null?10:pageSize;
        PageInfo<PayAccountVo> pageInfo = payAccountService.findByParams(vo,pageNum,pageSize);
        modelMap.put("pageInfo",pageInfo);
        modelMap.put("vo",vo);
        modelMap.put("param", vo.serialize());
        return  "bank";
    }

    /**
     * 银行账号页面
     * @return
     */
    @RequestMapping("/add")
    public String add(ModelMap modelMap){
        return  "bank_add";
    }

    /**
     * 修改银行账号
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("id") Integer id,
                               ModelMap model){
        PayAccount payAccount = payAccountService.findById(id);
        model.put("payAccount", payAccount);
        return "bank_edit";
    }

    /**
     * 保存账户，id为空新增，id不为空修改
     */
    @RequestMapping(value = "/save")
    public void save(HttpServletRequest request,
                             HttpServletResponse response,
                             PayAccount payAccount,
                             ModelMap model){
        Result result = new Result(true);
        if (payAccount.getId() == null) {
            payAccountService.create(payAccount);
            result.info("新增账户成功。");
        } else {
            payAccountService.update(payAccount);
            result.info("修改账户成功。");
        }
        WebUtil.printJson(response, result);
    }
}
