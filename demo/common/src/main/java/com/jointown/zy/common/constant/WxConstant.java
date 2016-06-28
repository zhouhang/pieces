package com.jointown.zy.common.constant;

import java.util.Date;

import com.jointown.zy.common.util.TimeUtil;

/**
 * 微信公众平台开发--常量类
 * 
 * @author aizhengdong,lichenxiao 2015年8月9日
 *
 * @data 2015年2月5日
 */
public class WxConstant {
	/** start 微信系统配置相关 ******************************************************************************/
	/** AppID(应用ID) */
	public static final String APPID = MessageConstant.RESOURCE_APPID_WX.getValue();

	/** AppSecret(应用密钥)*/
	public static final String APPSECRET = MessageConstant.RESOURCE_APPSECRET_WX.getValue();

	/** Token */
	public static final String TOKEN = MessageConstant.RESOURCE_TOKEN_WX.getValue();

	/** URL：手动创建菜单 */
	public static final String MENU_URL = "admin/createMenu?source=jztwxadmin";
	/** URL：创建菜单，限1000次/日 */
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/** URL：获取access_token的接口地址， 限2000 次/日 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/** URL：获取jsapi_ticket的接口地址  author:aizhengdong 2015.10.12 */
	public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/** URL：下载多媒体临时素材 */
	public static final String DOWNLOAD_MEDIA_URL = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	/** URL：获取用户基本信息 */
	public static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/** URL：域名 */
	public static final String HOST = MessageConstant.RESOURCE_WWW_WX
			.getValue() + "/";
	/** URL：图文消息中第一条消息的默认图片 */
	public static final String DEFAULT_PIC_URL = MessageConstant.RESOURCE_IMG_WX
			.getValue() + "/images/zyc_logo.png";
	/** 请求消息类型：文本消息 */
	public static final String TEXT_REQ_MESSAGE_TYPE = "text";
	/** 请求消息类型：图片消息 */
	public static final String IMAGE_REQ_MESSAGE_TYPE = "image";
	/** 请求消息类型：语音消息 */
	public static final String VOICE_REQ_MESSAGE_TYPE = "voice";
	/** 请求消息类型：小视频消息 */
	public static final String SHORTVIDEO_REQ_MESSAGE_TYPE = "shortvideo";
	/** 请求消息类型：地理位置消息 */
	public static final String LOCATION_REQ_MESSAGE_TYPE = "location";
	/** 请求消息类型：链接消息 */
	public static final String LINK_REQ_MESSAGE_TYPE = "link";
	/** 请求消息类型：事件推送 */
	public static final String EVENT_REQ_MESSAGE_TYPE = "event";
	/** 返回消息类型：文本消息 */
	public static final String TEXT_RESP_MESSAGE_TYPE = "text";
	/** 返回消息类型：图文消息 */
	public static final String NEWS_RESP_MESSAGE_TYPE = "news";
	/** 返回消息类型：客服消息  author:aizhengdong 2015年9月21日 */
	public static final String CUSTOMER_SERVICE_RESP_MESSAGE_TYPE = "transfer_customer_service";
	/** 事件类型：关注 */
	public static final String SUBSCRIBE_EVENT_TYPE = "subscribe";
	/** 事件类型：菜单点击 */
	public static final String CLICK_EVENT_TYPE = "CLICK";
	/** 菜单的响应动作类型：点击推事件 */
	public static final String CLICK_MENU_TYPE = "click";
	/** 菜单的响应动作类型：跳转URL */
	public static final String VIEW_MENU_TYPE = "view";
	/** 返回结果信息：请求成功 */
	public static final String SUC_ERRMSG = "请求成功";
	/** 返回结果信息：access_token 获取失败 */
	public static final String ACCESS_TOKEN_ERRMSG = "access_token 获取失败";
	/** 返回结果信息 ：请求失败 */
	public static final String REQ_FAIL_ERRMSG = "请求失败";
	/** end 微信系统配置相关 ******************************************************************************/

	/** start 珍药材相关 **********************************************************************************/

	/** 占位符文本：供求信息状态之审核通过 */
	public static final short PASS_SUPPLY_STATUSA = 1;

	/** 占位符文本：供求信息状态之审核未通过 */
	public static final short REFUSE_SUPPLY_STATUSA = 2;

	/** URL地址start ***************************************************************/
	/** 占位符文本：药材名称 author:aizhengdong */
	public static final String YC_NAME_TEXT = "YC_NAME";
	/** 占位符文本：价格 author:aizhengdong */
	public static final String PRICE_TEXT = "PRICE";
	/** 占位符文本：商家总数 author:aizhengdong */
	public static final String SELLER_COUNT_TEXT = "SELLER_COUNT";
	/** 占位符文本：供应信息总数 author:aizhengdong */
	public static final String SUPPLY_COUNT_TEXT = "SUPPLY_COUNT";

