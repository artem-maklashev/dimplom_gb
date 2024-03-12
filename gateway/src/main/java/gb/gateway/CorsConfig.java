package gb.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.reactive.CorsUtils;
import reactor.core.publisher.Mono;

@Configuration
public class CorsConfig {

    @Bean
    public GlobalFilter corsFilter() {
        return (exchange, chain) -> {
            var request = exchange.getRequest();

            if (CorsUtils.isCorsRequest(request)) {
                var response = exchange.getResponse();
                var headers = response.getHeaders();
//                headers.add("Access-Control-Allow-Origin", "*");
                headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
                headers.add("Access-Control-Max-Age", "3600");
                headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(exchange);
        };
    }
}
