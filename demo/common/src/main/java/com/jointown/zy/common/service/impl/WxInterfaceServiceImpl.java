package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.service.EastArticleService;
import com.jointown.zy.common.service.EastYcPriceTodayService;
import com.jointown.zy.common.service.WxActivityService;
import com.jointown.zy.common.service.WxInterfaceService;
import com.jointown.zy.common.util.WxMenuManager;
import com.jointown.zy.common.util.WxUtils;
import com.jointown.zy.common.vo.WxAccessTokenVo;
import com.jointown.zy.common.vo.WxArticleVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 微信公众平台开发--微信接口业务处理ServiceImpl
 * 
 * @author aizhengdong
 *
 * @data 2015年2月9日
 */
@Service
public class WxInterfaceServiceImpl implements WxInterfaceService {
	@Autowired
	private WxActivityService wxActivityService;
	
	@Autowired
	private EastYcPriceTodayService eastYcPriceTodayService;
	
	@Autowired
	private EastArticleService eastArticleService;
	
	private final static Logger logger = LoggerFactory.getLogger(WxInterfaceServiceImpl.class);

	/**
	 * 
	 * @see com.jointown.zy.common.service.WxInterfaceService#checkSignature(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String checkSignature(HttpServletRequest request) {
		// 微信发送GET请求携带的四个参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (WxUtils.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}

		return "fail";
	}

	/**
	 * 
	 * @see com.jointown.zy.common.service.WxInterfaceService#createMenu(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String createMenu(HttpServletRequest request) {
		String respMessage;

		// 验证操作来源
		String source = request.getParameter("source");
		if (source != null && source.equals("jztwxadmin")) {
			respMessage = WxMenuManager.createMenu();
		} else {
			// 验证失败
			respMessage = "暂无权限";
		}

		return respMessage;
	}

	/**
	 * @see com.jointown.zy.common.service.WxInterfaceService#processRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;

		try {
			// 解析xml
			Map<String, String> requestMap = WxUtils.parseXml(request);
			String fromUserName = requestMap.get("FromUserName"); // 发送方帐号
			String toUserName = requestMap.get("ToUserName"); // 公众帐号
			
			WxReqBaseMessageVo reqMessage = new WxReqBaseMessageVo();
			reqMessage.setFromUserName(fromUserName);
			reqMessage.setToUserName(toUserName);

			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 1.事件推送
			if (WxConstant.EVENT_REQ_MESSAGE_TYPE.equals(msgType)) {
				String eventType = requestMap.get("Event");

				// 1.1 菜单点击事件
				if (WxConstant.CLICK_EVENT_TYPE.equals(eventType)) {
					String eventKey = requestMap.get("EventKey");

					// 1.1.1 市场动态
					if (WxConstant.SCDT_MENU_KEY.equals(eventKey)) {
						respMessage = eastArticleService.findEastArticles(reqMessage, WxConstant.SCDT_ARTICLE_TYPE);
					}
					// 1.1.2 行业新闻
					else if (WxConstant.HY_NEWS_MENU_KEY.equals(eventKey)) {
						respMessage = eastArticleService.findEastArticles(reqMessage, WxConstant.HY_NEWS_ARTICLE_TYPE);
					}
				}
				// 1.2 关注事件
				else if (WxConstant.SUBSCRIBE_EVENT_TYPE.equals(eventType)) {
					respMessage =  respDefaultMsg(reqMessage);
				}

			}
			// 2.文本消息
			else if (WxConstant.TEXT_REQ_MESSAGE_TYPE.equals(msgType)) {
				String content = requestMap.get("Content").replaceAll("\\s*", "");
				logger.info("content:", content);
				// 2.1 买XX
				if (content.startsWith("买") && !content.equals("买") && !content.equals("买+")) {
					respMessage = eastYcPriceTodayService.findPriceByYcName(reqMessage,
							content.replace("买+", "").replace("买", ""));
					logger.info("content:", "2.1 买XX");
				}
				// 2.2 卖药材介绍
				else if (content.startsWith("卖")) {
					respMessage = wxActivityService.findSellInfo(reqMessage);
					logger.info("content:", "卖药材介绍");
				}
				// 2.3 找活动
				else if ("找活动".equals(content)) {
					respMessage = wxActivityService.findActivity(reqMessage);
					logger.info("content:", "找活动");
				}
				// 2.4 我要去看看
				else if ("我要去看看".equals(content)) {
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.TEMP_LOOK_TITLE, WxConstant.TEMP_LOOK_DESCRIPTION, WxConstant.TEMP_LOOK_URL, WxConstant.TEMP_LOOK_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "我要去看看");
				}
				//2.5 合作
				//else if("合作".equals(content)){
				//	respMessage = respYjhMessage(reqMessage);
				//}
				//2.6 zmkm
				else if("zmkm".equals(content)){
					respMessage = respWxSystemMessage(reqMessage);
					logger.info("content:", "zmkm");
				}
				//2.7 1
				else if("1".equals(content)){
					respMessage = respOneMessage(reqMessage);
					logger.info("content:", "1");
				}
				//2.7 2
				else if("2".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.TEMP_BRIEF_TITLE, WxConstant.TEMP_BRIEF_DESCRIPTION, WxConstant.TEMP_BRIEF_URL, WxConstant.TEMP_BRIEF_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "2");
				}
				//2.7 3
				else if("3".equals(content)){
					respMessage = respThreeMessage(reqMessage);
					logger.info("content:", "3");
				}	
				//2.7 4
				else if("4".equals(content)){
					respMessage = respFourMessage(reqMessage);
					logger.info("content:", "4");
				}	
				//2.7 5
				else if("5".equals(content)){
					respMessage = respFiveMessage(reqMessage);
					logger.info("content:", "5");
				}					
				//2.7 6
				else if("6".equals(content)){
					respMessage = respSixMessage(reqMessage);
					logger.info("content:", "6");
				}					
				
				// 2.8.1 保护和发展
				else if("保护和发展".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.BHFZ_TITLE, WxConstant.BHFZ_DESCRIPTION, WxConstant.BHFZ_URL, WxConstant.BHFZ_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "2.8.1 保护和发展");
				}
				// 2.8.2 解读保护和发展
				else if("解读保护和发展".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.JDBHFZ_TITLE, WxConstant.JDBHFZ_DESCRIPTION, WxConstant.JDBHFZ_URL, WxConstant.JDBHFZ_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "2.8.1 解读保护和发展");
				}
				// 2.8.3 现代物流
				else if("现代物流".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.XDWL_TITLE, WxConstant.XDWL_DESCRIPTION, WxConstant.XDWL_URL, WxConstant.XDWL_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "2.8.3 现代物流");
				}
				// 2.8.4 现代物流解读
				else if("现代物流解读".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.XDWLJD_TITLE, WxConstant.XDWLJD_DESCRIPTION, WxConstant.XDWLJD_URL, WxConstant.XDWLJD_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
					logger.info("content:", "2.8.4 现代物流解读");
				}
				// 2.8.5
				else if("中医药健康服务".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.ZYYJKFW_TITLE, WxConstant.ZYYJKFW_DESCRIPTION, WxConstant.ZYYJKFW_URL, WxConstant.ZYYJKFW_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.6
				else if("重点品种流通分析".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.ZDPZLTFX_TITLE, WxConstant.ZDPZLTFX_DESCRIPTION, WxConstant.ZDPZLTFX_URL, WxConstant.ZDPZLTFX_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.7
				else if("现状及发展趋势".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.XZFZQS_TITLE, WxConstant.XZFZQS_DESCRIPTION, WxConstant.XZFZQS_URL, WxConstant.XZFZQS_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.8
				else if("发展报告一".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG1_TITLE, WxConstant.FZBG1_DESCRIPTION, WxConstant.FZBG1_URL, WxConstant.FZBG1_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.9
				else if("发展报告二".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG2_TITLE, WxConstant.FZBG2_DESCRIPTION, WxConstant.FZBG2_URL, WxConstant.FZBG2_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.10
				else if("发展报告三".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG3_TITLE, WxConstant.FZBG3_DESCRIPTION, WxConstant.FZBG3_URL, WxConstant.FZBG3_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.11
				else if("发展报告四".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG4_TITLE, WxConstant.FZBG4_DESCRIPTION, WxConstant.FZBG4_URL, WxConstant.FZBG4_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.12
				else if("发展报告五".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG5_TITLE, WxConstant.FZBG5_DESCRIPTION, WxConstant.FZBG5_URL, WxConstant.FZBG5_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.1
				else if("发展报告六".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.FZBG6_TITLE, WxConstant.FZBG6_DESCRIPTION, WxConstant.FZBG6_URL, WxConstant.FZBG6_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				// 2.8.14
				else if("大势与前景".equals(content)){
					List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
					articles.add(WxUtils.createWxArticle(WxConstant.DSQJ_TITLE, WxConstant.DSQJ_DESCRIPTION, WxConstant.DSQJ_URL, WxConstant.DSQJ_PIC_URL));
					respMessage = WxUtils.createNewsMessage(articles, reqMessage);
				}
				
				
				// 2.X其他输入内容
				else if(content.length()!=0){
					respMessage = respOtherMsg(reqMessage);
				}
			}
			// 3.图片消息
			else if (WxConstant.IMAGE_REQ_MESSAGE_TYPE.equals(msgType)) {
				respMessage = respDefaultMsg(reqMessage);
				
				// 下载图片文件
				String mediaId = requestMap.get("MediaId");  // 消息媒体id
				downloadImageMedia(mediaId);
				
				// 获取用户基本信息
				getUserInfo(fromUserName);
				
			}
			// 4.语音消息
			else if (WxConstant.VOICE_REQ_MESSAGE_TYPE.equals(msgType)) {
				respMessage = respDefaultMsg(reqMessage);
				
				// 下载语音文件
				String mediaId = requestMap.get("MediaId");
				String format = requestMap.get("Format"); // 语音格式
				downloadVoiceMedia(mediaId, format);
				
				getUserInfo(fromUserName);
				
			}
			// 5.小视频消息
			else if (WxConstant.SHORTVIDEO_REQ_MESSAGE_TYPE.equals(msgType)) {
				respMessage = respDefaultMsg(reqMessage);
				
				// 下载视频文件
				String mediaId = requestMap.get("MediaId"); // 视频消息媒体id
				String thumbMediaId = requestMap.get("ThumbMediaId"); // 视频消息缩略图的媒体id
				downloadVideoMedia(mediaId, thumbMediaId);
				
				getUserInfo(fromUserName);
			}
			// 6.地理位置消息
			else if (WxConstant.LOCATION_REQ_MESSAGE_TYPE.equals(msgType)) {
				respMessage = respDefaultMsg(reqMessage);
			}
			// 7.链接消息
			else if (WxConstant.LINK_REQ_MESSAGE_TYPE.equals(msgType)) {
				respMessage = respDefaultMsg(reqMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * 回复默认消息
	 * 
	 * @param reqMessage
	 *            接收的消息
	 * @return 普通本文消息
	 */
	private String respDefaultMsg(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.DEFAULT_RESP_CONTENT, reqMessage);
	}

	/**
	 * 回复消息"合作"显示药交会联系信息
	 * @param reqMessage
	 * @return
	 
	private String respYjhMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.YJH_RESP_CONTENT, reqMessage);
	}
	*/
	/**
	 * 回复消息zmkm
	 * @param reqMessage
	 * @return
	 */
	private String respWxSystemMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.WXSYSTEM_RESP_CONTENT, reqMessage);
	}
	
	/**
	 * 回复消息1
	 * @param reqMessage
	 * @return
	 */
	private String respOneMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.ONE_RESP_CONTENT, reqMessage);
	}

	/**
	 * 回复消息3
	 * @param reqMessage
	 * @return
	 */
	private String respThreeMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.THREE_RESP_CONTENT, reqMessage);
	}

	/**
	 * 回复消息4
	 * @param reqMessage
	 * @return
	 */
	private String respFourMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.FOUR_RESP_CONTENT, reqMessage);
	}

	/**
	 * 回复消息5
	 * @param reqMessage
	 * @return
	 */
	private String respFiveMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.FIVE_RESP_CONTENT, reqMessage);
	}

	/**
	 * 回复消息6
	 * @param reqMessage
	 * @return
	 */
	private String respSixMessage(WxReqBaseMessageVo reqMessage) {
		return WxUtils.createTextMessage(WxConstant.SIX_RESP_CONTENT, reqMessage);
	}
	
	/**
	 * 回复其他消息
	 * @param reqMessage
	 * @return
	 */
	private String respOtherMsg(WxReqBaseMessageVo reqMessage) {
//		return WxUtils.createTextMessage(WxConstant.OTHER_RESP_CONTENT, reqMessage);
		
		// 修改为回复客服类型消息  author:aizhengdong 2015年9月21日
		return WxUtils.createCustomerServiceMessage(reqMessage);
	}
	
	/**
	 * 下载图片文件
	 *
	 * @param mediaId
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月3日
	 */
	private void downloadImageMedia(String mediaId) {
		String accessToken = WxUtils.getAccessToken();
		if (accessToken != null) {
			String url = WxConstant.DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
//			WxUtils.downloadFile(url, "d:/" + mediaId + ".png");
		}
	}
	
	/**
	 * 下载语音文件
	 * 
	 * @param mediaId
	 * @param format
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月3日
	 */
	private void downloadVoiceMedia(String mediaId, String format) {
		String accessToken = WxUtils.getAccessToken();
		if (accessToken != null) {
			String url = WxConstant.DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
//			WxUtils.downloadFile(url, "d:/" + mediaId + "." + format);
		}
	}
	
	/**
	 * 下载视频文件
	 * 
	 * @param mediaId
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月3日
	 */
	private void downloadVideoMedia(String mediaId, String thumbMediaId) {
		String accessToken = WxUtils.getAccessToken();
		if (accessToken != null) {
			// 下载视频文件
			String url = WxConstant.DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
//			WxUtils.downloadFile(url, "d:/" + mediaId + ".mp4");
			
			// 下载视频消息缩略图文件
			String thumbUrl = WxConstant.DOWNLOAD_MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", thumbMediaId);
//			WxUtils.downloadFile(thumbUrl, "d:/" + mediaId + ".png");
		}
	}
	
	/**
	 * 获取用户基本信息
	 * 
	 * @param openId
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月3日
	 */
	private void getUserInfo(String openId) {
		String accessToken = WxUtils.getAccessToken();
		if (accessToken != null) {
			String url = WxConstant.USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
			JSONObject jsonObject = WxUtils.httpRequest(url, "GET", null);
			if(jsonObject != null && jsonObject.containsKey("nickname")){
				String userName = jsonObject.getString("nickname");
			}
		}
	}
	
}
