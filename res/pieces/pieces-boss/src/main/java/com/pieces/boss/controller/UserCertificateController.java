package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.service.CertifyRecordService;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiao on 2016/11/17.
 */
@Controller
@RequestMapping(value = "/certify")
public class UserCertificateController {

    @Autowired
    CertifyRecordService certifyRecordService;

    @RequestMapping(value = "/list")
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

    @RequestMapping(value = "/info/{id}")
    public String certifyInfo(
                           @PathVariable("id") Integer id,
                           ModelMap model){
        return "certify_info";
    }


}
