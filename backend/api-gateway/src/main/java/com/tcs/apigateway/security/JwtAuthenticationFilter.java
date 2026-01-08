package com.tcs.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        System.out.println("Path: "+path);
        // 🔓 PUBLIC ENDPOINTS
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        // 🔒 AUTHORIZATION HEADER REQUIRED
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.isTokenValid(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String role = jwtUtil.getRole(token);
        String email = jwtUtil.getEmail(token);

        // 🔐 ADMIN-ONLY: VIEW ALL USERS
        if (path.equals("/user-service/users")) {
            if (!"ADMIN".equals(role)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }

        // ✅ Forward user info to downstream services (optional but useful)
        exchange.getRequest().mutate()
                .header("X-USER-EMAIL", email)
                .header("X-USER-ROLE", role)
                .build();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
