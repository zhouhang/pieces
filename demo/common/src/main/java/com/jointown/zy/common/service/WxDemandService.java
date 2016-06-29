package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.WxDemandDto;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.vo.WxDemandVo;

/**
 * 求购信息ServiceImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月12日
 */
public interface WxDemandService {
    /**
     * @Description:查询东网与珍药材的求购信息（翻页，每次显示5条）
     * @author lichenxiao
     * @Date:2015年4月28日下午16：10
     * @param map
     * @return
     */
	List<WxDemandVo> selectInfoByDemand(Map<String, Object> map);
	
	
	/**
	 * 
	 * @Description: 查询微信求购信息，分页条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月15日
	 * @param page
	 * @return
	 */
	List<WxDemandVo> findWxDemandsByPage(Page<WxDemand> page);
	
	/**
	 * 
	 * @Description: 查询微信求购信息，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月15日
	 * @param wxDemand
	 * @return
	 */
	WxDemandVo findWxDemandById(WxDemand wxDemand);
	
	/**
     * 
     * @Description: 新增微信求购信息
     * @Author: wangjunhu
     * @Date: 2015年4月15日
     * @param wxDemandDto
     * @return
     */
    void addWxDemand(WxDemandDto wxDemandDto) throws Exception;
    
	/**
	 * 
	 * @Description: 更新微信求购信息，ID条件更新
	 * @Author: wangjunhu
	 * @Date: 2015年5月15日
	 * @param wxDemand
	 * @throws Exception
	 */
	void updateWxDemandById(WxDemand wxDemand) throws Exception;
	
	/**
	 * 后台求购信息管理：查询所有求购信息
	 *
	 * @param page
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月27日
	 */
	List<WxDemandVo> findWxDemandsByCondition(Page<WxDemandVo> page);
	
	/**
	 * 后台求购信息管理：查询单条求购信息
	 *
	 * @param wxDemandDto 条件为：demandId，applyResource
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月19日
	 */
	WxDemandVo findDemandByCondition(WxDemandDto wxDemandDto);
	
	/**
	 * 后台求购信息管理：查询微信单条求购信息
	 *
	 * @param wxDemandDto
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月19日
	 */
	WxDemandVo findWxDemandByIdFromBack(WxDemandDto wxDemandDto);
	
	/**
	 * 后台求购信息管理：更新求购信息状态
	 *
	 * @param wxDemandDto
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月15日
	 */
	int updateDemandStatus(WxDemandDto wxDemandDto);
	
	/**
	 * 后台求购信息管理：添加求购信息
	 *
	 * @param wxDemandDto
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月15日
	 */
	int addBusiPurchaseApply(WxDemandDto wxDemandDto) throws Exception;
	
	/**
	 * 后台求购信息管理：根据品种名称 别名 查询
	 *
	 * @param param
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月16日
	 */
	List<Breed> getBreedNames(String breedName);
	
	/**
	 * 后台求购信息管理：检查品种名称
	 *
	 * @param breedName
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月23日
	 */
	Breed checkBreedName(String breedName);
}
