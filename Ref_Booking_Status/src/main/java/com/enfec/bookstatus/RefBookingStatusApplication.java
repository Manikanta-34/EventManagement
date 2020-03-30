package com.enfec.bookstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/************************************************
 *
 * Author: Venkat
 * Class: RefBookingStatusApplication
 *
<<<<<<< HEAD
 **************************************************/
=======
 ************************************************/
>>>>>>> branch 'master' of https://github.com/Manikanta-34/EventManagement.git
@SpringBootApplication
@EnableSwagger2
public class RefBookingStatusApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RefBookingStatusApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RefBookingStatusApplication.class);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.enfec.bookstatus")).build();

	}

}
