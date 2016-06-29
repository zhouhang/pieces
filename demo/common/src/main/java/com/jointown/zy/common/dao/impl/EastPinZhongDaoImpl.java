/**
 * @author guoyb
 * 2015年3月9日 下午7:36:38
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastPinZhongDao;

/**
 * @author guoyb
 * 2015年3月9日 下午7:36:38
 */
@Repository
public class EastPinZhongDaoImpl extends BaseDaoImpl implements EastPinZhongDao {

	@Override
	public List<String> selectAllChandi() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastPinZhongMapper.selectAllChandi");
	}

}
