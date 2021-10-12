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
        Function<PredicateSpec, Buildable<Route>> routeFunction = (p) -> p.path("/get").filters(filterFunc).uri("http://httpbin.org:80");
        return builder.routes()
                .route(routeFunction)
                .build();
    }
}
