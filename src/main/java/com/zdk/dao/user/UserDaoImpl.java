package com.zdk.dao.user;

import com.zdk.dao.BaseDao;
import com.zdk.dao.BaseDaoImpl;
import com.zdk.pojo.User;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:37
 */
public class UserDaoImpl implements UserDao {
    private UserDaoImpl() {
    }

    private static final UserDao userDao = new UserDaoImpl();
    public static UserDao getInstance(){
        return userDao;
    }


    private final BaseDao baseDao = BaseDaoImpl.getInstance();

    @Override
    public User login(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        return baseDao.queryRow(User.class, sql, username,password);
    }

    @Override
    public User queryUser(String username) {
        String sql = "select * from user where username = ?";
        return baseDao.queryRow(User.class, sql, username);
    }
}
