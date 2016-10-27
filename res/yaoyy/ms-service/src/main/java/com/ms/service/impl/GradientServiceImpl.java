package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.GradientDao;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.GradientVo;
import com.ms.service.GradientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradientServiceImpl  extends AbsCommonService<Gradient> implements GradientService{

	@Autowired
	private GradientDao gradientDao;


	@Override
	public PageInfo<GradientVo> findByParams(GradientVo gradientVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<GradientVo>  list = gradientDao.findByParams(gradientVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Gradient> getDao() {
		return gradientDao;
	}

	@Override
	@Transactional
	public void update(List<Gradient> list) {
		if (list != null && list.size() >0){
			gradientDao.deleteByCommodityId(list.get(0).getCommodityId());
			gradientDao.batchCreate(list);
		}

	}

	@Override
	public List<Gradient> findByCommodityId(Integer id) {
		return gradientDao.findByCommodityId(id);
	}

	@Override
	@Transactional
	public void deleteByCommodityId(Integer id) {
		gradientDao.deleteByCommodityId(id);
	}

	@Override
	public String getCommodityPrice(Integer id) {
		String price = null;
		List<Gradient> list = findByCommodityId(id);
		if (list != null && list.size()>0) {
			price = "" + list.get(0).getPrice() + list.get(list.size()-1).getPrice();
		}
		return price;
	}
}
