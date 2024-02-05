package com.itheima;

import com.aliyun.oss.common.auth.HmacSHA256Signature;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class TliasApplicationTests {

    @Test
    public void textUUID() {

        for (int i = 0; i < 1000; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }
    }

    /**
     * 生成Jwt令牌
     */
    @Test
    public void testGenJwt() {
        Map<String , Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name" ,"tom");

        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256 , "itheima")  //签名算法跟密钥
                .setClaims(claims)  //设置自定义参数
                .setExpiration(new Date(System.currentTimeMillis() + 12*3600*1000))   //设置令牌截至有效期
                .compact();

        System.out.println(Jwt);

    }

    /**
     * 解析Jwt令牌
     */
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")   //设置密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcwNTk3NjU1NH0.80CaksBVcQAr_fpQMufMAB1SIeCmiWEO2grenSoE-xc")
                .getBody();

        System.out.println(claims);
    }

}
