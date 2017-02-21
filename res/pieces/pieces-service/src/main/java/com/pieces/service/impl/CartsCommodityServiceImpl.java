package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CartsCommodityDao;
import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.vo.CartsCommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CartsCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartsCommodityServiceImpl  extends AbsCommonService<CartsCommodity> implements CartsCommodityService{

	@Autowired
	private CartsCommodityDao cartsCommodityDao;


	@Override
	public PageInfo<CartsCommodityVo> findByParams(CartsCommodityVo cartsCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CartsCommodityVo>  list = cartsCommodityDao.findByParams(cartsCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<CartsCommodity> getDao() {
		return cartsCommodityDao;
	}

}
