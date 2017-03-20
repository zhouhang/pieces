package com.pieces.service;

import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.vo.EnquiryCommoditysVo;

import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryCommoditysService extends ICommonService<EnquiryCommoditys>{

    List<EnquiryCommoditys> findByBillId(Integer billId,Integer userId, Integer pageSize);

    List<EnquiryCommoditys> findByIds(String ids);

    List<EnquiryCommoditysVo> findVoByIds(String ids);

    void create(List<EnquiryCommoditys> enquiryCommoditysList);

    /**
     * 报价
     * 更新报价人,报价时间字段
     * @param list
     * @return
     */
    void quoted(List<EnquiryCommoditys> list, Integer memberId, Integer billsId);



    /**
     * 更新报价信息
     * 更新修改人,修改时间字段
     * @param list
     * @return
     */
    void quotedUpdate(List<EnquiryCommoditys> list, Integer menmberId, Integer billsId);

    /**
     * 用户修改开票价
     * @param list
     */
    void priceUpdate(List<EnquiryCommoditys> list, Integer userId);

}
