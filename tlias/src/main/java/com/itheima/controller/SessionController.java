package com.itheima.controller;

import com.itheima.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cookie , HttpSession演示
 */
@Slf4j
@RestController
public class SessionController {

    //设置Cookie
    @GetMapping("c1")
    public Result setCookie(HttpServletResponse response) {

        response.addCookie(new Cookie("login_username","itheima"));

        return Result.success();
    }

    //获取Cookie
    @GetMapping("c2")
    public Result getCookie(HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            if(cookie.getName().equals("login_username")) {
                System.out.println("login_username" + cookie.getValue());
            }
        }

        return Result.success();
    }

    //往HttpSession中存储值
    @GetMapping("/s1")
    public Result setSession(HttpSession session) {
        log.info("HttpSession-s1：{}",session.hashCode());

        session.setAttribute("loginUser","tom");

        return Result.success();
    }

    //从HttpSession中获取值
    @GetMapping("/s2")
    public Result GetSession(HttpServletRequest request) {

        HttpSession session = request.getSession();
        log.info("HttpSession-s2：{}",session.hashCode());

        Object loginUser = session.getAttribute("loginUser");

        log.info("loginUser：{}",loginUser);

        return Result.success();
    }
}
