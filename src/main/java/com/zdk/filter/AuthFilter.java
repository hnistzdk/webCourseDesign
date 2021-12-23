package com.zdk.filter;

import com.zdk.utils.IpKit;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zdk
 * @date 2021/12/17 19:41
 */
@WebFilter(filterName = "BFilter",urlPatterns = {"/page/index.jsp","/card/list"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String ip = IpKit.getIpAddressByRequest(request);
        String sessionKey = ip+":userInfo";
        //在servletContext中查询用户是否登录
        Object user = request.getServletContext().getAttribute(sessionKey);
//        Object user = request.getSession().getAttribute(sessionKey);
        if (user == null) {
            request.setAttribute("notLoginMsg", "请先登录");
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
        }
        filterChain.doFilter(request, response);
    }
}
