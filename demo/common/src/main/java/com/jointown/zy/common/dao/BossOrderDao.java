/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.BossOrderListVo;
import com.jointown.zy.common.vo.OrderMobileEmailVo;

/**
 * @ClassName: BossOrderDao
 * @Description: 后台订单操作DAO
 * @Author: 赵航
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public interface BossOrderDao {
	
	/**
	 * @Description: 订单查询
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param page 查询条件
	 * @return 查询结果
	 */
	List<BossOrderListVo> selectOrderListByPage(Page<BossOrderListVo> page) throws Exception;
	
	/**
	 * @Description: 查看订单详情
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param orderId 订单编号
	 * @return 订单详情
	 */
	BossOrderInfoVo selectOrderInfoById(String orderId) throws Exception;
	
	/**
	 * @Description: 修改订单数量
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param order 修改内容及条件
	 * @return 记录数
	 */
	int updateOrderExpireTime(BusiOrder order) throws Exception;
	
	/**
	 * @Description: 根据ID，更新订单状态
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param orderId 订单编号
	 * @param stateKey 状态值
	 * @return 记录数
	 */
	int updateOrderStateById(String orderId, String stateKey) throws Exception;
	
	/**
	 * @Description: 检索备货状态中的过期订单
	 * @Author: 赵航
	 * @Date: 2015年4月17日
	 * @param params 参数，具体参看以下 <br/>orderState：订单状态（必须）<br/>nowDate：当前时间（必须）<br/>count：检索最大条数（可选）
	 * @return
	 * @throws Exception
	 */
	List<BusiOrder> selectOverdueOrderList(Map<String, Object> params) throws Exception;
	
	/**
	 * @Description: 根据订单号修改订单保证金
	 * @Author: ldp
	 * @Date: 2015年8月5日
	 * @param busiOrder
	 * @return
	 * @throws Exception
	 */
	int updateOrderDepositByOrderId(BusiOrder busiOrder) throws Exception;
	
	
	/**
	 * @Description: 将订单转为账期订单
	 * @Author: 赵航
	 * @Date: 2015年9月9日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	int updateTermOrder(String orderId) throws Exception;
	
	/**
	 * @Description: 根据订单编号查询其相关买卖双方及业务员信息
	 * @Author: 赵航
	 * @Date: 2015年9月9日
	 * @param orderId 订单编号
	 * @return
	 * @throws Exception
	 */
	OrderMobileEmailVo selectOrderMobileEmail(String orderId) throws Exception;

}
