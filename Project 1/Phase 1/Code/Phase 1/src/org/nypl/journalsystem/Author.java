package org.nypl.journalsystem;

public class Author {
	
	private int id;
	private String name;
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public void display() {
		System.out.println(name);
	}

}
