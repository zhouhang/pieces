package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.HistoryCommodityDao;
import com.ms.dao.model.HistoryCommodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.HistoryCommodityVo;
import com.ms.service.HistoryCommodityService;
import com.ms.tools.upload.PathConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryCommodityServiceImpl  extends AbsCommonService<HistoryCommodity> implements HistoryCommodityService{

	@Autowired
	private HistoryCommodityDao historyCommodityDao;


	@Autowired
	private PathConvert pathConvert;


	@Override
	public PageInfo<HistoryCommodityVo> findByParams(HistoryCommodityVo historyCommodityVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<HistoryCommodityVo>  list = historyCommodityDao.findByParams(historyCommodityVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<HistoryCommodityVo> findByIds(String ids) {
		List<Integer> list = new ArrayList<>();
		for(String id :ids.split(",")){
			list.add(Integer.parseInt(id));
		}
		List<HistoryCommodityVo> commodities= historyCommodityDao.findByIds(list);
		commodities.forEach(c->{
			c.setPictureUrl(pathConvert.getUrl(c.getPictureUrl()));
		});
		return commodities;
	}

	@Override
	@Transactional
	public HistoryCommodity saveCommodity(CommodityVo commodityVo) {
		HistoryCommodityVo	historyCommodity=new HistoryCommodityVo();
		historyCommodity.setName(commodityVo.getName());
		historyCommodity.setCommodityId(commodityVo.getId());
		historyCommodity.setTitle(commodityVo.getTitle());
		historyCommodity.setOrigin(commodityVo.getOrigin());
		historyCommodity.setPictureUrl(commodityVo.getPictureUrl());
		historyCommodity.setThumbnailUrl(commodityVo.getThumbnailUrl());
		historyCommodity.setSpec(commodityVo.getSpec());
		historyCommodity.setUnit(commodityVo.getUnitName());
		historyCommodity.setPrice(commodityVo.getPrice());
		historyCommodity.setCreateTime(new Date());
		historyCommodityDao.create(historyCommodity);
		return historyCommodity;
	}

	@Override
	public List<HistoryCommodity> findByName(String name) {
		return historyCommodityDao.findByName(name);
	}


	@Override
	public ICommonDao<HistoryCommodity> getDao() {
		return historyCommodityDao;
	}

}
