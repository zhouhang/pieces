package com.pieces.biz.controller;

import com.pieces.dao.enums.CertifyTypeEnum;
import com.pieces.dao.enums.QualificationTypeEnum;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.CompanyInfoVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.UserCertificationService;
import com.pieces.service.UserQualificationService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiao on 2016/12/13.
 * ERP对接接口
 */

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private UserCertificationService userCertificationService;

    @Autowired
    private UserQualificationService userQualificationService;

    @Autowired
    private UserService userService;

    private final static String secret="fe68a821107c34bf9863348831e26755";



    @RequestMapping(value = "/company/info")
    @ResponseBody
    public Result companyInfo(String timestamp,  String auth) {

        /**
         *验证签名
         */

        String  sig=EncryptUtil.getMD5(timestamp+secret,"UTF-8");
        if(!(sig.equals(auth))){
            return new Result("500").info("非法访问");
        }
        Date date = new Date();
        if(timestamp.equals("0")){
            date=null;
        }
        else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String str=sdf.format(Long.parseLong(timestamp));
            try {
                date=sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Result("500").info("timestamp转换错误");
            }
        }


        List<UserVo> userVos=userService.findUpdateUser(date);
        List<CompanyInfoVo> retList=new ArrayList<>();
        for(UserVo userVo :userVos){
            CompanyInfoVo companyInfoVo =new CompanyInfoVo();
            companyInfoVo.setCompany(userVo.getCompanyFullName());
            companyInfoVo.setId(userVo.getId());
            companyInfoVo.setContacts_mobile(userVo.getContactMobile());
            companyInfoVo.setSeller(userVo.getServiceName());
            /**
             * 获取企业资质
             */
            UserCertificationVo param =new UserCertificationVo();
            param.setUserId(userVo.getId());

            UserCertificationVo userCertificationVo=userCertificationService.findAll(param);
            companyInfoVo.setAddress(userCertificationVo.getAddress());
            companyInfoVo.setCompany_type(userCertificationVo.getTypeText());
            companyInfoVo.setCorporation(userCertificationVo.getCorporation());
            companyInfoVo.setTimestamp(userVo.getUpdateTime().getTime());


            UserQualificationVo param1=new UserQualificationVo();
            param1.setUserId(userVo.getId());
            /**
             * 默认值
             */
            companyInfoVo.setEntrust_licence("采购人员授权委托书");
            companyInfoVo.setEntrust_licence_expire("");

            List<UserQualificationVo> userQualificationVos=userQualificationService.findAll(param1);
            for(UserQualificationVo userQualificationVo:userQualificationVos){
                if(userQualificationVo.getType()==QualificationTypeEnum.LIENSE_1.getValue()){
                    companyInfoVo.setBusiness_licence(userQualificationVo.getNumber());
                    companyInfoVo.setBusiness_licence_expire(userQualificationVo.getTerm());
                }
                else if(userQualificationVo.getType()==QualificationTypeEnum.LIENSE_2.getValue()){
                    companyInfoVo.setGsp_licence(userQualificationVo.getNumber());
                    companyInfoVo.setGsp_licence_expire(userQualificationVo.getTerm());
                }
                else if(userQualificationVo.getType()==QualificationTypeEnum.LIENSE_7.getValue()){
                    companyInfoVo.setEntrust_licence("采购人员授权委托书");
                    companyInfoVo.setEntrust_licence_expire(userQualificationVo.getTerm());
                }
                else if(userQualificationVo.getType()==QualificationTypeEnum.LIENSE_6.getValue()){
                    if(userCertificationVo.getType()!= CertifyTypeEnum.SINGLE_DRUGSTORE.getValue()&&userCertificationVo.getType()!= CertifyTypeEnum.CHAIN_DRUGSTORE.getValue()){
                        companyInfoVo.setMedical_licence(userQualificationVo.getNumber());
                        companyInfoVo.setMedical_licence_expire(userQualificationVo.getTerm());
                    }

                }
                else if(userQualificationVo.getType()==QualificationTypeEnum.LIENSE_3.getValue()){
                    if(userCertificationVo.getType()== CertifyTypeEnum.SINGLE_DRUGSTORE.getValue()||userCertificationVo.getType()== CertifyTypeEnum.CHAIN_DRUGSTORE.getValue()){
                        companyInfoVo.setMedical_licence(userQualificationVo.getNumber());
                        companyInfoVo.setMedical_licence_expire(userQualificationVo.getTerm());
                    }
                }
            }

            retList.add(companyInfoVo);
        }


        HashMap<String,Object> ret=new HashMap<String,Object>();

        ret.put("total",retList.size());
        ret.put("list",retList);

        return new Result("200").data(ret);

    }





}
