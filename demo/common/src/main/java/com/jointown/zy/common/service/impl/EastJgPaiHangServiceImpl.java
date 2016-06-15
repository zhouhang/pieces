package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastJgPaiHangDao;
import com.jointown.zy.common.service.EastJgPaiHangService;
import com.jointown.zy.common.vo.EastJgPaiHangVo;

/**
 * 涨跌Top10-ServiceImpl
 *
 * @author aizhengdong
 *
 * @data 2015年3月5日
 */
@Service
public class EastJgPaiHangServiceImpl implements EastJgPaiHangService {
	@Autowired
	private EastJgPaiHangDao eastJgPaiHangDao;

	/**
	 * @see com.jointown.zy.common.service.EastJgPaiHangService#findUpPaiHang()
	 */
	@Override
	public List<EastJgPaiHangVo> findUpPaiHang() {
		List<EastJgPaiHangVo> paiHangs = eastJgPaiHangDao.selectUpPaiHang();
		for (EastJgPaiHangVo paiHang : paiHangs) {
			String hangQing = paiHang.getHangQing();
			paiHang.setHangQing(hangQing + "%");
		}

		return paiHangs;
	}

	/**
	 * @see com.jointown.zy.common.service.EastJgPaiHangService#findDownPaiHang()
	 */
	@Override
	public List<EastJgPaiHangVo> findDownPaiHang() {
		List<EastJgPaiHangVo> paiHangs = eastJgPaiHangDao.selectDownPaiHang();
		for (EastJgPaiHangVo paiHang : paiHangs) {
			String hangQing = paiHang.getHangQing().replace("-", "");
			paiHang.setHangQing(hangQing + "%");
		}

		return paiHangs;
	}

}
