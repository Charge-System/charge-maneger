package com.charge.chargeManeger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class ChargeManegerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ChargeManegerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ChargeManegerApplication.class, args);
	}

}
