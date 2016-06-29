package com.jointown.zy.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 提供bigdecimal 保留8位小数的运算操作 金额全部截断
 * @ClassName: BigDecimalUtil 
 * @Description:
 * @author Calvin.Wang
 * @date 2015-04-08 下午2:29:46 
 *
 */
public class BigDecimalUtil {
	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 * @throws
	 */
	public static BigDecimal add(BigDecimal v1,BigDecimal v2){
		BigDecimal decimal = v1.add(v2);
		return formateBigDecimal2(decimal.setScale(8, BigDecimal.ROUND_DOWN));
	}
	
	/**
	 * 减法
	 * @param v1
	 * @param v2
	 * @return
	 * @throws
	 */
	public static BigDecimal subtract(BigDecimal v1,BigDecimal v2){
		BigDecimal decimal = v1.subtract(v2);
		return formateBigDecimal2(decimal.setScale(8, BigDecimal.ROUND_DOWN));
	}
	
	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @return
	 * @throws
	 */
	public static BigDecimal multiply(BigDecimal v1,BigDecimal v2){
		BigDecimal decimal = v1.multiply(v2);
		return formateBigDecimal2(decimal.setScale(8, BigDecimal.ROUND_DOWN));
	}
	
	/** 
	 * 除法
	 * @param v1
	 * @param v2
	 * @return
	 * @throws
	 */
	public static BigDecimal divide(BigDecimal v1,BigDecimal v2){
		return formateBigDecimal2(v1.divide(v2, 8, BigDecimal.ROUND_DOWN));
	}
	
	/**
	 * 格式化后只截断保留两位小数
	 * @param decimal
	 * @return
	 * @throws
	 */
	public static String formateBigDecimal(BigDecimal decimal){
		 BigDecimal bd = decimal.setScale(3,BigDecimal.ROUND_DOWN);
		 String str = bd + "";
		 str = str.substring(0,str.length()-1);
		 if(str.equals("0.00")){
			 str = "0.00";
		 }
		 return str;
	}
	
	/**
	 * 格式化后只截断保留两位小数
	 * @param decimal
	 * @return
	 */
	public static BigDecimal formateBigDecimal2(BigDecimal decimal) {
		return new BigDecimal(formateBigDecimal(decimal));
	}
	
	/**
	 * 货币格式化
	 */
	public static String formateCurrency(BigDecimal decimal) {
		String str = "";
		if(decimal != null) {
			str = decimal.toString();
			if(str.equals("0.00")){
				str = "0.00";
			} else {
				String arr[] = str.split("[.]"); 
				String zheng = arr[0];
				String point = arr[1];
				DecimalFormat df = new DecimalFormat(",##0");
				str = df.format(Integer.parseInt(zheng)) + "." + point;
			}
		}
		return str;
	}
	
}
