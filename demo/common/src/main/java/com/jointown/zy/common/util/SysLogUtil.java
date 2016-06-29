/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.util;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.enums.SyslogEnum;

/**
 * @ClassName: SysLogUtil
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年8月19日
 * @Version: 1.0
 */
public class SysLogUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SysLogUtil.class);
	
	/**
	 * 
	 * @Description: 打入WMS的syslog日志
	 * @Author: robin.liu
	 * @Date: 2015年8月21日
	 * @param msg
	 * @param isError 默认是info，如果是true则是error
	 */
	public static void logForWMS(String msg,boolean...isError){
		if((!ArrayUtils.isEmpty(isError)) && isError[0]){
			log.error(SyslogEnum.WMS_API.getCode()+msg);
		}else{
			log.info(SyslogEnum.WMS_API.getCode()+msg);
		}
	}
	
	
	/**
	 * 
	 * @Description: 打入Solr的syslog日志
	 * @Author: robin.liu
	 * @Date: 2015年8月21日
	 * @param msg
	 * @param isError 默认是info，如果是true则是error
	 */
	public static void logForSolr(String msg,boolean...isError){
		if((!ArrayUtils.isEmpty(isError)) && isError[0]){
			log.error(SyslogEnum.EC54315_SOLR.getCode()+msg);
		}else{
			log.info(SyslogEnum.EC54315_SOLR.getCode()+msg);
		}
	}
	
	/**
	 * 
	 * @Description: 打入business的syslog日志
	 * @Author: robin.liu
	 * @Date: 2015年8月21日
	 * @param msg
	 * @param isError 默认是info，如果是true则是error
	 */
	public static void logForBusiness(String msg,boolean...isError){
		if((!ArrayUtils.isEmpty(isError)) && isError[0]){
			log.error(SyslogEnum.EC54315_BUSINESS.getCode()+msg);
		}else{
			log.info(SyslogEnum.EC54315_BUSINESS.getCode()+msg);
		}
	}
	
	/**
	 * 
	 * @Description: 打入pay的syslog日志
	 * @Author: robin.liu
	 * @Date: 2015年8月21日
	 * @param msg
	 * @param isError 默认是info，如果是true则是error
	 */
	public static void logForPay(String msg,boolean...isError){
		if((!ArrayUtils.isEmpty(isError)) && isError[0]){
			log.error(SyslogEnum.EC54315_PAY.getCode()+msg);
		}else{
			log.info(SyslogEnum.EC54315_PAY.getCode()+msg);
		}
	}
	
	
}
