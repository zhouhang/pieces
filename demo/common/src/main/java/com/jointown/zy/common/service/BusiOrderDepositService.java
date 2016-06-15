/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.BossOrderDepositDto;
import com.jointown.zy.common.dto.BossOrderDepositQueryDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderDepositVo;
import com.jointown.zy.common.vo.RefuseRemitFlowVo;

/**
 * @ClassName: BusiOrderDepositService
 * @Description: 订单划账Service
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public interface BusiOrderDepositService {
	
	/**
	 * 
	 * @Description: 根据订单ID获取已取消订单划账信息
	 * @Author: robin.liu
	 * @Date: 2015年8月10日
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public BossOrderDepositVo getCanceledOrderDepositInfo(String orderId) throws Exception;

	/**
	 * @Description: 查询后台订单划账列表
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param query 查询条件
	 * @return 订单划账任务列表Page
	 * @throws Exception
	 */
	Page<BossOrderDepositVo> selectDepositList(BossOrderDepositQueryDto query) throws Exception;
	

	/**
	 * @Description: 后台划账操作
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param deposit 划账信息
	 * @return "success":划账成功；"failure"：划账失败
	 * @throws Exception
	 */
	String todoOrderDeposit(BossOrderDepositDto deposit) throws Exception;
	
	/**
	 * @Description: 支付划账执行后，更新交易订单划账状态及其他相关内容
	 * @Author: 赵航
	 * @Date: 2015年7月2日
	 * @param updateJson 支付划账后，返回的json对象包括属性：<br/>
	 *        orderId:订单编号<br/>
	 *        remitType:划账类型<br/>
	 *        flowId:划账流水ID<br/>
	 *        oprateStatus:操作状态<br/>
	 *        remitTime:划账时间<br/>
	 * @throws Exception
	 */
	void changOrderDepositState(JsonObject updateJson) throws Exception;
	
	/**
	 * @Description: 支付划账后，扫描支付划账结果表
	 * @Author: 赵航
	 * @Date: 2015年7月6日
	 * @param count 返回记录条数
	 * @return 支付划账结果记录未处理的记录集合
	 * @throws Exception
	 */
	List<JsonObject> selectOrderDepositResults(int count) throws Exception;
	

	/**
	 * @Description: 调用pay的拒绝信息查询接口
	 * @Author: guoyb
	 * @Date: 2015年7月6日
	 * @param: reqJson按格式组成好的json串
	 * @return 
	 * @throws Exception
	 */
	List<RefuseRemitFlowVo> RejectReason(String reqJson) throws Exception;
}
