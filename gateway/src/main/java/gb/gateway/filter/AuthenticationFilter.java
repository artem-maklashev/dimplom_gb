package gb.gateway.filter;

import com.netflix.discovery.DiscoveryClient;
import gb.gateway.GatewayApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private RoutValidator validator;

    private RestTemplate template;

    @Autowired
    public AuthenticationFilter(RoutValidator validator, RestTemplate template) {
        super(Config.class);
        this.validator = validator;
        this.template = template;
    }

//    public AuthenticationFilter() {
//        super((Config.class));
//    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())) {
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }
            try {

//
//                String url = "http://AUTHORIZATION-SERVICE/validate";
//                Map<String, String> params = new HashMap<>();
//                params.put("token", authHeader);
//               template.getForObject(url, String.class, params);
//               template.getForObject("http://AUTHORIZATION-SERVICE/validate?token=" + authHeader, String.class);
               template.getForObject("http://localhost:8079/auth/validate?token=" + authHeader, String.class);
            } catch (Exception e) {
                System.out.println("invalid access ... !");
                e.printStackTrace();
                throw new RuntimeException("un authorized access to application");
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {}
}
