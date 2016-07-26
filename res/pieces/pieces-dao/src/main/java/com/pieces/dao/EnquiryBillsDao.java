package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.vo.EnquiryBillsVO;

public interface EnquiryBillsDao extends ICommonDao<EnquiryBills>{

    /**
     * 根据参数查询询价列表
     * @param enquiryBillsVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<EnquiryBillsVO> findByParam (EnquiryBillsVO enquiryBillsVO, int pageNum, int pageSize);


    /**
     * 根据询价单ID查询询价单信息
     * @param id
     * @return
     */
    public EnquiryBillsVO findVOById(Integer id);
}
