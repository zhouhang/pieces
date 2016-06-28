package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.HomePagePurchaseModel;
import com.jointown.zy.common.model.Page;

public interface HomePagePurchaseService {
	
	public List<HomePagePurchaseModel> getPageList(Page<HomePagePurchaseModel> page);
	
	public Integer getPurchaseOrder(String purchaseId);
	
	public HomePagePurchaseModel getPurchaseInfo(String id);
	
	public Map<String,Object> addPurchase(HomePagePurchaseModel purchase);
	
	public Map<String,Object> updatePurchase(HomePagePurchaseModel purchase);
	
	public Map<String,Object> deletePurchase(String id);
}
