package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CodeDao;
import com.pieces.dao.model.Code;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CodeDaoImpl extends BaseDaoImpl implements CodeDao{

        @Override
        public Code findById(int id) {
            return getSqlSession().selectOne("com.pieces.dao.CodeMapper.findById", id);
        }


        @Override
        public List<Code> findAll() {
            return getSqlSession().selectList("com.pieces.dao.CodeMapper.findAll");
        }

        @Override
        public PageInfo<Code> find(int pageNum, int pageSize) {
            List<Code> list = getSqlSession().selectList("com.pieces.dao.CodeMapper.findAll", null,new RowBounds(pageNum, pageSize));
            PageInfo page = new PageInfo(list);
            return page;
        }

        @Override
        public int deleteById(int id) {
            return getSqlSession().delete("com.pieces.dao.CodeMapper.deleteById",id);
        }

        @Override
        public int create(Code code) {
            return getSqlSession().insert("com.pieces.dao.CodeMapper.create",code);
        }

        @Override
        public int update(Code code) {
            return getSqlSession().update("com.pieces.dao.CodeMapper.update",code);
        }


		@Override
		public List<Code> find(Code code) {
			return getSqlSession().selectList("com.pieces.dao.CodeMapper.find",code);
		}

}