	/** "我要去看看"图片链接 */
	public static final String TEMP_LOOK_PIC_URL = "https://mmbiz.qlogo.cn/mmbiz/iaCkTBO1vySiaHibDakqrI2JS2sYPpJfEEKZYa8W4EqgAUDxwfcbp3HlI4Q4D01ET3R1cMzDZmouLemLRz3MxtjMA/0?wx_fmt=jpeg";
	/** "我要去看看"链接 author:lichenxiao 2015年6月 */
	public static final String TEMP_LOOK_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=213457114&idx=1&sn=67405327cda431d50180211b5df170fd#rd";
	/** "我要去看看"标题 author:lichenxiao 2015年6月 */
	public static final String TEMP_LOOK_TITLE = "【独家访谈】中药材行业专家👉戴伟 ";
	/** "我要去看看"描述 author:lichenxiao 2015年6月 */
	public static final String TEMP_LOOK_DESCRIPTION = "他是国内第一批中药材电子商务事业的拓荒者，深耕中药行业二十余年，小编独家访问「戴伟」";
	
	/** 发送1 药材价格查询 "平台简介" 图片链接 author:lichenxiao 2015年6月 */
	public static final String TEMP_BRIEF_PIC_URL = "https://mmbiz.qlogo.cn/mmbiz/iaCkTBO1vyShQT6r5AcOHZMjm7CLX2hniabCO5qKbSu8lemVT9xvZn6pWNYTqSrHfJMbaEeS5O5Bb7xSdRo4O22A/0?wx_fmt=jpeg";
	/** 发送1 药材价格查询 "平台简介" 链接 author:lichenxiao 2015年6月 */
	public static final String TEMP_BRIEF_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=212976221&idx=1&sn=ebc947d903a4f60cd2474d963ca5e300#rd";
	/** 发送1 药材价格查询 "平台简介" 标题 author:lichenxiao 2015年6月 */
	public static final String TEMP_BRIEF_TITLE = "关于我们 ";
	/** 发送1 药材价格查询 "平台简介" 描述 author:lichenxiao 2015年6月 */
	public static final String TEMP_BRIEF_DESCRIPTION = "珍药材 是九州通中药材电子商务有限公司打造的有质量保障体系的中药材电子商务第三方综合服务平台";
	
	
	/**  2015.9.18 活动添加   author:aizhengdong 2015.9.18 start */
	private static final String PIC_URL = "http://img.54315.com/wx/images/mp/zyc_logo.jpg";
	
