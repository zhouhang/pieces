package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.Resources;
import com.pieces.dao.vo.MemberVo;

import java.util.List;

@AutoMapper
public interface MemberDao extends ICommonDao<Member>{


	Member findByUsername(String username);

	List<Member> findByCondition(MemberVo memberVo);

	List<Resources> findResourcesByUserName(String username);
}
