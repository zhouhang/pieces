/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiOrderDetail;

/**
 * 
 * @ClassName: BusiOrderDetailDao
 * @Description: TODO
 * @Author: 刘漂
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
public interface BusiOrderDetailDao {
	
	/**
	 * 
	 * @Description: 查询订单详情
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param orderDetailId
	 * @return
	 * @throws Exception
	 */
	BusiOrderDetail selectOrderDetailById(Long orderDetailId) throws Exception;
	
	/**
	 * 
	 * @Description: 根据订单ID查询订单详情
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	List<BusiOrderDetail> selectOrderDetailByOrderId(String orderId) throws Exception;
	
	/**
	 * 
	 * @Description: 新增订单详情
	 * @Author: 刘漂
	 * @Date: 2015年4月16日
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int insertOrderDetail(BusiOrderDetail order);
	
}