	/** "保护和发展" 标题 */
	public static final String BHFZ_TITLE = "中药材保护和发展规划（2015—2020年）";
	/** "保护和发展" 描述  */
	public static final String BHFZ_DESCRIPTION = "中药材保护和发展规划（2015—2020年）工业和信息化部　中医药局　发展改革委　科技部财政部　环境保护";
	/** "保护和发展" 图片链接 */
	public static final String BHFZ_PIC_URL = PIC_URL;
	/** "保护和发展" 链接 */
	public static final String BHFZ_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215483096&idx=1&sn=8f57a238e6d48c196806b2d89ccfac27&scene=0&key=dffc561732c22651f78e22910c17dbec6021d2f0e9c0b1a1ff2bbe03da059dc5028b306581d5f65f6103ca45aeb5ead5&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "解读保护和发展" 标题 */
	public static final String JDBHFZ_TITLE = "两部门解读中药材保护和发展规划（2015-2020年）";
	/** "解读保护和发展" 描述  */
	public static final String JDBHFZ_DESCRIPTION = "2015-05-19文章来源： 中央政府门户网站  日前，国务院办公厅转发工业和信息化部、国家中医药管理局等";
	/** "解读保护和发展" 图片链接 */
	public static final String JDBHFZ_PIC_URL = PIC_URL;
	/** "解读保护和发展" 链接 */
	public static final String JDBHFZ_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215484075&idx=1&sn=9c1c856bc9dce312f2ad995eb1dd558a&scene=0&key=dffc561732c22651a7f0f3f49f62ff3da1528774ab2e2aa3ce63585b4f25e72a0cc71cc3de681f38bd8f3e165d1c07ed&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "现代物流" 标题 */
	public static final String XDWL_TITLE = "商务部办公厅关于加快推进中药材现代物流体系建设指导意见的通知";
	/** "现代物流" 描述  */
	public static final String XDWL_DESCRIPTION = "商办秩函[2014]809号  中药材是中医药事业传承和发展的物质基础，中药材物流是我国药品流通的重要组成部分。";
	/** "现代物流" 图片链接 */
	public static final String XDWL_PIC_URL = PIC_URL;
	/** "现代物流" 链接 */
	public static final String XDWL_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215484233&idx=1&sn=fb10f48009aab01e26f528320c0f1b7c&scene=0&key=dffc561732c22651ef63a961e3b63a871cec5e4538af4001c4d3c2a3cae95b736c6afe5c8d140d7eeb90856a3ab9dc74&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "现代物流解读" 标题 */
	public static final String XDWLJD_TITLE = "商务部秩序司负责人就《关于加快推进中药材现代物流体系建设指导意见的通知》进行解读";
	/** "现代物流解读" 描述  */
	public static final String XDWLJD_DESCRIPTION = "2015-03-13   商务部市场秩序司2015年1月，商务部办公厅印发了《关于加快推进中药材现代物流体系";
	/** "现代物流解读" 图片链接 */
	public static final String XDWLJD_PIC_URL = PIC_URL;
	/** "现代物流解读" 链接 */
	public static final String XDWLJD_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215484481&idx=1&sn=c6003cc4f0f960d76d98fea8971fd237&scene=0&key=dffc561732c2265196bf5f40d1bc7c79ea51aa87ff092e6e53d1e961b6ea5b386fde0f99310cbab47b5957d0b25199e3&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "中医药健康服务" 标题 */
	public static final String ZYYJKFW_TITLE = "中医药健康服务发展规划（2015—2020年）";
	/** "中医药健康服务" 描述  */
	public static final String ZYYJKFW_DESCRIPTION = "中医药（含民族医药）强调整体把握健康状态，注重个体化，突出治未病，临床疗效确切，治疗方式灵活";
	/** "中医药健康服务" 图片链接 */
	public static final String ZYYJKFW_PIC_URL = PIC_URL;
	/** "中医药健康服务" 链接 */
	public static final String ZYYJKFW_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215484968&idx=1&sn=19e6b7fe9110813d9a6cb7b4d0958e4c&scene=0&key=dffc561732c22651b82f8c84ff0c3315055476e9a3b141712d02839e6345b9503651d65ef388929c333c6142c786143b&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "重点品种流通分析" 标题 */
	public static final String ZDPZLTFX_TITLE = "商务部发布2014年中药材重点品种流通分析报告";
	/** "重点品种流通分析" 描述  */
	public static final String ZDPZLTFX_DESCRIPTION = "2015-08-07    商务部近日，商务部发布了《2014年中药材重点品种流通分析报告》。《报告》对纳入";
	/** "重点品种流通分析" 图片链接 */
	public static final String ZDPZLTFX_PIC_URL = PIC_URL;
	/** "重点品种流通分析" 链接 */
	public static final String ZDPZLTFX_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215490555&idx=1&sn=e66df42cbbbbf77a5133629289ba57f1&scene=0&key=dffc561732c22651edf5352d6322572be101e8384de6f04bb4a6330db0fdc0f64f4ae352f3038a78d19576a65017e748&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "现状及发展趋势" 标题 */
	public static final String XZFZQS_TITLE = "2015年中药饮片行业现状及发展趋势分析";
	/** "现状及发展趋势" 描述  */
	public static final String XZFZQS_DESCRIPTION = "2015-04-28   中国报告大厅中药饮片是中国中药产业的三大支柱之一，是中医临床辨证施治必需的传统武器";
	/** "现状及发展趋势" 图片链接 */
	public static final String XZFZQS_PIC_URL = PIC_URL;
	/** "现状及发展趋势" 链接 */
	public static final String XZFZQS_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215490619&idx=1&sn=79fb55bfe5506f712455fd1166f85105&scene=0&key=dffc561732c226519996b569ac97e3d1a1f4e9d59aeafa1eefd635c91508fbae29dbe0838f40160bb0c7c52f847e7169&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告一" 标题 */
	public static final String FZBG1_TITLE = "《中国中药资源发展报告》（2015年）（节选一：中药资源是国家战略资源）";
	/** "发展报告一" 描述  */
	public static final String FZBG1_DESCRIPTION = "中医药（含民族医药）强调整体把握健康状态，注重个体化，突出治未病，临床疗效确切，治疗方式灵活，";
	/** "发展报告一" 图片链接 */
	public static final String FZBG1_PIC_URL = PIC_URL;
	/** "发展报告一" 链接 */
	public static final String FZBG1_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215490911&idx=1&sn=a1e6d25e027d4b196cf8b890f7317306&scene=0&key=dffc561732c226519b905409bb6e173779b99ce5fc28ae2fcca25bd78338b5c79ae35225e4dd09a4d002250fdc20c664&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告二" 标题 */
	public static final String FZBG2_TITLE = "《中国中药资源发展报告》（2015年）（节选二：现状之中药资源普查）";
	/** "发展报告二" 描述  */
	public static final String FZBG2_DESCRIPTION = "编者按        守住百草，方能杏林芬芳。为了探索中药资源管理机制、建设中药材生产示范基础设施、研究解决";
	/** "发展报告二" 图片链接 */
	public static final String FZBG2_PIC_URL = PIC_URL;
	/** "发展报告二" 链接 */
	public static final String FZBG2_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215491178&idx=1&sn=a25cadb1fc08d9baf56667aaf30c5df7&scene=0&key=dffc561732c2265126fbdcbd67a01af6319fc105ad67f13c0fad451b2e1e3a35b68ffcf546a03c1f89ab64341782ea97&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告三" 标题 */
	public static final String FZBG3_TITLE = "《中国中药资源发展报告》（2015年）（节选三：现状之中药材的质量与安全）";
	/** "发展报告三" 描述  */
	public static final String FZBG3_DESCRIPTION = "编者按    中药材是加工中药饮片和生产中成药的原料，其品种的真伪、质量的优劣直接影响到临床用药的安全与有效";
	/** "发展报告三" 图片链接 */
	public static final String FZBG3_PIC_URL = PIC_URL;
	/** "发展报告三" 链接 */
	public static final String FZBG3_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215491364&idx=1&sn=df00de71e218c483e402e4ae3a7743c0&scene=0&key=dffc561732c22651e2807a77db872994964dd4f065a2444ce7a91a8a3e5ea7671c8518943223d340743270c25f6f2c23&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告四" 标题 */
	public static final String FZBG4_TITLE = "《中国中药资源发展报告》（2015年）（节选四：现状之中药材价格剧烈波动的成因）";
	/** "发展报告四" 描述  */
	public static final String FZBG4_DESCRIPTION = "编者按   近年来，中药材价格跌宕起伏备受关注。中国中药协会发布的数据显示，2007年至2014年，中药材价";
	/** "发展报告四" 图片链接 */
	public static final String FZBG4_PIC_URL = PIC_URL;
	/** "发展报告四" 链接 */
	public static final String FZBG4_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215491556&idx=1&sn=f71b0ff73144d8c9669d9959423c1ed4&scene=0&key=dffc561732c22651494ba53fa95bc13b55d8c13d6a2a8294a3a0e5dd1968203356a006fc43b09b9205a911a78c05fa4f&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告五" 标题 */
	public static final String FZBG5_TITLE = "《中国中药资源发展报告》（2015年）（节选五：现状之中药材市场、国际贸易及产业）";
	/** "发展报告五" 描述  */
	public static final String FZBG5_DESCRIPTION = "编者按随着中医药标准化、信息化战略的快速推进，我国中药材贸易有望告别“看货议价、现货现金”的传统交易模式，加";
	/** "发展报告五" 图片链接 */
	public static final String FZBG5_PIC_URL = PIC_URL;
	/** "发展报告五" 链接 */
	public static final String FZBG5_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215491981&idx=1&sn=52ff5b7f1e9e244576da834cb77a6157&scene=0&key=dffc561732c22651af074e3ca73adc1951db708952d448430bc893a51fcef39f886a43648764dd65cab793493d7ed0ad&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "发展报告六" 标题 */
	public static final String FZBG6_TITLE = "《中国中药资源发展报告》（2015年）（节选六：机遇与挑战）";
	/** "发展报告六" 描述  */
	public static final String FZBG6_DESCRIPTION = "编者按中药产业一直以来都是我国传统优势产业，有着悠久的发展历史，是中华民族的瑰宝。多年来，中药都以其产量多、";
	/** "发展报告六" 图片链接 */
	public static final String FZBG6_PIC_URL = PIC_URL;
	/** "发展报告六" 链接 */
	public static final String FZBG6_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215492144&idx=1&sn=629d96ef953a37633943f8ef216d68ce&scene=0&key=dffc561732c226511efa94de596b3ca7d89082d9d28d530f6f598ad93671588e08b78bd8662a622f6aafd59b433106eb&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/** "大势与前景" 标题 */
	public static final String DSQJ_TITLE = "中国中医药报通报我国中药产业的大势与前景";
	/** "大势与前景" 描述  */
	public static final String DSQJ_DESCRIPTION = "2015/9/6    作者：中国医科学院        看病贵、看病难是一个世界性难题。我国人均医疗费用约";
	/** "大势与前景" 图片链接 */
	public static final String DSQJ_PIC_URL = PIC_URL;
	/** "大势与前景" 链接 */
	public static final String DSQJ_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=215490750&idx=1&sn=576856436cabc6c1ddabdee07f8f1313&scene=0&key=dffc561732c226512ab0bd1a445bbecbb5272ddcaec2b32f67e20b8c8cfa57fca4635e37e44c6207a05e01009ee1b2dd&ascene=1&uin=MTcwNTY2ODM1&devicetype=Windows+7&version=6102002a&pass_ticket=DfxR%2F1sSp%2Bf9odPW1VoPuwsDMTyiUm6%2FGrPOs%2F5oXW8%3D";
	
