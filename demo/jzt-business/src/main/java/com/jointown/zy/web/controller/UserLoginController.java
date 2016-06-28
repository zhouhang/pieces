package com.jointown.zy.web.controller;

//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖保佑             永无BUG 

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.Article1Dto;
import com.jointown.zy.common.dto.ArticleDto;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.HomepageAdEnum;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.HomePageAd;
import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.service.HomePageAdService;
import com.jointown.zy.common.service.HomePageListingService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.MessageVo;


/**
 * @ClassName: UserLoginController
 * @Description: 首页动态化 及交易模块的登陆、登出的重定向
 * @Author: 宋威
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
@Controller(value = "userLoginController")
public class UserLoginController  extends UserBaseController{
	
	private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);
	
	private static final String TRADE_NEWS = "11";//行业新闻
	private static final String BREED_ANALYSIS = "1";//品种分析
	private static final String MARKET_DYNAMIC = "2";//市场动态
	
	@Autowired 
	private IndexService indexService;
	@Autowired
	private SortListService sortListService;
	@Autowired
	private HomePageAdService homePageAdService;
	@Autowired
	private HomePageListingService homePageListingService;
	@Autowired
	private BusiPurchaseService busiPurchaseService;
	
	
	/**
	 * @Description: 首页动态化数据填充模块
	 * @Author: 宋威
	 * @Date: 2015年4月16日
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	/*@RequestMapping("/")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long l1 = System.nanoTime();
		log.info("UserLoginController.index start");
		
		//add remark by Mr.song 2015.4.16 屏蔽首页火爆品种根据推荐品种名进行搜索，改为推荐挂牌进行查看挂牌详情
		//List<Map<Object, Object>> categorylist1 = sortListService.queryMedicinalByClassName("火爆品种");
		//List<Map<Object, Object>> categorylist2 = sortListService.queryMedicinalByClassName("低价特卖");
		
		//add remark by Mr.song 2015.4.16 首页类目品种搜索导航
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		*//***add remark by Mr.song 2015.4.16               首页推荐品种**** start****//*
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		List<Map<Object, Object>> categorylist3 = sortListService.queryMedicinalByClassName("小品种专区");
		//update by Mr.song 2015.5.20临时关闭查询常用大品种，珍稀小品种关键字
//		List<Map<Object, Object>> categorylist4 = sortListService.queryMedicinalByClassName("常用大品种");
//		List<Map<Object, Object>> categorylist5 = sortListService.queryMedicinalByClassName("珍稀小品种");
		*//***add remark by Mr.song 2015.4.16               首页推荐品种**** end****//*
		//add remark by Mr.song 2015.4.16 删除低价特卖下模块
		//List<Map<Object, Object>> categorylist6 = sortListService.queryMedicinalByClassName("低价特卖下");
		
		//add remark by Mr.song 2015.4.16 首页现货天天报
		List<Map<Object, Object>> broadcastEverydayList=indexService.broadcastEveryday();
		
		//add remark by Mr.song 2015.4.16 首页动态计算仓库药材的重量
		String tunnage = indexService.getWarrantsTunnage();
		
		*//***add remark by Mr.song 2015.4.16 首页文章查询，传入文章编号进行列表查询**** start****//*
		List<ArticleDto> articlelist = indexService.getArticleList("1596750392","10");
		List<ArticleDto> alist = indexService.getArticleList("561630814","6");
		*//***add remark by Mr.song 2015.4.16 首页文章查询，传入文章编号进行列表查询**** end****//*
		
		List<Article1Dto> dynamiclist = indexService.getWeixinArticleList("2",10);
		//产地快讯
		List<Article1Dto> origin = indexService.getWeixinArticleList("7",8);
		for (Article1Dto article1Dto : origin) {
			article1Dto.setCont(trimHtml2Txt(article1Dto.getCont()));
		}
		//品种分析
		List<Article1Dto> breed = indexService.getWeixinArticleList("1",3);
		for (Article1Dto article1Dto : breed) {
			article1Dto.setCont(trimHtml2Txt(article1Dto.getCont()));
		}
		//市场快讯
		List<Article1Dto> marketNews = indexService.getMKArticleList();
		//add remark by Mr.song 2015.4.16    首页广告展示
		Map<String, List<HomePageAd>> admap =  homePageAdService.selectAdByCategory();
		//add remark by Mr.song 2015.4.16    首页推荐的挂牌展示
		Map<String, List<HomePageListingDto>> homePageListingMap = homePageListingService.selectHomePageListings(getUserInfo()!=null?getUserInfo().getUserId():null);
		modelMap.put("articlelist", articlelist);
		modelMap.put("alist", alist);
		modelMap.put("dynamiclist", dynamiclist);
		modelMap.put("marketNews", marketNews);
		modelMap.put("origin", origin);
		modelMap.put("breed", breed);
		//首页类目品种搜索导航
		modelMap.put("sortList", sortList);
		*//***************************后台运营编辑的推荐的关键字品种 start****************************//*
		modelMap.put("categorylist", categorylist);
		modelMap.put("categorylist3", categorylist3);
//		modelMap.put("categorylist4", categorylist4);
//		modelMap.put("categorylist5", categorylist5);
		*//***************************后台运营编辑的推荐的关键字品种 end****************************//*
		//add remark by Mr.song 2015.4.16 删除低价特卖下模块
		//modelMap.put("categorylist6", categorylist5);
		//首页现货**吨
		modelMap.put("tunnage", tunnage);
		//首页广告展示
		modelMap.put("admap", admap);
		//首页推荐挂牌品种展示
		modelMap.put("msgmap", homePageListingMap);
		//首页现货天天报
		modelMap.put("broadcastEverydayList", broadcastEverydayList);
		long l2 = System.nanoTime();
		log.error("controller run time:: " +(l2-l1)/1000000.000d+" 毫秒");
		return "/homepage/index";
	} */
	
	/**
	 * @Description: 新首页
	 * @Author: ldp
	 * @Date: 2015年11月2日
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/")
	public String newIndex(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		long l1 = System.nanoTime();
		log.info("UserLoginController.newIndex start");
		
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		/*----搜索关键字的默认品种-------*/
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		
		/*----大货采购的默认品种-------*/
		List<Map<Object, Object>> categorylist4 = sortListService.queryMedicinalByClassName("大货采购");
		
		/*----动态计算仓库药材的重量-------*/
		String tunnage = indexService.getWarrantsTunnage();
		
		
		//药材快讯(首页2.0)
		List<Article1Dto> herbalNews = indexService.getHerbalNews();
		if(herbalNews !=null && herbalNews.size()>0){
			for (Article1Dto article1Dto : herbalNews) {
				article1Dto.setCont(trimHtml2Txt(article1Dto.getCont()));
			}
		}
		//市场动态(首页2.0)
		List<Article1Dto> dynamiclist = indexService.getWeixinArticleList(MARKET_DYNAMIC,7);
		//品种分析(首页2.0)
		List<Article1Dto> breed = indexService.getWeixinArticleList(BREED_ANALYSIS,7);
		for (Article1Dto article1Dto : breed) {
			article1Dto.setCont(trimHtml2Txt(article1Dto.getCont()));
		}
		//行业新闻(首页2.0)
		List<Article1Dto> tradeNews = indexService.getWeixinArticleList(TRADE_NEWS,7);
		modelMap.put("dynamiclist", dynamiclist);
		modelMap.put("herbalNews", herbalNews);
		modelMap.put("breed", breed);
		modelMap.put("tradeNews", tradeNews);//行业新闻
		//首页类目品种搜索导航
		modelMap.put("sortList", sortList);
		/***************************后台运营编辑的推荐的关键字品种 start****************************/
		modelMap.put("categorylist", categorylist);
		//modelMap.put("categorylist3", categorylist3);
		modelMap.put("categorylist4", categorylist4);
		/***************************后台运营编辑的推荐的关键字品种 end****************************/
		//首页现货**吨
		modelMap.put("tunnage", tunnage);
		//首页广告(大幅)展示（首页2.0）
		modelMap.put("admap", homePageAdService.getHomePageAds(HomepageAdEnum.HOMEPAGE_AD_BANNER_BIG.getType(), null));
		//广告专题（首页2.0）
		modelMap.put("adSpacialList", homePageAdService.getHomePageAds(HomepageAdEnum.HOMEPAGE_AD_BANNER_SPACIAL.getType(), null));
		//大区仓库现货-吨（首页2.0）
		modelMap.put("bigAreaTunage", indexService.getBigAreaTunage());
		
		/************************************首页2.0  珍药现货数据 START********************************************/
			//现货直销
			List<HomePageAd> sellingAdds =  homePageAdService.getHomePageAds(HomepageAdEnum.HOMEPAGE_AD_STRAIGHTPIN.getType(), "2");
			modelMap.put("sellingAdds", sellingAdds);
			
			//大户资源 道地药材挂牌广告
			Map<String, List<HomePageListingDto>> listingData = homePageListingService.getHomePageListingData();
			modelMap.put("listingData", listingData);
			
			//全国大仓
			List<HomePageListingDto> bigWarehouse = homePageListingService.getBigWarehouse();
			modelMap.put("bigWarehouse", bigWarehouse);
			
			//最新成交
			List<HomePageListingDto> newBargainList = homePageListingService.getNewBargain();
			modelMap.put("newBargainList", newBargainList);
			modelMap.put("orderStateMap", BusiOrderStateEnum.toMap());
			
			//道地药材,珍药采购 广告
			modelMap.put("adData",homePageAdService.getDaoDiMedicineAd());
			
			//道地药材 类型排序最小值
			modelMap.put("minSortno", homePageAdService.getMinSortno());
		/************************************首页2.0  珍药现货数据 END********************************************/
		
		/************************************首页2.0  珍药采购数据 START********************************************/	
			//最新采购
			List<BusiPurchase>  nowPurchase = busiPurchaseService.getHomePageNewPurchase();
			modelMap.put("nowPurchase", nowPurchase);
			
			//大货采购
			List<BusiPurchase> bigPurchase = busiPurchaseService.getBigPurchase();
			modelMap.put("bigPurchase", bigPurchase);
		/************************************首页2.0  珍药采购数据 END********************************************/	
		
		long l2 = System.nanoTime();
		log.error("controller run time:: " +(l2-l1)/1000000.000d+" 毫秒");
		return "homepage2.0/index";
	} 
	
	@RequestMapping(value="/page")
	public String page(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		return "homepage2.0/page";
	}
	
	/**
	 * @Description: 获取价格指数数据
	 * @Author: ldp
	 * @Date: 2015年11月5日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPriceIndex")
	public @ResponseBody MessageVo getPriceIndex(@RequestParam("type") String type,@RequestParam("k") String k ,HttpServletRequest request){
		return indexService.getPriceIndex(type, k);
	}
	
	/**
	 * @Description: 获取综合指数
	 * @Author: ldp
	 * @Date: 2015年11月6日
	 * @param type
	 * @return
	 */
	@RequestMapping("/getCompositeIndex")
	public @ResponseBody MessageVo getCompositeIndex(@RequestParam("type") String type){
		return indexService.getCompositeIndex(type);
	}
	
	/**
	 * @Description: 登陆重定向：绑定上一次访问的URL
	 * @Author: 宋威
	 * @Date: 2015年4月16日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	public String goback(HttpServletRequest request,HttpServletResponse response){
		log.info("UserLoginController.goback start");
		return "redirect:"+(StringUtils.isEmpty(request.getParameter("go"))?"/":request.getParameter("go"));
	}  
	
	/**
	 * @Description: 手动调用shiro登出方法
	 * @Author: 宋威
	 * @Date: 2015年4月16日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(HttpServletRequest request,HttpServletResponse response){ 
		log.info("UserLoginController.logout start");
		String service = request.getParameter("service");
		Subject subject = SecurityUtils.getSubject();
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		subject.logout();
		//bus=>uc
        return "redirect:"+SpringUtil.getConfigProperties("jointown.url.uc.server.prefix")+"/userlogout?service="+SpringUtil.getConfigProperties("jointown.url");
    }
	
	@RequestMapping(value="/userlogout")
    public String userlogout(HttpServletRequest request,HttpServletResponse response){
		String service = request.getParameter("service");
		if (SpringUtil.getConfigProperties("jointown.url").equals(service)) {
			return "redirect:/";
		}else {
			Subject subject = SecurityUtils.getSubject();
			//使用权限管理工具进行用户的退出，跳出登录，给出提示信息
			subject.logout();
			//bus=>uc
			return "redirect:"+SpringUtil.getConfigProperties("jointown.url.uc.server.prefix")+"/userlogout?service="+service;
		}
	}
	
	/**
	 * @Description: mini登录回调script，统一用于关闭弹层和进行后续操作的script
	 * @Author: guoyb
	 * @Date: 2015年6月30日
	 * @param request 
	 * @param response 
	 * @return
	 */
	@RequestMapping(value="/popLoginHandler")
	public String popLoginHandler(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String username = request.getParameter("u");
		String handleType = request.getParameter("type");
		
		modelMap.put("username", username);
		modelMap.put("handleType", handleType);
		return "/business/popLoginScript";
	}
	/** 
     * 把html内容转为文本 
     * @param html 需要处理的html文本 
     * @param filterTags 需要保留的html标签样式 
     * @return 
     */  
    public static String trimHtml2Txt(String html){ 
    	html = html.replaceAll("</?[^<]+>", "");
    	html = html.replaceAll("</?[^<]*", "");
    	/*html = html.replaceAll("\\<div[^>]*>", "");
        html = html.replaceAll("\\</div>(?i)", "");
        
        html = html.replaceAll("\\<p[^>]*>", "");
        html = html.replaceAll("\\</p>(?i)", ""); 
        
        html = html.replaceAll("\\<span[^>]*>", "");
        html = html.replaceAll("\\</span>(?i)", "");
        
        html = html.replaceAll("\\<br/>", "");*/
        
        html = html.replaceAll("&nbsp;", "");  
        html = html.replaceAll("&ldquo;", "");  
        html = html.replaceAll("&deg;", "");  
        html = html.replaceAll("&rdquo;", "");  
        html = html.replaceAll("\\\n", "");  
        html = html.replaceAll("\\\t", "");  
        return html.trim();  
    }  
}