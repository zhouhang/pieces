package com.jointown.zy.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @ClassName: FileUtil
 * @Description: 文件处理工具类
 * @Author: fanyuna
 * @Date: 2015年10月13日
 * @Version: 1.0
 */
public class FileUtil {
	
	/**
	 * 
	 * @Description: 下载文件到本机
	 * @Author: fanyuna
	 * @Date: 2015年10月15日
	 * @param filePath 文件路径 
	 * @param response
	 * @param fileName 文件名
	 * @param fileType 文件类型
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoadFile(String filePath,
	        HttpServletResponse response, String fileName, String fileType)
	        throws Exception {
	        File file = new File(filePath);  //根据文件路径获得File文件
	        //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
	        if("pdf".equals(fileType)){
	           response.setContentType("application/pdf;charset=GBK");
	        }else if("xls".equals(fileType)||"xlsx".equals(fileType)){
	           response.setContentType("application/msexcel;charset=GBK");
	        }else if("doc".equals(fileType)||"docx".equals(fileType)){
	           response.setContentType("application/msword;charset=GBK");
	        }
	        //文件名
//	        response.setHeader("Content-Disposition", "attachment;filename=\""
//	            + new String(fileName.getBytes(), "ISO8859-1") + "\"");
	        
	        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
	        response.setContentLength((int) file.length());
	        byte[] buffer = new byte[4096];// 缓冲区
	        BufferedOutputStream output = null;
	        BufferedInputStream input = null;
	        try {
	          output = new BufferedOutputStream(response.getOutputStream());
	          input = new BufferedInputStream(new FileInputStream(file));
	          int n = -1;
	          //遍历，开始下载
	          while ((n = input.read(buffer, 0, 4096)) > -1) {
	             output.write(buffer, 0, n);
	          }
	          output.flush();   //不可少
	          response.flushBuffer();//不可少
	        } catch (Exception e) {
	        	throw e;
	          //异常自己捕捉       
	        } finally {
	           //关闭流，不可少
	           if (input != null)
	                input.close();
	           if (output != null)
	                output.close();
	        }
	       return false;
	    }
	
	
	
	public static void main(String[] args) {
		String filePath = "D:\\fanyuna\\workspace_jy\\jointown_20151011\\jzt-user-center\\src\\main\\webapp\\purchase\\批量采购模版.xls";
		File file = new File(filePath);
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			System.out.println(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 
}
