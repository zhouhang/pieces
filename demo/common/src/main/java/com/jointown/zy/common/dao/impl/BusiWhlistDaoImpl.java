package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BreedVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.CategorysVo;
import com.jointown.zy.common.vo.DictInfoVo;

/**
 * 仓单管理DaoImpl
 * @author wangjunhu
 * 	2014-12-18
 */
@Repository
public class BusiWhlistDaoImpl extends BaseDaoImpl implements BusiWhlistDao {

	@Override
	public int insertBusiWhlist(BusiWhlist busiWhlist) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.insert("insertBusiWhlist",busiWhlist);
	}

	@Override
	public int updateBusiWhlistById(BusiWhlist busiWhlist) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("updateBusiWhlistById", busiWhlist);
	}

	@Override
	public void deleteBusiWhlistById(BusiWhlist busiWhlist) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("deleteBusiWhlistById", busiWhlist);
	}

	@Override
	public List<BusiWhlist> selectBusiWhlists() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BusiWhlist> busiWhlists = sqlSession.selectList("selectBusiWhlists");
		return busiWhlists;
	}
	
	@Override
	public BusiWhlistVo selectBusiWhlistById(HashMap<String,Object> map) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BusiWhlistVo busiWhlist = sqlSession.selectOne("com.jointown.zy.common.dao.BusiWhlistDao.selectBusiWhlistById",map);
		return busiWhlist;
	}
	
	@Override
	public List<BusiWhlistVo> selectBusiWhlistsByCondition(Page<BusiWhlistVo> page) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BusiWhlistVo> busiWhlists = sqlSession.selectList("com.jointown.zy.common.dao.BusiWhlistDao.selectBusiWhlistsByCondition",page);
		return busiWhlists;
	}

	@Override
	public BusiWhlistVo selectWlIdBySeqBusiWhlist(){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BusiWhlistVo busiWhlist = sqlSession.selectOne("selectWlIdBySeqBusiWhlist");
		return busiWhlist;
	}
	
	@Override
	public List<CategorysVo> selectCategorysByTree() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<CategorysVo> categorys = sqlSession.selectList("selectCategorysByTree");
		return categorys;
	}

	@Override
	public List<CategorysVo> selectCategorysByBreedId(Long breedId){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<CategorysVo> categorys = sqlSession.selectList("selectCategorysByBreedId",breedId);
		return categorys;
	}
	
	@Override
	public List<BreedVo> selectBreedsByTree() {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BreedVo> Breeds = sqlSession.selectList("selectBreedsByTree");
		return Breeds;
	}

	@Override
	public BreedVo selectBreedById(Long breedId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BreedVo Breed = sqlSession.selectOne("selectBreedById",breedId);
		return Breed;
	}
	
	@Override
	public List<BreedVo> selectBreedTreesByCategorysId(Long categorysId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<BreedVo> Breeds = sqlSession.selectList("selectBreedTreesByCategorysId",categorysId);
		return Breeds;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public int updateWLsurplusById(HashMap map) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		int i= sqlSession.update("updateWLsurplusById",map);
		return i;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int updateWLsurplus(HashMap map) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		int i= sqlSession.update("updateWLsurplus",map);
		return i;
	}

	@Override
	public AreaVo selectAreaByCode(String firstCode){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		AreaVo area = sqlSession.selectOne("selectAreaByCode",firstCode);
		return area;
	}
	
	@Override
	public List<AreaVo> selectAreasByFirstCode(String firstCode) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<AreaVo> areas = sqlSession.selectList("selectAreasByFirstCode",firstCode);
		return areas;
	}

	@Override
	public DictInfoVo selectDictInfoByBreedId(Long breedId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		DictInfoVo dictInfo = sqlSession.selectOne("selectDictInfoByBreedId",breedId);
		return dictInfo;
	}

	/**
	 * 我要挂牌--仓单选择
	 */
	@Override
	public List<BusiWhlistVo> selectBusiWhlistMohu(Page<BusiWhlistVo> page) {
		SqlSessionTemplate sessionTemplate = this.getSqlSession();
		return sessionTemplate.selectList("selectBusiWhlistMohu", page);
	}
	
	@Override
	public BusiWhlistVo selectBusiWhlistByWlId(String wlId){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BusiWhlistVo busiWhlist = sqlSession.selectOne("selectBusiWhlistByWlId",wlId);
		return busiWhlist;
	}

	@Override
	public int updateBusiWhlistByIdForWms(BusiWhlist busiWhlist) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.update("updateBusiWhlistByIdForWms", busiWhlist);
	}

	@Override
	public BusiWhlist selectWhlistByWlId(String wlId){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		BusiWhlist busiWhlist = sqlSession.selectOne("selectWhlistByWlId",wlId);
		return busiWhlist;
	}

	@Override
	public BusiWhlist selectWhlistByOrderId(String orderId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiWhlistDao.selectWhlistByOrderId", orderId);
	}
}
