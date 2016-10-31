package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickTrackingDao;
import com.ms.dao.model.PickTracking;
import com.ms.dao.vo.PickTrackingVo;
import com.ms.service.PickTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PickTrackingServiceImpl  extends AbsCommonService<PickTracking> implements PickTrackingService{

	@Autowired
	private PickTrackingDao pickTrackingDao;


	@Override
	public PageInfo<PickTrackingVo> findByParams(PickTrackingVo pickTrackingVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PickTrackingVo>  list = pickTrackingDao.findByParams(pickTrackingVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<PickTrackingVo> findByPickId(Integer pickId) {
		PickTrackingVo pickTrackingVo=new PickTrackingVo();
		pickTrackingVo.setPickId(pickId);
		return pickTrackingDao.findByParams(pickTrackingVo);
	}


	@Override
	public ICommonDao<PickTracking> getDao() {
		return pickTrackingDao;
	}

}
