/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderDepositDao;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderDeposit;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.vo.BossOrderDepositVo;

/**
 * @ClassName: BusiOrderDepositDaoImpl
 * @Description: 订单划账操作DAO实现类
 * @Author: 赵航
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
@Repository
public class BusiOrderDepositDaoImpl extends BaseDaoImpl implements BusiOrderDepositDao{
	
	@Override
	public BossOrderDepositVo getCanceledOrderDepositInfo(String orderId) throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDepositDao.getCanceledOrderDepositInfo", orderId);
	}

	@Override
	public List<BossOrderDepositVo> getDepositListByPage(
			Page<BossOrderDepositVo> page) throws Exception {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderDepositDao.getDepositListByPage", page);
	}

	@Override
	public int insertOrderDeposit(BusiOrder order, String depositType)
			throws Exception {
		BusiOrderDeposit deposit = new BusiOrderDeposit();
		deposit.setOrderId(order.getOrderid());
		deposit.setWlid(order.getWlid());
		deposit.setListingId(order.getListingid());
		deposit.setSellerId(order.getUserid());
		deposit.setBuyerId(order.getBuyer());
		deposit.setDepositType(Short.valueOf(depositType));
		deposit.setDepositAmount(order.getActualPayment());
		//以下3个值均为系统按默认方式预算的值，实际划账时可以修改
		BigDecimal platformAmount = null;
		if(BusiOrderDepositTypeEnum.ORDER_FINISHED_DEPOSIT.getCode().equals(depositType)){
			//订单完成划账
			platformAmount = BigDecimalUtil.formateBigDecimal2(order.getActualPayment()
					.multiply(new BigDecimal(BusiParamEnum.BUSI_PLATFORM_FINISHED_RATE.getInfo())));
			
			deposit.setBuyerAmount(new BigDecimal(0));//预设值：0
			deposit.setPlatformAmount(platformAmount);//预设值：总数的千分之五
			deposit.setSellerAmount(BigDecimalUtil.formateBigDecimal2(order.getActualPayment().subtract(platformAmount)));//预设值：总数的剩余
		} else if(BusiOrderDepositTypeEnum.ORDER_OVERTIME_DEPOSIT.getCode().equals(depositType)){
			//订单过期划账
			platformAmount = BigDecimalUtil.formateBigDecimal2(order.getActualPayment()
					.multiply(new BigDecimal(BusiParamEnum.BUSI_PLATFORM_OVERTIME_RATE.getInfo())));
			
			deposit.setBuyerAmount(new BigDecimal(0));//预设值：0
			deposit.setPlatformAmount(platformAmount);//预设值：总数的百分之五十
			deposit.setSellerAmount(BigDecimalUtil.formateBigDecimal2(order.getActualPayment().subtract(platformAmount)));//预设值：总数的剩余
		} else if(BusiOrderDepositTypeEnum.ORDER_REFUND_DEPOSIT.getCode().equals(depositType)){
			//订单申退划账
			deposit.setBuyerAmount(BigDecimalUtil.formateBigDecimal2(order.getActualPayment()));//预设值：全部
			deposit.setPlatformAmount(new BigDecimal(0));//预设值：0
			deposit.setSellerAmount(new BigDecimal(0));//预设值：0
		}
		//如果是订单过期划账，设定过期时间
		if(BusiOrderDepositTypeEnum.ORDER_OVERTIME_DEPOSIT.getCode().equals(depositType)){
			deposit.setOrderUpd(order.getExpiretime());
		} else {
			deposit.setOrderUpd(order.getUpdatetime());
		}
		Date date = new Date();
		deposit.setCreatetime(date);
		deposit.setUpdatetime(date);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderDepositDao.insertOrderDeposit", deposit);
	}

	@Override
	public int updateOrderDepositByOrderId(BusiOrderDeposit deposit)
			throws Exception {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDepositDao.updateOrderDepositByOrderId", deposit);
	}

	@Override
	public BusiOrderDeposit selectOrderDepositByLock(String orderId)
			throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDepositDao.selectOrderDepositByLock", orderId);
	}

	@Override
	public int updateOrderDepositByPayFinish(BusiOrderDeposit deposit)
			throws Exception {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderDepositDao.updateOrderDepositByPayFinish", deposit);
	}

	@Override
	public BusiOrderDeposit selectSingleOrderDeposit(String orderId)
			throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderDepositDao.selectSingleOrderDeposit", orderId);
	}

}
