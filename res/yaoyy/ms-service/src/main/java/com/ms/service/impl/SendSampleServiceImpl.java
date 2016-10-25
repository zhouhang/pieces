package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.*;
import com.ms.dao.enums.SampleEnum;
import com.ms.dao.enums.TrackingEnum;
import com.ms.dao.enums.TrackingTypeEnum;
import com.ms.dao.enums.UserEnum;
import com.ms.dao.model.*;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.CommodityService;
import com.ms.service.SendSampleService;
import com.ms.tools.utils.SeqNoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SendSampleServiceImpl  extends AbsCommonService<SendSample> implements SendSampleService{

	@Autowired
	private SendSampleDao sendSampleDao;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private SampleTrackingDao sampleTrackingDao;





	@Override
	public PageInfo<SendSampleVo> findByParams(SendSampleVo sendSampleVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<SendSampleVo>  list = sendSampleDao.findByParams(sendSampleVo);
		//意向商品转化为显示字符串
		list.forEach(s->{
			List<Commodity> commodityList = commodityService.findByIds(s.getIntention());
			s.setCommodityList(commodityList);
		});
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public SendSampleVo findDetailById(Integer id) {
		SendSampleVo sendSampleVo=sendSampleDao.findDetailById(id);
		List<Commodity> commodityList = commodityService.findByIds(sendSampleVo.getIntention());
		sendSampleVo.setCommodityList(commodityList);
		return sendSampleVo ;
	}

	@Override
	public List<SendSampleVo> findByCommodityId(int userId,List<Integer> ids) {
		String idstr=StringUtils.join(ids, "|");
		List<SendSampleVo> sendSampleVos= sendSampleDao.findByCommodityId(userId,idstr);
		for(SendSampleVo s:sendSampleVos)
		{
			List<Commodity> commodityList =commodityService.findByIds(s.getIntention());
			s.setCommodityList(commodityList);

		}
		return sendSampleVos;
	}

	@Override
	public List<SendSampleVo> findByUserId(Integer userId) {
		SendSampleVo sendSampleVo=new SendSampleVo();
		sendSampleVo.setUserId(userId);
		List<SendSampleVo>  list = sendSampleDao.findByParams(sendSampleVo);
		for(SendSampleVo s:list)
		{
			List<Commodity> commodityList =commodityService.findByIds(s.getIntention());
			s.setCommodityList(commodityList);

		}
		return list;
	}

	@Override
	@Transactional
	public void save(SendSampleVo sendSampleVo) {

		UserVo userVo=userDao.findByPhone(sendSampleVo.getPhone());
		//如果用户注册
		Date now=new Date();
		int useId;
		if (userVo==null){
			User user=new User();
			user.setPhone(sendSampleVo.getPhone());
			user.setType(UserEnum.auto.getType());
			user.setSalt("");
			user.setPassword("");
			user.setOpenid("");
			user.setUpdateTime(now);
			user.setCreateTime(now);
			userDao.create(user);

			useId=user.getId();
		}
		else{
			useId=userVo.getId();
		}
		UserDetail userDetail=userDetailDao.findById(useId);
		if (userDetail==null){
			userDetail=new UserDetail();
			userDetail.setPhone(sendSampleVo.getPhone());
			userDetail.setNickname(sendSampleVo.getNickname());
			userDetail.setArea(sendSampleVo.getArea());
			userDetail.setUserId(useId);
			userDetail.setName("");
			userDetail.setRemark("");
			userDetail.setType(0);
			userDetail.setUpdateTime(now);
			userDetail.setCreateTime(now);
			userDetailDao.create(userDetail);
		}
		else{
			userDetail.setPhone(sendSampleVo.getPhone());
			userDetail.setNickname(sendSampleVo.getNickname());
			userDetail.setArea(sendSampleVo.getArea());
			userDetail.setUpdateTime(now);
			userDetailDao.update(userDetail);
		}





		SendSample sendSample=new SendSample();
		sendSample.setUserId(useId);
		sendSample.setStatus(SampleEnum.SAMPLE_NOTHANDLE.getValue());
		sendSample.setIntention(sendSampleVo.getIntention());
		sendSample.setUpdateTime(now);
		sendSample.setCreateTime(now);
		sendSample.setCode("");
		sendSampleDao.create(sendSample);
		sendSample.setCode(SeqNoUtil.get("", sendSample.getId(), 6));
		sendSampleDao.update(sendSample);


		SampleTracking sampleTracking=new SampleTracking();
		sampleTracking.setName(sendSampleVo.getNickname());
		sampleTracking.setType(TrackingTypeEnum.TYPE_USER.getValue());
		sampleTracking.setOperator(useId);
		sampleTracking.setExtra("");
		sampleTracking.setCreateTime(now);
		sampleTracking.setSendId(sendSample.getId());
		sampleTracking.setRecordType(TrackingEnum.TRACKING_APPLY.getValue());
		sampleTrackingDao.create(sampleTracking);


	}


	@Override
	public ICommonDao<SendSample> getDao() {
		return sendSampleDao;
	}

}
