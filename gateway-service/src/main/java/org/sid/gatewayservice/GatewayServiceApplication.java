package org.sid.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    /* configurer les routes d'une maniere static c'est exactement la meme chose ce qu'on a fait dans application yml
   @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/customers/**").uri("http://localhost:8081/"))
                .route(r->r.path("/products/**").uri("http://localhost:8082/"))
                .build();
    }
*/
    // Configurer les routes d'une maniere dynamique avec discrover eureka service
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

  /*  #pour prendre ce fichier en considération il faut le renomé application dot yml et le placer dans resources
    spring:
    cloud:
    gateway:
    routes:
            - id : r1
    uri : http://localhost:8081/
    predicates :
            - Path= /customers*//**
 - id : r2
 uri : http://localhost:8082/
 predicates :
 - Path= /products/***/

 }
