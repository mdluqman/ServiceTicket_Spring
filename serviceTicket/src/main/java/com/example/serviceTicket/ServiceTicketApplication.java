package com.example.serviceTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"DaoClasses", "BusinessClasses","com.example.serviceTicket","RestControllers"})
@EntityScan(basePackages = {"Beans"})
@EnableJpaRepositories(basePackages = {"repositories"})
public class ServiceTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTicketApplication.class, args);
	}

}
