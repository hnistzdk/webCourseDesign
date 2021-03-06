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
@WebServlet(name = "userController",urlPatterns = {"/user","/user/*"})
public class UserController extends BaseController{
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if (url.contains("/user/logout")){
            logout(req);
            resp.sendRedirect("/page/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = getStr(req, "requestMethod");
        if (isOk(requestMethod) && requestMethod.equals("login")){
            login(req, resp);
        }
        String url = req.getRequestURL().toString();
        //注册
        if (url.contains("/user/register")){
            returnJson(resp, register(req));
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
        String userSessionKey = ip+":userInfo";
        session.setAttribute(userSessionKey,user);
        returnJson(resp, loginRes);
        return;
    }

    /**
     * 注销
     * @param req
     */
    private void logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        String ip = IpKit.getIpAddressByRequest(req);
        String userSessionKey = ip+":userInfo";
        session.removeAttribute(userSessionKey);
    }

    /**
     * 注册
     * @param req
     */
    private ApiResponse register(HttpServletRequest req) {
        String username = getStr(req, "username");
        String password = getStr(req, "password");
        String trueName = getStr(req, "trueName");
        return userService.register(username, password, trueName);
    }
}
