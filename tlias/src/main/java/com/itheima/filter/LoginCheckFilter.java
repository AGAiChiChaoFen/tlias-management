package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //讲参数强转为HttpServlet...
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求的url
        String url = request.getRequestURI().toString();
        log.info("请求路径：{}",url);

        //2.判断请求的url中是否包含login,如果有，则放行
        if(url.contains("login")) {
            log.info("登陆操作，放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }

        //3.获取请求头中的令牌token
        String Jwt = request.getHeader("token");        //请求头中的token就是令牌


        //4.判断令牌是否存在，若不存在则说明未登录，返回错误结果
        if(!StringUtils.hasLength(Jwt)) {   //调用String工具类，如果令牌有长度，则存在
            log.info("请求头token为空：返回未登陆信息");

            Result error = Result.error("NOT_LOGIN");//在RestController中会自动讲对象转成json格式，但这里不能，要用工具类
            String not_login = JSONObject.toJSONString(error);

            response.getWriter().write(not_login);

            return ;
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

            return ;
        }

        //6，放行
        log.info("令牌合法，放行...");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
