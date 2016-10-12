package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.CommodityDao;
import com.ms.dao.model.Commodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.service.AbsCommonService;
import com.ms.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommodityServiceImpl  extends AbsCommonService<Commodity> implements CommodityService{

	@Autowired
	private CommodityDao commodityDao;


	@Override
	public PageInfo<CommodityVo> findByParams(CommodityVo commodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CommodityVo>  list = commodityDao.findByParams(commodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Commodity> getDao() {
		return commodityDao;
	}

}
