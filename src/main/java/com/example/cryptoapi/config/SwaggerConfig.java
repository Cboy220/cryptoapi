package com.example.cryptoapi.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.Collections;

@ApplicationPath("/api")
public class SwaggerConfig extends ResourceConfig {
    public SwaggerConfig() {
        packages("com.example.cryptoapi.controller");
        configureSwagger();
    }

    private void configureSwagger() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Crypto API")
                        .version("1.0")
                        .description("API documentation for Crypto API")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Crypto API Documentation")
                        .url("https://example.com/docs"));

        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(openAPI)
                .prettyPrint(true)
                .resourcePackages(Collections.singleton("com.example.cryptoapi.controller"));

        OpenApiResource openApiResource = new OpenApiResource();
        openApiResource.setOpenApiConfiguration(oasConfig);
        register(openApiResource);
    }
}
