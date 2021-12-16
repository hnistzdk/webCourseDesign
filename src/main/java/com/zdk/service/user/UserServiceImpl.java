package com.zdk.service.user;

import com.zdk.dao.user.UserDao;
import com.zdk.dao.user.UserDaoImpl;
import com.zdk.pojo.User;
import com.zdk.service.BaseService;
import com.zdk.utils.ApiResponse;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:50
 */
public class UserServiceImpl extends BaseService implements UserService {
    public UserServiceImpl() {
    }

    private static final UserService instance = new UserServiceImpl();
    public static UserService getInstance() {
        return instance;
    }

    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public ApiResponse login(String username, String password) {
        if (notOk(username)||notOk(password)){
            return ApiResponse.fail("账号或密码为空");
        }
        if (password.length()<4){
            return ApiResponse.fail("密码长度必须大于4");
        }
        User user = userDao.login(username, password);
        if (user != null) {
            //存入session session监听 存入cookie
            return ApiResponse.success(user,"登录成功");
        }
        return ApiResponse.fail("账号或密码错误");
    }

    @Override
    public ApiResponse queryUser(String username) {
        User user = userDao.queryUser(username);
        if (user == null){
            return ApiResponse.fail("用户不存在");
        }
        return ApiResponse.success(user);
    }
}
