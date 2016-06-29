package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.PayResult;

/**
 * @ClassName: PayResultDao
 * @Description: 支付结果中间表操作Dao
 * @Author: ldp
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
public interface PayResultDao {

	/**
	 * 新增支付结果记录
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param payResult
	 * @return
	 */
	public int addPayResult(PayResult payResult);
	
	/**
	 * 获取支付结果
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param sourceSys
	 * 			系统标识，如交易系统
	 * @return
	 */
	public List<PayResult> selectPayResults(Integer sourceSys);
	
	/**
	 * 根据支付结果ID更改支付结果处理状态
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param resultId
	 * @return
	 */
	public int updatePayResultByResultId(Integer resultId,Integer status);
	
}
