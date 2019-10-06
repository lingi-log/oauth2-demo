package com.oauth2resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuth2ResourceServer {

	public static void main(String[] args) {
        System.out.println("OAuth 2 Resource Server");
		SpringApplication.run(OAuth2ResourceServer.class, args);
	}

}
