package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dao.WxOpinionDao;
import com.jointown.zy.common.dto.WxOpinionDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxOpinion;
import com.jointown.zy.common.service.IMemberFindPwdService;
import com.jointown.zy.common.service.WxOpinionService;
import com.jointown.zy.common.util.MobileCodeUtil;
import com.jointown.zy.common.vo.WxOpinionVo;

/**
 * 意见反馈
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
@Service
public class WxOpinionServiceImpl implements WxOpinionService {
	
	@Autowired
	private IMemberFindPwdService memberFindPwdService;
	
	@Autowired
	private WxOpinionDao wxOpinionDao;
	
	@Override
	public int addWxOpinion(WxOpinionDto wxOpinionDto) {
		WxOpinion wxOpinion = new WxOpinion();
		wxOpinion.setOpName(wxOpinionDto.getUserName());
		wxOpinion.setOpPhone(wxOpinionDto.getMobile());
		wxOpinion.setOpPicUrl(wxOpinionDto.getPicUrl());
		wxOpinion.setOpMemo(wxOpinionDto.getMemo());
		wxOpinion.setStatus((short)0);
		
		Date now = new Date();
		wxOpinion.setCreateTime(now);
		wxOpinion.setUpdateTime(now);
		
		return wxOpinionDao.insert(wxOpinion);
	}

	@Override
	public int updateWxOpinion(WxOpinion wxOpinion) {
		return wxOpinionDao.updateByPrimaryKeySelective(wxOpinion);
	}

	@Override
	public int deleteWxOpinion(Long opId) {
		return wxOpinionDao.deleteByPrimaryKey(opId);
	}
	
	@Override
	public WxOpinion findWxOpinionById(Long opId) {
		return wxOpinionDao.selectByPrimaryKey(opId);
	}

	@Override
	public List<WxOpinionVo> findWxOpinionByCondition(Page<WxOpinionVo> page) {
		List<WxOpinion> wxOpinions = wxOpinionDao.selectWxOpinionByCondition(page);
		List<WxOpinionVo> wxOpinionVos = new ArrayList<WxOpinionVo>();
		for(WxOpinion wxOpinion : wxOpinions){
			WxOpinionVo wxOpinionVo = new WxOpinionVo();
			wxOpinionVo.setOpId(wxOpinion.getOpId());
			wxOpinionVo.setOpName(wxOpinion.getOpName());
			wxOpinionVo.setOpPhone(wxOpinion.getOpPhone());
			wxOpinionVo.setOpPicUrl(wxOpinion.getOpPicUrl());
			wxOpinionVo.setStatus(wxOpinion.getStatus());
			wxOpinionVo.setCreateTime(wxOpinion.getCreateTime());
			
			String picUrls = wxOpinionVo.getOpPicUrl();
			if(!StringUtils.isBlank(picUrls)){
				String[] picUrlArray = picUrls.split(",");
				for (int i = 0; i < picUrlArray.length; i++) {
					picUrlArray[i] = MessageConstant.RESOURCE_IMG_UPLOAD_WX.getValue() + "/" + picUrlArray[i];
				}
				
				wxOpinionVo.setPicUrls(picUrlArray);
			}
			
			wxOpinionVos.add(wxOpinionVo);
		}
		
		return wxOpinionVos;
	}

	@Override
	public String getMobileCode(HttpServletRequest request, String mobile) {
		Map<String, Object> mobileCode = MobileCodeUtil.getMobileCode(mobile);
		request.getSession().setAttribute(MobileCodeUtil.MOBILE_CODE,
				mobileCode);
		return memberFindPwdService.getMobileCode(mobile, (String) mobileCode.get("mobileCode"));
	}

}
