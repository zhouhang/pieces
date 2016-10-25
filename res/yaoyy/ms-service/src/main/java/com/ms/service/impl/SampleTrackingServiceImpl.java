package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.SampleTrackingDao;
import com.ms.dao.SendSampleDao;
import com.ms.dao.TrackingDetailDao;
import com.ms.dao.enums.SampleEnum;
import com.ms.dao.enums.TrackingDetailEnum;
import com.ms.dao.enums.TrackingEnum;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.model.SendSample;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.service.SampleTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SampleTrackingServiceImpl  extends AbsCommonService<SampleTracking> implements SampleTrackingService{

	@Autowired
	private SampleTrackingDao sampleTrackingDao;

	@Autowired
	private SendSampleDao sendSampleDao;

	@Autowired
	private TrackingDetailDao tackingDetailDao;



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
	@Transactional
	public void save(SampleTracking sampleTracking, TrackingDetail trackingDetail) {
		//需要更新寄样单状态的记录类型
		List<Integer> requie=new ArrayList<Integer>();
		requie.add(TrackingEnum.TRACKING_AGREE.getValue());
		requie.add(TrackingEnum.TRACKING_REFUSE.getValue());
		requie.add(TrackingEnum.TRACKING_SEND.getValue());
		requie.add(TrackingEnum.TRACKING_ORDER.getValue());
		requie.add(TrackingEnum.TRACKING_FINISH.getValue());

		Date now=new Date();

		Integer recordType=sampleTracking.getRecordType();
		if(requie.indexOf(sampleTracking.getRecordType())!=-1){
			SendSample sendSample=new SendSample();
			sendSample.setId(sampleTracking.getSendId());
			if(recordType.intValue()==TrackingEnum.TRACKING_AGREE.getValue()){
				sendSample.setStatus(SampleEnum.SAMPLE_AGREE.getValue());
			}
			else if(recordType.intValue()==TrackingEnum.TRACKING_REFUSE.getValue()){
				sendSample.setStatus(SampleEnum.SAMPLE_REFUSE.getValue());
			}
			else if(recordType.intValue()==TrackingEnum.TRACKING_SEND.getValue()){
				trackingDetail.setType(TrackingDetailEnum.TYPE_SEND.getValue());
				trackingDetail.setCreateTime(now);
				trackingDetail.setSendId(sampleTracking.getSendId());
				tackingDetailDao.create(trackingDetail);
				sendSample.setStatus(SampleEnum.SAMPLE_SEND.getValue());
				sampleTracking.setExtra("快递公司："+trackingDetail.getCompany()+" "+"快递单号："+trackingDetail.getTrackingNo());
			}
			else if(recordType.intValue()==TrackingEnum.TRACKING_ORDER.getValue()){
				trackingDetail.setType(TrackingDetailEnum.TYPE_ORDER.getValue());
				trackingDetail.setCreateTime(now);
				trackingDetail.setSendId(sampleTracking.getSendId());
				tackingDetailDao.create(trackingDetail);
				sendSample.setStatus(SampleEnum.SAMPLE_VISTE.getValue());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
				sampleTracking.setExtra("预约时间："+dateFormat.format(trackingDetail.getVistorTime())+" "+"来访人："+trackingDetail.getVistor()+" "+"电话："+trackingDetail.getVistorPhone());
			}else{
				sendSample.setStatus(SampleEnum.SAMPLE_FINISH.getValue());
			}
			sendSampleDao.update(sendSample);
		}

		if(sampleTracking.getExtra()==null){
			sampleTracking.setExtra("");
		}
		sampleTracking.setCreateTime(now);

		sampleTrackingDao.create(sampleTracking);


	}


	@Override
	public ICommonDao<SampleTracking> getDao() {
		return sampleTrackingDao;
	}

}
