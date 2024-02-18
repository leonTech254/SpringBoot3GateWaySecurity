# Spring Boot 3 Gateway
## Overview

This Spring Boot 3 Gateway is designed to handle CORS (Cross-Origin Resource Sharing) and Authorization headers. The provided configuration allows requests from any origin (*) and defines the allowed methods and headers.


## Configuration
The WebFilterConfig class in the com.cysecinnovation.cysecinnovations_gateway.Config package provides the necessary configuration. It includes a WebFilter bean named optionsFilter that intercepts OPTIONS requests and sets the appropriate CORS headers.


```
package com.cysecinnovation.cysecinnovations_gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration
public class WebFilterConfig {
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
```

### CORS and Authorization Configuration
To enable CORS (Cross-Origin Resource Sharing) and handle Authorization headers in your Spring Boot application, you can use the provided WebFilterConfig class. This configuration ensures that requests from different origins are handled appropriately.


### Usage

1. Copy the WebFilterConfig class into your project's package structure.
2. Make sure your Spring Boot application scans the package containing the WebFilterConfig class for component scanning.
3. Upon application startup, the optionsFilter bean defined in the WebFilterConfig class will be registered as a global filter to handle OPTIONS requests and set the necessary CORS headers.

## CORS Configuration

1. Allowed Methods: GET, POST, PUT, DELETE, OPTIONS
2. Allowed Headers: Content-Type, Accept, Authorization
3. Allowed Origin: *