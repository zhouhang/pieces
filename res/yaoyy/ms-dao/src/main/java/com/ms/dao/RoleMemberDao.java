package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.RoleMember;
import com.ms.dao.vo.MemberVo;

import java.util.List;

@AutoMapper
public interface RoleMemberDao extends ICommonDao<RoleMember>{

    List<RoleMember> findByMember(Integer memberId);

    int deleteByMember(Integer memberId);

    List<RoleMember> findByCondition();

    List<RoleMember> findByRole(Integer roleId);

    int deleteByRole(Integer roleId);

    List<RoleMember> findByConditionAndRole(MemberVo memberVo);
}
