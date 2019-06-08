package com.xuh.ticket12306;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuh.ticket12306.*.dao")
public class Ticket12306Application {

	public static void main(String[] args) {
		SpringApplication.run(Ticket12306Application.class, args);
	}

}
