package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SpecialCommodityDao;
import com.ms.dao.SpecialDao;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Special;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.SpecialCommodityVo;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.CommodityService;
import com.ms.service.GradientService;
import com.ms.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialServiceImpl  extends AbsCommonService<Special> implements SpecialService{

	@Autowired
	private SpecialDao specialDao;

	@Autowired
	private SpecialCommodityDao specialCommodityDao;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private GradientService gradientService;


	@Override
	public PageInfo<SpecialVo> findByParams(SpecialVo specialVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<SpecialVo>  list = specialDao.findByParams(specialVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public PageInfo<CommodityVo> findCommodity(Integer specId, Integer pageNum, Integer pageSize) {
		if (pageNum == null || pageSize == null) {
			pageNum = 1;
			pageSize = 10;
		}
		SpecialCommodityVo vo = new SpecialCommodityVo();
		vo.setSpecialId(specId);

		PageHelper.startPage(pageNum, pageSize);
		List<SpecialCommodityVo> list = specialCommodityDao.findByParams(vo);
		PageInfo pageInfo = new PageInfo(list);

		StringBuilder ids = new StringBuilder();
		if (list != null && list.size() >0){
			list.forEach(sc ->{
				ids.append(sc.getCommodityId()).append(",");
			});
		}
		List<Commodity> commodities = commodityService.findByIds(ids.substring(0,ids.length()-1));
		pageInfo.setList(commodities);

		return pageInfo;
	}

	@Override
	public List<Commodity> findCommodity(Integer specId) {
		SpecialCommodityVo vo = new SpecialCommodityVo();
		vo.setSpecialId(specId);

		List<SpecialCommodityVo> list = specialCommodityDao.findByParams(vo);
		StringBuilder ids = new StringBuilder();
		if (list != null && list.size() >0){
			list.forEach(sc ->{
				ids.append(sc.getCommodityId()).append(",");
			});
		}
		List<Commodity> commodities = commodityService.findByIds(ids.substring(0,ids.length()-1));
		// 如果标识为量大价优 策去价格梯度表获取价格范围
		commodities.forEach(commodity -> {
			if (commodity.getMark() == 1){
				commodity.setDetail(gradientService.getCommodityPrice(commodity.getId()));
			} else {
				commodity.setDetail(null);
			}
		});
		return commodities;
	}

	@Override
	public ICommonDao<Special> getDao() {
		return specialDao;
	}

}
