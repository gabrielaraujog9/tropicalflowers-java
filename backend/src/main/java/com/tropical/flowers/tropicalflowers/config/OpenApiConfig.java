package com.tropical.flowers.tropicalflowers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration  
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class OpenApiConfig {
  @Bean
  OpenAPI usersMicroserviceOpenAPI() {
      return new OpenAPI()
              .info(new Info()
                .title("Tropical Flowers - API")
                .description("API de uma loja de flores famosa em todo território nacional, só que ninguém conhece!")
                .version("1.0")
              );
  }
}
