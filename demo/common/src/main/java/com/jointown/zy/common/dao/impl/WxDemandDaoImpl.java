package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxDemandDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.vo.WxDemandVo;

/**
 * 求购信息DaoImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月12日
 */
@Repository
public class WxDemandDaoImpl extends BaseDaoImpl implements WxDemandDao {

	@Override
	public int deleteByPrimaryKey(Long demandId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.WxDemandDao.deleteByPrimaryKey",demandId);
	}

	@Override
	public int insert(WxDemand record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxDemandDao.insert", record);
	}

	@Override
	public int insertSelective(WxDemand record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxDemandDao.insertSelective", record);
	}

	@Override
	public WxDemand selectByPrimaryKey(Long demandId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxDemandDao.selectByPrimaryKey", demandId);
	}

	@Override
	public int updateByPrimaryKeySelective(WxDemand record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxDemandDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WxDemand record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxDemandDao.updateByPrimaryKey", record);
	}

	/**
	 * @see com.jointown.zy.common.dao.WxDemandDao#selectWxDemandsByCondition(com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<WxDemandVo> selectWxDemandsByCondition(Page<WxDemandVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxDemandDao.selectWxDemandsByCondition", page);
	}

    /**
     * @Description:查询东网与珍药材的供应信息（翻页，每次显示5条）
     * @author lichenxiao
     * @Date:2015年4月28日下午16：10
     * @param map
     * @return
     */
	@Override
	public List<WxDemandVo> selectInfoByDemand(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxDemandDao.selectInfoByDemand", map);
	}

	@Override
	public List<WxDemandVo> selectWxDemandsByPage(Page<WxDemand> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxDemandDao.selectWxDemandsByPage", page);
	}

	@Override
	public WxDemandVo selectWxDemandById(WxDemand wxDemand) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxDemandDao.selectWxDemandById", wxDemand);
	}

	/**
	 * @see com.jointown.zy.common.dao.WxDemandDao#selectWxDemandByIdFromBack(com.jointown.zy.common.model.WxDemand)
	 */
	@Override
	public WxDemandVo selectWxDemandByIdFromBack(WxDemand wxDemand) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxDemandDao.selectWxDemandByIdFromBack", wxDemand);
	}
}
