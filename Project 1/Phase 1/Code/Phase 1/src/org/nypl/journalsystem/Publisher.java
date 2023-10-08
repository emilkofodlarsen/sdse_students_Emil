package org.nypl.journalsystem;

import org.nypl.journalsystem.core.IPublisher;

public class Publisher implements IPublisher{
	private String name;
	private String location;
	
	public Publisher(String name, String location) {
		this.name = name;
		this.location = location;
		
	}
	
	public String getName() {
		return name;
	}

	
	public String getLocation() {
		return location;
	}



}
