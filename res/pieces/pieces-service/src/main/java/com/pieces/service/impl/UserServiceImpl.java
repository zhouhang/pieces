package com.pieces.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.pieces.dao.enums.CertifyStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserDao;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserService;
import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.service.utils.ValidUtils;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbsCommonService<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findUserByCondition(User user) {
        return userDao.findUserByCondition(user);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int addUser(User user) {
        createPwdAndSaltMd5(user);
        user.setIsDel(false);
        user.setOnlineStatus(false);
        user.setBindErp(false);
        user.setCreateTime(new Date());
        user.setCertifyStatus(CertifyStatusEnum.NOT_CERTIFY.getValue());
        //user.setSource(0);
        return this.create(user);

    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int updateUser(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            createPwdAndSaltMd5(user);
        }
        user.setUpdateTime(new Date());
        return this.update(user);
    }


    @Override
    public boolean ifExistMobile(String contactMobile) {
        User user = new User();
        user.setContactMobile(contactMobile);
        List<User> users = userDao.findUserByCondition(user);
        return (users != null && users.size() != 0);
    }

    @Override
    public boolean checkUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        List<User> users = userDao.findUserByCondition(user);
        return ValidUtils.listNotBlank(users);
    }


    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public boolean checkMobileCode(String targetMobileCode) {
        return false;
    }

    @Override
    public PageInfo<User> findByCondition(UserVo userVo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.findByCondition(userVo);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    @Transactional
    public int updateUserByCondition(User user) {
        return userDao.updateUserByCondition(user);
    }

    @Override
    public PageInfo<User> findUserByVagueCondition(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.findUserByVagueCondition(user);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public ICommonDao<User> getDao() {
        return userDao;
    }

    /**
     * 生成密码加盐
     *
     * @param user
     * @return
     */
    public User createPwdAndSaltMd5(User user) {
        Password pass = EncryptUtil.PiecesEncode(user.getPassword());
        user.setPassword(pass.getPassword());
        user.setSalt(pass.getSalt());
        return user;
    }

    /**
     * 得到密码加盐
     *
     * @param user
     * @return
     */
    public User getPwdAndSaltMd5(User user) {
        Password pass = EncryptUtil.PiecesEncode(user.getPassword(), user.getSalt());
        user.setPassword(pass.getPassword());
        user.setSalt(pass.getSalt());
        return user;
    }

}
