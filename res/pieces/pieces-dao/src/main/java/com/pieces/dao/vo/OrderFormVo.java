package com.pieces.dao.vo;


import com.pieces.dao.config.SystemConfig;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.event.OrderStatusEvent;
import com.pieces.dao.model.*;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import javax.validation.Valid;
import java.util.*;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderFormVo extends OrderForm {

    private Integer orderId;

    // 商品列表
    private @Valid List<OrderCommodity> commodities;

    private List<OrderCommodityVo> commodityVos;

    // 邮寄地址
    private@Valid ShippingAddressHistory address;

    private ShippingAddress shippingAddress;

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // 发票信息
    private OrderInvoice invoice;

    //客户信息
    private@Valid User user;

    private Integer userId;

    // 商品信息摘要
    private String commodityOverview;
    
    // 订单商品ids
    private String commodityIds;

    private String statusText;

    //订单开始时间
    private Date startTime;
    //订单结束时间
    private Date endTime;

    // 订单剩余支付时间
    private String orderValidityPeriod;

    // 1 用户前台搜索 不显示已删除的订单.
    private Integer isUserSearch;


    /**
     * 创建订单时候传递的参数
     */

    private  List<EnquiryCommoditys> commodityses;

    /**
     * 提交订单要检验的token
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<EnquiryCommoditys> getCommodityses() {
        return commodityses;
    }

    public void setCommodityses(List<EnquiryCommoditys> commodityses) {
        this.commodityses = commodityses;
    }

    public List<OrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderCommodity> commodities) {
        this.commodities = commodities;
    }

    public ShippingAddressHistory getAddress() {
        return address;
    }

    public void setAddress(ShippingAddressHistory address) {
        this.address = address;
    }

    public OrderInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoice invoice) {
        this.invoice = invoice;
    }
    
    public String getCommodityIds() {
		return commodityIds;
	}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 用户不存在new 一个User 对象避免前台获取用户数据是出错
     * @return
     */
    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommodityIds(String commodityIds) {
		this.commodityIds = commodityIds;
	}

	public String getCommodityOverview() {
        if (commodities != null) {
            commodityOverview = "";
            int lenght = commodities.size() >= 3 ? 3 : commodities.size();
            String[] names = new String[lenght];
            for (int i = 0; i < lenght; i++) {
                names[i] = commodities.get(i).getName();
            }
            commodityOverview = StringUtils.join(names, ",");
            if (commodities.size() > 3) {
                commodityOverview += "...";
            }
        }

        return commodityOverview;
    }

    public String getStatusText() {
        getOrderValidityPeriod();
        aotoComplete();
        return OrderEnum.findByValue(getStatus());
    }

    public List<OrderCommodityVo> getCommodityVos() {
        return commodityVos;
    }

    public void setCommodityVos(List<OrderCommodityVo> commodityVos) {
        this.commodityVos = commodityVos;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getIsUserSearch() {
        return isUserSearch;
    }

    public void setIsUserSearch(Integer isUserSearch) {
        this.isUserSearch = isUserSearch;
    }

    /**
     * 每次显示是
     * 发货后15天自动收货完成订单
     */
    private void aotoComplete() {
        if (getDeliveryDate()!= null && getStatus().equals(OrderEnum.SHIPPED.getValue())){
            Long intervals = Long.valueOf(SystemConfig.deliveryValidityPeriod * 24 * 60 * 60 * 1000L);
            Long currentTime = new Date().getTime();
            Long createTime = this.getDeliveryDate().getTime() + intervals;
            if (createTime <= currentTime) {
                // 如果发货后 15天 订单设置为自动收货
                SpringUtil.getApplicationContext().publishEvent(new OrderStatusEvent(getId(), OrderEnum.COMPLETE.getValue()));
            }
        }
    }

    /**
     * 订单支付有效期
     * @return
     */
    public String getOrderValidityPeriod() {
        if (this.getCreaterTime() != null) {
            if (this.getStatus().equals(OrderEnum.UNPAID.getValue()) && this.getExpireDate()!= null) {
                Long day = 24 * 60 * 60 * 1000L;
                Long hour = 60 * 60 * 1000L;
                Long minute = 60 * 1000L;
                Calendar now = Calendar.getInstance();
                now.setTime(this.getExpireDate());
                now.set(Calendar.HOUR, 23);
                now.set(Calendar.MINUTE,59);
                now.set(Calendar.SECOND,59);
                Long expireDate = now.getTime().getTime();
                Long currentTime = new Date().getTime();
                if (expireDate <= currentTime) {
                    // 付款期限已过 设置付款状态为取消
                    this.setStatus(OrderEnum.CANCEL.getValue());
                    SpringUtil.getApplicationContext().publishEvent(new OrderStatusEvent(getId(), OrderEnum.CANCEL.getValue()));
                } else {
                    Long difference = expireDate - currentTime;
                    Long dayS = difference / day;
                    Long hourS = (difference % day) / hour;
                    Long minuteS = ((difference % day) % hour) / minute;
                    orderValidityPeriod = dayS.intValue() + "天" + hourS.intValue() + "时" + minuteS.intValue() + "分";
                }
            }
        }

        return orderValidityPeriod;
    }

    /**
     * 将对象序列化为url参数.
     * @return
     */
    public String serialize(){
        String param = "";
        User userL = this.user;
        this.user = null;
        param = Reflection.serialize(this);

        if (userL.getContactName() != null){
            param += "&" + "user.contactName="+ userL.getContactName();
        }

        if (userL.getCompanyFullName() != null) {
            param += "&" + "user.companyFullName="+ userL.getCompanyFullName();
        }

        this.user = userL;
        return param;
    }
}
