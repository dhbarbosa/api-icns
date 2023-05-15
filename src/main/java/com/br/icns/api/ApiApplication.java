package com.br.icns.api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("API_SECRET"));
		System.out.println(System.getenv("API_ISSUER"));
		System.out.println(System.getenv("USERBANCO"));
		System.out.println(System.getenv("PASSWORD"));
		System.out.println(System.getenv("PORT"));
		System.out.println(System.getenv("BD"));
		SpringApplication.run(ApiApplication.class, args);
	}

}
