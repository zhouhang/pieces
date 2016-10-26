package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SpecialDao;
import com.ms.dao.model.Special;
import com.ms.dao.vo.SpecialVo;
import com.ms.service.AbsCommonService;
import com.ms.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialServiceImpl  extends AbsCommonService<Special> implements SpecialService{

	@Autowired
	private SpecialDao specialDao;


	@Override
	public PageInfo<SpecialVo> findByParams(SpecialVo specialVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<SpecialVo>  list = specialDao.findByParams(specialVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Special> getDao() {
		return specialDao;
	}

}
