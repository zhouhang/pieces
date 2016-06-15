/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.shiro;


/**
 * @ClassName: Cache
 * @Description: 扩展shiro的cache接口，使用之更通用
 * @Author: robin.liu
 * @Date: 2015年8月1日
 * @Version: 1.0
 */
public interface Cache<K, V> extends org.apache.shiro.cache.Cache<K, V> {
	
	/**
	 * 
	 * @Description: 获取缓存名称
	 * @Author: robin.liu
	 * @Date: 2015年8月1日
	 * @return
	 */
	public String getName();
	

}
