package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.vo.EastJgPaiHangVo;

/**
 * 涨跌Top10-Service
 *
 * @author aizhengdong
 *
 * @data 2015年3月5日
 */
public interface EastJgPaiHangService {

	/**
	 * 查询涨Top10
	 *
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月5日
	 */
	List<EastJgPaiHangVo> findUpPaiHang();
	
	/**
	 * 查询跌Top10
	 *
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月11日
	 */
	List<EastJgPaiHangVo> findDownPaiHang();
	
}
