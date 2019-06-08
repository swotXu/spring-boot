package com.xuh.meituan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xuh.meituan"})
public class MeituanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeituanApplication.class, args);
	}

}
