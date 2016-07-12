package com.pieces.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.MemberVo;

import java.util.List;

public interface RoleMemberDao extends ICommonDao<RoleMember>{

    List<RoleMember> findByMember(Integer memberId);

    int deleteByMember(Integer memberId);

    List<RoleMember> findByCondition();

    List<RoleMember> findByRole(Integer roleId);

    int deleteByRole(Integer roleId);

    PageInfo<RoleMember> findByConditionAndRole(MemberVo memberVo, Integer pageNum, Integer pageSize);
}
