package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiPurchaseApplyDao;
import com.jointown.zy.common.dao.EastGongqiuDao;
import com.jointown.zy.common.dao.WxDemandDao;
import com.jointown.zy.common.dto.WxDemandDto;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.BusiPurchaseApplyService;
import com.jointown.zy.common.service.EastGongqiuService;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.BusiPurchaseApplyVo;
import com.jointown.zy.common.vo.EastGongqiuVo;
import com.jointown.zy.common.vo.WxDemandVo;

/**
 * 求购信息ServiceImpl
 * 
 * @author wangjunhu
 *
 * @data 2015年3月12日
 */
@Service
public class WxDemandServiceImpl implements WxDemandService {

	@Autowired
	WxDemandDao wxDemandDao;
	
	@Autowired
	EastGongqiuDao eastGongqiuDao;
	
	@Autowired
	BusiPurchaseApplyDao busiPurchaseApplyDao;
	
	@Autowired
	private BusiPurchaseApplyService busiPurchaseApplyService;
	
	@Autowired
	private BreedService breedService ;
	
	@Autowired
	private EastGongqiuService eastGongqiuService;

	/**
	 * 查询求购信息
	 *
	 * 修改：空值填充文字处理
	 * 
	 * @author aizhengdong
	 * @date 2015年8月7日
	 */
	@Override
	public List<WxDemandVo> findWxDemandsByCondition(Page<WxDemandVo> page) {
		List<WxDemandVo> wxDemandVos = wxDemandDao.selectWxDemandsByCondition(page);
		for (WxDemandVo wxDemandVo : wxDemandVos) {
			if(wxDemandVo != null){
				String breedStandardLevel = StringUtils.isBlank(wxDemandVo.getBreedStandardLevel()) ? "不限" : wxDemandVo.getBreedStandardLevel();
				String breedAmount = StringUtils.isBlank(wxDemandVo.getQtyUnitQty()) ? "面议" : wxDemandVo.getQtyUnitQty();
				String breedPrice = StringUtils.isBlank(wxDemandVo.getBreedPrice()) ? "面议" : wxDemandVo.getBreedPrice();
				String breedPlace = StringUtils.isBlank(wxDemandVo.getBreedPlace()) ? "不限" : wxDemandVo.getBreedPlace();
				String depict = StringUtils.isBlank(wxDemandVo.getDepict()) ? "无" : wxDemandVo.getDepict();
				
				wxDemandVo.setBreedStandardLevel(breedStandardLevel);
				wxDemandVo.setQtyUnitQty(breedAmount);
				wxDemandVo.setBreedPrice(breedPrice);
				wxDemandVo.setBreedPlace(breedPlace);
				wxDemandVo.setDepict(depict);
			}
			
		}
	
		return wxDemandVos;
	}

	/**
	 * @Description:查询东网与珍药材的供应信息（翻页，每次显示5条）
	 * @author lichenxiao
	 * @Date:2015年4月20日下午16：10
	 * @param map
	 * @return
	 */
	@Override
	public List<WxDemandVo> selectInfoByDemand(Map<String, Object> map) {
		return wxDemandDao.selectInfoByDemand(map);
	}

	@Override
	public List<WxDemandVo> findWxDemandsByPage(Page<WxDemand> page) {
		List<WxDemandVo> wxDemandVos = wxDemandDao.selectWxDemandsByPage(page);
		return wxDemandVos;
	}

	@Override
	public WxDemandVo findWxDemandById(WxDemand wxDemand) {
		WxDemandVo wxDemandVo = wxDemandDao.selectWxDemandById(wxDemand);
		return wxDemandVo;
	}

	@Override
	public void addWxDemand(WxDemandDto wxDemandDto) throws Exception {
		WxDemand wxDemand = wxDemandDto.getWxDemand();
		wxDemand.setStatus((short) 0);
		wxDemand.setCreateTime(new Date());
		wxDemand.setUpdateTime(new Date());
		int wxDemandOk = wxDemandDao.insertSelective(wxDemand);
		if (wxDemandOk != 1) {
			throw new Exception("新增微信求购信息失败！");
		}
	}

	@Override
	public void updateWxDemandById(WxDemand wxDemand) throws Exception {
		wxDemand.setUpdateTime(new Date());
		int wxDemandOk = wxDemandDao.updateByPrimaryKeySelective(wxDemand);
		if (wxDemandOk != 1) {
			throw new Exception("更新微信求购信息失败！");
		}
	}
	
