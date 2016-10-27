package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.AdDao;
import com.ms.dao.model.Ad;
import com.ms.dao.vo.AdVo;
import com.ms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdServiceImpl  extends AbsCommonService<Ad> implements AdService{

	@Autowired
	private AdDao adDao;


	@Override
	public PageInfo<AdVo> findByParams(AdVo adVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AdVo>  list = adDao.findByParams(adVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<AdVo> findByType(Integer typeId) {
		AdVo vo = new AdVo();
		vo.setTypeId(typeId);
		vo.setStatus(1);
		return adDao.findByParams(vo);
	}

	@Override
	public ICommonDao<Ad> getDao() {
		return adDao;
	}

}
