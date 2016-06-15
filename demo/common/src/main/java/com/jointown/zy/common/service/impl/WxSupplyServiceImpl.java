package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.WxDemandDao;
import com.jointown.zy.common.dao.WxSupplyDao;
import com.jointown.zy.common.dao.WxSupplyPicDao;
import com.jointown.zy.common.dto.WxSupplyDto;
import com.jointown.zy.common.dto.WxSupplyPicDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxSupply;
import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.BusiOrderVo;
import com.jointown.zy.common.vo.DictInfoVo;
import com.jointown.zy.common.vo.WxDemandVo;
import com.jointown.zy.common.vo.WxSupplyVo;
import com.jointown.zy.common.vo.WxSupplyPicVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;
import com.jointown.zy.common.vo.WxPriceVo;


/**
 * 供应信息ServiceImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月12日
 */
@Service
public class WxSupplyServiceImpl implements WxSupplyService {
	@Autowired
	private WxDemandDao wxDemandDao;

	@Autowired
	private WxSupplyDao wxSupplyDao;

	@Autowired
	private WxSupplyPicDao wxSupplyPicDao;
	
	@Autowired
	private BusiOrderDao busiOrderDao;

	/**
	 * @see com.jointown.zy.common.service.WxSupplyService#findWxSupplysByCondition

(com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<WxSupplyVo> findWxSupplysByCondition(Page<WxSupplyVo> page) {
		List<WxSupplyVo> wxSupplyList = wxSupplyDao.selectWxSupplysByCondition(page);

		// 将图片的大小图路径与序号对应
		for (WxSupplyVo wxSupply : wxSupplyList) {
			List<WxSupplyPic> picList = wxSupply.getPicList();
			if (picList != null && picList.size() > 1) {
				List<WxSupplyPicVo> picVoList = new ArrayList<WxSupplyPicVo>();
				wxSupply.setPicVoList(picVoList);
				
				// 根据图片类型对应图片的序号及大小图
				WxSupplyPicVo wxSupplyPicVo = null;
				for (WxSupplyPic wxSupplyPic : picList) {
					if (wxSupplyPic.getType() % 2 == 0) {
						wxSupplyPicVo = new WxSupplyPicVo();
						picVoList.add(wxSupplyPicVo);
						wxSupplyPicVo.setThumbnailPath(wxSupplyPic.getPicPath());
					}else if(wxSupplyPicVo != null){
						wxSupplyPicVo.setOriginalPath(wxSupplyPic.getPicPath());
					}
				}
				
//				for (int i = 0; i < picList.size() / 2; picVoList.add(i++, new 
//WxSupplyPicVo()));
//				
//				for (WxSupplyPic wxSupplyPic : picList) {
//					int index = wxSupplyPic.getType() / 2;
//					if (index < picList.size()) {
//						WxSupplyPicVo wxSupplyPicVo = picVoList.get(index);
//
//						// 小图
//						if (wxSupplyPic.getType() % 2 == 0) {
//							wxSupplyPicVo.setThumbnailPath
//
//(wxSupplyPic.getPicPath());
//						}
//						// 大图
//						else {
//							wxSupplyPicVo.setOriginalPath
//
//(wxSupplyPic.getPicPath());
//						}
//					}
//				}
				
			}
			
			/* 增加：空值填充文字处理 @author aizhengdong @date 2015年8月7日  */
		    
			// 规格
			String breedStandardLevel = StringUtils.isBlank(wxSupply.getBreedStandardLevel()) ? "不限" : wxSupply.getBreedStandardLevel();
			
			// 价格
			String priceUnitPrice;
			if(wxSupply.getPrice() == null){
				priceUnitPrice = "面议";
			}else{
				priceUnitPrice = wxSupply.getPrice() + "元/" + wxSupply.getPriceUnit();
			}
			
			// 库存
			String qtyUnitQty;
			if(wxSupply.getQty() == null){
				qtyUnitQty = "面议";
			}else{
				qtyUnitQty = wxSupply.getQty() + wxSupply.getPriceUnit();
			}
			
			// 产地
			String breedPlace = StringUtils.isBlank(wxSupply.getBreedPlace()) ? "不限" : wxSupply.getBreedPlace();
			
