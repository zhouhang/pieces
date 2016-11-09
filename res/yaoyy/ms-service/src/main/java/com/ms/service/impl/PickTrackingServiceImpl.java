package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.PickDao;
import com.ms.dao.PickTrackingDao;
import com.ms.dao.enums.PickEnum;
import com.ms.dao.enums.PickTrackingTypeEnum;
import com.ms.dao.model.Pick;
import com.ms.dao.model.PickTracking;
import com.ms.dao.vo.PickTrackingVo;
import com.ms.dao.vo.PickVo;
import com.ms.service.PickTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PickTrackingServiceImpl  extends AbsCommonService<PickTracking> implements PickTrackingService{

	@Autowired
	private PickTrackingDao pickTrackingDao;
	@Autowired
	private PickDao pickDao;


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
	@Transactional
	public void save(PickTrackingVo pickTrackingVo) {
		Integer recordType=pickTrackingVo.getRecordType();
		Date now=new Date();
		if(recordType!= PickTrackingTypeEnum.PICK_RECORD.getValue()&&recordType!=PickTrackingTypeEnum.PICK_APPLY.getValue()){
			Pick pick =new Pick();
			pick.setId(pickTrackingVo.getPickId());
			if(pickTrackingVo.getRecordType()==PickTrackingTypeEnum.PICK_AGREE.getValue()){
				pick.setStatus(PickEnum.PICK_HANDING.getValue());
			}
			else if(pickTrackingVo.getRecordType()==PickTrackingTypeEnum.TPICK_REFUSE.getValue()){
				pick.setStatus(PickEnum.PICK_REFUSE.getValue());
			}
			else if(pickTrackingVo.getRecordType()==PickTrackingTypeEnum.PICK_FINISH.getValue()){
				pick.setStatus(PickEnum.PICK_FINISH.getValue());
			}
			else{
				pick.setStatus(PickEnum.PICK_NOTFINISH.getValue());
			}

			pick.setUpdateTime(now);
			pickDao.update(pick);
		}

		if(pickTrackingVo.getExtra()==null){
			pickTrackingVo.setExtra("");
		}
		pickTrackingVo.setCreateTime(now);
		pickTrackingVo.setUpdateTime(now);

		pickTrackingDao.create(pickTrackingVo);

	}


	@Override
	public ICommonDao<PickTracking> getDao() {
		return pickTrackingDao;
	}

}
