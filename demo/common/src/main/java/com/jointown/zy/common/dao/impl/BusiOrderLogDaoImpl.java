package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;
import com.jointown.zy.common.util.GsonFactory;

@Repository
public class BusiOrderLogDaoImpl extends BaseDaoImpl implements BusiOrderLogDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getSqlSession().delete("com.jointown.zy.common.dao.BusiOrderLogDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insertBusiOrderLog(BusiOrder  orderInfo, String remark, Long operatorId, String optype) {
		BusiOrderLog log = new BusiOrderLog();
		log.setListingid(orderInfo.getListingid());
		log.setBuyer(orderInfo.getBuyer());
		log.setOperator(operatorId);
		log.setOptype(Short.valueOf(optype));
		log.setUserid(orderInfo.getUserid());
		log.setOrderid(orderInfo.getOrderid());
		if(BusinessLogEnum.ORDER_CREATED.getCode().equals(optype)){
			log.setOrderSnapshot("");
		} else {
			log.setOrderSnapshot(GsonFactory.createGson("yyyy-MM-dd HH:mm:ss.SSS").toJson(orderInfo));
		}
		log.setRemarks(remark);
		log.setWlid(orderInfo.getWlid());
		Date date = new Date();
		log.setCreatetime(date);
		log.setUpdatetime(date);
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderLogDao.insertBusiOrderLog", log);
	}

	@Override
	public int insertSelective(BusiOrderLog record) {
		return getSqlSession().insert("com.jointown.zy.common.dao.BusiOrderLogDao.insertBusiOrderLog", record);
	}

	@Override
	public BusiOrderLog selectByPrimaryKey(Long id) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.BusiOrderLogDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiOrderLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderLogDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(BusiOrderLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderLogDao.updateByPrimaryKeyWithBLOBs", record);
	}

	@Override
	public int updateByPrimaryKey(BusiOrderLog record) {
		return getSqlSession().update("com.jointown.zy.common.dao.BusiOrderLogDao.updateByPrimaryKey", record);
	}

	@Override
	public List<BusiOrderLog> selectOrderLogList(String orderId, boolean sortFlg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", orderId);
		if(sortFlg){
			map.put("sortFlg", "ASC");
		} else {
			map.put("sortFlg", "DESC");
		}
		return getSqlSession().selectList("com.jointown.zy.common.dao.BusiOrderLogDao.selectOrderLogList", map);
	}
}
