package com.pieces.service;

import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;

import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryBillsService extends ICommonService<EnquiryBills>{


    public void create(List<EnquiryCommoditys> enquiryCommoditysList, User user);
}
