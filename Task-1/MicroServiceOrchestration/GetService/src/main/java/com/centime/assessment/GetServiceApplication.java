package com.centime.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetServiceApplication.class, args);
	}

}
