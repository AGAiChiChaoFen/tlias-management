package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 */
@Slf4j
@Component
public class LoginCheckInterceptot implements HandlerInterceptor {

    //目标资源方法执行之前执行，返回true放行，返回false，不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler...");


        //1.获取请求的url
        String url = request.getRequestURI().toString();
        log.info("请求路径：{}",url);

        //2.判断请求的url中是否包含login,如果有，则放行
        if(url.contains("login")) {
            log.info("登陆操作，放行...");
            return true;
        }

        //3.获取请求头中的令牌token
        String Jwt = request.getHeader("token");        //请求头中的token就是令牌


        //4.判断令牌是否存在，若不存在则说明未登录，返回错误结果
        if(!StringUtils.hasLength(Jwt)) {   //调用String工具类，如果令牌有长度，则存在
            log.info("请求头token为空：返回未登陆信息");

            Result error = Result.error("NOT_LOGIN");//在RestController中会自动讲对象转成json格式，但这里不能，要用工具类
            String not_login = JSONObject.toJSONString(error);

            response.getWriter().write(not_login);

            return false;
        }

        //5.解析token，如果解析失败，则返回错误信息
        try {
            JwtUtils.parseJwt(Jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回未登录信息");

            Result error = Result.error("NOT_LOGIN");//在RestController中会自动讲对象转成json格式，但这里不能，要用工具类
            String not_login = JSONObject.toJSONString(error);

            response.getWriter().write(not_login);

            return false;
        }

        //6，放行
        log.info("令牌合法，放行...");

        return true;
    }

    //目标方法执行之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("afterHandler...");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("PostHanlder...");
    }
}
