package com.brokenFirmChallenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Brokage Firm Challenge API")
                        .version("1.0")
                        .description("API documentation for the Brokage Firm Challenge"));
    }

    @Bean
    GroupedOpenApi all() {
        return GroupedOpenApi.builder().group("all").pathsToMatch("/**/**").build();
    }

    @Bean
    GroupedOpenApi orders() {
        return GroupedOpenApi.builder().group("orders").pathsToMatch("/orders/**").build();
    }

    @Bean
    GroupedOpenApi customers() {
        return GroupedOpenApi.builder().group("customers").pathsToMatch("/customers/**").build();
    }

    @Bean
    GroupedOpenApi assets() {
        return GroupedOpenApi.builder().group("assets").pathsToMatch("/assets/**").build();
    }
}
