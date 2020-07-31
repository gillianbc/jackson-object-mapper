package com.gillianbc;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gillianbc.model.Car;
import com.gillianbc.utils.Utilities;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
public class JacksonApplication implements CommandLineRunner{
	
	@Autowired
	private Utilities utils;
	
	@Autowired
	private ObjectMapper objectMapper;
	

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
	
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		
		SpringApplication.run(JacksonApplication.class, args);
		

	}

	@Override
	public void run(String... args) throws Exception {
		if (utils == null)
			System.out.println("Where's my effing bean, Mr Spring?");
		if (objectMapper == null)
			System.out.println("Where's my effing object mapper bean, Mr Spring?");
		
		serializeCarToFile();
		
		deserializeCarFromFile();
		
		deserializeCarFromString();
		
		extractJsonNode();
		
	}

	private void extractJsonNode() throws JsonProcessingException, JsonMappingException {
		
		System.out.println("\n===== JsonNode Examples =====");
		String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
		utils.printPrettyJSON(json);
		JsonNode jsonNode = objectMapper.readTree(json);
		System.out.println("Getting node with property `color`: " + jsonNode.get("color").asText());
		
		System.out.println("\nNow let's read in a json file and get individual nodes using path(): ");
		json = utils.readJsonFile("json2.json");
		
		jsonNode = objectMapper.readTree(json);
		
		utils.printPrettyJSON(json);
		System.out.println("Rootnode is " + jsonNode);
		
		JsonNode locatedNode = jsonNode.path("identification").path("name");
		System.out.println("identification/name node is " + locatedNode);
		
		locatedNode = jsonNode.path("identification").path("address");
		System.out.println("identification/address node is " + locatedNode);
		
		// See how we use method chaining to drill down the hierarchy
		locatedNode = jsonNode
				.path("identification")
				.path("address")
				.path("number");
		System.out.println("identification/address/number node is " + locatedNode);
		
	}
	
	private void deserializeCarFromString() throws JsonMappingException, JsonProcessingException {
		System.out.println("\n=== Deserializing from string ===");
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Car car = objectMapper.readValue(json, Car.class);
		System.out.println("Car object deserialized from JSON string of " + json + " is: " + car);
	}

	private void serializeCarToFile() throws IOException, JsonGenerationException, JsonMappingException {
		System.out.println("\n=== Serializing to file ===");
		Car car = new Car("yellow", "renault");
		System.out.println("Writing car object to JSON file: " + car);
		
		
		utils.printPrettyJSON(objectMapper.writeValueAsString(car));
		
		objectMapper.writeValue(new File("src/test/resources/json_car.json"), car);
	}

	private void deserializeCarFromFile() throws IOException, JsonGenerationException, JsonMappingException {
		System.out.println("\n=== Deserializing from file ===");
		Car car = objectMapper.readValue(new File("src/test/resources/json_car.json"), Car.class);
		System.out.println("Car object read back in from JSON file: " + car);
	}
	
	
	
	

}
