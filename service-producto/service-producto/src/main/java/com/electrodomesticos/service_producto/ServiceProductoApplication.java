package com.electrodomesticos.service_producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductoApplication.class, args);
	}

}
