package com.zdk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zdk
 * @date 2021/12/17 19:41
 */
@WebFilter(filterName = "authFilter",urlPatterns = {"/page/index.jsp"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
