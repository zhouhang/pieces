package com.pieces.boss.controller;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.pagehelper.PageInfo;
import com.pieces.boss.commons.UEditorResult;
import com.pieces.dao.model.Area;
import com.pieces.service.AreaService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.impl.SmsService;
import com.pieces.service.vo.CropInfo;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.PrintWriter;
import java.util.List;

/**
 * 公共URL访问
 * Created by wangbin on 2016/6/27.
 */
@Controller
@RequestMapping(value = "gen")
public class GeneralController {

    @Autowired
    private SmsService smsService;

    @Autowired
    CommodityService commodityService;


    public final static ConfigurableCaptchaService captchaService = new ConfigurableCaptchaService();

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
    private AreaService areaService;

    @RequestMapping(value = "/file/index")
    public String index() {
        return "public/fileUploadTest";
    }

    /**
     * 省市区接口
     * @param request
     * @param response
     * @param parentId
     */
    @RequestMapping(value = "/area")
    @ResponseBody
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


    @RequestMapping(value = "/area/page")
    public void areaPage(HttpServletRequest request,
                         HttpServletResponse response,
                         Integer pageNum,
                         Integer pageSize) {
        PageInfo<Area> page = areaService.find(pageNum, pageSize);
        String result = GsonUtil.toJson(page);
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

    @RequestMapping(value = "/captcha/get")
    public void captchaGet(HttpServletRequest request,
                           HttpServletResponse response){
        HttpSession session = request.getSession();
        String code = session.getAttribute(BasicConstants.KAPTCHA_SESSION_KEY).toString();
        System.out.println("code:"+code);
    }

    @RequestMapping(value = "/send")
    public void sendPost() throws Exception{
        String result =  smsService.sendSmsCaptcha("18801285391");
        System.out.println("result:"+result);

    }


    /**
     * 上传商品图片信息
     * @param img
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CropResult updateFile(@RequestParam(required = false) MultipartFile img) throws Exception{
        return commodityService.uploadImage(img);
    }

    /**
     * 图片裁剪
     * @return
     */
    @RequestMapping(value = "/clipping", method = RequestMethod.POST)
    @ResponseBody
    public CropResult clipping(CropInfo cropInfo) throws Exception{
        return commodityService.cropImg(cropInfo);
    }


    /**
     * 富文本编辑器上传图片方法
     * @param upfile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void updateUEditorFile(@RequestParam(required = false) MultipartFile upfile,HttpServletResponse response) throws Exception{
        CropResult cropResult = commodityService.uploadUeditorImage(upfile);
        // 设置响应头
        response.setContentType("text/html;charset=utf-8"); // 指定内容类型为 JSON 格式
        // 向响应中写入数据
        PrintWriter writer = response.getWriter();
        writer.write(GsonUtil.toJson(UEditorResult.success(upfile.getOriginalFilename(),upfile.getOriginalFilename(),cropResult.getUrl()))); // 转为 JSON 字符串
        writer.flush();
        writer.close();
    }


}