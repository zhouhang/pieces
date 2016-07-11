package com.pieces.service;

import com.pieces.dao.model.RoleMember;

import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleMemberService extends ICommonService<RoleMember>{

    List<RoleMember> findByMemberId(Integer memberId);

    int deleteByMember(int memberId);

    void createRoleMember(Integer[] roleIds,Integer memberId);

    List<RoleMember> findByCondition();
}
