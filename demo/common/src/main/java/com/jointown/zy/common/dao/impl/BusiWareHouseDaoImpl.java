package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiWareHouseDao;
import com.jointown.zy.common.model.BusiWareHouse;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiWareHouseVo;

/**
 * 仓库管理DaoImpl
 * @author wangjunhu
 * 2012-12-22
 */
@Repository
public class BusiWareHouseDaoImpl extends BaseDaoImpl implements
		BusiWareHouseDao {

	@Override
	public int insertBusiWareHouse(BusiWareHouse busiWareHouse) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert("insertBusiWareHouse",busiWareHouse);
		
	}

	@Override
	public int updateBusiWareHouseById(BusiWareHouse busiWareHouse) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("updateBusiWareHouseById", busiWareHouse);
	}
	
	

	@Override
	public void deleteBusiWareHouseById(BusiWareHouse busiWareHouse) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleteBusiWareHouseById", busiWareHouse);
	}

	@Override
	public List<BusiWareHouseVo> selectBusiWareHouses() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BusiWareHouseVo> busiWareHouses = sqlSession.selectList("selectBusiWareHouses");
		return busiWareHouses;
	}

	@Override
	public List<BusiWareHouseVo> selectBusiWareHousesByTree() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BusiWareHouseVo> busiWareHouses = sqlSession.selectList("selectBusiWareHousesByTree");
		return busiWareHouses;
	}
	
	@Override
	public BusiWareHouseVo selectBusiWareHouseById(String wareHouseId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BusiWareHouseVo busiWareHouse = sqlSession.selectOne("selectBusiWareHouseById",wareHouseId);
		return busiWareHouse;
	}
	
	@Override
	public List<BusiWareHouseVo> selectBusiWareHousesByCondition(
			Page<BusiWareHouseVo> page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BusiWareHouseVo> busiWareHouses = sqlSession.selectList("selectBusiWareHousesByCondition",page);
		return busiWareHouses;
	}

/**
 * @author seven
 * @description 更具输入的用户id查询出用户的货物所在的仓库
 */
	@Override
	public List<BusiWareHouseVo> selectBusiWareHousesByUserId(Long userId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BusiWareHouseDao.selectBusiWareHouseByUserId", userId);
	}
	
	@Override
	public int delWarehouseById(String wareHouseId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BusiWareHouseDao.delWarehouseById", wareHouseId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiWareHouse busiWareHouse) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.BusiWareHouseDao.updateByPrimaryKeySelective",busiWareHouse);
	}

}
