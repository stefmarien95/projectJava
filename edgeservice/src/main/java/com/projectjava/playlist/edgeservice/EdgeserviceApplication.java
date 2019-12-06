package com.projectjava.playlist.edgeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectjava.playlist.edgeservice.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class EdgeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeserviceApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	@Bean
	public JwtConfig getJwtConfig(){return new JwtConfig(); }
}
