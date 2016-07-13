package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CodeTypeDao;
import com.pieces.dao.model.CodeType;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CodeTypeDaoImpl extends BaseDaoImpl implements CodeTypeDao{

        @Override
        public CodeType findById(int id) {
            return getSqlSession().selectOne("com.pieces.dao.CodeTypeMapper.findById", id);
        }


        @Override
        public List<CodeType> findAll() {
            return getSqlSession().selectList("com.pieces.dao.CodeTypeMapper.findAll");
        }

        @Override
        public PageInfo<CodeType> find(int pageNum, int pageSize) {
            List<CodeType> list = getSqlSession().selectList("com.pieces.dao.CodeTypeMapper.findAll", null,new RowBounds(pageNum, pageSize));
            PageInfo page = new PageInfo(list);
            return page;
        }

        @Override
        public int deleteById(int id) {
            return getSqlSession().delete("com.pieces.dao.CodeTypeMapper.deleteById",id);
        }

        @Override
        public int create(CodeType codeType) {
            return getSqlSession().insert("com.pieces.dao.CodeTypeMapper.create",codeType);
        }

        @Override
        public int update(CodeType codeType) {
            return getSqlSession().update("com.pieces.dao.CodeTypeMapper.update",codeType);
        }

}
