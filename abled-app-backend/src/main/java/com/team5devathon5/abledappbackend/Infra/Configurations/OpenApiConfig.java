package com.team5devathon5.abledappbackend.Infra.Configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Abled - Without Limits API",
                version = "1.0",
                description = "Documentation for endpoints in Abled - Without Limits"
        )
)
public class OpenApiConfig {
}
