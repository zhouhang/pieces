package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.MemberDao;
import com.pieces.dao.model.Member;
import com.pieces.service.AbsCommonService;
import com.pieces.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangbin on 2016/7/7.
 */
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl extends AbsCommonService<Member> implements MemberService{

    @Autowired
    private MemberDao memberDao;

    @Override
    public ICommonDao<Member> getDao() {
        return memberDao;
    }

    @Override
    public Member findByUsernameAndPassword(String username, String passwords) {
        return null;
    }


    @Override
    public Member findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

}
