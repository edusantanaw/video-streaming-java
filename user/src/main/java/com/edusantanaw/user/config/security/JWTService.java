package com.edusantanaw.user.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;
import io.jsonwebtoken.Claims;


@Service
public class JWTService {
    @Value("${jwt.secret}")
    protected String secret;

    JWTService() {
    }

    public String generate(UserDetails userDetails) {
        return generate(new HashMap<>(), userDetails);
    }

    public String generate(Map<String, Object> claims, UserDetails userDetails) {
        long currentTime = System.currentTimeMillis();
        int ONE_DAY_MILLISECONDS = 1000 * 60 * 24;
        long expireAt = currentTime * ONE_DAY_MILLISECONDS;
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expireAt))
                .signWith(signInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        System.out.println();
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signInKey() {
        System.out.println(secret);
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
