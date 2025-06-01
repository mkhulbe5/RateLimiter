package com.mohit.ratelimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.mohit.ratelimiter.interceptor.CalculationInterceptor;

@SpringBootApplication
public class RateLimiterProjectApplication {

	@Autowired
	@Lazy
	private CalculationInterceptor interceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/api/calculator/**");
	}

	public static void main(String[] args) {
		SpringApplication.run(RateLimiterProjectApplication.class, args);
	}

}
