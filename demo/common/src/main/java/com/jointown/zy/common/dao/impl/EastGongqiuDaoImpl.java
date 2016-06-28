package com.jointown.zy.common.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastGongqiuDao;
import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.vo.EastGongqiuVo;

/**
 * 
 * @ClassName: EastGongqiuDaoImpl
 * @Description: 东方中药材网供求信息DaoImpl
 * @Author: wangjunhu
 * @Date: 2015年6月12日
 * @Version: 1.0
 */
@Repository
public class EastGongqiuDaoImpl extends BaseDaoImpl implements EastGongqiuDao {

	@Override
	public int deleteByPrimaryKey(BigDecimal gqid) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.EastGongqiuDao.deleteByPrimaryKey",gqid);
	}

	@Override
	public int insert(EastGongqiu record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastGongqiuDao.insert", record);
	}

	@Override
	public int insertSelective(EastGongqiu record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.EastGongqiuDao.insertSelective", record);
	}

	@Override
	public EastGongqiu selectByPrimaryKey(BigDecimal gqid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.EastGongqiuDao.selectByPrimaryKey", gqid);
	}

	@Override
	public int updateByPrimaryKeySelective(EastGongqiu record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastGongqiuDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(EastGongqiu record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastGongqiuDao.updateByPrimaryKey", record);
	}

	/**
	 * @see com.jointown.zy.common.dao.EastGongqiuDao#selectEastGongqiuById(java.lang.Integer)
	 */
	@Override
	public EastGongqiuVo selectEastGongqiuById(Long gqid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.EastGongqiuDao.selectEastGongqiuById", gqid);
	}

}
