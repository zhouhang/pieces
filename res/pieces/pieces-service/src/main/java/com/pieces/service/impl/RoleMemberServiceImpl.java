package com.pieces.service.impl;

import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleMemberDao;
import com.pieces.dao.model.RoleMember;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
@Service
@Transactional(readOnly = true)
public class RoleMemberServiceImpl  extends AbsCommonService<RoleMember> implements RoleMemberService {

    @Autowired
    private RoleMemberDao roleMemberDao;


    @Override
    public ICommonDao<RoleMember> getDao() {
        return roleMemberDao;
    }

    @Override
    public List<RoleMember> findByMemberId(Integer memberId) {
        return roleMemberDao.findByMember(memberId);
    }

    @Override
    public int deleteByMember(int memberId) {
        return roleMemberDao.deleteByMember(memberId);
    }

    @Override
    @Transactional
    public void createRoleMember(Integer[] roleIds, Integer memberId) {
        deleteByMember(memberId);
        for(Integer roleId : roleIds){
            RoleMember roleMember = new RoleMember();
            roleMember.setRoleId(roleId);
            roleMember.setMemberId(memberId);
            create(roleMember);
        }
    }

    @Override
    public List<RoleMember> findByCondition() {
        return roleMemberDao.findByCondition();
    }
}
