package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickCommodityDao;
import com.ms.dao.model.PickCommodity;
import com.ms.dao.vo.PickCommodityVo;
import com.ms.service.PickCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PickCommodityServiceImpl  extends AbsCommonService<PickCommodity> implements PickCommodityService{

	@Autowired
	private PickCommodityDao pickCommodityDao;


	@Override
	public PageInfo<PickCommodityVo> findByParams(PickCommodityVo pickCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PickCommodityVo>  list = pickCommodityDao.findByParams(pickCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<PickCommodity> getDao() {
		return pickCommodityDao;
	}

}
