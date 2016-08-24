package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;

import java.util.List;

public interface ShippingAddressService extends ICommonService<ShippingAddress>{

    public PageInfo<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo, Integer pageNum, Integer pageSize);

    public List<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo);

    public List<ShippingAddressVo> findByUser(Integer userId);

    public void delete(int userId,int id);

    public void settingDefaultAddress(Integer addressId,Integer userId);

    /**
     * 保存或者更新收货地址信息
     * @param address
     */
    public void saveOrUpdate(ShippingAddress address, User user);

    /**
     * 根据地址ID 获取地址VO
     * @param id
     * @return
     */
    public ShippingAddressVo findVoById(Integer id);
}
