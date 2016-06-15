package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiPurchaseDao;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;
//import com.jointown.zy.common.vo.BusiPurchaseBatchVo;
import com.jointown.zy.common.vo.BusiPurchaseVo;

@Repository
public class BusiPurchaseDaoImpl extends BaseDaoImpl implements BusiPurchaseDao{

	@Override
	public int deleteByPrimaryKey(String purchaseId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusiPurchase record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseDao.insert", record);
	}

	@Override
	public int insertSelective(BusiPurchase record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiPurchase selectByPrimaryKey(String purchaseId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiPurchaseDao.selectByPrimaryKey", purchaseId);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiPurchase record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiPurchaseDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BusiPurchase record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BusiPurchaseVo> selectPurchaseAuditPage(
			Page<BusiPurchaseVo> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseAuditPage", page);
	}

	@Override
	public BusiPurchaseVo selectPurchaseDetail(String purchaseId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseDetail", purchaseId);
	}

	@Override
	public List<BusiPurchaseVo> selectPurchaseManagePage(
			Page<BusiPurchaseVo> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseManagePage", page);
	}
	@Override
	public List<BusiPurchaseVo> selectSameBreedList(Long breedId){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectSamePurchase", breedId);
	}
	@Override
	public List<BusiPurchaseVo> selectSameBreedNameList(String breedName){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectSameBreedPurchase", breedName);
	}
	@Override
	public List<BusiPurchase> selectPurOfSalerInfo(String saleName){
		if(StringUtils.isEmpty(saleName)){
			return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurOfSalerNull");			
		}
		else{
			return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurOfSaler", saleName);
		}
	}
	@Override
	public List<BusiPurchase> selectPurchaseByCode(String purchaseCode) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseByCode", purchaseCode);
	}

	@Override
	public BusiPurchaseMobileEmailMsgVo selectPurchaseMobileEmailMsgById(
			String purchaseId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseMobileEmailMsgById", purchaseId);
	}

	@Override
	public List<BusiPurchaseVo> selectExpiredPurchaseInfo() {
		return  getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectExpiredPurchaseInfo");
	}

	@Override
	public boolean expirePurchaseByPurchaseId(String purchaseId) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiPurchaseDao.expirePurchaseByPurchaseId", purchaseId)>0;
	}

//	@Override
//	public List<BusiPurchaseBatchVo> selectPurchaseBatchByCode(
//			String purchaseCode) {
//		return  getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectPurchaseBatchByCode",purchaseCode);
//	}

	@Override
	public List<BusiPurchaseVo> selectRecentlyPurchases(Integer maxCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("maxCount", maxCount);
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.selectRecentlyPurchases", map);
	}
	
	public List<BusiPurchaseVo> getPurchaseManagePage(Page<BusiPurchaseVo> page){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.getPurchaseManagePage", page);
	}
	
	@Override
	public boolean negotiaPurchaseByPurchaseCode(String purchaseId){
		return getSqlSession().update("com.jointown.zy.common.dao.BusiPurchaseDao.negotiaPurchaseByPurchaseCode", purchaseId)>0;
	}
	
	public List<BusiPurchase> queryBigPurchase(){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.queryBigPurchase");
	}
	
	public List<BusiPurchase> queryHomePagePurchase(){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.queryPurchaseInfo");
	}
	
	public List<BusiPurchase> queryHomePageNewPurchase(){
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiPurchaseDao.queryHomePageNewPurchase");
	}

	@Override
	public int batchInsert(List<BusiPurchase> list) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseDao.batchInsert", list);
	}
	
}
