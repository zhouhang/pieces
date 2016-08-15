package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.ShippingAddressDao;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShippingAddressServiceImpl  extends AbsCommonService<ShippingAddress> implements ShippingAddressService{

	@Autowired
	private ShippingAddressDao shippingAddressDao;


	@Override
	public PageInfo<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<ShippingAddressVo>  list = shippingAddressDao.findByParams(shippingAddressVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<ShippingAddress> getDao() {
		return shippingAddressDao;
	}

}
