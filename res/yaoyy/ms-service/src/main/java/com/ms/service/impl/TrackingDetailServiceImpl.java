package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.TrackingDetailDao;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.TrackingDetailVo;
import com.ms.service.TrackingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrackingDetailServiceImpl  extends AbsCommonService<TrackingDetail> implements TrackingDetailService{

	@Autowired
	private TrackingDetailDao trackingDetailDao;


	@Override
	public PageInfo<TrackingDetailVo> findByParams(TrackingDetailVo trackingDetailVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<TrackingDetailVo>  list = trackingDetailDao.findByParams(trackingDetailVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<TrackingDetail> getDao() {
		return trackingDetailDao;
	}

}
