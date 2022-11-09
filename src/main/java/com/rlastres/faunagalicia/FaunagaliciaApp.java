package com.rlastres.faunagalicia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.rlastres.faunagalicia")
public class FaunagaliciaApp {

	public static void main(String[] args) {
		SpringApplication.run(FaunagaliciaApp.class, args);
	}

}
