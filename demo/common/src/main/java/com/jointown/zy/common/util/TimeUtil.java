package com.jointown.zy.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间工具类
 * 
 * @author LiuPiao
 * 
 */
public class TimeUtil {

	public static SimpleDateFormat formate_YYYY_MM_DD_HH_MI_SS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formate_DAY_END_YMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd 23:59:59");
	public static SimpleDateFormat formate_YYYY_MM_DD_HH_MI_SS_TZ = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	public static SimpleDateFormat formate_SLASH_YYYY_MM_DD_HH_MI_SS = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat formate_YYYYMMDDHHMMSS_SSS = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	public static SimpleDateFormat formate_YYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * chengchang添加
	 */
	public static SimpleDateFormat formate_YYYY_MM_DD_HH_MI = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public static SimpleDateFormat formate_YYYY_MM_DD = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final SimpleDateFormat formate_YMDHMS = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static Timestamp getTimeStamp(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 通过指定格式提取指定时间的时间信息
	 * 
	 * @return
	 */
	public static String getTimeShowByTimePartten(Date showTime,
			String timeTartten) {
		if ((showTime == null) || (timeTartten == null)) {
			return "";
		}
		return new SimpleDateFormat(timeTartten).format(showTime);
	}

	/**
	 * 年月日时分秒(yyyyMMddHHmmssSSS)
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYYMMDHMS(Date date) {
		return formate_YYYYMMDDHHMMSS_SSS.format(date);
	}

	/**
	 * 年-月-日
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMD(Date date) {
		return formate_YYYY_MM_DD.format(date);
	}

	/**
	 * 年-月-日 时:分:秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMS(Date date) {
		return formate_YYYY_MM_DD_HH_MI_SS.format(date);
	}

	/**
	 * 年/月/日 时:分:秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getSlashYMDHMS(Date date) {
		return formate_SLASH_YYYY_MM_DD_HH_MI_SS.format(date);
	}

	/**
	 * 年-月-日'T' 时:分:秒'Z'
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDHMSTZ(Date date) {
		return formate_YYYY_MM_DD_HH_MI_SS_TZ.format(date);
	}

	public static Date parseYMDHMS(String date, String... type) {
		try {
			return formate_YYYY_MM_DD_HH_MI_SS.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseSlashYMDHMS(String date) {
		try {
			return formate_SLASH_YYYY_MM_DD_HH_MI_SS.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * chengchang 添加 2015-01-14
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseYMDHM(String date) {
		try {
			return formate_YYYY_MM_DD_HH_MI.parse(date.replace("/", "-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串日期时间 转换成 日期时间格式 yyyy-MM-dd HH:mm:ss java.util.Date类型
	 * 
	 * @param datetime
	 * @return
	 * @throws ParseException
	 * @author Calvin.Wh
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
		if(datetime.indexOf("/") > -1){
			datetime = datetime.replace("/", "-");
		}
		return formate_YYYY_MM_DD_HH_MI_SS.parse(datetime);
	}
	
	/**
	 * 支付时间 格式化 修改需谨慎
	 * 如需修改请联系Calvin.Wh QQ289224491 
	 * @param datetime
	 * @return
	 * @throws ParseException
	 */
	public static Date payDateTimeFormat(String datetime) throws ParseException {
		return formate_YMDHMS.parse(datetime);
	}
	

	/**
	 * wangjunhu 修改 2014-12-26
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseYMD(String date) {
		try {
			return formate_YYYY_MM_DD.parse(date.replace("/", "-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间比较
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(Date d1, Date d2) {
		String str1 = formate_YYYY_MM_DD_HH_MI_SS.format(d1);
		String str2 = formate_YYYY_MM_DD_HH_MI_SS.format(d2);
		int result = str1.compareTo(str2);
		return result;
	}

	/**
	 * 根据指定格式返回格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) formate_YYYY_MM_DD_HH_MI_SS
				.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	/**
	 * 
	 * @Description: 根据日期移动天数
	 * @Author: 刘漂
	 * @Date: 2015年4月26日
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date moveDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/**
	 * 
	 * @Description: 根据日期移动时间
	 * @Author: 刘漂
	 * @Date: 2015年4月26日
	 * @param date
	 * @param dhms
	 *            数组[天数，小时数，分钟数，秒数]
	 * @return
	 */
	public static Date moveTime(Date date, int... dhms) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (dhms != null && dhms.length > 0) {
			int index = 0;
			for (int time : dhms) {
				switch (index++) {
				case 0:
					cal.add(Calendar.DAY_OF_YEAR, time);
					break;
				case 1:
					cal.add(Calendar.HOUR_OF_DAY, time);
					break;
				case 2:
					cal.add(Calendar.MINUTE, time);
					break;
				case 3:
					cal.add(Calendar.SECOND, time);
					break;
				}
			}
		}
		return cal.getTime();
	}

