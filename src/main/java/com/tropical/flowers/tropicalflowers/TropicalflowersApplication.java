package com.tropical.flowers.tropicalflowers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library APIs", version = "1.0", description = "Library Management APIs."))
public class TropicalflowersApplication {
	public static void main(String[] args) {
	  SpringApplication.run(TropicalflowersApplication.class, args);
	}
}
