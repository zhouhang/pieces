package com.pieces.dao;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.Resources;
import com.pieces.dao.vo.MemberVo;

import java.util.List;

public interface MemberDao extends ICommonDao<Member>{

	Member  findByUsernameAndPassword(String username,String password);

	Member findByUsername(String username);

	PageInfo<Member> findByCondition(MemberVo memberVo, Integer pageNum, Integer pageSize);

	List<Resources> findResourcesByUserName(String username);
}
