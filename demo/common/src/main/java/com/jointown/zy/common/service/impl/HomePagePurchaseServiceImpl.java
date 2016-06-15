package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.HomePagePurchaseDao;
import com.jointown.zy.common.model.HomePagePurchaseModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.HomePagePurchaseService;
import com.jointown.zy.common.vo.BossUserVo;

/**
 * @ClassName: HomePagePurchaseServiceImpl
 * @Description: 首页大货采购信息维护
 * @Author: Calvin.wh
 * @Date: 2015-11-4
 * @Version: 1.0
 */
@Service
public class HomePagePurchaseServiceImpl implements HomePagePurchaseService {
	
	@Autowired
	private HomePagePurchaseDao purchaseDao;
	
	/**
	 * @Description: 获取后台用户信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 * @return
	 */
	public BossUserVo getUser(){
		Subject subject = SecurityUtils.getSubject();
		BossUserVo user = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		return user;
	}
	
	/**
	 * @Description: 分页列表
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 */
	public List<HomePagePurchaseModel> getPageList(Page<HomePagePurchaseModel> page) {
		return purchaseDao.getPageList(page);
	}
	
	/**
	 * @Description: 验证采购订单
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 */
	public Integer getPurchaseOrder(String purchaseId){
		return purchaseDao.purchaseOrderIsExist(purchaseId);
	}
	
	/**
	 * @Description: 查询采购信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 */
	public HomePagePurchaseModel getPurchaseInfo(String id){
		return purchaseDao.getPurchaseById(id);
	}
	
	/**
	 * @Description: 新增采购信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 */
	public Map<String,Object> addPurchase(HomePagePurchaseModel purchase){
		Map<String,Object> map = validate(purchase);
		boolean ok = (boolean) map.get("ok");
		if(ok){
			purchase.setCreater(getUser().getId());
			purchase.setCreateTime(new Date());
			purchase.setUpdateTime(new Date());
			purchase.setStatus(0);
			int flag = purchaseDao.addPurchaseInfo(purchase);
			if(flag < 1){
				map.put("ok", Boolean.FALSE);
				map.put("msg", "保存失败");
			}else{
				map.put("ok", Boolean.TRUE);
				map.put("msg", "保存成功");
			}
		}
		return map;
	}
	
	/**
	 * @Description: 修改采购信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param purchase
	 * @return
	 */
	public Map<String,Object> updatePurchase(HomePagePurchaseModel purchase){
		Map<String,Object> map = validate(purchase);
		boolean ok = (boolean) map.get("ok");
		if(ok){
			purchase.setUpdater(getUser().getId());
			purchase.setUpdateTime(new Date());
			purchase.setStatus(0);
			int flag = purchaseDao.updatePurchaseInfo(purchase);
			if(flag < 1){
				map.put("ok", Boolean.FALSE);
				map.put("msg", "修改失败");
			}else{
				map.put("ok", Boolean.TRUE);
				map.put("msg", "修改成功");
			}
		}
		return map;
	}
	
	/**
	 * @Description: 逻辑删除
	 * @Author: Calvin.wh
	 * @Date: 2015-11-4
	 */
	public Map<String,Object> deletePurchase(String id){
		Map<String,Object> map = new HashMap<String,Object>();
			HomePagePurchaseModel purchase = new HomePagePurchaseModel();
			purchase.setHomePageId(Integer.valueOf(id));
			purchase.setUpdater(getUser().getId());
			purchase.setUpdateTime(new Date());
			purchase.setStatus(1);
			int flag = purchaseDao.updatePurchaseInfo(purchase);
			if(flag < 0){
				map.put("ok", Boolean.FALSE);
				map.put("msg", "删除失败");
			}else{
				map.put("ok", Boolean.TRUE);
				map.put("msg", "删除成功");
			}
		return map;
	}
	
	/**
	 * @Description: 后台参数验证
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param purchase
	 * @return
	 */
	public Map<String,Object> validate(HomePagePurchaseModel purchase){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ok", Boolean.TRUE);
		if(null == purchase){
			map.put("ok", Boolean.FALSE);
			map.put("msg", "采购信息不能为空");
			return map;
		}
		if(StringUtils.isBlank(purchase.getPurchaseId())){
			map.put("ok", Boolean.FALSE);
			map.put("msg", "采购单号不能为空");
			return map;
		}
		if(null == purchase.getType()){
			map.put("ok", Boolean.FALSE);
			map.put("msg", "类型不能为空");
			return map;
		}
		if(null == purchase.getSortNo()){
			map.put("ok", Boolean.FALSE);
			map.put("msg", "排序编号不能为空");
			return map;
		}
		return map;
	}

}
