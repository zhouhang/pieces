package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.AdTypeDao;
import com.ms.dao.model.AdType;
import com.ms.dao.vo.AdTypeVo;
import com.ms.service.AdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdTypeServiceImpl  extends AbsCommonService<AdType> implements AdTypeService{

	@Autowired
	private AdTypeDao adTypeDao;


	@Override
	public PageInfo<AdTypeVo> findByParams(AdTypeVo adTypeVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AdTypeVo>  list = adTypeDao.findByParams(adTypeVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<AdType> getDao() {
		return adTypeDao;
	}

}
