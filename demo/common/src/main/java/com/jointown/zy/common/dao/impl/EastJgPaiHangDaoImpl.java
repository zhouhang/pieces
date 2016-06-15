package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastJgPaiHangDao;
import com.jointown.zy.common.vo.EastJgPaiHangVo;

/**
 * 涨跌Top10-DaoImpl
 *
 * @author aizhengdong
 *
 * @data 2015年3月5日
 */
@Repository
public class EastJgPaiHangDaoImpl extends BaseDaoImpl implements EastJgPaiHangDao {

	/**
	 * @see com.jointown.zy.common.dao.EastJgPaiHangDao#selectUpPaiHang()
	 */
	@Override
	public List<EastJgPaiHangVo> selectUpPaiHang() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastJgPaiHangDao.selectUpPaiHang");
	}

	/**
	 * @see com.jointown.zy.common.dao.EastJgPaiHangDao#selectDownPaiHang()
	 */
	@Override
	public List<EastJgPaiHangVo> selectDownPaiHang() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastJgPaiHangDao.selectDownPaiHang");
	}

}
