package com.pieces.biz.controller;

import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.User;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.CertifyRecordService;
import com.pieces.service.UserCertificationService;
import com.pieces.service.UserQualificationService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.utils.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by xiao on 2016/11/16.
 */
//用户认证
@Controller
@RequestMapping(value = "/center/certificate")
public class UserCertificateController {


    @Autowired
    HttpSession httpSession;

    @Autowired
    UserQualificationService userQualificationService;

    @Autowired
    UserCertificationService userCertificationService;

    @Autowired
    CertifyRecordService certifyRecordService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/stepOne", method = RequestMethod.GET)
    public String stepOne(){
        return "certificate_1";
    }

    @RequestMapping(value = "/stepTwo", method = RequestMethod.GET)
    @SecurityToken(generateToken = true)
    public String stepTwo(ModelMap model){
        UserCertificationVo certificationVo=( UserCertificationVo)httpSession.getAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue());
        if(certificationVo==null){
            return "redirect:error/404";
        }
        model.put("certificationVo",certificationVo);
        return "certificate_2";
    }

    @RequestMapping(value = "/stepThree", method = RequestMethod.GET)
    public String stepThree(){
        return "certificate_3";
    }

    @RequestMapping(value = "/stepOne", method = RequestMethod.POST)
    @ResponseBody
    public Result stepOnePost(@Valid UserCertificationVo userCertificationVo){
        String status=null;
        String info="提交成功";
        if(!StringUtils.isNotBlank(userCertificationVo.getCompany())){
              status="20001";
              info="企业名称不能为空";

        }
        else if(!StringUtils.isNotBlank(userCertificationVo.getCorporation())){
            status="20002";
            info="企业负责人不能为空";
        }
        else if(!StringUtils.isNotBlank(userCertificationVo.getAddress())){
            status="20003";
            info="企业所在地不能为空";
        }
        else if(userCertificationVo.getType()==null){
            status="20004";
            info="企业类型不能为空";
        }
        if(status==null){
            httpSession.setAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue(),userCertificationVo);
            return new Result(true).info(info);
        }
        else{
            return new Result(status).info(info);
        }

    }

    @RequestMapping(value = "/stepTwo", method = RequestMethod.POST)
    @ResponseBody
    @SecurityToken(validateToken=true)
    public Result stepTwoPost(@RequestBody List<UserQualificationVo> userQualificationVos){
        UserCertificationVo certificationVo=( UserCertificationVo)httpSession.getAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue());
        User user = (User)httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(certificationVo==null){
            return new Result(false).info("session 过期");
        }
        CertifyRecord certifyRecord=new CertifyRecord();
        certifyRecord.setUserId(user.getId());
        certifyRecord.setUserName(user.getUserName());
        certifyRecord.setCreateTime(new Date());
        certifyRecord.setStatus(CertifyRecordStatusEnum.NOT_HANDLE.getValue());
        certifyRecordService.saveRecord(certifyRecord,certificationVo,userQualificationVos);
        httpSession.removeAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue());
        // 通知管理员有新的资质审核请求提交
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.certify.getTitle(String.valueOf(certificationVo.getId())),
                        NotifyTemplateEnum.certify.getContent(certificationVo.getCompany(),time.format(new Date())),NotifyTemplateEnum.certify.getType(),certificationVo.getRecordId()));

        return new Result(true).info("提交成功");
    }






}
