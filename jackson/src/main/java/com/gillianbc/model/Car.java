package com.gillianbc.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
	 
    public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String color;
    private String type;
    
	public Car(String color, String type) {
		super();
		this.color = color;
		this.type = type;
	}
 
    
}
