package com.cssl.service.impl;

import com.cssl.dao.UserDao;
import com.cssl.service.UserService;
import com.cssl.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int isExist(String username) {
        return userDao.isExist(username);
    }

    @Override
    public int isTouPiao(int uid, int sid) {
        return userDao.isTouPiao(uid,sid);
    }

    @Override
    public int updateOnline(UserVo userVo) {
        return userDao.updateOnline(userVo);
    }

    @Override
    public int isOnline(int uid1) {
        return userDao.isOnline(uid1);
    }

    @Override
    public int isAdmin(int uid) {
        return userDao.isAdmin(uid);
    }

    @Override
    public int userRegister(UserVo userVo) {
        return userDao.userRegister(userVo);
    }

    @Override
    public int xiuOnline(UserVo userVo) {
        return userDao.xiuOnline(userVo);
    }

    @Override
    public UserVo userLogin(UserVo userVo) {
        return userDao.userLogin(userVo);
    }
}
