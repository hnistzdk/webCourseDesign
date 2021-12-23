package com.zdk.service.user;

import com.zdk.utils.ApiResponse;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:49
 */
public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ApiResponse login(String username, String password);

    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    ApiResponse queryUser(String username);

    /**
     * 注册
     * @param username
     * @param password
     * @param trueName
     * @return
     */
    ApiResponse register(String username,String password,String trueName);
}
