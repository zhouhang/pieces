package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxSupplyDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.WxPriceVo;
import com.jointown.zy.common.vo.WxSupplyVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;



/**
 * 供应信息DaoImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月4日
 */
@Repository
public class WxSupplyDaoImpl extends BaseDaoImpl implements WxSupplyDao {

	@Override
	public int deleteByPrimaryKey(Long supplyId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.WxSupplyDao.deleteByPrimaryKey",supplyId);
	}

	@Override
	public int insert(WxSupply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxSupplyDao.insert", record);
	}

	@Override
	public int insertSelective(WxSupply record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.WxSupplyDao.insertSelective", record);
	}

	@Override
	public WxSupply selectByPrimaryKey(Long supplyId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectByPrimaryKey", supplyId);
	}

	@Override
	public int updateByPrimaryKeySelective(WxSupply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxSupplyDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(WxSupply record) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.WxSupplyDao.updateByPrimaryKey", record);
	}

	@Override
	public int selectCountByCondition(Map<String, Object> map) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectCountByCondition", map);
	}
	
	@Override
	public int selectUserCountByBreedName(String breedName) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectUserCountByBreedName",breedName);
	}
	
	@Override
	public int selectSupplyCountByBreedName(String breedName) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectSupplyCountByBreedName",breedName);
	}

	@Override
	public List<Map<String, Object>> selectTodayPriceByBreedName(String breedName) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectTodayPriceByBreedName",breedName);
	}

	/**
	 * @see com.jointown.zy.common.dao.WxSupplyDao#selectWxSupplysByCondition(com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<WxSupplyVo> selectWxSupplysByCondition(Page<WxSupplyVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplysByCondition", page);
	}

	/**
	 * 供求查询：查询珍药材的挂牌信息
	 * @author lichenxiao 2015年4月13日 下午13:35
	 */
	@Override
	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectPriceByAll",map);
	}

    /**
     * @Description:查询东网与珍药材的供应信息（翻页，每次显示5条）
     * @author lichenxiao
     * @Date:2015年4月20日下午16：10
     * @param map
     * @return
     */
	@Override
	public List<WxSupplyVo> selectInfoBySupply(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectInfoBySupply", map);
	}
	
	/**
	 * 供求查询：查询东网的产地信息，根据东网的品种表去从后获得
	 * @author lichenxiao 2015年4月13日下午13:56
	 * @return
	 */
	@Override
	public List<String> selectAllChandi() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectAllChandi");
	}

	/**
     * 
     * @Description: 查询类目品种的产地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
	@Override
	public List<String> selectWxSupplyPlaces(){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyPlaces");
	};
    
	/**
     * 
     * @Description: 查询类目品种的货物所在地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return 
     */
	@Override
	public List<AreaVo> selectWxSupplyAreas() {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyAreas");
	}
	
	/**
     * 
     * @Description: 查询类目的单位信息
     * @Author: wangjunhu
     * @Date: 2015年4月15日
     * @return
     */
	@Override
	public List<DictInfoVo> selectWxSupplyDicts(){
    	return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyDicts");
    };

	/**
     * 
     * @Description: 查询类目的品种信息，根据品种名称
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param breedName
     * @return
     */
	@Override
	public WxSupplyBreedVo selectWxSupplyByBreedName(String breedName) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyByBreedName",breedName);
	}

	/**
	 * 
	 * @Description: 查询微信供应信息，分页条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param page
	 * @return
	 */
	@Override
	public List<WxSupplyVo> selectWxSupplysByPage(Page<WxSupply> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplysByPage",page);
	}

	/**
	 * 
	 * @Description: 查询微信供应信息和图片，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @return
	 */
	@Override
	public WxSupplyVo selectWxSupplyAndPicById(WxSupply wxSupply) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyAndPicById",wxSupply);
	}

	/**
	 * 
	 * @Description: 查询微信供应信息，条件查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月19日
	 * @param wxSupply
	 * @return
	 */
	@Override
	public WxSupply selectWxSupplyById(WxSupply wxSupply) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.WxSupplyDao.selectWxSupplyById",wxSupply);
	}

}
