package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickCommodityDao;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.model.PickCommodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.HistoryCommodityVo;
import com.ms.dao.vo.PickCommodityVo;
import com.ms.service.CommodityService;
import com.ms.service.HistoryCommodityService;
import com.ms.service.PickCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PickCommodityServiceImpl  extends AbsCommonService<PickCommodity> implements PickCommodityService{

	@Autowired
	private PickCommodityDao pickCommodityDao;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private HistoryCommodityService historyCommodityService;


	@Override
	public PageInfo<PickCommodityVo> findByParams(PickCommodityVo pickCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PickCommodityVo>  list = pickCommodityDao.findByParams(pickCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<PickCommodityVo> findByPickId(Integer pickId) {
		PickCommodityVo pickCommodityVo=new PickCommodityVo();
		pickCommodityVo.setPickId(pickId);
		return pickCommodityDao.findByParams(pickCommodityVo);
	}

	@Override
	@Transactional
	public void saveList(List<PickCommodityVo> pickCommodities) {
		Date now=new Date();
        pickCommodities.forEach(p->{
			CommodityVo commodityVo=commodityService.findById(p.getCommodityId());
			HistoryCommodity historyCommodity=historyCommodityService.saveCommodity(commodityVo);
			p.setCommodityId(historyCommodity.getId());
			p.setPrice(commodityVo.getPrice());
			float total=(commodityVo.getPrice())*(p.getNum());
			p.setTotal(total);
			p.setUnit(commodityVo.getUnitName());
		    p.setCreateTime(now);
		    pickCommodityDao.create(p);
	  });
	}


	@Override
	public ICommonDao<PickCommodity> getDao() {
		return pickCommodityDao;
	}

}
