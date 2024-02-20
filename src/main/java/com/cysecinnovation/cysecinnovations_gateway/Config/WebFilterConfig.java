package com.cysecinnovation.cysecinnovations_gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebFilterConfig {
    @Bean
    public WebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Content-Type", "Accept", "Authorization"));
        config.setExposedHeaders(List.of("Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Max-Age", "Content-Length", "X-Application-Context"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public WebFilter optionsFilter() {
        return (exchange, chain) -> {
            if (exchange.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.OK);
                response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                response.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
                response.getHeaders().add("Access-Control-Allow-Origin", "*");
                return Mono.from(response.setComplete());
            }
            return chain.filter(exchange);
        };
    }
}