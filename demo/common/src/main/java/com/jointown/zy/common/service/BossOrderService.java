/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service;

import java.util.Date;

import com.jointown.zy.common.dto.BossOrderListDto;
import com.jointown.zy.common.model.BusiTermOrderDetail;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.BossOrderListVo;

/**
 * @ClassName: BossOrderService
 * @Description: 后台订单相关操作Service
 * @Author: 赵航
 * @Date: 2015年4月3日
 * @Version: 1.0
 */
public interface BossOrderService {

	/**
	 * 后台查询订单列表
	 * @param query 分页查询条件
	 * @return 分页查询订单结果
	 */
	Page<BossOrderListVo> selectOrderListByPage(BossOrderListDto query) throws Exception;
	
	/**
	 * 后台订单查询列表页-订单详情查询
	 * @param orderId 订单编号
	 * @return 订单详情
	 */
	BossOrderInfoVo selectOrderInfoById(String orderId) throws Exception;
	
	/**
	 * 后台订单查询列表页-修改过期时间
	 * @param orderId 订单编号
	 * @param expireTime 修改后的过期时间
	 */
	void updateExpireTime(String orderId, Date expireTime) throws Exception;
	
	/**
	 * @Description: 根据订单号修改订单保证金
	 * @Author: ldp
	 * @Date: 2015年8月5日
	 * @param orderId 订单号
	 * @param deposit 要修改的订单保证金
	 * @throws Exception
	 */
	public void updateOrderDeposit(String orderId,String deposit) throws Exception;
	
	/**
	 * @Description: 转为账期订单
	 * @Author: 赵航
	 * @Date: 2015年9月9日
	 * @param termOrder
	 * @throws Exception
	 */
	void changeOrderTerm(BusiTermOrderDetail termOrder) throws Exception;
	
	/**
	 * @Description: 查看账期订单详情
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @return
	 * @throws Exception
	 */
	BusiTermOrderDetail selectTermOrder(String orderId) throws Exception;
	
}
