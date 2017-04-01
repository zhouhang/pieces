package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.ShippingAddressDao;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AreaService;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.BeanUtils;
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
		// 如果用户的收货地址只有一个设置为默认收货地址.
		if (list.size() == 1){
			list.get(0).setIsDefault(true);
		}
		return list;
	}

	@Override
	public List<ShippingAddressVo> findByUser(Integer userId) {
		ShippingAddressVo shippingAddressVo = new ShippingAddressVo();
		shippingAddressVo.setUserId(userId);
		return setFullAdd(findByParams(shippingAddressVo));
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

	@Override
	@Transactional
	public void saveOrUpdate(ShippingAddress address, User user) {
		// 如果用户的收货地址只有一个设置为默认收货地址.
		if (shippingAddressDao.getCountByUserId(user.getId()) <1){
			address.setIsDefault(true);
		}

		if (address.getId() == null) {
			address.setCreateTime(new Date());
			address.setUserId(user.getId());
			shippingAddressDao.create(address);
		} else {
			shippingAddressDao.update(address);
		}
		if (address.getIsDefault()!=null && address.getIsDefault()) {
			// 如果更新时设置为默认地址
			settingDefaultAddress(address.getId(), address.getUserId());
		}
	}

	@Override
	public ShippingAddressVo findVoById(Integer id) {
		ShippingAddress shippingAddress = findById(id);
		ShippingAddressVo vo = new ShippingAddressVo();
		BeanUtils.copy(shippingAddress, vo);
		Area area = areaService.findParentsById(vo.getAreaId());
		vo.setProvinceId(area.getProvinceId());
		vo.setCityId(area.getCityId());
		getFullAdd(vo);
		return vo;
	}

	@Override
	public ShippingAddressVo getAddressById(Integer addressId, Integer userId) {
		ShippingAddressVo shippingAddress = null;
		List<ShippingAddressVo>  shippingAddressList = findByUser(userId);
		if(addressId!= null) {
			for(ShippingAddressVo svo : shippingAddressList){
				if(svo.getId().equals(addressId)){
					shippingAddress =  svo;
					break;
				}
			}
		} else {
			shippingAddress = ValidUtils.listNotBlank(shippingAddressList) ? shippingAddressList.get(0) : null;
		}
		return shippingAddress;
	}

	/**
	 * 设置地址全称
	 */
	private List<ShippingAddressVo> setFullAdd(List<ShippingAddressVo>  shippingAddressList){
		for(ShippingAddressVo svo : shippingAddressList){
			getFullAdd(svo);
		}
		return shippingAddressList;
	}

	/**
	 * 获取地址全称
	 */
	private void getFullAdd(ShippingAddressVo shippingAddressVo){
		if (shippingAddressVo.getAreaId() != null) {
			Area area = areaService.findParentsById(shippingAddressVo.getAreaId());
			shippingAddressVo.setArea(area);
			shippingAddressVo.setFullAdd( area.getProvince() + area.getCity() + area.getAreaname());
		}
	}
}
