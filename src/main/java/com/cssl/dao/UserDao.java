package com.cssl.dao;

import com.cssl.vo.UserVo;
import org.springframework.stereotype.Repository;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@Repository
public interface UserDao {
    //用户登陆
    public UserVo userLogin(UserVo userVo);
    //修改用户在线状态
    public int xiuOnline(UserVo userVo);
    //注册用户
    public int userRegister(UserVo userVo);
    //判断用户名是否存在
    public int isExist(String username);
    //判断用户是否投票
    public int isTouPiao(int uid,int sid);
    //判断用户是否是管理员
    public int isAdmin(int uid);

    public int updateOnline(UserVo userVo);

    public int isOnline(int uid1);
}
