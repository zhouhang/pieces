package com.pieces.biz.controller;

import com.pieces.dao.model.CartsCommodity;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.CartsCommodityVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.service.*;
import com.pieces.service.constant.BasicConstants;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.service.redis.RedisManager;
import com.pieces.tools.utils.CookieUtils;
import com.pieces.tools.utils.SpringUtil;
import com.pieces.tools.utils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pieces.tools.utils.CookieUtils;

/**
 * Created by xiao on 2017/2/21.
 */

/**
 * 购物车操作以及页面
 */
@Controller
@RequestMapping("/cart")
public class CartController {



       private static final int CART_EXPIRE = 3600*24*30;//默认30天

       private static final String CART_NAME ="cart";

       @Autowired
       HttpSession httpSession;

       @Autowired
       CartsCommodityService cartsCommodityService;

       @Autowired
       private EnquiryBillsService enquiryBillsService;



       @Autowired
       private CommodityService commodityService;


       @Autowired
       private UserService userService;

       @Autowired
       private RedisManager redisManager;


       /**
        * 购物车主页
        * @param modelMap
        * @return
        */
       @RequestMapping("/index")
       public String index(ModelMap modelMap){
              modelMap.put("cartHide",true);
              return "cart_index";
       }

       /**
        * 获取购物车列表
        * @param ids
        * @return
        */
       @RequestMapping(value = "/list",method = RequestMethod.POST)
       @ResponseBody
       public Result list(String ids){
              List<CommodityVo> commodityVos=new ArrayList<CommodityVo>();
              if(!ids.equals("")){
                     commodityVos=commodityService.findVoByIds(ids);
              }
              return new Result(true).data(commodityVos);
       }

       /**
        * 添加商品(仅在用户登陆时候更新数据库)
        * @param commodityId
        * @return
        */
       @RequestMapping(value = "/add",method = RequestMethod.POST)
       @ResponseBody
       public Result add(HttpServletRequest request, Integer commodityId){
              User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
              if(user!=null){
                     CartsCommodity cartsCommodity=new CartsCommodity();
                     cartsCommodity.setUserId(user.getId());
                     cartsCommodity.setCommodityId(commodityId);
                     cartsCommodityService.save(cartsCommodity);
              }
              return new Result(true).info("添加成功");
       }

       /**
        * 删除商品(仅在用户登陆时候更新数据库)
        * @param commodityId
        * @return
        */
       @RequestMapping(value = "/delete",method = RequestMethod.POST)
       @ResponseBody
       public Result delete(HttpServletRequest request,Integer commodityId){
              User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
              if(user!=null){
                     CartsCommodityVo cartsCommodity=new CartsCommodityVo();
                     cartsCommodity.setUserId(user.getId());
                     cartsCommodity.setCommodityId(commodityId);
                     cartsCommodityService.deleteByVo(cartsCommodity);
              }
              return new Result(true).info("删除成功");
       }


       @RequestMapping(value="/reEnquiry",method=RequestMethod.GET)
       public String reEnquiry(HttpServletRequest request,HttpServletResponse response,Integer billId) throws Exception {
              User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
              if(user!=null&&billId!=null){
                     EnquiryBillsVo enquiryBillsVo=enquiryBillsService.findVOById(billId);
                     if(enquiryBillsVo!=null){
                            List<EnquiryCommoditys> enquiryCommodityses=enquiryBillsVo.getEnquiryCommoditys();

                            List<String> commodityIds=new ArrayList<String>();
                            for(EnquiryCommoditys enquiryCommoditys:enquiryCommodityses){
                                   commodityIds.add(enquiryCommoditys.getCommodityId().toString());
                            }
                            //由于登录了cookie和数据库中是一致的只需更新到数据库中，然后刷新cookie就行了
                            cartsCommodityService.combine((String[])commodityIds.toArray(new String[commodityIds.size()]),user);
                            List<Integer> ids=cartsCommodityService.getIds(user.getId());
                            if(ids.size()!=0){
                                   CookieUtils.setCookie(response, CART_NAME, StringUtils.join(ids,"@") ,CART_EXPIRE);
                            }
                     }

              }

              return "redirect:/cart/index";
       }




       /**
        * 提交询价单
        * @param username,phone,code
        * @return
        */
       @RequestMapping(value = "/submit",method = RequestMethod.POST)
       @ResponseBody
       public Result submit(HttpServletRequest request,String username,String mobile,String code) throws Exception {
              String cookieValue = CookieUtils.getCookieValue(request, "cart");
              String ids=StringUtils.join(StringUtils.split(cookieValue,"@"),",");

              User user = (User) request.getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
              List<EnquiryCommoditys> list=new ArrayList<EnquiryCommoditys>();
              if(user==null){
                   //帮用户注册生成询价单
                     String sessionCode  = redisManager.get(RedisEnum.KEY_MOBILE_EQUIRY_CAPTCHA.getValue()+mobile);
                     if (!StringUtils.isNotBlank(code)) {
                            return new Result(false).info("请获取验证码");
                     }
                     if (!code.equals(sessionCode)) {
                            return new Result(false).info("验证码错误");
                     }

                     if(!userService.ifExistMobile(mobile)){
                            user=new User();
                            user.setContactName(username);
                            user.setContactMobile(mobile);
                            userService.generateUser(user);
                     }
                     user= userService.findByAccount(mobile);
              }
              List<CommodityVo> commodityVos=commodityService.findVoByIds(ids);
              for(CommodityVo commodityVo:commodityVos){
                     EnquiryCommoditys enquiryCommoditys=new EnquiryCommoditys();
                     enquiryCommoditys.setCommodityId(commodityVo.getId());
                     enquiryCommoditys.setLevel(commodityVo.getLevel());
                     enquiryCommoditys.setOrigin(commodityVo.getOriginOf());
                     enquiryCommoditys.setSpecs(commodityVo.getSpec());
                     enquiryCommoditys.setCommodityName(commodityVo.getName());
                     list.add(enquiryCommoditys);
              }


              if(list.size()!=0){
                     Integer billId = enquiryBillsService.create(list,user);
                     /**
                      * 清空购物车
                      */
                     CartsCommodityVo cartsCommodity=new CartsCommodityVo();
                     cartsCommodity.setUserId(user.getId());
                     cartsCommodityService.deleteByVo(cartsCommodity);


                     //用户询价成功后通知管理员处理
                     SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     SpringUtil.getApplicationContext().
                             publishEvent(new NotifyEvent(NotifyTemplateEnum.enquiry.getTitle(String.valueOf(billId)),
                                     NotifyTemplateEnum.enquiry.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.enquiry.getType(),billId));
              }

              return new Result(true).info("提交询价成功");
       }
















}
