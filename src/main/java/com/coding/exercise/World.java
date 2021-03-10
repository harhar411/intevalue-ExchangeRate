package com.coding.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = "com.coding.exercise.component")
@EntityScan(basePackages = "com.coding.exercise.entity")
public class World {
	
	public static void main(String... args) {
		SpringApplication.run(World.class, args);
	}
	
}
