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

    @Override
    public ApiResponse register(String username, String password, String trueName) {
        if (notOk(username)||notOk(password)||notOk(trueName)){
            return ApiResponse.fail("参数错误");
        }
        User user = userDao.queryUser(username);
        if (user != null){
            return ApiResponse.fail("该用户名已存在,请重新填写");
        }
        return ApiResponse.result(userDao.register(username, password, trueName)>0,"注册失败");
    }
}
