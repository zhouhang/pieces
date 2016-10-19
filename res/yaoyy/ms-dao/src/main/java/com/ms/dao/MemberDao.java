package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Member;
import com.ms.dao.model.Resources;
import com.ms.dao.vo.MemberVo;

import java.util.List;

@AutoMapper
public interface MemberDao extends ICommonDao<Member>{


	Member findByUsername(String username);

	List<Member> findByCondition(MemberVo memberVo);

	List<Resources> findResourcesByUserName(String username);


}
