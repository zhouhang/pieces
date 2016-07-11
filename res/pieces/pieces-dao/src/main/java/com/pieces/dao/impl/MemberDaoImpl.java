package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.MemberDao;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.MemberVo;
import com.pieces.dao.vo.Params;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberDaoImpl extends BaseDaoImpl implements MemberDao {

    @Override
    public Member findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.MemberMapper.findById", id);
    }


    @Override
    public List<Member> findAll() {
        return getSqlSession().selectList("com.pieces.dao.MemberMapper.findAll");
    }

    @Override
    public PageInfo<Member> find(int pageNum, int pageSize) {
        List<Member> list = getSqlSession().selectList("com.pieces.dao.MemberMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.MemberMapper.deleteById", id);
    }

    @Override
    public int create(Member member) {
        return getSqlSession().insert("com.pieces.dao.MemberMapper.create", member);
    }

    @Override
    public int update(Member member) {
        return getSqlSession().update("com.pieces.dao.MemberMapper.update", member);
    }

    @Override
    public Member findByUsernameAndPassword(String username, String password) {
        Member member =  getSqlSession().selectOne("com.pieces.dao.MemberMapper.findByUsernameAndPassword",new Params("username",username).add("password",password));
        return member;
    }

    @Override
    public Member findByUsername(String username) {
        Member member =  getSqlSession().selectOne("com.pieces.dao.MemberMapper.findByUsername",username);
        return member;
    }

    @Override
    public PageInfo<Member> findByCondition(MemberVo memberVo, Integer pageNum, Integer pageSize) {
        List<Member> list = getSqlSession().selectList("com.pieces.dao.MemberMapper.findByCondition", memberVo,new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }
}
