/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderDeposit;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderDepositVo;

/**
 * @ClassName: BusiOrderDepositDao
 * @Description: 订单划账操作DAO
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public interface BusiOrderDepositDao {
	
	/**
	 * 
	 * @Description: 获取取消订单的划账信息
	 * @Author: robin.liu
	 * @Date: 2015年8月10日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public BossOrderDepositVo getCanceledOrderDepositInfo(String orderId) throws Exception;
	/**
	 * @Description: 分页查询订单划账列表
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<BossOrderDepositVo> getDepositListByPage(Page<BossOrderDepositVo> page) throws Exception;
	
	/**
	 * @Description: 插入划账信息
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param order 订单信息
	 * @param depositType 划账类型
	 * @return 插入记录数
	 * @throws Exception
	 */
	int insertOrderDeposit(BusiOrder order, String depositType) throws Exception;
	
	/**
	 * @Description: 根据订单ID更新订单划账信息
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param deposit 订单划账信息
	 * @return 更新记录数
	 * @throws Exception
	 */
	int updateOrderDepositByOrderId(BusiOrderDeposit deposit) throws Exception;
	
	/**
	 * @Description: 划账更新前查询用（仅限后台）
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param orderId 订单ID
	 * @return 划账信息
	 * @throws Exception
	 */
	BusiOrderDeposit selectOrderDepositByLock(String orderId) throws Exception;
	
	/**
	 * @Description: 根据支付划账结果，更新交易划账申请表
	 * @Author: 赵航
	 * @Date: 2015年7月2日
	 * @param deposit
	 * @return
	 * @throws Exception
	 */
	int updateOrderDepositByPayFinish(BusiOrderDeposit deposit) throws Exception;
	
	/**
	 * @Description: 根据订单编号获取订单的划账信息
	 * @Author: 赵航
	 * @Date: 2015年9月25日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	BusiOrderDeposit selectSingleOrderDeposit(String orderId) throws Exception;
}
