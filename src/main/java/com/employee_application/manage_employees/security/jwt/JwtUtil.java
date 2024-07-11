package com.employee_application.manage_employees.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.employee_application.manage_employees.exception.AuthenticationException;
import com.employee_application.manage_employees.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    @Value("${spring.security.secretkey-value}")
    private String SECRET_KEY;
    
    @Value("${spring.security.expiration-value}")
    private long EXPIRATION_TIME;
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    private boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date());
    }
    
    public String generateToken(Object userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserDetails) {
            return createToken(claims, ((UserDetails) userDetails).getUsername());
        } else {
            throw new IllegalArgumentException("Invalid user details type");
        }
    }
    
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if (!username.equals(userDetails.getUsername())) {
            throw new AuthenticationException("Invalid user");
        }
        if (isTokenExpired(token)) {
            throw new TokenExpiredException("Your JWT has expired");
        }
        return true;
    }
}
