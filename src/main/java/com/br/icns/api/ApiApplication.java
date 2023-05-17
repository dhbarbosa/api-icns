package com.br.icns.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		System.out.println("API_SECRET: "+System.getenv("API_SECRET"));
		System.out.println("API_ISSUER: "+System.getenv("API_ISSUER"));
		System.out.println("USERBANCO: "+System.getenv("POSTGRES_USER"));
		System.out.println("PASSWORD: "+System.getenv("POSTGRES_PASSWORD"));
		System.out.println("PORT: "+System.getenv("PORT"));
		System.out.println("BD: "+System.getenv("POSTGRES_DB"));
		System.out.println("IP_BD: "+System.getenv("IP_BD"));
		SpringApplication.run(ApiApplication.class, args);
	}

}
