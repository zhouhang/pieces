package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.vo.EastJgPaiHangVo;

/**
 * 涨跌Top10-Dao
 *
 * @author aizhengdong
 *
 * @data 2015年3月5日
 */
public interface EastJgPaiHangDao {

	/**
	 * 查询涨Top10
	 *
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月5日
	 */
	List<EastJgPaiHangVo> selectUpPaiHang();
	
	/**
	 * 查询跌Top10
	 *
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月11日
	 */
	List<EastJgPaiHangVo> selectDownPaiHang();
}
