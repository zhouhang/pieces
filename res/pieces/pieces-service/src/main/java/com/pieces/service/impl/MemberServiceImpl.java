package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.MemberDao;
import com.pieces.dao.model.Member;
import com.pieces.service.AbsCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangbin on 2016/7/7.
 */
@Transactional
public class MemberServiceImpl extends AbsCommonService<Member>{

    @Autowired
    private MemberDao memberDao;



    @Override
    public ICommonDao<Member> getDao() {
        return memberDao;
    }
}
