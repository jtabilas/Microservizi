package com.example.gateway_services.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account-microservice", r -> r
                        .path("/api/auth/**")
                        .uri("lb://account-microservice"))
                .route("product-microservice", r -> r
                        .path("/api/products", "/api/products/{id}", "/api/addProducts")
                        .filters(f -> f.filter(filter))
                        .uri("lb://product-microservice"))
                .route("cart-microservice", r -> r
                        .path("/api/cart",
                                "/api/cart/{IdCart}",
                                "/api/addCart/{cartId}/{productId}/{quantity}",
                                "/api/deleteItem/{idCart}/{idItemCart}",
                                "/api/deleteAll/{idCart}",
                                "/api/updateItem/{idCart}/{idItemCart}")
                        .filters(f -> f.filter(filter))
                        .uri("lb://cart-microservice"))
                .route("order-microservice", r -> r
                        .path("/api/order",
                                "/api/order/{idCart}/{Customer}",
                                "/api/order/{idOrder}",
                                "/api/orders/{surname}",
                                "/api/deleteOrder/{idOrder}")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-microservice"))
                .build();
    }
}
