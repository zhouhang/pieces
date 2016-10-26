package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SpecialCommodityDao;
import com.ms.dao.model.SpecialCommodity;
import com.ms.dao.vo.SpecialCommodityVo;
import com.ms.service.SpecialCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialCommodityServiceImpl  extends AbsCommonService<SpecialCommodity> implements SpecialCommodityService{

	@Autowired
	private SpecialCommodityDao specialCommodityDao;


	@Override
	public PageInfo<SpecialCommodityVo> findByParams(SpecialCommodityVo specialCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<SpecialCommodityVo>  list = specialCommodityDao.findByParams(specialCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<SpecialCommodity> getDao() {
		return specialCommodityDao;
	}

}
