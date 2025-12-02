package com.charge.chargeManeger;

import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChargeManegerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ChargeManegerApplication.class);
	}

	//executa database via cli
	public static void main(String[] args) {
		SpringApplication.run(ChargeManegerApplication.class, args);
	}
}