	/**
	 * 获取当前时间的前day天 2015-08-03 00:00:00
	 * 
	 * @param day
	 * @return
	 */
	public static Date getBeforeDayStart(int day) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		Date date = null;
		try {
			date = formate_YYYY_MM_DD.parse(formate_YYYY_MM_DD
					.format(new Date()));// 初始日期
		} catch (Exception e) {
			System.err.print("getBeforeDayStart error. currentDate: "
					+ formate_YYYY_MM_DD_HH_MI_SS.format(new Date()) + " day: "
					+ day);
		}
		c.setTime(date);// 设置日历时间
		c.add(Calendar.DATE, -day); // 在日历的天数上减少day天
		return c.getTime();
	}

	/**
	 * 获取当前天数前day天的结束时间 2015-08-05 23:59:59
	 * 
	 * @param day
	 * @return
	 */
	public static Date getBeforeDayEnd(int day) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		Date date = null;
		try {
			date = formate_YYYY_MM_DD.parse(formate_YYYY_MM_DD
					.format(new Date()));// 初始日期
		} catch (Exception e) {
			System.err.print("getBeforeDayEnd error. currentDate: "
					+ formate_YYYY_MM_DD.format(new Date()) + " day: " + day);
		}
		c.setTime(date);// 设置日历时间
		c.add(Calendar.DATE, -day); // 在日历的天数上减少day天
		return c.getTime();
	}
	
	 /**  
	 * 计算两个日期之间相差的天数  
	 * @param smdate 较小的时间 
	 * @param bdate  较大的时间 
	 * @return 相差天数 
	 * @throws ParseException  
	 */    
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	    Calendar cal = Calendar.getInstance();    
	    cal.setTime(sdf.parse(smdate));    
	    long time1 = cal.getTimeInMillis();                 
	    cal.setTime(sdf.parse(bdate));    
	    long time2 = cal.getTimeInMillis();         
	    long between_days=(time2-time1)/(1000*3600*24);  
	        
	   return Integer.parseInt(String.valueOf(between_days));     
	}  
	   
	/**  
	 * 计算两个日期之间相差的天数  
	 * @param smdate 较小的时间 
	 * @param bdate  较大的时间 
	 * @return 相差天数 
	 * @throws ParseException  
	 */    
	public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	{    
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	    smdate=sdf.parse(sdf.format(smdate));  
	    bdate=sdf.parse(sdf.format(bdate));  
	    Calendar cal = Calendar.getInstance();    
	    cal.setTime(smdate);    
	    long time1 = cal.getTimeInMillis();                 
	    cal.setTime(bdate);    
	    long time2 = cal.getTimeInMillis();         
	    long between_days=(time2-time1)/(1000*3600*24);  
	        
	   return Integer.parseInt(String.valueOf(between_days));           
	}

	
	/**
	 * @Description: 计算两个日期之间相差的天数 
	 * @Author: 赵航
	 * @Date: 2015年10月23日
	 * @param fDate 开始时间
	 * @param oDate 结束时间
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate){
		 Calendar aCalendar = Calendar.getInstance();
		 aCalendar.setTime(fDate);
		 int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		 aCalendar.setTime(oDate);
		 int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		 return day2 - day1;
	}
	
    /**
	 * 获得当前时间的<code>java.util.Date</code>对象
	 * @return
	 */
    public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		//一周的第一天 天朝从周一开始算
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}
    
    /**
	 * 获取当前月的第一天
	 * HH:mm:ss SS为零
	 * @author Calvin.Wh
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 1);// S置零
		return cal.getTime();
	}
	
	/**
	 * 获取上个月的第一天
	 * @author Calvin.Wh
	 * @return
	 */
	public static Date firstDayOfLastMonth(){
		Calendar cal = calendar();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 1);// S置零
		return cal.getTime();
	}
	
	/**
	 * 获取当前月的最后一天
	 * @author Calvin.Wh
	 * @return
	 */
	public static Date lastDayOfMonth() {
    	Calendar cal =calendar();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}
	
	/**
	 * 获取上个月的最后一天
	 * @author Calvin.Wh
	 * @return
	 */
	public static Date lastDayOfLastMonth(){
		Calendar cal =calendar();
    	//cal.add(Calendar.DATE, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}
	

}
