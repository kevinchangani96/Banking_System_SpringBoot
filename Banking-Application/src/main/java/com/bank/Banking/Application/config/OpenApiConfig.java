package com.bank.Banking.Application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  OpenAPI customOpenApi() {
    return new OpenAPI().info(
            new Info()
                    .title("Banking System API")
                    .version("v1")
                    .description("Welcome to the Banking System API. This API provides a range of features for managing your banking needs, including account creation, transactions, and more.")
                    .termsOfService("https://springdoc.org/")
                    .license(
                            new License()
                                    .name("Source Code")
                                    .url("https://github.com/kevinchangani96/Addzero_springbooot/tree/main/Banking-Application")
                    )
    );
  }

}
