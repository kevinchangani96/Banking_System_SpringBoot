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
                    .title("Demo for Implement Swagger")
                    .version("v1")
                    .description("Hello guys, chalo bank lutne")
                    .termsOfService("https://springdoc.org/")
                    .license(
                            new License()
                                    .name("OpenSource")
                                    .url("https://springdoc.org/")
                    )
    );
  }

}
