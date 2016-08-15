package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.ShippingAddressHistoryDao;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.ShippingAddressHistoryVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.ShippingAddressHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShippingAddressHistoryServiceImpl  extends AbsCommonService<ShippingAddressHistory> implements ShippingAddressHistoryService{

	@Autowired
	private ShippingAddressHistoryDao shippingAddressHistoryDao;


	@Override
	public PageInfo<ShippingAddressHistoryVo> findByParams(ShippingAddressHistoryVo shippingAddressHistoryVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<ShippingAddressHistoryVo>  list = shippingAddressHistoryDao.findByParams(shippingAddressHistoryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<ShippingAddressHistory> getDao() {
		return shippingAddressHistoryDao;
	}

}
