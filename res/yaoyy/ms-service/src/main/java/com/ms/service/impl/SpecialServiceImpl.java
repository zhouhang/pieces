package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SpecialCommodityDao;
import com.ms.dao.SpecialDao;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.Special;
import com.ms.dao.model.SpecialCommodity;
import com.ms.dao.vo.CommodityVo;
import com.ms.dao.vo.SpecialCommodityVo;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.CommodityService;
import com.ms.service.GradientService;
import com.ms.service.SpecialService;
import com.ms.tools.upload.PathConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

	@Autowired
	private PathConvert pathConvert;

	private String folderName = "special/";


	@Override
	public PageInfo<SpecialVo> findByParams(SpecialVo specialVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
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
		List<CommodityVo> commodities = commodityService.findByIds(ids.substring(0,ids.length()-1));
		pageInfo.setList(commodities);

		return pageInfo;
	}

	@Override
	public List<CommodityVo> findCommodity(Integer specId) {
		SpecialCommodityVo vo = new SpecialCommodityVo();
		vo.setSpecialId(specId);

		List<SpecialCommodityVo> list = specialCommodityDao.findByParams(vo);
		StringBuilder ids = new StringBuilder();
		if (list != null && list.size() >0){
			list.forEach(sc ->{
				ids.append(sc.getCommodityId()).append(",");
			});
		}
		List<CommodityVo> commodities = commodityService.findByIds(ids.substring(0,ids.length()-1));
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
	@Transactional
	public void save(SpecialVo specialVo) {
		Date now=new Date();
		List<Integer> list = new ArrayList<>();
		for(String id :specialVo.getCommodities().split(",")){
			list.add(Integer.parseInt(id));
		}
		if(specialVo.getDescription()==null){
			specialVo.setDescription("");
		}
		if (specialVo.getStatus()==null){
			specialVo.setStatus(0);
		}
		specialVo.setPictuerUrl(pathConvert.saveFileFromTemp(specialVo.getPictuerUrl(),folderName));
		if(specialVo.getSort()==null){
			specialVo.setSort(0);
		}
		if (specialVo.getId()==null){
			specialVo.setCreateTime(now);
			specialVo.setUpdateTime(now);
			specialDao.create(specialVo);

		}
		else{
			 specialVo.setUpdateTime(now);
             specialDao.update(specialVo);
			 specialCommodityDao.deleteBySpecialId(specialVo.getId());

		}
		list.forEach(s->{
			SpecialCommodity specialCommodity=new SpecialCommodity();
			specialCommodity.setCommodityId(s);
			specialCommodity.setSpecialId(specialVo.getId());
			specialCommodityDao.create(specialCommodity);
		});

	}

	@Override
	public ICommonDao<Special> getDao() {
		return specialDao;
	}

	@Override
	@Transactional
	public int deleteById(int id) {
		specialCommodityDao.deleteBySpecialId(id);
		return super.deleteById(id);
	}

	@Override
	public Special findById(int id){
		Special special=specialDao.findById(id);
		special.setPictuerUrl(pathConvert.getUrl(special.getPictuerUrl()));
		return special;
	}

}
