package com.rest.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<GatewayFilterSpec, UriSpec> filterFunc = (f) -> f.addRequestHeader("myHeader", "myHeaderValue")
                .addRequestParameter("myParam", "myValue");
        Function<PredicateSpec, Buildable<Route>> rf1 = (p) -> p.path("/get").filters(filterFunc).uri("http://httpbin.org:80");
        Function<PredicateSpec, Buildable<Route>> rf2 = (p) -> p.path("/currency-exchange/**").filters(filterFunc).uri("lb://currency-exchange/");
        Function<PredicateSpec, Buildable<Route>> rf3 = (p) -> p.path("/currency-conversion/**").filters(filterFunc).uri("lb://currency-conversion/");
        return builder.routes()
                .route(rf2).route(rf1).route(rf3)
                .build();
    }
}
