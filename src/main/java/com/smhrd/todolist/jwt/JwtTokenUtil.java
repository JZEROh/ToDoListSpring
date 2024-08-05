package com.smhrd.todolist.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "secret";

    public static String generateToken(String username) {   // 토큰 생성
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY)
                .compact();

    }

    public static String validateToken(String token) {  // 토큰을 검증하고 사용자명 반환
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
