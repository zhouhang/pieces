package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.dao.vo.AnonFollowRecordVo;
import com.pieces.service.AnonEnquiryService;
import com.pieces.service.AnonFollowRecordService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.AnonEnquiryEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.FileUtil;
import com.pieces.tools.utils.Reflection;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 11/17/16.
 * 新客询价管理
 */
@Controller
@RequestMapping("/anon")
public class AnonController extends BaseController{

    @Autowired
    private AnonEnquiryService anonEnquiryService;

    @Autowired
    private AnonFollowRecordService followRecordService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserService userService;

    @Autowired
    EnquiryBillsService enquiryBillsService;

    /**
     * 新客询价
     * @param pageSize
     * @param pageNum
     * @param anonEnquiryVo
     * @param model
     * @return
     */
    @RequiresPermissions(value = "anon:enquiry")
    @BizLog(type = LogConstant.anon, desc = "新客询价列表")
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
    @RequiresPermissions(value = "anon:detail")
    @BizLog(type = LogConstant.anon, desc = "新客询价详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Integer id, ModelMap model){
        AnonEnquiryVo vo = anonEnquiryService.findVoById(id);
        model.put("vo", vo);
        if(vo.getEnquriyBillId()!=null){
           EnquiryBills enquiryBills =enquiryBillsService.findById(vo.getEnquriyBillId());
           vo.setEnquriyBillCode(enquiryBills.getCode());
        }
        User u=userService.findByAccount(vo.getPhone());
        List<AnonFollowRecordVo> list =  followRecordService.findByAnonId(id);
        if(list.size()!=0){
            model.put("record",list.get(list.size()-1));
        }

        if(u!=null){
            model.put("userId",u.getId());
        }
        return "anon_enquiry_detail";
    }

    /**
     * 跟踪记录
     * @param anonId
     * @return
     */
    @RequiresPermissions(value = "anon:trail")
    @BizLog(type = LogConstant.anon, desc = "跟踪记录")
    @RequestMapping(value = "/trail", method = RequestMethod.GET)
    @SecurityToken(generateToken = true)
    public String trail(Integer anonId, ModelMap model){
       List<AnonFollowRecordVo> list =  followRecordService.findByAnonId(anonId);
        AnonEnquiry anon = anonEnquiryService.findById(anonId);
        model.put("anon", anon);
        model.put("list",list);
        return "anon_enquiry_trail";
    }

    /**
     * 保存跟踪记录
     * @return
     */
    @RequiresPermissions(value = "anon:trail")
    @BizLog(type = LogConstant.anon, desc = "保存跟踪记录")
    @RequestMapping(value = "/trail", method = RequestMethod.POST)
    @ResponseBody
    @SecurityToken(validateToken=true)
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



    /**
     * 匿名询价下载文件
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request, String url, String fileName) throws IOException {
        String path = FileUtil.getAbsolutePath(url);

        String ext = FileUtil.getFileExt(path);
        fileName = fileName + ext;
        File file = new File(path);
        if (!file.exists()) throw new RuntimeException("文件不存在");

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");

        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0 ||
                request.getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }

        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        InputStream inputStream = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        try {
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.flush();
        } finally {
            // 这里主要关闭。
            os.close();
            inputStream.close();
        }
    }

}
