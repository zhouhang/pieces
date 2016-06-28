package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.PayOrder;
import com.jointown.zy.common.model.PayResult;

/**
 * @ClassName: PayResultService
 * @Description: 支付结果service
 * @Author: ldp
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
public interface PayResultService {
	
	/**
	 * 添加支付结果记录
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param payResult
	 * @return
	 * @throws Exception 
	 */
	public int addPayResult(PayOrder payOrder) throws Exception;
	
	/**
	 * 根据系统标识和查询条数获取支付结果记录
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param sourceSys
	 * 			系统标识
	 * @param num
	 * 			查新条数记录
	 * @return
	 */
	public List<PayResult> selectPayResults(Integer sourceSys,String num) throws Exception;
	
	/**
	 * 根据支付结果ID修改支付结果记录处理状态
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param resultId
	 * 			支付结果ID
	 * @param status
	 * 			处理状态
	 * @return
	 * @throws Exception 
	 */
	public int updatePayResultByResultId(Integer resultId,Integer status) throws Exception;
	

}
