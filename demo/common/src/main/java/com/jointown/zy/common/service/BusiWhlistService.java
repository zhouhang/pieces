package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;

import com.jcraft.jsch.Session;
import com.jointown.zy.common.dto.BusiQualityInfoDto;
import com.jointown.zy.common.dto.BusiQualityPicDto;
import com.jointown.zy.common.dto.BusiWhlistDto;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BreedVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.CategorysVo;
import com.jointown.zy.common.vo.DictInfoVo;

/**
 * 仓单管理Service
 * @author wangjunhu
 * 2014-12-20
 */
public interface BusiWhlistService {

	/**
	 * 新增仓单
	 * @author wangjunhu

	 * @param busiWhlistDto, busiQualityInfoDto, busiQualityPicDto

	 */

	public void addBusiWhlist(SFTPUtil sftp,Session session,BusiWhlistDto busiWhlistDto, BusiQualityInfoDto busiQualityInfoDto, BusiQualityPicDto busiQualityPicDto);

	
	/**
	 * 更新仓单，根据仓单ID
	 * @author wangjunhu

	 * @param busiWhlistDto, busiQualityInfoDto, busiQualityPicDto

	 */

	public void alterBusiWhlist(SFTPUtil sftp,Session session,BusiWhlistDto busiWhlistDto, BusiQualityInfoDto busiQualityInfoDto, BusiQualityPicDto busiQualityPicDto);

	
	/**
	 * 删除仓单，根据仓单ID
	 * @author wangjunhu
	 * @param busiWhlistDto
	 */
	public void removeBusiWhlist(BusiWhlistDto busiWhlistDto);
	
	/**
	 * 查找仓单，查找全部
	 * @author wangjunhu
	 * @param busiWhlist
	 */
	public List<BusiWhlist> findBusiWhlists();
	
	/**
	 * 查找仓单，根据仓单ID
	 * @author wangjunhu
	 * @param wlId
	 */
	public BusiWhlistVo findBusiWhlistById(String wlId,Long userId);
	
	/**
	 * 查找仓单，分页查找
	 * @author wangjunhu
	 * @param page
	 */
	public List<BusiWhlistVo> findBusiWhlistsByCondition(Page<BusiWhlistVo> page);
	
	/**
	 * 查找仓单ID，根据仓单ID序列
	 * @return
	 */
	public BusiWhlistVo findWlIdBySeqBusiWhlist();
	
	/**
	 * 查找类目，查找全部
	 * @author wangjunhu
	 */
	public List<CategorysVo> findCategorysByTree();
	
	/**
	 * 查找类目，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public List<CategorysVo> findCategorysByBreedId(Long breedId);
	
	/**
	 * 查找品种，查找全部
	 * @author wangjunhu
	 */
	public List<BreedVo> findBreedsByTree();
	
	/**
	 * 查找品种，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public BreedVo findBreedById(Long breedId);
	
	/**
	 * 查找品种，根据类目ID查找
	 * @author wangjunhu
	 * @param categorysId
	 */

	public List<BreedVo> findBreedTreesByCategorysId(Long categorysId);
	
	/**
	 * 查找计量单位，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public DictInfoVo findDictInfoByBreedId(Long breedId);
	
	/**
	 * 根据仓单ID修改仓单可挂数量(减)
	 * @author Mr.songwei
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	int updateWLsurplusById(HashMap map);
	
	/**
	 * 根据仓单ID修改仓单可挂数量(加)
	 * @author Mr.songwei
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	int updateWLsurplus(HashMap map);
	
	/**
	 * 查找区域，根据ID查找
	 * @param firstCode
	 * @return
	 */
	public AreaVo findAreaByCode(String firstCode);
	
	/**
	 * 查找区域，根据父类ID查找
	 * @param firstCode
	 * @return
	 */
	public List<AreaVo> findAreasByFirstCode(String firstCode);
	
	/**
	 * 查询出需要的仓单
	 * @param queryMap
	 * @return
	 */
	public List<BusiWhlistVo> selectBusiWhlistMohu(Page<BusiWhlistVo> page);
	
	/**
	 * 验证仓单ID，根据仓单ID
	 * @author wangjunhu
	 * @param wlId
	 */
	public BusiWhlistVo findBusiWhlistByWlId(String wlId);
	
	/**
	 * 保存图片到资源服务器
	 * @param filePath
	 * @throws Exception
	 */
	public String saveBusiQualityPic(SFTPUtil sftp,Session session,String filePath) throws Exception;
	
	/**
	 * 上传图片到临时服务器
	 * @param busiQualityPicDto
	 * @return
	 */
	public BusiQualityPicDto uploadBusiQualityPic(SFTPUtil sftp,Session session,BusiQualityPicDto busiQualityPicDto);
	
	/**
	 * 
	 * @Description: 根据仓单ID查询仓单信息
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param wlId
	 * @return
	 */
	public BusiWhlist selectWhlistByWlId(String wlId);
	
	/**
	 * 
	 * @Description: 记录日志
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @param busiWhlist
	 * @param logType
	 * @param userId
	 * @param data
	 */
	public void addBusiWhlistLog(BusiWhlist busiWhlist,Long userId,BusinessLogEnum logType,Object...data);
	
	/**
	 * 
	 * 记仓单日志
	 * @Author: fanyuna
	 * @Date: 2015年8月27日
	 * @param busiWhlist 仓单对象
	 * @param remark 日志描述
	 * @param operatorId 操作人	
	 * @param optype  操作类型
	 * @param recordSnapshot 是否需要快照
	 * @return
	 */
	public int addBusiWhlistLog(BusiWhlist busiWhlist, String remark, Long operatorId, String optype, boolean...recordSnapshot);
	
}
