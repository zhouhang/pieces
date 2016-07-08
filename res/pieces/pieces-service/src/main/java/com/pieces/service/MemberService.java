package com.pieces.service;

import com.pieces.dao.model.Member;

/**
 * Created by wangbin on 2016/7/7.
 */
public interface MemberService extends ICommonService<Member>{


    Member findByUsernameAndPassword(String username,String passwords);

    Member findByUsername(String username);

}
