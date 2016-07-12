package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.MemberVo;

import java.util.List;

/**
 * Created by wangbin on 2016/7/8.
 */
public interface RoleMemberService extends ICommonService<RoleMember>{

    List<RoleMember> findByMemberId(Integer memberId);

    int deleteByMember(int memberId);

    void createRoleMember(Integer[] roleIds,Integer memberId);

    List<RoleMember> findByCondition();

    List<RoleMember> findByRole(Integer roleId);

    void updateRoleMember(Integer roleId,Integer[] memberIds);

    int deleteByRole(int roleId);

    PageInfo<RoleMember> findByConditionAndRole(MemberVo memberVo,Integer pageNum, Integer pageSize);
}
