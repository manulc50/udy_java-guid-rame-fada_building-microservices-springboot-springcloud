package net.mlorenzo.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

// Esta anotación escanea el proyecto en busca de interfaces declaradas como clientes OpenFeign, es decir,
// interfaces marcadas con la anotación "@FeignClient"
@EnableFeignClients
@EnableEurekaClient // Opcional
@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	// Se comenta porque ahora usamos el cliente WebClient
	/*
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

	@LoadBalanced
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
