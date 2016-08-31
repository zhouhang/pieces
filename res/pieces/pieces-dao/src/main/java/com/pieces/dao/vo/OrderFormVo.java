package com.pieces.dao.vo;

import com.pieces.dao.OrderFormDao;
import com.pieces.dao.config.SystemConfig;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.*;
import com.pieces.tools.utils.Reflection;
import com.pieces.tools.utils.SpringUtil;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderFormVo extends OrderForm {

    private Integer orderId;

    // 商品列表
    private List<OrderCommodity> commodities;

    private List<OrderCommodityVo> commodityVos;

    // 邮寄地址
    private ShippingAddressHistory address;

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
    private User user;

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

    public String getOrderValidityPeriod() {
        if (this.getCreaterTime() != null) {
            if (this.getStatus() == OrderEnum.UNPAID.getValue()) {
                // 10天的间隔 转换成毫秒
                Long intervals = Long.valueOf(SystemConfig.orderValidityPeriod * 24 * 60 * 60 * 1000);
                Long day = 24 * 60 * 60 * 1000L;
                Long hour = 60 * 60 * 1000L;
                Long minute = 60 * 1000L;

                Long createTime = this.getCreaterTime().getTime() + intervals;

                Long currentTime = new Date().getTime();
                if (createTime <= currentTime) {
                    // 付款期限已过 设置付款状态为取消
                    // TODO:
                    this.setStatus(OrderEnum.CANCEL.getValue());
//                    OrderFormDao orderFormDao = (OrderFormDao)SpringUtil.getBean(OrderFormDao.class);
//                    OrderForm form = new OrderForm();
//                    form.setId(orderId);
//                    form.setStatus(OrderEnum.CANCEL.getValue());
//                    orderFormDao.update(form);
                } else {
                    Long difference = createTime - currentTime;
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
