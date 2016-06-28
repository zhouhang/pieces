/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: RabbitmqCodecUtil
 * @Description: Rabbitmq字节数组与对象间的相互转换工具类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class RabbitmqCodecUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(RabbitmqCodecUtil.class);
	
	/**
	 * @Description: 将Obj转换成字节数组
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param obj
	 * @return
	 */
	public static byte[] ObjectToBytes(Object obj) {
		if(obj == null){
			return null;
		}
		byte[] bytes = null;
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		try{
			bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
		} catch(IOException e){
			logger.error("Object transfor to Bytes is failure");
		} finally {
			if(oo != null){
				try {
					oo.close();
				} catch (IOException e) {
					logger.error("Failed to close outputstream");
				}
			}
		}
		return bytes;
	}
	
	/**
	 * @Description: 将字节数组转换成对象
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param bytes
	 * @return
	 */
	public static Object BytesToObject(byte[] bytes) {
		if(bytes == null){
			return null;
		}
		Object object = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		try {
			bi = new ByteArrayInputStream(bytes);
			oi = new ObjectInputStream(bi);
			object = oi.readObject();
		} catch (Exception e) {
			logger.error("Bytes transfor to Object is failure");
		} finally {
			if(oi != null) {
				try {
					oi.close();
				} catch (IOException e) {
					logger.error("Failed to close inputstream");
				}
			}
		}
		return object;
	}
}
