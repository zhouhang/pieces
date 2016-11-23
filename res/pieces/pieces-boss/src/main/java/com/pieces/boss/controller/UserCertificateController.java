package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.CertifyRecordService;
import com.pieces.service.UserCertificationService;
import com.pieces.service.UserQualificationService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by xiao on 2016/11/17.
 */
@Controller
@RequestMapping(value = "/certify")
public class UserCertificateController {

    @Autowired
    CertifyRecordService certifyRecordService;

    @Autowired
    UserCertificationService userCertificationService;

    @Autowired
    UserQualificationService userQualificationService;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @RequiresPermissions(value = "certify:index" )
    @RequestMapping(value = "/list")
    @BizLog(type = LogConstant.certify, desc = "资质审核列表")
    public String certifyIndex(
                            Integer pageNum,
                            Integer pageSize,
                            CertifyRecordVo certifyRecordVo,
                            ModelMap model) {

        PageInfo<CertifyRecordVo> certifyRecordVoPageInfo = certifyRecordService.findByParams(certifyRecordVo,pageNum,pageSize);
        model.put("pageInfo",certifyRecordVoPageInfo);
        model.put("certifyParams", Reflection.serialize(certifyRecordVo));

        return "certify_list";
    }
    @RequiresPermissions(value = "certify:info" )
    @RequestMapping(value = "/info/{id}")
    @BizLog(type = LogConstant.certify, desc = "资质审核详情")
    public String certifyInfo(
                           @PathVariable("id") Integer id,
                           ModelMap model){

        UserCertificationVo userCertification=new UserCertificationVo();
        userCertification.setRecordId(id);
        UserQualificationVo userQualification=new UserQualificationVo();
        userQualification.setRecordId(id);



        model.put("userCertification",userCertificationService.findAll(userCertification));
        model.put("userQualification",userQualificationService.findAll(userQualification));
        model.put("certifyRecord",certifyRecordService.findById(id));



        return "certify_info";
    }
    //审核认证信息
    @RequiresPermissions(value = "certify:handle" )
    @RequestMapping(value = "/handle",method = RequestMethod.POST)
    @ResponseBody
    @BizLog(type = LogConstant.certify, desc = "资质审核")
    public Result handleCertify(CertifyRecordVo certifyRecordVo){

           Boolean status=true;
           String info="提交成功";
           Member mem = (Member)httpSession.getAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue());
           certifyRecordVo.setFolllowId(mem.getId());
           certifyRecordVo.setFollowName(mem.getName());
           certifyRecordVo.setFollowTime(new Date());

           if(certifyRecordVo.getStatus()== CertifyRecordStatusEnum.CERTIFY_FAIL.getValue()){
               certifyRecordService.update(certifyRecordVo);
           }else if(certifyRecordVo.getStatus()== CertifyRecordStatusEnum.CERTIFY_SUCESS.getValue()){
              //是否已经有验证通过的记录
               User user=userService.findById(certifyRecordVo.getUserId());
               if(user.getCertifyStatus()== CertifyStatusEnum.CERTIFY_SUCESS.getValue()){
                   status=false;
                   info="已经通过认证";
               }
               else{
                   certifyRecordService.passCertify(certifyRecordVo);
               }

           }
           return new Result(status).info(info);
    }




}
