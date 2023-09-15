package org.maklashev.goldengroup.config;

import org.maklashev.goldengroup.model.entity.Product;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class MethodDataRestConfig implements RepositoryRestConfigurer {

    private String clientUrl = Environment.getInstance().getHost();

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = {
                // HttpMethod.POST,
                // HttpMethod.DELETE,
                // HttpMethod.PUT
        };

        config.exposeIdsFor(Product.class);

        disableHttpMethods(Product.class, config, unsupportedActions);

        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(clientUrl);

    }

    private void disableHttpMethods(
            Class<?> self,
            RepositoryRestConfiguration config,
            HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(self)
                .withItemExposure(
                        (metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure(
                        (metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }
}