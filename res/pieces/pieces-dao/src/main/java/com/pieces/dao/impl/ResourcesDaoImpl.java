package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ResourcesDao;
import com.pieces.dao.model.Resources;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ResourcesDaoImpl extends BaseDaoImpl implements ResourcesDao{

        @Override
        public Resources findById(int id) {
            return getSqlSession().selectOne("com.pieces.dao.ResourcesMapper.findById", id);
        }


        @Override
        public List<Resources> findAll() {
            return getSqlSession().selectList("com.pieces.dao.ResourcesMapper.findAll");
        }

        @Override
        public PageInfo<Resources> find(int pageNum, int pageSize) {
            List<Resources> list = getSqlSession().selectList("com.pieces.dao.ResourcesMapper.findAll", null,new RowBounds(pageNum, pageSize));
            PageInfo page = new PageInfo(list);
            return page;
        }

        @Override
        public int deleteById(int id) {
            return getSqlSession().delete("com.pieces.dao.ResourcesMapper.deleteById",id);
        }

        @Override
        public int create(Resources resources) {
            return getSqlSession().insert("com.pieces.dao.ResourcesMapper.create",resources);
        }

        @Override
        public int update(Resources resources) {
            return getSqlSession().update("com.pieces.dao.ResourcesMapper.update",resources);
        }

}
