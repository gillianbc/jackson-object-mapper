package com.gillianbc.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MagicBeanMaker {
	@Bean
	public Utilities produceUtilitiesBean() {
		System.out.println("******* Making a Utilities bean");
		return new Utilities();
	}
	
	@Bean
	public ObjectMapper produceObjectMapper() {
		System.out.println("******* Making an Object Mapper bean");
		return new ObjectMapper();
	}
}
