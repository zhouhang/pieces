package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxOpinionDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxOpinion;
import com.jointown.zy.common.vo.WxOpinionVo;

/**
 * 意见反馈
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
@Repository
public class WxOpinionDaoImpl extends BaseDaoImpl implements WxOpinionDao {

	@Override
	public int insert(WxOpinion wxOpinion) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxOpinionDao.insert", wxOpinion);
	}

	@Override
	public int updateByPrimaryKeySelective(WxOpinion wxOpinion) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxOpinionDao.updateByPrimaryKeySelective", wxOpinion);
	}

	@Override
	public int deleteByPrimaryKey(Long opId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.WxOpinionDao.deleteByPrimaryKey", opId);
	}
	
	@Override
	public WxOpinion selectByPrimaryKey(Long opId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxOpinionDao.selectByPrimaryKey", opId);
	}
	
	@Override
	public List<WxOpinion> selectWxOpinionByCondition(Page<WxOpinionVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxOpinionDao.selectWxOpinionByCondition", page);
	}

}
