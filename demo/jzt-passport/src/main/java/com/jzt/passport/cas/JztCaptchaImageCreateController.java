package com.jzt.passport.cas;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jzt.passport.cas.ValidatorCodeUtil;
import com.jzt.passport.cas.ValidatorCodeUtil.ValidatorCode;


public class JztCaptchaImageCreateController implements Controller,
		InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	        ValidatorCode codeUtil = ValidatorCodeUtil.getCode();
	        request.getSession().setAttribute("vcode", codeUtil.getCode());
	        // 禁止图像缓存。
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg");
	        
	        ServletOutputStream sos = null;
	        try {
	            // 将图像输出到Servlet输出流中。
	            sos = response.getOutputStream();
	            BufferedImage bi = codeUtil.getImage();
	            ImageIO.write(bi, "jpeg" , sos ); 
	            sos.flush();
	        } catch (Exception e) {
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
