package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxActivityDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.vo.WxActivityVo;

/**
 * 微信公众平台开发--活动DaoImpl
 * 
 * @author aizhengdong
 *
 * @data 2015年2月14日
 */
@Repository
public class WxActivityDaoImpl extends BaseDaoImpl implements WxActivityDao {

	@Override
	public int deleteByPrimaryKey(Long activityId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.WxActivityDao.deleteByPrimaryKey",activityId);
	}

	@Override
	public int insert(WxActivity record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxActivityDao.insert", record);
	}

	@Override
	public int insertSelective(WxActivity record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxActivityDao.insertSelective", record);
	}

	@Override
	public WxActivityVo selectByPrimaryKey(Long activityId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxActivityDao.selectByPrimaryKey", activityId);
	}

	@Override
	public int updateByPrimaryKeySelective(WxActivity record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxActivityDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WxActivity record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxActivityDao.updateByPrimaryKey", record);
	}

	@Override
	public List<WxActivityVo> selectWxActivitysByCondition(Page<WxActivityVo> page){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxActivityDao.selectWxActivitysByCondition", page);
	};
	
	@Override
	public int selectByCondition(WxActivity record) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxActivityDao.selectByCondition", record);
	}
	
	/**
	 * @see com.jointown.zy.common.dao.WxActivityDao#findActivity()
	 */
	@Override
	public List<WxActivity> findActivity() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<WxActivity> wxActivitys = sqlSession
				.selectList("com.jointown.zy.common.dao.WxActivityDao.findActivity");
		return wxActivitys;
	}

	/**
	 * @see com.jointown.zy.common.dao.WxActivityDao#findSellInfo()
	 */
	@Override
	public List<WxActivity> findSellInfo() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<WxActivity> wxActivitys = sqlSession
				.selectList("com.jointown.zy.common.dao.WxActivityDao.findSellInfo");
		return wxActivitys;
	}
}
