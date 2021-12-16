package com.zdk.service.user;

import com.zdk.pojo.User;
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
}
