package com.carol_lewandowski.gambling_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan(basePackages = "com.carol_lewandowski.gambling_control.model")
@SpringBootApplication
public class GamblingControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamblingControlApplication.class, args);
	}

}
