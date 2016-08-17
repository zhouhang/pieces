package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.ShippingAddressHistoryVo;

public interface ShippingAddressHistoryService extends ICommonService<ShippingAddressHistory>{

    public PageInfo<ShippingAddressHistoryVo> findByParams(ShippingAddressHistoryVo shippingAddressHistoryVo, Integer pageNum, Integer pageSize);


    public ShippingAddressHistory createByAddress(ShippingAddress shippingAddress);

    public ShippingAddressHistory createByAddress(Integer shippingAddressId);

}
