package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SampleTrackingDao;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.service.SampleTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SampleTrackingServiceImpl  extends AbsCommonService<SampleTracking> implements SampleTrackingService{

	@Autowired
	private SampleTrackingDao sampleTrackingDao;


	@Override
	public PageInfo<SampleTrackingVo> findByParams(SampleTrackingVo sampleTrackingVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<SampleTrackingVo>  list = sampleTrackingDao.findByParams(sampleTrackingVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<SampleTrackingVo> findAllByParams(SampleTrackingVo sampleTrackingVo) {
		return sampleTrackingDao.findByParams(sampleTrackingVo);
	}


	@Override
	public ICommonDao<SampleTracking> getDao() {
		return sampleTrackingDao;
	}

}
