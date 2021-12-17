package com.zdk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/17 13:28
 */
@WebFilter(filterName = "charsetFilter",urlPatterns = "/*")
public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //禁止页面缓存
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        //单独处理js中文乱码
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        int i = url.lastIndexOf(".");
        if (i != -1){
            String type = url.substring(i);
            System.out.println("type = " + type);
            if (type.equals(".js")){
                servletResponse.setContentType("application/javascript");
            }
        }else{
            servletRequest.setCharacterEncoding("utf-8");
            servletResponse.setCharacterEncoding("utf-8");
            servletResponse.setContentType("text/html;charset=utf-8");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
