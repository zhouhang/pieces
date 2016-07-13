package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.vo.MemberVo;

import java.util.Set;

/**
 * Created by wangbin on 2016/7/7.
 */
public interface MemberService extends ICommonService<Member>{


    Member findByUsernameAndPassword(String username,String passwords);

    Member findByUsername(String username);

    int addMember(Member member);

    int updateMember(Member member);

    PageInfo<Member> findByCondition(MemberVo memberVo, Integer pageNum, Integer pageSize);

    Set<String> findPermissionByUsername(String username);

}
