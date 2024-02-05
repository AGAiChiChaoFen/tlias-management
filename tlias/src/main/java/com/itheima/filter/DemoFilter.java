package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {   //初始化方法，只执行一次
        System.out.println("init 初始化方法执行...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {    //拦截到请求时调用，执行多次
        System.out.println("Demo过滤器拦截到了请求...放行前的逻辑");

        filterChain.doFilter(servletRequest,servletResponse);   //放行

        System.out.println("Demp过滤器拦截到了请求...放行后的逻辑");
    }

    @Override
    public void destroy() { //销毁时执行，执行一次
        System.out.println("destory 销毁方法执行...");
    }
}
