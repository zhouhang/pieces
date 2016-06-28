/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.PreferenceData;

/**
 * @ClassName: MahoutDao
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年8月18日
 * @Version: 1.0
 */
public interface MahoutDao {
	
	/**
	 * 
	 * @Description: 获取所有用户的偏好值
	 * @Author: robin.liu
	 * @Date: 2015年8月18日
	 * @return
	 */
	public List<PreferenceData> getPreferenceData();

}
