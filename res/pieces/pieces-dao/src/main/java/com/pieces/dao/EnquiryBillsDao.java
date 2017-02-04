package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryRecordVo;

import java.util.List;

@AutoMapper
public interface EnquiryBillsDao extends ICommonDao<EnquiryBills>{

    /**
     * 根据参数查询询价列表
     * @param enquiryBillsVO
     * @return
     */
    public List<EnquiryBillsVo> findByParam (EnquiryBillsVo enquiryBillsVO);


    /**
     * 根据询价单ID查询询价单信息
     * @param id
     * @return
     */
    public EnquiryBillsVo findVOById(Integer id);


    public List<EnquiryBills> findByCommoditys(EnquiryRecordVo enquiryRecordVo);

    /**
     * 未处理询价单数目
     * @return
     */
    public Integer getNotHandleCount();

    public List<Integer> getNotHandleIds();

}
