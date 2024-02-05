package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String SignKey = "itheima";  //设置签名密钥
    private static Long expire = 12*3600*1000L;  //设置过期时间为12小时之后

    /**
     * 生成Jwt令牌
     */
    public static String generateJwt(Map<String , Object> claims) {
        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256 , "itheima") //设置签名算法和密钥
                .setClaims(claims)  //设置有效载荷
                .setExpiration(new Date(System.currentTimeMillis() + 12*3600*1000)) //设置过期时间
                .compact();
        return Jwt;
    }

    /**
     * 解析Jwt令牌
     */
    public static Claims parseJwt(String Jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")   //设置密钥
                .parseClaimsJws(Jwt)    //解析
                .getBody();

        return claims;
    }
}