	/**  2015.9.18 活动添加  author:aizhengdong 2015.9.18 end */
	
	
	/** URL：手指海报 */
	public static final String ZYDS_URL = "http://eqxiu.com/s/aGwmS0?eqrcode=1&from=groupmessage&isappinstalled=0";
	/** URL：供应信息“查看详情” author:lichenxiao 2015年6月 */
	public static final String SUPPLY_DETAIL_URL = WxConstant.HOST
			+ "supply_info?type=supply&yc=YC_NAME";
	/** URL：发布求购信息 author:lichenxiao 2015年6月 */
	public static final String RELEASE_URL = WxConstant.HOST
			+ "supplySendAuthentication#demand";
	/** URL:发布供应信息 author:lichenxiao */
	public static final String SUPPLY_SEND_URL = WxConstant.HOST
			+ "wxSupplySend/supplySendAuthentication#supply";
	/** URL:供求信息查询 author:lichenxiao */
	public static final String SUPPLY_URL = WxConstant.HOST + "supply_info";
	/** URL:珍药网微信入驻页面 author:lichenxiao 2015年6月 */
	public static final String REGIST_URL = "http://mp.weixin.qq.com/s?__biz=MzA3NDg4ODc5Ng==&mid=211024079&idx=1&sn=cd19b09e911227d2546f378494adffbf#rd";
	/** URL:诚聘英才 author lichenxiao 2015年8月28日 */
	public static final String RECRUIT_URL = "http://x.eqxiu.com/s/ftjZtoME";
	/** URL:咨询客服 author lichenxiao 2015年8月9日 */
	public static final String SERVICE_URL = "http://meiqia.com/chat/55dedd304eae351c2b000013";
	/** URL:我要提建议 author lichenxiao 2015年8月9日 */
	public static final String FEEDBACK_URL = WxConstant.HOST + "feedback";
	/** URL：新手指引 author:aizhengdong 2015年7月10日 */
	public static final String NEW_HAND_URL = WxConstant.HOST + "newHand";
	/** URL:品种行情 author:lichenxiao 2015年8月9日 */
	public static final String MENU_BREED_PRICE_URL = WxConstant.HOST
			+ "breedPrice";
	/** URL:我的小珍 author:lichenxiao 2015年8月9日 */
	public static final String MENU_MY_ZYC_URL = WxConstant.HOST + "myzyc";
	/** URL地址end *****************************************************************/

	
	/** 微信分享标题  author:aizhengdong 2015.10.13 */
	public static final String WXSHARE_TITLE = "【珍药材】供求信息";
	
