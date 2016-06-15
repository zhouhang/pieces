package com.jointown.zy.common.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiOrderVo;

public interface BusiOrderService {
	/**
	 * 会员中心购买记录列表查询
	 */
	List<BusiOrderVo> selectOrderListByPage(Page<BusiOrderVo> page);
	
	/**
	 * add by Mr.song 
	 * 查询我的购买记录列表中所有洽谈中的记录
	 */
	List<BusiOrder> selectMyOrdering(HashMap<String,Object> map);
	
	/**
	 * 
	 * @Description: 查询全部符合条件的订单
	 * @Author: wangjunhu
	 * @Date: 2015年4月22日
	 * @param record
	 * @return
	 */
	List<BusiOrder> findBusiOrdersByAttributes(BusiOrder record);
	
	/**
	 * add by Mr.song 2015.3.20
	 * 修改购买记录信息
	 */
	int updateByIdSelective(BusiOrder record);
	
	
	/**
	 * 
	 * 删除已关闭的订单
	 */
	int deleteColosedOrderById(String orderid,Long...operator) throws Exception ;
	
	/**
	 * 会员中心取消购买记录
	 */
	void cancelOrder(String orderid);
	
	/**
	 * 分页查询购买申请
	 */
	List<BusiOrderVo> findOrderListByCondition(Page<BusiOrderVo> page);
	
	/**
	 * 更新交易状态
	 */
	int updateOrderState(BusiOrder record);
	
	/**
	 * 更新交易总价
	 */
	int updateDiscountPrice(BusiOrder record);
	
	/**
	 * 根据主键查询订单信息
	 */
	BusiOrder findBusiOrderById(String orderid);
	
	/**
	 * 记录支付保证金的情况
	 * @author guoyb
	 * 2015年4月7日 上午10:48:49
	 * @param depositedOrder
	 * @return
	 */
	Integer updateDeposit(BusiOrder depositedOrder);
	
	/**
	 * 
	 * @Description: 更新订单支付信息，更新支付记录状态
	 * @Author: wangjunhu
	 * @Date: 2015年4月23日
	 * @param busiOrder 订单
	 * @param donePayResultUrl 更新支付记录状态地址
	 * @param resultId 支付记录ID
	 * @param amtType 付款类型
	 * @param amount 付款金额
	 * @throws Exception
	 */
	boolean updateBusiOrderJob(BusiOrder busiOrder, String donePayResultUrl, String resultId, int amtType, BigDecimal amount) throws Exception;
	
	/**
	 * 
	 * @Description: 查询全部已下单状态的72小时过期的订单
	 * @Author: wangjunhu
	 * @Date: 2015年7月1日
	 * @param afterDays
	 * @return
	 */
	List<BusiOrder> findOverTimeOrdersByPlaced(Integer afterDays) throws Exception;
	
	/**
	 * 
	 * @Description: 关闭已下单状态的72小时过期的订单
	 * @Author: wangjunhu
	 * @Date: 2015年7月7日
	 * @param orderId
	 * @throws Exception
	 */
	void updateOverTimeOrderById(String orderId) throws Exception;
	
	/**
	 * 获取认证名称
	 * @Author:zhouji
	 * @Date: 2015年8月4号
	 * @param userId
	 * @return
	 */
	String selectCertifyName(Integer userId);
	
	/**
	 * @Description: 查询订单最后的日志信息
	 * @Author: 赵航
	 * @Date: 2015年8月18日
	 * @param orderId 订单ID
	 * @return 订单信息
	 */
	BusiOrderLog selectLastOrderLog(String orderId);
	
	/**
	 * @Description: 到期的未付尾款的账期订单 发短信、邮件通知
	 * @Author: fanyuna
	 * @Date: 2015年9月10日
	 * @param beforeDays 提前天数，为-1则到期发(因为是过期的第二天发提醒，SQL里是减，减减等于加)
	 * @param warnHms 提醒时间（时分秒）
	 */
	public void TermOrderSendMsg(int beforeDays,String warnHms);

}
