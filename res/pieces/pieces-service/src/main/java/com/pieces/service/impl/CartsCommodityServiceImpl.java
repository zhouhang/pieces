package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CartsCommodityDao;
import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CartsCommodityVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CartsCommodityService;
import com.pieces.service.CommodityService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.collect.HppcMaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartsCommodityServiceImpl  extends AbsCommonService<CartsCommodity> implements CartsCommodityService{

	@Autowired
	private CartsCommodityDao cartsCommodityDao;

	@Autowired
	private CommodityService commodityService;


	@Override
	public PageInfo<CartsCommodityVo> findByParams(CartsCommodityVo cartsCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CartsCommodityVo>  list = cartsCommodityDao.findByParams(cartsCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public void save(CartsCommodity cartsCommodity) {
		cartsCommodity.setCreateTime(new Date());
		cartsCommodityDao.create(cartsCommodity);
	}

	@Override
	@Transactional
	public Integer deleteByVo(CartsCommodityVo cartsCommodityVo) {
		return cartsCommodityDao.deleteByVo(cartsCommodityVo);
	}

	@Override
	@Transactional
	public void combine(String [] ids,User user) {
		List<CartsCommodityVo> cartsCommodityVos=new ArrayList<CartsCommodityVo>();
		for(String id:ids){
			CartsCommodityVo cartsCommodityVo=new CartsCommodityVo();
			cartsCommodityVo.setUserId(user.getId());
			cartsCommodityVo.setCommodityId(Integer.parseInt(id));
			cartsCommodityVo.setCreateTime(new Date());
			cartsCommodityVos.add(cartsCommodityVo);
		}
		if(cartsCommodityVos.size()!=0){
			cartsCommodityDao.combine(cartsCommodityVos);
		}

	}

	@Override
	public List<Integer> getIds(Integer userId) {
		return cartsCommodityDao.getIds(userId);
	}


	@Override
	public ICommonDao<CartsCommodity> getDao() {
		return cartsCommodityDao;
	}

}
