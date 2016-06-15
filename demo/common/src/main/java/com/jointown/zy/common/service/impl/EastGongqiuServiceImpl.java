package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastGongqiuDao;
import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.service.EastGongqiuService;
import com.jointown.zy.common.vo.EastGongqiuVo;

/**
 * 
 * @ClassName: EastGongqiuDaoServiceImpl
 * @Description: 东方中药材网供求信息ServiceImpl
 * @Author: wangjunhu
 * @Date: 2015年6月12日
 * @Version: 1.0
 */
@Service
public class EastGongqiuServiceImpl implements EastGongqiuService {
	
	@Autowired
	private EastGongqiuDao eastGongqiuDao;

	@Override
	public EastGongqiu findEastGongqiuById(BigDecimal gqid) throws Exception {
		return eastGongqiuDao.selectByPrimaryKey(gqid);
	}

	@Override
	public void updateEastGongqiu(EastGongqiu eastGongqiu) throws Exception {
		eastGongqiuDao.updateByPrimaryKeySelective(eastGongqiu);
	}

	/**
	 * @see com.jointown.zy.common.service.EastGongqiuService#findEastGongqiuById(java.lang.Integer)
	 */
	@Override
	public EastGongqiuVo findEastGongqiuById(Long gqid) {
		return eastGongqiuDao.selectEastGongqiuById(gqid);
	}

}
