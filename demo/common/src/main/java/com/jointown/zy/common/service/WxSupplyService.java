package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.WxSupplyDto;
import com.jointown.zy.common.dto.WxSupplyPicDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.WxPriceVo;
import com.jointown.zy.common.vo.WxSupplyVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;

public interface WxSupplyService {

	/**
	 * 供应信息审核：查询所有供应信息
	 *
	 * @param page
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月17日
	 */
	List<WxSupplyVo> findWxSupplysByCondition(Page<WxSupplyVo> page);
	
	/**
	 * 供应信息审核：更新供应信息状态
	 *
	 * @param supplyId
	 * @param status
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月18日
	 */
	int updateWxSupplyStatus(Long supplyId, Short status);
	

	/**
	 * 供求查询：查询珍药材的挂牌信息
	 * @author lichenxiao 2015年4月13日上午21:30
	 * @param map
	 * @return
	 */
	List<WxPriceVo> selectPriceByAll(Map<String, Object> map);

	/**
	 * 供求查询：查询东网的产地信息，根据东网的品种表去从后获得
	 * @author lichenxiao 2015年4月13日下午13:56
	 * @return
	 */
	List<String> selectAllChandi();
	
	/**
     * 
     * @Description: 查询类目品种的产地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
    List<String> findWxSupplyPlaces();
	
	/**
     * 
     * @Description: 查询类目品种的货物所在地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
    List<AreaVo> findWxSupplyAreas();
    
    /**
     * 
     * @Description: 查询类目的单位信息
     * @Author: wangjunhu
     * @Date: 2015年4月15日
     * @return
     */
    List<DictInfoVo> findWxSupplyDicts();
    
    /**
     * 
     * @Description: 查询类目的品种信息，根据品种名称
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param breedName
     * @return
     */
    WxSupplyBreedVo findWxSupplyByBreedName(String breedName);
    
    /**
     * 
     * @Description: 新增微信供应信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param wxSupplyDto
     * @return
     */
    void addWxSupply(WxSupplyDto wxSupplyDto,WxSupplyPicDto wxSupplyPicDto) throws Exception;
    
    /**
     * 
     * @Description: 修改微信供应信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param wxSupplyDto
     * @return
     */
    void updateWxSupply(WxSupplyDto wxSupplyDto,WxSupplyPicDto wxSupplyPicDto) throws Exception;

    /**
     * @Description:查询东网与珍药材的供应信息（翻页，每次显示5条）
     * @author lichenxiao
     * @Date:2015年4月20日下午16：10
     * @param map
     * @return
     */
	List<WxSupplyVo> selectInfoBySupply(Map<String, Object> map);
   
	/**
	 * 
	 * @Description: 查询微信供应信息，分页条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param page
	 * @return
	 */
	List<WxSupplyVo> findWxSupplysByPage(Page<WxSupply> page);
	
	/**
	 * 
	 * @Description: 查询微信供应信息和图片，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @return
	 */
	WxSupplyVo findWxSupplyAndPicById(WxSupply wxSupply);
	
	/**
	 * 
	 * @Description: 查询微信供应信息，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @return
	 */
	WxSupply findWxSupplyById(WxSupply wxSupply);
	
	/**
	 * 
	 * @Description: 删除微信供应信息图片，ID删除
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param supplyPicIds
	 * @return
	 */
	void deleteWxSupplyPicById(String supplyPicIds) throws Exception;
	
	/**
	 * 
	 * @Description: 更新微信供应信息，ID条件更新
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @throws Exception
	 */
	void updateWxSupplyById(WxSupply wxSupply) throws Exception;
	
}
