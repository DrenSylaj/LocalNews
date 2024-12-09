package com.LocalNews.Komenti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = "com.LocalNews.Komenti.client")
public class KomentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomentiApplication.class, args);
	}

}
