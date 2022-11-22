package com.centime.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MainServiceApplication {

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
