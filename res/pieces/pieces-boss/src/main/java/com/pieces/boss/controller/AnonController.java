package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonEnquiry;
import com.pieces.dao.model.AnonFollowRecord;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.dao.vo.AnonFollowRecordVo;
import com.pieces.service.AnonEnquiryService;
import com.pieces.service.AnonFollowRecordService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.AnonEnquiryEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 11/17/16.
 * 新客询价管理
 */
@Controller
@RequestMapping("/anon")
public class AnonController {

    @Autowired
    private AnonEnquiryService anonEnquiryService;

    @Autowired
    private AnonFollowRecordService followRecordService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/enquiry", method = RequestMethod.GET)
    public String index(Integer pageSize,
                        Integer pageNum,
                        AnonEnquiryVo anonEnquiryVo,
                        ModelMap model){

        PageInfo<AnonEnquiryVo> anonPage = anonEnquiryService.findByParams(anonEnquiryVo, pageNum, pageSize);
        String params =  Reflection.serialize(anonEnquiryVo);
        model.put("params",params);
        model.put("anonPage",anonPage);
        model.put("anonVo", anonEnquiryVo);
        return "anon_enquiry";
    }

    /**
     * 询价详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Integer id, ModelMap model){
        AnonEnquiryVo vo = anonEnquiryService.findVoById(id);
        model.put("vo", vo);
        return "anon_enquiry_detail";
    }

    /**
     * 跟踪记录
     * @param anonId
     * @return
     */
    @RequestMapping(value = "/trail", method = RequestMethod.GET)
    public String trail(Integer anonId, ModelMap model){
       List<AnonFollowRecordVo> list =  followRecordService.findByAnonId(anonId);

        model.put("anonId", anonId);
        model.put("list",list);
        return "anon_enquiry_trail";
    }

    /**
     * 保存跟踪记录
     * @return
     */
    @RequestMapping(value = "/trail", method = RequestMethod.POST)
    @ResponseBody
    public Result trailSave(AnonFollowRecord record){
        Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
        record.setFollowerId(mem.getId());
        record.setCreateTime(new Date());
        followRecordService.create(record);
        // 修改询价单状态为已处理
        AnonEnquiry anonEnquiry = new AnonEnquiry();
        anonEnquiry.setId(record.getAnonEnquiryId());
        anonEnquiry.setStatus(AnonEnquiryEnum.COMPLETE.getValue());
        anonEnquiry.setLastFollowId(mem.getId());
        anonEnquiry.setLastFollowTime(new Date());
        anonEnquiryService.update(anonEnquiry);
        return new Result(true).info("创建成功");
    }

}
