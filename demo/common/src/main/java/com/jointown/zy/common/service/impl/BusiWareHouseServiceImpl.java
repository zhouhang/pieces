package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiWareHouseDao;
import com.jointown.zy.common.dto.BusiWareHouseDto;
import com.jointown.zy.common.model.BusiWareHouse;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.vo.BusiWareHouseVo;

/**
 * 仓库管理ServiceImpl
 * @author wangjunhu
 * 2014-12-22
 */
@Service
public class BusiWareHouseServiceImpl implements BusiWareHouseService {

	@Autowired
	private BusiWareHouseDao busiWareHouseDao;
	
	@Override
	public void addBusiWareHouse(BusiWareHouseDto busiWareHouseDto) {
		BusiWareHouse busiWareHouse = busiWareHouseDto.getBusiWareHouse();
		busiWareHouseDao.insertBusiWareHouse(busiWareHouse);
	}

	@Override
	public void alterBusiWareHouse(BusiWareHouseDto busiWareHouseDto) {
		BusiWareHouse busiWareHouse = busiWareHouseDto.getBusiWareHouse();
		busiWareHouseDao.updateBusiWareHouseById(busiWareHouse);
	}

	@Override
	public void removeBusiWareHouse(BusiWareHouseDto busiWareHouseDto){
		BusiWareHouse busiWareHouse = busiWareHouseDto.getBusiWareHouse();
		busiWareHouseDao.deleteBusiWareHouseById(busiWareHouse);
	};
	
	@Override
	public List<BusiWareHouseVo> findBusiWareHouses() {
		return busiWareHouseDao.selectBusiWareHouses();
	}

	@Override
	public List<BusiWareHouseVo> findBusiWareHousesByTree(){
		return busiWareHouseDao.selectBusiWareHousesByTree();
	};
	
	@Override
	public BusiWareHouseVo findBusiWareHouseById(String busiWareHouseId) {
		return busiWareHouseDao.selectBusiWareHouseById(busiWareHouseId);
	}

	@Override
	public List<BusiWareHouseVo> findBusiWareHousesByCondition(
			Page<BusiWareHouseVo> page) {
		return busiWareHouseDao.selectBusiWareHousesByCondition(page);
	}


	/**
	 * @description 根据用户id查询出用户的货物所在的仓库
	 * @author chengchang
	 * @param userid
	 */
	@Override
	public List<BusiWareHouseVo> selectBusiWareHousesByUserId(Long userId) {
		return busiWareHouseDao.selectBusiWareHousesByUserId(userId);
	}

}
