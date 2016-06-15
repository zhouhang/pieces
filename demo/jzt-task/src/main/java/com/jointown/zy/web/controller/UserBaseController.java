package com.jointown.zy.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.WebConstatVar;

public class UserBaseController {

    /** 用户帐号在request中的key */
    public static final String PassportAttKey = "passport";
    private static final Logger LOG = LoggerFactory.getLogger(UserBaseController.class);

    /**
     * 设置不要缓存数据
     * 
     * @param response
     */
    public void setNoCache(HttpServletResponse response)
    {
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
    }

    /** 写入信息 */
    private void write(String contentType, HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	resp.setContentType(contentType);
	OutputStreamWriter out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
	out.write(msg);
	resp.setContentLength(msg.getBytes("UTF-8").length);
	out.flush();
    }

    protected void writePlain(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	write("text/plain; charset=UTF-8", req, resp, msg);
    }

    protected void writeHtml(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	write("text/html; charset=UTF-8", req, resp, msg);
    }

    protected void writeJavaScript(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	write("application/x-javascript; charset=\"UTF-8\"", req, resp, msg);
    }

    protected void writeJson(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	write("application/json; charset=\"UTF-8\"", req, resp, msg);
    }

    protected void writeXml(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException
    {
	write("application/xml; charset=\"UTF-8\"", req, resp, msg);
    }

    protected void writeFile(HttpServletRequest req, HttpServletResponse resp, String fileName, InputStream in) throws ServletException, IOException
    {
	resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	// resp.addHeader("Content-Length", "" + data.length);
	// resp.setContentType("application/octet-stream; charset=\"UTF-8\"");
	resp.setContentType("application/x-zip-compressed; charset=\"UTF-8\"");
	writeImage(req, resp, in);
    }

    protected void writeExcel(HttpServletRequest req, HttpServletResponse resp, String fileName, OutputStream out) throws ServletException, IOException
    {
	resp.setHeader("Content-Disposition", "attachment; filename=stat_data.xls");
	resp.setHeader("Content-Type", "application/vnd.ms-excel");
	resp.setContentType("application/vnd.ms-excel; charset=\"UTF-8\"");
	out.flush();
	out.close();
    }

    protected void writeImage(HttpServletRequest req, HttpServletResponse resp, InputStream imageIn) throws ServletException, IOException
    {
	OutputStream output = resp.getOutputStream();
	BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
	BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
	byte data[] = new byte[4096];// 缓冲字节数
	int size = 0;
	size = bis.read(data);
	while (size != -1)
	{
	    bos.write(data, 0, size);
	    size = bis.read(data);
	}
	bis.close();
	bos.flush();// 清空输出缓冲流
	bos.close();

	output.close();
    }

    /**
     * 判断字符串是否是数字和字母组成
     * 
     * @param str
     * @return
     */
    public boolean isLegalString(String str)
    {
	return str != null && str.matches("^[0-9|a-z|A-Z]+$");
    }

    /**
     * 判断passport是否合法
     * 
     * @param passport
     * @return
     */
    public boolean isLegalPassport(String passport)
    {
	return passport != null && passport.matches("^[^'\"`]{4,20}$");
    }

    public String getNotNull(String key, HttpServletRequest req)
    {
	String value = req.getParameter(key);
	return (value == null ? "" : value);
    }

    /**
     * 返回登录的通行证
     * 
     * @param request
     * @param response
     * @return
     */
    public static String getRequestPassport(HttpServletRequest request, boolean needExist)
    {
	String passport = (String) request.getAttribute(PassportAttKey);
	if (StringUtils.isEmpty(passport) && needExist)
	    throw new RuntimeException("not found request attribute " + PassportAttKey);
	return passport;
    }

    /**
     * 设置登陆的通行证
     * 
     * @param request
     * @param passport
     */
    public static void setLoginedPassport(HttpServletRequest request, String passport)
    {
	request.setAttribute(PassportAttKey, passport);
    }

    /**
     * 通过request文件头拿到真实IP地址
     */
    public static String getRealIp(HttpServletRequest request)
    {
	String ip = request.getHeader("X-Forwarded-For");
	if (StringUtils.isEmpty(ip))
	{
	    return request.getRemoteAddr();
	}
	ip = ip.split(", ")[0].trim();
	if ("127.0.0.1".equals(ip) || !isLicitIp(ip))
	{
	    return request.getRemoteAddr();
	}
	return ip;
    }

    public static boolean isLicitIp(String ip)
    {
	if (StringUtils.isEmpty(ip))
	    return false;
	String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
	Pattern p = Pattern.compile(regex);
	Matcher m = p.matcher(ip);
	return m.find();
    }

    public int getPageNum(HttpServletRequest request)
    {
	try
	{
	    return Integer.parseInt(request.getParameter("pagenum"));
	} catch (NumberFormatException ne)
	{
	    return 1;
	}
    }

    public void setErrorMessage(HttpServletRequest request, String msg)
    {
	request.setAttribute(WebConstatVar.ERROR_MSG, msg);
	LOG.info("[UserBaseController][setErrorMessage]msg="+msg);
    }
}
