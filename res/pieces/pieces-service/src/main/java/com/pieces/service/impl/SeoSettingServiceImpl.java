package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.SeoSettingDao;
import com.pieces.dao.model.SeoSetting;
import com.pieces.dao.vo.SeoSettingVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.SeoSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SeoSettingServiceImpl  extends AbsCommonService<SeoSetting> implements SeoSettingService{

	@Autowired
	private SeoSettingDao seoSettingDao;


	@Override
	public PageInfo<SeoSettingVo> findByParams(SeoSettingVo seoSettingVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<SeoSettingVo>  list = seoSettingDao.findByParams(seoSettingVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public SeoSettingVo findByType(Integer type) {
		return seoSettingDao.findByType(type);
	}

	@Override
	@Transactional
	public void save(SeoSettingVo seoSettingVo) {
		Date now=new Date();
		seoSettingVo.setCreateTime(now);
		if(seoSettingVo.getId()==null){
			seoSettingDao.create(seoSettingVo);
		}
		else{
			seoSettingVo.setUpdateTime(now);
			seoSettingDao.update(seoSettingVo);
		}
	}


	@Override
	public ICommonDao<SeoSetting> getDao() {
		return seoSettingDao;
	}



}
