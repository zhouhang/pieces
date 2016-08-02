package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.vo.EnquiryBillsVO;
import com.pieces.dao.vo.EnquiryRecordVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class EnquiryBillsDaoImpl extends BaseDaoImpl implements EnquiryBillsDao {
    @Autowired
    private EnquiryCommoditysDao enquiryCommoditysDao;

    @Override
    public EnquiryBills findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.EnquiryBillsMapper.findById", id);
    }


    @Override
    public List<EnquiryBills> findAll() {
        return getSqlSession().selectList("com.pieces.dao.EnquiryBillsMapper.findAll");
    }

    @Override
    public PageInfo<EnquiryBills> find(int pageNum, int pageSize) {
        List<EnquiryBills> list = getSqlSession().selectList("com.pieces.dao.EnquiryBillsMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.EnquiryBillsMapper.deleteById", id);
    }

    @Override
    public int create(EnquiryBills enquiryBills) {
        return getSqlSession().insert("com.pieces.dao.EnquiryBillsMapper.create", enquiryBills);
    }

    @Override
    public int update(EnquiryBills enquiryBills) {
        return getSqlSession().update("com.pieces.dao.EnquiryBillsMapper.update", enquiryBills);
    }

    public PageInfo<EnquiryBills> findByCommoditys(int pageNum, int pageSize, EnquiryRecordVo enquiryRecordVo) {
        List<EnquiryBills> list = getSqlSession().selectList("com.pieces.dao.EnquiryBillsMapper.findByCommoditys", enquiryRecordVo, new RowBounds(pageNum, pageSize));
        for (EnquiryBills enquiryBills : list) {
            List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysDao.findByBillId(enquiryBills.getId(), 10);
            enquiryBills.setEnquiryCommoditys(enquiryCommoditysList);
        }
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public PageInfo<EnquiryBillsVO> findByParam(EnquiryBillsVO enquiryBillsVO, int pageNum, int pageSize) {
        List<EnquiryBillsVO> list = getSqlSession().selectList("com.pieces.dao.EnquiryBillsMapper.findByParam", enquiryBillsVO, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public EnquiryBillsVO findVOById(Integer id) {
        return getSqlSession().selectOne("com.pieces.dao.EnquiryBillsMapper.findVOById", id);
    }
}
