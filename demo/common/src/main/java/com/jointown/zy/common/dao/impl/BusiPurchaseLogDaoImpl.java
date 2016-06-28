package com.jointown.zy.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiPurchaseLogDao;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.BusiPurchaseLog;
import com.jointown.zy.common.util.GsonFactory;

@Repository
public class BusiPurchaseLogDaoImpl extends BaseDaoImpl implements BusiPurchaseLogDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusiPurchaseLog record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseLogDao.insert", record);
	}

	@Override
	public int insertSelective(BusiPurchaseLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusiPurchaseLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusiPurchaseLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusiPurchaseLog record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int batchInsert(List<BusiPurchaseLog> list) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiPurchaseLogDao.batchInsert", list);
	}


	@Override
	public int insertByBusiPurchase(BusiPurchase record, String optype,
			String operator, String remarks, boolean...recordSnapshot) {
		BusiPurchaseLog recordLog = new BusiPurchaseLog();
		recordLog.setPurchaseId(record.getPurchaseId());
		recordLog.setPurchaseCode(record.getPurchaseCode());
		recordLog.setOperator(operator);
		recordLog.setRemarks(remarks);
		recordLog.setOptype(Integer.valueOf(optype));
		Date date = new Date();
		recordLog.setCreateTime(date);
		recordLog.setUpdateTime(date);
		recordLog.setPurchaseSnapshot((recordSnapshot.length>0&&!recordSnapshot[0])?null:GsonFactory.toJson(record, "yyyy-MM-dd HH:mm:ss"));
		return insert(recordLog);
	}
	
	@Override
	public int batchInsertByBusiPurchase(List<BusiPurchase> recordList, String optype,
			String operator, String remarks, boolean...recordSnapshot){
		if(recordList!=null && recordList.size()>0){
			List<BusiPurchaseLog> logList = new ArrayList<BusiPurchaseLog>();
			for(BusiPurchase purchase:recordList){
				
				BusiPurchaseLog recordLog = new BusiPurchaseLog();
				recordLog.setPurchaseId(purchase.getPurchaseId());
				recordLog.setPurchaseCode(purchase.getPurchaseCode());
				recordLog.setOperator(operator);
				recordLog.setRemarks(remarks);
				recordLog.setOptype(Integer.valueOf(optype));
				Date date = new Date();
				recordLog.setCreateTime(date);
				recordLog.setUpdateTime(date);
				recordLog.setPurchaseSnapshot((recordSnapshot.length>0&&!recordSnapshot[0])?null:GsonFactory.toJson(purchase, "yyyy-MM-dd HH:mm:ss"));
				logList.add(recordLog);
			}
			if(logList!=null && logList.size()>0){
				return batchInsert(logList);
			}
		}
		return 0;
	}

}
