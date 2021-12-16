package com.zdk.dao.user;

import com.zdk.pojo.User;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:36
 */
public interface UserDao {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    User queryUser(String username);
}
