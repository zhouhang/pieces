package com.jointown.zy.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.IMemberHomeService;
import com.jointown.zy.common.vo.MemberHomeVo;

@Service
public class MemberHomeServiceImpl implements IMemberHomeService {

	@Autowired
	public UcUserDao ucUserDao;
	
	@Override
	public MemberHomeVo getMemberHomeInfo(String userName) {
		UcUser ucUser = ucUserDao.findUcUserByUserName(userName);
		MemberHomeVo memberHomeVo = new MemberHomeVo();
		memberHomeVo.setUserName(userName);
		memberHomeVo.setRealName(ucUser.getCompanyName());
		memberHomeVo.setAccessIp(ucUser.getAccessIp());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != ucUser.getLastAccessTime()) {
			memberHomeVo.setLastAccessDate(sdf.format(ucUser.getLastAccessTime()));
		}
		memberHomeVo.setCertifyStatus(ucUser.getCertifyStatus());
		memberHomeVo.setEmail(ucUser.getEmail());
		return memberHomeVo;
	}
	
	//动态获取会员中心首页的数字（如销售已成交笔数，待付保证金笔数，待付货款笔数;采购已成交笔数，待付保证金笔数，待付货款笔数）
	public List<Integer> getNums(Long userId){
		HashMap map = new HashMap();
		map.put("userId", userId);
		map.put("COMPLETED_ORDER", BusiOrderStateEnum.COMPLETED_ORDER.getCode());
		map.put("PlACED_ORDER", BusiOrderStateEnum.PlACED_ORDER.getCode());
		map.put("READY_WARE", BusiOrderStateEnum.READY_WARE.getCode());
		List<Integer> nums = ucUserDao.getNums(map);
		return nums;
	}
}
