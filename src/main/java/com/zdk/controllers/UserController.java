package com.zdk.controllers;

import cn.hutool.core.convert.Convert;
import com.zdk.pojo.User;
import com.zdk.service.user.UserService;
import com.zdk.service.user.UserServiceImpl;
import com.zdk.utils.ApiResponse;
import com.zdk.utils.IpKit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:26
 * 登录、注册等
 */
@WebServlet(name = "userController",urlPatterns = "/user")
public class UserController extends BaseController{
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = getStr(req, "requestMethod");
        if (requestMethod.equals("login")){
            login(req, resp);
        }
    }

    /**
     * 登录接口
     * @param req
     * @param resp
     * @return
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = getStr(req, "username");
        String password = getStr(req, "password");
        if (notOk(username) || notOk(password)){
            returnJson(resp, ApiResponse.fail("参数错误"));
            return;
        }
        ApiResponse loginRes = userService.login(username, password);
        if (loginRes.isFail()){
            returnJson(resp, loginRes);
            return;
        }
        User user = Convert.convert(User.class, loginRes.getData());
        HttpSession session = req.getSession();
        String ip = IpKit.getIpAddressByRequest(req);
        String userSessionKey = ip+":"+user.getUsername();
        session.setAttribute(userSessionKey,user);
        returnJson(resp, loginRes);
        return;
    }

    /**
     * 注册接口
     * @param req
     * @param resp
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) {

    }
}
