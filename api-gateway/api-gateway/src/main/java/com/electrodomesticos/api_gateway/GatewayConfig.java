package com.electrodomesticos.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-producto", r -> r
                        .path("/service-producto/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://SERVICE-PRODUCTO"))
                .route("service-carrito",r->r
                        .path("/service-carrito/**")
                        .filters(f->f.stripPrefix(1))
                        .uri("lb://SERVICE-CARRITO"))
                .route("service-ventas",r->r
                        .path("/service-ventas/**")
                        .filters(f->f.stripPrefix(1))
                        .uri("lb://SERVICE-VENTAS"))
                .build();
    }
}
