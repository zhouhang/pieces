package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.WxAdDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxAdVo;

/**
 * 微信公众平台开发--广告ServiceImpl
 * 
 * @author lichenxiao
 *
 * @data 2015年3月17日
 */
@Service
public class WxAdServiceImpl implements WxAdService {
	@Autowired
	private WxAdDao wxAdDao;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	/**
	 * 广告列表，分页查询
	 * @param page
	 * @return
	 */
	@Override
	public List<WxAdVo> findWxAdsByCondition(Page<WxAdVo> page) {
		return wxAdDao.selectWxAdsByCondition(page);
	}
	
    /**
     * 查询数量，条件查询
     * @param wxAd
     * @return
     */
	@Override
	public int findByCondition(WxAd wxAd) {
		return wxAdDao.selectByCondition(wxAd);
	}
	
	/**
	 * 通过广告位置查找广告
	 */
	@Override
	public WxAdVo findWxAdsByPostionName(String adPostionName) {
		// TODO Auto-generated method stub
		return wxAdDao.findWxAdsByPostionName(adPostionName);
	}

	/**
	 * 添加广告
	 * @param wxAd
	 * @return
	 */
	@Override
	public int addWxAd(WxAd wxAd) {
		Date date = new Date();
		wxAd.setCreateTime(date);
		wxAd.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxAd.setCreater(bossUserId);
		wxAd.setUpdater(bossUserId);
		return wxAdDao.insertSelective(wxAd);
	}

	@Override
	public int addAdByWxBoss(WxAd wxAd) {
		return wxAdDao.insertSelective(wxAd);
	}
	
	/**
	 * 修改广告
	 * @param wxAd
	 * @return
	 */
	@Override
	public int updateWxAd(WxAd wxAd) {
		Date date = new Date();
		wxAd.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxAd.setUpdater(bossUserId);
		return wxAdDao.updateByPrimaryKeySelective(wxAd);
	}

	@Override
	public int updateAdByWxBoss(WxAd wxAd) {
		return wxAdDao.updateByPrimaryKeySelective(wxAd);
	}
	
	/**
	 * 删除广告
	 * @param wxAd
	 * @return
	 */
	@Override
	public int deleteWxAd(WxAd wxAd) {
		Date date = new Date();
		wxAd.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxAd.setUpdater(bossUserId);
		wxAd.setStatus(1);
		return wxAdDao.updateByPrimaryKeySelective(wxAd);
	}

	@Override
	public int deleteAdByWxBoss(WxAd wxAd) {
		wxAd.setStatus(1);
		return wxAdDao.updateByPrimaryKeySelective(wxAd);
	}
	
	/**
	 * 得到当前广告
	 * @param adId
	 * @return
	 */
	@Override
	public WxAdVo findWxAdById(Long adId) {
		return wxAdDao.selectByPrimaryKey(adId);
	}

}
