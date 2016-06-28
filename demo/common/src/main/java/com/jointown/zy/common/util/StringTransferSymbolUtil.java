package com.jointown.zy.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串转换符号
 * @ClassName:StringTransferSymbolUtil
 * @author:Calvin.Wangh
 * @date:2015-8-4下午12:06:56
 * @version V1.0
 * @Description:
 */
public class StringTransferSymbolUtil {
	
	public final static int MOBILE = 1;
	public final static int IDENTITY_CARD = 2;
	public final static int EMAIL = 3;
	public final static int CHINESE_NAME = 4;
	public final static int LICENSE_CODE = 5;
	
	/**
	 * 指定字符串 根据类型做字符串转换符号
	 * @param v 指定字符串
	 * @param type 指定类型
	 * @return
	 */
	public static String hideString(String v, int type) {
		String tem = "";
		String first = "";
		String hide = "";
		String last = "";
		if(StringUtils.isNotBlank(v)){
			int vLength = v.length();
			switch (type) {
			case MOBILE:
				if(vLength >=11){
					first = v.substring(0, 3);
					hide = convertSymbol(v.substring(first.length(),vLength - 4).length());
					last = v.substring(vLength - 4);
				}else{
					first = v.substring(0, 1);
					last = v.substring(vLength - 1);
					hide = convertSymbol(v.substring(first.length(),v.length()-1).length());
				}
				tem = first + hide + last;
				break;
			case IDENTITY_CARD:
				if(vLength >=15 || vLength>=18){
					first = v.substring(0, 5);
					hide = convertSymbol(v.substring(first.length(),vLength - 4).length());
					last = v.substring(vLength - 4);
				}else{
					first = v.substring(0, 1);
					last = v.substring(vLength - 1);
					hide = convertSymbol(v.substring(first.length(),v.length()-1).length());
				}
				tem = first + hide + last;
				break;
			case EMAIL:
				String head = v.substring(0, v.indexOf("@"));
				String tail =  v.substring(v.indexOf("@"));
				if(head.length()<=3){
					first = head;
				}else{
					first = head.substring(0,3);
					hide = convertSymbol(head.substring(first.length(), head.length()).length());
				}
				tem = first + hide  + tail;
				break;
			case CHINESE_NAME:
				if(vLength>=2){
					first = convertSymbol(v.subSequence(0, v.length()-1).length());
					last = v.substring(v.length()-1);
					tem = first + last ;
				}else{
					tem = v;
				}
				break;
			case LICENSE_CODE:
				if(vLength>=15){
					first = v.substring(0, 5);
					last = v.substring(vLength - 4);
					hide = convertSymbol(v.substring(first.length(),v.length()-4).length());
					tem = first + hide + last;
				}else{
					if(vLength<=1){
						tem = v;
					}else{
						first = v.substring(0, 1);
						last = v.substring(vLength - 1);
						hide = convertSymbol(v.substring(first.length(),v.length()-1).length());
						tem = first + hide + last;
					}
				}
				break;
			default:
				break;
			}
		}
		return tem;
	}
	
	/**
	 * 转换处理
	 * @param length
	 * @return
	 */
	public static String convertSymbol(int length) {
		String symbol = "";
		if(0<length){
			for(int i=0;i<length;i++){
				 symbol += "*";
			}
		}
		return symbol;
	}
	
	public static void main(String[] args) {
		//System.out.println(hideString("123", MOBILE));
		System.out.println(hideString("hip_hop_funcation@54315.com", EMAIL));
		//System.out.println(hideString("", LICENSE_CODE));
		//System.out.println(hideString("1", IDENTITY_CARD));
		//System.out.println(hideString("骂", CHINESE_NAME));
	}
}
