package com.ratracks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class RatracksApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatracksApiApplication.class, args);
	}

}
