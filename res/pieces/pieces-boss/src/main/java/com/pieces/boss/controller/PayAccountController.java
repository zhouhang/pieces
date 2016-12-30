package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.annotation.SameUrlData;
import com.pieces.tools.annotation.TokenHold;
import com.pieces.tools.annotation.TokenVerify;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.WebUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    @RequiresPermissions(value = "bank:index")
    @BizLog(type = LogConstant.pay, desc = "支付银行账号页面")
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
    @RequiresPermissions(value = "bank:add")
    @BizLog(type = LogConstant.pay, desc = "支付添加银行账号页面")
    public String add(ModelMap modelMap){
        return  "bank_add";
    }

    /**
     * 修改银行账号
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    @RequiresPermissions(value = "bank:edit")
    @BizLog(type = LogConstant.pay, desc = "支付编辑银行账号页面")
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
    @RequiresPermissions(value = {"bank:add","bank:edit"},logical = Logical.OR)
    @BizLog(type = LogConstant.pay, desc = "保存银行账号")
    @SameUrlData
    public void save(HttpServletRequest request,
                             HttpServletResponse response,
                             @Valid PayAccount payAccount,
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

    /**
     * 删除账户
     * @param request
     * @param response
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    @BizLog(type = LogConstant.pay, desc = "支付银行账号删除")
    public void delete(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("id") Integer id,
                               ModelMap model){
        payAccountService.deleteById(id);
        Result result = new Result(true).info("删除分类成功。");
        WebUtil.printJson(response, result);
    }
}
