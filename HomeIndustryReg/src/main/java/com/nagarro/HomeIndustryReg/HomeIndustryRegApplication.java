package com.nagarro.HomeIndustryReg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@ComponentScan(basePackages = { "com.nagarro.HomeIndustryReg"} )
@EnableJpaRepositories
@SpringBootApplication
public class HomeIndustryRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeIndustryRegApplication.class, args);
	}

}
