package com.tcs.apigateway.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    //This method converts your 
    //plain-text secret string into a cryptographic Key object using the HS256
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //A client sends a token saying, "I claim to be an Admin."

    //The server checks the Signature.

    //If the signature is valid, the server "accepts" the claim and grants Admin access.

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }
}
