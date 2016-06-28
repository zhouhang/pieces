package com.jointown.zy.common.util;

import com.alibaba.fastjson.JSONObject;
import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.vo.WxBaseButtonVo;
import com.jointown.zy.common.vo.WxCommonButtonVo;
import com.jointown.zy.common.vo.WxComplexButtonVo;
import com.jointown.zy.common.vo.WxMenuVo;

/**
 * 微信公众平台开发--菜单管理器类
 * 
 * @author aizhengdong
 *
 * @data 2015年2月9日
 */
public class WxMenuManager {

	/**
	 * 创建菜单
	 * 
	 * @param accessToken
	 * @return 返回码
	 */
	public static String createMenu() {
		// 获取access_token
		String accessToken = WxUtils.getAccessToken();
		if (accessToken == null) {
			return WxConstant.ACCESS_TOKEN_ERRMSG;
		}

		WxMenuVo wxMenu = getWxMenu();
		String url = WxConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		String jsonWxMenu = JSONObject.toJSONString(wxMenu);
		JSONObject jsonObject = WxUtils.httpRequest(url, "POST", jsonWxMenu);
		if (jsonObject == null) {
			// 请求失败
			return WxConstant.REQ_FAIL_ERRMSG;
		}

		return jsonObject.getInteger("errcode") + jsonObject.getString("errmsg");

	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static WxMenuVo getWxMenu() {
		/**	微信一至三期：一二级菜单显示自助查询与行情动态 start author:lichenxiao 2015年5月
		// 自助查询
		WxCommonButtonVo btn11 = new WxCommonButtonVo();
		btn11.setName("药材价格");
		btn11.setType(WxConstant.VIEW_MENU_TYPE);
		btn11.setUrl(WxConstant.BREED_PRICE_URL);
		WxCommonButtonVo btn12 = new WxCommonButtonVo();
		btn12.setName("分析报告");
		btn12.setType(WxConstant.VIEW_MENU_TYPE);
		btn12.setUrl(WxConstant.ANALYSIS_REPORT_URL);
		WxCommonButtonVo btn14 = new WxCommonButtonVo();
		btn14.setName("药材百科");
		btn14.setType(WxConstant.VIEW_MENU_TYPE);
		btn14.setUrl(WxConstant.ENCYCLOPEDIA_URL);
		WxCommonButtonVo btn15 = new WxCommonButtonVo();
		btn15.setName("供求信息");
		btn15.setType(WxConstant.VIEW_MENU_TYPE);
		btn15.setUrl(WxConstant.SUPPLY_URL);
		WxComplexButtonVo mainBtn1 = new WxComplexButtonVo();
		mainBtn1.setName("自助查询");
		mainBtn1.setSub_button(new WxCommonButtonVo[] { btn11, btn12, btn14, btn15 });
		// 行情动态
		WxCommonButtonVo btn21 = new WxCommonButtonVo();
		btn21.setName("涨跌TOP10");
		btn21.setType(WxConstant.VIEW_MENU_TYPE);
		btn21.setUrl(WxConstant.TOP10_URL);
		WxCommonButtonVo btn22 = new WxCommonButtonVo();
		btn22.setName("行情快讯");
		btn22.setType(WxConstant.VIEW_MENU_TYPE);
		btn22.setUrl(WxConstant.PRICE_NEWS_URL);
		WxCommonButtonVo btn23 = new WxCommonButtonVo();
		btn23.setName("市场动态");
		btn23.setType(WxConstant.CLICK_MENU_TYPE);
		btn23.setKey(WxConstant.SCDT_MENU_KEY);
		WxCommonButtonVo btn24 = new WxCommonButtonVo();
		btn24.setName("行业新闻");
		btn24.setType(WxConstant.CLICK_MENU_TYPE);
		btn24.setKey(WxConstant.HY_NEWS_MENU_KEY);
		WxComplexButtonVo mainBtn2 = new WxComplexButtonVo();
		mainBtn2.setName("行情动态");
		mainBtn2.setSub_button(new WxCommonButtonVo[] { btn21, btn22, btn23, btn24 });
		// 珍药电商-活动区
		mainBtn3.setName("珍药电商");
		mainBtn3.setType(WxConstant.VIEW_MENU_TYPE);
		mainBtn3.setUrl(WxConstant.ZYDS_URL);
		微信一至三期：一二级菜单显示自助查询与行情动态 end author:lichenxiao 2015年5月*/

		/**微信第四期：一二级菜单显示自助查询与行情动态 start author:lichenxiao 2015年6月24日
		//点击微信一级菜单进入H5页面，显示品种资讯与行情动态
		WxComplexButtonVo mainBtn1 = new WxComplexButtonVo();
		mainBtn1.setName("品种资讯");
		mainBtn1.setType(WxConstant.VIEW_MENU_TYPE);
		mainBtn1.setUrl(WxConstant.PZ_NEWS_URL);
		WxComplexButtonVo mainBtn2 = new WxComplexButtonVo();
		mainBtn2.setName("行情资讯");
		mainBtn2.setType(WxConstant.VIEW_MENU_TYPE);
		mainBtn2.setUrl(WxConstant.HQ_NEWS_URL);		
		//用户功能
		WxCommonButtonVo btn31 = new WxCommonButtonVo();
		btn31.setName("新手指引");
		btn31.setType(WxConstant.VIEW_MENU_TYPE);
		btn31.setUrl(WxConstant.NEW_HAND_URL);
		WxCommonButtonVo btn32 = new WxCommonButtonVo();
		btn32.setName("入驻珍药材");
		btn32.setType(WxConstant.VIEW_MENU_TYPE);
		btn32.setUrl(WxConstant.REGIST_URL);
		WxCommonButtonVo btn33 = new WxCommonButtonVo();
		btn33.setName("发布供求");
		btn33.setType(WxConstant.VIEW_MENU_TYPE);
		btn33.setUrl(WxConstant.SUPPLY_SEND_URL);
		WxComplexButtonVo mainBtn3 = new WxComplexButtonVo();
		mainBtn3.setName("业务办理");
		mainBtn3.setSub_button(new WxCommonButtonVo[] { btn31,btn32,btn33 });
		*/
		
		/** 微信第五期：一二级菜单进入H5页面，显示品种行情与我的小珍 start author:lichenxiao 2015年8月9日*/
		WxComplexButtonVo mainBtn1 = new WxComplexButtonVo();
		mainBtn1.setName("💹品种行情");
		mainBtn1.setType(WxConstant.VIEW_MENU_TYPE);
		mainBtn1.setUrl(WxConstant.MENU_BREED_PRICE_URL);
		WxComplexButtonVo mainBtn2 = new WxComplexButtonVo();
		mainBtn2.setName("🔑我的小珍");
		mainBtn2.setType(WxConstant.VIEW_MENU_TYPE);
		mainBtn2.setUrl(WxConstant.MENU_MY_ZYC_URL);		
		/** 点击微信一级菜单进入H5页面，显示品种行情与我的小珍 end author:lichenxiao 2015年8月9日*/
	
		//微信第五期：小珍肋手菜单 author:lichenxiao 2015年8月9日
		WxCommonButtonVo btn31 = new WxCommonButtonVo();
		btn31.setName("新手指引");
		btn31.setType(WxConstant.VIEW_MENU_TYPE);
		btn31.setUrl(WxConstant.NEW_HAND_URL);
		WxCommonButtonVo btn32 = new WxCommonButtonVo();
		btn32.setName("咨询客服");
		btn32.setType(WxConstant.VIEW_MENU_TYPE);
		btn32.setUrl(WxConstant.SERVICE_URL);
		WxCommonButtonVo btn33 = new WxCommonButtonVo();
		btn33.setName("我要提建议");
		btn33.setType(WxConstant.VIEW_MENU_TYPE);
		btn33.setUrl(WxConstant.FEEDBACK_URL);
		WxCommonButtonVo btn34 = new WxCommonButtonVo();
		btn34.setName("诚聘英才");
		btn34.setType(WxConstant.VIEW_MENU_TYPE);
		btn34.setUrl(WxConstant.RECRUIT_URL);
		WxComplexButtonVo mainBtn3 = new WxComplexButtonVo();
		mainBtn3.setName("❤小珍助手");
		mainBtn3.setSub_button(new WxCommonButtonVo[] { btn31,btn32,btn33,btn34 });
		
		WxMenuVo WxMenu = new WxMenuVo();
		WxMenu.setButton(new WxBaseButtonVo[] { mainBtn1, mainBtn2, mainBtn3 });

		return WxMenu;
	}
}
