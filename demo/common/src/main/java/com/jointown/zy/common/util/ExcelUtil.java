package com.jointown.zy.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * Excel导出工具类
 * 主要处理excel文件名
 * excel字体格式化
 * 只针对使用JXL.jar
 * @ClassName:ExcelUtil
 * @author:Calvin.Wangh
 * @date:2015-7-22下午5:15:34
 * @version V1.0
 * @Description:
 */
public class ExcelUtil {
	
	
	/**
	 * 生成文件名
	 * @param fileName
	 * @return
	 */
	public static String createExcelName(String fileName){
		String name = fileName +"_" + TimeUtil.formatDatetime(new Date(), "yyyyMMddHHmmss") + ".xls";
		return name;
	}
	
	/**
	 * 设置中文标题输出属性
	 * @param response
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static HttpServletResponse setResponseProperties(HttpServletResponse response,String fileName) throws UnsupportedEncodingException{
		response.reset();
		response.setHeader("Content-Disposition", new String(("attachment; filename=" + fileName).getBytes("GBK"), "ISO8859-1"));
		response.setContentType("application/vnd.ms-excel");
		return response;
	}
	
	/**
	 * 设置标题字体以及格式
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getTitleFormat() throws WriteException {
		// 标题字体及样式--居中
		WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"), 10, 
				WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfTitle = new WritableCellFormat(writableFont);
		
		wcfTitle.setAlignment(Alignment.CENTRE);
		//水平居中
		wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
		wcfTitle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
		wcfTitle.setWrap(true);//自动换行
		return wcfTitle;
	}
	
	/**
	 * 设置内容字体以及格式
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getContentFormat() throws WriteException{
		// 内容字体及样式--居中
		WritableFont wfContentCenter = new WritableFont(WritableFont.createFont("宋体"), 10, 
				WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfContentCenter = new WritableCellFormat(wfContentCenter);
		wcfContentCenter.setAlignment(Alignment.CENTRE);
		wcfContentCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcfContentCenter.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
		wcfContentCenter.setWrap(true);
		return wcfContentCenter;
	}
	
	/**
	 * 设置内容字体以及格式 无边框
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat contentFormatNoBorder() throws WriteException{
		// 内容字体及样式--居中
		WritableFont wfContentCenter = new WritableFont(WritableFont.createFont("宋体"), 10, 
				WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfContentCenter = new WritableCellFormat(wfContentCenter);
		wcfContentCenter.setAlignment(Alignment.CENTRE);
		wcfContentCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcfContentCenter.setWrap(true);
		return wcfContentCenter;
	}
	
	/**
	 * 格式化数字到两位小数
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getNumberFormat() throws WriteException {
		// 格式化数字
		WritableFont wfFont = new WritableFont(WritableFont.createFont("宋体"), 10, 
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		NumberFormat numberFormatTwo = new NumberFormat("0.00");
		WritableCellFormat wcfContentRightTwo = new WritableCellFormat(wfFont, numberFormatTwo);
		wcfContentRightTwo.setAlignment(Alignment.RIGHT);
		wcfContentRightTwo.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcfContentRightTwo.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
		wcfContentRightTwo.setWrap(true);
		return wcfContentRightTwo;
	}
	
	public static WritableCellFormat numberFormatNoBorder() throws WriteException {
		// 格式化数字
		WritableFont wfFont = new WritableFont(WritableFont.createFont("宋体"), 10, 
				WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		NumberFormat numberFormatTwo = new NumberFormat("0.00");
		WritableCellFormat wcfContentRightTwo = new WritableCellFormat(wfFont, numberFormatTwo);
		wcfContentRightTwo.setAlignment(Alignment.RIGHT);
		wcfContentRightTwo.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcfContentRightTwo.setWrap(true);
		return wcfContentRightTwo;
	}
	

}