			wxSupply.setBreedStandardLevel(breedStandardLevel);
			wxSupply.setPriceUnitPrice(priceUnitPrice);
			wxSupply.setQtyUnitQty(qtyUnitQty);
			wxSupply.setBreedPlace(breedPlace);
		}

		return wxSupplyList;
	}

	/**
	 * @see com.jointown.zy.common.service.WxSupplyService#updateWxSupplyStatus(java.lang.Long)
	 */
	@Override
	public int updateWxSupplyStatus(Long supplyId, Short status) {
		Date date = new Date();
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute

(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();

		WxSupply wxSupply = new WxSupply();
		wxSupply.setSupplyId(supplyId);
		wxSupply.setStatus(status);
		wxSupply.setApproveTime(date);
		wxSupply.setApprover(Long.parseLong(bossUserId.toString()));

		return wxSupplyDao.updateByPrimaryKeySelective(wxSupply);
	}

	/**
	 * 供求查询：查询珍药材的挂牌信息
	 * @author lichenxiao 2015年4月13日 下午13:35
	 */
	@Override
	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map) {
		return this.wxSupplyDao.selectPriceByAll(map);
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
		return this.wxSupplyDao.selectInfoBySupply(map);
	}
	
	
	/**
	 * 供求查询：查询东网的产地信息，根据东网的品种表去从后获得
	 * @author lichenxiao 2015年4月13日下午13:56
	 * @return
	 */
	@Override
	public List<String> selectAllChandi() {
		return this.wxSupplyDao.selectAllChandi();
	}

	/**
     * 
     * @Description: 查询类目品种的产地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
	@Override
	public List<String> findWxSupplyPlaces(){
		return wxSupplyDao.selectWxSupplyPlaces();
	};
	
	/**
     * 
     * @Description: 查询类目品种的货物所在地信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @return
     */
	@Override
	public List<AreaVo> findWxSupplyAreas() {
		return wxSupplyDao.selectWxSupplyAreas();
	}
	
	/**
     * 
     * @Description: 查询类目的单位信息
     * @Author: wangjunhu
     * @Date: 2015年4月15日
     * @return
     */
	@Override
	public List<DictInfoVo> findWxSupplyDicts(){
		return wxSupplyDao.selectWxSupplyDicts();
    }
    
    /**
     * 
     * @Description: 查询类目的品种信息，根据品种名称
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param breedName
     * @return
     */
	@Override
	public WxSupplyBreedVo findWxSupplyByBreedName(String breedName) {
		return wxSupplyDao.selectWxSupplyByBreedName(breedName);
	}

	/**
     * 
     * @Description: 新增微信供应信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param wxSupplyDto
     * @return
     */
	@Override
	public void addWxSupply(WxSupplyDto wxSupplyDto,WxSupplyPicDto wxSupplyPicDto) throws Exception {
		//保存微信供应信息
		WxSupply wxSupply = wxSupplyDto.getWxSupply();
		wxSupply.setCreateTime(new Date());
		wxSupply.setUpdateTime(new Date());
		int wxSupplyOk = wxSupplyDao.insertSelective(wxSupply);
		if(wxSupplyOk!=1){
			throw new Exception("新增微信供应信息失败！");
		}
		Long supplyId = wxSupply.getSupplyId();
		//保存微信供应信息图片
		List<WxSupplyPic> wxSupplyPics = wxSupplyPicDto.getWxSupplyPics();
		for (WxSupplyPic wxSupplyPic : wxSupplyPics) {
			wxSupplyPic.setSupplyId(supplyId);
			wxSupplyPic.setStatus((short)0);
			wxSupplyPic.setCreateTime(new Date());
			wxSupplyPic.setUpdateTime(new Date());
			int wxSupplyPicOk = wxSupplyPicDao.insertSelective(wxSupplyPic);
			if(wxSupplyPicOk!=1){
				throw new Exception("新增微信供应信息图片失败！");
			}
		}
	}

    /**
     * 
     * @Description: 修改微信供应信息
     * @Author: wangjunhu
     * @Date: 2015年4月12日
     * @param wxSupplyDto
     * @return
     */
	@Override
	public void updateWxSupply(WxSupplyDto wxSupplyDto,WxSupplyPicDto wxSupplyPicDto) throws Exception {
		//修改微信供应信息
		WxSupply wxSupply = wxSupplyDto.getWxSupply();
		wxSupply.setStatus((short)0);
		wxSupply.setUpdateTime(new Date());
		int wxSupplyOk = wxSupplyDao.updateByPrimaryKeySelective(wxSupply);
		if(wxSupplyOk!=1){
			throw new Exception("修改微信供应信息失败！");
		}
		Long supplyId = wxSupply.getSupplyId();
		//保存微信供应信息图片
		List<WxSupplyPic> wxSupplyPics = wxSupplyPicDto.getWxSupplyPics();
		for (WxSupplyPic wxSupplyPic : wxSupplyPics) {
			wxSupplyPic.setSupplyId(supplyId);
			wxSupplyPic.setStatus((short)0);
			wxSupplyPic.setCreateTime(new Date());
			wxSupplyPic.setUpdateTime(new Date());
			int wxSupplyPicOk = wxSupplyPicDao.insertSelective(wxSupplyPic);
			if(wxSupplyPicOk!=1){
				throw new Exception("修改微信供应信息图片失败！");
			}
		}
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
	public List<WxSupplyVo> findWxSupplysByPage(Page<WxSupply> page) {
		List<WxSupplyVo> wxSupplyList = wxSupplyDao.selectWxSupplysByPage(page);
		// 将图片的大小图路径与序号对应
		for (WxSupplyVo wxSupply : wxSupplyList) {
			List<WxSupplyPic> picList = wxSupply.getPicList();
			if (picList != null && picList.size() > 1) {
				List<WxSupplyPicVo> picVoList = new ArrayList<WxSupplyPicVo>();
				wxSupply.setPicVoList(picVoList);
				// 根据图片类型对应图片的序号及大小图
				WxSupplyPicVo wxSupplyPicVo = null;
				for (WxSupplyPic wxSupplyPic : picList) {
					if (wxSupplyPic.getType() % 2 == 0) {
						wxSupplyPicVo = new WxSupplyPicVo();
						picVoList.add(wxSupplyPicVo);
						wxSupplyPicVo.setThumbnailPicId(wxSupplyPic.getSupplyPicId());
						wxSupplyPicVo.setThumbnailPath(wxSupplyPic.getPicPath());
					}else if(wxSupplyPicVo != null){
						wxSupplyPicVo.setOriginalPicId(wxSupplyPic.getSupplyPicId());
						wxSupplyPicVo.setOriginalPath(wxSupplyPic.getPicPath());
					}
				}
			}
		}
		return wxSupplyList;
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
	public WxSupplyVo findWxSupplyAndPicById(WxSupply wxSupply){
		WxSupplyVo wxSupplyVo = wxSupplyDao.selectWxSupplyAndPicById(wxSupply);
		// 将图片的大小图路径与序号对应
		List<WxSupplyPic> picList = wxSupplyVo.getPicList();
		if (picList != null && picList.size() > 1) {
			List<WxSupplyPicVo> picVoList = new ArrayList<WxSupplyPicVo>();
			wxSupplyVo.setPicVoList(picVoList);
			// 根据图片类型对应图片的序号及大小图
			WxSupplyPicVo wxSupplyPicVo = null;
			for (WxSupplyPic wxSupplyPic : picList) {
				if (wxSupplyPic.getType() % 2 == 0) {
					wxSupplyPicVo = new WxSupplyPicVo();
					picVoList.add(wxSupplyPicVo);
					wxSupplyPicVo.setThumbnailPicId(wxSupplyPic.getSupplyPicId());
					wxSupplyPicVo.setThumbnailPath(wxSupplyPic.getPicPath());
				}else if(wxSupplyPicVo != null){
					wxSupplyPicVo.setOriginalPicId(wxSupplyPic.getSupplyPicId());
					wxSupplyPicVo.setOriginalPath(wxSupplyPic.getPicPath());
				}
			}
		}
		return wxSupplyVo;
	};
	
	/**
	 * 
	 * @Description: 查询微信供应信息，ID查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @return
	 */
	@Override
	public WxSupply findWxSupplyById(WxSupply wxSupply){
		return wxSupplyDao.selectWxSupplyById(wxSupply);
	};
	
	/**
	 * 
	 * @Description: 删除微信供应信息图片，ID删除
	 * @Author: wangjunhu
	 * @Date: 2015年5月12日
	 * @param supplyPicId
	 * @return
	 */
	@Override
	public void deleteWxSupplyPicById(String supplyPicIds) throws Exception {
		String[] supplyPicIdz = supplyPicIds.split("-");
		for (String string : supplyPicIdz) {
			Long supplyPicId = Long.parseLong(string);
			int wxSupplyPicOk = wxSupplyPicDao.deleteByPrimaryKey(supplyPicId);
			if(wxSupplyPicOk!=1){
				throw new Exception("删除微信供应信息图片失败！");
			}
		}
	}

	/**
	 * 
	 * @Description: 更新微信供应信息，ID条件更新
	 * @Author: wangjunhu
	 * @Date: 2015年5月13日
	 * @param wxSupply
	 * @throws Exception
	 */
	@Override
	public void updateWxSupplyById(WxSupply wxSupply) throws Exception {
		wxSupply.setUpdateTime(new Date());
		int wxSupplyOk = wxSupplyDao.updateByPrimaryKeySelective(wxSupply);
		if(wxSupplyOk!=1){
			throw new Exception("更新微信供应信息失败！");
		}
	}
}