	/** 微信描述  author:aizhengdong 2015.10.13 */
	public static final String WXSHARE_DESC = "中国首创有质量保障的中药材现货交易综合服务平台，您药材生意的好帮手！";
	
	/** 微信分享图标路径  author:aizhengdong 2015.10.13 */
	public static final String WXSHARE_IMGURL = MessageConstant.RESOURCE_IMG_WX.getValue() + "/images/mp/LOGO_160X160.jpg";
	
	/** 微信分享链接  author:aizhengdong 2015.10.13 */
	public static final String WXSHARE_LINK = WxConstant.HOST + "supply_info";
	
	
	/** 文本内容start ***************************************************************/
	/** 文本内容：默认回复 */
	public static final String DEFAULT_RESP_CONTENT = new StringBuffer()
	/** 文本内容：药博会活动默认回复
	.append("非你不可，就等你来!\n")
	.append("欢迎与相关负责人联系合作/合伙事宜!\n")
	.append("-------------------\n")
	.append("公司CEO：\n朱志国  13517278182\n")
	.append("业务副总：\n张逢祥  13986125987\n")
	.append("电商公司副总：\n戴伟  13966515000\n")
	.append("药材采购总监：\n彭春玲  18971539608\n")
	.append("药材营销总监：\n高杰  15972106068\n")
	.append("饮片采购总监：\n周利华  13971625785\n")
	.append("饮片营销总监：\n程修真  18616965662\n")
	.append("药材基地总监：\n吴卫刚  13419665081\n")
	.append("医院业务总监：\n江克海  18802771833\n")
	.append("投资合作总监：\n李斌  18827421950\n")
	.append("人力资源总监：\n贺华文  13886085137\n\n")
	.append("更多信息请点击 \nhttp://www.54315.com\n")
	.toString();
	*/
			.append("珍药材——九州通医药集团旗下中国最大的中药材现货交易平台😘亲关注对了!\n").append("--------------\n")
			.append("回复【1】查询药材最新价格；\n").append("回复【2】查看平台简介；\n")
			.append("回复【3】查看专家分析；\n").append("回复【4】查看服务方式；\n")
			.append("回复【5】查看趣味段子；\n").append("回复【6】查看历史资讯；\n\n")
			.append("更多信息请点击 \nhttp://www.54315.com\n")
			.append("买卖药材，快找小珍客服电话：4001054315\n")
			.append("--------------\n")
			.append("欢迎参加“2015中华中医药学会年会”的各位嘉宾，会议室wifi密码：fy123456。感谢您的关注，祝您参会愉快！\n")
			.toString();

