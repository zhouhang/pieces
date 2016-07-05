package com.pieces.biz.controller;

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
import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.DefaultUploadFile;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 公共URL访问
 * Created by wangbin on 2016/6/27.
 */
@Controller
@RequestMapping(value = "gen")
public class GeneralController {

    @Autowired
    private SmsService smsService;


    public static ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();

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

    @RequestMapping(value = "/file/index")
    public String index() {
        return "fileUploadTest";
    }


    @RequestMapping(value = "/file/upload")
    public void fileUpload(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(required = false) MultipartFile file)throws Exception {
        FileBo fileBo = defaultUploadFile.uploadFile(file.getOriginalFilename(), file.getInputStream());

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

    /**
     * 发送验证码测试
     * @throws Exception
     */
    @RequestMapping(value = "/send")
    public void sendPost(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(required = true) String mobile) throws Exception{
        String code =  smsService.sendSmsCaptcha("18801285391");
        Result result =  new Result(true).data(Collections.singletonMap("code",code)).info("短信验证码");
        WebUtil.print(response,result);
    }

}
