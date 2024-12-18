package com.localnews.ankesa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnkesaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnkesaApplication.class, args);
	}

}
