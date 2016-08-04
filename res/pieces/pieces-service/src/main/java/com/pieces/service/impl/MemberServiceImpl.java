package com.pieces.service.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.MemberDao;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.Resources;
import com.pieces.dao.model.RoleMember;
import com.pieces.dao.vo.MemberVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.MemberService;
import com.pieces.service.RoleMemberService;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangbin on 2016/7/7.
 */
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl extends AbsCommonService<Member> implements MemberService{

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private RoleMemberService roleMemberService;

    @Override
    public ICommonDao<Member> getDao() {
        return memberDao;
    }

    @Override
    public Member findByUsernameAndPassword(String username, String passwords) {
        return null;
    }


    @Override
    public Member findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    @Override
    @Transactional
    public int addMember(Member member) {
        Password pass = EncryptUtil.PiecesEncode(member.getPassword());
        member.setPassword(pass.getPassword());
        member.setSalt(pass.getSalt());
        member.setCreateDate(new Date());
        create(member);
        return member.getId();
    }

    @Override
    @Transactional
    public int updateMember(Member member) {
        if (StringUtils.isNotBlank(member.getPassword())) {
            Password pass = EncryptUtil.PiecesEncode(member.getPassword());
            member.setPassword(pass.getPassword());
            member.setSalt(pass.getSalt());
        }
        member.setUpdateDate(new Date());
        return this.update(member);
    }

    @Override
    public PageInfo<Member> findByCondition(MemberVo memberVo, Integer pageNum, Integer pageSize) {
        PageInfo<Member> page =  memberDao.findByCondition(memberVo,pageNum,pageSize);
        return page;
    }


    @Override
    public Set<String> findPermissionByUsername(String username) {
        List<Resources> resourcesList =  memberDao.findResourcesByUserName(username);
        Set<String> set = new HashSet<>();
        for(Resources resources : resourcesList){
            if(resources!=null&&StringUtils.isNotBlank(resources.getPermission())){
                set.add(resources.getPermission());
            }
        }
        return set;
    }


}
