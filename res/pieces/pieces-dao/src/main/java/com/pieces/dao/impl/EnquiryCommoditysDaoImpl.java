package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.model.EnquiryCommoditys;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class EnquiryCommoditysDaoImpl extends BaseDaoImpl implements EnquiryCommoditysDao{

        @Override
        public EnquiryCommoditys findById(int id) {
            return getSqlSession().selectOne("com.pieces.dao.EnquiryCommoditysMapper.findById", id);
        }


        @Override
        public List<EnquiryCommoditys> findAll() {
            return getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findAll");
        }

        @Override
        public PageInfo<EnquiryCommoditys> find(int pageNum, int pageSize) {
            List<EnquiryCommoditys> list = getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findAll", null,new RowBounds(pageNum, pageSize));
            PageInfo page = new PageInfo(list);
            return page;
        }

        @Override
        public int deleteById(int id) {
            return getSqlSession().delete("com.pieces.dao.EnquiryCommoditysMapper.deleteById",id);
        }

        @Override
        public int create(EnquiryCommoditys enquiryCommoditys) {
            return getSqlSession().insert("com.pieces.dao.EnquiryCommoditysMapper.create",enquiryCommoditys);
        }

        @Override
        public int update(EnquiryCommoditys enquiryCommoditys) {
            return getSqlSession().update("com.pieces.dao.EnquiryCommoditysMapper.update",enquiryCommoditys);
        }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer billId, Integer pageSize) {
        List<EnquiryCommoditys> list = null;
        if(pageSize==null){
           list = getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findByBillId", billId);
        }else{
           list = getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findByBillId", billId,new RowBounds(0, pageSize));
        }
        return list;
    }

    @Override
    public List<EnquiryCommoditys> findByBillId(Integer userId, Integer billId, Integer pageSize) {
        List<EnquiryCommoditys> list = null;
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("billId",billId);

        if(pageSize==null){
            list = getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findByUserAndBillId", param);
        }else{
            list = getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findByUserAndBillId", param,new RowBounds(0, pageSize));
        }
        return list;
    }

    @Override
    public Integer quotedUpdate(List<EnquiryCommoditys> list) {
        return getSqlSession().update("com.pieces.dao.EnquiryCommoditysMapper.quotedUpdate",list);
    }

    @Override
    public void deleteByBillId(Integer billId) {
         getSqlSession().delete("com.pieces.dao.EnquiryCommoditysMapper.deleteByBillId",billId);
    }

    @Override
    public List<EnquiryCommoditys> findCommoditysByUser(String userId) {
        return getSqlSession().selectList("com.pieces.dao.EnquiryCommoditysMapper.findCommoditysByUser",userId);
    }
}
