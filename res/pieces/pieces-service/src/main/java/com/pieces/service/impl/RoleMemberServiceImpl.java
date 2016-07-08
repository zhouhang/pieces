package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleMemberDao;
import com.pieces.dao.model.RoleMember;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleMemberService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangbin on 2016/7/8.
 */
public class RoleMemberServiceImpl  extends AbsCommonService<RoleMember> implements RoleMemberService {

    @Autowired
    private RoleMemberDao roleMemberDao;


    @Override
    public ICommonDao<RoleMember> getDao() {
        return roleMemberDao;
    }

}
