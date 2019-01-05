package com.cssl.intercetor;

import com.cssl.vo.UserVo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

@WebListener()
public class myListener implements ServletContextListener, HttpSessionListener {

    private ServletContext application;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session的创建");
        HttpSession session = se.getSession();
        session.setMaxInactiveInterval(60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        UserVo userVo = (UserVo) session.getAttribute("user");
        ArrayList<String> userVos = (ArrayList<String>) session.getAttribute("users");
        if(userVos.contains(userVo.getUsername())){
            userVos.remove(userVo);
        }
        System.out.println("session的销毁");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
        application.setAttribute("users",new ArrayList<String>());
        System.out.println("监听器contextInitialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听器contextDestroyed...");
    }
}
