package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Member;
import com.ms.dao.vo.MemberVo;

import java.util.Set;

/**
 * Created by wangbin on 2016/7/7.
 */
public interface MemberService extends ICommonService<Member>{


    Member findByUsernameAndPassword(String username, String passwords);

    Member findByUsername(String username);

    int addMember(Member member);

    int addMember(Member member,Integer roleId);

    int updateMember(Member member,Integer roleId);

    PageInfo<Member> findByCondition(MemberVo memberVo, Integer pageNum, Integer pageSize);

    Set<String> findPermissionByUsername(String username);

}
