package com.nayoon.api_gateway.config;

import com.nayoon.api_gateway.filter.AuthorizationHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestFilterConfig {

  @Bean
  public RouteLocator testGatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.path("/test/user-service/auth/**")
            .filters(f -> f.rewritePath("/test/user-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://USER-SERVICE"))
        .route(r -> r.path("/test/user-service/signup")
            .filters(f -> f.rewritePath("/test/user-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://USER-SERVICE"))
        .route(r -> r.path("/test/user-service/users/**")
            .filters(f -> f
                .rewritePath("/test/user-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://USER-SERVICE"))
        .route(r -> r.path("/test/product-service/**")
            .filters(f -> f
                .rewritePath("/test/product-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://PRODUCT-SERVICE"))
        .route(r -> r.path("/test/purchase-service/**")
            .filters(f -> f
                .rewritePath("/test/purchase-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://PURCHASE-SERVICE"))
        .route(r -> r.path("/test/payment-service/**")
            .filters(f -> f
                .rewritePath("/test/payment-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://PAYMENT-SERVICE"))
        .route(r -> r.path("/test/stock-service/**")
            .filters(f -> f
                .rewritePath("/test/stock-service/(?<segment>.*)", "/api/v1/${segment}"))
            .uri("lb://STOCK-SERVICE"))
        .build();
  }

}
