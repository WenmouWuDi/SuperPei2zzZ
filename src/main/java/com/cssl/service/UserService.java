package com.cssl.service;

import com.cssl.vo.UserVo;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
public interface UserService {

    public UserVo userLogin(UserVo userVo);

    public int xiuOnline(UserVo userVo);

    public int userRegister(UserVo userVo);

    public int isExist(String username);

    public int isTouPiao(int uid,int sid);

    public int isAdmin(int uid);

    public int updateOnline(UserVo userVo);

    public int isOnline(int uid1);
}
