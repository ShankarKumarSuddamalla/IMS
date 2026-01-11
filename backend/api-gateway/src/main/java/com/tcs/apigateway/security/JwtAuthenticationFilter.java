package com.tcs.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        HttpMethod method = exchange.getRequest().getMethod();

        System.out.println("Path: " + path + " | Method: " + method);

        // 🔓 PUBLIC ENDPOINTS
        if (path.startsWith("/auth-service/auth")) {
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

        /* ================= USER SERVICE ================= */

        // ADMIN ONLY → view all users
        if (path.equals("/user-service/users/customers") && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        if (path.equals("/user-service/users/suppliers") && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
     // allow internal calls
        if (path.startsWith("/user-service/internal")) {
            return chain.filter(exchange);
        }

        // admin only
        if (path.startsWith("/user-service/users")
                && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        if (path.startsWith("/user-service/internal")) {
            return chain.filter(exchange);
        }

        /* ================= PRODUCT SERVICE ================= */

        // READ products → ADMIN, CUSTOMER, SUPPLIER
        if (path.startsWith("/product-service/products") && method == HttpMethod.GET) {
            if (!isAnyRole(role, "ADMIN", "CUSTOMER", "SUPPLIER")) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }

        // WRITE products → ADMIN ONLY
        if (path.startsWith("/product-service/products")
                && (method == HttpMethod.POST
                || method == HttpMethod.PUT
                || method == HttpMethod.DELETE)) {

            if (!"ADMIN".equals(role)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }

        /* ================= CATEGORY SERVICE ================= */

        // VIEW categories → ALL ROLES
        if (path.startsWith("/product-service/categories") && method == HttpMethod.GET) {
            if (!isAnyRole(role, "ADMIN", "CUSTOMER", "SUPPLIER")) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }

        // ADD category → ADMIN ONLY
        if (path.startsWith("/product-service/categories") && method == HttpMethod.POST) {
            if (!"ADMIN".equals(role)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }
     // ADMIN – update stock
        if (path.equals("/inventory-service/inventory/update") && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

     // CUSTOMER – place order
        if (path.equals("/order-service/orders")
                && method == HttpMethod.POST
                && !"CUSTOMER".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // CUSTOMER – view own orders
        if (path.equals("/order-service/orders/my")
                && method == HttpMethod.GET
                && !"CUSTOMER".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // ADMIN – view all orders
        if (path.equals("/order-service/orders")
                && method == HttpMethod.GET
                && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
     // SUPPLIER – supply stock
        if (path.equals("/supplier-service/suppliers/supply")
                && !"SUPPLIER".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // SUPPLIER – view own supplies
        if (path.equals("/supplier-service/suppliers/supplies")
                && !"SUPPLIER".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // ADMIN – view all supplies
        if (path.equals("/supplier-service/suppliers/all-supplies")
                && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        if (path.startsWith("/report-service")
                && !"ADMIN".equals(role)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }





        /* ================= FORWARD USER INFO ================= */

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header("X-USER-EMAIL", email)
                        .header("X-USER-ROLE", role)
                        .build())
                .build();

        return chain.filter(mutatedExchange);

    }

    private boolean isAnyRole(String role, String... roles) {
        for (String r : roles) {
            if (r.equals(role)) return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
