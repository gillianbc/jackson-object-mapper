package com.gillianbc.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

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
	
	public String readFileIntoString(String filename) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		try {
			while (reader.read(buffer) != -1) {
				stringBuilder.append(new String(buffer));
				buffer = new char[10];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}
}
