package com.enfec.bookstatus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enfec.bookstatus.service.JWTFilter;



@Configuration
public class JwtConfig {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	public FilterRegistrationBean<JWTFilter> filterRegistrationBean() {
		FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(jwtFilter);
		filterRegistrationBean.addUrlPatterns("/Ref-Booking/update");
		filterRegistrationBean.addUrlPatterns("/Ref-Booking/save");
		filterRegistrationBean.addUrlPatterns("/Ref-Booking/getDetails/*");
		filterRegistrationBean.addUrlPatterns("/Ref-Booking/deleteBooking/*");
		
		return filterRegistrationBean;
	}
}
