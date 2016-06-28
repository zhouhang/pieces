package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxSupplyPicDao;
import com.jointown.zy.common.model.WxSupplyPic;

/**
 * 
 * @ClassName: WxSupplyPicDaoImpl
 * @Description: 供应信息图片DapImpl
 * @Author: wangjunhu
 * @Date: 2015年4月12日
 * @Version: 1.0
 */
@Repository
public class WxSupplyPicDaoImpl extends BaseDaoImpl implements WxSupplyPicDao {

	@Override
	public int deleteByPrimaryKey(Long supplyPicId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.WxSupplyPicDao.deleteByPrimaryKey",supplyPicId);
	}

	@Override
	public int insert(WxSupplyPic record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxSupplyPicDao.insert", record);
	}

	@Override
	public int insertSelective(WxSupplyPic record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxSupplyPicDao.insertSelective", record);
	}

	@Override
	public WxSupplyPic selectByPrimaryKey(Long supplyPicId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyPicDao.selectByPrimaryKey", supplyPicId);
	}

	@Override
	public int updateByPrimaryKeySelective(WxSupplyPic record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxSupplyPicDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WxSupplyPic record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxSupplyPicDao.updateByPrimaryKey", record);
	}
	
	@Override
	public List<WxSupplyPic> selectAllBySupplyPic(Long supplyId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyPicDao.selectAllBySupplyPic", supplyId);
	}

	@Override
	public WxSupplyPic selectSupplyIdBySupplyPic(String picPath) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyPicDao.selectSupplyIdBySupplyPic", picPath);
	}

}
