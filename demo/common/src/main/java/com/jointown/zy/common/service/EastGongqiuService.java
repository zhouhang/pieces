package com.jointown.zy.common.service;

import java.math.BigDecimal;

import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.vo.EastGongqiuVo;

/**
 * 
 * @ClassName: EastGongqiuDaoService
 * @Description: 东方中药材网供求信息Service
 * @Author: wangjunhu
 * @Date: 2015年6月12日
 * @Version: 1.0
 */
public interface EastGongqiuService {

	/**
	 * 
	 * @Description: 查询供求信息，根据ID
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param gqid
	 */
	EastGongqiu findEastGongqiuById(BigDecimal gqid) throws Exception;
	
	/**
	 * 
	 * @Description: 更新供求信息
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param eastGongqiu
	 * @throws Exception
	 */
	void updateEastGongqiu(EastGongqiu eastGongqiu) throws Exception;
	
	
	/**
	 * 后台求购信息管理：查询单条求购信息
	 *
	 * @param gqid
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月19日
	 */
	EastGongqiuVo findEastGongqiuById(Long gqid);
}
