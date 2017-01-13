package com.pieces.biz.controller;

import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.vo.RecruitAgentVo;
import com.pieces.service.RecruitAgentService;
import com.pieces.service.constant.bean.Result;
import com.pieces.tools.annotation.SecurityToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by xiao on 2017/1/12.
 * 代理商招募
 */
@Controller
@RequestMapping(value = "/recruit/")
public class RecruitController {

    @Autowired
    private RecruitAgentService recruitAgentService;

    /**
     * 代理商招募主页
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    @SecurityToken(generateToken = true)
    public String index(ModelMap model)
    {

        model.put("CURRENT_PAGE","recruit");
        return "recruit_agent";
    }

    /**
     * 代理商提交
     * @param recruitAgentVo
     * @return
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    @SecurityToken(validateToken=true)
    public Result submit(RecruitAgentVo recruitAgentVo){
        recruitAgentVo.setCreateTime(new Date());
        recruitAgentVo.setStatus(0);
        recruitAgentService.create(recruitAgentVo);
        return new Result(true).info("提交成功");
    }



}
