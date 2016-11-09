package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.*;
import com.ms.dao.enums.*;
import com.ms.dao.model.*;
import com.ms.dao.vo.*;
import com.ms.service.CommodityService;
import com.ms.service.PickCommodityService;
import com.ms.service.PickService;
import com.ms.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PickServiceImpl  extends AbsCommonService<Pick> implements PickService{

	@Autowired
	private PickDao pickDao;


	@Autowired
	private PickCommodityService pickCommodityService;


	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailDao userDetailDao;


	@Autowired
	private PickTrackingDao pickTrackingDao;

	@Autowired
	private CommodityService commodityService;

	private CodeDao codeDao;




	@Override
	public PageInfo<PickVo> findByParams(PickVo pickVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<PickVo>  list = pickDao.findByParams(pickVo);
		list.forEach(p->{
			List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(p.getId());
			float total=0;

			for(PickCommodityVo vo :pickCommodityVos){
				total+=vo.getTotal();
			}
			p.setTotal(total);

			p.setPickCommodityVoList(pickCommodityVos);
		});
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public PickVo findVoById(Integer id) {
		PickVo pickVo=pickDao.findVoById(id);
		List<PickCommodityVo> pickCommodityVos=pickCommodityService.findByPickId(id);
		float total=0;

		for(PickCommodityVo vo :pickCommodityVos){
			total+=vo.getTotal();
		}
		pickVo.setTotal(total);

		pickVo.setPickCommodityVoList(pickCommodityVos);

		return pickVo;
	}

	@Override
	@Transactional
	public void save(PickVo pickVo) {
		UserVo userVo=userDao.findByPhone(pickVo.getPhone());

		Integer nowLogin=pickVo.getUserId();//现在登录的userid
		//如果用户注册
		Date now=new Date();

		int useId;
		if (userVo==null){
			User user=new User();
			user.setPhone(pickVo.getPhone());
			user.setType(UserEnum.auto.getType());
			user.setSalt("");
			user.setPassword("");
			//user.setOpenid("");
			user.setUpdateTime(now);
			user.setCreateTime(now);
			userDao.create(user);

			useId=user.getId();
		}
		else{
			useId=userVo.getId();
		}
		UserDetail userDetail=userDetailDao.findByUserId(useId);
		if (userDetail==null){
			userDetail=new UserDetail();
			userDetail.setPhone(pickVo.getPhone());
			userDetail.setNickname(pickVo.getNickname());
			userDetail.setArea("");
			userDetail.setUserId(useId);
			userDetail.setName("");
			userDetail.setRemark("");
			userDetail.setType(0);
			userDetail.setUpdateTime(now);
			userDetail.setCreateTime(now);
			userDetailDao.create(userDetail);
		}
		else{
			userDetail.setPhone(pickVo.getPhone());
			userDetail.setNickname(pickVo.getNickname());
			userDetail.setUpdateTime(now);
			userDetailDao.update(userDetail);
		}




		Pick pick=new Pick();
		if(nowLogin==null){
			pick.setUserId(useId);
		}
		else{
			pick.setUserId(nowLogin);
		}
		pick.setNickname(pickVo.getNickname());
		pick.setPhone(pickVo.getPhone());
		pick.setStatus(PickEnum.PICK_NOTHANDLE.getValue());
		pick.setUpdateTime(now);
		pick.setCreateTime(now);
		pick.setCode("");
		pickDao.create(pick);
		pick.setCode(SeqNoUtil.get("", pick.getId(), 6));
		pickDao.update(pick);

		pickVo.getPickCommodityVoList().forEach(c->{
			c.setPickId(pick.getId());
		});
		pickCommodityService.saveList(pickVo.getPickCommodityVoList());



		PickTracking pickTracking=new PickTracking();
		pickTracking.setName(pickVo.getNickname());
		pickTracking.setOpType(TrackingTypeEnum.TYPE_USER.getValue());
		if(nowLogin==null){
			pickTracking.setOperator(useId);
		}
		else{
			pickTracking.setOperator(nowLogin);
		}
		pickTracking.setExtra("");
		pickTracking.setCreateTime(now);
		pickTracking.setUpdateTime(now);
		pickTracking.setPickId(pick.getId());
		pickTracking.setRecordType(PickTrackingTypeEnum.PICK_APPLY.getValue());
		pickTrackingDao.create(pickTracking);



	}


	@Override
	public ICommonDao<Pick> getDao() {
		return pickDao;
	}



}
