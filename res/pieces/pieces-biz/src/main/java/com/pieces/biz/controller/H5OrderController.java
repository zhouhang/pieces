package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.pieces.biz.shiro.BizToken;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.service.vo.CropResult;
import com.pieces.tools.bean.BASE64DecodedMultipartFile;
import com.pieces.tools.exception.NotFoundException;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.WebUtil;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: koabs
 * 3/14/17.
 * 微信端订单功能
 */
@Controller
@RequestMapping("/h5c/")
public class H5OrderController {

    Logger logger = LoggerFactory.getLogger(H5OrderController.class);
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderFormService orderFormService;

    private static final  Integer pageSize=5;

    @Autowired
    PayAccountService payAccountService;

    @Autowired
    PayRecordService payRecordService;

    @Autowired
    AccountBillService accountBillService;


    @RequestMapping(value ="order/list",method = RequestMethod.GET)
    public String orderList(Integer status,ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(status==null){
            status=1;
        }
        modelMap.put("status",status);
        modelMap.put("userType",user.getType());
        return "wechat/order_list";
    }


    @RequestMapping(value ="order/list",method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderList(Integer pageNum,Integer status) {

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        PageInfo<OrderFormVo> pageInfo;
        if(user.getType()==1){
           pageInfo = orderFormService.findOrderByUserId(user.getId(),status,pageNum, pageSize);
        }
        else{
            pageInfo = orderFormService.findOrderByAgentId(user.getId(),status,pageNum, pageSize);
        }

        return new Result(true).data(pageInfo);
    }

    /**
     * 用户订单详情
     * @return
     */
    @RequestMapping(value = "order/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/order_detail";
    }


    /**
     * 用户订单支付
     * @return
     */
    @RequestMapping(value = "order/pay/{id}", method = RequestMethod.GET)
    public String payOrder(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/pay";
    }

    @RequestMapping(value = "pay/bank/{id}", method = RequestMethod.GET)
    public String payBank(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        OrderFormVo vo =  orderFormService.findVoById(id);

        List<PayAccount> payAccountList = payAccountService.findAll();
        modelMap.put("payAccountList",payAccountList);
        modelMap.put("userType",user.getType());
        modelMap.put("vo",vo);
        return "wechat/pay_bank";
    }
    @RequestMapping(value = "pay/bill/{id}", method = RequestMethod.GET)
    public String payBill(@PathVariable("id")Integer id, ModelMap modelMap){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(user.getType()==1){
            return "redirect:error/404";
        }
        OrderFormVo vo =  orderFormService.findVoById(id);
        modelMap.put("vo",vo);
        return "wechat/pay_bill";
    }

    @RequestMapping(value = "pay/success", method = RequestMethod.GET)
    public String paySuccess(ModelMap modelMap){
        return "wechat/pay_message";
    }


    /**
     * 创建支付记录
     * @param payRecordVo
     * @return
     */
    @RequestMapping(value = "/payRecord/create")
    @ResponseBody
    public Result create(PayRecordVo payRecordVo,
                         String[] img){

        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        payRecordService.create(payRecordVo,img,user.getId());
        //支付成功后通知管理员审核
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.payment.getTitle(String.valueOf(payRecordVo.getId())),
                        NotifyTemplateEnum.payment.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.payment.getType(),payRecordVo.getId()));
        return new Result(true).info("支付信息提交成功!");
    }

    /**
     * 提交帐单
     * @param billtime
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/bill/create")
    @ResponseBody
    public Result bill(Integer billtime,
                       Integer orderId){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        AccountBill accountBill = new AccountBill();
        accountBill.setUserId(user.getId());

        accountBill.setOrderId(orderId);
        accountBill.setBillTime(billtime);
        accountBillService.createBill(accountBill);
        // 账单提交成功后通知管理员审核
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SpringUtil.getApplicationContext().
                publishEvent(new NotifyEvent(NotifyTemplateEnum.account_bill.getTitle(String.valueOf(accountBill.getId())),
                        NotifyTemplateEnum.account_bill.getContent(user.getContactName(),time.format(new Date())),NotifyTemplateEnum.account_bill.getType(),accountBill.getId()));

        return new Result(true).info("账单提交成功!");
    }


    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;

    @Autowired
    private EnquiryBillsService enquiryBillsService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private PayAccountService payAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountBillService accountBillService;

    @Autowired
    private PayRecordService payRecordService;

    @Autowired
    private LogisticalService logisticalService;

    @RequestMapping(value = "/order/md5")
    public String redictOrder(String commodityIds){
        String md5 = EncryptUtil.getSHA1(commodityIds,"UTF-8");
        httpSession.setAttribute(md5,commodityIds);
        return "redirect:/center/order/create?omd5=" + md5;
    }

    @RequestMapping(value = "/order/create")
    @BizLog(type = LogConstant.order, desc = "创建订单页面")
    @SecurityToken(generateToken = true)
    public String orderCreate(ModelMap modelMap, String omd5,Integer addressId){
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        String commodityIds = null;
        if (omd5 != null) {
            commodityIds = (String) httpSession.getAttribute(omd5);
        }
        // 根据询价商品计算保证金和开票金额
        List<EnquiryCommoditys> enquiryCommoditys = enquiryCommoditysService.findByIds(commodityIds);
        for(EnquiryCommoditys ec : enquiryCommoditys){
            ec.setAmount(1);
        }
        //获取收货地址
        ShippingAddressVo shippingAddress = shippingAddressService.getAddressById(addressId,user.getId());
        modelMap.put("enquiryCommoditys", enquiryCommoditys);
        modelMap.put("shippingAddress", shippingAddress);
        modelMap.put("user", user);
        //根据用户类型来判断该跳转到那个下单页面并获取代理商代理的用户信息
        if (user.getType()==2) {
            List<UserVo> agentUser = userService.findUserByProxy(user.getId());
            modelMap.put("agentUser",agentUser);
        }
        return "order";
    }

    /**
     * 用户代理商列表页面
     * @return
     */
    @RequestMapping(value = "/order/agent")
    public String getAgentUser(ModelMap model, Integer selectId) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<UserVo> agentUser = userService.findUserByProxy(user.getId());
        model.put("agentUser",agentUser);
        model.put("selectId",selectId);
        return "order_agent_list";
    }

    /**
     * 用户地址
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/address")
    public String getAddress(ModelMap model, Integer selectId) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<ShippingAddressVo>  shippingAddress =shippingAddressService.findByUser(user.getId());
        model.put("agentUser",shippingAddress);
        model.put("selectId",selectId);
        return "order_address_list";
    }

    /**
     * 保存订单
     * @param orderFormVo
     * @return
     */
    @RequestMapping(value = "/order/save")
    @BizLog(type = LogConstant.order, desc = "保存订单")
    @SecurityToken(validateToken = true)
    public Result saveOrder(@RequestBody  OrderFormVo orderFormVo) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        return new Result(true);
    }

    /**
     * 下单成功页面
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/success")
    public String orderSuccess(Integer orderId) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        return "order_success";
    }

    /**
     * 编辑收货地址
     * @return
     */
    @RequestMapping(value = "/address/edit", method = RequestMethod.GET)
    public String saveAddressPage() {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        return "order_success";
    }

    /**
     * 保存收货地址 不能超过10个的限制
     * @param shippingAddress
     * @return
     */
    @RequestMapping(value = "/address/save", method = RequestMethod.POST)
    public Result saveAddress(@Valid ShippingAddress shippingAddress) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        return null;
    }

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    @RequestMapping(value = "/address/delete", method = RequestMethod.POST)
    public Result deleteAddress(Integer id) {
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        return null;
    }



}
