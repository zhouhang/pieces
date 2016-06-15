package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.dao.EastYcPriceTodayDao;
import com.jointown.zy.common.dao.WxSupplyDao;
import com.jointown.zy.common.service.EastPzDanganService;
import com.jointown.zy.common.service.EastYcPriceTodayService;
import com.jointown.zy.common.util.WxUtils;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 微信公众平台开发--今日价格ServiceImpl
 * 
 * @author aizhengdong
 *
 * @data 2015年3月1日
 */
@Service
public class EastYcPriceTodayServiceImpl implements EastYcPriceTodayService {
	@Autowired
	private WxSupplyDao wxSupplyDao;

	@Autowired
	EastPzDanganService eastPzDanganService;
	
	@Autowired
	private EastYcPriceTodayDao eastYcPriceTodayDao;
	
	/**
	 * @see com.jointown.zy.common.service.EastYcPriceTodayService#findPriceByYcName(com.jointown.zy.common.vo.WxReqBaseMessageVo,
	 *      java.lang.String)
	 */
	@Override
	public String findPriceByYcName(WxReqBaseMessageVo reqMessage, String ycName) {
		// 返回文字
		String content = "";

		String name = eastPzDanganService.findBreedNameByName(ycName);

		// 查询不到此药材名称
		if (name == null || name == "") {
			name = ycName;
			content = WxConstant.TODAY_PRICE_4_CONTENT.replace(
					WxConstant.YC_NAME_TEXT, name);

		} 
		// 有此药材
		else {
			// 今日价格集合
			List<Map<String, Object>> prices = wxSupplyDao
					.selectTodayPriceByBreedName(name);

			// 卖家数
			int sellerCount = wxSupplyDao.selectUserCountByBreedName(name);

			// 供应信息数
			int supplyCount = wxSupplyDao.selectSupplyCountByBreedName(name);

			// 有供应信息
			if (supplyCount > 0) {

				// 有价格
				if (prices.size() > 0) {
					// 填充药材名称
					content = WxConstant.TODAY_PRICE_1_CONTENT.replace(
							WxConstant.YC_NAME_TEXT, name);

					// 填充价格
					content = packPriceInfo(content, prices);

					// 填充供应信息
					content = packSupplyInfo(content, sellerCount, supplyCount);
				}
				// 无价格
				else {
					content = WxConstant.TODAY_PRICE_2_CONTENT.replace(
							WxConstant.YC_NAME_TEXT, name);
					content = packSupplyInfo(content, sellerCount, supplyCount);
				}
			}
			// 无供应信息
			else {
				// 有价格
				if (prices.size() > 0) {
					content = WxConstant.TODAY_PRICE_3_CONTENT.replace(
							WxConstant.YC_NAME_TEXT, name);
					content = packPriceInfo(content, prices);
				}
				// 无价格
				else {
					content = WxConstant.TODAY_PRICE_4_CONTENT.replace(
							WxConstant.YC_NAME_TEXT, name);
				}

			}
		}
		
		return WxUtils.createTextMessage(content, reqMessage);

	}

	/**
	 * 封装价格信息
	 *
	 * @param content
	 * @param prices
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月4日
	 */
	private String packPriceInfo(String content,
			List<Map<String, Object>> prices) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < prices.size(); i++) {
			Map<String, Object> map = prices.get(i);
			Object guiGe = map.get("YC_GUIGE");
			Object price = map.get("YC_PRI");

			if (guiGe != null && price != null) {
				// 价钱
				stringBuffer.append(price);
				stringBuffer.append("元");

				// 规格
				if (guiGe != null && guiGe != "") {
					stringBuffer.append("（").append(guiGe).append("）");
				}

				// 分隔符，最后一项不加
				if (i < (prices.size() - 1)) {
					stringBuffer.append("，");
				}
			}
		}

		return content.replace(WxConstant.PRICE_TEXT, stringBuffer.toString());
	}

	/**
	 * 封装供应信息
	 * 
	 * @param content
	 * @param sellerCount
	 * @param supplyCount
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月4日
	 */
	private String packSupplyInfo(String content, int sellerCount,
			int supplyCount) {
		return content.replace(WxConstant.SELLER_COUNT_TEXT,
				Integer.toString(sellerCount)).replace(
				WxConstant.SUPPLY_COUNT_TEXT, Integer.toString(supplyCount));
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.service.EastYcPriceTodayService#selectPriceBy(java.util.Map)
	 */
	@Override
	public List<EastYcPriceTodayVo> selectPriceBy(Map<String, Object> map) {
		return this.eastYcPriceTodayDao.selectPriceBy(map);
	}
}
