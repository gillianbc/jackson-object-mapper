package com.gillianbc.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Utilities {
	
	
	public Utilities() {
		super();
	}

	public String readJsonFile(String filename){
	    
	    String data = null;
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/" + filename);
		    data = IOUtils.toString(fis, "UTF-8");
		} catch (IOException e) {
			System.out.println("File not found " + filename);
		}
		return data;
	}
	
	public void printPrettyJSON(String jsonString) throws JsonProcessingException {
		
		JSONObject json = new JSONObject(jsonString); // Convert text to object
		System.out.println(json.toString(4)); // Print it with specified indentation
	}
}
