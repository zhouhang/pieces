/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiAppealDao;
import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderCancelInfoVo;
import com.jointown.zy.common.vo.BossOrderCancelListVo;
import com.jointown.zy.common.vo.BusiAppealVo;

/**
 * @ClassName: BusiAppealDaoImpl
 * @Description: 订单申诉DAO实现类
 * @Author: 赵航
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
@Repository
public class BusiAppealDaoImpl extends BaseDaoImpl implements BusiAppealDao{

	@Override
	public List<BossOrderCancelListVo> selectOrderCancelListByPage(
			Page<BossOrderCancelListVo> page) throws Exception {
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiAppealDao.selectOrderCancelListByPage", page);
	}

	@Override
	public BossOrderCancelInfoVo selectOrderCancelInfoById(String orderId)
			throws Exception {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiAppealDao.selectOrderCancelInfoById", orderId);
	}

	@Override
	public int updateRejectReason(BusiAppeal appeal) throws Exception {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiAppealDao.updateRejectReason", appeal);
	}

	@Override
	public int updateExamineStateById(String orderId, String stateKey)
			throws Exception {
		BusiAppeal appeal = new BusiAppeal();
		appeal.setOrderId(orderId);
		appeal.setUpdateTime(new Date());
		appeal.setExamineState(Short.valueOf(stateKey));
		return getSqlSession().update("com.jointown.zy.common.dao.BusiAppealDao.updateExamineStateById", appeal);
	}
	
	@Override
	public BusiAppealVo selectBusiAppealVoByOrderId(Map<String,Object> map) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiAppealDao.selectBusiAppealVoByOrderId", map);
	}

	@Override
	public int insertBusiAppeal(BusiAppeal busiAppeal) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BusiAppealDao.insertBusiAppeal", busiAppeal);
	}

	@Override
	public BusiAppeal selectBusiAppealByOrderId(String orderId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.BusiAppealDao.selectBusiAppealByOrderId", orderId);
	}

}
