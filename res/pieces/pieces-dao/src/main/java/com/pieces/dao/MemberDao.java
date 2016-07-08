package com.pieces.dao;

import com.pieces.dao.model.Member;

public interface MemberDao extends ICommonDao<Member>{

	Member  findByUsernameAndPassword(String username,String password);

	Member findByUsername(String username);
}
