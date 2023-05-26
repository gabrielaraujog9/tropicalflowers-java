package com.tropical.flowers.tropicalflowers;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tropical.flowers.tropicalflowers.models.User;
import com.tropical.flowers.tropicalflowers.repositories.UserRepository;

@SpringBootApplication
public class TropicalflowersApplication {

	public static void main(String[] args) {
	  SpringApplication.run(TropicalflowersApplication.class, args);
	
	}

}
