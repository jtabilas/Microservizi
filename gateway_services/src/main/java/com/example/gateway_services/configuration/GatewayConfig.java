package com.example.gateway_services.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.util.ConditionalOnBootstrapEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account-microservice", r -> r
                        .path("/api/auth/**")
                        .uri("lb://account-microservice"))
                .route("product-microservice", r -> r
                        .path("/api/products", "/api/products/{id}", "/api/addProducts")
                        .uri("lb://product-microservice"))
                .route("cart-microservice", r -> r
                        .path("/api/cart",
                                "/api/cart/{IdCart}",
                                "/api/addCart/{cartId}/{productId}/{quantity}",
                                "/api/deleteItem/{idCart}/{idItemCart}",
                                "/api/deleteAll/{idCart}",
                                "/api/updateItem/{idCart}/{idItemCart}")
                        .uri("lb://cart-microservice"))
                .build();
    }
}
