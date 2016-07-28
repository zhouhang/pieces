package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.EnquiryBillsVO;
import com.pieces.dao.vo.EnquiryRecordVo;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryBillsService extends ICommonService<EnquiryBills>{


    public void create(List<EnquiryCommoditys> enquiryCommoditysList, User user);

    public PageInfo<EnquiryBills> findByPage(int pageNum, int pageSize, EnquiryRecordVo enquiryRecordVo);


    /**
     * 根据参数查询询价列表
     * @param enquiryBillsVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<EnquiryBillsVO> findByParam (EnquiryBillsVO enquiryBillsVO, Integer pageNum, Integer pageSize);

    public EnquiryBillsVO findVOById(Integer id);
}
