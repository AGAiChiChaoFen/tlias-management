package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录功能
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录功能：{}" , emp);

        Emp e = empService.login(emp);

        //登陆成功，生成Jwt令牌并下发
        if ( e != null) {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id" ,e.getId());
            claims.put("name" , e.getName());
            claims.put("username",e.getUsername());

            String Jwt = JwtUtils.generateJwt(claims);

            return Result.success(Jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误！！！");

    }
}
