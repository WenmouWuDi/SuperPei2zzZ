package com.cssl.intercetor;

import com.cssl.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class myAuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlpath = request.getRequestURI();
        if(urlpath.endsWith("login.html") || urlpath.endsWith("regist.html") || urlpath.endsWith("User-login.action")){
            return true;
        }
        HttpSession session = request.getSession();
        UserVo userVo = (UserVo) session.getAttribute("user");
        if(userVo == null){
            response.sendRedirect("login.html");
            return false;
        }
        return true;
    }
}