	/** 文本内容：平时默认回复
	 * .append("珍药材，中国最大的中药材现货交易平台😘亲关注对了!\n").append("--------------\n")
			.append("回复【1】查询药材最新价格；\n").append("回复【2】查看平台简介；\n")
			.append("回复【3】查看专家分析；\n").append("回复【4】查看服务方式；\n")
			.append("回复【5】查看趣味段子；\n").append("回复【6】查看历史资讯；\n\n")
			.append("更多信息请点击 \nhttp://www.54315.com\n")
			.append("买卖药材，快找小珍客服电话：4001054315\n").toString();
     */
	
	
	
	/** 文本内容:发送zmkm 微信后台系统入口 */
	public static final String WXSYSTEM_RESP_CONTENT = new StringBuffer()
			.append("<a href='http://zycwx.54315.com/WxBoss/wxSystem'>")
			.append(">>点我进入微信后台系统入口</a>").append("\n").toString();
	/** 文本内容:发送1 药材价格查询 */
	public static final String ONE_RESP_CONTENT = new StringBuffer()
			.append("您好，价格查询：\n\n")
			.append("1.编辑“买”+“药材名称”，例如编辑“买枸杞”，点击发送，就能查询枸杞的当前价格和供应信息。\n")
			.append("2.编辑“卖”+“药材名称”，点击发送，您就可以把您经营的商品添加到珍药材平台。").toString();
	/** 文本内容:发送3 专家分析 */
	public static final String THREE_RESP_CONTENT = new StringBuffer()
			.append("<a href='http://zycwx.54315.com/marketNews'>")
			.append(">>点我浏览专家分析</a>").append("\n").toString();
	/** 文本内容:发送4 服务方式 */
	public static final String FOUR_RESP_CONTENT = new StringBuffer()
			.append("<a href='http://eqxiu.com/s/aL9oVN'>")
			.append(">>点我浏览服务方式</a>").append("\n").toString();
	/** 文本内容:发送5 趣味段子 */
	public static final String FIVE_RESP_CONTENT = new StringBuffer()
			.append("老公：媳妇，饿不？我给你煮碗面啊？\n").append("老婆：不用。\n")
			.append("老公：那我去给你洗点水果吧？\n").append("老婆：不用。\n")
			.append("老公：那我去洗洗衣服拖拖地吧？\n").append("老婆：不用……\n")
			.append("老公：媳妇，那你说我干点啥呢？\n")
			.append("老婆：你就消停在那跪到天亮！看你还敢不敢自作主张做平台了！告诉你多少次了，卖药材一定要上珍药材，记住了吗？\n")
			.append("老公：记住了！\n").append("老婆：平台地址是多少！\n").append("老公：\n")
			.append("http://www.54315.com/").toString();
	/** 文本内容:发送6历史资讯 */
	public static final String SIX_RESP_CONTENT = new StringBuffer()
			.append("<a href='http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA3NDg4ODc5Ng==#wechat_webview_type=1&wechat_redirect'>")
			.append(">>点我浏览历史资讯</a>").append("\n").toString();
	/** 文本内容：多次回复 */
	public static final String OTHER_RESP_CONTENT = new StringBuffer()
			.append("您好，您的信息我们已经收到，客服小珍稍后会回复您。\n").append("\n")
			.append("您也可按以下方式自助查询：\n")
			.append("1.编辑“买”+“药材名称”，例如编辑“买枸杞”，点击发送；\n")
			.append("2.编辑“卖”+“药材名称”，点击发送；\n").append("3.编辑“我要去看看”，点击发送。\n")
			.toString();
	
	
	
