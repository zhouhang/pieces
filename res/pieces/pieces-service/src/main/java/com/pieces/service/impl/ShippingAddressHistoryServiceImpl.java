package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.ShippingAddressDao;
import com.pieces.dao.ShippingAddressHistoryDao;
import com.pieces.dao.model.Area;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.ShippingAddressHistoryVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AreaService;
import com.pieces.service.ShippingAddressHistoryService;
import com.pieces.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShippingAddressHistoryServiceImpl  extends AbsCommonService<ShippingAddressHistory> implements ShippingAddressHistoryService{

	@Autowired
	private ShippingAddressHistoryDao shippingAddressHistoryDao;
	@Autowired
	private ShippingAddressService shippingAddressService;
	@Autowired
	private AreaService areaService;

	@Override
	public PageInfo<ShippingAddressHistoryVo> findByParams(ShippingAddressHistoryVo shippingAddressHistoryVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<ShippingAddressHistoryVo>  list = shippingAddressHistoryDao.findByParams(shippingAddressHistoryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	@Transactional
	public ShippingAddressHistory createByAddress(ShippingAddress shippingAddress) {
		ShippingAddressHistory shippingAddressHistory = new ShippingAddressHistory();
		shippingAddressHistory.setUserId(shippingAddress.getUserId());
		shippingAddressHistory.setConsignee(shippingAddress.getConsignee());
		//查找地区
		Area area = areaService.findParentsById(shippingAddress.getAreaId());
		String fullAddress = area.getProvince()+area.getCity()+area.getAreaname();
		shippingAddressHistory.setAreaId(area.getId());
		shippingAddressHistory.setArea(fullAddress);
		shippingAddressHistory.setPostcode(shippingAddress.getPostcode());
		shippingAddressHistory.setTel(shippingAddress.getTel());
		shippingAddressHistory.setDetail(shippingAddress.getDetail());
		shippingAddressHistory.setCreateTime(new Date());
		shippingAddressHistory.setAreaId(shippingAddress.getAreaId());
		shippingAddressHistoryDao.create(shippingAddressHistory);
		return shippingAddressHistory;
	}

	@Override
	@Transactional
	public ShippingAddressHistory createByAddress(Integer shippingAddressId) {
		ShippingAddress shippingAddress = shippingAddressService.findById(shippingAddressId);
		return createByAddress(shippingAddress);
	}


	@Override
	public ICommonDao<ShippingAddressHistory> getDao() {
		return shippingAddressHistoryDao;
	}

}
