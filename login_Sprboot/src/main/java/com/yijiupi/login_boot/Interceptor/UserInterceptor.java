package com.yijiupi.login_boot.Interceptor;

import com.yijiupi.login_boot.pojo.UserPO;
import com.yijiupi.login_boot.util.CommonUtil;
import org.apache.catalina.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor{


    /** 定义不需要拦截的请求 */
    private static final String[] IGNORE_URI = {"/loginForm", "/login"};

    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //默认用户没有进行登录
        Boolean flag = false;
        //获取请求的ServletPath
        String servletPath = request.getServletPath();
        //判断是否需要拦截
        for (String s: IGNORE_URI){
            if (servletPath.contains(s)){
                flag = true;
                break;
            }
        }

        //拦截请求
        if (!flag){
            //获取session中的用户
            UserPO userPO = (UserPO)request.getSession().getAttribute(CommonUtil.USER_SESSION);
            //判断用户是否已经登录，跳转到登录页面
            if (null == userPO){
                request.setAttribute("message", "请先登录再访问");
                request.getRequestDispatcher(CommonUtil.LOGIN).forward(request, response);
                return flag;
            }else {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object handler, ModelAndView mv) throws Exception {

    }


    /**
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object handler, Exception e) throws Exception {


    }
}
