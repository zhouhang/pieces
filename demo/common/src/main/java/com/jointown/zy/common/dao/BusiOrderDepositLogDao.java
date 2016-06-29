/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiOrderDeposit;

/**
 * @ClassName: BusiOrderDepositLogDao
 * @Description: 订单划账操作日志DAO
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
public interface BusiOrderDepositLogDao {

	/**
	 * @Description: 插入订单划账操作日志记录
	 * @Author: 赵航
	 * @Date: 2015年5月15日
	 * @param deposit 订单划账信息
	 * @param operattorId 操作者ID
	 * @param operattorIp 操作者IP
	 * @param result 操作结果
	 * @param remark 操作结果备注
	 * @return 插入记录数
	 * @throws Exception
	 */
	int insertDepositLog(BusiOrderDeposit deposit, Long operattorId, String operattorIp, String result, String remark) throws Exception;
}
