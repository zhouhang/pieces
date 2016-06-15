package com.jointown.zy.common.constant;


public class WebConstatVar {

    /**
     * DES加密对称KEY
     */
    public static final String DES_KEY = "gfgbfg_(*&^%^_sohu";

    /**
     * PEAK支付使用的KEY
     */
    public static final String PEAK_KEY = "@#&86am*$";
    // public static final String PEAK_KEY = "EGe9x4G$QV";//Test
    /**
     * PEAK支付使用的psid
     */
    public static final String PEAK_PSID = "1992";
    /**
     * PEAK支付的地址
     */
    public static final String PEAK_URL = "https://up.sohu.com/channel/gw/order.up?";
    // public static final String PEAK_URL = "http://202.106.180.3/channel/gw/orderpay.up?";
    /**
     * PEAK支付返回调用地址
     */
    public static final String PEAK_RETURN_URL = "http://sendcloud.sohu.com/depositReturl.do";
    // public static final String PEAK_RETURN_URL = "http://10.11.150.143:8016/depositReturl.do";
    /**
     * PEAK支付返回调用展示地址
     */
    public static final String PEAK_RETURN_URL2 = "http://sendcloud.sohu.com/payInfo.do";
    // public static final String PEAK_RETURN_URL2 = "http://10.11.150.143:8016/payInfo.do";

    /**
     * 参数分隔符
     */
    public static final String PARM_SPLIT = "_split2012_";

    // 用户session
    public static final String LOGIN_SESSION_ID = "user_session_id";
    
    //用户预热信息
    public static final String USER_WARM_UP_INFO = "user_warm_up_info";
    
    //用户是否存在于预热列表
    public static final String USER_IS_WARM_UP = "user_is_warm_up";
    
    // 用户帐号信息
    public static final String USER_CONTACT_INFO = "user_contact_info";

    // 用户当前发送的邮件数
    public static final String USER_TODAY_DELIVERED_COUNT = "user_today_delivered_count";

    // 用户发送的邮件总数
    public static final String USER_TOTAL_DELIVERED_COUNT = "user_total_delivered_count";

    // 用户邮件统计信息列表
    public static final String USER_STAT_EMAIL_LIST = "user_stat_email_list";

    // 用户邮件统计信息列表
    public static final String USER_STAT_BOUNCE_LIST = "user_stat_bounce_list";

    // 页码信息
    public static final String USER_STAT_PAGE_VO = "paging_vo";

    // 用户统计信息的起始时间
    public static final String USER_STAT_EMAIL_BEGIN_DATE = "user_stat_email_begin_date";

    // 用户统计信息的结束时间
    public static final String USER_STAT_EMAIL_END_DATE = "user_stat_email_end_date";

    // 用户选择的统计信息类别
    public static final String USER_STAT_SELECT_CATEGORY = "user_stat_select_category";

    // 用户app状态列表
    public static final String USER_APP_STATUS_LIST = "user_app_status_list";

    public static final String ADMIN_LOGIN_KEY = "fsd99vdfv$%^%sdgv98862@$#!$%";

    // 每页长度
    public static final int PAGE_LEN = 10;

    // app的三种状态，默认启用，启用和停用
    public static final short APP_DEFAULT_ENABLED = 1;
    public static final short APP_ENABLED = 2;
    public static final short APP_DISABLED = 3;

    // 所有app的名称
    public static final String LIST_APP_NAME[] = {

    "dkim", "click_tracking", "open_tracking", "gravatar", "footer", "spam_checker", "google_analytics", "email_template", "domain_keys", "bcc", "event_notification", "forward_spam",
	    "address_whitelist", "subscription_tracking", "webhooks", "custom_dkim" };

    // 所有app的默认状态
    public static final short LIST_DEFAULT_APP_STATUS[] = { APP_DEFAULT_ENABLED, APP_ENABLED, APP_ENABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED,
	    APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED, APP_DISABLED };

