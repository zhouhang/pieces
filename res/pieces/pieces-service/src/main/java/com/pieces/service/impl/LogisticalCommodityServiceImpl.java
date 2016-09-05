package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.LogisticalCommodityDao;
import com.pieces.dao.model.LogisticalCommodity;
import com.pieces.dao.vo.LogisticalCommodityVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.LogisticalCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogisticalCommodityServiceImpl  extends AbsCommonService<LogisticalCommodity> implements LogisticalCommodityService{

	@Autowired
	private LogisticalCommodityDao logisticalCommodityDao;


	@Override
	public PageInfo<LogisticalCommodityVo> findByParams(LogisticalCommodityVo logisticalCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<LogisticalCommodityVo>  list = logisticalCommodityDao.findByParams(logisticalCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<LogisticalCommodity> getDao() {
		return logisticalCommodityDao;
	}

}
