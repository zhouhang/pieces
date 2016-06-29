package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiPurchaseApplyDao;
import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.vo.BusiPurchaseApplyVo;

/**
 * 
 * @ClassName: BusiPurchaseApplyDaoImpl
 * @Description: 采购申请
 * @Author: wangjunhu
 * @Date: 2015年5月19日
 * @Version: 1.0
 */
@Repository
public class BusiPurchaseApplyDaoImpl extends BaseDaoImpl implements BusiPurchaseApplyDao {

	@Override
	public int deleteByPrimaryKey(Long purchaseId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiPurchaseApplyDao.deleteByPrimaryKey",purchaseId);
	}

	@Override
	public int insert(BusiPurchaseApply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseApplyDao.insert",record);
	}

	@Override
	public int insertSelective(BusiPurchaseApply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseApplyDao.insertSelective",record);
	}

	@Override
	public BusiPurchaseApply selectByPrimaryKey(Long purchaseId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiPurchaseApplyDao.selectByPrimaryKey",purchaseId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiPurchaseApply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiPurchaseApplyDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(BusiPurchaseApply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiPurchaseApplyDao.updateByPrimaryKey",record);
	}

	/**
	 * @see com.jointown.zy.common.dao.BusiPurchaseApplyDao#selectBusiPurchaseApplyById(java.lang.Long)
	 */
	@Override
	public BusiPurchaseApplyVo selectBusiPurchaseApplyById(Long purchaseId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiPurchaseApplyDao.selectBusiPurchaseApplyById", purchaseId);
	}

}
