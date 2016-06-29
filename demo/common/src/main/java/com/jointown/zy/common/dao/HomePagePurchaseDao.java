package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.HomePagePurchaseModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.HomePagePurchaseVo;

public interface HomePagePurchaseDao {
	
	public List<HomePagePurchaseModel> getPageList(Page<HomePagePurchaseModel> page);
	
	public Integer purchaseOrderIsExist(String purchaseId);
	
	public HomePagePurchaseModel getPurchaseById(String id);
	
	public Integer addPurchaseInfo(HomePagePurchaseModel purchase);
	
	public Integer updatePurchaseInfo(HomePagePurchaseModel purchase);

}
