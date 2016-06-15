package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.HomePagePurchaseDao;
import com.jointown.zy.common.model.HomePagePurchaseModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.HomePagePurchaseVo;

@Repository
public class HomePagePurchaseDaoImpl extends BaseDaoImpl implements HomePagePurchaseDao {
	

	@Override
	public List<HomePagePurchaseModel> getPageList(Page<HomePagePurchaseModel> page) {
		return getSqlSession().selectList("com.jointown.zy.common.persistence.HomePagePurchaseMapper.selectPageList", page);
	}
	
	public Integer purchaseOrderIsExist(String purchaseId){
		return getSqlSession().selectOne("com.jointown.zy.common.persistence.HomePagePurchaseMapper.purchaseOrderIsExist", purchaseId);
	}
	
	public HomePagePurchaseModel getPurchaseById(String id){
		return getSqlSession().selectOne("com.jointown.zy.common.persistence.HomePagePurchaseMapper.selectPurachse", id);
	}
	
	public Integer addPurchaseInfo(HomePagePurchaseModel purchase){
		return getSqlSession().insert("com.jointown.zy.common.persistence.HomePagePurchaseMapper.addPurchaseInfo", purchase);
	}
	
	public Integer updatePurchaseInfo(HomePagePurchaseModel purchase){
		return getSqlSession().update("com.jointown.zy.common.persistence.HomePagePurchaseMapper.updatePurchaseInfo", purchase);
	}

}
