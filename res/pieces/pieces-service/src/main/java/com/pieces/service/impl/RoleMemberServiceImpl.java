package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RoleMemberDao;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.MemberVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RoleMemberService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(roleIds==null||roleIds.length==0){
            return;
        }
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

    @Override
    public List<RoleMember> findByRole(Integer roleId) {
        return roleMemberDao.findByRole(roleId);
    }

    @Override
    @Transactional
    public void updateRoleMember(Integer roleId, Integer[] memberIds) {

        List<RoleMember> roleMemberList = findByRole(roleId);
        List<Integer> memberIdList = new ArrayList<>();
        for(RoleMember roleMember : roleMemberList){
            memberIdList.add(roleMember.getMemberId());
        }
        for(Integer memberId : memberIds){
            if(!memberIdList.contains(memberId)){
                RoleMember newRoleMember = new RoleMember();
                newRoleMember.setRoleId(roleId);
                newRoleMember.setMemberId(memberId);
                create(newRoleMember);
            }
        }

    }

    @Override
    public int deleteByRole(int roleId) {
        return roleMemberDao.deleteByRole(roleId);
    }

    @Override
    public PageInfo<RoleMember> findByConditionAndRole(MemberVo memberVo, Integer pageNum, Integer pageSize) {
        PageInfo<RoleMember>  roleMemberPageInfo=   roleMemberDao.findByConditionAndRole(memberVo,pageNum,pageSize);
        return roleMemberPageInfo;
    }


}
