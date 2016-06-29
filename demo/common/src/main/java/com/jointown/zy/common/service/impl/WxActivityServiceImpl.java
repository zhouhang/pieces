package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Session;
import com.jointown.zy.common.dao.WxActivityDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxActivityService;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.WxUtils;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxActivityVo;
import com.jointown.zy.common.vo.WxArticleVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 微信公众平台开发--活动ServiceImpl
 * 
 * @author aizhengdong
 *
 * @data 2015年2月14日
 */
@Service
public class WxActivityServiceImpl implements WxActivityService {
	
	@Autowired
	private WxActivityDao wxActivityDao;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;

	/**
	 * @see com.jointown.zy.common.service.WxActivityService#findActivity(com.jointown.zy.common.vo.WxReqBaseMessageVo)
	 */
	@Override
	public String findActivity(WxReqBaseMessageVo reqMessage) {
		List<WxActivity> activitys = wxActivityDao.findActivity();
		return WxUtils.createNewsMessage(packWxArticle(activitys), reqMessage);
	}

	/**
	 * @see com.jointown.zy.common.service.WxActivityService#findSellInfo(com.jointown.zy.common.vo.WxReqBaseMessageVo)
	 */
	@Override
	public String findSellInfo(WxReqBaseMessageVo reqMessage) {
		List<WxActivity> activitys = wxActivityDao.findSellInfo();
		return WxUtils.createNewsMessage(packWxArticle(activitys), reqMessage);
	}

	/**
	 * 把活动的数据封装为微信接口格式的数据
	 * 
	 * @param activitys 活动的数据
	 * @return 微信多图文格式的数据
	 */
	private List<WxArticleVo> packWxArticle(List<WxActivity> activitys){
		List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
		for (WxActivity activity : activitys) {
			WxArticleVo article = new WxArticleVo();
			article.setTitle(activity.getName());
			article.setDescription(activity.getMemo());
			article.setUrl(activity.getUrl());
			article.setPicUrl(activity.getPicUrl());
			articles.add(article);
		}
		
		return articles;
	}
	
	@Override
	public List<WxActivityVo> findWxActivitysByCondition(Page<WxActivityVo> page){
		return wxActivityDao.selectWxActivitysByCondition(page);
	}

	@Override
	public int findByCondition(WxActivity wxActivity) {
		return wxActivityDao.selectByCondition(wxActivity);
	}
	
	@Override
	public int addWxActivity(WxActivity wxActivity) {
		Date date = new Date();
		wxActivity.setCreateTime(date);
		wxActivity.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxActivity.setCreater(bossUserId);
		wxActivity.setUpdater(bossUserId);
		
		String picUrl = wxActivity.getPicUrl();
		if(picUrl!=null&&!picUrl.isEmpty()){
			String fileNewPath = saveWxActivityPic(picUrl);
			wxActivity.setPicUrl(fileNewPath);
		}
		return wxActivityDao.insertSelective(wxActivity);
	}

	@Override
	public int updateWxActivity(WxActivity wxActivity) {
		Date date = new Date();
		wxActivity.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxActivity.setUpdater(bossUserId);
		
		String picUrl = wxActivity.getPicUrl();
		if(picUrl!=null&&!picUrl.isEmpty()){
			String fileNewPath = saveWxActivityPic(picUrl);
			wxActivity.setPicUrl(fileNewPath);
		}
		return wxActivityDao.updateByPrimaryKeySelective(wxActivity);
	}

	@Override
	public int deleteWxActivity(WxActivity wxActivity) {
		Date date = new Date();
		wxActivity.setUpdateTime(date);
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		Integer bossUserId = bossUser.getId();
		wxActivity.setUpdater(bossUserId);
		wxActivity.setStatus(1);
		return wxActivityDao.updateByPrimaryKeySelective(wxActivity);
	};
	
	@Override
	public WxActivityVo findWxActivityById(Long activityId) {
		return wxActivityDao.selectByPrimaryKey(activityId);
	}
	
	@Override
	public String saveWxActivityPic(String filePath){
		SFTPUtil sftp = null;
		Session session = null;
		String fileNewPath = null;
		try{
			sftp = SFTPUtil.getSingleton();
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("imgpath", filePath);
			map.put("dataDir", sftpConfigInfo.getSftpDataDir());
			map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
			map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
			map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
			sftp.moveImg(session, map);
			
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
			String b = filePath.substring(0,filePath.lastIndexOf("/"));
			String dateDir = b.substring(b.lastIndexOf("/")+1);
			
			fileNewPath = sftpConfigInfo.getSftpPath()+dateDir+"/"+fileName;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				}
			}
		}
		return fileNewPath;
	}

	@Override
	public int addActivityByWxBoss(WxActivity wxActivity) {
		return wxActivityDao.insertSelective(wxActivity);
	}
	
	@Override
	public int updateActivityByWxBoss(WxActivity wxActivity) {
		return wxActivityDao.updateByPrimaryKeySelective(wxActivity);
	}
	
	@Override
	public int deleteActivityByWxBoss(WxActivity wxActivity) {
		wxActivity.setStatus(1);
		return wxActivityDao.updateByPrimaryKeySelective(wxActivity);
	}
	
}
