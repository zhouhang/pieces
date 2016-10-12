package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.GradientDao;
import com.ms.dao.model.Gradient;
import com.ms.dao.vo.GradientVo;
import com.ms.service.AbsCommonService;
import com.ms.service.GradientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