    public static String OLD_PASSPORT_LIST = ",tpc,mtpc,passport,17173,auto,tv,sendcloud,techipd,it,matt,delong,sendcloud1,iheima,huxiu,yunbian,fanqu,mysohu,test01,123123123,baorui,bao,baor,baortest,gkzhong,cvsdvdfvf,str_website,jsjwkcdscdsc,open,baoruir,wangpan,sce_mail,wangyuanbo,wrongwaycn,liang8962381,wengshanjin,sogoumarketing,bizmail,iscript,xienianhua,yuwenhui,zhouliyi,blueven,junelishu,maji,spark,nickyhu,wangge,accexine,xjxxmxjxxm,zzzhr,haibi.com,sogoudaohang,yucmedia,sendcloudmonitor,sendcloud_admin,cdscds,sohushiyong,sogoupinyin,sogougo2map,luffy,JivinLee,Loning,chuanlei,SohuMailFeedback,playadmin,thinkerpan,yaojing,sports,shangji,tvdingyue,anquanbao,Zhengqiang,yixue360,yidonghua,jincili,Fountain,gaitu,long5991000,konotoo,javenisme,cdcdc,cdcdcdcdcd,cdscsdcds11,cdcdscds,chinaemail,mogo,srdrm,delongwu,zhang20084,huangyineng,mojingshuo,erhuok,taojin,tangliqun,perpy,cool,budaigou,leeyue723,fengjichuan,bizhongli,whistle,jasonzhu,long008,daocaoli,smallchen,azoon,Lane,haojay,shiny,cxu1122,bj591wed,wangjian,dlam888,quakewang,dcbescal,aidesoft,aggrelxf,wurihua,guju,caimingjun,jiae,gmagic10,weakdncer,dong,cscetd,xlvxing,donghui,xiaomifeng,cpzhong,jwfeng520,send,wangqingping,jiang,fish,xlzone,iozzz,chunlinyao,sohukan,alleniver,uper,kefca,xxy693,fenng,xiangrikui,qianniuwang,randong,qixiang,xtz136,imfiend,Phabro,henizaiyiqi,songchuans,xiaomian,ushi.com,test,xalinx,dreamplayer,rainy,flymm007,Wsquaredlive,kelvin,fanao,alert,feeson,chopin,lijinci,hulaiyang,kiwiit,andrew81,reilost,udika,zhihu,lihongkuo,samuel,skywalker,baicizhan,huixin,testsend,leyilvyou,ol_beta,chenyinwei,liming3389,kshhs_chenyinwei,leftstar,killjin,zc13888,goodbyennn,haha_cdsc,pau_rui,wangji_ec,dadao598,zzj7251,amdy,lixingguang,duysh,admin,aihaitun,cuiwei,data_monitor,zhouhaocheng,sharonlibra,pxrcsc,zehaocarpet,alezi,beatsboot1,qq396666272,marketing,qing606,wenyi,darkhe,rsheng,yanjun,Globe,aiopio,mzread,yaoweibo,ankain,GOLDENSEA,xmlian,inroading,yangdejin8,kk4u,kevinyao,cubedata,GaiaMagic,shouhuyuncai,shanbay,chinahighlights,zhang365,weiluo,fat1,kimberleyer,qq459094521,uugames,xuhuafeng,rongjch,kevin,w19384599,chenge,Michael,smily8002,jamguo,kl521516,sunlane,shiatang,meinvjz,reshion,jackwojiushiwo,emloog,heima,bokeyuan,LeonZhu,MrHan,xinyige,shiyong,market,";
    
    // 用户所有category列表
    public static final String USER_CATEGORY_LIST = "user_category_list";

    // 用户所有label列表
    public static final String USER_LABEL_LIST = "user_label_list";

    // 状态信息
    public static final String STATUS_MSG = "status_msg";

    // 错误信息
    public static final String ERROR_MSG = "error_message";



    public static final String INDEX_PAGE = "/index.jsp";
    public static final String LOGIN_PAGE = "/login";
    public static final String ERROR_PAGE = "http://sendcloud.sohu.com/new-ui/error.jsp";
    public static final String ERROR_404_PAGE = ERROR_PAGE;

    /**
     * 请求额度是发送额度的倍数
     */
    public static final double REQUEST_THAN_EMAILVOLUMN = 1.2;
    
    /**
     * 额度限制Cache更新时长
     */
    public static long QUOTA_CACHE_UPDATE_TIME = 1000*60*5L;
    
    /**
     * ip浮动区间
     */
    public static final double REPUTATION_AREA = 10;

    /**
     * ip最低信誉度
     */
    public static final double IP_MIN_REPUTATION = 0;
    /**
     * ip最大信誉度
     */
    public static final double IP_MAX_REPUTATION = 100;
    
    /**
     * 初始化免费用户额度
     */
    public static final int INIT_EMAILVOLUMN = 200;
    
    /**
     * 初始化内部用户额度
     */
    public static final int INIT_INNER_EMAILVOLUMN = 200000;

    /**
     * 初始化VIP用户额度
     */
    public static final int INIT_VIP_EMAILVOLUMN = 200000;
    /**
     * 初始化用户信誉度
     */
    public static final double INIT_REPUTATION = 60D;
    
    /**
     * 共享域名后缀
     */
    public static final String SHARED_DOMAIN_SUFFIX = "sendcloud.org";

    
    public static String UCLOUD_API_URL = "http://api.test.ucloud.cn";
//    public static String UCLOUD_JUMP_URL = "http://ucenter.ucloud.cn/external/sendcloud";
    public static String UCLOUD_JUMP_URL = "https://vip.ucloud.cn/external/sendcloud";
    public static String UCLOUD_APP_KEY = "22627296951140352";
    public static String UCLOUD_APP_SECRET = "8566dbcfe942662dfb8a1b7a9a16c39686c24264";
    
    // mailchimp 列表个数限制
    public static final Integer MAILCHIMP_LIST_LIMIT = 30;
    
 // mailchimp 列表管理单页显示列表个数
    public static final Integer MAILCHIMP_LIST_PAGE_LEN = 6;
    
    // mailchimp 模板个数限制
    public static final Integer MAILCHIMP_TEMPLATE_LIMIT = 30;
    
    //跳转到认证页面
    public static final String   AUTHENTICATION_URL="/getLegalize";
    
    //business跳转到认证页面
    public static final String BUSI_AUTHENTICATION_URL="http://uc.54315.com/getLegalize";
    
    //business跳转到登陆页面 for js
    public static final String BUSI_LOGIN_URL="https://passport.54315.com/login?service=http://www.54315.com/casuc";
    
    //////////////////////////////////////////////// 会员信息完善 //////////////////////////////////////////////// 
    //完善经营信息
    public static final String COMPLETE_BUSI_INFO_URL = "/completeInfoGuide/completeBusiInfo";
    
    //完善联系人信息
    public static final String COMPLETE_CONTACTER_INFO_URL ="/completeInfoGuide/completeContacterInfo";
    
}