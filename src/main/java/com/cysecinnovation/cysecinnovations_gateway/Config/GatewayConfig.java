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
                        .path("/api/software/**") // Match all paths under /api/software/
                        .uri("http://localhost:8082")
                )
                .route("another_service_route", r -> r
                        .path("/api/posts/**") // Match all paths under /api/posts/
                        .uri("http://localhost:8083")
                )
                .route("another_service_route", r -> r
                        .path("/api/user/**") // Match all paths under /api/user/
                        .uri("http://localhost:8081")
                )
//                .route("forward_all", r -> r
//                        .path("/**") // Match all paths
//                        .filters(f -> f.rewritePath("/(?<segment>.*)", "/${segment}")) // Rewrite path if needed
//                        .uri("http://localhost:8082") // Forward to any service
//                )
                .build();
    }
}
