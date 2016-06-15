package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dao.WxSupplyPicDao;
import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.service.WxSupplyPicService;
import com.jointown.zy.common.vo.WxSupplyPicVo;
/**
 * 查询供应信息中的图片
 * @author lichenxiao 2015年5月8日 下午17:42:20
 *
 */
@Service
public class WxSupplyPicServiceImpl implements WxSupplyPicService {

	@Autowired
	private WxSupplyPicDao wxSupplyPicDao;
	
	/**
	 * 查询东网与珍药材的供应信息中的图片
	 */
	@Override
	public List<WxSupplyPic> selectAllBySupplyPic(String picOne) {
		List<WxSupplyPic> wxSupplyPicList = new ArrayList<WxSupplyPic>();
		WxSupplyPic wxsupplyPic;
		//判断图片是东网的还是珍药材网的
		if(picOne.contains("?")){//如果图片路径中包函?这个字串则为东网的图片路经
			wxsupplyPic = new WxSupplyPic();
			wxsupplyPic.setPicPath("http://www.zyczyc.com/pic/shop/gongqiu/" + picOne);
			wxSupplyPicList.add(wxsupplyPic);
		}else{//否则图片为珍药材网的图片路径
			WxSupplyPic supplyPic = wxSupplyPicDao.selectSupplyIdBySupplyPic(picOne);//获取发布供应信息图片所对应的ID号
			Long supply_id = 0L;
			if(supplyPic != null){
				supply_id = supplyPic.getSupplyId();
				wxSupplyPicList = wxSupplyPicDao.selectAllBySupplyPic(supply_id);
			}
			
		}
		return wxSupplyPicList;
	}

}