	/**
	 * 查询求购信息详情
	 *
	 * 修改：空值填充文字处理
	 * 
	 * @author aizhengdong
	 * @date 2015年8月7日
	 */
	@Override
	public WxDemandVo findDemandByCondition(WxDemandDto wxDemandDto) {
		String resource = wxDemandDto.getApplyResource();
		WxDemandVo wxDemandVo = null;
				
		// 信息来源：微信
		if ("0".equals(resource)) {
			wxDemandVo = findWxDemandByIdFromBack(wxDemandDto);
			wxDemandVo.setApplyResource(resource);
		}
		// 信息来源：东方中药材
		else if ("1".equals(resource)) {
			EastGongqiuVo eastGongqiuVo = eastGongqiuService.findEastGongqiuById(wxDemandDto.getDemandId());
			wxDemandVo = new WxDemandVo();
			wxDemandVo.setDemandId((long)eastGongqiuVo.getGqid());
			wxDemandVo.setUserName(eastGongqiuVo.getUserName());
			wxDemandVo.setUserMobile(eastGongqiuVo.getUserMobile());
			wxDemandVo.setBreedName(eastGongqiuVo.getYcnam());
			wxDemandVo.setBreedStandardLevel(eastGongqiuVo.getGuige());
			wxDemandVo.setBreedPrice(eastGongqiuVo.getPri());
			wxDemandVo.setBreedPlace(eastGongqiuVo.getChandi());
			wxDemandVo.setQtyUnitQty(eastGongqiuVo.getQtyUnitQty());
			wxDemandVo.setCreateTime(eastGongqiuVo.getDtm());
			wxDemandVo.setUpdateTime(eastGongqiuVo.getUpdtm());
			wxDemandVo.setStatus(eastGongqiuVo.getStatus());
			wxDemandVo.setApproverName(eastGongqiuVo.getApproverName());
			wxDemandVo.setApproveTime(eastGongqiuVo.getApproveTime());
			wxDemandVo.setApplyResource(resource);
		}
		// 信息来源：珍药材或客服
		else {
			BusiPurchaseApplyVo busiPurchaseApplyVo = busiPurchaseApplyService.findBusiPurchaseApplyById(wxDemandDto.getDemandId());
			wxDemandVo = new WxDemandVo();
			wxDemandVo.setDemandId(busiPurchaseApplyVo.getPurchaseId());
			wxDemandVo.setUserName(busiPurchaseApplyVo.getApplyName());
			wxDemandVo.setUserMobile(busiPurchaseApplyVo.getApplyMobile());
			wxDemandVo.setBreedName(busiPurchaseApplyVo.getBreedName());
			wxDemandVo.setBreedStandardLevel(busiPurchaseApplyVo.getBreedStandardLevel());
			wxDemandVo.setBreedPrice(busiPurchaseApplyVo.getBreedPrice());
			wxDemandVo.setBreedPlace(busiPurchaseApplyVo.getBreedPlace());
			wxDemandVo.setQtyUnitQty(busiPurchaseApplyVo.getBreedAmount());
			wxDemandVo.setCreateTime(busiPurchaseApplyVo.getCreateTime());
			wxDemandVo.setUpdateTime(busiPurchaseApplyVo.getUpdateTime());
			wxDemandVo.setStatus(busiPurchaseApplyVo.getStatus());
			wxDemandVo.setApproverName(busiPurchaseApplyVo.getApplyName());
			wxDemandVo.setApproveTime(busiPurchaseApplyVo.getApproveTime());
			wxDemandVo.setApplyResource(busiPurchaseApplyVo.getApplyResource());
			wxDemandVo.setDepict(busiPurchaseApplyVo.getDepict());
		}
		
		if(wxDemandVo != null){
			String breedStandardLevel = StringUtils.isBlank(wxDemandVo.getBreedStandardLevel()) ? "不限" : wxDemandVo.getBreedStandardLevel();
			String breedAmount = StringUtils.isBlank(wxDemandVo.getQtyUnitQty()) ? "面议" : wxDemandVo.getQtyUnitQty();
			String breedPrice = StringUtils.isBlank(wxDemandVo.getBreedPrice()) ? "面议" : wxDemandVo.getBreedPrice();
			String breedPlace = StringUtils.isBlank(wxDemandVo.getBreedPlace()) ? "不限" : wxDemandVo.getBreedPlace();
			String depict = StringUtils.isBlank(wxDemandVo.getDepict()) ? "无" : wxDemandVo.getDepict();
			
			wxDemandVo.setBreedStandardLevel(breedStandardLevel);
			wxDemandVo.setQtyUnitQty(breedAmount);
			wxDemandVo.setBreedPrice(breedPrice);
			wxDemandVo.setBreedPlace(breedPlace);
			wxDemandVo.setDepict(depict);
		}
		
		return wxDemandVo;
	}

	/**
	 * @see com.jointown.zy.common.service.WxDemandService#findWxDemandByIdFromBack(com.jointown.zy.common.model.WxDemand)
	 */
	@Override
	public WxDemandVo findWxDemandByIdFromBack(WxDemandDto wxDemandDto) {
		WxDemand wxDemand = new WxDemand();
		wxDemand.setDemandId(wxDemandDto.getDemandId());
		return wxDemandDao.selectWxDemandByIdFromBack(wxDemand);
	}