	/** 文本内容：今日价格 -> 价格信息 */
	public static final String PRICE_CONTENT = new StringBuffer()
			.append(WxConstant.YC_NAME_TEXT).append("的当前价格是：")
			.append(WxConstant.PRICE_TEXT).append("\n\n").toString();
	/** 文本内容：今日价格 -> 供应信息 */
	public static final String SUPPLY_CONTENT = new StringBuffer()
			.append("共有大约").append(WxConstant.SELLER_COUNT_TEXT)
			.append("个商家的在销售").append(WxConstant.YC_NAME_TEXT).append("，共有")
			.append(WxConstant.SUPPLY_COUNT_TEXT).append("个供应信息\n\n")
			.append("<a href=\"").append(WxConstant.SUPPLY_DETAIL_URL)
			.append("\">点击查看</a>").toString();
	/** 文本内容：今日价格 -> 发布供应信息超链接 */
	public static final String RELEASE_CONTENT = new StringBuffer()
			.append("<a href=\"").append(WxConstant.RELEASE_URL)
			.append("\">立即发布求购信息</a>").toString();
	/** 文本内容：今日价格 -> 有供应信息有价格 */
	public static final String TODAY_PRICE_1_CONTENT = new StringBuffer()
			.append(WxConstant.PRICE_CONTENT).append(WxConstant.SUPPLY_CONTENT)
			.toString();
	/** 文本内容：今日价格 -> 有供应信息无价格 */
	public static final String TODAY_PRICE_2_CONTENT = new StringBuffer()
			.append(WxConstant.YC_NAME_TEXT)
			.append("的当前还没有指导价格，但是当前我们搜集到以下信息：\n\n")
			.append(WxConstant.SUPPLY_CONTENT).toString();
	/** 文本内容：今日价格 -> 无供应信息有价格 */
	public static final String TODAY_PRICE_3_CONTENT = new StringBuffer()
			.append(WxConstant.PRICE_CONTENT).append("市场上还没有")
			.append(WxConstant.YC_NAME_TEXT).append("的供应信息，这是一个大商机哦\n\n")
			.append(WxConstant.RELEASE_CONTENT).toString();
	/** 文本内容：今日价格 -> 无供应信息无价格 */
	public static final String TODAY_PRICE_4_CONTENT = new StringBuffer()
			.append("整个市场都没有找到").append(WxConstant.YC_NAME_TEXT)
			.append("的价格和供求信息，这是一个神秘品种，是一个大的商机\n\n")
			.append(WxConstant.RELEASE_CONTENT).toString();
	/** 文本内容end ***************************************************************/

	
	/** 邮件设置 start ************************************************************/
	/**
	 * 意见处理的邮件主题
	 * 
	 * @author aizhengdong 2015年7月20日
	 */
	public static final String EMAIL_SUBJECT_OPINION = "珍药材电商-意见处理";
	/**
	 * 供应信息审核的邮件主题
	 * 
	 * @author aizhengdong 2015年7月20日
	 */
	public static final String EMAIL_SUBJECT_SUPPLY = "珍药材电商-供应信息审核";
	/**
	 * 求购信息审核的邮件主题
	 * 
	 * @author aizhengdong 2015年7月20日
	 */
	public static final String EMAIL_SUBJECT_DEMAND = "珍药材电商-求购信息审核";

	/**
	 * 封装意见处理的邮件内容
	 *
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @date 2015年7月20日
	 */
	public static String getOpinionEmailContent(String mobile) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("亲爱的管理员，你好！<p>")
				.append("　　客户通过小珍微信号提出了宝贵意见，联系方式为：")
				.append(mobile)
				.append("。请在1个工作日内回访，谢谢！")
				.append("<p>")
				.append("　　【注意】：本邮件为系统自动发送，不必回复！")
				.append("<p>")
				.append("　　此致，")
				.append("<p>")
				.append("　　　　中药材电商团队")
				.append("<p>")
				.append("　　　　")
				.append(TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyy年MM月dd日HH:mm:ss"));

		return stringBuilder.toString();
	}

	/**
	 * 封装新增供应信息的邮件内容
	 *
	 * @param userName
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @date 2015年7月20日
	 */
	public static String getAddSupplyEmailContent(String userName, String mobile) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("亲爱的管理员，你好！<p>")
				.append("　　客户通过小珍微信号发布了供应信息，用户名为：")
				.append(userName)
				.append("；联系方式为：")
				.append(mobile)
				.append("。请在1个工作日内处理，谢谢！")
				.append("<p>")
				.append("　　【注意】：本邮件为系统自动发送，不必回复！")
				.append("<p>")
				.append("　　此致，")
				.append("<p>")
				.append("　　　　中药材电商团队")
				.append("<p>")
				.append("　　　　")
				.append(TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyy年MM月dd日HH:mm:ss"));

		return stringBuilder.toString();
	}

	/**
	 * 封装修改供应信息的邮件内容
	 *
	 * @param breedName
	 * @param userName
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @date 2015年7月20日
	 */
	public static String getUpdateSupplyEmailContent(String breedName,
			String userName, String mobile) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("亲爱的管理员，你好！<p>")
				.append("　　客户通过小珍微信号修改了供应信息，品种名称为：")
				.append(breedName)
				.append("；用户名为：")
				.append(userName)
				.append("；联系方式为：")
				.append(mobile)
				.append("。请在1个工作日内处理，谢谢！")
				.append("<p>")
				.append("　　【注意】：本邮件为系统自动发送，不必回复！")
				.append("<p>")
				.append("　　此致，")
				.append("<p>")
				.append("　　　　中药材电商团队")
				.append("<p>")
				.append("　　　　")
				.append(TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyy年MM月dd日HH:mm:ss"));

		return stringBuilder.toString();
	}

