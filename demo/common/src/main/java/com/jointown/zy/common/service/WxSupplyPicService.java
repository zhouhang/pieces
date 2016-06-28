package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.vo.WxSupplyPicVo;

public interface WxSupplyPicService {

    /**
     * @Description:查询东网与珍药材的供应信息中的图片
     * @author lichenxiao
     * @Date:2015年5月8日下午17:38
     * @param map
     * @return
     */
	List<WxSupplyPic> selectAllBySupplyPic(String picOne);
   

}
