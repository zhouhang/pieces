package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.BusiPurchaseLog;

public interface BusiPurchaseLogDao {
    

    int deleteByPrimaryKey(Long id);

    int insert(BusiPurchaseLog record);

    int insertSelective(BusiPurchaseLog record);

    BusiPurchaseLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiPurchaseLog record);

    int updateByPrimaryKey(BusiPurchaseLog record);
    
    int insertByBusiPurchase(BusiPurchase record, String optype, String operator, String remarks, boolean...recordSnapshot);
    
    public int batchInsert(List<BusiPurchaseLog> list);
    
    /**
     * 
     * @Description: 根据采购对象批量插入日志
     * @Author: fanyuna
     * @Date: 2015年11月23日
     * @param recordList
     * @param optype
     * @param operator
     * @param remarks
     * @param recordSnapshot
     * @return
     */
    public int batchInsertByBusiPurchase(List<BusiPurchase> recordList, String optype,
			String operator, String remarks, boolean...recordSnapshot);
}