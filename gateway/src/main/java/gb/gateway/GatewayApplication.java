package gb.gateway;

import gb.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
//    @Autowired
//    private AuthenticationFilter filter;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
//        return builder.routes()
//                .route("backend", r -> r.path("/api/**")
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
//                        .uri("http://localhost:8078/"))
//                .route("auth", r -> r.path("/auth/**")
//                        .uri("http://localhost:8079/"))
//                .build();
//    }
//
//    @Bean
//    public GatewayFilter authenticationFilter() {
//        return new AuthenticationFilter().apply(new AuthenticationFilter.Config());
//    }
}
