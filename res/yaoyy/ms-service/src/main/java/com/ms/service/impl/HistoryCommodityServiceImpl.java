package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.HistoryCommodityDao;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.vo.HistoryCommodityVo;
import com.ms.service.HistoryCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistoryCommodityServiceImpl  extends AbsCommonService<HistoryCommodity> implements HistoryCommodityService{

	@Autowired
	private HistoryCommodityDao historyCommodityDao;


	@Override
	public PageInfo<HistoryCommodityVo> findByParams(HistoryCommodityVo historyCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<HistoryCommodityVo>  list = historyCommodityDao.findByParams(historyCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<HistoryCommodity> getDao() {
		return historyCommodityDao;
	}

}
