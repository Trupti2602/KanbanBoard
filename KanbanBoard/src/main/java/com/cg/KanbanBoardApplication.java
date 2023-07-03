package com.cg;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class KanbanBoardApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();																											
	}
	
	private static final Logger LOG = LogManager.getLogger(KanbanBoardApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KanbanBoardApplication.class, args);
		LOG.info("Kanban Board started.");		
		
	} 
	

	
	
	
	
}
