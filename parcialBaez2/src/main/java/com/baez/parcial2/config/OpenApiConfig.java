package com.baez.parcial2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("CRM API - Parcial Baez 2").version("1.0.0").description("API REST de clientes, contactos, oportunidades y usuarios"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .schemaRequirement("bearerAuth", new SecurityScheme().name("bearerAuth").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
    }
}
