package com.sicredi.vote.infrastructure.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vote Assembly")
                        .description("Application that represents the classes.")
                        .version("v1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Voting system for assemblies")
                        .url("https://github.com/GiovanniFrozza")
                );
    }
}