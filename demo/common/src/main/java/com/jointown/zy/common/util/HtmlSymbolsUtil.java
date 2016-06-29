/*
 * CommonUtil.java
 * Copyright (c) 2011,融众网络技术有限公司(www.11186.com)
 * All rights reserved.
 * ---------------------------------------------------------------------
 * 2011-3-28 Created
 */
package com.jointown.zy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName:HtmlSymbolsUtil
 * @author:Calvin.Wangh
 * @date:2015-7-24上午9:29:05
 * @version V1.0
 * @Description: Html标签元素过滤类 提供对HTML标签 JS代码过滤
 */
public class HtmlSymbolsUtil {
	
	// 过滤script标签
	private static	Pattern p_script;
	private	static  Matcher m_script;
	// 过滤style标签
	private static	Pattern p_style;
	private static	Matcher m_style;
	// 过滤html标签
	private static	Pattern p_html;
	private static	Matcher m_html;
	// 过滤html标签
	private static	Pattern p_html1;
	private static	Matcher m_html1;
	
	/**
	 * 对html页面所有元素过滤 返回过滤后的字符串
	 * 过滤前 <html><body><script>alert(hello world')</script></body></html>
	 * 过滤后 alert(hello world')
	 * @param str
	 * @return
	 */
	public static String htmlSymbols(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		// 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
		String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
		// 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
		String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		String regEx_html1 = "<[^>]+";
		
		try {
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(str);
			str = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(str);
			str = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(str);
			str = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(str);
			str = m_html1.replaceAll(""); // 过滤html标签
		} catch (Exception e) {
			System.err.println("htmlSymbols: " + e.getMessage());
		}
		return str;
	}
	
	/**
	 * 替换页面元素过滤
	 * 过滤前 <script>alert("hello world")</script>
	 * 过滤后 &lt;script&gt;alert('hello world')&lt;/script&gt;
	 * @param str
	 * @return
	 */
	public static String replaceHtmlSymbols(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		str = str.trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
		/*将script 转换为全角  
		 * String tmp = str.toLowerCase();
		 * if(tmp.indexOf("script")!=-1){ str =
		 * str.toLowerCase().replaceAll("script", StringTools.ToSBC("script"));
		 * }
		 */
		return str;
	}
	
	public static String replaceSymbolsHtml(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.trim().replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("	", "    ");
	}
	
	//过滤大部分html字符
    public static String encode(String input) {
        if (input == null) {
            return input;
        }
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = 0, c = input.length(); i < c; i++) {
            char ch = input.charAt(i);
            switch (ch) {
//                case '&': sb.append("&amp;");
//                    break;
                case '<': sb.append("&lt;");
                    break;
                case '>': sb.append("&gt;");
                    break;
//                case '"': sb.append("&quot;");
//                    break;
//                case '\'': sb.append("&#x27;");
//                    break;
//                case '/': sb.append("&#x2F;");
//                    break;
                default: sb.append(ch);
            }
        }
        return sb.toString();
    }
	
	/**
	 * 测试方法保留
	 * @param args
	 */
	public static void main(String[] args) {
		  StringBuffer htmlStr = new StringBuffer();  
	        htmlStr.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>")  
	               .append("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en'><head><title>aaa</title><mce:script type='text/javascript'></mce:script>")  
	               .append("<link href='static_files/help.css' mce_href='static_files/help.css' rel='stylesheet' type='text/css' media='all' />")  
	               .append("</head><script>alert('hello world')</script><body><ul><li>XXXX</li></ul></body></html>");  
		System.out.println(htmlStr.toString());
		System.out.println(htmlSymbols(htmlStr.toString()));

	}
}
