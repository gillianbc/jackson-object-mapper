package com.gillianbc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
		
		deserializeCarFromJsonUrl();
		
		deserializeCarFromString();
		
		extractJsonNode();
		
		deserializeCarFromFileIntoArray();
		
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
		
		JsonNode locatedNode = jsonNode.path("identification");
		System.out.println("identification node is " + locatedNode);
		
		locatedNode = jsonNode.path("identification").path("name");
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
		System.out.println("Car object deserialized from JSON string of " + json + " is:\n" + car);
	}
	
	private void deserializeCarFromJsonUrl() throws JsonMappingException, JsonProcessingException {
		System.out.println("\n=== Deserializing from url json ===");
		Car car;
		try {
			car = objectMapper.readValue(new URL("file:src/test/resources/json_car.json"), Car.class);
			System.out.println("Car object deserialized from url is: " + car);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	private void deserializeCarFromFileIntoArray() throws IOException, JsonGenerationException, JsonMappingException {
		System.out.println("\n=== Deserializing from file into Array ===");
		
		String json = utils.readFileIntoString("src/test/resources/json_cars.json");
		
		JsonNode carsNode = objectMapper.readTree(json).path("cars");
		
		System.out.println(" Cars is " + carsNode);
		
		List<Car> listCar = 
				objectMapper.readValue(carsNode.toString(), new TypeReference<List<Car>>(){});
		System.out.println("Cars list loaded in from JSON file: " + listCar);
	}
	
	
	
	

}