	/**
	 * 封装新增求购信息的邮件内容
	 *
	 * @param userName
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @date 2015年7月20日
	 */
	public static String getAddDemandEmailContent(String userName, String mobile) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("亲爱的管理员，你好！<p>")
				.append("　　客户通过小珍微信号发布了求购信息，用户名为：")
				.append(userName)
				.append("；联系方式为：")
				.append(mobile)
				.append("。请在1个工作日内处理，谢谢！")
				.append("<p>")
				.append("　　【注意】：本邮件为系统自动发送，不必回复！")
				.append("<p>")
				.append("　　此致，")
				.append("<p>")
				.append("　　　　中药材电商团队")
				.append("<p>")
				.append("　　　　")
				.append(TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyy年MM月dd日HH:mm:ss"));

		return stringBuilder.toString();
	}

	/**
	 * 封装修改求购信息的邮件内容
	 *
	 * @param breedName
	 * @param userName
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @date 2015年7月20日
	 */
	public static String getUpdateDemandEmailContent(String breedName,
			String userName, String mobile) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("亲爱的管理员，你好！<p>")
				.append("　　客户通过小珍微信号修改了求购信息，品种名称为：")
				.append(breedName)
				.append("；用户名为：")
				.append(userName)
				.append("；联系方式为：")
				.append(mobile)
				.append("。请在1个工作日内处理，谢谢！")
				.append("<p>")
				.append("　　【注意】：本邮件为系统自动发送，不必回复！")
				.append("<p>")
				.append("　　此致，")
				.append("<p>")
				.append("　　　　中药材电商团队")
				.append("<p>")
				.append("　　　　")
				.append(TimeUtil.getTimeShowByTimePartten(new Date(),
						"yyyy年MM月dd日HH:mm:ss"));

		return stringBuilder.toString();
	}

	/** 邮件设置 end ************************************************************/
	/** end 珍药材相关 **********************************************************************************/

	/** start 东方中药材网相关 ******************************************************************************/
	/** URL:东方中药材网微信入驻页面 */
	public static final String EAST_REGIST_URL = "http://mp.weixin.qq.com/s?__biz=MzAxODE3MjM1OA==&mid=205234989&idx=1&sn=d6476389262f4eb7aeb1334d623cc377#rd";
	/** URL：分析报告 */
	public static final String ANALYSIS_REPORT_URL = WxConstant.HOST
			+ "analysis?lmid=1";
	/** URL：药材百科 */
	public static final String ENCYCLOPEDIA_URL = WxConstant.HOST
			+ "getEastYaocaiPzs";
	/** URL：药材价格 */
	public static final String BREED_PRICE_URL = WxConstant.HOST
			+ "ycprice?type=today";
	/** URL：TOP10 */
	public static final String TOP10_URL = WxConstant.HOST + "top10";
	/** URL：行情快讯 */
	public static final String PRICE_NEWS_URL = WxConstant.HOST
			+ "eastArticleNews";
	/** URL:行业行文和市场动态详情 */
	public static final String ARTICLE_URL = WxConstant.HOST
			+ "articleDetail?acid=";
	/** URL：行业新闻图文消息中第一条消息的图片 */
	public static final String HY_NEWS_PIC_URL = MessageConstant.RESOURCE_IMG_WX
			.getValue() + "/images/hyxw.jpg";
	/** URL：市场动态图文消息中第一条消息的图片 */
	public static final String SCDT_PIC_URL = MessageConstant.RESOURCE_IMG_WX
			.getValue() + "/images/scdt.jpg";
	/** 页长：今日价格和历史价格每页显示数量 */
	public static final int LENGTH_PRICE_PAGESIZE = 5;
	/** 长度：摘要长度 */
	public static final int LENGTH_ABSTRACT = 102;
	/** 文章类型：品种分析 lmid=1 */
	public static final int PZ_ANALYSIS_TYPE = 1;
	/** 文章类型：市场动态 lmid=2 */
	public static final int SCDT_ARTICLE_TYPE = 2;
	/** 文章类型：产地快讯 lmid=7 */
	public static final int PLACE_ARTICLE_TYPE = 7;
	/** 文章类型：研究报告 lmid=9 */
	public static final int RESEARCH_PAPER_TYPE = 9;
	/** 文章类型：行业新闻 lmid=11 */
	public static final int HY_NEWS_ARTICLE_TYPE = 11;
	/** 菜单KEY值：市场动态 */
	public static final String SCDT_MENU_KEY = "jzt_scdt";
	/** 菜单KEY值：行业新闻 */
	public static final String HY_NEWS_MENU_KEY = "jzt_hy_news";
	/** end 东方中药材网相关 ********************************************************************************/
}