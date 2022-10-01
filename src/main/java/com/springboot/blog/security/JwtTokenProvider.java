package com.springboot.blog.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs; 
    
    public String generateToken(Authentication authentication) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());        
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate  = new Date(currentDate.getTime() + jwtExpirationInMs);
        
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
    }
    
    public String getUserNameFromJwt(String token) { 
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());    
        Jws<Claims> claimsJws = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();        
    }
    
    public boolean isValidated(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());     
        try {
            Jwts
            .parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                | IllegalArgumentException e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "token is not valid");           
        }
    }
}
