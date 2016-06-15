package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiListingSalesman;

public interface BusiListingSalesmanDao {

	public void insertSalesMan(BusiListingSalesman salesMan) throws Exception;
	
	public BusiListingSalesman findBusiListingSalesmanByListingId(String listingid) throws Exception;
	
	public void updateSalesManInfoByPRO() throws Exception;

}