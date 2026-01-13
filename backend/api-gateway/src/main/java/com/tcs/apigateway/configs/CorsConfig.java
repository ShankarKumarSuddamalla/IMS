package com.tcs.apigateway.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration

//Purpose is to handle Cross Origin Resource Sharing(Cors),
//Allowing backend to to accept requests from different origin(localhost:4200)

public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        //Allows the browser to send cookies 
        //or authentication headers (like Authorization) along with the request.
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        //Allows all HTTP headers (e.g., Content-Type, Authorization).
        config.addAllowedMethod("*");
        config.addAllowedMethod("*");
        //Allows all HTTP methods (GET, POST, PUT, DELETE, etc.).

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        //Applies the rules (config) to all paths (/**) entering the API Gateway.

        return new CorsWebFilter(source);
        //Creates the filter that intercepts every Incoming request.
    }
}
