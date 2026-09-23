package com.example.background_jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka 
public class BackgroundJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackgroundJobsApplication.class, args);
	}

}
