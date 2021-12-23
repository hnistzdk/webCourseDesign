package com.zdk.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 9:57
 */
@WebListener
public class SessionListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        String attributeName = event.getName();
        if (attributeName.contains("userInfo")){
            ServletContext context = event.getSession().getServletContext();
            //放入servletContext
            context.setAttribute(attributeName, session.getAttribute(attributeName));
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        if (attributeName.contains("userInfo")){
            ServletContext context = event.getSession().getServletContext();
            //放入servletContext
            context.removeAttribute(attributeName);
        }
    }
}
