package com.jointown.zy.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: NumberUtils
 * @Description: 数字运算的方法
 * @Author: ldp
 * @Date: 2015年8月10日
 * @Version: 1.0
 */
public class NumberUtils {
	
	private static final int DECIMAL_DIGITS = 4;

	/**
	 * 判断金额格式是否合法(最多两位小数),且金额大于0
	 * @Author: ldp
	 * @Date: 2015年8月10日
	 * @param amt
	 * @return boolean
	 * 			金额格式合法，返回true;金额格式不合法返回false
	 */
	public static boolean isIegalAmt(String amt){
		if (StringUtils.isNotBlank(amt)) {
			//判断两位小数
			boolean flag = Pattern.compile("([1-9]+[0-9]*|0)(\\.[0-9]{1,2})?").matcher(amt).matches();
			//判断金额大于0
			if (flag) {
				return amtBigZero(amt);
			} 
		}
		return false;
	}
	
	/**
	 * 判断金额是否大于0
	 * @Author: ldp
	 * @Date: 2015年8月10日
	 * @return
	 */
	public static boolean amtBigZero(String amt){
		if (StringUtils.isNotBlank(amt)) {
			if (new BigDecimal(amt).compareTo(new BigDecimal(0))>0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @Description: 四舍五入保留2位有效数字
	 * @Author: robin.liu
	 * @Date: 2015年8月25日
	 * @param decimal
	 * @return
	 */
	public static BigDecimal halfUp(BigDecimal decimal){
		
		return decimal.setScale(DECIMAL_DIGITS, RoundingMode.HALF_UP);
	}
	
	/**
	 * 
	 * @Description: 四舍五入保留2位有效数字
	 * @Author: robin.liu
	 * @Date: 2015年8月25日
	 * @param decimal
	 * @return
	 */
	public static BigDecimal halfUp(Double decimal){
		return new BigDecimal(decimal).setScale(DECIMAL_DIGITS, RoundingMode.HALF_UP);
	}
}
