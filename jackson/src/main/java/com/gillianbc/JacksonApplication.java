package com.gillianbc;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gillianbc.model.Car;

@SpringBootApplication
public class JacksonApplication {
	static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		
		SpringApplication.run(JacksonApplication.class, args);
		
		serializeCar();
		
		deserializeCar();
		

	}

	private static void serializeCar() throws IOException, JsonGenerationException, JsonMappingException {
		
		Car car = new Car("yellow", "renault");
		System.out.println("Writing car to JSON file: " + car);
		
		printAsJSON(car);
		
		objectMapper.writeValue(new File("src/test/resources/json_car.json"), car);
	}

	private static void printAsJSON(Car car) throws JsonProcessingException {
		String jsonString = objectMapper.writeValueAsString(car);
		JSONObject json = new JSONObject(jsonString); // Convert text to object
		System.out.println(json.toString(4)); // Print it with specified indentation
	}
	
	private static void deserializeCar() throws IOException, JsonGenerationException, JsonMappingException {
		Car car = objectMapper.readValue(new File("src/test/resources/json_car.json"), Car.class);
		System.out.println("Car read back in from JSON file: " + car);
	}

}
