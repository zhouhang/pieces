package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.WxPriceVo;
import com.jointown.zy.common.vo.WxSupplyVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;

public interface WxSupplyDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    int deleteByPrimaryKey(Long supplyId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    int insert(WxSupply record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    int insertSelective(WxSupply record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    WxSupply selectByPrimaryKey(Long supplyId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    int updateByPrimaryKeySelective(WxSupply record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-04 14:34:04
     */
    int updateByPrimaryKey(WxSupply record);
    
    /**
     * 查询数量，根据Map对象
     * @param record
     * @return
     */
    int selectCountByCondition(Map<String,Object> map);
    
    /**
     * 查询供应信息的商家的数量，根据品种名称
     * @param breedName
     * @return
     */
    int selectUserCountByBreedName(String breedName);
    
    /**
     * 查询供应信息的数量，根据品种名称
     * @param breedName
     * @return
     */
    int selectSupplyCountByBreedName(String breedName);
    
    /**
     * 查询品种的今日价格的信息
     * @param breedName
     * @return
     */
    List<Map<String, Object>> selectTodayPriceByBreedName(String breedName);
    
    /**
     * 后台审核：查询所有供应信息
     *
     * @param page
     * @return
     *
     * @author aizhengdong
     *
     * @data 2015年3月19日
     */
    List<WxSupplyVo> selectWxSupplysByCondition(Page<WxSupplyVo> page);

	/**
	 * 供求查询：查询珍药材的挂牌信息
	 * @author lichenxiao 2015年4月13日 下午13:35
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
    List<String> selectWxSupplyPlaces();
    
    /**
     * 
     * @Description: 查询类目品种的货物所在地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
    List<AreaVo> selectWxSupplyAreas();
     
    /**
     * 
     * @Description: 查询类目的单位信息
     * @Author: wangjunhu
     * @Date: 2015年4月15日
     * @return
     */
    List<DictInfoVo> selectWxSupplyDicts();
    
    /**
     * 
     * @Description: 查询类目的品种信息，根据品种名称
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param breedName
     * @return
     */
    WxSupplyBreedVo selectWxSupplyByBreedName(String breedName);

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
	List<WxSupplyVo> selectWxSupplysByPage(Page<WxSupply> page);
	
	/**
	 * 
	 * @Description: 查询微信供应信息和图片，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @return
	 */
	WxSupplyVo selectWxSupplyAndPicById(WxSupply wxSupply);
	
	/**
	 * 
	 * @Description: 查询微信供应信息，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月19日
	 * @param wxSupply
	 * @return
	 */
	WxSupply selectWxSupplyById(WxSupply wxSupply);

}