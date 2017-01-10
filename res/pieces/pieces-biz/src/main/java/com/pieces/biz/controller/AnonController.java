package com.pieces.biz.controller;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.pieces.dao.vo.AnonEnquiryVo;
import com.pieces.service.AnonEnquiryService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.TempUploadFile;
import com.pieces.tools.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;

/**
 * Author: koabs
 * 11/16/16.
 * 陌生人询价
 */
@Controller
@RequestMapping("/anon")
public class AnonController {

    Logger logger = LoggerFactory.getLogger(AnonController.class);

    @Autowired
    private TempUploadFile tempUploadFile;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AnonEnquiryService anonEnquiryService;

    private final static ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();

    static {
        //生成验证码
        captchaService.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        captchaService.setFilterFactory(new CurvesRippleFilterFactory(captchaService.getColorFactory()));
        //设置随机验证码的位数 --
        RandomWordFactory randomWordFactory = new RandomWordFactory();
        randomWordFactory.setMaxLength(4);
        randomWordFactory.setMinLength(4);
        captchaService.setWordFactory(randomWordFactory);
    }

    @RequestMapping(value = "/enquiry", method = RequestMethod.GET)
    @SecurityToken(generateToken = true)
    public String index(){
        return "anon_enquiry";
    }


    /**
     * 提交询价信息
     * @return
     */
    @RequestMapping(value = "/enquiry", method = RequestMethod.POST)
    @ResponseBody
    @SecurityToken(validateToken=true)
    public Result create(@Valid @RequestBody AnonEnquiryVo enquiry, String captcha){
        Result result = null;

        String code = (String)httpSession.getAttribute(BasicConstants.CAPTCHA_ANON_ENQUIRY_KEY);

        if (!StringUtils.isEmpty(code) && code.equalsIgnoreCase(captcha)){
            result = anonEnquiryService.save(enquiry);
            // 通知管理员
            SpringUtil.getApplicationContext().
                    publishEvent(new NotifyEvent(NotifyTemplateEnum.anon.getTitle(String.valueOf(enquiry.getId())),
                            NotifyTemplateEnum.anon.getContent(String.valueOf(enquiry.getId()))));
        } else {
            result = new Result(false).info("验证码错误");
        }

        return result;
    }


    /**
     * 上传文件
     * @param file
     * @throws Exception
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Result fileUpload(@RequestParam(required = true) MultipartFile file) throws IOException {
        FileBo fileBo = tempUploadFile.uploadFile(file.getOriginalFilename(), file.getInputStream());
        fileBo.setName(file.getOriginalFilename());
        return new Result(true).data(fileBo);
    }


    /**
     * 图片验证码
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/captcha")
    public void captcha(HttpServletResponse response) throws Exception {
        //设置不缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        //生成验证码
        Captcha captcha = captchaService.getCaptcha();
        ServletOutputStream out = response.getOutputStream();
        try {
            String code = captcha.getChallenge();
            httpSession.setAttribute(BasicConstants.CAPTCHA_ANON_ENQUIRY_KEY, code);
            logger.info("生成的验证码为:" + code);
            ImageIO.write(captcha.getImage(), "png", out);
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 询价成功页面
     * @return
     */
    @RequestMapping(value = "/enquirySuccess")
    public String enquiryMessage(){
        return "anno_message_enquiry";
    }



}
