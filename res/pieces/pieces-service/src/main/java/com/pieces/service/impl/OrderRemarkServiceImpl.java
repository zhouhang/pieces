package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.OrderRemarkDao;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderRemarkVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderRemarkServiceImpl  extends AbsCommonService<OrderRemark> implements OrderRemarkService{

	@Autowired
	private OrderRemarkDao orderRemarkDao;


	@Override
	public PageInfo<OrderRemarkVo> findByParams(OrderRemarkVo orderRemarkVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<OrderRemarkVo>  list = orderRemarkDao.findByParams(orderRemarkVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<OrderRemark> getDao() {
		return orderRemarkDao;
	}


}
