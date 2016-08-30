package com.pieces.biz.controller;


import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pieces.dao.model.CommodityCollect;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CommodityCollectVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.CommodityCollectService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;

/**
 * Author: ff 8/29/16. 商品收藏
 */
@Controller
@RequestMapping("/center")
public class CommodityCollectController {
	@Autowired
	private CommodityCollectService commodityCollectService;
	
	@Autowired
	private CommodityService commodityService;
	
	/**
	 * 添加商品收藏
	 */
	@RequestMapping(value = "/collect/add/{id}")
	@ResponseBody
	public Result addCollect(@PathVariable("id") Integer cid, ModelMap model) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		CommodityCollect cc = new CommodityCollect();
		cc.setCommodityId(cid);
		cc.setUserId(user.getId());
		cc.setCreateTime(new Date());
		commodityCollectService.create(cc);
		return new Result(true).info("成功添加收藏");
	}
	
	/**
	 * 商品收藏列表
	 *
	 */
	@RequestMapping(value = "/collect/index")
	public String index(ModelMap model) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		List<CommodityCollectVo> ccl = commodityCollectService.findByUser(user.getId());
		String ids = "";
		for(CommodityCollectVo ccv : ccl){
			ids = ids + ccv.getCommodityId() + ",";
		}
		ids = ids.substring(0, ids.length()-1);
		List<CommodityVo> cvl = commodityService.findVoByIds(ids);
		
		model.put("commodityVoList", cvl);
		return "user_collect";
	}
	
	/**
	 * 删除商品收藏
	 */
	@RequestMapping(value = "/collect/delete/{id}")
	public String deleteCollect(@PathVariable("id") Integer cid, ModelMap model) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
		CommodityCollect cc = new CommodityCollect();
		cc.setCommodityId(cid);
		cc.setUserId(user.getId());
		commodityCollectService.deleteCollect(cc);
		return "redirect:/center/collect/index";
	}
}
