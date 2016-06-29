package com.jointown.zy.web.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sun.awt.image.JPEGImageDecoder;

import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.service.CertifyImgService;
import com.jointown.zy.common.util.ValidationCodeUtil;
import com.jointown.zy.common.util.ValidationCodeUtil.ValidatorCode;
import com.jointown.zy.common.vo.MessageVo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.jndi.toolkit.url.Uri;


/**
 * 验证图片
 * 
 */
@Controller(value = "VerifyImgController")
public class VerifyImgController  extends UserBaseController{
	
	private static final Logger log = LoggerFactory.getLogger(VerifyImgController.class);
	
	@Autowired
	public CertifyImgService certifyImgService;

	@RequestMapping(value="/verifyImg",method=RequestMethod.GET)  
	@ResponseBody
    public String  verifyImg(HttpServletRequest request,HttpServletResponse response,ModelMap model){ 
		Integer certifyId =Integer.valueOf(request.getParameter("certifyId"));
		Integer type = Integer.valueOf(request.getParameter("type"));
		CertifyImg img=certifyImgService.findCertifyImgByCertifyIdAndType(certifyId, type);
		if(img==null) {
			return null;
		}
		URL url =null;
		try {
			 url = new URL(img.getPath());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpg");
        ServletOutputStream sos = null;
        try {
            // 将图像输出到Servlet输出流中。
            sos = response.getOutputStream();
            BufferedImage bf = ImageIO.read(url);
            ImageIO.write(bf, "jpg" , sos ); 
            sos.flush();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        } finally {
            if (null != sos) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		return null;
    } 
	
}
