package com.localnews.lajmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LajmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LajmiApplication.class, args);
	}

}
