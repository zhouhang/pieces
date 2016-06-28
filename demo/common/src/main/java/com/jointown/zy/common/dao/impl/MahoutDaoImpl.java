/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.MahoutDao;
import com.jointown.zy.common.model.PreferenceData;

/**
 * @ClassName: MahoutDaoImpl
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年8月18日
 * @Version: 1.0
 */
@Repository
public class MahoutDaoImpl extends BaseDaoImpl implements MahoutDao {

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.MahoutDao#getPreferenceData()
	 */
	@Override
	public List<PreferenceData> getPreferenceData() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.MahoutDao.getAllPreference");
	}

}
