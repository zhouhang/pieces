package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiWarehouseApplyDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.vo.AreaVo;

/**
 * 
 * @ClassName: BusiWarehouseApplyDaoImpl
 * @Description: 入仓申请
 * @Author: wangjunhu
 * @Date: 2015年5月19日
 * @Version: 1.0
 */
@Repository
public class BusiWarehouseApplyDaoImpl extends BaseDaoImpl implements BusiWarehouseApplyDao {

	@Override
	public int deleteByPrimaryKey(Long warehouseId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiWarehouseApplyDao.deleteByPrimaryKey",warehouseId);
	}

	@Override
	public int insert(BusiWarehouseApply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiWarehouseApplyDao.insert",record);
	}

	@Override
	public int insertSelective(BusiWarehouseApply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiWarehouseApplyDao.insertSelective",record);
	}

	@Override
	public BusiWarehouseApply selectByPrimaryKey(Long warehouseId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiWarehouseApplyDao.selectByPrimaryKey",warehouseId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiWarehouseApply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiWarehouseApplyDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(BusiWarehouseApply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiWarehouseApplyDao.updateByPrimaryKey",record);
	}

	@Override
	public List<AreaVo> selectAreasByCondition(AreaVo record) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiWarehouseApplyDao.selectAreasByCondition",record);
	}

	@Override
	public Breed getBreedInfo(String breedName) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiWarehouseApplyDao.selectBreedName", breedName);
	}

}
