package com.electrodomesticos.service_ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVentasApplication.class, args);
	}

}
