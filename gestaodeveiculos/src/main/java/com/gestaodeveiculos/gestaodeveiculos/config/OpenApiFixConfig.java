package com.gestaodeveiculos.gestaodeveiculos.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiFixConfig {

    @Bean
    public OpenApiCustomizer sortParameterRemover() {
        return openApi -> {
            // Remove parâmetros Sort problemáticos de todos os endpoints
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((path, pathItem) -> {
                    pathItem.readOperations().forEach(operation -> {
                        if (operation.getParameters() != null) {
                            operation.getParameters().removeIf(param -> {
                                String paramName = param.getName();
                                return paramName != null &&
                                        (paramName.equals("sort") ||
                                                paramName.contains("Sort") ||
                                                paramName.toLowerCase().contains("sort"));
                            });
                        }
                    });
                });
            }
        };
    }
}