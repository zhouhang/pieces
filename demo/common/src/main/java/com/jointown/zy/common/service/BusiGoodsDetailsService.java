package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jointown.zy.common.dto.BusiBuyGoodsDto;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiCollectionVo;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.BusiGoodsRecommenVo;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;

/**
 * 
 * 描述： 前台商品详情<br/>
 * 
 * 日期： 2015年1月7日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public interface BusiGoodsDetailsService {
	/**
	 * 商品详情-卖家信息<br/>
	 * @param listingid 商品挂牌ID
	 */
	BusiGoodsSellerVo selectGoodsSellerInfo(String listingid);
	
	/**
	 * 商品详情-平台推荐
	 * @param count 推荐条数
	 */
	List<BusiGoodsRecommenVo> selectGoodsRecommenList(Integer count);
	
	/**
	 * 商品详情-商品信息（包括基本信息、交收信息、质检信息、图片）
	 */
	BusiGoodsInfoVo selectGoodsInfo(String listingid);
	
	/**
	 * 商品详情-交易记录
	 * @param page (需要设定参数：listingid[商品挂牌ID])
	 */
	List<BusiGoodsOrderVo> selectGoodsOrderList(Page<BusiGoodsOrderVo> page);
	
	/**
	 * 商品详情-购买
	 * @return "y":表示成功；<br/>"y"以外：表示失败且结果为失败信息
	 */
	String buyGoodsOrder(BusiBuyGoodsDto goodsOrder,HttpServletRequest request);
	
	/**
	 * @Description: 商品详情-购买-返回插入后的订单信息
	 * @Author: guoyb
	 * @Date: 2015年4月17日
	 * @param goodsOrder
	 * @param request
	 * @return
	 */
	BusiOrder buyGoodsOrderReturnOrder(BusiBuyGoodsDto goodsOrder,HttpServletRequest request);
	
	/**
	 * 商品详情-收藏
	 * @return "y":表示成功；<br/>"y"以外：表示失败且结果为失败信息
	 */
	String saveGoodsCollection(BusiBuyGoodsDto goodsOrder);
	
	/**
	 * 根据条件，如用户ID，排序规则查询我的收藏集合
	 * @param HashMap<String,Object> map
	 * @return 收藏列表集合 @see BusiCollectionVo
	 */
	List<BusiCollectionVo> selectCollentionsByUserId(Page<Map<String, Object>> page);
	
	/**
	 * 取消收藏 
	 * @param cindex 收藏主键
	 * @return
	 */
	public int cancelCollect(Long cindex);
	
	/**
	 * 根据用户ID查询我的收藏列表中的收藏品种清单
	 * @param Long userid
	 * @return List<HashMap<String,String>> 返回品种名称
	 */
	public List<HashMap<String,String>> selectCollectionBread(Long userid);
	
	/**
	 * 查询热门药材 5条记录
	 * @return key: TITLE 挂牌名称、LOWUNITPRICE 价格、LISTINGID 挂牌ID、
	 *              PATH 图片路径、WLID 仓单ID、DICT_VALUE 计量单位
	 */
	public List<HashMap<String, Object>> selectHotBusiListing();
	/**
	 * 通过wlid查询出卖家信息
	 * @param wlid
	 * @return
	 */
	public Map<String,Object> getSellerInfo(String wlid);
}
