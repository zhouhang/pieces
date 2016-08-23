package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.ShippingAddressDao;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AreaService;
import com.pieces.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShippingAddressServiceImpl  extends AbsCommonService<ShippingAddress> implements ShippingAddressService{

	@Autowired
	private ShippingAddressDao shippingAddressDao;
	@Autowired
	private AreaService areaService;

	@Override
	public PageInfo<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<ShippingAddressVo>  list = shippingAddressDao.findByParams(shippingAddressVo);
        PageInfo page = new PageInfo(list);
        return page;
	}




	@Override
	public List<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo) {
		List<ShippingAddressVo>  list = shippingAddressDao.findByParams(shippingAddressVo);
		return list;
	}

	@Override
	public List<ShippingAddressVo> findByUser(Integer userId) {
		ShippingAddressVo shippingAddressVo = new ShippingAddressVo();
		shippingAddressVo.setUserId(userId);
		List<ShippingAddressVo> list = findByParams(shippingAddressVo);
		for(ShippingAddressVo shippingAddress : list){
			Area area =areaService.findParentsById(shippingAddress.getAreaId());
			shippingAddress.setFullAdd(area.getProvince()+area.getCity()+area.getAreaname());
			shippingAddress.setArea(area);
		}
		return list;
	}



	@Override
	@Transactional
	public void delete(int userId, int id) {
		shippingAddressDao.deleteByUserIdAndId(userId,id);
	}

	@Override
	@Transactional
	public void settingDefaultAddress(Integer addressId,Integer userId) {
		shippingAddressDao.updateAllNotDefault(userId);
		ShippingAddress shippingAddress =	findById(addressId);
		shippingAddress.setIsDefault(true);
		this.update(shippingAddress);
	}


	@Override
	public ICommonDao<ShippingAddress> getDao() {
		return shippingAddressDao;
	}

}
