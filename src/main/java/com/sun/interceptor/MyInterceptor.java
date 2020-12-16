package com.sun.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    //在请求处理的方法之前执行
    //如果返回true执行下一个拦截器
    //如果返回false就不执行下一个拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("uri:" + requestURI);
        //如果是登录页面则放行
        if (requestURI.contains("login")||requestURI.contains("toRegistered")||requestURI.contains("registeredUser")) {
            return true;
        }
        //如果是已经登录的用户则放行
        if (request.getSession().getAttribute("userName") != null) {
            System.out.println("用户已经登录!");
            return true;
        }
            //不是登录页面，也没有登录，则强制跳转到登录页面
            request.setAttribute("login_msg","尚未登录，请登录！");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;

}

    //在请求处理方法执行之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //在dispatcherServlet处理后执行,做清理工作.
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
