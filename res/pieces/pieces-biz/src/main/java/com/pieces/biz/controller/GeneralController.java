package com.pieces.biz.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pieces.dao.exception.SmsOverException;
import com.pieces.service.CommodityService;
import com.pieces.service.impl.CreateHtmlService;
import com.pieces.service.vo.CropResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.pieces.dao.model.Area;
import com.pieces.service.AreaService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.impl.SmsService;
import com.pieces.tools.upload.DefaultUploadFile;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * 公共URL访问
 * Created by wangbin on 2016/6/27.
 */
@Controller
@RequestMapping(value = "gen")
public class GeneralController extends BaseController {

    Logger logger = LoggerFactory.getLogger(GeneralController.class);


    @Autowired
    private SmsService smsService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CreateHtmlService createHtmlService;


    private final static ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();

    static {
        //生成验证码
        captchaService.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        captchaService.setFilterFactory(new CurvesRippleFilterFactory(captchaService.getColorFactory()));
        //设置随机验证码的位数
        RandomWordFactory randomWordFactory = new RandomWordFactory();
        randomWordFactory.setMaxLength(4);
        randomWordFactory.setMinLength(4);
        captchaService.setWordFactory(randomWordFactory);
    }


    @Autowired
    private DefaultUploadFile defaultUploadFile;
    @Autowired
    private AreaService areaService;



    @RequestMapping(value = "/file/upload")
    public void fileUpload(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(required = false) MultipartFile file)throws Exception {
        defaultUploadFile.uploadFile(file.getOriginalFilename(), file.getInputStream());
    }

    /**
     * 上传图片
     * @param request
     * @param response
     * @param img
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/img/upload")
    @ResponseBody
    public CropResult imgUpload(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(required = false) MultipartFile img)throws Exception {
        return commodityService.uploadImage(img);
    }


    /**
     * 省市区接口
     * @param request
     * @param response
     * @param parentId
     */
    @RequestMapping(value = "/area")
    public void area(HttpServletRequest request,
                     HttpServletResponse response,
                     @RequestParam(required = false) Integer parentId) {
        List<Area> areaList = null;
        //父类ID为空，查询全部省
        if (parentId == null) {
            areaList = areaService.findByLevel(1);
        } else {
            areaList = areaService.findByParent(parentId);
        }

        String result = GsonUtil.toJsonInclude(areaList, "id", "areaname");
        WebUtil.printJson(response, result);
    }


    /**
     * 图片验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/captcha")
    public void captcha(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
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
            session.setAttribute(BasicConstants.KAPTCHA_SESSION_KEY, code);
            System.out.println("生成的验证码为:" + code);
            ImageIO.write(captcha.getImage(), "png", out);
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/captcha/get")
    public void captchaGet(HttpServletRequest request,
                           HttpServletResponse response){
        HttpSession session = request.getSession();
        String code = session.getAttribute(BasicConstants.KAPTCHA_SESSION_KEY).toString();
        WebUtil.printJson(response, new Result(true).data(code));
    }


	@RequestMapping(value="/code")
	public void getMobileCode(String contactMobile,
						HttpServletRequest request,
						HttpServletResponse response){
        try {
            smsService.sendSmsCaptcha(contactMobile);
        } catch (Exception e) {
            Map<String, String> result = new HashMap<String, String>();
            result.put("error", "该手机号短信发送次数超标!");
            WebUtil.print(response,result);
            return;
        }
        Map<String, String> result = new HashMap<String, String>();
		result.put("ok", "ok");
        WebUtil.print(response,result);
	}


    @RequestMapping(value="/home/create")
    public void createHtml(HttpServletRequest request){
        WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(request);
        FreeMarkerConfigurer freeMarkerConfigurer =  (FreeMarkerConfigurer) webApplicationContext.getBean("freemarkerConfig");
        createHtmlService.createHomePage(freeMarkerConfigurer);
    }




}
