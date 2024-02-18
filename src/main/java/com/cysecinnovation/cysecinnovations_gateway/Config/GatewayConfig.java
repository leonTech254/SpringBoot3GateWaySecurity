package com.cysecinnovation.cysecinnovations_gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service_route", r -> r
                        .path("/api/software/**")
                        .uri("http://localhost:8082")
                )
                .route("another_service_route", r -> r
                        .path("/api/posts/**")
                        .uri("http://localhost:8083")
                )
                .route("another_service_route", r -> r
                        .path("/api/user/**") // Match all paths under /api/user/
                        .uri("http://localhost:8081")
                )

                .build();
    }
}
