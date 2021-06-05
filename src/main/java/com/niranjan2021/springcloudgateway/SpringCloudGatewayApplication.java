package com.niranjan2021.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator helloRoute(RouteLocatorBuilder builder) {

        return builder.routes().route(r -> r
                .path("/hello/**")
                .filters(f -> f.addRequestHeader("x-location", "USA")).
                        uri("http://localhost:8010/")).build();
    }

    @Bean
    public RouteLocator goodByeRoute(RouteLocatorBuilder builder) {

        return builder.routes().route(r -> r.path("/goodbye/**").
                uri("http://localhost:8011/")
        ).build();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .build();
    }

}