	@Override
	public int updateDemandStatus(WxDemandDto wxDemandDto) {
		String resource = wxDemandDto.getApplyResource();
		if (StringUtils.isBlank(resource)) {
			return 0;
		}
		
		Long bossUserId = wxDemandDto.getApprover();
		Date date = null;
		if(bossUserId == null){
			BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			bossUserId =  new Long(bossUser.getId());
			date = new Date();
		}else{
			date = wxDemandDto.getApproveTime();
		}

		// 信息来源：微信
		if ("0".equals(resource)) {
			WxDemand wxDemand = new WxDemand();
			wxDemand.setDemandId(wxDemandDto.getDemandId());
			wxDemand.setStatus(wxDemandDto.getStatus());
			wxDemand.setRemarks(wxDemandDto.getRemarks());
			wxDemand.setApprover(bossUserId);
			wxDemand.setApproveTime(date);
			return wxDemandDao.updateByPrimaryKeySelective(wxDemand);
		}
		// 信息来源：东方中药材
		else if ("1".equals(resource)) {
			EastGongqiu eastGongqiu = new EastGongqiu();
			eastGongqiu.setGqid(new BigDecimal(wxDemandDto.getDemandId()));
			eastGongqiu.setStatus(wxDemandDto.getStatus());
			eastGongqiu.setApprover(bossUserId);
			eastGongqiu.setApproveTime(date);
			return eastGongqiuDao.updateByPrimaryKeySelective(eastGongqiu);
		}
		// 信息来源：珍药材或客服
		else {
			BusiPurchaseApply busiPurchaseApply = new BusiPurchaseApply();
			busiPurchaseApply.setPurchaseId(wxDemandDto.getDemandId());
			busiPurchaseApply.setStatus(wxDemandDto.getStatus());
			busiPurchaseApply.setRemarks(wxDemandDto.getRemarks());
			busiPurchaseApply.setApprover(bossUserId);
			busiPurchaseApply.setApproveTime(date);
			return busiPurchaseApplyDao.updateByPrimaryKeySelective(busiPurchaseApply);
		}
	}

	/**
	 * @see com.jointown.zy.common.service.WxDemandService#addBusiPurchaseApply(com.jointown.zy.common.model.BusiPurchaseApply)
	 */
	@Override
	public int addBusiPurchaseApply(WxDemandDto wxDemandDto) throws Exception {
		Long bossUserId = wxDemandDto.getApprover();
		Date date = null;
		if(bossUserId == null){
			BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			bossUserId =  new Long(bossUser.getId());
			date = new Date();
		}else{
			date = wxDemandDto.getApproveTime();
		}
		
		String breedStandardLevel = StringUtils.isBlank(wxDemandDto.getBreedStandardLevel()) ? "不限" : wxDemandDto.getBreedStandardLevel();
		String breedAmount = StringUtils.isBlank(wxDemandDto.getQtyUnitQty()) ? "面议" : wxDemandDto.getQtyUnitQty();
		String breedPrice = StringUtils.isBlank(wxDemandDto.getBreedPrice()) ? "面议" : wxDemandDto.getBreedPrice();
		String breedPlace = StringUtils.isBlank(wxDemandDto.getBreedPlace()) ? "不限" : wxDemandDto.getBreedPlace();
		
		BusiPurchaseApply busiPurchaseApply = new BusiPurchaseApply();
		busiPurchaseApply.setApplyName(wxDemandDto.getUserName());
		busiPurchaseApply.setApplyMobile(Long.parseLong(wxDemandDto.getUserMobile()));
		busiPurchaseApply.setBreedName(wxDemandDto.getBreedName());
		busiPurchaseApply.setBreedStandardLevel(breedStandardLevel);
		busiPurchaseApply.setBreedAmount(breedAmount);
		busiPurchaseApply.setBreedPrice(breedPrice);
		busiPurchaseApply.setBreedPlace(breedPlace);
		busiPurchaseApply.setApplyResource("客服");
		busiPurchaseApply.setBreedDesc("无");
		busiPurchaseApply.setStatus((short)1);
		busiPurchaseApply.setCreateTime(date);
		busiPurchaseApply.setUpdateTime(date);
		busiPurchaseApply.setApprover(bossUserId);
		busiPurchaseApply.setApproveTime(date);
		busiPurchaseApply.setDepict(wxDemandDto.getDepict());
		
		return busiPurchaseApplyService.addPurchaseApply(busiPurchaseApply);
	}

	/**
	 * @see com.jointown.zy.common.service.WxDemandService#getBreedNames(java.lang.String)
	 */
	@Override
	public List<Breed> getBreedNames(String breedName) {
		return breedService.findBreedsByName(breedName);
	}

	/**
	 * @see com.jointown.zy.common.service.WxDemandService#checkBreedName(java.lang.String)
	 */
	@Override
	public Breed checkBreedName(String breedName) {
		return breedService.findBreedByBreedName(breedName);
	}

}
