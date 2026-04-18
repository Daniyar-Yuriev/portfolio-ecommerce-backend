package com.daniyar.ecommerce.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

//    @Value("${jwt.secret}")
//    private String secretKey;  // Secret key for signing JWT (set in application.properties)

    private long validityInMilliseconds = 3600000; // 1 hour

//    // Create a JWT token
//    public String createToken(String username) {
//        Claims claims = Jwts.claims().setSubject(username);
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    // Parse the JWT token and extract claims (username)
//    public String parseToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    // Check if the token has expired
//    public boolean isTokenExpired(String token) {
//        Date expiration = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//        return expiration.before(new Date());
//    }
}