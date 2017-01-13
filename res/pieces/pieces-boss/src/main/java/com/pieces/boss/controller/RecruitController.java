package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.model.RecruitRecord;
import com.pieces.dao.vo.RecruitAgentVo;
import com.pieces.dao.vo.RecruitRecordVo;
import com.pieces.service.RecruitAgentService;
import com.pieces.service.RecruitRecordService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xiao on 2017/1/12.
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController {


    @Autowired
    private RecruitAgentService recruitAgentService;

    @Autowired
    private RecruitRecordService recruitRecordService;


    @Autowired
    HttpSession httpSession;

    @RequiresPermissions(value = "recruit:index")
    @BizLog(type = LogConstant.recruit, desc = "合作伙伴列表")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Integer pageSize,
                        Integer pageNum,
                        RecruitAgentVo recruitAgentVo,
                        ModelMap model){
        PageInfo<RecruitAgentVo> recruitPage = recruitAgentService.findByParams(recruitAgentVo, pageNum, pageSize);
        String params =  Reflection.serialize(recruitAgentVo);
        model.put("params",params);
        model.put("recruitPage",recruitPage);
        model.put("recruitAgentVo",recruitAgentVo);
        return "recruit-list";
    }

    @RequiresPermissions(value = "recruit:detail")
    @BizLog(type = LogConstant.recruit, desc = "合作伙伴详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Integer id, ModelMap model){
        RecruitAgentVo vo = recruitAgentService.findVoById(id);
        model.put("vo", vo);
        return "recruit-detail";
    }

    @RequiresPermissions(value = "recruit:trail")
    @BizLog(type = LogConstant.recruit, desc = "合作伙伴跟踪")
    @RequestMapping(value = "/trail", method = RequestMethod.GET)
    @SecurityToken(generateToken = true)
    public String trail(Integer recruitAgentId, ModelMap model){
        List<RecruitRecordVo> list =  recruitRecordService.findByRecruitId(recruitAgentId);
        RecruitAgent recruitAgent=recruitAgentService.findById(recruitAgentId);
        model.put("agent", recruitAgent);
        model.put("list", list);
        return "recruit-trail";
    }

    @RequiresPermissions(value = "recruit:trail")
    @BizLog(type = LogConstant.recruit, desc = "保存跟踪记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @SecurityToken(validateToken=true)
    public Result trailSave(RecruitRecordVo record){
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        record.setFollowId(mem.getId());
        recruitRecordService.save(record);
        return new Result(true).info("创建成功");
    }



}
