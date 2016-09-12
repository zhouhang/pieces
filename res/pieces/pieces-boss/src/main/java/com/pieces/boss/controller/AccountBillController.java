package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.AccountBillVo;
import com.pieces.service.AccountBillService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Author: koabs
 * 9/6/16.
 * 账单
 */
@Controller
@RequestMapping("/account/bill/")
public class AccountBillController extends BaseController{

    @Autowired
    AccountBillService accountBillService;

    @Autowired
    HttpSession httpSession;

    @RequiresPermissions(value = "bill:index")
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(AccountBillVo vo, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        PageInfo<AccountBillVo> pageInfo = accountBillService.findByParams(vo, pageNum, pageSize);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("param", vo);
        modelMap.put("paramGet", Reflection.serialize(vo));
        return "account_bill";
    }

    /**
     * 账单详情
     * @param id
     * @return
     */
    @RequiresPermissions(value = "bill:info")
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Integer id, ModelMap modelMap) {
        AccountBillVo vo = accountBillService.findVoById(id);
        modelMap.put("vo",vo);
        return "account_bill_detail";
    }

    /**
     * 账单审核成功
     * @param id
     * @return
     */
    @RequiresPermissions(value = "bill:edit")
    @RequestMapping(value = "success", method = RequestMethod.POST)
    @ResponseBody
    public Result success(Integer id) {
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        accountBillService.auditSuccess(id, mem.getId());
        return new Result(true).info("账单审核成功!");
    }

    /**
     * 账单审核失败
     * @param id
     * @return
     */
    @RequiresPermissions(value = "bill:edit")
    @RequestMapping(value = "fail", method = RequestMethod.POST)
    @ResponseBody
    public Result fail(Integer id, String msg) {
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        accountBillService.auditFail(id, msg, mem.getId());
        return new Result(true).info("账单审核失败!");
    }

}
