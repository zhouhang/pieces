package com.pieces.dao;

import com.pieces.dao.model.RoleMember;

import java.util.List;

public interface RoleMemberDao extends ICommonDao<RoleMember>{

    List<RoleMember> findByMember(Integer memberId);

    int deleteByMember(Integer memberId);

    List<RoleMember> findByCondition();
}
